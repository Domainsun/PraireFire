package com.praire.fire.merchant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.praire.fire.R;

import java.util.List;

/**
 * Created by sunlo on 2017/12/29.
 */

public class SettledBaseAdapter extends  RecyclerView.Adapter<SettledBaseAdapter.ViewHolder> {



    private List<String> data;
    private LayoutInflater inflater;

    public SettledBaseAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(inflater.inflate(
                R.layout.item_settled, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(SettledBaseAdapter.ViewHolder holder, int position) {
        holder.name.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public ViewHolder(View view) {
            super(view);
            name =view.findViewById(R.id.tv);
        }
    }

    public void setData(List<String> pDatas) {
        data = pDatas;
    }

}
