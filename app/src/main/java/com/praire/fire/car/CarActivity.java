package com.praire.fire.car;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
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
import com.praire.fire.utils.ToastUtil;
import com.praire.fire.utils.statusbarcolor.Eyes;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

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
    private int index = 1;
    private boolean isFrist = true;
    private List<CarBean.PagelistBean> carBeanlist = new ArrayList<>();
    private String searchKey = "";

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

        lat = (String) SharePreferenceMgr.get(this, Constants.Latitude, "");
        lng = (String) SharePreferenceMgr.get(this, Constants.Longitude, "");
        plugSearchEdittext.addTextChangedListener(new TextWatcher() {
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
                    getShopList();
                }
            }
        });
        plugSearchEdittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean aBoolean = (actionId == 0 || actionId == 3) && event != null;
                if (aBoolean) {
                    searchKey = plugSearchEdittext.getText().toString();
                    if (TextUtils.isEmpty(searchKey)) {
                        searchKey = "";
                    }
                    getShopList();
                    return true;
                }
                return false;

            }

        });
    }

    @Override
    protected void initListeners() {
        carRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        carRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        carRecyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL));
        carRecyclerView.useDefaultLoadMore();
        carRecyclerView.setLoadMoreListener(new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (carBeanlist == null || carBeanlist.isEmpty() || carBean.getPagelist().size() == 0) {
                    ToastUtil.show(CarActivity.this, "暂无更多数据");
                    return;
                }
                ++index;
                isFrist = false;
                getShopList();
            }
        });
        carAdapter = new CarAdapter(this);
        carRecyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                ShopActivity.startActivity(CarActivity.this, carBeanlist.get(position).getId(), false);
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
        isFrist = true;
        getShopList();
    }


    private void getShopList() {
        /*type	string	是	搜索类型(1产品类型 2服务类型)		1
        class	string	是	类品服务类型id		30
        ordertype	string	是	排序类型(1智能排序 2好评优先 3离我最近 4人均最低 5人均最高)		1
        lng	string	是	经度(按距离排序需用)
                lat	string	是	纬度(按距离排序需用)*/
        String str = "?type=" + types + "&class=" + sonType + "&ordertype=" + sortId + "&lng=" + lng + "&lat=" + lat + "&keywords=" + searchKey + "&p=" + index;
        OkhttpRequestUtil.get(ConstanUrl.SEARCH_SEARCHSHOP + str, 2, false, uiHandler);
    }

    @Override
    protected void networkResponse(Message msg) {
        switch (msg.what) {
            case 1:
                Gson gson2 = new Gson();
                typeMenuBean = gson2.fromJson((String) msg.obj, TypeMenuBean.class);
                break;
            case 2:
                carRecyclerView.loadMoreFinish(false, true);
                if (isFrist) {
                    carBeanlist.clear();
                }
                Gson gson = new Gson();
                carBean = gson.fromJson((String) msg.obj, CarBean.class);
                carBeanlist.addAll(carBean.getPagelist());
                carAdapter.setEntities(carBeanlist);
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.search_bar2_back, R.id.plug_search_edittext, R.id.car_type, R.id.car_sort,R.id.search_bar2_search})
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
            case R.id.search_bar2_search:
                searchKey = plugSearchEdittext.getText().toString();
                if (TextUtils.isEmpty(searchKey)) {
                    searchKey = "";
                }
                getShopList();
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
                index = 1;
                isFrist = true;
                carSort.setText(getSortName(position));
                getShopList();
            }
        });
    }

    private String getSortName(int position) {
        String name = "智能排序";
        switch (position) {
            case 1:
                name = "智能排序";
                break;
            case 2:
                name = "好评优先";
                break;
            case 3:
                name = "离我最近";
                break;
            case 4:
                name = "人均最低";
                break;
            case 5:
                name = "人均最高";
                break;
            default:
                break;
        }
        return name;
    }

    private void setPop(int menuType, View tv, TypeMenuBean popentities) {
        TypeMenuPopwindows medicinePop = new TypeMenuPopwindows(CarActivity.this, menuType, popentities);
        medicinePop.showAsDropDown(tv, 0, 0);
        medicinePop.setOutsideTouchable(true);
        medicinePop.setDataBack(new TypeMenuPopwindows.OnItemClickListener() {
            @Override
            public void onItemClick(int type, String typeName, String id) {
                types = type;
                sonType = id;
                index = 1;
                isFrist = true;
                carType.setText(typeName);
                getShopList();
            }

        });
    }



}
