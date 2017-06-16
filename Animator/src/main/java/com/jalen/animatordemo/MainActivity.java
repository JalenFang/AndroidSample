package com.jalen.animatordemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.jalen.animatordemo.activity.Demo1Activity;
import com.jalen.animatordemo.activity.interpolator.InterpolatorActivity;

/**
 * @author Jalen
 * @date 2017/6/16 11:18
 * @editor
 * @date
 * @describe 动画的主界面
 */
public class MainActivity extends BaseActivity {

    private Button btnDemo1Activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        btnDemo1Activity = (Button) findViewById(R.id.activity_main_btn_Demo1Activity);
    }


    //在Android动画中，总共有两种类型  View Animation(视图动画)  Property Animator(属性动画)
    //View Animation包括 Tween Animation(补间动画) 和 Frame Animation(逐帧动画)
    //Property Animator 包括 ValueAnimation和 ObjectAnimation


    private void setClickListener() {
        btnDemo1Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Demo1Activity.startActivity(MainActivity.this);
            }
        });


        findViewById(R.id.activity_main_btn_Interpolator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InterpolatorActivity.startActivity(MainActivity.this);
            }
        });
    }

    public void onClickTransitionBaseUse(View v) {
        new AlertDialog.Builder(MainActivity.this)
                .setItems(R.array.transition_type, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0://基本使用

                                break;
                            case 1://AddTarget

                                break;
                            default:
                                break;
                        }
                    }
                })
                .show();
    }

    private void startActivity(Class<?> c) {
        startActivity(new Intent(this, c));
    }


}
