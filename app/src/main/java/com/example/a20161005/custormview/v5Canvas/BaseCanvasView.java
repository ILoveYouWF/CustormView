package com.example.a20161005.custormview.v5Canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.a20161005.custormview.R;

/**
 * Created by ML on 2017/1/13.
 */

public class BaseCanvasView extends View {

    protected Paint mPaint;

    protected int width;
    protected int hight;

    protected int X;
    protected int Y;

    protected boolean canvasAssistLine = true;

    public BaseCanvasView(Context context) {
        super(context);
        initPaint();
    }

    public BaseCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    protected void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        mPaint.setColor(getResources().getColor(R.color.black));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        hight = h;
        X = width / 2;
        Y = hight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //TODO 移动原点坐标到画布的中心位置，并以此为原点绘制坐标系！
        canvas.translate(X, Y);
        if (canvasAssistLine) {
            mPaint.setColor(getResources().getColor(R.color.black));
            canvas.drawLine(-X, 0, X, 0, mPaint);
            canvas.drawLine(0, -Y, 0, Y, mPaint);
        }
    }
}
