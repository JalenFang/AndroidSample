package com.jalen.canvasdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.jalen.canvasdemo.utils.PaintUtil;

/**
 * @author Jalen
 * @date 2016/12/14 21:29
 * @describe
 */
public class PathView extends View {
    private Context context;
    private Paint paint;

    public PathView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        paint = PaintUtil.getPaint(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //drawPath(canvas);
        //drawPath1(canvas);
        drawLinePathClose(canvas);
        drawLinePathNotClose(canvas);
        drawRectPath(canvas);
        drawRoundRectPath(canvas);
        drawCirclePath(canvas);
        //还可以画椭圆 弧线
    }

    private void drawPath(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, 50);
        path.quadTo(30, 220, 320, 450);
        canvas.drawPath(path, paint);
    }

    private void drawPath1(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        // paint.setStrokeWidth(10.0f);//线的宽度
        // paint.setStyle(Paint.Style.STROKE);//空心效果
        paint.setStyle(Paint.Style.FILL);//实心效果

        Path path = new Path();
        path.moveTo(0, 500);
        path.quadTo(30, 220, 320, 450);
        canvas.drawPath(path, paint);
    }

    //Path闭合
    private void drawLinePathClose(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);

        Path path = new Path();
        path.moveTo(50, 50);//设置起始点
        path.lineTo(50, 100);//第一条直线
        path.lineTo(100, 100);
        path.close();

        canvas.drawPath(path, paint);
    }

    //Path不闭合
    private void drawLinePathNotClose(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);

        Path path = new Path();
        path.moveTo(50, 150);//设置起始点
        path.lineTo(50, 200);//第一条直线
        path.lineTo(100, 200);

        canvas.drawPath(path, paint);
    }


    private void drawRectPath(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);

        Path CCWpath = new Path();
        RectF rectF = new RectF(50, 250, 150, 350);
        CCWpath.addRect(rectF, Path.Direction.CCW);//逆时针方向生成
        canvas.drawPath(CCWpath, paint);

        Path CWpath = new Path();
        RectF rectF1 = new RectF(200, 250, 300, 350);
        CWpath.addRect(rectF1, Path.Direction.CW);//顺时针方向生成
        canvas.drawPath(CWpath, paint);

        paint.setColor(Color.GRAY);
        paint.setTextSize(35);
        String text = "风萧萧兮易水寒";//在Path上面画字 这个地方用为了区别出 逆时针还是顺时针
        canvas.drawTextOnPath(text, CCWpath, 0, 0, paint);
        canvas.drawTextOnPath(text, CWpath, 0, 0, paint);
    }

    private void drawRoundRectPath(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setColor(Color.RED);

        Path path = new Path();
        RectF rectF = new RectF(50, 400, 150, 500);
        path.addRoundRect(rectF, 9, 9, Path.Direction.CW);//圆角的椭圆的横轴半径和纵轴半径
        canvas.drawPath(path, paint);

        Path path1 = new Path();
        RectF rectF1 = new RectF(200, 400, 300, 500);
        float radii[] = {5, 10, 5, 10, 10, 5, 10, 5};//每个角所使用的椭圆的横轴半径和纵轴半径
        path1.addRoundRect(rectF1, radii, Path.Direction.CW);
        canvas.drawPath(path1, paint);
    }

    private void drawCirclePath(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);

        Path path = new Path();
        path.addCircle(100, 600, 50, Path.Direction.CW);
        canvas.drawPath(path, paint);
    }

}
