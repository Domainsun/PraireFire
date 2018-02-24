package com.praire.fire.merchant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.okhttp.JavaBean.OrderDetailsInfoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunlo on 2018/1/5.
 */

public class OrderChangeAdapter extends RecyclerView.Adapter<OrderChangeAdapter.MyViewHolder> {


    Context context;


    private List<OrderDetailsInfoBean.OrderinfoBean.PslistBean> data;
    private LayoutInflater inflater;

    public OrderChangeAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    private OnItemClickListener mOnItemClickListener = null;



    public interface OnItemClickListener {
        void onItemClick(int position, String psId, double nprice);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }





    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.item_business_order_change, parent,
                false));

        return holder;
    }

    List<Double> nprices = new ArrayList<>();

    Boolean b = true;

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        final String cover = data.get(position).getCover();
        final Double nprice = Double.parseDouble(data.get(position).getNprice());

        nprices.add(nprice);

        if (cover.length() == 0) {
            holder.iv.setVisibility(View.GONE);
        } else {
            holder.iv.setVisibility(View.VISIBLE);
            holder.iv.setImageURI(cover);
        }

        if (data.get(position).getTag().equals("0")) {

            holder.edit.setVisibility(View.INVISIBLE);

        }


        holder.tvName.setText(data.get(position).getName());
        holder.tvPrice.setText("¥ " + nprice + "元");
        holder.tvCount.setText("数量: x" + data.get(position).getNumber());
        holder.count.setText("" + nprice);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cover.length() != 0) {
                    holder.tvName.setVisibility(View.INVISIBLE);
                    holder.tvPrice.setVisibility(View.INVISIBLE);
                    holder.tvCount.setVisibility(View.INVISIBLE);
                }
                holder.done.setVisibility(View.VISIBLE);
                holder.rlEdit.setVisibility(View.VISIBLE);
            }
        });
        final Double d1 = nprices.get(position);

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                nprices.set(position, nprices.get(position) + 1);


                holder.count.setText("" + nprices.get(position));
            }
        });

        holder.dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                nprices.set(position, nprices.get(position) - 1);


                holder.count.setText("" + nprices.get(position));
            }
        });

        holder.done.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                nprices.set(position,  Double.parseDouble(holder.count.getText().toString()));




                if (!nprices.get(position).equals(d1)) {
                    mOnItemClickListener.onItemClick(position, data.get(position).getId(), Double.parseDouble(holder.count.getText().toString()));
                }


                holder.tvName.setVisibility(View.VISIBLE);
                holder.tvPrice.setVisibility(View.VISIBLE);
                holder.tvCount.setVisibility(View.VISIBLE);
                holder.done.setVisibility(View.INVISIBLE);
                holder.rlEdit.setVisibility(View.INVISIBLE);


                holder.tvPrice.setText("¥ " + nprices.get(position) + "元");

            }
        });


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<OrderDetailsInfoBean.OrderinfoBean.PslistBean> Datas) {
        data = Datas;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        SimpleDraweeView iv;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.edit)
        ImageView edit;
        @BindView(R.id.done)
        TextView done;
        @BindView(R.id.dec)
        TextView dec;
        @BindView(R.id.count)
        EditText count;
        @BindView(R.id.add)
        TextView add;
        @BindView(R.id.rl_edit)
        RelativeLayout rlEdit;


        double nprice;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }
}
