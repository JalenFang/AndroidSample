package com.jalen.animator.activity.transition.pagecontent;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Fade;
import android.view.View;
import android.view.Window;

import com.jalen.animator.BaseActivity;
import com.jalen.animator.R;
import com.jalen.animator.constant.Constant;

/**
 * @author Dragon
 * @date 2017/6/20. 9:28
 * @editor
 * @date
 * @describe 页面内容过渡动画
 */
public class PageContentFirstActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTransiton();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_content_first);
    }


    //进入页面二
    @TargetApi(21)
    public void onClickTransitionEnterSecondPage(View view) {
        startActivity(new Intent(this, PageContentSecondActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    @TargetApi(21)
    private void setTransiton() {
        if (isApiUp21()) {
            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

            getWindow().setAllowEnterTransitionOverlap(true);
            getWindow().setAllowReturnTransitionOverlap(true);

            //幻灯片
            /*Slide slide = new Slide(Gravity.TOP);
            slide.setDuration(Constant.DURATION_TIME);
            getWindow().setEnterTransition(slide);

            Slide slide1 = new Slide();
            slide1.setDuration(Constant.DURATION_TIME);
            slide1.setSlideEdge(Gravity.TOP);
            getWindow().setReturnTransition(slide1);

            Slide slideExit = new Slide(Gravity.BOTTOM);
            slideExit.setDuration(Constant.DURATION_TIME);
            getWindow().setExitTransition(slideExit);


            Slide slideReenter = new Slide(Gravity.BOTTOM);
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

           /* Fade fadeExit = new Fade(Fade.OUT);
            fadeExit.setDuration(Constant.DURATION_TIME);
            getWindow().setExitTransition(fadeExit);

            Fade fadeReenter = new Fade();
            fadeReenter.setMode(Fade.IN);
            fadeReenter.setDuration(Constant.DURATION_TIME);
            getWindow().setReenterTransition(fadeReenter);*/
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
