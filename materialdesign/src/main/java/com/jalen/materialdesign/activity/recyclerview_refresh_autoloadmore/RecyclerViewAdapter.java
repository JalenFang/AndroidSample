package com.jalen.materialdesign.activity.recyclerview_refresh_autoloadmore;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jalen.materialdesign.R;
import com.jalen.materialdesign.activity.recyclerview_refresh_autoloadmore.base.RecyclerViewMultiTypeAdapter;
import com.jalen.materialdesign.activity.recyclerview_refresh_autoloadmore.viewholder.RecyclerViewCommonViewHolder;

/**
 * @author dragon
 * @date 2017/6/14 15:05
 * @editor
 * @date
 * @describe
 */
//可以自己的需求自定义RecyclerViewAdapter
public class RecyclerViewAdapter extends RecyclerViewMultiTypeAdapter<String> {

    public RecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewCommonViewHolder holder, final String data, int viewType, int position) {
        if (viewType == TYPE_HEADER_VIEW) {
            //找到view的一种方法
            LinearLayout heardView = (LinearLayout) holder.getConvertView();
            TextView headText2 = (TextView) heardView.findViewById(R.id.item_head_test2);

            //找到view的更好的方法
            TextView headText1 = holder.getView(R.id.item_head_test1);

            //设置点击事件的一种方法
            headText1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "头部item1", Toast.LENGTH_SHORT).show();
                }
            });

            //设置点击事件更好的方法
            holder.setOnClickListener(R.id.item_head_test2, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "头部item2", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (viewType == TYPE_COMMON_VIEW) {
            holder.setText(R.id.text1, data);
            holder.setOnClickListener(R.id.text1, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
                }
            });
        } else {//尾部（可以对只添加尾部的做处理）

        }
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        if (viewType == TYPE_HEADER_VIEW) {
            return R.layout.item_head;
        }
        return R.layout.item_layout1;
    }

    @Override
    public int getViewType(int position, String data) {
        if (data == null) {//头部
            return TYPE_HEADER_VIEW;
        } else {
            return TYPE_COMMON_VIEW;//正常的item
        }
    }

}
