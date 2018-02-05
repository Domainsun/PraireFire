package com.praire.fire.map;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nex3z.flowlayout.FlowLayout;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.merchant.bean.ShopTypeBeanList;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SaveArrayListUtil;
import com.praire.fire.utils.ToastUtil;
import com.praire.fire.utils.statusbarcolor.Eyes;

import java.util.ArrayList;
import java.util.List;

/**
 * 地图上 搜索商家
 * Created by lyp on 2018/1/9.
 */

public class MapSearchActivity extends BaseActivity implements View.OnClickListener, TabLayout.OnTabSelectedListener {

    private FlowLayout mFlowLayout;
    private TextView clean;
    private ArrayList<String> searchEntities = new ArrayList<>();
    private ShopTypeBeanList typeEntities;
    SaveArrayListUtil save;
    private String searchKey;
    EditText edtSearch;
    TabLayout tabLayout;
    ImageView back;

    public static void startActivity(Context context, String key, boolean forResult) {
        Intent intent = new Intent(context, MapSearchActivity.class);
        intent.putExtra(Constants.SEARCH_KEY, key);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_SEARCH);
        }
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_map_search;
    }

    @Override
    protected void initViews() {
        Eyes.setStatusBarColor(this, ContextCompat.getColor(this, R.color.status_bar));
        clean = findViewById(R.id.search_clear);
        edtSearch = findViewById(R.id.plug_search_edittext);
        tabLayout = findViewById(R.id.search_tabs);
        back = findViewById(R.id.search_bar2_back);
        requestShopTypeList();
    }

    @Override
    protected void initListeners() {
        getIntentDatas();
        save = new SaveArrayListUtil();
        searchEntities = save.getSearchArrayList(this);
        if (!TextUtils.isEmpty(searchKey)) {
            edtSearch.setText(searchKey);
            //保存
//            save.saveArrayList(MapSearchActivity.this, searchEntities, searchKey);
//            goSearch();
        }
        clean.setOnClickListener(this);
        back.setOnClickListener(this);

    }

    @Override
    protected void initAdapters() {
//输入关键字 确定搜索
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean aBoolean = (actionId == 0 || actionId == 3) && event != null;
                if (aBoolean) {
                    searchKey = edtSearch.getText().toString();
                    if (TextUtils.isEmpty(searchKey)) {
                        ToastUtil.show(MapSearchActivity.this, "请输入搜索条件");
                        return false;
                    }
                    //保存
                    save.saveArrayList(MapSearchActivity.this, searchEntities, searchKey);
                    goSearch();
                    return true;


                }
                return false;

            }

        });
    }
    /**
     * 获取商家类型列表
     */
    private void requestShopTypeList() {
        String str=new UseAPIs().getShopType();
        Gson gson = new Gson();
        typeEntities = gson.fromJson(str, ShopTypeBeanList.class);
        for(ShopTypeBeanList.ListBean bean :typeEntities.getList()){
            tabLayout.addTab(tabLayout.newTab().setText(bean.getName()));
        }
        tabLayout.setOnTabSelectedListener(this);

    }



    /**
     * 带了数据的
     */
    protected void getIntentDatas() {
        searchKey = getIntent().getStringExtra(Constants.SEARCH_KEY);

        /*initData = (IntentDataForMarchantActivity) getIntent().getSerializableExtra(INTENT_DATA);
        if (initData != null) {
            isGroup = initData.groupNavParams.getFIsGroup();
            tradeId = initData.groupNavParams.getFTradeID();
            isOrder = initData.groupNavParams.getfIsOrder();
            tradeIdCN =initData.groupNavParams.getFTradeID_CN();
            isTakeOut = initData.groupNavParams.getfIsTakeOut();
        }*/
    }

    @Override
    protected void initData() {
        getList(searchEntities);
    }

    private void getList(List<String> list) {
        mFlowLayout = (FlowLayout) findViewById(R.id.flow_layout);
        /**
         * 根据集合动态生成控件TextView
         */
        for (final String b : list) {
            TextView textView = new TextView(this);
            textView.setPadding(16, 8, 16, 8);
            textView.setText(b);
            textView.setTextColor(getResources().getColor(R.color.grey));
            textView.setTextSize(12);
            textView.setBackgroundResource(R.drawable.shap_search_item_bg);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchKey = b;
                    goSearch();
                }
            });
            mFlowLayout.addView(textView);
        }
    }

    private void goSearch() {
        Intent intent  = new Intent();
        intent.putExtra(Constants.SEARCH_KEY, searchKey);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_clear:
                save.clear(this);
                searchEntities.clear();
                mFlowLayout.removeAllViews();
                break;
            case R.id.search_bar2_back:
                finish();
            default:
                break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
//        statusType = tab.getText().toString();
        searchKey = typeEntities.getList().get(tab.getPosition()).getName();
        goSearch();
       /* switch (tab.getPosition()) {
            case 0:
                statusType = tab.getText().toString();
                break;
            case 1:
                statusType = "56";

                break;
            case 2:
                statusType = "57";

                break;
            case 3:
                statusType = "58";

                break;
            case 4:
                statusType = "59";

                break;
            default:
                break;
        }*/
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
