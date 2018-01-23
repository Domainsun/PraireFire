package com.praire.fire.merchant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.okhttp.JavaBean.BusinessEvaluateListBean;
import com.praire.fire.okhttp.JavaBean.ProductListBean;
import com.praire.fire.utils.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.VISIBLE;

/**
 * Created by sunlo on 2018/1/5.
 */

public class EvaluateAdapter extends RecyclerView.Adapter<EvaluateAdapter.MyViewHolder> {


    Context context;
    private List<BusinessEvaluateListBean.PagelistBean> data;
    private LayoutInflater inflater;

    public EvaluateAdapter(Context context) {
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
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.item_evaluate, parent,
                false));

        return holder;
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.ivHead.setImageURI(data.get(position).getUserinfo().getHead());
            holder.tvUserName.setText(data.get(position).getUserinfo().getNickname());

            holder.tvTime.setText( DateUtils.times1(data.get(position).getCreate_time()));

            holder.tvEvaluate.setText(data.get(position).getComment());
            holder.ivProduct.setImageURI(data.get(position).getPsinfo().getCover());
            holder.tvIntroduction.setText(data.get(position).getPsinfo().getName());
            holder.tvPrice.setText("¥  "+data.get(position).getPsinfo().getNprice());
            holder.tvCount.setText("数量  "+data.get(position).getPsinfo().getNumber());


        if (data.get(position).getReply().length() == 0) {
            holder.businessAnswer.setVisibility(View.GONE);
            holder.tvShowBusinessAnswer.setVisibility(View.GONE);
            holder.tvBusinessAnswer.setVisibility(View.VISIBLE);
        } else {
            holder.tvBusinessAnswer.setVisibility(View.GONE);
            holder.businessAnswer.setVisibility(VISIBLE);
            holder.tvShowBusinessAnswer.setVisibility(VISIBLE);
            holder.tvShowBusinessAnswer.setText(data.get(position).getReply());
        }



        holder.tvBusinessAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(position, view, data.get(position).getId());
            }
        });


//        if (data.get(position).getStatus().equals("0")) {
//            p = position;
//        }
//
//        if (p != null) {
//            holder.iv.setVisibility(position == p ? View.VISIBLE : View.INVISIBLE);
//            holder.tvDelete.setText(position == p ? "上架" : "下架");
//        }


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<BusinessEvaluateListBean.PagelistBean> Datas) {
        data = Datas;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        Context context;
        @BindView(R.id.iv_head)
        SimpleDraweeView ivHead;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_evaluate)
        TextView tvEvaluate;
        @BindView(R.id.iv_product)
        SimpleDraweeView ivProduct;
        @BindView(R.id.tv_introduction)
        TextView tvIntroduction;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_business_answer)
        TextView tvBusinessAnswer;
        @BindView(R.id.business_answer)
        TextView businessAnswer;
        @BindView(R.id.tv_show_business_answer)
        TextView tvShowBusinessAnswer;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }
}
