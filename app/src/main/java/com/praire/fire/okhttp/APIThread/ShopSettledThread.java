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

public class ShopSettledThread implements Callable {
    /**
     * Created by domain on 2017/12/29.
     */
    String type, name,door,licence,contact,tel,opentime,address,lng,lat,city,desc,identify,cookie;

    public ShopSettledThread(String type, String name, String door, String licence, String contact, String tel, String opentime, String address, String lng, String lat, String city, String desc, String identify,String cookie) {
        this.type = type;
        this.name = name;
        this.door = door;
        this.licence = licence;
        this.contact = contact;
        this.tel = tel;
        this.opentime = opentime;
        this.address = address;
        this.lng = lng;
        this.lat = lat;
        this.city = city;
        this.desc = desc;
        this.identify = identify;
        this.cookie=cookie;
    }

    @Override
    public Object call() throws Exception {

        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("type", type)
                .add("name", name)
                .add("door", door)
                .add("licence", licence)
                .add("contact", contact)
                .add("tel", tel)
                .add("opentime", opentime)
                .add("address", address)
                .add("lng", lng)
                .add("lat", lat)
                .add("city", city)
                .add("desc", desc)
                .add("identify", identify)
                .build();
        Request request = new Request.Builder()
                .url(ConstanUrl.settled)
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
