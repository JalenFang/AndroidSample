package com.jalen.materialdesign.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jalen.materialdesign.R;

/**
 * @author Dragon
 * @date 2017/5/2. 15:09
 * @editor
 * @date
 * @describe
 */
public class TestActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        initToorBar();
    }

    private void initToorBar() {
        toolbar = (Toolbar) findViewById(R.id.activity_test_toolBar);
        toolbar.setTitle("测试");
        toolbar.setNavigationIcon(R.mipmap.white_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, TestActivity.class));
    }
}
