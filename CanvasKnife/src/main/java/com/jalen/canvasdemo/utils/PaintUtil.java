package com.jalen.canvasdemo.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;

import com.jalen.canvasdemo.R;

/**
 * @author Jalen
 * @date 2016/12/7 22:37
 * @describe Paint的使用类
 */
public class PaintUtil {
    public static Paint paint;

    public static Paint getPaint(Context context) {
        if (paint == null) {
            paint = new Paint();
            paint.setAntiAlias(true);//抗锯齿
            paint.setColor(Color.RED);//画笔颜色
            // paint.setStrokeWidth(10.0f);//线的宽度
            // paint.setStyle(Paint.Style.STROKE);//空心效果
            paint.setStyle(Paint.Style.FILL);//实心效果
            paint.setTextSize(ScreenUtils.sp2px(context, context.getResources().getDimension(R.dimen.text_12sp)));
        }
        return paint;
    }
}
