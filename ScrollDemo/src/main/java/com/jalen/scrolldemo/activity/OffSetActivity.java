package com.jalen.scrolldemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.jalen.scrolldemo.R;

/**
 * @author Dragon
 * @date 2017/4/21. 11:43
 * @editor
 * @date
 * @describe
 */
public class OffSetActivity extends AppCompatActivity {

    private ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_off_set);

        actionBar = getSupportActionBar();
        actionBar.setTitle("layout");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, OffSetActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
