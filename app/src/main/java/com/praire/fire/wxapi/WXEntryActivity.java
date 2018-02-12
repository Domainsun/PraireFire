package com.praire.fire.wxapi;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.praire.fire.common.Constants;
import com.praire.fire.utils.ToastUtil;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xyzlf.share.library.interfaces.ShareConstant;

/**
 * 这个类是微信回调的类
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, Constants.PRODUCT_WEIXIN_APP_ID, false);
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
        Intent intent = new Intent();
        intent.setAction(ShareConstant.ACTION_WEIXIN_CALLBACK);
        intent.putExtra(ShareConstant.EXTRA_WEIXIN_RESULT, resp.errCode);
        sendBroadcast(intent);
        finish();
    }
}
