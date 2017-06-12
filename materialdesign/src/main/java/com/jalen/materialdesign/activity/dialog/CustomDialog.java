package com.jalen.materialdesign.activity.dialog;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.ViewGroup;

/**
 * @author Dragon
 * @date 2017/6/12. 14:31
 * @editor
 * @date
 * @describe 解决BottomSheetDialog弹出之后状态栏为黑色的
 */
public class CustomDialog extends BottomSheetDialog {
    private int screenHeight;

    public CustomDialog(@NonNull Context context, int screenHeight) {
        super(context);
        this.screenHeight = screenHeight;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int statusBarHeight = getStatusBarHeight(getContext());
        int dialogHeight = screenHeight - statusBarHeight;
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, dialogHeight == 0 ? ViewGroup.LayoutParams.MATCH_PARENT : dialogHeight);
    }


    private static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
}
