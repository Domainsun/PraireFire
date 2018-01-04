package com.andview.refreshview;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.andview.refreshview.callback.IFooterCallBack;
import com.andview.refreshview.callback.IHeaderCallBack;
import com.andview.refreshview.utils.LogUtils;
import com.andview.refreshview.utils.Utils;

public class XRefreshView extends LinearLayout {
	// -- header view
	private View					mHeaderView;

	private int						mHeaderViewHeight;									// header
																						// view's
																						// height

	// 实际滚动view
	private View					scrollView						= null;
	private int						scrollViewId					= 0;
	/**
	 * 最初的滚动位置.第一次布局时滚动header的高度的距离
	 */
	protected int					mInitScrollY					= 0;
	private int						mLastY							= -1;				// save
																						// event
																						// y
	private int						mLastX							= -1;				// save
																						// event
																						// x

	private int						mStartY							= -1;				// save
	// event
	// y
	// event
	// x

	private int						mFootHeight;
	private XRefreshViewFooter		mFooterView;
	private boolean					mEnablePullLoad					= false;
	public boolean					mPullLoading;

	private boolean					mEnablePullRefresh				= false;
	public boolean					mPullRefreshing					= false;			// is
																						// refreashing.
	private float					OFFSET_RADIO					= 1.8f;			// support
																						// iOS
																						// like
																						// pull

	private int						SCROLL_DURATION					= 300;				// scroll
																						// back
																						// duration
	private XRefreshViewListener	mRefreshViewListener;

	/**
	 * 默认不自动刷新
	 */
	private boolean					autoRefresh						= false;
	/**
	 * 被刷新的view
	 */
	private XRefreshContentView		mContentView;
	private int						mTouchSlop;
	private XRefreshHolder			mHolder;

	private MotionEvent				mLastMoveEvent;
	private boolean					mHasSendCancelEvent				= false;
	private boolean					mHasSendDownEvent				= false;
	private Scroller				mScroller;
	private boolean					mMoveForHorizontal				= false;
	private boolean					isForHorizontalMove				= false;
	private boolean					mIsIntercept					= false;
	private IHeaderCallBack			mHeaderCallBack;
	private IFooterCallBack			mFooterCallBack;
	/**
	 * 当刷新完成以后，headerview和footerview被固定的时间，在这个时间以后headerview才会回弹
	 */
	private int						mPinnedTime;
	/**
	 * 有没有滚回初始位置
	 */
	private boolean					mHasScrollBack;
	private static Handler			mHandler						= new Handler();
	private XRefreshViewState		mState							= null;
	/**
	 * 在刷新的时候是否可以移动contentView
	 */
	private boolean					mIsPinnedContentWhenRefreshing	= true;

	public XRefreshView(Context context) {
		this(context, null);
	}

	public XRefreshView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setClickable(true);
		setLongClickable(true);
		mContentView = new XRefreshContentView();
		mHolder = new XRefreshHolder();
		mScroller = new Scroller(getContext(), new LinearInterpolator());

		initWithContext(context, attrs);
		setOrientation(VERTICAL);
	}

	public void setTouchSlopScale(float scale) {
		if (scale <= 1.f) {
			return;
		}
		mTouchSlop = (int) (ViewConfiguration.get(getContext()).getScaledTouchSlop() * scale + 0.5f);
	}

	/**
	 * pass true if need use for Horizontal move, or false
	 *
	 * @param isForHorizontalMove
	 *            default false
	 */
	public void setMoveForHorizontal(boolean isForHorizontalMove) {
		this.isForHorizontalMove = isForHorizontalMove;
	}

	private void initWithContext(Context context, AttributeSet attrs) {
		mHeaderView = new XRefreshViewHeader(getContext());
		mFooterView = new XRefreshViewFooter(getContext());
		this.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				addHeaderView();
				if (mEnablePullLoad) {
					Log.i("CustomView", "add footView");
					addView(mFooterView);
					mFooterView.measure(0, 0);
					mFooterCallBack = mFooterView;
				}
				endAdd(this);
			}
		});
		mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
	}

	private void addHeaderView() {
		addView(mHeaderView, 0);
		mHeaderView.measure(0, 0);
		if (scrollView != null) {
			mContentView.setContentView(XRefreshView.this.getChildAt(1), scrollView);
		} else if (scrollViewId != 0) {
			mContentView.setContentView(XRefreshView.this.getChildAt(1), scrollViewId);
		} else {
			mContentView.setContentView(XRefreshView.this.getChildAt(1));
		}
		mContentView.setContentViewLayoutParams();
		mHeaderCallBack = (IHeaderCallBack) mHeaderView;
		checkPullRefreshEnable();
	}

	private void endAdd(OnGlobalLayoutListener listener) {
		mHeaderViewHeight = ((IHeaderCallBack) mHeaderView).getHeaderHeight();
		mFootHeight = ((IFooterCallBack) mFooterCallBack).getFooterHeight();
		mContentView.setScrollListener();
		// 移除视图树监听器
		removeViewTreeObserver(listener);

		if (autoRefresh) {
			startRefresh();
		}
		if (mHeadMoveDistence == 0) {
			int ScreenHeight = Utils.getScreenSize(getContext()).y;
			mHeadMoveDistence = ScreenHeight / 3;
		}
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public void removeViewTreeObserver(OnGlobalLayoutListener listener) {
		if (Build.VERSION.SDK_INT < VERSION_CODES.JELLY_BEAN) {
			getViewTreeObserver().removeGlobalOnLayoutListener(listener);
		} else {
			getViewTreeObserver().removeOnGlobalLayoutListener(listener);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int childCount = getChildCount();
		int finalHeight = 0;
		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			if (child.getVisibility() != View.GONE) {
				measureChild(child, widthMeasureSpec, heightMeasureSpec);
				finalHeight += child.getMeasuredHeight();
			}
		}
		setMeasuredDimension(width, finalHeight);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		// if(mHolder.mOffsetY!=0)return;
		LogUtils.d("onLayout mHolder.mOffsetY=" + mHolder.mOffsetY);
		int childCount = getChildCount();
		int top = getPaddingTop() + mHolder.mOffsetY;
		int adHeight = 0;
		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			if (child.getVisibility() != View.GONE) {
				if (i == 0) {
					adHeight = child.getMeasuredHeight() - mHeaderViewHeight;
					// 通过把headerview向上移动一个headerview高度的距离来达到隐藏headerview的效果
					child.layout(0, top - mHeaderViewHeight, child.getMeasuredWidth(), top + adHeight);
					top += adHeight;
				} else if (i == 1) {
					int childHeight = child.getMeasuredHeight() - adHeight;
					child.layout(0, top, child.getMeasuredWidth(), childHeight + top);
					top += childHeight;
				} else {
					child.layout(0, top, child.getMeasuredWidth(), child.getMeasuredHeight() + top);
					top += child.getMeasuredHeight();
				}
			}
		}
	}

	private boolean	isIntercepted;
	private int		mHeadMoveDistence;

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		final int action = ev.getAction();
		int deltaY = 0;
		int deltaX = 0;
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			mHasSendCancelEvent = false;
			mHasSendDownEvent = false;
			mStartY = (int) ev.getRawY();
			mLastY = (int) ev.getRawY();
			mLastX = (int) ev.getRawX();
			break;
		case MotionEvent.ACTION_MOVE:
			mLastMoveEvent = ev;
			if (mPullRefreshing || !isEnabled() || mIsIntercept || mHasScrollBack || mPullLoading) {
				if (mIsPinnedContentWhenRefreshing) {
					return super.dispatchTouchEvent(ev);
				} else {
					sendCancelEvent();
					return true;
				}
			}
			int currentY = (int) ev.getRawY();
			int currentX = (int) ev.getRawX();
			deltaY = currentY - mLastY;
			deltaX = currentX - mLastX;
			mLastY = currentY;
			mLastX = currentX;
			// intercept the MotionEvent only when user is not scrolling
			if (!isIntercepted && (Math.abs(deltaY) <= mTouchSlop)) {
				isIntercepted = true;
				return super.dispatchTouchEvent(ev);
			}
			if (isForHorizontalMove && !mMoveForHorizontal && Math.abs(deltaX) > mTouchSlop && Math.abs(deltaX) > Math.abs(deltaY)) {
				if (mHolder.mOffsetY == 0) {
					mMoveForHorizontal = true;
				}
			}
			if (mMoveForHorizontal) {
				return super.dispatchTouchEvent(ev);
			}
			LogUtils.d("isTop=" + mContentView.isTop() + ";isBottom=" + mContentView.isBottom());
			if (deltaY > 0 && mHolder.mOffsetY <= mHeadMoveDistence || deltaY < 0) {
				deltaY = (int) (deltaY / OFFSET_RADIO);
			} else {
				deltaY = 0;
			}

			if (mContentView.isTop() && (deltaY > 0 || (deltaY < 0 && mHolder.hasHeaderPullDown()))) {
				if (Math.abs(currentY - mStartY) > mTouchSlop * 2) {
					sendCancelEvent();
				}
				updateHeaderHeight(currentY, deltaY);
			} else if (mContentView.isBottom() && (deltaY < 0 || deltaY > 0 && mHolder.hasFooterPullUp())) {
				if (Math.abs(currentY - mStartY) > mTouchSlop * 2) {
					sendCancelEvent();
				}
				updateFooterHeight(currentY, deltaY);
			} else if (mContentView.isTop() && !mHolder.hasHeaderPullDown() || mContentView.isBottom() && !mHolder.hasFooterPullUp()) {
				if (Math.abs(deltaY) > 0)
					sendDownEvent();
			}
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			if (mHolder.hasHeaderPullDown() && !mHasScrollBack) {
				// invoke refresh
				if (mEnablePullRefresh && mHolder.mOffsetY > mHeaderViewHeight) {
					mPullRefreshing = true;
					mHeaderCallBack.onStateRefreshing();
					mState = XRefreshViewState.STATE_REFRESHING;
					if (mRefreshViewListener != null) {
						mRefreshViewListener.onRefresh();
					}
				}
				resetHeaderHeight();
			} else if (mContentView.isBottom() && mHolder.hasFooterPullUp() && !mHasScrollBack) {
				if (mEnablePullLoad) {
					if (-mHolder.mOffsetY > mFootHeight) {
						mPullLoading = true;
						mFooterCallBack.onStateLoading();
						mState = XRefreshViewState.STATE_REFRESHING;
						if (mRefreshViewListener != null) {
							mRefreshViewListener.onLoadMore();
						}
					}
				}
				resetFooterHeight();
			}
			mLastY = -1; // reset
			isIntercepted = true;
			mMoveForHorizontal = false;
			mIsIntercept = false;
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

	/**
	 * if child need the touch event,pass true
	 */
	public void disallowInterceptTouchEvent(boolean isIntercept) {
		mIsIntercept = isIntercept;
	}

	@SuppressLint("Recycle")
	private void sendCancelEvent() {
		LogUtils.d("sendCancelEvent");
		if (!mHasSendCancelEvent) {
			setRefreshTime();
			mHasSendCancelEvent = true;
			mHasSendDownEvent = false;
			MotionEvent last = mLastMoveEvent;
			MotionEvent e =
					MotionEvent.obtain(
							last.getDownTime(),
							last.getEventTime() + ViewConfiguration.getLongPressTimeout(),
							MotionEvent.ACTION_CANCEL,
							last.getX(),
							last.getY(),
							last.getMetaState());
			dispatchTouchEventSupper(e);
		}
	}

	/**
	 * header可下拉的最大距离
	 *
	 * @param headMoveDistence
	 */
	public void setHeadMoveLargestDistence(int headMoveDistence) {
		mHeadMoveDistence = headMoveDistence;
	}

	@SuppressLint("Recycle")
	private void sendDownEvent() {
		if (!mHasSendDownEvent) {
			LogUtils.d("sendDownEvent");
			mHasSendCancelEvent = false;
			mHasSendDownEvent = true;
			isIntercepted = false;
			final MotionEvent last = mLastMoveEvent;
			if (last == null)
				return;
			MotionEvent e = MotionEvent.obtain(last.getDownTime(), last.getEventTime(), MotionEvent.ACTION_DOWN, last.getX(), last.getY(), last.getMetaState());
			dispatchTouchEventSupper(e);
		}
	}

	public boolean dispatchTouchEventSupper(MotionEvent e) {
		return super.dispatchTouchEvent(e);
	}

	/**
	 * enable or disable pull down refresh feature.
	 *
	 * @param enable
	 */
	public void setPullRefreshEnable(boolean enable) {
		mEnablePullRefresh = enable;
	}

	/**
	 * enable or disable pull up load more feature.
	 * 
	 * @param enable
	 */
	public void setPullLoadEnable(boolean enable) {
		LogUtils.d("setPullLoadEnable");
		mEnablePullLoad = enable;
		if (!mEnablePullLoad) {
			mFooterView.hide();
			mFooterView.setOnClickListener(null);
		} else {
			mPullLoading = false;
			mFooterView.show();
		}
	}

	private void checkPullRefreshEnable() {
		if (!mEnablePullRefresh) {
			mHeaderCallBack.hide();
		} else {
			mHeaderCallBack.show();
		}
	}

	/**
	 * 如果第可变参数不为空，则代表是自动刷新
	 *
	 * @param currentY
	 * @param deltaY
	 * @param during
	 */
	private void updateHeaderHeight(int currentY, int deltaY, int... during) {
		boolean isAutoRefresh = during != null && during.length > 0;
		if (isAutoRefresh) {
			mHeaderCallBack.onStateRefreshing();
			startScroll(deltaY, during[0]);
		} else {
			if (mHolder.isOverHeader(deltaY)) {
				deltaY = -mHolder.mOffsetY;
			}
			moveView(deltaY);
			if (mEnablePullRefresh && !mPullRefreshing) {
				if (mHolder.mOffsetY > mHeaderViewHeight) {
					if (mState != XRefreshViewState.STATE_READY) {
						mHeaderCallBack.onStateReady();
						mState = XRefreshViewState.STATE_READY;
					}
				} else {
					if (mState != XRefreshViewState.STATE_NORMAL) {
						mHeaderCallBack.onStateNormal();
						mState = XRefreshViewState.STATE_NORMAL;
					}
				}
			}
		}
	}

	private void updateFooterHeight(int currentY, int deltaY, int... during) {
		boolean isAutoRefresh = during != null && during.length > 0;
		if (isAutoRefresh) {
			mHeaderCallBack.onStateRefreshing();
			startScroll(deltaY, during[0]);
		} else {
			moveView(deltaY);
			if (mEnablePullLoad) {
				if (-mHolder.mOffsetY > mFootHeight) {
					if (mState != XRefreshViewState.STATE_READY) {
						mFooterCallBack.onStateReady();
						mState = XRefreshViewState.STATE_READY;
					}
				} else {
					if (mState != XRefreshViewState.STATE_NORMAL) {
						mFooterCallBack.onStateNormal();
						mState = XRefreshViewState.STATE_NORMAL;
					}
				}
			}
		}
	}

	/**
	 * 设置是否自动刷新，默认不自动刷新
	 *
	 * @param autoRefresh
	 *            true则自动刷新
	 */
	public void setAutoRefresh(boolean autoRefresh) {
		this.autoRefresh = autoRefresh;
		setRefreshTime();
	}

	public void startRefresh() {
		if (mHolder.mOffsetY != 0 || !isEnabled()) {
			return;
		}
		// 如果条件成立，代表布局还没有初始化完成，改变标记，等待该方法再次调用，完成开始刷新
		if (mHeaderCallBack == null) {
			this.autoRefresh = true;
		} else {
			if (!mPullRefreshing) {
				updateHeaderHeight(0, mHeaderViewHeight, 0);
			}
			mPullRefreshing = true;
			if (mRefreshViewListener != null) {
				mRefreshViewListener.onRefresh();
			}
			mContentView.scrollToTop();
		}
	}

	/**
	 * reset header view's height.
	 */
	private void resetHeaderHeight() {
		float height = mHolder.mOffsetY;
		if (height == 0) // not visible.
			return;
		// refreshing and header isn't shown fully. do nothing.
		if (mPullRefreshing && height <= mHeaderViewHeight) {
			return;
		}
		int offsetY = 0;
		if (mPullRefreshing) {
			offsetY = mHeaderViewHeight - mHolder.mOffsetY;
			startScroll(offsetY, SCROLL_DURATION);
		} else {
			offsetY = 0 - mHolder.mOffsetY;
			startScroll(offsetY, SCROLL_DURATION);
		}
		LogUtils.d("resetHeaderHeight offsetY=" + offsetY);
	}

	/**
	 * reset footer view's height.
	 */
	private void resetFooterHeight() {
		float height = mHolder.mOffsetY;
		if (height == 0) // not visible.
			return;
		// refreshing and header isn't shown fully. do nothing.
		if (mPullLoading && height <= mFootHeight) {
			return;
		}
		int offsetY = 0;
		if (mPullLoading) {
			offsetY = mFootHeight - mHolder.mOffsetY;
			startScroll(offsetY, SCROLL_DURATION);
		} else {
			offsetY = 0 - mHolder.mOffsetY;
			startScroll(offsetY, SCROLL_DURATION);
		}
		LogUtils.d("resetFooterHeight offsetY=" + offsetY);
	}

	public void moveView(int deltaY) {
		mHolder.move(deltaY);
		mHeaderView.offsetTopAndBottom(deltaY);
		mContentView.offsetTopAndBottom(deltaY);
		mFooterView.offsetTopAndBottom(deltaY);
		ViewCompat.postInvalidateOnAnimation(this);

		if (mRefreshViewListener != null && (mContentView.isTop() || mPullRefreshing)) {
			double offset = 1.0 * mHolder.mOffsetY / mHeaderViewHeight;
			offset = offset > 1 ? 1 : offset;
			mRefreshViewListener.onHeaderMove(offset, mHolder.mOffsetY);
			mHeaderCallBack.onHeaderMove(offset, mHolder.mOffsetY, deltaY);
		}
	}

	@Override
	public void computeScroll() {
		super.computeScroll();
		if (mScroller.computeScrollOffset()) {
			int lastScrollY = mHolder.mOffsetY;
			int currentY = mScroller.getCurrY();
			int offsetY = currentY - lastScrollY;
			lastScrollY = currentY;
			moveView(offsetY);

			LogUtils.d("currentY=" + currentY + ";mHolder.mOffsetY=" + mHolder.mOffsetY);
		} else {
			LogUtils.d("scroll end mOffsetY=" + mHolder.mOffsetY);
			if (mHolder.mOffsetY == 0)
				mHasScrollBack = false;
		}
	}

	/**
	 * stop refresh, reset header view.
	 */
	public void stopRefresh() {
		LogUtils.i("stopRefresh mPullRefreshing=" + mPullRefreshing);
		if (mPullRefreshing == true) {
			mPullRefreshing = false;
			mHeaderCallBack.onStateFinish();
			mState = XRefreshViewState.STATE_COMPLETE;
			mHasScrollBack = true;
			mHandler.postDelayed(new Runnable() {

				@Override
				public void run() {
					resetHeaderHeight();
					lastRefreshTime = Calendar.getInstance().getTimeInMillis();
				}
			}, mPinnedTime);
		}

		if (mPullLoading == true) {
			mPullLoading = false;
			mFooterCallBack.onStateFinish();
			mState = XRefreshViewState.STATE_COMPLETE;
			mHasScrollBack = true;
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					resetFooterHeight();
				}
			}, mPinnedTime);
		}
	}

	private long	lastRefreshTime	= -1;

	/**
	 * 恢复上次刷新的时间
	 *
	 * @param lastRefreshTime
	 */
	public void restoreLastRefreshTime(long lastRefreshTime) {
		this.lastRefreshTime = lastRefreshTime;
	}

	/**
	 * 在停止刷新的时候调用，记录这次刷新的时间，用于下次刷新的时候显示
	 *
	 * @return
	 */
	public long getLastRefreshTime() {
		return lastRefreshTime;
	}

	/**
	 * 设置并显示上次刷新的时间
	 */
	private void setRefreshTime() {
		if (lastRefreshTime <= 0) {
			return;
		}
		mHeaderCallBack.setRefreshTime(lastRefreshTime);
	}

	/**
	 * @param offsetY
	 *            滑动偏移量，负数向上滑，正数反之
	 * @param duration
	 *            滑动持续时间
	 */
	public void startScroll(int offsetY, int duration) {
		mHasScrollBack = true;
		if (offsetY != 0) {
			mScroller.startScroll(0, mHolder.mOffsetY, 0, offsetY, duration);
			invalidate();
		}
	}

	/**
	 * 设置Abslistview的滚动监听事件
	 *
	 * @param scrollListener
	 */
	public void setOnAbsListViewScrollListener(OnScrollListener scrollListener) {
		mContentView.setOnAbsListViewScrollListener(scrollListener);
	}

	public void setXRefreshViewListener(XRefreshViewListener l) {
		mRefreshViewListener = l;
	}

	/**
	 * 设置headerview回滚的时间，默认400毫秒
	 *
	 * @param during
	 */
	public void setScrollDuring(int during) {
		SCROLL_DURATION = during;
	}

	/**
	 * 设置阻尼系数，建议使用默认的
	 *
	 * @param ratio
	 *            默认 1.8
	 */
	public void setDampingRatio(float ratio) {
		OFFSET_RADIO = ratio;
	}

	/**
	 * 设置当下拉刷新完成以后，headerview和footerview被固定的时间
	 * 注:考虑到ui效果，只有时间大于1s的时候，footerview被固定的效果才会生效
	 *
	 * @param pinnedTime
	 */
	public void setPinnedTime(int pinnedTime) {
		mPinnedTime = pinnedTime;
	}

	/**
	 * 设置在刷新的时候是否可以移动contentView
	 *
	 * @param isPinned
	 *            true 固定不移动 反之，可以移动
	 */
	public void setPinnedContent(boolean isPinned) {
		mIsPinnedContentWhenRefreshing = !isPinned;
	}

	/**
	 * 设置自定义headerView
	 *
	 * @param headerView
	 *            headerView必须要实现 IHeaderCallBack接口
	 */
	public void setCustomHeaderView(View headerView) {
		if (headerView instanceof IHeaderCallBack) {
			mHeaderView = headerView;
		} else {
			throw new RuntimeException("headerView must be implementes IHeaderCallBack!");
		}
	}

	/**
	 * 设置自定义headerView
	 *
	 * @param headerView
	 *            headerView必须要实现 IHeaderCallBack接口
	 */
	public void setCustomFooterView(XRefreshViewFooter footerView) {
		if (footerView instanceof IFooterCallBack) {
			mFooterView = footerView;
		} else {
			throw new RuntimeException("footerView must be implementes IFooterCallBack!");
		}
	}

	/**
	 * 设置嵌套滚动的view
	 * 
	 * @Param view 当view不是XRefreshView的直接子View时需调用此接口
	 */
	public void setNestedScrollView(View view) {
		if (view != null) {
			scrollView = view;
		}
	}

	/**
	 * 设置嵌套滚动的view
	 * 
	 * @Param viewid 当view不是XRefreshView的直接子View时需调用此接口
	 */
	public void setNestedScrollView(int viewid) {
		if (viewid != 0) {
			scrollViewId = viewid;
		}
	}

	/**
	 * implements this interface to get refresh/load more event.
	 */
	public interface XRefreshViewListener {
		public void onRefresh();

		public void onLoadMore();

		/**
		 * 获取headerview显示的高度与headerview高度的比例
		 *
		 * @param offset
		 *            移动距离和headerview高度的比例，范围是0~1，0：headerview完全没显示
		 *            1：headerview完全显示
		 * @param offsetY
		 *            headerview移动的距离
		 */
		public void onHeaderMove(double offset, int offsetY);
	}

	public static class SimpleXRefreshListener implements XRefreshViewListener {

		@Override
		public void onRefresh() {
		}

		@Override
		public void onHeaderMove(double offset, int offsetY) {
		}

		@Override
		public void onLoadMore() {

		}

	}
}
