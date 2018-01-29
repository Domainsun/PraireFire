package com.praire.fire.car;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.car.adapter.ALLEvalauteAdapter;
import com.praire.fire.car.adapter.ShopEvalauteAdapter;
import com.praire.fire.car.bean.AllProductEvalauteBean;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.utils.RecycleViewDivider;
import com.praire.fire.utils.TextViewUtils;
import com.praire.fire.utils.statusbarcolor.Eyes;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商品评价列表
 * 用户评价
 * Created by lyp on 2018/1/4.
 */

public class ShopAllEvalauteActivity extends BaseTitleActivity {

    @BindView(R.id.all_evalaute_count)
    TextView allCount;
    @BindView(R.id.all_evalaute_recyclerView)
    SwipeMenuRecyclerView allRecyclerView;
    private String businessId;
    private ALLEvalauteAdapter adapterEvaluate;
    /**
     * 0 全部，1 商品，2服务
     */
    int types;

    private int index = 1;
    private boolean isFrist = true;
    private List<AllProductEvalauteBean.PagelistBean> evalautelist = new ArrayList<>();

    public static void startActivity(Context context, String businessId, String allcount, int type, boolean forResult) {
        Intent intent = new Intent(context, ShopAllEvalauteActivity.class);
        intent.putExtra(Constants.BUSSINESS_ID, businessId);
        intent.putExtra(Constants.COUNT, allcount);
        intent.putExtra(Constants.UI_TYPE, type);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_shop_product_evalaute;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        Eyes.setStatusBarColor(this, ContextCompat.getColor(this, R.color.status_bar));
    }

    @Override
    protected void initListeners() {
        types = getIntent().getIntExtra(Constants.UI_TYPE, 0);
        businessId = getIntent().getStringExtra(Constants.BUSSINESS_ID);
        allCount.setText(String.format(allCount.getTag().toString(), (String) getIntent().getStringExtra(Constants.COUNT)));
        TextViewUtils.changeFontSize(allCount, 4, allCount.getText().toString().length(), getResources().getColor(R.color.orange), 30);

    }

    @Override
    protected void initAdapters() {
        allRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        allRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        allRecyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL));
        adapterEvaluate = new ALLEvalauteAdapter(this);
        allRecyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
//                ProductEvalauteInfoActivity.startActivity(ShopAllEvalauteActivity.this, evalautelist.get(position).getId(), false);

            }
        });
        allRecyclerView.useDefaultLoadMore();
        allRecyclerView.setLoadMoreListener(new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                isFrist = false;
                index++;
                requestData(index);
            }
        });
        allRecyclerView.setAdapter(adapterEvaluate);
    }

    @Override
    protected void initData() {
        isFrist = true;
        index = 1;
        requestData(index);
    }

    private void requestData(int ind) {
        if (types == 0) {
            OkhttpRequestUtil.get(ConstanUrl.COMMONINFO_COMMENTLIST + "?shop_id=" + businessId + "&p=" + ind, 1, true, uiHandler);
        } else {
            OkhttpRequestUtil.get(ConstanUrl.COMMONINFO_COMMENTLIST + "?id=" + businessId + "&type=" + types + "&p=" + ind, 1, true, uiHandler);
        }
    }

    @Override
    public void initiTile() {
        setDefaultBack();
        setTitleMiddle(getString(R.string.user_evaluate));
    }

    @Override
    protected void networkResponse(Message msg) {
        // 数据完更多数据，一定要调用这个方法。
        // 第一个参数：表示此次数据是否为空。
        // 第二个参数：表示是否还有更多数据。
        allRecyclerView.loadMoreFinish(false, true);
        switch (msg.what) {
            case 1:
                if (isFrist) {
                    evalautelist.clear();
                }
                Gson gson = new Gson();
                AllProductEvalauteBean evalaute = gson.fromJson((String) msg.obj, AllProductEvalauteBean.class);
                evalautelist.addAll(evalaute.getPagelist());
                adapterEvaluate.setEntities(evalautelist);
                break;
            default:
                break;
        }
    }
}
