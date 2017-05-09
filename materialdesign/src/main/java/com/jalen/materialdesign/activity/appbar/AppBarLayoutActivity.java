package com.jalen.materialdesign.activity.appbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jalen.materialdesign.R;

/**
 * @author Dragon
 * @date 2017/5/9. 11:26
 * @editor
 * @date
 * @describe
 */
public class AppBarLayoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_appbar_layout_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.activity_appbar_layout_recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_layout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.app_bar_layout_menu_scorll:
                finish();
                break;
            case R.id.app_bar_layout_menu_enterAlways:
                finish();
                break;
            case R.id.app_bar_layout_menu_enterAlwaysCollapsed:
                finish();
                break;
            case R.id.app_bar_layout_menu_snap:
                finish();
                break;
            case R.id.app_bar_layout_menu_exitUntilCollapsed:
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
