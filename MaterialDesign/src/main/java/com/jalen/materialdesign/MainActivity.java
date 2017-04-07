package com.jalen.materialdesign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Dragon
 * @date 2017/3/21. 16:11
 * @editor
 * @date
 * @describe
 */
public class MainActivity extends BaseAtivity {

    @BindView(R.id.activity_main_recyclerView)
    public RecyclerView recyclerView;

    private static final int RECYCLER_VIEW = 0;


    private String[] viewArray = new String[]{"RecyclerView"};
    private List<String> viewList;

    @Override
    public void initUI(@Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerView);
        viewList = Arrays.asList(viewArray);
        initRecyclerView();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setSmoothScrollbarEnabled(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(new Adapter());
    }

    private class Adapter extends RecyclerView.Adapter<MainActivity.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_button, null));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.btn.setText(TextUtils.isEmpty(viewList.get(position)) ? "" : viewList.get(position));
            setOnClickListener(holder, position);
        }

        @Override
        public int getItemCount() {
            return viewList == null ? 0 : viewList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @NonNull
        @BindView(R.id.item_button)
        public Button btn;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(ViewHolder.this, itemView);
        }
    }

    //点击事件
    private void setOnClickListener(ViewHolder holder, final int position) {
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("dragon", viewList.get(position));
                toActivity(position);
            }
        });
    }

    //跳转activity
    private void toActivity(int position) {
        switch (position) {
            case RECYCLER_VIEW:

                break;
            default:
                break;
        }
    }


}
