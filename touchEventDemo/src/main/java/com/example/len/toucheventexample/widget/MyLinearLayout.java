package com.example.len.toucheventexample.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.len.toucheventexample.utils.UtilTool;

/**
 * @author dragon
 * @date 2016/7/26 13:47
 */

public class MyLinearLayout extends LinearLayout {


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
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("dragon", "MyLinearLayout dispatchTouchEvent " + UtilTool.getEventName(ev));
        boolean flag;
        flag = super.dispatchTouchEvent(ev);
        //flag = true;
        Log.i("dragon", "MyLinearLayout dispatchTouchEvent return = " + flag);
        return flag;
        // return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.w("dragon", "MyLinearLayout onInterceptTouchEvent　" + UtilTool.getEventName(ev));
        boolean flag;
        flag = super.onInterceptTouchEvent(ev);
        //flag = true;
        Log.w("dragon", "MyLinearLayout onInterceptTouchEvent return = " + flag);
        return flag;
        //return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("dragon", "MyLinearLayout onTouchEvent " + UtilTool.getEventName(event));
        boolean flag;
        flag = super.onTouchEvent(event);
        // flag = true;
        Log.e("dragon", "MyLinearLayout onTouchEvent return = " + flag);
        return flag;
        //return super.onTouchEvent(event);
    }
}
