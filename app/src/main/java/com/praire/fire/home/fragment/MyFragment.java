package com.praire.fire.home.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.praire.fire.R;
import com.praire.fire.SignAcitvity;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseFragment;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.merchant.BusinessServiceActivity;
import com.praire.fire.merchant.MerchantActivity1;
import com.praire.fire.my.AccountManagementActivity;
import com.praire.fire.my.MyEvaluateActivity;
import com.praire.fire.my.MyWalletActivity;
import com.praire.fire.my.NearbyActivity;
import com.praire.fire.my.SetActivity;
import com.praire.fire.my.ShoppingCarActivity;
import com.praire.fire.my.bean.UserBean;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.ShopInfoBean;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.ImageUtils;
import com.praire.fire.utils.SharePreferenceMgr;
import com.squareup.picasso.Picasso;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;
import static com.praire.fire.common.Constants.USER_ID;

/**
 * 我的
 * Created by lyp on 2017/12/27.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener {
    CircleImageView fragmentMyImg;
    TextView fragmentMyPhone;
    TextView fragmentMyVip;
    TextView fragmentMyWallet;
    TextView fragmentMyIntegral;
    TextView fragmentMyInvitationIntegral;
    TextView fragmentMyOrder;
    TextView fragmentMyShoppingcar;
    RelativeLayout nearby, merchantServices, myEvaluate, myOrder, shoppingcar, collect, wallet, myIntegral, invitationIntegral;
    ImageView set, earphone;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initFindView(view);
        return view;
    }

    private void initFindView(View view) {
        fragmentMyImg = view.findViewById(R.id.fragment_my_img);
        fragmentMyPhone = view.findViewById(R.id.fragment_my_phone);
        fragmentMyWallet = view.findViewById(R.id.fragment_my_wallet);
        fragmentMyIntegral = view.findViewById(R.id.fragment_my_integral);
        fragmentMyInvitationIntegral = view.findViewById(R.id.fragment_my_invitation_integral);
        fragmentMyOrder = view.findViewById(R.id.fragment_my_order);
        fragmentMyShoppingcar = view.findViewById(R.id.fragment_my_shoppingcar);

        nearby = view.findViewById(R.id.fragment_my_nearby_rl);
        merchantServices = view.findViewById(R.id.fragment_my_merchant_services_rl);
        myEvaluate = view.findViewById(R.id.fragment_my_evaluate_rl);
        fragmentMyVip = view.findViewById(R.id.fragment_my_vip);
        myOrder = view.findViewById(R.id.fragment_my_order_rl);
        shoppingcar = view.findViewById(R.id.fragment_my_shoppingcar_rl);
        collect = view.findViewById(R.id.fragment_my_collect_rl);
        wallet = view.findViewById(R.id.fragment_my_wallet_rl);
        myIntegral = view.findViewById(R.id.fragment_my_integral_rl);
        invitationIntegral = view.findViewById(R.id.fragment_my_invitation_integral_rl);

        set = view.findViewById(R.id.fragment_my_set);
        earphone = view.findViewById(R.id.fragment_my_earphone);

        fragmentMyImg.setOnClickListener(this);
        nearby.setOnClickListener(this);
        myEvaluate.setOnClickListener(this);
        merchantServices.setOnClickListener(this);
        fragmentMyPhone.setOnClickListener(this);
        fragmentMyVip.setOnClickListener(this);
        myOrder.setOnClickListener(this);
        collect.setOnClickListener(this);
        wallet.setOnClickListener(this);
        myIntegral.setOnClickListener(this);
        invitationIntegral.setOnClickListener(this);
        set.setOnClickListener(this);
        earphone.setOnClickListener(this);
        shoppingcar.setOnClickListener(this);
    }

    @Override
    public void initListener() {
        if (hasLogin(false)) {
            OkhttpRequestUtil.get(ConstanUrl.USER_INDEX, 1, true, uiHandler);
        }

    }

    @Override
    public void initData() {

    }

    @Override
    protected void networkResponse(Message msg) {
        switch (msg.what) {
            case 1:
                Gson gson = new Gson();
                UserBean userBean = gson.fromJson((String) msg.obj, UserBean.class);
                if (!userBean.getNickname().isEmpty()) {
                    fragmentMyPhone.setText(userBean.getNickname());
                } else {
                    fragmentMyPhone.setText(userBean.getTel());
                }
                if (!TextUtils.isEmpty(userBean.getHead())) {
                    Picasso.with(getActivity()).load(userBean.getHead()).into(fragmentMyImg);
                } else {
                    fragmentMyImg.setImageResource(R.mipmap.avatar2);
                }

                fragmentMyVip.setText(userBean.getLevelinfo().getDes());
                fragmentMyWallet.setText(userBean.getCapital());

//                fragmentMyIntegral.setText(userBean.getTel());
//                fragmentMyInvitationIntegral.setText(userBean.getTel());
//                fragmentMyOrder.setText(userBean.getTel());
//                fragmentMyShoppingcar.setText(userBean.getTel());
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (hasLogin(false)) {
            OkhttpRequestUtil.get(ConstanUrl.USER_INDEX, 1, true, uiHandler);
        } else {
            fragmentMyPhone.setText("点击登录");
            fragmentMyImg.setImageResource(R.mipmap.avatar2);
            fragmentMyVip.setText("普通会员");
            fragmentMyWallet.setText("0.00");
            fragmentMyIntegral.setText("0");
            fragmentMyInvitationIntegral.setText("0");
        }
    }

    @Override
    public void onClick(View view) {
        if (hasLogin(true)) {

            switch (view.getId()) {
                case R.id.fragment_my_phone:
                    SignAcitvity.startActivity(getActivity(), false);
                    break;
                case R.id.fragment_my_vip:
                    break;
                case R.id.fragment_my_earphone:
//                CustomerServiceActivity.startActivity(getActivity(), false);


                    break;
                case R.id.fragment_my_set:
                    SetActivity.startActivity(getActivity(), false);
                    break;
                case R.id.fragment_my_img:
                    AccountManagementActivity.startActivity(getActivity(), false);
                    break;
                case R.id.fragment_my_wallet_rl:
                    MyWalletActivity.startActivity(getActivity(), false);
                    break;
                case R.id.fragment_my_integral_rl:
                    //                IntegralActivity.startActivity(getActivity(), false);
                    break;
                case R.id.fragment_my_invitation_integral_rl:

//                InvitationIntegralActivity.startActivity(getActivity(), false);
                    break;
                case R.id.fragment_my_order_rl:
//                CustomerServiceActivity.startActivity(getActivity(),false);
                    break;
                case R.id.fragment_my_shoppingcar_rl:
                    ShoppingCarActivity.startActivity(getActivity(), false);
                    break;
                case R.id.fragment_my_collect_rl:
//                MyCollectActivity.startActivity(getActivity(), false);
                    break;
                case R.id.fragment_my_evaluate_rl:
                    MyEvaluateActivity.startActivity(getActivity(), false);


                    break;
                case R.id.fragment_my_merchant_services_rl:

                /*by domain*/

                    String cookie = (String) SharePreferenceMgr.get(getContext(), LOGIN_COOKIE, "");
                    String result = "";
                    try {
                        result = new UseAPIs().getShopInfo(cookie);

                        Log.d("result", "onClick: " + result);

                        if (result.length() == 0) {

                        }

                        if (result != null) {
                            ShopInfoBean s = new J2O().getShopInfo(result);


                            if (s.getChecked().equals("0") || s.getChecked().equals("2")) {  /*审核中 和 被拒绝*/
                                Intent i = new Intent(getContext(), MerchantActivity1.class);
                                Bundle b = new Bundle();
                                b.putSerializable("shopInfo", s);
                                i.putExtras(b);
                                startActivity(i);

                            } else if (s.getChecked().equals("1")) {/*通过*/
                    /*跳到商家服务*/
                                Intent i = new Intent(getContext(), BusinessServiceActivity.class);
                                startActivity(i);
                            }
                        } else {

                            Toast.makeText(getContext(), "网络错误！", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Intent i2 = new Intent(getContext(), MerchantActivity1.class);
                        startActivity(i2);
                    }


                    break;
                case R.id.fragment_my_nearby_rl:
                    NearbyActivity.startActivity(getActivity(), false);
                    break;
                default:
                    break;
            }
        }
    }
}
