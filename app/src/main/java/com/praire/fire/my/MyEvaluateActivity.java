package com.praire.fire.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.car.CarActivity;
import com.praire.fire.car.ShopActivity;
import com.praire.fire.car.adapter.CarAdapter;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.my.adapter.MyEvaluateAdapter;
import com.praire.fire.my.bean.MyEvaluateBean;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.utils.RecycleViewDivider;
import com.praire.fire.utils.SaveArrayListUtil;
import com.praire.fire.utils.ToastUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的评价
 * Created by lyp on 2018/1/2.
 */

public class MyEvaluateActivity extends BaseTitleActivity {


    @BindView(R.id.my_evaluate_recyclerView)
    SwipeMenuRecyclerView myEvaluateRecyclerView;
    private int index = 1;
    private boolean isFrist = true;
    private MyEvaluateAdapter myAdapter;
    private List<MyEvaluateBean.PagelistBean> evaluateBeans = new ArrayList<>();

    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, MyEvaluateActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_my_evaluate;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);

        myEvaluateRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myEvaluateRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        myEvaluateRecyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL));
        myEvaluateRecyclerView.useDefaultLoadMore();
        myEvaluateRecyclerView.setLoadMoreListener(new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (evaluateBeans == null || evaluateBeans.isEmpty()) {
                    ToastUtil.show(MyEvaluateActivity.this, "暂无更多数据");
                    return;
                }
                ++index;
                isFrist = false;
                getListDate();
            }
        });
        myAdapter = new MyEvaluateAdapter(this);
       /* myEvaluateRecyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                ShopActivity.startActivity(MyEvaluateActivity.this,carBeanlist.get(position).getId(),false);
            }
        });*/
        myEvaluateRecyclerView.setAdapter(myAdapter);
    }

    private void getListDate() {
        OkhttpRequestUtil.get(ConstanUrl.COMMENT_COMMENTLIST + "?p=" + index, 1, true, uiHandler);
    }

    @Override
    protected void initListeners() {
        getListDate();
    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void networkResponse(Message msg) {
        switch (msg.what) {
            case 1:
                if(isFrist){
                    evaluateBeans.clear();
                }
                Gson gson = new Gson();
                MyEvaluateBean evaluateBean = gson.fromJson((String)msg.obj,MyEvaluateBean.class);
                myEvaluateRecyclerView.loadMoreFinish(evaluateBean.getPagelist().size() == 0,evaluateBean.getPagelist().size()  != 10);
                evaluateBeans.addAll(evaluateBean.getPagelist()) ;
                myAdapter.setEntities(evaluateBeans);
                break;
            default:
                break;
        }
    }

    @Override
    public void initiTile() {
        setDefaultBack();
        setTitleMiddle("我的评价");
    }

}
