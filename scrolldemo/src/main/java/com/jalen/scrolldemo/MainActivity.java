package com.jalen.scrolldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jalen.scrolldemo.activity.LayoutActivity;
import com.jalen.scrolldemo.activity.LayoutParamsActivity;
import com.jalen.scrolldemo.activity.OffSetActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setClickListener();
    }

    private void setClickListener() {
        findViewById(R.id.activity_main_btn_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.activity_main_btn_offset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OffSetActivity.startActivity(MainActivity.this);
            }
        });

        findViewById(R.id.activity_main_btn_LayoutParams).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutParamsActivity.startActivity(MainActivity.this);
            }
        });
    }
}