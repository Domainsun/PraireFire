package com.praire.fire.my;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.my.adapter.AccountBillAdapter;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.AccountBillBean;
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

/**
 * 钱包明细
 * 账单明细
 * Created by lyp on 2017/12/29.
 */

public class BillActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_screen)
    TextView tvScreen;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_income_expend)
    TextView tvIncomeExpend;
    @BindView(R.id.iv_choose_date)
    ImageView ivChooseDate;
    @BindView(R.id.tv_show_date)
    TextView tvShowDate;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;

    private PopupWindow mPopWindow;

    UseAPIs u = new UseAPIs();
    J2O j = new J2O();
    SwipeMenuRecyclerView recyclerView;
    SwipeRefreshLayout mRefreshLayout;

    AccountBillAdapter adapter;

    private List<AccountBillBean.PagelistBean> mDatas = new ArrayList<>();

    int index = 1;
    Boolean dataEmpty = false;
    Boolean hasMore = true;

    String cookie = "";


    TimePickerView pvTime;
    Boolean choose = false;

    String startDate = "";
    String endDate = "";

    String type = "";

    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, BillActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_CODE_COMMONT);
        }
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_bill;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        initTimePicker();
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new AccountBillAdapter(this);
    }

    @Override
    protected void initListeners() {
        mRefreshLayout.setOnRefreshListener(mRefreshListener); // 刷新监听。
        recyclerView.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {
        cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");

        loadData("", "");
        recyclerView.setAdapter(adapter);
    }

    private void loadData(String startdate, String enddate) {

        mDatas.clear();

        String str = "";
        try {
            str = u.getAccountBill(cookie, startdate, enddate, "1", type);

            if (str.length() != 0) {

                AccountBillBean s = j.getAccountBill(str);
                mDatas = s.getPagelist();
                adapter.setData(mDatas);
                recyclerView.loadMoreFinish(false, true);

            } else {
                Toast.makeText(this, "网络错误！", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "网络错误！", Toast.LENGTH_SHORT).show();

        }


    }

    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            loadData("", "");
            adapter.notifyDataSetChanged();


            mRefreshLayout.setRefreshing(false);
            index = 1;
            dataEmpty = false;
            hasMore = true;
            Toast.makeText(BillActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
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

                    BillActivity.this.index++;
                    String index = String.valueOf(BillActivity.this.index);
                    List<AccountBillBean.PagelistBean> data = new ArrayList<>();
                    String str = "";
                    str = u.getAccountBill(cookie, startDate, endDate, index, type);
                    if (str.length() != 0) {
                        AccountBillBean s = j.getAccountBill(str);

                        for (int i = 0; i < s.getPagelist().size(); i++) {
                            mDatas.add(s.getPagelist().get(i));
                        }

                        if (s.getPagelist().size() == 0) {
                            dataEmpty = true;
                        }
                        if (s.getPagelist().size() < 10) {
                            hasMore = false;
                        }
                        adapter.notifyDataSetChanged();
                        adapter.notifyItemInserted(1);
                        recyclerView.loadMoreFinish(dataEmpty, hasMore);
                    } else {
                        Toast.makeText(BillActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
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
        startDate.set(2016, 0, 0);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(2050, 11, 31);
        //时间选择器
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/
                String str = getTime(date);


                TextView tv = (TextView) v;
                if (!choose) {
                    tv.setText(str + "-");
                    ftime = str;
                    choose = true;
                    BillActivity.this.startDate = str;
                    Toast.makeText(BillActivity.this, "请选择结束时间", Toast.LENGTH_SHORT).show();
                } else {
                    tv.setText(ftime + " 至 " + str);
                    choose = false;
                    BillActivity.this.endDate = str;


                    loadData(BillActivity.this.startDate, BillActivity.this.endDate);

                    Log.d("s e", "s e: "+startDate  +"    "+endDate);



                    adapter.notifyDataSetChanged();
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

    @SuppressLint("WrongConstant")
    private void showPopupWindow(View view) {

        View contentView = LayoutInflater.from(BillActivity.this).inflate(R.layout.pop_bill_search, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);


        mPopWindow.setFocusable(true);
        mPopWindow.setOutsideTouchable(true); // 设置允许在外点击消失
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());// 设置背景，这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        mPopWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);    //必须加这两行，不然不会显示在键盘上方
        mPopWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mPopWindow.showAsDropDown(view); // PopupWindow的显示及位置设置


        //设置各个控件的点击响应
        Button btn_recharge = contentView.findViewById(R.id.btn_recharge);
        Button btn_withdraw = contentView.findViewById(R.id.btn_withdraw);
        Button btn_refund = contentView.findViewById(R.id.btn_refund);
        Button btn_consumption = contentView.findViewById(R.id.btn_consumption);
        Button btn_income = contentView.findViewById(R.id.btn_income);
        Button btn_all = contentView.findViewById(R.id.btn_all);

        btn_recharge.setOnClickListener(this);
        btn_withdraw.setOnClickListener(this);
        btn_refund.setOnClickListener(this);
        btn_consumption.setOnClickListener(this);
        btn_income.setOnClickListener(this);
        btn_all.setOnClickListener(this);

        //显示PopupWindow
        View rootview = LayoutInflater.from(BillActivity.this).inflate(R.layout.activity_bill, null);



//        mPopWindow.showAsDropDown(view, 0, 0);


    }

    Boolean b_screen=true;
//    Drawable drawable=
    @OnClick({R.id.tv_screen, R.id.tv_search, R.id.iv_choose_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_screen:
                if (b_screen) {
                    tvScreen.setCompoundDrawables(null, null, getResources().getDrawable(R.mipmap.triangle2), null);
                    b_screen=false;
                } else {
                    tvScreen.setCompoundDrawables(null, null, getResources().getDrawable(R.mipmap.triangle), null);
                    b_screen=true;
                }
                showPopupWindow(rlTitle);


                break;
            case R.id.tv_search:


                break;
            case R.id.iv_choose_date:
                pvTime.show(tvShowDate);
                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_recharge:
                type = "1";

                break;
            case R.id.btn_withdraw:
                type = "4";
                break;
            case R.id.btn_refund:
                type = "6";
                break;
            case R.id.btn_consumption:
                type = "3";
                break;
            case R.id.btn_income:
                type = "5";
                break;
            case R.id.btn_all:
                type = "";
                break;
        }
        Log.d("type", "type: "+type);
        loadData("","");
        adapter.notifyDataSetChanged();
        index = 1;
        dataEmpty = false;
        hasMore = true;

        mPopWindow.dismiss();
    }


}
