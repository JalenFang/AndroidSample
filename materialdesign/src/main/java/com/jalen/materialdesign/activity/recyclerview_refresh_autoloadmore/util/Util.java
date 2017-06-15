package com.jalen.materialdesign.activity.recyclerview_refresh_autoloadmore.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @author dragon
 * @date 2017/6/14 15:04
 * @editor
 * @date
 * @describe
 */
public class Util {
    /**
     * StaggeredGridLayoutManager时，查找position最大的列
     *
     * @param lastVisiblePositions
     * @return
     */
    public static int findMax(int[] lastVisiblePositions) {
        int max = lastVisiblePositions[0];
        for (int value : lastVisiblePositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static View inflate(Context context, int layoutId) {
        if (layoutId <= 0) {
            return null;
        }
        return LayoutInflater.from(context).inflate(layoutId, null);
    }
}
