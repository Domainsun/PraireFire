package com.praire.fire;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.J2O;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.okhttp.UseApi;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    public  static Handler handler_register;
    public static final int PhotoCode =1;
    UseApi api=new UseApi();
    J2O j2O=new J2O();


    private String phone="";
    private String photoCode="";
    private String smsCode="";
    private String pw="";
    private String invitation="";
    String photoCodeCookie="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initdata();
        initview();


    }

    private void initview() {
        api.getPhotoCode();
        handler_register=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what==PhotoCode) {
                    Bitmap bitmap= (Bitmap) msg.obj;
                    ivShowCode.setImageBitmap(bitmap);
                    photoCodeCookie= api.getPhotocookie();
                    System.out.println("cookie"+photoCodeCookie);
                }
            }
        };
    }
    private void initdata() {

    }


    @OnClick({R.id.iv_finsh, R.id.iv_show_code, R.id.btn_sendSmsCode, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finsh:
                break;
            case R.id.iv_show_code:
                api.getPhotoCode();
                break;
            case R.id.btn_sendSmsCode:
                phone=edInputPhone.getText().toString();
                photoCode=edInputPhotoCode.getText().toString();
                if (phone.equals("") || photoCode.equals("")) {
                    Toast.makeText(this, "请输入手机号和图形验证码", Toast.LENGTH_SHORT).show();
                } else {
                    String result="";
                    result=new UseAPIs().sendSmsCode(phone,photoCode,photoCodeCookie);
                    APIResultBean a=j2O.getAPIResult(result);
                    if ("0".equals(a.getCode())) {
                        Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();

//                        new CountDownUtil()
//                                .setCountDownMillis(60_000L)//倒计时60000ms
//                                .setCountDownColor(android.R.color.holo_blue_light,android.R.color.darker_gray)//不同状态字体颜色
//                                .setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        Log.e("MainActivity","发送成功");
//                                    }
//                                })
//                                .start();


                    } else {
                        Toast.makeText(this, a.getMsg(), Toast.LENGTH_SHORT).show();
                        api.getPhotoCode();
                    }
                }
                break;
            case R.id.btn_register:
                phone=edInputPhone.getText().toString();
                smsCode=etInputSmsCode.getText().toString();
                pw=etInputPw.getText().toString();
                invitation=etInputInvitationCode.getText().toString();
                String result="";
                result=new UseAPIs().register(phone,pw,invitation,photoCodeCookie);
                APIResultBean a=j2O.getAPIResult(result);
                Toast.makeText(this, a.getMsg()+"", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, invitation, Toast.LENGTH_SHORT).show();
                System.out.println(result);
                break;
        }
    }

}
