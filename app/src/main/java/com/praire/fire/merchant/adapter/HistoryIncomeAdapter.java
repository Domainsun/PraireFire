package com.praire.fire.merchant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.praire.fire.R;
import com.praire.fire.okhttp.JavaBean.BusinessEvaluateListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunlo on 2018/1/5.
 */

public class HistoryIncomeAdapter extends RecyclerView.Adapter<HistoryIncomeAdapter.MyViewHolder> {


    Context context;
    @BindView(R.id.tv_income)
    TextView tvIncome;
    @BindView(R.id.tv_order_count)
    TextView tvOrderCount;
    @BindView(R.id.tv_time)
    TextView tvTime;


    private List<BusinessEvaluateListBean.PagelistBean> data;
    private LayoutInflater inflater;

    public HistoryIncomeAdapter(Context context) {
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
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.item_history_income, parent,
                false));

        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

//        holder.ivHead.setImageURI(data.get(position).getUserinfo().getHead());


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<BusinessEvaluateListBean.PagelistBean> Datas) {
        data = Datas;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
