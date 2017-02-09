package com.example.a20161005.custormview.v5Canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.example.a20161005.custormview.R;

/**
 * Created by ML on 2017/1/13.
 * 移动画布坐标原点
 * 这个移动是基于上次坐标点的
 */

public class CanvasTranslateView extends BaseCanvasView {

    public CanvasTranslateView(Context context) {
        super(context);
    }

    public CanvasTranslateView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        canvas.drawPoint(0, 0, mPaint);
        canvas.drawCircle(0, 0, 80, mPaint);

        canvas.translate(200, 0);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        canvas.drawPoint(0, 0, mPaint);
        canvas.drawCircle(0, 0, 80, mPaint);

        canvas.translate(-400, 0);
        mPaint.setColor(getResources().getColor(R.color.DarkMagenta));
        canvas.drawPoint(0, 0, mPaint);
        canvas.drawCircle(0, 0, 80, mPaint);

        canvas.translate(200, 200);
        mPaint.setColor(getResources().getColor(R.color.Yellow));
        canvas.drawPoint(0, 0, mPaint);
        canvas.drawCircle(0, 0, 80, mPaint);

        canvas.translate(0, -400);
        mPaint.setColor(getResources().getColor(R.color.Aqua));
        canvas.drawPoint(0, 0, mPaint);
        canvas.drawCircle(0, 0, 80, mPaint);

    }
}
