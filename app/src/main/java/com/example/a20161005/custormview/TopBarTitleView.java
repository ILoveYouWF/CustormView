package com.example.a20161005.custormview;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a20161005.custormview.CommentUtil.StrUitl;

import static android.view.View.VISIBLE;

/**
 * Created by ML on 2017/1/12.
 */

public class TopBarTitleView {

    private BaseAcitivty ctx;
    private LayoutInflater mLayoutInflater;

    private TextView tvTitle;
    private ImageButton leftImg;
    private TextView tvRight;

    private LinearLayout rlView;

    public TopBarTitleView(BaseAcitivty ctx) {
        this.ctx = ctx;
    }

    public void initView() {
        tvTitle = (TextView) ctx.findViewById(R.id.tv_title);
        tvRight = (TextView) ctx.findViewById(R.id.tv_right_sure);
        leftImg = (ImageButton) ctx.findViewById(R.id.left_imageView);

    }

    public void setLinstener(View.OnClickListener listener) {
        if (listener != null) {
            leftImg.setOnClickListener(listener);

        }
    }

    public void initTopTitle(String topTitle) {
        this.initTopTitle(topTitle, null);
    }

    public void initTopTitle(String topTitle, String rightText) {

        if (StrUitl.isNotEmptyOrNull(topTitle)) {
            tvTitle.setText(topTitle);
            tvTitle.setVisibility(VISIBLE);
        }

        if (StrUitl.isNotEmptyOrNull(rightText)) {
            tvRight.setText(rightText);
            tvTitle.setVisibility(VISIBLE);
        }

    }

    public ImageButton getLeftImg() {
        return leftImg;
    }

    public void setLeftImg(ImageButton leftImg) {
        this.leftImg = leftImg;
    }

    public TextView getTvRight() {
        return tvRight;
    }

    public void setTvRight(TextView tvRight) {
        this.tvRight = tvRight;
    }
}
