package com.praire.fire.my.setActivitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.praire.fire.MyApplication;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.home.MainActivity;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.RealVerifyBean;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;

public class SetPayPasswrodActivity extends BaseActivity {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_id_num)
    TextView tvIdNum;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_pay_password)
    TextView tvPayPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.submit)
    Button submit;

    UseAPIs u=new UseAPIs();
    J2O j=new J2O();
    String cookie="";
    String password="";
    String cpassword="";

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_set_pay_passwrod;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        cookie = (String) SharePreferenceMgr.get(MyApplication.getInstance(), LOGIN_COOKIE, "");
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



    @OnClick({R.id.et_password, R.id.et_confirm_password, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submit:
                password=etPassword.getText().toString();
                cpassword=etConfirmPassword.getText().toString();
                if (password.length()!=0 && cpassword.length()!=0) {

                    try {
                        String str=   u.setPayPassword(password,cpassword,cookie);
                        APIResultBean o =j.getAPIResult(str);

                        Toast.makeText(this, o.getMsg()+"", Toast.LENGTH_SHORT).show();

                        if (o.getCode().equals("1")) {

                            try {
                                String str1= u.getRealVerifyInfo(cookie);
                                RealVerifyBean r  =j.getRealVerifyInfo(str1);

                                if (r.getStatus()!=null) {
                                    startActivity(new Intent(this, MainActivity.class));
                                }
                            } catch (Exception e) {
                                startActivity(new Intent(this,RealVerifyActivity.class));
                            }

                        }

                        Log.d("str", "onViewClicked: "+str);
                    } catch (Exception e) {
                        Log.e("onViewClicked", "onViewClicked: "+e.toString() );
                    }

                }


                break;
        }
    }
}
