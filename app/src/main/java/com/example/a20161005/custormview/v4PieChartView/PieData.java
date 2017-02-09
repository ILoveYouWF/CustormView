package com.example.a20161005.custormview.v4PieChartView;

import android.support.annotation.NonNull;

/**
 * Created by ML on 2016/12/29.
 * 饼图统计图
 */

public class PieData {

    //用户关心
    private String pieName;   // 名字
    private float value;        // 数值
    private float percentage;   //百分比

    //用户不关心
    private int pieColor = 0;  //颜色
    private float pieAngle = 0;  //角度


    public PieData(@NonNull String pieName,@NonNull float value) {
        this.pieName = pieName;
        this.value = value;
    }

    public String getPieName() {
        return pieName;
    }

    public void setPieName(String pieName) {
        this.pieName = pieName;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getPieColor() {
        return pieColor;
    }

    public void setPieColor(int pieColor) {
        this.pieColor = pieColor;
    }

    public float getPieAngle() {
        return pieAngle;
    }

    public void setPieAngle(float pieAngle) {
        this.pieAngle = pieAngle;
    }
}
