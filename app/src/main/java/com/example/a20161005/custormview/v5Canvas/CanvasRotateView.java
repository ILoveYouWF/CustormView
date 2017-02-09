package com.example.a20161005.custormview.v5Canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * Created by ML on 2017/1/13.
 * 画布的缩放
 */

public class CanvasRotateView extends BaseCanvasView {

    public CanvasRotateView(Context context) {
        super(context);
    }

    public CanvasRotateView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //        canvas.drawLine(-X, 0, X, 0, mPaint);
        //        canvas.drawLine(0, -Y, 0, Y, mPaint);
        //        RectF rectF = new RectF(0, -300, 300, 0);
        //        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        //        mPaint.setStrokeWidth(4);
        //        canvas.drawRect(rectF, mPaint);
        //
        //        canvas.rotate(90,150,0);
        //        mPaint.setColor(getResources().getColor(R.color.Darkorange));
        //        canvas.drawRect(rectF, mPaint);
        //
        //        canvas.rotate(90,150,0);
        //        mPaint.setColor(getResources().getColor(R.color.Aqua));
        //        canvas.drawRect(rectF, mPaint);


        canvas.drawCircle(0, 0, 300, mPaint);          // 绘制两个圆形
        canvas.drawCircle(0, 0, 280, mPaint);

        for (int i = 0; i <= 360; i += 10) {               // 绘制圆形之间的连接线
            canvas.drawLine(0, 280, 0, 300, mPaint);
            canvas.rotate(10);
        }
    }

}
