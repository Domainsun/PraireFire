package com.praire.fire.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 支付成功
 * 评价成功
 * Created by lyp on 2018/1/24.
 */

public class ResultActivity extends BaseTitleActivity {

    @BindView(R.id.thanks_word)
    TextView thanksWord;

    public static void startActivity(Context context, String title, String msg, boolean forResult) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(Constants.TITLE, title);
        intent.putExtra(Constants.THANKS_MSG, msg);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_pay_success;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        thanksWord.setText(getIntent().getStringExtra(Constants.THANKS_MSG));

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
        setTitleMiddle(getIntent().getStringExtra(Constants.TITLE));
    }

    

    @OnClick(R.id.btn_done_pay)
    public void onViewClicked() {
        finish();
    }
}
