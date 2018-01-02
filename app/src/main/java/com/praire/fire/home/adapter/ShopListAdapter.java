package com.praire.fire.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.home.bean.ShopBean;
import com.praire.fire.utils.CalculateUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 评价列表适配器
 */

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.MyViewHolder> {

    private Context context;
    private List<ShopBean.PagelistBean> entities = new ArrayList<>();

    public ShopListAdapter(Context context) {
        this.context = context;
    }

    public void setEntities(List<ShopBean.PagelistBean> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_shop_list, parent,
                false));
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ShopBean.PagelistBean item = entities.get(position);
        holder.itemShopListName.setText(item.getName());
        holder.itemShopListStar.setRating(Float.valueOf(item.getStar()));
        holder.itemShopListScroe.setText(item.getStar() + "分");
        holder.itemShopListTime .setText(item.getOpentime());
        holder.itemShopListInfo.setText(item.getDesc());
        holder.itemShopListAddress.setText(item.getAddress());
        //还没有获取到本地位置  所以距离都是0 获取到本地经纬度后 修改参数
        holder.itemShopListDistance.setText(CalculateUtils.showDistance(CalculateUtils.getDistance(item.getLat(),item.getLng(),item.getLat(),item.getLng())));
        holder.itemShopListAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Uri uri = Uri.parse(item.getDoor());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        holder.itemShopListImg.setController(controller);
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.item_shop_list_img)
        SimpleDraweeView itemShopListImg;
        @BindView(R.id.item_shop_list_name)
        TextView itemShopListName;
        @BindView(R.id.item_shop_list_star)
        RatingBar itemShopListStar;
        @BindView(R.id.item_shop_list_scroe)
        TextView itemShopListScroe;
        @BindView(R.id.item_shop_list_time)
        TextView itemShopListTime;
        @BindView(R.id.item_shop_list_info)
        TextView itemShopListInfo;
        @BindView(R.id.item_shop_list_address)
        TextView itemShopListAddress;
        @BindView(R.id.item_shop_list_distance)
        TextView itemShopListDistance;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


}

