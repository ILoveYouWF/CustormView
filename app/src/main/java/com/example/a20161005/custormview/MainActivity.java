package com.example.a20161005.custormview;

import android.os.Bundle;
import android.view.View;

import com.example.a20161005.custormview.v10CanvasPath.CanvasPathActivity;
import com.example.a20161005.custormview.v11BezierOfPath.CanvasBeziarActivity;
import com.example.a20161005.custormview.v1MobileCoordinates.MobileCoordinatesActivity;
import com.example.a20161005.custormview.v2BaseCustormView.BaseViewActivity;
import com.example.a20161005.custormview.v3AnimalCustormView.AnimalCustormViewActivity;
import com.example.a20161005.custormview.v4PieChartView.PieChartActivity;
import com.example.a20161005.custormview.v5Canvas.CanvasActivity;
import com.example.a20161005.custormview.v6DrawPictureAndText.DrawPictureActivity;
import com.example.a20161005.custormview.v7EditTextNum.EditTextNumActivity;
import com.example.a20161005.custormview.v8GAStudioLeafLoadingDemo.CustormLoadingActivty;

/**
 * Created by ML on 2017/1/9.
 */

public class MainActivity extends BaseAcitivty implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitleView.getLeftImg().setVisibility(View.GONE);
        mTitleView.initTopTitle("启动页");
        initBindView();
    }

    private void initBindView() {
        findViewById(R.id.btn_text).setOnClickListener(this);
        findViewById(R.id.btn_pieChart).setOnClickListener(this);
        findViewById(R.id.btn_mobile_coordinet).setOnClickListener(this);
        findViewById(R.id.btn_baseview).setOnClickListener(this);
        findViewById(R.id.btn_animalview).setOnClickListener(this);
        findViewById(R.id.btn_canvas).setOnClickListener(this);
        findViewById(R.id.btn_canvas_picture_text).setOnClickListener(this);
        findViewById(R.id.btn_edittext_nunshow).setOnClickListener(this);
        findViewById(R.id.btn_custorm_progress).setOnClickListener(this);
        findViewById(R.id.btn_canvas_path).setOnClickListener(this);
        findViewById(R.id.btn_canvas_beziar).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_text:
                startToActivity(TextViewActivity.class);
                break;
            case R.id.btn_pieChart:
                startToActivity(PieChartActivity.class);
                break;
            case R.id.btn_mobile_coordinet:
                startToActivity(MobileCoordinatesActivity.class);
                break;
            case R.id.btn_baseview:
                startToActivity(BaseViewActivity.class);
                break;
            case R.id.btn_animalview:
                startToActivity(AnimalCustormViewActivity.class);
                break;
            case R.id.btn_canvas:
                startToActivity(CanvasActivity.class);
                break;
            case R.id.btn_canvas_picture_text:
                startToActivity(DrawPictureActivity.class);
                break;
            case R.id.btn_edittext_nunshow:
                startToActivity(EditTextNumActivity.class);
                break;
            case R.id.btn_custorm_progress:
                startToActivity(CustormLoadingActivty.class);
                break;
            case R.id.btn_canvas_path:
                startToActivity(CanvasPathActivity.class);
                break;
            case R.id.btn_canvas_beziar:
                startToActivity(CanvasBeziarActivity.class);
                break;
            default:
                break;
        }
    }
}
