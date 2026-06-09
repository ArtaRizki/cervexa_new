package com.serenegiant.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.serenegiant.common.C1831R;
import com.serenegiant.utils.BuildCheck;

/* JADX INFO: loaded from: classes2.dex */
public class TwoPainViewGroup extends FrameLayout {
    private static final int DEFAULT_CHILD_GRAVITY = 17;
    private static final int DEFAULT_HEIGHT = 200;
    private static final float DEFAULT_SUB_WINDOW_SCALE = 0.2f;
    private static final int DEFAULT_WIDTH = 200;
    public static final int HORIZONTAL = 0;
    public static final int MODE_SELECT_1 = 1;
    public static final int MODE_SELECT_2 = 2;
    public static final int MODE_SINGLE_1 = 3;
    public static final int MODE_SINGLE_2 = 4;
    public static final int MODE_SPLIT = 0;
    private static final String TAG = TwoPainViewGroup.class.getSimpleName();
    public static final int VERTICAL = 1;
    private ObjectAnimator mAnimator;
    private final Animator.AnimatorListener mAnimatorListener;
    private View mChild1;
    private View mChild2;
    private final Rect mChildRect;
    private int mDisplayMode;
    private boolean mEnableSubWindow;
    private boolean mFlipChildPos;
    private int mOrientation;
    private float mSubWindowScale;
    private final Object mSync;

    public TwoPainViewGroup(Context context) {
        this(context, null, 0);
    }

    public TwoPainViewGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TwoPainViewGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSync = new Object();
        this.mChildRect = new Rect();
        this.mAnimatorListener = new Animator.AnimatorListener() { // from class: com.serenegiant.widget.TwoPainViewGroup.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                synchronized (TwoPainViewGroup.this.mSync) {
                    TwoPainViewGroup.this.mAnimator = null;
                }
                TwoPainViewGroup.this.requestLayout();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                synchronized (TwoPainViewGroup.this.mSync) {
                    TwoPainViewGroup.this.mAnimator = null;
                }
                TwoPainViewGroup.this.requestLayout();
            }
        };
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1831R.styleable.TwoPainViewGroup, i, 0);
        this.mOrientation = typedArrayObtainStyledAttributes.getInt(C1831R.styleable.TwoPainViewGroup_orientation, 0);
        this.mDisplayMode = typedArrayObtainStyledAttributes.getInt(C1831R.styleable.TwoPainViewGroup_displayMode, 0);
        this.mEnableSubWindow = typedArrayObtainStyledAttributes.getBoolean(C1831R.styleable.TwoPainViewGroup_enableSubWindow, true);
        this.mFlipChildPos = typedArrayObtainStyledAttributes.getBoolean(C1831R.styleable.TwoPainViewGroup_flipChildPos, false);
        float f = typedArrayObtainStyledAttributes.getFloat(C1831R.styleable.TwoPainViewGroup_subWindowScale, 0.2f);
        this.mSubWindowScale = f;
        if (f <= 0.0f || f >= 1.0f) {
            this.mSubWindowScale = 0.2f;
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() >= 2) {
            throw new IllegalStateException("Can't add more than 2 views to a ViewSwitcher");
        }
        super.addView(view, i, layoutParams);
        int childCount = getChildCount();
        if (childCount > 0 && this.mChild1 == null) {
            this.mChild1 = getChildAt(0);
        }
        if (childCount <= 1 || this.mChild2 != null) {
            return;
        }
        this.mChild2 = getChildAt(1);
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view == this.mChild1) {
            this.mChild1 = null;
        } else if (view == this.mChild2) {
            this.mChild2 = null;
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(TwoPainViewGroup.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(TwoPainViewGroup.class.getName());
    }

    public void setOrientation(int i) {
        synchronized (this.mSync) {
            if (this.mOrientation != i % 2) {
                this.mOrientation = i % 2;
                startLayout();
            }
        }
    }

    public int getOrientation() {
        int i;
        synchronized (this.mSync) {
            i = this.mOrientation;
        }
        return i;
    }

    public void setEnableSubWindow(boolean z) {
        synchronized (this.mSync) {
            if (this.mEnableSubWindow != z) {
                this.mEnableSubWindow = z;
                startLayout();
            }
        }
    }

    public boolean getEnableSubWindow() {
        boolean z;
        synchronized (this.mSync) {
            z = this.mEnableSubWindow;
        }
        return z;
    }

    public void setDisplayMode(int i) {
        synchronized (this.mSync) {
            if (this.mDisplayMode != i) {
                this.mDisplayMode = i;
                startLayout();
            }
        }
    }

    public int getDisplayMode() {
        int i;
        synchronized (this.mSync) {
            i = this.mDisplayMode;
        }
        return i;
    }

    public void setSubWindowScale(float f) {
        if (f <= 0.0f || f >= 1.0f) {
            f = 0.2f;
        }
        synchronized (this.mSync) {
            if (f != this.mSubWindowScale) {
                this.mSubWindowScale = f;
                startLayout();
            }
        }
    }

    public float getSubWindowScale() {
        float f;
        synchronized (this.mSync) {
            f = this.mSubWindowScale;
        }
        return f;
    }

    public void setFlipChildPos(boolean z) {
        synchronized (this.mSync) {
            if (z != this.mFlipChildPos) {
                this.mFlipChildPos = z;
                startLayout();
            }
        }
    }

    public boolean getFlipChildPos() {
        boolean z;
        synchronized (this.mSync) {
            z = this.mFlipChildPos;
        }
        return z;
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        View view;
        if (View.MeasureSpec.getMode(i) == 1073741824) {
            View.MeasureSpec.getMode(i2);
        }
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (this.mDisplayMode == 0) {
            if (this.mOrientation == 1) {
                size2 >>>= 1;
            } else {
                size >>>= 1;
            }
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, View.MeasureSpec.getMode(i));
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(size2, View.MeasureSpec.getMode(i2));
        int childCount = getChildCount();
        int iCombineMeasuredStates = 0;
        int iMax = 0;
        int iMax2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                int i4 = iCombineMeasuredStates;
                measureChildWithMargins(childAt, iMakeMeasureSpec, 0, iMakeMeasureSpec2, 0);
                int i5 = this.mDisplayMode;
                if (i5 != 0) {
                    if (i5 == 1 || i5 == 3) {
                        view = childAt;
                        if (view != this.mChild1) {
                        }
                    } else {
                        view = childAt;
                    }
                    int i6 = this.mDisplayMode;
                    if ((i6 == 2 || i6 == 3) && view == this.mChild2) {
                    }
                    iCombineMeasuredStates = combineMeasuredStates(i4, view.getMeasuredState());
                } else {
                    view = childAt;
                }
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                iMax = Math.max(iMax, view.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
                iMax2 = Math.max(iMax2, view.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin);
                iCombineMeasuredStates = combineMeasuredStates(i4, view.getMeasuredState());
            }
        }
        int i7 = iCombineMeasuredStates;
        if (this.mDisplayMode == 0) {
            if (this.mOrientation == 1) {
                iMax2 <<= 1;
            } else {
                iMax <<= 1;
            }
        }
        int paddingLeft = iMax + getPaddingLeft() + getPaddingRight();
        int iMax3 = Math.max(iMax2 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight());
        int iMax4 = Math.max(paddingLeft, getSuggestedMinimumWidth());
        Drawable foreground = getForeground();
        if (foreground != null) {
            iMax3 = Math.max(iMax3, foreground.getMinimumHeight());
            iMax4 = Math.max(iMax4, foreground.getMinimumWidth());
        }
        setMeasuredDimension(resolveSizeAndState(iMax4, i, i7), resolveSizeAndState(iMax3, i2, i7 << 16));
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount2 = getChildCount();
        if (childCount2 == 1) {
            callChildMeasure(this.mChild1, measuredWidth, measuredHeight, i, i2);
            return;
        }
        if (childCount2 > 0) {
            int i8 = this.mDisplayMode;
            if (i8 != 1) {
                if (i8 != 2) {
                    if (i8 != 3) {
                        if (i8 != 4) {
                            onMeasureSplit(measuredWidth, measuredHeight, i, i2);
                            return;
                        }
                    }
                }
                onMeasureSelect2(measuredWidth, measuredHeight, i, i2);
                return;
            }
            onMeasureSelect1(measuredWidth, measuredHeight, i, i2);
            return;
        }
        super.onMeasure(i, i2);
    }

    private void onMeasureSplit(int i, int i2, int i3, int i4) {
        if (this.mOrientation == 1) {
            onMeasureVertical(i, i2, i3, i4);
        } else {
            onMeasureHorizontal(i, i2, i3, i4);
        }
    }

    private void onMeasureSelect1(int i, int i2, int i3, int i4) {
        View view = this.mFlipChildPos ? this.mChild2 : this.mChild1;
        View view2 = this.mFlipChildPos ? this.mChild1 : this.mChild2;
        callChildMeasure(view, i, i2, i3, i4);
        if (this.mEnableSubWindow) {
            float f = this.mSubWindowScale;
            callChildMeasure(view2, (int) (i * f), (int) (i2 * f), i3, i4);
        }
    }

    private void onMeasureSelect2(int i, int i2, int i3, int i4) {
        View view = this.mFlipChildPos ? this.mChild2 : this.mChild1;
        callChildMeasure(this.mFlipChildPos ? this.mChild1 : this.mChild2, i, i2, i3, i4);
        if (this.mEnableSubWindow) {
            float f = this.mSubWindowScale;
            callChildMeasure(view, (int) (i * f), (int) (i2 * f), i3, i4);
        }
    }

    private void onMeasureHorizontal(int i, int i2, int i3, int i4) {
        View view = this.mFlipChildPos ? this.mChild2 : this.mChild1;
        View view2 = this.mFlipChildPos ? this.mChild1 : this.mChild2;
        int i5 = i >>> 1;
        callChildMeasure(view, i5, i2, i3, i4);
        callChildMeasure(view2, i5, i2, i3, i4);
    }

    private void onMeasureVertical(int i, int i2, int i3, int i4) {
        View view = this.mFlipChildPos ? this.mChild2 : this.mChild1;
        View view2 = this.mFlipChildPos ? this.mChild1 : this.mChild2;
        int i5 = i2 >>> 1;
        callChildMeasure(view, i, i5, i3, i4);
        callChildMeasure(view2, i, i5, i3, i4);
    }

    private void callChildMeasure(View view, int i, int i2, int i3, int i4) {
        int iMakeMeasureSpec;
        int iMakeMeasureSpec2;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (marginLayoutParams.width == -1) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.min(i, (((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) - marginLayoutParams.leftMargin) - marginLayoutParams.rightMargin), BasicMeasure.EXACTLY);
        } else {
            int childMeasureSpec = getChildMeasureSpec(i3, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin, marginLayoutParams.width);
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(childMeasureSpec), i), View.MeasureSpec.getMode(childMeasureSpec));
        }
        if (marginLayoutParams.height == -1) {
            iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.min(i2, (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - marginLayoutParams.topMargin) - marginLayoutParams.bottomMargin), BasicMeasure.EXACTLY);
        } else {
            int childMeasureSpec2 = getChildMeasureSpec(i4, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height);
            iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i2), View.MeasureSpec.getMode(childMeasureSpec2));
        }
        view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int paddingLeft = getPaddingLeft() + i;
        int paddingTop = i2 + getPaddingTop();
        int paddingRight = i3 - getPaddingRight();
        int paddingBottom = i4 - getPaddingBottom();
        int childCount = getChildCount();
        if (childCount == 1) {
            callChildLayout(this.mChild1, z, paddingLeft, paddingTop, paddingRight, paddingBottom);
            return;
        }
        if (childCount > 0) {
            int i5 = this.mDisplayMode;
            if (i5 != 1) {
                if (i5 != 2) {
                    if (i5 != 3) {
                        if (i5 != 4) {
                            onLayoutSplit(z, paddingLeft, paddingTop, paddingRight, paddingBottom);
                            return;
                        }
                    }
                }
                onLayoutSelect2(z, paddingLeft, paddingTop, paddingRight, paddingBottom);
                return;
            }
            onLayoutSelect1(z, paddingLeft, paddingTop, paddingRight, paddingBottom);
            return;
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    private void onLayoutSplit(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation == 1) {
            onLayoutVertical(z, i, i2, i3, i4);
        } else {
            onLayoutHorizontal(z, i, i2, i3, i4);
        }
    }

    private void onLayoutSelect1(boolean z, int i, int i2, int i3, int i4) {
        View view = this.mFlipChildPos ? this.mChild2 : this.mChild1;
        View view2 = this.mFlipChildPos ? this.mChild1 : this.mChild2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        callChildLayout(view, z, i - paddingLeft, i2 - paddingTop, i3 - paddingLeft, i4 - paddingTop);
        if (this.mEnableSubWindow) {
            int bottom = view.getBottom();
            int right = view.getRight();
            int measuredWidth = view2.getMeasuredWidth();
            int measuredHeight = view2.getMeasuredHeight();
            if (this.mOrientation == 1) {
                callChildLayout(view2, z, right - measuredWidth, bottom - measuredHeight, right, bottom);
            } else {
                callChildLayout(view2, z, right - measuredWidth, bottom - measuredHeight, right, bottom);
            }
        }
    }

    private void onLayoutSelect2(boolean z, int i, int i2, int i3, int i4) {
        View view = this.mFlipChildPos ? this.mChild2 : this.mChild1;
        View view2 = this.mFlipChildPos ? this.mChild1 : this.mChild2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        callChildLayout(view2, z, i - paddingLeft, i2 - paddingTop, i3 - paddingLeft, i4 - paddingTop);
        if (this.mEnableSubWindow) {
            int left = view2.getLeft();
            int top = view2.getTop();
            int right = view2.getRight();
            int bottom = view2.getBottom();
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            if (this.mOrientation == 1) {
                callChildLayout(view, z, right - measuredWidth, top, right, top + measuredHeight);
            } else {
                callChildLayout(view, z, left, bottom - measuredHeight, left + measuredWidth, bottom);
            }
        }
    }

    private void onLayoutHorizontal(boolean z, int i, int i2, int i3, int i4) {
        View view = this.mFlipChildPos ? this.mChild2 : this.mChild1;
        View view2 = this.mFlipChildPos ? this.mChild1 : this.mChild2;
        int i5 = (i3 - i) >>> 1;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int i6 = i - paddingLeft;
        int i7 = i2 - paddingTop;
        int i8 = i6 + i5;
        int i9 = i4 - paddingTop;
        callChildLayout(view, z, i6, i7, i8, i9);
        callChildLayout(view2, z, i8, i7, i3 - paddingLeft, i9);
    }

    private void onLayoutVertical(boolean z, int i, int i2, int i3, int i4) {
        View view = this.mFlipChildPos ? this.mChild2 : this.mChild1;
        View view2 = this.mFlipChildPos ? this.mChild1 : this.mChild2;
        int i5 = (i4 - i2) >>> 1;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int i6 = i - paddingLeft;
        int i7 = i2 - paddingTop;
        int i8 = i3 - paddingLeft;
        int i9 = i7 + i5;
        callChildLayout(view, z, i6, i7, i8, i9);
        callChildLayout(view2, z, i6, i9, i8, i4 - paddingTop);
    }

    private void callChildLayout(View view, boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i7 = layoutParams.gravity;
        if (i7 == -1) {
            i7 = 17;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(i7, BuildCheck.isAndroid4_2() ? getLayoutDirection() : 0);
        int i8 = i7 & 112;
        int i9 = absoluteGravity & 7;
        if (i9 == 1) {
            i5 = ((i + (((i3 - i) - measuredWidth) / 2)) + layoutParams.leftMargin) - layoutParams.rightMargin;
        } else if (i9 == 5) {
            i5 = (i3 - measuredWidth) - layoutParams.rightMargin;
        } else {
            i5 = i + layoutParams.leftMargin;
        }
        if (i8 == 16) {
            i6 = ((i2 + (((i4 - i2) - measuredHeight) / 2)) + layoutParams.topMargin) - layoutParams.bottomMargin;
        } else if (i8 != 48 && i8 == 80) {
            i6 = (i4 - measuredHeight) - layoutParams.bottomMargin;
        } else {
            int i10 = layoutParams.topMargin;
            i6 = i2 + i10;
        }
        view.layout(i5, i6, measuredWidth + i5, measuredHeight + i6);
    }

    public void startLayout() {
        if (isInEditMode() || getChildCount() < 2) {
            requestLayout();
        }
        post(new Runnable() { // from class: com.serenegiant.widget.TwoPainViewGroup.1
            @Override // java.lang.Runnable
            public void run() {
                TwoPainViewGroup.this.startLayoutOnUI();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0024 A[Catch: all -> 0x006c, TryCatch #0 {all -> 0x006c, blocks: (B:10:0x0012, B:19:0x0024, B:21:0x002e, B:24:0x0033, B:25:0x003a, B:27:0x0047, B:31:0x004d, B:32:0x0051), top: B:54:0x0012 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003a A[Catch: all -> 0x006c, TryCatch #0 {all -> 0x006c, blocks: (B:10:0x0012, B:19:0x0024, B:21:0x002e, B:24:0x0033, B:25:0x003a, B:27:0x0047, B:31:0x004d, B:32:0x0051), top: B:54:0x0012 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void startLayoutOnUI() {
        /*
            r7 = this;
            boolean r0 = r7.mFlipChildPos
            if (r0 == 0) goto L7
            android.view.View r0 = r7.mChild2
            goto L9
        L7:
            android.view.View r0 = r7.mChild1
        L9:
            boolean r1 = r7.mFlipChildPos
            if (r1 == 0) goto L10
            android.view.View r1 = r7.mChild1
            goto L12
        L10:
            android.view.View r1 = r7.mChild2
        L12:
            int r2 = r7.mDisplayMode     // Catch: java.lang.Throwable -> L6c
            r3 = 0
            if (r2 == 0) goto L51
            r4 = 1
            r5 = 3
            r6 = 4
            if (r2 == r4) goto L3a
            r4 = 2
            if (r2 == r4) goto L24
            if (r2 == r5) goto L3a
            if (r2 == r6) goto L24
            goto L57
        L24:
            r7.removeView(r1)     // Catch: java.lang.Throwable -> L6c
            r7.addView(r1, r3)     // Catch: java.lang.Throwable -> L6c
            boolean r2 = r7.mEnableSubWindow     // Catch: java.lang.Throwable -> L6c
            if (r2 == 0) goto L33
            int r2 = r7.mDisplayMode     // Catch: java.lang.Throwable -> L6c
            if (r2 == r6) goto L33
            r6 = 0
        L33:
            r0.setVisibility(r6)     // Catch: java.lang.Throwable -> L6c
            r1.setVisibility(r3)     // Catch: java.lang.Throwable -> L6c
            goto L57
        L3a:
            r7.removeView(r0)     // Catch: java.lang.Throwable -> L6c
            r7.addView(r0, r3)     // Catch: java.lang.Throwable -> L6c
            r0.setVisibility(r3)     // Catch: java.lang.Throwable -> L6c
            boolean r2 = r7.mEnableSubWindow     // Catch: java.lang.Throwable -> L6c
            if (r2 == 0) goto L4c
            int r2 = r7.mDisplayMode     // Catch: java.lang.Throwable -> L6c
            if (r2 == r5) goto L4c
            goto L4d
        L4c:
            r3 = 4
        L4d:
            r1.setVisibility(r3)     // Catch: java.lang.Throwable -> L6c
            goto L57
        L51:
            r0.setVisibility(r3)     // Catch: java.lang.Throwable -> L6c
            r1.setVisibility(r3)     // Catch: java.lang.Throwable -> L6c
        L57:
            boolean r2 = r7.mFlipChildPos
            if (r2 == 0) goto L5d
            r2 = r1
            goto L5e
        L5d:
            r2 = r0
        L5e:
            r7.mChild1 = r2
            boolean r2 = r7.mFlipChildPos
            if (r2 == 0) goto L65
            goto L66
        L65:
            r0 = r1
        L66:
            r7.mChild2 = r0
            r7.requestLayout()
            return
        L6c:
            r2 = move-exception
            boolean r3 = r7.mFlipChildPos
            if (r3 == 0) goto L73
            r3 = r1
            goto L74
        L73:
            r3 = r0
        L74:
            r7.mChild1 = r3
            boolean r3 = r7.mFlipChildPos
            if (r3 == 0) goto L7b
            goto L7c
        L7b:
            r0 = r1
        L7c:
            r7.mChild2 = r0
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.serenegiant.widget.TwoPainViewGroup.startLayoutOnUI():void");
    }

    private void cancelAnimation() {
        synchronized (this.mSync) {
            if (this.mAnimator != null) {
                this.mAnimator.cancel();
                this.mAnimator = null;
            }
        }
    }
}
