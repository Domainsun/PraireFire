package com.praire.fire.map;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.overlay.WalkRouteOverlay;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.data.IntentDataForGPSNaviActivity;
import com.praire.fire.data.IntentDataForRoutePlanningActivity;
import com.praire.fire.map.adapter.BusResultListAdapter;
import com.praire.fire.utils.ToastUtil;
import com.praire.fire.utils.map.AMapUtil;
import com.praire.fire.utils.statusbarcolor.Eyes;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 驾车路线规划
 * Created by lyp on 2018/1/9.
 */

public class RoutePlanningActivity extends AppCompatActivity implements AMap.OnMapClickListener,
        AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener, AMap.InfoWindowAdapter, RouteSearch.OnRouteSearchListener {

    @BindView(R.id.route_drive)
    ImageView routeDrive;
    @BindView(R.id.route_bus)
    ImageView routeBus;
    @BindView(R.id.route_walk)
    ImageView routeWalk;
    private AMap aMap;
    private MapView mapView;
    private Context mContext;
    private RouteSearch mRouteSearch;
    private DriveRouteResult mDriveRouteResult;
    private LatLonPoint mStartPoint;
    private LatLonPoint mEndPoint;

    private RelativeLayout mBottomLayout, mHeadLayout;
    private TextView mRotueTimeDes, mRouteDetailDes;
    // 搜索时进度条
    private ProgressDialog progDialog = null;
    private RouteSearch.FromAndTo fromAndTo;
    private final int ROUTE_TYPE_DRIVE = 2;
    private final int ROUTE_TYPE_BUS = 1;
    private final int ROUTE_TYPE_WALK = 3;
    private WalkRouteResult mWalkRouteResult;
    private BusRouteResult mBusRouteResult;
    private LinearLayout busResultlayout;
    private ListView mBusResultList;
    private String city = "0797";
    private int ROUTE_TYPE = 2;

    public static void startActivity(Context context, IntentDataForRoutePlanningActivity data, boolean forResult) {
        Intent intent = new Intent(context, RoutePlanningActivity.class);
        intent.putExtra(Constants.INTENT_DATA, data);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_map_route_planning);
        ButterKnife.bind(this);
        Eyes.setStatusBarColor(this, ContextCompat.getColor(this, R.color.status_bar));
        mContext = this.getApplicationContext();
        mapView = (MapView) findViewById(R.id.route_map);
        // 此方法必须重写
        mapView.onCreate(bundle);
        init();
        setfromandtoMarker();
        searchRouteResult(ROUTE_TYPE_DRIVE);
    }

    private void setfromandtoMarker() {

        aMap.addMarker(new MarkerOptions()
                .position(AMapUtil.convertToLatLng(mStartPoint))
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.start)));
        aMap.addMarker(new MarkerOptions()
                .position(AMapUtil.convertToLatLng(mEndPoint))
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.end)));
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        registerListener();
        mRouteSearch = new RouteSearch(this);
        mRouteSearch.setRouteSearchListener(this);
        mBottomLayout = (RelativeLayout) findViewById(R.id.bottom_layout);
        mHeadLayout = (RelativeLayout) findViewById(R.id.routemap_header);
        mRotueTimeDes = (TextView) findViewById(R.id.firstline);
        mRouteDetailDes = (TextView) findViewById(R.id.secondline);
        mBusResultList = (ListView) findViewById(R.id.bus_result_list);
        busResultlayout = findViewById(R.id.bus_result);
        IntentDataForRoutePlanningActivity data = getIntent().getParcelableExtra(Constants.INTENT_DATA);
        mStartPoint = data.mStartPoint;
        mEndPoint = data.mEndPoint;
    }

    /**
     * 注册监听
     */
    private void registerListener() {
        aMap.setOnMapClickListener(RoutePlanningActivity.this);
        aMap.setOnMarkerClickListener(RoutePlanningActivity.this);
        aMap.setOnInfoWindowClickListener(RoutePlanningActivity.this);
        aMap.setInfoWindowAdapter(RoutePlanningActivity.this);

    }

    @Override
    public View getInfoContents(Marker arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public View getInfoWindow(Marker arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onInfoWindowClick(Marker arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onMarkerClick(Marker arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onMapClick(LatLng arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * 开始搜索路径规划方案
     */
    public void searchRouteResult(int routeType) {
        if (mStartPoint == null) {
            ToastUtil.show(mContext, "定位中，稍后再试...");
            return;
        }
        if (mEndPoint == null) {
            ToastUtil.show(mContext, "终点未设置");
        }
        showProgressDialog();
        fromAndTo = new RouteSearch.FromAndTo(
                mStartPoint, mEndPoint);
        switch (routeType) {
            // 驾车路径规划
            case ROUTE_TYPE_DRIVE:
                // 第一个参数表示路径规划的起点和终点，第二个参数表示驾车模式，第三个参数表示途经点，第四个参数表示避让区域，第五个参数表示避让道路
                RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, RouteSearch.DrivingDefault, null,
                        null, "");
                // 异步路径规划驾车模式查询
                mRouteSearch.calculateDriveRouteAsyn(query);
                break;
            // 公交
            case ROUTE_TYPE_BUS:
                // fromAndTo包含路径规划的起点和终点，RouteSearch.BusLeaseWalk表示公交查询模式
                // 第三个参数表示公交查询城市区号，第四个参数表示是否计算夜班车，0表示不计算,1表示计算
                RouteSearch.BusRouteQuery query1 = new RouteSearch.BusRouteQuery(fromAndTo, RouteSearch.BusLeaseWalk, city, 1);
//                query1.setCityd(city);//终点城市区号
                mRouteSearch.calculateBusRouteAsyn(query1);//开始规划路径
                break;
            // 步行
            case ROUTE_TYPE_WALK:
                //初始化query对象，fromAndTo是包含起终点信息，walkMode是步行路径规划的模式
                RouteSearch.WalkRouteQuery query2 = new RouteSearch.WalkRouteQuery(fromAndTo);
                mRouteSearch.calculateWalkRouteAsyn(query2);
                break;
         /*   // 跨城公交
            case ROUTE_TYPE_MASS_BUS:
                // fromAndTo包含路径规划的起点和终点，RouteSearch.BusLeaseWalk表示公交查询模式
                // 第三个参数表示公交查询城市区号，第四个参数表示是否计算夜班车，0表示不计算,1表示计算
                RouteSearch.BusRouteQuery query3 = new RouteSearch.BusRouteQuery(fromAndTo, RouteSearch.BusLeaseWalk, "0797", 0);
                query3.setCityd("027");//终点城市区号
                mRouteSearch.calculateBusRouteAsyn(query3);//开始规划路径
                break;*/
            default:
                break;
        }

    }

    @Override
    public void onBusRouteSearched(BusRouteResult result, int errorCode) {
        dissmissProgressDialog();
        aMap.clear();// 清理地图上的所有覆盖物
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    mBusRouteResult = result;
                    BusResultListAdapter mBusResultListAdapter = new BusResultListAdapter(mContext, mBusRouteResult);
                    mBusResultList.setAdapter(mBusResultListAdapter);
                } else {
                    ToastUtil.show(mContext, R.string.no_result);
                }
            } else {
                ToastUtil.show(mContext, R.string.no_result);
            }
        } else {
            ToastUtil.showerror(this.getApplicationContext(), errorCode);
        }
    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult result, int errorCode) {
        dissmissProgressDialog();
        aMap.clear();// 清理地图上的所有覆盖物
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    mDriveRouteResult = result;
                    final DrivePath drivePath = mDriveRouteResult.getPaths()
                            .get(0);
                    DrivingRouteOverLay drivingRouteOverlay = new DrivingRouteOverLay(
                            mContext, aMap, drivePath,
                            mDriveRouteResult.getStartPos(),
                            mDriveRouteResult.getTargetPos(), null);
                    drivingRouteOverlay.setNodeIconVisibility(false);//设置节点marker是否显示
                    drivingRouteOverlay.setIsColorfulline(true);//是否用颜色展示交通拥堵情况，默认true
                    drivingRouteOverlay.removeFromMap();
                    drivingRouteOverlay.addToMap();
                    drivingRouteOverlay.zoomToSpan();
                    mBottomLayout.setVisibility(View.VISIBLE);
                    int dis = (int) drivePath.getDistance();
                    int dur = (int) drivePath.getDuration();
                    String des = AMapUtil.getFriendlyTime(dur) + "(" + AMapUtil.getFriendlyLength(dis) + ")";
                    mRotueTimeDes.setText(des);
                    mRouteDetailDes.setVisibility(View.VISIBLE);
                    int taxiCost = (int) mDriveRouteResult.getTaxiCost();
                    mRouteDetailDes.setText("打车约" + taxiCost + "元");
                    mBottomLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext,
                                    DriveRouteDetailActivity.class);
                            intent.putExtra(Constants.DRIVE_PATH, drivePath);
                            intent.putExtra(Constants.DRIVE_RESULT,
                                    mDriveRouteResult);
                            startActivity(intent);
                        }
                    });
                } else if (result != null && result.getPaths() == null) {
                    ToastUtil.show(mContext, R.string.no_result);
                }

            } else {
                ToastUtil.show(mContext, R.string.no_result);
            }
        } else {
            ToastUtil.showerror(this.getApplicationContext(), errorCode);
        }


    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult result, int errorCode) {
        dissmissProgressDialog();
        aMap.clear();// 清理地图上的所有覆盖物
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    mWalkRouteResult = result;
                    final WalkPath walkPath = mWalkRouteResult.getPaths()
                            .get(0);
                    WalkRouteOverlay walkRouteOverlay = new WalkRouteOverlay(
                            this, aMap, walkPath,
                            mWalkRouteResult.getStartPos(),
                            mWalkRouteResult.getTargetPos());
                    walkRouteOverlay.removeFromMap();
                    walkRouteOverlay.addToMap();
                    walkRouteOverlay.zoomToSpan();
                    mBottomLayout.setVisibility(View.VISIBLE);
                    int dis = (int) walkPath.getDistance();
                    int dur = (int) walkPath.getDuration();
                    String des = AMapUtil.getFriendlyTime(dur) + "(" + AMapUtil.getFriendlyLength(dis) + ")";
                    mRotueTimeDes.setText(des);
                    mRouteDetailDes.setVisibility(View.GONE);
                    mBottomLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext,
                                    WalkRouteDetailActivity.class);
                            intent.putExtra("walk_path", walkPath);
                            intent.putExtra("walk_result",
                                    mWalkRouteResult);
                            startActivity(intent);
                        }
                    });
                } else if (result.getPaths() == null) {
                    ToastUtil.show(mContext, R.string.no_result);
                }
            } else {
                ToastUtil.show(mContext, R.string.no_result);
            }
        } else {
            ToastUtil.showerror(this.getApplicationContext(), errorCode);
        }
    }


    /**
     * 显示进度框
     */
    private void showProgressDialog() {
        if (progDialog == null) {
            progDialog = new ProgressDialog(this);
        }
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(true);
        progDialog.setMessage("正在搜索");
        progDialog.show();
    }

    /**
     * 隐藏进度框
     */
    private void dissmissProgressDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onRideRouteSearched(RideRouteResult arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @OnClick({R.id.route_drive, R.id.route_bus, R.id.route_walk, R.id.navi_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.route_drive:
                busResultlayout.setVisibility(View.GONE);
                searchRouteResult(ROUTE_TYPE_DRIVE);
                ROUTE_TYPE = ROUTE_TYPE_DRIVE;
                mBottomLayout.setVisibility(View.VISIBLE);
                routeDrive.setImageResource(R.mipmap.route_drive_select);
                routeBus.setImageResource(R.mipmap.route_bus_normal);
                routeWalk.setImageResource(R.mipmap.route_walk_normal);
                break;
            case R.id.route_bus:
                busResultlayout.setVisibility(View.VISIBLE);
                mBottomLayout.setVisibility(View.GONE);
                ROUTE_TYPE = ROUTE_TYPE_BUS;
                searchRouteResult(ROUTE_TYPE_BUS);
                routeDrive.setImageResource(R.mipmap.route_drive_normal);
                routeBus.setImageResource(R.mipmap.route_bus_select);
                routeWalk.setImageResource(R.mipmap.route_walk_normal);
                break;
            case R.id.route_walk:
                ROUTE_TYPE = ROUTE_TYPE_WALK;
                busResultlayout.setVisibility(View.GONE);
                searchRouteResult(ROUTE_TYPE_WALK);
                mBottomLayout.setVisibility(View.VISIBLE);
                routeDrive.setImageResource(R.mipmap.route_drive_normal);
                routeBus.setImageResource(R.mipmap.route_bus_normal);
                routeWalk.setImageResource(R.mipmap.route_walk_select);
                break;
            case R.id.navi_btn:
                IntentDataForGPSNaviActivity data = new IntentDataForGPSNaviActivity();
                data.mStartPoint = new NaviLatLng(this.mStartPoint.getLatitude(), this.mStartPoint.getLongitude());
                data.mEndPoint = new NaviLatLng(this.mEndPoint.getLatitude(), this.mEndPoint.getLongitude());
                data.naviType = ROUTE_TYPE;
                GPSNaviActivity.startActivity(this,data,false);
                break;
            default:
                break;
        }
    }


}
