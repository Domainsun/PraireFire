package com.praire.fire.my.setActivitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.praire.fire.FindPasswordNextActivity;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.UserInfoBean;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.okhttp.UseApi;
import com.praire.fire.utils.SharePreferenceMgr;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.praire.fire.common.ConstanUrl.HsmsCode;
import static com.praire.fire.common.ConstanUrl.PhotoCode;
import static com.praire.fire.common.Constants.LOGIN_COOKIE;

public class FindPayPasswordActivity extends BaseActivity {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_show_phone)
    TextView tvShowPhone;
    @BindView(R.id.ed_input_photoCode)
    EditText edInputPhotoCode;
    @BindView(R.id.iv_show_code)
    ImageView ivShowCode;
    @BindView(R.id.et_smsCode)
    EditText etSmsCode;
    @BindView(R.id.btn_sendSmsCode)
    Button btnSendSmsCode;
    @BindView(R.id.submit)
    Button submit;

    CountDownTimer timer;
    String phone = "";

    String photoCode = "";
    public static Handler handler_find_pay_Password;
    String photoCodeCookie = "";
    String smsCodeCookie = "";
    UseApi api = new UseApi();
    UseAPIs u=new UseAPIs();

    String cookie="";
    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, FindPayPasswordActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_find_pay_password;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);

        cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");

        handler_find_pay_Password = new Handler() {
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
        api.getPhotoCode("3");



        try {
            String str=u.getUserInfo(cookie);
            UserInfoBean o=new J2O().getUserInfo(str);
            phone=o.getTel();
            if (phone.length() == 11) {

                String str1 = phone.substring(8, 11);
                tvShowPhone.setText("请输入手机****" + str1 + "的验证码");
            }

        } catch (Exception e) {
            Log.e("initViews", "initViews: "+e.toString() );

        }








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


    String str[] =new String[2];
    @OnClick({R.id.tv_back, R.id.iv_show_code, R.id.btn_sendSmsCode, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.iv_show_code:
                api.getPhotoCode("3");
                break;
            case R.id.btn_sendSmsCode:

                photoCode = edInputPhotoCode.getText().toString();
                if (phone.equals("") || photoCode.equals("")) {
                    Toast.makeText(this, "请输入图形验证码", Toast.LENGTH_SHORT).show();
                } else {
                    String result = "";
                    try {
                        result = new UseAPIs().sendSmsCode(phone, photoCode, photoCodeCookie, "3");

                        if (result.length() != 0) {
                            APIResultBean a = new J2O().getAPIResult(result);
                            if (1==a.getCode()) {
                                Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
                                timer.start();
                            } else {
                                Toast.makeText(this, a.getMsg(), Toast.LENGTH_SHORT).show();
                                api.getPhotoCode("3");
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
            case R.id.submit:

                String sms="";


                    sms=etSmsCode.getText().toString();
                    if (phone.length() != 0 && sms.length() != 0) {


                        try {
                            str= u.verifySms(phone,sms);

                            Log.d("str[0]", "onViewClicked: "+str[0]);
                            APIResultBean o = new J2O().getAPIResult(str[0]);
                            Toast.makeText(this, o.getMsg(), Toast.LENGTH_SHORT).show();
                            if (o.getCode()==1) {
                                Intent i = new Intent(this, FindPasswordNextActivity.class);
                                i.putExtra("phone", phone);
                                i.putExtra("cookie", str[1]);
                                startActivity(i);
                            }

                        } catch (Exception e) {
                            Log.e("eee", "onViewClicked: "+e.toString() );
                        }
                    } else {
                        Toast.makeText(this, "请把信息填写完整", Toast.LENGTH_SHORT).show();
                    }

                break;
        }
    }
}
