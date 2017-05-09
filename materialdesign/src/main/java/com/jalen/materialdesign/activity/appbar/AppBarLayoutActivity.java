package com.jalen.materialdesign.activity.appbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jalen.materialdesign.R;
import com.jalen.materialdesign.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dragon
 * @date 2017/5/9. 11:26
 * @editor
 * @date
 * @describe
 */
public class AppBarLayoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<Map<String, Object>> data = new ArrayList<>();
    boolean isLoading;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_appbar_layout_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setData();
        recyclerView = (RecyclerView) findViewById(R.id.activity_appbar_layout_recyclerView);
        initRecyclerView();
    }

    private void setData() {
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<>();
            data.add(map);
        }
    }

    private void initRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(AppBarLayoutActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(AppBarLayoutActivity.this, data);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
                    if (!isLoading) {
                        isLoading = true;
                        setData();
                        adapter.notifyDataSetChanged();
                        adapter.notifyItemRemoved(adapter.getItemCount());
                        isLoading = false;
                    }
                }
            }
        });
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
