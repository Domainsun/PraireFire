package com.praire.fire.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.MyApplication;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 账号管理
 * Created by lyp on 2018/1/2.
 */
public class AccountManagementActivity extends BaseTitleActivity {

    @BindView(R.id.account_management_icon)
    SimpleDraweeView accountManagementIcon;
    @BindView(R.id.account_management_order)
    TextView accountManagementOrder;
    @BindView(R.id.account_management_phone)
    TextView accountManagementPhone;

    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, AccountManagementActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_my_account_management;
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
        setTitleMiddle(getString(R.string.account_management));
    }


    @OnClick({R.id.account_management_icon_rl, R.id.account_management_order_rl,
            R.id.account_management_phone_rl, R.id.account_management_psd_rl,
            R.id.account_management_truth_name, R.id.account_management_bankcard_rl,
            R.id.account_management_address_rl,R.id.btn_exit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.account_management_icon_rl:
                break;
            case R.id.account_management_order_rl:
//                PassWordManagementActivity.startActivity(this,false);
                break;
            case R.id.account_management_phone_rl:
//                PassWordManagementActivity.startActivity(this,false);
                break;
            case R.id.account_management_psd_rl:
                PassWordManagementActivity.startActivity(this, false);
                break;
            case R.id.account_management_truth_name:
                CertificationActivity.startActivity(this, false);
                break;
            case R.id.account_management_bankcard_rl:
                BankCardActivity.startActivity(this, false);
                break;
            case R.id.account_management_address_rl:
                ShippingAddressActivity.startActivity(this, false);
                break;
            case R.id.btn_exit_login:
                new MyApplication().exit();
                break;
            default:
                break;
        }
    }



}
