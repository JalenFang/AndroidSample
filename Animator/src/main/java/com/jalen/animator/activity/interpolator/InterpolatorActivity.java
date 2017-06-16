package com.jalen.animator.activity.interpolator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

import com.jalen.animator.R;

/**
 * @author Dragon
 * @date 2017/4/26. 11:05
 * @editor
 * @date
 * @describe
 */
public class InterpolatorActivity extends AppCompatActivity {

    private Button btnExecuteAnimation;
    private ValueAnimator valueAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator);
        btnExecuteAnimation = (Button) findViewById(R.id.activity_interpolator_btn_execute_animation);
        setClickListener();
    }

    private void setClickListener() {
        findViewById(R.id.activity_interpolator_btn_start_animation).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                valueAnimator = ValueAnimator.ofInt(0, 600);
                valueAnimator.setDuration(2000);
                //DecelerateInterpolator开始变化快，后期变化慢
                //LinearInterpolator  匀速返回区间点的值
                //BounceInterpolator 动画结束的时候弹起
                valueAnimator.setInterpolator(new BounceInterpolator());
                valueAnimator.start();
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) valueAnimator.getAnimatedValue();
                        btnExecuteAnimation.layout(btnExecuteAnimation.getLeft(), value,
                                btnExecuteAnimation.getRight(), btnExecuteAnimation.getHeight() + value);
                    }
                });
            }
        });

        findViewById(R.id.activity_interpolator_btn_cancle_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valueAnimator != null) {
                    valueAnimator.cancel();
                }
            }
        });
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, InterpolatorActivity.class));
    }
}
