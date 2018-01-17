package com.praire.fire.car.adapter;

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
import com.praire.fire.car.bean.BusinessInfoBean;
import com.praire.fire.car.bean.ProductInfoBean;
import com.praire.fire.my.bean.ShoppingCarBean;
import com.praire.fire.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CommitOrderAdapter extends RecyclerView.Adapter<CommitOrderAdapter.MyViewHolder> {



    private Context context;
    private List<ShoppingCarBean.PagelistBean> entities = new ArrayList<>();

    public CommitOrderAdapter(Context context) {
        this.context = context;

    }



    public void setEntities(List<ShoppingCarBean.PagelistBean> entities ) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_commit_order, parent,
                false));
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            ShoppingCarBean.PagelistBean item =  entities.get(position);

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

