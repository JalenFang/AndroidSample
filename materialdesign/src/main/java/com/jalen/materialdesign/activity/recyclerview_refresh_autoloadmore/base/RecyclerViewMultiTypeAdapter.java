package com.jalen.materialdesign.activity.recyclerview_refresh_autoloadmore.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.jalen.materialdesign.activity.recyclerview_refresh_autoloadmore.interfaces.OnItemClickListeners;
import com.jalen.materialdesign.activity.recyclerview_refresh_autoloadmore.interfaces.OnItemLongClickListeners;
import com.jalen.materialdesign.activity.recyclerview_refresh_autoloadmore.viewholder.RecyclerViewCommonViewHolder;

/**
 * @author dragon
 * @date 2017/6/14 15:04
 * @editor
 * @date
 * @describe
 */
public abstract class RecyclerViewMultiTypeAdapter<T> extends RecyclerViewBaseAdapter<T> {
    private OnItemClickListeners<T> itemClickListener;
    private OnItemLongClickListeners<T> itemLongClickListener;

    public RecyclerViewMultiTypeAdapter(Context context) {
        super(context);
    }

    protected abstract void convert(RecyclerViewCommonViewHolder holder, T data, int viewType, int position);

    protected abstract int getItemLayoutId(int viewType);

    @Override
    public RecyclerViewCommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (isCommonItemView(viewType)) {
            return RecyclerViewCommonViewHolder.create(context, getItemLayoutId(viewType), parent);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        final RecyclerViewCommonViewHolder viewHolder = (RecyclerViewCommonViewHolder) holder;
        T data;
        if (isCommonItemView(viewType)) {
            data = dataList.get(position);
        } else {
            data = null;
        }

        bindCommonItem(viewHolder, data, viewType, position);
    }

    private void bindCommonItem(final RecyclerViewCommonViewHolder viewHolder, final T data, final int viewType, final int position) {
        convert(viewHolder, data, viewType, position);
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(viewHolder, data, viewType, position);
                }
            }
        });

        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemLongClickListener != null) {
                    itemLongClickListener.onItemLongClick(viewHolder, data, viewType, position);
                }
                return true;
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListeners<T> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListeners<T> itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }
}
