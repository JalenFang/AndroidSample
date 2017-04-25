package com.jalen.scrolldemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * @author Dragon
 * @date 2017/4/24. 9:33
 * @editor
 * @date
 * @describe
 */
public class ScrollerView extends View {

    private int initX;
    private int initY;
    private int lastX;
    private int lastY;
    private Scroller scroller;

    public ScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollerView(Context context) {
        super(context);
        init();
    }

    public ScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        scroller = new Scroller(getContext());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("dragon", "onSizeChanged execute");
        initX = getLeft();
        initY = getTop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        //Log.i("dragon", "x = " + x + ";" + "y = " + y);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                if (scroller.computeScrollOffset()) {
                    scroller.forceFinished(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                //Log.i("dragon", "offsetX = " + offsetX + ";" + "offsetY = " + offsetY);
                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);
                break;
            case MotionEvent.ACTION_UP:
                scroller.startScroll(getLeft(), getTop(), initX - getLeft(), initY - getTop(), 2000);
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if (scroller.computeScrollOffset()) {
            offsetLeftAndRight(scroller.getCurrX() - getLeft());
            offsetTopAndBottom(scroller.getCurrY() - getTop());

            invalidate();
        }
    }
}
