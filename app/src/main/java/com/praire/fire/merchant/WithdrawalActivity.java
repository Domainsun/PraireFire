package com.praire.fire.merchant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;

public class WithdrawalActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_withdrawal;
    }

    @Override
    protected void initViews() {

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
}
