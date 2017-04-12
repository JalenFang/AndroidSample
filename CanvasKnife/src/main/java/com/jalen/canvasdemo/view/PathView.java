package com.jalen.canvasdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.jalen.canvasdemo.utils.PaintUtil;

/**
 * @author Jalen
 * @date 2016/12/14 21:29
 * @describe
 */
public class PathView extends View {
    private Context context;
    private Paint paint;

    public PathView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public PathView(Context context, AttributeSet attrs) {
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
        drawPath(canvas);
        drawPath1(canvas);
    }

    private void drawPath(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, 50);
        path.quadTo(30, 220, 320, 450);
        canvas.drawPath(path, paint);
    }

    private void drawPath1(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        // paint.setStrokeWidth(10.0f);//线的宽度
        // paint.setStyle(Paint.Style.STROKE);//空心效果
        paint.setStyle(Paint.Style.FILL);//实心效果

        Path path = new Path();
        path.moveTo(0, 500);
        path.quadTo(30, 220, 320, 450);
        canvas.drawPath(path, paint);
    }
}
