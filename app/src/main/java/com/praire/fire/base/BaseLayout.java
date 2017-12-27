package com.praire.fire.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.praire.fire.R;


public class BaseLayout extends LinearLayout implements OnClickListener {
    private LinearLayout mLayout;
    private View mTitlebar;
    private RelativeLayout mLeft;
    private RelativeLayout mRight;
    private RelativeLayout mMiddle;

    private BarClickListener mBarClickListener = null;

    public BaseLayout(Context context, int resId) {
        super(context);
        LayoutInflater i = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // title
        initTitleLayout(i);

        // content
        View contentView = i.inflate(resId, null);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mLayout.addView(contentView, lp);
    }

    public BaseLayout(Context context, View contentView) {
        super(context);
        LayoutInflater i = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // title
        initTitleLayout(i);

        // content
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mLayout.addView(contentView, lp);
    }

    private void initTitleLayout(LayoutInflater i) {
        // title
        i.inflate(R.layout.base_generic_title, this);

        // find
        mLayout = (LinearLayout) findViewById(R.id.ll_maim_layout);
        mTitlebar = (RelativeLayout) findViewById(R.id.rl_title);
        mLeft = (RelativeLayout) findViewById(R.id.fl_title_left);
        mRight = (RelativeLayout) findViewById(R.id.fl_title_right);
        mMiddle = (RelativeLayout) findViewById(R.id.fl_title_middle);
        mTitlebar.setVisibility(View.GONE);
        mLeft.setOnClickListener(this);
        mRight.setOnClickListener(this);
        mMiddle.setOnClickListener(this);
    }

    public BaseLayout(Context context) {
        super(context);
        // 这个构造方法仅仅为了在编辑器里面能够预览
    }

    public void setTitleLeft(View view) {
        mLeft.removeAllViews();
        if (view != null) {
            if (mTitlebar.getVisibility() == View.GONE) {
                mTitlebar.setVisibility(View.VISIBLE);
            }
            RelativeLayout.LayoutParams lp =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.CENTER_VERTICAL);
            mLeft.addView(view, lp);
        }
    }

    public void setTitleRight(View view) {
        mRight.removeAllViews();
        if (view != null) {
            if (mTitlebar.getVisibility() == View.GONE) {
                mTitlebar.setVisibility(View.VISIBLE);
            }
            RelativeLayout.LayoutParams lp =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.CENTER_VERTICAL);
            mRight.addView(view, lp);
        }
    }

    public void setTitleMiddle(View view) {
        mMiddle.removeAllViews();
        if (view != null) {
            if (mTitlebar.getVisibility() == View.GONE) {
                mTitlebar.setVisibility(View.VISIBLE);
            }
            RelativeLayout.LayoutParams lp =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.CENTER_VERTICAL);
            mMiddle.addView(view, lp);
        }
    }

    public void setBarClickListener(BarClickListener listener) {
        mBarClickListener = listener;
    }

    public void EnableClickLeft(boolean enalbe) {
        mLeft.setClickable(enalbe);
    }

    public void EnableClickRight(boolean enalbe) {
        mRight.setClickable(enalbe);
    }

    public void EnableClickMid(boolean enalbe) {
        mMiddle.setClickable(enalbe);
    }

    public void removeLeft() {
        mLeft.removeAllViews();
    }

    public void removeRight() {
        mRight.removeAllViews();
    }

    public void removeMid() {
        mMiddle.removeAllViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl_title_left: {
                mBarClickListener.onClickLeft();
            }
            break;
            case R.id.fl_title_right: {
                mBarClickListener.onClickRight();
            }
            break;
            case R.id.fl_title_middle: {
                mBarClickListener.onClickMiddle();
            }
            break;
            default:
                break;
        }
    }

    public void setTitleVisibility(int visibility) {
        mTitlebar.setVisibility(visibility);
    }

    public  interface BarClickListener {
         void onClickRight();

         void onClickLeft();

         void onClickMiddle();
    }
}
