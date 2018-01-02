package com.praire.fire.home.fragment;

import android.content.Intent;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andview.refreshview.XRefreshView;
import com.praire.fire.R;
import com.praire.fire.base.BaseFragment;
import com.praire.fire.common.Constants;
import com.praire.fire.order.OrderAdapter;
import com.praire.fire.utils.RecycleViewDivider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 订单
 * Created by lyp on 2017/12/27.
 */

public class OrderFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {

    /**
     * 待付款
     */
    private static final int FOR_WAIT_PAY = 0;
    /**
     * 待消费
     */
    private static final int FOR_WAIT_SIGN = 1;
    /**
     * 待评价
     */
    private static final int FOR_WAIT_EVALUATE = 2;
    /**
     * 全部订单
     */
    private static final int FOR_ALL_ORDER = 3;

    @BindView(R.id.order_tabs)
    TabLayout tabLayout;
    @BindView(R.id.order_list_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.order_list_refresh)
    XRefreshView refresh;
    Unbinder unbinder;

    private boolean isFirst = true;
    private int orderType = 0;
    private int index = 1;
    private long lastRequestTime = 0;
    private OrderAdapter adapter;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initListener() {
        tabLayout.addTab(tabLayout.newTab().setText("待付款"));
        tabLayout.addTab(tabLayout.newTab().setText("待消费"));
        tabLayout.addTab(tabLayout.newTab().setText("待评价"));
        tabLayout.addTab(tabLayout.newTab().setText("全部订单"));
        tabLayout.setOnTabSelectedListener(this);
        adapter = new OrderAdapter(getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //添加分割线
        recyclerView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(adapter);
      /*  adapter.setOnItemClickListener(new ProductOrderRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               *//* Intent intent;
                if("3".equals(entities.get(position).fO_Type)) {
                    intent = new Intent(ProductOrderActivity.this, ScanCodePayDetailetActivity.class);
                    intent.putExtra(ORDER_ID, entities.get(position).fO_OrderId);
                }else{
                    intent = new Intent(ProductOrderActivity.this, OrderDetaileActivity.class);
                    intent.putExtra(ORDER_ID, entities.get(position).fO_OrderId);
                }
                startActivity(intent);*//*
            }
        });*/
//        refresh.setNestedScrollingEnabled(true);
//        refresh.setNestedScrollView(R.id.order_list_recyclerview);
        refresh.setPullRefreshEnable(true);
        refresh.setPullLoadEnable(true);
        // 设置刷新view的类型
//        refresh.setRefreshViewType(XRefreshViewType.ABSLISTVIEW);
        refresh.setPinnedTime(Constants.REFRESH_PINNED_TIME);
        refresh.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                initData();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                getNextPage();
            }

        });
    }

    @Override
    public void initData() {
        isFirst = true;
        index = 1;
        getDates(index);
    }

    private void getDates(int index) {

    }


    public void getNextPage() {
       /* if (entities == null || entities.isEmpty()) {
            refresh.stopRefresh();
            return;
        }*/
        isFirst = false;
        index++;
        getDates(index);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                orderType = FOR_WAIT_PAY;
                break;
            case 1:
                orderType = FOR_WAIT_SIGN;
                break;
            case 2:
                orderType = FOR_WAIT_EVALUATE;
                break;
            case 3:
                orderType = FOR_ALL_ORDER;
                break;
            default:
                break;
        }
        initData();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
