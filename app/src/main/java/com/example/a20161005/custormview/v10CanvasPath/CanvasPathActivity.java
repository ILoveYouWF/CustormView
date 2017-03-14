package com.example.a20161005.custormview.v10CanvasPath;

import com.example.a20161005.custormview.BaseMoreActivity;
import com.example.a20161005.custormview.R;

/**
 * Created by ML on 2017/3/6.
 */

public class CanvasPathActivity extends BaseMoreActivity {
    public static final String PATH_TITME = "绘制Path";
    public static final String PATH_BASE_METHOD = "Path基础方法";
    public static final String PATH_ADD_SHAPES = "Path添加基础图形相关方法";
    public static final String PATH_ADD_BOOLEAN = "Path中判断相关方法";
    public static final String PATH_RADAR_MAP = "绘制雷达图";

    @Override
    public void specialCase() {

    }

    @Override
    public void setDatas() {
        titles = new String[]{PATH_TITME, PATH_BASE_METHOD, PATH_ADD_SHAPES, PATH_ADD_BOOLEAN,PATH_RADAR_MAP};
        layouts = new int[]{R.layout.v10_draw_path_view, R.layout.v10_path_base_method_view, R.layout.v10_path_add_shapes_view, R.layout.v10_path_boolean_view, R.layout.v10_path_radar_map_view};
        viewIds = new int[]{R.id.btn_one, R.id.btn_two, R.id.btn_three, R.id.btn_four};
    }
}