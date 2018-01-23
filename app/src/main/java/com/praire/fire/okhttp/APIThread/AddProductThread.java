package com.praire.fire.okhttp.APIThread;

import com.iflytek.cloud.thirdparty.L;
import com.praire.fire.common.ConstanUrl;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by sunlo on 2017/12/29.
 */

public class AddProductThread implements Callable {
    /**
     * Created by domain on 2017/12/29.
     */
    String name,type,desc,price,cookie;
    String photo1="";
    String photo2="";
    String photo3="";
    String photo4="";

    public AddProductThread(String name, String type, String desc, String price, String cookie,String photo1, String photo2, String photo3, String photo4) {
        this.name = name;
        this.type = type;
        this.desc = desc;
        this.price = price;
        this.cookie = cookie;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        this.photo4 = photo4;
    }

    @Override
    public Object call() throws Exception {


        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("name", name)
                .add("class", type)
                .add("desc", desc)
                .add("nprice", price)
                .add("piclist[]",photo1)
                .add("piclist[]",photo2)
                .add("piclist[]",photo3)
                .add("piclist[]",photo4)
                .build();
        Request request = new Request.Builder()
                .url(ConstanUrl.ADD_PRODUCT)
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
