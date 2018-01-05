package com.praire.fire.car;

import android.content.Context;
import android.content.Intent;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.Constants;

import butterknife.ButterKnife;

/**
 * 商品详细信息
 * Created by lyp on 2018/1/4.
 */

public class ProductInfoActivity extends BaseActivity {

    private String productId;

    public static void startActivity(Context context, String productId, boolean forResult) {
        Intent intent = new Intent(context, ProductInfoActivity.class);
        intent.putExtra(Constants.PRODUCT_ID, productId);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_shop_product_info;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initListeners() {
        productId = getIntent().getStringExtra(Constants.PRODUCT_ID);
    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

    }
}
