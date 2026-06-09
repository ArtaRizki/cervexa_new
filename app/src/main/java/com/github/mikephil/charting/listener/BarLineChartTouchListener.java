package com.github.mikephil.charting.listener;

import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

/* JADX INFO: loaded from: classes.dex */
public class BarLineChartTouchListener extends ChartTouchListener<BarLineChartBase<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>>> {
    private IDataSet mClosestDataSetToTouch;
    private MPPointF mDecelerationCurrentPoint;
    private long mDecelerationLastTime;
    private MPPointF mDecelerationVelocity;
    private float mDragTriggerDist;
    private Matrix mMatrix;
    private float mMinScalePointerDistance;
    private float mSavedDist;
    private Matrix mSavedMatrix;
    private float mSavedXDist;
    private float mSavedYDist;
    private MPPointF mTouchPointCenter;
    private MPPointF mTouchStartPoint;
    private VelocityTracker mVelocityTracker;

    public BarLineChartTouchListener(BarLineChartBase<? extends BarLineScatterCandleBubbleData<? extends IBarLineScatterCandleBubbleDataSet<? extends Entry>>> barLineChartBase, Matrix matrix, float f) {
        super(barLineChartBase);
        this.mMatrix = new Matrix();
        this.mSavedMatrix = new Matrix();
        this.mTouchStartPoint = MPPointF.getInstance(0.0f, 0.0f);
        this.mTouchPointCenter = MPPointF.getInstance(0.0f, 0.0f);
        this.mSavedXDist = 1.0f;
        this.mSavedYDist = 1.0f;
        this.mSavedDist = 1.0f;
        this.mDecelerationLastTime = 0L;
        this.mDecelerationCurrentPoint = MPPointF.getInstance(0.0f, 0.0f);
        this.mDecelerationVelocity = MPPointF.getInstance(0.0f, 0.0f);
        this.mMatrix = matrix;
        this.mDragTriggerDist = Utils.convertDpToPixel(f);
        this.mMinScalePointerDistance = Utils.convertDpToPixel(3.5f);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        VelocityTracker velocityTracker;
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        if (motionEvent.getActionMasked() == 3 && (velocityTracker = this.mVelocityTracker) != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
        if (this.mTouchMode == 0) {
            this.mGestureDetector.onTouchEvent(motionEvent);
        }
        if (!((BarLineChartBase) this.mChart).isDragEnabled() && !((BarLineChartBase) this.mChart).isScaleXEnabled() && !((BarLineChartBase) this.mChart).isScaleYEnabled()) {
            return true;
        }
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            startAction(motionEvent);
            stopDeceleration();
            saveTouchStart(motionEvent);
        } else if (action == 1) {
            VelocityTracker velocityTracker2 = this.mVelocityTracker;
            int pointerId = motionEvent.getPointerId(0);
            velocityTracker2.computeCurrentVelocity(1000, Utils.getMaximumFlingVelocity());
            float yVelocity = velocityTracker2.getYVelocity(pointerId);
            float xVelocity = velocityTracker2.getXVelocity(pointerId);
            if ((Math.abs(xVelocity) > Utils.getMinimumFlingVelocity() || Math.abs(yVelocity) > Utils.getMinimumFlingVelocity()) && this.mTouchMode == 1 && ((BarLineChartBase) this.mChart).isDragDecelerationEnabled()) {
                stopDeceleration();
                this.mDecelerationLastTime = AnimationUtils.currentAnimationTimeMillis();
                this.mDecelerationCurrentPoint.f1970x = motionEvent.getX();
                this.mDecelerationCurrentPoint.f1971y = motionEvent.getY();
                this.mDecelerationVelocity.f1970x = xVelocity;
                this.mDecelerationVelocity.f1971y = yVelocity;
                Utils.postInvalidateOnAnimation(this.mChart);
            }
            if (this.mTouchMode == 2 || this.mTouchMode == 3 || this.mTouchMode == 4 || this.mTouchMode == 5) {
                ((BarLineChartBase) this.mChart).calculateOffsets();
                ((BarLineChartBase) this.mChart).postInvalidate();
            }
            this.mTouchMode = 0;
            ((BarLineChartBase) this.mChart).enableScroll();
            VelocityTracker velocityTracker3 = this.mVelocityTracker;
            if (velocityTracker3 != null) {
                velocityTracker3.recycle();
                this.mVelocityTracker = null;
            }
            endAction(motionEvent);
        } else if (action != 2) {
            if (action == 3) {
                this.mTouchMode = 0;
                endAction(motionEvent);
            } else if (action != 5) {
                if (action == 6) {
                    Utils.velocityTrackerPointerUpCleanUpIfNecessary(motionEvent, this.mVelocityTracker);
                    this.mTouchMode = 5;
                }
            } else if (motionEvent.getPointerCount() >= 2) {
                ((BarLineChartBase) this.mChart).disableScroll();
                saveTouchStart(motionEvent);
                this.mSavedXDist = getXDist(motionEvent);
                this.mSavedYDist = getYDist(motionEvent);
                float fSpacing = spacing(motionEvent);
                this.mSavedDist = fSpacing;
                if (fSpacing > 10.0f) {
                    if (((BarLineChartBase) this.mChart).isPinchZoomEnabled()) {
                        this.mTouchMode = 4;
                    } else if (((BarLineChartBase) this.mChart).isScaleXEnabled() != ((BarLineChartBase) this.mChart).isScaleYEnabled()) {
                        this.mTouchMode = ((BarLineChartBase) this.mChart).isScaleXEnabled() ? 2 : 3;
                    } else {
                        this.mTouchMode = this.mSavedXDist > this.mSavedYDist ? 2 : 3;
                    }
                }
                midPoint(this.mTouchPointCenter, motionEvent);
            }
        } else if (this.mTouchMode == 1) {
            ((BarLineChartBase) this.mChart).disableScroll();
            performDrag(motionEvent);
        } else if (this.mTouchMode == 2 || this.mTouchMode == 3 || this.mTouchMode == 4) {
            ((BarLineChartBase) this.mChart).disableScroll();
            if (((BarLineChartBase) this.mChart).isScaleXEnabled() || ((BarLineChartBase) this.mChart).isScaleYEnabled()) {
                performZoom(motionEvent);
            }
        } else if (this.mTouchMode == 0 && Math.abs(distance(motionEvent.getX(), this.mTouchStartPoint.f1970x, motionEvent.getY(), this.mTouchStartPoint.f1971y)) > this.mDragTriggerDist) {
            if (((BarLineChartBase) this.mChart).hasNoDragOffset()) {
                if (!((BarLineChartBase) this.mChart).isFullyZoomedOut() && ((BarLineChartBase) this.mChart).isDragEnabled()) {
                    this.mTouchMode = 1;
                } else {
                    this.mLastGesture = ChartTouchListener.ChartGesture.DRAG;
                    if (((BarLineChartBase) this.mChart).isHighlightPerDragEnabled()) {
                        performHighlightDrag(motionEvent);
                    }
                }
            } else if (((BarLineChartBase) this.mChart).isDragEnabled()) {
                this.mLastGesture = ChartTouchListener.ChartGesture.DRAG;
                this.mTouchMode = 1;
            }
        }
        this.mMatrix = ((BarLineChartBase) this.mChart).getViewPortHandler().refresh(this.mMatrix, this.mChart, true);
        return true;
    }

    private void saveTouchStart(MotionEvent motionEvent) {
        this.mSavedMatrix.set(this.mMatrix);
        this.mTouchStartPoint.f1970x = motionEvent.getX();
        this.mTouchStartPoint.f1971y = motionEvent.getY();
        this.mClosestDataSetToTouch = ((BarLineChartBase) this.mChart).getDataSetByTouchPoint(motionEvent.getX(), motionEvent.getY());
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void performDrag(android.view.MotionEvent r5) {
        /*
            r4 = this;
            com.github.mikephil.charting.listener.ChartTouchListener$ChartGesture r0 = com.github.mikephil.charting.listener.ChartTouchListener.ChartGesture.DRAG
            r4.mLastGesture = r0
            android.graphics.Matrix r0 = r4.mMatrix
            android.graphics.Matrix r1 = r4.mSavedMatrix
            r0.set(r1)
            T extends com.github.mikephil.charting.charts.Chart<?> r0 = r4.mChart
            com.github.mikephil.charting.charts.BarLineChartBase r0 = (com.github.mikephil.charting.charts.BarLineChartBase) r0
            com.github.mikephil.charting.listener.OnChartGestureListener r0 = r0.getOnChartGestureListener()
            boolean r1 = r4.inverted()
            if (r1 == 0) goto L46
            T extends com.github.mikephil.charting.charts.Chart<?> r1 = r4.mChart
            boolean r1 = r1 instanceof com.github.mikephil.charting.charts.HorizontalBarChart
            if (r1 == 0) goto L32
            float r1 = r5.getX()
            com.github.mikephil.charting.utils.MPPointF r2 = r4.mTouchStartPoint
            float r2 = r2.f1970x
            float r1 = r1 - r2
            float r1 = -r1
            float r2 = r5.getY()
            com.github.mikephil.charting.utils.MPPointF r3 = r4.mTouchStartPoint
            float r3 = r3.f1971y
            goto L57
        L32:
            float r1 = r5.getX()
            com.github.mikephil.charting.utils.MPPointF r2 = r4.mTouchStartPoint
            float r2 = r2.f1970x
            float r1 = r1 - r2
            float r2 = r5.getY()
            com.github.mikephil.charting.utils.MPPointF r3 = r4.mTouchStartPoint
            float r3 = r3.f1971y
            float r2 = r2 - r3
            float r2 = -r2
            goto L58
        L46:
            float r1 = r5.getX()
            com.github.mikephil.charting.utils.MPPointF r2 = r4.mTouchStartPoint
            float r2 = r2.f1970x
            float r1 = r1 - r2
            float r2 = r5.getY()
            com.github.mikephil.charting.utils.MPPointF r3 = r4.mTouchStartPoint
            float r3 = r3.f1971y
        L57:
            float r2 = r2 - r3
        L58:
            android.graphics.Matrix r3 = r4.mMatrix
            r3.postTranslate(r1, r2)
            if (r0 == 0) goto L62
            r0.onChartTranslate(r5, r1, r2)
        L62:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.listener.BarLineChartTouchListener.performDrag(android.view.MotionEvent):void");
    }

    private void performZoom(MotionEvent motionEvent) {
        boolean zCanZoomInMoreY;
        boolean zCanZoomInMoreX;
        boolean zCanZoomInMoreX2;
        boolean zCanZoomInMoreY2;
        if (motionEvent.getPointerCount() >= 2) {
            OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
            float fSpacing = spacing(motionEvent);
            if (fSpacing > this.mMinScalePointerDistance) {
                MPPointF trans = getTrans(this.mTouchPointCenter.f1970x, this.mTouchPointCenter.f1971y);
                ViewPortHandler viewPortHandler = ((BarLineChartBase) this.mChart).getViewPortHandler();
                if (this.mTouchMode == 4) {
                    this.mLastGesture = ChartTouchListener.ChartGesture.PINCH_ZOOM;
                    float f = fSpacing / this.mSavedDist;
                    boolean z = f < 1.0f;
                    if (z) {
                        zCanZoomInMoreX2 = viewPortHandler.canZoomOutMoreX();
                    } else {
                        zCanZoomInMoreX2 = viewPortHandler.canZoomInMoreX();
                    }
                    if (z) {
                        zCanZoomInMoreY2 = viewPortHandler.canZoomOutMoreY();
                    } else {
                        zCanZoomInMoreY2 = viewPortHandler.canZoomInMoreY();
                    }
                    float f2 = ((BarLineChartBase) this.mChart).isScaleXEnabled() ? f : 1.0f;
                    float f3 = ((BarLineChartBase) this.mChart).isScaleYEnabled() ? f : 1.0f;
                    if (zCanZoomInMoreY2 || zCanZoomInMoreX2) {
                        this.mMatrix.set(this.mSavedMatrix);
                        this.mMatrix.postScale(f2, f3, trans.f1970x, trans.f1971y);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.onChartScale(motionEvent, f2, f3);
                        }
                    }
                } else if (this.mTouchMode == 2 && ((BarLineChartBase) this.mChart).isScaleXEnabled()) {
                    this.mLastGesture = ChartTouchListener.ChartGesture.X_ZOOM;
                    float xDist = getXDist(motionEvent) / this.mSavedXDist;
                    if (xDist < 1.0f) {
                        zCanZoomInMoreX = viewPortHandler.canZoomOutMoreX();
                    } else {
                        zCanZoomInMoreX = viewPortHandler.canZoomInMoreX();
                    }
                    if (zCanZoomInMoreX) {
                        this.mMatrix.set(this.mSavedMatrix);
                        this.mMatrix.postScale(xDist, 1.0f, trans.f1970x, trans.f1971y);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.onChartScale(motionEvent, xDist, 1.0f);
                        }
                    }
                } else if (this.mTouchMode == 3 && ((BarLineChartBase) this.mChart).isScaleYEnabled()) {
                    this.mLastGesture = ChartTouchListener.ChartGesture.Y_ZOOM;
                    float yDist = getYDist(motionEvent) / this.mSavedYDist;
                    if (yDist < 1.0f) {
                        zCanZoomInMoreY = viewPortHandler.canZoomOutMoreY();
                    } else {
                        zCanZoomInMoreY = viewPortHandler.canZoomInMoreY();
                    }
                    if (zCanZoomInMoreY) {
                        this.mMatrix.set(this.mSavedMatrix);
                        this.mMatrix.postScale(1.0f, yDist, trans.f1970x, trans.f1971y);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.onChartScale(motionEvent, 1.0f, yDist);
                        }
                    }
                }
                MPPointF.recycleInstance(trans);
            }
        }
    }

    private void performHighlightDrag(MotionEvent motionEvent) {
        Highlight highlightByTouchPoint = ((BarLineChartBase) this.mChart).getHighlightByTouchPoint(motionEvent.getX(), motionEvent.getY());
        if (highlightByTouchPoint == null || highlightByTouchPoint.equalTo(this.mLastHighlighted)) {
            return;
        }
        this.mLastHighlighted = highlightByTouchPoint;
        ((BarLineChartBase) this.mChart).highlightValue(highlightByTouchPoint, true);
    }

    private static void midPoint(MPPointF mPPointF, MotionEvent motionEvent) {
        float x = motionEvent.getX(0) + motionEvent.getX(1);
        float y = motionEvent.getY(0) + motionEvent.getY(1);
        mPPointF.f1970x = x / 2.0f;
        mPPointF.f1971y = y / 2.0f;
    }

    private static float spacing(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    private static float getXDist(MotionEvent motionEvent) {
        return Math.abs(motionEvent.getX(0) - motionEvent.getX(1));
    }

    private static float getYDist(MotionEvent motionEvent) {
        return Math.abs(motionEvent.getY(0) - motionEvent.getY(1));
    }

    public MPPointF getTrans(float f, float f2) {
        float f3;
        ViewPortHandler viewPortHandler = ((BarLineChartBase) this.mChart).getViewPortHandler();
        float fOffsetLeft = f - viewPortHandler.offsetLeft();
        if (inverted()) {
            f3 = -(f2 - viewPortHandler.offsetTop());
        } else {
            f3 = -((((BarLineChartBase) this.mChart).getMeasuredHeight() - f2) - viewPortHandler.offsetBottom());
        }
        return MPPointF.getInstance(fOffsetLeft, f3);
    }

    private boolean inverted() {
        return (this.mClosestDataSetToTouch == null && ((BarLineChartBase) this.mChart).isAnyAxisInverted()) || (this.mClosestDataSetToTouch != null && ((BarLineChartBase) this.mChart).isInverted(this.mClosestDataSetToTouch.getAxisDependency()));
    }

    public Matrix getMatrix() {
        return this.mMatrix;
    }

    public void setDragTriggerDist(float f) {
        this.mDragTriggerDist = Utils.convertDpToPixel(f);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        this.mLastGesture = ChartTouchListener.ChartGesture.DOUBLE_TAP;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartDoubleTapped(motionEvent);
        }
        if (((BarLineChartBase) this.mChart).isDoubleTapToZoomEnabled() && ((BarLineScatterCandleBubbleData) ((BarLineChartBase) this.mChart).getData()).getEntryCount() > 0) {
            MPPointF trans = getTrans(motionEvent.getX(), motionEvent.getY());
            ((BarLineChartBase) this.mChart).zoom(((BarLineChartBase) this.mChart).isScaleXEnabled() ? 1.4f : 1.0f, ((BarLineChartBase) this.mChart).isScaleYEnabled() ? 1.4f : 1.0f, trans.f1970x, trans.f1971y);
            if (((BarLineChartBase) this.mChart).isLogEnabled()) {
                Log.i("BarlineChartTouch", "Double-Tap, Zooming In, x: " + trans.f1970x + ", y: " + trans.f1971y);
            }
            MPPointF.recycleInstance(trans);
        }
        return super.onDoubleTap(motionEvent);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        this.mLastGesture = ChartTouchListener.ChartGesture.LONG_PRESS;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartLongPressed(motionEvent);
        }
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        this.mLastGesture = ChartTouchListener.ChartGesture.SINGLE_TAP;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartSingleTapped(motionEvent);
        }
        if (!((BarLineChartBase) this.mChart).isHighlightPerTapEnabled()) {
            return false;
        }
        performHighlight(((BarLineChartBase) this.mChart).getHighlightByTouchPoint(motionEvent.getX(), motionEvent.getY()), motionEvent);
        return super.onSingleTapUp(motionEvent);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.mLastGesture = ChartTouchListener.ChartGesture.FLING;
        OnChartGestureListener onChartGestureListener = ((BarLineChartBase) this.mChart).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.onChartFling(motionEvent, motionEvent2, f, f2);
        }
        return super.onFling(motionEvent, motionEvent2, f, f2);
    }

    public void stopDeceleration() {
        this.mDecelerationVelocity.f1970x = 0.0f;
        this.mDecelerationVelocity.f1971y = 0.0f;
    }

    public void computeScroll() {
        if (this.mDecelerationVelocity.f1970x == 0.0f && this.mDecelerationVelocity.f1971y == 0.0f) {
            return;
        }
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        this.mDecelerationVelocity.f1970x *= ((BarLineChartBase) this.mChart).getDragDecelerationFrictionCoef();
        this.mDecelerationVelocity.f1971y *= ((BarLineChartBase) this.mChart).getDragDecelerationFrictionCoef();
        float f = (jCurrentAnimationTimeMillis - this.mDecelerationLastTime) / 1000.0f;
        float f2 = this.mDecelerationVelocity.f1970x * f;
        float f3 = this.mDecelerationVelocity.f1971y * f;
        this.mDecelerationCurrentPoint.f1970x += f2;
        this.mDecelerationCurrentPoint.f1971y += f3;
        MotionEvent motionEventObtain = MotionEvent.obtain(jCurrentAnimationTimeMillis, jCurrentAnimationTimeMillis, 2, this.mDecelerationCurrentPoint.f1970x, this.mDecelerationCurrentPoint.f1971y, 0);
        performDrag(motionEventObtain);
        motionEventObtain.recycle();
        this.mMatrix = ((BarLineChartBase) this.mChart).getViewPortHandler().refresh(this.mMatrix, this.mChart, false);
        this.mDecelerationLastTime = jCurrentAnimationTimeMillis;
        if (Math.abs(this.mDecelerationVelocity.f1970x) >= 0.01d || Math.abs(this.mDecelerationVelocity.f1971y) >= 0.01d) {
            Utils.postInvalidateOnAnimation(this.mChart);
            return;
        }
        ((BarLineChartBase) this.mChart).calculateOffsets();
        ((BarLineChartBase) this.mChart).postInvalidate();
        stopDeceleration();
    }
}
