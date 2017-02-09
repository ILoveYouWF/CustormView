package com.example.a20161005.custormview.v1MobileCoordinates;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.a20161005.custormview.BaseAcitivty;
import com.example.a20161005.custormview.R;

/**
 * Created by ML on 2017/1/9.
 */

public class MobileCoordinatesActivity extends BaseAcitivty {
    private MobileCoordinatesActivity ctx;

    private TextView tvMobileWidth;
    private TextView tvMobileHeight;
    private TextView viewLeftWidth;
    private TextView viewLeftHeight;
    private TextView viewRightWidth;
    private TextView viewRightHeight;
    private TextView tvMotionWidth;
    private TextView tvMotionHeight;
    private TextView tvViewWidth;
    private TextView tvViewHeight;
    private TextView tvView;
    private RelativeLayout rlAll;

    private WindowManager mManager;

    private ScrollView slAll;


    private float mobileWidth;
    private float mobileHight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobile_coordinetes);
        initView();
        initData();
        initListener();

    }

    private void initListener() {
        tvView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("MotionEvent.ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("MotionEvent.ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("MotionEvent.ACTION_UP");
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        System.out.println("MotionEvent.ACTION_CANCEL");
                        break;
                    default:
                        break;
                }
                tvMotionWidth.setText(event.getX() + "/" + event.getRawX());
                tvMotionHeight.setText(event.getY() + "/" + event.getRawY());
                //TODO 返回ture表示触摸时间继续想子View传递自己不做处理，返回false表示拦截触摸事件自己做处理
                return false;
            }
        });
    }

    private void initData() {
        mobileWidth = mManager.getDefaultDisplay().getWidth();
        mobileHight = mManager.getDefaultDisplay().getHeight();
        tvMobileWidth.setText(mobileWidth + "");
        tvMobileHeight.setText(mobileHight + "");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //需要进行一定的延迟，不然视图并没有绘制完成则计算出的结果有误
                tvViewWidth.setText(tvView.getWidth() + "");
                tvViewHeight.setText(tvView.getHeight() + "");
                viewLeftWidth.setText(tvView.getLeft() + "");
                viewLeftHeight.setText(tvView.getTop() + "");
                viewRightWidth.setText(tvView.getRight() + "");
                viewRightHeight.setText(tvView.getBottom() + "");
            }
        }, 500);
    }


    private void initView() {
        ctx = this;
        mTitleView.initTopTitle("手机坐标系练习");
        mManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        tvMobileWidth = (TextView) findViewById(R.id.tv_mobile_width);
        tvMobileHeight = (TextView) findViewById(R.id.tv_mobile_height);
        viewLeftWidth = (TextView) findViewById(R.id.view_left_width);
        viewLeftHeight = (TextView) findViewById(R.id.view_left_height);
        viewRightWidth = (TextView) findViewById(R.id.view_right_width);
        viewRightHeight = (TextView) findViewById(R.id.view_right_height);
        tvMotionWidth = (TextView) findViewById(R.id.tv_motion_width);
        tvMotionHeight = (TextView) findViewById(R.id.tv_motion_height);
        tvViewWidth = (TextView) findViewById(R.id.tv_view_width);
        tvViewHeight = (TextView) findViewById(R.id.tv_view_height);
        slAll = (ScrollView) findViewById(R.id.sl_all);
        tvView = (TextView) findViewById(R.id.tv_view);
        rlAll = (RelativeLayout) findViewById(R.id.rl_all);
    }
}
