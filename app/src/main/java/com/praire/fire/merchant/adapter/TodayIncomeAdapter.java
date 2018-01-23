package com.praire.fire.merchant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.praire.fire.R;
import com.praire.fire.okhttp.JavaBean.BusinessEvaluateListBean;
import com.praire.fire.okhttp.JavaBean.TodayIncomeBean;
import com.praire.fire.utils.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunlo on 2018/1/5.
 */

public class TodayIncomeAdapter extends RecyclerView.Adapter<TodayIncomeAdapter.MyViewHolder> {


    Context context;


    private List<TodayIncomeBean.PagelistBean> data;
    private LayoutInflater inflater;

    public TodayIncomeAdapter(Context context) {
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
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.item_day_income, parent,
                false));

        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.tvOrderId.setText("单号:"+data.get(position).getOrderno());
            holder.tvServiceName.setText(data.get(position).getConactname()+" 【共"+data.get(position).getTotalcount()+"件】");
            holder.tvIncome.setText("+"+data.get(position).getTotalincome()+"元");
            holder.tvTime.setText(DateUtils.times2(data.get(position).getUse_time()));


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<TodayIncomeBean.PagelistBean> Datas) {
        data = Datas;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_order_id)
        TextView tvOrderId;
        @BindView(R.id.tv_service_name)
        TextView tvServiceName;
        @BindView(R.id.tv_income)
        TextView tvIncome;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
