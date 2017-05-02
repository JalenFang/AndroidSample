package com.jalen.materialdesign.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jalen.materialdesign.R;
import com.jalen.materialdesign.fragment.Fragment1;
import com.jalen.materialdesign.fragment.Fragment2;
import com.jalen.materialdesign.fragment.Fragment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Dragon
 * @date 2017/5/2. 15:09
 * @editor
 * @date
 * @describe
 */
public class TestActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        initToorBar();
        initView();
    }

    private void initToorBar() {
        toolbar = (Toolbar) findViewById(R.id.activity_test_toolBar);
        toolbar.setTitle("测试");
        toolbar.setNavigationIcon(R.mipmap.white_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.activity_test_tabLayout);
        viewPager = (ViewPager) findViewById(R.id.activity_test_viewPager);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), Arrays.asList(getResources().getStringArray(R.array.title)), fragmentList);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);

    }


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, TestActivity.class));
    }


    private static class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<String> titleList;
        private List<Fragment> fragmentList;

        public ViewPagerAdapter(FragmentManager fm, List<String> titleList, List<Fragment> fragmentList) {
            super(fm);
            this.titleList = titleList;
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList == null ? "" : titleList.get(position);
        }
    }
}
