package com.example.a20161005.custormview.v2BaseCustormView;

import android.os.Bundle;

import com.example.a20161005.custormview.BaseAcitivty;
import com.example.a20161005.custormview.R;

/**
 * Created by ML on 2017/1/11.
 */

public class BaseViewActivity extends BaseAcitivty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v1_base_view);
        mTitleView.initTopTitle("绘制基础图形");
    }
}
