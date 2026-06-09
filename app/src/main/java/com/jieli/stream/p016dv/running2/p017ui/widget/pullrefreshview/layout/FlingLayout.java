package com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.layout;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.Scroller;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.support.impl.Pullable;
import com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.support.utils.CanPullUtil;
import com.nineoldandroids.view.ViewHelper;

/* JADX INFO: loaded from: classes.dex */
public class FlingLayout extends FrameLayout implements NestedScrollingChild, NestedScrollingParent {
    private static final int MAX_DURATION = 600;
    private static final int MIN_DURATION = 300;
    public static final int SCROLL_STATE_FLING = 2;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_TOUCH_SCROLL = 1;
    protected int MAXDISTANCE;
    private boolean canPullDown;
    private boolean canPullUp;
    protected float downX;
    protected float downY;
    private boolean isScrolling;
    private NestedScrollingChildHelper mChildHelper;
    protected OnScrollListener mOnScrollListener;
    private NestedScrollingParentHelper mParentHelper;
    int mPointerId;
    protected View mPullView;
    private Scroller mScroller;
    private int mTouchSlop;
    protected int maxDistance;
    float moveY;
    protected Pullable pullable;
    private int stateType;
    private int tempStateType;
    protected float tepmX;
    protected float tepmY;
    protected int version;

    public interface OnScrollListener {
        void onScroll(FlingLayout flingLayout, float f);

        void onScrollChange(FlingLayout flingLayout, int i);
    }

    protected boolean onScroll(float f) {
        return false;
    }

    protected void onScrollChange(int i) {
    }

    protected boolean onStartFling(float f) {
        return false;
    }

    public View getPullView() {
        return this.mPullView;
    }

    public FlingLayout(Context context) {
        this(context, null);
    }

    public FlingLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlingLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.stateType = 0;
        this.tempStateType = 0;
        this.isScrolling = false;
        this.canPullUp = true;
        this.canPullDown = true;
        this.maxDistance = 0;
        this.MAXDISTANCE = 0;
        this.moveY = 0.0f;
        init(context);
    }

    public void init(Context context) {
        this.version = Build.VERSION.SDK_INT;
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mScroller = new Scroller(context, new AccelerateDecelerateInterpolator());
        this.mParentHelper = new NestedScrollingParentHelper(this);
        this.mChildHelper = new NestedScrollingChildHelper(this);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.MAXDISTANCE = (getMeasuredHeight() * 3) / 5;
    }

    @Override // android.view.View
    public void computeScroll() {
        if (!this.mScroller.isFinished()) {
            if (this.mScroller.computeScrollOffset()) {
                moveTo(this.mScroller.getCurrY());
                ViewCompat.postInvalidateOnAnimation(this);
            } else if (this.stateType == 2) {
                setScrollState(0);
            }
        } else if (this.stateType == 2) {
            setScrollState(0);
        }
        super.computeScroll();
    }

    private boolean canPullUp() {
        Pullable pullable;
        if (this.mPullView == null || (pullable = this.pullable) == null) {
            return this.canPullUp;
        }
        return this.canPullUp && pullable.isGetBottom();
    }

    private boolean canPullDown() {
        Pullable pullable;
        if (this.mPullView == null || (pullable = this.pullable) == null) {
            return this.canPullDown;
        }
        return this.canPullDown && pullable.isGetTop();
    }

    private void moveTo(float f) {
        setMoveY(f);
        setScrollState(this.tempStateType);
        boolean zOnScroll = onScroll(f);
        OnScrollListener onScrollListener = this.mOnScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScroll(this, f);
        }
        if (zOnScroll) {
            return;
        }
        setViewTranslationY(this.mPullView, f);
    }

    private void setScrollState(int i) {
        if (this.stateType != i) {
            this.stateType = i;
            this.tempStateType = i;
            onScrollChange(i);
            OnScrollListener onScrollListener = this.mOnScrollListener;
            if (onScrollListener != null) {
                onScrollListener.onScrollChange(this, i);
            }
        }
    }

    private void moveBy(float f) {
        moveTo(getMoveY() + f);
    }

    protected static void setViewTranslationY(View view, float f) {
        if (view == null) {
            return;
        }
        ViewHelper.setTranslationY(view, f);
    }

    private void setMoveY(float f) {
        this.moveY = f;
    }

    public float getMoveY() {
        return this.moveY;
    }

    public int startMoveBy(float f, float f2) {
        setScrollState(2);
        int iMax = Math.max(300, Math.min(600, (int) Math.abs(f2)));
        this.mScroller.startScroll(0, (int) f, 0, (int) f2, iMax);
        invalidate();
        return iMax;
    }

    public int startMoveTo(float f, float f2) {
        return startMoveBy(f, f2 - f);
    }

    private void startFling() {
        float moveY = getMoveY();
        if (moveY != 0.0f) {
            if (onStartFling(moveY)) {
                return;
            }
            startMoveTo(moveY, 0.0f);
            return;
        }
        setScrollState(0);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (this.mPullView == null) {
            Pullable pullAble = CanPullUtil.getPullAble(view);
            this.pullable = pullAble;
            if (pullAble != null) {
                this.mPullView = view;
            }
        }
        super.addView(view, i, layoutParams);
    }

    public void setMaxDistance(int i) {
        this.maxDistance = i;
    }

    public void setCanPullDown(boolean z) {
        this.canPullDown = z;
        if (z || getMoveY() <= 0.0f) {
            return;
        }
        moveTo(0.0f);
    }

    public void setCanPullUp(boolean z) {
        this.canPullUp = z;
        if (z || getMoveY() >= 0.0f) {
            return;
        }
        moveTo(0.0f);
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x013a  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r10) {
        /*
            Method dump skipped, instruction units count: 366
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.layout.FlingLayout.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        View view = this.mPullView;
        if (view == null || ViewCompat.isNestedScrollingEnabled(view) || motionEvent.getAction() != 0) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onStartNestedScroll(View view, View view2, int i) {
        if (!isNestedScrollingEnabled()) {
            setNestedScrollingEnabled(true);
        }
        return (i & 2) != 0;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.mParentHelper.onNestedScrollAccepted(view, view2, i);
        startNestedScroll(2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onStopNestedScroll(View view) {
        startFling();
        stopNestedScroll();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        dispatchNestedScroll(0, i2, 0, i4, new int[2]);
        moveBy((-i4) - r7[1]);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0075  */
    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onNestedPreScroll(android.view.View r8, int r9, int r10, int[] r11) {
        /*
            r7 = this;
            r8 = 1
            r7.tempStateType = r8
            float r9 = r7.getMoveY()
            android.view.View r0 = r7.mPullView
            r1 = 0
            r2 = 0
            if (r0 == 0) goto La3
            r0 = 0
            int r3 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r3 != 0) goto L14
            goto La3
        L14:
            r11[r2] = r2
            r7.stopNestedScroll()
            int r4 = -r10
            int r5 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r5 >= 0) goto L24
            float r6 = (float) r4
            float r6 = r6 + r9
            int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r6 >= 0) goto L2c
        L24:
            if (r3 <= 0) goto L47
            float r6 = (float) r4
            float r6 = r6 + r9
            int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r6 > 0) goto L47
        L2c:
            r7.moveTo(r0)
            r3 = 2
            r7.startNestedScroll(r3)
            float r9 = r9 - r0
            int r9 = (int) r9
            r11[r8] = r9
            int[] r9 = new int[r3]
            r0 = r11[r8]
            int r10 = r10 - r0
            r7.dispatchNestedPreScroll(r2, r10, r9, r1)
            r10 = r11[r8]
            r9 = r9[r8]
            int r10 = r10 + r9
            r11[r8] = r10
            goto La6
        L47:
            if (r3 <= 0) goto L4b
            if (r4 > 0) goto L4f
        L4b:
            if (r5 >= 0) goto L9c
            if (r4 >= 0) goto L9c
        L4f:
            int r0 = r7.maxDistance
            if (r0 == 0) goto L75
            float r0 = java.lang.Math.abs(r9)
            int r1 = r7.maxDistance
            float r2 = (float) r1
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L5f
            goto L75
        L5f:
            float r0 = (float) r1
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 <= 0) goto L69
            float r9 = (float) r1
            r7.moveTo(r9)
            goto L99
        L69:
            int r0 = -r1
            float r0 = (float) r0
            int r9 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r9 >= 0) goto L99
            int r9 = -r1
            float r9 = (float) r9
            r7.moveTo(r9)
            goto L99
        L75:
            int r0 = r4 / 2
            int r1 = r7.maxDistance
            if (r1 != 0) goto L86
            int r1 = -r0
            float r1 = (float) r1
            float r9 = java.lang.Math.abs(r9)
            float r1 = r1 * r9
            int r9 = r7.MAXDISTANCE
            goto L90
        L86:
            int r1 = -r0
            float r1 = (float) r1
            float r9 = java.lang.Math.abs(r9)
            float r1 = r1 * r9
            int r9 = r7.maxDistance
        L90:
            float r9 = (float) r9
            float r1 = r1 / r9
            int r9 = (int) r1
            int r9 = r9 - r0
            int r9 = r9 + r4
            float r9 = (float) r9
            r7.moveBy(r9)
        L99:
            r11[r8] = r10
            goto La6
        L9c:
            float r9 = (float) r4
            r7.moveBy(r9)
            r11[r8] = r10
            goto La6
        La3:
            r7.dispatchNestedPreScroll(r2, r10, r11, r1)
        La6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.layout.FlingLayout.onNestedPreScroll(android.view.View, int, int, int[]):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        return dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(View view, float f, float f2) {
        if (dispatchNestedPreFling(f, f2)) {
            return true;
        }
        Pullable pullAble = CanPullUtil.getPullAble(view);
        if (pullAble == null) {
            return false;
        }
        if (!pullAble.isGetBottom() || f2 >= 0.0f) {
            return pullAble.isGetTop() && f2 > 0.0f;
        }
        return true;
    }

    @Override // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        this.mChildHelper.setNestedScrollingEnabled(z);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return this.mChildHelper.isNestedScrollingEnabled();
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean startNestedScroll(int i) {
        return this.mChildHelper.startNestedScroll(i);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void stopNestedScroll() {
        this.mChildHelper.stopNestedScroll();
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.mChildHelper.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.mChildHelper.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.mChildHelper.dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.mChildHelper.dispatchNestedPreFling(f, f2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mChildHelper.onDetachedFromWindow();
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }
}
