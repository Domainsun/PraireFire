package com.praire.fire.order.adapter;

import java.util.List;

/**
 * Created by lyp on 2018/1/15.
 */

public class CommitOrderBean {

    /**
     * code : 1
     * orderarr : ["2018011914530174154267"]
     * msg : 订单生成成功
     */

    private int code;
    private String msg;
    private List<String> orderarr;

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

    public List<String> getOrderarr() {
        return orderarr;
    }

    public void setOrderarr(List<String> orderarr) {
        this.orderarr = orderarr;
    }
}
