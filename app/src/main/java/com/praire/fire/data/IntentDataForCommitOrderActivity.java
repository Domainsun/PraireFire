package com.praire.fire.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.amap.api.services.core.LatLonPoint;
import com.iflytek.cloud.thirdparty.L;
import com.praire.fire.car.bean.CommentlistBean;
import com.praire.fire.car.bean.CommitProduct;
import com.praire.fire.car.bean.ProductInfoBean;
import com.praire.fire.car.bean.ServiceInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyp on 2018/1/10.
 */
public class IntentDataForCommitOrderActivity implements Parcelable {
    public List<CommitProduct> commitProductList = new ArrayList<>();
    /**
     * 商品数量
     */
    public int count = 1;
    /**
     * （1：产品，2：服务）0所有
     */
    public String type = "1";


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.commitProductList);
        dest.writeInt(this.count);
        dest.writeString(this.type);
    }

    public IntentDataForCommitOrderActivity() {
    }

    protected IntentDataForCommitOrderActivity(Parcel in) {
        this.commitProductList = in.createTypedArrayList(CommitProduct.CREATOR);
        this.count = in.readInt();
        this.type = in.readString();
    }

    public static final Creator<IntentDataForCommitOrderActivity> CREATOR = new Creator<IntentDataForCommitOrderActivity>() {
        @Override
        public IntentDataForCommitOrderActivity createFromParcel(Parcel source) {
            return new IntentDataForCommitOrderActivity(source);
        }

        @Override
        public IntentDataForCommitOrderActivity[] newArray(int size) {
            return new IntentDataForCommitOrderActivity[size];
        }
    };
}

