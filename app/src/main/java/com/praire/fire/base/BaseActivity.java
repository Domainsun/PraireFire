package com.praire.fire.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;



public abstract class BaseActivity extends AppCompatActivity {

    private static final int NUM_OF_ITEMS = 100;

    private static final int NUM_OF_ITEMS_FEW = 3;

    protected ActionBar actionBar;

    protected Intent intent;
    // Refer fragment class
    protected FragmentManager fm;

    protected FragmentTransaction fragmentTransaction;

    protected Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getFragmentLayout());
        setupComponent();
        init();
    }
    public void setupComponent() {

    }
    /**
     * Every fragment has to inflate a layout in the onCreateView method. We have added this method to
     * avoid duplicate all the inflate code in every fragment. You only have to return the layout to
     * inflate in this method when extends BaseFragment.
     */
    protected abstract int getFragmentLayout();

    protected abstract void initViews();

    /**
     * Init some listeners
     */
    protected abstract void initListeners();

    /**
     * Init some adapters
     */
    protected abstract void initAdapters();

    /**
     * Init some datas
     */
    protected abstract void initData();

    /**
     * Init
     */
    protected void init() {
        initReferFragment();
        initViews();
        initData();
        initAdapters();
        initListeners();
    }
    protected void initReferFragment() {
        fm = getSupportFragmentManager();
    }



}
