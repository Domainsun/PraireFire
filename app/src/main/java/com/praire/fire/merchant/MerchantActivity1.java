package com.praire.fire.merchant;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.model.LatLng;
import com.bigkoo.pickerview.TimePickerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.CommonMethod;
import com.praire.fire.common.Constants;
import com.praire.fire.merchant.bean.RegionListBean;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.ShopInfoBean;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;
import static com.praire.fire.common.Constants.REQUEST_CODE_CHOOSE_SHOP_TYPE;
import static com.praire.fire.common.Constants.REQUEST_CODE_UPLOAD_BUSINESS_LICENSE;
import static com.praire.fire.common.Constants.REQUEST_CODE_UPLOAD_ID_CARD;
import static com.praire.fire.common.Constants.REQUEST_CODE_UPLOAD_SHOP_PHOTO;

/**
 * Created by sunlo on 2018/1/4.
 */

public class MerchantActivity1 extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_chose_shop_region)
    TextView tvChoseShopRegion;
    @BindView(R.id.tv_chose_shop_type)
    TextView tvChoseShopType;
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
    @BindView(R.id.tv_contacts)
    TextView tvContacts;
    @BindView(R.id.et_Contact_person)
    EditText etContactPerson;
    @BindView(R.id.tv_contact_phone)
    TextView tvContactPhone;
    @BindView(R.id.et_Contact_phone)
    EditText etContactPhone;
    @BindView(R.id.tv_chose_shop_mapregion)
    TextView tvChoseShopMapregion;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et_details_adress)
    EditText etDetailsAdress;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.rl_chose_shop_region)
    RelativeLayout rlChoseShopRegion;
    @BindView(R.id.rl_chose_shop_type)
    RelativeLayout rlChoseShopType;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.rl_chose_shop_mapregion)
    RelativeLayout rlChoseShopMapregion;
    private WheelView mainWheelView, subWheelView, childWheelView;
    CommonMethod commonMethod = new CommonMethod();
    String base64_shop_photo = "";
    String base64_business_license = "";
    String getBase64_identity_card = "";

    String shop_region = "";
    String shop_type = "";
    String shop_name = "";
    String shop_details = "";
    String contacts = "";
    String contact_phone = "";
    String shop_opentime = "";
    String shop_lt = "";
    String shop_lng = "";
    String shop_lat = "";
    String shop_details_address = "";
    String cookie = "";

    List<String> privince = new ArrayList<>();
    List<String> city = new ArrayList<>();
    List<String> countylist = new ArrayList<>();


    HashMap<String, List<String>> cityMap = new HashMap<String, List<String>>();
    HashMap<String, List<String>> countyMap = new HashMap<String, List<String>>();

    Map<String, String> countyMap1 = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);
        ButterKnife.bind(this);


        Bundle b = getIntent().getExtras();
        ShopInfoBean s = new ShopInfoBean();

        try {
            s = (ShopInfoBean) b.getSerializable("shopInfo");
            if (s != null) {
                if (s.getChecked().equals("0")) {
                    tvChoseShopRegion.setText(s.getCity_name());
                    tvChoseShopType.setText(s.getType_name());
                    etShopName.setText(s.getName());
                    etShopDetails.setText(s.getDesc());
                    uploadShopPhoto.setImageURI(s.getDoor());
                    uploadBusinessLicense.setImageURI(s.getLicence());
                    uploadIdCard.setImageURI(s.getIdentify());
                    etContactPerson.setText(s.getContact());
                    etContactPhone.setText(s.getTel());
//                    tvChoseShopOpenTime.setText(s.getOpentime());
                    tvChoseShopMapregion.setText(s.getLat() + "," + s.getLng());
                    tvAddress.setText(s.getAddress());
                    submit.setText("审核中");
                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(MerchantActivity1.this, "审核中，请耐心等待", Toast.LENGTH_SHORT).show();
                        }
                    });
                    submit.setEnabled(false);

                } else if (s.getChecked().equals("2")) {
                    shop_region = s.getCity_id();
                    shop_type = s.getType();
                    shop_name = s.getName();
                    shop_details = s.getDesc();
                    base64_shop_photo = s.getDoor();
                    base64_business_license = s.getLicence();
                    getBase64_identity_card = s.getIdentify();
                    contacts = s.getContact();
                    contact_phone = s.getTel();
                    shop_opentime = s.getOpentime();
                    shop_lat = s.getLat();
                    shop_lng = s.getLng();
                    shop_details_address = s.getAddress();
                    Log.d("getAddress", shop_details_address);

                    tvChoseShopRegion.setText(s.getCity_name());
                    tvChoseShopType.setText(s.getType_name());
                    etShopName.setText(s.getName());
                    etShopDetails.setText(s.getDesc());
                    uploadShopPhoto.setImageURI(s.getDoor());
                    uploadBusinessLicense.setImageURI(s.getLicence());
                    uploadIdCard.setImageURI(s.getIdentify());
                    etContactPerson.setText(s.getContact());
                    etContactPhone.setText(s.getTel());
//                    tvChoseShopOpenTime.setText(s.getOpentime());
                    tvChoseShopMapregion.setText(s.getLat() + "," + s.getLng());
                    etDetailsAdress.setText(s.getAddress());
                    submit.setText("重新提交");

                    Toast.makeText(this, "您的申请被拒绝，原因:" + s.getCheck_desc(), Toast.LENGTH_SHORT).show();

                }
            }

        } catch (Exception e) {
            Log.e("onCreate", "onCreate: " + e.toString());

        }

        initWheelViewdata();

    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_merchant;
    }

    @Override
    protected void initViews() {

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


    @OnClick({R.id.rl_chose_shop_region, R.id.rl_chose_shop_type, R.id.tv_start_time, R.id.tv_end_time, R.id.rl_chose_shop_mapregion,R.id.tv_back, R.id.tv_chose_shop_region, R.id.tv_chose_shop_type, R.id.tv_shop_name, R.id.et_shop_name, R.id.et_shop_details, R.id.upload_shop_photo, R.id.upload_business_license, R.id.upload_id_card, R.id.et_Contact_person, R.id.et_Contact_phone, R.id.tv_chose_shop_mapregion, R.id.et_details_adress, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.tv_chose_shop_region:

                break;
            case R.id.tv_chose_shop_type:

                break;


            case R.id.upload_shop_photo:
                showChoosePic(REQUEST_CODE_UPLOAD_SHOP_PHOTO);
                break;
            case R.id.upload_business_license:
                showChoosePic(REQUEST_CODE_UPLOAD_BUSINESS_LICENSE);
                break;
            case R.id.upload_id_card:
                showChoosePic(REQUEST_CODE_UPLOAD_ID_CARD);
                break;
//            case R.id.tv_chose_shop_open_time:
//                choseTime();
//                break;
            case R.id.tv_chose_shop_mapregion:

                break;



            case R.id.rl_chose_shop_region:
                show1();
                break;
            case R.id.rl_chose_shop_type:
                Intent i = new Intent(this, SettledActivity.class);
                Bundle bundle = new Bundle();
//                bundle.putString("typeId",shop_type);
                i.putExtras(bundle);
                startActivityForResult(i, REQUEST_CODE_CHOOSE_SHOP_TYPE);
                break;
            case R.id.tv_start_time:
                choseTime("0");
                break;
            case R.id.tv_end_time:
                choseTime("1");
                break;
            case R.id.rl_chose_shop_mapregion:
                MapChooseActivity.startActivity(this, true);
                break;


            case R.id.submit:

                shop_name = etShopName.getText().toString();
                shop_details = etShopDetails.getText().toString();
                contacts = etContactPerson.getText().toString();
                contact_phone = etContactPhone.getText().toString();


                shop_details_address = etDetailsAdress.getText().toString();
                cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");


                String starttime="";
                starttime= tvStartTime.getText().toString();
                String endtime="";
                endtime=tvEndTime.getText().toString();


                if (starttime.length()!=0 && endtime.length()!=0) {
                    shop_opentime=starttime+"-"+endtime;
                }


//                Toast.makeText(this, shop_details_address, Toast.LENGTH_SHORT).show();
                if (shop_region.length() == 0 || shop_type.length() == 0 || shop_name.length() == 0 || shop_details.length() == 0 || base64_shop_photo.length() == 0 || base64_business_license.length() == 0 || getBase64_identity_card.length() == 0 || contacts.length() == 0 || contact_phone.length() == 0 || shop_opentime.length() == 0 || shop_opentime.length() < 8 || shop_lat.length() == 0 || shop_lng.length() == 0 || shop_details_address.length() == 0) {
                    Toast.makeText(this, "请将信息填写完整", Toast.LENGTH_SHORT).show();
                } else {
                    String s = "";
                    s = new UseAPIs().shopSettled(shop_type, shop_name, base64_shop_photo, base64_business_license, contacts, contact_phone, shop_opentime, shop_details_address, shop_lng, shop_lat, shop_region, shop_details, getBase64_identity_card, cookie);

                    if (s.length() != 0) {
                        APIResultBean a = new J2O().getAPIResult(s);
                        Toast.makeText(MerchantActivity1.this, a.getMsg() + "", Toast.LENGTH_SHORT).show();

                        if (a.getCode().equals("1")) {
                            submit.setText("审核中");
                            submit.setEnabled(false);
                        }
                    } else {
                        Toast.makeText(this, "网络错误！", Toast.LENGTH_SHORT).show();
                    }

                }


                break;
        }
    }


    boolean choseTime = false;
    String ftime = "";

    public void choseTime(final String type) {
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String str = sdf.format(date);

                if (type.equals("0")) {

                    tvStartTime.setText(str+"  -");

                } else if(type.equals("1")){
                    tvEndTime.setText(str);
                }


            }
        })
                .setType(new boolean[]{false, false, false, true, true, false})
                .build();
        pvTime.show();

    }


    private void showChoosePic(int request_code) {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Matisse.from(MerchantActivity1.this)
                    .choose(MimeType.allOf()) // 选择 mime 的类型
                    .countable(true)
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
        //依据返回值判断是否调用
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_CODE_CHOOSE_SHOP_TYPE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String typeText = bundle.getString("typeText");
            String typeId = bundle.getString("typeId");
            shop_type = typeId;
            tvChoseShopType.setText(typeText);
        }

        /*地图返回值*/
        if (requestCode == Constants.REQUEST_CODE_CHOOSE_MAP_ADDRESS && resultCode == RESULT_OK) {
            LatLng latLon;

            latLon = data.getParcelableExtra(Constants.LATLNG);
            shop_lng = latLon.longitude + "";
            shop_lat = latLon.latitude + "";

            tvChoseShopMapregion.setText(shop_lng + "," + shop_lat);


            Log.d("onActivityResult", "onActivityResult: " + latLon.latitude + "   " + latLon.longitude);


        }




        /*选择照片回调*/
        Uri uri;
        if (requestCode == REQUEST_CODE_UPLOAD_SHOP_PHOTO && resultCode == RESULT_OK) {
              /*照片选择后的回调*/
            List<Uri> mSelected = Matisse.obtainResult(data);
            if (!(mSelected.size() == 0)) {
                uri = mSelected.get(0);
                uploadShopPhoto.setImageURI(uri);
                base64_shop_photo = "data:image/jpeg;base64," + commonMethod.uriToBase64(uri, this);
                Log.d("base64_shop_photo", base64_shop_photo);
            }
        }


        if (requestCode == REQUEST_CODE_UPLOAD_BUSINESS_LICENSE && resultCode == RESULT_OK) {
              /*照片选择后的回调*/
            List<Uri> mSelected = Matisse.obtainResult(data);
            if (!(mSelected.size() == 0)) {
                uri = mSelected.get(0);
                uploadBusinessLicense.setImageURI(uri);
                base64_business_license = "data:image/jpeg;base64," + commonMethod.uriToBase64(uri, this);
                Log.d("base64_shop_photo", base64_business_license);
            }
        }

        if (requestCode == REQUEST_CODE_UPLOAD_ID_CARD && resultCode == RESULT_OK) {
              /*照片选择后的回调*/
            List<Uri> mSelected = Matisse.obtainResult(data);
            if (!(mSelected.size() == 0)) {
                uri = mSelected.get(0);
                uploadIdCard.setImageURI(uri);
                getBase64_identity_card = "data:image/jpeg;base64," + commonMethod.uriToBase64(uri, this);
                Log.d("getBase64_identity_card", getBase64_identity_card);
            }
        }
    }
/*-------------------------权限申请的回调-------------------------------*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**/
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "获取选择照片权限失败", Toast.LENGTH_SHORT).show();
    }

   /* -----------------------------------*//**/

    private void show1() {
        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.select_region_dialog, null);

        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextSize = 20;
        style.textSize = 16;

        mainWheelView = (WheelView) contentView.findViewById(R.id.main_wheelview);
        mainWheelView.setWheelAdapter(new ArrayWheelAdapter(this));
        mainWheelView.setSkin(WheelView.Skin.Holo);
        mainWheelView.setWheelData(privince);
        mainWheelView.setStyle(style);

        subWheelView = (WheelView) contentView.findViewById(R.id.sub_wheelview);
        subWheelView.setWheelAdapter(new ArrayWheelAdapter(this));
        subWheelView.setSkin(WheelView.Skin.Holo);
        subWheelView.setWheelData(cityMap.get(privince.get(mainWheelView.getSelection())));
        subWheelView.setStyle(style);
        mainWheelView.join(subWheelView);
        mainWheelView.joinDatas(cityMap);

        childWheelView = (WheelView) contentView.findViewById(R.id.child_wheelview);
        childWheelView.setWheelAdapter(new ArrayWheelAdapter(this));
        childWheelView.setSkin(WheelView.Skin.Holo);
        childWheelView.setWheelData(countyMap.get(cityMap.get(privince.get(mainWheelView.getSelection())).get(subWheelView.getSelection())));
        childWheelView.setStyle(style);
        subWheelView.join(childWheelView);
        subWheelView.joinDatas(countyMap);


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
                tvChoseShopRegion.setText(mainWheelView.getSelectionItem() + " " + subWheelView.getSelectionItem() + " " + childWheelView.getSelectionItem());
                shop_region = countyMap1.get(childWheelView.getSelectionItem());
                Toast.makeText(MerchantActivity1.this, shop_region, Toast.LENGTH_SHORT).show();
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

    private void initWheelViewdata() {

        String str = "";
        str = new UseAPIs().getRegion();

        if (str.length() != 0) {
            RegionListBean s = new J2O().getRegion(str);
            for (int i = 0; i < s.getList().size(); i++) {
                String privinceName = s.getList().get(i).getName();
                privince.add(privinceName);
                List<String> citylist = new ArrayList<>();
                for (int j = 0; j < s.getList().get(i).getSon().size(); j++) {
                    String cityName = s.getList().get(i).getSon().get(j).getName();
                    citylist.add(cityName);
                    city.add(cityName);
                    System.out.println(citylist);

                    List<String> countylist = new ArrayList<>();
                    for (int k = 0; k < s.getList().get(i).getSon().get(j).getSon().size(); k++) {
                        String countyCode = s.getList().get(i).getSon().get(j).getSon().get(k).getId();
                        String countyName = s.getList().get(i).getSon().get(j).getSon().get(k).getName();
                        countylist.add(countyName);
                        countyMap1.put(countyName, countyCode);
                    }
                    countyMap.put(cityName, countylist);

                }
                cityMap.put(privinceName, citylist);
            }
        } else {
            Toast.makeText(this, "网络错误！", Toast.LENGTH_SHORT).show();
        }


    }





}
