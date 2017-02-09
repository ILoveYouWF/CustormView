package com.example.a20161005.custormview.v3AnimalCustormView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by ML on 2016/12/22.
 */

public class ExtandBaseView extends BaseView {
    private float moveX = 0;
    private RectF mRectF = new RectF(0, 40, 60, 100);
    private float sweepAngle = 0;

    public ExtandBaseView(Context context) {
        super(context);
        mIsAnimal = true;
    }

    public ExtandBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mIsAnimal = true;
    }

    @Override
    public void drawView(Canvas canvas) {
        mPaint.setTextSize(30);
        int r = mRand.nextInt(256);
        int g = mRand.nextInt(256);
        int b = mRand.nextInt(256);
        mPaint.setARGB(255, r, g, b);
        canvas.drawText("我在不停的移动中", moveX, 30, mPaint);
        canvas.drawArc(mRectF, 0, sweepAngle, true, mPaint);
    }

    @Override
    public void animalView() {
        moveX = moveX + 1;
        float textWitch = mPaint.measureText("我在不停的移动中");
        if (moveX > getWidth()) {
            moveX = 0 - textWitch;
        }
        sweepAngle = sweepAngle + 1;
        if (sweepAngle > 360) {
            sweepAngle = 0;
        }
    }

}
