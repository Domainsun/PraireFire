package com.praire.fire.home.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.google.gson.Gson;
import com.praire.fire.R;
import com.praire.fire.base.BaseFragment;
import com.praire.fire.car.CarActivity;
import com.praire.fire.car.ShopActivity;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.home.adapter.ShopListAdapter;
import com.praire.fire.home.bean.ShopListBean;
import com.praire.fire.home.bean.SwipeBean;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.utils.RecycleViewDivider;
import com.praire.fire.utils.SharePreferenceMgr;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lyp on 2017/12/27.
 */

public class HomeFragment extends BaseFragment implements BaseSliderView.OnSliderClickListener, AMapLocationListener {


    @BindView(R.id.search_bar_address)
    TextView searchBarAddress;
    @BindView(R.id.plug_search_edittext)
    EditText plugSearchEdittext;
    @BindView(R.id.search_bar_relativeLayout)
    RelativeLayout searchBarRelativeLayout;
    @BindView(R.id.home_banner_slider)
    SliderLayout homeBannerSlider;
    @BindView(R.id.home_car)
    TextView homeCar;
    @BindView(R.id.home_edu)
    TextView homeEdu;
    @BindView(R.id.home_life)
    TextView homeLife;
    @BindView(R.id.home_trip)
    TextView homeTrip;
    @BindView(R.id.home_clothes)
    TextView homeClothes;
    @BindView(R.id.home_ecyclerView)
    RecyclerView homeEcyclerView;
    @BindView(R.id.active1)
    ImageView active1;
    @BindView(R.id.active2)
    ImageView active2;
    @BindView(R.id.active3)
    ImageView active3;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.no_more_line)
    TextView bottonLine;
    private ShopListAdapter adapter;
    private int index = 1;
    private List<ShopListBean.PagelistBean> evEntitys = new ArrayList<>();
    private int lastVisibleItem;
    private LinearLayoutManager linearLayoutManager;
    private boolean loadMore = true;
    private double longitude = 0;
    private double latitude = 0;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private AMapLocation myLocation;
    private boolean isFrist = true;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initListener() {
        setUpMap();
        index = 1;
        OkhttpRequestUtil.get(ConstanUrl.COMMONINFO_GET_SWIPE, 2, false, uiHandler);


        //设置指示器的位置
        homeBannerSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        //设置图片的切换效果
        homeBannerSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        //添加textView动画特效
        homeBannerSlider.setCustomAnimation(new DescriptionAnimation());
        //设置切换时长3000 ,时长越小，切换速度越快
        homeBannerSlider.setDuration(6000);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        homeEcyclerView.setLayoutManager(linearLayoutManager);
        homeEcyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        homeEcyclerView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL));

        adapter = new ShopListAdapter(getActivity());
        homeEcyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ShopListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ShopActivity.startActivity(getActivity(), evEntitys.get(position).getId(), false);
            }
        });
////设置 Header 为 Material样式
//        refreshLayout.setRefreshHeader(new BezierRadarHeader(getActivity()).setEnableHorizontalDrag(true));
//设置 Footer 为 球脉冲
        refreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
       /* refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                isFrist = true;
                index = 1;
                requestShopList(index);
                //加载失败的话3秒后结束加载
                refreshlayout.finishRefresh(3000*//*,false*//*);//传入false表示刷新失败
            }
        });*/

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (loadMore) {
                    getNextPage();
                    bottonLine.setVisibility(View.GONE);
//                    refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示加载失败
                } else {
                    refreshLayout.finishLoadmoreWithNoMoreData();
                    bottonLine.setVisibility(View.VISIBLE);
                }
                //加载失败的话3秒后结束加载

            }
        });
    }


    @Override
    public void initData() {
        isFrist = true;
        index = 1;
        requestShopList(index);

    }

    /**
     * 设置一些amap的属性
     */


    private void setUpMap() {
//初始化定位
        mLocationClient = new AMapLocationClient(getActivity());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
//设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
//        mLocationOption.setInterval(100000);
        //获取最近3s内精度最高的一次定位结果：
//设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
//给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        mLocationClient.startLocation();
    }

    private void getNextPage() {

        isFrist = false;
        index++;
        requestShopList(index);
    }

    /**
     * 获取商家列表
     */
    private void requestShopList(int index) {
        OkhttpRequestUtil.get(ConstanUrl.COMMONINFO_SHOPLIST + "?p=" + index, 1, false, uiHandler);

    }

    @Override
    protected void networkResponse(Message msg) {
        //结束加载
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadmore();
        switch (msg.what) {
            case 0:
                Toast.makeText(getActivity(), "网络出错，请重试", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                if (isFrist) {
                    evEntitys.clear();
                }
                Gson gson = new Gson();
                final ShopListBean evEntity = gson.fromJson((String) msg.obj, ShopListBean.class);
                if (evEntity.getPagelist().isEmpty() || evEntity.getPagelist().size() % 10 != 0) {
                    loadMore = false;
                }
                evEntitys.addAll(evEntity.getPagelist());
                adapter.setEntities(evEntitys, longitude, latitude);
                break;
            case 2:
                Gson gson2 = new Gson();
                SwipeBean bean = gson2.fromJson((String) msg.obj, SwipeBean.class);
                getAdSuccess(bean.getSwipelist());
                break;
            default:
                break;
        }
    }


    /**
     * 添加图片控件
     */
    public void getAdSuccess(List<SwipeBean.SwipelistBean> swipelist) {
        if (swipelist == null || swipelist.size() <= 0) {
            return;
        }

        for (int i = 0, n = swipelist.size(); i < n; ++i) {
            SwipeBean.SwipelistBean bannerItem = swipelist.get(i);

            DefaultSliderView textSliderView = new DefaultSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .image(bannerItem.getOssclientcover())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
//            textSliderView.bundle(new Bundle())
//                    .getBundle()
//                    .putString(Constants.HTTP_URL, bannerItem.getImgHttpUrl());
//            textSliderView.getBundle().putString(Constants.TITLE, bannerItem.getImgName());
            homeBannerSlider.addSlider(textSliderView);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        //销毁定位客户端，同时销毁本地定位服务。
        mLocationClient.onDestroy();
    }

    //性能优化。当页面显示时进行自动播放
    @Override
    public void onStart() {
        super.onStart();
        if (homeBannerSlider != null) {
            homeBannerSlider.startAutoCycle();
        }
    }

    //性能优化。当页面不显示时暂停自动播放
    @Override
    public void onStop() {
        super.onStop();
        if (homeBannerSlider != null) {
            homeBannerSlider.stopAutoCycle();
        }
        //停止定位后，本地定位服务并不会被销毁
        mLocationClient.stopLocation();
    }


    /**
     * 动态图点击事件
     *
     * @param baseSliderView
     */
    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {
        homeBannerSlider.startAutoCycle(6000, 6000, true);
//        int id = homeBannerSlider.getCurrentPosition();
//        switch (imgAdEntitys.get(id).imgPage) {}
    }


    /**
     * 定位回调监听
     *
     * @param amapLocation
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            //可在其中解析amapLocation获取相应内容。
            if (amapLocation.getErrorCode() == 0) {
               /* amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                amapLocation.getLatitude();//获取纬度
                amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                amapLocation.getCountry();//国家信息
                amapLocation.getProvince();//省信息
                amapLocation.getCity();//城市信息
                amapLocation.getDistrict();//城区信息
                amapLocation.getStreet();//街道信息
                amapLocation.getStreetNum();//街道门牌号信息
                amapLocation.getCityCode();//城市编码
                amapLocation.getAdCode();//地区编码
                amapLocation.getAoiName();//获取当前定位点的AOI信息
                amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                amapLocation.getFloor();//获取当前室内定位的楼层*/
                searchBarAddress.setText(amapLocation.getDistrict());

                longitude = amapLocation.getLongitude();
                latitude = amapLocation.getLatitude();

                SharePreferenceMgr.put(getActivity(), Constants.Latitude, amapLocation.getLatitude() + "");
                SharePreferenceMgr.put(getActivity(), Constants.Longitude, amapLocation.getLongitude() + "");
                SharePreferenceMgr.put(getActivity(), Constants.CITY, amapLocation.getDistrict());
                requestShopList(1);
//                SharePreferenceMgr.put(getContext(),Constants.District,amapLocation.getDistrict());
            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }


        }
    }


    @OnClick({R.id.search_bar_address, R.id.search_bar_search, R.id.home_car, R.id.home_edu, R.id.home_life,
            R.id.home_trip, R.id.home_clothes, R.id.plug_search_edittext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_bar_address:
                break;

            case R.id.search_bar_search:
                if (onClickShopListner != null) {
                    onClickShopListner.setOnClickShopListner(plugSearchEdittext.getText().toString().trim());
                }
//                MapSearchActivity.startActivity(getActivity(), plugSearchEdittext.getText().toString().trim(), true);
                break;
            case R.id.home_car:
                CarActivity.startActivity(getActivity(), false);
                break;
            case R.id.home_edu:
                //                EducationActivity.startActivity(getActivity(), false);
                break;
            case R.id.home_life:
                break;
            case R.id.home_trip:
                break;
            case R.id.home_clothes:
                break;
        }
    }

    OnClickShopListner onClickShopListner;

    /**
     * 定义地接口，用于fragment和activity之间的数据传递
     */
    public OnClickShopListner setOnClickShopListner(OnClickShopListner onClickShopListner) {
        return this.onClickShopListner = onClickShopListner;
    }



    public interface OnClickShopListner {
        void setOnClickShopListner(String key);
    }

}
