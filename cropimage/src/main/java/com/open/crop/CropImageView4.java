// The idea is to do it with a resizable moving square

package com.open.crop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 底图不变，浮层缩放
 */
public class CropImageView4 extends View {

    //单点触摸的时候
    private float oldX = 0;
    private float oldY = 0;

    //状态
    private final int STATUS_TOUCH_SINGLE = 1;//单点
    private final int STATUS_TOUCH_MULTI_START = 2;//多点开始
    private final int STATUS_TOUCH_MULTI_TOUCHING = 3;//多点拖拽中

    private int status = STATUS_TOUCH_SINGLE;

    //默认的裁剪图片宽度与高度
    private final int defaultCropWidth = 300;
    private final int defaultCropHeight = 300;
    private int cropWidth = defaultCropWidth;
    private int cropHeight = defaultCropHeight;

    private final int EDGE_LT = 1;//左上
    private final int EDGE_RT = 2;//右上
    private final int EDGE_LB = 3;//左下
    private final int EDGE_RB = 4;//右下
    private final int EDGE_MOVE_IN = 5;//里面移动
    private final int EDGE_MOVE_OUT = 6;//外面移动
    private final int EDGE_NONE = 7;

    public int currentEdge = EDGE_NONE;

    protected float oriRationWH = 0;//原始宽高比率

    protected Drawable drawable;//原图
    protected FloatDrawable floatDrawable;//浮层
    protected Rect drawableSrcRect = new Rect();
    protected Rect drawableDstRect = new Rect();
    protected Rect drawableFloatRect = new Rect();//浮层选择框，就是头像选择框
    protected boolean isFrist = true;
    private boolean isTouchInSquare = true;

    protected Context context;

    public CropImageView4(Context context) {
        super(context);
        init(context);
    }

    public CropImageView4(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CropImageView4(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @SuppressLint("NewApi")
    private void init(Context context) {
        this.context = context;
        //todo dragon 2017/6/19 14:41 这个api不熟悉
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        floatDrawable = new FloatDrawable(context);//头像选择框
    }

    public void setDrawable(Drawable mDrawable, int cropWidth, int cropHeight) {
        this.drawable = mDrawable;
        this.cropWidth = cropWidth;
        this.cropHeight = cropHeight;
        this.isFrist = true;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getPointerCount() > 1) {
            if (status == STATUS_TOUCH_SINGLE) {
                status = STATUS_TOUCH_MULTI_START;
            } else if (status == STATUS_TOUCH_MULTI_START) {
                status = STATUS_TOUCH_MULTI_TOUCHING;
            }
        } else {
            if (status == STATUS_TOUCH_MULTI_START || status == STATUS_TOUCH_MULTI_TOUCHING) {
                oldX = event.getX();
                oldY = event.getY();
            }

            status = STATUS_TOUCH_SINGLE;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldX = event.getX();
                oldY = event.getY();
                currentEdge = getTouchEdge((int) oldX, (int) oldY);
                isTouchInSquare = drawableFloatRect.contains((int) event.getX(), (int) event.getY());
                break;

            case MotionEvent.ACTION_UP:
                checkBounds();
                break;

            case MotionEvent.ACTION_POINTER_1_DOWN:
                break;

            case MotionEvent.ACTION_POINTER_UP:
                currentEdge = EDGE_NONE;
                break;

            case MotionEvent.ACTION_MOVE:
                if (status == STATUS_TOUCH_MULTI_TOUCHING) {

                } else if (status == STATUS_TOUCH_SINGLE) {
                    int dx = (int) (event.getX() - oldX);
                    int dy = (int) (event.getY() - oldY);

                    oldX = event.getX();
                    oldY = event.getY();

                    if (!(dx == 0 && dy == 0)) {
                        int toMoveOf;
                        if (Math.abs(dx) >= Math.abs(dy)) {
                            toMoveOf = dx;
                        } else {
                            toMoveOf = dy;
                        }
                        switch (currentEdge) {
                            case EDGE_LT:
                                drawableFloatRect.set(drawableFloatRect.left + toMoveOf, drawableFloatRect.top + toMoveOf, drawableFloatRect.right, drawableFloatRect.bottom);
                                break;

                            case EDGE_RT:
                                if (toMoveOf == dy) {
                                    drawableFloatRect.set(drawableFloatRect.left, drawableFloatRect.top + toMoveOf, drawableFloatRect.right - toMoveOf, drawableFloatRect.bottom);
                                    break;
                                } else {
                                    drawableFloatRect.set(drawableFloatRect.left, drawableFloatRect.top - toMoveOf, drawableFloatRect.right + toMoveOf, drawableFloatRect.bottom);
                                    break;
                                }
                            case EDGE_LB:
                                if (toMoveOf == dx) {
                                    drawableFloatRect.set(drawableFloatRect.left + toMoveOf, drawableFloatRect.top, drawableFloatRect.right, drawableFloatRect.bottom - toMoveOf);
                                    break;
                                } else {
                                    drawableFloatRect.set(drawableFloatRect.left - toMoveOf, drawableFloatRect.top, drawableFloatRect.right, drawableFloatRect.bottom + toMoveOf);
                                    break;
                                }
                            case EDGE_RB:
                                drawableFloatRect.set(drawableFloatRect.left, drawableFloatRect.top, drawableFloatRect.right + toMoveOf, drawableFloatRect.bottom + toMoveOf);
                                break;

                            case EDGE_MOVE_IN:
                                if (isTouchInSquare) {
                                    drawableFloatRect.offset(dx, dy);
                                }
                                break;

                            case EDGE_MOVE_OUT:
                                break;
                        }
                        drawableFloatRect.sort();
                        invalidate();
                    }
                }
                break;
        }

        return true;
    }

    public int getTouchEdge(int eventX, int eventY) {
        if (floatDrawable.getBounds().left <= eventX && eventX < (floatDrawable.getBounds().left + floatDrawable.getCirleWidth())
                && floatDrawable.getBounds().top <= eventY && eventY < (floatDrawable.getBounds().top + floatDrawable.getCirleHeight())) {
            return EDGE_LT;//左上
        } else if ((floatDrawable.getBounds().right - floatDrawable.getCirleWidth()) <= eventX && eventX < floatDrawable.getBounds().right
                && floatDrawable.getBounds().top <= eventY && eventY < (floatDrawable.getBounds().top + floatDrawable.getCirleHeight())) {
            return EDGE_RT;//右上
        } else if (floatDrawable.getBounds().left <= eventX && eventX < (floatDrawable.getBounds().left + floatDrawable.getCirleWidth())
                && (floatDrawable.getBounds().bottom - floatDrawable.getCirleHeight()) <= eventY && eventY < floatDrawable.getBounds().bottom) {
            return EDGE_LB;//左下
        } else if ((floatDrawable.getBounds().right - floatDrawable.getCirleWidth()) <= eventX && eventX < floatDrawable.getBounds().right
                && (floatDrawable.getBounds().bottom - floatDrawable.getCirleHeight()) <= eventY && eventY < floatDrawable.getBounds().bottom) {
            return EDGE_RB;//右下
        } else if (floatDrawable.getBounds().contains(eventX, eventY)) {
            return EDGE_MOVE_IN;//里面移动
        }
        return EDGE_MOVE_OUT;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (drawable == null) {
            return;
        }

        if (drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0) {
            return;
        }

        configureBounds();

        drawable.draw(canvas);
        canvas.save();
        canvas.clipRect(drawableFloatRect, Region.Op.DIFFERENCE);
        canvas.drawColor(Color.parseColor("#a0000000"));
        canvas.restore();
        floatDrawable.draw(canvas);
    }

    protected void configureBounds() {
        if (isFrist) {
            oriRationWH = ((float) drawable.getIntrinsicWidth()) / ((float) drawable.getIntrinsicHeight());

            final float scale = context.getResources().getDisplayMetrics().density;
            int w = Math.min(getWidth(), (int) (drawable.getIntrinsicWidth() * scale + 0.5f));
            int h = (int) (w / oriRationWH);

            int left = (getWidth() - w) / 2;
            int top = (getHeight() - h) / 2;
            int right = left + w;
            int bottom = top + h;

            drawableSrcRect.set(left, top, right, bottom);
            drawableDstRect.set(drawableSrcRect);

            int floatWidth = dipToPx(context, cropWidth);
            int floatHeight = dipToPx(context, cropHeight);

            if (floatWidth > getWidth()) {
                floatWidth = getWidth();
                floatHeight = cropHeight * floatWidth / cropWidth;
            }

            if (floatHeight > getHeight()) {
                floatHeight = getHeight();
                floatWidth = cropWidth * floatHeight / cropHeight;
            }

            int floatLeft = (getWidth() - floatWidth) / 2;
            int floatTop = (getHeight() - floatHeight) / 2;
            drawableFloatRect.set(floatLeft, floatTop, floatLeft + floatWidth, floatTop + floatHeight);

            isFrist = false;
        }

        drawable.setBounds(drawableDstRect);
        floatDrawable.setBounds(drawableFloatRect);
    }

    protected void checkBounds() {
        int newLeft = drawableFloatRect.left;
        int newTop = drawableFloatRect.top;

        boolean isChange = false;
        if (drawableFloatRect.left < getLeft()) {
            newLeft = getLeft();
            isChange = true;
        }

        if (drawableFloatRect.top < getTop()) {
            newTop = getTop();
            isChange = true;
        }

        if (drawableFloatRect.right > getRight()) {
            newLeft = getRight() - drawableFloatRect.width();
            isChange = true;
        }

        if (drawableFloatRect.bottom > getBottom()) {
            newTop = getBottom() - drawableFloatRect.height();
            isChange = true;
        }

        drawableFloatRect.offsetTo(newLeft, newTop);
        if (isChange) {
            invalidate();
        }
    }

    public Bitmap getCropImage() {
        Bitmap tmpBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.RGB_565);
        Canvas canvas = new Canvas(tmpBitmap);
        drawable.draw(canvas);

        Matrix matrix = new Matrix();
        float scale = (float) (drawableSrcRect.width()) / (float) (drawableDstRect.width());
        matrix.postScale(scale, scale);

        Bitmap ret = Bitmap.createBitmap(tmpBitmap, drawableFloatRect.left, drawableFloatRect.top, drawableFloatRect.width(), drawableFloatRect.height(), matrix, true);
        tmpBitmap.recycle();

        return ret;
    }

    public int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
