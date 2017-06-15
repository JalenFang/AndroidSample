package com.jalen.materialdesign.activity.recyclerview_refresh_autoloadmore.interfaces;

/**
 * @author dragon
 * @date 2017/6/14 15:04
 * @editor
 * @date
 * @describe
 */
import com.jalen.materialdesign.activity.recyclerview_refresh_autoloadmore.viewholder.RecyclerViewCommonViewHolder;

public interface OnItemClickListeners<T> {
    void onItemClick(RecyclerViewCommonViewHolder viewHolder, T data, int viewType,int position);
}
