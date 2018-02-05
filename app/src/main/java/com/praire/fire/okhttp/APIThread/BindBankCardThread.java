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

public class BindBankCardThread implements Callable {
    /**
     * Created by domain on 2017/12/29.
     */
   String cardno,cardtype,city,branchname,cookie;

    public BindBankCardThread(String cardno, String cardtype, String city, String branchname, String cookie) {
        this.cardno = cardno;
        this.cardtype = cardtype;
        this.city = city;
        this.branchname = branchname;
        this.cookie = cookie;
    }

    @Override
    public Object call() throws Exception {


        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("cardno", cardno)
                .add("cardtype", cardtype)
                .add("city", city)
                .add("branchname", branchname)
                .build();
        Request request = new Request.Builder()
                .url(ConstanUrl.BIND_BANK_CARD)
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
