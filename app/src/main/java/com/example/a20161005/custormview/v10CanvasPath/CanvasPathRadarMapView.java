package com.example.a20161005.custormview.v10CanvasPath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.example.xutil.LogUtil.L;


/**
 * Createdby ML on 2017/3/6.
 */

public class CanvasPathRadarMapView extends View {

    private String[] titles = new String[]{"A", "B", "C", "D", "E", "F"};
    private double[] values = new double[]{50, 50, 50, 50, 50, 50};
//    private double[] data = {100,60,60,60,100,50}; //各维度分值
    private int maxValues = 100;

    private int count = 6;  //几边形
    private int centerX;
    private int centerY;
    private float radius; //多边形之间的距离
    private float angle = (float) (Math.PI * 2 / count);

    private Paint mapPaint;
    private Paint valuePaint;
    private Paint textPaint;


    public CanvasPathRadarMapView(Context context) {
        super(context);
    }

    public CanvasPathRadarMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMapPaint();
        initTextPaint();
        initValuePaint();
    }

    private void initMapPaint() {
        mapPaint = new Paint();
        mapPaint.setColor(Color.BLACK);
        mapPaint.setStrokeWidth(2);
        mapPaint.setStyle(Paint.Style.STROKE);
    }

    private void initTextPaint() {
        textPaint = new Paint();
        textPaint.setColor(Color.RED);
        textPaint.setStrokeWidth(2);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.STROKE);
    }

    private void initValuePaint() {
        valuePaint = new Paint();
        valuePaint.setColor(Color.BLUE);
        valuePaint.setStrokeWidth(2);
        valuePaint.setAntiAlias(true);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        L.e("angle:" + angle);
        centerX = w / 2;
        centerY = h / 2;
        radius = Math.min(w, h) / 2 * 0.9f;
        L.e("radius:" + radius);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPolygon(canvas);
        drawLines(canvas);
        drawText(canvas);
        drawRegion(canvas);
    }

    private void drawPolygon(Canvas canvas){
        Path path = new Path();
        float r = radius/(count-1);//r是蜘蛛丝之间的间距
        for(int i=1;i<count;i++){//中心点不用绘制
            float curR = r*i;//当前半径
            path.reset();
            for(int j=0;j<count;j++){
                if(j==0){
                    path.moveTo(centerX+curR,centerY);
                }else{
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (centerX+curR*Math.cos(angle*j));
                    float y = (float) (centerY+curR*Math.sin(angle*j));
                    path.lineTo(x,y);
                }
            }
            path.close();//闭合路径
            canvas.drawPath(path, mapPaint);
        }
    }


    /**
     * 绘制直线
     */
    private void drawLines(Canvas canvas){
        Path path = new Path();
        for(int i=0;i<count;i++){
            path.reset();
            path.moveTo(centerX, centerY);
            float x = (float) (centerX+radius*Math.cos(angle*i));
            float y = (float) (centerY+radius*Math.sin(angle*i));
            path.lineTo(x, y);
            canvas.drawPath(path, mapPaint);
        }
    }

    /**
     * 绘制文字
     * @param canvas
     */
    private void drawText(Canvas canvas){
        textPaint.setTextSize(30);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for(int i=0;i<count;i++){
            float x = (float) (centerX+(radius+fontHeight/2)*Math.cos(angle*i));
            float y = (float) (centerY+(radius+fontHeight/2)*Math.sin(angle*i));
            if(angle*i>=0&&angle*i<=Math.PI/2){//第4象限
                canvas.drawText(titles[i], x,y,textPaint);
            }else if(angle*i>=3*Math.PI/2&&angle*i<=Math.PI*2){//第3象限
                canvas.drawText(titles[i], x,y,textPaint);
            }else if(angle*i>Math.PI/2&&angle*i<=Math.PI){//第2象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x-dis,y,textPaint);
            }else if(angle*i>=Math.PI&&angle*i<3*Math.PI/2){//第1象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x-dis,y,textPaint);
            }
        }
    }

    /**
     * 绘制区域
     * @param canvas
     */
    private void drawRegion(Canvas canvas){
        Path path = new Path();
        valuePaint.setAlpha(255);
        for(int i=0;i<count;i++){
            double percent = values[i]/maxValues;
            float x = (float) (centerX+radius*Math.cos(angle*i)*percent);
            float y = (float) (centerY+radius*Math.sin(angle*i)*percent);
            if(i==0){
                path.moveTo(x, centerY);
            }else{
                path.lineTo(x,y);
            }
            //绘制小圆点
            canvas.drawCircle(x,y,5,valuePaint);
        }
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);
        valuePaint.setAlpha(127);
        //绘制填充区域
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }
}
