package com.praire.fire.okhttp.JavaBean;

/**
 * Created by sunlo on 2018/1/10.
 */

public class BusinessTodayCountBean {
    /**
     * comment_count
     * order_count
     * total_income : 0.00
     * pay_count : 0
     * use_count : 0
     * refund_count : 0
     */
    private String comment_count;
    private String order_count;
    private String total_income;

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getOrder_count() {
        return order_count;
    }

    public void setOrder_count(String order_count) {
        this.order_count = order_count;
    }

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
