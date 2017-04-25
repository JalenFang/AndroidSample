package com.jalen.scrolldemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jalen.scrolldemo.R;

/**
 * @author Dragon
 * @date 2017/4/25. 11:40
 * @editor
 * @date
 * @describe
 */
public class ViewDragHelperActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drag_helper);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ViewDragHelperActivity.class));
    }
}
