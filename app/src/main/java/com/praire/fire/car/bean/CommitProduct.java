package com.praire.fire.car.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lyp on 2018/1/18.
 */

public class CommitProduct implements Parcelable{
    private String type;
    private String ps_id;
    private int number;
    private String pName;
    private String pPrice;
    private String shopId;
    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPs_id() {
        return ps_id;
    }

    public void setPs_id(String ps_id) {
        this.ps_id = ps_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpPrice() {
        return pPrice;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.ps_id);
        dest.writeInt(this.number);
        dest.writeString(this.pName);
        dest.writeString(this.pPrice);
        dest.writeString(this.shopId);
    }

    public CommitProduct() {
    }

    protected CommitProduct(Parcel in) {
        this.type = in.readString();
        this.ps_id = in.readString();
        this.number = in.readInt();
        this.pName = in.readString();
        this.pPrice = in.readString();
        this.shopId = in.readString();
    }

    public static final Creator<CommitProduct> CREATOR = new Creator<CommitProduct>() {
        @Override
        public CommitProduct createFromParcel(Parcel source) {
            return new CommitProduct(source);
        }

        @Override
        public CommitProduct[] newArray(int size) {
            return new CommitProduct[size];
        }
    };
}
