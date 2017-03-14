package com.example.a20161005.custormview.v6DrawPictureAndText;

import android.os.Bundle;

import com.example.a20161005.custormview.BaseMoreActivity;
import com.example.a20161005.custormview.R;

/**
 * Created by ML on 2017/1/14.
 */

public class DrawPictureActivity extends BaseMoreActivity {

    private static final String CANVAS_PICTURE_BITMAP_TEXT = "Canvas之图片文字操作";
    private static final String CANVAS_PICTURE = "绘制Picture";
    private static final String CANVAS_BITMAP = "绘制位图";
    private static final String CANVAS_TEXT = "绘制文字";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void specialCase() {

    }

    @Override
    public void setDatas() {
        titles = new String[]{CANVAS_PICTURE_BITMAP_TEXT, CANVAS_PICTURE, CANVAS_BITMAP, CANVAS_TEXT};
        layouts = new int[]{R.layout.v6_canvas_picture_text_bitmap, R.layout.v6_draw_picture_view, R.layout.v6_draw_bitmap_view, R.layout.v6_draw_text_view};
        viewIds = new int[]{R.id.btn_canvas_picture, R.id.btn_canvas_bitmap, R.id.btn_canvas_text};
    }
}
