package com.praire.fire.merchant;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.praire.fire.R;
import com.praire.fire.merchant.adapter.TodayIncomeAdapter;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.TodayIncomeBean;
import com.praire.fire.okhttp.UseAPIs;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryIncomeActivity extends AppCompatActivity {

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
    TodayIncomeAdapter adapter;


    private List<TodayIncomeBean.PagelistBean> Datas;


    List<String> year=new ArrayList<>();
    List<String> mon=new ArrayList<>();
    List<String> day=new ArrayList<>();

    HashMap<String, List<String>> monMap = new HashMap<String, List<String>>();
    HashMap<String, List<String>> dayMap = new HashMap<String, List<String>>();

    int currentYear=2018;
    int currentMon=1;
    int currentday=1;

    WheelView yearWheelView, monWheelView,dayWheelView,year1WheelView, mon1WheelView,day1WheelView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_income);
        ButterKnife.bind(this);
        initdatedata();
    }

    @OnClick({R.id.tv_back, R.id.iv_choose_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:

                break;
            case R.id.iv_choose_date:

                show1();

                break;
        }
    }




    public void initdatedata(){
        for (int i=0;i<31;i++) {
            String day1=String.valueOf(currentday);
            day.add(String.valueOf(currentday));
            currentday++;
        }
        for (int i=0;i<12;i++) {
            String mon1=String.valueOf(currentMon);
            mon.add(String.valueOf(currentMon));
            dayMap.put(mon1,day);
            currentMon++;
        }
        for (int i=0;i<50;i++) {
            String year1=String.valueOf(currentYear);
            year.add(year1);
            monMap.put(year1,mon);
            currentYear++;
        }
    }

    private void show1() {
        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.select_income_date_dialog, null);
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextSize = 20;
        style.textSize = 16;
        yearWheelView = (WheelView) contentView.findViewById(R.id.year_wheelview);
        year1WheelView = (WheelView) contentView.findViewById(R.id.year1_wheelview);
        monWheelView = (WheelView) contentView.findViewById(R.id.mon_wheelview);
        mon1WheelView = (WheelView) contentView.findViewById(R.id.mon1_wheelview);
//        dayWheelView = (WheelView) contentView.findViewById(R.id.day_wheelview);
//        day1WheelView = (WheelView) contentView.findViewById(R.id.day1_wheelview);



        yearWheelView.setWheelAdapter(new ArrayWheelAdapter(this));
        yearWheelView.setSkin(WheelView.Skin.Holo);
        yearWheelView.setWheelData(year);
        yearWheelView.setStyle(style);


        monWheelView.setWheelAdapter(new ArrayWheelAdapter(this));
        monWheelView.setSkin(WheelView.Skin.Holo);
        monWheelView.setWheelData(monMap.get(year.get(yearWheelView.getSelection())));
        monWheelView.setStyle(style);
        yearWheelView.join(monWheelView);
        yearWheelView.joinDatas(monMap);

//        dayWheelView.setWheelAdapter(new ArrayWheelAdapter(this));
//        dayWheelView.setSkin(WheelView.Skin.Holo);
//        dayWheelView.setWheelData(dayMap.get(mon.get(monWheelView.getSelection())));
//        dayWheelView.setStyle(style);
//        monWheelView.join(dayWheelView);
//        monWheelView.joinDatas(dayMap);


        year1WheelView.setWheelAdapter(new ArrayWheelAdapter(this));
        year1WheelView.setSkin(WheelView.Skin.Holo);
        year1WheelView.setWheelData(year);
        year1WheelView.setStyle(style);


        mon1WheelView.setWheelAdapter(new ArrayWheelAdapter(this));
        mon1WheelView.setSkin(WheelView.Skin.Holo);
        mon1WheelView.setWheelData(monMap.get(year.get(year1WheelView.getSelection())));
        mon1WheelView.setStyle(style);
        year1WheelView.join(mon1WheelView);
        year1WheelView.joinDatas(monMap);

//        day1WheelView.setWheelAdapter(new ArrayWheelAdapter(this));
//        day1WheelView.setSkin(WheelView.Skin.Holo);
//        day1WheelView.setWheelData(dayMap.get(mon.get(mon1WheelView.getSelection())));
//        day1WheelView.setStyle(style);
//        mon1WheelView.join(day1WheelView);
//        mon1WheelView.joinDatas(dayMap);


        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();
        TextView tv_confirm = contentView.findViewById(R.id.tv_ok);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancel);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                bottomDialog.dismiss();
            }
        });

        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog.dismiss();
            }
        });
    }
}
