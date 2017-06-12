package com.jalen.materialdesign;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jalen.materialdesign.activity.appbar.AppBarLayoutActivity;
import com.jalen.materialdesign.activity.dialog.BottomSheetDialogActivity;
import com.jalen.materialdesign.activity.drawer.DrawerLayoutAnimationActivity;
import com.jalen.materialdesign.activity.drawer.DrawerLayoutSimpleActivity;
import com.jalen.materialdesign.activity.floatingactionbutton.FloatingActionButtonActivity;
import com.jalen.materialdesign.activity.palette.PaletteActivity;
import com.jalen.materialdesign.activity.recyclerview.GridActivity;
import com.jalen.materialdesign.activity.recyclerview.LinearHorizantalActivity;
import com.jalen.materialdesign.activity.recyclerview.LinearVerticalActivity;
import com.jalen.materialdesign.activity.recyclerview.StaggeredActivity;
import com.jalen.materialdesign.activity.refresh.SwipeRefreshLayoutActivity;
import com.jalen.materialdesign.activity.searchview.SeachViewActivity;
import com.jalen.materialdesign.activity.searchview.SearchViewToolbarActivity;
import com.jalen.materialdesign.activity.snackbar.SnackbarActivity;
import com.jalen.materialdesign.activity.tab.TabLayoutBottomActivity;
import com.jalen.materialdesign.activity.tab.TabLayoutTopActivity;
import com.jalen.materialdesign.activity.textinput.TextInputLayoutActivity;
import com.jalen.materialdesign.activity.toolbar.ToolbarActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickToolbarBasicUse(View v) {
        startActivity(ToolbarActivity.class);
    }

    public void onClickSnackbar(View v) {
        startActivity(SnackbarActivity.class);
    }

    public void onClickFloatingActionButton(View v) {
        startActivity(FloatingActionButtonActivity.class);
    }

    public void onClickAppBarLayout(View v) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.select_scroll_flag))
                .setItems(R.array.array_scroll_flag, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(getApplicationContext(), AppBarLayoutActivity.class);
                        switch (which) {
                            case 0:
                                intent.putExtra(AppBarLayoutActivity.SCROLL_FLAG, "scroll");
                                break;
                            case 1:
                                intent.putExtra(AppBarLayoutActivity.SCROLL_FLAG, "enterAlways");
                                break;
                            case 2:
                                intent.putExtra(AppBarLayoutActivity.SCROLL_FLAG, "enterAlwaysCollapsed");
                                break;
                            case 3:
                                intent.putExtra(AppBarLayoutActivity.SCROLL_FLAG, "snap");
                                break;
                            case 4:
                                intent.putExtra(AppBarLayoutActivity.SCROLL_FLAG, "exitUntilCollapsed");
                                break;
                            case 5:
                                intent.putExtra(AppBarLayoutActivity.SCROLL_FLAG, "scroll_enterAlways");
                                break;
                            case 6:
                                intent.putExtra(AppBarLayoutActivity.SCROLL_FLAG, "scroll_enterAlways_enterAlwaysCollapsed");
                                break;
                            case 7:
                                intent.putExtra(AppBarLayoutActivity.SCROLL_FLAG, "scroll_exitUntilCollapsed");
                                break;
                            case 8:
                                intent.putExtra(AppBarLayoutActivity.SCROLL_FLAG, "scroll_snap");
                                break;
                            default:
                                break;

                        }
                        startActivity(intent);
                    }
                })
                .show();

    }

    public void onClickBottomSheetDialog(View v) {
        startActivity(BottomSheetDialogActivity.class);
    }

    public void onClickTabLayoutTop(View v) {
        startActivity(TabLayoutTopActivity.class);
    }

    public void onClickTabLayoutBottom(View v) {
        startActivity(TabLayoutBottomActivity.class);
    }

    public void onClickRecyclerViewLinearVertical(View v) {
        startActivity(LinearVerticalActivity.class);
    }

    public void onClickRecyclerViewLinearHorizantal(View v) {
        startActivity(LinearHorizantalActivity.class);
    }

    public void onClickRecyclerViewStaggered(View v) {
        startActivity(StaggeredActivity.class);
    }

    public void onClickRecyclerViewGrid(View v) {
        startActivity(GridActivity.class);
    }

    public void onClickRecyclerViewAnimation(View v) {

    }

    public void onClickSearchView(View v) {
        startActivity(SeachViewActivity.class);
    }


    public void onClickSearchViewToolbar(View v) {
        startActivity(SearchViewToolbarActivity.class);
    }

    public void onClickTextInputLayout(View v) {
        startActivity(TextInputLayoutActivity.class);
    }

    public void onClickDrawerLayoutSimple(View v) {
        startActivity(DrawerLayoutSimpleActivity.class);
    }

    public void onClickDrawerLayoutAnimation(View v) {
        startActivity(DrawerLayoutAnimationActivity.class);
    }

    public void onClickPalette(View v) {
        startActivity(PaletteActivity.class);
    }

    public void onClickSwipeRefreshLayout(View v) {
        startActivity(SwipeRefreshLayoutActivity.class);
    }

    private void startActivity(Class<?> c) {
        startActivity(new Intent(this, c));
    }

}

