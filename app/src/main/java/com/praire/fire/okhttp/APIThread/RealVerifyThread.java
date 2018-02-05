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

public class RealVerifyThread implements Callable {
    /**
     * Created by domain on 2017/12/29.
     */
    String idnum,truename,frontpic,cookie;

    public RealVerifyThread(String idnum, String truename, String frontpic, String cookie) {
        this.idnum = idnum;
        this.truename = truename;
        this.frontpic = frontpic;
        this.cookie = cookie;
    }

    @Override
    public Object call() throws Exception {

        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("idnum", idnum)
                .add("truename", truename)
                .add("frontpic", frontpic)
                .build();
        Request request = new Request.Builder()
                .url(ConstanUrl.REAL_VERIFY)
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
