package com.gizthon.camera.view;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;

/* JADX INFO: loaded from: classes.dex */
public class TouchImageView extends AppCompatImageView {
    static final int CLICK = 3;
    static final int DRAG = 1;
    static final int NONE = 0;
    static final int ZOOM = 2;
    final int DOUBLE_CLICK_TIMEOUT;
    private boolean bAllowSlidePage;
    private boolean bCenterFlag;
    private boolean bDoubleClickFlag;
    private boolean bPointerDownFlag;
    Context context;
    protected float fOrigHeight;
    protected float fOrigWidth;
    PointF last;

    /* JADX INFO: renamed from: m */
    private float[] f1996m;
    private MotionEvent mCurrentDownEvent;
    private MotionEvent mPreviousUpEvent;
    ScaleGestureDetector mScaleDetector;
    Matrix matrix;
    private float maxScale;
    private float minScale;
    int mode;
    private int oldMeasuredHeight;
    private int oldMeasuredWidth;
    private float saveScale;
    PointF start;
    private int viewHeight;
    private int viewWidth;

    float getFixDragTrans(float f, float f2, float f3) {
        if (f3 <= f2) {
            return 0.0f;
        }
        return f;
    }

    float getFixTrans(float f, float f2, float f3) {
        float f4;
        float f5;
        if (f3 <= f2) {
            f5 = f2 - f3;
            f4 = 0.0f;
        } else {
            f4 = f2 - f3;
            f5 = 0.0f;
        }
        if (f < f4) {
            return (-f) + f4;
        }
        if (f > f5) {
            return (-f) + f5;
        }
        return 0.0f;
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    public TouchImageView(Context context) {
        super(context);
        this.mode = 0;
        this.last = new PointF();
        this.start = new PointF();
        this.minScale = 1.0f;
        this.maxScale = 3.0f;
        this.saveScale = 1.0f;
        this.bAllowSlidePage = true;
        this.bCenterFlag = false;
        this.bPointerDownFlag = false;
        this.bDoubleClickFlag = false;
        this.DOUBLE_CLICK_TIMEOUT = 200;
        sharedConstructing(context);
    }

    public TouchImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mode = 0;
        this.last = new PointF();
        this.start = new PointF();
        this.minScale = 1.0f;
        this.maxScale = 3.0f;
        this.saveScale = 1.0f;
        this.bAllowSlidePage = true;
        this.bCenterFlag = false;
        this.bPointerDownFlag = false;
        this.bDoubleClickFlag = false;
        this.DOUBLE_CLICK_TIMEOUT = 200;
        sharedConstructing(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean IsConsideredDoubleTap(MotionEvent motionEvent, MotionEvent motionEvent2, MotionEvent motionEvent3) {
        if (motionEvent3.getEventTime() - motionEvent2.getEventTime() > 200) {
            return false;
        }
        int x = ((int) motionEvent2.getX()) - ((int) motionEvent3.getX());
        int y = ((int) motionEvent2.getY()) - ((int) motionEvent3.getY());
        return (x * x) + (y * y) < 10000;
    }

    private void sharedConstructing(Context context) {
        super.setClickable(true);
        this.context = context;
        this.mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        Matrix matrix = new Matrix();
        this.matrix = matrix;
        this.f1996m = new float[9];
        setImageMatrix(matrix);
        setScaleType(ImageView.ScaleType.MATRIX);
        setOnTouchListener(new View.OnTouchListener() { // from class: com.gizthon.camera.view.TouchImageView.1
            /* JADX WARN: Removed duplicated region for block: B:30:0x010b  */
            @Override // android.view.View.OnTouchListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public boolean onTouch(android.view.View r7, android.view.MotionEvent r8) {
                /*
                    Method dump skipped, instruction units count: 316
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.gizthon.camera.view.TouchImageView.ViewOnTouchListenerC11221.onTouch(android.view.View, android.view.MotionEvent):boolean");
            }
        });
    }

    void DetermineAllowSlidePage(float f) {
        if (f <= 1.0f) {
            this.bAllowSlidePage = true;
        } else if (f > 1.0f) {
            this.bAllowSlidePage = false;
        }
        getParent().requestDisallowInterceptTouchEvent(true ^ this.bAllowSlidePage);
    }

    void fixTrans() {
        this.matrix.getValues(this.f1996m);
        float[] fArr = this.f1996m;
        float f = fArr[2];
        float f2 = fArr[5];
        float fixTrans = getFixTrans(f, this.viewWidth, this.fOrigWidth * this.saveScale);
        float fixTrans2 = getFixTrans(f2, this.viewHeight, this.fOrigHeight * this.saveScale);
        if (fixTrans != 0.0f || fixTrans2 != 0.0f) {
            this.matrix.postTranslate(fixTrans, fixTrans2);
        }
        if (f2 < 0.0f) {
            this.bCenterFlag = true;
        }
        if (this.bDoubleClickFlag) {
            this.saveScale = 1.0f;
            this.bCenterFlag = true;
            this.bDoubleClickFlag = false;
        }
        if (this.saveScale == 1.0f && this.bCenterFlag) {
            this.bCenterFlag = false;
            CenterImage();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        super.onMeasure(i, i2);
        this.viewWidth = View.MeasureSpec.getSize(i);
        int size = View.MeasureSpec.getSize(i2);
        this.viewHeight = size;
        int i5 = this.oldMeasuredHeight;
        if ((i5 == this.viewWidth && i5 == size) || (i3 = this.viewWidth) == 0 || (i4 = this.viewHeight) == 0) {
            return;
        }
        this.oldMeasuredHeight = i4;
        this.oldMeasuredWidth = i3;
        CenterImage();
    }

    void CenterImage() {
        if (this.saveScale == 1.0f) {
            Drawable drawable = getDrawable();
            if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0) {
                return;
            }
            float intrinsicWidth = drawable.getIntrinsicWidth();
            float intrinsicHeight = drawable.getIntrinsicHeight();
            float fMin = Math.min(this.viewWidth / intrinsicWidth, this.viewHeight / intrinsicHeight);
            this.matrix.setScale(fMin, fMin);
            float f = (this.viewHeight - (intrinsicHeight * fMin)) / 2.0f;
            float f2 = (this.viewWidth - (fMin * intrinsicWidth)) / 2.0f;
            this.matrix.postTranslate(f2, f);
            this.fOrigWidth = this.viewWidth - (f2 * 2.0f);
            this.fOrigHeight = this.viewHeight - (f * 2.0f);
            setImageMatrix(this.matrix);
        }
        fixTrans();
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private ScaleListener() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            TouchImageView.this.mode = 2;
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0094  */
        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean onScale(android.view.ScaleGestureDetector r5) {
            /*
                r4 = this;
                float r0 = r5.getScaleFactor()
                com.gizthon.camera.view.TouchImageView r1 = com.gizthon.camera.view.TouchImageView.this
                float r1 = com.gizthon.camera.view.TouchImageView.access$700(r1)
                com.gizthon.camera.view.TouchImageView r2 = com.gizthon.camera.view.TouchImageView.this
                float r3 = com.gizthon.camera.view.TouchImageView.access$700(r2)
                float r3 = r3 * r0
                com.gizthon.camera.view.TouchImageView.access$702(r2, r3)
                com.gizthon.camera.view.TouchImageView r2 = com.gizthon.camera.view.TouchImageView.this
                float r2 = com.gizthon.camera.view.TouchImageView.access$700(r2)
                com.gizthon.camera.view.TouchImageView r3 = com.gizthon.camera.view.TouchImageView.this
                float r3 = com.gizthon.camera.view.TouchImageView.access$900(r3)
                int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                if (r2 <= 0) goto L36
                com.gizthon.camera.view.TouchImageView r0 = com.gizthon.camera.view.TouchImageView.this
                float r2 = com.gizthon.camera.view.TouchImageView.access$900(r0)
                com.gizthon.camera.view.TouchImageView.access$702(r0, r2)
                com.gizthon.camera.view.TouchImageView r0 = com.gizthon.camera.view.TouchImageView.this
                float r0 = com.gizthon.camera.view.TouchImageView.access$900(r0)
            L34:
                float r0 = r0 / r1
                goto L56
            L36:
                com.gizthon.camera.view.TouchImageView r2 = com.gizthon.camera.view.TouchImageView.this
                float r2 = com.gizthon.camera.view.TouchImageView.access$700(r2)
                com.gizthon.camera.view.TouchImageView r3 = com.gizthon.camera.view.TouchImageView.this
                float r3 = com.gizthon.camera.view.TouchImageView.access$1000(r3)
                int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                if (r2 >= 0) goto L56
                com.gizthon.camera.view.TouchImageView r0 = com.gizthon.camera.view.TouchImageView.this
                float r2 = com.gizthon.camera.view.TouchImageView.access$1000(r0)
                com.gizthon.camera.view.TouchImageView.access$702(r0, r2)
                com.gizthon.camera.view.TouchImageView r0 = com.gizthon.camera.view.TouchImageView.this
                float r0 = com.gizthon.camera.view.TouchImageView.access$1000(r0)
                goto L34
            L56:
                com.gizthon.camera.view.TouchImageView r1 = com.gizthon.camera.view.TouchImageView.this
                float r1 = r1.fOrigWidth
                com.gizthon.camera.view.TouchImageView r2 = com.gizthon.camera.view.TouchImageView.this
                float r2 = com.gizthon.camera.view.TouchImageView.access$700(r2)
                float r1 = r1 * r2
                com.gizthon.camera.view.TouchImageView r2 = com.gizthon.camera.view.TouchImageView.this
                int r2 = com.gizthon.camera.view.TouchImageView.access$600(r2)
                float r2 = (float) r2
                int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
                if (r1 <= 0) goto L94
                com.gizthon.camera.view.TouchImageView r1 = com.gizthon.camera.view.TouchImageView.this
                float r1 = r1.fOrigHeight
                com.gizthon.camera.view.TouchImageView r2 = com.gizthon.camera.view.TouchImageView.this
                float r2 = com.gizthon.camera.view.TouchImageView.access$700(r2)
                float r1 = r1 * r2
                com.gizthon.camera.view.TouchImageView r2 = com.gizthon.camera.view.TouchImageView.this
                int r2 = com.gizthon.camera.view.TouchImageView.access$800(r2)
                float r2 = (float) r2
                int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
                if (r1 <= 0) goto L94
                com.gizthon.camera.view.TouchImageView r1 = com.gizthon.camera.view.TouchImageView.this
                android.graphics.Matrix r1 = r1.matrix
                float r2 = r5.getFocusX()
                float r5 = r5.getFocusY()
                r1.postScale(r0, r0, r2, r5)
                goto Lad
            L94:
                com.gizthon.camera.view.TouchImageView r5 = com.gizthon.camera.view.TouchImageView.this
                android.graphics.Matrix r5 = r5.matrix
                com.gizthon.camera.view.TouchImageView r1 = com.gizthon.camera.view.TouchImageView.this
                int r1 = com.gizthon.camera.view.TouchImageView.access$600(r1)
                int r1 = r1 / 2
                float r1 = (float) r1
                com.gizthon.camera.view.TouchImageView r2 = com.gizthon.camera.view.TouchImageView.this
                int r2 = com.gizthon.camera.view.TouchImageView.access$800(r2)
                int r2 = r2 / 2
                float r2 = (float) r2
                r5.postScale(r0, r0, r1, r2)
            Lad:
                com.gizthon.camera.view.TouchImageView r5 = com.gizthon.camera.view.TouchImageView.this
                r5.fixTrans()
                r5 = 1
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.gizthon.camera.view.TouchImageView.ScaleListener.onScale(android.view.ScaleGestureDetector):boolean");
        }
    }
}
