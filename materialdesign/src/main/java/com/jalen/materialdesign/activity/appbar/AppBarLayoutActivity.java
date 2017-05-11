package com.jalen.materialdesign.activity.appbar;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
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
    private AppBarLayout appBarLayout;

    private List<Map<String, Object>> data = new ArrayList<>();
    boolean isLoading;
    private RecyclerViewAdapter adapter;
    private Toolbar toolbar;

    public static final String SCROLL_FLAG = "scroll_flag";
    private String scrollFlag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_layout);

        scrollFlag = getIntent().getStringExtra(SCROLL_FLAG);

        appBarLayout = (AppBarLayout) findViewById(R.id.activity_appbar_layout_appBarLayout);

        toolbar = (Toolbar) findViewById(R.id.activity_appbar_layout_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setScrollFlag();

        setData();
        recyclerView = (RecyclerView) findViewById(R.id.activity_appbar_layout_recyclerView);
        initRecyclerView();
    }

    private void setScrollFlag() {
        AppBarLayout.LayoutParams param = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        if (scrollFlag.equals("scroll")) {
            param.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
        } else if (scrollFlag.equals("enterAlways")) {
            param.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
        } else if (scrollFlag.equals("enterAlwaysCollapsed")) {
            param.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED);
        } else if (scrollFlag.equals("snap")) {
            param.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
        } else if (scrollFlag.equals("exitUntilCollapsed")) {
            param.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        }
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
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                setData();
                                adapter.notifyDataSetChanged();
                                adapter.notifyItemRemoved(adapter.getItemCount());
                                isLoading = false;
                            }
                        }, 1000);
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.app_bar_layout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.app_bar_layout_menu_scorll:
                AppBarLayout.LayoutParams param1 = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
                param1.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
                break;
            case R.id.app_bar_layout_menu_enterAlways:
                AppBarLayout.LayoutParams param2 = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
                param2.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                break;
            case R.id.app_bar_layout_menu_enterAlwaysCollapsed:
                AppBarLayout.LayoutParams param3 = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
                param3.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED);
                break;
            case R.id.app_bar_layout_menu_snap:
                AppBarLayout.LayoutParams param4 = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
                param4.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
                break;
            case R.id.app_bar_layout_menu_exitUntilCollapsed:
                AppBarLayout.LayoutParams param5 = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
                param5.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
