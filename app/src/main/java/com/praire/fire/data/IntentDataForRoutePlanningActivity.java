package com.praire.fire.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.amap.api.services.core.LatLonPoint;

/**
 * Created by lyp on 2018/1/10.
 */
public class IntentDataForRoutePlanningActivity implements Parcelable {
    public LatLonPoint mStartPoint;
    public LatLonPoint mEndPoint;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mStartPoint, flags);
        dest.writeParcelable(this.mEndPoint, flags);
    }

    public IntentDataForRoutePlanningActivity() {
    }

    protected IntentDataForRoutePlanningActivity(Parcel in) {
        this.mStartPoint = in.readParcelable(LatLonPoint.class.getClassLoader());
        this.mEndPoint = in.readParcelable(LatLonPoint.class.getClassLoader());
    }

    public static final Creator<IntentDataForRoutePlanningActivity> CREATOR = new Creator<IntentDataForRoutePlanningActivity>() {
        @Override
        public IntentDataForRoutePlanningActivity createFromParcel(Parcel source) {
            return new IntentDataForRoutePlanningActivity(source);
        }

        @Override
        public IntentDataForRoutePlanningActivity[] newArray(int size) {
            return new IntentDataForRoutePlanningActivity[size];
        }
    };
}

