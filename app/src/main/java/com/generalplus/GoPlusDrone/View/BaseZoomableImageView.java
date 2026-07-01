package com.generalplus.GoPlusDrone.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import androidx.viewpager.widget.ViewPager;
import com.generalplus.GoPlusDrone.Fragment.BitmapUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseZoomableImageView extends View {
    private static final float MAX_IMAGE_RATIO_LARGE = 5.0f;
    private static final float MAX_IMAGE_RATIO_WIDTH_LARGE_LANDSCAPE = 2.0f;
    public static final int MIN_SDK_ENABLE_LAYER_TYPE_HARDWARE = 14;
    static final int sAnimationDelay = 500;
    static final int sPaintDelay = 250;
    static final float sPanRate = 7.0f;
    static final float sScaleRate = 1.25f;
    private boolean adjustLongImageEnable;
    private boolean fling;
    private boolean landscape;
    private Matrix mBaseMatrix;
    protected Bitmap mBitmap;
    private Matrix mDisplayMatrix;
    private Runnable mFling;
    protected ImageGestureListener mImageGestureListener;
    private double mLastDraw;
    private Matrix mMatrix;
    private float[] mMatrixValues;
    private float mMaxZoom;
    private Runnable mOnLayoutRunnable;
    private Paint mPaint;
    private Runnable mRefresh;
    private Matrix mSuppMatrix;
    private int mThisHeight;
    private int mThisWidth;
    protected ViewPager mViewPager;

    /* JADX INFO: Access modifiers changed from: private */
    public float easeOut(float f, float f2, float f3, float f4) {
        float f5 = (f / f4) - 1.0f;
        return (f3 * ((f5 * f5 * f5) + 1.0f)) + f2;
    }

    protected void onScrollFinish() {
    }

    protected Rect updateSelection() {
        return null;
    }

    public BaseZoomableImageView(Context context) {
        super(context);
        this.mBaseMatrix = new Matrix();
        this.mSuppMatrix = new Matrix();
        this.mDisplayMatrix = new Matrix();
        this.mMatrix = new Matrix();
        this.mMatrixValues = new float[9];
        this.mThisWidth = -1;
        this.mThisHeight = -1;
        this.mOnLayoutRunnable = null;
        this.mRefresh = null;
        this.mLastDraw = 0.0d;
        this.mFling = null;
        this.fling = false;
        this.landscape = false;
        this.adjustLongImageEnable = true;
        initBaseZoomableImageView(context);
    }

    public BaseZoomableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBaseMatrix = new Matrix();
        this.mSuppMatrix = new Matrix();
        this.mDisplayMatrix = new Matrix();
        this.mMatrix = new Matrix();
        this.mMatrixValues = new float[9];
        this.mThisWidth = -1;
        this.mThisHeight = -1;
        this.mOnLayoutRunnable = null;
        this.mRefresh = null;
        this.mLastDraw = 0.0d;
        this.mFling = null;
        this.fling = false;
        this.landscape = false;
        this.adjustLongImageEnable = true;
        initBaseZoomableImageView(context);
    }

    protected void initBaseZoomableImageView(Context context) {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setDither(true);
        this.mPaint.setFilterBitmap(true);
        this.mPaint.setAntiAlias(true);
        if (context.getResources().getConfiguration().orientation == 2) {
            this.landscape = true;
        } else {
            this.landscape = false;
        }
        this.mRefresh = new Runnable() { // from class: com.generalplus.GoPlusDrone.View.BaseZoomableImageView.1
            @Override // java.lang.Runnable
            public void run() {
                BaseZoomableImageView.this.postInvalidate();
            }
        };
    }

    public void setImageGestureListener(ImageGestureListener imageGestureListener) {
        this.mImageGestureListener = imageGestureListener;
    }

    public void setViewPager(ViewPager viewPager) {
        this.mViewPager = viewPager;
    }

    public Bitmap getImageBitmap() {
        return this.mBitmap;
    }

    public void clear() {
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.mBitmap.recycle();
        }
        this.mBitmap = null;
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mThisWidth = i3 - i;
        this.mThisHeight = i4 - i2;
        Runnable runnable = this.mOnLayoutRunnable;
        if (runnable != null) {
            this.mOnLayoutRunnable = null;
            runnable.run();
        }
    }

    protected static void translatePoint(Matrix matrix, float[] fArr) {
        matrix.mapPoints(fArr);
    }

    public void setImageMatrix(Matrix matrix) {
        if (matrix != null && matrix.isIdentity()) {
            matrix = null;
        }
        if ((matrix != null || this.mMatrix.isIdentity()) && (matrix == null || this.mMatrix.equals(matrix))) {
            return;
        }
        this.mMatrix.set(matrix);
        invalidate();
    }

    public void setImageBitmap(Bitmap bitmap) {
        setImageBitmap(bitmap, true);
    }

    public void setImageBitmap(final Bitmap bitmap, final boolean z) {
        if (Build.VERSION.SDK_INT >= 14) {
            if (bitmap != null && (bitmap.getHeight() > BitmapUtils.getTextureSize() || bitmap.getWidth() > BitmapUtils.getTextureSize())) {
                setLayerType(1, null);
            } else {
                setLayerType(2, null);
            }
        }
        if (getWidth() <= 0) {
            this.mOnLayoutRunnable = new Runnable() { // from class: com.generalplus.GoPlusDrone.View.BaseZoomableImageView.2
                @Override // java.lang.Runnable
                public void run() {
                    BaseZoomableImageView.this.setImageBitmap(bitmap, z);
                }
            };
            return;
        }
        Bitmap bitmap2 = this.mBitmap;
        if (bitmap != null) {
            setBaseMatrix(bitmap, this.mBaseMatrix);
            this.mBitmap = bitmap;
        } else {
            this.mBaseMatrix.reset();
            this.mBitmap = bitmap;
        }
        if (bitmap2 != null && bitmap2 != this.mBitmap && !bitmap2.isRecycled()) {
            bitmap2.recycle();
        }
        this.mSuppMatrix.reset();
        setImageMatrix(getImageViewMatrix());
        this.mMaxZoom = maxZoom();
        if (z) {
            zoomToScreen();
        }
    }

    public void setImageBitmap(final Bitmap bitmap, Rect rect) {
        if (getWidth() <= 0) {
            this.mOnLayoutRunnable = new Runnable() { // from class: com.generalplus.GoPlusDrone.View.BaseZoomableImageView.3
                @Override // java.lang.Runnable
                public void run() {
                    BaseZoomableImageView baseZoomableImageView = BaseZoomableImageView.this;
                    baseZoomableImageView.setImageBitmap(bitmap, baseZoomableImageView.updateSelection());
                }
            };
            return;
        }
        Bitmap bitmap2 = this.mBitmap;
        if (bitmap != null) {
            setBaseMatrix(bitmap, this.mBaseMatrix, rect);
            this.mBitmap = bitmap;
        } else {
            this.mBaseMatrix.reset();
            this.mBitmap = bitmap;
        }
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            bitmap2.recycle();
        }
        this.mSuppMatrix.reset();
        setImageMatrix(getImageViewMatrix());
        this.mMaxZoom = maxZoom();
    }

    protected void center(boolean z, boolean z2, boolean z3) {
        float f;
        float f2;
        float f3 = 0.0f;
        float height = 0.0f;
        float f4 = 0.0f;
        if (this.mBitmap == null) {
            return;
        }
        Matrix imageViewMatrix = getImageViewMatrix();
        float[] fArr = {0.0f, 0.0f};
        float[] fArr2 = {this.mBitmap.getWidth(), this.mBitmap.getHeight()};
        translatePoint(imageViewMatrix, fArr);
        translatePoint(imageViewMatrix, fArr2);
        float f5 = fArr2[1] - fArr[1];
        float f6 = fArr2[0] - fArr[0];
        if (z) {
            float height2 = getHeight();
            if (f5 < height2) {
                height = (height2 - f5) / MAX_IMAGE_RATIO_WIDTH_LARGE_LANDSCAPE;
                f4 = fArr[1];
            } else if (fArr[1] > 0.0f) {
                f = -fArr[1];
            } else {
                if (fArr2[1] < height2) {
                    height = getHeight();
                    f4 = fArr2[1];
                }
                f = 0.0f;
            }
            f = height - f4;
        } else {
            f = 0.0f;
        }
        if (z2) {
            float width = getWidth();
            if (f6 < width) {
                width = (width - f6) / MAX_IMAGE_RATIO_WIDTH_LARGE_LANDSCAPE;
                f3 = fArr[0];
            } else if (fArr[0] > 0.0f) {
                f2 = -fArr[0];
            } else {
                if (fArr2[0] < width) {
                    f3 = fArr2[0];
                }
                f2 = 0.0f;
            }
            f2 = width - f3;
        } else {
            f2 = 0.0f;
        }
        postTranslate(f2, f);
        if (z3) {
            TranslateAnimation translateAnimation = new TranslateAnimation(-f2, 0.0f, -f, 0.0f);
            translateAnimation.setStartTime(SystemClock.elapsedRealtime());
            translateAnimation.setDuration(250L);
            setAnimation(translateAnimation);
        }
        setImageMatrix(getImageViewMatrix());
    }

    protected float getValue(Matrix matrix, int i) {
        matrix.getValues(this.mMatrixValues);
        return this.mMatrixValues[i];
    }

    protected float getScale(Matrix matrix) {
        if (this.mBitmap != null) {
            return getValue(matrix, 0);
        }
        return 1.0f;
    }

    public float getScale() {
        return getScale(this.mSuppMatrix);
    }

    private void setBaseMatrix(Bitmap bitmap, Matrix matrix) {
        float width = getWidth();
        float height = getHeight();
        matrix.reset();
        float fMin = Math.min(width / bitmap.getWidth(), 1.0f);
        float fMin2 = Math.min(height / bitmap.getHeight(), 1.0f);
        if (fMin > fMin2) {
            fMin = fMin2;
        }
        matrix.setScale(fMin, fMin);
        matrix.postTranslate((width - (bitmap.getWidth() * fMin)) / MAX_IMAGE_RATIO_WIDTH_LARGE_LANDSCAPE, (height - (bitmap.getHeight() * fMin)) / MAX_IMAGE_RATIO_WIDTH_LARGE_LANDSCAPE);
    }

    private void setBaseMatrix(Bitmap bitmap, Matrix matrix, Rect rect) {
        if (rect == null) {
            return;
        }
        float f = rect.right - rect.left;
        float f2 = rect.bottom - rect.top;
        matrix.reset();
        float width = f / bitmap.getWidth();
        float height = f2 / bitmap.getHeight();
        if (width <= height) {
            width = height;
        }
        matrix.setScale(width, width);
        matrix.postTranslate((getWidth() - (bitmap.getWidth() * width)) / MAX_IMAGE_RATIO_WIDTH_LARGE_LANDSCAPE, (getHeight() - (bitmap.getHeight() * width)) / MAX_IMAGE_RATIO_WIDTH_LARGE_LANDSCAPE);
    }

    protected Matrix getImageViewMatrix() {
        this.mDisplayMatrix.set(this.mBaseMatrix);
        this.mDisplayMatrix.postConcat(this.mSuppMatrix);
        return this.mDisplayMatrix;
    }

    protected float maxZoom() {
        if (this.mBitmap == null) {
            return 1.0f;
        }
        float fMax = Math.max(this.mBitmap.getWidth() / this.mThisWidth, this.mBitmap.getHeight() / this.mThisHeight) * 16.0f;
        if (fMax < 1.0f) {
            return 1.0f;
        }
        return fMax;
    }

    public float zoomDefault() {
        if (this.mBitmap == null) {
            return 1.0f;
        }
        return Math.max(Math.min(this.mThisWidth / this.mBitmap.getWidth(), this.mThisHeight / this.mBitmap.getHeight()), 1.0f);
    }

    protected void zoomTo(float f, float f2, float f3) {
        float f4 = this.mMaxZoom;
        if (f > f4) {
            f = f4;
        }
        float scale = f / getScale();
        this.mSuppMatrix.postScale(scale, scale, f2, f3);
        setImageMatrix(getImageViewMatrix());
        center(true, true, false);
    }

    protected void zoomTo(float f, final float f2, final float f3, final float f4) {
        final float scale = (f - getScale()) / f4;
        final float scale2 = getScale();
        final long jCurrentTimeMillis = System.currentTimeMillis();
        post(new Runnable() { // from class: com.generalplus.GoPlusDrone.View.BaseZoomableImageView.4
            @Override // java.lang.Runnable
            public void run() {
                float fMin = Math.min(f4, System.currentTimeMillis() - jCurrentTimeMillis);
                BaseZoomableImageView.this.zoomTo(scale2 + (scale * fMin), f2, f3);
                if (fMin < f4) {
                    BaseZoomableImageView.this.post(this);
                }
            }
        });
    }

    public void setAdjustLongImageEnable(boolean z) {
        this.adjustLongImageEnable = z;
    }

    public void zoomToScreen() {
        if (this.mBitmap == null) {
            return;
        }
        float f = 1.0f;
        float width = this.mThisWidth / this.mBitmap.getWidth();
        boolean z = false;
        if (this.adjustLongImageEnable && (this.mBitmap.getHeight() / this.mBitmap.getWidth() > MAX_IMAGE_RATIO_LARGE || (this.landscape && this.mBitmap.getHeight() / this.mBitmap.getWidth() > MAX_IMAGE_RATIO_WIDTH_LARGE_LANDSCAPE))) {
            f = width;
            z = true;
        }
        if (z) {
            float scale = f / getScale();
            this.mBaseMatrix.reset();
            this.mSuppMatrix.postScale(scale, scale, 0.0f, 0.0f);
            setImageMatrix(getImageViewMatrix());
            return;
        }
        zoomTo(zoomDefault());
    }

    public void zoomTo(float f) {
        zoomTo(f, getWidth() / MAX_IMAGE_RATIO_WIDTH_LARGE_LANDSCAPE, getHeight() / MAX_IMAGE_RATIO_WIDTH_LARGE_LANDSCAPE);
    }

    public void zoomIn() {
        zoomIn(sScaleRate);
    }

    public void zoomOut() {
        zoomOut(sScaleRate);
    }

    protected void zoomIn(float f) {
        if (getScale() < this.mMaxZoom && this.mBitmap != null) {
            this.mSuppMatrix.postScale(f, f, getWidth() / MAX_IMAGE_RATIO_WIDTH_LARGE_LANDSCAPE, getHeight() / MAX_IMAGE_RATIO_WIDTH_LARGE_LANDSCAPE);
            setImageMatrix(getImageViewMatrix());
        }
    }

    protected void zoomOut(float f) {
        if (this.mBitmap == null) {
            return;
        }
        float width = getWidth();
        float height = getHeight();
        Matrix matrix = new Matrix(this.mSuppMatrix);
        float f2 = width / MAX_IMAGE_RATIO_WIDTH_LARGE_LANDSCAPE;
        float f3 = height / MAX_IMAGE_RATIO_WIDTH_LARGE_LANDSCAPE;
        matrix.postScale(0.8f, 0.8f, f2, f3);
        if (getScale(matrix) < 1.0f) {
            this.mSuppMatrix.setScale(1.0f, 1.0f, f2, f3);
        } else {
            float f4 = 1.0f / f;
            this.mSuppMatrix.postScale(f4, f4, f2, f3);
        }
        setImageMatrix(getImageViewMatrix());
        center(true, true, false);
    }

    protected boolean postTranslate(float f, float f2) {
        return this.mSuppMatrix.postTranslate(f, f2);
    }

    protected void scrollBy(final float f, final float f2, final float f3) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        Runnable runnable = new Runnable() { // from class: com.generalplus.GoPlusDrone.View.BaseZoomableImageView.5
            float old_x = 0.0f;
            float old_y = 0.0f;

            @Override // java.lang.Runnable
            public void run() {
                float fMin = Math.min(f3, System.currentTimeMillis() - jCurrentTimeMillis);
                float fEaseOut = BaseZoomableImageView.this.easeOut(fMin, 0.0f, f, f3);
                float fEaseOut2 = BaseZoomableImageView.this.easeOut(fMin, 0.0f, f2, f3);
                BaseZoomableImageView.this.postTranslate(fEaseOut - this.old_x, fEaseOut2 - this.old_y);
                BaseZoomableImageView.this.center(true, true, false);
                this.old_x = fEaseOut;
                this.old_y = fEaseOut2;
                if (fMin < f3) {
                    BaseZoomableImageView baseZoomableImageView = BaseZoomableImageView.this;
                    baseZoomableImageView.fling = baseZoomableImageView.post(this);
                } else {
                    BaseZoomableImageView.this.stopFling();
                }
            }
        };
        this.mFling = runnable;
        this.fling = post(runnable);
    }

    protected void stopFling() {
        removeCallbacks(this.mFling);
        if (this.fling) {
            this.fling = false;
            onScrollFinish();
        }
    }

    protected boolean fling() {
        return this.fling;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = this.mBitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 14 && getLayerType() == 2) {
            canvas.drawBitmap(this.mBitmap, this.mMatrix, null);
            return;
        }
        if (System.currentTimeMillis() - this.mLastDraw > 250.0d) {
            canvas.drawBitmap(this.mBitmap, this.mMatrix, this.mPaint);
            this.mLastDraw = System.currentTimeMillis();
        } else {
            canvas.drawBitmap(this.mBitmap, this.mMatrix, null);
            removeCallbacks(this.mRefresh);
            postDelayed(this.mRefresh, 250L);
        }
    }

    protected boolean isScrollOver(float f) {
        try {
            if (this.mDisplayMatrix != null) {
                float value = getValue(this.mDisplayMatrix, 2);
                float width = getWidth() - value;
                if ((value == 0.0f && f <= 0.0f) || (width == this.mBitmap.getWidth() * getValue(this.mDisplayMatrix, 0) && f >= 0.0f)) {
                    System.out.println("ScrollOver");
                    return true;
                }
            }
        } catch (IllegalArgumentException e) {
            Log.v("Vincent", "isScrollOver");
            e.printStackTrace();
        }
        return false;
    }
}
