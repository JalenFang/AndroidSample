package com.jalen.gcdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Dragon
 * @date 2017/5/27. 10:35
 * @editor
 * @date
 * @describe
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        switch (AppStatusManager.getInstance().getAppStatus()) {
            case AppStatusConstant.STATUS_FORCE_KILLED:
                restartApp();
                break;
            case AppStatusConstant.STATUS_NORMAL:
                setUpViewAndData();
                break;
        }
    }


    protected abstract void setUpViewAndData();

    protected void restartApp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(AppStatusConstant.KEY_HOME_ACTION, AppStatusConstant.ACTION_RESTART_APP);
        startActivity(intent);
    }
}
