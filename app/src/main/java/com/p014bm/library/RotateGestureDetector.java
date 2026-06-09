package com.p014bm.library;

import android.view.MotionEvent;

/* JADX INFO: loaded from: classes.dex */
public class RotateGestureDetector {
    private static final int MAX_DEGREES_STEP = 120;
    private float mCurrSlope;
    private OnRotateListener mListener;
    private float mPrevSlope;

    /* JADX INFO: renamed from: x1 */
    private float f1901x1;

    /* JADX INFO: renamed from: x2 */
    private float f1902x2;

    /* JADX INFO: renamed from: y1 */
    private float f1903y1;

    /* JADX INFO: renamed from: y2 */
    private float f1904y2;

    public RotateGestureDetector(OnRotateListener onRotateListener) {
        this.mListener = onRotateListener;
    }

    public void onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 2) {
            if ((actionMasked == 5 || actionMasked == 6) && motionEvent.getPointerCount() == 2) {
                this.mPrevSlope = caculateSlope(motionEvent);
                return;
            }
            return;
        }
        if (motionEvent.getPointerCount() > 1) {
            float fCaculateSlope = caculateSlope(motionEvent);
            this.mCurrSlope = fCaculateSlope;
            double degrees = Math.toDegrees(Math.atan(fCaculateSlope)) - Math.toDegrees(Math.atan(this.mPrevSlope));
            if (Math.abs(degrees) <= 120.0d) {
                this.mListener.onRotate((float) degrees, (this.f1902x2 + this.f1901x1) / 2.0f, (this.f1904y2 + this.f1903y1) / 2.0f);
            }
            this.mPrevSlope = this.mCurrSlope;
        }
    }

    private float caculateSlope(MotionEvent motionEvent) {
        this.f1901x1 = motionEvent.getX(0);
        this.f1903y1 = motionEvent.getY(0);
        this.f1902x2 = motionEvent.getX(1);
        float y = motionEvent.getY(1);
        this.f1904y2 = y;
        return (y - this.f1903y1) / (this.f1902x2 - this.f1901x1);
    }
}
