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
                valueAnimator.setDuration(2000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int curValue = (int) animation.getAnimatedValue();
                        tvView.layout(curValue, curValue, curValue + tvView.getWidth(), curValue + tvView.getHeight());
                    }
                });

                valueAnimator.start();
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
