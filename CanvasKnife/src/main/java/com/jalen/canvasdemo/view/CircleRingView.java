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

    /**
     * 圆环的宽度
     */
    private int roundWidth = 130;

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
        int raduis = center - roundWidth / 2;//圆的半径
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWidth);
        canvas.drawCircle(center, center, raduis, paint);

    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
