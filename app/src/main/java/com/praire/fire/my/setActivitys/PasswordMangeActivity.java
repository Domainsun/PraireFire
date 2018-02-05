package com.praire.fire.my.setActivitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PasswordMangeActivity extends BaseActivity {

    @BindView(R.id.rl_find_pay_password)
    RelativeLayout rlFindPayPassword;
    @BindView(R.id.change_sign_password)
    RelativeLayout changeSignPassword;
    @BindView(R.id.tv_back)
    TextView tvBack;

    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, PasswordMangeActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_password_mange;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
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


    @OnClick({R.id.rl_find_pay_password, R.id.tv_back,R.id.change_sign_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_find_pay_password:

                FindPayPasswordActivity.startActivity(this, false);

                break;
            case R.id.change_sign_password:
                ChangeSignPasswordActivity.startActivity(this, false);

                break;
            case R.id.tv_back:
                finish();

                break;
        }
    }


}
