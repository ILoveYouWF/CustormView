package com.example.a20161005.custormview.v9CustormDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.a20161005.custormview.R;
import com.example.a20161005.custormview.v8GAStudioLeafLoadingDemo.CanvasLoadingView;

import java.util.Random;

/**
 * Created by ML on 2017/3/2.
 */

public class CustormLoadingDialog extends Dialog {

    private Context mContext;
    private LayoutInflater mInflater;
    private CanvasLoadingView loadView;
    private int progress = 0;

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

    public CustormLoadingDialog(Context context) {
        super(context);
        mContext = context;
    }

    public CustormLoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        initView();
    }


    public void initView() {
        View view = mInflater.inflate(R.layout.v8_wheel_dialog_view, null);
        setContentView(view);
        loadView = (CanvasLoadingView) view.findViewById(R.id.canvasRotateWheel);
        loadView.setLoading(new CanvasLoadingView.overLoading() {
            @Override
            public void over() {
                dismiss();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadView.setbIsCreateMoreLeaf(true);
                mHandler.sendEmptyMessageDelayed(progress, 3000);
            }
        }, 5000);
        //设置对话框的大小
        WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Window window = getWindow();
        this.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams params = window.getAttributes();
        Display display = manager.getDefaultDisplay();
        window.setGravity(Gravity.CENTER);
        params.width = (int) (display.getWidth() * 0.8);
        params.height = (int) (display.getHeight() * 0.4);
        window.setAttributes(params);

    }
}
