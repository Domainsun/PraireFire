package com.praire.fire.merchant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.ShopInfoBean;
import com.praire.fire.okhttp.OkhttpRequestUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopInfoActivity extends BaseActivity {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.tv_chose_shop_region)
    TextView tvChoseShopRegion;
    @BindView(R.id.rl_chose_shop_region)
    RelativeLayout rlChoseShopRegion;
    @BindView(R.id.tv_chose_shop_type)
    TextView tvChoseShopType;
    @BindView(R.id.rl_chose_shop_type)
    RelativeLayout rlChoseShopType;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.et_shop_name)
    EditText etShopName;
    @BindView(R.id.et_shop_details)
    EditText etShopDetails;
    @BindView(R.id.upload_shop_photo)
    SimpleDraweeView uploadShopPhoto;
    @BindView(R.id.upload_business_license)
    SimpleDraweeView uploadBusinessLicense;
    @BindView(R.id.upload_id_card)
    SimpleDraweeView uploadIdCard;
    @BindView(R.id.tv_chose_shop_mapregion)
    TextView tvChoseShopMapregion;
    @BindView(R.id.rl_chose_shop_mapregion)
    RelativeLayout rlChoseShopMapregion;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et_details_adress)
    EditText etDetailsAdress;
    @BindView(R.id.submit)
    Button submit;

    J2O j=new J2O();


    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, ShopInfoActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_shop_info2;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {
        if (hasLogin()) {
            OkhttpRequestUtil.get(ConstanUrl.GET_SHOP_INFO, 1, true, uiHandler);
        }
    }

    @Override
    protected void networkResponse(Message msg) {
        super.networkResponse(msg);


        switch (msg.what) {
            case 1:

                try {
                    String str=msg.obj+"";
                    ShopInfoBean o=j.getShopInfo(str);
                   tvChoseShopRegion.setText(o.getCity_name());
                   tvChoseShopType.setText(o.getType_name());
                   etShopName.setText(o.getName());
                   etShopDetails.setText(o.getDesc());
                   uploadBusinessLicense.setImageURI(o.getLicence());
                   uploadIdCard.setImageURI(o.getIdentify());
                   uploadShopPhoto.setImageURI(o.getDoor());
                   tvChoseShopMapregion.setText(o.getLng()+","+o.getLat());
                   etDetailsAdress.setText(o.getAddress());

                } catch (Exception e) {

                    Log.e("networkResponseeeee", "networkResponseeeee: "+e.toString() );
                }


                break;
            default:
                break;
        }


    }

    @OnClick({R.id.tv_back, R.id.rl_chose_shop_region, R.id.rl_chose_shop_type, R.id.upload_shop_photo, R.id.upload_business_license, R.id.upload_id_card, R.id.rl_chose_shop_mapregion, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.rl_chose_shop_region:
                break;
            case R.id.rl_chose_shop_type:
                break;
            case R.id.upload_shop_photo:
                break;
            case R.id.upload_business_license:
                break;
            case R.id.upload_id_card:
                break;
            case R.id.rl_chose_shop_mapregion:
                break;
            case R.id.submit:
                break;
        }
    }
}
