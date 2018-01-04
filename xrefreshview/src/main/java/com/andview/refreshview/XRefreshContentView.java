package com.andview.refreshview;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;

public class XRefreshContentView implements OnScrollListener
{
	private View				child;
	private View				scrollView;
	// total list items, used to detect is at the bottom of listview.
	private int					mTotalItemCount;
	private OnScrollListener	mAbsListViewScrollListener;
	
	public void setContentViewLayoutParams()
	{
		LinearLayout.LayoutParams lp = (LayoutParams) child.getLayoutParams();
		lp.height = LayoutParams.MATCH_PARENT;
		lp.height = LayoutParams.MATCH_PARENT;
		// 默认设置宽高为match_parent
		child.setLayoutParams(lp);
	}
	
	public void setContentView(View child)
	{
		this.child = child;
		this.scrollView = child;
	}
	public void setContentView(View child, View scrollView)
	{
		this.child = child;
		this.scrollView = scrollView;
	}
	public void setContentView(View child, int scrollViewid)
	{
		this.child = child;
		View view = child.findViewById(scrollViewid);
		if (view == null)
		{
			this.scrollView = child;
		}
		else
		{
			this.scrollView = view;
		}
	}
	
	public View getContentView()
	{
		return child;
	}
	
	// public void setHolder(XRefreshHolder holder)
	// {
	// mHolder = holder;
	// }
	
	public void scrollToTop()
	{
		if (scrollView instanceof AbsListView) 
		{
			AbsListView absListView = (AbsListView) scrollView;
			absListView.setSelection(0);
		}
		else if (scrollView instanceof RecyclerView)
		{
			RecyclerView recyclerView = (RecyclerView) scrollView;
			RecyclerView.LayoutManager layoutManager = null;
			layoutManager = recyclerView.getLayoutManager();
			layoutManager.scrollToPosition(0);
		}
	}
	
	public void setScrollListener()
	{
		if (scrollView instanceof AbsListView)
		{
			AbsListView absListView = (AbsListView) scrollView;
			absListView.setOnScrollListener(this);
		}
	}
	
	public void setOnAbsListViewScrollListener(OnScrollListener listener)
	{
		mAbsListViewScrollListener = listener;
	}
	
	public boolean isTop()
	{
		return hasChildOnTop();
	}
	
	public boolean isBottom()
	{
		return hasChildOnBottom();
	}
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState)
	{
		if (mAbsListViewScrollListener != null)
		{
			mAbsListViewScrollListener.onScrollStateChanged(view, scrollState);
		}
	}
	
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
	{
		mTotalItemCount = totalItemCount;
		if (mAbsListViewScrollListener != null)
		{
			mAbsListViewScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
		}
	}
	
	public int getTotalItemCount()
	{
		return mTotalItemCount;
	}
	
	public boolean hasChildOnTop()
	{
		return !canChildPullDown();
	}
	
	public boolean hasChildOnBottom()
	{
		return !canChildPullUp();
	}
	
	/**
	 * @return Whether it is possible for the child view of this layout to
	 *         scroll up. Override this if the child view is a custom view.
	 */
	public boolean canChildPullDown()
	{
		if (scrollView instanceof AbsListView)
		{
			final AbsListView absListView = (AbsListView) scrollView;
			return canScrollVertically(scrollView, -1) || absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
		}
		else
		{
			return canScrollVertically(scrollView, -1) || scrollView.getScrollY() > 0;
		}
	}
	
	@SuppressWarnings("deprecation")
	public boolean canChildPullUp()
	{
		if (scrollView instanceof AbsListView)
		{
			AbsListView absListView = (AbsListView) scrollView;
			return canScrollVertically(scrollView, 1) || absListView.getLastVisiblePosition() != mTotalItemCount - 1;
		}
		else if (scrollView instanceof WebView)
		{
			WebView webview = (WebView) scrollView;
			return canScrollVertically(scrollView, 1) || webview.getContentHeight() * webview.getScale() != webview.getHeight() + webview.getScrollY();
		}
		else if (scrollView instanceof ScrollView)
		{
			ScrollView scrollView_t = (ScrollView) scrollView;
			View childView = scrollView_t.getChildAt(0);
			if (childView != null)
			{
				return canScrollVertically(scrollView, 1) || scrollView_t.getScrollY() != childView.getHeight() - scrollView_t.getHeight();
			}
		}
		else
		{
			return canScrollVertically(scrollView, 1);
		}
		return true;
	}
	
	/**
	 * 用来判断view在竖直方向上能不能向上或者向下滑动
	 *
	 * @param view
	 *            v
	 * @param direction
	 *            方向 负数代表向上滑动 ，正数则反之
	 * @return
	 */
	public boolean canScrollVertically(View view, int direction)
	{
		return ViewCompat.canScrollVertically(view, direction);
	}
	
	public void offsetTopAndBottom(int offset)
	{
		child.offsetTopAndBottom(offset);
	}
}
