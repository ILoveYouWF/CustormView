package com.example.a20161005.custormview.v3AnimalCustormView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by ML on 2016/12/21.
 */

public abstract class BaseView extends View {

    protected MyThread mThread;
    protected Paint mPaint = new Paint();
    protected Random mRand = new Random();
    protected boolean mIsAnimal = true;


    public BaseView(Context context) {
        super(context);
    }

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (mThread == null) {
            mThread = new MyThread();
            mThread.start();
        } else {
            drawView(canvas);
        }
    }

    public abstract void drawView(Canvas canvas);

    public abstract void animalView();


    protected class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            if (mIsAnimal) {
                while (true) {
                    animalView();
                    postInvalidate();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                postInvalidate();
            }
        }
    }
}
