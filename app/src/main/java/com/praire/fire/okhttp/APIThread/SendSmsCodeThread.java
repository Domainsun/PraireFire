package com.praire.fire.okhttp.APIThread;

import android.app.Application;
import android.os.Message;

import com.praire.fire.FindPasswordActivity;
import com.praire.fire.RegisterActivity;
import com.praire.fire.common.ConstanUrl;

import java.io.IOException;
import java.util.concurrent.Callable;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.praire.fire.common.ConstanUrl.HsmsCode;

/**
 * Created by domain on 2017/12/29.
 */


public class SendSmsCodeThread implements Callable {

    String tel, checkcode,cookie,type;
    public SendSmsCodeThread(String tel, String checkcode, String cookie,String type) {
        this.tel = tel;
        this.checkcode = checkcode;
        this.cookie = cookie;
        this.type=type;
    }

    @Override
    public Object call() throws Exception {

        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("tel",tel)
                .add("checkcode",checkcode)
                .build();
        Request request = new Request.Builder()
                .url(ConstanUrl.SENDSMSCODE)
                .addHeader("cookie",cookie)
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        String cookie = response.headers("set-cookie").get(0);

        System.out.println("cookielist"+response.headers());


        System.out.println("smscookie"+cookie);
        Message message=Message.obtain();
        message.what=HsmsCode;
        message.obj=cookie;

        if (type.equals("0")) {
            RegisterActivity.handler_register.sendMessage(message);
        } else if (type.equals("1")) {
            FindPasswordActivity.handler_findPassword.sendMessage(message);
        }


        String result = response.body().string();
        return result;
    }
}
