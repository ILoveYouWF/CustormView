package com.example.a20161005.custormview.v6DrawPictureAndText;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;

import com.example.a20161005.custormview.R;
import com.example.a20161005.custormview.v5Canvas.BaseCanvasView;

//TODO 使用Picture前请关闭硬件加速，以免引起不必要的问题！
//TODO 使用Picture前请关闭硬件加速，以免引起不必要的问题！
//TODO 使用Picture前请关闭硬件加速，以免引起不必要的问题！
//TODO 在AndroidMenifest文件中application节点下添上 android:hardwareAccelerated="false"以关闭整个应用的硬件加速!!!!

/**
 * Created by ML on 2017/1/14.
 * 可以把Picture看作是一个录制Canvas操作的录像机
 * 我们把Canvas绘制点，线，矩形等诸多操作用Picture录制下来，下次需要的时候拿来就能用，
 * 使用Picture相比于再次调用绘图API，开销是比较小的，也就是说对于重复的操作可以更加省时省力。
 */

/**
 * 提供的方法：
 * public int getWidth () 	获取宽度
 * public int getHeight () 	获取高度
 * public Canvas beginRecording (int width, int height) 	开始录制 (返回一个Canvas，在Canvas中所有的绘制都会存储在Picture中)
 * public void endRecording () 	结束录制
 * public void draw (Canvas canvas) 	将Picture中内容绘制到Canvas中
 */

public class DrawPictureView extends BaseCanvasView {

    private Picture picture = new Picture();

    public DrawPictureView(Context context) {
        super(context);
    }

    public DrawPictureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initRecording();
    }

    public void initRecording() {
        /**
         * 注意：这里指定录制区域如果你指定确定的数值，可以不做考虑，
         * 如果你指定的是需要测量得到的，那么一定要确保在测量结果后再开始录制，不然会有问题！
         */
        Canvas canvas = picture.beginRecording(width, hight);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.Yellow));
        canvas.drawCircle(100, 100, 300, mPaint);
        picture.endRecording();
    }

    /**
     * 将Picture中的内容绘制出来可以有以下几种方法：
     * 1 	使用Picture提供的draw方法绘制。
     * 2 	使用Canvas提供的drawPicture方法绘制。
     * 3 	将Picture包装成为PictureDrawable，使用PictureDrawable的draw方法绘制。
     * <p>
     * 以上几种方法主要区别：
     * 主要区别            	分类 	                        简介
     * 是否对Canvas有影响 	1有影响  2,3不影响 	            此处指绘制完成后是否会影响Canvas的状态(Matrix clip等)
     * 可操作性强弱        	1可操作性较弱  2,3可操作性较强 	此处的可操作性可以简单理解为对绘制结果可控程度。
     */

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 方法一 ：这种方法在比较低版本的系统上绘制后可能会影响Canvas状态，所以这种方法一般不会使用
         * picture.draw(canvas);
         */

        /**
         方法二 : 使用Canvas提供的drawPicture方法绘制  不会影响canvas的状态
         public void drawPicture (Picture picture)

         public void drawPicture (Picture picture, Rect dst)

         public void drawPicture (Picture picture, RectF dst)

         方法2 3，是将Picture的内容绘制在指定的范围内，并对绘制的内容根据选区进行了缩放。
         注意：这时候是绘制的内容是之前已经录制好的了，所以这时候设置画笔的相关状态并不会影响绘制的内容！

         问题：矩形的位置和Picture位置之间的关系，
         推测 ：根据方法的源码来看，矩形的left和top会改变画布的中心点，从而影响Picture的位置，同时也受 “-” 好的影响！

         */

//        RectF rectF = new RectF(100, 100, -getWidth(), 500);
//        canvas.drawPicture(picture, rectF);

        /**
         * 方法三：将Picture包装成为PictureDrawable，使用PictureDrawable的draw方法绘制。
         */

        // 包装成为Drawable
        PictureDrawable drawable = new PictureDrawable(picture);
        // 设置绘制区域 -- 注意此处所绘制的实际内容不会缩放
        drawable.setBounds(0,0,250,picture.getHeight());
        // 绘制
        drawable.draw(canvas);

    }
}