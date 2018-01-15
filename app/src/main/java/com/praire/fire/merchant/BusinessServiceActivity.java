package com.praire.fire.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.praire.fire.R;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.BusinessTodayCountBean;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;
import static com.praire.fire.common.Constants.REQUEST_CODE_CHOOSE_SHOP_TYPE;

public class BusinessServiceActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_service);
        ButterKnife.bind(this);
        ininview();
    }

    private void ininview() {
        String cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");
//        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy-MM-dd");
//        Date curDate =  new Date(System.currentTimeMillis());
//        String   str   =   formatter.format(curDate);
//        Log.d("time",str);
//        String result=new UseAPIs().getBusinessIncome("","",cookie);
//        Log.d("result", "ininview: "+result);

        String result1 = new UseAPIs().getBusinessTodayCount(cookie);
        Log.d("result", "ininview: " + result1);
        BusinessTodayCountBean b = new J2O().getBusinessTodayCount(result1);
        tvMoney.setText(b.getTotal_income());
        tvDealCount.setText("共交易"+b.getPay_count()+"笔");
        tvConsumeCount.setText("共消费"+b.getUse_count()+"笔");
        tvRefundCount.setText("共退款"+b.getRefund_count()+"笔");

    }


    @OnClick({R.id.tv_back, R.id.ll_income, R.id.tv_reconciliation, R.id.tv_withdrawal, R.id.rl_service_manage, R.id.rl_commodity_manage, R.id.rl_order_manage, R.id.rl_member_manage, R.id.rl_evaluate_manage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.ll_income:

                break;
            case R.id.tv_reconciliation:
                break;
            case R.id.tv_withdrawal:
                break;
            case R.id.rl_service_manage:
                Intent i=new Intent(this,ServiceManageActivity.class);
                startActivity(i);

                break;
            case R.id.rl_commodity_manage:
                break;
            case R.id.rl_order_manage:
                break;
            case R.id.rl_member_manage:
                break;
            case R.id.rl_evaluate_manage:
                break;
        }
    }
}