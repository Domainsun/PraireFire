package com.praire.fire.order.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.order.bean.OrderListBean;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lyp on 2018/1/2.
 */

public class OrderListItemAdapter extends RecyclerView.Adapter<OrderListItemAdapter.MyViewHolder> {


    private Context context;
    private List<OrderListBean.PagelistBean.PslistBean> entities = new ArrayList<>();

    public OrderListItemAdapter(Context context) {
        this.context = context;
    }

    public void setEntities(List<OrderListBean.PagelistBean.PslistBean> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_item_order_list, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OrderListBean.PagelistBean.PslistBean bean = entities.get(position);
        holder.itemOrderListName.setText(bean.getName());
        holder.itemOrderListPrice.setText(bean.getPrice());

        holder.itemOrderListCount.setText(bean.getNumber());

        Uri uri = Uri.parse(bean.getCover());
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
        @BindView(R.id.item_order_list_name)
        TextView itemOrderListName;
        @BindView(R.id.item_order_list_count)
        TextView itemOrderListCount;
        @BindView(R.id.item_order_list_price)
        TextView itemOrderListPrice;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
