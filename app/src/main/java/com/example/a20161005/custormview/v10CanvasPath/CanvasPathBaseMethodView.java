package com.example.a20161005.custormview.v10CanvasPath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.util.AttributeSet;

import com.example.a20161005.custormview.v5Canvas.BaseCanvasView;

/**
 * Createdby ML on 2017/3/6.
 */

public class CanvasPathBaseMethodView extends BaseCanvasView {


    public CanvasPathBaseMethodView(Context context) {
        super(context);
    }

    public CanvasPathBaseMethodView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLineTo(canvas);
        drawMoveTo(canvas);
        drawLastPoint(canvas);
        drawClose(canvas);

    }

    private void drawClose(Canvas canvas) {
        // TODO: 连接当前最后一个点和最初的一个点(如果两个点不重合的话)，最终形成一个封闭的图形。
        // TODO: 注意：close的作用是封闭路径，与连接当前最后一个点和第一个点并不等价。如果连接了最后一个点和第一个点仍然无法形成封闭图形，则close什么 也不做。
        Path path = new Path();
        mPaint.setColor(Color.RED);
        path.lineTo(100, 100);
        path.lineTo(0, 100);
        path.close();
        canvas.drawPath(path, mPaint);
    }

    private void drawLastPoint(Canvas canvas) {
        //TODO: 设置之前操作的最后一个点位置(影响之前最后一步的操作和接下来的操作)
        Path path = new Path();
        path.lineTo(200, -200);
        path.setLastPoint(200, -100);
        path.lineTo(200, 0);
        canvas.drawPath(path, mPaint);
    }

    private void drawMoveTo(Canvas canvas) {
        // TODO:  改变下次操作的起点(不影响之前的操作)
        Path path = new Path();
        path.lineTo(-200, -200);
        path.moveTo(-200, -100);
        path.lineTo(-200, -200);
        canvas.drawPath(path, mPaint);
    }

    private void drawLineTo(Canvas canvas) {
        //TODO 从上一个操作点到参数坐标点之间连一条线(所以默认点就是坐标原点)
        Path path = new Path();
        path.lineTo(200, 200);
        path.lineTo(0, 200);
        canvas.drawPath(path, mPaint);
    }
}
