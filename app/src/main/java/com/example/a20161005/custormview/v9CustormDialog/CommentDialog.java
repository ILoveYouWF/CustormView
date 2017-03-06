package com.example.a20161005.custormview.v9CustormDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.xutil.ConvertUtils;

/**
 * Created by ML on 2017/3/2.
 * 简单的自定义dialog
 */

public class CommentDialog extends Dialog {

    private int height, width;
    private boolean cancelTouchout;
    private View mView;

    private CommentDialog(Builder builder) {
        super(builder.mContext);
        initView(builder);
    }

    private CommentDialog(Builder builder, int resStyle) {
        super(builder.mContext, resStyle);
        initView(builder);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mView);
        setCanceledOnTouchOutside(cancelTouchout);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        params.width = width;
        params.height = height;
        window.setAttributes(params);
    }

    public void initView(Builder builder) {
        this.height = builder.height;
        this.width = builder.width;
        this.cancelTouchout = builder.cancelTouchout;
        this.mView = builder.mView;
    }


    static class Builder {
        private Context mContext;
        private int height, width;
        private boolean cancelTouchout;
        private View mView;
        private int resStyle = -1;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setHeightPx(int height) {
            this.height = height;
            return this;
        }

        public Builder setWidthPx(int width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(int height) {
            this.height = ConvertUtils.dip2px(mContext, height);
            return this;
        }

        public Builder setWidth(int width) {
            this.width = ConvertUtils.dip2px(mContext, width);
            return this;
        }

        public Builder setHeightDimenRes(int height) {
            this.height = mContext.getResources().getDimensionPixelOffset(height);
            return this;
        }

        public Builder setWidthDimenRes(int width) {
            this.height = mContext.getResources().getDimensionPixelOffset(width);
            return this;
        }

        public Builder setCancelTouchout(boolean cancelTouchout) {
            this.cancelTouchout = cancelTouchout;
            return this;
        }

        public Builder setResStyle(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        public Builder setViewID(int resView) {
            mView = LayoutInflater.from(mContext).inflate(resView, null);
            return this;
        }

        public Builder setView(View view) {
            mView = view;
            return this;
        }


        public Builder addViewOnClick(int viewRes, View.OnClickListener onClickListener) {
            mView.findViewById(viewRes).setOnClickListener(onClickListener);
            return this;
        }

        public CommentDialog build() {
            if (resStyle != -1) {
                return new CommentDialog(this, resStyle);
            } else {
                return new CommentDialog(this);
            }
        }
    }

}
