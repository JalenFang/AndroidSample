package com.jalen.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jalen.materialdesign.activity.toolbar.ToolbarActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickToolbarBasicUse(View v) {
        startActivity(ToolbarActivity.class);
    }

    public void onClickTabLayoutBottom(View v) {

    }

    private void startActivity(Class<?> c) {
        startActivity(new Intent(this, c));
    }
}

