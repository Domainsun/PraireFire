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
import android.view.View;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.car.adapter.ShopEvalauteAdapter;
import com.praire.fire.car.adapter.ShopProductAdapter;
import com.praire.fire.car.adapter.ShopServiceAdapter;
import com.praire.fire.car.bean.BusinessInfoBean;
import com.praire.fire.car.bean.CommentlistBean;
import com.praire.fire.car.bean.ProductlistBean;
import com.praire.fire.car.bean.ServicelistBean;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.home.MainActivity;
import com.praire.fire.my.ShoppingCarActivity;
import com.praire.fire.utils.RecycleViewDivider;
import com.praire.fire.utils.statusbarcolor.Eyes;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Intent.ACTION_CALL;

/**
 * 店铺
 * Created by lyp on 2018/1/3.
 */

public class ShopActivity extends BaseActivity {


    @BindView(R.id.shop_logo)
    SimpleDraweeView shopLogo;
    @BindView(R.id.shop_name)
    TextView shopName;
    @BindView(R.id.shop_star)
    RatingBar shopStar;
    @BindView(R.id.shop_address)
    TextView shopAddress;
    @BindView(R.id.shop_business_info)
    TextView shopBusinessInfo;
    @BindView(R.id.shop_business_info_time)
    TextView shopBusinessInfoTime;
    @BindView(R.id.shop_more_service)
    RelativeLayout shopMoreService;
    @BindView(R.id.shop_more_products)
    RelativeLayout shopMoreProducts;
    @BindView(R.id.shop_evaluate)
    TextView shopEvaluate;
    @BindView(R.id.shop_more_evaluate_number)
    TextView shopMoreEvaluateNumber;
    @BindView(R.id.shop_more_evaluate)
    RelativeLayout shopMoreEvaluate;

    @BindView(R.id.shop_score)
    TextView shopScore;
    @BindView(R.id.shop_name_2)
    TextView shopName2;
    @BindView(R.id.shop_star2)
    RatingBar shopStar2;
    @BindView(R.id.shop_good_num)
    TextView shopGoodNum;
    @BindView(R.id.shop_good_more_than)
    TextView shopGoodMoreThan;
    @BindView(R.id.shop_back)
    TextView shopBack;
    @BindView(R.id.recyclerview_service)
    SwipeMenuRecyclerView recyclerviewService;
    @BindView(R.id.recyclerview_product)
    SwipeMenuRecyclerView recyclerviewProduct;
    @BindView(R.id.recyclerview_evaluate)
    SwipeMenuRecyclerView recyclerviewEvaluate;

    private String businessId;
    private BusinessInfoBean businessInfoBean;
    private ShopServiceAdapter adapterService;
    private ShopProductAdapter adapterProduct;
    private ShopEvalauteAdapter adapterEvaluate;

    public static void startActivity(Context context, String businessId, boolean forResult) {
        Intent intent = new Intent(context, ShopActivity.class);
        intent.putExtra(Constants.BUSSINESS_ID, businessId);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_shop_info;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        Eyes.setStatusBarColor(this, ContextCompat.getColor(this, R.color.status_bar));
        shopBack.setText(R.string.shop);

    }

    @Override
    protected void initListeners() {
        businessId = getIntent().getStringExtra(Constants.BUSSINESS_ID);
        new Thread(new Runnable() {
            @Override
            public void run() {
                requestDatas();
            }
        }).start();

    }

    @Override
    protected void initAdapters() {
        setRecyclerView(recyclerviewService);
        setRecyclerView(recyclerviewProduct);
        setRecyclerView(recyclerviewEvaluate);
        adapterService = new ShopServiceAdapter(this);
        adapterProduct = new ShopProductAdapter(this);
        adapterEvaluate = new ShopEvalauteAdapter(this);
        recyclerviewProduct.setAdapter(adapterProduct);
        recyclerviewService.setAdapter(adapterService);
        recyclerviewEvaluate.setAdapter(adapterEvaluate);
        recyclerviewService.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                ServiceInfoActivity.startActivity(ShopActivity.this, businessInfoBean.getServicelist().get(position).getId(), false);
            }
        });
        recyclerviewProduct.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                ProductInfoActivity.startActivity(ShopActivity.this, businessInfoBean.getProductlist().get(position).getId(), false);
            }
        });
        recyclerviewEvaluate.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
//                ProductEvalauteInfoActivity.startActivity(ShopActivity.this, businessInfoBean.getCommentlist().get(position).getId(), false);
            }
        });
    }

    private void setRecyclerView(SwipeMenuRecyclerView recyclerview) {
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        recyclerview.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL));
    }

    @Override
    protected void initData() {

    }

    private void requestDatas() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(ConstanUrl.Shop_Info + "?id=" + businessId)
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
                    businessInfoBean = gson.fromJson(data, BusinessInfoBean.class);
                    Message msg = new Message();
                    if (businessInfoBean == null) {
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
        switch (msg.what) {
            case 0:
                Toast.makeText(this,  R.string.error_network, Toast.LENGTH_SHORT).show();
                break;
            case 1:
                setBaseInfo();
                List<ServicelistBean>  servicelistBeans = businessInfoBean.getServicelist();
                List<ProductlistBean> productlistBeans = businessInfoBean.getProductlist();
                List<CommentlistBean> commentlistBeans = businessInfoBean.getCommentlist();
                adapterService.setEntities(servicelistBeans,false);
                adapterProduct.setEntities(productlistBeans,false);
                adapterEvaluate.setEntities(commentlistBeans);
                break;
            case 2:
                Toast.makeText(this,  R.string.no_data, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void setBaseInfo() {
        Uri uri = Uri.parse(businessInfoBean.getDoor());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        shopLogo.setController(controller);

        shopName.setText(businessInfoBean.getName());
        shopStar.setRating(Float.valueOf(businessInfoBean.getStar()));
        shopAddress.setText(businessInfoBean.getAddress());
        shopBusinessInfo.setText(businessInfoBean.getDesc());
        shopBusinessInfoTime.setText(businessInfoBean.getOpentime());
        shopEvaluate.setText(String.format(shopEvaluate.getTag().toString(), businessInfoBean.getComment().getCommentallcount()));
        shopMoreEvaluateNumber.setText(businessInfoBean.getComment().getCommentallcount());
        shopScore.setText(businessInfoBean.getStar());
        shopName2.setText(businessInfoBean.getName());
        shopStar2.setRating(Float.valueOf(businessInfoBean.getStar()));
        shopGoodNum.setText("好评率" + businessInfoBean.getComment().getHighcomment() + "%");
        shopGoodMoreThan.setText("高于" + businessInfoBean.getComment().getHigherthan() + "%的同行");


    }

    @OnClick({R.id.shop_back, R.id.shop_more, R.id.shop_collect, R.id.shop_share, R.id.shop_tel,
            R.id.shop_more_service, R.id.shop_more_products, R.id.shop_more_evaluate, R.id.go_shop, R.id.go_shoping_car, R.id.go_my})
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
            case R.id.shop_tel:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    return;
                }
                startActivity(new Intent(ACTION_CALL, Uri.parse("tel:" + businessInfoBean.getTel())));
                break;
            case R.id.shop_more_service:
                MoreProductActivity.startActivity(this, businessId, 1,false);
                break;
            case R.id.shop_more_products:
                MoreProductActivity.startActivity(this, businessId, 0,false);
                break;
            case R.id.shop_more_evaluate:
                ShopAllEvalauteActivity.startActivity(ShopActivity.this, businessId,businessInfoBean.getComment().getCommentallcount(),0, false);
                break;
            case R.id.go_shop:
                MainActivity.startActivity(this, 0, false);
                finish();
                break;
            case R.id.go_shoping_car:
                ShoppingCarActivity.startActivity(this, false);
                break;
            case R.id.go_my:
                MainActivity.startActivity(this, 3, false);
                finish();
                break;
            default:
                break;
        }
    }


}
