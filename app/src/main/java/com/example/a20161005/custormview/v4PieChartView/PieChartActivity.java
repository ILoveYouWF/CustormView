package com.example.a20161005.custormview.v4PieChartView;

import android.os.Bundle;
import android.os.Handler;

import com.example.a20161005.custormview.BaseAcitivty;
import com.example.a20161005.custormview.R;

import java.util.ArrayList;

public class PieChartActivity extends BaseAcitivty {

    private ArrayList<PieData> mPieDatas = new ArrayList<>();
    private PieView mPieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        mPieView = new PieView(this);
        setContentView(R.layout.v4_piechat_view);
        mTitleView.initTopTitle("绘制饼状图");
        mPieView = (PieView) findViewById(R.id.pie_chart);
        initData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPieView.setPieDatas(mPieDatas);
            }
        }, 50);
    }

    private void initData() {
        mPieDatas.add(new PieData("土建一部", 100));
        mPieDatas.add(new PieData("土建二部", 200));
        mPieDatas.add(new PieData("土建三部", 300));
        mPieDatas.add(new PieData("安装一部", 400));
        mPieDatas.add(new PieData("安装二部", 500));
        mPieDatas.add(new PieData("市政工程部", 600));
        mPieDatas.add(new PieData("招标代理部", 700));
    }
}
