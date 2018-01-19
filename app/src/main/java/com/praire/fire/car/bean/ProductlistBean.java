package com.praire.fire.car.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lyp on 2018/1/18.
 */

public class ProductlistBean {
    /**
     * id : 19
     * shop_id : 5
     * name : 汽车配件
     * class : 80
     * picurl : null
     * sprice : 150.00
     * nprice : 100.00
     * desc : 汽车配件alert('xxx')
     * status : 0
     * salecount : 0
     * create_time : 1513736990
     * update_time : 0
     * is_delete : 0
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
}
