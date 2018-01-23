package com.praire.fire.okhttp.JavaBean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunlo on 2018/1/13.
 */

public class ProductInfoBean {

    /**
     * code : 1
     * info : {"id":"28","shop_id":"17","name":"零件5","class":"63","picurl":"shop/product/201801/38f4f4a38709f338b90a376285639d58.jpg|shop/product/201801/d916b73b282af2633742e084979eb2e4.jpg|shop/product/201801/74a82566c6c65403ade59024d74ebb62.jpg|shop/product/201801/2c9522d9982023d11e363668625bb794.jpg","sprice":"0.00","nprice":"33.00","desc":"44444","status":"1","salecount":"0","create_time":"1515827028","update_time":"1515830388","is_delete":"0","class_name":"汽车用品-品质内饰","piclist":["shop/product/201801/38f4f4a38709f338b90a376285639d58.jpg","shop/product/201801/d916b73b282af2633742e084979eb2e4.jpg","shop/product/201801/74a82566c6c65403ade59024d74ebb62.jpg","shop/product/201801/2c9522d9982023d11e363668625bb794.jpg"],"osspiclist":["http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/38f4f4a38709f338b90a376285639d58.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1515838562&Signature=X363%2Fvpk7qENgBNYkhcONxvbM14%3D","http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/d916b73b282af2633742e084979eb2e4.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1515838562&Signature=vQwSqqLbORBh4Bt5hOOG0kQg3F0%3D","http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/74a82566c6c65403ade59024d74ebb62.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1515838562&Signature=vx5ThAuQmCZMXSS5DuQvFyv%2BKOs%3D","http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/2c9522d9982023d11e363668625bb794.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1515838562&Signature=RvG5NNtiR5SWZaPEcEjOw63uVHY%3D"]}
     */

    private int code;
    private InfoBean info;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * id : 28
         * shop_id : 17
         * name : 零件5
         * class : 63
         * picurl : shop/product/201801/38f4f4a38709f338b90a376285639d58.jpg|shop/product/201801/d916b73b282af2633742e084979eb2e4.jpg|shop/product/201801/74a82566c6c65403ade59024d74ebb62.jpg|shop/product/201801/2c9522d9982023d11e363668625bb794.jpg
         * sprice : 0.00
         * nprice : 33.00
         * desc : 44444
         * status : 1
         * salecount : 0
         * create_time : 1515827028
         * update_time : 1515830388
         * is_delete : 0
         * class_name : 汽车用品-品质内饰
         * piclist : ["shop/product/201801/38f4f4a38709f338b90a376285639d58.jpg","shop/product/201801/d916b73b282af2633742e084979eb2e4.jpg","shop/product/201801/74a82566c6c65403ade59024d74ebb62.jpg","shop/product/201801/2c9522d9982023d11e363668625bb794.jpg"]
         * osspiclist : ["http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/38f4f4a38709f338b90a376285639d58.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1515838562&Signature=X363%2Fvpk7qENgBNYkhcONxvbM14%3D","http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/d916b73b282af2633742e084979eb2e4.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1515838562&Signature=vQwSqqLbORBh4Bt5hOOG0kQg3F0%3D","http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/74a82566c6c65403ade59024d74ebb62.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1515838562&Signature=vx5ThAuQmCZMXSS5DuQvFyv%2BKOs%3D","http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/product/201801/2c9522d9982023d11e363668625bb794.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1515838562&Signature=RvG5NNtiR5SWZaPEcEjOw63uVHY%3D"]
         */

        private String id;
        private String shop_id;
        private String name="";
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
        private String class_name;
        private List<String> piclist;
        private List<String> osspiclist;

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

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public List<String> getPiclist() {
            return piclist;
        }

        public void setPiclist(List<String> piclist) {
            this.piclist = piclist;
        }

        public List<String> getOsspiclist() {
            return osspiclist;
        }

        public void setOsspiclist(List<String> osspiclist) {
            this.osspiclist = osspiclist;
        }
    }
}
