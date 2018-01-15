package com.praire.fire.car.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.praire.fire.R;
import com.praire.fire.car.bean.BusinessInfoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



/**
 * @author lyp
 */
public class ShopServiceAdapter extends RecyclerView.Adapter<ShopServiceAdapter.MyViewHolder> {



    private Context context;
    private List<BusinessInfoBean.ServicelistBean> entities = new ArrayList<>();

    public ShopServiceAdapter(Context context) {
        this.context = context;

    }

    public void setEntities(List<BusinessInfoBean.ServicelistBean> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_shop_service, parent,
                false));
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final BusinessInfoBean.ServicelistBean item = entities.get(position);
        holder.itemShopServiceName.setText(item.getName());
        holder.itemShopServiceInfo.setText(item.getDesc());
        holder.itemShopServicePrice.setText(String.format(holder.itemShopServicePrice.getTag().toString(),item.getNprice()));
//        TextViewUtils.changeFontColor(context, holder.itemMapNearlyPerson, 0, item.getOrdercount().length(), R.color.grey, R.color.orange);
        holder.itemShopServiceSale.setText(String.format(holder.itemShopServiceSale.getTag().toString(),item.getSalecount()));
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.item_shop_service_name)
        TextView itemShopServiceName;
        @BindView(R.id.item_shop_service_info)
        TextView itemShopServiceInfo;
        @BindView(R.id.item_shop_service_price)
        TextView itemShopServicePrice;
        @BindView(R.id.item_shop_service_sale)
        TextView itemShopServiceSale;
        @BindView(R.id.item_shop_service_mine)
        ImageView itemShopServiceMine;
        @BindView(R.id.item_shop_service_num)
        TextView itemShopServiceNum;
        @BindView(R.id.item_shop_service_add)
        ImageView itemShopServiceAdd;
        @BindView(R.id.item_shop_service_add_ll)
        LinearLayout itemShopServiceAddLl;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


}

