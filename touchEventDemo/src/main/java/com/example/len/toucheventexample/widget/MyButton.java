package com.example.len.toucheventexample.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.len.toucheventexample.utils.UtilTool;

/**
 * Created by Jalen on 2016/7/26.
 */
public class MyButton extends Button {
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("dragon", "MyButton dispatchTouchEvent " + UtilTool.getEventName(event));
        boolean flag;
        flag = super.dispatchTouchEvent(event);
        //flag = true;
        Log.i("dragon", "MyButton dispatchTouchEvent return = " + flag);
        return flag;
        //return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("dragon", "MyButton onTouchEvent　" + UtilTool.getEventName(event));
        boolean flag;
        flag = super.onTouchEvent(event);
        // flag = true;
        Log.e("dragon", "MyButton onTouchEvent return = " + flag);
        return flag;
        //return super.onTouchEvent(event);
    }
}
