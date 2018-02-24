package com.praire.fire.merchant.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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


    int count=0;
    double price11=0.00;

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        holder.setIsRecyclable(false);





        holder.tvConfirmRefund.setVisibility(View.VISIBLE);

//        holder.tvConfirmRefund.setVisibility( data.get(position).getStatus().equals("0") ||data.get(position).getStatus().equals("1") ? View.VISIBLE : View.INVISIBLE);


        if (data.get(position).getStatus().equals("0")) {
            holder.tvConfirmRefund.setVisibility(View.VISIBLE);
            holder.tvConfirmRefund.setText("修改");
        } else if (data.get(position).getRefund().equals("1")) {
            holder.tvConfirmRefund.setVisibility(View.VISIBLE);
            holder.tvConfirmRefund.setText("确认退款");
        } else {
            holder.tvConfirmRefund.setVisibility(View.INVISIBLE);
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


        holder.headPhoto.setImageURI(data.get(position).getHead());


        holder.ivSex.setImageResource(data.get(position).getSex().equals("0") ? R.mipmap.business_order_female : R.mipmap.business_order_male);
        holder.tvName.setText(data.get(position).getNickname());
        holder.tvOrderId.setText(data.get(position).getTel());





        if (data.get(position).getStatus().equals("0")) {
            holder.tvOrderStatus.setText("等待买家付款");
        } else if (data.get(position).getStatus().equals("1")) {
            holder.tvOrderStatus.setText("等待买家消费");
        } else if (data.get(position).getStatus().equals("2")) {
            holder.tvOrderStatus.setText("交易成功");
        } else if (data.get(position).getStatus().equals("3")) {
            holder.tvOrderStatus.setText("退款成功");
        }


        holder.tvTime.setText(DateUtils.times1(data.get(position).getCreate_time()));





        /*添加 订单*/

        //防止滑动的时候重复显示数据
        if (holder.isFrist) {
            count=0;
            price11=0.00;
            for (int i = 0; i < data.get(position).getPslist().size(); i++) {


                Log.d("name:", "name: "+"position:"+position+"name:"+data.get(position).getPslist().get(i).getName());




                count = count + Integer.parseInt( data.get(position).getPslist().get(i).getNumber()) ;

                price11=price11 +( Double.parseDouble(data.get(position).getPslist().get(i).getShopprice()))  * Integer.parseInt(data.get(position).getPslist().get(i).getNumber())  ; ;

                Log.d("price11", "price11: "+price11);

                ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.item_business_order_content, null);


                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT); // , 1是可选写的


                lp.setMargins(0, 1, 0, 0);
                viewGroup.setLayoutParams(lp);


                SimpleDraweeView product_photo = viewGroup.findViewById(R.id.product_photo);
                TextView tv_name = viewGroup.findViewById(R.id.tv_name);
                TextView type = viewGroup.findViewById(R.id.tv_type);
                TextView tv_count = viewGroup.findViewById(R.id.tv_count);
                TextView tv_nprice = viewGroup.findViewById(R.id.tv_nprice);
                TextView oprice1 = viewGroup.findViewById(R.id.oprice);

                String cover = data.get(position).getPslist().get(i).getCover();

                if (cover.length() == 0) {
                    product_photo.setVisibility(View.GONE);
                } else {
                    product_photo.setImageURI(cover);
                }


                tv_name.setText(data.get(position).getPslist().get(i).getName());
                tv_count.setText("数量: " + data.get(position).getPslist().get(i).getNumber());
                type.setText(data.get(position).getPslist().get(i).getClasspath());
                tv_nprice.setText("¥ " + data.get(position).getPslist().get(i).getShopprice());

                oprice1.setVisibility(View.INVISIBLE);
//                oprice1.setText("¥ " + data.get(position).getPslist().get(i).getNprice());
////
////                //添加删除线
//                oprice1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                holder.addProducts.addView(viewGroup);
            }
            holder.isFrist = false;

            holder.tvOrderid.setText("订单编号: "+data.get(position).getOrderno());
            holder.tvTotalCount.setText("共"+count+"件商品  合计: ¥"+price11);
        }



    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<BusinessOrderListBean.PagelistBean> Datas) {
        data = Datas;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_orderid)
        TextView tvOrderid;
        @BindView(R.id.tv_total_count)
        TextView tvTotalCount;
        @BindView(R.id.head_photo)
        SimpleDraweeView headPhoto;
        @BindView(R.id.iv_sex)
        ImageView ivSex;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_order_id)
        TextView tvOrderId;
        @BindView(R.id.tv_order_status)
        TextView tvOrderStatus;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_confirm_refund)
        TextView tvConfirmRefund;
        @BindView(R.id.add_products)


        LinearLayout addProducts;
        boolean isFrist = true;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
