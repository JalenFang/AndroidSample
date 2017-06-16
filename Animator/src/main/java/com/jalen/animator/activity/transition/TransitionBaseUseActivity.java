package com.jalen.animator.activity.transition;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jalen.animator.BaseActivity;
import com.jalen.animator.R;

/**
 * @author Dragon
 * @date 2017/6/16. 14:51
 * @editor
 * @date
 * @describe
 */
public class TransitionBaseUseActivity extends BaseActivity {

    private FrameLayout flRootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_base_use);
        flRootView = (FrameLayout) findViewById(R.id.activity_transition_base_use_fl_rootview);
    }

    public void onClickTransitionBaseUseStartAnimator(View v) {
        int version = Build.VERSION.SDK_INT;
        if (version >= 19) {
            Scene scene2 = Scene.getSceneForLayout(flRootView, R.layout.scene2, v.getContext());
            TransitionManager.go(scene2, new ChangeBounds());
        } else {
            Toast.makeText(TransitionBaseUseActivity.this, "Api版本低于19", Toast.LENGTH_SHORT).show();
        }
    }
}
