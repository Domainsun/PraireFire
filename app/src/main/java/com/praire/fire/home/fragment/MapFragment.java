package com.praire.fire.home.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.Projection;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.AMap;

import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.services.core.LatLonPoint;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseFragment;
import com.praire.fire.car.ShopActivity;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.data.IntentDataForGPSNaviActivity;
import com.praire.fire.data.IntentDataForRoutePlanningActivity;
import com.praire.fire.home.MainActivity;
import com.praire.fire.map.GPSNaviActivity;
import com.praire.fire.map.MapSearchActivity;
import com.praire.fire.map.RoutePlanningActivity;
import com.praire.fire.map.adapter.NearlyShopAdapter;
import com.praire.fire.map.bean.NearlyShopBean;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.utils.RecycleViewDivider;
import com.praire.fire.utils.statusbarcolor.Eyes;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


import javax.xml.transform.Result;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

/**
 * 地图
 * Created by lyp on 2017/12/27.
 */

public class MapFragment extends BaseFragment implements AMap.OnMarkerClickListener, AMap.OnMyLocationChangeListener, View.OnClickListener {


    /**
     * 获取距离多少米范围内的商家信息
     */
    public static final String NEARLY_RADIUS = "30000";
    TextureMapView mMapView;
    RecyclerView mapRecyclerView;
    TextView checkMoreTv;
    EditText mapInputKey;
    TextView mapBusinessInfoName;
    TextView mapBusinessInfoInfo;
    TextView mapBusinessInfo;
    TextView mapBusinessInfoNavigation;
    LinearLayout mapBusinessInfoLl;
    LinearLayout mapBusinessInfoRound;
    ImageView back, clean;
    private AMap aMap;
    private MarkerOptions markerOption;
    private NearlyShopAdapter adapter;
    private int index = 1;
    private List<NearlyShopBean> evEntitys = new ArrayList<>();
    private double longitude;
    private double latitude;
    private String businessId;
    /**
     * 被点击的是列表中的第几个商家
     */
    private int bPersion = 0;
    /**
     * 经纬度
     */
    private LatLng CHONGQING;
    private CameraPosition cameraPosition;
    private String searchKey = "";
    private boolean isMarkerClicked = false;
    private boolean isSearch = false;
    private Activity activity;

    protected CameraPosition getCameraPosition() {
        return cameraPosition;
    }


    protected void setCameraPosition(CameraPosition cameraPosition) {
        this.cameraPosition = cameraPosition;
    }

    protected LatLng getTarget() {
        return CHONGQING;
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        initFindView(view);
        return view;
    }


    private void initFindView(View view) {
        mapRecyclerView = view.findViewById(R.id.map_recyclerView);
        checkMoreTv = view.findViewById(R.id.check_more_tv);
        mapInputKey = view.findViewById(R.id.map_input_key);
        mapBusinessInfoName = view.findViewById(R.id.map_business_info_name);
        mapBusinessInfoInfo = view.findViewById(R.id.map_business_info_info);
        mapBusinessInfo = view.findViewById(R.id.map_business_info);
        mapBusinessInfoNavigation = view.findViewById(R.id.map_business_info_navigation);
        mapBusinessInfoLl = view.findViewById(R.id.map_business_info_ll);
        mapBusinessInfoRound = view.findViewById(R.id.map_business_info_round);
        back = view.findViewById(R.id.map_back);
        clean = view.findViewById(R.id.map_clean);
        checkMoreTv.setOnClickListener(this);
        mapBusinessInfo.setOnClickListener(this);
        mapBusinessInfoNavigation.setOnClickListener(this);
        mapInputKey.setOnClickListener(this);
        mapBusinessInfoRound.setOnClickListener(this);
        back.setOnClickListener(this);
        clean.setOnClickListener(this);
        activity = getActivity();
    }

    @Override
    public void initListener() {
        Eyes.setStatusBarColor(activity, ContextCompat.getColor(activity, R.color.status_bar));


        mapRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        mapRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        mapRecyclerView.addItemDecoration(new RecycleViewDivider(
                activity, LinearLayoutManager.HORIZONTAL));
        adapter = new NearlyShopAdapter(activity);


//        mapRecyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
//            @Override
//            public void onItemClick(View itemView, int position) {
//                ShopActivity.startActivity(activity, evEntitys.get(position).getId(), false);
//            }
//        });

        mapRecyclerView.setAdapter(adapter);
        adapter.setDataBack(new NearlyShopAdapter.ItemClickLister() {

            @Override
            public void oncheckd(int position) {
                ShopActivity.startActivity(activity, evEntitys.get(position).getId(), false);
            }
        });

        if (mapRecyclerView.getVisibility() == View.GONE && mapBusinessInfoLl.getVisibility() == View.GONE) {
            checkMoreTv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initData() {
        //输入关键字 确定搜索
        mapInputKey.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                boolean aBoolean = (actionId == 0 || actionId == 3) && event != null;
                if (aBoolean) {

                    String key = mapInputKey.getText().toString();
                    MapSearchActivity.startActivity(activity, key, false);
                    //写你要做的事情
                    return false;
                }
                return false;

            }

        });
       /* mapInputKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 0) {
                    searchKey = "";
                    requestShopList(longitude, latitude, searchKey);
                }
            }
        });*/
    }


    /**
     * 获取商家列表
     */
    private void requestShopList(double longitude, double latitude, String searchKey) {
        OkhttpRequestUtil.get(ConstanUrl.SEARCH_NEARSHOP + "?lng=" + longitude + "&lat=" + latitude + "&name=" + searchKey, 1, false, uiHandler);
    }

    @Override
    protected void networkResponse(Message msg) {
        switch (msg.what) {

            case 1:
                Gson gson = new Gson();
                List<NearlyShopBean> entitys = gson.fromJson((String) msg.obj, new TypeToken<List<NearlyShopBean>>() {
                }.getType());
                if (entitys.size() == 0) {
                    mapRecyclerView.setVisibility(View.GONE);
                    checkMoreTv.setVisibility(View.GONE);
                    Toast.makeText(activity, "没有更多数据了", Toast.LENGTH_SHORT).show();
                }
                evEntitys = entitys;
                if (isSearch) {
                    mapRecyclerView.setVisibility(View.VISIBLE);
                    checkMoreTv.setVisibility(View.GONE);
                }
                adapter.setEntities(entitys, longitude, latitude);
                addMarkersToMap(evEntitys);// 往地图上添加marker
                break;

            default:
                break;
        }
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {


        // 如果要设置定位的默认状态，可以在此处进行设置
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.location_marker));
        //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.interval(20000000);
        // 设置定位的类型为 跟随模式
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);//MyLocationStyle.LOCATION_TYPE_FOLLOW
        aMap.setMyLocationStyle(myLocationStyle);
        // 设置默认定位按钮是否显示
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);

        //设置SDK 自带定位消息监听
        aMap.setOnMyLocationChangeListener(this);

        aMap.setOnMarkerClickListener(this);

    }


    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroyView() {
        //保存地图状态
        setCameraPosition(aMap.getCameraPosition());
        super.onDestroyView();

        if (mMapView != null) {
            //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
            mMapView.onDestroy();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMapView = (TextureMapView) getView().findViewById(R.id.map);

        if (mMapView != null) {
            mMapView.onCreate(savedInstanceState);
            aMap = mMapView.getMap();
            setUpMap();
            if (getCameraPosition() == null) {
                aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(getTarget(), 10, 0, 0)));
            } else {
                aMap.moveCamera(CameraUpdateFactory.newCameraPosition(getCameraPosition()));
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mMapView != null) {
            //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
            mMapView.onResume();
        }
        searchKey = ((MainActivity) activity).getSearchKey();
        if (searchKey.isEmpty()) {
            searchKey = "";
            requestShopList(longitude, latitude, searchKey);
        }
        if (!"0".equals(searchKey)) {
            isSearch = true;
            mapInputKey.setText(searchKey);
            evEntitys.clear();
            requestShopList(longitude, latitude, searchKey);
            ((MainActivity) activity).setSearchKey("");
        }

    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        if (mMapView != null) {
            //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
            mMapView.onPause();
        }
    }


    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap(List<NearlyShopBean> evEntitys) {
        aMap.clear();

        markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(activity.getResources(), R.mipmap.location_car)))
                //设置Marker可拖动true
                .draggable(false);
        for (int i = 0; i < evEntitys.size(); i++) {
            NearlyShopBean bean = evEntitys.get(i);
            markerOption.position(new LatLng(Double.valueOf(bean.getLat()), Double.valueOf(bean.getLng())));
            markerOption.title(bean.getName());
            markerOption.period(i);
            aMap.addMarker(markerOption);
        }
        MarkerOptions markerOption2 = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(activity.getResources(), R.mipmap.location_marker)))
                .position(CHONGQING)
                //设置Marker可拖动true
                .draggable(false);
        aMap.addMarker(markerOption2);
        // 绑定 Marker 被点击事件
        aMap.setOnMarkerClickListener(this);

    }


    /**
     * 对marker标注点点击响应事件
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        if (evEntitys.size() == 0) {
            return false;
        }
        if (aMap != null) {
            jumpPoint(marker);
        }
        isMarkerClicked = true;
        checkMoreTv.setVisibility(View.GONE);
        mapRecyclerView.setVisibility(View.GONE);
        mapBusinessInfoLl.setVisibility(View.VISIBLE);
        mapBusinessInfoRound.setVisibility(View.VISIBLE);
        mapBusinessInfoName.setText(marker.getTitle());
        mapBusinessInfoInfo.setText(evEntitys.get(marker.getPeriod()).getDesc());
        businessId = evEntitys.get(marker.getPeriod()).getId();
        bPersion = marker.getPeriod();
        return true;
    }

    /**
     * marker点击时跳动一下
     */
    public void jumpPoint(final Marker marker) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = aMap.getProjection();
        final LatLng markerLatlng = marker.getPosition();
        Point markerPoint = proj.toScreenLocation(markerLatlng);
        markerPoint.offset(0, -100);
        final LatLng startLatLng = proj.fromScreenLocation(markerPoint);
        final long duration = 1500;

        final Interpolator interpolator = new BounceInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * markerLatlng.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * markerLatlng.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));
                if (t < 1.0) {
                    handler.postDelayed(this, 16);
                }
            }
        });
    }


    @Override
    public void onMyLocationChange(Location location) {
        // 定位回调监听
        if (location != null) {

            Log.e("amap", "onMyLocationChange 定位成功， lat: " + location.getLatitude() + " lon: " + location.getLongitude());
            Bundle bundle = location.getExtras();
            if (bundle != null) {
                int errorCode = bundle.getInt(MyLocationStyle.ERROR_CODE);
                String errorInfo = bundle.getString(MyLocationStyle.ERROR_INFO);
                // 定位类型，可能为GPS WIFI等，具体可以参考官网的定位SDK介绍
                int locationType = bundle.getInt(MyLocationStyle.LOCATION_TYPE);
                longitude = location.getLongitude();
                latitude = location.getLatitude();
                CHONGQING = new LatLng(latitude, longitude);
                requestShopList(longitude, latitude, searchKey);

                Log.e("amap", "定位信息， code: " + errorCode + " errorInfo: " + errorInfo + " locationType: " + locationType);
            } else {
                Log.e("amap", "定位信息， bundle is null ");
            }

        } else {
            Log.e("amap", "定位失败");
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.map_back:
                if (mapRecyclerView.getVisibility() == View.VISIBLE) {
                    mapRecyclerView.setVisibility(mapRecyclerView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                    if (isMarkerClicked) {
                        mapBusinessInfoLl.setVisibility(mapBusinessInfoLl.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                        mapBusinessInfoRound.setVisibility(mapBusinessInfoRound.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                    }
                    if (mapRecyclerView.getVisibility() == View.GONE && mapBusinessInfoLl.getVisibility() == View.GONE) {
                        checkMoreTv.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (isMarkerClicked) {
                        mapBusinessInfoLl.setVisibility(mapBusinessInfoLl.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                        mapBusinessInfoRound.setVisibility(mapBusinessInfoRound.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                    }
                    if (mapRecyclerView.getVisibility() == View.GONE && mapBusinessInfoLl.getVisibility() == View.GONE) {
                        checkMoreTv.setVisibility(View.VISIBLE);
                    }
                }


                break;
            case R.id.map_clean:
                mapInputKey.setText(null);
                break;
            case R.id.check_more_tv:
                checkMoreTv.setVisibility(View.GONE);
                mapRecyclerView.setVisibility(View.VISIBLE);
                break;
            case R.id.map_business_info:
                ShopActivity.startActivity(activity, businessId, false);
                break;
            case R.id.map_business_info_navigation:
                IntentDataForGPSNaviActivity gdata = new IntentDataForGPSNaviActivity();
                gdata.mStartPoint = new NaviLatLng(latitude, longitude);
                gdata.mEndPoint = new NaviLatLng(Double.valueOf(evEntitys.get(bPersion).getLat()), Double.valueOf(evEntitys.get(bPersion).getLng()));
                GPSNaviActivity.startActivity(activity, gdata, false);
                break;
            case R.id.map_input_key:
                MapSearchActivity.startActivity(activity, mapInputKey.getText().toString(), true);
                break;
            case R.id.map_business_info_round:
                IntentDataForRoutePlanningActivity data = new IntentDataForRoutePlanningActivity();
                data.mStartPoint = new LatLonPoint(latitude, longitude);
                data.mEndPoint = new LatLonPoint(Double.valueOf(evEntitys.get(bPersion).getLat()), Double.valueOf(evEntitys.get(bPersion).getLng()));
                RoutePlanningActivity.startActivity(activity, data, false);
                break;
            default:
                break;
        }

    }


}
