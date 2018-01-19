package com.praire.fire.home.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.praire.fire.R;
import com.praire.fire.base.BaseFragment;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.order.adapter.OrderListAdapter;
import com.praire.fire.order.bean.OrderListBean;
import com.praire.fire.utils.RecycleViewDivider;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

/**
 * 订单
 * Created by lyp on 2017/12/27.
 */

public class OrderFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {

    /**
     * 待付款 未支付 就是待付款
     */
    private static final int FOR_WAIT_PAY = 1;
    /**
     * 待消费 已支付就是待消费
     */
    private static final int FOR_WAIT_SIGN = 2;
    /**
     * 待评价 已消费就是待评价
     */
    private static final int FOR_WAIT_EVALUATE = 3;
    /**
     *退款  就是已退款
     */
    private static final int FOR_IS_REFUND = 4;
    /**
     * 全部订单
     */
    private static final int FOR_ALL_ORDER = 0;

    TabLayout tabLayout;
    SwipeMenuRecyclerView srecyclerView;


    private boolean isFirst = true;
    private int orderType = 0;
    private int index = 1;
    private long lastRequestTime = 0;
    private OrderListAdapter adapter;
    private boolean loadMore = true;
    /**
     * 订单状态(0:未支付 1:已支付 2:已消费 3:已退款 4:已评价)
     */
    private String statusType = "0";
    private OrderListBean orderlist;


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        initFindView(view);
        return view;
    }

    private void initFindView(View view) {
        tabLayout = view.findViewById(R.id.order_tabs);
        srecyclerView = view.findViewById(R.id.order_list_recyclerview);

    }

    @Override
    public void initListener() {
        tabLayout.addTab(tabLayout.newTab().setText("全部"));
        tabLayout.addTab(tabLayout.newTab().setText("待付款"));
        tabLayout.addTab(tabLayout.newTab().setText("待消费"));
        tabLayout.addTab(tabLayout.newTab().setText("待评价"));
        tabLayout.addTab(tabLayout.newTab().setText("退款/售后"));
        tabLayout.setOnTabSelectedListener(this);
        adapter = new OrderListAdapter(getActivity());
        srecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //添加分割线
        srecyclerView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL));
        srecyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        isFirst = true;
        index = 1;

        getDates(index,"");
    }

    private void getDates(int index ,String status) {
        OkhttpRequestUtil.get(ConstanUrl.ORDER_ORDERLIST + "?status=" +status+"&p="+ index,1,true,uiHandler);
    }


    @Override
    protected void networkResponse(Message msg) {
        switch (msg.what) {
            case 1:
                Log.e("orderlist",(String)msg.obj);
                Gson gson = new Gson();
                orderlist = gson.fromJson((String)msg.obj, OrderListBean.class);
                adapter.setEntities(orderlist.getPagelist());

                break;
            default:
                break;
        }
    }

    public void getNextPage() {
       /* if (entities == null || entities.isEmpty()) {
            refresh.stopRefresh();
            return;
        }*/
        isFirst = false;
        index++;
//        getDates(index);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                statusType = "";
                orderType = FOR_ALL_ORDER;

                break;
            case 1:
                statusType = "0";
                orderType = FOR_WAIT_PAY;

                break;
            case 2:
                statusType = "1";
                orderType = FOR_WAIT_SIGN;

                break;
            case 3:
                statusType = "2";
                orderType = FOR_WAIT_EVALUATE;
                break;
            case 4:
                statusType = "3";
                orderType =  FOR_IS_REFUND;
            default:
                break;
        }
        getDates(1,statusType);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
