package com.praire.fire.edu;

import android.content.Context;
import android.content.Intent;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.car.CarActivity;
import com.praire.fire.common.Constants;

/**
 * 教育
 * Created by lyp on 2017/12/29.
 */

public class EducationActivity extends BaseTitleActivity {

    public static void startActivity(Context context,  boolean forResult) {
        Intent intent = new Intent(context, EducationActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }
    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_education;
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
        setTitleMiddle(getString(R.string.education));
    }
}
