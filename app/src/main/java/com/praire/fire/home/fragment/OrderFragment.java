package com.praire.fire.home.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.praire.fire.R;
import com.praire.fire.base.BaseFragment;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.order.EvaluateActivity;
import com.praire.fire.order.OrderInfoActivity;
import com.praire.fire.order.PayActivity;
import com.praire.fire.order.adapter.OrderListAdapter;
import com.praire.fire.order.bean.OrderListBean;
import com.praire.fire.utils.RecycleViewDivider;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import okhttp3.FormBody;
import okhttp3.RequestBody;

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
     * 退款  就是已退款
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
        srecyclerView.setItemAnimator(new DefaultItemAnimator());
        srecyclerView.setAdapter(adapter);
        srecyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, final int position) {
                final OrderListBean.PagelistBean bean = orderlist.getPagelist().get(position);
                itemView.findViewById(R.id.item_order_list_clean).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if("1".equals(bean.getRefund())){
                            refundOrder(bean.getId());
                        }else{
                            cancelOrder(bean.getId());
                        }

                    }
                });

                itemView.findViewById(R.id.item_order_list_status_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (bean.getStatus()) {
                            case "0":
                                PayActivity.startActivity(getActivity(),bean.getId(),"0",false);
                                break;
                            case "1":
                                checkOrder(bean.getId());
                                break;
                            case "2":
//                                评价
                                EvaluateActivity.startActivity(getActivity(),bean.getId(),false);
                                break;
                            case "3":

                                break;
                            default:
                                break;
                        }
                    }
                });

                OrderInfoActivity.startActivity(getActivity(), bean.getId(), false);
            }
        });
    }

    @Override
    public void initData() {
        isFirst = true;
        index = 1;

        getDates(index, "");
    }

    private void getDates(int index, String status) {
        OkhttpRequestUtil.get(ConstanUrl.ORDER_ORDERLIST + "?status=" + status + "&p=" + index, 1, true, uiHandler);
    }


    @Override
    protected void networkResponse(Message msg) {
        switch (msg.what) {
            case 1:
                Log.e("orderlist", (String) msg.obj);
                Gson gson = new Gson();
                orderlist = gson.fromJson((String) msg.obj, OrderListBean.class);
                if (orderlist != null) {
                    adapter.setEntities(orderlist.getPagelist());
                }
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
                orderType = FOR_IS_REFUND;
            default:
                break;
        }
        getDates(1, statusType);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /**
     * 取消订单
     *
     * @param orderId 订单id
     */
    private void cancelOrder(String orderId) {
        RequestBody requestBody = new FormBody.Builder()
                .add("id", orderId)
                .build();
        OkhttpRequestUtil.post(ConstanUrl.ORDER_CANCEL, requestBody, 2, uiHandler, true);
    }

    /**
     * 申请退款
     *
     * @param orderId 订单id
     */
    private void refundOrder(String orderId) {
        RequestBody requestBody = new FormBody.Builder()
                .add("id", orderId)
                .build();
        OkhttpRequestUtil.post(ConstanUrl.ORDER_REFUND, requestBody, 3, uiHandler, true);
    }

    /**
     * 用户确认消费
     *
     * @param orderId 订单id
     */
    private void checkOrder(String orderId) {
        getPayPassword();
        RequestBody requestBody = new FormBody.Builder()
                .add("id", orderId)
//                .add("paypassword", payPsd)
                .build();
        OkhttpRequestUtil.post(ConstanUrl.ORDER_CHECKUSE, requestBody, 4, uiHandler, true);
    }

    private void getPayPassword() {
//        payPsd
    }
}
