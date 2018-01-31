package com.praire.fire.merchant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.merchant.adapter.ProductAdapter;
import com.praire.fire.merchant.adapter.ServiceAdapter;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.ProductListBean;
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

public class ProductManageActivity extends BaseActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_add_product)
    TextView tvAddProduct;
    @BindView(R.id.rl_add_product)
    RelativeLayout rlAddProduct;


    SwipeMenuRecyclerView recyclerView;
    private SwipeRefreshLayout mRefreshLayout;

    UseAPIs u = new UseAPIs();
    J2O j = new J2O();
    String cookie;

    int index = 1;
    Boolean dataEmpty = false;
    Boolean hasMore = true;

    private List<ProductListBean.PagelistBean> mDatas = new ArrayList<>();
    private ProductAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_manage);
        ButterKnife.bind(this);


//        initdata();
        initview();

    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.activity_product_manage;
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

    private void initview() {

        cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");
        LinearLayoutManager mLayoutManager =new LinearLayoutManager(this);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        mRefreshLayout.setOnRefreshListener(mRefreshListener); // 刷新监听。
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.useDefaultLoadMore(); // 使用默认的加载更多的View。
        recyclerView.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        adapter = new ProductAdapter(this);

        loadData();

        adapter.setmOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, String id, String status) {
                showButtonDialogFragment(position,view,id,status);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initdata() {

//        String str=u.getProductList(cookie,"1");
//        Log.d("initdata", "initdata: "+str);

    }

    /*
    * 下拉刷新*/
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            loadData();
            adapter.notifyDataSetChanged();
            mRefreshLayout.setRefreshing(false);
            index = 1;
            dataEmpty = false;
            hasMore = true;
            Toast.makeText(ProductManageActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
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

                    ProductManageActivity.this.index++;
                    String index = String.valueOf(ProductManageActivity.this.index);
                    List<ProductListBean.PagelistBean> data = new ArrayList<>();
                    String str ="";
                    str=u.getProductList(cookie, index);
                    if (str.length() != 0) {
                        ProductListBean s = j.getProductList(str);

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
                        Toast.makeText(ProductManageActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                    }



                }
            }, 1000);
        }

    };

    private void loadData() {



        String str = "";
        str=u.getProductList(cookie, "1");
        if (str.length() != 0) {
            ProductListBean s = j.getProductList(str);
            for (int i = 0; i < mDatas.size(); i++) {
                mDatas.remove(i);
            }


            Log.d("loadData", "loadData: "+s.getPagelist().get(0).getName());
            mDatas = s.getPagelist();
            adapter.setData(mDatas);
            recyclerView.loadMoreFinish(false, true);
        } else {
            Toast.makeText(this, "网络错误！", Toast.LENGTH_SHORT).show();
        }



    }

    public void showButtonDialogFragment(final int position, View view, final String id, final String status) {
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


                Log.d("onClick", "onClick: "+"position"+position+"id:"+id+"status"+status);

                String str = "";
                str=new UseAPIs().changeProductStatus(id, cookie, status);

                if (str.length() != 0) {
                    APIResultBean a = new J2O().getAPIResult(str);
                    Toast.makeText(ProductManageActivity.this, a.getMsg() + "", Toast.LENGTH_SHORT).show();
                    Log.d("onClick", "onClick: "+a.getCode());
                    if (a.getCode().equals("1")) {
                        if (status.equals("0")) {
                            mDatas.get(position).setStatus("0");
                        } else {
                            mDatas.get(position).setStatus("1");
                        }
                        adapter.notifyItemChanged(position);

                    }
                } else {
                    Toast.makeText(ProductManageActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                }


            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ProductManageActivity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    @OnClick({R.id.tv_back, R.id.rl_add_product})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.rl_add_product:
                Intent i = new Intent(this, AddProductActivity.class);
                startActivity(i);
                break;
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        loadData();
        adapter.notifyDataSetChanged();
        mRefreshLayout.setRefreshing(false);
        index = 1;
        dataEmpty = false;
        hasMore = true;
    }
}
