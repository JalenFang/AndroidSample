package com.jalen.viewdemo.activity.viewpager;


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
 * @date 2017/4/10. 14:45
 * @editor
 * @date
 * @describe
 */
public class ViewPagerActivity extends BaseAtivity {

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_view_pager;
    }

    @Override
    public void initUI(@Nullable Bundle savedInstanceState) {

    }

    @OnClick(R.id.activity_view_pager_btn_direction_view_pager)
    public void clickDirectionViewPager() {
        DirectionViewPagerActivity.startActivity(getActivity());
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ViewPagerActivity.class));
    }

}
