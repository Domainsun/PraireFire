package com.praire.fire.car.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.car.bean.CarBean;
import com.praire.fire.common.Constants;
import com.praire.fire.order.OrderUtils;
import com.praire.fire.order.bean.OrderListBean;
import com.praire.fire.utils.CalculateUtils;
import com.praire.fire.utils.SharePreferenceMgr;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lyp on 2018/1/2.
 */

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {



    private Context context;
    private List<CarBean.PagelistBean> entities = new ArrayList<>();


    public CarAdapter(Context context) {
        this.context = context;
    }

    public void setEntities(List<CarBean.PagelistBean> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                context).inflate(R.layout.item_car, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final CarBean.PagelistBean bean = entities.get(position);
        holder.itemView.setTag(position);
        holder.itemCarName.setText(bean.getName());
        holder.itemCarStar.setRating(Float.valueOf(bean.getStar()));
        holder.itemCarScroe.setText(bean.getStar()+"分");
        holder.itemCarInfo.setText(bean.getName());
        holder.itemCarTimes.setText(bean.getOpentime());
        holder.itemCarDistance.setText((String)SharePreferenceMgr.get(context , Constants.CITY,"")+bean.getDistance()+"km");
        //防止滑动的时候重复显示数据
        if (holder.isFrist) {
            for (int i = 0; i < bean.getProduct().size(); i++) {
                ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.item_car_item, null);
//                SimpleDraweeView img = viewGroup.findViewById(R.id.item_shop_list_img);
                TextView productName = viewGroup.findViewById(R.id.item_car_item_name);
                TextView price = viewGroup.findViewById(R.id.item_car_price);
                TextView saled = viewGroup.findViewById(R.id.item_car_item_saled);
                productName.setText(bean.getProduct().get(i).getName());
                price.setText(String.format(price.getTag().toString(), bean.getProduct().get(i).getNprice()));
                saled.setText(String.format(saled.getTag().toString(),bean.getProduct().get(i).getSalecount()));
//                viewGroup.setOnClickListener(new);

                holder.addServiceLl.addView(viewGroup);
            }
            for (int i = 0; i < bean.getService().size(); i++) {
                ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.item_car_item, null);
                TextView productName = viewGroup.findViewById(R.id.item_car_item_name);
                TextView price = viewGroup.findViewById(R.id.item_car_price);
                TextView saled = viewGroup.findViewById(R.id.item_car_item_saled);

                productName.setText(bean.getService().get(i).getName());
                price.setText(String.format(price.getTag().toString(), bean.getService().get(i).getNprice()));
                saled.setText(String.format(saled.getTag().toString(),bean.getService().get(i).getSalecount()));
                holder.addServiceLl.addView(viewGroup);
            }
            holder.isFrist = false;
        }

            Uri uri = Uri.parse(bean.getDoor());
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setAutoPlayAnimations(true)
                    .build();
            holder.itemCarImg.setController(controller);

    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_car_img)
        SimpleDraweeView itemCarImg;
        @BindView(R.id.item_car_name)
        TextView itemCarName;
        @BindView(R.id.item_car_star)
        RatingBar itemCarStar;
        @BindView(R.id.item_car_scroe)
        TextView itemCarScroe;
        @BindView(R.id.item_car_info)
        TextView itemCarInfo;
        @BindView(R.id.item_car_distance)
        TextView itemCarDistance;
        @BindView(R.id.item_car_times)
        TextView itemCarTimes;
        @BindView(R.id.add_service_ll)
        LinearLayout addServiceLl;

        boolean isFrist = true;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

    }

    ItemClickLister itemClickLister;

    public void setItemClickLister(ItemClickLister itemClickLister) {
        this.itemClickLister = itemClickLister;
    }

    public interface ItemClickLister {
        void cancel(String status, String orderId);

        void btnStatus(String status, String orderno, String orderId, String paycost);
    }
}
