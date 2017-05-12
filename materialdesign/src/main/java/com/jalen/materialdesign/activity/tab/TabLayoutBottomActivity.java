package com.jalen.materialdesign.activity.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.jalen.materialdesign.R;
import com.jalen.materialdesign.fragment.FragmentFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dragon
 * @date 2017/5/12. 9:53
 * @editor
 * @date
 * @describe
 */
public class TabLayoutBottomActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private static final int TAB_COUNT = 5;
    private List<String> cotentList;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_bottom);

        toolbar = (Toolbar) findViewById(R.id.activity_tab_layout_bottom_toolbar);
        viewPager = (ViewPager) findViewById(R.id.activity_tab_layout_bottom_viewPager);
        tabLayout = (TabLayout) findViewById(R.id.activity_tab_layout_bottom_tabLayout);

        initToolbar();
        initViewPager();
        initTabLayout();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initViewPager() {
        cotentList = new ArrayList<>();
        for (int i = 0; i < TAB_COUNT; i++) {
            cotentList.add("tab" + i);
        }

        fragmentList = new ArrayList<>();
        for (int i = 0; i < TAB_COUNT; i++) {
            fragmentList.add(FragmentFactory.newInstant(cotentList.get(i)));
        }

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void initTabLayout() {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);//这个很重要 tab的数量和viewpager的数量一样了
        tabLayout.setSelectedTabIndicatorHeight(0);

        for (int i = 0; i < TAB_COUNT; i++) {
            TabLayout.Tab tabAt = tabLayout.getTabAt(i);
            if (tabAt != null) {
                tabAt.setCustomView(R.layout.item_tab_layout_custom);
                TextView itemTv = (TextView) tabAt.getCustomView().findViewById(R.id.tv_menu_item);
                itemTv.setText(cotentList.get(i));
            }
        }

       // tabLayout.getTabAt(0).select();
          tabLayout.getTabAt(0).getCustomView().setSelected(true);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
        case android.R.id.home:
            finish();
            break;
        default:
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
