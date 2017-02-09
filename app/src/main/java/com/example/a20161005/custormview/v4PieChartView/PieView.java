package com.example.a20161005.custormview.v4PieChartView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;

/**
 * Created by ML on 2016/12/29.
 */

public class PieView extends View {

    private Paint mPaint;

    // 颜色表(注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    private ArrayList<PieData> mPieDatas = new ArrayList<>();

    private float startAngle = 0;  //绘制扇形开始角度

    private int viewWidth;
    private int viewHight;


    /**
     * 构造方法
     *
     * @param context
     */
    public PieView(Context context) {
        this(context, null);

    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);   //防止边缘锯齿！
    }

    /**
     * 测量View的大小
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    /**
     * 确定View的大小
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHight = h;
    }

    /**
     * 确定子View的布局
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);


    }

    public void setStartAngle(float startAngle) {
        this.startAngle = startAngle;
        invalidate();
    }

    public void setPieDatas(ArrayList<PieData> pieDatas) {
        this.mPieDatas = pieDatas;
        initData(mPieDatas);
        invalidate();
    }

    private void initData(ArrayList<PieData> pieDatas) {

        if (pieDatas == null || pieDatas.size() == 0) {
            return;
        }

        float allValue = 0;
        float allAngle = 0;
        for (int i = 0; i < pieDatas.size(); i++) {
            PieData data = pieDatas.get(i);
            allValue += data.getValue();
            int j = i % mColors.length;
            data.setPieColor(mColors[j]);
        }

        for (int i = 0; i < pieDatas.size(); i++) {

            PieData data = pieDatas.get(i);
            float percentage = data.getValue() / allValue;
            float angle = percentage * 360;

            data.setPieAngle(angle);
            data.setPercentage(percentage);
            allAngle += angle;
            Log.i("angle", "" + data.getPieAngle());
        }
        Log.e("allAngle", allAngle + "");
    }


    /**
     * 绘制图形
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPieDatas == null || mPieDatas.size() == 0) {
            return;
        }

        float currentStartAngle = startAngle;
        canvas.translate(viewWidth / 2, viewHight / 2);  //将画布的坐标原点移动到中心位置！
        float r = (float) (Math.min(viewWidth, viewHight) / 2 * 0.8); //饼状图的半径
        RectF rect = new RectF(-r, -r, r, r);   //根据半径得到矩形

        for (PieData data : mPieDatas) {    //循环绘制扇形饼图
            mPaint.setColor(data.getPieColor());
            canvas.drawArc(rect, currentStartAngle, data.getPieAngle(), true, mPaint);
            currentStartAngle += data.getPieAngle();
        }
    }
    /*对外提供设置数据接口*/
}
