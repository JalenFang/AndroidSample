package com.jalen.canvas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jalen.canvas.R;
import com.jalen.canvas.utils.ScreenUtils;

/**
 * @author Jalen
 * @date 2016/12/8 21:14
 * @describe 画文字
 */
public class TextView extends View {

    private Paint paint;

    public TextView(Context context) {
        super(context);
        init();
    }

    public TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        paint.setColor(Color.RED);//画笔颜色
        // paint.setStrokeWidth(10.0f);//线的宽度
        // paint.setStyle(Paint.Style.STROKE);//空心效果
        paint.setStyle(Paint.Style.FILL);//实心效果
        paint.setTextSize(ScreenUtils.sp2px(getContext(), getContext().getResources().getDimension(R.dimen.text_12sp)));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTextView(canvas);
        drawTextView1(canvas);
        drawTextView2(canvas);
        drawTextView3(canvas);
    }

    private void drawTextView(Canvas canvas) {
        canvas.drawText("Jalen", 200, 100, paint);
    }

    private void drawTextView1(Canvas canvas) {
        canvas.drawText("Jalen", 0, 100, paint);
    }

    private void drawTextView2(Canvas canvas) {
        canvas.drawText("你 好", 0, 300, paint);
        Log.i("dragon", "paint stroke width 2 = " + paint.getStrokeWidth());
    }

    private void drawTextView3(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        paint.setUnderlineText(true);
        Log.i("dragon", "paint stroke width 3 = " + paint.getStrokeWidth());

        paint.setTextSize(ScreenUtils.sp2px(getContext(), getContext().getResources().getDimension(R.dimen.text_12sp)));

        canvas.drawText("测试", 20, 500, paint);
    }
}
