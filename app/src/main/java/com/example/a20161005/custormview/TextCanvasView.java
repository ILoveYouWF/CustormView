package com.example.a20161005.custormview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.a20161005.custormview.v5Canvas.BaseCanvasView;

/**
 * Created by ML on 2017/3/1.
 */

public class TextCanvasView extends BaseCanvasView {


    public TextCanvasView(Context context) {
        super(context);
    }

    public TextCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        RectF rectF = new RectF(-200, -200, 200, 200);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(rectF, paint);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectF, 90, 90, false, paint);
        canvas.drawArc(rectF, 0, 90, true, paint);
    }
}
