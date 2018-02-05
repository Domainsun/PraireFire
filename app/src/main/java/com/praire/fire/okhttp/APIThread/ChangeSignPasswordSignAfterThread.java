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

public class ChangeSignPasswordSignAfterThread implements Callable {
    /**
     * Created by domain on 2017/12/29.
     */
    String pwdsrc,pwd,cpwd,cookie;

    public ChangeSignPasswordSignAfterThread(String pwdsrc, String pwd, String cpwd, String cookie) {
        this.pwdsrc = pwdsrc;
        this.pwd = pwd;
        this.cpwd = cpwd;
        this.cookie = cookie;
    }

    @Override
    public Object call() throws Exception {


        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("pwdsrc", pwdsrc)
                .add("pwd", pwd)
                .add("cpwd", cpwd)
                .build();
        Request request = new Request.Builder()
                .url(ConstanUrl.CHANGE_SIGN_PASSWORD_AFTER_SIGN)
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
