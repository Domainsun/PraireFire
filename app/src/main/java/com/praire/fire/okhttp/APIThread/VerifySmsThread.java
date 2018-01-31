package com.praire.fire.okhttp.APIThread;

import com.praire.fire.common.ConstanUrl;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;

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

public class VerifySmsThread implements Callable {
    /**
     * Created by domain on 2017/12/29.
     */
   String tel,smscode;


    public VerifySmsThread(String tel, String smscode) {
        this.tel = tel;
        this.smscode = smscode;
    }

    @Override
    public Object call() throws Exception {


        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("tel", tel)
                .add("smscode", smscode)

                .build();
        Request request = new Request.Builder()
                .url(ConstanUrl.VERIFY_SMS)
                .post(formBody)
//                .addHeader("cookie",cookie)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        String cookie = response.headers("set-cookie").get(0);

        System.out.println("verifysmscookie"+response.headers());





        String result = response.body().string();

        APIResultBean a = new J2O().getAPIResult(result);

        if (a.getCode().equals("1")) {
            return cookie;
        } else if (a.getCode().equals("0")){
            return result;
        }



        return result;

    }
}
