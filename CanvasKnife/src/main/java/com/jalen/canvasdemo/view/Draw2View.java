package com.jalen.canvasdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author Dragon
 * @date 2017/4/14. 11:10
 * @editor
 * @date
 * @describe 画板(使用二阶贝塞尔曲线)
 */
public class Draw2View extends View {

    private Paint paint;
    private Path path;
    private float mPreX, mPreY;

    public Draw2View(Context context) {
        super(context);
        init();
    }

    public Draw2View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Draw2View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.GREEN);
        path = new Path();
    }

    //public void quadTo(float x1, float y1, float x2, float y2)
    //参数中(x1,y1)是控制点坐标，(x2,y2)是终点坐标

    //将两个线段的中间做为二阶贝尔赛曲线的起始点和终点，把上一个手指的位置做为控制点
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(), event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = (mPreX + event.getX()) / 2;
                float endY = (mPreY + event.getY()) / 2;
                path.quadTo(mPreX, mPreY, endX, endY);
                mPreX = event.getX();
                mPreY = event.getY();
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }
}
