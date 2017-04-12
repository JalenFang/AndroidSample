package com.jalen.canvasdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.jalen.canvasdemo.utils.PaintUtil;

/**
 * @author Jalen
 * @date 2016/12/14 21:04
 * @describe
 */
public class LineView extends View {

    private Paint paint;
    private Context context;

    public LineView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public LineView(Context context, AttributeSet attrs) {
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
        drawLine(canvas);
        drawLine1(canvas);
        drawLine2(canvas);
    }

    private void drawLine(Canvas canvas) {
        canvas.drawLine(0, 0, 100, 100, paint);
    }

    private void drawLine1(Canvas canvas) {
        canvas.drawLine(100, 100, 200, 0, paint);
    }

    private void drawLine2(Canvas canvas) {
        canvas.drawLine(0, 0, 200, 0, paint);
    }

}
