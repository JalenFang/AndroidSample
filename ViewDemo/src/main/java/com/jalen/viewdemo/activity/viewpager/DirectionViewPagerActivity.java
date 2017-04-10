package com.jalen.viewdemo.activity.viewpager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jalen.viewdemo.BaseAtivity;
import com.jalen.viewdemo.R;
import com.jalen.widget.viewpager.DirectionViewPager;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * @author Dragon
 * @date 2017/4/10. 15:06
 * @editor
 * @date
 * @describe
 */
public class DirectionViewPagerActivity extends BaseAtivity {

    @BindView(R.id.activity_direction_view_pager_DirectionViewPager)
    public DirectionViewPager directionViewPager;

    public static List<Integer> ICONS =
            Arrays.asList(R.mipmap.icon_item_1, R.mipmap.icon_item_2, R.mipmap.icon_item_3, R.mipmap.icon_item_4,
                    R.mipmap.icon_item_5, R.mipmap.icon_item_6, R.mipmap.icon_item_7, R.mipmap.icon_item_8,
                    R.mipmap.icon_item_9, R.mipmap.icon_item_10, R.mipmap.icon_item_11, R.mipmap.icon_item_12,
                    R.mipmap.icon_item_13, R.mipmap.icon_item_14);
    private ImageView[] viewArray;

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_direction_view_pager;
    }

    @Override
    public void initUI(@Nullable Bundle savedInstanceState) {
        viewArray = new ImageView[14];
        for (int i = 0; i < ICONS.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), ICONS.get(i)));
            viewArray[i] = imageView;
        }

        directionViewPager.setAdapter(new Adapter());
        directionViewPager.setPageChangeListener(new ViewPagerListener());
    }

    private class Adapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = viewArray[position];
            if (view != null) {
                container.addView(view);
            }
            return view;
        }

        @Override
        public int getCount() {
            return viewArray.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            android.view.View view = viewArray[position];
            if (view != null) {
                container.removeView(view);
            }
        }
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, DirectionViewPagerActivity.class));
    }

    private class ViewPagerListener implements DirectionViewPager.PageStateChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Log.e("dragon", "position = " + position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.i("dragon", "state = " + state);
        }

        @Override
        public void onSlideDirection(int slideState) {
            Log.w("dragon", "slideState = " + slideState);
        }
    }
}
