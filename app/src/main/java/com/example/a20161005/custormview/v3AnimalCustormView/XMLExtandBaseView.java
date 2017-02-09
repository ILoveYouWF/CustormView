package com.example.a20161005.custormview.v3AnimalCustormView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.example.a20161005.custormview.R;

/**
 * Created by ML on 2016/12/22.
 */

public class XMLExtandBaseView extends BaseView {
    private int lines = 1;
    private int textColor;


    public XMLExtandBaseView(Context context) {
        super(context);
    }

    public XMLExtandBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumView);
        lines = typedArray.getInt(R.styleable.NumView_lines, 1);
        textColor = typedArray.getInt(R.styleable.NumView_textColor, getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void drawView(Canvas canvas) {
        mPaint.setColor(textColor);
        for (int i = 1; i <= lines; i++) {
            int textSize = 30 * i;
            mPaint.setTextSize(textSize);
            canvas.drawText("自定义属性", 0, textSize * i, mPaint);
        }
    }

    @Override
    public void animalView() {
    }

}
