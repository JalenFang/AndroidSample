package com.jalen.viewdemo.activity.linearlayout;

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
 * @date 2017/4/17. 11:22
 * @editor
 * @date
 * @describe
 */
public class LinearLayoutActivity extends BaseAtivity {
    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_linear_layout;
    }

    @Override
    public void initUI(@Nullable Bundle savedInstanceState) {

    }

    @OnClick(R.id.activity_view_pager_btn_my_linear_layout)
    public void clickMyLinearLayout() {
        MyLinearLayoutActivity.startActivity(getActivity());
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LinearLayoutActivity.class));
    }
}