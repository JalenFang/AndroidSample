package com.jalen.materialdesign.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jalen.materialdesign.R;

/**
 * @author Dragon
 * @date 2017/5/12 10:15
 * @editor
 * @date
 * @describe
 */
public class FragmentFactory extends Fragment {
    private static final String EXTRA_CONTENT = "content";

    public static Fragment newInstant(String content) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_CONTENT, content);
        FragmentFactory fragmentFactory = new FragmentFactory();
        fragmentFactory.setArguments(bundle);
        return fragmentFactory;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_tab_content, null);
        ((TextView) contentView.findViewById(R.id.tv_content)).setText(getArguments().getString(EXTRA_CONTENT));
        return contentView;
    }


}
