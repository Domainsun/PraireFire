package com.praire.fire.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public abstract class BaseFragment extends Fragment {

    protected View rootView ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(null == rootView){
            rootView = initView(inflater, container, savedInstanceState);
            initListener();
            initData();
        }
        return rootView;
    }

    public abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract void initListener();

    public abstract void initData();


}
