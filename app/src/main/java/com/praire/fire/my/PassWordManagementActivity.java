package com.praire.fire.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.Constants;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 密码管理
 * Created by lyp on 2018/1/3.
 */

public class PassWordManagementActivity extends BaseTitleActivity {
    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, PassWordManagementActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_my_password_management;
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



    @OnClick({R.id.password_management_find, R.id.password_management_edite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.password_management_find:
                VerifyPhoneActivity.startActivity(this,false);
                break;
            case R.id.password_management_edite:
                ChangLoginPswActivity.startActivity(this,false);
                break;
            default:
                break;
        }
    }

    @Override
    public void initiTile() {
        setDefaultBack();
        setTitleMiddle("密码管理");
    }
}
