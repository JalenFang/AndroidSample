package com.jalen.canvasdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.jalen.canvasdemo.utils.ScreenUtils;

/**
 * @author Jalen
 * @date 2016/12/14 21:51
 * @describe
 */
public class BezierView extends View {
    private Paint mPaint;
    private Path mPath;
    private Point startPoint;
    private Point endPoint;

    // 辅助点
    private Point assistPoint;

    private Context context;

    public BezierView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        Point screenSize = ScreenUtils.getScreenSize(context);
        Log.i("jalen", "" + screenSize.toString());//720 1280
        mPaint = new Paint();
        mPath = new Path();
        startPoint = new Point(160, 854);
        endPoint = new Point(560, 854);
        assistPoint = new Point(360, 600);
        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 防抖动
        mPaint.setDither(true);
        // 画笔颜色
        mPaint.setColor(Color.GREEN);
        // 笔宽
        mPaint.setStrokeWidth(10);
        // 空心
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 重置路径
        mPath.reset();
        // 起点
        mPath.moveTo(startPoint.x, startPoint.y);
        mPath.quadTo(assistPoint.x, assistPoint.y, endPoint.x, endPoint.y);
        // 画路径
        canvas.drawPath(mPath, mPaint);
        // 画辅助点
        canvas.drawPoint(assistPoint.x, assistPoint.y, mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                assistPoint.x = (int) event.getX();
                assistPoint.y = (int) event.getY();
                invalidate();
                break;
        }
        return true;
    }
}
