package com.praire.fire.car.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.praire.fire.R;
import com.praire.fire.car.bean.TypeMenuBean;

import java.util.ArrayList;
import java.util.List;


public class TypeMenuPopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    //HeaderView
    private View mHeaderView;
    private Activity activity;
    private List<?> entities = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;
    private int type;

    public TypeMenuPopAdapter(FragmentActivity activity) {
        this.activity = activity;
    }

    public void setEntities(List<?> entities, int type) {
        this.entities = entities;
        this.type = type;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            mHeaderView.setOnClickListener(this);
            return new ListHolder(mHeaderView);
        }
        View view = LayoutInflater.from(
                activity).inflate(R.layout.pop_type_menu_item, parent,
                false);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return new ListHolder(view);//holder;//new ListHolder(layout);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            if (holder instanceof ListHolder) {
                //将position保存在itemView的Tag中，以便点击时进行获取
                holder.itemView.setTag(position);
                //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了

                String names = null;
                String count = null;
                if(type == 1) {
                      names = ((TypeMenuBean.ProductTypeListBean) entities.get(position)).getName();
//                    count = ((TypeMenuBean.ProductTypeListBean) entities.get(position)).getName();
                }else if (type == 2){
                    names = ((TypeMenuBean.ServiceTypeListBean) entities.get(position)).getName();
//                    count = ((TypeMenuBean.ProductTypeListBean) entities.get(position)).getId();
                }
                ((ListHolder) holder).name.setText(names);
//                ((ListHolder) holder).count.setText(count);
            }
        }

    }

    //在这里面加载ListView中的每个item的布局
    class ListHolder extends RecyclerView.ViewHolder {
        TextView name,count;

        ListHolder(View itemView) {
            super(itemView);
            //如果是headerview或者是footerview,直接返回
            if (itemView == mHeaderView) {
                itemView.setTag(-1);
                return;
            }

            name = itemView.findViewById(R.id.type_menu_item_tv);
            count=  itemView.findViewById(R.id.type_menu_item_count);
        }
    }

    //返回View中Item的个数，这个时候，总的个数应该是ListView中Item的个数加上HeaderView和FooterView
    @Override
    public int getItemCount() {
        if (mHeaderView != null) {
            return entities.size() + 1;
        } else {
            return entities.size();
        }
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag(), type);
        }

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    //define interface
    public interface OnItemClickListener {
        void onItemClick(View view, int position, int type);
    }

    //HeaderView和FooterView的get和set函数
    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    /**
     * 重写这个方法，很重要，是加入Header和Footer的关键，我们通过判断item的类型，从而绑定不同的view    *
     */
    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0) {
            //第一个item应该加载Header
            return TYPE_HEADER;
        }
        return TYPE_NORMAL;
    }


}
