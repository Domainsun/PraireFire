package com.praire.fire.my.setActivitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.UserInfoBean;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;

public class AddressActivity extends BaseActivity {


    @BindView(R.id.tv_id_num)
    TextView tvIdNum;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.tv_pay_password)
    TextView tvPayPassword;
    @BindView(R.id.et_post_code)
    EditText etPostCode;
    @BindView(R.id.rl_creat_sub_account)
    RelativeLayout rlCreatSubAccount;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.tv_back)
    TextView tvBack;

    UseAPIs u = new UseAPIs();
    J2O j = new J2O();
    String cookie = "";


    String name="";
    String phone="";
    String address="";
    String postCode="";


    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, AddressActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_address;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");

        getUserInfo(cookie);


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

    public void getUserInfo(String cookie) {

        try {

            String str = u.getUserInfo(cookie);
            UserInfoBean o = j.getUserInfo(str);
            etName.setText(o.getContact());
            etPhone.setText(o.getContactnumber());
            etAddress.setText(o.getAddress());
            etPostCode.setText(o.getPostcode());

            if (etPostCode.length()!=0) {
                submit.setText("修改");
            }
        } catch (Exception e) {


        }


    }


    @OnClick({R.id.tv_back,R.id.tv_id_num, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_id_num:
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.submit:
                name=etName.getText().toString();
                phone=etPhone.getText().toString();
                address=etAddress.getText().toString();
                postCode=etPostCode.getText().toString();
                if (name.length() != 0 && phone.length() != 0 && address.length() != 0 && postCode.length() != 0) {
                    try{
                        String str="";
                        str=u.changeUserInfo(cookie,"",address,name,phone,postCode,"");
                        APIResultBean a=j.getAPIResult(str);
                        Toast.makeText(this, a.getMsg(), Toast.LENGTH_SHORT).show();
                        if (1==a.getCode()) {
                            finish();
                        }
                        Log.d("str", "str: "+str);
                    }catch (Exception e){
                    }
                } else {
                    Toast.makeText(this, "请把信息填写完整", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }



    @OnClick(R.id.tv_back)
    public void onViewClicked() {
    }
}
