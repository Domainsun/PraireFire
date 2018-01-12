package com.praire.fire.okhttp.JavaBean;

/**
 * Created by sunlo on 2018/1/10.
 */

public class BusinessTodayCountBean {

    /**
     * total_income : 0.00
     * pay_count : 0
     * use_count : 0
     * refund_count : 0
     */

    private String total_income;
    private String pay_count;
    private String use_count;
    private String refund_count;

    public String getTotal_income() {
        return total_income;
    }

    public void setTotal_income(String total_income) {
        this.total_income = total_income;
    }

    public String getPay_count() {
        return pay_count;
    }

    public void setPay_count(String pay_count) {
        this.pay_count = pay_count;
    }

    public String getUse_count() {
        return use_count;
    }

    public void setUse_count(String use_count) {
        this.use_count = use_count;
    }

    public String getRefund_count() {
        return refund_count;
    }

    public void setRefund_count(String refund_count) {
        this.refund_count = refund_count;
    }
}
