package com.praire.fire.okhttp.JavaBean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunlo on 2018/1/13.
 */

public class AddProductResultBean {

    /**
     * code : 1
     * msg : 添加成功！
     * data : {"name":"零件","shop_id":"17","class":"63","picurl":"shop/product/201801/f93387a4fd3386ce106af127bbe59d3d.jpg|shop/product/201801/8388c80c36b747dadf7fb3556e5aed56.jpg|shop/product/201801/2640d546fbc8229afbbc52def31ae71a.jpg|shop/product/201801/95e24ee18a120cc0cc47874762ad4528.jpg","sprice":"","nprice":"111","desc":"介绍一波","create_time":1515810191}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 零件
         * shop_id : 17
         * class : 63
         * picurl : shop/product/201801/f93387a4fd3386ce106af127bbe59d3d.jpg|shop/product/201801/8388c80c36b747dadf7fb3556e5aed56.jpg|shop/product/201801/2640d546fbc8229afbbc52def31ae71a.jpg|shop/product/201801/95e24ee18a120cc0cc47874762ad4528.jpg
         * sprice :
         * nprice : 111
         * desc : 介绍一波
         * create_time : 1515810191
         */

        private String name;
        private String shop_id;
        @SerializedName("class")
        private String classX;
        private String picurl;
        private String sprice;
        private String nprice;
        private String desc;
        private int create_time;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getSprice() {
            return sprice;
        }

        public void setSprice(String sprice) {
            this.sprice = sprice;
        }

        public String getNprice() {
            return nprice;
        }

        public void setNprice(String nprice) {
            this.nprice = nprice;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }
    }
}
