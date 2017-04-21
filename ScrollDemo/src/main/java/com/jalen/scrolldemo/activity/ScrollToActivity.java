package com.jalen.scrolldemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.jalen.scrolldemo.R;

/**
 * @author Dragon
 * @date 2017/4/21. 14:57
 * @editor
 * @date
 * @describe
 */
public class ScrollToActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scroll_to);

        textView = (TextView) findViewById(R.id.activity_scroll_to_textView);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("scrollTo");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        textView.scrollTo(300, 300);
    }


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ScrollToActivity.class));
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
