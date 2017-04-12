package com.jalen.annotationsample;

import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MainActivity extends AppCompatActivity {

    private TextView tvText;

    @Size(max = 3, min = 1)
    private int a;

    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;

    private int currentNum;

    @IntDef({ZERO, ONE, TWO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Num {
    }

    public void setCurrentNum(@Num int currentNum) {
        this.currentNum = currentNum;
    }

    @Num
    public int getCurrentNum() {
        return currentNum;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText = (TextView) findViewById(R.id.tv_desc);

        setCurrentNum(ZERO);

        testNullable(true);

        //testNonNull(null);
        testNonNull("a");

        setText(R.string.text);

        setIntValue(1);
        setFloatValue(0);
    }

    //Nullable注解说明这个方法的返回值可能为空
    @Nullable
    private String testNullable(boolean b) {
        String a = null;
        if (b) {
            a = "a";
        }
        return a;
    }

    //NonNull注解说明传递的参数不能为空 如果传值为空的话 Android Studio会有一个警告提示
    private void testNonNull(@NonNull String a) {
    }

    //StringRes注解说明传递的参数是一个String类型的资源id
    private void setText(@StringRes int id) {
        tvText.setText(id);
    }
    // @DrawableRes, @DimenRes, @ColorRes


    //注解IntRange可以限制传递的参数的类型是int类型的 最小值是0 最大值是2
    public void setIntValue(@IntRange(from = 0, to = 2) int i) {
    }

    //注解FloatRange可以限制传递的参数的类型是float类型的 最小值是0 最大值是2
    public void setFloatValue(@FloatRange(from = 0, to = 2) float i) {
    }

}
