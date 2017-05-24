package com.jalen.materialdesign.activity.refresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.jalen.materialdesign.R;
import com.jalen.materialdesign.adapter.RecyclerViewAdapter3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dragon
 * @date 2017/5/18. 14:30
 * @editor
 * @date
 * @describe
 */
public class SwipeRefreshLayoutActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private Handler handler = new Handler();
    List<String> dataList = new ArrayList();
    private RecyclerViewAdapter3 recyclerViewAdapter;

    private static final int DELAYED_TIME = 2000;

    private int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);

        toolbar = (Toolbar) findViewById(R.id.activity_swipe_refresh_layout_toolbar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_swipe_refresh_layout_swipeRefreshLayout);
        recyclerView = (RecyclerView) findViewById(R.id.activity_swipe_refresh_layout_recyclerView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        swipeRefreshLayout.setColorSchemeResources(R.color.common_style_blue);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);//刷新图标显示
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setDataList();
            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter3(this, dataList);
        recyclerView.setAdapter(recyclerViewAdapter);

        setDataList();
    }

    private void setDataList() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List dataList = new ArrayList();
                int j = 0;
                for (int i = count; i < count + 20; i++) {
                    dataList.add("" + i);
                    j = i;
                }
                count += j;
                recyclerViewAdapter.setData(dataList);
                swipeRefreshLayout.setRefreshing(false);//刷新图标关闭
            }
        }, DELAYED_TIME);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
