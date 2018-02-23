package com.praire.fire.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.praire.fire.R;
import com.praire.fire.SignAcitvity;
import com.praire.fire.home.MainActivity;
import com.praire.fire.okhttp.NetworkHandler;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;
import com.praire.fire.utils.ToastUtil;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;


public abstract class BaseFragment extends Fragment {

    protected View rootView ;
    protected NetworkHandler uiHandler ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(null == rootView){
            rootView = initView(inflater, container, savedInstanceState);
            uiHandler = createNetWorkHandler();
            initListener();
            initData();
        }
        return rootView;
    }

    public abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract void initListener();

    public abstract void initData();

    @SuppressLint("HandlerLeak")
    public NetworkHandler createNetWorkHandler() {
        return new NetworkHandler(getActivity()) {
            @Override
            public void dispatchMessage(Message msg) {

                if(msg.what == OkhttpRequestUtil.NETWORK_ERROR){
                    ToastUtil.show(getActivity(),getString(R.string.error_network));
                }else if(msg.what == OkhttpRequestUtil.NONE_DATA){
                    ToastUtil.show(getActivity(),getString(R.string.no_data));
                }else {
                    networkResponse(msg);
                }
            }
        };
    }
    protected void networkResponse(Message msg) {


    }



    /**
     * 判断是否已登录
     *
     * @param toLogin 若未登录是否进入登录页面
     * @return
     */
    protected boolean hasLogin( boolean toLogin) {

        String cookie = (String) SharePreferenceMgr.get(getActivity(), LOGIN_COOKIE, "");

        /*如果未登录过，自动跳转到登录页*/
        String str = "\"code\":0";
        if (cookie != null && cookie.length() != 0) {
            String result = new UseAPIs().getShopInfo(cookie);
            if (result.length() != 0) {
                if (result.contains(str)) {
                    if(toLogin) {
                        toLogin();
                    }
                    return false;
                }
                return true;
            }
        } else {
            if(toLogin) {
                toLogin();
            }
            return false;
        }
        return true;
    }





    private void toLogin() {

        Toast.makeText(getContext(), "还未登录，请先登录！", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getContext(), SignAcitvity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
