package com.praire.fire.my;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.praire.fire.MyApplication;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.okhttp.NetworkHandler;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.order.PayActivity;
import com.praire.fire.order.ResultActivity;
import com.praire.fire.order.bean.PayResult;
import com.praire.fire.utils.SharePreferenceMgr;
import com.praire.fire.utils.ToastUtil;
import com.praire.fire.utils.statusbarcolor.Eyes;
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
import pub.devrel.easypermissions.EasyPermissions;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;

/**
 * 钱包充值
 * Created by lyp on 2017/12/29.
 */
public class RechargeActivity extends BaseActivity {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_select1)
    ImageView ivSelect1;
    @BindView(R.id.iv_select2)
    ImageView ivSelect2;
    @BindView(R.id.recharge)
    TextView recharge;
    @BindView(R.id.et_recharge)
    EditText etRecharge;
    @BindView(R.id.submit)
    Button submit;

    private static final int SDK_PAY_FLAG = 100;
    private static final int SDK_PAY_WEIXIN = 101;
    UseAPIs u = new UseAPIs();

    String cookie;
    Boolean bpay = true;

    String paystr = "";

    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, RechargeActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_wallet_recharge;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {
        cookie = (String) SharePreferenceMgr.get(MyApplication.getInstance(), LOGIN_COOKIE, "");


//
    }

//    @Override


//    @Override
//    public NetworkHandler createNetWorkHandler() {
//        return super.createNetWorkHandler();
//    }

    protected void networkResponse(Message msg) {

        switch (msg.what) {
            case SDK_PAY_WEIXIN:
                weixinPay((String) msg.obj);
                break;
            case SDK_PAY_FLAG:
                //ailipay
            {
                PayResult payResult = new PayResult((String) msg.obj);
                /**
                 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                 */
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    ResultActivity.startActivity(RechargeActivity.this, "支付成功", "感谢您的使用！", false);
                    finish();
                } else {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    Toast.makeText(RechargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                }
                break;
            }


            default:
                break;
        }
    }


    @OnClick({R.id.tv_back, R.id.iv_select1, R.id.iv_select2, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.iv_select1:
                bpay = true;
                ivSelect1.setImageResource(R.mipmap.selected_circle);
                ivSelect2.setImageResource(R.mipmap.unselected);

                break;
            case R.id.iv_select2:
                bpay = false;
                ivSelect1.setImageResource(R.mipmap.unselected);
                ivSelect2.setImageResource(R.mipmap.selected_circle);

                break;
            case R.id.submit:

                String[] perms = {Manifest.permission.READ_PHONE_STATE};
                if (EasyPermissions.hasPermissions(this, perms)) {
                    String price = "";
                    price = etRecharge.getText().toString();
                    if (bpay) {
                        creatPay(price);

                    } else {

                        if (price.length() != 0) {
                            paystr = u.creatPay(cookie, "1", "1", "", price);
                            Log.d("paystr", "paystr: " + paystr);
                            aliPay(paystr);

                        } else {
                            Toast.makeText(this, "请输入价格", Toast.LENGTH_SHORT).show();
                        }
                    }

                    break;
                } else {
                    EasyPermissions.requestPermissions(this, "读取手机设备需要获取权限",
                            0, perms);
                }
        }
    }

    /**
     * type支付类型(1支付宝，2微信 0余额)
     * recharge  是否充值(0付款，1充值)
     */
    private void creatPay(String price) {
        RequestBody requestBody = new FormBody.Builder()
                .add("type", "2")
                .add("recharge", "1")
                .add("orderno[]", "")
                .add("money", price)
                .build();
        OkhttpRequestUtil.post(ConstanUrl.COMMONINFO_CREATEPAY, requestBody, SDK_PAY_WEIXIN, uiHandler, true);

    }

    private void aliPay(final String orderInfo) {
        // info订单信息

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(RechargeActivity.this);
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

    private void weixinPay(String paystr) {


        IWXAPI api = WXAPIFactory.createWXAPI(this, Constants.PRODUCT_WEIXIN_APP_ID, false);
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
                ToastUtil.show(this, "正常调起支付");
                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                api.sendReq(req);
                return;
            }
            Toast.makeText(this, "请先安装微信客户端方可使用微信支付", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {

            Log.e("weixinPay", "weixinPay: " + e.toString());

            e.printStackTrace();
        }


        ToastUtil.show(this, paystr);
    }

    /**
     * 判断用户手机是否安装微信客户端
     */
    private boolean isWXAppInstalledAndSupported() {
        IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);
        msgApi.registerApp(Constants.PRODUCT_WEIXIN_APP_ID);

        boolean sIsWXAppInstalledAndSupported = msgApi.isWXAppInstalled()
                && msgApi.isWXAppSupportAPI();

        return sIsWXAppInstalledAndSupported;
    }

}
