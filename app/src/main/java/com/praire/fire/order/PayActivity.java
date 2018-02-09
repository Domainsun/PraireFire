package com.praire.fire.order;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.order.bean.PayResult;
import com.praire.fire.utils.ToastUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import static com.praire.fire.common.Constants.INTENT_DATA;
import static com.praire.fire.common.Constants.UI_TYPE;

/**
 * 支付订单
 * Created by lyp on 2018/1/5.
 */

public class PayActivity extends BaseTitleActivity {


    private static final int SDK_PAY_FLAG = 100;
    public static final String PAY_TYPE_WEIXIN = "2";
    public static final String PAY_TYPE_ALIPAY = "1";
    public static final String PAY_TYPE_YU_E = "0";
    @BindView(R.id.pay_cost)
    TextView payCost;
    @BindView(R.id.business_name_pay)
    TextView businessNamePay;
    @BindView(R.id.pay_balance)
    TextView payBalance;
    @BindView(R.id.pay_balance_checkbox)
    CheckBox payBalanceCheckbox;
    @BindView(R.id.pay_balance_rl)
    RelativeLayout payBalanceRl;
    @BindView(R.id.pay_weixin_checkbox)
    CheckBox payWeixinCheckbox;
    @BindView(R.id.pay_weixin_rl)
    RelativeLayout payWeixinRl;
    @BindView(R.id.pay_alipay_checkbox)
    CheckBox payAlipayCheckbox;
    @BindView(R.id.pay_alipay_rl)
    RelativeLayout payAlipayRl;
    @BindView(R.id.btn_commit_pay)
    Button btnCommitPay;
    private String recharge;
    private String orderId;
    private static final String PAY_COST = "PayCost";
    private String payType = PAY_TYPE_YU_E;

    /**
     * @param context
     * @param orderId
     * @param recharge  是否充值(0付款，1充值)
     * @param forResult
     */
    public static void startActivity(Context context, String orderId, String recharge,String paycost, boolean forResult) {
        Intent intent = new Intent(context, PayActivity.class);
        intent.putExtra(INTENT_DATA, orderId);
        intent.putExtra(UI_TYPE, recharge);
        intent.putExtra(PAY_COST,paycost);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        recharge = getIntent().getStringExtra(UI_TYPE);
        orderId = getIntent().getStringExtra(INTENT_DATA);
        payCost.setText(String.format(payCost.getTag().toString(),getIntent().getStringExtra(PAY_COST)));
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

    @Override
    public void initiTile() {
        setDefaultBack();
        setTitleMiddle("支付订单");
    }

    @OnClick({R.id.pay_balance_checkbox, R.id.pay_balance_rl, R.id.pay_weixin_checkbox, R.id.pay_weixin_rl,
            R.id.pay_alipay_checkbox, R.id.pay_alipay_rl, R.id.btn_commit_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pay_balance_checkbox:
                payBalanceCheckbox.setChecked(true);
                payWeixinCheckbox.setChecked(false);
                payAlipayCheckbox.setChecked(false);
                break;
            case R.id.pay_balance_rl:
                payBalanceCheckbox.setChecked(true);
                payWeixinCheckbox.setChecked(false);
                payAlipayCheckbox.setChecked(false);
                break;
            case R.id.pay_weixin_checkbox:
                payBalanceCheckbox.setChecked(false);
                payWeixinCheckbox.setChecked(true);
                payAlipayCheckbox.setChecked(false);
                break;
            case R.id.pay_weixin_rl:
                payBalanceCheckbox.setChecked(false);
                payWeixinCheckbox.setChecked(true);
                payAlipayCheckbox.setChecked(false);
                break;
            case R.id.pay_alipay_checkbox:
                payBalanceCheckbox.setChecked(false);
                payWeixinCheckbox.setChecked(false);
                payAlipayCheckbox.setChecked(true);
                break;
            case R.id.pay_alipay_rl:
                payBalanceCheckbox.setChecked(false);
                payWeixinCheckbox.setChecked(false);
                payAlipayCheckbox.setChecked(true);
                break;
            case R.id.btn_commit_pay:
                 if (payBalanceCheckbox.isChecked()) {
                     payType = PAY_TYPE_YU_E;
                } else if (payWeixinCheckbox.isChecked()) {
                     payType = PAY_TYPE_WEIXIN;
                } else if (payAlipayCheckbox.isChecked()) {
                     payType = PAY_TYPE_ALIPAY;
                }
                creatPay(payType);

                break;
            default:
                break;
        }
    }

    @Override
    protected void networkResponse(Message msg) {

        switch (msg.what) {
            case 1:
                Log.e("msgpay", (String) msg.obj);

              String paystr = (String) msg.obj ;

                if(PAY_TYPE_ALIPAY.equals(payType)) {
                    aliPay(paystr);
                }else if(PAY_TYPE_WEIXIN.equals(payType)){
                    weixinPay(paystr);
                }else if(PAY_TYPE_YU_E.equals(payType)){

                }

                break;
            case SDK_PAY_FLAG:
                //ailipay
            {
                Log.e("SDK_PAY_FLAG", (String) msg.obj);
                PayResult payResult = new PayResult((String)msg.obj);
                /**
                 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                 */
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    ResultActivity.startActivity(PayActivity.this,"支付成功","感谢您的使用！",false);
                    finish();
                } else {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }


            default:
                break;
        }
    }



    /**
     * @param type 支付类型(1支付宝，2微信 0余额)
     *             recharge  是否充值(0付款，1充值)
     */
    private void creatPay(String type) {
        RequestBody requestBody = new FormBody.Builder()
                .add("type", type)
                .add("recharge", recharge)
                .add("orderno[]", orderId)
                .build();
        OkhttpRequestUtil.post(ConstanUrl.COMMONINFO_CREATEPAY, requestBody, 1, uiHandler, true);

    }

    private void weixinPay(String paystr) {

        IWXAPI  api = WXAPIFactory.createWXAPI(this, Constants.PRODUCT_WEIXIN_APP_ID,false);
        api.registerApp(Constants.PRODUCT_WEIXIN_APP_ID);
        PayReq req = new PayReq();
        try {
            JSONObject json = new JSONObject(paystr);

            req.appId = json.getString("appid");
            req.partnerId = json.getString("partnerid");
            req.prepayId = json.getString("prepayid");
            req.nonceStr = json.getString("noncestr");
            req.timeStamp = json.getString("timestamp");
            req.packageValue = json.getString("package");
            req.sign = json.getString("sign");
//            req.extData			= "app data"; // optional
            if (isWXAppInstalledAndSupported()) {
                ToastUtil.show(this,"正常调起支付");
                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                api.sendReq(req);
                return;
            }
            Toast.makeText(this, "请先安装微信客户端方可使用微信支付", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    ToastUtil.show(this,paystr);
    }

    private void aliPay(final String orderInfo) {
        // info订单信息

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayActivity.this);
                String result = alipay.pay(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                uiHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }



     /**
     * 判断用户手机是否安装微信客户端
      */
    private boolean isWXAppInstalledAndSupported() {
        IWXAPI msgApi = WXAPIFactory.createWXAPI(this,null);
        msgApi.registerApp(Constants.PRODUCT_WEIXIN_APP_ID);

        boolean sIsWXAppInstalledAndSupported = msgApi.isWXAppInstalled()
                && msgApi.isWXAppSupportAPI();

        return sIsWXAppInstalledAndSupported;
    }
    @Override
    protected void onResume() {
        super.onResume();
        //调支付页面最好加个进度条，因为有时候很慢，造成无反应假像，然后在这里关闭。
//        if (dialog!=null) {
//            dialog.dismissProcessDialog();
//        }
//        微信支付成功后关闭此页面
        if (Constants.payResult==0) {
            finish();
        }
    }
}
