package com.praire.fire.okhttp;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.praire.fire.MyApplication;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.my.bean.ShoppingCarBean;
import com.praire.fire.order.adapter.CommitOrderBean;
import com.praire.fire.utils.SharePreferenceMgr;
import com.praire.fire.utils.TextViewUtils;
import com.praire.fire.utils.ToastUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;

/**
 * 网络请求
 * Created by lyp on 2018/1/17.
 */
public class OkhttpRequestUtil {

    /**
     * 返回数据为空
     */
    public static final int NONE_DATA = 300;
    /**
     * 网络连接失败
     */
    public static final int NETWORK_ERROR = 301;
    /**
     * Get方式请求数据
     *
     * @param url        请求的URL
     * @param scussful   请求成功后取得结果的识别标识
     * @param needCookie 是否需要登录的Cookie值
     * @param uiHandler  接收返回结果的uiHandler
     */
    public static void get(final String url, final int scussful, final boolean needCookie, final Handler uiHandler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request;
                if (needCookie) {
                    request = new Request.Builder()
                            .url(url)
                            .get()
                            .addHeader("cookie", (String) SharePreferenceMgr.get(MyApplication.getInstance(), LOGIN_COOKIE, ""))
                            .build();

                } else {
                    request = new Request.Builder()
                            .url(url)
                            .get()
                            .build();
                }
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                        Message msg = new Message();
                        msg.what = NETWORK_ERROR;
                        uiHandler.sendMessage(msg);
                        e.printStackTrace();

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                        String data = response.body().string();
                        if (data != null) {
                            Message msg = new Message();
                            msg.what = scussful;
                            msg.obj = data;
                            uiHandler.sendMessage(msg);


                            return;
                        }
                        //     返回数据为空
                        Message msg = new Message();
                        msg.what = NONE_DATA;
                        uiHandler.sendMessage(msg);

                    }
                });
            }
        }).start();
    }

    /**
     * @param url         请求的URL
     * @param requestBody 请求参数
     * @param scussful    请求成功后取得结果的识别标识
     * @param uiHandler   接收返回结果的uiHandler
     * @param needCookie  是否需要登录的Cookie值
     */
    public static void post(final String url, final RequestBody requestBody, final int scussful, final Handler uiHandler, final boolean needCookie) {
        new Thread(new Runnable() {
            @Override
            public void run() {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request;

        if (needCookie) {
            request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .addHeader("cookie", (String) SharePreferenceMgr.get(MyApplication.getInstance(), LOGIN_COOKIE, ""))
                    .build();

        } else {
            request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
        }
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = new Message();
                msg.what = NETWORK_ERROR;
                uiHandler.sendMessage(msg);
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                String data = response.body().string();
                if (data != null) {
                    Message msg = new Message();
                    //     成功
                    msg.what = scussful;
                    msg.obj = data;
                    uiHandler.sendMessage(msg);
                    return;
                }
                Message msg = new Message();
                //     返回数据为空
                msg.what = NONE_DATA;
                uiHandler.sendMessage(msg);
            }
        });
            }
        }).start();
    }

}
