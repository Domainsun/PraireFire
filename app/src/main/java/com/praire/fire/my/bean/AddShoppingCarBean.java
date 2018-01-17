package com.praire.fire.my.bean;

/**
 * Created by Administrator on 2018/1/17.
 */

public class AddShoppingCarBean {
    /**
     * code : 1
     * msg : 加入成功
     * cartinfo :
     */

    private int code;
    private String msg;
    private String cartinfo;

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

    public String getCartinfo() {
        return cartinfo;
    }

    public void setCartinfo(String cartinfo) {
        this.cartinfo = cartinfo;
    }
}
