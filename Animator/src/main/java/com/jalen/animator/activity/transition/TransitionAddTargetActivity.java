package com.jalen.animator.activity.transition;

import android.os.Build;
import android.os.Bundle;
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
 * @date 2017/6/19. 15:16
 * @editor
 * @date
 * @describe
 */
public class TransitionAddTargetActivity extends BaseActivity {

    private FrameLayout flRootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_add_target);
        flRootView = (FrameLayout) findViewById(R.id.activity_transition_add_target_fl_rootview);
    }

    public void onClickTransitionAddTargetStartAnimator(View v) {
        int version = Build.VERSION.SDK_INT;
        if (version >= 19) {
            Scene scene2 = Scene.getSceneForLayout(flRootView, R.layout.scene2, v.getContext());
            ChangeBounds changeBounds = new ChangeBounds();
            changeBounds.addTarget(R.id.image1);//指定动画的执行者  只让image1执行
            TransitionManager.go(scene2, changeBounds);
        } else {
            Toast.makeText(TransitionAddTargetActivity.this, "Api版本低于19", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickTransitionBaseUsePauseAnimator(View view) {
        Toast.makeText(TransitionAddTargetActivity.this, "无Api", Toast.LENGTH_SHORT).show();
    }

    public void onClickTransitionBaseUseCancelAnimator(View view) {
        Toast.makeText(TransitionAddTargetActivity.this, "无Api", Toast.LENGTH_SHORT).show();
    }
}
