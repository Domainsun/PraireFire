package com.praire.fire.merchant.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.praire.fire.R;
import com.praire.fire.merchant.AddServiceActivity;
import com.praire.fire.okhttp.JavaBean.ServiceListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunlo on 2018/1/5.
 */

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyViewHolder> {

    Context context;

    private List<ServiceListBean.PagelistBean> data;
    private LayoutInflater inflater;

    public ServiceAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    private OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(int position,View view, String id, String status);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.item_service, parent,
                false));

        return holder;
    }


    Integer p = null;

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvName.setText(data.get(position).getName());
        holder.tvIntroduce.setText(data.get(position).getDesc());
        holder.tvPrice.setText("¥ " + data.get(position).getNprice());
        holder.saled.setText("已售" + data.get(position).getSalecount());


        holder.iv.setVisibility(data.get(position).getStatus().equals("0") ? View.VISIBLE : View.INVISIBLE);
        holder.tvDelete.setText(data.get(position).getStatus().equals("0") ?  "上架" : "下架");

//        if (data.get(position).getStatus().equals("0")) {
//            p = position;
//        }
//
//        if (p != null) {
//            holder.iv.setVisibility(position == p ? View.VISIBLE : View.INVISIBLE);
//            holder.tvDelete.setText(position == p ? "上架" : "下架");
//        }


        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String status = "0";
                if (data.get(position).getStatus().equals("0")) {
                    status = "1";
                }
                mOnItemClickListener.onItemClick(position,view, data.get(position).getId(), status);
            }
        });

        holder.tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceListBean.PagelistBean s = data.get(position);
                Intent i = new Intent(context, AddServiceActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("data", s);
                b.putString("tab", "1");
                i.putExtras(b);
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<ServiceListBean.PagelistBean> Datas) {
        data = Datas;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_introduce)
        TextView tvIntroduce;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.saled)
        TextView saled;
        @BindView(R.id.tv_change)
        TextView tvChange;
        @BindView(R.id.tv_delete)
        TextView tvDelete;
        @BindView(R.id.iv)
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }
}
