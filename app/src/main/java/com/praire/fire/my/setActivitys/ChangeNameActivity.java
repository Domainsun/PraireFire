package com.praire.fire.my.setActivitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.my.SetActivity;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.UserHeadBean;
import com.praire.fire.okhttp.JavaBean.UserInfoBean;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;

public class ChangeNameActivity extends BaseActivity {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_service_name)
    TextView tvServiceName;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.iv_del)
    ImageView ivDel;
    @BindView(R.id.submit)
    Button submit;

    UseAPIs u=new UseAPIs();
    J2O j=new J2O();
    String cookie="";
    String  name="";

    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, ChangeNameActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_change_name;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");
        Log.d("cookie", "initViews: "+cookie);


        OkhttpRequestUtil.get(ConstanUrl.GET_USER_INFO, 1, true, uiHandler);

    }
    @Override
    protected void networkResponse(Message msg) {
        super.networkResponse(msg);


        switch (msg.what) {

            case 1:
                String str1 = msg.obj + "";
                Log.d("uinfo", "uinfo: "+str1);

                if (!str1.isEmpty()) {
                    UserInfoBean o = j.getUserInfo(str1);
                    etName.setHint(o.getNickname());
                    Log.d("getNickname", "getNickname: " + o.getNickname());


                }



                break;
            default:
                break;
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



    @OnClick({R.id.tv_back, R.id.iv_del, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.iv_del:

                name="";
                etName.setText("");


                break;
            case R.id.submit:
                name=etName.getText().toString();
                if (name.length()!=0) {

                    try{
                        String str="";
                        str=u.changeUserInfo(cookie,name,null,null,null,null,null);
                        APIResultBean a=j.getAPIResult(str);
                        Toast.makeText(this, a.getMsg(), Toast.LENGTH_SHORT).show();
                        if (1==a.getCode()) {
                            Intent i=new Intent();
                            i.putExtra("name",name);
                            setResult(RESULT_OK, i);
                            finish();
                        }
                        Log.d("str", "str: "+str);
                    }catch (Exception e){
                        Log.e("onViewClicked", "onViewClicked: "+e.toString() );
                    }

                }



                break;
        }
    }
}
