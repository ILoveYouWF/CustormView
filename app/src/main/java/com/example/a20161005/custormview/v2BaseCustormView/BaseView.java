package com.example.a20161005.custormview.v2BaseCustormView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.a20161005.custormview.R;

/**
 * Created by ML on 2016/12/21.
 */

public class BaseView extends View {

    protected Paint mPaint;
    private int viewWigth;
    private int viewHigth;


    public BaseView(Context context) {
        //此构造方法是为了在代码中实例化对象使用
        super(context);
    }

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWigth = w;
        viewHigth = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /**
         * mPaint 画笔
         */
        mPaint = new Paint();
        /**
         * 绘制文字的时候设定文字的大小，和绘制图形无关
         */
        mPaint.setTextSize(60);
        mPaint.setColor(Color.RED);
        /**
         * 设置画笔的样式：
         * Paint.Style.FILL 填充
         * Paint.Style.STROKE 描边
         * Paint.Style.FILL_AND_STROKE 填充加描边
         */
        mPaint.setStyle(Paint.Style.FILL);
        /**
         * 绘制图形的时候描边的宽度，和绘制文字无关
         */
        mPaint.setStrokeWidth(5);

        /**
         * 画布配置背景颜色
         */
        canvas.drawColor(Color.BLUE);
        //        canvas.drawARGB(00,00,00,00);
        //        canvas.drawRGB(00,00,00);

        /**
         * 绘制一个点和一组点
         */
        canvas.drawPoint(200, 200, mPaint);
        canvas.drawPoints(new float[]{500, 500, 500, 600, 500, 700}, mPaint);

        /**
         * 绘制线条
         */
        canvas.drawLine(300, 300, 450, 300, mPaint);
        canvas.drawLines(new float[]{300, 350, 450, 350,
                300, 400, 450, 400}, mPaint);

        /**
         * float left,  矩形左侧相对view左侧的距离
         * float right,  矩形右侧相对view左侧的距离
         * float top,  矩形顶端相对view顶端的距离
         * float bottom,  矩形底端相对view底端的距离
         * 矩形的高 = bottom-top
         * 矩形的宽 = right - left
         * @NonNull Paint paint
         */
        //第一种
        canvas.drawRect(600, 600, 800, 1000, mPaint);
        //第二种
        Rect rect = new Rect(600, 600, 800, 1000);
        canvas.drawRect(rect, mPaint);
        //第三种 两者最大的区别就是精度不同，Rect是int(整形)的，而RectF是float(单精度浮点型)的
        RectF rectF = new RectF(600, 600, 800, 1000);
        canvas.drawRect(rectF, mPaint);

        /**
         * 绘制圆角矩形
         * 椭圆的两个半径:
         *  rx
         *  ry
         *  当你让 rx大于宽度的一半， ry大于高度的一半时你会发现圆角矩形变成了一个椭圆
         */
        RectF rectF1 = new RectF(200, 600, 400, 1000);
        canvas.drawRect(rectF1, mPaint);
        mPaint.setColor(Color.GRAY);
        canvas.drawRoundRect(rectF1, 200, 400, mPaint);

        /**
         * 绘制椭圆 只需要传入一个矩形的参数就可以了
         */
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectFO = new RectF(0, 600, 200, 1000);
        canvas.drawOval(rectFO, mPaint);

        /**
         * 绘制圆形
         * float cx, 原点X坐标
         * float cy, 原点Y坐标
         * float radius, 半径
         * @NonNull Paint paint
         */
        canvas.drawCircle(viewWigth/2, viewHigth-100, 90, mPaint);

        /**
         * 绘制一段弧度
         */
        mPaint.setColor(Color.MAGENTA);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectF1, -90.0F, -180.0F, true, mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawArc(rectF1, -45.0F, 90.0F, false, mPaint);


        float size = mPaint.measureText("自定义View"); //测量字符串的宽度、


        /**
         * canvas 画布
         * 绘制文字
         * @NonNullString text,  要绘制的文字   “自定义View”
         * int start,  所要绘制文字的开始下标
         * int end,   所要绘制文字的结束下标
         * float x,   绘制文字所在view的X坐标
         * float y,   绘制文字所在view的Y坐标（以文字的基准线为准，就是说当Y坐标为0时，文字会超出View消失）！
         * @NonNull Paint paint  画笔 （The paint used for the text (e.g. color, size, style)）
         *
         */
        canvas.drawText("自定义View", 100, 100, mPaint);
        canvas.drawText("自定义View", 0, 2, 100, 180, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);  // 设置绘制图形为空心的


        /**
         * 绘制图片
         * @NonNull Bitmap bitmap,
         * float left,  X坐标
         * float top,  Y坐标
         * @Nullable Paint paint
         */
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        canvas.drawBitmap(bitmap, 50, 300, mPaint);

    }
}
