package com.praire.fire.home;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyp on 2017/12/28.
 */

public class ShopBean implements Parcelable {

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

    public static class PagelistBean {
        /**
         * id : 5
         * user_id : 1467
         * type : 56
         * name : 赣州修理厂
         * door : shop/door/201712/f4b3b6b532815e262613c99ff9076d3c.jpg
         * licence : shop/licence/201712/a1086a72c47748148183468745c9f251.jpg
         * identify : shop/identify/201712/366b5fca266072c6130a5700d2b78207.jpg
         * contact : 刘燕兵
         * tel : 15007061760
         * opentime : 9:00-22:00
         * address : 江西省赣州市章贡区沙河大道花园别墅3街2号
         * lng : 123.567895
         * lat : 568.987654
         * desc : 赣州修理厂是一家10年老店，技术精湛，价格合理
         * city : 章贡区
         * checked : 1
         * check_time : 1514368792
         * create_time : 1513217984
         * update_time : 1514368192
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
        private String city;
        private String checked;
        private String check_time;
        private String create_time;
        private String update_time;

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

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
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
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeInt(this.p);
        dest.writeList(this.pagelist);
    }

    public ShopBean() {
    }

    protected ShopBean(Parcel in) {
        this.code = in.readInt();
        this.p = in.readInt();
        this.pagelist = new ArrayList<PagelistBean>();
        in.readList(this.pagelist, PagelistBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ShopBean> CREATOR = new Parcelable.Creator<ShopBean>() {
        @Override
        public ShopBean createFromParcel(Parcel source) {
            return new ShopBean(source);
        }

        @Override
        public ShopBean[] newArray(int size) {
            return new ShopBean[size];
        }
    };
}
