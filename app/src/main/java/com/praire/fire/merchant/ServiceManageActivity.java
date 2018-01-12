package com.praire.fire.merchant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.praire.fire.R;
import com.praire.fire.merchant.adapter.ServiceAdapter;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.ServiceListBean;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;

public class ServiceManageActivity extends AppCompatActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.rl_add_service)
    RelativeLayout rlAddService;
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView recyclerView;
    private SwipeRefreshLayout mRefreshLayout;

    UseAPIs u = new UseAPIs();
    J2O j = new J2O();
    String cookie;

    int index = 1;
    Boolean dataEmpty = false;
    Boolean hasMore = true;

    private List<ServiceListBean.PagelistBean> mDatas = new ArrayList<>();
    private ServiceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_manage);
        ButterKnife.bind(this);
        cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");
//        initdata();
        initview();
    }

//    private void initdata() {
//
//        String str = u.getServiceList(cookie, "1");
//        ServiceListBean s = j.getServiceList(str);
//        for (int i = 0; i < s.getPagelist().size(); i++) {
//            mDatas.add(s.getPagelist().get(i));
//        }
//        if (s.getPagelist().size() == 0) {
//            dataEmpty = true;
//        }
//        if (s.getPagelist().size() < 10) {
//            hasMore = false;
//        }
//
//        Log.d("initdata", "initdata: " + cookie);
//        Log.d("initdata", "initdata: " + str);
//        Log.d("initdata", "initdata: " + s.getPagelist().get(0).getClassX());
//    }


    private void initview() {

        LinearLayoutManager mLayoutManager =new LinearLayoutManager(this);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        mRefreshLayout.setOnRefreshListener(mRefreshListener); // 刷新监听。
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.useDefaultLoadMore(); // 使用默认的加载更多的View。
        recyclerView.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        adapter = new ServiceAdapter(this);
//        adapter.setData(mDatas);
        loadData();

        adapter.setmOnItemClickListener(new ServiceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String id, String status) {
                showButtonDialogFragment(view, id, status);
                Log.d("status: ", "status: " + status);
            }
        });
        recyclerView.setAdapter(adapter);


    }


    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            loadData();
            adapter.notifyDataSetChanged();
            mRefreshLayout.setRefreshing(false);
            index = 1;
            dataEmpty = false;
            hasMore = true;
            Toast.makeText(ServiceManageActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
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

                    ServiceManageActivity.this.index++;
                    String index = String.valueOf(ServiceManageActivity.this.index);
                    List<ServiceListBean.PagelistBean> data = new ArrayList<>();
                    String str = u.getServiceList(cookie, index);
                    ServiceListBean s = j.getServiceList(str);

                    for (int i = 0; i < s.getPagelist().size(); i++) {
                        mDatas.add(s.getPagelist().get(i));
                    }

                    if (s.getPagelist().size() == 0) {
                        dataEmpty = true;
                    }
                    if (s.getPagelist().size() < 10) {
                        hasMore = false;
                        Log.d("onLoadMore", "onLoadMore< 10: ");
                    }
//                    adapter.setData(mDatas);
                    adapter.notifyDataSetChanged();
                    adapter.notifyItemInserted(1);
//             notifyItemRangeInserted()或者notifyDataSetChanged().
//            adapter.notifyItemRangeInserted(mDatas.size() - data.size(), data.size());
                    // 数据完更多数据，一定要掉用这个方法。
                    // 第一个参数：表示此次数据是否为空。
                    // 第二个参数：表示是否还有更多数据。

                    recyclerView.loadMoreFinish(dataEmpty, hasMore);
                    // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
                    // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
                    // errorMessage是会显示到loadMoreView上的，用户可以看到。
//            recyclerView.loadMoreError(0, "网络错误");

                    Log.d("Pagelistsize", "Pagelistsize: " + s.getPagelist().size());
                    Log.d("dataEmpty", "dataEmpty: " + dataEmpty);
                    Log.d("hasMore", "hasMore: " + hasMore);
                    Log.d("index", "index: " + index);
                }
            }, 1000);
        }

    };


    private void loadData() {
        String str = u.getServiceList(cookie, "1");
        ServiceListBean s = j.getServiceList(str);
        for (int i = 0; i < mDatas.size(); i++) {
            mDatas.remove(i);
        }
        mDatas = s.getPagelist();
        adapter.setData(mDatas);
        // 第一次加载数据：一定要调用这个方法，否则不会触发加载更多。
        // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
        // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
        recyclerView.loadMoreFinish(false, true);
    }


    public void showButtonDialogFragment(View view, final String id, final String status) {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                this);
        builder.setCancelable(false);
        if (status.equals("0")) {
            builder.setMessage("是否下架该产品？");
        } else {
            builder.setMessage("是否上架架该产品？");
        }

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String str = new UseAPIs().changeServiceStatus(id, cookie, status);
                APIResultBean a = new J2O().getAPIResult(str);
                Toast.makeText(ServiceManageActivity.this, a.getMsg() + "", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ServiceManageActivity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    @OnClick({R.id.tv_back, R.id.rl_add_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.rl_add_service:
                Intent i = new Intent(this, AddServiceActivity.class);
                startActivity(i);
                break;
        }
    }
}
