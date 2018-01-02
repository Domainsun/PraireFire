package com.praire.fire.okhttp.JavaBean;

import android.app.Activity;

/**
 * Created by sunlo on 2017/12/29.
 */

public class APIResultBean {

    /**
     * code : 1
     * msg : 验证码发送成功
     */

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
