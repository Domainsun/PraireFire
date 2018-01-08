package com.praire.fire.okhttp.JavaBean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by sunlo on 2018/1/8.
 */

public class ShopInfoBean implements  Serializable {

    /**
     * id : 17
     * user_id : 1473
     * type : 59
     * name : domain的2店
     * door : http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/door/201801/07d9704be80de1ab731ca52633f4829c.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1515404888&Signature=NXmmkgehLCDC2DNLBEAFPS73UNY%3D
     * licence : http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/licence/201801/e1353842fd0bd535196cb2b014dab976.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1515404888&Signature=LRSKiv%2Fobpo8kVQa5JNBGOzv0Yw%3D
     * identify : http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/identify/201801/14ec4cc5e5adf7e17792021732ae19f7.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1515404888&Signature=Eoee7htW%2FuYFoREYKBu0RV90Kzs%3D
     * contact : domain
     * tel : 130973402782
     * opentime : 18:35-15:35
     * address : 广州
     * lng : 32.111000
     * lat : 32.111100
     * desc : 信用商家
     * city_id : 124
     * star : 5.00
     * checked : 0
     * check_time : 1515398225
     * create_time : 1515398159
     * update_time : 1515400521
     * check_desc : null
     */

    private String id;
    private String user_id;
    private String type;
    private String name;
    private String door;
    private String licence;
    private String identify;
    private String contact;
    private String tel;
    private String opentime;
    private String address;
    private String lng;
    private String lat;
    private String desc;
    private String city_id;
    private String star;
    private String checked;
    private String check_time;
    private String create_time;
    private String update_time;
    private String check_desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
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

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getCheck_time() {
        return check_time;
    }

    public void setCheck_time(String check_time) {
        this.check_time = check_time;
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

    public String getCheck_desc() {
        return check_desc;
    }

    public void setCheck_desc(String check_desc) {
        this.check_desc = check_desc;
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//
//        parcel.writeString(id);
//        parcel.writeString(user_id);
//        parcel.writeString(type);
//        parcel.writeString(name);
//        parcel.writeString(door);
//        parcel.writeString(licence);
//        parcel.writeString(identify);
//        parcel.writeString(contact);
//        parcel.writeString(tel);
//        parcel.writeString(opentime);
//        parcel.writeString(address);
//        parcel.writeString(lng);
//        parcel.writeString(lat);
//        parcel.writeString(desc);
//        parcel.writeString(city_id);
//        parcel.writeString(star);
//        parcel.writeString(checked);
//        parcel.writeString(check_time);
//        parcel.writeString(create_time);
//        parcel.writeString(update_time);
//        parcel.writeString(check_desc);
//
//    }
}
