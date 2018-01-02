package com.praire.fire.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.base.BaseFragment;
import com.praire.fire.my.AccountManagementActivity;
import com.praire.fire.my.CustomerServiceActivity;
import com.praire.fire.my.IntegralActivity;
import com.praire.fire.my.InvitationIntegralActivity;
import com.praire.fire.my.MyCollectActivity;
import com.praire.fire.my.MyEvaluateActivity;
import com.praire.fire.my.MyWalletActivity;
import com.praire.fire.my.NearbyActivity;
import com.praire.fire.my.SetActivity;
import com.praire.fire.my.ShoppingCarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的
 * Created by lyp on 2017/12/27.
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.fragment_my_img)
    SimpleDraweeView fragmentMyImg;
    @BindView(R.id.fragment_my_phone)
    TextView fragmentMyPhone;
    @BindView(R.id.fragment_my_vip)
    TextView fragmentMyVip;
    @BindView(R.id.fragment_my_wallet)
    TextView fragmentMyWallet;
    @BindView(R.id.fragment_my_integral)
    TextView fragmentMyIntegral;
    @BindView(R.id.fragment_my_invitation_integral)
    TextView fragmentMyInvitationIntegral;
    @BindView(R.id.fragment_my_order)
    TextView fragmentMyOrder;
    @BindView(R.id.fragment_my_shoppingcar)
    TextView fragmentMyShoppingcar;
    Unbinder unbinder;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null) {
            unbinder.unbind();
        }
    }

    @OnClick({R.id.fragment_my_earphone, R.id.fragment_my_set, R.id.fragment_my_img,
            R.id.fragment_my_wallet_rl, R.id.fragment_my_integral_rl, R.id.fragment_my_invitation_integral_rl,
            R.id.fragment_my_order_rl, R.id.fragment_my_shoppingcar_rl, R.id.fragment_my_collect_rl,
            R.id.fragment_my_evaluate_rl, R.id.fragment_my_merchant_services_rl, R.id.fragment_my_nearby_rl,
            R.id.fragment_my_phone, R.id.fragment_my_vip})
    public void onViewClicked(View view) {
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
//                .startActivity(getActivity(),false);
                break;
            case R.id.fragment_my_nearby_rl:
                NearbyActivity.startActivity(getActivity(), false);
                break;
            default:
                break;
        }
    }




}
