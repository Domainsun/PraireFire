package com.praire.fire.merchant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.BusinessTodayCountBean;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;

public class BusinessServiceActivity extends BaseActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;


    TextView moneyCount;
    @BindView(R.id.ll_income)
    LinearLayout llIncome;
    @BindView(R.id.tv_reconciliation)
    TextView tvReconciliation;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.tv_withdrawal)
    TextView tvWithdrawal;
    @BindView(R.id.tv_chose_shop_region)
    TextView tvChoseShopRegion;
    @BindView(R.id.rl_service_manage)
    RelativeLayout rlServiceManage;
    @BindView(R.id.rl_commodity_manage)
    RelativeLayout rlCommodityManage;
    @BindView(R.id.tv_order_manage_go)
    TextView tvOrderManageGo;
    @BindView(R.id.rl_order_manage)
    RelativeLayout rlOrderManage;
    @BindView(R.id.rl_member_manage)
    RelativeLayout rlMemberManage;
    @BindView(R.id.tv_evaluate_manage_go)
    TextView tvEvaluateManageGo;
    @BindView(R.id.rl_evaluate_manage)
    RelativeLayout rlEvaluateManage;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_deal_count)
    TextView tvDealCount;
    @BindView(R.id.tv_refund_count)
    TextView tvRefundCount;
    @BindView(R.id.tv_consume_count)
    TextView tvConsumeCount;
    @BindView(R.id.tv_order_count)
    TextView tvOrderCount;
    @BindView(R.id.tv_evaluate_count)
    TextView tvEvaluateCount;

    String orderCount="";
    String evaluateCount="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_business_service);




    }

    @Override
    protected int getFragmentLayout() {
        super.context=this;
        return R.layout.activity_business_service;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        String cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");
        String result1 = "";
        result1 = new UseAPIs().getBusinessTodayCount(cookie);
        if (result1.length() != 0) {

            try  {
                BusinessTodayCountBean b = new J2O().getBusinessTodayCount(result1);
                tvMoney.setText(b.getTotal_income());
                tvDealCount.setText("共交易" + b.getPay_count() + "笔");
                tvConsumeCount.setText("共消费" + b.getUse_count() + "笔");
                tvRefundCount.setText("共退款" + b.getRefund_count() + "笔");

                orderCount=b.getOrder_count();
                evaluateCount=b.getComment_count();

                if (orderCount.equals("0")) {
                    tvOrderCount.setVisibility(View.INVISIBLE);
                } else {
                    tvOrderCount.setVisibility(View.VISIBLE);
                    tvOrderCount.setText(b.getOrder_count());
                }

                if (evaluateCount.equals("0")) {
                    tvEvaluateCount.setVisibility(View.INVISIBLE);
                } else {
                    tvEvaluateCount.setVisibility(View.VISIBLE);
                    tvEvaluateCount.setText((b.getComment_count()));
                }
            } catch (Exception e) {

            }





        } else {
            Toast.makeText(this, "网络错误！", Toast.LENGTH_SHORT).show();
        }
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




    private void ininview() {




    }

    Intent iReconciliation= new Intent();
    @OnClick({R.id.tv_back, R.id.ll_income, R.id.tv_reconciliation, R.id.tv_withdrawal, R.id.rl_service_manage, R.id.rl_commodity_manage, R.id.rl_order_manage, R.id.rl_member_manage, R.id.rl_evaluate_manage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.ll_income:
                iReconciliation.setClass(this,TodayIncomeActivity.class);
                startActivity(iReconciliation);
                break;
            case R.id.tv_reconciliation:
                iReconciliation.setClass(this,TodayIncomeActivity.class);
                startActivity(iReconciliation);
                break;
            case R.id.tv_withdrawal:
                Intent iWithdrawal = new Intent(this, WithdrawalActivity.class);
                startActivity(iWithdrawal);
                break;
            case R.id.rl_service_manage:
                Intent i = new Intent(this, ServiceManageActivity.class);
                startActivity(i);

                break;
            case R.id.rl_commodity_manage:
                Intent i2 = new Intent(this, ProductManageActivity.class);
                startActivity(i2);
                break;
            case R.id.rl_order_manage:
                Intent i3 = new Intent(this, OrderManageActivity.class);
                startActivity(i3);
                break;
            case R.id.rl_member_manage:
                break;
            case R.id.rl_evaluate_manage:
                Intent i4 = new Intent(this, EvaluateManageActivity.class);
                startActivity(i4);
                break;
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ininview();
    }
}
