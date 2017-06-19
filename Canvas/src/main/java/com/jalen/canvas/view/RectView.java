package com.jalen.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.jalen.canvas.utils.PaintUtil;

/**
 * @author Jalen
 * @date 2016/12/6 21:46
 * @describe 矩形
 */
public class RectView extends View {
    private Paint paint;

    public RectView(Context context) {
        super(context);
        init();
    }

    public RectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = PaintUtil.getPaint(getContext());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRect1(canvas);
        drawRect2(canvas);
    }

    private void drawRect1(Canvas canvas) {
        Rect rect = new Rect(50, 50, 200, 200);
        canvas.drawRect(rect, paint);
    }

    private void drawRect2(Canvas canvas) {
        Rect rect = new Rect(200, 200, 500, 500);
        canvas.drawRect(rect, paint);
    }
}
