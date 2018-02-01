package com.praire.fire.okhttp.APIThread;

import com.praire.fire.common.ConstanUrl;

import java.io.IOException;
import java.util.concurrent.Callable;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by sunlo on 2017/12/29.
 */

public class CreatPayThread implements Callable {
    /**
     * Created by domain on 2017/12/29.
     */
   String cookie,type,recharge,orderno,money;

    public CreatPayThread(String cookie,String type, String recharge, String orderno, String money) {
        this.cookie = cookie;
        this.recharge = recharge;
        this.orderno = orderno;
        this.money = money;
        this.type=type;
    }

    @Override
    public Object call() throws Exception {


        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("recharge", recharge)
                .add("orderno[]", orderno)
                .add("money", money)
                .add("type", type)
                .build();
        Request request = new Request.Builder()
                .url(ConstanUrl.COMMONINFO_CREATEPAY)
                .post(formBody)
                .addHeader("cookie",cookie)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        String cookie = response.headers("set-cookie").get(0);
        String result = response.body().string();
        return result;
    }
}
