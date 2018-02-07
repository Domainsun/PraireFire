package com.praire.fire;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.UseAPIs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindPasswordNextActivity extends AppCompatActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_cpassword)
    EditText etCpassword;
    @BindView(R.id.submit)
    Button submit;
    String phone = "";
    String password = "";
    String cpassword = "";
    String cookie="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password_next);
        ButterKnife.bind(this);

        Intent i = getIntent();
        phone = i.getStringExtra("phone");
        cookie=i.getStringExtra("cookie");

        Log.d("smscodecookie", "smscodecookie: "+cookie);
    }

    @OnClick({R.id.tv_back, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.submit:
                password = etPassword.getText().toString();
                cpassword = etCpassword.getText().toString();


                if (password.length() != 0 & cpassword.length() != 0) {
                    if (password.equals(cpassword)) {
                        String str = "";
                        try {
                            str = new UseAPIs().changeSignPassword(password, cpassword, phone,cookie);

                            Log.d("phone", "phone: "+phone);


                            APIResultBean a = new J2O().getAPIResult(str);


                            if (1==a.getCode()) {
                                Intent i=new Intent(this,SignAcitvity.class);

                                i.putExtra("phone",phone);
                                startActivity(i);
                            }
                            Toast.makeText(this, a.getMsg(), Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            Log.e("onViewClicked", "onViewClicked: " + e.toString());

                        }


                    } else {
                        Toast.makeText(this, "两次填写不一致！", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "请填写完整！", Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }
}
