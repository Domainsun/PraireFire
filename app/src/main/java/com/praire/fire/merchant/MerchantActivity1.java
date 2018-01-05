package com.praire.fire.merchant;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.praire.fire.R;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

import static com.praire.fire.common.Constants.REQUEST_CODE_UPLOAD_SHOP_PHOTO;

/**
 * Created by sunlo on 2018/1/4.
 */

public class MerchantActivity1 extends Activity implements EasyPermissions.PermissionCallbacks {
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
    @BindView(R.id.tv_chose_shop_open_time)
    TextView tvChoseShopOpenTime;
    @BindView(R.id.tv_chose_shop_mapregion)
    TextView tvChoseShopMapregion;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et_details_adress)
    EditText etDetailsAdress;
    @BindView(R.id.submit)
    Button submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.tv_back, R.id.tv_chose_shop_region, R.id.tv_chose_shop_type, R.id.tv_shop_name, R.id.et_shop_name, R.id.et_shop_details, R.id.upload_shop_photo, R.id.upload_business_license, R.id.upload_id_card, R.id.et_Contact_person, R.id.et_Contact_phone, R.id.tv_chose_shop_open_time, R.id.tv_chose_shop_mapregion, R.id.et_details_adress, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                break;
            case R.id.tv_chose_shop_region:
                choseShopRegion();
                break;
            case R.id.tv_chose_shop_type:
                break;
            case R.id.tv_shop_name:
                break;
            case R.id.et_shop_name:
                break;
            case R.id.et_shop_details:
                break;
            case R.id.upload_shop_photo:
                showChoosePic();
                break;
            case R.id.upload_business_license:
                break;
            case R.id.upload_id_card:
                break;
            case R.id.et_Contact_person:
                break;
            case R.id.et_Contact_phone:
                break;
            case R.id.tv_chose_shop_open_time:
                choseTime();
                break;
            case R.id.tv_chose_shop_mapregion:
                break;
            case R.id.et_details_adress:
                break;
            case R.id.submit:
                break;
        }
    }

    public void choseShopRegion() {
        CityPicker cityPicker = new CityPicker.Builder(MerchantActivity1.this).textSize(20)
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .province("江苏省")
                .city("常州市")
                .district("天宁区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .build();

        cityPicker.show();
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... strings) {
                tvChoseShopRegion.setText(strings[1] + " " + strings[2]);
            }

            @Override
            public void onCancel() {

            }
        });
    }

    boolean choseTime=false;
    String ftime="";
    public void choseTime() {


            TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {//选中事件回调
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    String str = sdf.format(date);
                    if (!choseTime) {
                        tvChoseShopOpenTime.setText(str + "-");
                        ftime = str;
                        choseTime=true;
                    } else {
                        tvChoseShopOpenTime.setText(ftime + "-"+str);
                        choseTime=false;
                    }
                }
            })
                    .setType(new boolean[]{false, false, false, true, true, false})
                    .build();
//            pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
            pvTime.show();

        }







    private void showChoosePic() {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Matisse.from(MerchantActivity1.this)
                    .choose(MimeType.allOf()) // 选择 mime 的类型
                    .countable(true)
                    .maxSelectable(1) // 图片选择的最多数量
                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                    .restrictOrientation(android.content.pm.ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .thumbnailScale(0.85f) // 缩略图的比例
                    .imageEngine(new PicassoEngine()) // 使用的图片加载引擎
                    .forResult(REQUEST_CODE_UPLOAD_SHOP_PHOTO); // 设置作为标记的请求码
        } else {
            EasyPermissions.requestPermissions(this, "打开相册需要获取权限",
                    0, perms);
        }
        //依据返回值判断是否调用
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_UPLOAD_SHOP_PHOTO && resultCode == RESULT_OK) {
              /*照片选择后的回调*/
            List<Uri>  mSelected = Matisse.obtainResult(data);
            if (!(mSelected.size()==0)) {
                uploadShopPhoto.setImageURI(mSelected.get(0));
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
}
