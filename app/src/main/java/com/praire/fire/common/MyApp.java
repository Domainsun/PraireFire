package com.praire.fire.common;

import android.app.Application;

/**
 * Created by sunlo on 2018/1/2.
 */

public class MyApp extends Application {
    private String signCookie="";


    public String getSignCookie() {
        return signCookie;
    }

    public void setSignCookie(String signCookie) {
        this.signCookie = signCookie;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
