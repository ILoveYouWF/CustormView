package com.example.a20161005.custormview.v8GAStudioLeafLoadingDemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.a20161005.custormview.R;

/**
 * Created by ML on 2017/1/20.
 */

public class CanvasRotateWheel extends View {

    private Paint mPaint;
    private int totalWidth;
    private int totalHeigth;

    private Bitmap mWheelBitmap;


    public CanvasRotateWheel(Context context) {
        super(context);
    }

    public CanvasRotateWheel(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        mWheelBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.fengshan);
    }

    public void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        totalWidth = w;
        totalHeigth = h;
    }

    private MyThread mThread;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mThread == null) {
            mThread = new MyThread();
        } else {
            drawView(canvas);
        }
        postInvalidate();
    }

    int index = 0;

    public void drawView(Canvas canvas) {
        canvas.translate(totalWidth / 2, totalHeigth / 2);
        canvas.drawColor(getResources().getColor(R.color.loading));
        canvas.drawCircle(0, 0, 100, mPaint);
        canvas.drawBitmap(mWheelBitmap, -mWheelBitmap.getWidth() / 2, -mWheelBitmap.getHeight() / 2, mPaint);
        canvas.rotate(index);
    }

    public class MyThread extends Thread {

        @Override
        public void run() {
            super.run();

            while (true) {
                index++;
                postInvalidate();
            }

        }
    }


}
