package com.praire.fire.my;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.MyApplication;
import com.praire.fire.R;
import com.praire.fire.SignAcitvity;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.CommonMethod;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.my.setActivitys.AddressActivity;
import com.praire.fire.my.setActivitys.BankCardActivity;
import com.praire.fire.my.setActivitys.ChangeNameActivity;
import com.praire.fire.my.setActivitys.PasswordMangeActivity;
import com.praire.fire.my.setActivitys.RealVerifyActivity;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.ShopInfoBean;
import com.praire.fire.okhttp.JavaBean.UserHeadBean;
import com.praire.fire.okhttp.JavaBean.UserInfoBean;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;
import static com.praire.fire.common.Constants.REQUEST_CODE_COMMONT;

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
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.rl_phone)
    RelativeLayout rlPhone;
    @BindView(R.id.rl_password_manage)
    RelativeLayout rlPasswordManage;
    @BindView(R.id.rl_real_name_verify)
    RelativeLayout rlRealNameVerify;
    @BindView(R.id.rl_bind_bank_card)
    RelativeLayout rlBindBankCard;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    private PopupWindow mPopWindow;
    UseAPIs u = new UseAPIs();
    J2O j = new J2O();
    String cookie = "";
    String name = "";
    String sex = "";
    String phone = "";


    private WheelView mainWheelView;
    List<String> servicelists = new ArrayList<>();
    CommonMethod commonMethod = new CommonMethod();



    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, SetActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, REQUEST_CODE_COMMONT);
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
        cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");

//        getUserInfo(cookie);

        servicelists.add("男");
        servicelists.add("女");

            OkhttpRequestUtil.get(ConstanUrl.GET_USER_HEAD, 1, true, uiHandler);
            OkhttpRequestUtil.get(ConstanUrl.GET_USER_INFO, 2, true, uiHandler);



    }

    @Override
    protected void networkResponse(Message msg) {
        super.networkResponse(msg);


        switch (msg.what) {
            case 1:

                String str = msg.obj + "";
                if (!str.isEmpty()) {
                    UserHeadBean h = j.getUserHead(str);
                    ivHead.setImageURI(h.getOssheadurl());
                }


                break;
            case 2:
                String str1 = msg.obj + "";
                if (!str1.isEmpty()) {
                    UserInfoBean o = j.getUserInfo(str1);

                    Log.d("getNickname", "getNickname: " + o.getNickname());

                    tvName.setText(o.getNickname());

                    if (o.getSex().equals("0")) {
                        tvSex.setText("男");
                    }


                    if (o.getSex().equals("1")) {
                        tvSex.setText("女");
                    }
                    tvPhone.setText(o.getTel());

                }


                break;
            default:
                break;
        }


    }


//    public void getUserInfo(final String cookie) {
//
//
//        String str1 = u.getUserHead(cookie);
//        UserHeadBean h = j.getUserHead(str1);
//        ivHead.setImageURI(h.getOssheadurl());
//
//
//        String str = u.getUserInfo(cookie);
//        UserInfoBean o = j.getUserInfo(str);
//
//        Log.d("getNickname", "getNickname: " + o.getNickname());
//
//        tvName.setText(o.getNickname());
//
//        if (o.getSex().equals("0")) {
//            tvSex.setText("男");
//        }
//
//
//        if (o.getSex().equals("1")) {
//            tvSex.setText("女");
//        }
//        tvPhone.setText(o.getTel());
//
//
//    }


    @OnClick({R.id.submit, R.id.rl_address, R.id.tv_back, R.id.iv_head, R.id.rl_name, R.id.rl_sex, R.id.rl_phone, R.id.rl_password_manage, R.id.rl_real_name_verify, R.id.rl_bind_bank_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.iv_head:

                showChoosePic(Constants.REQUEST_CHANGE_USER_HEAD);

                break;
            case R.id.rl_name:
                ChangeNameActivity.startActivity(this, true);
                break;
            case R.id.rl_sex:
//                showPopupWindow();

                show1();
                break;
            case R.id.rl_phone:
                break;
            case R.id.rl_password_manage:
                PasswordMangeActivity.startActivity(this, false);
                break;
            case R.id.rl_real_name_verify:
                RealVerifyActivity.startActivity(this, false);

                break;
            case R.id.rl_bind_bank_card:

                BankCardActivity.startActivity(this, false);
                break;

            case R.id.rl_address:

                AddressActivity.startActivity(this, false);
                break;
            case R.id.submit:

                SharePreferenceMgr.put(MyApplication.getInstance(), Constants.LOGIN_COOKIE, "");
                startActivity(new Intent(this, SignAcitvity.class));
                break;
        }
    }


    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgcolor;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);

    }


    private void showPopupWindow() {

        View contentView = LayoutInflater.from(SetActivity.this).inflate(R.layout.pop_choose_sex, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        darkenBackground(0.2f);
        mPopWindow.setContentView(contentView);


        mPopWindow.setFocusable(true);
        mPopWindow.setOutsideTouchable(true); // 设置允许在外点击消失
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());// 设置背景，这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景

//        mPopWindow.showAsDropDown(view); // PopupWindow的显示及位置设置

        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                darkenBackground(1f);
            }
        });

        View rootview = LayoutInflater.from(this).inflate(R.layout.activity_bill, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);


        //设置各个控件的点击响应
        TextView tv_confirm = contentView.findViewById(R.id.tv_confirm);
        TextView tv_male = contentView.findViewById(R.id.tv_male);
        TextView tv_female = contentView.findViewById(R.id.tv_female);


        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });

        tv_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String str = "";
                    str = u.changeUserInfo(cookie, null, null, null, null, null, "0");
                    APIResultBean a = j.getAPIResult(str);
                    Toast.makeText(SetActivity.this, a.getMsg(), Toast.LENGTH_SHORT).show();
                    if (1 == a.getCode()) {
                        tvSex.setText("男");
                    }
                    mPopWindow.dismiss();
                    Log.d("str", "str: " + str);
                } catch (Exception e) {
                }
            }
        });

        tv_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String str = "";
                    str = u.changeUserInfo(cookie, null, null, null, null, null, "1");
                    APIResultBean a = j.getAPIResult(str);
                    Toast.makeText(SetActivity.this, a.getMsg(), Toast.LENGTH_SHORT).show();
                    if (1 == a.getCode()) {
                        tvSex.setText("女");
                    }
                    mPopWindow.dismiss();
                    Log.d("str", "str: " + str);
                } catch (Exception e) {
                }
            }
        });


        //显示PopupWindow


    }


    //滚轮选择性别
    private void show1() {



        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.select_service_type_dialog, null);

        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextSize = 20;
        style.textSize = 16;

        mainWheelView = (WheelView) contentView.findViewById(R.id.main_wheelview);
        mainWheelView.setWheelAdapter(new ArrayWheelAdapter(this));
        mainWheelView.setSkin(WheelView.Skin.Holo);
        mainWheelView.setWheelData(servicelists);
        mainWheelView.setStyle(style);


        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();

        TextView tv_confirm = contentView.findViewById(R.id.tv_ok);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancel);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    String str = "";
                    if (mainWheelView.getSelectionItem().equals("男")) {
                        str = u.changeUserInfo(cookie, null, null, null, null, null, "0");
                    } else {
                        str = u.changeUserInfo(cookie, null, null, null, null, null, "1");
                    }

                    APIResultBean a = j.getAPIResult(str);
                    Toast.makeText(SetActivity.this, a.getMsg(), Toast.LENGTH_SHORT).show();
                    if (1 == a.getCode()) {
                        tvSex.setText(mainWheelView.getSelectionItem() + "");
                    }
                    mPopWindow.dismiss();
                    Log.d("str", "str: " + str);
                } catch (Exception e) {


                }
                bottomDialog.dismiss();
            }
        });


        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog.dismiss();
            }
        });

    }


    private void showChoosePic(int request_code) {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Matisse.from(SetActivity.this)
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
        if (requestCode == Constants.REQUEST_CHANGE_USER_HEAD && resultCode == RESULT_OK) {
              /*照片选择后的回调*/
            List<Uri> mSelected = Matisse.obtainResult(data);


            if (!(mSelected.size() == 0)) {

                try {
                    String base64 = "data:image/jpeg;base64," + commonMethod.uriToBase64(mSelected.get(0), this);

                    String str = u.uploadUserHead(cookie, base64);
                    APIResultBean o = j.getAPIResult(str);
                    Toast.makeText(this, o.getMsg() + "", Toast.LENGTH_SHORT).show();
                    ivHead.setImageURI(mSelected.get(0));


                    Log.d("ivHead", "ivHead: " + base64);
                } catch (Exception e) {


                }


            }
        }


        if (requestCode == REQUEST_CODE_COMMONT && resultCode == RESULT_OK) {


            String name = data.getStringExtra("name");
            Log.d("name", "name: " + name);
            tvName.setText(name + "");

        }


    }


}
