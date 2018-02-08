package com.praire.fire.my;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.base.BaseTitleActivity;
import com.praire.fire.common.ConstanUrl;
import com.praire.fire.common.Constants;
import com.praire.fire.my.adapter.IntegralAdapter;
import com.praire.fire.my.bean.IntegralBean;
import com.praire.fire.okhttp.OkhttpRequestUtil;
import com.praire.fire.utils.RecycleViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 积分详情
 * Created by lyp on 2018/2/8.
 */

public class IntegralDetailActivity extends BaseTitleActivity implements TabLayout.OnTabSelectedListener {
    @BindView(R.id.integral_score)
    TextView integralScore;
    @BindView(R.id.integral_used_score)
    TextView integralUsedScore;
    @BindView(R.id.integral_tab)
    TabLayout integralTab;
    @BindView(R.id.integral_recyclerview)
    RecyclerView irecyclerView;
    @BindView(R.id.integral_refreshLayout)
    SmartRefreshLayout iRefreshLayout;
    private String statusType = "0";
    private boolean loadMore = true;
    private IntegralAdapter adapter;
    private List<IntegralBean.PagelistBean> beans = new ArrayList<>();
    private boolean isFrist = true;
    private int index = 1;

    public static void startActivity(Context context, String id, boolean forResult) {
        Intent intent = new Intent(context, IntegralDetailActivity.class);
        intent.putExtra(Constants.PRODUCT_ID,id);
        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_integral;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        integralTab.addTab(integralTab.newTab().setText("全部"));
        integralTab.addTab(integralTab.newTab().setText("已赚积分"));
        integralTab.addTab(integralTab.newTab().setText("已用积分"));
        integralTab.setOnTabSelectedListener(this);
    }

    @Override
    protected void initListeners() {


        iRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        iRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                isFrist = true;
                index = 1;
                if("0".equals(statusType)){
                    initData();
                }else {
                    setDatas();
                }
                //加载失败的话2秒后结束加载
//                refreshlayout.finishRefresh(2000  /*,false*/  );//传入false表示刷新失败
            }
        });
        iRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (loadMore) {
                    getNextPage();
                    //加载失败的话2秒后结束加载
//                    refreshlayout.finishLoadmore(2000 /*,false*/);//传入false表示加载失败
                } else {
                    refreshlayout.finishLoadmoreWithNoMoreData();
                }

            }
        });
    }

    private void getNextPage() {


            isFrist = false;
            index++;
        initData();
    }

    @Override
    protected void initAdapters() {
        irecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //添加分割线
        irecyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL));
        irecyclerView.setItemAnimator(new DefaultItemAnimator());

        setItemClick();
    }

    @Override
    protected void initData() {

        OkhttpRequestUtil.get(ConstanUrl.CREDIT_INDEX+"?p="+index, 1, true, uiHandler);
    }

    private void setDatas() {
        OkhttpRequestUtil.get(ConstanUrl.CREDIT_INDEX + "?type=" + statusType+"&p="+index, 1, true, uiHandler);
    }

    @Override
    public void initiTile() {
        setDefaultBack();
        setTitleMiddle("积分详情");
    }

    private void setItemClick() {
        adapter = null;
        adapter = new IntegralAdapter(this);
        irecyclerView.setAdapter(adapter);
        adapter.setItemClickLister(new IntegralAdapter.ItemClickLister() {

            @Override
            public void itemClick(View itemView, int position) {

            }
        });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                isFrist = true;
                index = 1;
                statusType = "0";
                setItemClick();
                initData();
                break;
            case 1:
                isFrist = true;
                index = 1;
                statusType = "1";
                setItemClick();
                setDatas();
                break;
            case 2:
                statusType = "2";
                isFrist = true;
                index = 1;
                setItemClick();
                setDatas();
                break;
            default:
                break;
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override
    protected void networkResponse(Message msg) {
        //结束加载
        iRefreshLayout.finishRefresh();
        iRefreshLayout.finishLoadmore();
        Log.e("msgIntegral", (String) msg.obj);
        switch (msg.what) {
            case 1:
                if (isFrist) {
                    beans.clear();
                }
                Gson gson = new Gson();
                IntegralBean integralBean = gson.fromJson((String) msg.obj,IntegralBean.class);
                beans.addAll(integralBean.getPagelist());
                adapter.setData(beans);
                break;
            default:
                break;
        }
    }
}
