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

public class BusinessEvaluateThread implements Callable {
    /**
     * Created by domain on 2017/12/29.
     */
    String id,reply,cookie;

    public BusinessEvaluateThread(String id, String reply, String cookie) {
        this.id = id;
        this.reply = reply;
        this.cookie = cookie;
    }

    @Override
    public Object call() throws Exception {


        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("id", id)
                .add("reply", reply)
                .build();
        Request request = new Request.Builder()
                .url(ConstanUrl.BUSINESS_EVALUATE)
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
