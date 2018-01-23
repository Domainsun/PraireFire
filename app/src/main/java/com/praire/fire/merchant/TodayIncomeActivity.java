package com.praire.fire.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.praire.fire.R;
import com.praire.fire.merchant.adapter.TodayIncomeAdapter;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.ServiceListBean;
import com.praire.fire.okhttp.JavaBean.TodayIncomeBean;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;

public class TodayIncomeActivity extends AppCompatActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_history_income)
    TextView tvHistoryIncome;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_show_total_income)
    TextView tvShowTotalIncome;
    @BindView(R.id.ll_income)
    RelativeLayout llIncome;
    @BindView(R.id.tv_income_count1)
    TextView tvIncomeCount1;

    UseAPIs u = new UseAPIs();
    J2O j = new J2O();
    String cookie;

    int index = 1;
    Boolean dataEmpty = false;
    Boolean hasMore = true;

    SwipeMenuRecyclerView recyclerView;
    SwipeRefreshLayout mRefreshLayout;
    TodayIncomeAdapter adapter;


    private List<TodayIncomeBean.PagelistBean> Datas=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);

        initview();
//        initdata();

    }

    private void initview() {
        cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new TodayIncomeAdapter(this);


//        recyclerView.addItemDecoration(new RecycleViewDivider(
//               this, LinearLayoutManager.HORIZONTAL));

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.useDefaultLoadMore(); // 使用默认的加载更多的View。
        mRefreshLayout.setOnRefreshListener(mRefreshListener); // 刷新监听。
        recyclerView.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。

        initdata(getTodayDate());


    }


    private void initdata(String date) {
        recyclerView.loadMoreFinish(false, true);
        for (int i=0;i<Datas.size();i++) {
            Datas.remove(i);
        }

        String str = "";
        str = u.getTodayIncome(cookie, date,"1");
        if (str.length() != 0) {
            Log.d("initdata", "initdata: \n" + str);
            TodayIncomeBean s = j.geTodayIncome(str);
            Datas = s.getPagelist();
            adapter.setData(Datas);
            recyclerView.setAdapter(adapter);

            tvDate.setText(s.getDate() + " · 全部收入");
            tvIncomeCount1.setText("共计" + s.getAllcount() + "笔");
            tvShowTotalIncome.setText(s.getAllincome());
        } else {
            Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show();
        }

    }


    @OnClick({R.id.tv_back, R.id.tv_history_income, R.id.ll_income})
    public void onViewClicked(View view) {
        Intent i = new Intent(this, HistoryIncomeActivity.class);
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_history_income:

                startActivity(i);
                break;
            case R.id.ll_income:
//                startActivity(i);
                break;
        }
    }

    public  String getTodayDate(){
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }




    /* 下拉刷新*/
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            initdata(getTodayDate());
            adapter.notifyDataSetChanged();
            mRefreshLayout.setRefreshing(false);
            index = 1;
            dataEmpty = false;
            hasMore = true;
            Toast.makeText(TodayIncomeActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {

            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {

                    TodayIncomeActivity.this.index++;
                    String index = String.valueOf(TodayIncomeActivity.this.index);
                    List<TodayIncomeBean.PagelistBean> data = new ArrayList<>();
                    String str = "";
                    str = u.getTodayIncome(cookie, getTodayDate(),index);
                    if (str.length() != 0) {
                        TodayIncomeBean s = j.geTodayIncome(str);

                        for (int i = 0; i < s.getPagelist().size(); i++) {
                            Datas.add(s.getPagelist().get(i));
                        }

                        if (s.getPagelist().size() == 0) {
                            dataEmpty = true;
                        }
                        if (s.getPagelist().size() < 10) {
                            hasMore = false;
                        }
                        adapter.notifyDataSetChanged();
                        recyclerView.loadMoreFinish(dataEmpty, hasMore);
                    } else {
                        Toast.makeText(TodayIncomeActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                    }

                }
            }, 1000);
        }

    };

}
