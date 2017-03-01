package com.example.a20161005.custormview.UiUtils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by ML on 2017/2/17.
 */

public class UiUitls {

    /**
     * DisplayMetrics
     * <p>
     * public int widthPixels : 设备的绝对宽度，单位是px
     * <p>
     * <p>
     * public int heightPixels : 设备的绝对高度，单位是px
     * <p>
     * <p>
     * public float density : 屏幕密度
     * <p>
     * <p>
     * public int densityDpi : 单位尺寸的像素点
     * <p>
     * <p>
     * public float scaledDensity : 字体显示的缩放因子
     * <p>
     * <p>
     * public float xdpi : 水平方向的dpi
     * <p>
     * <p>
     * public float ydpi : 竖直方向的dpi
     * <p>
     */
    public static int dipToPx(Context context, int dip) {

        return (int) (dip * getSreenDensity(context) + 0.5F);

    }

    static public int getScreenWidthPixels(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getMetrics(dm);
        return dm.widthPixels;
    }

    private static float getSreenDensity(Context context) {

        try {
            DisplayMetrics dm = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
            return dm.density;
        } catch (Exception e) {
            return DisplayMetrics.DENSITY_DEFAULT;
        }
    }

}
