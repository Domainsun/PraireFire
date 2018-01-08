package com.praire.fire.merchant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.praire.fire.R;
import com.praire.fire.merchant.bean.RegionListBean;
import com.praire.fire.merchant.bean.ShopTypeBeanList;

import java.util.List;

/**
 * Created by sunlo on 2018/1/5.
 */

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.MyViewHolder>  {


    private List<RegionListBean.ListBean.SonBeanX.SonBean> data;
    private LayoutInflater inflater;

    public RegionAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    private OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.item_region, parent,
                false));

        return holder;
    }




    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.name.setText(data.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view,position);
            }
        });
    }






    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<RegionListBean.ListBean.SonBeanX.SonBean> Datas) {
        data = Datas;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.tv_county);
        }

    }
}
