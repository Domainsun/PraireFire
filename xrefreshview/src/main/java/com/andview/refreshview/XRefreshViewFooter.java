package com.andview.refreshview;

import com.andview.refreshview.callback.IFooterCallBack;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 上拉加载底部控件
 * 
 * @author JiangTing
 * @date 2016年4月18日
 */
public class XRefreshViewFooter extends LinearLayout implements IFooterCallBack {
	private Context		mContext;

	private View		mContentView;
	private View		mProgressBar;
	private TextView	mHintView;

	public XRefreshViewFooter(Context context) {
		super(context);
		initView(context);
	}

	public XRefreshViewFooter(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public void setBottomMargin(int height) {
		Log.i("footView", "footView is Visible=" + getVisibility());
		if (height < 0)
			return;
		RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mContentView.getLayoutParams();
		lp.bottomMargin = height;
		mContentView.setLayoutParams(lp);
	}

	public int getBottomMargin() {
		RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mContentView.getLayoutParams();
		return lp.bottomMargin;
	}

	/**
	 * hide footer when disable pull load more
	 */
	public void hide() {
		setVisibility(View.GONE);
	}

	/**
	 * show footer
	 */
	public void show() {
		setVisibility(View.VISIBLE);
	}

	public void initView(Context context) {
		mContext = context;
		LinearLayout moreView = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.xrefreshview_footer, null);
		addView(moreView);
		moreView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		mContentView = moreView.findViewById(R.id.xrefreshview_footer_content);
		mProgressBar = moreView.findViewById(R.id.xrefreshview_footer_progressbar);
		mHintView = (TextView) moreView.findViewById(R.id.xrefreshview_footer_hint_textview);
	}

	@Override
	public void onStateNormal() {
		mProgressBar.setVisibility(View.INVISIBLE);
		mHintView.setVisibility(View.VISIBLE);
		mHintView.setText(R.string.xrefreshview_footer_hint_pull);
	}

	@Override
	public void onStateReady() {
		mProgressBar.setVisibility(View.INVISIBLE);
		mHintView.setVisibility(View.VISIBLE);
		mHintView.setText(R.string.xrefreshview_footer_hint_ready);
	}

	@Override
	public void onStateLoading() {
		mHintView.setVisibility(View.VISIBLE);
		mProgressBar.setVisibility(View.VISIBLE);
		mHintView.setText(R.string.xrefreshview_header_hint_loading);
	}

	@Override
	public void onStateFinish() {
		mProgressBar.setVisibility(View.INVISIBLE);
		mHintView.setVisibility(View.VISIBLE);
		mHintView.setText(R.string.xrefreshview_footer_hint_normal);
	}

	@Override
	public void onFooterMove(double offset, int offsetY, int deltaY) {

	}

	@Override
	public void setRefreshTime(long lastRefreshTime) {

	}

	@Override
	public int getFooterHeight() {
		return getMeasuredHeight();
	}
}
