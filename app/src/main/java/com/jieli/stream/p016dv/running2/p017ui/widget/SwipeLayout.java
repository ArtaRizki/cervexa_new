package com.jieli.stream.p016dv.running2.p017ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

/* JADX INFO: loaded from: classes.dex */
public class SwipeLayout extends LinearLayout {
    private View actionView;
    private View contentView;
    private int dragDistance;
    private int draggedX;
    private ViewDragHelper viewDragHelper;

    public SwipeLayout(Context context) {
        this(context, null);
    }

    public SwipeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public SwipeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.viewDragHelper = ViewDragHelper.create(this, new DragHelperCallback());
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    public void init() {
        this.contentView = getChildAt(0);
        View childAt = getChildAt(1);
        this.actionView = childAt;
        childAt.setVisibility(8);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.dragDistance = this.actionView.getMeasuredWidth();
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {
        private DragHelperCallback() {
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i) {
            return view == SwipeLayout.this.contentView || view == SwipeLayout.this.actionView;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            SwipeLayout.this.draggedX = i;
            if (view == SwipeLayout.this.contentView) {
                SwipeLayout.this.actionView.offsetLeftAndRight(i3);
            } else {
                SwipeLayout.this.contentView.offsetLeftAndRight(i3);
            }
            if (SwipeLayout.this.actionView.getVisibility() == 8) {
                SwipeLayout.this.actionView.setVisibility(0);
            }
            SwipeLayout.this.invalidate();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i, int i2) {
            if (view != SwipeLayout.this.contentView) {
                int paddingLeft = (SwipeLayout.this.getPaddingLeft() + SwipeLayout.this.contentView.getMeasuredWidth()) - SwipeLayout.this.dragDistance;
                return Math.min(Math.max(i, paddingLeft), SwipeLayout.this.getPaddingLeft() + SwipeLayout.this.contentView.getMeasuredWidth() + SwipeLayout.this.getPaddingRight());
            }
            return Math.min(Math.max((-SwipeLayout.this.getPaddingLeft()) - SwipeLayout.this.dragDistance, i), 0);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            return SwipeLayout.this.dragDistance;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f, float f2) {
            super.onViewReleased(view, f, f2);
            double d = f;
            boolean z = true;
            if (d > 800.0d) {
                z = false;
            } else if (d >= -800.0d && SwipeLayout.this.draggedX > (-SwipeLayout.this.dragDistance) / 2) {
                int unused = SwipeLayout.this.draggedX;
                int i = (-SwipeLayout.this.dragDistance) / 2;
                z = false;
            }
            SwipeLayout.this.viewDragHelper.smoothSlideViewTo(SwipeLayout.this.contentView, z ? -SwipeLayout.this.dragDistance : 0, 0);
            ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.viewDragHelper.shouldInterceptTouchEvent(motionEvent) || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.viewDragHelper.processTouchEvent(motionEvent);
        return true;
    }

    @Override // android.view.View
    public void computeScroll() {
        super.computeScroll();
        if (this.viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
