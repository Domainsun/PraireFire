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
import com.praire.fire.car.adapter.ShopEvalauteAdapter;
import com.praire.fire.common.CommonDialog;
import com.praire.fire.my.adapter.ShopCarAdapter;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.my.bean.ShoppingCarBean;
import com.praire.fire.okhttp.OkhttpRequestUtil;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
            OkhttpRequestUtil.get(ConstanUrl.CART_DELETE + "?id=" + id, DELETE_SHOPPING_CAR_PRODUCT, true, uiHandler);
        }
    };

    @Override
    protected void initListeners() {

        OkhttpRequestUtil.get(ConstanUrl.CART_CARTLIST + "?p=" + index, REQUEST_SHOPPING_CAR_LIST, true, uiHandler);
//        点击 选中
        recyclerview.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
//itemView.findViewById()
            }
        });

       /* //长按  删除
        recyclerview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {

                Looper.prepare();
                CommonDialog.Build(ShoppingCarActivity.this).setTitle("删除商品").setMessage("确定从购物车内删除此商品？").setConfirm(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = "0";

                    }
                }).showconfirm();
                Looper.loop();
                return false;
            }
        });*/
    }


    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.shopping_car_back, R.id.shopping_car_checkbox, R.id.shopping_car_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shopping_car_back:
                finish();
                break;
            case R.id.shopping_car_checkbox:

                break;
            case R.id.shopping_car_commit:

                break;
            default:
                break;
        }
    }


    @Override
    protected void networkResponse(Message msg) {
        switch (msg.what) {
            case REQUEST_SHOPPING_CAR_LIST:
                Gson gson = new Gson();
                bean = gson.fromJson((String) msg.obj, ShoppingCarBean.class);
                shoppingCarNum.setText(String.format(shoppingCarNum.getTag().toString(), bean.getPagelist().size() + ""));
                TextViewUtils.changeFontSize(shoppingCarNum, 3, shoppingCarNum.getText().toString().trim().length(), getResources().getColor(R.color.white), 28);
//                setBaseInfo();
                adapter.setEntities(bean.getPagelist());
                break;
            case DELETE_SHOPPING_CAR_PRODUCT:

                /*Gson gson1 = new Gson();
                bean = gson1.fromJson((String) msg.obj, ShoppingCarBean.class);
                shoppingCarNum.setText(String.format(shoppingCarNum.getTag().toString(),bean.getPagelist().size()+""));
                TextViewUtils.changeFontSize(shoppingCarNum,3,shoppingCarNum.getText().toString().trim().length(),getResources().getColor(R.color.white),28);
//                setBaseInfo();
                adapter.setEntities(bean.getPagelist());*/
                break;
            default:
                break;
        }
    }

}
