package com.example.a20161005.custormview.v5Canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.a20161005.custormview.R;

/**
 * Created by ML on 2017/1/13.
 * 画布的缩放
 *
 * @param sx The amount to scale in X
 * @param sy The amount to scale in Y
 * @param px The x-coord for the pivot point (unchanged by the scale)  //旋转中心点的改变
 * @param py The y-coord for the pivot point (unchanged by the scale)
 */

/**
 * 缩放规则
 * [-∞, -1)	先根据缩放中心放大n倍，再根据中心轴进行翻转
 * -1 	    根据缩放中心轴进行翻转
 * (-1, 0) 	先根据缩放中心缩小到n，再根据中心轴进行翻转
 * 0 	    不会显示，若sx为0，则宽度为0，不会显示，sy同理
 * (0, 1) 	根据缩放中心缩小到n
 * 1 	    没有变化
 * (1, +∞)	根据缩放中心放大n倍
 * 补充：旋转一直都是顺时针180度
 */

public class CanvasScaleView extends BaseCanvasView {

    public CanvasScaleView(Context context) {
        super(context);
    }

    public CanvasScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //        没有缩放状态
        //        RectF rectF = new RectF(-300, -300, 300, 300);
        //        canvas.drawColor(Color.RED);
        //        mPaint.setColor(getResources().getColor(R.color.black));
        //        canvas.drawRect(rectF, mPaint);
        //        //开始第一次缩放
        //        mPaint.setColor(getResources().getColor(R.color.top_bar_title_bg));
        //        canvas.scale(0.5f, 0.5f);
        //        canvas.drawRect(rectF, mPaint);
        //        //开始第二次缩放
        //        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        //        canvas.scale(0.5f, 0.5f);
        //        canvas.drawRect(rectF, mPaint);
        //        //开始第三次缩放
        //        mPaint.setColor(getResources().getColor(R.color.Yellow));
        //        canvas.scale(0.5f, 0.5f);
        //        canvas.drawRect(rectF, mPaint);


        //        mPaint.setStyle(Paint.Style.STROKE);
        //        mPaint.setStrokeWidth(10);
        //        RectF rect = new RectF(-400, -400, 400, 400);   // 矩形区域
        //        for (int i = 0; i <= 20; i++) {
        //            canvas.scale(0.9f, 0.9f);
        //            canvas.drawRect(rect, mPaint);
        //        }

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        mPaint.setColor(getResources().getColor(R.color.black));
        canvas.drawLine(-X, 0, X, 0, mPaint);
        canvas.drawLine(0, -Y, 0, Y, mPaint);
        RectF rectF = new RectF(0, -300, 300, 0);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        canvas.drawRect(rectF, mPaint);

        mPaint.setStrokeWidth(5);
        mPaint.setColor(getResources().getColor(R.color.Yellow));
        canvas.scale(-0.5f, -0.5f);
        canvas.drawRect(rectF, mPaint);

        mPaint.setStrokeWidth(10);
        mPaint.setColor(getResources().getColor(R.color.DarkMagenta));
        canvas.scale(-0.5f, -0.5f);
        canvas.drawRect(rectF, mPaint);

        mPaint.setStrokeWidth(15);
        mPaint.setColor(getResources().getColor(R.color.DarkMagenta));
        canvas.scale(-1f, -1f);
        canvas.drawRect(rectF, mPaint);

        mPaint.setStrokeWidth(15);
        mPaint.setColor(getResources().getColor(R.color.Aqua));
        canvas.scale(-0.5f, -0.5f, -300, 0);
        canvas.drawRect(rectF, mPaint);

    }
}
