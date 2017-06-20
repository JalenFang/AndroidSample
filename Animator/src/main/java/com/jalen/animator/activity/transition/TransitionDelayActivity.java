package com.jalen.animator.activity.transition;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jalen.animator.BaseActivity;
import com.jalen.animator.R;

/**
 * @author Dragon
 * @date 2017/6/19. 15:35
 * @editor
 * @date
 * @describe
 */
public class TransitionDelayActivity extends BaseActivity {

    private LinearLayout llRootView;
    private View viewCirculOne;
    private View viewCirculTwo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_delay);

        llRootView = (LinearLayout) findViewById(R.id.activity_transition_delay_ll_container);
        viewCirculOne = findViewById(R.id.activity_transition_delay_view_circul_one);
        viewCirculTwo = findViewById(R.id.activity_transition_delay_view_circul_two);

        Toast.makeText(this, "2秒钟后开始执行延时动画", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                executeDelayedTransition();
            }
        }, 2000);
    }

    private void executeDelayedTransition() {
        int version = Build.VERSION.SDK_INT;
        if (version >= 19) {
            ChangeBounds changeBounds = new ChangeBounds();
            changeBounds.setDuration(1000);
            TransitionManager.beginDelayedTransition(llRootView, changeBounds);

            ViewGroup.LayoutParams layoutParams = viewCirculOne.getLayoutParams();
            layoutParams.height = 400;
            layoutParams.width = 400;
            viewCirculOne.setLayoutParams(layoutParams);

            ViewGroup.LayoutParams layoutParams2 = viewCirculTwo.getLayoutParams();
            layoutParams2.height = 100;
            layoutParams2.width = 100;
            viewCirculTwo.setLayoutParams(layoutParams2);
        }
    }
}
