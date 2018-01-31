package com.praire.fire.merchant;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.Constants;


/**
 * 地图选址
 *
 * @author lyp
 */
public class MapChooseActivity extends AppCompatActivity implements AMapLocationListener, AMap.OnMapClickListener, AMap.OnMyLocationChangeListener, GeocodeSearch.OnGeocodeSearchListener {

    private AMap aMap;
    private MapView mapView;
    private LatLng latLon;
    private MarkerOptions markerOption;
    TextView confirm, back;
    /**
     * 城市  区
     */
    private String city = "赣州", areas = "";
    private String address = "";
    private GeocodeSearch geocoderSearch;

    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, MapChooseActivity.class);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_CHOOSE_MAP_ADDRESS);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_choose);

        mapView = findViewById(R.id.map);
        // 此方法必须重写
        mapView.onCreate(savedInstanceState);


        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }

        back = findViewById(R.id.tv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        confirm = findViewById(R.id.tv_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("latLon=", latLon.latitude + city + latLon.longitude + areas + address);
                Intent intent = new Intent();
                intent.putExtra(Constants.LATLNG, latLon);
                intent.putExtra(Constants.CITY, city);
                intent.putExtra(Constants.AREAS, areas);
                intent.putExtra(Constants.ADDRESS, address);
                setResult(RESULT_OK, intent);
                finish();


            }
        });
    }


    private void setUpMap() {

        // 对amap添加单击地图事件监听器
        aMap.setOnMapClickListener(this);
        // 如果要设置定位的默认状态，可以在此处进行设置
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.map1));
        //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.interval(0);

        aMap.setMyLocationStyle(myLocationStyle);
        // 设置默认定位按钮是否显示
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);

        //设置SDK 自带定位消息监听
        aMap.setOnMyLocationChangeListener(this);
        //缩放级别的设置 地图的缩放级别一共分为 17 级，从 3 到 19。数字越大，展示的图面信息越精细
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));

        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);
    }


    @Override
    public void onMapClick(LatLng latLng) {
        latLon = latLng;
        aMap.clear();

        markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(), R.mipmap.map1)))
                //设置Marker可拖动true
                .draggable(false);
        markerOption.position(latLng);
        aMap.addMarker(markerOption);
        LatLonPoint latLonPoint = new LatLonPoint(latLng.latitude, latLng.longitude);
// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200, GeocodeSearch.AMAP);
        geocoderSearch.getFromLocationAsyn(query);
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
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();
                latLon = new LatLng(latitude, longitude);
                LatLonPoint latLonPoint = new LatLonPoint(latitude, longitude);
// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
                RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200, GeocodeSearch.AMAP);
                geocoderSearch.getFromLocationAsyn(query);
                Log.e("amap", "定位信息， code: " + errorCode + " errorInfo: " + errorInfo + " locationType: " + locationType);
            } else {
                Log.e("amap", "定位信息， bundle is null ");
            }

        } else {
            Log.e("amap", "定位失败");
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        // 定位回调监听
        if (aMapLocation != null) {
            //可在其中解析amapLocation获取相应内容。
            if (aMapLocation.getErrorCode() == 0) {
                Log.e("amap", "onMyLocationChange 定位成功， lat: " + aMapLocation.getLatitude() + " lon: " + aMapLocation.getLongitude());
                Bundle bundle = aMapLocation.getExtras();
                if (bundle != null) {
                    int errorCode = bundle.getInt(MyLocationStyle.ERROR_CODE);
                    String errorInfo = bundle.getString(MyLocationStyle.ERROR_INFO);
                    // 定位类型，可能为GPS WIFI等，具体可以参考官网的定位SDK介绍
                    int locationType = bundle.getInt(MyLocationStyle.LOCATION_TYPE);
                    double longitude = aMapLocation.getLongitude();
                    double latitude = aMapLocation.getLatitude();
                    latLon = new LatLng(latitude, longitude);
                    city = aMapLocation.getCity();
                    areas = aMapLocation.getDistrict();
                    address = aMapLocation.getAddress();
                    Log.e("amap", "定位信息， code: " + errorCode + " errorInfo: " + errorInfo + " locationType: " + locationType);
                }
            } else {
                Log.e("amap", "定位信息， bundle is null ");
            }

        } else {
            Log.e("amap", "定位失败");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mapView.onSaveInstanceState(outState);
    }


    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int code) {
        //解析result获取地址描述信息,1000为成功，其他为失败
        if (code == 1000) {
            city = regeocodeResult.getRegeocodeAddress().getCity();
            areas = regeocodeResult.getRegeocodeAddress().getDistrict();
            address = regeocodeResult.getRegeocodeAddress().getFormatAddress();
        }
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }
}
