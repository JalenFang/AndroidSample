package com.jalen.scrolldemo.view;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author Dragon
 * @date 2017/4/25. 11:21
 * @editor
 * @date
 * @describe
 */
public class ViewDragHelperView extends LinearLayout {

    private ViewDragHelper viewDragHelper;
    private View mDragView;
    private View mAutoBackView;
    private View mEdgeTrackerView;

    private Point mAutoBackOriginPos = new Point();

    public ViewDragHelperView(Context context) {
        super(context);
        init();
    }

    public ViewDragHelperView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewDragHelperView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        viewDragHelper = ViewDragHelper.create(this, callBack);
        viewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView = getChildAt(0);
        mAutoBackView = getChildAt(1);
        mEdgeTrackerView = getChildAt(2);
    }

    //保存最开始的时候mAutoBackView的位置信息
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mAutoBackOriginPos.x = mAutoBackView.getLeft();
        mAutoBackOriginPos.y = mAutoBackView.getTop();
    }

    private ViewDragHelper.Callback callBack = new ViewDragHelper.Callback() {

        //如果返回ture则表示可以捕获该view，你可以根据传入的第一个view参数决定哪些可以捕获
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            //mEdgeTrackerView禁止直接移动
            return child == mAutoBackView || child == mDragView;
        }

        //clampViewPositionHorizontal,clampViewPositionVertical可以在该方法中对child移动的边界进行控制
        // left , top 分别为即将移动到的位置
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.i("dragon", "left " + left + " ; " + " dx " + dx);
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            Log.i("dragon", "top " + top + " ; " + " dy " + dy);
            return top;
        }

        //手指释放的时候回调
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (releasedChild == mAutoBackView) {
                viewDragHelper.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
                invalidate();
            }
        }

        //在边界拖动时回调
        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            super.onEdgeDragStarted(edgeFlags, pointerId);
            viewDragHelper.captureChildView(mEdgeTrackerView, pointerId);
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return getMeasuredWidth() - child.getMeasuredWidth();
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return getMeasuredHeight() - child.getMeasuredWidth();
        }
    };

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (viewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }
}
