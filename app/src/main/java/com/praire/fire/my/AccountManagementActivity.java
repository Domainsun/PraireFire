package com.praire.fire.my;

import android.content.Context;
import android.content.Intent;

import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.Constants;

/**
 * 账号管理
 * Created by lyp on 2018/1/2.
 */

public class AccountManagementActivity extends BaseActivity {
    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, AccountManagementActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }
    @Override
    protected int getFragmentLayout() {
        return 0;
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