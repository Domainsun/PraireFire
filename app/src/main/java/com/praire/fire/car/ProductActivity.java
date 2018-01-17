package com.praire.fire.car;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.utils.statusbarcolor.Eyes;

import butterknife.ButterKnife;

/**
 * 商品列表
 * 商品  服务
 * Created by lyp on 2018/1/4.
 */

public class ProductActivity extends BaseActivity {

    private String businessId;

    public static void startActivity(Context context, String businessId, int type, boolean forResult) {
        Intent intent = new Intent(context, ProductActivity.class);
        intent.putExtra(Constants.BUSSINESS_ID, businessId);
        intent.putExtra(Constants.UI_TYPE, type);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_shop_product;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initListeners() {
        businessId = getIntent().getStringExtra(Constants.BUSSINESS_ID);
        //getIntent().getIntExtra(Constants.UI_TYPE,0);
        Eyes.setStatusBarColor(this, ContextCompat.getColor(this, R.color.status_bar));
    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

    }
}
