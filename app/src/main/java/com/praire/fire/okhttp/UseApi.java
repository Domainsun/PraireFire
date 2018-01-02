package com.praire.fire.okhttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.util.Log;

import com.praire.fire.RegisterActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;
import static com.praire.fire.common.ConstanUrl.PhotoCode;

/**
 * Created by domain on 2017/12/29.
 */

public class UseApi {



    private String photocookie="";
    private String result_Sendsms="";

    public String getPhotocookie() {
        return photocookie;
    }

    public void setPhotocookie(String photocookie) {
        this.photocookie = photocookie;
    }






    public void getPhotoCode() {
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
                System.out.println(e.toString());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    photocookie=response.headers("set-cookie").get(0);
                    setPhotocookie(photocookie);
                    InputStream in = response.body().byteStream();
                    //转化为bitmap
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    Log.d(TAG, "onResponse: "+bitmap);
                    Message message=Message.obtain();
                    message.what=PhotoCode;
                    message.obj=bitmap;
                    RegisterActivity.handler_register.sendMessage(message);
                }
            }
        });
    }






}
