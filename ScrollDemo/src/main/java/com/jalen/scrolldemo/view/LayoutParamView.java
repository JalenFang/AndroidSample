package com.jalen.scrolldemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Dragon
 * @date 2017/4/21. 14:07
 * @editor
 * @date
 * @describe
 */
public class LayoutParamView extends View {

    private int lastX, lastY;

    public LayoutParamView(Context context) {
        super(context);
        init();
    }

    public LayoutParamView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LayoutParamView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) getLayoutParams();
                params.topMargin = getTop() + offsetY;
                params.leftMargin = getLeft() + offsetX;
                setLayoutParams(params);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return true;
    }
}
