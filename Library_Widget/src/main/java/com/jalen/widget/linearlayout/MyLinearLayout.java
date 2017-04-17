package com.jalen.widget.linearlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Dragon
 * @date 2017/4/17. 10:59
 * @editor
 * @date
 * @describe
 */
public class MyLinearLayout extends ViewGroup {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        Log.i("dragon", "widthMode = " + widthMode);
        Log.i("dragon", "widthSize = " + widthSize);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        Log.i("dragon", "heightSize = " + heightSize);
        Log.i("dragon", "heightMode = " + heightMode);

        int height = 0;
        int width = 0;

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();

            int measuredHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int measuredWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;

            height += measuredHeight;
            width = Math.max(width, measuredWidth);
        }

        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width, heightMode == MeasureSpec.EXACTLY ? heightSize : height);
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();

        Log.w("dragon", "measuredHeight = " + measuredHeight);
        Log.w("dragon", "measuredWidth = " + measuredWidth);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int top = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();

            /*int measuredHeight = child.getMeasuredHeight();
            int measuredWidth = child.getMeasuredWidth();
            child.layout(0, top, measuredWidth, top + measuredHeight);
            top += measuredHeight;
            */

            child.layout(layoutParams.leftMargin, top + layoutParams.topMargin, child.getMeasuredWidth() + layoutParams.leftMargin, top + child.getMeasuredHeight() + layoutParams.topMargin);
            top += child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }


}
