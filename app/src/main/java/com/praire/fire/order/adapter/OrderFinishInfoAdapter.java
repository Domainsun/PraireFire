package com.praire.fire.order.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.order.OrderUtils;
import com.praire.fire.order.bean.OrderInfoBean;
import com.praire.fire.utils.TextViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lyp on 2018/1/2.
 */

public class OrderFinishInfoAdapter extends RecyclerView.Adapter<OrderFinishInfoAdapter.MyViewHolder> {


    private Context context;
    private List<OrderInfoBean.OrderlistBean.PslistBean> entities = new ArrayList<>();


    public OrderFinishInfoAdapter(Context context) {
        this.context = context;
    }

    public void setEntities(List<OrderInfoBean.OrderlistBean.PslistBean> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                context).inflate(R.layout.item_item_order_list, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final OrderInfoBean.OrderlistBean.PslistBean bean = entities.get(position);
        holder.itemView.setTag(position);


        holder.productName.setText(bean.getName());
        holder.count.setText(String.format(holder.count.getTag().toString(), bean.getNumber()));
        holder.type.setText(String.format(holder.type.getTag().toString(), bean.getClasspath()));
        holder.nprice.setText(String.format(holder.nprice.getTag().toString(), bean.getNprice()));
        holder.sprice.setText(String.format(holder.sprice.getTag().toString(), bean.getPrice()));
                //添加删除线
        holder.nprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                if ("1".equals(bean.getType())) {
                    holder.img.setVisibility(View.VISIBLE);
                    Uri uri = Uri.parse(bean.getCover());
                    DraweeController controller = Fresco.newDraweeControllerBuilder()
                            .setUri(uri)
                            .setAutoPlayAnimations(true)
                            .build();
                    holder.img.setController(controller);
                }




    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView img ;
        TextView productName  ;
        TextView count ;
        TextView type  ;
        TextView nprice ;
        TextView sprice  ;


        public MyViewHolder(View view) {
            super(view);
              img = view.findViewById(R.id.item_shop_list_img);
              productName = view.findViewById(R.id.item_order_list_name);
              count = view.findViewById(R.id.item_order_list_count);
              type = view.findViewById(R.id.item_order_list_type);
              nprice = view.findViewById(R.id.item_order_list_nprice);
              sprice = view.findViewById(R.id.item_order_list_sprice);


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
