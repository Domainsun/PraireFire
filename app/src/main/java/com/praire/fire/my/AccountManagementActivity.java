package com.praire.fire.my;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.praire.fire.R;
import com.praire.fire.SignAcitvity;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.CommonMethod;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.my.bean.CommentResultBean;
import com.praire.fire.my.popwindows.ChoosePhotoPopWindow;
import com.praire.fire.my.setActivitys.AddressActivity;
import com.praire.fire.my.setActivitys.BankCardActivity;
import com.praire.fire.my.setActivitys.ChangeNameActivity;
import com.praire.fire.my.setActivitys.RealVerifyActivity;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.UserHeadBean;
import com.praire.fire.okhttp.JavaBean.UserInfoBean;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.PicassoImageLoader;
import com.praire.fire.utils.SharePreferenceMgr;
import com.praire.fire.utils.TextViewUtils;
import com.praire.fire.utils.ToastUtil;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;

/**
 * 账号管理
 * Created by lyp on 2018/1/2.
 */
public class AccountManagementActivity extends BaseTitleActivity {

    private static final int IMAGE_PICKER = 125;
    private static final int REQUEST_CODE_SELECT = 126;
    @BindView(R.id.account_management_icon)
    CircleImageView accountManagementIcon;
    @BindView(R.id.account_management_order)
    TextView accountManagementName;
    @BindView(R.id.account_management_phone)
    TextView accountManagementPhone;
    @BindView(R.id.account_management_sex)
    TextView accountManagementSex;
    private PicassoImageLoader picassoImageLoader;
    private PopupWindow mPopWindow;
    private String cookie;
    UseAPIs u = new UseAPIs();
    J2O j = new J2O();

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
        //配置图片选择器，一般在Application初始化配置一次就可以,这里就需要将上面的图片加载器设置进来,其余的配置根据需要设置
        ImagePicker imagePicker = ImagePicker.getInstance();
        picassoImageLoader = new PicassoImageLoader();
        imagePicker.setImageLoader(picassoImageLoader);   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.CIRCLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素

    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {
        cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");
        getUserInfo(cookie);
    }

    public void getUserInfo(String cookie) {

        try {


            String str1 = u.getUserHead(cookie);
            UserHeadBean h = j.getUserHead(str1);

            if (!TextUtils.isEmpty(h.getOssheadurl())) {
                Picasso.with(this).load(h.getOssheadurl()).into(accountManagementIcon);
            } else {
                accountManagementIcon.setImageResource(R.mipmap.avatar2);
            }
            String str = u.getUserInfo(cookie);
            UserInfoBean o = j.getUserInfo(str);

            Log.d("getNickname", "getNickname: " + o.getNickname());

            accountManagementName.setText(o.getNickname());

            if (o.getSex().equals("0")) {
                accountManagementSex.setText("男");
            }


            if (o.getSex().equals("1")) {
                accountManagementSex.setText("女");
            }
            accountManagementPhone.setText(o.getTel());


        } catch (Exception e) {


        }


    }

    @Override
    public void initiTile() {
        setDefaultBack();
        setTitleMiddle(getString(R.string.account_management));
    }


    @OnClick({R.id.account_management_icon_rl, R.id.account_management_order_rl,
            R.id.account_management_phone_rl, R.id.account_management_psd_rl,
            R.id.account_management_truth_name, R.id.account_management_bankcard_rl,
            R.id.account_management_address_rl, R.id.btn_exit_login,R.id.account_management_sex_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.account_management_icon_rl:
                choosePhoto();
                break;
            case R.id.account_management_sex_rl:
                showPopupWindow();
                break;
            case R.id.account_management_order_rl:
                ChangeNameActivity.startActivity(this, true);
                break;
            case R.id.account_management_phone_rl:

                break;
            case R.id.account_management_psd_rl:
                PassWordManagementActivity.startActivity(this, false);
                break;
            case R.id.account_management_truth_name:
                RealVerifyActivity.startActivity(this, false);
                break;
            case R.id.account_management_bankcard_rl:
                BankCardActivity.startActivity(this, false);
                break;
            case R.id.account_management_address_rl:
                AddressActivity.startActivity(this, false);
                break;
            case R.id.btn_exit_login:
//                SharePreferenceMgr.remove(this, Constants.USER_ID);
                SharePreferenceMgr.remove(this, Constants.PASSWORD);
                SharePreferenceMgr.remove(this, LOGIN_COOKIE);
                SignAcitvity.startActivity(this, false);
                finish();
                break;
            default:
                break;
        }
    }

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
                    intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true);
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

        if (resultCode == ImagePicker.RESULT_CODE_ITEMS && data != null) {
            if (requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    commitImage(images.get(0).path);
                }
            }
            //添加图片返回
            if (requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    commitImage(images.get(0).path);
                }

            }
        }

    }

    private void commitImage(String images) {
        picassoImageLoader.displayImage(AccountManagementActivity.this, images, accountManagementIcon,
                TextViewUtils.dip2px(AccountManagementActivity.this, 68), TextViewUtils.dip2px(AccountManagementActivity.this, 68));
        CommonMethod commonMethod = new CommonMethod();
        Log.e("images", images);
//        Uri.fromFile(new File(images));
        Uri uri = Uri.parse("file://"+images);// String转化成Uri
        String base64 = "data:image/jpeg;base64," + commonMethod.uriToBase64(uri, this);
        RequestBody requestBody = new FormBody.Builder()
                .add("head", base64)
                .build();
        OkhttpRequestUtil.post(ConstanUrl.UPLOAD_USER_HEAD, requestBody, 1, uiHandler, true);
    }

    @Override
    protected void networkResponse(Message msg) {
        Log.e("msg Image", (String) msg.obj);
        switch (msg.what) {
            case 1:
                Gson gson = new Gson();
                CommentResultBean resultBean = gson.fromJson((String) msg.obj,CommentResultBean.class);
                ToastUtil.show(this,resultBean.getMsg());
                break;
            default:
                break;
        }
    }

    private void showPopupWindow() {

        View contentView = LayoutInflater.from(AccountManagementActivity.this).inflate(R.layout.pop_choose_sex, null);
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
                    str = u.changeUserInfo(cookie, "", "", "", "", "", "0");
                    APIResultBean a = j.getAPIResult(str);
                    Toast.makeText(AccountManagementActivity.this, a.getMsg(), Toast.LENGTH_SHORT).show();
                    if (a.getCode().equals("1")) {
                        accountManagementSex.setText("男");
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
                    str = u.changeUserInfo(cookie, "", "", "", "", "", "1");
                    APIResultBean a = j.getAPIResult(str);
                    Toast.makeText(AccountManagementActivity.this, a.getMsg(), Toast.LENGTH_SHORT).show();
                    if (a.getCode().equals("1")) {
                        accountManagementSex.setText("女");
                    }
                    mPopWindow.dismiss();
                    Log.d("str", "str: " + str);
                } catch (Exception e) {
                }
            }
        });
        //显示PopupWindow

    }

    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgcolor;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);

    }





}
