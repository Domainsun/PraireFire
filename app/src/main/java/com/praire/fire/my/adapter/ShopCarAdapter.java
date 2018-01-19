package com.praire.fire.my.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.car.MoreProductActivity;
import com.praire.fire.my.ShoppingCarActivity;
import com.praire.fire.my.bean.ShoppingCarBean;
import com.praire.fire.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author lyp
 */
public class ShopCarAdapter extends RecyclerView.Adapter<ShopCarAdapter.MyViewHolder> {


    private Context context;
    private List<ShoppingCarBean.PagelistBean> entities = new ArrayList<>();

    public ShopCarAdapter(Context context) {
        this.context = context;

    }

    public void setEntities(List<ShoppingCarBean.PagelistBean> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_shoping_car, parent,
                false));
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ShoppingCarBean.PagelistBean item = entities.get(position);
        holder.itemShoppingCarName.setText(item.getInfo().getName());
        holder.itemShoppingCarShop.setText(item.getInfo().getShop_name());
        holder.itemShoppingCarCount.setText(String.format(holder.itemShoppingCarCount.getTag().toString(), item.getCount()));
        holder.itemShoppingCarNum.setText(item.getCount());
        holder.itemShoppingCarPrice.setText(String.format(holder.itemShoppingCarPrice.getTag().toString(), item.getInfo().getNprice()));
        //（1：产品，2：服务）
        if ("1".equals(item.getType())) {
//            、、商品
            Uri uri = Uri.parse(item.getInfo().getNprice());
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setAutoPlayAnimations(true)
                    .build();
            holder.itemShopListImg.setVisibility(View.VISIBLE);
            holder.itemShopListImg.setController(controller);
        } else {
            //服务
            holder.itemShopListImg.setVisibility(View.GONE);
        }
        holder.shoppingCarCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.itemShoppingCarMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count =Integer.valueOf(holder.itemShoppingCarNum.getText().toString().trim());
                if(count>1) {
                    count--;
                    holder.itemShoppingCarNum.setText(count + "");
                    item.setCount(count+"");
                    ((ShoppingCarActivity) context).editNumShoppingCar(item.getId(), count);
                }else {
                    ToastUtil.show(context,"不能再减了，请左滑删除");
                }

            }
        });
        holder.itemShoppingCarAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count =Integer.valueOf(holder.itemShoppingCarNum.getText().toString().trim());
                count++;
                holder.itemShoppingCarNum.setText(count +"");
                item.setCount(count+"");
                ((ShoppingCarActivity)context).editNumShoppingCar(item.getId(),count);
            }
        });
        holder.itemShoppingCarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.itemShoppingCarAddLl.setVisibility(View.VISIBLE);
                holder.itemShoppingCarInfo.setVisibility(View.GONE);
                holder.itemShoppingCarDone.setVisibility(View.VISIBLE);
                holder.itemShoppingCarEdit.setVisibility(View.GONE);
            }
        });
        holder.itemShoppingCarDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.itemShoppingCarAddLl.setVisibility(View.GONE);
                holder.itemShoppingCarInfo.setVisibility(View.VISIBLE);
                holder.itemShoppingCarEdit.setVisibility(View.VISIBLE);
                holder.itemShoppingCarDone.setVisibility(View.GONE);
                holder.itemShoppingCarCount.setText(String.format(holder.itemShoppingCarCount.getTag().toString(), item.getCount()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_shopping_car_checkbox)
        CheckBox shoppingCarCheckbox;
        @BindView(R.id.item_shopping_car_img)
        SimpleDraweeView itemShopListImg;
        @BindView(R.id.item_shopping_car_mine)
        TextView itemShoppingCarMine;
        @BindView(R.id.item_shopping_car_num)
        TextView itemShoppingCarNum;
        @BindView(R.id.item_shopping_car_add)
        TextView itemShoppingCarAdd;
        @BindView(R.id.item_shopping_car_add_ll)
        LinearLayout itemShoppingCarAddLl;
        @BindView(R.id.item_shopping_car_name)
        TextView itemShoppingCarName;
        @BindView(R.id.item_shopping_car_shop)
        TextView itemShoppingCarShop;
        @BindView(R.id.item_shopping_car_count)
        TextView itemShoppingCarCount;
        @BindView(R.id.item_shopping_car_price)
        TextView itemShoppingCarPrice;
        @BindView(R.id.item_shopping_car_info)
        LinearLayout itemShoppingCarInfo;
        @BindView(R.id.item_shopping_car_done)
        TextView itemShoppingCarDone;
        @BindView(R.id.item_shopping_car_edit)
        ImageView itemShoppingCarEdit;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


}

