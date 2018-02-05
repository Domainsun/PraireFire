package com.praire.fire.my;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
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
     /*   //配置图片选择器，一般在Application初始化配置一次就可以,这里就需要将上面的图片加载器设置进来,其余的配置根据需要设置
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new PicassoImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素*/

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
/*
    private void choosePhoto() {
        //点击修改商家图片
         ChoosePhotoPopWindow poop = new ChoosePhotoPopWindow(this);
        poop.showAtLocation(getTitleBar(), Gravity.BOTTOM, 0, 0);
        poop.setOnItemClickListener(new ChoosePhotoPopWindow.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position == 1) {
                    Intent intent = new Intent(AccountManagementActivity.this, ImageGridActivity.class);
                    // 是否是直接打开相机
                    intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS,true);
                    startActivityForResult(intent, REQUEST_CODE_SELECT);

                } else if (position == 2) {
                    Intent intent = new Intent(AccountManagementActivity.this, ImageGridActivity.class);
                    startActivityForResult(intent, IMAGE_PICKER);
                }
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        //拍照功能或者裁剪后返回
        if (resultCode == RESULT_OK  ) {
            if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
                if (data != null && requestCode == IMAGE_PICKER) {
                    ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);

                } else {
                    Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }*/


}
