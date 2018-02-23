package com.praire.fire.merchant.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.merchant.AddProductActivity;
import com.praire.fire.merchant.AddServiceActivity;
import com.praire.fire.okhttp.JavaBean.ProductListBean;
import com.praire.fire.okhttp.JavaBean.ServiceListBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunlo on 2018/1/5.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    Context context;
    private Map viewHolderMap = new HashMap<String,ImageView>();



    private List<ProductListBean.PagelistBean> data;
    private LayoutInflater inflater;

    public ProductAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    private OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(int position, View view, String id, String status);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.item_product, parent,
                false));

        return holder;
    }


    Integer p = null;

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.setIsRecyclable(false);

        holder.iv.setVisibility(data.get(position).getStatus().equals("0") ? View.VISIBLE : View.INVISIBLE);
        holder.tvDelete.setText(data.get(position).getStatus().equals("0") ?  "上架" : "下架");


        holder.tvName.setText(data.get(position).getName());
        holder.tvPrice.setText("¥ " + data.get(position).getNprice());
        holder.saled.setText("已售" + data.get(position).getSalecount());
        holder.productPhoto.setImageURI(data.get(position).getCover());


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
                mOnItemClickListener.onItemClick(position, view, data.get(position).getId(), status);
            }
        });

        holder.tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  productId = data.get(position).getId();
                Intent i = new Intent(context, AddProductActivity.class);
                i.putExtra("data",productId);
                context.startActivity(i);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  productId = data.get(position).getId();
                Intent i = new Intent(context, AddProductActivity.class);
                i.putExtra("data",productId);
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<ProductListBean.PagelistBean> Datas) {
        data = Datas;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_photo)
        SimpleDraweeView productPhoto;
        @BindView(R.id.tv_name)
        TextView tvName;
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
