package com.jalen.attrsstyle;

import android.content.Context;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author Dragon
 * @date 2017/6/9. 9:59
 * @editor
 * @date
 * @describe 使用自定义属性和自定义样式的view   http://www.jianshu.com/p/61b79e7f88fc
 */


/**
 * 优先级如下：
 * 1.在Xml里面使用单个属性比在my_style里面优先级高
 * 2.my_style的优先级要高于custom_style
 * 3.custom_style的优先级要高于default_style
 */
public class AttrsStyleView extends LinearLayout {
    public AttrsStyleView(Context context) {
        this(context, null);
    }

    public AttrsStyleView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.custom_style);
        //this(context, attrs, 0);
    }

    public AttrsStyleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.custom_attrs, defStyleAttr, R.style.default_style);
        int color1 = tintTypedArray.getColor(R.styleable.custom_attrs_color1, 0xffff0000);
        int color2 = tintTypedArray.getColor(R.styleable.custom_attrs_color2, 0xffff0000);
        int color3 = tintTypedArray.getColor(R.styleable.custom_attrs_color3, 0xffff0000);
        tintTypedArray.recycle();

        TextView textView1 = new TextView(context);
        textView1.setText("color1 " + Integer.toHexString(color1));
        addView(textView1);

        TextView textView2 = new TextView(context);
        textView2.setText("color2 " + Integer.toHexString(color2));
        addView(textView2);

        TextView textView3 = new TextView(context);
        textView3.setText("color3 " + Integer.toHexString(color3));
        addView(textView3);
    }
}
