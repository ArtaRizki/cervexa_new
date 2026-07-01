package com.serenegiant.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.serenegiant.common.C1831R;
import com.serenegiant.glutils.IRendererCommon;

/* JADX INFO: loaded from: classes2.dex */
public class ZoomAspectScaledTextureView extends AspectScaledTextureView implements IRendererCommon {
    private static final float DEFAULT_MAX_SCALE = 8.0f;
    private static final float DEFAULT_MIN_SCALE = 0.8f;
    private static final float DEFAULT_SCALE = 1.0f;
    private static final float EPS = 0.1f;
    private static final float MIN_DISTANCE = 15.0f;
    private static final float MIN_DISTANCE_SQUARE = 225.0f;
    private static final float MOVE_LIMIT_RATE = 0.2f;
    private static final int STATE_CHECKING = 3;
    private static final int STATE_DRAGING = 2;
    private static final int STATE_NON = 0;
    private static final int STATE_ROTATING = 5;
    private static final int STATE_WAITING = 1;
    private static final int STATE_ZOOMING = 4;
    private static final float TO_DEGREE = 57.29578f;
    private float mCurrentDegrees;
    protected final Matrix mDefaultMatrix;
    private boolean mHandleTouchEvent;
    protected boolean mImageMatrixChanged;
    private final RectF mImageRect;
    private boolean mIsRotating;
    private final RectF mLimitRect;
    private final LineSegment[] mLimitSegments;
    protected final float[] mMatrixCache;
    protected final float mMaxScale;
    private float mMinScale;
    private int mMirrorMode;
    private float mPivotX;
    private float mPivotY;
    private int mPrimaryId;
    private float mPrimaryX;
    private float mPrimaryY;
    private final Matrix mSavedImageMatrix;
    private float mSecondX;
    private float mSecondY;
    private int mSecondaryId;
    private Runnable mStartCheckRotate;
    private int mState;
    private float mTouchDistance;
    private final float[] mTrans;
    private Runnable mWaitImageReset;
    private static final int CHECK_TIMEOUT = ViewConfiguration.getTapTimeout() + ViewConfiguration.getLongPressTimeout();
    private static final int TAP_TIMEOUT = ViewConfiguration.getTapTimeout() * 2;
    private static final int LONG_PRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();

    private static final float crossProduct(float f, float f2, float f3, float f4) {
        return (f * f4) - (f3 * f2);
    }

    private static final float dotProduct(float f, float f2, float f3, float f4) {
        return (f * f3) + (f2 * f4);
    }

    public ZoomAspectScaledTextureView(Context context) {
        this(context, null, 0);
    }

    public ZoomAspectScaledTextureView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ZoomAspectScaledTextureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDefaultMatrix = new Matrix();
        this.mMatrixCache = new float[9];
        this.mSavedImageMatrix = new Matrix();
        this.mLimitRect = new RectF();
        this.mLimitSegments = new LineSegment[4];
        this.mImageRect = new RectF();
        this.mTrans = new float[8];
        this.mMaxScale = DEFAULT_MAX_SCALE;
        this.mMinScale = DEFAULT_MIN_SCALE;
        this.mState = -1;
        this.mMirrorMode = 0;
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C1831R.styleable.ZoomAspectScaledTextureView, i, 0);
        try {
            this.mHandleTouchEvent = typedArrayObtainStyledAttributes.getBoolean(C1831R.styleable.ZoomAspectScaledTextureView_handle_touch_event, true);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x0073  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            boolean r0 = r5.mHandleTouchEvent
            if (r0 != 0) goto L9
            boolean r6 = super.onTouchEvent(r6)
            return r6
        L9:
            int r0 = r6.getActionMasked()
            r1 = 1
            if (r0 == 0) goto Laa
            if (r0 == r1) goto L73
            r2 = 5
            r3 = 3
            r4 = 2
            if (r0 == r4) goto L37
            if (r0 == r3) goto L73
            if (r0 == r2) goto L20
            r1 = 6
            if (r0 == r1) goto La1
            goto La5
        L20:
            int r0 = r5.mState
            if (r0 == r1) goto L28
            if (r0 == r4) goto L2d
            goto La5
        L28:
            java.lang.Runnable r0 = r5.mWaitImageReset
            r5.removeCallbacks(r0)
        L2d:
            int r0 = r6.getPointerCount()
            if (r0 <= r1) goto La5
            r5.startCheck(r6)
            return r1
        L37:
            int r0 = r5.mState
            if (r0 == r1) goto L64
            if (r0 == r4) goto L5d
            if (r0 == r3) goto L53
            r3 = 4
            if (r0 == r3) goto L4c
            if (r0 == r2) goto L45
            goto La5
        L45:
            boolean r0 = r5.processRotate(r6)
            if (r0 == 0) goto La5
            return r1
        L4c:
            boolean r0 = r5.processZoom(r6)
            if (r0 == 0) goto La5
            return r1
        L53:
            boolean r0 = r5.checkTouchMoved(r6)
            if (r0 == 0) goto La5
            r5.startZoom(r6)
            return r1
        L5d:
            boolean r0 = r5.processDrag(r6)
            if (r0 == 0) goto La5
            return r1
        L64:
            boolean r0 = r5.checkTouchMoved(r6)
            if (r0 == 0) goto La5
            java.lang.Runnable r6 = r5.mWaitImageReset
            r5.removeCallbacks(r6)
            r5.setState(r4)
            return r1
        L73:
            java.lang.Runnable r2 = r5.mWaitImageReset
            r5.removeCallbacks(r2)
            java.lang.Runnable r2 = r5.mStartCheckRotate
            r5.removeCallbacks(r2)
            if (r0 != r1) goto La1
            int r0 = r5.mState
            if (r0 != r1) goto La1
            long r0 = android.os.SystemClock.uptimeMillis()
            long r2 = r6.getDownTime()
            long r0 = r0 - r2
            int r2 = com.serenegiant.widget.ZoomAspectScaledTextureView.LONG_PRESS_TIMEOUT
            long r2 = (long) r2
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L97
            r5.performLongClick()
            goto La1
        L97:
            int r2 = com.serenegiant.widget.ZoomAspectScaledTextureView.TAP_TIMEOUT
            long r2 = (long) r2
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto La1
            r5.performClick()
        La1:
            r0 = 0
            r5.setState(r0)
        La5:
            boolean r6 = super.onTouchEvent(r6)
            return r6
        Laa:
            r5.startWaiting(r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.serenegiant.widget.ZoomAspectScaledTextureView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // com.serenegiant.widget.AspectScaledTextureView, android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        super.onSurfaceTextureAvailable(surfaceTexture, i, i2);
        setMirror(0);
    }

    @Override // com.serenegiant.widget.AspectScaledTextureView, android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        super.onSurfaceTextureSizeChanged(surfaceTexture, i, i2);
        applyMirrorMode();
    }

    @Override // com.serenegiant.glutils.IRendererCommon
    public void setMirror(int i) {
        if (this.mMirrorMode != i) {
            this.mMirrorMode = i;
            applyMirrorMode();
        }
    }

    @Override // com.serenegiant.glutils.IRendererCommon
    public int getMirror() {
        return this.mMirrorMode;
    }

    public void setEnableHandleTouchEvent(boolean z) {
        this.mHandleTouchEvent = z;
    }

    public void reset() {
        init();
    }

    @Override // com.serenegiant.widget.AspectScaledTextureView
    protected void init() {
        this.mState = -1;
        setState(0);
        this.mMinScale = DEFAULT_MIN_SCALE;
        this.mCurrentDegrees = 0.0f;
        this.mIsRotating = Math.abs((((float) ((int) (0.0f / 360.0f))) * 360.0f) - 0.0f) > 0.1f;
        int width = getWidth();
        int height = getHeight();
        Rect rect = new Rect();
        getDrawingRect(rect);
        this.mLimitRect.set(rect);
        this.mLimitRect.inset((int) (width * 0.2f), (int) (height * 0.2f));
        this.mLimitSegments[0] = null;
        this.mImageRect.set(0.0f, 0.0f, rect.width(), rect.height());
        super.init();
        this.mDefaultMatrix.set(this.mImageMatrix);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setState(int i) {
        if (this.mState != i) {
            this.mState = i;
            getTransform(this.mSavedImageMatrix);
            if (this.mImageMatrix.equals(this.mSavedImageMatrix)) {
                return;
            }
            this.mImageMatrix.set(this.mSavedImageMatrix);
            this.mImageMatrixChanged = true;
        }
    }

    private final void startWaiting(MotionEvent motionEvent) {
        this.mPrimaryId = 0;
        this.mSecondaryId = -1;
        float x = motionEvent.getX();
        this.mSecondX = x;
        this.mPrimaryX = x;
        float y = motionEvent.getY();
        this.mSecondY = y;
        this.mPrimaryY = y;
        if (this.mWaitImageReset == null) {
            this.mWaitImageReset = new WaitImageReset();
        }
        postDelayed(this.mWaitImageReset, CHECK_TIMEOUT);
        setState(1);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0199 A[PHI: r4
  0x0199: PHI (r4v30 boolean) = (r4v29 boolean), (r4v34 boolean), (r4v38 boolean) binds: [B:30:0x0158, B:32:0x016d, B:34:0x0183] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean processDrag(android.view.MotionEvent r17) {
        /*
            Method dump skipped, instruction units count: 613
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.serenegiant.widget.ZoomAspectScaledTextureView.processDrag(android.view.MotionEvent):boolean");
    }

    private final void startCheck(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() > 1) {
            this.mPrimaryId = motionEvent.getPointerId(0);
            this.mPrimaryX = motionEvent.getX(0);
            this.mPrimaryY = motionEvent.getY(0);
            this.mSecondaryId = motionEvent.getPointerId(1);
            this.mSecondX = motionEvent.getX(1);
            this.mSecondY = motionEvent.getY(1);
            float fHypot = (float) Math.hypot(this.mSecondX - this.mPrimaryX, this.mSecondY - this.mPrimaryY);
            if (fHypot < MIN_DISTANCE) {
                return;
            }
            this.mTouchDistance = fHypot;
            this.mPivotX = (this.mPrimaryX + this.mSecondX) / 2.0f;
            this.mPivotY = (this.mPrimaryY + this.mSecondY) / 2.0f;
            if (this.mStartCheckRotate == null) {
                this.mStartCheckRotate = new StartCheckRotate();
            }
            postDelayed(this.mStartCheckRotate, CHECK_TIMEOUT);
            setState(3);
        }
    }

    private final void startZoom(MotionEvent motionEvent) {
        removeCallbacks(this.mStartCheckRotate);
        setState(4);
    }

    private final boolean processZoom(MotionEvent motionEvent) {
        restoreMatrix();
        float matrixScale = getMatrixScale();
        float fCalcScale = calcScale(motionEvent);
        float f = matrixScale * fCalcScale;
        if (f < this.mMinScale || f > DEFAULT_MAX_SCALE) {
            return false;
        }
        if (this.mImageMatrix.postScale(fCalcScale, fCalcScale, this.mPivotX, this.mPivotY)) {
            this.mImageMatrixChanged = true;
            setTransform(this.mImageMatrix);
        }
        return true;
    }

    private final float calcScale(MotionEvent motionEvent) {
        return ((float) Math.hypot(motionEvent.getX(0) - motionEvent.getX(1), motionEvent.getY(0) - motionEvent.getY(1))) / this.mTouchDistance;
    }

    private final boolean checkTouchMoved(MotionEvent motionEvent) {
        int iFindPointerIndex = motionEvent.findPointerIndex(this.mPrimaryId);
        int iFindPointerIndex2 = motionEvent.findPointerIndex(this.mSecondaryId);
        if (iFindPointerIndex < 0) {
            return true;
        }
        float x = motionEvent.getX(iFindPointerIndex) - this.mPrimaryX;
        float y = motionEvent.getY(iFindPointerIndex) - this.mPrimaryY;
        if ((x * x) + (y * y) >= MIN_DISTANCE_SQUARE) {
            return true;
        }
        if (iFindPointerIndex2 >= 0) {
            float x2 = motionEvent.getX(iFindPointerIndex2) - this.mSecondX;
            float y2 = motionEvent.getY(iFindPointerIndex2) - this.mSecondY;
            if ((x2 * x2) + (y2 * y2) >= MIN_DISTANCE_SQUARE) {
                return true;
            }
        }
        return false;
    }

    private final boolean processRotate(MotionEvent motionEvent) {
        if (checkTouchMoved(motionEvent)) {
            restoreMatrix();
            float fCalcAngle = calcAngle(motionEvent);
            this.mCurrentDegrees = fCalcAngle;
            boolean z = Math.abs((((float) ((int) (fCalcAngle / 360.0f))) * 360.0f) - fCalcAngle) > 0.1f;
            this.mIsRotating = z;
            if (z && this.mImageMatrix.postRotate(this.mCurrentDegrees, this.mPivotX, this.mPivotY)) {
                this.mImageMatrixChanged = true;
                setTransform(this.mImageMatrix);
                return true;
            }
        }
        return false;
    }

    private final float calcAngle(MotionEvent motionEvent) {
        int iFindPointerIndex = motionEvent.findPointerIndex(this.mPrimaryId);
        int iFindPointerIndex2 = motionEvent.findPointerIndex(this.mSecondaryId);
        if (iFindPointerIndex < 0 || iFindPointerIndex2 < 0) {
            return 0.0f;
        }
        float f = this.mSecondX - this.mPrimaryX;
        float f2 = this.mSecondY - this.mPrimaryY;
        float x = motionEvent.getX(iFindPointerIndex2) - motionEvent.getX(iFindPointerIndex);
        float y = motionEvent.getY(iFindPointerIndex2) - motionEvent.getY(iFindPointerIndex);
        return ((float) Math.acos(((double) dotProduct(f, f2, x, y)) / Math.sqrt(((f * f) + (f2 * f2)) * ((x * x) + (y * y))))) * 57.29578f * Math.signum(crossProduct(f, f2, x, y));
    }

    private static final float crossProduct(Vector vector, Vector vector2) {
        return (vector.f2263x * vector2.f2264y) - (vector2.f2263x * vector.f2264y);
    }

    private static final boolean ptInPoly(float f, float f2, float[] fArr) {
        int length = fArr.length & Integer.MAX_VALUE;
        if (length < 6) {
            return false;
        }
        Vector vector = new Vector();
        Vector vector2 = new Vector();
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            vector.set(f, f2).dec(fArr[i], fArr[i2]);
            int i3 = i + 2;
            if (i3 < length) {
                vector2.set(fArr[i3], fArr[i + 3]);
            } else {
                vector2.set(fArr[0], fArr[1]);
            }
            vector2.dec(fArr[i], fArr[i2]);
            if (crossProduct(vector, vector2) > 0.0f) {
                return false;
            }
            i = i3;
        }
        return true;
    }

    private static final class Vector {

        /* JADX INFO: renamed from: x */
        public float f2263x;

        /* JADX INFO: renamed from: y */
        public float f2264y;

        public Vector() {
        }

        public Vector(float f, float f2) {
            set(f, f2);
        }

        public Vector set(float f, float f2) {
            this.f2263x = f;
            this.f2264y = f2;
            return this;
        }

        public Vector sub(Vector vector) {
            return new Vector(this.f2263x - vector.f2263x, this.f2264y - vector.f2264y);
        }

        public Vector dec(float f, float f2) {
            this.f2263x -= f;
            this.f2264y -= f2;
            return this;
        }
    }

    private static final class LineSegment {

        /* JADX INFO: renamed from: p1 */
        public final Vector f2261p1;

        /* JADX INFO: renamed from: p2 */
        public final Vector f2262p2;

        public LineSegment(float f, float f2, float f3, float f4) {
            this.f2261p1 = new Vector(f, f2);
            this.f2262p2 = new Vector(f3, f4);
        }

        public LineSegment set(float f, float f2, float f3, float f4) {
            this.f2261p1.set(f, f2);
            this.f2262p2.set(f3, f4);
            return this;
        }
    }

    private static final boolean checkIntersect(LineSegment lineSegment, LineSegment[] lineSegmentArr) {
        int length = lineSegmentArr != null ? lineSegmentArr.length : 0;
        Vector vectorSub = lineSegment.f2262p2.sub(lineSegment.f2261p1);
        boolean z = false;
        for (int i = 0; i < length; i++) {
            z = crossProduct(vectorSub, lineSegmentArr[i].f2261p1.sub(lineSegment.f2261p1)) * crossProduct(vectorSub, lineSegmentArr[i].f2262p2.sub(lineSegment.f2261p1)) < 0.1f;
            if (z) {
                Vector vectorSub2 = lineSegmentArr[i].f2262p2.sub(lineSegmentArr[i].f2261p1);
                z = crossProduct(vectorSub2, lineSegment.f2261p1.sub(lineSegmentArr[i].f2261p1)) * crossProduct(vectorSub2, lineSegment.f2262p2.sub(lineSegmentArr[i].f2261p1)) < 0.1f;
                if (z) {
                    break;
                }
            }
        }
        return z;
    }

    private final float getMatrixScale() {
        updateMatrixCache();
        float[] fArr = this.mMatrixCache;
        float fMin = Math.min(fArr[0], fArr[0]);
        if (fMin <= 0.0f) {
            return 1.0f;
        }
        return fMin;
    }

    private final void restoreMatrix() {
        this.mImageMatrix.set(this.mSavedImageMatrix);
        this.mImageMatrixChanged = true;
    }

    private final boolean updateMatrixCache() {
        if (!this.mImageMatrixChanged) {
            return false;
        }
        this.mImageMatrix.getValues(this.mMatrixCache);
        this.mImageMatrixChanged = false;
        return true;
    }

    private void applyMirrorMode() {
        int i = this.mMirrorMode;
        if (i == 1) {
            setScaleX(-1.0f);
            setScaleY(1.0f);
        } else if (i == 2) {
            setScaleX(1.0f);
            setScaleY(-1.0f);
        } else if (i == 3) {
            setScaleX(-1.0f);
            setScaleY(-1.0f);
        } else {
            setScaleX(1.0f);
            setScaleY(1.0f);
        }
    }

    private final class WaitImageReset implements Runnable {
        private WaitImageReset() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ZoomAspectScaledTextureView.this.init();
        }
    }

    private final class StartCheckRotate implements Runnable {
        private StartCheckRotate() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ZoomAspectScaledTextureView.this.mState == 3) {
                ZoomAspectScaledTextureView.this.setState(5);
            }
        }
    }
}
