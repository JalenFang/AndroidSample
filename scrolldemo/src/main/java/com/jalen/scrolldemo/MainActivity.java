package com.jalen.scrolldemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jalen.scrolldemo.activity.LayoutActivity;
import com.jalen.scrolldemo.activity.LayoutParamsActivity;
import com.jalen.scrolldemo.activity.OffSetActivity;
import com.jalen.scrolldemo.activity.ScrollByActivity;
import com.jalen.scrolldemo.activity.ScrollToActivity;
import com.jalen.scrolldemo.activity.ScrollerActivity;

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

        findViewById(R.id.activity_main_btn_To_by).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.scroll_type)
                        .setItems(R.array.scroll_style, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                if (which == 0) {//ScrollTo
                                    ScrollToActivity.startActivity(MainActivity.this);
                                } else if (which == 1) {//ScrollBy
                                    ScrollByActivity.startActivity(MainActivity.this);
                                }
                            }
                        });

                builder.show();
            }
        });

        findViewById(R.id.activity_main_btn_Scroller).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScrollerActivity.startActivity(MainActivity.this);
            }
        });
    }
}
