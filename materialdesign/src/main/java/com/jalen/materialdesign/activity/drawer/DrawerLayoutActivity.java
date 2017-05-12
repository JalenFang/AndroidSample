package com.jalen.materialdesign.activity.drawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.jalen.materialdesign.R;
import com.jalen.materialdesign.adapter.RecyclerViewAdapter2;

/**
 * @author Dragon
 * @date 2017/5/12. 17:39
 * @editor
 * @date
 * @describe
 */
public class DrawerLayoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DrawerLayout drawerLayout;

    //  mDrawer.setScrimColor(getResources().getColor(R.color.color_2095f2));
    //  mDrawer.openDrawer(Gravity.RIGHT);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_drawer_layout_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.activity_drawer_layout_recyclerView);
        initRecyclerView();

        drawerLayout = (DrawerLayout) findViewById(R.id.activity_drawer_layout_drawerLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_layout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.drawer_layout_menu_red:
                //设置滑动出来的背景色
                drawerLayout.setScrimColor(ContextCompat.getColor(getApplicationContext(),R.color.common_style_red));
                break;
            case R.id.drawer_layout_menu_blue:
                drawerLayout.setScrimColor(ContextCompat.getColor(getApplicationContext(),R.color.common_style_blue));
                break;
            case R.id.drawer_layout_menu_right:
                //打开
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.drawer_layout_menu_left:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter2 adapter = new RecyclerViewAdapter2();
        recyclerView.setAdapter(adapter);
    }

}
