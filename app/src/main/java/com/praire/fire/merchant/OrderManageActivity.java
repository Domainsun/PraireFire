package com.praire.fire.merchant;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.praire.fire.R;
import com.praire.fire.SignAcitvity;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.merchant.adapter.OrderAdapter;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.BusinessOrderListBean;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;


public class OrderManageActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
    private TabLayout myTabLayout;
    SwipeMenuRecyclerView recyclerView;
    SwipeRefreshLayout mRefreshLayout;
    TextView tv_statu;
    TextView tv_back;
    private OrderAdapter adapter;

    UseAPIs u=new UseAPIs();
    J2O j=new J2O();
    String cookie;
    List<BusinessOrderListBean.PagelistBean> Datas=new ArrayList<>();

    String statu="1";
    int index = 1;
    Boolean dataEmpty = false;
    Boolean hasMore = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_manage);

        cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");

        try {
            Thread  thread=new Thread(){
                @Override
                public void run() {
                    super.run();
                    u.changeOrderReadStatus(cookie);
                }
            };

            thread.start();



        } catch (Exception e) {
            Log.e("initData", "initData: "+e.toString() );
        }
        adapter = new OrderAdapter(this);
        initData("1");
        initView();



    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_order_manage;
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


    private void initData(String statu) {
        for (int i = 0; i < Datas.size(); i++) {
            Datas.remove(i);
        }


//        Datas.clear();



        String str="";
        str=u.getBusinessOrderList(statu,cookie,"1");

        Log.d("statu", "statu: "+statu);

        if (str.length() != 0) {

            Log.d("initData", "initData: "+str);
            BusinessOrderListBean b = j.getBusinessOrderList(str);
            Datas = b.getPagelist();
            adapter.setData(Datas);
        } else {
            Toast.makeText(this, "网络错误！", Toast.LENGTH_SHORT).show();
        }



    }

    private void initView() {
        tv_statu=findViewById(R.id.tv_status);
        tv_back=findViewById(R.id.tv_back);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderManageActivity.this.finish();
            }
        });

        LinearLayoutManager mLayoutManager =new LinearLayoutManager(this);

        myTabLayout = (TabLayout) findViewById(R.id.myTab);




        myTabLayout.addTab(myTabLayout.newTab().setText("待付款"));
        myTabLayout.addTab(myTabLayout.newTab().setText("待消费"),true);
        myTabLayout.addTab(myTabLayout.newTab().setText("已消费"));
        myTabLayout.addTab(myTabLayout.newTab().setText("已退款"));
        myTabLayout.setOnTabSelectedListener(this);

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.useDefaultLoadMore(); // 使用默认的加载更多的View。
        recyclerView.setLoadMoreListener(mLoadMoreListener);
        recyclerView.loadMoreFinish(false, true);

        mRefreshLayout.setOnRefreshListener(mRefreshListener); // 刷新监听。





        adapter.setmOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, String id, String status) {

                if (status.equals("1")) {
                    showButtonDialogFragment(id,position);
                } else if (status.equals("0")) {
                    String orderId=Datas.get(position).getId();


                    Intent i=new Intent(OrderManageActivity.this,OrderChangeActivity.class);
                    i.putExtra("orderId",orderId);
                    i.putExtra("tag","1");
                    startActivity(i);


                }

            }
        });
        recyclerView.setAdapter(adapter);

    }



    /*
  * 下拉刷新*/
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {



//            recyclerView.removeAllViews();
//
//            Datas.clear();

            initData(statu);

            adapter.notifyDataSetChanged();
//            adapter.notify();



            mRefreshLayout.setRefreshing(false);
            index = 1;
            dataEmpty = false;
            hasMore = true;
            recyclerView.loadMoreFinish(false, true);
            Toast.makeText(OrderManageActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
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

                    OrderManageActivity.this.index++;
                    String index = String.valueOf(OrderManageActivity.this.index);
                    List<BusinessOrderListBean.PagelistBean> data = new ArrayList<>();
                    String str = u.getBusinessOrderList(statu,cookie,index);
                    BusinessOrderListBean b=j.getBusinessOrderList(str);


                    Log.d("statu", "statu: "+statu);
                    Log.d("index", "inex: "+index);

                    for (int i = 0; i < b.getPagelist().size(); i++) {
                        Datas.add(b.getPagelist().get(i));

                    }
                    if (b.getPagelist().size() == 0) {
                        dataEmpty = true;
                    }
                    if (b.getPagelist().size() < 5) {
                        hasMore = false;
                    }
                    adapter.notifyDataSetChanged();

                    recyclerView.loadMoreFinish(dataEmpty, hasMore);

                    Log.d("Pagelistsize", "Pagelistsize: " + b.getPagelist().size());
                    Log.d("dataEmpty", "dataEmpty: " + dataEmpty);
                    Log.d("hasMore", "hasMore: " + hasMore);
                    Log.d("index", "index: " + index);
                }
            }, 500);
        }

    };

    /*退款dialog*/

    public void showButtonDialogFragment(final String id, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                this);
        builder.setCancelable(false);

        builder.setMessage("是否退款？");


        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {



                String str = new UseAPIs().agreeFund(cookie,id);
                if (str.length()!=0) {

                    APIResultBean a = new J2O().getAPIResult(str);
                    if (1==a.getCode()) {
                        Datas.get(position).setRefund("3");
                        adapter.notifyItemChanged(position);
                    }
                    Toast.makeText(OrderManageActivity.this, a.getMsg() + "", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OrderManageActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(OrderManageActivity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        int index=tab.getPosition();
        OrderManageActivity.this.index = 1;
        dataEmpty = false;
        hasMore = true;
        recyclerView.loadMoreFinish(dataEmpty, hasMore);

        Log.d("onTabSelected", "onTabSelected: "+index+"\ndataEmpty"+dataEmpty+"\nhasMore"+hasMore);

        Log.d("getPosition", "onTabSelected: "+tab.getPosition());

        if (index==0) {
            statu="0";
            tv_statu.setText("待付款");
        } else if (index==1) {
            statu="1";
            tv_statu.setText("待消费");
        }else if (index==2) {
            statu="2";
            tv_statu.setText("已消费");
        }else if (index==3) {
            statu="3";
            tv_statu.setText("已退款");
        }

        initData(statu);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}



