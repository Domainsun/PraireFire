package com.praire.fire.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.praire.fire.okhttp.NetworkHandler;


public abstract class BaseFragment extends Fragment {

    protected View rootView ;
    protected NetworkHandler uiHandler ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(null == rootView){
            rootView = initView(inflater, container, savedInstanceState);
            uiHandler = createNetWorkHandler();
            initListener();
            initData();
        }
        return rootView;
    }

    public abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract void initListener();

    public abstract void initData();

    @SuppressLint("HandlerLeak")
    public NetworkHandler createNetWorkHandler() {
        return new NetworkHandler(getActivity()) {
            @Override
            public void dispatchMessage(Message msg) {
                networkResponse(msg);
            }
        };
    }
    protected void networkResponse(Message msg) {
    }

}
