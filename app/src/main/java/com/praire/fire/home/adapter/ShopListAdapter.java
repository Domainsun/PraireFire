package com.praire.fire.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.model.LatLng;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.common.Constants;
import com.praire.fire.home.bean.ShopListBean;
import com.praire.fire.utils.CalculateUtils;
import com.praire.fire.utils.SharePreferenceMgr;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 评价列表适配器
 */

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.MyViewHolder> implements View.OnClickListener {

    private Context context;
    private List<ShopListBean.PagelistBean> entities = new ArrayList<>();
    private AMapLocation location;
    private LatLng startlatLng;
    private String distance = "0km";
    private double latitude;
    private double longitude;

    public ShopListAdapter(Context context) {
        this.context = context;

    }

    public void setEntities(List<ShopListBean.PagelistBean> entities, double longitude, double latitude) {
        this.entities = entities;
        this.longitude = longitude;
        this.latitude = latitude;
        startlatLng = new LatLng(latitude, longitude);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_shop_list, parent,
                false));
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
        ShopListBean.PagelistBean item = entities.get(position);
        holder.itemShopListName.setText(item.getName());
        holder.itemShopListStar.setRating(Float.valueOf(item.getStar()));
        holder.itemShopListScroe.setText(item.getStar() + "分");
        holder.itemShopListTime.setText(item.getOpentime());
        holder.itemShopListInfo.setText(item.getDesc());
        holder.itemShopListAddress.setText(item.getAddress());

        LatLng latLng2 = new LatLng(Double.valueOf(item.getLat()), Double.valueOf(item.getLng()));
//          latitude = (Double) SharePreferenceMgr.get(context, Constants.Latitude, 0.0);
//          longitude = (Double) SharePreferenceMgr.get(context, Constants.Longitude, 0.0);


        distance = CalculateUtils.showDistance(AMapUtils.calculateLineDistance(startlatLng, latLng2));
        holder.itemShopListDistance.setText(distance);
        holder.itemShopListAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Uri uri = Uri.parse(item.getDoor());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        holder.itemShopListImg.setController(controller);
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.item_shop_list_img)
        SimpleDraweeView itemShopListImg;
        @BindView(R.id.item_shop_list_name)
        TextView itemShopListName;
        @BindView(R.id.item_shop_list_star)
        RatingBar itemShopListStar;
        @BindView(R.id.item_shop_list_scroe)
        TextView itemShopListScroe;
        @BindView(R.id.item_shop_list_time)
        TextView itemShopListTime;
        @BindView(R.id.item_shop_list_info)
        TextView itemShopListInfo;
        @BindView(R.id.item_shop_list_address)
        TextView itemShopListAddress;
        @BindView(R.id.item_shop_list_distance)
        TextView itemShopListDistance;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    private OnItemClickListener mOnItemClickListener;

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}

