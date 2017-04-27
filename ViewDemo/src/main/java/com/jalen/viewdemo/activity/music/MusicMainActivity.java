package com.jalen.viewdemo.activity.music;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jalen.viewdemo.R;

/**
 * @author Dragon
 * @date 2017/4/27. 16:52
 * @editor
 * @date
 * @describe
 */
public class MusicMainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_main);

        findViewById(R.id.activity_music_main_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusciDetailActivity.startActivity(MusicMainActivity.this);
                setVisibility(false);
            }
        });
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MusicMainActivity.class));
    }
}
