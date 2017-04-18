package com.jalen.viewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jalen.viewdemo.activity.custom.CustomActivity;
import com.jalen.viewdemo.activity.linearlayout.LinearLayoutActivity;
import com.jalen.viewdemo.activity.viewpager.ViewPagerActivity;

import butterknife.OnClick;

/**
 * @author Dragon
 * @date 2017/4/7. 10:55
 * @editor
 * @date
 * @describe
 */
public class MainActivity extends BaseAtivity {

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initUI(@Nullable Bundle savedInstanceState) {

    }

    @OnClick(R.id.activity_main_btn_view_pager)
    public void clickViewPager() {
        ViewPagerActivity.startActivity(getActivity());
    }

    @OnClick(R.id.activity_main_btn_linearlayout)
    public void clickLinearLayout() {
        LinearLayoutActivity.startActivity(getActivity());
    }

    @OnClick(R.id.activity_main_btn_custom)
    public void clickCustom() {
        CustomActivity.startActivity(getActivity());
    }
}
