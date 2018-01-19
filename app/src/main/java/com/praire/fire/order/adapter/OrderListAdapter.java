package com.praire.fire.order.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.praire.fire.R;
import com.praire.fire.order.bean.OrderListBean;
import com.praire.fire.utils.RecycleViewDivider;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lyp on 2018/1/2.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder> {


    private Context context;
    private List<OrderListBean.PagelistBean> entities = new ArrayList<>();

    public OrderListAdapter(Context context) {
        this.context = context;
    }

    public void setEntities(List<OrderListBean.PagelistBean> entities) {
        this.entities = entities;
        Log.e("orderli——adapter","orderli——adapter");
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_order_list, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OrderListBean.PagelistBean bean = entities.get(position);
        Log.e("OrderListAdapter","8888888==");
      holder.itemOrderListBusinessname.setText(bean.getShopname());
        holder.itemOrderListOrderId.setText(bean.getOrderno());
        holder.itemOrderListClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.itemOrderListStatus0.setText(bean.getStatus());
        holder.itemOrderListStatusBtn.setText(bean.getStatus());
        holder.itemOrderListStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        holder.recyclerview.setLayoutManager(new LinearLayoutManager(context));
        //添加分割线
        holder.recyclerview.addItemDecoration(new RecycleViewDivider(
                context, LinearLayoutManager.HORIZONTAL));
        OrderListItemAdapter adapter = new OrderListItemAdapter(context);
        holder.recyclerview.setAdapter(adapter);
        adapter.setEntities(bean.getPslist());

    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_order_list_businessname)
        TextView itemOrderListBusinessname;
        @BindView(R.id.item_order_list_orderId)
        TextView itemOrderListOrderId;
        @BindView(R.id.item_order_list_status0)
        TextView itemOrderListStatus0;
//        @BindView(R.id.item_order_list_recyclerview)
        SwipeMenuRecyclerView recyclerview;
        @BindView(R.id.item_order_list_clean)
        TextView itemOrderListClean;
        @BindView(R.id.item_order_list_status_btn)
        TextView itemOrderListStatusBtn;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            recyclerview  = view.findViewById(R.id.item_order_list_recyclerview);
        }

    }
}
