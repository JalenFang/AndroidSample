package com.jalen.canvasdemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.jalen.canvasdemo.utils.LogUtils;

/**
 * @author Dragon
 * @date 2017/4/14. 14:45
 * @editor
 * @date
 * @describe 波纹
 */
public class WaveView extends View {

    private Path path;
    private Paint paint;
    //private int mItemWaveLength = 400;
    private int mItemWaveLength = 1000;
    private int dx;
    int originY = 0;

    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        path = new Path();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.GREEN);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startAnim();
            }
        }, 1000);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        int halfWaveLen = mItemWaveLength / 2;
        //originY = originY + dx;
        path.moveTo(-mItemWaveLength + dx, originY);
        for (int i = -mItemWaveLength; i <= getWidth() + mItemWaveLength; i += mItemWaveLength) {
            path.rQuadTo(halfWaveLen / 2, -100, halfWaveLen, 0);
            path.rQuadTo(halfWaveLen / 2, 100, halfWaveLen, 0);
        }

        path.lineTo(getWidth(), getHeight());
        path.lineTo(0, getHeight());
        path.close();

        canvas.drawPath(path, paint);
    }


    private void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(2 * 1000);
//        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatCount(1);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                LogUtils.I("dragon", "dx = " + dx);
                originY = (dx * getHeight()) / mItemWaveLength;
                invalidate();
            }
        });
        animator.start();
    }


}
