package com.example.a20161005.custormview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

import com.example.a20161005.custormview.CommentUtil.GlobalConstant;
import com.example.a20161005.custormview.CommentUtil.StrUitl;

/**
 * Created by ML on 2017/1/9.
 */

public class BaseAcitivty extends FragmentActivity implements View.OnClickListener {

    private BaseAcitivty ctx;

    protected TopBarTitleView mTitleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;

    }

    public void loadView() {
        mTitleView = new TopBarTitleView(ctx);
        mTitleView.initView();
        mTitleView.setLinstener(ctx);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        loadView();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        loadView();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        loadView();
    }

    protected void startToActivity(Class<?> cls) {
        Intent intent = new Intent(ctx, cls);
        startActivity(intent);
    }

    protected void startToActivity(Class<?> cls, String type) {
        Intent intent = new Intent(ctx, cls);
        if (StrUitl.isNotEmptyOrNull(type)) {
            intent.putExtra(GlobalConstant.KEY_CONSTANT_VALUE, type);
        }
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        if (v == mTitleView.getLeftImg()) {
            ctx.finish();
        }
    }
}
