package com.jalen.viewdemo.activity.custom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jalen.viewdemo.BaseAtivity;
import com.jalen.viewdemo.R;

import butterknife.OnClick;

/**
 * @author Dragon
 * @date 2017/4/18. 11:18
 * @editor
 * @date
 * @describe
 */
public class CustomActivity extends BaseAtivity {
    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_custom;
    }

    @Override
    public void initUI(@Nullable Bundle savedInstanceState) {

    }


    @OnClick(R.id.activity_custom_btn_flow_layout)
    public void clickFlowLayout() {
        FolwLayoutActivity.startActivity(getActivity());
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, CustomActivity.class));
    }
}
