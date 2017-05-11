package com.jalen.materialdesign.activity.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jalen.materialdesign.R;
import com.jalen.materialdesign.adapter.RecyclerViewAdapter2;

/**
 * @author Dragon
 * @date 2017/5/11. 16:19
 * @editor
 * @date
 * @describe
 */
public class BottomSheetDialogActivity extends AppCompatActivity {

    private BottomSheetDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_dialog);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_bottom_sheet_dialog_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_sheet_dialog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.bottom_sheet_dialong_show:
                showBottomSheetDialog();
                break;
            case R.id.bottom_sheet_dialong_gone:
                goneBottomSheetDialog();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showBottomSheetDialog() {
        dialog = new BottomSheetDialog(this);
        View contentView = View.inflate(this, R.layout.bottom_sheets_layout, null);
        RecyclerView itemView = (RecyclerView) contentView.findViewById(R.id.bottom_sheet_layout_recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        itemView.setLayoutManager(layoutManager);
        RecyclerViewAdapter2 adapter = new RecyclerViewAdapter2();
        itemView.setAdapter(adapter);
        dialog.setContentView(contentView);
        dialog.show();
    }

    private void goneBottomSheetDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
