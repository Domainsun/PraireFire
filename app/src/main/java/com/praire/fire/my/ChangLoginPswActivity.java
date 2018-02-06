package com.praire.fire.my;

import android.content.Context;
import android.content.Intent;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.Constants;

/**
 * 修改登录密码
 * Created by lyp on 2018/1/3.
 */

public class ChangLoginPswActivity extends BaseTitleActivity {
    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, ChangLoginPswActivity.class);

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

    @Override
    public void initiTile() {
        setDefaultBack();
        setTitleMiddle("修改登录密码");
    }
}
