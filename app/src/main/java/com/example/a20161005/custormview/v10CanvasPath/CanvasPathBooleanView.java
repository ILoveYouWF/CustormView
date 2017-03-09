package com.example.a20161005.custormview.v10CanvasPath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.a20161005.custormview.v5Canvas.BaseCanvasView;
import com.example.xutil.LogUtil.L;

/**
 * Createdby ML on 2017/3/6.
 */

public class CanvasPathBooleanView extends BaseCanvasView {


    public CanvasPathBooleanView(Context context) {
        super(context);
    }

    public CanvasPathBooleanView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawIsEmpty();
        drawIsRect();
        drawSet(canvas);
        drawOffSet(canvas);
    }

    private void drawOffSet(Canvas canvas) {
        //TODO: 这个的作用也很简单，就是对path进行一段平移，它和Canvas中的translate作用很像，但Canvas作用于整个画布，而path的offset只作用于当前path。
        /**
         dst状态 	效果
         dst不为空 	将当前path平移后的状态存入dst中，不会影响当前path
         dat为空(null) 	平移将作用于当前path，相当于第一种方法
         **/
        Path path = new Path();                     // path中添加一个圆形(圆心在坐标原点)
        path.addCircle(0, 0, 100, Path.Direction.CW);
        Path dst = new Path();                      // dst中添加一个矩形
        dst.addRect(-200, -200, 200, 200, Path.Direction.CW);
        path.offset(300, 0, dst);                     // 平移
        canvas.drawPath(path, mPaint);               // 绘制path
        mPaint.setColor(Color.BLUE);                // 更改画笔颜色
        canvas.drawPath(dst, mPaint);                // 绘制dst
    }

    private void drawSet(Canvas canvas) {
        //TODO: 将新的path赋值到现有path。
        canvas.scale(1, -1);                         // <-- 注意 翻转y坐标轴
        Path path = new Path();                     // path添加一个矩形
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        Path src = new Path();                      // src添加一个圆
        src.addCircle(0, 0, 100, Path.Direction.CW);
        path.set(src);                              // 大致相当于 path = src;
        canvas.drawPath(path, mPaint);
    }

    private void drawIsRect() {
        //TODO: 判断path是否是一个矩形，如果是一个矩形的话，会将矩形的信息存放进参数rect中。
        Path path = new Path();
        path.lineTo(0, 400);
        path.lineTo(400, 400);
        path.lineTo(400, 0);
        path.lineTo(0, 0);
        RectF rect = new RectF();
        boolean b = path.isRect(rect);
        L.e("isRect:" + b + "| left:" + rect.left + "| top:" + rect.top + "| right:" + rect.right + "| bottom:" + rect.bottom);
    }

    private void drawIsEmpty() {
        //TODO: 判断path中是否包含内容。
        Path path = new Path();
        L.e(path.isEmpty() + "");
        path.lineTo(100, 100);
        L.e(path.isEmpty() + "");
    }
}
