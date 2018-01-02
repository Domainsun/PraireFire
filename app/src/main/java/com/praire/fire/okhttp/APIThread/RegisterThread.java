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

public class RegisterThread implements Callable {
    /**
     * Created by domain on 2017/12/29.
     */
    String tel, pwd,cookie,managertel,smsCode;

    public RegisterThread(String tel, String pwd, String smsCode,String managertel, String cookie) {
        this.tel = tel;
        this.pwd = pwd;
        this.smsCode=smsCode;
        this.cookie = cookie;
        this.managertel = managertel;
    }

    @Override
    public Object call() throws Exception {

        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("tel", tel)
                .add("pwd", pwd)
                .add("smscode", smsCode)
                .add("managertel", managertel)
                .build();
        Request request = new Request.Builder()
                .url(ConstanUrl.Register)
                .post(formBody)
//                .addHeader("cookie",cookie)
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
