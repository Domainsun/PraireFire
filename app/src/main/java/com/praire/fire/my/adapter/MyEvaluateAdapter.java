package com.praire.fire.my.adapter;

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
import com.praire.fire.my.bean.MyEvaluateBean;
import com.praire.fire.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 评价列表适配器
 */

public class MyEvaluateAdapter extends RecyclerView.Adapter<MyEvaluateAdapter.MyViewHolder> {


    private Context context;
    private List<MyEvaluateBean.PagelistBean> entities = new ArrayList<>();

    public MyEvaluateAdapter(Context context) {
        this.context = context;
    }

    public void setEntities(List<MyEvaluateBean.PagelistBean> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_my_evaluate, parent,
                false));
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyEvaluateBean.PagelistBean entity = entities.get(position);
        //日期截取年月日
        holder.itemMyEaluateDate.setText(DateUtils.times1(entity.getCreate_time()));
        holder.itemMyEaluateName.setText(entity.getUser_id());
        if (!"".equals(entity.getComment())) {
            holder.itemAllEvaluateContent.setText(entity.getComment());
        }
        float score = Float.valueOf(entity.getStar());
        holder.itemMyEaluateStar.setRating(score);
        if ("1".equals(entity.getType())) {
            holder.productIcon.setVisibility(View.VISIBLE);
            Uri uri = Uri.parse(entity.getPsinfo().getCover());
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setAutoPlayAnimations(true)
                    .build();
            holder.productIcon.setController(controller);
        }
        holder.itemMyEaluateProductName.setText(entity.getPsinfo().getName());
        holder.productNumber.setText(String.format(holder.productNumber.getTag().toString(),entity.getPsinfo().getNumber()));
        holder.itemMyEaluateProductPrice.setText(String.format(holder.itemMyEaluateProductPrice.getTag().toString(),entity.getPsinfo().getPrice()));
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.user_icon)
        SimpleDraweeView userIcon;
        @BindView(R.id.item_my_ealuate_name)
        TextView itemMyEaluateName;
        @BindView(R.id.item_my_ealuate_date)
        TextView itemMyEaluateDate;
        @BindView(R.id.item_my_ealuate_star)
        RatingBar itemMyEaluateStar;
        @BindView(R.id.item_all_evaluate_content)
        TextView itemAllEvaluateContent;
        @BindView(R.id.item_all_evaluate_read)
        TextView itemAllEvaluateRead;
        @BindView(R.id.item_all_evaluate_zan)
        TextView itemAllEvaluateZan;
        @BindView(R.id.item_all_evaluate_evaluate)
        TextView itemAllEvaluateEvaluate;
        @BindView(R.id.product_icon)
        SimpleDraweeView productIcon;
        @BindView(R.id.item_my_ealuate_product_name)
        TextView itemMyEaluateProductName;
        @BindView(R.id.item_my_ealuate_product_price)
        TextView itemMyEaluateProductPrice;
        @BindView(R.id.item_my_ealuate_ll)
        LinearLayout itemMyEaluateLl;
        @BindView(R.id.item_my_ealuate_line)
        View itemMyEaluateLine;
        @BindView(R.id.bussiness_respond)
        TextView bussinessRespond;
        @BindView(R.id.bussiness_respond_ll)
        LinearLayout bussinessRespondLl;
        @BindView(R.id.item_my_ealuate_product_number)
        TextView productNumber;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}

