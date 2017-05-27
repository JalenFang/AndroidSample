package com.jalen.gcdemo;

import android.util.Log;
import android.widget.TextView;

/**
 * @author Dragon
 * @date 2017/5/27. 9:15
 * @editor
 * @date
 * @describe
 */
public class FirstActivity extends BaseActivity {

    private static final String TEXT = "TEXT";

    @Override
    protected void setUpViewAndData() {
        TextView textView = new TextView(this);
        setContentView(textView);
        textView.setText(TEXT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("dragon", "FirstActivity onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("dragon", "FirstActivity onDestroy");
    }
}
