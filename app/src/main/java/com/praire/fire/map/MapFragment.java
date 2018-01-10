package com.praire.fire.map;

import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.Projection;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.BusinessArea;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.praire.fire.R;
import com.praire.fire.base.BaseFragment;
import com.praire.fire.car.ShopActivity;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.data.IntentDataForRoutePlanningActivity;
import com.praire.fire.home.adapter.ShopListAdapter;
import com.praire.fire.home.bean.ShopListBean;
import com.praire.fire.map.adapter.NearlyShopAdapter;
import com.praire.fire.map.bean.NearlyShopBean;
import com.praire.fire.utils.RecycleViewDivider;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 地图
 * Created by lyp on 2017/12/27.
 */

public class MapFragment extends BaseFragment implements AMap.OnMarkerClickListener, AMap.OnMyLocationChangeListener/*, LocationSource ,AMapLocationListener*/ {
    /**
     * 获取距离多少米范围内的商家信息
     */
    public static final String NEARLY_RADIUS = "30000";
    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.map_recyclerView)
    SwipeMenuRecyclerView mapRecyclerView;
    @BindView(R.id.check_more_tv)
    TextView checkMoreTv;
    Unbinder unbinder;
    @BindView(R.id.map_input_key)
    EditText mapInputKey;
    @BindView(R.id.map_business_info_name)
    TextView mapBusinessInfoName;
    @BindView(R.id.map_business_info_info)
    TextView mapBusinessInfoInfo;
    @BindView(R.id.map_business_info)
    TextView mapBusinessInfo;
    @BindView(R.id.map_business_info_navigation)
    TextView mapBusinessInfoNavigation;
    @BindView(R.id.map_business_info_ll)
    LinearLayout mapBusinessInfoLl;
    @BindView(R.id.map_business_info_round)
    LinearLayout mapBusinessInfoRound;
    private AMap aMap;
    private MarkerOptions markerOption;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
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


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        unbinder = ButterKnife.bind(this, view);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        return view;
    }

    @Override
    public void initListener() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            setUpMap();

        }

        mapRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mapRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        mapRecyclerView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL));
        adapter = new NearlyShopAdapter(getActivity());
        mapRecyclerView.setAdapter(adapter);
        mapRecyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                ShopActivity.startActivity(getActivity(), evEntitys.get(position).getId(), false);
            }
        });

    }

    @Override
    public void initData() {
        //输入关键字 确定搜索
        mapInputKey.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,KeyEvent event) {

                boolean aBoolean = (actionId == 0 || actionId == 3) && event != null;
                if (aBoolean) {

                        String key = mapInputKey.getText().toString();
                        MapSearchActivity.startActivity(getActivity(),key,false);
                    //写你要做的事情
                    return false;
                }
                return false;

            }

        });

    }

    /**
     * 获取商家列表
     */
    private void requestShopList(final double longitude, final double latitude) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(ConstanUrl.SEARCH_NEARSHOP + "?lng=" + longitude + "&lat=" + latitude)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "网络出错，请重试", Toast.LENGTH_SHORT).show();
//                        loadMore = false;
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                String data = response.body().string();
                if (data != null) {
//                    loadMore = false;
                    Gson gson = new Gson();
                    evEntitys = gson.fromJson(data, new TypeToken<List<NearlyShopBean>>() {
                    }.getType());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setEntities(evEntitys, longitude, latitude);
                            addMarkersToMap();// 往地图上添加marker
                        }
                    });
                }

            }
        });

    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {


        // 如果要设置定位的默认状态，可以在此处进行设置
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.location_marker));
        //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.interval(200000);
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mMapView != null) {
            //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
            mMapView.onDestroy();
        }
        unbinder.unbind();
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


    @OnClick({R.id.map_back, R.id.map_clean, R.id.check_more_tv, R.id.map_business_info,
            R.id.map_business_info_navigation , R.id.map_input_key , R.id.map_business_info_round})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.map_back:
                mapRecyclerView.setVisibility(mapRecyclerView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                mapBusinessInfoLl.setVisibility(mapBusinessInfoLl.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                mapBusinessInfoRound.setVisibility(mapBusinessInfoRound.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                break;
            case R.id.map_clean:
                mapInputKey.setText("");
                break;
            case R.id.check_more_tv:
                checkMoreTv.setVisibility(View.GONE);
                mapRecyclerView.setVisibility(View.VISIBLE);
                break;
            case R.id.map_business_info:
                ShopActivity.startActivity(getActivity(), businessId,false);
                break;
            case R.id.map_business_info_navigation:
                break;
            case R.id.map_input_key:
                MapSearchActivity.startActivity(getActivity(),"",false);
                break;
            case R.id.map_business_info_round:
                IntentDataForRoutePlanningActivity data = new IntentDataForRoutePlanningActivity();
                data.mStartPoint = new LatLonPoint(latitude, longitude);
                data.mEndPoint = new LatLonPoint(Double.valueOf(evEntitys.get(bPersion).getLat()), Double.valueOf(evEntitys.get(bPersion).getLng()));
                RoutePlanningActivity.startActivity(getActivity(),data,false);
                break;
            default:
                break;
        }

    }


    /**
     * 在地图上添加marker
     */
    private void addMarkersToMap() {
        markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(), R.mipmap.location_car)))
                //设置Marker可拖动true
                .draggable(false);
        for (int i = 0; i < evEntitys.size(); i++) {
            NearlyShopBean bean = evEntitys.get(i);
            markerOption.position(new LatLng(Double.valueOf(bean.getLat()), Double.valueOf(bean.getLng())));
            markerOption.title(bean.getName());
            markerOption.period(i);
            aMap.addMarker(markerOption);
        }

        // 绑定 Marker 被点击事件
        aMap.setOnMarkerClickListener(this);

    }


    /**
     * 对marker标注点点击响应事件
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        if (aMap != null) {
            jumpPoint(marker);
        }
        checkMoreTv.setVisibility(View.GONE);
        mapRecyclerView.setVisibility(View.GONE);
        mapBusinessInfoLl.setVisibility(View.VISIBLE);
        mapBusinessInfoRound.setVisibility(View.VISIBLE);
        mapBusinessInfoName.setText(marker.getTitle());
        mapBusinessInfoInfo.setText(evEntitys.get(marker.getPeriod()).getDesc());
        businessId = evEntitys.get(marker.getPeriod()).getId();
        bPersion= marker.getPeriod();
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
                requestShopList(longitude, latitude);

                Log.e("amap", "定位信息， code: " + errorCode + " errorInfo: " + errorInfo + " locationType: " + locationType);
            } else {
                Log.e("amap", "定位信息， bundle is null ");

            }

        } else {
            Log.e("amap", "定位失败");
        }
    }


}
