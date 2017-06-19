package com.jalen.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.jalen.canvas.utils.PaintUtil;

/**
 * @author Jalen
 * @date 2016/12/8 21:03
 * @describe 圆角矩形
 */
public class RoundRectView extends View {

    private Paint paint;
    private Context context;

    public RoundRectView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public RoundRectView(Context context, AttributeSet attrs) {
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
        drawRoundRectView(canvas);
    }

    private void drawRoundRectView(Canvas canvas) {
        RectF rectF = new RectF(100, 100, 200, 200);
        canvas.drawRoundRect(rectF, 30, 30, paint);
        //canvas.drawRoundRect(0, 0, 50, 50, 5, 5, paint);
    }
}
