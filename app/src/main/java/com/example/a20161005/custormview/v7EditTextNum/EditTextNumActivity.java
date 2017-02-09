package com.example.a20161005.custormview.v7EditTextNum;

import android.os.Bundle;

import com.example.a20161005.custormview.BaseAcitivty;
import com.example.a20161005.custormview.R;

/**
 * Created by ML on 2017/1/14.
 */

public class EditTextNumActivity extends BaseAcitivty {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v7_edittext_numshow_view);
        mTitleView.initTopTitle("限定字数编辑器");

    }
}
