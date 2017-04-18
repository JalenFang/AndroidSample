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
 * @describe 自定义ViewGroup
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

    /**
     * 测量MyLinearLayout的大小
     *
     * @param widthMeasureSpec  父View给MyLinearLayout宽度的建议值 包括测量的模式和大小
     * @param heightMeasureSpec 父View给MyLinearLayout高度的建议值 包括测量的模式和大小
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

       /* MeasureSpec.getMode 获取测量模式
        共有三种模式：
        MeasureSpec.EXACTLY 精确的 父元素决定子元素的确切大小，子元素将被限定在给定的边界里而忽略它本身大小  match_parent
        MeasureSpec.AT_MOST 子元素至多达到指定大小的值 wrap_content
        MeasureSpec.UNSPECIFIED 父元素不对子元素施加任何束缚，子元素可以得到任意想要的大小*/


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

    /**
     * 设置子View的位置
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
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
