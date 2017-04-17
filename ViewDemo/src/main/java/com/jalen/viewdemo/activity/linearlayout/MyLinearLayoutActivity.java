package com.jalen.viewdemo.activity.linearlayout;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jalen.viewdemo.BaseAtivity;
import com.jalen.viewdemo.R;

/**
 * @author Dragon
 * @date 2017/4/17. 11:16
 * @editor
 * @date
 * @describe
 */
public class MyLinearLayoutActivity extends BaseAtivity {
    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_my_linear_layout;
    }

    @Override
    public void initUI(@Nullable Bundle savedInstanceState) {

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MyLinearLayoutActivity.class));
    }
}
