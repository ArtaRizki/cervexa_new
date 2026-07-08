package com.serenegiant.usb.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;
import com.serenegiant.usb.widget.CameraViewInterface;
import com.serenegiant.widget.IAspectRatioView;

/* JADX INFO: loaded from: classes2.dex */
public class AspectRatioTextureView extends TextureView implements IAspectRatioView {
    private static final boolean DEBUG = true;
    private static final String TAG = "AbstractCameraView";
    private CameraViewInterface.Callback mCallback;
    private double mRequestedAspect;

    public AspectRatioTextureView(Context context) {
        this(context, null, 0);
    }

    public AspectRatioTextureView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AspectRatioTextureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRequestedAspect = -1.0d;
    }

    @Override // com.serenegiant.widget.IAspectRatioView
    public void setAspectRatio(double d) {
        if (d < 0.0d) {
            throw new IllegalArgumentException();
        }
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

    /* JADX WARN: Removed duplicated region for block: B:11:0x0058  */
    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mRequestedAspect > 0.0d) {
            int width = android.view.View.MeasureSpec.getSize(widthMeasureSpec);
            int height = android.view.View.MeasureSpec.getSize(heightMeasureSpec);
            int horizontalPadding = getPaddingLeft() + getPaddingRight();
            int verticalPadding = getPaddingTop() + getPaddingBottom();
            width -= horizontalPadding;
            height -= verticalPadding;

            double viewAspect = (double) width / (double) height;
            double aspectDiff = (this.mRequestedAspect / viewAspect) - 1.0d;

            if (Math.abs(aspectDiff) > 0.01d) {
                if (aspectDiff > 0.0d) {
                    height = (int) (width / this.mRequestedAspect);
                } else {
                    width = (int) (height * this.mRequestedAspect);
                }
                width += horizontalPadding;
                height += verticalPadding;
                widthMeasureSpec = android.view.View.MeasureSpec.makeMeasureSpec(width, 1073741824); // EXACTLY
                heightMeasureSpec = android.view.View.MeasureSpec.makeMeasureSpec(height, 1073741824); // EXACTLY
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
