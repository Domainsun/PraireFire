package com.praire.fire.order;

import android.content.Context;
import android.content.Intent;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.car.CommitOrderActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.data.IntentDataForCommitOrderActivity;

import static com.praire.fire.common.Constants.INTENT_DATA;

/**
 * 订单详情
 * Created by lyp on 2018/1/5.
 */

public class OrderInfoActivity extends BaseTitleActivity{

    public static void startActivity(Context context, String orderId, boolean forResult) {
        Intent intent = new Intent(context, OrderInfoActivity.class);
        intent.putExtra(INTENT_DATA, orderId);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }
    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_order_info;
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
        setTitleMiddle("订单详情");
    }
}
