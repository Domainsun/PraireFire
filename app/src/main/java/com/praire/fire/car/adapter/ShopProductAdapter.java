package com.praire.fire.car.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.car.bean.BusinessInfoBean;
import com.praire.fire.common.ConstanUrl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class ShopProductAdapter extends RecyclerView.Adapter<ShopProductAdapter.MyViewHolder> {



    private Context context;
    private List<BusinessInfoBean.ProductlistBean> entities = new ArrayList<>();


    public ShopProductAdapter(Context context) {
        this.context = context;

    }

    public void setEntities(List<BusinessInfoBean.ProductlistBean> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_shop_product, parent,
                false));
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final BusinessInfoBean.ProductlistBean item = entities.get(position);
        holder.itemShopProductName.setText(item.getName());
        holder.itemShopProductSale.setText(String.format(holder.itemShopProductSale.getTag().toString(),item.getSalecount()));
        holder.itemShopProductPrice.setText(String.format(holder.itemShopProductPrice.getTag().toString(),item.getNprice()));
        holder.itemShopProductInfo.setText(item.getDesc());
        if(!item.getPicurl().equals("")) {
//            String[] picurl = item.getPicurl().split("\\|");
//            Log.e("picurl" ,picurl[0]);
            Uri uri = Uri.parse(item.getPicurl());
            Log.e("Url" ,uri.toString());
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setAutoPlayAnimations(true)
                    .build();
            holder.itemShopListImg.setController(controller);
        }

    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.item_shop_list_img)
        SimpleDraweeView itemShopListImg;
        @BindView(R.id.item_shop_product_name)
        TextView itemShopProductName;
        @BindView(R.id.item_shop_product_info)
        TextView itemShopProductInfo;
        @BindView(R.id.item_shop_product_price)
        TextView itemShopProductPrice;
        @BindView(R.id.item_shop_product_sale)
        TextView itemShopProductSale;
        @BindView(R.id.item_shop_product_mine)
        ImageView itemShopProductMine;
        @BindView(R.id.item_shop_product_num)
        TextView itemShopProductNum;
        @BindView(R.id.item_shop_product_add)
        ImageView itemShopProductAdd;
        @BindView(R.id.item_shop_product_add_ll)
        LinearLayout itemShopProductAddLl;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


}

