package com.jalen.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.jalen.canvas.utils.PaintUtil;

/**
 * @author Jalen
 * @date 2016/12/15 21:32
 * @describe
 */
public class CircleView extends View {
    private Context context;
    private Paint paint;

    public CircleView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        paint = PaintUtil.getPaint(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircleView(canvas);
        drawCircleView1(canvas);
    }

    private void drawCircleView1(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(360, 640, 100, paint);
    }

    private void drawCircleView(Canvas canvas) {
        canvas.drawCircle(360, 640, 50, paint);
    }
}
