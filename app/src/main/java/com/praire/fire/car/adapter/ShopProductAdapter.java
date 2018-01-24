package com.praire.fire.car.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
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
import com.praire.fire.car.MoreProductActivity;
import com.praire.fire.car.bean.CommitProduct;
import com.praire.fire.car.bean.ProductlistBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShopProductAdapter extends RecyclerView.Adapter<ShopProductAdapter.MyViewHolder> {

    int[] chooseNum;
    private Context context;
    private List<ProductlistBean> entities = new ArrayList<>();
    boolean isShowAdds = false;

    public ShopProductAdapter(Context context) {
        this.context = context;

    }

    public void setEntities(List<ProductlistBean> entities, boolean isShowAdds) {
        this.entities = entities;
        this.isShowAdds = isShowAdds;
        chooseNum = new int[entities.size()];
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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ProductlistBean item = entities.get(position);

        chooseNum[position] = 0;
        holder.itemShopProductName.setText(item.getName());
        holder.itemShopProductSale.setText(String.format(holder.itemShopProductSale.getTag().toString(), item.getSalecount()));
        holder.itemShopProductPrice.setText(String.format(holder.itemShopProductPrice.getTag().toString(), item.getNprice()));
        holder.itemShopProductInfo.setText(item.getDesc());

        Uri uri = Uri.parse(item.getCover());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        holder.itemShopListImg.setController(controller);
        if (isShowAdds) {

            holder.itemShopProductAddLl.setVisibility(View.VISIBLE);
            holder.itemShopProductAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (context instanceof MoreProductActivity) {
                        holder.itemShopProductMine.setVisibility(View.VISIBLE);
                        ++chooseNum[position];
                        holder.itemShopProductNum.setText(chooseNum[position] + "");

                        ((MoreProductActivity) context).addToShoppingCar("1", item.getId(), position);

                    }

                }
            });

            holder.itemShopProductMine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (context instanceof MoreProductActivity) {
                        if (chooseNum[position] > 1) {
                            --chooseNum[position];
                            holder.itemShopProductNum.setText(chooseNum[position] + "");
                            ((MoreProductActivity) context).editNumShoppingCar(item.getId(), chooseNum[position], position);

                        } else {
                            holder.itemShopProductMine.setVisibility(View.GONE);
                            holder.itemShopProductNum.setVisibility(View.GONE);

                            ((MoreProductActivity) context).deleteShoppingCar(item.getId());


                        }
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

