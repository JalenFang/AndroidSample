package com.jalen.materialdesign.activity.searchview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.jalen.materialdesign.R;

/**
 * @author Dragon
 * @date 2017/6/7. 9:19
 * @editor
 * @date
 * @describe
 */
public class SearchViewToolbarActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view_toolbar);
        toolbar = (Toolbar) findViewById(R.id.activity_search_view_toolbar_toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_view_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
