package com.jalen.materialdesign.activity.searchview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jalen.materialdesign.R;

import java.lang.reflect.Method;

/**
 * @author Dragon
 * @date 2017/6/7. 9:19
 * @editor
 * @date
 * @describe 参考文章 http://www.jianshu.com/p/7c1e78e91506
 */
public class SearchViewToolbarActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SearchView searchView;
    private SearchView.SearchAutoComplete searchAutoComplete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view_toolbar);
        toolbar = (Toolbar) findViewById(R.id.activity_search_view_toolbar_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_view_toolbar, menu);
        MenuItem searchViewItem = menu.findItem(R.id.menu_search_view_toolbar_searchView);
        searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);

        initSearchView();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (searchAutoComplete.isShown()) {
                    try {
                        //如果搜索框中有文字，则会先清空文字，但网易云音乐是在点击返回键时直接关闭搜索框
                        searchAutoComplete.setText("");
                        Method method = searchView.getClass().getDeclaredMethod("onCloseClicked");
                        method.setAccessible(true);
                        method.invoke(searchView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    finish();
                }
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // 让菜单同时显示图标和文字
    //todo dragon 2017/6/7 10:50 这个反射的方法来源需要了解
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    private void initSearchView() {
        /*------------------ SearchView有三种默认展开搜索框的设置方式，区别如下： ------------------*/
        //设置搜索框直接展开显示。左侧有放大镜(在搜索框中) 右侧有叉叉 可以关闭搜索框
        //searchView.setIconified(false);
        //设置搜索框直接展开显示。左侧有放大镜(在搜索框外) 右侧无叉叉 有输入内容后有叉叉 不能关闭搜索框
        //searchView.setIconifiedByDefault(false);
        //设置搜索框直接展开显示。左侧无放大镜(在搜索框中) 右侧无叉叉 有输入内容后有叉叉 不能关闭搜索框
        // searchView.onActionViewExpanded();


        //设置最大宽度
        //searchView.setMaxWidth(500);
        //设置是否显示搜索框展开时的提交按钮
        //searchView.setSubmitButtonEnabled(true);
        //设置输入框提示语
        searchView.setQueryHint("搜索提示语");

        //设置输入框提示文字样式
        searchAutoComplete.setHintTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.darker_gray));
        searchAutoComplete.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.background_light));
        searchAutoComplete.setTextSize(14);

        //设置搜索框有字时显示叉叉，无字时隐藏叉叉
        searchView.onActionViewExpanded();
        searchView.setIconified(true);

        //修改搜索框控件间的间隔
        LinearLayout search_edit_frame = (LinearLayout) searchView.findViewById(R.id.search_edit_frame);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) search_edit_frame.getLayoutParams();
        params.leftMargin = 0;
        search_edit_frame.setLayoutParams(params);

        //设置搜素框输入内容的间隔
        searchAutoComplete.setPadding(0, 0, 0, 0);

        //设置触发查询的最少字符数（默认2个字符才会触发查询）
        searchAutoComplete.setThreshold(1);

        //搜索框展开时后面叉叉按钮的点击事件
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText(getApplicationContext(), "Close", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //搜索图标按钮(打开搜索框的按钮)的点击事件
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Open", Toast.LENGTH_SHORT).show();
            }
        });

        //搜索框文字变化监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.e("dragon", "TextSubmit : " + s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.e("dragon", "TextChange --> " + s);
                return false;
            }
        });
    }
}
