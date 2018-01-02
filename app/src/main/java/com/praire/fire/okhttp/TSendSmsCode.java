package com.praire.fire.okhttp;

import java.io.IOException;
import java.util.concurrent.Callable;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by sunlo on 2017/12/28.
 */

public class TSendSmsCode implements Callable {


    @Override
    public Object call() throws Exception {
//        final OkHttpClient client = new OkHttpClient();
////        RequestBody formBody = new FormBody.Builder()
////                .add("data", data)
////                .build();
////        Request request = new Request.Builder()
////                .url(Xutils.URL_API+"add_activity.php")
////                .post(formBody)
////                .addHeader("cookie",cookie)
////                .build();
//        Response response = client.newCall(request).execute();
//        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//        String Result=response.body().string();
////        System.out.println("AddActivityThread Result-------------:"+Result);
        return null;
    }
}
