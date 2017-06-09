package com.jalen.materialdesign.activity.palette;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.jalen.materialdesign.R;

/**
 * @author Dragon
 * @date 2017/6/9. 15:42
 * @editor
 * @date
 * @describe 提取图片的颜色 http://www.jianshu.com/p/9fcf316031ba
 */
public class PaletteActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        toolbar = (Toolbar) findViewById(R.id.activity_palette_toolbar);
        initPalette();
    }

    // palette.getVibrantSwatch()对某些图片会出现提取不到的情况,vibrant使用前最好做一下非空判断

    private void initPalette() {
        // 用来提取颜色的Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.banner);
        // Palette的部分
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                //获取到充满活力的这种色调
                Palette.Swatch vibrant = palette.getVibrantSwatch();

                //获取充满活力的黑
                // Palette.Swatch vibrant = palette.getDarkVibrantSwatch();

                //获取充满活力的亮
                // Palette.Swatch vibrant = palette.getLightVibrantSwatch();

                //获取柔和的色调
                // Palette.Swatch vibrant = palette.getMutedSwatch();

                //获取柔和的黑
                // Palette.Swatch vibrant = palette.getDarkMutedSwatch();

                //获取柔和的亮
                // Palette.Swatch vibrant = palette.getLightMutedSwatch();

                if (vibrant != null) {

                  /*  getPopulation(): 像素的数量
                    getRgb(): RGB颜色
                    getHsl(): HSL颜色
                    getBodyTextColor(): 用于内容文本的颜色
                    getTitleTextColor(): 标题文本的颜色
                    */

                    toolbar.setBackgroundColor(vibrant.getRgb());
                    if (android.os.Build.VERSION.SDK_INT >= 21) {
                        Window window = getWindow();
                        window.setStatusBarColor(colorBurn(vibrant.getRgb()));
                        window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
                    }
                }
            }
        });
    }

    /**
     * 颜色加深处理
     *
     * @param RGBValues RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
     *                  Android中我们一般使用它的16进制，
     *                  例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
     *                  red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
     *                  所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
     * @return
     */

    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }
}
