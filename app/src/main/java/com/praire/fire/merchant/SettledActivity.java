package com.praire.fire.merchant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.merchant.adapter.SetledAdapter;
import com.praire.fire.merchant.bean.ShopTypeBeanList;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.RecycleViewDivider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

public class SettledActivity extends BaseActivity {


    private RecyclerView mRecyclerView;
    private List<ShopTypeBeanList.ListBean> mDatas;
    private SetledAdapter adapter;

    List<String> typelist=new ArrayList<>();
    Map<String,String> map=new HashMap<>();
    String typeText="";
    String typeId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settled);
        ButterKnife.bind(this);
        mRecyclerView = findViewById(R.id.recyclerview);

        initView();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_settled;
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

    private void initView() {

        String str=new UseAPIs().getShopType();
        ShopTypeBeanList s=new J2O().getShopType(str);
        mDatas=s.getList();
        for (int i=0;i<mDatas.size();i++) {
            typelist.add(mDatas.get(i).getId());
            map.put(mDatas.get(i).getId(),mDatas.get(i).getName());
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, 2, getResources().getColor(R.color.grey)));
        adapter = new SetledAdapter(this);
        adapter.setData(mDatas);
        adapter.setmOnItemClickListener(new SetledAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(SettledActivity.this, map.get(typelist.get(position)), Toast.LENGTH_SHORT).show();
                typeText=map.get(typelist.get(position));
                typeId=typelist.get(position);
                back();
            }
        });


        mRecyclerView.setAdapter(adapter);
    }

    public void back(){
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        bundle.putString("typeText", typeText);//添加要返回给页面1的数据
        bundle.putString("typeId", typeId);//添加要返回给页面1的数据
        intent.putExtras(bundle);
        this.setResult(Activity.RESULT_OK, intent);//返回页面1
        this.finish();
    }

}
