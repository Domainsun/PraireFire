package com.praire.fire.order;

import android.content.Context;
import android.content.Intent;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.Constants;

import static com.praire.fire.common.Constants.INTENT_DATA;

/**
 * 评价订单
 * Created by lyp on 2018/1/23.
 */

public class EvaluateActivity extends BaseTitleActivity {
    public static void startActivity(Context context,String orderId, boolean forResult) {
        Intent intent = new Intent(context, EvaluateActivity.class);
        intent.putExtra(INTENT_DATA, orderId);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_evaluate;
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

    }
}
