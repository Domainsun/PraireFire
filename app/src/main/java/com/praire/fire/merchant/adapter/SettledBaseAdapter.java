package com.praire.fire.merchant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.praire.fire.R;

/**
 * Created by sunlo on 2017/12/29.
 */

public class SettledBaseAdapter extends  RecyclerView.Adapter<SettledBaseAdapter.ViewHolder> {


    @Override
    public SettledBaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_settled,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(SettledBaseAdapter.ViewHolder holder, int position) {
//        holder.name.setText(book.getName());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public ViewHolder(View view) {
            super(view);
            name =view.findViewById(R.id.tv);
        }
    }

}
