package com.praire.fire.my.setActivitys;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.CommonMethod;
import com.praire.fire.common.Constants;
import com.praire.fire.my.SetActivity;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.RealVerifyBean;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;
import static com.praire.fire.common.Constants.REQUEST_REAL_VERIFY_PHTOTO;

public class RealVerifyActivity extends BaseActivity {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_inum)
    EditText etInum;
    @BindView(R.id.upload_id_card)
    SimpleDraweeView uploadIdCard;
    @BindView(R.id.submit)
    Button submit;


    UseAPIs u = new UseAPIs();
    J2O j = new J2O();

    String cookie = "";
    String name = "";
    String inum = "";
    String iphoto = "";
    @BindView(R.id.rl_submit)
    LinearLayout rlSubmit;

    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, RealVerifyActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_real_verify;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");

        try {
            String str = u.getRealVerifyInfo(cookie);
            RealVerifyBean o = j.getRealVerifyInfo(str);

            String name = o.getTruename();
            String inum = o.getIdnum();
            String iphoto = o.getOssfrontpic();
            String status = o.getStatus();


            if (status.equals("0")) {
                etName.setText(name);
                etInum.setText(inum);
                uploadIdCard.setImageURI(iphoto);
                submit.setText("审核中");
                submit.setEnabled(false);
                etInum.setEnabled(false);
                etName.setEnabled(false);
                uploadIdCard.setEnabled(false);
            } else if (status.equals("1")) {
                etName.setText(name);
                etInum.setText(inum);
                uploadIdCard.setImageURI(iphoto);
//                rlSubmit.setVisibility(View.INVISIBLE);
                etInum.setEnabled(false);
                etName.setEnabled(false);
                uploadIdCard.setEnabled(false);
                submit.setText("已认证");
            } else if (status.equals("2")) {
                submit.setText("信息有误，请重新提交");
            }


        } catch (Exception e) {

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


    @OnClick({R.id.tv_back, R.id.upload_id_card, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.upload_id_card:


                showChoosePic(REQUEST_REAL_VERIFY_PHTOTO);


                break;
            case R.id.submit:

                name = etName.getText().toString();
                inum = etInum.getText().toString();
                if (name.length() != 0 && inum.length() != 0 && iphoto.length() != 0) {
                    try {
                        String str = u.userRealVerity(inum, name, iphoto, cookie);
                        APIResultBean o = j.getAPIResult(str);
                        Toast.makeText(this, o.getMsg() + "", Toast.LENGTH_SHORT).show();

                        Log.d("str", "str: "+str);

                        if (1==o.getCode()) {

                            Log.d("startAcitivty", "onViewClicked-----------------startAcitivty: ");
                            SetActivity.startActivity(this,false);
                        }
                    } catch (Exception e) {
                        Log.e("onViewClicked", "onViewClicked: " + e.toString());

                    }

                } else {
                    Toast.makeText(this, "请把信息填写完整！", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private void showChoosePic(int request_code) {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Matisse.from(RealVerifyActivity.this)
                    .choose(MimeType.allOf()) // 选择 mime 的类型
                    .countable(true)
                    .capture(true)
                    .captureStrategy(
                            new CaptureStrategy(true, "com.praire.fire.MyFileProvider"))
                    .maxSelectable(1) // 图片选择的最多数量
                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
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
        if (requestCode == REQUEST_REAL_VERIFY_PHTOTO && resultCode == RESULT_OK) {
              /*照片选择后的回调*/
            List<Uri> mSelected = Matisse.obtainResult(data);


            if (!(mSelected.size() == 0)) {


                iphoto = "data:image/jpeg;base64," + new CommonMethod().uriToBase64(mSelected.get(0), this);
                Log.d("iphoto", "iphoto: " + iphoto);
                uploadIdCard.setImageURI(mSelected.get(0));


            }
        }
    }


}
