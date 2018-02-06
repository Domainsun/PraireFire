package com.praire.fire.my.setActivitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.praire.fire.R;
import com.praire.fire.SignAcitvity;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;

public class ChangeSignPasswordActivity extends BaseActivity {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.et_old_password)
    EditText etOldPassword;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.submit)
    Button submit;


    UseAPIs u=new UseAPIs();
    J2O j=new J2O();

    String cookie="";
    String oldPassword="";
    String newPassword="";
    String confirmPassword="";


    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, ChangeSignPasswordActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_change_sign_password;
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
        cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");

    }




    @OnClick({R.id.tv_back, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:

                finish();
                break;
            case R.id.submit:
                oldPassword=etOldPassword.getText().toString();
                newPassword=etNewPassword.getText().toString();
                confirmPassword=etConfirmPassword.getText().toString();

                if (oldPassword.length()!=0 && newPassword.length()!=0 && confirmPassword.length()!=0) {
                    try{
                        String str=u.changeSignPasswordAfterSign(oldPassword,newPassword,confirmPassword,cookie);
                        APIResultBean o =j.getAPIResult(str);
                        if (1==o.getCode()) {
                            startActivity(new Intent(this, SignAcitvity.class));
                        }
                        Toast.makeText(this, o.getMsg()+"", Toast.LENGTH_SHORT).show();

                    }catch (Exception e){


                    }

                }

                break;
        }
    }
}
