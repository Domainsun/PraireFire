package com.praire.fire.okhttp.APIThread;

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

public class SendSmsCodeThread implements Callable {

    String tel, checkcode,cookie;

    public SendSmsCodeThread(String tel, String checkcode, String cookie) {
        this.tel = tel;
        this.checkcode = checkcode;
        this.cookie = cookie;
    }

    @Override
    public Object call() throws Exception {

        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("tel",tel)
                .add("checkcode",checkcode)
                .build();
        Request request = new Request.Builder()
                .url("https://www.lygyxh.cn/api.php/Verifytel/sendsms")
                .addHeader("cookie",cookie)
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        String cookie = response.headers("set-cookie").get(0);
        System.out.println("sendsmscodecookie"+cookie);
        String result = response.body().string();

        return result;
    }
}
