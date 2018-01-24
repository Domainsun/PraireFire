package com.praire.fire.car.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.praire.fire.R;
import com.praire.fire.car.bean.CommitProduct;
import com.praire.fire.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CommitOrderAdapter extends RecyclerView.Adapter<CommitOrderAdapter.MyViewHolder> {



    private Context context;
    private List<CommitProduct> entities = new ArrayList<>();

    public CommitOrderAdapter(Context context) {
        this.context = context;

    }


    public void setEntities(List<CommitProduct> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_commit_order, parent,
                false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final CommitProduct item = entities.get(position);
        holder.itemCommitName.setText(item.getpName());
        holder.itemCommitNum.setText(item.getNumber()+"");
        holder.itemCommitAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setNumber(item.getNumber()+1);
                holder.itemCommitNum.setText(item.getNumber()+"");
                if(itemClickLister !=null){
                    itemClickLister.add(position,item.getNumber());
                }
            }
        });
        holder.itemCommitMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.getNumber() >1) {
                    item.setNumber(item.getNumber() - 1);
                    holder.itemCommitNum.setText(item.getNumber()+"");
                    if(itemClickLister !=null){
                        itemClickLister.mine(position,item.getNumber());
                    }
                }else {
                    ToastUtil.show(context,"不能再减了");
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return entities.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_commit_name)
        TextView itemCommitName;
        @BindView(R.id.item_commit_mine)
        TextView itemCommitMine;
        @BindView(R.id.item_commit_num)
        TextView itemCommitNum;
        @BindView(R.id.item_commit_add)
        TextView itemCommitAdd;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
 ItemClickLister itemClickLister;

    public void setItemClickLister( ItemClickLister itemClickLister) {
        this.itemClickLister = itemClickLister;
    }

    public interface ItemClickLister {
        void mine(int position, int number);

        void add(int position, int number);
    }

}

