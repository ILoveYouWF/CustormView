package com.example.a20161005.custormview.v5Canvas;

import android.os.Bundle;

import com.example.a20161005.custormview.BaseMoreActivity;
import com.example.a20161005.custormview.R;

/**
 * Created by ML on 2017/1/13.
 */

public class CanvasActivity extends BaseMoreActivity {

    private static final String CANVAS_CANDO = "画布相关操作";
    private static final String TRANSLATE = "画布位移";
    private static final String SCALE = "画布缩放";
    private static final String ROTATE = "画布旋转";
    private static final String SKEW = "画布错切";
    private static final String SAVE_RESTORE = "画布快照和回滚";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setDatas() {
        titles = new String[]{CANVAS_CANDO, TRANSLATE, SCALE, ROTATE, SKEW, SAVE_RESTORE};
        layouts = new int[]{R.layout.v5_canvas_view, R.layout.canvas_traslate_view, R.layout.canvas_scale_view,
                R.layout.canvas_rotate_view, R.layout.canvas_skew_view, R.layout.canvas_save_restore_view};
        viewIds = new int[]{R.id.btn_canvas_translate, R.id.btn_canvas_scale, R.id.btn_canvas_rotate, R.id.btn_canvas_skew, R.id.btn_canvas_save_restore};
    }
}
