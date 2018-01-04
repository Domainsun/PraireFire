package com.praire.fire.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 验证手机号
 * Created by lyp on 2018/1/3.
 */
public class VerifyPhoneActivity extends BaseTitleActivity {
    @BindView(R.id.tv_tisi)
    TextView tvTisi;
    @BindView(R.id.input_verify)
    EditText inputVerify;

    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, VerifyPhoneActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_my_verify_phone;
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

    @Override
    public void initiTile() {
        setDefaultBack();
        setTitleMiddle("验证手机号");
    }


    @OnClick({R.id.btn_send_sms, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send_sms:
                break;
            case R.id.btn_next:
                break;
            default:
                break;
        }
    }
}
