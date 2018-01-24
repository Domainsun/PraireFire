package com.praire.fire.car;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Message;
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

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.car.adapter.ShopEvalauteAdapter;
import com.praire.fire.car.bean.CommentlistBean;
import com.praire.fire.car.bean.CommitProduct;
import com.praire.fire.car.bean.ServiceInfoBean;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.data.IntentDataForCommitOrderActivity;
import com.praire.fire.my.bean.CommentResultBean;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.utils.RecycleViewDivider;
import com.praire.fire.utils.statusbarcolor.Eyes;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import static android.content.Intent.ACTION_CALL;

/**
 * 服务详情
 * Created by lyp on 2018/1/4.
 */

public class ServiceInfoActivity extends BaseActivity {

    @BindView(R.id.shop_back)
    TextView shopBack;
    @BindView(R.id.shop_more)
    ImageView shopMore;
    @BindView(R.id.shop_collect)
    ImageView shopCollect;
    @BindView(R.id.shop_share)
    ImageView shopShare;
    @BindView(R.id.service_logo)
    SimpleDraweeView productLogo;
    @BindView(R.id.service_name)
    TextView productName;
    @BindView(R.id.service_star)
    RatingBar productStar;
    @BindView(R.id.service_price)
    TextView productPrice;
    @BindView(R.id.service_sale)
    TextView productSale;
    @BindView(R.id.service_business_info)
    TextView productBusinessInfo;
    @BindView(R.id.service_evaluate)
    TextView productEvaluate;
    @BindView(R.id.recyclerview_evaluate)
    SwipeMenuRecyclerView recyclerviewEvaluate;
    @BindView(R.id.service_more_evaluate_number)
    TextView productMoreEvaluateNumber;
    @BindView(R.id.service_more_evaluate)
    RelativeLayout productMoreEvaluate;
    @BindView(R.id.service_customer_service)
    TextView productCustomerService;
    @BindView(R.id.service_to_shop)
    TextView productToShop;
    @BindView(R.id.service_add_shoppingcar)
    TextView productAddShoppingcar;
    @BindView(R.id.service_buy)
    TextView productBuy;

    private String productId;
    private ServiceInfoBean serviceInfoBean;
    private ShopEvalauteAdapter adapterEvaluate;
    private int count = 1;
    private CommentResultBean commitBean;

    public static void startActivity(Context context, String productId, boolean forResult) {
        Intent intent = new Intent(context, ServiceInfoActivity.class);
        intent.putExtra(Constants.PRODUCT_ID, productId);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_shop_service_info;
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

    }


    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {
        OkhttpRequestUtil.get(ConstanUrl.COMMONINFO_SERVICEINFO + "?id=" + productId,1,false,uiHandler);
    }



    @Override
    protected void networkResponse(Message msg) {
        String data = (String)msg.obj;
        Log.e("serviceInfo=",(String)msg.obj);
        switch (msg.what) {
            case 1:
                Gson gson = new Gson();
                serviceInfoBean = gson.fromJson(data, ServiceInfoBean.class);
                setBaseInfo();
                List<CommentlistBean> commentlistBeans = serviceInfoBean.getComment().getServicecomment();
                adapterEvaluate.setEntities(commentlistBeans);
                break;
            case 2:
                //                成功添加到购物车
                Gson gson2 = new Gson();
                commitBean = gson2.fromJson(data, CommentResultBean.class);
                if(commitBean.getMsg() != null) {
                    Toast.makeText(this, commitBean.getMsg(), Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void setBaseInfo() {
        if (serviceInfoBean.getInfo().getOsspiclist().size() > 0) {
            Uri uri = Uri.parse(serviceInfoBean.getInfo().getOsspiclist().get(0));
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setAutoPlayAnimations(true)
                    .build();
            productLogo.setController(controller);
        }
        productName.setText(serviceInfoBean.getInfo().getName());
        productStar.setRating(Float.valueOf(serviceInfoBean.getInfo().getStar()));
        productPrice.setText(String.format(productPrice.getTag().toString(), serviceInfoBean.getInfo().getNprice()));
        productSale.setText(String.format(productSale.getTag().toString(), serviceInfoBean.getInfo().getSalecount()));
        productBusinessInfo.setText(serviceInfoBean.getInfo().getDesc());
        productEvaluate.setText(String.format(productEvaluate.getTag().toString(), serviceInfoBean.getComment().getCommentcount()));
        productMoreEvaluateNumber.setText(String.format(productMoreEvaluateNumber.getTag().toString(), serviceInfoBean.getComment().getCommentcount()));
    }


    @OnClick({R.id.shop_back, R.id.shop_more, R.id.shop_collect, R.id.shop_share, R.id.service_tel,
            R.id.service_more_evaluate, R.id.service_to_shop, R.id.service_add_shoppingcar, R.id.service_buy})
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
            case R.id.service_tel:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    return;
                }
                startActivity(new Intent(ACTION_CALL, Uri.parse("tel:" + serviceInfoBean.getInfo().getTel())));
                break;
            case R.id.service_more_evaluate:
                ProductAllEvalauteActivity.startActivity(this, productId, false);
                break;
            case R.id.service_to_shop:
                ShopActivity.startActivity(this, serviceInfoBean.getInfo().getShop_id(), false);
                finish();
                break;
            case R.id.service_add_shoppingcar:
                RequestBody requestBody = new FormBody.Builder()
                        //（1：产品，2：服务）
                        .add("type", "2")
                        .add("ps_id", productId)
                        .add("count", count + "")
                        .build();
                OkhttpRequestUtil.post(ConstanUrl.CART_ADD,requestBody,2,uiHandler,true);
                break;
            case R.id.service_buy:
                IntentDataForCommitOrderActivity data = new IntentDataForCommitOrderActivity();
                CommitProduct commitProduct = new CommitProduct();
                commitProduct.setNumber(1);
                commitProduct.setpsId(serviceInfoBean.getInfo().getId());
                commitProduct.setType("2");
                commitProduct.setpPrice(serviceInfoBean.getInfo().getNprice());
                commitProduct.setpName(serviceInfoBean.getInfo().getName());
                commitProduct.setShopId(serviceInfoBean.getInfo().getShop_id());
                data.commitProductList.add(commitProduct);
                data.count = 1;
                data.type = "2";
                CommitOrderActivity.startActivity(this, data, false);

                break;
            default:
                break;
        }
    }



}
