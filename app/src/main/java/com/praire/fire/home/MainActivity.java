package com.praire.fire.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.praire.fire.MyApplication;
import com.praire.fire.R;
import com.praire.fire.SignAcitvity;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.home.fragment.HomeFragment;
import com.praire.fire.home.fragment.MyFragment;
import com.praire.fire.home.fragment.OrderFragment;
import com.praire.fire.home.fragment.MapFragment;
import com.praire.fire.my.setActivitys.SetPayPasswrodActivity;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;

import java.util.ArrayList;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;


/**
 * 主页
 *
 * @author lyp
 * @date 2017/12/27
 */
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {


    private ArrayList<Fragment> fragments;
    private BottomNavigationBar bottomNavigationBar;

    private String searchKey = "0";
    private MyFragment myFragment;
    private HomeFragment homeFragment;


    public static void startActivity(Context context, int type, boolean forResult) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(Constants.UI_TYPE, type);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

        myFragment = new MyFragment();
        myFragment.setOnClickShopListner(new MyFragment.OnClickShopListner() {
            @Override
            public void setOnClickShopListner(int index) {
                if (index == 2) {
                    bottomNavigationBar.selectTab(2);
                }
                if (index == 1) {
                    bottomNavigationBar.selectTab(1);
                }
            }
        });
        homeFragment = new HomeFragment();
        homeFragment.setOnClickShopListner(new HomeFragment.OnClickShopListner() {

            @Override
            public void setOnClickShopListner(String key) {
                setSearchKey(key);
                bottomNavigationBar.selectTab(1);
            }
        });

        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);//MODE_SHIFTING
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home_page, getString(R.string.index)).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.map, getString(R.string.map)).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.order, getString(R.string.order)).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.my, getString(R.string.my)).setActiveColorResource(R.color.orange))
                .setFirstSelectedPosition(0)
                .initialise();
        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
        //从店铺点击进入
        if (getIntent() != null) {
            bottomNavigationBar.selectTab(getIntent().getIntExtra(Constants.UI_TYPE, 0));
        }
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

    }


    @Override
    public void onTabSelected(int position) {
        if ((position == 2) && !hasLogin()) {
            return;
        }
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                if (fragment.isAdded()) {
                    ft.replace(R.id.layFrame, fragment);
                } else {
                    ft.add(R.id.layFrame, fragment);
                }
                ft.commitAllowingStateLoss();
            }
        }
    }


    @Override
    public void onTabUnselected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }

    /**
     * 设置默认的页面（首页）
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, homeFragment);
        transaction.commit();
    }

    private ArrayList<Fragment> getFragments() {

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(new MapFragment());
        fragments.add(new OrderFragment());
        fragments.add(myFragment);
        return fragments;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE_SEARCH && resultCode == RESULT_OK) {
            if (data != null) {
                setSearchKey(data.getStringExtra(Constants.SEARCH_KEY));
                bottomNavigationBar.selectTab(1);
            }
        }
    }


    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
    /*@Override
    protected void onResume() {
        Bundle bundle = new Bundle();
        int id = bundle.getInt(Constants.FRAGMENTFLAG, 0);
        if (id == 2 ) {
            bottomNavigationBar.selectTab(2);
        }
        super.onResume();
    }*/
}
