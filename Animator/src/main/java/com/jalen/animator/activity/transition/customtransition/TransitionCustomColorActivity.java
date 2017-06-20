package com.jalen.animator.activity.transition.customtransition;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jalen.animator.BaseActivity;
import com.jalen.animator.R;

/**
 * @author Dragon
 * @date 2017/6/19. 17:05
 * @editor
 * @date
 * @describe
 */
public class TransitionCustomColorActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_custom_color);

        final LinearLayout llRootView = (LinearLayout) findViewById(R.id.activity_transition_custom_color_ll_rootView);
        final View view = findViewById(R.id.activity_transition_custom_color_view);

        Toast.makeText(this, "1秒钟后开始执行改变颜色的动画", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int version = Build.VERSION.SDK_INT;
                if (version >= 19) {
                    TransitionManager.beginDelayedTransition(llRootView, new CustomColorTransition());
                    view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.common_style_orange));
                }
            }
        }, 1000);
    }
}
