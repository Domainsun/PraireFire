package com.praire.fire;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.praire.fire.base.BaseApplication;

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
    }

    public static MyApplication getInstance() {
        return gInstance;
    }
}
