package com.praire.fire.okhttp.JavaBean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunlo on 2018/1/13.
 */

public class ProductListBean {

    /**
     * code : 1
     * p : 1
     * pagelist : [{"id":"24","shop_id":"17","name":"零件","class":"63","picurl":"shop/product/201801/f93387a4fd3386ce106af127bbe59d3d.jpg|shop/product/201801/8388c80c36b747dadf7fb3556e5aed56.jpg|shop/product/201801/2640d546fbc8229afbbc52def31ae71a.jpg|shop/product/201801/95e24ee18a120cc0cc47874762ad4528.jpg","sprice":"0.00","nprice":"111.00","desc":"介绍一波","status":"1","salecount":"0","create_time":"1515810191","update_time":"0","is_delete":"0","cover":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/f93387a4fd3386ce106af127bbe59d3d.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1515816982&Signature=KQoh0E5gZE5P5K8rJmUaQvpvGPc%3D","class_name":"汽车用品-品质内饰"},{"id":"23","shop_id":"17","name":"","class":"63","picurl":"shop/product/201801/d27eadadd48f4ed0e32e78d92531ba91.jpg|shop/product/201801/c0d2996f92358dedda5df10283c4afa7.jpg|shop/product/201801/19e83e6f97d08cef2760602371107253.jpg|shop/product/201801/c1801a3e31454a42d46f2d443d34fd3c.jpg","sprice":"0.00","nprice":"0.00","desc":"","status":"1","salecount":"0","create_time":"1515809106","update_time":"0","is_delete":"0","cover":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/d27eadadd48f4ed0e32e78d92531ba91.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1515816982&Signature=DC2CUf7m%2F11CAkCpM%2BjF32i2ZGU%3D","class_name":"汽车用品-品质内饰"},{"id":"22","shop_id":"17","name":"","class":"63","picurl":"shop/product/201801/5358ab03bb7c578749ca67465d4854a5.jpg|shop/product/201801/ef4a1f547d1bb7dd09ba883f19f60bf7.jpg|shop/product/201801/7e13629336d094c7b077c15befa54113.jpg|shop/product/201801/f7dc7ecbc39b6894d6ba41e064f3f2a9.jpg","sprice":"0.00","nprice":"0.00","desc":"","status":"1","salecount":"0","create_time":"1515809055","update_time":"0","is_delete":"0","cover":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/5358ab03bb7c578749ca67465d4854a5.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1515816982&Signature=%2ByJ0UCeHOeUfTP7RsL3m0xd9Ryc%3D","class_name":"汽车用品-品质内饰"},{"id":"21","shop_id":"17","name":"","class":"63","picurl":"shop/product/201801/f9e5c183f4117bd5bb486408f9d220b3.jpg|shop/product/201801/c53161a0f41c24ee21af3ff26903d154.jpg|shop/product/201801/dede40c01235a6600886d2dcc6721cd7.jpg|shop/product/201801/58563386034a903fafe61426e8d266a9.jpg","sprice":"0.00","nprice":"0.00","desc":"","status":"1","salecount":"0","create_time":"1515809051","update_time":"0","is_delete":"0","cover":"http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/f9e5c183f4117bd5bb486408f9d220b3.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1515816982&Signature=wcUVR7XwvkIx4aE15a9NCme8IVk%3D","class_name":"汽车用品-品质内饰"},{"id":"19","shop_id":"5","name":"汽车配件","class":"80","picurl":"","sprice":"150.00","nprice":"100.00","desc":"汽车配件alert('xxx')","status":"0","salecount":"0","create_time":"1513736990","update_time":"0","is_delete":"0","class_name":"茶叶"}]
     */

    private int code;
    private int p;
    private List<PagelistBean> pagelist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public List<PagelistBean> getPagelist() {
        return pagelist;
    }

    public void setPagelist(List<PagelistBean> pagelist) {
        this.pagelist = pagelist;
    }

    public static class PagelistBean implements Serializable {
        /**
         * id : 24
         * shop_id : 17
         * name : 零件
         * class : 63
         * picurl : shop/product/201801/f93387a4fd3386ce106af127bbe59d3d.jpg|shop/product/201801/8388c80c36b747dadf7fb3556e5aed56.jpg|shop/product/201801/2640d546fbc8229afbbc52def31ae71a.jpg|shop/product/201801/95e24ee18a120cc0cc47874762ad4528.jpg
         * sprice : 0.00
         * nprice : 111.00
         * desc : 介绍一波
         * status : 1
         * salecount : 0
         * create_time : 1515810191
         * update_time : 0
         * is_delete : 0
         * cover : http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/f93387a4fd3386ce106af127bbe59d3d.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1515816982&Signature=KQoh0E5gZE5P5K8rJmUaQvpvGPc%3D
         * class_name : 汽车用品-品质内饰
         */

        private String id;
        private String shop_id;
        private String name;
        @SerializedName("class")
        private String classX;
        private String picurl;
        private String sprice;
        private String nprice;
        private String desc;
        private String status;
        private String salecount;
        private String create_time;
        private String update_time;
        private String is_delete;
        private String cover;
        private String class_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSalecount() {
            return salecount;
        }

        public void setSalecount(String salecount) {
            this.salecount = salecount;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }
    }
}
