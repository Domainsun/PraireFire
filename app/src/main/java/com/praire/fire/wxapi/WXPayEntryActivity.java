package com.praire.fire.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.praire.fire.R;
import com.praire.fire.common.Constants;
import com.praire.fire.utils.ToastUtil;
import com.praire.fire.utils.statusbarcolor.Eyes;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	
	private static final String TAG = "WXPayEntryActivity";
	
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
		Eyes.setStatusBarColor(this, ContextCompat.getColor(this, R.color.status_bar));
    	api = WXAPIFactory.createWXAPI(this, Constants.PRODUCT_WEIXIN_APP_ID);
        api.handleIntent(getIntent(), this);


    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		Log.e("errorStr==",resp.errStr+"---"+resp.openId+","+resp.transaction+"+++"+resp.errCode);
		String msgs = "";
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			if(resp.errCode == 0){
				msgs = "支付成功";
				Constants.payResult=resp.errCode;
			}else if(resp.errCode == 2){
				msgs = "取消支付";
			}else {
				msgs = "支付失败，请重试";
			}
			ToastUtil.show(this,msgs);
//			EventBus.getDefault().post(new FirstEvent(msgs));
			finish();
			/*AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.app_tip);
			builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
			builder.show();*/
		}
	}
}