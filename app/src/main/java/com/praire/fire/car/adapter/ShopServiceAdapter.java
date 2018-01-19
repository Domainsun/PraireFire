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
import com.praire.fire.car.MoreProductActivity;
import com.praire.fire.car.bean.BusinessInfoBean;
import com.praire.fire.car.bean.ServicelistBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



/**
 * @author lyp
 */
public class ShopServiceAdapter extends RecyclerView.Adapter<ShopServiceAdapter.MyViewHolder> {

    int[] chooseNum ;

    private Context context;
    private List<ServicelistBean> entities = new ArrayList<>();
    boolean isShowAdds = false;
    public ShopServiceAdapter(Context context) {
        this.context = context;

    }

    /**
     *
     * @param entities
     * @param isShowAdds 是否显示添加到购物车的视图
     */
    public void setEntities(List<ServicelistBean> entities,boolean isShowAdds) {
        this.entities = entities;
        this.isShowAdds = isShowAdds;
        chooseNum = new int[entities.size()];
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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ServicelistBean item = entities.get(position);
        chooseNum[position]=0;
        holder.itemShopServiceName.setText(item.getName());
        holder.itemShopServiceInfo.setText(item.getDesc());
        holder.itemShopServicePrice.setText(String.format(holder.itemShopServicePrice.getTag().toString(),item.getNprice()));
//        TextViewUtils.changeFontColor(context, holder.itemMapNearlyPerson, 0, item.getOrdercount().length(), R.color.grey, R.color.orange);
        holder.itemShopServiceSale.setText(String.format(holder.itemShopServiceSale.getTag().toString(),item.getSalecount()));
        if(isShowAdds){
            holder.itemShopServiceAddLl.setVisibility(View.VISIBLE);
            holder.itemShopServiceAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.itemShopServiceMine.setVisibility(View.VISIBLE);
                    holder.itemShopServiceNum.setVisibility(View.VISIBLE);
                    ++chooseNum[position];
                    holder.itemShopServiceNum.setText(chooseNum[position]+"");
                    ((MoreProductActivity)context).addToShoppingCar("2",item.getId(),position);
                }
            });
            holder.itemShopServiceMine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(chooseNum[position] > 1) {
                        --chooseNum[position];
                        holder.itemShopServiceNum.setText(chooseNum[position] + "");
                        ((MoreProductActivity)context).editNumShoppingCar(item.getId(),chooseNum[position],position);
                    }else {
                        holder.itemShopServiceMine.setVisibility(View.GONE);
                        holder.itemShopServiceNum.setVisibility(View.GONE);
                        ((MoreProductActivity)context).deleteShoppingCar(item.getId());
                    }

                }
            });
        }
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

