package com.serenegiant.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;
/* JADX INFO: loaded from: classes2.dex */
public class AspectRatioFrameLayout extends FrameLayout implements IAspectRatioView {
    private double mRequestedAspect;

    public AspectRatioFrameLayout(Context context) {
        this(context, null, 0);
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.mRequestedAspect = -1.0d;
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRequestedAspect = -1.0d;
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, com.weioa.KmedHealthIndonesia.R.styleable.IAspectRatioView, i, 0);
        try {
            this.mRequestedAspect = typedArrayObtainStyledAttributes.getFloat(com.weioa.KmedHealthIndonesia.R.styleable.IAspectRatioView_aspect_ratio, -1.0f);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0058  */
    @Override // android.widget.FrameLayout, android.view.View
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
            if (r5 <= 0) goto L58
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
            if (r17 <= 0) goto L58
            int r11 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
            if (r11 <= 0) goto L46
            double r2 = r0.mRequestedAspect
            double r7 = r7 / r2
            int r2 = (int) r7
            goto L4b
        L46:
            double r3 = r0.mRequestedAspect
            double r9 = r9 * r3
            int r1 = (int) r9
        L4b:
            int r1 = r1 + r5
            int r2 = r2 + r6
            r3 = 1073741824(0x40000000, float:2.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r3)
            int r2 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r3)
            goto L5c
        L58:
            r1 = r19
            r2 = r20
        L5c:
            super.onMeasure(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.serenegiant.widget.AspectRatioFrameLayout.onMeasure(int, int):void");
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
}
