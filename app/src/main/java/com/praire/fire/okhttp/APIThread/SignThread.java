package com.praire.fire.okhttp.APIThread;

import com.praire.fire.MyApplication;
import com.praire.fire.common.ConstanUrl;

import java.io.IOException;
import java.util.concurrent.Callable;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by domain on 2017/12/29.
 */

public class SignThread implements Callable {

    String tel, pwd;
    MyApplication application;
    public SignThread(String tel, String pwd,MyApplication application) {
        this.tel = tel;
        this.pwd = pwd;
        this.application=application;
    }

    @Override
    public Object call() throws Exception {

        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("tel", tel)
                .add("pwd", pwd)
                .build();
        Request request = new Request.Builder()
                .url(ConstanUrl.LOGIN)
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        String cookie = response.headers("set-cookie").get(0);

        application.setSignCookie(cookie);
        String result = response.body().string();
        return result;
    }
}
