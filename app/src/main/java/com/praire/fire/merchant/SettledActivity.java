package com.praire.fire.merchant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.praire.fire.R;
import com.praire.fire.merchant.adapter.SetledAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class SettledActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private SetledAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settled);
        ButterKnife.bind(this);
        mRecyclerView = findViewById(R.id.recyclerview);

        initData();
        initView();
    }

    private void initView() {
        adapter = new SetledAdapter(this);
        adapter.setData(mDatas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setmOnItemClickListener(new SetledAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(SettledActivity.this, position+"", Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.setAdapter(adapter);

    }

    private void initData() {
        mDatas = new ArrayList<String>();
        mDatas.add("汽车");
        mDatas.add("教育");
        mDatas.add("休闲");
        mDatas.add("旅游");
        mDatas.add("服饰");
        mDatas.add("其他");
    }

}
