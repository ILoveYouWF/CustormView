package com.example.a20161005.custormview.v5Canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.example.a20161005.custormview.R;

/**
 * Created by ML on 2017/1/13.
 * 画布的状态保存和回滚
 * 常用方法：
 * save 	把当前的画布的状态进行保存，然后放入特定的栈中
 * saveLayerXxx 	新建一个图层，并放入特定的栈中
 * restore 	把栈中最顶层的画布状态取出来，并按照这个状态恢复当前的画布
 * restoreToCount 	弹出指定位置及其以上所有的状态，并按照指定位置的状态进行恢复
 * getSaveCount 	获取栈中内容的数量(即保存次数)
 */

public class CanvasSaveRestoreView extends BaseCanvasView {

    public CanvasSaveRestoreView(Context context) {
        super(context);
    }

    public CanvasSaveRestoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 这个栈可以存储画布状态和图层状态。
     * Q：什么是画布和图层？
     * A：实际上我们看到的画布是由多个图层构成的，如下图(图片来自网络)：
     * 实际上我们之前讲解的绘制操作和画布操作都是在默认图层上进行的。
     * 在通常情况下，使用默认图层就可满足需求，但是如果需要绘制比较复杂的内容，
     * 如地图(地图可以有多个地图层叠加而成，比如：政区层，道路层，兴趣点层)等，则分图层绘制比较好一些。
     * 你可以把这些图层看做是一层一层的玻璃板，你在每层的玻璃板上绘制内容，
     * 然后把这些玻璃板叠在一起看就是最终效果
     */

    /**
     * save
     * 保存全部状态
     * public int save ()
     * <p>
     * 根据saveFlags参数保存一部分状态
     * public int save (int saveFlags)
     * 可以看到第二种方法比第一种多了一个saveFlags参数，使用这个参数可以只保存一部分状态，更加灵活，这个saveFlags参数具体可参考上面表格中的内容。
     * <p>
     * 每调用一次save方法，都会在栈顶添加一条状态信息，以上面状态栈图片为例，再调用一次save则会在第5次上面载添加一条状态。
     * <p>
     * 数据类型 	名称 	简介
     * int 	ALL_SAVE_FLAG 	默认，保存全部状态
     * int 	CLIP_SAVE_FLAG 	保存剪辑区
     * int 	CLIP_TO_LAYER_SAVE_FLAG 	剪裁区作为图层保存
     * int 	FULL_COLOR_LAYER_SAVE_FLAG 	保存图层的全部色彩通道
     * int 	HAS_ALPHA_LAYER_SAVE_FLAG 	保存图层的alpha(不透明度)通道
     * int 	MATRIX_SAVE_FLAG 	保存Matrix信息(translate, rotate, scale, skew)
     */

    /**
     * saveLayerXxx
     * // 无图层alpha(不透明度)通道
     * public int saveLayer (RectF bounds, Paint paint)
     * public int saveLayer (RectF bounds, Paint paint, int saveFlags)
     * public int saveLayer (float left, float top, float right, float bottom, Paint paint)
     * public int saveLayer (float left, float top, float right, float bottom, Paint paint, int saveFlags)
     * <p>
     * // 有图层alpha(不透明度)通道
     * public int saveLayerAlpha (RectF bounds, int alpha)
     * public int saveLayerAlpha (RectF bounds, int alpha, int saveFlags)
     * public int saveLayerAlpha (float left, float top, float right, float bottom, int alpha)
     * public int saveLayerAlpha (float left, float top, float right, float bottom, int alpha, int saveFlags)
     * <p>
     * 注意：saveLayerXxx方法会让你花费更多的时间去渲染图像(图层多了相互之间叠加会导致计算量成倍增长)，使用前请谨慎，如果可能，尽量避免使用。
     * <p>
     * 使用saveLayerXxx方法，也会将图层状态也放入状态栈中，同样使用restore方法进行恢复。
     */

    /**
     * restore
     * <p>
     * 状态回滚，就是从栈顶取出一个状态然后根据内容进行恢复。
     * <p>
     * 同样以上面状态栈图片为例，调用一次restore方法则将状态栈中第5次取出，根据里面保存的状态进行状态恢复。
     * restoreToCount
     * <p>
     * 弹出指定位置以及以上所有状态，并根据指定位置状态进行恢复。
     * <p>
     * 以上面状态栈图片为例，如果调用restoreToCount(2) 则会弹出 2 3 4 5 的状态，并根据第2次保存的状态进行恢复。
     * getSaveCount
     * <p>
     * 获取保存的次数，即状态栈中保存状态的数量，以上面状态栈图片为例，使用该函数的返回值为5。
     * <p>
     * 不过请注意，该函数的最小返回值为1，即使弹出了所有的状态，返回值依旧为1，代表默认状态。
     */

    /**
     * 常用格式
     * <p>
     * 虽然关于状态的保存和回滚啰嗦了不少，不过大多数情况下只需要记住下面的步骤就可以了：
     * <p>
     * save();      //保存状态
     * ...          //具体操作
     * restore();   //回滚到之前的状态
     * <p>
     * 这种方式也是最简单和最容易理解的使用方法。
     */

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.Yellow));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.b);
        canvas.drawBitmap(bitmap, -bitmap.getWidth() - 30, -bitmap.getHeight() / 2, mPaint);
        canvas.drawBitmap(bitmap2, 30, -bitmap.getHeight() / 2, mPaint);
    }
}
