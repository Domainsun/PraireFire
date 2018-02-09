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
import com.praire.fire.data.IntentDataForEvaluateActivity;
import com.praire.fire.my.bean.CommentResultBean;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.order.EvaluateActivity;
import com.praire.fire.order.OrderFinishInfoActivity;
import com.praire.fire.order.OrderInfoActivity;
import com.praire.fire.order.OrderUtils;
import com.praire.fire.order.PayActivity;
import com.praire.fire.order.adapter.OrderListAdapter;
import com.praire.fire.order.bean.OrderListBean;
import com.praire.fire.utils.RecycleViewDivider;
import com.praire.fire.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * 订单
 * Created by lyp on 2017/12/27.
 */

public class OrderFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {


    public static final int CANCEL_ORDER = 2;
    public static final int REFUND_ORDER = 3;
    public static final int CHECK_ORDER = 4;
    public static final int ORDER_LIST = 1;

    TabLayout tabLayout;
    RecyclerView srecyclerView;
    SmartRefreshLayout refreshLayout;

    private boolean isFirst = true;
    private int index = 1;
    private OrderListAdapter adapter;
    private boolean loadMore = true;
    /**
     * 订单状态(0:未支付 1:已支付 2:已消费 3:已退款 4:已评价)
     */
    private String statusType = "";

    private List<OrderListBean.PagelistBean> orderlists = new ArrayList<>();
    private boolean isFrist = true;


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        initFindView(view);
        return view;
    }

    private void initFindView(View view) {
        tabLayout = view.findViewById(R.id.order_tabs);
        srecyclerView = view.findViewById(R.id.order_list_recyclerview);
        refreshLayout = view.findViewById(R.id.order_refreshLayout);
    }

    @Override
    public void initListener() {
        tabLayout.addTab(tabLayout.newTab().setText("全部"));
        tabLayout.addTab(tabLayout.newTab().setText("待付款"));
        tabLayout.addTab(tabLayout.newTab().setText("待消费"));
        tabLayout.addTab(tabLayout.newTab().setText("待评价"));
        tabLayout.addTab(tabLayout.newTab().setText("退款/售后"));
        tabLayout.setOnTabSelectedListener(this);


        refreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initData();

                //加载失败的话2秒后结束加载
//                refreshlayout.finishRefresh(2000  /*,false*/  );//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (loadMore) {
                    getNextPage();
                    //加载失败的话2秒后结束加载
//                    refreshlayout.finishLoadmore(2000 /*,false*/);//传入false表示加载失败
                } else {
                    refreshlayout.finishLoadmoreWithNoMoreData();
                }

            }
        });

        srecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //添加分割线
        srecyclerView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL, 10, getActivity().getResources().getColor(R.color.grey_background)));
        srecyclerView.setItemAnimator(new DefaultItemAnimator());


        adapter = new OrderListAdapter(getActivity());

        srecyclerView.setAdapter(adapter);
        setItemClick();
    }

    private void setItemClick() {
        adapter.setItemClickLister(new OrderListAdapter.ItemClickLister() {
            @Override
            public void cancel(String status, final String orderId) {
                if ("1".equals(status)) {
                    CommonDialog.Build((BaseActivity) getActivity()).setTitle(false).setMessage("是否确认退款？").setConfirm(new View.OnClickListener() {
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
            public void itemClick(View itemView, int position) {
                OrderListBean.PagelistBean bean = orderlists.get(position);
                if ("0".equals(bean.getStatus())) {
                    OrderInfoActivity.startActivity(getActivity(), bean.getOrderno(), false);

                } else {
                    OrderFinishInfoActivity.startActivity(getActivity(), bean.getOrderno(), false);
                }
            }

            @Override
            public void btnStatus(String status, String orderno, final int position, String paycost) {
                switch (status) {
                    case "0":
                        PayActivity.startActivity(getActivity(), orderno, "0", paycost, false);
                        break;
                    case "1":
                        CommonDialog.Build((BaseActivity) getActivity()).setTitle(false).setMessage("是否确认消费？").setConfirm(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                checkOrder(orderlists.get(position).getId());
                            }
                        }).showconfirm();

                        break;
                    case "2":
//                                评价
                        IntentDataForEvaluateActivity data = new IntentDataForEvaluateActivity();
                        data.orderInfo = orderlists.get(position);
                        EvaluateActivity.startActivity(getActivity(), data, false);
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
        getDates(index, statusType, ORDER_LIST);
    }

    private void getDates(int index, String status, int type) {
        OkhttpRequestUtil.get(ConstanUrl.ORDER_ORDERLIST + "?status=" + status + "&p=" + index, type, true, uiHandler);
    }


    @Override
    protected void networkResponse(Message msg) {

        switch (msg.what) {
            case ORDER_LIST:
                //结束加载
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
                if (isFrist) {
                    orderlists.clear();
                }
                Gson gson = new Gson();
                OrderListBean orderlist = gson.fromJson((String) msg.obj, OrderListBean.class);
                loadMore = !orderlist.getPagelist().isEmpty() && orderlist.getPagelist().size() % 10 == 0;
                orderlists.addAll(orderlist.getPagelist());
                adapter.setEntities(orderlists);

                break;

            case CANCEL_ORDER:
                Gson gson5 = new Gson();
                CommentResultBean resultBean = gson5.fromJson((String) msg.obj, CommentResultBean.class);
                ToastUtil.show(getActivity(), resultBean.getMsg());
                getDates(1, statusType, ORDER_LIST);
                break;
            case REFUND_ORDER:
                Log.e("REFUND_ORDER", (String) msg.obj);
                Gson gson6 = new Gson();
                CommentResultBean resultBean1 = gson6.fromJson((String) msg.obj, CommentResultBean.class);
                ToastUtil.show(getActivity(), resultBean1.getMsg());
                getDates(1, statusType, ORDER_LIST);
                break;
            case CHECK_ORDER:
                Log.e("CHECK_ORDER", (String) msg.obj);
                Gson gson7 = new Gson();
                CommentResultBean resultBean2 = gson7.fromJson((String) msg.obj, CommentResultBean.class);
                ToastUtil.show(getActivity(), resultBean2.getMsg());
                getDates(1, statusType, ORDER_LIST);

                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getDates(1, statusType, ORDER_LIST);
    }

    public void getNextPage() {
        isFirst = false;
        index++;
        getDates(index, statusType, ORDER_LIST);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        switch (tab.getPosition()) {
            case 0:
                statusType = "";
                break;
            case 1:
                statusType = "0";
                break;
            case 2:
                statusType = "1";
                break;
            case 3:
                statusType = "2";
                break;
            case 4:
                statusType = "3";
            default:
                break;
        }
        setDatas();

    }

    private void setDatas() {
        refreshLayout.resetNoMoreData();
        adapter = null;
        adapter = new OrderListAdapter(getActivity());
        srecyclerView.setAdapter(adapter);
        setItemClick();

        getDates(1, statusType, ORDER_LIST);
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
