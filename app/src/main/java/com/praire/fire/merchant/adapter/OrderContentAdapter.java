package com.praire.fire.merchant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.okhttp.JavaBean.BusinessOrderListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunlo on 2018/1/5.
 */

public class OrderContentAdapter extends RecyclerView.Adapter<OrderContentAdapter.MyViewHolder> {

    Context context;



    private List<BusinessOrderListBean.PagelistBean.PslistBean> data;
    private LayoutInflater inflater;

    public OrderContentAdapter(Context context) {
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
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.item_business_order_content, parent,
                false));

        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        try {

            holder.productPhoto.setImageURI(data.get(position).getCover());
            holder.tvName.setText(data.get(position).getName());
            holder.tvType.setText(data.get(position).getType());
            holder.tvCount.setText(data.get(position).getNumber());
            holder.tvNprice.setText(data.get(position).getNprice());
            holder.oprice.setText(data.get(position).getPrice());
        } catch (Exception e) {
            Log.e("orderContentAdapter", "onBindViewHolder: "+e.toString() );
        }

    }


    @Override
    public int getItemCount() {
        int count = (data == null ? 0 : data.size());
        return count;
    }

    public void setData(List<BusinessOrderListBean.PagelistBean.PslistBean> Datas) {
        data = Datas;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_photo)
        SimpleDraweeView productPhoto;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_nprice)
        TextView tvNprice;
        @BindView(R.id.oprice)
        TextView oprice;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
