package com.praire.fire.order.adapter;

/**
 * Created by lyp on 2018/1/15.
 */

public class CommitOrderBean {
    /**
     * data : {"type":"2","user_id":"1474","shop_id":"17","ps_id":"7","orderno":"2018011517272397331444","number":"1","name":"1","picurl":"","nprice":"2.00","sprice":"0.00","price":1.96,"totalprice":1.96,"create_time":1516008443}
     * code : 1
     * msg : 订单生成成功
     */

    private DataBean data;
    private int code;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * type : 2
         * user_id : 1474
         * shop_id : 17
         * ps_id : 7
         * orderno : 2018011517272397331444
         * number : 1
         * name : 1
         * picurl :
         * nprice : 2.00
         * sprice : 0.00
         * price : 1.96
         * totalprice : 1.96
         * create_time : 1516008443
         */

        private String type;
        private String user_id;
        private String shop_id;
        private String ps_id;
        private String orderno;
        private String number;
        private String name;
        private String picurl;
        private String nprice;
        private String sprice;
        private double price;
        private double totalprice;
        private int create_time;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getPs_id() {
            return ps_id;
        }

        public void setPs_id(String ps_id) {
            this.ps_id = ps_id;
        }

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getNprice() {
            return nprice;
        }

        public void setNprice(String nprice) {
            this.nprice = nprice;
        }

        public String getSprice() {
            return sprice;
        }

        public void setSprice(String sprice) {
            this.sprice = sprice;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(double totalprice) {
            this.totalprice = totalprice;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }
    }
}
