package com.jalen.gcdemo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

//garbage collection  java垃圾回收处理  让应用重启
public class MainActivity extends BaseActivity {

    @Override
    protected void setUpViewAndData() {
        Button button = new Button(this);
        button.setText("求点击");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FirstActivity.class));
            }
        });

        setContentView(button);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int action = intent.getIntExtra(AppStatusConstant.KEY_HOME_ACTION, AppStatusConstant.ACTION_BACK_TO_HOME);
        switch (action) {
            case AppStatusConstant.ACTION_RESTART_APP:
                restartApp();
                break;
            default:
                break;
        }
    }

    @Override
    protected void restartApp() {
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }

}
