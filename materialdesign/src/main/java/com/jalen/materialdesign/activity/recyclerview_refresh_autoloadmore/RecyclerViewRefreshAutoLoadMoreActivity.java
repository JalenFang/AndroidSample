package com.jalen.materialdesign.activity.recyclerview_refresh_autoloadmore;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jalen.materialdesign.R;
import com.jalen.materialdesign.activity.recyclerview_refresh_autoloadmore.interfaces.OnItemClickListeners;
import com.jalen.materialdesign.activity.recyclerview_refresh_autoloadmore.interfaces.OnItemLongClickListeners;
import com.jalen.materialdesign.activity.recyclerview_refresh_autoloadmore.interfaces.OnLoadMoreListener;
import com.jalen.materialdesign.activity.recyclerview_refresh_autoloadmore.viewholder.RecyclerViewCommonViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dragon
 * @date 2017/6/14. 11:41
 * @editor
 * @date
 * @describe
 */
public class RecyclerViewRefreshAutoLoadMoreActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerViewAdapter recyclerViewAdapter;
    private boolean isFailed = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_refresh_autoloadmore);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_recyclerview_refresh_autoloadmore_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_recyclerview_refresh_autoloadmore_swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.common_style_blue));

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.activity_recyclerview_refresh_autoloadmore_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);

        loadData();

        recyclerViewAdapter.setOnItemClickListener(new OnItemClickListeners<String>() {

            @Override
            public void onItemClick(RecyclerViewCommonViewHolder viewHolder, String data, int viewType, int position) {
                String text = "viewType = " + viewType + " data = " + data + " position = " + position;
                Log.i("dragon", text);
                Toast.makeText(RecyclerViewRefreshAutoLoadMoreActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerViewAdapter.setOnItemLongClickListener(new OnItemLongClickListeners<String>() {
            @Override
            public void onItemLongClick(RecyclerViewCommonViewHolder viewHolder, String data, int viewType, final int position) {
                new AlertDialog.Builder(RecyclerViewRefreshAutoLoadMoreActivity.this)
                        .setItems(R.array.array_item, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                switch (which) {
                                    case 0:// 更新
                                        recyclerViewAdapter.updateItem(position, "更新的数据");
                                        break;
                                    case 1://删除
                                        recyclerViewAdapter.removeItem(position);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recyclerview_refresh_auto_load, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        recyclerViewAdapter.setAddHeader(false);
        recyclerViewAdapter.setAddFooter(false);
        recyclerViewAdapter.setOpenLoadMore(false);
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_recyclerview_refresh_auto_load_add_header:
                addHeader();
                break;
            case R.id.menu_recyclerview_refresh_auto_load_add_footer:
                addFooter();
                break;
            case R.id.menu_recyclerview_refresh_auto_load_refresh_add_header_footer:
                addHeaderAndFooter();
                break;
            case R.id.menu_recyclerview_refresh_auto_load_refresh_auto_load:
                autoLoad();
                break;
            case R.id.menu_recyclerview_refresh_auto_load_refresh_add_header_auot_load:
                autoLoadAndHeader();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //只添加头部
    private void addHeader() {
        recyclerViewAdapter.getData().clear();
        recyclerViewAdapter.setAddHeader(true);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(i + "");
        }
        recyclerViewAdapter.setData(data);
    }

    //只添加尾部
    private void addFooter() {
        recyclerViewAdapter.getData().clear();
        recyclerViewAdapter.setAddFooter(true);
        recyclerViewAdapter.setFooterView(R.layout.item_footer);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(i + "");
        }
        recyclerViewAdapter.setData(data);
    }

    //添加头部和尾部
    private void addHeaderAndFooter() {
        recyclerViewAdapter.getData().clear();
        recyclerViewAdapter.setAddHeader(true);
        recyclerViewAdapter.setAddFooter(true);
        recyclerViewAdapter.setFooterView(R.layout.item_footer);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(i + "");
        }
        recyclerViewAdapter.setData(data);
    }

    //滑动到底部自动加载数据
    private void autoLoad() {
        recyclerViewAdapter.getData().clear();
        recyclerViewAdapter.setOpenLoadMore(true);
        recyclerViewAdapter.setLoadingView(R.layout.load_loading);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(i + "");
        }
        recyclerViewAdapter.setData(data);

        recyclerViewAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                loadMore();
            }
        });
    }

    //滑动到底部自动加载数据和添加头部
    private void autoLoadAndHeader() {
        recyclerViewAdapter.getData().clear();
        recyclerViewAdapter.setAddHeader(true);
        recyclerViewAdapter.setOpenLoadMore(true);
        recyclerViewAdapter.setLoadingView(R.layout.load_loading);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(i + "");
        }
        recyclerViewAdapter.setData(data);

        recyclerViewAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                loadMore();
            }
        });
    }

    //自动加载更多
    private void loadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (recyclerViewAdapter.getItemCount() > 30 && isFailed) {
                    isFailed = false;
                    //加载失败
                    recyclerViewAdapter.setLoadFailedView(R.layout.load_failed);
                } else {
                    final List<String> data = new ArrayList<>();
                    for (int i = 0; i < 20; i++) {
                        data.add("我是加载更多" + i);
                    }
                    recyclerViewAdapter.setData(data);
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (recyclerViewAdapter.getItemCount() > 50) {
                            //加载完成
                            recyclerViewAdapter.setLoadEndView(R.layout.load_end);//显示加载完成的view
                            //recyclerViewAdapter.removeFooterView();//不显示加载完成
                        }
                    }
                }, 100);


            }
        }, 2000);
    }

    /**
     * 延时加载数据，模拟从网络获取数据
     */
    private void loadData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> data = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    data.add(i + "");
                }
                recyclerViewAdapter.setData(data);
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }


}
