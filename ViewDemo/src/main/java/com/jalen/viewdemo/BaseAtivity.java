package com.jalen.viewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author Dragon
 * @date 2017/3/30. 16:28
 * @editor
 * @date
 * @describe
 */
public abstract class BaseAtivity extends AppCompatActivity {

    public abstract Activity getActivity();

    public abstract int getLayout();

    public abstract void initUI(@Nullable Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(getActivity());

        initUI(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
