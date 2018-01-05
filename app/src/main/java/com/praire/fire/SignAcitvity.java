package com.praire.fire;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.praire.fire.common.Constants;
import com.praire.fire.home.MainActivity;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.J2O;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.praire.fire.common.ConstanUrl.Hsign;
import static com.praire.fire.common.ConstanUrl.LOGIN;
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
    public  static Handler handler_sign;
    MyApplication myApplication;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);

       initview();

    }

    @SuppressLint("HandlerLeak")
    private void initview() {
       myApplication = (MyApplication) getApplication();
        Intent i= getIntent();
        phone=i.getStringExtra("phone");
        etPhone.setText(phone);
        handler_sign=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == Hsign) {
                    String signCookie= (String) msg.obj;
                    myApplication.setSignCookie(signCookie);
                }
            }
        };
    }
    @OnClick({R.id.btn_sign, R.id.tv_register, R.id.tv_findPw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sign:
            phone=etPhone.getText().toString();
            pw=etPw.getText().toString();

            if ("".equals(phone) || "".equals(pw)) {
                Toast.makeText(this, "请输入手机号或密码", Toast.LENGTH_SHORT).show();
            } else {
                String result=new UseAPIs().sign(phone,pw,myApplication);
                APIResultBean a= new J2O().getAPIResult(result);
                Toast.makeText(this, a.getMsg()+"", Toast.LENGTH_SHORT).show();
                if ("1".equals(a.getCode())) {

                    SharePreferenceMgr.put(this, Constants.LOGIN_COOKIE,myApplication.getSignCookie());

                    Intent i=new Intent(this, MainActivity.class);
                    startActivity(i);
                }

            }
                break;

            case R.id.tv_register:
                Intent i=new Intent(this,RegisterActivity.class);
                startActivity(i);

                break;
            case R.id.tv_findPw:
                break;
        }
    }
}
