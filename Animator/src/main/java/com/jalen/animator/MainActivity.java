package com.jalen.animator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jalen.animator.activity.transition.TransitionBaseUseActivity;

/**
 * @author Jalen
 * @date 2017/6/16 11:18
 * @editor
 * @date
 * @describe 动画的主界面
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }


    //在Android动画中，总共有两种类型  View Animation(视图动画)  Property Animator(属性动画)
    //View Animation包括 Tween Animation(补间动画) 和 Frame Animation(逐帧动画)
    //Property Animator 包括 ValueAnimation和 ObjectAnimation

    public void onClickTransitionBaseUse(View v) {
        startActivity(TransitionBaseUseActivity.class);
    }

    private void startActivity(Class<?> c) {
        startActivity(new Intent(this, c));
    }


}
