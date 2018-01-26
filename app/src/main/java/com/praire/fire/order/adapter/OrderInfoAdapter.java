package com.praire.fire.order.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
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
import com.praire.fire.order.bean.OrderInfoBean;
import com.praire.fire.order.bean.OrderListBean;
import com.praire.fire.utils.TextViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lyp on 2018/1/2.
 */

public class OrderInfoAdapter extends RecyclerView.Adapter<OrderInfoAdapter.MyViewHolder> {


    private Context context;
    private List<OrderInfoBean.OrderlistBean> entities = new ArrayList<>();


    public OrderInfoAdapter(Context context) {
        this.context = context;
    }

    public void setEntities(List<OrderInfoBean.OrderlistBean> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                context).inflate(R.layout.item_order_info, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final OrderInfoBean.OrderlistBean bean = entities.get(position);
        holder.itemView.setTag(position);
        holder.itemOrderListBusinessname.setText(bean.getShopname());
        holder.itemOrderListOrderId.setText(String.format(holder.itemOrderListOrderId.getTag().toString(),bean.getOrderno()));




        holder.itemOrderListPnumber.setText(String.format(holder.itemOrderListPnumber.getTag().toString(), bean.getPslist().size()));
        holder.itemOrderListTotlePrice.setText(String.format(holder.itemOrderListTotlePrice.getTag().toString(), OrderUtils.totlePrice1(bean.getPslist())));
        TextViewUtils.changeFontColor(context, holder.itemOrderListTotlePrice,3,
                holder.itemOrderListTotlePrice.getText().toString().length(),R.color.grey,R.color.orange);
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

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_order_info_businessname)
        TextView itemOrderListBusinessname;
        @BindView(R.id.item_order_info_orderId)
        TextView itemOrderListOrderId;

        @BindView(R.id.add_products)
        LinearLayout addProducts;
        @BindView(R.id.item_order_info_pnumber)
        TextView itemOrderListPnumber;
        @BindView(R.id.item_order_info_totle_price)
        TextView itemOrderListTotlePrice;

        boolean isFrist = true;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

    }

    ItemClickLister itemClickLister;

    public void setItemClickLister(ItemClickLister itemClickLister) {
        this.itemClickLister = itemClickLister;
    }

    public interface ItemClickLister {
        void cancel(String status, String orderId);

        void btnStatus(String status, String orderno, String orderId, String paycost);
    }
}
