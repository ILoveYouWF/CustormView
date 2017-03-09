package com.example.a20161005.custormview.v10CanvasPath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.a20161005.custormview.R;
import com.example.a20161005.custormview.v5Canvas.BaseCanvasView;

/**
 * Createdby ML on 2017/3/6.
 */

public class CanvasPathAddMethodView extends BaseCanvasView {
    /**
     * 提供一个确定点（ABCD）位置的小方法： （float left, float top, float right, float bottom）
     * A点：left和top确定
     * B点：top和right确定
     * C点：right和bottom确定
     * D点：bottom和left确定
     * 自己发小小规律哟~~~~~~~~~~
     **/
    public CanvasPathAddMethodView(Context context) {
        super(context);
    }

    public CanvasPathAddMethodView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    /**
     * Direction是一个枚举(Enum)类型:
     * CW 	clockwise 	顺时针
     * CCW 	counter-clockwise 	逆时针
     * <p>  作用：
     * 1 	在添加图形时确定闭合顺序(各个点的记录顺序)
     * 2 	对图形的渲染结果有影响(是判断图形渲染的重要条件)
     **/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDShapeCW(canvas);
        drawBShapeCCW(canvas);
        drawCShapeCCM(canvas);
        drawAddPath(canvas);
        drawArcTo(canvas);
    }

    private void drawArcTo(Canvas canvas) {
        /**
         addArc 添加一个圆弧到path 	直接添加一个圆弧到path中
         public void addArc (RectF oval, float startAngle, float sweepAngle)
         arcTo 添加一个圆弧到path 	添加一个圆弧到path，如果圆弧的起点和上次最后一个坐标点不相同，就连接两个点
         public void arcTo (RectF oval, float startAngle, float sweepAngle)
         public void arcTo (RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo)

         forceMoveTo:
         true 	将最后一个点移动到圆弧起点，即不连接最后一个点与圆弧起点 	public void addArc (RectF oval, float startAngle, float sweepAngle)
         false 	不移动，而是连接最后一个点与圆弧起点 	public void arcTo (RectF oval, float startAngle, float sweepAngle)
         **/

        Path path = new Path();
        path.lineTo(100, -100);
        RectF rectF = new RectF(0, 0, 300, 300);
        path.addRect(rectF, Path.Direction.CW);
        //        path.addOval(rectF, Path.Direction.CW);
        path.addArc(rectF, 0, 270);
        path.arcTo(rectF, 0, 270, false);
        mPaint.setColor(getResources().getColor(R.color.Yellow));
        canvas.drawPath(path, mPaint);
    }

    private void drawAddPath(Canvas canvas) {
        /**
         public void addPath (Path src)
         两个参数是将src进行了位移之后再添加进当前path中。
         public void addPath (Path src, float dx, float dy)
         将src添加到当前path之前先使用Matrix进行变换。
         public void addPath (Path src, Matrix matrix)
         **/
        Path path = new Path();
        Path cir = new Path();
        cir.addCircle(0, 0, 20, Path.Direction.CW);
        RectF rectF = new RectF(-100, -300, 100, 0);
        path.addOval(rectF, Path.Direction.CW);
        path.addPath(cir, 0, 20);
        mPaint.setColor(getResources().getColor(R.color.DarkMagenta));
        canvas.drawPath(path, mPaint);
    }

    private void drawCShapeCCM(Canvas canvas) {
        //TODO: 逆时针绘制点, A-B-C-D
        //TODO: 注意虽然是同样的矩形但是坐标点的位置发生的变化，这样就影响了最后绘制出来的内容
        Path path = new Path();
        path.addRect(200, -200, -200, 200, Path.Direction.CCW);
        path.setLastPoint(-300, -300);
        canvas.drawPath(path, mPaint);
    }

    private void drawBShapeCCW(Canvas canvas) {
        //TODO: 逆时针绘制点, A-D-C-B
        mPaint.setColor(Color.RED);
        Path path = new Path();
        path.addRect(-200, -200, 200, 200, Path.Direction.CCW);
        path.setLastPoint(-300, 300);
        canvas.drawPath(path, mPaint);
    }

    private void drawDShapeCW(Canvas canvas) {
        //TODO: 逆时针绘制点, A-B-C-D
        Path path = new Path();
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        path.setLastPoint(-300, 300);
        canvas.drawPath(path, mPaint);
    }
}
