package com.jalen.gcdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ImageView;

/**
 * @author Dragon
 * @date 2017/5/27. 10:45
 * @editor
 * @date
 * @describe
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AppStatusManager.getInstance().setAppStatus(AppStatusConstant.STATUS_NORMAL);//进入应用必须设置
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpViewAndData() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.splash);
        setContentView(imageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 1000);
    }
}
