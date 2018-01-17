package com.praire.fire.car.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.iflytek.cloud.thirdparty.T;
import com.praire.fire.R;
import com.praire.fire.car.bean.BusinessInfoBean;
import com.praire.fire.car.bean.ProductInfoBean;
import com.praire.fire.data.IntentDataForRoutePlanningActivity;
import com.praire.fire.map.RoutePlanningActivity;
import com.praire.fire.utils.DateUtils;
import com.praire.fire.utils.TextViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Intent.ACTION_CALL;


public class ShopEvalauteAdapter extends RecyclerView.Adapter<ShopEvalauteAdapter.MyViewHolder> {



    private Context context;
    private List<?> entities = new ArrayList<>();

    public ShopEvalauteAdapter(Context context) {
        this.context = context;

    }



    public void setEntities(List<?> entities ) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_shop_evaluate, parent,
                false));
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(entities.get(position) instanceof BusinessInfoBean.CommentlistBean ) {
              BusinessInfoBean.CommentlistBean item = (BusinessInfoBean.CommentlistBean)entities.get(position);
            fillInfoShop(holder,item);
        }else if(entities.get(position) instanceof ProductInfoBean.CommentBean.ProductcommentBean){
            ProductInfoBean.CommentBean.ProductcommentBean item = (ProductInfoBean.CommentBean.ProductcommentBean)entities.get(position);
            fillInfo(holder,item);
        }
    }
    private void fillInfoShop(MyViewHolder holder, BusinessInfoBean.CommentlistBean item) {
        holder.itemShopEaluateName.setText(item.getNickname());
        holder.itemShopEaluateStar.setRating(Float.valueOf(item.getStar()));
        holder.itemShopEaluateDate.setText(DateUtils.timedate(item.getCreate_time()));
        holder.itemQueryPayContent.setText(item.getComment());


        Uri uri = Uri.parse(item.getPicurl());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        holder.userIcon.setController(controller);
    }
    private void fillInfo(MyViewHolder holder, ProductInfoBean.CommentBean.ProductcommentBean item) {
        holder.itemShopEaluateName.setText(item.getNickname());
        holder.itemShopEaluateStar.setRating(Float.valueOf(item.getStar()));
        holder.itemShopEaluateDate.setText(DateUtils.timedate(item.getCreate_time()));
        holder.itemQueryPayContent.setText(item.getComment());


        Uri uri = Uri.parse(item.getPicurl());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        holder.userIcon.setController(controller);
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.user_icon)
        SimpleDraweeView userIcon;
        @BindView(R.id.item_shop_ealuate_name)
        TextView itemShopEaluateName;
        @BindView(R.id.item_shop_ealuate_star)
        RatingBar itemShopEaluateStar;
        @BindView(R.id.item_shop_ealuate_date)
        TextView itemShopEaluateDate;
        @BindView(R.id.item_query_pay_content)
        TextView itemQueryPayContent;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


}

