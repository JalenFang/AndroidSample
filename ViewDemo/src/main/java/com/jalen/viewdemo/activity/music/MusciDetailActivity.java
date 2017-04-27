package com.jalen.viewdemo.activity.music;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jalen.viewdemo.R;

/**
 * @author Dragon
 * @date 2017/4/27. 16:59
 * @editor
 * @date
 * @describe
 */
public class MusciDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_detail);
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MusciDetailActivity.class));
    }

}
