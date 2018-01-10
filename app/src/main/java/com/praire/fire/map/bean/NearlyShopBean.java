package com.praire.fire.map.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lyp on 2018/1/9.
 */

public class NearlyShopBean implements Parcelable {


    /**
     * id : 15
     * type : 56
     * name : 赣州修理厂
     * door : shop/door/201712/f4b3b6b532815e262613c99ff9076d3c.jpg
     * contact : 刘燕兵
     * tel : 15007061760
     * opentime : 9:00-22:00
     * address : 江西省赣州市章贡区沙河大道花园别墅3街2号
     * star : 5.00
     * lng : 114.976925
     * lat : 25.833648
     * desc : 赣州修理厂是一家10年老店，技术精湛，价格合理
     * city_id : 102
     * distance : 1.263
     * ordercount : 0
     */

    private String id;
    private String type;
    private String name;
    private String door;
    private String contact;
    private String tel;
    private String opentime;
    private String address;
    private String star;
    private String lng;
    private String lat;
    private String desc;
    private String city_id;
    private double distance;
    private String ordercount;
    private String type_name;

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getOrdercount() {
        return ordercount;
    }

    public void setOrdercount(String ordercount) {
        this.ordercount = ordercount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.type);
        dest.writeString(this.name);
        dest.writeString(this.door);
        dest.writeString(this.contact);
        dest.writeString(this.tel);
        dest.writeString(this.opentime);
        dest.writeString(this.address);
        dest.writeString(this.star);
        dest.writeString(this.lng);
        dest.writeString(this.lat);
        dest.writeString(this.desc);
        dest.writeString(this.city_id);
        dest.writeDouble(this.distance);
        dest.writeString(this.ordercount);
        dest.writeString(this.type_name);

    }

    public NearlyShopBean() {
    }

    protected NearlyShopBean(Parcel in) {
        this.id = in.readString();
        this.type = in.readString();
        this.name = in.readString();
        this.door = in.readString();
        this.contact = in.readString();
        this.tel = in.readString();
        this.opentime = in.readString();
        this.address = in.readString();
        this.star = in.readString();
        this.lng = in.readString();
        this.lat = in.readString();
        this.desc = in.readString();
        this.city_id = in.readString();
        this.distance = in.readDouble();
        this.ordercount = in.readString();
        this.type_name = in.readString();
    }

    public static final Creator<NearlyShopBean> CREATOR = new Creator<NearlyShopBean>() {
        @Override
        public NearlyShopBean createFromParcel(Parcel source) {
            return new NearlyShopBean(source);
        }

        @Override
        public NearlyShopBean[] newArray(int size) {
            return new NearlyShopBean[size];
        }
    };
}
