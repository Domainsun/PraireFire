package com.praire.fire.home.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyp on 2017/12/28.
 */

public class ShopListBean implements Parcelable {
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
         * type : 56
         * name : 赣州修理厂
         * door : http://lysh-upload.oss-cn-shanghai.aliyuncs.com/shop/door/201712/f4b3b6b532815e262613c99ff9076d3c.jpg?OSSAccessKeyId=LTAIjyidULA5tuIB&Expires=1514531580&Signature=juLhdtv5BIqSgZGJVYNRcAKyRTA%3D
         * contact : 刘燕兵
         * tel : 15007061760
         * opentime : 9:00-22:00
         * address : 江西省赣州市章贡区沙河大道花园别墅3街2号
         * star : 5.00
         * lng : 123.567895
         * lat : 568.987654
         * desc : 赣州修理厂是一家10年老店，技术精湛，价格合理
         * city : 章贡区
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
        private String city;

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

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
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

    public ShopListBean() {
    }

    protected ShopListBean(Parcel in) {
        this.code = in.readInt();
        this.p = in.readInt();
        this.pagelist = new ArrayList<PagelistBean>();
        in.readList(this.pagelist, PagelistBean.class.getClassLoader());
    }

    public static final Creator<ShopListBean> CREATOR = new Creator<ShopListBean>() {
        @Override
        public ShopListBean createFromParcel(Parcel source) {
            return new ShopListBean(source);
        }

        @Override
        public ShopListBean[] newArray(int size) {
            return new ShopListBean[size];
        }
    };
}
