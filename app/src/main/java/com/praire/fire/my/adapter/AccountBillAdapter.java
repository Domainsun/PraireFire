package com.praire.fire.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.okhttp.JavaBean.AccountBillBean;
import com.praire.fire.okhttp.JavaBean.HistoryIncomeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunlo on 2018/1/5.
 */

public class AccountBillAdapter extends RecyclerView.Adapter<AccountBillAdapter.MyViewHolder> {


    Context context;


    private List<AccountBillBean.PagelistBean> data;
    private LayoutInflater inflater;

    public AccountBillAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    private OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(int position, View view, String id);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.item_bill, parent,
                false));

        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.setIsRecyclable(false);


//       holder.ivType.setImageURI();

        if (data.get(position).getType().equals("1") || data.get(position).getType().equals("2")) {
            holder.ivType.setImageResource(R.mipmap.bill_recharge);
        } else if (data.get(position).getType().equals("3")) {
            holder.ivType.setImageResource(R.mipmap.bill_consumption);
        } else if (data.get(position).getType().equals("4")) {
            holder.ivType.setImageResource(R.mipmap.bill_withdrawals);
        } else if (data.get(position).getType().equals("5")) {
            holder.ivType.setImageResource(R.mipmap.bill_shop);
        } else if (data.get(position).getType().equals("6")) {
            holder.ivType.setImageResource(R.mipmap.bill_refund);
        } else if (data.get(position).getType().equals("7")) {
            holder.ivType.setImageResource(R.mipmap.bill_reward);
        }


        holder.tvName.setText(data.get(position).getTypename());
        holder.tvPrice.setText(data.get(position).getSymbol() + data.get(position).getCapital());
        holder.tvTime.setText(data.get(position).getCreate_time());

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<AccountBillBean.PagelistBean> Datas) {
        data = Datas;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_type)
        SimpleDraweeView ivType;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
