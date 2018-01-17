package com.praire.fire.car;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.utils.statusbarcolor.Eyes;

import butterknife.ButterKnife;

/**
 * 商品评价列表
 * 用户评价
 * Created by lyp on 2018/1/4.
 */

public class ProductAllEvalauteActivity extends BaseTitleActivity {

    private String businessId;

    public static void startActivity(Context context, String businessId, boolean forResult) {
        Intent intent = new Intent(context, ProductAllEvalauteActivity.class);
        intent.putExtra(Constants.BUSSINESS_ID, businessId);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_shop_product_evalaute;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initListeners() {
        businessId = getIntent().getStringExtra(Constants.BUSSINESS_ID);
        Eyes.setStatusBarColor(this, ContextCompat.getColor(this, R.color.status_bar));
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
        setTitleMiddle(getString(R.string.user_evaluate));
    }
}
