package com.jalen.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.jalen.canvas.utils.PaintUtil;

/**
 * @author Jalen
 * @date 2016/12/15 22:01
 * @describe  椭圆
 */
public class OvalView extends View {
    private Context context;
    private Paint paint;

    public OvalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public OvalView(Context context) {
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
        drawOval(canvas);
        drawRect(canvas);
    }

    private void drawRect(Canvas canvas) {
        RectF rect = new RectF(100, 200, 300, 400);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLUE);
        canvas.drawRect(rect, paint);
    }

    private void drawOval(Canvas canvas) {
        RectF rect = new RectF(100, 200, 300, 400);
        canvas.drawOval(rect, paint);
        //canvas.drawOval(100f, 100f, 200f, 200f, paint);
    }
}
