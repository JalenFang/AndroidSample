package com.jalen.canvasdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jalen.canvasdemo.R;
import com.jalen.canvasdemo.constants.ViewNameConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Jalen
 * @date 2016/12/6 21:46
 * @describe
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.activity_main_recyclerView)
    RecyclerView recyclerView;
    private List<String> viewNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    // [+] Init
    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//线性布局管理器
        viewNameList = initData();
        recyclerView.setAdapter(new Adapter());
    }

    private List<String> initData() {
        List<String> viewNameList = new ArrayList<>();
        viewNameList.add(ViewNameConstants.RECT_VIEW);
        viewNameList.add(ViewNameConstants.ROUND_RECT_VIEW);
        viewNameList.add(ViewNameConstants.TEXT_VIEW);
        viewNameList.add(ViewNameConstants.LINE_VIEW);
        viewNameList.add(ViewNameConstants.PATH_VIEW);
        viewNameList.add(ViewNameConstants.BEZIER_VIEW);
        viewNameList.add(ViewNameConstants.CIRCLE_VIEW);
        viewNameList.add(ViewNameConstants.Arc_View);
        viewNameList.add(ViewNameConstants.BITMAP_VIEW);
        viewNameList.add(ViewNameConstants.OVAL_VIEW);
        viewNameList.add(ViewNameConstants.POINT_VIEW);
        viewNameList.add(ViewNameConstants.BEZIER_DRAW_VIEW);
        return viewNameList;
    }
    // [-] Init

    //[+] Adapter
    private class Adapter extends RecyclerView.Adapter<CustomViewHolder> {

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_view, parent, false);
            final CustomViewHolder holdView = new CustomViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowViewActivity.startActivity(MainActivity.this, (String) holdView.itemView.getTag());
                }
            });
            return holdView;
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            if (viewNameList != null && !viewNameList.isEmpty()) {
                holder.tvViewName.setText(viewNameList.get(position));
                holder.itemView.setTag(viewNameList.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return viewNameList == null ? 0 : viewNameList.size();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_view_tv_view_name)
        TextView tvViewName;

        View itemView;

        public CustomViewHolder(View itemView) {
            super(itemView);
            CustomViewHolder.this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
    //[-] Adapter
}
