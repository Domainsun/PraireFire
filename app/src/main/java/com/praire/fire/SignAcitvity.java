package com.praire.fire;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.praire.fire.base.BaseActivity;
import com.praire.fire.car.CarActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.home.MainActivity;
import com.praire.fire.my.setActivitys.RealVerifyActivity;
import com.praire.fire.my.setActivitys.SetPayPasswrodActivity;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.RealVerifyBean;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.praire.fire.common.ConstanUrl.Hsign;
import static com.praire.fire.common.Constants.LOGIN_COOKIE;

/**
 * Created by sunlo on 2018/1/2.
 */

public class SignAcitvity extends Activity {
    String phone = "";
    String pw = "";
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pw)
    EditText etPw;
    @BindView(R.id.btn_sign)
    Button btnSign;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_findPw)
    TextView tvFindPw;
    public static Handler handler_sign;
    MyApplication myApplication;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.check_password)
    CheckBox checkPassword;

    String cookie = "";
    UseAPIs u = new UseAPIs();
    J2O j = new J2O();


    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, SignAcitvity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);


        initview();

    }

    @SuppressLint("HandlerLeak")
    private void initview() {

        //add by lyp
        phone = (String) SharePreferenceMgr.get(this, Constants.USER_ID, "");


        myApplication = (MyApplication) getApplication();
        Intent i = getIntent();
        phone = i.getStringExtra("phone");
        //--------add by lyp
        phone = (String) SharePreferenceMgr.get(this, Constants.USER_ID, "");
        pw = (String) SharePreferenceMgr.get(this, Constants.PASSWORD, "");
        etPw.setText(pw);
        //--------add end

        etPhone.setText(phone);
        handler_sign = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == Hsign) {
                    String signCookie = (String) msg.obj;
                    myApplication.setSignCookie(signCookie);
                }
            }
        };

        checkPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
//                    checkPassword.setChecked(false);
                    etPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
//                    checkPassword.setChecked(true);
                    etPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @OnClick({R.id.btn_sign, R.id.tv_register, R.id.tv_findPw, R.id.imageView2, R.id.check_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sign:
                phone = etPhone.getText().toString();
                pw = etPw.getText().toString();

                if ("".equals(phone) || "".equals(pw)) {
                    Toast.makeText(this, "请输入手机号或密码", Toast.LENGTH_SHORT).show();
                } else {
                    String result = new UseAPIs().sign(phone, pw, myApplication);
                    if (result.length() != 0) {
                        APIResultBean a = new J2O().getAPIResult(result);
                        Toast.makeText(this, a.getMsg() + "", Toast.LENGTH_SHORT).show();
                        if ("1".equals(a.getCode())) {
                            SharePreferenceMgr.put(this, Constants.LOGIN_COOKIE, myApplication.getSignCookie());
                            SharePreferenceMgr.put(MyApplication.getInstance(), Constants.LOGIN_COOKIE, myApplication.getSignCookie());
                            //-------//add by lyp --------






//
                            SharePreferenceMgr.put(this, Constants.USER_ID, phone);
                            SharePreferenceMgr.put(this, Constants.PASSWORD, pw);
//

                            cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");
                                     /*----add by domain--------*/

                              /*判断是否设置了支付密码，没则跳转设置*/
                            try {
                                String str = u.hasSetPassword(cookie);
                                APIResultBean o = j.getAPIResult(str);


                                Log.d("onViewClicked", "onViewClicked: "+o.getCode());
                                if (o.getCode().equals("0")) {
                                    startActivity(new Intent(this, SetPayPasswrodActivity.class));
                                } else {

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
                            } catch (Exception e) {
                                Log.e("initViews", "initViews: " + e.toString());
                            }


                                 /*----add by domain--------*/
//                            finish();
                        }
                    } else {
                        Toast.makeText(SignAcitvity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                    }


                }
                break;

            case R.id.tv_register:
                Intent i = new Intent(this, RegisterActivity.class);
                startActivity(i);

                break;
            case R.id.tv_findPw:

                phone = etPhone.getText().toString();
                if (phone.length() != 0) {
                    Intent i_find = new Intent(this, FindPasswordActivity.class);
                    i_find.putExtra("phone", phone);
                    startActivity(i_find);

                } else {
                    Toast.makeText(this, "请输入用户后找回", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.imageView2:
                finish();
                break;
            case R.id.check_password:


                break;

        }
    }


    @OnClick(R.id.check_password)
    public void onViewClicked() {
    }
}
