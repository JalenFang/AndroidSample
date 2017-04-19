package com.jalen.animatordemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jalen.animatordemo.activity.Demo1Activity;

public class MainActivity extends AppCompatActivity {

    private Button btnDemo1Activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDemo1Activity = (Button) findViewById(R.id.activity_main_btn_Demo1Activity);

        setClickListener();
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
    }
}
