package com.praire.fire.order;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.order.adapter.OrderFinishInfoAdapter;
import com.praire.fire.order.adapter.OrderInfoAdapter;
import com.praire.fire.order.bean.OrderInfoBean;
import com.praire.fire.utils.AppBigDecimal;
import com.praire.fire.utils.RecycleViewDivider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import static android.content.Intent.ACTION_CALL;
import static com.praire.fire.common.Constants.INTENT_DATA;

/**
 * 订单详情 （售后、付款后）
 * Created by lyp on 2018/1/5.
 */
public class OrderFinishInfoActivity extends BaseTitleActivity {


    @BindView(R.id.item_order_info_businessname)
    TextView orderInfoBusinessname;
    @BindView(R.id.recyclerview_product_info)
    RecyclerView recyclerviewProductInfo;
    @BindView(R.id.item_order_info_totle_products)
    TextView orderInfoTotleProducts;
    @BindView(R.id.item_order_info_discounts)
    TextView orderInfoDiscounts;
    @BindView(R.id.item_order_info_totle_order)
    TextView orderInfoTotleOrder;
    @BindView(R.id.item_order_info_disbursements)
    TextView orderInfoDisbursements;
    @BindView(R.id.item_order_info_msg)
    TextView orderInfoMsg;
    @BindView(R.id.item_order_info_call)
    TextView orderInfoCall;
    @BindView(R.id.order_info_order_id)
    TextView orderInfoOrderId;
    @BindView(R.id.order_info_pay_time)
    TextView orderInfoPayTime;
    private String orderId;
    private OrderFinishInfoAdapter adapter;
    private OrderInfoBean infoBean;
    private String totlePrice = "0";

    public static void startActivity(Context context, String orderId, boolean forResult) {
        Intent intent = new Intent(context, OrderFinishInfoActivity.class);
        intent.putExtra(INTENT_DATA, orderId);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_order_finish_info;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        orderId = getIntent().getStringExtra(INTENT_DATA);
        adapter = new OrderFinishInfoAdapter(this);
        recyclerviewProductInfo.setLayoutManager(new LinearLayoutManager(this));
        //添加分割线
        recyclerviewProductInfo.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL));
        recyclerviewProductInfo.setItemAnimator(new DefaultItemAnimator());
        recyclerviewProductInfo.setAdapter(adapter);
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
                Log.e("dataINfo", (String) msg.obj);
                Gson gson = new Gson();

                infoBean = gson.fromJson((String) msg.obj, OrderInfoBean.class);

                adapter.setEntities(infoBean.getOrderlist().get(0).getPslist());

                String totleNPrice = OrderUtils.totleNPriceInfo(infoBean.getOrderlist());
                totlePrice= OrderUtils.totlePriceInfo(infoBean.getOrderlist());
                //商品总价
                orderInfoTotleProducts.setText(String.format(orderInfoTotleProducts.getTag().toString(), totleNPrice));
                //优惠折扣
                orderInfoDiscounts.setText(String.format(orderInfoDiscounts.getTag().toString(), AppBigDecimal.substract(totleNPrice,totlePrice,2)));
                //订单总价
                orderInfoTotleOrder.setText(String.format(orderInfoTotleOrder.getTag().toString(), totlePrice));

                orderInfoPayTime.setText(String.format(orderInfoPayTime.getTag().toString(), infoBean.getOrderlist().get(0).getPay_time()));
                orderInfoOrderId.setText(String.format(orderInfoOrderId.getTag().toString(), infoBean.getOrderlist().get(0).getOrderno()));
                String disbursements = OrderUtils.totlePriceInfo2(infoBean.getOrderlist());
                //实付款
                orderInfoDisbursements.setText(String.format(orderInfoDisbursements.getTag().toString(), disbursements));
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






    @OnClick({R.id.item_order_info_msg, R.id.item_order_info_call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_order_info_msg:
                break;
            case R.id.item_order_info_call:
                if(infoBean !=null && !"".equals(infoBean.getOrderlist().get(0).getShoptel())) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        return;
                    }
                    startActivity(new Intent(ACTION_CALL, Uri.parse("tel:" + infoBean.getOrderlist().get(0).getShoptel())));
                }
                break;
                default:
                    break;
        }
    }
}
