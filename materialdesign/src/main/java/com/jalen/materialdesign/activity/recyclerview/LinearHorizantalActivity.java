package com.jalen.materialdesign.activity.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.jalen.materialdesign.IconsHelper;
import com.jalen.materialdesign.R;

/**
 * @author Dragon
 * @date 2017/5/24. 17:20
 * @editor
 * @date
 * @describe
 */
public class LinearHorizantalActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_horizantal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_linear_horizantal_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.activity_linear_horizantal_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(
                DrawableAdapter.created(this, IconsHelper.ICONS, DrawableAdapter.ORANGE));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
