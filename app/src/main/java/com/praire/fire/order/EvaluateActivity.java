package com.praire.fire.order;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.CommonMethod;
import com.praire.fire.common.Constants;
import com.praire.fire.merchant.AddProductActivity;
import com.praire.fire.order.bean.OrderListBean;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

import static com.praire.fire.common.Constants.INTENT_DATA;
import static com.praire.fire.common.Constants.REQUEST_CODE_UPLOAD_PRODUCT1;
import static com.praire.fire.common.Constants.REQUEST_CODE_UPLOAD_PRODUCT2;
import static com.praire.fire.common.Constants.REQUEST_CODE_UPLOAD_PRODUCT3;
import static com.praire.fire.common.Constants.REQUEST_CODE_UPLOAD_PRODUCT4;

/**
 * 评价订单
 * Created by lyp on 2018/1/23.
 */

public class EvaluateActivity extends BaseTitleActivity {


    @BindView(R.id.evaluate_product_img)
    SimpleDraweeView evaluateProductImg;
    @BindView(R.id.evaluate_product_name)
    TextView evaluateProductName;
    @BindView(R.id.evaluate_count)
    TextView evaluateCount;
    @BindView(R.id.evaluate_price)
    TextView evaluatePrice;
    @BindView(R.id.item_shop_ealuate_star)
    RatingBar itemShopEaluateStar;
    @BindView(R.id.input_evaluate)
    EditText inputEvaluate;
    @BindView(R.id.img_evaluate1)
    SimpleDraweeView imgEvaluate1;
    @BindView(R.id.img_evaluate2)
    SimpleDraweeView imgEvaluate2;
    @BindView(R.id.img_evaluate3)
    SimpleDraweeView imgEvaluate3;
    @BindView(R.id.img_evaluate4)
    SimpleDraweeView imgEvaluate4;
    @BindView(R.id.evaluate_checkbox)
    CheckBox evaluateCheckbox;
    @BindView(R.id.add_evaluate_ll)
    LinearLayout addEvaluateLl;
    OrderListBean.PagelistBean orderInfo;
    String base64_photo1 = "";
    String base64_photo2 = "";
    String base64_photo3 = "";
    String base64_photo4 = "";
    private CommonMethod commonMethod;
    List<SimpleDraweeView> imageList = new ArrayList<>();
    List<String> base64PhotoList = new ArrayList<>();
    public static void startActivity(Context context, OrderListBean.PagelistBean orderInfo, boolean forResult) {
        Intent intent = new Intent(context, EvaluateActivity.class);
        intent.putExtra(INTENT_DATA, orderInfo);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_evaluate;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);

        Uri uri = Uri.parse(orderInfo.getPslist().get(0).getCover());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        evaluateProductImg.setController(controller);

          evaluateProductName.setText(orderInfo.getPslist().get(0).getName());
          evaluateCount.setText(String.format(evaluateCount.getTag().toString(),orderInfo.getPslist().get(0).getNumber()));
          evaluatePrice.setText(String.format(evaluatePrice.getTag().toString(),orderInfo.getPslist().get(0).getPrice()));
        if(orderInfo.getPslist().size()>1) {
            for(int i=1;i<orderInfo.getPslist().size();i++) {
                ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.layout_evaluate, null);
                SimpleDraweeView img = viewGroup.findViewById(R.id.evaluate_product_img);
                TextView productName = viewGroup.findViewById(R.id.evaluate_product_name);
                TextView count = viewGroup.findViewById(R.id.evaluate_count);
                TextView price = viewGroup.findViewById(R.id.evaluate_price);
                RatingBar itemShopEaluateStar = viewGroup.findViewById(R.id.item_shop_ealuate_star);
                EditText inputEvaluate = viewGroup.findViewById(R.id.input_evaluate);
                SimpleDraweeView imgEvaluate1 = viewGroup.findViewById(R.id.img_evaluate1);
                SimpleDraweeView imgEvaluate2 = viewGroup.findViewById(R.id.img_evaluate2);
                SimpleDraweeView imgEvaluate3 = viewGroup.findViewById(R.id.img_evaluate3);
                SimpleDraweeView imgEvaluate4 = viewGroup.findViewById(R.id.img_evaluate4);
                CheckBox evaluateCheckbox = viewGroup.findViewById(R.id.evaluate_checkbox);
                productName.setText(orderInfo.getPslist().get(i).getName());
                count.setText(String.format(count.getTag().toString(), orderInfo.getPslist().get(i).getNumber()));
                price.setText(String.format(price.getTag().toString(), orderInfo.getPslist().get(i).getPrice()));


        /*if ("1".equals(bean.getPslist().get(i).getType())) {
            img.setVisibility(View.VISIBLE);
            Uri uri = Uri.parse(bean.getPslist().get(i).getCover());
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setAutoPlayAnimations(true)
                    .build();
            img.setController(controller);
        }*/
                addEvaluateLl.addView(viewGroup);
            }
        }
    }

    @Override
    protected void initListeners() {
        imageList.add(imgEvaluate1);
        imageList.add(imgEvaluate2);
        imageList.add(imgEvaluate3);
        imageList.add(imgEvaluate4);
        base64PhotoList.add(base64_photo1);
        base64PhotoList.add(base64_photo2);
        base64PhotoList.add(base64_photo3);
        base64PhotoList.add(base64_photo4);
    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initiTile() {
        orderInfo = getIntent().getParcelableExtra(INTENT_DATA);
        setDefaultBack();
        setTitleMiddle(orderInfo.getShopname());
    }


    @OnClick({R.id.img_evaluate1, R.id.img_evaluate2, R.id.img_evaluate3, R.id.img_evaluate4, R.id.btn_commit_evaluate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_evaluate1:
                showChoosePic(REQUEST_CODE_UPLOAD_PRODUCT1, 1);

                break;
            case R.id.img_evaluate2:
                showChoosePic(REQUEST_CODE_UPLOAD_PRODUCT2, 1);
                break;
            case R.id.img_evaluate3:
                showChoosePic(REQUEST_CODE_UPLOAD_PRODUCT3, 1);
                break;
            case R.id.img_evaluate4:
                showChoosePic(REQUEST_CODE_UPLOAD_PRODUCT4, 1);
                break;
            case R.id.btn_commit_evaluate:
                break;
            default:
                break;
        }
    }

    private void showChoosePic(int requestCode, int number) {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Matisse.from(EvaluateActivity.this)
                    // 选择 mime 的类型
                    .choose(MimeType.allOf())
                    .countable(true)
                    // 图片选择的最多数量
                    .maxSelectable(number)
                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                    .restrictOrientation(android.content.pm.ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    // 缩略图的比例0.85
                    .thumbnailScale(0.85f)
                    // 使用的图片加载引擎
                    .imageEngine(new PicassoEngine())
                    // 设置作为标记的请求码
                    .forResult(requestCode);
        } else {
            EasyPermissions.requestPermissions(this, "打开相册需要获取权限",
                    0, perms);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        commonMethod = new CommonMethod();
        /*选择照片回调*/
        if (resultCode == RESULT_OK) {
            List<Uri> mSelected = Matisse.obtainResult(data);
            switch (requestCode) {
                case REQUEST_CODE_UPLOAD_PRODUCT1:
                    if (mSelected.size() != 0) {
//                        setImageUrl(mSelected.get(0), imgEvaluate1, base64_photo1);
                        String base64 = "data:image/jpeg;base64," + commonMethod.uriToBase64(mSelected.get(0), this);
                        base64_photo1 = base64;
                        imgEvaluate1.setImageURI(mSelected.get(0));
                        imgEvaluate2.setVisibility(View.VISIBLE);
                    }
                    break;
                case REQUEST_CODE_UPLOAD_PRODUCT2:

                    if (mSelected.size() != 0) {
                        String base64 = "data:image/jpeg;base64," + commonMethod.uriToBase64(mSelected.get(0), this);
                        base64_photo2 = base64;
                        imgEvaluate2.setImageURI(mSelected.get(0));
//                        setImageUrl(mSelected.get(0), imgEvaluate2, base64_photo2);
                        imgEvaluate3.setVisibility(View.VISIBLE);
                    }
                    break;
                case REQUEST_CODE_UPLOAD_PRODUCT3:
                    if (mSelected.size() != 0) {
                        String base64 = "data:image/jpeg;base64," + commonMethod.uriToBase64(mSelected.get(0), this);
                        base64_photo3 = base64;
                        imgEvaluate3.setImageURI(mSelected.get(0));
//                        setImageUrl(mSelected.get(0), imgEvaluate3, base64_photo3);
                        imgEvaluate4.setVisibility(View.VISIBLE);
                    }
                    break;
                case REQUEST_CODE_UPLOAD_PRODUCT4:
                    if (mSelected.size() != 0) {
                        String base64 = "data:image/jpeg;base64," + commonMethod.uriToBase64(mSelected.get(0), this);
                        base64_photo4 = base64;
                        imgEvaluate4.setImageURI(mSelected.get(0));
//                        setImageUrl(mSelected.get(0), imgEvaluate4, base64_photo4);
                    }
                    break;
                default:
                    break;
            }
        }


    }

    private void setImageUrl(Uri mSelected, SimpleDraweeView imgEvaluate, String base64_photo) {
        String base64 = "data:image/jpeg;base64," + commonMethod.uriToBase64(mSelected, this);
        base64_photo = base64;
        imgEvaluate.setImageURI(mSelected);

    }
    public void btnLoadPic(View view) {
        //大图片
        // imageView.setImageResource(R.mipmap.large_pic);


        //从大图片，在加载的时候，直接加载缩小尺寸的缩略图，（BitmapFactory）
        Resources res = getResources();
        //图片加载时，可以指定，Options参数，实际上BitmapFactory 通过Options
        // 可以将配置的参数传递给图片解码器（）
        BitmapFactory.Options options = new BitmapFactory.Options();
        //图片解码的时候进行的采样比例，1代表不变，原始尺寸
        // ，2 以及一闪给，代表宽度/inSampleSize 高度/inSampleSize
        options.inSampleSize = 32;
//        Bitmap bitmap = BitmapFactory.decodeResource(res, R.mipmap.large_pic,options);
//        imageView.setImageBitmap(bitmap);
        //
    }
}
