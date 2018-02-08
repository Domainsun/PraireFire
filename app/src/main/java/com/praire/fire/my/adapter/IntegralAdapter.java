package com.praire.fire.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.my.bean.IntegralBean;
import com.praire.fire.okhttp.JavaBean.BankBean;
import com.praire.fire.order.adapter.OrderListAdapter;
import com.praire.fire.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lyp on 2018/2/3.
 */

public class IntegralAdapter extends RecyclerView.Adapter<IntegralAdapter.MyViewHolder> implements View.OnClickListener{
    Context context;



    private List<IntegralBean.PagelistBean> data = new ArrayList<>();
    private LayoutInflater inflater;

    public IntegralAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    private OnItemClickListener mOnItemClickListener = null;

    @Override
    public void onClick(View view) {
        if(itemClickLister !=null){
            itemClickLister.itemClick(view,(int)view.getTag());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view, String id);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_integral_list, parent,
                false);
        view.setOnClickListener(this);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        IntegralBean.PagelistBean item =  data.get(position);
        holder.itemIntegralName.setText(item.getText());
        holder.itemIntegralTime.setText(DateUtils.times1(item.getCreate_time()));
        holder.itemIntegralChangNum.setText(item.getSymbol()+item.getCredit());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<IntegralBean.PagelistBean> Datas) {
        data = Datas;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_integral_name)
        TextView itemIntegralName;
        @BindView(R.id.item_integral_time)
        TextView itemIntegralTime;
        @BindView(R.id.item_integral_chang_num)
        TextView itemIntegralChangNum;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
    ItemClickLister itemClickLister;

    public void setItemClickLister( ItemClickLister itemClickLister) {
        this.itemClickLister = itemClickLister;
    }

    public interface ItemClickLister {
        void itemClick(View itemView,int position);
    }
}
