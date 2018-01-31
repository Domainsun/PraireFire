package com.praire.fire.order.adapter;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.order.EvaluateActivity;
import com.praire.fire.order.bean.EvaluateCommitInfo;
import com.praire.fire.order.bean.OrderListBean;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EvaluateOrderAdapter extends RecyclerView.Adapter<EvaluateOrderAdapter.MyViewHolder> implements View.OnClickListener{

    private EvaluateActivity context;
    private List<OrderListBean.PagelistBean.PslistBean> entities = new ArrayList<>();

    public EvaluateOrderAdapter(EvaluateActivity context) {
        this.context = context;
    }

    public void setEntities(List<OrderListBean.PagelistBean.PslistBean> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                context).inflate(R.layout.layout_evaluate, parent,
                false);
        view.setOnClickListener(this);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.itemView.setTag(position);
        final EvaluateCommitInfo.OrderpsListBean item = new EvaluateCommitInfo.OrderpsListBean();
        OrderListBean.PagelistBean.PslistBean entity = entities.get(position);
        item.setOrderps_id(entity.getPs_id());
        item.setStar("5");
        item.setComment("默认好评！");
        item.setPiclist("");
        holder.evaluateCount.setText(String.format( holder.evaluateCount.getTag().toString(),entity.getNumber()));
        holder.evaluatePrice.setText(String.format( holder.evaluatePrice.getTag().toString(), entity.getPrice()));
        holder .evaluateProductName.setText(entity.getName());

        if ("1".equals(entity.getType())) {
            holder.evaluateProductImg.setVisibility(View.VISIBLE);
            Uri uri = Uri.parse(entity.getCover());
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setAutoPlayAnimations(true)
                    .build();
            holder.evaluateProductImg.setController(controller);
        }

        holder.course_star.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                item.setStar(rating + "");
            }
        });
        holder.inputEvaluate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                item.setComment(editable.toString());
                if(itemClickLister !=null) {
                    itemClickLister.itemClick(position,item);
                }
            }
        });
      /*  holder.imgEvaluate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.showChoosePic(REQUEST_CODE_UPLOAD_PRODUCT1, 1);
            }
        });
        holder.imgEvaluate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.showChoosePic(REQUEST_CODE_UPLOAD_PRODUCT2, 1);
            }
        });
        holder.imgEvaluate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.showChoosePic(REQUEST_CODE_UPLOAD_PRODUCT3, 1);
            }
        });
        holder.imgEvaluate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.showChoosePic(REQUEST_CODE_UPLOAD_PRODUCT4, 1);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    @Override
    public void onClick(View view) {
      /*  if(itemClickLister !=null) {
            itemClickLister.itemClick(view.getRootView(),(int)view.getTag());
        }*/
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.evaluate_product_img)
        SimpleDraweeView evaluateProductImg;
        @BindView(R.id.evaluate_product_name)
        TextView evaluateProductName;
        @BindView(R.id.evaluate_count)
        TextView evaluateCount;
        @BindView(R.id.evaluate_price)
        TextView evaluatePrice;
        @BindView(R.id.item_shop_ealuate_star)
        RatingBar course_star;
        @BindView(R.id.input_evaluate)
        EditText inputEvaluate;
        @BindView(R.id.img_evaluate1)
        SimpleDraweeView imgEvaluate1;
        @BindView(R.id.img_evaluate2)
        SimpleDraweeView imgEvaluate2;
        @BindView(R.id.img_evaluate3)
        SimpleDraweeView imgEvaluate3;
        @BindView(R.id.img_evaluate4)
        SimpleDraweeView imgEvaluate4;
        @BindView(R.id.image_say)
        TextView imageSay;
        @BindView(R.id.imag_add)
        LinearLayout imagAdd;
        @BindView(R.id.evaluate_checkbox)
        CheckBox evaluateCheckbox;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }

     ItemClickLister itemClickLister;

    public void setItemClickLister( ItemClickLister itemClickLister) {
        this.itemClickLister = itemClickLister;
    }

    public interface ItemClickLister {
        void itemClick(int position, EvaluateCommitInfo.OrderpsListBean item);
    }
}


