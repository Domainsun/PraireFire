package com.praire.fire.my;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.MyApplication;
import com.praire.fire.R;
import com.praire.fire.SignAcitvity;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.merchant.AddProductActivity;
import com.praire.fire.my.popwindows.ChoosePhotoPopWindow;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;

/**
 * 账号管理
 * Created by lyp on 2018/1/2.
 */
public class AccountManagementActivity extends BaseTitleActivity {

    @BindView(R.id.account_management_icon)
    SimpleDraweeView accountManagementIcon;
    @BindView(R.id.account_management_order)
    TextView accountManagementOrder;
    @BindView(R.id.account_management_phone)
    TextView accountManagementPhone;

    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, AccountManagementActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_my_account_management;
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

    }


    @Override
    public void initiTile() {
        setDefaultBack();
        setTitleMiddle(getString(R.string.account_management));
    }


    @OnClick({R.id.account_management_icon_rl, R.id.account_management_order_rl,
            R.id.account_management_phone_rl, R.id.account_management_psd_rl,
            R.id.account_management_truth_name, R.id.account_management_bankcard_rl,
            R.id.account_management_address_rl,R.id.btn_exit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.account_management_icon_rl:
//                choosePhoto();

                break;
            case R.id.account_management_order_rl:
//                PassWordManagementActivity.startActivity(this,false);
                break;
            case R.id.account_management_phone_rl:
//                PassWordManagementActivity.startActivity(this,false);
                break;
            case R.id.account_management_psd_rl:
                PassWordManagementActivity.startActivity(this, false);
                break;
            case R.id.account_management_truth_name:
                CertificationActivity.startActivity(this, false);
                break;
            case R.id.account_management_bankcard_rl:
                BankCardActivity.startActivity(this, false);
                break;
            case R.id.account_management_address_rl:
                ShippingAddressActivity.startActivity(this, false);
                break;
            case R.id.btn_exit_login:
                SignAcitvity.startActivity(this,false);
                finish();
                break;
            default:
                break;
        }
    }

    private void choosePhoto() {
        //点击修改商家图片
        /*ChoosePhotoPopWindow poop = new ChoosePhotoPopWindow(this);
        poop.showAtLocation(getTitleBar(), Gravity.BOTTOM, 0, 0);
        poop.setOnItemClickListener(new ChoosePhotoPopWindow.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position == 1) {
                    PhotoPicker.builder()
                            .setPhotoCount(1)
                            .setShowCamera(true)
                            .setShowGif(true)
                            .setPreviewEnabled(false)
                            .start(AccountManagementActivity.this, PhotoPicker.REQUEST_CODE);

                } else if (position == 2) {
                    PhotoPicker.builder()
                            //设置图片选择数量
                            .setPhotoCount(1)
                            //取消选择时点击图片浏览
                            .setPreviewEnabled(false)
                            //开启裁剪
                            .setCrop(true)
                            //设置裁剪比例(X,Y)
                            .setCropXY(13, 10)
                            //设置裁剪界面标题栏颜色，设置裁剪界面状态栏颜色
                            .setCropColors(R.color.orange, R.color.orange)
                            .start(AccountManagementActivity.this);
                }
            }
        });*/
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        //拍照功能或者裁剪后返回
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
//            Uri uri = Uri.fromFile(new File(data.getStringExtra(PhotoPicker.KEY_SELECTED_PHOTOS)));
            /*Bitmap bitmap = SystemPhotoUtil.decodeUriBitmap(uri, AccountManagementActivity.this);
            if (bitmap != null) {//这里必须判空
                imagedatas = SystemPhotoUtil.Bitmap2StrByBase64(bitmap);
                Command_UpdateImgByBase64 request = new Command_UpdateImgByBase64(imagedatas, "jpg");//（jpg、bmp）
                SoapNetworkRequest.sendReuqest(request, networkHanlder, 0);
            }*/
        }
    }

  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
        }
    }*/
}
