package com.praire.fire.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 绑定银行卡
 * Created by lyp on 2018/1/3.
 */
public class BankCardActivity extends BaseActivity {
    @BindView(R.id.bankcard_name)
    EditText bankcardName;
    @BindView(R.id.bankcard_number)
    EditText bankcardNumber;
    @BindView(R.id.bankcard_bankname_info)
    TextView bankcardBanknameInfo;
    @BindView(R.id.bankcard_address_info)
    TextView bankcardAddressInfo;
    @BindView(R.id.bankcard_sub_branch)
    EditText bankcardSubBranch;

    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, BankCardActivity.class);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_my_bankcard;
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


    @OnClick({R.id.bankcard_bankname, R.id.bankcard_address,R.id.bankcard_btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bankcard_bankname:
                break;
            case R.id.bankcard_address:
                break;
            case R.id.bankcard_btn_commit:
                break;
            default:
                break;
        }
    }




}
