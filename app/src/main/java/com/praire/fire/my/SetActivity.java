package com.praire.fire.my;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.CommonMethod;
import com.praire.fire.common.Constants;
import com.praire.fire.merchant.AddProductActivity;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

import static com.praire.fire.common.Constants.REQUEST_CODE_UPLOAD_PRODUCT1;

/**
 * 设置
 * Created by lyp on 2018/1/2.
 */

public class SetActivity extends BaseActivity {
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_head)
    SimpleDraweeView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_password_manage)
    TextView tvPasswordManage;
    @BindView(R.id.tv_real_name_verify)
    TextView tvRealNameVerify;
    @BindView(R.id.tv_bind_bank_card)
    TextView tvBindBankCard;
    @BindView(R.id.submit)
    Button submit;



    CommonMethod commonMethod=new CommonMethod();

    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, SetActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_set;
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



    @OnClick({R.id.tv_back, R.id.iv_head, R.id.tv_name, R.id.tv_sex, R.id.tv_phone, R.id.tv_password_manage, R.id.tv_real_name_verify, R.id.tv_bind_bank_card, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.iv_head:

                showChoosePic(Constants.REQUEST_CHANGE_USER_HEAD);

                break;
            case R.id.tv_name:
                break;
            case R.id.tv_sex:
                break;
            case R.id.tv_phone:
                break;
            case R.id.tv_password_manage:
                break;
            case R.id.tv_real_name_verify:
                break;
            case R.id.tv_bind_bank_card:
                break;
            case R.id.submit:
                break;
        }
    }

    private void showChoosePic(int request_code) {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Matisse.from(SetActivity.this)
                    .choose(MimeType.allOf()) // 选择 mime 的类型
                    .countable(true)
                    .capture(true)
                    .captureStrategy(
                            new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider"))
                    .maxSelectable(1) // 图片选择的最多数量
                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                    .restrictOrientation(android.content.pm.ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .thumbnailScale(0.85f) // 缩略图的比例
                    .imageEngine(new PicassoEngine()) // 使用的图片加载引擎
                    .forResult(request_code); // 设置作为标记的请求码
        } else {
            EasyPermissions.requestPermissions(this, "打开相册需要获取权限",
                    0, perms);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*选择照片回调*/
        if (requestCode == Constants.REQUEST_CHANGE_USER_HEAD && resultCode == RESULT_OK) {
              /*照片选择后的回调*/
            List<Uri> mSelected = Matisse.obtainResult(data);


            if (!(mSelected.size() == 0)) {

                String base64 = "data:image/jpeg;base64," + commonMethod.uriToBase64(mSelected.get(0), this);

                ivHead.setImageURI(mSelected.get(0));
                Log.d("ivHead", "ivHead: "+base64);
            }
        }
    }


}
