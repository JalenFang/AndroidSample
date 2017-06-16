package com.jalen.animatordemo.activity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jalen.animatordemo.R;

/**
 * @author Dragon
 * @date 2017/4/19. 14:50
 * @editor
 * @date
 * @describe
 */
public class Demo1Activity extends AppCompatActivity {

    private Button btnStartViewAnimation;
    private TextView tvView;
    private Button btnStartPropertyAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);

        btnStartViewAnimation = (Button) findViewById(R.id.activity_demo1_btn_start_view_animation);
        btnStartPropertyAnimation = (Button) findViewById(R.id.activity_demo1_btn_start_property_animation);
        tvView = (TextView) findViewById(R.id.activity_demo1_tv_view);

        setClickListener();
    }

    private void setClickListener() {
        btnStartViewAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TranslateAnimation animation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 800,
                        Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 1000);
                animation.setFillAfter(true);
                animation.setDuration(2000);
                tvView.startAnimation(animation);
            }
        });


        btnStartPropertyAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 400);
                //设置动画时长，单位是毫秒
                valueAnimator.setDuration(2000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        //获取ValueAnimator在运动时，当前运动点的值
                        int curValue = (int) animation.getAnimatedValue();
                        tvView.layout(curValue, curValue, curValue + tvView.getWidth(), curValue + tvView.getHeight());
                    }
                });

                //开始动画
                valueAnimator.start();
                /**
                 * 设置循环次数,设置为INFINITE表示无限循环
                 */
                valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
                /**
                 * 设置循环模式
                 * value取值有RESTART，REVERSE，
                 */
                valueAnimator.setRepeatMode(ValueAnimator.REVERSE);

                /**
                 * 取消动画
                 */
                //void cancel()
            }
        });

        tvView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Demo1Activity.this, "我被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, Demo1Activity.class));
    }
}
