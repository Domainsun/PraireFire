package com.praire.fire.okhttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by sunlo on 2017/12/28.
 */

public class TGetPhtotCode implements Callable {


    String str="";
    Bitmap bitmap=null;
    @Override
    public Object call() throws Exception {
        OkHttpClient client = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .get()
                .url("https://www.lygyxh.cn/api.php/Public/verify")
                .build();
        //通过client发起请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                str = e.toString();
                System.out.println(str);

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    InputStream in = response.body().byteStream();
                    //转化为bitmap
                    bitmap = BitmapFactory.decodeStream(in);
                }
            }
        });
        return bitmap;
    }
}
