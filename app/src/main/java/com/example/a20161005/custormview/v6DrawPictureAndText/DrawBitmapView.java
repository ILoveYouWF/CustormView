package com.example.a20161005.custormview.v6DrawPictureAndText;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;

import com.example.a20161005.custormview.R;
import com.example.a20161005.custormview.v5Canvas.BaseCanvasView;

/**
 * Created by ML on 2017/1/14.
 * 获取Bitmap方式:
 * <p>
 * 1 	通过Bitmap创建 	复制一个已有的Bitmap(新Bitmap状态和原有的一致) 或者 创建一个空白的Bitmap(内容可改变)
 * 2 	通过BitmapDrawable获取 	从资源文件 内存卡 网络等地方获取一张图片并转换为内容不可变的Bitmap
 * 3 	通过BitmapFactory获取 	从资源文件 内存卡 网络等地方获取一张图片并转换为内容不可变的Bitmap
 */

/**
 * 通过BitmapFactory从不同位置获取Bitmap:
 * 资源文件(drawable/mipmap/raw):    Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),R.raw.bitmap);
 * 资源文件(assets):
 * Bitmap bitmap=null;
 * try {
 * InputStream is = mContext.getAssets().open("bitmap.png");
 * bitmap = BitmapFactory.decodeStream(is);
 * is.close();
 * } catch (IOException e) {
 * e.printStackTrace();
 * }
 * 内存卡文件:   Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/bitmap.png");
 * 网络文件:
 * Bitmap bitmap = BitmapFactory.decodeStream(is);
 * is.close();
 */


public class DrawBitmapView extends BaseCanvasView {

    Bitmap bitmap = null;

    private static final int ANIM_NULL = 0;         //动画状态-没有
    private static final int ANIM_CHECK = 1;        //动画状态-开启
    private static final int ANIM_UNCHECK = 2;      //动画状态-结束

    private Handler mHandler;           // 用来做循环更新视图用

    private Paint mPaint;
    private Bitmap okBitmap;

    private int animCurrentPage = -1;       // 当前页码
    private int animMaxPage = 13;           // 总页数
    private int animDuration = 500;         // 动画时长
    private int animState = ANIM_NULL;      // 动画状态
    private int animPage;

    private boolean isCheck = false;        // 是否只选中状态

    public DrawBitmapView(Context context) {
        super(context);
    }

    public DrawBitmapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void initView() {
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.Darkorange));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        okBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ba);
        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (animCurrentPage < animMaxPage && animCurrentPage >= 0) {

                    if (animState == ANIM_NULL) {
                        return;
                    }
                    if (animState == ANIM_CHECK) {
                        animCurrentPage++;
                    } else if (animState == ANIM_UNCHECK) {
                        animCurrentPage--;
                    }
                    animPage = animCurrentPage;
                    invalidate();
                    this.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
                    Log.e("当前页数：", "Current = " + animCurrentPage);
                } else {
                    if (isCheck) {
                    } else {
                        animCurrentPage = -1;
                        invalidate();
                    }
                    animState = ANIM_NULL;
                }
            }
        };
        check();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                unCheck();
            }
        }, 1000);
    }

    /**
     * PS:图片左上角位置默认为坐标原点。
     * 绘制方法：
     * // 第一种
     * public void drawBitmap (Bitmap bitmap, Matrix matrix, Paint paint)
     * <p>
     * // 第二种
     * public void drawBitmap (Bitmap bitmap, float left, float top, Paint paint)
     * <p>
     * // 第三种
     * public void drawBitmap (Bitmap bitmap, Rect src, Rect dst, Paint paint)
     * public void drawBitmap (Bitmap bitmap, Rect src, RectF dst, Paint paint)
     * <p>
     * Rect src 	指定绘制图片的区域
     * Rect dst 或RectF dst 	指定图片在屏幕上显示(绘制)的区域
     * <p>
     * 用src指定了图片绘制部分的区域，然后用dst指定了绘制在屏幕上的绘制，即下图中蓝色方框标注的区域，图片宽高会根据指定的区域自动进行缩放。
     */


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //        try {
        //            InputStream stream = getContext().getAssets().open("boy.jpg");
        //            bitmap = BitmapFactory.decodeStream(stream);
        //            //            canvas.drawBitmap(bitmap, 0, 0, mPaint);
        //            Rect src = new Rect(0, 0, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        //            RectF dst = new RectF(0, 0, 200, 200);
        //            canvas.drawBitmap(bitmap, src, dst, mPaint);
        //            stream.close();
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }

        canvas.drawCircle(0, 0, 240, mPaint);
        Rect src = null;
        int sideLength = okBitmap.getHeight();
        if (animPage == animMaxPage) {
            animPage--;
        }
        src = new Rect(sideLength * animPage, 0, sideLength * (animPage + 1), sideLength);
        Rect dst = new Rect(-200, -200, 200, 200);
        //        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        //        canvas.drawRect(dst, mPaint);
        canvas.drawBitmap(okBitmap, src, dst, null);
    }

    //开始动画
    public void check() {
        if (animState != ANIM_NULL || isCheck) {
            return;
        }
        animState = ANIM_CHECK;
        animCurrentPage = 0;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCheck = true;
    }

    //结束动画
    public void unCheck() {
        if (animState != ANIM_NULL || (!isCheck)) {
            return;
        }
        animState = ANIM_UNCHECK;
        animCurrentPage = animCurrentPage - 1;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCheck = false;

    }

    //设置动画总时间
    public void setAnimDuration(int animDuration) {
        if (animDuration <= 0) {
            return;
        }
        this.animDuration = animDuration;
    }

    /**
     * 设置背景圆形颜色
     *
     * @param color
     */
    public void setBackgroundColor(int color) {
        mPaint.setColor(color);
    }

}
