package com.generalplus.GoPlusDrone.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

/* JADX INFO: loaded from: classes.dex */
public class MultiTouchZoomableImageView extends BaseZoomableImageView {
    private GestureDetector mGestureDetector;
    private ScaleGestureDetector mScaleDetector;
    private boolean scaleRecognized;
    protected boolean transIgnoreScale;

    public MultiTouchZoomableImageView(Context context) {
        super(context);
        this.transIgnoreScale = false;
        this.scaleRecognized = false;
        initMultiTouchZoomableImageView(context);
    }

    public MultiTouchZoomableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.transIgnoreScale = false;
        this.scaleRecognized = false;
        initMultiTouchZoomableImageView(context);
    }

    protected void initMultiTouchZoomableImageView(Context context) {
        this.mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        this.mGestureDetector = new GestureDetector(context, new MyGestureListener());
    }

    class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        ScaleListener() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            if (scaleGestureDetector == null || !scaleGestureDetector.isInProgress()) {
                return false;
            }
            try {
                MultiTouchZoomableImageView.this.zoomTo(Math.min(MultiTouchZoomableImageView.this.maxZoom(), Math.max(MultiTouchZoomableImageView.this.getScale() * scaleGestureDetector.getScaleFactor(), 1.0f)), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                MultiTouchZoomableImageView.this.invalidate();
                MultiTouchZoomableImageView.this.scaleRecognized = true;
                return true;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private MyGestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (MultiTouchZoomableImageView.this.mImageGestureListener != null) {
                MultiTouchZoomableImageView.this.mImageGestureListener.onImageGestureSingleTapConfirmed();
                return false;
            }
            return super.onSingleTapConfirmed(motionEvent);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            if (MultiTouchZoomableImageView.this.mImageGestureListener == null || MultiTouchZoomableImageView.this.scaleRecognized) {
                return;
            }
            MultiTouchZoomableImageView.this.mImageGestureListener.onImageGestureLongPress();
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (motionEvent != null) {
                try {
                    if (motionEvent.getPointerCount() <= 1) {
                    }
                    return false;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
            if ((motionEvent2 == null || motionEvent2.getPointerCount() <= 1) && (MultiTouchZoomableImageView.this.mScaleDetector == null || !MultiTouchZoomableImageView.this.mScaleDetector.isInProgress())) {
                if (MultiTouchZoomableImageView.this.transIgnoreScale || MultiTouchZoomableImageView.this.getScale() > MultiTouchZoomableImageView.this.zoomDefault()) {
                    MultiTouchZoomableImageView.this.stopFling();
                    MultiTouchZoomableImageView.this.postTranslate(-f, -f2);
                    if (MultiTouchZoomableImageView.this.isScrollOver(f)) {
                        if (MultiTouchZoomableImageView.this.mViewPager != null) {
                            MultiTouchZoomableImageView.this.mViewPager.requestDisallowInterceptTouchEvent(false);
                        }
                    } else if (MultiTouchZoomableImageView.this.mViewPager != null) {
                        MultiTouchZoomableImageView.this.mViewPager.requestDisallowInterceptTouchEvent(true);
                    }
                    MultiTouchZoomableImageView.this.center(true, true, false);
                } else if (MultiTouchZoomableImageView.this.mViewPager != null) {
                    MultiTouchZoomableImageView.this.mViewPager.requestDisallowInterceptTouchEvent(false);
                }
                return true;
            }
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (MultiTouchZoomableImageView.this.getScale() != MultiTouchZoomableImageView.this.zoomDefault()) {
                MultiTouchZoomableImageView multiTouchZoomableImageView = MultiTouchZoomableImageView.this;
                multiTouchZoomableImageView.zoomTo(multiTouchZoomableImageView.zoomDefault());
                return true;
            }
            MultiTouchZoomableImageView multiTouchZoomableImageView2 = MultiTouchZoomableImageView.this;
            multiTouchZoomableImageView2.zoomTo(multiTouchZoomableImageView2.zoomDefault() * 3.0f, motionEvent.getX(), motionEvent.getY(), 200.0f);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if ((motionEvent != null && motionEvent.getPointerCount() > 1) || ((motionEvent2 != null && motionEvent2.getPointerCount() > 1) || MultiTouchZoomableImageView.this.mScaleDetector.isInProgress())) {
                return false;
            }
            if (motionEvent.getX() - motionEvent2.getX() > 100.0f && Math.abs(f) > 200.0f) {
                Log.i("MultiTouchZoomableImageView", "Fling Left");
            } else if (motionEvent2.getX() - motionEvent.getX() > 100.0f && Math.abs(f) > 200.0f) {
                Log.i("MultiTouchZoomableImageView", "Fling Right");
            } else if (motionEvent.getY() - motionEvent2.getY() > 100.0f && Math.abs(f2) > 200.0f) {
                Log.i("MultiTouchZoomableImageView", "Fling Up");
            } else if (motionEvent2.getY() - motionEvent.getY() > 100.0f && Math.abs(f2) > 200.0f) {
                Log.i("MultiTouchZoomableImageView", "Fling Down");
                if (!MultiTouchZoomableImageView.this.transIgnoreScale && MultiTouchZoomableImageView.this.getScale() <= MultiTouchZoomableImageView.this.zoomDefault()) {
                    MultiTouchZoomableImageView.this.mImageGestureListener.onImageGestureFlingDown();
                    return true;
                }
            }
            try {
                float x = motionEvent2.getX() - motionEvent.getX();
                float y = motionEvent2.getY() - motionEvent.getY();
                if (Math.abs(f) > 800.0f || Math.abs(f2) > 800.0f) {
                    MultiTouchZoomableImageView.this.scrollBy(x / 2.0f, y / 2.0f, 300.0f);
                    MultiTouchZoomableImageView.this.invalidate();
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (NullPointerException unused) {
            }
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0019 A[Catch: Exception -> 0x003d, TryCatch #0 {Exception -> 0x003d, blocks: (B:3:0x0001, B:5:0x0006, B:12:0x0013, B:13:0x0019, B:14:0x0020, B:16:0x0024, B:18:0x0031, B:19:0x0037), top: B:24:0x0001 }] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            r0 = 1
            androidx.viewpager.widget.ViewPager r1 = r4.mViewPager     // Catch: java.lang.Exception -> L3d
            r2 = 0
            if (r1 == 0) goto L20
            int r1 = r5.getAction()     // Catch: java.lang.Exception -> L3d
            if (r1 == r0) goto L19
            r3 = 2
            if (r1 == r3) goto L13
            r3 = 3
            if (r1 == r3) goto L19
            goto L20
        L13:
            androidx.viewpager.widget.ViewPager r1 = r4.mViewPager     // Catch: java.lang.Exception -> L3d
            r1.requestDisallowInterceptTouchEvent(r0)     // Catch: java.lang.Exception -> L3d
            goto L20
        L19:
            androidx.viewpager.widget.ViewPager r1 = r4.mViewPager     // Catch: java.lang.Exception -> L3d
            r1.requestDisallowInterceptTouchEvent(r2)     // Catch: java.lang.Exception -> L3d
            r4.scaleRecognized = r2     // Catch: java.lang.Exception -> L3d
        L20:
            android.graphics.Bitmap r1 = r4.mBitmap     // Catch: java.lang.Exception -> L3d
            if (r1 == 0) goto L37
            android.view.ScaleGestureDetector r1 = r4.mScaleDetector     // Catch: java.lang.Exception -> L3d
            r1.onTouchEvent(r5)     // Catch: java.lang.Exception -> L3d
            android.view.ScaleGestureDetector r1 = r4.mScaleDetector     // Catch: java.lang.Exception -> L3d
            boolean r1 = r1.isInProgress()     // Catch: java.lang.Exception -> L3d
            if (r1 != 0) goto L41
            android.view.GestureDetector r1 = r4.mGestureDetector     // Catch: java.lang.Exception -> L3d
            r1.onTouchEvent(r5)     // Catch: java.lang.Exception -> L3d
            goto L41
        L37:
            com.generalplus.GoPlusDrone.View.ImageGestureListener r5 = r4.mImageGestureListener     // Catch: java.lang.Exception -> L3d
            r5.onImageGestureSingleTapConfirmed()     // Catch: java.lang.Exception -> L3d
            return r2
        L3d:
            r5 = move-exception
            r5.printStackTrace()
        L41:
            return r0
        */
        return false;
    }
}

