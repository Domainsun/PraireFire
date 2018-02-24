package com.praire.fire.my;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.Constants;

/**
 * 客服中心详情
 * Created by lyp on 2018/2/9.
 */

public class CustomerServiceInfoActivity extends BaseTitleActivity {

    String type="";

    public static void startActivity(Context context, boolean forResult) {


        Intent intent = new Intent(context, CustomerServiceInfoActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_call_center;
    }

    @Override
    protected void initViews() {


        Intent i=getIntent();
        type=i.getStringExtra("type");
        Log.d("type", "initViews:  "+type);


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
        setTitleMiddle("客服中心");
    }
}
