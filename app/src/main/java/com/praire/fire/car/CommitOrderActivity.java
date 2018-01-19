package com.praire.fire.car;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.AnimatorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.praire.fire.MyApplication;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.car.adapter.CommitOrderAdapter;
import com.praire.fire.car.bean.CommitProduct;
import com.praire.fire.car.bean.ProductInfoBean;
import com.praire.fire.car.bean.ServiceInfoBean;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.data.IntentDataForCommitOrderActivity;
import com.praire.fire.my.adapter.ShopCarAdapter;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.order.OrderInfoActivity;
import com.praire.fire.order.adapter.CommitOrderBean;
import com.praire.fire.utils.CalculateUtils;
import com.praire.fire.utils.RecycleViewDivider;
import com.praire.fire.utils.SharePreferenceMgr;
import com.praire.fire.utils.ToastUtil;
import com.praire.fire.utils.statusbarcolor.Eyes;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.praire.fire.common.Constants.INTENT_DATA;
import static com.praire.fire.common.Constants.LOGIN_COOKIE;

/**
 * 提交订单
 * Created by lyp on 2017/12/29.
 */
public class CommitOrderActivity extends BaseTitleActivity {


    @BindView(R.id.recyclerview_commit_order)
    SwipeMenuRecyclerView recyclerviewCommitOrder;
    @BindView(R.id.commit_order_price)
    TextView commitOrderPrice;
    @BindView(R.id.commit_order_ticket)
    TextView commitOrderTicket;
    @BindView(R.id.commit_order_ticket_ll)
    LinearLayout commitOrderTicketLl;
    @BindView(R.id.commit_order_totle_price)
    TextView commitOrderTotlePrice;
    @BindView(R.id.commit_order_totle_price_ll)
    LinearLayout commitOrderTotlePriceLl;
    @BindView(R.id.commit_order_phone)
    TextView commitOrderPhone;
    List<CommitProduct> commitProductList;

    private CommitOrderAdapter adapter;
    private CommitOrderBean commitBean;

    private int count = 1;
    IntentDataForCommitOrderActivity data;

    public static void startActivity(Context context, IntentDataForCommitOrderActivity bean, boolean forResult) {
        Intent intent = new Intent(context, CommitOrderActivity.class);
        intent.putExtra(INTENT_DATA, bean);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_commit_order;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        Eyes.setStatusBarColor(this, ContextCompat.getColor(this, R.color.status_bar));

        data = getIntent().getParcelableExtra(INTENT_DATA);
        commitProductList = data.commitProductList;
        count = data.count;

        recyclerviewCommitOrder.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewCommitOrder.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
//        recyclerviewCommitOrder.addItemDecoration(new RecycleViewDivider(
//                this, LinearLayoutManager.HORIZONTAL));

        adapter = new CommitOrderAdapter(this);
        recyclerviewCommitOrder.setAdapter(adapter);
        adapter.setEntities(commitProductList);

    }

    @Override
    protected void initListeners() {
        commitOrderPrice.setText(String.format(commitOrderPrice.getTag().toString(), CalculateUtils.totlePrice(commitProductList)));
        commitOrderTotlePrice.setText(String.format(commitOrderTotlePrice.getTag().toString(), CalculateUtils.totlePrice(commitProductList)));
    }

    @Override
    protected void initAdapters() {
//        if ("0".equals(data.type)) {
//            adapter.setEntities(commitProductList);
//        }
    }

    @Override
    protected void initData() {

        commitOrderPhone.setText((String) SharePreferenceMgr.get(this, Constants.USER_ID, ""));
    }

    @Override
    public void initiTile() {
        setDefaultBack();
        setTitleMiddle(getString(R.string.commit_order));
    }


    @OnClick(R.id.btn_commit)
    public void onViewClicked() {
       /* String ps_id;
        if ("2".equals(data.type)) {
            ps_id = serviceBean.getInfo().getId();
        } else if("1".equals(data.type)){
            ps_id = productBean.getInfo().getId();
        }*/

        try {
            commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void commit() throws JSONException {

        JSONArray jsonArray = new JSONArray();
        JSONObject tmpObj = null;

        int count = commitProductList.size();

        for (int i = 0; i < count; i++) {

            tmpObj = new JSONObject();

            tmpObj.put("type", commitProductList.get(i).getType());

            tmpObj.put("ps_id", commitProductList.get(i).getPs_id());

            tmpObj.put("number", commitProductList.get(i).getNumber());

            jsonArray.put(tmpObj);

            tmpObj = null;

        }
// 将JSONArray转换得到String
        String personInfos = jsonArray.toString();
        // 获得JSONObject的String
        Log.e("orderstring",personInfos);
        /**
         * 提交订单
         */
        RequestBody requestBody = new FormBody.Builder()
                //（1：产品，2：服务）
                .add("orderstring", personInfos)
                .build();
        OkhttpRequestUtil.post(ConstanUrl.ORDER_IN, requestBody, 1, uiHandler, true);
    }

    @Override
    protected void networkResponse(Message msg) {
        Log.e("(String) msg.obj",(String) msg.obj);
        switch (msg.what) {
            case 1:
                Gson gson = new Gson();
                commitBean = gson.fromJson((String) msg.obj, CommitOrderBean.class);
                ToastUtil.show(this,commitBean.getMsg());
                if(commitBean.getCode() == 1){
                    OrderInfoActivity.startActivity(this,commitBean.getOrderarr().get(0),false);
                    finish();
                }

                break;
            default:
                break;
        }
    }


}
