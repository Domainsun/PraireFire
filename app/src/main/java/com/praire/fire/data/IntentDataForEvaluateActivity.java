package com.praire.fire.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.amap.api.services.core.LatLonPoint;
import com.praire.fire.order.bean.OrderListBean;

/**
 * Created by lyp on 2018/1/10.
 */
public class IntentDataForEvaluateActivity implements Parcelable {
    public OrderListBean.PagelistBean orderInfo  = new OrderListBean.PagelistBean();


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) this.orderInfo, flags);
    }

    public IntentDataForEvaluateActivity() {
    }

    protected IntentDataForEvaluateActivity(Parcel in) {
        this.orderInfo = in.readParcelable(OrderListBean.PagelistBean.class.getClassLoader());
    }

    public static final Creator<IntentDataForEvaluateActivity> CREATOR = new Creator<IntentDataForEvaluateActivity>() {
        @Override
        public IntentDataForEvaluateActivity createFromParcel(Parcel source) {
            return new IntentDataForEvaluateActivity(source);
        }

        @Override
        public IntentDataForEvaluateActivity[] newArray(int size) {
            return new IntentDataForEvaluateActivity[size];
        }
    };
}

