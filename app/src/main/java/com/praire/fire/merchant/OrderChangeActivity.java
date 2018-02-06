package com.praire.fire.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.merchant.adapter.OrderChangeAdapter;
import com.praire.fire.merchant.adapter.ProductAdapter;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.OrderDetailsInfoBean;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.DateUtils;
import com.praire.fire.utils.SharePreferenceMgr;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;

public class OrderChangeActivity extends BaseActivity {
    String orderId = "";
    UseAPIs u = new UseAPIs();
    J2O j = new J2O();
    String cookie = "";
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_contact_person)
    TextView tvContactPerson;
    @BindView(R.id.tv_contact_phone)
    TextView tvContactPhone;
    @BindView(R.id.tv_orderId)
    TextView tvOrderId;
    @BindView(R.id.tv_time)
    TextView tvTime;

    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_discount)
    TextView tvDiscount;
    @BindView(R.id.submit)
    Button submit;

    @BindView(R.id.tv_shop_price)
    TextView tvShopPrice;


    SwipeMenuRecyclerView recyclerView;


    double cprice = 0.00;
    double sprice = 0.00;



    OrderChangeAdapter adapter;
    List<OrderDetailsInfoBean.OrderinfoBean.PslistBean> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_change);
        ButterKnife.bind(this);


        cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");
        Intent i = getIntent();
        orderId = i.getStringExtra("orderId");

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrderChangeAdapter(this);

        getData();



        adapter.setmOnItemClickListener(new OrderChangeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String psId, double nprice) {

                try {

                    String str = u.changeOrderPrice(psId, nprice + "", orderId, cookie);

                    Log.d("nprice", "nprice: "+nprice+"   "+psId+"  "+   orderId);
                    APIResultBean a = j.getAPIResult(str);
                    if (1==a.getCode()) {

                        getData();

                        adapter.notifyDataSetChanged();

                    }
                    Toast.makeText(OrderChangeActivity.this, a.getMsg(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {

                    Log.e("Exception", "Exception: " + e.toString());

                }
            }
        });

        recyclerView.setAdapter(adapter);


    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_order_change;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

    }


    public void getData() {

        cprice=0.00;
        sprice=0.00;


        for (int i = 0; i < datas.size(); i++) {
            datas.remove(i);
        }

        String str = u.getOrderInfo(orderId, cookie);
        OrderDetailsInfoBean o = j.getOrderInfo(str);


        Log.d("str", "onCreate: " + str);
        datas = o.getOrderinfo().getPslist();
        for (int j = 0; j < datas.size(); j++) {
            int count = Integer.parseInt(o.getOrderinfo().getPslist().get(j).getNumber());
            double cprice1 = Double.parseDouble(o.getOrderinfo().getPslist().get(j).getPrice());
            double sprice1 = Double.parseDouble(o.getOrderinfo().getPslist().get(j).getShopprice());
            cprice = cprice + (count * cprice1);
            sprice = sprice + (count * sprice1);
        }

        tvContactPerson.setText(o.getOrderinfo().getNickname());
        tvContactPhone.setText(o.getOrderinfo().getTel());
        tvOrderId.setText(o.getOrderinfo().getOrderno());
        tvTime.setText(DateUtils.times1(o.getOrderinfo().getCreate_time()));
        tvPrice.setText(cprice + "");
        tvShopPrice.setText(sprice + "");

        adapter.setData(datas);
    }


    @OnClick({R.id.tv_back, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.submit:
                finish();
                break;
        }
    }


}
