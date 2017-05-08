package com.jalen.materialdesign.activity.snackbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.jalen.materialdesign.R;

/**
 * @author Dragon
 * @date 2017/5/8. 16:26
 * @editor
 * @date
 * @describe
 */
public class SnackbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);
    }

    public void onClickSnackbar(View v) {
        Snackbar.make(this.findViewById(android.R.id.content), "Snackbar", Snackbar.LENGTH_SHORT)
                .setActionTextColor(ContextCompat.getColor(SnackbarActivity.this, R.color.common_style_red))
                .setAction("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SnackbarActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
}
