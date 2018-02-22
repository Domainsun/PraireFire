package com.praire.fire.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.services.core.LatLonPoint;

/**
 * Created by lyp on 2018/1/10.
 */
public class IntentDataForGPSNaviActivity implements Parcelable {
    public NaviLatLng mStartPoint;
    public NaviLatLng mEndPoint;
    public int naviType = 2;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mStartPoint, flags);
        dest.writeParcelable(this.mEndPoint, flags);
        dest.writeInt(this.naviType);
    }

    public IntentDataForGPSNaviActivity() {
    }

    protected IntentDataForGPSNaviActivity(Parcel in) {
        this.mStartPoint = in.readParcelable(NaviLatLng.class.getClassLoader());
        this.mEndPoint = in.readParcelable(NaviLatLng.class.getClassLoader());
        this.naviType = in.readInt();
    }

    public static final Creator<IntentDataForGPSNaviActivity> CREATOR = new Creator<IntentDataForGPSNaviActivity>() {
        @Override
        public IntentDataForGPSNaviActivity createFromParcel(Parcel source) {
            return new IntentDataForGPSNaviActivity(source);
        }

        @Override
        public IntentDataForGPSNaviActivity[] newArray(int size) {
            return new IntentDataForGPSNaviActivity[size];
        }
    };
}

