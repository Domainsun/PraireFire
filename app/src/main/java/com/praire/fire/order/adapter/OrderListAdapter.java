package com.praire.fire.order.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.order.OrderUtils;
import com.praire.fire.order.PayActivity;
import com.praire.fire.order.bean.OrderListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lyp on 2018/1/2.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder> implements View.OnClickListener{


    private Context context;
    private List<OrderListBean.PagelistBean> entities = new ArrayList<>();


    public OrderListAdapter(Context context) {
        this.context = context;
    }

    public void setEntities(List<OrderListBean.PagelistBean> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                context).inflate(R.layout.item_order_list, parent,
                false);
        view.setOnClickListener(this);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final OrderListBean.PagelistBean bean = entities.get(position);


        holder.itemView.setTag(position);
        holder.itemOrderListBusinessname.setText(bean.getShopname());
        holder.itemOrderListOrderId.setText(bean.getOrderno());

        if ("1".equals(bean.getRefund())) {
            holder.itemOrderListClean.setText(R.string.refund);
        } else if ("0".equals(bean.getStatus())) {
            holder.itemOrderListClean.setText(R.string.cancel);
        } else {
            holder.itemOrderListClean.setVisibility(View.GONE);
        }
        holder.itemOrderListClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemClickLister !=null) {
                    itemClickLister.cancel(bean.getRefund(),bean.getId());
                }
            }
        });
        holder.itemOrderListStatus0.setText(OrderUtils.statusCN(bean.getStatus()));
        holder.itemOrderListStatusBtn.setText(OrderUtils.statusBtnCN(bean.getStatus()));
        if("3".equals(bean.getStatus()) || "4".equals(bean.getStatus()) ||"5".equals(bean.getStatus())) {
            holder.itemOrderListStatusBtn.setVisibility(View.GONE);
        }

        final String totlePrice =  OrderUtils.totlePriceList(bean.getPslist());
        holder.itemOrderListStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemClickLister !=null) {
                    itemClickLister.btnStatus(bean.getStatus(),bean.getOrderno(),position,totlePrice);
                }
            }
        });
        holder.itemOrderListPnumber.setText(String.format(holder.itemOrderListPnumber.getTag().toString(), bean.getPslist().size()));
        holder.itemOrderListTotlePrice.setText(String.format(holder.itemOrderListTotlePrice.getTag().toString(), totlePrice));
        //防止滑动的时候重复显示数据
        if (holder.isFrist) {
            for (int i = 0; i < bean.getPslist().size(); i++) {
                ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.item_item_order_list, null);
                SimpleDraweeView img = viewGroup.findViewById(R.id.item_shop_list_img);
                TextView productName = viewGroup.findViewById(R.id.item_order_list_name);
                TextView count = viewGroup.findViewById(R.id.item_order_list_count);
                TextView type = viewGroup.findViewById(R.id.item_order_list_type);
                TextView nprice = viewGroup.findViewById(R.id.item_order_list_nprice);
                TextView sprice = viewGroup.findViewById(R.id.item_order_list_sprice);
                productName.setText(bean.getPslist().get(i).getName());
                count.setText(String.format(count.getTag().toString(), bean.getPslist().get(i).getNumber()));
                type.setText(String.format(type.getTag().toString(), bean.getPslist().get(i).getClasspath()));
                nprice.setText(String.format(nprice.getTag().toString(), bean.getPslist().get(i).getNprice()));
                sprice.setText(String.format(sprice.getTag().toString(), bean.getPslist().get(i).getPrice()));
                //添加删除线
                nprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                if ("1".equals(bean.getPslist().get(i).getType())) {
                    img.setVisibility(View.VISIBLE);
                    Uri uri = Uri.parse(bean.getPslist().get(i).getCover());
                    DraweeController controller = Fresco.newDraweeControllerBuilder()
                            .setUri(uri)
                            .setAutoPlayAnimations(true)
                            .build();
                    img.setController(controller);
                }
                holder.addProducts.addView(viewGroup);
            }
            holder.isFrist = false;
        }

    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    @Override
    public void onClick(View view) {
        if(itemClickLister !=null) {
            itemClickLister.itemClick(view,(int)view.getTag());
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_order_list_businessname)
        TextView itemOrderListBusinessname;
        @BindView(R.id.item_order_list_orderId)
        TextView itemOrderListOrderId;
        @BindView(R.id.item_order_list_status0)
        TextView itemOrderListStatus0;
        @BindView(R.id.item_order_list_clean)
        TextView itemOrderListClean;
        @BindView(R.id.item_order_list_status_btn)
        TextView itemOrderListStatusBtn;
        @BindView(R.id.add_products)
        LinearLayout addProducts;
        @BindView(R.id.item_order_list_pnumber)
        TextView itemOrderListPnumber;
        @BindView(R.id.item_order_list_totle_price)
        TextView itemOrderListTotlePrice;
        boolean isFrist;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
              isFrist = true;
        }

    }

    ItemClickLister itemClickLister;

    public void setItemClickLister(ItemClickLister itemClickLister) {
        this.itemClickLister = itemClickLister;
    }

    public interface ItemClickLister {
        void cancel(String status,String orderId);
        void itemClick(View itemView,int position);
        void btnStatus(String status,String orderno,int position,String paycost);
    }
}
