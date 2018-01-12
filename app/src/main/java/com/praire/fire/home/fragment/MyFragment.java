package com.praire.fire.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.base.BaseFragment;
import com.praire.fire.merchant.MerchantActivity1;
import com.praire.fire.my.AccountManagementActivity;
import com.praire.fire.my.CustomerServiceActivity;
import com.praire.fire.my.IntegralActivity;
import com.praire.fire.my.InvitationIntegralActivity;
import com.praire.fire.my.MyCollectActivity;
import com.praire.fire.my.MyEvaluateActivity;
import com.praire.fire.my.MyWalletActivity;
import com.praire.fire.my.NearbyActivity;
import com.praire.fire.my.SetActivity;
import com.praire.fire.car.ShoppingCarActivity;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.ShopInfoBean;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;

/**
 * 我的
 * Created by lyp on 2017/12/27.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener{
    SimpleDraweeView fragmentMyImg;
    TextView fragmentMyPhone;
    TextView fragmentMyVip;
    TextView fragmentMyWallet;
    TextView fragmentMyIntegral;
    TextView fragmentMyInvitationIntegral;
    TextView fragmentMyOrder;
    TextView fragmentMyShoppingcar;
    RelativeLayout nearby,merchantServices,myEvaluate,myOrder,shoppingcar,collect,wallet,myIntegral,invitationIntegral;
    ImageView set,earphone;
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
        fragmentMyPhone.setOnClickListener(this);
        fragmentMyVip.setOnClickListener(this);
        myOrder.setOnClickListener(this);
        collect.setOnClickListener(this);
        wallet.setOnClickListener(this);
        myIntegral.setOnClickListener(this);
        invitationIntegral.setOnClickListener(this);
        set.setOnClickListener(this);
        earphone.setOnClickListener(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_my_phone:
                AccountManagementActivity.startActivity(getActivity(),false);
                break;
            case R.id.fragment_my_vip:
                break;
            case R.id.fragment_my_earphone:
                CustomerServiceActivity.startActivity(getActivity(), false);
                break;
            case R.id.fragment_my_set:
                SetActivity.startActivity(getActivity(), false);
                break;
            case R.id.fragment_my_img:

                break;
            case R.id.fragment_my_wallet_rl:
                MyWalletActivity.startActivity(getActivity(), false);
                break;
            case R.id.fragment_my_integral_rl:
                IntegralActivity.startActivity(getActivity(), false);
                break;
            case R.id.fragment_my_invitation_integral_rl:
                InvitationIntegralActivity.startActivity(getActivity(), false);
                break;
            case R.id.fragment_my_order_rl:
//                CustomerServiceActivity.startActivity(getActivity(),false);
                break;
            case R.id.fragment_my_shoppingcar_rl:
                ShoppingCarActivity.startActivity(getActivity(), false);
                break;
            case R.id.fragment_my_collect_rl:
                MyCollectActivity.startActivity(getActivity(), false);
                break;
            case R.id.fragment_my_evaluate_rl:
                MyEvaluateActivity.startActivity(getActivity(), false);
                break;
            case R.id.fragment_my_merchant_services_rl:

                /*by domain*/


                String cookie= (String) SharePreferenceMgr.get(getContext(),LOGIN_COOKIE,"");
                String result=new UseAPIs().getShopInfo(cookie);

                ShopInfoBean s=new J2O().getShopInfo(result);

                Toast.makeText(getContext(), s.getChecked()+"", Toast.LENGTH_SHORT).show();

                if (s.getChecked().equals("0") || s.getChecked().equals("2")) {  /*审核中*/
                    Intent i=new Intent(getContext(),MerchantActivity1.class);
                    Bundle b=new Bundle();
//                    b.putParcelable("shopInfo",s);
                    b.putSerializable("shopInfo",s);
                    i.putExtras(b);
                    startActivity(i);

                } else if (s.getChecked().equals("1")) {/*通过*/
                    /*跳到商家服务*/
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
