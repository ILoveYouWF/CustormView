package com.example.a20161005.custormview.v8GAStudioLeafLoadingDemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.a20161005.custormview.BaseAcitivty;
import com.example.a20161005.custormview.R;
import com.example.a20161005.custormview.v9CustormDialog.CustormLoadingDialog;

import java.util.Random;

/**
 * Created by ML on 2017/1/20.
 */

public class CustormLoadingActivty extends BaseAcitivty {

    private int progress = 0;
    private CanvasLoadingView loadView;
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (progress < 100) {
                progress += 1;
                loadView.setProgress(progress);
                mHandler.sendEmptyMessageDelayed(progress, (long) new Random().nextInt(1000));
            }
        }
    };

    public void startDialog(View view) {
        CustormLoadingDialog dialog = new CustormLoadingDialog(this, R.style.custorm_loading_dialog);
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v8_wheel_view);
        loadView = (CanvasLoadingView) findViewById(R.id.canvasRotateWheel);
        loadView.setLoading(new CanvasLoadingView.overLoading() {
            @Override
            public void over() {
                //TODO  加载完成会回调接口
            }
        });
        /**
         * 延迟五秒在执行，其实就是模仿一下等在网络的过程！
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadView.setbIsCreateMoreLeaf(true);
                mHandler.sendEmptyMessageDelayed(progress, 3000);
            }
        }, 5000);
    }
}
