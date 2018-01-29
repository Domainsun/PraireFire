package com.praire.fire.car.popwindows;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 点餐页面
 * 下拉菜单
 */
public class SortMenuPopwindows extends PopupWindow implements View.OnClickListener {

    ImageView shortMenuPopIcon1;
    TextView shortMenuPopTv1;
    ImageView shortMenuPopIcon2;
    TextView shortMenuPopTv2;
    ImageView shortMenuPopIcon3;
    TextView shortMenuPopTv3;
    ImageView shortMenuPopIcon4;
    TextView shortMenuPopTv4;
    ImageView shortMenuPopIcon5;
    TextView shortMenuPopTv5;
    LinearLayout short1, short2, short3, short4, short5;
    private BaseActivity context;
    private View mainView;


    private OnItemClickListener mOnItemClickListener;
    private int position = 1;

    public SortMenuPopwindows(BaseActivity context) {
        super(context);
        this.context = context;
        int layoutid = R.layout.pop_sort_menu;
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


    }

    private void initView() {
        shortMenuPopIcon1 = mainView.findViewById(R.id.short_menu_pop_icon1);
        shortMenuPopTv1 = mainView.findViewById(R.id.short_menu_pop_tv1);
        shortMenuPopIcon2 = mainView.findViewById(R.id.short_menu_pop_icon2);
        shortMenuPopTv2 = mainView.findViewById(R.id.short_menu_pop_tv2);
        shortMenuPopIcon3 = mainView.findViewById(R.id.short_menu_pop_icon3);
        shortMenuPopTv3 = mainView.findViewById(R.id.short_menu_pop_tv3);
        shortMenuPopIcon4 = mainView.findViewById(R.id.short_menu_pop_icon4);
        shortMenuPopTv4 = mainView.findViewById(R.id.short_menu_pop_tv4);
        shortMenuPopIcon5 = mainView.findViewById(R.id.short_menu_pop_icon5);
        shortMenuPopTv5 = mainView.findViewById(R.id.short_menu_pop_tv5);
        short1 = mainView.findViewById(R.id.short_menu_pop_ll1);
        short2 = mainView.findViewById(R.id.short_menu_pop_ll2);
        short3 = mainView.findViewById(R.id.short_menu_pop_ll3);
        short4 = mainView.findViewById(R.id.short_menu_pop_ll4);
        short5 = mainView.findViewById(R.id.short_menu_pop_ll5);
        short1.setOnClickListener(this);
        short2.setOnClickListener(this);
        short3.setOnClickListener(this);
        short4.setOnClickListener(this);
        short5.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.short_menu_pop_ll1:
                position = 1;
                shortMenuPopTv1.setTextColor(context.getResources().getColor(R.color.orange));
                short1.setBackgroundColor(context.getResources().getColor(R.color.grey));
                break;
            case R.id.short_menu_pop_ll2:
                position = 2;
                shortMenuPopTv2.setTextColor(context.getResources().getColor(R.color.orange));
                short2.setBackgroundColor(context.getResources().getColor(R.color.grey));

                break;
            case R.id.short_menu_pop_ll3:
                position = 3;
                shortMenuPopTv3.setTextColor(context.getResources().getColor(R.color.orange));
                short3.setBackgroundColor(context.getResources().getColor(R.color.grey));

                break;
            case R.id.short_menu_pop_ll4:
                position = 4;
                shortMenuPopTv4.setTextColor(context.getResources().getColor(R.color.orange));
                short4.setBackgroundColor(context.getResources().getColor(R.color.grey));
                break;
            case R.id.short_menu_pop_ll5:
                position = 5;
                shortMenuPopTv5.setTextColor(context.getResources().getColor(R.color.orange));
                short5.setBackgroundColor(context.getResources().getColor(R.color.grey));
                break;
            default:
                break;
        }
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(position);
            dismiss();
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setDataBack(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
