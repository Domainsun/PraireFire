package com.praire.fire.my;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.car.CommitOrderActivity;
import com.praire.fire.car.adapter.ShopEvalauteAdapter;
import com.praire.fire.car.bean.CommitProduct;
import com.praire.fire.common.CommonDialog;
import com.praire.fire.data.IntentDataForCommitOrderActivity;
import com.praire.fire.my.adapter.ShopCarAdapter;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.my.bean.CommentResultBean;
import com.praire.fire.my.bean.ShoppingCarBean;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.order.OrderUtils;
import com.praire.fire.utils.RecycleViewDivider;
import com.praire.fire.utils.SharePreferenceMgr;
import com.praire.fire.utils.TextViewUtils;
import com.praire.fire.utils.statusbarcolor.Eyes;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
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

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static com.praire.fire.common.Constants.LOGIN_COOKIE;
import static com.praire.fire.okhttp.OkhttpRequestUtil.NETWORK_ERROR;
import static com.praire.fire.okhttp.OkhttpRequestUtil.NONE_DATA;

/**
 * 购物车
 * Created by lyp on 2018/1/2.
 */
public class ShoppingCarActivity extends BaseActivity {

    /**
     * 获取购物车列表数据
     */
    private static final int REQUEST_SHOPPING_CAR_LIST = 1;
    /**
     * 删除购物车商品
     */
    private static final int DELETE_SHOPPING_CAR_PRODUCT = 2;
    public static final int EDIT_SHOPPING_CAR_NUM = 4;
    @BindView(R.id.shopping_car_back)
    TextView shoppingCarBack;
    @BindView(R.id.shopping_car_num)
    TextView shoppingCarNum;
    @BindView(R.id.shopping_car_recyclerview)
    SwipeMenuRecyclerView recyclerview;
    @BindView(R.id.shopping_car_checkbox)
    CheckBox shoppingCarCheckbox;
    @BindView(R.id.shopping_car_price)
    TextView shoppingCarPrice;
    @BindView(R.id.shopping_car_commit)
    TextView shoppingCarCommit;
    private ShoppingCarBean bean;
    private ShopCarAdapter adapter;
    private String index = "1";
    private Float total = 0f;
    private List<CommitProduct> commitList = new ArrayList<>();
    /**
     * 记录选中的条目数量
      */
    private int checkNum = 0;
    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, ShoppingCarActivity.class);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_shopping_car;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        Eyes.setStatusBarColor(this, ContextCompat.getColor(this, R.color.status_bar));

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        recyclerview.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL));
        // 菜单点击监听。
        recyclerview.setSwipeMenuItemClickListener(mMenuItemClickListener);
        // 设置监听器。
        recyclerview.setSwipeMenuCreator(mSwipeMenuCreator);
        adapter = new ShopCarAdapter(this);
        recyclerview.setAdapter(adapter);
    }

    // 创建菜单：
    SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
//            SwipeMenuItem deleteItem = new SwipeMenuItem(ShoppingCarActivity.this);
//            ...; // 各种文字和图标属性设置。
//            leftMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。

            SwipeMenuItem deleteItem = new SwipeMenuItem(ShoppingCarActivity.this);
            // 各种文字和图标属性设置。//删除
            deleteItem.setWidth(TextViewUtils.dip2px(ShoppingCarActivity.this, 80));
            deleteItem.setHeight(MATCH_PARENT);
            deleteItem.setBackgroundColor(getResources().getColor(R.color.orange));
            deleteItem.setTextColor(getResources().getColor(R.color.white));
            deleteItem.setText("删除");
            deleteItem.setTextSize(18);
//            deleteItem.setBackground(R.drawable.clear_red);
            // 在Item右侧添加一个菜单。
            rightMenu.addMenuItem(deleteItem);

            // 注意：哪边不想要菜单，那么不要添加即可。
        }
    };

    // 菜单点击监听。
    SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            menuBridge.closeMenu();
            // 左侧还是右侧菜单。
//            int direction = menuBridge.getDirection();
            // RecyclerView的Item的position。
            int adapterPosition = menuBridge.getAdapterPosition();
            // 菜单在RecyclerView的Item中的Position。
//            int menuPosition = menuBridge.getPosition();

            String id = bean.getPagelist().get(adapterPosition).getId();
            deleteShoppingCar(id);
        }
    };

    @Override
    protected void initListeners() {
        getShoppingCarList();
    }

    private void getShoppingCarList() {
        OkhttpRequestUtil.get(ConstanUrl.CART_CARTLIST + "?p=" + index, REQUEST_SHOPPING_CAR_LIST, true, uiHandler);
    }


    @Override
    protected void initAdapters() {
        adapter.setListener(new ShopCarAdapter.onCheckListener() {
            @Override
            public void onCheck(HashMap<Integer, Boolean> map) {
                total = 0.00f;
                checkNum = 0;
                for (int i = 0; i < map.size(); i++) {
                    if (map.get(i)) {
                        ShoppingCarBean.PagelistBean item = bean.getPagelist().get(i);
                        total += Float.valueOf(item.getInfo().getNprice()) * Float.valueOf(item.getCount());
//                        addCommitList(item.getType(),item.getInfo().getNprice(),Integer.valueOf(item.getCount()),
//                                item.getInfo().getName(),item.getPs_id(),item.getInfo().getShop_id());
                        checkNum++;
                    }
                }

                //更新显示数据
                dataChanged(total);

            }
        });
    }
    private void addCommitList(String type, String price,int count, String pName, String pId, String shopid) {
        CommitProduct commitProduct = new CommitProduct();
        commitProduct.setType(type);
        commitProduct.setpPrice(price);
        commitProduct.setpName(pName);
        commitProduct.setpsId(pId);
        commitProduct.setShopId(shopid);
        commitProduct.setNumber(count);
        commitList.add(commitProduct);
        commitProduct = null;
    }
    private void dataChanged(float total2) {
        // 构造方法的字符格式这里如果小数不足2位,会以0补足.
        DecimalFormat decimalFormat = new DecimalFormat();
        // format 返回的是字符串
        String priceNum = decimalFormat.format(total2);
        // 通知listView刷新
        adapter.notifyDataSetChanged();
        shoppingCarPrice.setText(String.format(shoppingCarPrice.getTag().toString(), priceNum));

        if(checkNum == bean.getPagelist().size()){
            shoppingCarCheckbox.setChecked(true);
        }else {
            shoppingCarCheckbox.setChecked(false);
        }
    }
    @Override
    protected void initData() {

    }

    /**
     * 是否为全选状态，true为全选状态，false为不是全选状态
     */
    boolean isChooseAll=false;
    @OnClick({R.id.shopping_car_back, R.id.shopping_car_checkbox, R.id.shopping_car_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shopping_car_back:
                finish();
                break;
            case R.id.shopping_car_checkbox:
                checkChooseAll();
                break;
            case R.id.shopping_car_commit:
                commitList.clear();
                for (int i = 0; i < ShopCarAdapter.getIsSelected().size(); i++) {
                    if (ShopCarAdapter.getIsSelected().get(i)) {
                        ShoppingCarBean.PagelistBean item = bean.getPagelist().get(i);
                        addCommitList(item.getType(),item.getInfo().getNprice(),Integer.valueOf(item.getCount()),
                                item.getInfo().getName(),item.getPs_id(),item.getInfo().getShop_id());
//                        deleteShoppingCar(item.getPs_id());
                    }
                }
                IntentDataForCommitOrderActivity data = new IntentDataForCommitOrderActivity();
                data.commitProductList = commitList;
                CommitOrderActivity.startActivity(this, data, false);

                break;
            default:
                break;
        }
    }

    /**
     *  //全选
     */
    private void checkChooseAll() {
        //为全选时，遍历设置为全不选
        if (isChooseAll) {
            // 遍历list的长度，设为未选
            for (int i = 0; i < bean.getPagelist().size(); i++) {
                ShopCarAdapter.getIsSelected().put(i, false);
                checkNum=0;// 数量减1
                total = 0.00f;
            }
            // 刷新listview和TextView的显示
            dataChanged(total);
            isChooseAll = false;
        }else{
            // 当不是全选时，设置为全选
            total = 0.00f;
            // 遍历list的长度，设为已选
            checkNum = bean.getPagelist().size();
            for (int i = 0; i < bean.getPagelist().size(); i++) {
                ShopCarAdapter.getIsSelected().put(i, true);
                total += Float.valueOf(bean.getPagelist().get(i).getInfo().getNprice())* Float.valueOf(bean.getPagelist().get(i).getCount());
            }
            // 刷新listview和TextView的显示
            dataChanged(total);
            //保存全选状态
            isChooseAll = true;
        }
    }


    @Override
    protected void networkResponse(Message msg) {
        switch (msg.what) {
            case REQUEST_SHOPPING_CAR_LIST:
                Gson gson = new Gson();
                bean = gson.fromJson((String) msg.obj, ShoppingCarBean.class);
                if (bean.getCode() == 1) {
                    shoppingCarNum.setText(String.format(shoppingCarNum.getTag().toString(), bean.getPagelist().size() + ""));
                    TextViewUtils.changeFontSize(shoppingCarNum, 3, shoppingCarNum.getText().toString().trim().length(), getResources().getColor(R.color.white), 28);
                    adapter.setEntities(bean.getPagelist());
                    shoppingCarPrice.setText(String.format(shoppingCarPrice.getTag().toString(), OrderUtils.totlePriceCar(bean.getPagelist())));
//                    shoppingCarCommit.setText(String.format(shoppingCarCommit.getTag().toString(), OrderUtils.totlePriceCar(bean.getPagelist())));

                }
                break;
            case DELETE_SHOPPING_CAR_PRODUCT:
                //删除购物车内某商品
                Gson gson4 = new Gson();
                CommentResultBean commitBean4 = gson4.fromJson((String) msg.obj, CommentResultBean.class);
                if (commitBean4.getMsg() != null) {
                    Toast.makeText(this, commitBean4.getMsg(), Toast.LENGTH_SHORT).show();
                }
                getShoppingCarList();

                break;
            case EDIT_SHOPPING_CAR_NUM:
                //修改购物车内某商品数量
                Gson gson5 = new Gson();
                CommentResultBean commitBean5 = gson5.fromJson((String) msg.obj, CommentResultBean.class);
                if (commitBean5.getMsg() != null) {
                    Toast.makeText(this, commitBean5.getMsg(), Toast.LENGTH_SHORT).show();
                }
                getShoppingCarList();
                break;
            default:
                break;
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
        OkhttpRequestUtil.post(ConstanUrl.CART_DELETE, requestBody, DELETE_SHOPPING_CAR_PRODUCT, uiHandler, true);
    }

    /**
     * 修改购物车内某商品的数量
     *
     * @param id    商品id
     * @param count
     */
    public void editNumShoppingCar(String id, String type, String count) {
        RequestBody requestBody = new FormBody.Builder()
                .add("type", type)
                .add("ps_id", id)
                .add("count", count)
                .build();
        OkhttpRequestUtil.post(ConstanUrl.CART_CHANGECOUNT, requestBody, EDIT_SHOPPING_CAR_NUM, uiHandler, true);
    }


}
