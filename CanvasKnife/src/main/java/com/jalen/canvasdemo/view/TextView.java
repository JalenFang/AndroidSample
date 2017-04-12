package com.jalen.canvasdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.jalen.canvasdemo.utils.PaintUtil;

/**
 * @author Jalen
 * @date 2016/12/8 21:14
 * @describe 文本
 */
public class TextView extends View {

    private Paint paint;
    private Context context;

    public TextView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public TextView(Context context, AttributeSet attrs) {
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
        drawTextView(canvas);
        drawTextView1(canvas);
        drawTextView2(canvas);
    }

    private void drawTextView(Canvas canvas) {
        canvas.drawText("Jalen", 200, 100, paint);
    }

    private void drawTextView1(Canvas canvas) {
        canvas.drawText("Jalen", 0, 100, paint);
    }

    private void drawTextView2(Canvas canvas) {
        canvas.drawText("你 好", 0, 300, paint);
    }
}
