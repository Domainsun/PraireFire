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
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseFragment;
import com.praire.fire.common.CommonDialog;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.my.bean.CommentResultBean;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.order.EvaluateActivity;
import com.praire.fire.order.OrderInfoActivity;
import com.praire.fire.order.OrderUtils;
import com.praire.fire.order.PayActivity;
import com.praire.fire.order.adapter.OrderListAdapter;
import com.praire.fire.order.bean.OrderListBean;
import com.praire.fire.utils.RecycleViewDivider;
import com.praire.fire.utils.ToastUtil;
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
    public static final int CANCEL_ORDER = 2;
    public static final int REFUND_ORDER = 3;
    public static final int CHECK_ORDER = 4;
    public static final int ORDER_LIST = 1;

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
    private String statusType = "";
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
                getActivity(), LinearLayoutManager.HORIZONTAL,10,getActivity().getResources().getColor(R.color.grey_background)));
        srecyclerView.setItemAnimator(new DefaultItemAnimator());
        srecyclerView.setAdapter(adapter);
        srecyclerView.setItemViewSwipeEnabled(true);

        srecyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                OrderListBean.PagelistBean bean = orderlist.getPagelist().get(position);
                OrderInfoActivity.startActivity(getActivity(), bean.getOrderno(), false);
            }
        });
        adapter.setItemClickLister(new OrderListAdapter.ItemClickLister() {
            @Override
            public void cancel(String status, final String orderId) {
                if ("1".equals(status)) {
                    CommonDialog.Build((BaseActivity)getActivity()).setTitle(false).setMessage("是否确认退款？").setConfirm(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            refundOrder(orderId);
                        }
                    }).showconfirm();

                } else {
                    CommonDialog.Build((BaseActivity) getActivity()).setTitle(false).setMessage("是否确认取消订单？").setConfirm(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cancelOrder(orderId);
                        }
                    }).showconfirm();

                }
            }

            @Override
            public void btnStatus(String status, String orderno, final int position, String paycost) {
//                EvaluateActivity.startActivity(getActivity(), orderlist.getPagelist().get(position), false);
                switch (status) {
                    case "0":
                        PayActivity.startActivity(getActivity(), orderno, "0",paycost, false);
                        break;
                    case "1":
                        CommonDialog.Build((BaseActivity)getActivity()).setTitle(false).setMessage("是否确认消费？").setConfirm(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                checkOrder(orderlist.getPagelist().get(position).getId());
                            }
                        }).showconfirm();

                        break;
                    case "2":
//                                评价
                        EvaluateActivity.startActivity(getActivity(), orderlist.getPagelist().get(position), false);
                        break;
                    default:
                        break;
                }
            }


        });
    }

    @Override
    public void initData() {
        isFirst = true;
        index = 1;

        getDates(index, statusType);
    }

    private void getDates(int index, String status) {
        OkhttpRequestUtil.get(ConstanUrl.ORDER_ORDERLIST + "?status=" + status + "&p=" + index, ORDER_LIST, true, uiHandler);
    }


    @Override
    protected void networkResponse(Message msg) {
        switch (msg.what) {
            case ORDER_LIST:
                Log.e("orderlist", (String) msg.obj);
                Gson gson = new Gson();
                orderlist = gson.fromJson((String) msg.obj, OrderListBean.class);
                if (orderlist != null) {
                    adapter.setEntities(orderlist.getPagelist());
                }
                break;
            case CANCEL_ORDER:
                Gson gson2 = new Gson();
                CommentResultBean  resultBean = gson2.fromJson((String) msg.obj, CommentResultBean.class);
                ToastUtil.show(getActivity(),resultBean.getMsg());
                getDates(1,statusType);
                break;
            case REFUND_ORDER:
                Log.e("REFUND_ORDER", (String) msg.obj);
                Gson gson3 = new Gson();
                CommentResultBean  resultBean1 = gson3.fromJson((String) msg.obj, CommentResultBean.class);
                ToastUtil.show(getActivity(),resultBean1.getMsg());
                getDates(1,statusType);
                break;
            case CHECK_ORDER:
                Log.e("CHECK_ORDER", (String) msg.obj);
                Gson gson4 = new Gson();
                CommentResultBean  resultBean2 = gson4.fromJson((String) msg.obj, CommentResultBean.class);
                ToastUtil.show(getActivity(),resultBean2.getMsg());
                getDates(1,statusType);

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
        OkhttpRequestUtil.post(ConstanUrl.ORDER_CANCEL, requestBody, CANCEL_ORDER, uiHandler, true);
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
        OkhttpRequestUtil.post(ConstanUrl.ORDER_REFUND, requestBody, REFUND_ORDER, uiHandler, true);
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
        OkhttpRequestUtil.post(ConstanUrl.ORDER_CHECKUSE, requestBody, CHECK_ORDER, uiHandler, true);
    }

    private void getPayPassword() {
//        payPsd
    }
}
