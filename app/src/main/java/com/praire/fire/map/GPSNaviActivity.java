package com.praire.fire.map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.enums.NaviType;
import com.amap.api.navi.model.NaviLatLng;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseNaviActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.data.IntentDataForGPSNaviActivity;
import com.praire.fire.data.IntentDataForRoutePlanningActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 导航
 * @author lyp
 */
public class GPSNaviActivity extends BaseNaviActivity {
//        protected NaviLatLng mEndLatlng ;//= new NaviLatLng(40.084894,116.603039);
//    protected NaviLatLng mStartLatlng ;//= new NaviLatLng(39.825934,116.342972);
    protected final List<NaviLatLng> sList = new ArrayList<>();
    protected final List<NaviLatLng> eList = new ArrayList<>();

    public static void startActivity(Context context, IntentDataForGPSNaviActivity data, boolean forResult) {
        Intent intent = new Intent(context, GPSNaviActivity.class);
        intent.putExtra(Constants.INTENT_DATA, data);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentDataForGPSNaviActivity data = getIntent().getParcelableExtra(Constants.INTENT_DATA);
//        mStartLatlng = data.mStartPoint;
//        mEndLatlng = data.mEndPoint;
        sList.add(data.mStartPoint);
        eList.add(data.mEndPoint);
        setContentView(R.layout.activity_map_navi);
        mAMapNaviView = (AMapNaviView) findViewById(R.id.navi_view);
        mAMapNaviView.onCreate(savedInstanceState);
        mAMapNaviView.setAMapNaviViewListener(this);
    }

    @Override
    public void onInitNaviSuccess() {
        super.onInitNaviSuccess();
        /**
         * 方法: int strategy=mAMapNavi.strategyConvert(congestion, avoidhightspeed, cost, hightspeed, multipleroute); 参数:
         *
         * @congestion 躲避拥堵
         * @avoidhightspeed 不走高速
         * @cost 避免收费
         * @hightspeed 高速优先
         * @multipleroute 多路径
         *
         *  说明: 以上参数都是boolean类型，其中multipleroute参数表示是否多条路线，如果为true则此策略会算出多条路线。
         *  注意: 不走高速与高速优先不能同时为true 高速优先与避免收费不能同时为true
         */
        int strategy = 0;
        try {
            //再次强调，最后一个参数为true时代表多路径，否则代表单路径
            strategy = mAMapNavi.strategyConvert(true, false, false, false, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mAMapNavi.setCarNumber("京", "DFZ588");
        mAMapNavi.calculateDriveRoute(sList, eList, mWayPointList, strategy);

    }

    @Override
    public void onCalculateRouteSuccess(int[] ids) {
        super.onCalculateRouteSuccess(ids);
        mAMapNavi.startNavi(NaviType.GPS);
    }
}
