package com.praire.fire.car;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.car.adapter.CarAdapter;
import com.praire.fire.car.bean.CarBean;
import com.praire.fire.car.bean.TypeMenuBean;
import com.praire.fire.car.popwindows.SortMenuPopwindows;
import com.praire.fire.car.popwindows.TypeMenuPopwindows;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.utils.RecycleViewDivider;
import com.praire.fire.utils.SharePreferenceMgr;
import com.praire.fire.utils.statusbarcolor.Eyes;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 汽车首页
 * Created by lyp on 2017/12/29.
 */
public class CarActivity extends BaseActivity {


    @BindView(R.id.search_bar2_back)
    ImageView searchBar2Back;
    @BindView(R.id.plug_search_edittext)
    EditText plugSearchEdittext;
    @BindView(R.id.search_bar2_relativeLayout)
    RelativeLayout searchBar2RelativeLayout;
    @BindView(R.id.car_recyclerView)
    SwipeMenuRecyclerView carRecyclerView;
    @BindView(R.id.car_type)
    TextView carType;
    @BindView(R.id.car_sort)
    TextView carSort;
    @BindView(R.id.choose_type_ll)
    LinearLayout chooseTypeLl;
    @BindView(R.id.choose_line)
    View chooseLine;
    private String sonType;
    /**
     * 搜索类型(1产品类型 2服务类型)
     */
    private int types;
    private TypeMenuBean typeMenuBean;
    /**
     * 排序类型
     */
    private int sortId = 0;
    private String lat, lng;
    private CarBean carBean;
    private CarAdapter carAdapter;

    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, CarActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_car;
    }

    @Override
    protected void initViews() {
        Eyes.setStatusBarColor(this, ContextCompat.getColor(this, R.color.status_bar));
        ButterKnife.bind(this);
        plugSearchEdittext.setFocusable(false);
        lat = (String) SharePreferenceMgr.get(this, Constants.Latitude, "");
        lng = (String) SharePreferenceMgr.get(this, Constants.Longitude, "");
        Log.e("lat+lng", lat + "+" + lng);

    }

    @Override
    protected void initListeners() {
        carRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        carRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        carRecyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL));
        carAdapter = new CarAdapter(this);
        carRecyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                ShopActivity.startActivity(CarActivity.this,carBean.getPagelist().get(position).getId(),false);
            }
        });
        carRecyclerView.setAdapter(carAdapter);
    }


    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {
        OkhttpRequestUtil.get(ConstanUrl.SEARCH_GET_PS_TYPE_LIST, 1, false, uiHandler);
        getShopList();
    }

    private void getShopList() {
        /*type	string	是	搜索类型(1产品类型 2服务类型)		1
        class	string	是	类品服务类型id		30
        ordertype	string	是	排序类型(1智能排序 2好评优先 3离我最近 4人均最低 5人均最高)		1
        lng	string	是	经度(按距离排序需用)
                lat	string	是	纬度(按距离排序需用)*/
        String str = "?type=" + types + "&class=" + sonType + "&ordertype=" + sortId + "&lng=" + lng + "&lat=" + lat;
        OkhttpRequestUtil.get(ConstanUrl.SEARCH_SEARCHSHOP + str, 2, false, uiHandler);
    }

    @Override
    protected void networkResponse(Message msg) {
        Log.e("msg=", (String) msg.obj);
        switch (msg.what) {
            case 1:
                Gson gson2 = new Gson();
                typeMenuBean = gson2.fromJson((String) msg.obj, TypeMenuBean.class);
                break;
            case 2:
                Gson gson = new Gson();
                carBean = gson.fromJson((String) msg.obj, CarBean.class);
                carAdapter.setEntities(carBean.getPagelist());
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.search_bar2_back, R.id.plug_search_edittext, R.id.car_type, R.id.car_sort})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_bar2_back:
                finish();
                break;
            case R.id.plug_search_edittext:
                break;
            case R.id.car_type:
                if (typeMenuBean != null) {
                    setPop(0, chooseLine, typeMenuBean);
                }
                break;
            case R.id.car_sort:
                if (typeMenuBean != null) {
                    setSortPop(chooseLine);
                }
                break;
            default:
                break;
        }
    }

    private void setSortPop(View tv) {

        SortMenuPopwindows medicinePop = new SortMenuPopwindows(CarActivity.this);
        medicinePop.showAsDropDown(tv, 0, 0);
        medicinePop.setOutsideTouchable(true);
        medicinePop.setDataBack(new SortMenuPopwindows.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                sortId = position;
                getShopList();
            }
        });
    }

    private void setPop(int menuType, View tv, TypeMenuBean popentities) {
        TypeMenuPopwindows medicinePop = new TypeMenuPopwindows(CarActivity.this, menuType, popentities);
        medicinePop.showAsDropDown(tv, 0, 0);
        medicinePop.setOutsideTouchable(true);
        medicinePop.setDataBack(new TypeMenuPopwindows.OnItemClickListener() {
            @Override
            public void onItemClick(int type, int position, String id) {
                types = type;
                sonType = id;
                getShopList();
            }
        });
    }


}
