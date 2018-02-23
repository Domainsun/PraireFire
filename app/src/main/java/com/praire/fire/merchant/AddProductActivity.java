package com.praire.fire.merchant;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.CommonMethod;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.AddProductResultBean;
import com.praire.fire.okhttp.JavaBean.ProductInfoBean;
import com.praire.fire.okhttp.JavaBean.ProductListBean;
import com.praire.fire.okhttp.JavaBean.ProductTypeBean;
import com.praire.fire.okhttp.JavaBean.ServiceTypeBean;
import com.praire.fire.okhttp.NetworkHandler;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;
import static com.praire.fire.common.Constants.REQUEST_CODE_UPLOAD_PRODUCT1;
import static com.praire.fire.common.Constants.REQUEST_CODE_UPLOAD_PRODUCT2;
import static com.praire.fire.common.Constants.REQUEST_CODE_UPLOAD_PRODUCT3;
import static com.praire.fire.common.Constants.REQUEST_CODE_UPLOAD_PRODUCT4;

public class AddProductActivity extends BaseActivity {
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.et_product_name)
    EditText etProductName;
    @BindView(R.id.tv_product_type)
    TextView tvProductType;
    @BindView(R.id.tv_show_product_type)
    TextView tvShowProductType;
    @BindView(R.id.tv_choose_product_type)
    TextView tvChooseProductType;
    @BindView(R.id.rl_product_type)
    RelativeLayout rlProductType;
    @BindView(R.id.tv_procut_photo)
    TextView tvProcutPhoto;
    @BindView(R.id.load_photo1)
    SimpleDraweeView loadPhoto1;
    @BindView(R.id.load_photo2)
    SimpleDraweeView loadPhoto2;
    @BindView(R.id.load_photo3)
    SimpleDraweeView loadPhoto3;
    @BindView(R.id.load_photo4)
    SimpleDraweeView loadPhoto4;
    @BindView(R.id.tv_product_price)
    TextView tvProductPrice;
    @BindView(R.id.et_product_price)
    EditText etProductPrice;
    //    @BindView(R.id.tv_product_count)
//    TextView tvProductCount;
//    @BindView(R.id.et_product_count)
//    EditText etProductCount;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et_product_introduction)
    EditText etProductIntroduction;
    @BindView(R.id.submit)
    Button submit;
    private WheelView mainWheelView, subWheelView;

    List<String> mainList = new ArrayList<>();
    List<String> subList = new ArrayList<>();
    HashMap<String, List<String>> map = new HashMap<String, List<String>>();
    Map<String, String> subMap = new HashMap<>();

    CommonMethod commonMethod = new CommonMethod();

    String product_name = "";

    String product_type = "";
    String base64_photo1 = "";
    String base64_photo2 = "";
    String base64_photo3 = "";
    String base64_photo4 = "";
    List<String> pic = new ArrayList<>();

    int[] photos;

    Boolean b = false;

    String product_price = "";
    //    String product_count="";
    String product_introduct = "";

    String cookie;
    J2O j = new J2O();


    Map<String, String> typeMap = new HashMap<>();
    ProductListBean.PagelistBean s = new ProductListBean.PagelistBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);

        initdata();
        final String productId = getIntent().getStringExtra("data");

        Log.d("productId", "productId: " + productId);
        if (productId != null) {
            b = true;
            String str = "";
            str = new UseAPIs().getProductInfo(productId, cookie);

            if (str.length() != 0) {


                ProductInfoBean p = new J2O().getProductInfo(str);
                product_name = p.getInfo().getName();
                product_type = p.getInfo().getClassX();
                Log.d("getClassX", "getClassX: " + p.getInfo().getClassX());
                product_price = p.getInfo().getNprice();
                product_introduct = p.getInfo().getDesc();
                etProductName.setText(product_name);
                tvShowProductType.setText(p.getInfo().getClass_name());
                System.out.println("类别:" + p.getInfo().getClass_name());
//                Toast.makeText(context, "类别:"+p.getInfo().getClass_name(), Toast.LENGTH_SHORT).show();
                etProductPrice.setText(product_price);
                etProductIntroduction.setText(product_introduct);


                try {
                    for (int i = 0; i < p.getInfo().getOsspiclist().size(); i++) {
                        String oss = p.getInfo().getOsspiclist().get(i);
                        pic.add(oss);
                        Log.d("oss", "onCreate: " + oss + "i=" + i);
                        SimpleDraweeView iv = findViewById(photos[i]);

                        if (!TextUtils.isEmpty(p.getInfo().getOsspiclist().get(i))) {
                            iv.setImageURI(p.getInfo().getOsspiclist().get(i));
                        }
                        if (i == 0) {
                            base64_photo1 = oss;
                            Log.d("onCreate1", "onCreate1: ");
                        }
                        if (i == 1) {
                            base64_photo2 = oss;
                            Log.d("onCreate2", "onCreate2: ");

                        }
                        if (i == 2) {
                            base64_photo3 = oss;
                            Log.d("onCreate3", "onCreate3: ");

                        }
                        if (i == 3) {
                            base64_photo4 = oss;
                            Log.d("onCreate4", "onCreate4: ");

                        }
                    }


                } catch (Exception e) {
                    Log.e("onCreate", "onCreate: " + e.toString());

                }

        } else {
            Toast.makeText(this, "网络错误！", Toast.LENGTH_SHORT).show();
        }


        submit.setText("修改");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = productId;
                getData();

                Log.d("onClick", "onClick: " + product_name.length() + "  " + product_type.length() + "  " + product_price.length() + "  " + product_introduct.length() + "  " + pic.size());

                if (product_name.length() == 0 || product_type.length() == 0 || product_price.length() == 0 || product_introduct.length() == 0 || pic.size()<1) {
                    Toast.makeText(AddProductActivity.this, "请把信息填写完整！", Toast.LENGTH_SHORT).show();
                } else {
                    String str = "";
                    str = new UseAPIs().changeProductInfo(id, product_name, product_type, product_introduct, product_price, cookie, base64_photo1, base64_photo2, base64_photo3, base64_photo4);


                    Log.d("photos", "photos: " + base64_photo1 + "\n" + base64_photo2 + "\n" + base64_photo3 + "\n" + base64_photo4 + "\n");


                    if (str.length() != 0) {
                        Log.d("str", "str" + str);
                        APIResultBean a = new J2O().getAPIResult(str);
                        if (a.getCode() == 1) {
                            AddProductActivity.this.finish();
                        }

                        Toast.makeText(AddProductActivity.this, a.getMsg() + "", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddProductActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
//
    }


}

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_add_product;
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

    private void getData() {

        product_name = etProductName.getText().toString();
        product_price = etProductPrice.getText().toString();
        product_introduct = etProductIntroduction.getText().toString();

    }


    @Override
    protected void networkResponse(Message msg) {
        super.networkResponse(msg);
        switch (msg.what) {
            case 1:
                String str = msg.obj + "";
                if (!str.isEmpty()) {
                    ProductTypeBean s = new J2O().getProductType(str);

                    for (int i = 0; i < s.getList().size(); i++) {
                        String mainName = s.getList().get(i).getName();
                        mainList.add(mainName);
                        List<String> cSublist = new ArrayList<>();
                        for (int j = 0; j < s.getList().get(i).getSon().size(); j++) {
                            String subName = s.getList().get(i).getSon().get(j).getName();
                            String subCode = s.getList().get(i).getSon().get(j).getId();
                            cSublist.add(subName);
                            subList.add(subName);
                            subMap.put(subName, subCode);

                        }
                        map.put(mainName, cSublist);
                    }
                    String defaultType = s.getList().get(0).getSon().get(0).getName();
                    String defaultTypeCode = s.getList().get(0).getSon().get(0).getId();

                    if (!b) {
                        tvShowProductType.setText(defaultType);
                        product_type = defaultTypeCode;
                    }
                }
                break;
            default:
                break;
        }
    }


    private void initdata() {


        loadPhoto1 = findViewById(R.id.load_photo1);
        loadPhoto2 = findViewById(R.id.load_photo2);
        loadPhoto3 = findViewById(R.id.load_photo3);
        loadPhoto4 = findViewById(R.id.load_photo4);
        photos = new int[]{loadPhoto1.getId(), loadPhoto2.getId(), loadPhoto3.getId(), loadPhoto4.getId()};


        cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");


        OkhttpRequestUtil.get(ConstanUrl.GET_PRODUCT_TYPE, 1, true, uiHandler);

//        String str ="";
//        str=new UseAPIs().getProductType();
//        if (str.length() != 0) {
//            ProductTypeBean s = new J2O().getProductType(str);
//
//            for (int i = 0; i < s.getList().size(); i++) {
//                String mainName = s.getList().get(i).getName();
//                mainList.add(mainName);
//                List<String> cSublist = new ArrayList<>();
//                for (int j = 0; j < s.getList().get(i).getSon().size(); j++) {
//                    String subName = s.getList().get(i).getSon().get(j).getName();
//                    String subCode = s.getList().get(i).getSon().get(j).getId();
//                    cSublist.add(subName);
//                    subList.add(subName);
//                    subMap.put(subName, subCode);
//
//                }
//                map.put(mainName, cSublist);
//            }
//            String defaultType = s.getList().get(0).getSon().get(0).getName();
//            String defaultTypeCode = s.getList().get(0).getSon().get(0).getId();
//            tvShowProductType.setText(defaultType);
//            product_type = defaultTypeCode;
//        } else {
//            Toast.makeText(this, "网络错误！", Toast.LENGTH_SHORT).show();
//        }


    }


    @OnClick({R.id.tv_back, R.id.rl_product_type, R.id.load_photo1, R.id.load_photo2, R.id.load_photo3, R.id.load_photo4, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.rl_product_type:
                show1();
                break;
            case R.id.load_photo1:
                showChoosePic(REQUEST_CODE_UPLOAD_PRODUCT1);
                break;
            case R.id.load_photo2:
                showChoosePic(REQUEST_CODE_UPLOAD_PRODUCT2);
                break;
            case R.id.load_photo3:
                showChoosePic(REQUEST_CODE_UPLOAD_PRODUCT3);
                break;
            case R.id.load_photo4:
                showChoosePic(REQUEST_CODE_UPLOAD_PRODUCT4);
                break;
            case R.id.submit:

                getData();

                if (product_name.length() == 0 || product_type.length() == 0 || product_price.length() == 0 || product_introduct.length() == 0 || base64_photo1.length() == 0) {
                    Toast.makeText(this, "请把信息填写完整", Toast.LENGTH_SHORT).show();
                } else {

//                    Toast.makeText(context, "类别:"+product_type, Toast.LENGTH_SHORT).show();
                    System.out.println("类别:" + product_type);

                    String result = "";
                    result = new UseAPIs().addProduct(product_name, product_type, product_introduct, product_price, cookie, base64_photo1, base64_photo2, base64_photo3, base64_photo4);

                    if (result.length() != 0) {
                        AddProductResultBean a = j.addProductResult(result);

                        if (a.getCode() == 1) {
                            this.finish();
                        }
                        Toast.makeText(this, a.getMsg(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "网络错误！", Toast.LENGTH_SHORT).show();
                    }


                }
                break;
        }
    }


    private void show1() {
        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.select_product_type_dialog, null);
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextSize = 12;
        style.textSize = 8;
        mainWheelView = (WheelView) contentView.findViewById(R.id.main_wheelview);
        mainWheelView.setWheelAdapter(new ArrayWheelAdapter(this));
        mainWheelView.setSkin(WheelView.Skin.Holo);
        mainWheelView.setWheelData(mainList);
        mainWheelView.setStyle(style);
        subWheelView = (WheelView) contentView.findViewById(R.id.sub_wheelview);
        subWheelView.setWheelAdapter(new ArrayWheelAdapter(this));
        subWheelView.setSkin(WheelView.Skin.Holo);
        subWheelView.setWheelData(map.get(mainList.get(mainWheelView.getSelection())));
        subWheelView.setStyle(style);
        mainWheelView.join(subWheelView);
        mainWheelView.joinDatas(map);
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
                tvShowProductType.setText(subWheelView.getSelectionItem() + "");
                product_type = subMap.get(subWheelView.getSelectionItem());
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
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Matisse.from(AddProductActivity.this)
                    .choose(MimeType.allOf()) // 选择 mime 的类型
                    .countable(true)
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
        if (requestCode == REQUEST_CODE_UPLOAD_PRODUCT1 && resultCode == RESULT_OK) {
              /*照片选择后的回调*/
            List<Uri> mSelected = Matisse.obtainResult(data);
            if (!(mSelected.size() == 0)) {

                String base64 = "data:image/jpeg;base64," + commonMethod.uriToBase64(mSelected.get(0), this);
                base64_photo1 = base64;
                loadPhoto1.setImageURI(mSelected.get(0));
                pic.add(base64_photo1);
            }
        }
        if (requestCode == REQUEST_CODE_UPLOAD_PRODUCT2 && resultCode == RESULT_OK) {
              /*照片选择后的回调*/
            List<Uri> mSelected = Matisse.obtainResult(data);
            if (!(mSelected.size() == 0)) {

                String base64 = "data:image/jpeg;base64," + commonMethod.uriToBase64(mSelected.get(0), this);
                base64_photo2 = base64;
                loadPhoto2.setImageURI(mSelected.get(0));
                pic.add(base64_photo2);
            }
        }


        if (requestCode == REQUEST_CODE_UPLOAD_PRODUCT3 && resultCode == RESULT_OK) {
              /*照片选择后的回调*/
            List<Uri> mSelected = Matisse.obtainResult(data);
            if (!(mSelected.size() == 0)) {

                String base64 = "data:image/jpeg;base64," + commonMethod.uriToBase64(mSelected.get(0), this);
                base64_photo3 = base64;
                loadPhoto3.setImageURI(mSelected.get(0));
                pic.add(base64_photo3);
            }
        }


        if (requestCode == REQUEST_CODE_UPLOAD_PRODUCT4 && resultCode == RESULT_OK) {
              /*照片选择后的回调*/
            List<Uri> mSelected = Matisse.obtainResult(data);
            if (!(mSelected.size() == 0)) {

                String base64 = "data:image/jpeg;base64," + commonMethod.uriToBase64(mSelected.get(0), this);
                base64_photo4 = base64;
                loadPhoto4.setImageURI(mSelected.get(0));
                pic.add(base64_photo4);
            }
        }

    }

}
