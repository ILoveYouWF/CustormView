package com.example.a20161005.custormview.v5Canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;

import com.example.a20161005.custormview.R;

/**
 * Created by ML on 2017/1/13.
 * 画布的缩放
 */

public class CanvasSkewView extends BaseCanvasView {

    public CanvasSkewView(Context context) {
        super(context);
    }

    public CanvasSkewView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(0, 0, 200, 200);
        mPaint.setColor(getResources().getColor(R.color.Red));
        canvas.drawRect(rectF, mPaint);

        double angle = Math.atan(2) / Math.PI * 180;
        Log.e("角度值：", angle + "");
        canvas.skew(0, -2);
        mPaint.setColor(getResources().getColor(R.color.Chocolate));
        canvas.drawRect(rectF, mPaint);

    }
}
