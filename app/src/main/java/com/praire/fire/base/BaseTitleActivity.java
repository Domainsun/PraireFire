package com.praire.fire.base;

import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.praire.fire.R;


public abstract class BaseTitleActivity extends BaseActivity implements BaseLayout.BarClickListener {

	protected BaseLayout	mBaseLayout;

	private int rightTxtColor;

	@SuppressWarnings("deprecation")
	@Override
	public void setContentView(int layoutResID) {
		mBaseLayout = new BaseLayout(this, layoutResID);
		super.setContentView(mBaseLayout);

		// setTranslucentStatus(true);
		setBackgroundResource(R.mipmap.head_backgroud);

		initiTile();
		contentInit();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setContentView(View view) {
		mBaseLayout = new BaseLayout(this, view);
		super.setContentView(mBaseLayout);

		// setTranslucentStatus(true);
		setBackgroundResource(R.mipmap.head_backgroud);

		initiTile();
		contentInit();
	}


	public void setBackgroundColor(int color) {
		mBaseLayout.setBackgroundColor(color);
	}

	public void setBackgroundResource(int resid) {
		mBaseLayout.setBackgroundResource(resid);
	}

	public void setTitleLeft(View view) {
		mBaseLayout.setTitleLeft(view);
	}

	public void setDefaultBack() {
		ImageView left = new ImageView(this);
//		left.setImageResource(R.drawable.backgray);
		left.setImageResource(R.mipmap.back);
		left.setDuplicateParentStateEnabled(true);
		setTitleLeft(left);
	}

	public void setTitleRightByResource(int resourceId) {
		ImageView right = new ImageView(this);
		right.setImageResource(resourceId);
		right.setDuplicateParentStateEnabled(true);
		setTitleRight(right);
	}

	public void setTitleLeftByResource(int resourceId) {
		ImageView left = new ImageView(this);
		left.setImageResource(resourceId);
		left.setDuplicateParentStateEnabled(true);
		setTitleLeft(left);
	}

	public void setTitleRight(View view) {
		mBaseLayout.setTitleRight(view);
	}

	@SuppressWarnings("deprecation")
	public void setTitleRight(String str) {
		if (TextUtils.isEmpty(str)) {
			mBaseLayout.setTitleRight(null);
			return;
		}

		TextView right = new TextView(this);
		right.setTextColor(getResources().getColorStateList(R.color.white));
		right.setTextSize(TypedValue.COMPLEX_UNIT_PX, 16);
		right.setText(str);
		if (rightTxtColor > 0) {
			right.setTextColor(getResources().getColor(rightTxtColor));
		}
		right.setGravity(Gravity.CENTER_VERTICAL);
		right.setDuplicateParentStateEnabled(true);
		mBaseLayout.setTitleRight(right);
	}

	public void setTitleMiddle(View view) {
		mBaseLayout.setTitleMiddle(view);
	}

	@SuppressWarnings("deprecation")
	public void setTitleMiddle(String title) {
		if (TextUtils.isEmpty(title)) {
			mBaseLayout.setTitleMiddle(null);
			return;
		}
		TextView middleV = new TextView(this);
		middleV.setTextColor(getResources().getColor(R.color.white));
		middleV.setTextSize(TypedValue.COMPLEX_UNIT_PX, 16);
		middleV.setText(title);
		middleV.setGravity(Gravity.CENTER_VERTICAL);
		middleV.setMaxLines(1);
		middleV.setEllipsize(TruncateAt.END);
		mBaseLayout.setTitleMiddle(middleV);
		EnableClickMid(false);
	}

	public void setTitleVisibility(int visibility) {
		mBaseLayout.setTitleVisibility(visibility);
	}

	public void removeLeft() {
		mBaseLayout.removeLeft();
	}

	public void removeRight() {
		mBaseLayout.removeRight();
	}

	public void removeMid() {
		mBaseLayout.removeMid();
	}

	private void contentInit() {
		mBaseLayout.setBarClickListener(this);
	}

	public View getTitleBar() {
		return mBaseLayout.findViewById(R.id.rl_title);
	}

	/**
	 * 初始化标题
	 */
	public abstract void initiTile();

	@Override
	public void onClickRight() {

	}

	@Override
	public void onClickLeft() {
		finish();
	}

	@Override
	public void onClickMiddle() {

	}

	public void EnableClickLeft(boolean enalbe) {
		mBaseLayout.EnableClickLeft(enalbe);
	}

	public void EnableClickRight(boolean enalbe) {
		mBaseLayout.EnableClickRight(enalbe);
	}

	public void EnableClickMid(boolean enalbe) {
		mBaseLayout.EnableClickMid(enalbe);
	}

	public int getRightTxtColor() {
		return rightTxtColor;
	}

	public void setRightTxtColor(int rightTxtColor) {
		this.rightTxtColor = rightTxtColor;
	}

}
