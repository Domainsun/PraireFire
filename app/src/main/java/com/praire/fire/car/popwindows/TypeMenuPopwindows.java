package com.praire.fire.car.popwindows;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.praire.fire.R;
import com.praire.fire.base.BaseActivity;
import com.praire.fire.car.adapter.TypeMenuPopAdapter;
import com.praire.fire.car.bean.TypeMenuBean;

/**
 * 点餐页面
 * 下拉菜单
 */
public class TypeMenuPopwindows extends PopupWindow implements View.OnClickListener {
    public static final int TYPE_PRODUCT = 1;
    public static final int TYPE_SERVICE = 2;
    ImageView typeMenuPopIcon1;
    TextView typeMenuPopTv1;
    LinearLayout typeMenuPopLl1;
    ImageView typeMenuPopIcon2;
    TextView typeMenuPopTv2;
    TextView typeMenuPopCount2;
    LinearLayout typeMenuPopLl2;
    ImageView typeMenuPopIcon3;
    TextView typeMenuPopTv3;
    TextView typeMenuPopCount3;
    LinearLayout typeMenuPopLl3;

    private BaseActivity context;
    private View mainView;
    private int type;
    private TypeMenuPopAdapter menuAdapter;

    private TypeMenuBean entities;
    private RecyclerView recyclerView;
    private OnItemClickListener mOnItemClickListener;

    public TypeMenuPopwindows(BaseActivity context, int type, TypeMenuBean entities) {
        super(context);
        this.entities = entities;
        this.type = type;
        this.context = context;
        int layoutid = R.layout.pop_type_menu;
        mainView = LayoutInflater.from(context).inflate(layoutid, null);

        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        this.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        this.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ban_tou_ming));
        this.setContentView(mainView);
        this.setFocusable(true);

        final View v = context.getWindow().peekDecorView();
        if (v != null && v.getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }

        initView();
        initData();
    }

    private void initData() {
        switch (type) {
            case TYPE_SERVICE:
                menuAdapter.setEntities(entities.getServiceTypeList(), TYPE_SERVICE);
                break;
            case TYPE_PRODUCT:
                menuAdapter.setEntities(entities.getProductTypeList(), TYPE_PRODUCT);
                break;
            default:
                break;
        }


    }

    private void initView() {
        typeMenuPopIcon1 = mainView.findViewById(R.id.type_menu_pop_icon1);
        typeMenuPopTv1 = mainView.findViewById(R.id.type_menu_pop_tv1);
        typeMenuPopLl1 = mainView.findViewById(R.id.type_menu_pop_ll1);
        typeMenuPopIcon2 = mainView.findViewById(R.id.type_menu_pop_icon2);
        typeMenuPopTv2 = mainView.findViewById(R.id.type_menu_pop_tv2);
        typeMenuPopCount2 = mainView.findViewById(R.id.type_menu_pop_count2);
        typeMenuPopLl2 = mainView.findViewById(R.id.type_menu_pop_ll2);
        typeMenuPopIcon3 = mainView.findViewById(R.id.type_menu_pop_icon3);
        typeMenuPopTv3 = mainView.findViewById(R.id.type_menu_pop_tv3);
        typeMenuPopCount3 = mainView.findViewById(R.id.type_menu_pop_count3);
        typeMenuPopLl3 = mainView.findViewById(R.id.type_menu_pop_ll3);
        typeMenuPopLl1.setOnClickListener(this);
        typeMenuPopLl2.setOnClickListener(this);
        typeMenuPopLl3.setOnClickListener(this);
        recyclerView = mainView.findViewById(R.id.type_menu_pop_rv);
        //高度自适应
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
//        medicineRV.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.HORIZONTAL));
        menuAdapter = new TypeMenuPopAdapter(context);
        recyclerView.setAdapter(menuAdapter);
        //为RecyclerView添加HeaderView和FooterView
//        setHeaderView(medicineRV);
        menuAdapter.setOnItemClickListener(new TypeMenuPopAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position, int type) {
                switch (type) {
                    case TYPE_SERVICE:
                        mOnItemClickListener.onItemClick(TYPE_SERVICE, entities.getServiceTypeList().get(position).getName(), entities.getServiceTypeList().get(position).getId());
                        break;
                    case TYPE_PRODUCT:

                        mOnItemClickListener.onItemClick(TYPE_PRODUCT, entities.getProductTypeList().get(position).getName(), entities.getProductTypeList().get(position).getId());
                        break;

                    default:
                        break;
                }
                dismiss();
            }
        });
    }


    public void setDataBack(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.type_menu_pop_ll1:
                recyclerView.setVisibility(View.INVISIBLE);
                break;
            case R.id.type_menu_pop_ll2:
                recyclerView.setVisibility(View.VISIBLE);
                menuAdapter.setEntities(entities.getServiceTypeList(), TYPE_SERVICE);
                break;
            case R.id.type_menu_pop_ll3:
                recyclerView.setVisibility(View.VISIBLE);
                menuAdapter.setEntities(entities.getProductTypeList(), TYPE_PRODUCT);
                break;
            default:
                break;
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int type, String typeName, String id);
    }


}
