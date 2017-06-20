package com.jalen.animator.activity.transition.customtransition;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.graphics.drawable.ColorDrawable;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.ViewGroup;

/**
 * @author Dragon
 * @date 2017/6/19. 16:22
 * @editor
 * @date
 * @describe 自定义颜色改变的动画
 */
@TargetApi(19)
public class CustomColorTransition extends Transition {
    private static final String CUSTOM_BACKGROUND_NAME = "com.jalen.animator.activity.transition.customtransition.CustomColorTransition.custom_background_name";

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        if (transitionValues.view.getBackground() instanceof ColorDrawable) {
            captureValues(transitionValues);
        }
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        if (transitionValues.view.getBackground() instanceof ColorDrawable) {
            captureValues(transitionValues);
        }
    }

    private void captureValues(TransitionValues transitionValues) {
        transitionValues.values.put(CUSTOM_BACKGROUND_NAME, ((ColorDrawable) (transitionValues.view.getBackground())).getColor());
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, final TransitionValues endValues) {
        if (startValues == null || endValues == null) {
            return null;
        }

        Integer startBackground = (Integer) startValues.values.get(CUSTOM_BACKGROUND_NAME);
        Integer endBackground = (Integer) endValues.values.get(CUSTOM_BACKGROUND_NAME);

        if (startBackground != endBackground) {
            ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), startBackground, endBackground);
            valueAnimator.setDuration(2000);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Object animatedValue = animation.getAnimatedValue();
                    if (animatedValue != null) {
                        endValues.view.setBackgroundColor((Integer) animatedValue);
                    }
                }
            });

            return valueAnimator;
        }

        return null;
    }

    @Override
    public String[] getTransitionProperties() {
        return new String[]{CUSTOM_BACKGROUND_NAME};
    }
}
