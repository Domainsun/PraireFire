package com.praire.fire.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.praire.fire.MyApplication;
import com.praire.fire.R;
import com.praire.fire.base.BaseFragment;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.order.adapter.OrderAdapter;
import com.praire.fire.utils.RecycleViewDivider;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
    private boolean loadMore = true;
    /**
     * //订单状态（0：待付款，1：已付款）
     */
    private String statusType = "0";
    /**
     * //是否评价（0：未评价，1：已评价）
     */
    private String isComment = "0";

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

        refresh.setNestedScrollView(R.id.order_list_recyclerview);
        refresh.setPullRefreshEnable(true);
        refresh.setPullLoadEnable(true);
        // 设置刷新view的类型
//        refresh.setRefreshViewType(XRefreshViewType.ABSLISTVIEW);
        refresh.setPinnedTime(Constants.REFRESH_PINNED_TIME);
        refresh.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }

            @Override
            public void onLoadMore() {
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

    private void getDates(final int index) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody formBody = new FormBody.Builder()
                        .add("status", statusType)
                        .add("comment", isComment)
                        .add("PHPSESSID", new MyApplication().getSignCookie())
                        .build();
                Request request = new Request.Builder()
                        .url(ConstanUrl.ORDER_orderlist + "?p=" + index)
                        .post(formBody)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "网络出错，请重试", Toast.LENGTH_SHORT).show();
                                loadMore = false;
                            }
                        });
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                        String data = response.body().string();
                        if (data == null) {
                            loadMore = false;
                        }
                        Log.e("data", data);
                       /* Gson gson = new Gson();
                        final ShopListBean evEntity = gson.fromJson(data, ShopListBean.class);
                        orderEntitys = evEntity.getPagelist();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.setEntities(orderEntitys);
                            }
                        });*/

                    }
                });

            }
        });
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
                statusType = "0";
                isComment = "0";
                orderType = FOR_WAIT_PAY;
                break;
            case 1:
                statusType = "1";
                isComment = "0";
                orderType = FOR_WAIT_SIGN;
                break;
            case 2:
                statusType = "1";
                isComment = "0";
                orderType = FOR_WAIT_EVALUATE;
                break;
            case 3:
                statusType = "";
                isComment = "";
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
