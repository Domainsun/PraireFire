package com.praire.fire;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.okhttp.UseApi;
import com.praire.fire.order.PayActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.praire.fire.common.ConstanUrl.HsmsCode;
import static com.praire.fire.common.ConstanUrl.PhotoCode;
import static com.praire.fire.common.Constants.INTENT_DATA;
import static com.praire.fire.common.Constants.UI_TYPE;

public class FindPasswordActivity extends AppCompatActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_show_phone)
    TextView tvShowPhone;
    @BindView(R.id.et_smsCode)
    EditText etSmsCode;
    @BindView(R.id.btn_sendSmsCode)
    Button btnSendSmsCode;
    @BindView(R.id.submit)
    Button submit;
    CountDownTimer timer;
    String phone = "";
    @BindView(R.id.ed_input_photoCode)
    EditText edInputPhotoCode;
    @BindView(R.id.iv_show_code)
    ImageView ivShowCode;


    String photoCode = "";
    public static Handler handler_findPassword;

    String photoCodeCookie = "";
    String smsCodeCookie = "";

    UseApi api = new UseApi();
    UseAPIs u=new UseAPIs();
    public static void startActivity(Context context, String phone, boolean forResult) {
        Intent intent = new Intent(context, FindPasswordActivity.class);
        intent.putExtra("phone", phone);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        ButterKnife.bind(this);


        handler_findPassword = new Handler() {
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
        api.getPhotoCode("1");

        Intent i = getIntent();
        phone = i.getStringExtra("phone");

        if (phone.length() == 11) {

            String str = phone.substring(8, 11);
            tvShowPhone.setText("请输入手机****" + str + "的验证码");
        }


    }
//
//
//    public void getPhotoCode() {
//
//        Runnable payRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//                OkHttpClient client = new OkHttpClient();
//                //创建一个Request
//                final Request request = new Request.Builder()
//                        .get()
//                        .url("https://www.lygyxh.cn/api.php/Public/verify")
//                        .build();
//                //通过client发起请求
//                client.newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        System.out.println(e.toString());
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        if (response.isSuccessful()) {
//                            photocookie = response.headers("set-cookie").get(0);
//
//                            InputStream in = response.body().byteStream();
//                            //转化为bitmap
//                            Bitmap bitmap = BitmapFactory.decodeStream(in);
//                            ivShowCode.setImageBitmap(bitmap);
//
//                        }
//                    }
//                });
//            }
//        };
//
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();
//    }
String str[] =new String[2];

    @OnClick({R.id.tv_back, R.id.btn_sendSmsCode, R.id.submit, R.id.iv_show_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_sendSmsCode:
                photoCode = edInputPhotoCode.getText().toString();
                if (phone.equals("") || photoCode.equals("")) {
                    Toast.makeText(this, "请输入图形验证码", Toast.LENGTH_SHORT).show();
                } else {
                    String result = "";
                    try {
                        result = new UseAPIs().sendSmsCode(phone, photoCode, photoCodeCookie, "1");

                        if (result.length() != 0) {
                            APIResultBean a = new J2O().getAPIResult(result);
                            if (1==a.getCode()) {
                                Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
                                timer.start();
                            } else {
                                Toast.makeText(this, a.getMsg(), Toast.LENGTH_SHORT).show();
                                api.getPhotoCode("1");
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

            case R.id.iv_show_code:
                api.getPhotoCode("1");
                break;
        }
    }

}
