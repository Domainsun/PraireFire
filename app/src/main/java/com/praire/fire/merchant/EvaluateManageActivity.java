package com.praire.fire.merchant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.praire.fire.R;
import com.praire.fire.merchant.adapter.EvaluateAdapter;
import com.praire.fire.merchant.adapter.ProductAdapter;
import com.praire.fire.okhttp.GsonUtils.J2O;
import com.praire.fire.okhttp.JavaBean.APIResultBean;
import com.praire.fire.okhttp.JavaBean.BusinessEvaluateListBean;
import com.praire.fire.okhttp.JavaBean.ProductListBean;
import com.praire.fire.okhttp.UseAPIs;
import com.praire.fire.utils.SharePreferenceMgr;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.praire.fire.common.Constants.LOGIN_COOKIE;

public class EvaluateManageActivity extends AppCompatActivity {

    UseAPIs u = new UseAPIs();
    J2O j = new J2O();
    String cookie;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_evaluate_count)
    TextView tvEvaluateCount;
    LinearLayout ll;
    SwipeMenuRecyclerView recyclerView;
    SwipeRefreshLayout mRefreshLayout;
    private PopupWindow mPopWindow;
    InputMethodManager imm;
    EditText et;
    EvaluateAdapter adapter;

    int index = 1;
    Boolean dataEmpty = false;
    Boolean hasMore = true;

    private List<BusinessEvaluateListBean.PagelistBean> mDatas = new ArrayList<>();

    BusinessEvaluateListBean b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate_manage);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        cookie = (String) SharePreferenceMgr.get(this, LOGIN_COOKIE, "");
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        /*未读变已读*/
        u.changeEvaluateStatus(cookie);

        ll = findViewById(R.id.ll);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
            }
        });

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        adapter = new EvaluateAdapter(this);
        loadData();
        tvEvaluateCount.setText("(" + b.getCount() + ")");
        mRefreshLayout.setOnRefreshListener(mRefreshListener); // 刷新监听。
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.useDefaultLoadMore(); // 使用默认的加载更多的View。
        recyclerView.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。
        recyclerView.loadMoreFinish(false, true);

        adapter.setmOnItemClickListener(new EvaluateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, String id) {
                showPopupWindow(position, view, id);
            }
        });

        recyclerView.setAdapter(adapter);

    }


    private void loadData() {
        String str = "";
        str = u.getEvaluateList(cookie, "1");

        if (str.length() != 0) {
            b = j.getBusinessEvaluateList(str);
            mDatas = b.getPagelist();
            adapter.setData(mDatas);

        } else {
            Toast.makeText(this, "网络错误！", Toast.LENGTH_SHORT).show();
        }


    }

    /*
      * 下拉刷新*/
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

            for (int i = 0; i < mDatas.size(); i++) {
                mDatas.remove(i);
            }

            loadData();
            adapter.notifyDataSetChanged();
            mRefreshLayout.setRefreshing(false);
            index = 1;
            dataEmpty = false;
            hasMore = true;
            recyclerView.loadMoreFinish(false, true);
            Toast.makeText(EvaluateManageActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
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

                    EvaluateManageActivity.this.index++;
                    String index = String.valueOf(EvaluateManageActivity.this.index);
                    List<BusinessEvaluateListBean.PagelistBean> data = new ArrayList<>();
                    String str = "";
                    str = u.getEvaluateList(cookie, index);

                    if (str.length() != 0) {
                        BusinessEvaluateListBean s = j.getBusinessEvaluateList(str);

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
                        adapter.notifyDataSetChanged();
                        recyclerView.loadMoreFinish(dataEmpty, hasMore);
                    } else {
                        Toast.makeText(EvaluateManageActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                    }


                }
            }, 1000);
        }

    };


    @SuppressLint("WrongConstant")
    private void showPopupWindow(final int position, View view, final String id) {

        View contentView = LayoutInflater.from(EvaluateManageActivity.this).inflate(R.layout.item_popupwindow, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv1 = contentView.findViewById(R.id.popup_send);



        et = contentView.findViewById(R.id.popup_edit);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reply = "";
                reply = et.getText().toString();
                Log.d("onClick", "onClick: " + et.getText().toString());
                imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
                mPopWindow.dismiss();

                String str = "";
                str = u.businessEvaluate(id, reply, cookie);
                if (str.length() != 0) {
                    APIResultBean o = j.getAPIResult(str);

                    Toast.makeText(EvaluateManageActivity.this, o.getMsg(), Toast.LENGTH_SHORT).show();

                    if (o.getCode().equals("1")) {
                        mDatas.get(position).setReply(reply);
                        adapter.notifyItemChanged(position);
                    }
                } else {
                    Toast.makeText(EvaluateManageActivity.this, "网络错误！", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(EvaluateManageActivity.this).inflate(R.layout.activity_evaluate_manage, null);


        mPopWindow.setFocusable(true);
        mPopWindow.setOutsideTouchable(true); // 设置允许在外点击消失
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());// 设置背景，这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        mPopWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);    //必须加这两行，不然不会显示在键盘上方
        mPopWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mPopWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0); // PopupWindow的显示及位置设置
//        mPopWindow.showAsDropDown(view, 0, 0);


        //显示软键盘
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                imm.showSoftInput(et, InputMethodManager.SHOW_FORCED);
            }
        }, 200);

//
//
//        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//
//
//                imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
//                Log.d("onDismiss", "onDismiss: .......");
//            }
//        });
    }


    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        finish();
    }
}
