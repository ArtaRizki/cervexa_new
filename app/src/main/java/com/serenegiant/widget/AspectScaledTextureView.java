package com.serenegiant.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes2.dex */
public class AspectScaledTextureView extends TextureView implements TextureView.SurfaceTextureListener, IAspectRatioView, IScaledView, ITextureView {
    private static final String TAG = AspectScaledTextureView.class.getSimpleName();
    private volatile boolean mHasSurface;
    protected final Matrix mImageMatrix;
    private final Set<TextureView.SurfaceTextureListener> mListeners;
    private double mRequestedAspect;
    private int mScaleMode;
    private TextureView.SurfaceTextureListener mSurfaceTextureListener;
    private int prevHeight;
    private int prevWidth;

    protected void onResize(int i, int i2) {
    }

    public AspectScaledTextureView(Context context) {
        this(context, null, 0);
    }

    public AspectScaledTextureView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AspectScaledTextureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mImageMatrix = new Matrix();
        this.mScaleMode = 0;
        this.mRequestedAspect = -1.0d;
        this.mListeners = new CopyOnWriteArraySet();
        this.prevWidth = -1;
        this.prevHeight = -1;
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, com.weioa.KmedHealthIndonesia.R.styleable.AspectScaledTextureView, i, 0);
        try {
            this.mRequestedAspect = typedArrayObtainStyledAttributes.getFloat(com.weioa.KmedHealthIndonesia.R.styleable.AspectScaledTextureView_aspect_ratio, -1.0f);
            this.mScaleMode = typedArrayObtainStyledAttributes.getInt(com.weioa.KmedHealthIndonesia.R.styleable.AspectScaledTextureView_scale_mode, 0);
            typedArrayObtainStyledAttributes.recycle();
            super.setSurfaceTextureListener(this);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x005c  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onMeasure(int r19, int r20) {
        /*
            r18 = this;
            r0 = r18
            double r1 = r0.mRequestedAspect
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L5c
            int r1 = r0.mScaleMode
            if (r1 != 0) goto L5c
            int r1 = android.view.View.MeasureSpec.getSize(r19)
            int r2 = android.view.View.MeasureSpec.getSize(r20)
            int r5 = r18.getPaddingLeft()
            int r6 = r18.getPaddingRight()
            int r5 = r5 + r6
            int r6 = r18.getPaddingTop()
            int r7 = r18.getPaddingBottom()
            int r6 = r6 + r7
            int r1 = r1 - r5
            int r2 = r2 - r6
            double r7 = (double) r1
            double r9 = (double) r2
            double r11 = r7 / r9
            double r13 = r0.mRequestedAspect
            double r13 = r13 / r11
            r11 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r13 = r13 - r11
            double r11 = java.lang.Math.abs(r13)
            r15 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            int r17 = (r11 > r15 ? 1 : (r11 == r15 ? 0 : -1))
            if (r17 <= 0) goto L5c
            int r11 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
            if (r11 <= 0) goto L4a
            double r2 = r0.mRequestedAspect
            double r7 = r7 / r2
            int r2 = (int) r7
            goto L4f
        L4a:
            double r3 = r0.mRequestedAspect
            double r9 = r9 * r3
            int r1 = (int) r9
        L4f:
            int r1 = r1 + r5
            int r2 = r2 + r6
            r3 = 1073741824(0x40000000, float:2.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r3)
            int r2 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r3)
            goto L60
        L5c:
            r1 = r19
            r2 = r20
        L60:
            super.onMeasure(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.serenegiant.widget.AspectScaledTextureView.onMeasure(int, int):void");
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        if (this.prevWidth != getWidth() || this.prevHeight != getHeight()) {
            this.prevWidth = getWidth();
            int height = getHeight();
            this.prevHeight = height;
            onResize(this.prevWidth, height);
        }
        init();
    }

    @Override // android.view.TextureView
    public final void setSurfaceTextureListener(TextureView.SurfaceTextureListener surfaceTextureListener) {
        unregister(this.mSurfaceTextureListener);
        this.mSurfaceTextureListener = surfaceTextureListener;
        register(surfaceTextureListener);
    }

    @Override // com.serenegiant.widget.ITextureView
    public void register(TextureView.SurfaceTextureListener surfaceTextureListener) {
        if (surfaceTextureListener != null) {
            this.mListeners.add(surfaceTextureListener);
        }
    }

    @Override // com.serenegiant.widget.ITextureView
    public void unregister(TextureView.SurfaceTextureListener surfaceTextureListener) {
        this.mListeners.remove(surfaceTextureListener);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.mHasSurface = true;
        init();
        for (TextureView.SurfaceTextureListener surfaceTextureListener : this.mListeners) {
            try {
                surfaceTextureListener.onSurfaceTextureAvailable(surfaceTexture, i, i2);
            } catch (Exception e) {
                this.mListeners.remove(surfaceTextureListener);
                Log.w(TAG, e);
            }
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        for (TextureView.SurfaceTextureListener surfaceTextureListener : this.mListeners) {
            try {
                surfaceTextureListener.onSurfaceTextureSizeChanged(surfaceTexture, i, i2);
            } catch (Exception e) {
                this.mListeners.remove(surfaceTextureListener);
                Log.w(TAG, e);
            }
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.mHasSurface = false;
        for (TextureView.SurfaceTextureListener surfaceTextureListener : this.mListeners) {
            try {
                surfaceTextureListener.onSurfaceTextureDestroyed(surfaceTexture);
            } catch (Exception e) {
                this.mListeners.remove(surfaceTextureListener);
                Log.w(TAG, e);
            }
        }
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        for (TextureView.SurfaceTextureListener surfaceTextureListener : this.mListeners) {
            try {
                surfaceTextureListener.onSurfaceTextureUpdated(surfaceTexture);
            } catch (Exception e) {
                this.mListeners.remove(surfaceTextureListener);
                Log.w(TAG, e);
            }
        }
    }

    @Override // com.serenegiant.widget.IAspectRatioView
    public void setAspectRatio(double d) {
        if (this.mRequestedAspect != d) {
            this.mRequestedAspect = d;
            requestLayout();
        }
    }

    @Override // com.serenegiant.widget.IAspectRatioView
    public void setAspectRatio(int i, int i2) {
        setAspectRatio(((double) i) / ((double) i2));
    }

    @Override // com.serenegiant.widget.IAspectRatioView
    public double getAspectRatio() {
        return this.mRequestedAspect;
    }

    @Override // com.serenegiant.widget.IScaledView
    public void setScaleMode(int i) {
        if (this.mScaleMode != i) {
            this.mScaleMode = i;
            requestLayout();
        }
    }

    @Override // com.serenegiant.widget.IScaledView
    public int getScaleMode() {
        return this.mScaleMode;
    }

    protected void init() {
        int width = getWidth();
        int height = getHeight();
        this.mImageMatrix.reset();
        if (this.mScaleMode == 2) {
            double d = height;
            double d2 = this.mRequestedAspect * d;
            double d3 = width;
            double dMax = Math.max(d3 / d2, d / d);
            this.mImageMatrix.postScale((float) ((d2 * dMax) / d3), (float) ((dMax * d) / d), width / 2, height / 2);
        }
        setTransform(this.mImageMatrix);
    }
}
