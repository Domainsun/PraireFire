package com.praire.fire.car;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.praire.fire.MyApplication;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.home.MainActivity;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 店铺
 * Created by lyp on 2018/1/3.
 */

public class ShopActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {


    @BindView(R.id.shop_logo)
    ImageView shopLogo;
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
    @BindView(R.id.shop_navigation_bar)
    BottomNavigationBar shopNavigationBar;
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
        shopBack.setText(R.string.shop);
        shopNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        shopNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        shopNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home, getString(R.string.index)).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.shop_car, getString(R.string.shopping_car)).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.me, getString(R.string.my)).setActiveColorResource(R.color.orange))
                .setFirstSelectedPosition(0)
                .initialise();
        shopNavigationBar.setTabSelectedListener(this);
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

    }

    @Override
    protected void initData() {

    }

    private void requestDatas() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(ConstanUrl.Shop_Info + "?PHPSESSID=" + new MyApplication().getSignCookie() + "&")  ////'''''''''''''''''''''''''''''''''''''''''''
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ShopActivity.this, "网络出错，请重试", Toast.LENGTH_SHORT).show();
//                        loadMore = false;
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                String data = response.body().string();
                if (data == null) {
//                    loadMore = false;
                    return;
                }
                Log.e("Shop_Info", data);
               /* Gson gson = new Gson();
                final ShopListBean evEntity = gson.fromJson(data, ShopListBean.class);
                evEntitys = evEntity.getPagelist();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setEntities(evEntitys);
                    }
                });
*/
            }
        });
    }

    @OnClick({R.id.shop_back, R.id.shop_more, R.id.shop_collect, R.id.shop_share, R.id.shop_tel,
            R.id.shop_more_service, R.id.shop_more_products, R.id.shop_more_evaluate})
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
                break;
            case R.id.shop_more_service:
                break;
            case R.id.shop_more_products:

                break;
            case R.id.shop_more_evaluate:
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case 0:
                MainActivity.startActivity(this, 0, false);
                finish();
                break;
            case 1:

                break;
            case 2:
                MainActivity.startActivity(this, 3, false);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabUnselected(int i) {

    }

    @Override
    public void onTabReselected(int i) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
