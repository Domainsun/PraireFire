package com.praire.fire.my.setActivitys;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.common.Constants;
import com.praire.fire.merchant.adapter.ServiceAdapter;
import com.praire.fire.my.adapter.BankAdapter;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.BankBean;
import com.praire.fire.okhttp.JavaBean.ServiceListBean;
import com.praire.fire.okhttp.UseAPIs;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChooseBankActivity extends BaseActivity {
    SwipeMenuRecyclerView recyclerView;
    List<BankBean.BanklistBean> mDatas = new ArrayList<>();
    BankAdapter adapter;

    UseAPIs u=new UseAPIs();
    J2O j=new J2O();



    public static void startActivity(Context context, boolean forResult) {
        Intent intent = new Intent(context, ChooseBankActivity.class);

        if (!forResult) {
            context.startActivity(intent);
        } else if (context instanceof BaseActivity) {
            ((BaseActivity) context).startActivityForResult(intent, Constants.REQUEST_BANK);
        }
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_choose_bank;
    }

    @Override
    protected void initViews() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BankAdapter(this);
    }

    @Override
    protected void initListeners() {
        adapter.setmOnItemClickListener(new BankAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, String id) {
                String bankName=mDatas.get(position).getName();
                Intent i=new Intent();
                i.putExtra("bankId",id);
                i.putExtra("bankName",bankName);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }

    @Override
    protected void initAdapters() {
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void initData() {

        try{
           String str= u.getBankList();
            BankBean o =j.getBankList(str);
            mDatas=o.getBanklist();
            adapter.setData(mDatas);
        }catch (Exception e){


        }


    }


}
