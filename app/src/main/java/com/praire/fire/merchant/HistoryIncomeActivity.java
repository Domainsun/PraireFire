package com.praire.fire.merchant;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.merchant.adapter.HistoryIncomeAdapter;
import com.praire.fire.merchant.adapter.TodayIncomeAdapter;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.HistoryIncomeBean;
import com.praire.fire.okhttp.JavaBean.TodayIncomeBean;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;

public class HistoryIncomeActivity extends BaseActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_choose_date)
    ImageView ivChooseDate;


    UseAPIs u = new UseAPIs();
    J2O j = new J2O();
    String cookie;

    int index = 1;
    Boolean dataEmpty = false;
    Boolean hasMore = true;

    SwipeMenuRecyclerView recyclerView;
    SwipeRefreshLayout mRefreshLayout;
    HistoryIncomeAdapter adapter;
    @BindView(R.id.tv_date)
    TextView tvDate;


    private List<HistoryIncomeBean.PagelistBean> Datas=new ArrayList<>();


    TimePickerView pvTime;
    Boolean choose=false;

    String startDate="";
    String endDate="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_income);
        ButterKnife.bind(this);

        initTimePicker();
        initview();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_history_income;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_back, R.id.iv_choose_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.iv_choose_date:

                pvTime.show(tvDate);

                Log.d("onViewClicked", "onViewClicked: "+startDate+"\n"+endDate);
                break;
        }
    }








    private void initview() {
        cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new HistoryIncomeAdapter(this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.useDefaultLoadMore(); // 使用默认的加载更多的View。
        mRefreshLayout.setOnRefreshListener(mRefreshListener); // 刷新监听。
        recyclerView.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。

        initdata("","");

    }


    private void initdata(String startDate,String endDate) {
        recyclerView.loadMoreFinish(false, true);
        for (int i=0;i<Datas.size();i++) {
            Datas.remove(i);
        }

        String str = "";
        str = u.getHistoryIncome(cookie, "1",startDate,endDate);
        if (str.length() != 0) {
            Log.d("initdata", "initdata: \n" + str);
            HistoryIncomeBean s = j.getHistoryIncome(str);
            Datas = s.getPagelist();
            adapter.setData(Datas);
            recyclerView.setAdapter(adapter);

        } else {
            Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show();
        }

    }






    /* 下拉刷新*/
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            initdata("","");
            adapter.notifyDataSetChanged();
            mRefreshLayout.setRefreshing(false);
            index = 1;
            dataEmpty = false;
            hasMore = true;
            Toast.makeText(HistoryIncomeActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
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

                    HistoryIncomeActivity.this.index++;
                    String index = String.valueOf(HistoryIncomeActivity.this.index);
                    List<HistoryIncomeBean.PagelistBean> data = new ArrayList<>();
                    String str = "";
                    str = u.getHistoryIncome(cookie,index,startDate,endDate);
                    if (str.length() != 0) {
                        HistoryIncomeBean s = j.getHistoryIncome(str);

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
                        Toast.makeText(HistoryIncomeActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                    }

                }
            }, 1000);
        }

    };







    String ftime = "";
    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        final Calendar startDate = Calendar.getInstance();
        startDate.set(2018, 0, 0);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(2050, 11, 31);
        //时间选择器
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/
                String str=getTime(date);


                TextView tv = (TextView) v;
                if (!choose) {
                    tv.setText(str + "-");
                    ftime = str;
                    choose = true;
                    HistoryIncomeActivity.this.startDate=str;
                    Toast.makeText(HistoryIncomeActivity.this, "请选择结束时间", Toast.LENGTH_SHORT).show();
                } else {
                    tv.setText(ftime + " 至 " + str);
                    choose = false;
                    HistoryIncomeActivity.this.endDate=str;


                    initdata(HistoryIncomeActivity.this.startDate,HistoryIncomeActivity.this.endDate);
                    adapter.notifyDataSetChanged();
                    mRefreshLayout.setRefreshing(false);
                    index = 1;
                    dataEmpty = false;
                    hasMore = true;
//                    u.getHistoryIncome();
                }
            }
        })

                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
//                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

}
