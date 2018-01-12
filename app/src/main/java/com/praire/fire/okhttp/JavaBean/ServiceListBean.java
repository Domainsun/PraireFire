package com.praire.fire.okhttp.JavaBean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunlo on 2018/1/10.
 */

public class ServiceListBean {


    /**
     * code : 1
     * p : 1
     * pagelist : [{"id":"13","class":"99","shop_id":"17","name":"洗车","desc":"洗得快 洗的好","sprice":"0.00","nprice":"555.00","status":"1","salecount":"0","create_time":"1515632310","update_time":"0","is_delete":"0","class_name":"汽车服务"},{"id":"11","class":"98","shop_id":"17","name":"火疗","desc":"打通任督二脉","sprice":"0.00","nprice":"3580.00","status":"1","salecount":"0","create_time":"1515567399","update_time":"0","is_delete":"0","class_name":"运输服务"},{"id":"8","class":"97","shop_id":"17","name":"汗蒸","desc":"促进血液循环","sprice":"0.00","nprice":"59.00","status":"1","salecount":"0","create_time":"1515566759","update_time":"0","is_delete":"0","class_name":"娱乐、文化、体育服务"},{"id":"7","class":"90","shop_id":"17","name":"1","desc":"3","sprice":"0.00","nprice":"2.00","status":"1","salecount":"0","create_time":"1515566556","update_time":"0","is_delete":"0","class_name":"建筑家装服务"},{"id":"12","class":"91","shop_id":"17","name":"222","desc":"介绍哦啊的撒大","sprice":"0.00","nprice":"22.00","status":"0","salecount":"0","create_time":"1515571126","update_time":"1515576289","is_delete":"0","class_name":"销售服务"}]
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
         * id : 13
         * class : 99
         * shop_id : 17
         * name : 洗车
         * desc : 洗得快 洗的好
         * sprice : 0.00
         * nprice : 555.00
         * status : 1
         * salecount : 0
         * create_time : 1515632310
         * update_time : 0
         * is_delete : 0
         * class_name : 汽车服务
         */

        private String id;
        @SerializedName("class")
        private String classX;
        private String shop_id;
        private String name;
        private String desc;
        private String sprice;
        private String nprice;
        private String status;
        private String salecount;
        private String create_time;
        private String update_time;
        private String is_delete;
        private String class_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
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

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
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
    }
}
