package com.praire.fire.car;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.car.adapter.CommitOrderAdapter;
import com.praire.fire.car.bean.CommitProduct;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.data.IntentDataForCommitOrderActivity;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.order.OrderInfoActivity;
import com.praire.fire.order.OrderUtils;
import com.praire.fire.order.adapter.CommitOrderBean;
import com.praire.fire.utils.SharePreferenceMgr;
import com.praire.fire.utils.ToastUtil;
import com.praire.fire.utils.statusbarcolor.Eyes;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import static com.praire.fire.common.Constants.INTENT_DATA;

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
        adapter.setItemClickLister(new CommitOrderAdapter.ItemClickLister() {
            @Override
            public void mine(int position, int number) {
                commitProductList.get(position).setNumber(number);
                commitOrderPrice.setText(String.format(commitOrderPrice.getTag().toString(), OrderUtils.totlePrice(commitProductList)));
                commitOrderTotlePrice.setText(String.format(commitOrderTotlePrice.getTag().toString(), OrderUtils.totlePrice(commitProductList)));
            }

            @Override
            public void add(int position, int number) {
                commitProductList.get(position).setNumber(number);
                commitOrderPrice.setText(String.format(commitOrderPrice.getTag().toString(), OrderUtils.totlePrice(commitProductList)));
                commitOrderTotlePrice.setText(String.format(commitOrderTotlePrice.getTag().toString(), OrderUtils.totlePrice(commitProductList)));
            }


        });
    }

    @Override
    protected void initListeners() {
        commitOrderPrice.setText(String.format(commitOrderPrice.getTag().toString(), OrderUtils.totlePrice(commitProductList)));
        commitOrderTotlePrice.setText(String.format(commitOrderTotlePrice.getTag().toString(), OrderUtils.totlePrice(commitProductList)));
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
        String personInfos = OrderUtils.setArguments(commitProductList);
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
        Log.e("(String) msg.obj", (String) msg.obj);
        switch (msg.what) {
            case 1:
                Gson gson = new Gson();
                commitBean = gson.fromJson((String) msg.obj, CommitOrderBean.class);
                ToastUtil.show(this, commitBean.getMsg());
                if (commitBean.getCode() == 1) {
                    OrderInfoActivity.startActivity(this, commitBean.getOrderarr().get(0), false);
                    finish();
                }

                break;
            default:
                break;
        }
    }


}
