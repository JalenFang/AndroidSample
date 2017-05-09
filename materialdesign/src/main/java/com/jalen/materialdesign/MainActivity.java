package com.jalen.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jalen.materialdesign.activity.floatingactionbutton.FloatingActionButtonActivity;
import com.jalen.materialdesign.activity.snackbar.SnackbarActivity;
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

    public void onClickSnackbar(View v) {
        startActivity(SnackbarActivity.class);
    }

    public void onClickFloatingActionButton(View v) {
        startActivity(FloatingActionButtonActivity.class);
    }

    public void onClickAppBarLayoutScroll(View v) {

    }

    public void onClickAppBarLayoutEnterAlways(View v) {

    }

    public void onClickAppBarLayoutEnterAlwaysCollapsed(View v) {

    }

    public void onClickAppBarLayoutSnap(View v) {

    }

    public void onClickAppBarLayoutExitUntilCollapsed(View v) {

    }

    public void onClickTabLayoutBottom(View v) {

    }

    private void startActivity(Class<?> c) {
        startActivity(new Intent(this, c));
    }
}

