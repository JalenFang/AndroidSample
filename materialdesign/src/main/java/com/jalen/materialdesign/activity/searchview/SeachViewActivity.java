package com.jalen.materialdesign.activity.searchview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;

import com.jalen.materialdesign.R;

/**
 * @author Dragon
 * @date 2017/6/5. 14:28
 * @editor
 * @date
 * @describe
 */
public class SeachViewActivity extends AppCompatActivity {

    private SearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_search_view_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchView = (SearchView) findViewById(R.id.activity_search_view_searchView);

        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }


    private void init() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("dragon", "query = " + query);
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.w("dragon", "newText = " + newText);
                return false;
            }
        });

       /* setIconifiedByDefault(boolean);//图标是否显示在搜索框内
        setImeOptions(int);//输入法搜索选项字段，默认是搜索
        setInputType(int);//输入类型
        setMaxWidth(int);//最大宽度
        setQueryHint(CharSequence);//提示字符串*/

        searchView.setIconifiedByDefault(true);
        searchView.setInputType(InputType.TYPE_CLASS_PHONE);
        searchView.setQueryHint("测试啊");
        searchView.setMaxWidth(500);
    }

}
