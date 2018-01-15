package com.praire.fire.merchant.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunlo on 2018/1/5.
 */

public class ShopTypeBeanList implements Parcelable{


    /**
     * code : 1
     * list : [{"id":"56","name":"汽车"},{"id":"57","name":"茶庄"},{"id":"58","name":"酒庄"},{"id":"59","name":"餐饮"},{"id":"60","name":"旅游"},{"id":"61","name":"服装"}]
     */

    private int code;
    private List<ListBean> list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 56
         * name : 汽车
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeList(this.list);
    }

    public ShopTypeBeanList() {
    }

    protected ShopTypeBeanList(Parcel in) {
        this.code = in.readInt();
        this.list = new ArrayList<ListBean>();
        in.readList(this.list, ListBean.class.getClassLoader());
    }

    public static final Creator<ShopTypeBeanList> CREATOR = new Creator<ShopTypeBeanList>() {
        @Override
        public ShopTypeBeanList createFromParcel(Parcel source) {
            return new ShopTypeBeanList(source);
        }

        @Override
        public ShopTypeBeanList[] newArray(int size) {
            return new ShopTypeBeanList[size];
        }
    };
}
