package com.jalen.canvasdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.jalen.canvasdemo.utils.PaintUtil;

/**
 * @author Jalen
 * @date 2016/12/23 21:52
 * @describe
 */
public class ArcView extends View {
    private Context context;
    private Paint paint;

    public ArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public ArcView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init() {
        paint = PaintUtil.getPaint(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawArcView(canvas);
        drawRect(canvas);

        drawRect2(canvas);
        drawArcView2(canvas);

        drawRect3(canvas);
        drawArcView3(canvas);
    }

    //当 drawArcs(rect,startAngel,sweepAngel,useCenter,paint)中的
    // useCenter为false时，弧线区域是用弧线开始角度和结束角度直接连接起来的，
    // 当useCenter为true时，是弧线开始角度和结束角度都与中心点连接，形成一个扇形。

    private void drawArcView2(Canvas canvas) {
        RectF rect = new RectF(200, 200, 400, 400);
        canvas.drawArc(rect, //弧线所使用的矩形区域大小
                0,  //开始角度
                90, //扫过的角度
                true, //是否使用中心
                paint);
    }

    private void drawRect2(Canvas canvas) {
        RectF rect = new RectF(200, 200, 400, 400);
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect, paint);
    }

    private void drawRect(Canvas canvas) {
        RectF rect = new RectF(0, 0, 100, 100);
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect, paint);
    }

    private void drawArcView(Canvas canvas) {
        RectF rect = new RectF(0, 0, 100, 100);
        canvas.drawArc(rect, //弧线所使用的矩形区域大小
                0,  //开始角度
                90, //扫过的角度
                false, //是否使用中心
                paint);
    }

    private void drawArcView3(Canvas canvas) {
        RectF rect = new RectF(400, 400, 600, 600);
        canvas.drawArc(rect, //弧线所使用的矩形区域大小
                45,  //开始角度
                90, //扫过的角度
                true, //是否使用中心
                paint);
    }

    private void drawRect3(Canvas canvas) {
        RectF rect = new RectF(400, 400, 600, 600);
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect, paint);
    }


}
