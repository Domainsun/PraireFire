package com.praire.fire.home.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.home.OrderFragment;
import com.praire.fire.map.MapFragment;

import java.util.ArrayList;


/**
 *主页
 * @author lyp
 * @date 2017/12/27
 */
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener{

    private ArrayList<Fragment> fragments;

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        BottomNavigationBar bottomNavigationBar =  findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home, getString(R.string.index)).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.location, getString(R.string.map)).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.order, getString(R.string.order)).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.me, getString(R.string.my)).setActiveColorResource(R.color.orange))
                .setFirstSelectedPosition(0)
                .initialise();
        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
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
        transaction.replace(R.id.layFrame, new HomeFragment());
        transaction.commit();
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new MapFragment());
        fragments.add(new OrderFragment());
        fragments.add(new MyFragment());
        return fragments;
    }

}
