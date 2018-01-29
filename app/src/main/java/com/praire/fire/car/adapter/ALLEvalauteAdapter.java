package com.praire.fire.car.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.car.bean.AllProductEvalauteBean;
import com.praire.fire.utils.DateUtils;
import com.praire.fire.utils.TextViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ALLEvalauteAdapter extends RecyclerView.Adapter<ALLEvalauteAdapter.MyViewHolder> {



    private Context context;
    private List<AllProductEvalauteBean.PagelistBean> entities = new ArrayList<>();

    public ALLEvalauteAdapter(Context context) {
        this.context = context;

    }


    public void setEntities(List<AllProductEvalauteBean.PagelistBean> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_shop_all_evaluate, parent,
                false));
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        AllProductEvalauteBean.PagelistBean item = entities.get(position);
        holder.itemShopEaluateName.setText(item.getNickname());
        holder.itemShopEaluateStar.setRating(Float.valueOf(item.getStar()));
        holder.itemShopEaluateDate.setText(DateUtils.timedate(item.getCreate_time()));
        holder.itemAllEvaluateContent.setText(item.getComment());
//        holder.itemAllEvaluateRead.setText(item.getComment());
//        holder.itemAllEvaluateZan.setText(item.getIsread());
//        holder.itemAllEvaluateEvaluate.setText(item.g());
        holder.bussinessRespond.setText(String.format(holder.bussinessRespond.getTag().toString(),item.getReply()));
        TextViewUtils.changeFontColor(context,holder.bussinessRespond,0,5,R.color.grey,R.color.orange);
        Uri uri = Uri.parse(item.getHead());
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
        @BindView(R.id.item_shop_ealuate_date)
        TextView itemShopEaluateDate;
        @BindView(R.id.item_shop_ealuate_star)
        RatingBar itemShopEaluateStar;
        @BindView(R.id.item_all_evaluate_content)
        TextView itemAllEvaluateContent;
        @BindView(R.id.item_all_evaluate_read)
        TextView itemAllEvaluateRead;
        @BindView(R.id.item_all_evaluate_zan)
        TextView itemAllEvaluateZan;
        @BindView(R.id.item_all_evaluate_evaluate)
        TextView itemAllEvaluateEvaluate;
        @BindView(R.id.item_shop_ealuate_ll)
        LinearLayout itemShopEaluateLl;
        @BindView(R.id.bussiness_respond)
        TextView bussinessRespond;
        @BindView(R.id.bussiness_respond_ll)
        LinearLayout bussinessRespondLl;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


}

