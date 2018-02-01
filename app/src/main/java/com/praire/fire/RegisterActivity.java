package com.praire.fire;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.okhttp.UseApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.praire.fire.common.ConstanUrl.HsmsCode;
import static com.praire.fire.common.ConstanUrl.PhotoCode;

public class RegisterActivity extends Activity {

    @BindView(R.id.iv_finsh)
    ImageView ivFinsh;
    @BindView(R.id.ed_input_phone)
    EditText edInputPhone;
    @BindView(R.id.ed_input_photoCode)
    EditText edInputPhotoCode;
    @BindView(R.id.iv_show_code)
    ImageView ivShowCode;
    @BindView(R.id.et_input_smsCode)
    EditText etInputSmsCode;
    @BindView(R.id.btn_sendSmsCode)
    Button btnSendSmsCode;
    @BindView(R.id.et_input_pw)
    EditText etInputPw;
    @BindView(R.id.et_input_invitationCode)
    EditText etInputInvitationCode;
    @BindView(R.id.btn_register)
    Button btnRegister;

    public static Handler handler_register;

    UseApi api = new UseApi();
    J2O j2O = new J2O();
    @BindView(R.id.check_password)
    CheckBox checkPassword;


    private String phone = "";
    private String photoCode = "";
    private String smsCode = "";
    private String pw = "";
    private String invitation = "";
    String photoCodeCookie = "";
    String smsCodeCookie = "";
    CountDownTimer timer;
    Application application = getApplication();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initview();


    }

    private void initview() {

        timer = new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                btnSendSmsCode.setText((millisUntilFinished / 1000) + "秒后重发");
                btnSendSmsCode.setEnabled(false);
            }

            @Override
            public void onFinish() {
                btnSendSmsCode.setEnabled(true);
                btnSendSmsCode.setText("发送验证码");
            }
        };


        api.getPhotoCode("0");

        handler_register = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == PhotoCode) {
                    Bitmap bitmap = (Bitmap) msg.obj;
                    ivShowCode.setImageBitmap(bitmap);
                    photoCodeCookie = api.getPhotocookie();
                } else if (msg.what == HsmsCode) {
                    smsCodeCookie = (String) msg.obj;
                }
            }


        };

        checkPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
//                    checkPassword.setChecked(false);
                    etInputPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
//                    checkPassword.setChecked(true);
                    etInputPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }


    @OnClick({R.id.iv_finsh, R.id.iv_show_code, R.id.btn_sendSmsCode, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finsh:
                finish();
                break;
            case R.id.iv_show_code:
                api.getPhotoCode("0");
                break;
            case R.id.btn_sendSmsCode:

                phone = edInputPhone.getText().toString();
                photoCode = edInputPhotoCode.getText().toString();
                if (phone.equals("") || photoCode.equals("")) {
                    Toast.makeText(this, "请输入手机号和图形验证码", Toast.LENGTH_SHORT).show();
                } else {
                    String result = "";
                    try {
                        result = new UseAPIs().sendSmsCode(phone, photoCode, photoCodeCookie, "0");
                        if (result.length() != 0) {


                            APIResultBean a = j2O.getAPIResult(result);
                            if ("1".equals(a.getCode())) {
                                Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
                                timer.start();
                            } else {
                                Toast.makeText(this, a.getMsg(), Toast.LENGTH_SHORT).show();
                                api.getPhotoCode("0");
                            }
                        } else {
                            Toast.makeText(this, "网络错误！", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Log.e("onViewClicked", "onViewClicked: " + e.toString());
                        Toast.makeText(this, "网络错误！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_register:
                phone = edInputPhone.getText().toString();
                smsCode = etInputSmsCode.getText().toString();
                pw = etInputPw.getText().toString();
                invitation = etInputInvitationCode.getText().toString();

                if (phone.equals("") || pw.equals("") || smsCode.equals("")) {
                    Toast.makeText(this, "请填写完整", Toast.LENGTH_SHORT).show();
                } else {
                    String result = "";
                    try {
                        result = new UseAPIs().register(phone, pw, smsCode, invitation, smsCodeCookie);
                        if (result.length() != 0) {
                            APIResultBean a = j2O.getAPIResult(result);
                            if ("1".equals(a.getCode())) {
                                Intent i = new Intent(this, SignAcitvity.class);
                                i.putExtra("phone", phone);
                                startActivity(i);
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.e("onViewClicked", "onViewClicked: " + e.toString());
                        Toast.makeText(this, "网络错误！", Toast.LENGTH_SHORT).show();
                    }

                }

                break;
        }
    }


}
