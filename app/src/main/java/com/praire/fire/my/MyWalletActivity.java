package com.praire.fire.my;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.car.bean.WalletResult;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.order.adapter.CommitOrderBean;
import com.praire.fire.utils.SharePreferenceMgr;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;

/**
 * 钱包
 * Created by lyp on 2018/1/2.
 */
public class MyWalletActivity extends BaseActivity {
    @BindView(R.id.wallet_money)
    TextView walletMoney;

    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, MyWalletActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initListeners() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                reqestData();
            }
        }).start();

    }

    private void reqestData() {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(ConstanUrl.CAPITAL_INDEX)
                .get()
                .addHeader("cookie",(String) SharePreferenceMgr.get(this, LOGIN_COOKIE, ""))
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = new Message();
                msg.what = 0;
                uiHandler.sendMessage(msg);
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                String data = response.body().string();
                if (data != null) {
                    Log.e("data",data);

                    Gson gson = new Gson();
                    WalletResult  commitBean = gson.fromJson(data, WalletResult.class);
                    Message msg = new Message();
                    if (commitBean.getCode() == 1) {
                        //    订单生成成功
                        msg.what = 1;
                        msg.obj = commitBean.getCapital();
                    } else {
                        msg.what = 2;
                    }
                    uiHandler.sendMessage(msg);
                }

            }
        });

    }
    @Override
    protected void networkResponse(Message msg) {
        switch (msg.what) {
            case 0:
                Toast.makeText(this, R.string.error_network, Toast.LENGTH_SHORT).show();
                break;
            case 1:
                walletMoney.setText((String) msg.obj);
                break;
            case 2:
                Toast.makeText(this, R.string.no_data, Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.wallet_back, R.id.wallet_info, R.id.wallet_input, R.id.wallet_output})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wallet_back:
                finish();
                break;
            case R.id.wallet_info:
                BillActivity.startActivity(this, false);
                break;
            case R.id.wallet_input:
                RechargeActivity.startActivity(this,false);
                break;
            case R.id.wallet_output:
                WithdrawActivity.startActivity(this,false);
                break;
            default:
                break;
        }
    }
}
