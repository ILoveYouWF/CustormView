package com.example.a20161005.custormview;

import android.os.Bundle;
import android.view.View;

import com.example.a20161005.custormview.CommentUtil.GlobalConstant;
import com.example.xutil.LogUtil.L;
import com.example.xutil.StrUtils;

/**
 * Created by ML on 2017/1/13.
 */

public abstract class BaseMoreActivity extends BaseAcitivty {


    protected String[] titles;
    protected int[] layouts;
    protected int[] viewIds;
    protected BaseMoreActivity ctx;

    private String type = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        setDatas();
        initView();
        specialCase();
    }

    public abstract void specialCase();

    private void initView() {
        type = getIntent().getStringExtra(GlobalConstant.KEY_CONSTANT_VALUE);
        for (int i = 0; i < layouts.length; i++) {

            if (StrUtils.noEmptyOrNull(type)) {
                if (type.equals(titles[i])) {
                    ctx.setContentView(layouts[i]);
                    ctx.mTitleView.initTopTitle(type);
                    break;
                }
            } else {
                ctx.setContentView(layouts[0]);
                ctx.mTitleView.initTopTitle(titles[0]);
                initBindView();
                break;
            }
        }

    }

    public abstract void setDatas();


    private void initBindView() {
        for (int i = 0; i < viewIds.length; i++) {
            ctx.findViewById(viewIds[i]).setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        for (int i = 0; i < viewIds.length; i++) {
            if (v.getId() == viewIds[i]) {
                if (i + 1 >= titles.length) {
                    L.e("出现数组越界");
                    return;
                }
                ctx.startToActivity(ctx.getClass(), titles[i + 1]);
                break;
            }
        }
    }
}
