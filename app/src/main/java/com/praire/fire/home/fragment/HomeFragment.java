package com.praire.fire.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.praire.fire.R;
import com.praire.fire.base.BaseFragment;

/**
 * Created by Administrator on 2017/12/27.
 */

public class HomeFragment extends BaseFragment {
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
