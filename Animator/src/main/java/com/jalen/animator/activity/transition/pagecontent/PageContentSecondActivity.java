package com.jalen.animator.activity.transition.pagecontent;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Fade;
import android.view.Window;

import com.jalen.animator.BaseActivity;
import com.jalen.animator.R;
import com.jalen.animator.constant.Constant;

/**
 * @author Dragon
 * @date 2017/6/20. 9:40
 * @editor
 * @date
 * @describe
 */
public class PageContentSecondActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTransiton();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_content_second);
    }

    @TargetApi(21)
    private void setTransiton() {
        if (isApiUp21()) {
            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

            getWindow().setAllowEnterTransitionOverlap(true);
            getWindow().setAllowReturnTransitionOverlap(true);

            /*Slide slide = new Slide(Gravity.TOP);
            slide.setDuration(Constant.DURATION_TIME);
            getWindow().setEnterTransition(slide);

            Slide slide1 = new Slide();
            slide1.setDuration(Constant.DURATION_TIME);
            slide1.setSlideEdge(Gravity.TOP);
            getWindow().setReturnTransition(slide1);*/


            /*Slide slideExit = new Slide(Gravity.LEFT);
            slideExit.setDuration(Constant.DURATION_TIME);
            getWindow().setExitTransition(slideExit);


            Slide slideReenter = new Slide(Gravity.RIGHT);
            slideReenter.setDuration(Constant.DURATION_TIME);
            getWindow().setReenterTransition(slideReenter);*/

            //淡入淡出
            Fade fadeEnter = new Fade();
            fadeEnter.setMode(Fade.IN);
            fadeEnter.setDuration(Constant.DURATION_TIME);
            getWindow().setEnterTransition(fadeEnter);

            Fade fadeReturn = new Fade();
            fadeReturn.setMode(Fade.OUT);
            fadeReturn.setDuration(Constant.DURATION_TIME);
            getWindow().setReturnTransition(fadeReturn);
        }
    }

    public boolean isApiUp21() {
        return Build.VERSION.SDK_INT >= 21 ? true : false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isApiUp21()) {
            finishAfterTransition();
        } else {
            finish();
        }
    }
}
