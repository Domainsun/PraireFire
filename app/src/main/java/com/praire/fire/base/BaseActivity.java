package com.praire.fire.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.praire.fire.R;
import com.praire.fire.okhttp.NetworkHandler;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.utils.ToastUtil;


public abstract class BaseActivity extends AppCompatActivity {

    // Refer fragment class
    protected FragmentManager fm;
    protected NetworkHandler uiHandler ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getFragmentLayout());
        setupComponent();
        uiHandler = createNetWorkHandler();
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
        initListeners();
        initData();
        initAdapters();
    }
    protected void initReferFragment() {
        fm = getSupportFragmentManager();
    }


    @SuppressLint("HandlerLeak")
    public NetworkHandler createNetWorkHandler() {
        return new NetworkHandler(this) {
            @Override
            public void dispatchMessage(Message msg) {
                if(msg.what == OkhttpRequestUtil.NETWORK_ERROR){
                    ToastUtil.show(BaseActivity.this,getString(R.string.error_network));
                }else if(msg.what == OkhttpRequestUtil.NONE_DATA){
                    ToastUtil.show(BaseActivity.this,getString(R.string.no_data));
                }else {
                    networkResponse(msg);
                }
            }
        };
    }


    protected void networkResponse(Message msg) {
    }
}
