package com.praire.fire.merchant;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.merchant.adapter.RegionAdapter;
import com.praire.fire.merchant.adapter.SetledAdapter;
import com.praire.fire.merchant.bean.RegionListBean;
import com.praire.fire.merchant.bean.ShopTypeBeanList;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class RegionChooseActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private List<RegionListBean.ListBean.SonBeanX.SonBean> mDatas;
    private RegionAdapter adapter;

    List<String>  countys=new ArrayList<>();
    String county="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region_choose);
        ButterKnife.bind(this);
        mRecyclerView = findViewById(R.id.recyclerview);

        initView();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_region_choose;
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

        String str=new UseAPIs().getRegion();
        System.out.println(str);
        RegionListBean s=new J2O().getRegion(str);
        System.out.println(s.getList().get(0).getSon().get(0).getSon().get(0).getName());
        mDatas=s.getList().get(0).getSon().get(0).getSon();

        for (int i=0;i<mDatas.size();i++) {
            countys.add(mDatas.get(i).getName());
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, 2, getResources().getColor(R.color.grey)));
        adapter = new RegionAdapter(this);
        adapter.setData(mDatas);
        adapter.setmOnItemClickListener(new RegionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RegionChooseActivity.this, countys.get(position), Toast.LENGTH_SHORT).show();
                county=countys.get(position);
                back();

            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    public void back(){
        Log.d("county", county);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        bundle.putString("county", county);//添加要返回给页面1的数据
        intent.putExtras(bundle);
        this.setResult(Activity.RESULT_OK, intent);//返回页面1
        this.finish();
    }
}
