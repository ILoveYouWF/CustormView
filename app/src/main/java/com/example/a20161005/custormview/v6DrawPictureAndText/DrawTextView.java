package com.example.a20161005.custormview.v6DrawPictureAndText;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.example.a20161005.custormview.v5Canvas.BaseCanvasView;

/**
 * Created by ML on 2017/1/14.
 */

public class DrawTextView extends BaseCanvasView {


    /**
     * 常用方法:
     * // 第一类
     * public void drawText (String text, float x, float y, Paint paint)
     * public void drawText (String text, int start, int end, float x, float y, Paint paint)
     * public void drawText (CharSequence text, int start, int end, float x, float y, Paint paint)
     * public void drawText (char[] text, int index, int count, float x, float y, Paint paint)
     * <p>
     * // 第二类
     * public void drawPosText (String text, float[] pos, Paint paint)
     * public void drawPosText (char[] text, int index, int count, float[] pos, Paint paint)
     * <p>
     * // 第三类
     * public void drawTextOnPath (String text, Path path, float hOffset, float vOffset, Paint paint)
     * public void drawTextOnPath (char[] text, int index, int count, Path path, float hOffset, float vOffset, Paint paint)
     * <p>
     * 第一类只能指定文本基线位置(基线x默认在字符串左侧，基线y默认在字符串下方)。
     * 第二类可以分别指定每个文字的位置。
     * 第三类是指定一个路径，根据路径绘制文字
     */


    public DrawTextView(Context context) {
        super(context);
    }

    public DrawTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTextPaint();
    }

    public void initTextPaint() {
        mPaint.setColor(Color.BLACK);        // 设置颜色
        mPaint.setStyle(Paint.Style.FILL);   // 设置样式
        mPaint.setTextSize(50);              // 设置字体大小
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String message = "马良是个大帅哥";
        char[] chars = message.toCharArray();

        canvas.drawText(message, -200, -200, mPaint);
        //对于String和CharSequence来说只指定字符串下标start和end位置(注意：0<= start < end < str.length()),包头不包尾
        canvas.drawText(message, 0, 2, 0, 0, mPaint);
        //对于字符数组char[]我们截取字符串使用起始位置(index)和长度(count)来确定。
        canvas.drawText(chars, 0, 2, -200, 200, mPaint);

        String str = "SLOOP";
        /**
         * 第二类 反对理由
         *1 必须指定所有字符位置，否则直接crash掉，反人类设计
         *2 性能不佳，在大量使用的时候可能导致卡顿
         *3 不支持emoji等特殊字符，不支持字形组合与分解
         */
        canvas.drawPosText(str, new float[]{
                100, 100,    // 第一个字符位置
                200, 200,    // 第二个字符位置
                300, 300,    // ...
                400, 400,
                500, 500
        }, mPaint);


    }
}
