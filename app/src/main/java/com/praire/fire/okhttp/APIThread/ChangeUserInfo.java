package com.praire.fire.okhttp.APIThread;

import android.util.Log;

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

public class ChangeUserInfo implements Callable {
    /**
     * Created by domain on 2017/12/29.
     */
   String cookie,nickname,address,contact,contactnumber,postcode,sex;

    public ChangeUserInfo(String cookie, String nickname, String address, String contact, String contactnumber, String postcode, String sex) {
        this.cookie = cookie;
        this.nickname = nickname;
        this.address = address;
        this.contact = contact;
        this.contactnumber = contactnumber;
        this.postcode = postcode;
        this.sex = sex;
    }

    @Override
    public Object call() throws Exception {

        Log.d(cookie, "cookie: "+cookie);

        final OkHttpClient client = new OkHttpClient();
//        RequestBody formBody = new FormBody.Builder()
//                .add("nickname", nickname)
//                .add("address", address)
//                .add("contact", contact)
//                .add("contactnumber", contactnumber)
//                .add("postcode",postcode)
//                .add("sex",sex)
//                .build();

        FormBody.Builder formBody = new FormBody.Builder();

        if (nickname.length()!=0){
            formBody.add("nickname", nickname);
        }

        if (address.length()!=0){
            formBody.add("address", address);
        }

        if (contact.length()!=0){
            formBody.add("contact", contact);
        }
        if (contactnumber.length()!=0){
            formBody.add("contactnumber", contactnumber);
        }

        if (postcode.length()!=0){
            formBody.add("postcode", postcode);
        }
         if (sex.length()!=0){
            formBody.add("sex", sex);
        }








        Request request = new Request.Builder()
                .url(ConstanUrl.CHANGE_USER_INFO)
                .addHeader("cookie", cookie)
                .post(formBody.build())
                .build();




//        Request.Builder builder = new Request.Builder();

//         RequestBody formBody=new FormBody.Builder().build();





//        builder.url(ConstanUrl.CHANGE_USER_INFO);
//        builder.post(formBody);
//        builder.addHeader("cookie", cookie);
//        Request request = builder.build();


        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        String cookie = response.headers("set-cookie").get(0);
        String result = response.body().string();
        return result;
    }
}
