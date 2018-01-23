package com.praire.fire;

import android.content.Intent;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.praire.fire.base.BaseApplication;
import com.praire.fire.home.MainActivity;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;

/**
 * Created by lyp on 2017/12/29.
 */

public class MyApplication extends BaseApplication {


    private static MyApplication gInstance;
    private String signCookie="";


    public String getSignCookie() {
        return signCookie;
    }

    public void setSignCookie(String signCookie) {
        this.signCookie = signCookie;
    }


    @Override
    protected void create() {
        gInstance = this;
        Fresco.initialize(this);

        String cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");

        /*如果登录过，自动跳转到主页*/

        String result = "";
        String str = "\"code\":0";

        if (cookie.length() != 0 && cookie != null) {
            result = new UseAPIs().getShopInfo(cookie);
            if (result.length() != 0) {
                if (result.indexOf(str) == -1) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(i);
                }

            }
        }

        Log.d("create", "create: " + str.length() + "\n" + result.length());
        Log.d("create", "create: " + str + "\n" + result+"\n"+cookie);
    }

    public static MyApplication getInstance() {
        return gInstance;
    }
}



