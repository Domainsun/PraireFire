package com.praire.fire.my;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.merchant.InfoChooseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 客服中心
 * Created by lyp on 2018/1/2.
 */

public class CustomerServiceActivity extends BaseTitleActivity {
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.rl_2)
    RelativeLayout rl2;
    @BindView(R.id.rl_3)
    RelativeLayout rl3;
    @BindView(R.id.btn_phone_customer)
    Button btnPhoneCustomer;
    @BindView(R.id.btn_online_customer)
    Button btnOnlineCustomer;

    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, CustomerServiceActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_customer_service;//这个要换掉 不能修改这个activity_call_center文件这是客服中心详情用的
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
        setTitleMiddle("客服中心");
    }



    @OnClick({R.id.rl_1, R.id.rl_2, R.id.rl_3, R.id.btn_phone_customer, R.id.btn_online_customer})
    public void onViewClicked(View view) {

        Intent i=new Intent(this,CustomerServiceInfoActivity.class);

        switch (view.getId()) {
            case R.id.rl_1:
                i.putExtra("type","1");
                startActivity(i);

                break;
            case R.id.rl_2:
                i.putExtra("type","2");
                startActivity(i);
                break;
            case R.id.rl_3:
                i.putExtra("type","3");
                startActivity(i);
                break;
            case R.id.btn_phone_customer:

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"3570638724"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                break;
            case R.id.btn_online_customer:
                if (checkApkExist(this, "com.tencent.mobileqq")){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin="+"1710521115"+"&version=1")));
                }else{
                    Toast.makeText(this,"本机未安装QQ应用", Toast.LENGTH_SHORT).show();
                }
                break;


        }
    }

    public boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
