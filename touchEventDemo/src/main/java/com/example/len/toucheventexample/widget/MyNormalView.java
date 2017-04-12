package com.example.len.toucheventexample.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.len.toucheventexample.utils.UtilTool;

/**
 * @author dragon
 * @date 2016/7/26 13:49
 */
public class MyNormalView extends View {
    public MyNormalView(Context context) {
        super(context);
    }

    public MyNormalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNormalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("dragon", "MyNormalView dispatchTouchEvent " + UtilTool.getEventName(event));
        boolean flag;
        flag = super.dispatchTouchEvent(event);
        //flag = true;
        Log.i("dragon", "MyNormalView dispatchTouchEvent return = " + flag);
        return flag;
        //return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("dragon", "MyNormalView onTouchEvent " + UtilTool.getEventName(event));
        boolean flag;
        flag = super.onTouchEvent(event);
        // flag = true;
        Log.e("dragon", "MyNormalView onTouchEvent return = " + flag);
        return flag;
        // return super.onTouchEvent(event);
    }
}
