package com.jalen.animator.activity.transition.shareelement;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jalen.animator.BaseActivity;
import com.jalen.animator.R;

/**
 * @author Dragon
 * @date 2017/6/20. 14:21
 * @editor
 * @date
 * @describe 共享元素
 */
public class ShareElementFirstActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_element_first);
    }

    @TargetApi(21)
    public void onClickShareElementFirstView(View view) {
        startActivity(new Intent(ShareElementFirstActivity.this, ShareElementSecondActivity.class), ActivityOptions.makeSceneTransitionAnimation(this, view, "share").toBundle());
    }

}
