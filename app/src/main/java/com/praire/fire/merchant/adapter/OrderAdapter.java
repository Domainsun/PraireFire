package com.praire.fire.merchant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.okhttp.JavaBean.BusinessOrderListBean;
import com.praire.fire.utils.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunlo on 2018/1/5.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    Context context;


    private List<BusinessOrderListBean.PagelistBean> data;
    private LayoutInflater inflater;

    public OrderAdapter(Context context) {
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
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.item_business_order, parent,
                false));

        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tvConfirmRefund.setVisibility(View.VISIBLE);

//        holder.tvConfirmRefund.setVisibility( data.get(position).getStatus().equals("0") ||data.get(position).getStatus().equals("1") ? View.VISIBLE : View.INVISIBLE);
//        holder.tvConfirmRefund.setText(data.get(position).getStatus().equals("0") ? );

        if (data.get(position).getStatus().equals("0")) {
            holder.tvConfirmRefund.setText("修改");
        } else if (data.get(position).getRefund().equals("1")) {
            holder.tvConfirmRefund.setText("确认退款");
        } else {
            holder.tvConfirmRefund.setVisibility(View.INVISIBLE);
        }

        holder.tvAmount.setText("金额:    ¥"+data.get(position).getNprice());
        holder.tvCount.setText("数量:    "+data.get(position).getNumber());

        holder.headPhoto.setImageURI(data.get(position).getUserinfo().getHead());
        holder.tvName.setText(data.get(position).getUserinfo().getNickname());
        holder.tvType.setText(data.get(position).getName());
        holder.tvTime.setText( DateUtils.times1(data.get(position).getCreate_time()));
        if (data.get(position).getUserinfo().getSex()!=null) {
            holder.ivSex.setImageResource(data.get(position).getUserinfo().getSex().equals("0") ? R.mipmap.business_order_male : R.mipmap.business_order_female);
        }


        holder.tvConfirmRefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status = "";
                if (data.get(position).getStatus().equals("0")) {
                    status = "0";
                } else if (data.get(position).getStatus().equals("1")) {
                    status = "1";
                }
                mOnItemClickListener.onItemClick(position, view, data.get(position).getId(), status);
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<BusinessOrderListBean.PagelistBean> Datas) {
        data = Datas;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_amount)
        TextView tvAmount;
        @BindView(R.id.head_photo)
        SimpleDraweeView headPhoto;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_confirm_refund)
        TextView tvConfirmRefund;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.iv_sex)
        ImageView ivSex;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
