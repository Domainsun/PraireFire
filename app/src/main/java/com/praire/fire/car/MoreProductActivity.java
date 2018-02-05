package com.praire.fire.car;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.car.adapter.ShopProductAdapter;
import com.praire.fire.car.adapter.ShopServiceAdapter;
import com.praire.fire.car.bean.CommitProduct;
import com.praire.fire.car.bean.ProductlistBean;
import com.praire.fire.car.bean.ServicelistBean;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.data.IntentDataForCommitOrderActivity;
import com.praire.fire.map.MapSearchActivity;
import com.praire.fire.my.ShoppingCarActivity;
import com.praire.fire.my.bean.CommentResultBean;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.order.OrderUtils;
import com.praire.fire.utils.RecycleViewDivider;
import com.praire.fire.utils.statusbarcolor.Eyes;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * 商品列表
 * 商品  服务
 * Created by lyp on 2018/1/4.
 */

public class MoreProductActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.plug_search_edittext)
    EditText plugSearchEdittext;
    @BindView(R.id.search_bar2_relativeLayout)
    RelativeLayout searchBar2RelativeLayout;
    @BindView(R.id.product_tab)
    TabLayout productTab;
    @BindView(R.id.product_commodity_img)
    ImageView productCommodityImg;
    @BindView(R.id.product_commodity)
    TextView productCommodity;
    @BindView(R.id.product_commodity_ll)
    LinearLayout productCommodityLl;
    @BindView(R.id.product_recyclerView)
    SwipeMenuRecyclerView productRecyclerView;
    @BindView(R.id.product_totle_price)
    TextView productTotlePrice;
    @BindView(R.id.product_true_price)
    TextView productTruePrice;
    @BindView(R.id.product_buying)
    Button productBuying;
    @BindView(R.id.product_pop_buying)
    RelativeLayout productPopBuying;
    @BindView(R.id.shopping_car)
    ImageView shoppingCar;
    @BindView(R.id.shopping_car_number)
    TextView shoppingCarNumber;
    private String businessId;
    private List<ProductlistBean> productbean;
    private List<ServicelistBean> serviceBean;
    private ShopServiceAdapter adapterService;
    private ShopProductAdapter adapterProduct;
    private int tabType = 0;
    private int shoppingCarCount = 0;

    List<CommitProduct> commitProductList = new ArrayList<>();

    public static void startActivity(Context context, String businessId, int type, boolean forResult) {
        Intent intent = new Intent(context, MoreProductActivity.class);
        intent.putExtra(Constants.BUSSINESS_ID, businessId);
        intent.putExtra(Constants.UI_TYPE, type);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_shop_product;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        Eyes.setStatusBarColor(this, ContextCompat.getColor(this, R.color.status_bar));

        productTab.addTab(productTab.newTab().setText(R.string.product));
        productTab.addTab(productTab.newTab().setText(R.string.service));
        productTab.setOnTabSelectedListener(this);
        businessId = getIntent().getStringExtra(Constants.BUSSINESS_ID);
        tabType = getIntent().getIntExtra(Constants.UI_TYPE, 0);

        plugSearchEdittext.setFocusable(false);

    }

    @Override
    protected void initListeners() {

        adapterService = new ShopServiceAdapter(this);
        adapterProduct = new ShopProductAdapter(this);

        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        productRecyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL));
        productRecyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                if (tabType == 0) {
                    ProductInfoActivity.startActivity(MoreProductActivity.this, productbean.get(position).getId(), false);
                } else {
                    ServiceInfoActivity.startActivity(MoreProductActivity.this, serviceBean.get(position).getId(), false);
                }


            }
        });


    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {
        if (tabType == 0) {
            getProductList(1);
            productRecyclerView.setAdapter(adapterProduct);
        } else {
            getServiceList(1);
            productRecyclerView.setAdapter(adapterService);
            productTab.getTabAt(1).select();
        }
    }

    private void getProductList(int index) {
        OkhttpRequestUtil.get(ConstanUrl.COMMONINFO_PRODUCTLIST + "?shopid=" + businessId + "&p=" + index, 1, false, uiHandler);
    }

    private void getServiceList(int index) {
        OkhttpRequestUtil.get(ConstanUrl.COMMONINFO_SERVICELIST + "?shopid=" + businessId + "&p=" + index, 2, false, uiHandler);
    }

    @OnClick({R.id.search_bar2_back, R.id.plug_search_edittext, R.id.product_buying, R.id.shopping_car})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_bar2_back:
                finish();
                break;
            case R.id.plug_search_edittext:
                MapSearchActivity.startActivity(this, plugSearchEdittext.getText().toString().trim(), false);
                break;
            case R.id.product_buying:
                IntentDataForCommitOrderActivity data = new IntentDataForCommitOrderActivity();
                data.commitProductList = commitProductList;
                data.type = "0";
                CommitOrderActivity.startActivity(this, data, false);
                break;
            case R.id.shopping_car:
                ShoppingCarActivity.startActivity(this, false);
                break;
            default:
                break;
        }
    }

    @Override
    protected void networkResponse(Message msg) {
        String data = (String) msg.obj;
        Log.e("moreProduct=", data);
        switch (msg.what) {
            case 1:
                JSONArray personObject = null;
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    personObject = jsonObject.getJSONArray("pagelist");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Gson gson = new Gson();
                productbean = gson.fromJson(personObject.toString(), new TypeToken<List<ProductlistBean>>() {
                }.getType());
                adapterProduct.setEntities(productbean, true);
                break;
            case 2:
                JSONArray personObject2 = null;
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    personObject2 = jsonObject.getJSONArray("pagelist");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Gson gson1 = new Gson();
                serviceBean = gson1.fromJson(personObject2.toString(), new TypeToken<List<ServicelistBean>>() {
                }.getType());
                adapterService.setEntities(serviceBean, true);
                break;
            case 3:
                //                成功添加到购物车

                Gson gson2 = new Gson();
                CommentResultBean commitBean = gson2.fromJson(data, CommentResultBean.class);
                if (commitBean.getCode() == 1) {
                    ++shoppingCarCount;
                    shoppingCarNumber.setVisibility(View.VISIBLE);
                    shoppingCarNumber.setText(shoppingCarCount + "");
                }
                Toast.makeText(this, commitBean.getMsg(), Toast.LENGTH_SHORT).show();
                productTruePrice.setText(String.format(productTruePrice.getTag().toString(), OrderUtils.totlePrice(commitProductList)));
                break;
            case 4:
                //修改购物车内某商品数量
                Gson gson3 = new Gson();
                CommentResultBean commitBean3 = gson3.fromJson(data, CommentResultBean.class);
                if (commitBean3.getMsg() != null) {
                    Toast.makeText(this, commitBean3.getMsg(), Toast.LENGTH_SHORT).show();
                }
                if (commitBean3.getCode() == 1) {
                    --shoppingCarCount;
                    if (shoppingCarCount == 0) {
                        shoppingCarNumber.setVisibility(View.GONE);
                    } else {
                        shoppingCarNumber.setVisibility(View.VISIBLE);
                        shoppingCarNumber.setText(shoppingCarCount + "");
                    }
                }
                productTruePrice.setText(String.format(productTruePrice.getTag().toString(), OrderUtils.totlePrice(commitProductList)));
                break;
            case 5:
                //删除购物车内某商品

                Gson gson4 = new Gson();
                CommentResultBean commitBean4 = gson4.fromJson(data, CommentResultBean.class);
                if (commitBean4.getMsg() != null) {
                    Toast.makeText(this, commitBean4.getMsg(), Toast.LENGTH_SHORT).show();
                }
                if (commitBean4.getCode() == 1) {
                    --shoppingCarCount;
                    if (shoppingCarCount == 0) {
                        shoppingCarNumber.setVisibility(View.GONE);
                    } else {
                        shoppingCarNumber.setVisibility(View.VISIBLE);
                        shoppingCarNumber.setText(shoppingCarCount + "");
                    }
                }
                productTruePrice.setText(String.format(productTruePrice.getTag().toString(), OrderUtils.totlePrice(commitProductList)));
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                tabType = 0;
                productCommodityImg.setImageResource(R.mipmap.commodity);
                productCommodity.setText(R.string.product);
                productRecyclerView.setAdapter(adapterProduct);
                getProductList(1);
                break;
            case 1:
                tabType = 1;
                productCommodityImg.setImageResource(R.mipmap.service);
                productCommodity.setText(R.string.service);
                productRecyclerView.setAdapter(adapterService);
                getServiceList(1);
                break;

            default:
                break;
        }
//        initData();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /**
     * 添加到购物车
     *
     * @param type      （1：产品，2：服务）
     * @param productId
     */
    public void addToShoppingCar(String type, String productId, int position) {
        RequestBody requestBody = new FormBody.Builder()
                //（1：产品，2：服务）
                .add("type", type)
                .add("ps_id", productId)
                .add("count", "1")
                .build();
        OkhttpRequestUtil.post(ConstanUrl.CART_ADD, requestBody, 3, uiHandler, true);

        chooseAdds(type, productId, position);
    }

    private void chooseAdds(String type, String productId, int position) {
        if (commitProductList != null && commitProductList.size() > 0) {
            for (int i = 0; i < commitProductList.size(); i++) {
                if (productId.equals(commitProductList.get(i).getPsId())) {
                    commitProductList.get(i).setNumber(commitProductList.get(i).getNumber() + 1);
                    return;
                }
            }
            if (tabType == 0) {
                addCommitList(type, productbean.get(position).getNprice(), productbean.get(position).getName(), productId, productbean.get(position).getShop_id());
            } else if (tabType == 1) {
                addCommitList(type, serviceBean.get(position).getNprice(), serviceBean.get(position).getName(), productId, serviceBean.get(position).getShop_id());
            }
            return;
        }

        if (tabType == 0) {
            addCommitList(type, productbean.get(position).getNprice(), productbean.get(position).getName(), productId, productbean.get(position).getShop_id());
        } else if (tabType == 1) {
            addCommitList(type, serviceBean.get(position).getNprice(), serviceBean.get(position).getName(), productId, serviceBean.get(position).getShop_id());

        }

    }

    /**
     * 修改购物车内某商品的数量
     *
     * @param id    商品id
     * @param count
     */
    public void editNumShoppingCar(String id, int count, int position) {
        RequestBody requestBody = new FormBody.Builder()
                .add("id", id)
                .add("count", count + "")
                .build();
        OkhttpRequestUtil.post(ConstanUrl.CART_CHANGECOUNT, requestBody, 4, uiHandler, true);

        for (int i = 0; i < commitProductList.size(); i++) {
            if (id.equals(commitProductList.get(i).getPsId())) {
                commitProductList.get(i).setNumber(count);
            }
        }
    }

    /**
     * 删除购物车内某商品
     *
     * @param id 商品id
     */
    public void deleteShoppingCar(String id) {
        RequestBody requestBody = new FormBody.Builder()
                .add("id", id)
                .build();
        OkhttpRequestUtil.post(ConstanUrl.CART_DELETE, requestBody, 5, uiHandler, true);

        for (CommitProduct com : commitProductList) {
            if (id.equals(com.getPsId())) {
                commitProductList.remove(com);
            }
        }
    }

    private void addCommitList(String type, String price, String pName, String pId, String shopid) {
        CommitProduct commitProduct = new CommitProduct();
        commitProduct.setType(type);
        commitProduct.setpPrice(price);
        commitProduct.setpName(pName);
        commitProduct.setpsId(pId);
        commitProduct.setShopId(shopid);
        commitProduct.setNumber(1);
        commitProductList.add(commitProduct);
        commitProduct = null;
    }


}
