package com.jalen.widget.checkview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jalen.library_widget.R;

/**
 * @author Dragon
 * @date 2017/4/20. 15:14
 * @editor
 * @date
 * @describe
 */
public class RightLeftCheckView2 extends LinearLayout {

    private ImageView ivBg;
    private TextView tvAllCompany;
    private TextView tvDirectFollower;

    private int value = dp2px(getContext(), 165);
    private int lastValue;

    public RightLeftCheckView2(Context context) {
        super(context);
        init();
    }

    public RightLeftCheckView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RightLeftCheckView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_score_rank_check_2, this, true);

        ivBg = (ImageView) findViewById(R.id.view_score_rank_check_2_iv_bg);
        tvAllCompany = (TextView) findViewById(R.id.view_score_rank_check_2_tv_all_company);
        tvDirectFollower = (TextView) findViewById(R.id.view_score_rank_check_2_tv_direct_follower);
        tvAllCompany.setEnabled(false);
        tvDirectFollower.setEnabled(true);
        setClickListener();
    }


    private void setClickListener() {
        tvAllCompany.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAllCompany.setEnabled(false);
                tvDirectFollower.setEnabled(false);

                tvAllCompany.setTextColor(ContextCompat.getColor(getContext(), R.color.common_style_white));
                tvDirectFollower.setTextColor(ContextCompat.getColor(getContext(), R.color.common_style_black));
                /*TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -1,
                        Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
                animation.setFillAfter(true);
                animation.setDuration(1000);
                ivBg.startAnimation(animation);*/

                lastValue = 0;
                ValueAnimator valueAnimator = ValueAnimator.ofInt(0, value);
                valueAnimator.setDuration(value);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int curValue = (int) animation.getAnimatedValue();
                        ivBg.offsetLeftAndRight(lastValue - curValue);
                        lastValue = curValue;

                        if (curValue == value) {
                            tvAllCompany.setEnabled(false);
                            tvDirectFollower.setEnabled(true);
                        }
                    }
                });

                valueAnimator.start();
            }
        });

        tvDirectFollower.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                tvAllCompany.setEnabled(false);
                tvDirectFollower.setEnabled(false);

                tvAllCompany.setTextColor(ContextCompat.getColor(getContext(), R.color.common_style_black));
                tvDirectFollower.setTextColor(ContextCompat.getColor(getContext(), R.color.common_style_white));

               /* TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1,
                        Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
                animation.setFillAfter(true);
                animation.setDuration(1000);
                ivBg.startAnimation(animation);*/

                lastValue = 0;

                ValueAnimator valueAnimator = ValueAnimator.ofInt(0, value);
                valueAnimator.setDuration(value);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int curValue = (int) animation.getAnimatedValue();
                        ivBg.offsetLeftAndRight(curValue - lastValue);
                        lastValue = curValue;

                        if (curValue == value) {
                            tvAllCompany.setEnabled(true);
                            tvDirectFollower.setEnabled(false);
                        }

                    }
                });

                valueAnimator.start();
            }
        });
    }

    private int dp2px(Context context, double dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
