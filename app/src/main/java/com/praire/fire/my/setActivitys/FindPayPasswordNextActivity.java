package com.praire.fire.my.setActivitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.praire.fire.R;
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

public class FindPayPasswordNextActivity extends BaseActivity {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.et_inum)
    EditText etInum;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.submit)
    Button submit;

    UseAPIs u = new UseAPIs();
    J2O j = new J2O();

    String cookie = "";
    String inum = "";
    String password = "";
    @BindView(R.id.check_password)
    CheckBox checkPassword;



    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, FindPayPasswordNextActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_find_pay_password_next;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");

        Log.d("signcookie", "signcookie: "+cookie);

        checkPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {
//        Intent i = getIntent();
//        cookie = i.getStringExtra("cookie");
//        Log.d("smscookie", "smscookie: " + cookie);
    }


    @OnClick({R.id.tv_back, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.submit:
                inum = etInum.getText().toString();
                password = etPassword.getText().toString();

                if (inum.length() != 0 && password.length() != 0) {
                    String str = u.changePayPassword(password, inum, cookie);
                    APIResultBean o = j.getAPIResult(str);

                    Log.d("str findpay", "findpay: " + str);

                    Toast.makeText(this, o.getMsg() + "", Toast.LENGTH_SHORT).show();
                    if (1 == o.getCode()) {
                        startActivity(new Intent(this, PasswordMangeActivity.class));

                        Log.d("str", "str: " + str);
                    }
                } else {
                    Toast.makeText(this, "请把信息填写完整!", Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }


}
