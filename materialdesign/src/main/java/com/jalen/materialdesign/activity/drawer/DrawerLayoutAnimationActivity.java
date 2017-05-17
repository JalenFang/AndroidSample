package com.jalen.materialdesign.activity.drawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.jalen.materialdesign.R;

/**
 * @author Dragon
 * @date 2017/5/17. 15:13
 * @editor
 * @date
 * @describe
 */
public class DrawerLayoutAnimationActivity extends AppCompatActivity {

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout_animation);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.activity_drawer_layout_animation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_drawer_layout_animation_toolbar);


        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(mActionBarDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }
}
