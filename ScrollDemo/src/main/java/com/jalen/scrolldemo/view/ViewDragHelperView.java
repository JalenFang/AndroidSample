package com.jalen.scrolldemo.view;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
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
    }

    private ViewDragHelper.Callback callBack = new ViewDragHelper.Callback() {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        //left , top 分别为即将移动到的位置
        /*比如横向的情况下，我希望只在ViewGroup的内部移动，
        即：最小>=paddingleft，最大<=ViewGroup.getWidth()-paddingright-child.getWidth*/
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            final int leftBound = getPaddingLeft();
            final int rightBound = getWidth() - child.getWidth() - leftBound;

            final int newLeft = Math.min(Math.max(left, leftBound), rightBound);

            return newLeft;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return top;
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
}
