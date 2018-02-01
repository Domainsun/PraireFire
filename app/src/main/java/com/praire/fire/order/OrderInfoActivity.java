package com.praire.fire.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.order.adapter.OrderInfoAdapter;
import com.praire.fire.order.adapter.OrderListAdapter;
import com.praire.fire.order.bean.OrderInfoBean;
import com.praire.fire.utils.RecycleViewDivider;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import static com.praire.fire.common.Constants.INTENT_DATA;

/**
 * 订单详情
 * Created by lyp on 2018/1/5.
 */
public class OrderInfoActivity extends BaseTitleActivity {

    @BindView(R.id.order_info_recyclerview)
    SwipeMenuRecyclerView orderInfoRecyclerview;
    @BindView(R.id.order_info_price)
    TextView orderInfoPrice;
    @BindView(R.id.order_info_commit)
    TextView orderInfoCommit;
    private String orderId;
    private OrderInfoAdapter adapter;
    private OrderInfoBean infoBean;
    private String totlePrice = "0";

    public static void startActivity(Context context, String orderId , boolean forResult) {
        Intent intent = new Intent(context, OrderInfoActivity.class);
        intent.putExtra(INTENT_DATA, orderId);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_order_info;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        orderId = getIntent().getStringExtra(INTENT_DATA);
        adapter = new OrderInfoAdapter(this);
        orderInfoRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        //添加分割线
        orderInfoRecyclerview.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, 10, getResources().getColor(R.color.grey_background)));
        orderInfoRecyclerview.setItemAnimator(new DefaultItemAnimator());
        orderInfoRecyclerview.setAdapter(adapter);
    }

    @Override
    protected void initListeners() {


    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {
        RequestBody requestBody = new FormBody.Builder()
                .add("orderno[]", orderId)
                .build();
        OkhttpRequestUtil.post(ConstanUrl.ORDER_ORDERINFO, requestBody, 1, uiHandler, true);

    }

    @Override
    protected void networkResponse(Message msg) {
        switch (msg.what) {
            case 1:
                Gson gson = new Gson();
                infoBean = gson.fromJson((String) msg.obj,OrderInfoBean.class);
                adapter.setEntities(infoBean.getOrderlist());
                totlePrice = OrderUtils.totlePriceInfo(infoBean.getOrderlist());
                orderInfoPrice.setText(String.format(orderInfoPrice.getTag().toString(),totlePrice));
                break;
            default:
                break;
        }
    }

    @Override
    public void initiTile() {
        setDefaultBack();
        setTitleMiddle("订单详情");
    }


    @OnClick(R.id.order_info_commit)
    public void onViewClicked() {
        PayActivity.startActivity(OrderInfoActivity.this,orderId,"0",totlePrice,false);
        finish();
    }
}
