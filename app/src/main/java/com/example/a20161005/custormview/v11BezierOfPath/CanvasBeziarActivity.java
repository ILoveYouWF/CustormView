package com.example.a20161005.custormview.v11BezierOfPath;

import android.widget.RadioGroup;

import com.example.a20161005.custormview.BaseMoreActivity;
import com.example.a20161005.custormview.R;
import com.example.xutil.LogUtil.L;

/**
 * Created by ML on 2017/3/14.
 */

public class CanvasBeziarActivity extends BaseMoreActivity {

    private static final String CANCAS_BEZIAR_PATH = "绘制贝尔曲线";
    private static final String CANCAS_SECOND_ORDER_BEZIAR_PATH = "绘制二阶贝尔曲线";
    private static final String CANCAS_THREE_ORDER_BEZIAR_PATH = "绘制三阶贝尔曲线";

    private RadioGroup mGroup;
    private CanvasThreeOrderBeziarView beziarView;

    @Override
    public void specialCase() {
        if (mTitleView.getTvTitle().getText().equals(CANCAS_THREE_ORDER_BEZIAR_PATH)) {
            initCheckBox();
            beziarView = (CanvasThreeOrderBeziarView) findViewById(R.id.beziarView);
        }
    }

    private void initCheckBox() {
        mGroup = (RadioGroup) ctx.findViewById(R.id.radioGroup);
        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.checkbox1) {
                    beziarView.setMode(true);
                } else if (checkedId == R.id.checkbox2) {
                    beziarView.setMode(false);
                }
            }
        });
    }

    @Override
    public void setDatas() {
        titles = new String[]{CANCAS_BEZIAR_PATH, CANCAS_SECOND_ORDER_BEZIAR_PATH, CANCAS_THREE_ORDER_BEZIAR_PATH};
        viewIds = new int[]{R.id.btn_one, R.id.btn_two};
        layouts = new int[]{R.layout.v11_draw_beziar_path_view, R.layout.v11_path_second_beziar_view, R.layout.v11_path_three_beziar_view};

    }
}
