package com.jalen.gcdemo;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

/**
 * @author Dragon
 * @date 2017/5/27. 9:17
 * @editor
 * @date
 * @describe
 */
public class MyApplication extends Application {

    private String text;

    @Override
    public void onCreate() {
        super.onCreate();
        Thread thread = Thread.currentThread();

        Log.i("dragon", "MyApplication Thread id =  " + thread.getId() + " Thread name = " + thread.getName());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.i("dragon", "MyApplication onLowMemory");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.i("dragon", "MyApplication onTerminate");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.i("dragon", "MyApplication onTrimMemory");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("dragon", "MyApplication onConfigurationChanged");
    }
}
