package com.jalen.scrolldemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
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
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int i = dp2px(getBaseContext(), 30);
        Log.i("dragon", "" + dp2px(getBaseContext(), 30));

        setContentView(R.layout.activity_scroll_to);

        frameLayout = (FrameLayout) findViewById(R.id.activity_scroll_to_frameLayout);

        findViewById(R.id.activity_scroll_to_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FrameLayout) textView.getParent()).scrollTo(i, i);
                //((FrameLayout) textView.getParent()).scrollBy(i, i);

                int left = frameLayout.getLeft();
                int top = frameLayout.getTop();
                int right = frameLayout.getRight();
                int bottom = frameLayout.getBottom();

                Log.w("dragon", "left " + left + " ; " + "top " + top + " ; " + "right " + right + " ; " + "bottom " + bottom);
            }
        });

        textView = (TextView) findViewById(R.id.activity_scroll_to_textView);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("scrollTo");
        actionBar.setDisplayHomeAsUpEnabled(true);

        setLayoutListener();
    }

    private void setLayoutListener() {
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int left = frameLayout.getLeft();
                int top = frameLayout.getTop();
                int right = frameLayout.getRight();
                int bottom = frameLayout.getBottom();

                Log.i("dragon", "left " + left + " ; " + "top " + top + " ; " + "right " + right + " ; " + "bottom " + bottom);
            }
        });
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

    public static int dp2px(Context context, double dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
