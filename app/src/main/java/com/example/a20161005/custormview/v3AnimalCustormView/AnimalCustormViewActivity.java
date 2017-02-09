package com.example.a20161005.custormview.v3AnimalCustormView;

import android.os.Bundle;

import com.example.a20161005.custormview.BaseAcitivty;
import com.example.a20161005.custormview.R;

/**
 * Created by ML on 2017/1/12.
 */

public class AnimalCustormViewActivity extends BaseAcitivty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v2_animal_view);
        mTitleView.initTopTitle("绘制动态图形");
    }
}
