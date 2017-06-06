package com.jalen.canvasdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

    public CircleRingView(Context context) {
        super(context);
        init();
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
        paint.setStrokeWidth(30);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
    }

    private void drawCircleRing(Canvas canvas) {
        //canvas.drawCircle(100, 100, 50, paint);
        int center = getWidth() / 2;
        int innerCircle = dip2px(getContext(), 83); //设置内圆半径
        int ringWidth = dip2px(getContext(), 5); //设置圆环宽度

        //绘制内圆
        this.paint.setARGB(155, 167, 190, 206);
        this.paint.setStrokeWidth(2);
        canvas.drawCircle(center, center, innerCircle, this.paint);

        //绘制圆环
        this.paint.setARGB(255, 212, 225, 233);
        this.paint.setStrokeWidth(ringWidth);
        canvas.drawCircle(center, center, innerCircle + 1 + ringWidth / 2, this.paint);

        //绘制外圆
        this.paint.setARGB(155, 167, 190, 206);
        this.paint.setStrokeWidth(2);
        canvas.drawCircle(center, center, innerCircle + ringWidth, this.paint);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
