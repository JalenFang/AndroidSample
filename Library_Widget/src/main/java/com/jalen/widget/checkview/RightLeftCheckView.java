package com.jalen.widget.checkview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jalen.library_widget.R;

/**
 * @author Dragon
 * @date 2017/4/20. 14:45
 * @editor
 * @date
 * @describe
 */
public class RightLeftCheckView extends LinearLayout {

    private OnCheckListener listener;

    public RadioGroup radioGroup;

    public RadioButton rbAllCompany;

    public RadioButton rbDirectFollower;

    public RightLeftCheckView(Context context) {
        super(context);
        init();
    }

    public RightLeftCheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RightLeftCheckView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_score_rank_check, this);

        radioGroup = (RadioGroup) findViewById(R.id.view_score_rank_radio_group);
        rbAllCompany = (RadioButton) findViewById(R.id.view_score_rank_rb_all_company);
        rbDirectFollower = (RadioButton) findViewById(R.id.view_score_rank_rb_direct_follower);

        setCheckListener();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        radioGroup.check(R.id.view_score_rank_rb_all_company);
    }

    private void setCheckListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.view_score_rank_rb_all_company) {
                    if (listener != null) {
                        listener.onCheckAllCompany();
                    }
                } else {
                    if (listener != null) {
                        listener.onCheckDirectFollower();
                    }
                }
            }
        });
    }

    public interface OnCheckListener {
        void onCheckAllCompany();

        void onCheckDirectFollower();
    }

    public void setOnCheckListener(OnCheckListener listener) {
        this.listener = listener;
    }

    public void setScoreType(int scoreType) {
        if (scoreType == 1) {
            rbAllCompany.setBackgroundResource(R.drawable.selector_cash_score);
            rbDirectFollower.setBackgroundResource(R.drawable.selector_cash_score);
        } else {
            rbAllCompany.setBackgroundResource(R.drawable.selector_honor_score);
            rbDirectFollower.setBackgroundResource(R.drawable.selector_honor_score);
        }

        radioGroup.check(R.id.view_score_rank_rb_all_company);
    }

    public void setLeftRadioButtonText(String text) {
        rbAllCompany.setText(text);
    }

    public String getLeftRadioButtonText() {
        return rbAllCompany.getText().toString();
    }
}
