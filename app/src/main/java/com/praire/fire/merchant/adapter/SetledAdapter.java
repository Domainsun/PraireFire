package com.praire.fire.merchant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.praire.fire.R;

import java.util.List;

/**
 * Created by sunlo on 2018/1/5.
 */

public class SetledAdapter extends RecyclerView.Adapter<SetledAdapter.MyViewHolder>  {


    private List<String> data;
    private LayoutInflater inflater;

    public SetledAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    private OnItemClickListener mOnItemClickListener = null;






    public static interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.item_setled, parent,
                false));

        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.name.setText(data.get(position));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(position);


            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<String> Datas) {
        data = Datas;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.tv_type);
            iv=itemView.findViewById(R.id.iv_choose);
        }

    }
}
