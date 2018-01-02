package com.praire.fire.okhttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.praire.fire.RegisterActivity;
import com.praire.fire.data.Data;

import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;

import static android.content.ContentValues.TAG;

/**
 * Created by sunlo on 2017/12/28.
 */

public class UseApi {





    private String photocookie="";
    private String result_Sendsms="";

    public String getResult_Sendsms() {
        return result_Sendsms;
    }

    public void setResult_Sendsms(String result_Sendsms) {
        this.result_Sendsms = result_Sendsms;
    }

    public String getPhotocookie() {
        return photocookie;
    }

    public void setPhotocookie(String photocookie) {
        this.photocookie = photocookie;
    }


    OkHttpClient client = new OkHttpClient.Builder()
            .cookieJar(new CookieJar() {
                private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                    cookieStore.put(url, cookies);
                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    List<Cookie> cookies = cookieStore.get(url);
                    return cookies != null ? cookies : new ArrayList<Cookie>();
                }
            })
            .build();



    public void getPhotoCode() {
        //创建一个Request
        Request request = new Request.Builder()
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
                    System.out.println(response.headers());
                    setPhotocookie(photocookie);
                    InputStream in = response.body().byteStream();
                    //转化为bitmap
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    Log.d(TAG, "onResponse: "+bitmap);
                    Message message=Message.obtain();
                    message.what=RegisterActivity.PhotoCode;
                    message.obj=bitmap;
                    RegisterActivity.handler_register.sendMessage(message);
                }
            }
        });
    }




    public void sendSmsCode(String tel,String checkcode,String cookie){
        RequestBody formBody = new FormBody.Builder()
                .add("tel",tel)
                .add("checkcode",checkcode)
                .build();
        Request request = new Request.Builder()
                .url("https://www.lygyxh.cn/api.php/Verifytel/sendsms")
                .post(formBody)
                .addHeader("cookie",cookie)
                .build();
        Response response = null;

        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!response.isSuccessful()) {
            try {
                setResult_Sendsms(response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            setResult_Sendsms(response.body().string());
            System.out.println(getResult_Sendsms());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public void register(String tel,String smscode,String pwd,String managertel,String cookie){
        RequestBody formBody = new FormBody.Builder()
                .add("tel",tel)
                .add("smscode",smscode)
                .add("pwd",pwd)
                .add("managertel",managertel)
                .build();
        Request request = new Request.Builder()
                .url("https://www.lygyxh.cn/api.php/Public/register")
                .post(formBody)
                .addHeader("cookie",cookie)
                .build();
        Response response = null;

        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!response.isSuccessful()) {
            try {
                setResult_Sendsms(response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            setResult_Sendsms(response.body().string());
            System.out.println(getResult_Sendsms());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
