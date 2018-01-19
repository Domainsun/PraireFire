package com.praire.fire.car;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.google.gson.Gson;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.car.adapter.ShopEvalauteAdapter;
import com.praire.fire.car.bean.CommitProduct;
import com.praire.fire.car.bean.ProductInfoBean;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.data.IntentDataForCommitOrderActivity;
import com.praire.fire.my.bean.CommentResultBean;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.utils.RecycleViewDivider;
import com.praire.fire.utils.statusbarcolor.Eyes;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Intent.ACTION_CALL;

/**
 * 商品详细信息
 * Created by lyp on 2018/1/4.
 */

public class ProductInfoActivity extends BaseActivity implements BaseSliderView.OnSliderClickListener {

    @BindView(R.id.shop_back)
    TextView shopBack;
    @BindView(R.id.shop_more)
    ImageView shopMore;
    @BindView(R.id.shop_collect)
    ImageView shopCollect;
    @BindView(R.id.shop_share)
    ImageView shopShare;

    @BindView(R.id.product_name)
    TextView productName;
    @BindView(R.id.product_star)
    RatingBar productStar;
    @BindView(R.id.product_price)
    TextView productPrice;
    @BindView(R.id.product_sale)
    TextView productSale;
    @BindView(R.id.product_business_info)
    TextView productBusinessInfo;
    @BindView(R.id.product_evaluate)
    TextView productEvaluate;
    @BindView(R.id.recyclerview_evaluate)
    SwipeMenuRecyclerView recyclerviewEvaluate;
    @BindView(R.id.product_more_evaluate_number)
    TextView productMoreEvaluateNumber;
    @BindView(R.id.product_more_evaluate)
    RelativeLayout productMoreEvaluate;
    @BindView(R.id.product_customer_service)
    TextView productCustomerService;
    @BindView(R.id.product_to_shop)
    TextView productToShop;
    @BindView(R.id.product_add_shoppingcar)
    TextView productAddShoppingcar;
    @BindView(R.id.product_buy)
    TextView productBuy;
    @BindView(R.id.home_banner_slider)
    SliderLayout homeBannerSlider;

    private String productId;
    private ProductInfoBean productInfoBean;
    private ShopEvalauteAdapter adapterEvaluate;

    public static void startActivity(Context context, String productId, boolean forResult) {
        Intent intent = new Intent(context, ProductInfoActivity.class);
        intent.putExtra(Constants.PRODUCT_ID, productId);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_shop_product_info;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        Eyes.setStatusBarColor(this, ContextCompat.getColor(this, R.color.status_bar));

        recyclerviewEvaluate.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewEvaluate.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        recyclerviewEvaluate.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL));
        adapterEvaluate = new ShopEvalauteAdapter(this);

        recyclerviewEvaluate.setAdapter(adapterEvaluate);
    }

    @Override
    protected void initListeners() {
        productId = getIntent().getStringExtra(Constants.PRODUCT_ID);
        shopBack.setText(R.string.product_info);
        //设置指示器的位置
        homeBannerSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        //设置图片的切换效果
        homeBannerSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        //添加textView动画特效
        homeBannerSlider.setCustomAnimation(new DescriptionAnimation());
        //设置切换时长3000 ,时长越小，切换速度越快
        homeBannerSlider.setDuration(3000);
    }


    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

        OkhttpRequestUtil.get(ConstanUrl.COMMONINFO_PRODUCTINFO + "?id=" + productId,1,false,uiHandler);
    }

    private void requestDatas() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(ConstanUrl.COMMONINFO_PRODUCTINFO + "?id=" + productId)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = new Message();
                msg.what = 0;
                uiHandler.sendMessage(msg);
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                String data = response.body().string();
                if (data != null) {

                    Gson gson = new Gson();
                    productInfoBean = gson.fromJson(data, ProductInfoBean.class);
                    Message msg = new Message();
                    if (productInfoBean.getCode() == 0) {
                        msg.what = 2;
                    } else {
                        msg.what = 1;
                    }
                    uiHandler.sendMessage(msg);
                }

            }
        });
    }

    @Override
    protected void networkResponse(Message msg) {
        Log.e("product_Info", (String)msg.obj);
        switch (msg.what) {
            case 1:
                Gson gson = new Gson();

                productInfoBean = gson.fromJson((String)msg.obj, ProductInfoBean.class);
                getAdSuccess(productInfoBean.getInfo().getOsspiclist());
                setBaseInfo();
                adapterEvaluate.setEntities(productInfoBean.getComment().getProductcomment());
                break;
            case 5:
                Gson gson2 = new Gson();
                CommentResultBean addBean = gson2.fromJson((String)msg.obj, CommentResultBean.class);
                Toast.makeText(this, addBean.getMsg(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void setBaseInfo() {
        productName.setText(productInfoBean.getInfo().getName());
        productStar.setRating(Float.valueOf(productInfoBean.getInfo().getStar()));
        productPrice.setText(String.format(productPrice.getTag().toString(), productInfoBean.getInfo().getNprice()));
        productSale.setText(String.format(productSale.getTag().toString(), productInfoBean.getInfo().getSalecount()));
        productBusinessInfo.setText(productInfoBean.getInfo().getDesc());
        productEvaluate.setText(String.format(productEvaluate.getTag().toString(), productInfoBean.getComment().getCommentcount()));
        productMoreEvaluateNumber.setText(String.format(productMoreEvaluateNumber.getTag().toString(), productInfoBean.getComment().getCommentcount()));
    }

    /**
     * 添加图片控件
     */
    public void getAdSuccess(List<String> swipelist) {
        if (swipelist == null || swipelist.size() <= 0) {
            return;
        }

        for (int i = 0, n = swipelist.size(); i < n; ++i) {
            String bannerItem = swipelist.get(i);

            DefaultSliderView textSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .image(bannerItem)
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
//            textSliderView.bundle(new Bundle())
//                    .getBundle()
//                    .putString(Constants.HTTP_URL, bannerItem.getImgHttpUrl());
//            textSliderView.getBundle().putString(Constants.TITLE, bannerItem.getImgName());
            homeBannerSlider.addSlider(textSliderView);
        }
    }

    @OnClick({R.id.shop_back, R.id.shop_more, R.id.shop_collect, R.id.shop_share, R.id.product_tel,
            R.id.product_more_evaluate, R.id.product_to_shop, R.id.product_add_shoppingcar, R.id.product_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shop_back:
                finish();
                break;
            case R.id.shop_more:
                break;
            case R.id.shop_collect:
                break;
            case R.id.shop_share:
                break;
            case R.id.product_tel:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    return;
                }
                startActivity(new Intent(ACTION_CALL, Uri.parse("tel:" + productInfoBean.getInfo().getTel())));
                break;
            case R.id.product_more_evaluate:
                ProductAllEvalauteActivity.startActivity(this, productId, false);
                break;
            case R.id.product_to_shop:
                ShopActivity.startActivity(this, productInfoBean.getInfo().getShop_id(), false);
                finish();
                break;
            case R.id.product_add_shoppingcar:
                RequestBody requestBody = new FormBody.Builder()
                        //（1：产品，2：服务）
                        .add("type", "1")
                        .add("ps_id", productId)
                        .add("count",  "1")
                        .build();
                OkhttpRequestUtil.post(ConstanUrl.CART_ADD,requestBody,5,uiHandler,true);
                break;
            case R.id.product_buy:
                IntentDataForCommitOrderActivity data = new IntentDataForCommitOrderActivity();
                CommitProduct commitProduct = new CommitProduct();
                commitProduct.setNumber(1);
                commitProduct.setPs_id(productInfoBean.getInfo().getId());
                commitProduct.setType("1");
                commitProduct.setpPrice(productInfoBean.getInfo().getNprice());
                commitProduct.setpName(productInfoBean.getInfo().getName());
                commitProduct.setShopId(productInfoBean.getInfo().getShop_id());

                data.commitProductList.add(commitProduct);
                data.count = 1;
                data.type = "1";
                CommitOrderActivity.startActivity(this, data, false);

                break;
            default:
                break;
        }
    }

    /**
     * 性能优化。当页面显示时进行自动播放
     */
    @Override
    public void onStart() {
        super.onStart();
        homeBannerSlider.startAutoCycle();
    }

    /**
     * 性能优化。当页面不显示时暂停自动播放
     */
    @Override
    public void onStop() {
        super.onStop();
        homeBannerSlider.stopAutoCycle();

    }

    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {
        homeBannerSlider.startAutoCycle(4000, 4000, true);
//
//        int id = homeBannerSlider.getCurrentPosition();
//        switch (imgAdEntitys.get(id).imgPage) {}
    }


}
