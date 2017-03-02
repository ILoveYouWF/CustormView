package com.example.a20161005.custormview.v8GAStudioLeafLoadingDemo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.a20161005.custormview.R;
import com.example.a20161005.custormview.UiUtils.UiUitls;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by ML on 2017/1/20.
 */

public class CanvasLoadingView extends View {
    private Paint mBitmapPaint, mWhitePaint, mOrangePaint, mWheelBitmapPaint;
    // 淡白色
    private static final int WHITE_COLOR = 0xfffde399;
    // 橙色
    private static final int ORANGE_COLOR = 0xffffa800;
    // 用于控制绘制的进度条距离左／上／下的距离
    private static final int LEFT_MARGIN = 9;
    // 用于控制绘制的进度条距离右的距离
    private static final int RIGHT_MARGIN = 25;

    private int mTotalWidth;
    private int mTotalHeight;
    private int mLeftMargin;
    private int mRightMargin;
    // 所绘制的进度条部分的宽度
    private int mProgressWidth;
    // 弧形的半径
    private int mArcRadius;

    private Bitmap mOuterBitmap, mWheelBitmap;
    private int mOuterWidth, mOuterHight, mWheelWidth, mWheelHight;
    private RectF mWhiteRectF, mOrangeRectF, mArcRectF;

    private Rect mOuterSrcRect, mOuterDestRect, mWheelSrcRect, mWheelDestRect;

    // 当前进度
    private int mProgress;

    private float mArcRightLocation;

    // 当前所在的绘制的进度条的位置
    private int mCurrentProgressPosition;
    private Resources mResources;


    private Bitmap mLeafBitmap;
    private int mLeafWidth, mLeafHeight;
    // 总进度
    private static final int TOTAL_PROGRESS = 100;
    // 叶子飘动一个周期所花的时间
    private static final long LEAF_FLOAT_TIME = 3000;
    // 叶子旋转一周需要的时间
    private static final long LEAF_ROTATE_TIME = 2000;

    // 叶子飘动一个周期所花的时间
    private long mLeafFloatTime = LEAF_FLOAT_TIME;
    // 叶子旋转一周需要的时间
    private long mLeafRotateTime = LEAF_ROTATE_TIME;
    // 用于控制随机增加的时间不抱团
    private int mAddTime;

    private LeafFactory mLeafFactory;

    private List<Leaf> mLeafInfos;


    // 中等振幅大小
    private static final int MIDDLE_AMPLITUDE = 20;
    // 不同类型之间的振幅差距
    private static final int AMPLITUDE_DISPARITY = 5;

    private int mMiddleAmplitude = MIDDLE_AMPLITUDE;
    // 振幅差
    private int mAmplitudeDisparity = AMPLITUDE_DISPARITY;

    private boolean bIsCreateMoreLeaf = false;

    public CanvasLoadingView(Context context) {
        super(context);
    }

    public CanvasLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mResources = getResources();
        mLeftMargin = UiUitls.dipToPx(context, LEFT_MARGIN);
        mRightMargin = UiUitls.dipToPx(context, RIGHT_MARGIN);
        mLeafFloatTime = LEAF_FLOAT_TIME;
        mLeafRotateTime = LEAF_ROTATE_TIME;
        initBitmap();
        initPaint();
        mLeafFactory = new LeafFactory();
    }

    private void initPaint() {
        mBitmapPaint = new Paint();
        mBitmapPaint.setFilterBitmap(true);
        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setDither(true);

        mWheelBitmapPaint = new Paint();
        mWheelBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setFilterBitmap(true);
        mBitmapPaint.setDither(true);

        mWhitePaint = new Paint();
        mWhitePaint.setAntiAlias(true);
        mWhitePaint.setColor(WHITE_COLOR);

        mOrangePaint = new Paint();
        mOrangePaint.setAntiAlias(true);
        mOrangePaint.setColor(ORANGE_COLOR);

    }

    private void initBitmap() {
        mLeafBitmap = ((BitmapDrawable) mResources.getDrawable(R.drawable.leaf)).getBitmap();
        mLeafWidth = mLeafBitmap.getWidth();
        mLeafHeight = mLeafBitmap.getHeight();
        mOuterBitmap = ((BitmapDrawable) mResources.getDrawable(R.drawable.leaf_kuang)).getBitmap();
        mOuterHight = mOuterBitmap.getHeight();
        mOuterWidth = mOuterBitmap.getWidth();
        //        mWheelBitmap = ((BitmapDrawable) mResources.getDrawable(R.drawable.fengshan)).getBitmap();
        //        mWheelWidth = mWheelBitmap.getWidth();
        //        mWheelHight = mWheelBitmap.getHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;
        mProgressWidth = mTotalWidth - mLeftMargin - mRightMargin;
        mArcRadius = (mTotalHeight - 2 * mLeftMargin) / 2;
        mOuterSrcRect = new Rect(0, 0, mOuterWidth, mOuterHight);
        mOuterDestRect = new Rect(0, 0, mTotalWidth, mTotalHeight);
        //        mWheelSrcRect = new Rect(0, 0, mWheelWidth, mWheelHight);
        //        mWheelDestRect = new Rect(mTotalWidth - mWheelWidth - UiUitls.dipToPx(getContext(), 5), 0 + UiUitls.dipToPx(getContext(), 5), mTotalWidth - UiUitls.dipToPx(getContext(), 7), mTotalHeight - UiUitls.dipToPx(getContext(), 5));
        //绘制白色进度条矩形的矩形
        mWhiteRectF = new RectF(mLeftMargin + mCurrentProgressPosition, mLeftMargin, mTotalWidth - mRightMargin, mTotalHeight - mLeftMargin);
        //绘制橘色进度条矩形的矩形
        mOrangeRectF = new RectF(mLeftMargin + mArcRadius, mLeftMargin, mCurrentProgressPosition, mTotalHeight - mLeftMargin);
        //绘制椭圆的矩形
        mArcRectF = new RectF(mLeftMargin, mLeftMargin, mLeftMargin + 2 * mArcRadius, mTotalHeight - mLeftMargin);
        mArcRightLocation = mLeftMargin + mArcRadius;
    }

    private boolean onlyOne = true;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!bIsCreateMoreLeaf) {
            drawWaitingView(canvas);
        } else if (bIsCreateMoreLeaf) {
            if (onlyOne) {
                mLeafInfos = mLeafFactory.generateLeafs();
            }
            onlyOne = false;
            drawView(canvas);
        }
        if (mProgress < TOTAL_PROGRESS) {
            postInvalidate();
        }
    }

    private void drawWaitingView(Canvas canvas) {
        canvas.drawArc(mArcRectF, 90, 180, false, mWhitePaint);
        mWhiteRectF.left = mLeftMargin + mArcRadius;
        canvas.drawRect(mWhiteRectF, mWhitePaint);
        //        drawLeafs(canvas);
        canvas.drawBitmap(mOuterBitmap, mOuterSrcRect, mOuterDestRect, mBitmapPaint);

    }

    public void drawView(Canvas canvas) {
        drawProgressAndLeafs(canvas);
        canvas.drawBitmap(mOuterBitmap, mOuterSrcRect, mOuterDestRect, mBitmapPaint);
    }

    public interface overLoading {
        void over();
    }

    public overLoading mLoading;

    public void setLoading(overLoading loading) {
        mLoading = loading;
    }

    public void drawProgressAndLeafs(Canvas canvas) {

        if (mProgress >= TOTAL_PROGRESS) {
            mWhiteRectF.left = mProgressWidth;
            canvas.drawRect(mWhiteRectF, mWhitePaint);
            canvas.drawArc(mArcRectF, 90, 180, false, mOrangePaint);
            mOrangeRectF.left = mArcRightLocation;
            mOrangeRectF.right = mProgressWidth;
            canvas.drawRect(mOrangeRectF, mOrangePaint);
            if (mLoading != null) {
                mLoading.over();
            }
            return;
        }
        mCurrentProgressPosition = mProgressWidth * mProgress / TOTAL_PROGRESS;
        if (mCurrentProgressPosition < mArcRadius) {
            canvas.drawArc(mArcRectF, 90, 180, false, mWhitePaint);
            mWhiteRectF.left = mArcRightLocation;
            canvas.drawRect(mWhiteRectF, mWhitePaint);
            drawLeafs(canvas);
            int angle = (int) Math.toDegrees(Math.acos((mArcRadius - mCurrentProgressPosition) / (float) mArcRadius));
            int startAngle = 180 - angle;
            int sweepAngle = 2 * angle;
            //            L.e("startAngle = " + startAngle);
            canvas.drawArc(mArcRectF, startAngle, sweepAngle, false, mOrangePaint);
        } else {
            mWhiteRectF.left = mCurrentProgressPosition;
            canvas.drawRect(mWhiteRectF, mWhitePaint);
            drawLeafs(canvas);
            canvas.drawArc(mArcRectF, 90, 180, false, mOrangePaint);
            mOrangeRectF.left = mArcRightLocation;
            mOrangeRectF.right = mCurrentProgressPosition;
            canvas.drawRect(mOrangeRectF, mOrangePaint);
        }

    }

    public void drawLeafs(Canvas canvas) {
        mLeafRotateTime = mLeafRotateTime <= 0 ? LEAF_ROTATE_TIME : mLeafRotateTime;
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < mLeafInfos.size(); i++) {
            Leaf leaf = mLeafInfos.get(i);
            if (currentTime > leaf.startTime && leaf.startTime != 0) {
                getLeafLocation(leaf, currentTime);
                canvas.save();
                Matrix matrix = new Matrix();
                float transX = mLeftMargin + leaf.x;
                float transY = mLeftMargin + leaf.y;
                matrix.postTranslate(transX, transY);
                float rotateFration = ((currentTime - leaf.startTime)) % mLeafRotateTime / (float) mLeafRotateTime;
                int angle = (int) (rotateFration * 360);
                int rotate = leaf.rotateDirection == 0 ? angle + leaf.rotateAngle : -angle + leaf.rotateAngle;
                matrix.postRotate(rotate, transX + mLeafWidth / 2, transY + mLeafHeight / 2);
                canvas.drawBitmap(mLeafBitmap, matrix, mBitmapPaint);
                canvas.restore();
            } else {
                continue;
            }
        }
    }

    private void getLeafLocation(Leaf leaf, long currentTime) {
        long intervalTime = currentTime - leaf.startTime;
        mLeafFloatTime = mLeafFloatTime <= 0 ? LEAF_FLOAT_TIME : mLeafFloatTime;
        if (intervalTime < 0) {
            return;
        } else if (intervalTime > mLeafFloatTime) {
            leaf.startTime = System.currentTimeMillis() + new Random().nextInt((int) mLeafFloatTime);
        }
        float fraction = (float) intervalTime / mLeafFloatTime;
        leaf.x = (int) (mProgressWidth - mProgressWidth * fraction);
        leaf.y = getLocationY(leaf);
    }

    private int getLocationY(Leaf leaf) {
        // y = A(wx+Q)+h
        float w = (float) ((float) 2 * Math.PI / mProgressWidth);
        float a = mMiddleAmplitude;

        switch (leaf.type) {
            case LITTLE:
                a = mMiddleAmplitude - mAmplitudeDisparity;
                break;
            case MIDDLE:
                a = mMiddleAmplitude;
                break;
            case BIG:
                a = mMiddleAmplitude + mAmplitudeDisparity;
                break;
            default:
                break;
        }
        return (int) (a * Math.sin(w * leaf.x)) + mArcRadius * 2 / 3;
    }

    private enum StartType {
        LITTLE, MIDDLE, BIG
    }

    public class Leaf {
        private float x, y;
        private StartType type;
        private int rotateAngle;
        // 旋转方向--0代表顺时针，1代表逆时针
        private int rotateDirection;
        private long startTime;
    }

    /**
     * 叶子工厂
     **/
    private class LeafFactory {
        private static final int MAX_LEAFS_LOADING = 8;
        private boolean bIsFirst = true;  //第一个叶子不需要延迟
        Random random = new Random();

        /**
         * 生产叶子
         *
         * @return
         */
        public Leaf createLeafs() {
            Leaf leaf = new Leaf();
            int leafType = random.nextInt(3);
            StartType startType = StartType.MIDDLE;
            switch (leafType) {
                case 0:
                    startType = StartType.LITTLE;
                    break;
                case 1:
                    startType = StartType.MIDDLE;
                    break;
                case 2:
                    startType = StartType.BIG;
                    break;
                default:
                    break;
            }
            leaf.type = startType;
            leaf.rotateAngle = random.nextInt(360);
            leaf.rotateDirection = random.nextInt(2);
            mLeafFloatTime = mLeafFloatTime <= 0 ? LEAF_FLOAT_TIME : mLeafFloatTime;
            cofigLeafStartTimeFromLoading(leaf);
            return leaf;
        }

        private void cofigLeafStartTimeFromLoading(Leaf leaf) {
            mAddTime += random.nextInt((int) mLeafFloatTime - 1500);
            leaf.startTime = System.currentTimeMillis() + (bIsFirst ? 0 : mAddTime);
        }

        public List<Leaf> generateLeafs() {
            return generateLeafs(MAX_LEAFS_LOADING);
        }

        public List<Leaf> generateLeafs(int leafSize) {
            List<Leaf> leafs = new LinkedList<Leaf>();
            for (int i = 0; i < leafSize; i++) {
                leafs.add(createLeafs());
                bIsFirst = false;
            }
            return leafs;
        }
    }

    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int progress) {
        mProgress = progress;
        postInvalidate();
    }

    public void setbIsCreateMoreLeaf(boolean bIsCreateMoreLeaf) {
        this.bIsCreateMoreLeaf = bIsCreateMoreLeaf;
    }
}
