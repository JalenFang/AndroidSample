package com.jalen.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Dragon
 * @date 2017/6/6. 10:34
 * @editor
 * @date
 * @describe
 */
public class CircleRingView extends View {

    private Paint paint;

    /**
     * 圆环的宽度
     */
    private int roundWidth = 130;

    /**
     * 中间进度百分比的字符串的字体
     */
    private int textSize = 176;


    public CircleRingView(Context context) {
        this(context, null);
    }

    public CircleRingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircleRing(canvas);
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    private void drawCircleRing(Canvas canvas) {
        //1.画最外层圆环
        int center = getWidth() / 2;//圆心的坐标
        int radius = center - roundWidth / 2;//圆的半径
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWidth);
        canvas.drawCircle(center, center, radius, paint);

        //2.画进度百分比
        paint.setStrokeWidth(0);
        paint.setTextSize(textSize);
        paint.setColor(Color.BLUE);
        paint.setTypeface(Typeface.DEFAULT);
        String percent = "75%";

        float textWidth = paint.measureText(percent);
        canvas.drawText(percent, center - textWidth / 2, center + textSize / 2, paint);

        //3.画圆环的进度
        paint.setStrokeWidth(roundWidth);
        paint.setColor(Color.BLUE);
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);  //用于定义的圆弧的形状和大小的界限

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(oval, 270 ,360 * 75 / 100, false, paint);  //根据进度画圆弧
    }
}
