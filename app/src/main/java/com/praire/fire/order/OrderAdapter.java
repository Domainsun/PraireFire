package com.praire.fire.order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.praire.fire.home.bean.ShopBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lyp on 2018/1/2.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    private Context context;
    private List<ShopBean.PagelistBean> entities = new ArrayList<>();
    public OrderAdapter(Context context) {
        this.context = context;
    }

    public void setEntities(List<ShopBean.PagelistBean> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
