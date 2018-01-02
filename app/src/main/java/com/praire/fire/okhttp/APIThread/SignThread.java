package com.praire.fire.okhttp.APIThread;

import android.app.Application;
import android.os.Message;

import com.praire.fire.RegisterActivity;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.MyApp;

import java.io.IOException;
import java.util.concurrent.Callable;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.praire.fire.common.ConstanUrl.Hsign;

/**
 * Created by domain on 2017/12/29.
 */

public class SignThread implements Callable {

    String tel, pwd;
    MyApp application;
    public SignThread(String tel, String pwd,MyApp application) {
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
