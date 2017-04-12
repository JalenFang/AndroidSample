package com.jalen.canvasdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jalen.canvasdemo.R;
import com.jalen.canvasdemo.constants.ViewNameConstants;
import com.jalen.canvasdemo.view.RectView;
import com.jalen.canvasdemo.view.RoundRectView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Jalen
 * @date 2016/12/1. 17:40
 * @describe
 */
public class ShowViewActivity extends AppCompatActivity {

    @Bind(R.id.activity_show_view_ll_container)
    LinearLayout llContainer;

    @Bind(R.id.activity_show_view_tv_title)
    TextView tvTitle;

    public static String VIEW_NAME = "view_name";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_view);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        String viewName = getIntent().getStringExtra(VIEW_NAME);
        tvTitle.setText(viewName);
        showView(viewName);
    }

    //根据viewName显示绘图
    private void showView(String viewName) {
        if (viewName.equals(ViewNameConstants.RECT_VIEW)) {
            llContainer.addView(new RectView(ShowViewActivity.this));
        } else if (viewName.equals(ViewNameConstants.ROUND_RECT_VIEW)) {
            llContainer.addView(new RoundRectView(ShowViewActivity.this));
        } else if (viewName.equals(ViewNameConstants.TEXT_VIEW)) {
            llContainer.addView(new com.jalen.canvasdemo.view.TextView(ShowViewActivity.this));
        } else if (viewName.equals(ViewNameConstants.LINE_VIEW)) {
            llContainer.addView(new com.jalen.canvasdemo.view.LineView(ShowViewActivity.this));
        } else if (viewName.equals(ViewNameConstants.PATH_VIEW)) {
            llContainer.addView(new com.jalen.canvasdemo.view.PathView(ShowViewActivity.this));
        } else if (viewName.equals(ViewNameConstants.BEZIER_VIEW)) {
            llContainer.addView(new com.jalen.canvasdemo.view.BezierView(ShowViewActivity.this));
        } else if (viewName.equals(ViewNameConstants.CIRCLE_VIEW)) {
            llContainer.addView(new com.jalen.canvasdemo.view.CircleView(ShowViewActivity.this));
        }else if (viewName.equals(ViewNameConstants.Arc_View)) {
            llContainer.addView(new com.jalen.canvasdemo.view.ArcView(ShowViewActivity.this));
        }else if (viewName.equals(ViewNameConstants.BITMAP_VIEW)) {
            llContainer.addView(new com.jalen.canvasdemo.view.CircleView(ShowViewActivity.this));
        }else if (viewName.equals(ViewNameConstants.OVAL_VIEW)) {
            llContainer.addView(new com.jalen.canvasdemo.view.OvalView(ShowViewActivity.this));
        }
    }

    //启动ShowViewActivity
    public static void startActivity(Context context, String viewName) {
        Intent intent = new Intent(context, ShowViewActivity.class);
        intent.putExtra(VIEW_NAME, viewName);
        context.startActivity(intent);
    }
}
