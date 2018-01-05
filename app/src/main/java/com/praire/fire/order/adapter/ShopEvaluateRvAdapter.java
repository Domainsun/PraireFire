package com.praire.fire.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;


import com.praire.fire.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 评价列表适配器
 */

public class ShopEvaluateRvAdapter extends RecyclerView.Adapter<ShopEvaluateRvAdapter.MyViewHolder> {
    private Context context;
//    private List<EvaluateFoodEntity> entities = new ArrayList<>();

    public ShopEvaluateRvAdapter(Context context) {
        this.context = context;
    }

   /* public void setEntities(List<EvaluateFoodEntity> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }*/

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_shop_evaluate, parent,
                false));
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       /* EvaluateFoodEntity entity = entities.get(position);
        holder.date.setText(stringToDate(entity.createTime));//日期截取年月日
        holder.name.setText(entity.fC_UserUid);
        if(!"".equals(entity.fC_Content)) {
            holder.content.setText(entity.fC_Content);
        }
        int score = Integer.parseInt(entity.fC_Score);
        if(score > 0) {
            holder. course_star.setVisibility(View.VISIBLE);
            holder.course_star.setNumStars(score);
            holder.course_star.setRating (score);
        }else{//分数为0不显示评分
            holder.course_star.setVisibility(View.GONE);
        }*/
    }

    @Override
    public int getItemCount()
    {
        return 0;//entities.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView name, date,content;
        /**
         * 评分
         */
        RatingBar course_star;
        public MyViewHolder(View view)
        {
            super(view);
            name = (TextView) view.findViewById(R.id.item_shop_ealuate_name);
            date = (TextView) view.findViewById(R.id.item_shop_ealuate_date);

            content = (TextView) view.findViewById(R.id.item_query_pay_content);
            course_star = (RatingBar) view.findViewById(R.id.item_shop_ealuate_star);
        }
    }
    /**
     *   字符转日期
     * @param date
     * @return
     */
    public String stringToDate(String date){
        SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date dat = format.parse(date);
            String dateString = format.format(dat);
            return dateString;
        } catch (ParseException e) {
            return null;
        }
    }
}

