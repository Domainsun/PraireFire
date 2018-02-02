package com.praire.fire.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.praire.fire.R;
import com.praire.fire.SignAcitvity;
import com.praire.fire.home.MainActivity;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.NetworkHandler;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;
import com.praire.fire.utils.ToastUtil;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;


public abstract class BaseActivity extends AppCompatActivity {

    // Refer fragment class
    protected FragmentManager fm;
    protected NetworkHandler uiHandler ;
    public Context context ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getFragmentLayout());
        setupComponent();
        uiHandler = createNetWorkHandler();
//        if(hasLogin()) {
            init();
//        }
    }
    public void setupComponent() {

    }



    /**
     * Every fragment has to inflate a layout in the onCreateView method. We have added this method to
     * avoid duplicate all the inflate code in every fragment. You only have to return the layout to
     * inflate in this method when extends BaseFragment.
     */
    protected abstract int getFragmentLayout();

    protected abstract void initViews();

    /**
     * Init some listeners
     */
    protected abstract void initListeners();

    /**
     * Init some adapters
     */
    protected abstract void initAdapters();

    /**
     * Init some datas
     */
    protected abstract void initData();

    /**
     * Init
     */

//    protected abstract void isSign();


    protected void init() {

        initReferFragment();
        initViews();
        initListeners();
        initData();
        initAdapters();

    }

    public  void isSign(){
//
        Log.d("isSign", "isSign: 111111111111111111111111111111111");


        String cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");

        String result = "";
        String str = "\"code\":0";

        if (cookie.length() != 0 && cookie != null) {
            result = new UseAPIs().getShopInfo(cookie);


            Log.d("isSign", "isSign: "+result);



            if (result.length() != 0) {

                if (result.indexOf(str)!= -1) {

                    Toast.makeText(context, "登录已经失效，请重新登录", Toast.LENGTH_SHORT).show();

                    Log.d("isSign222222", "isSign222222: "+result);
                    Intent i = new Intent(context, SignAcitvity.class);
                    context.startActivity(i);
                }

            }
        }
    }



    protected void initReferFragment() {
        fm = getSupportFragmentManager();
    }


    @SuppressLint("HandlerLeak")
    public NetworkHandler createNetWorkHandler() {
        return new NetworkHandler(this) {
            @Override
            public void dispatchMessage(Message msg) {
                if(msg.what == OkhttpRequestUtil.NETWORK_ERROR){
                    ToastUtil.show(BaseActivity.this,getString(R.string.error_network));
                }else if(msg.what == OkhttpRequestUtil.NONE_DATA){
                    ToastUtil.show(BaseActivity.this,getString(R.string.no_data));
                }else {
                    networkResponse(msg);
                }
            }
        };
    }

    /**
     *
     * @param msg
     */
    protected void networkResponse(Message msg) {

    }

    protected boolean hasLogin() {
        String cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");
        /*如果未登录过，自动跳转到登录页*/
        String str = "\"code\":0";
        if (cookie != null && cookie.length() != 0) {
            String result = new UseAPIs().getShopInfo(cookie);
            if (result.length() != 0) {
                if (result.contains(str)) {
                    toLogin();
                    return false;
                }
                return true;
            }
        } else {
            toLogin();
            return false;
        }
        return true;
    }

    private void toLogin() {
        Intent i = new Intent(this, SignAcitvity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
