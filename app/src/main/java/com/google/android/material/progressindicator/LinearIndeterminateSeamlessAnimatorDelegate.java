package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Property;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.math.MathUtils;

/* JADX INFO: loaded from: classes.dex */
final class LinearIndeterminateSeamlessAnimatorDelegate extends IndeterminateAnimatorDelegate<AnimatorSet> {
    private static final int DURATION_PER_COLOR = 667;
    private static final Property<LinearIndeterminateSeamlessAnimatorDelegate, Float> LINE_CONNECT_POINT_1_FRACTION = new Property<LinearIndeterminateSeamlessAnimatorDelegate, Float>(Float.class, "lineConnectPoint1Fraction") { // from class: com.google.android.material.progressindicator.LinearIndeterminateSeamlessAnimatorDelegate.3
        @Override // android.util.Property
        public Float get(LinearIndeterminateSeamlessAnimatorDelegate linearIndeterminateSeamlessAnimatorDelegate) {
            return Float.valueOf(linearIndeterminateSeamlessAnimatorDelegate.getLineConnectPoint1Fraction());
        }

        @Override // android.util.Property
        public void set(LinearIndeterminateSeamlessAnimatorDelegate linearIndeterminateSeamlessAnimatorDelegate, Float f) {
            linearIndeterminateSeamlessAnimatorDelegate.setLineConnectPoint1Fraction(f.floatValue());
        }
    };
    private static final Property<LinearIndeterminateSeamlessAnimatorDelegate, Float> LINE_CONNECT_POINT_2_FRACTION = new Property<LinearIndeterminateSeamlessAnimatorDelegate, Float>(Float.class, "lineConnectPoint2Fraction") { // from class: com.google.android.material.progressindicator.LinearIndeterminateSeamlessAnimatorDelegate.4
        @Override // android.util.Property
        public Float get(LinearIndeterminateSeamlessAnimatorDelegate linearIndeterminateSeamlessAnimatorDelegate) {
            return Float.valueOf(linearIndeterminateSeamlessAnimatorDelegate.getLineConnectPoint2Fraction());
        }

        @Override // android.util.Property
        public void set(LinearIndeterminateSeamlessAnimatorDelegate linearIndeterminateSeamlessAnimatorDelegate, Float f) {
            linearIndeterminateSeamlessAnimatorDelegate.setLineConnectPoint2Fraction(f.floatValue());
        }
    };
    private static final int NEXT_COLOR_DELAY = 333;
    private AnimatorSet animatorSet;
    private float lineConnectPoint1Fraction;
    private float lineConnectPoint2Fraction;
    private int referenceSegmentColorIndex;

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void registerAnimatorsCompleteCallback(Animatable2Compat.AnimationCallback animationCallback) {
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void requestCancelAnimatorAfterCurrentCycle() {
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void resetPropertiesForNextCycle() {
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void unregisterAnimatorsCompleteCallback() {
    }

    public LinearIndeterminateSeamlessAnimatorDelegate() {
        super(3);
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void startAnimator() {
        maybeInitializeAnimators();
        this.animatorSet.start();
    }

    private void maybeInitializeAnimators() {
        if (this.animatorSet == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, LINE_CONNECT_POINT_1_FRACTION, 0.0f, 1.0f);
            objectAnimatorOfFloat.setDuration(667L);
            objectAnimatorOfFloat.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            objectAnimatorOfFloat.setRepeatCount(-1);
            objectAnimatorOfFloat.setRepeatMode(1);
            objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.progressindicator.LinearIndeterminateSeamlessAnimatorDelegate.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                    super.onAnimationRepeat(animator);
                    if (LinearIndeterminateSeamlessAnimatorDelegate.this.getLineConnectPoint2Fraction() <= 0.0f || LinearIndeterminateSeamlessAnimatorDelegate.this.getLineConnectPoint2Fraction() >= 1.0f) {
                        return;
                    }
                    LinearIndeterminateSeamlessAnimatorDelegate.this.shiftSegmentColors();
                }
            });
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, LINE_CONNECT_POINT_2_FRACTION, 0.0f, 0.0f);
            objectAnimatorOfFloat2.setDuration(333L);
            ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this, LINE_CONNECT_POINT_2_FRACTION, 0.0f, 1.0f);
            objectAnimatorOfFloat3.setDuration(667L);
            objectAnimatorOfFloat3.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            objectAnimatorOfFloat3.setRepeatCount(-1);
            objectAnimatorOfFloat3.setRepeatMode(1);
            objectAnimatorOfFloat3.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.progressindicator.LinearIndeterminateSeamlessAnimatorDelegate.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                    super.onAnimationRepeat(animator);
                    if (LinearIndeterminateSeamlessAnimatorDelegate.this.getLineConnectPoint1Fraction() <= 0.0f || LinearIndeterminateSeamlessAnimatorDelegate.this.getLineConnectPoint1Fraction() >= 1.0f) {
                        return;
                    }
                    LinearIndeterminateSeamlessAnimatorDelegate.this.shiftSegmentColors();
                }
            });
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(objectAnimatorOfFloat2, objectAnimatorOfFloat3);
            AnimatorSet animatorSet2 = new AnimatorSet();
            this.animatorSet = animatorSet2;
            animatorSet2.playTogether(objectAnimatorOfFloat, animatorSet);
        }
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void resetPropertiesForNewStart() {
        setLineConnectPoint1Fraction(0.0f);
        setLineConnectPoint2Fraction(0.0f);
        resetSegmentColors();
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void cancelAnimatorImmediately() {
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
    }

    @Override // com.google.android.material.progressindicator.IndeterminateAnimatorDelegate
    public void invalidateSpecValues() {
        resetSegmentColors();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shiftSegmentColors() {
        this.referenceSegmentColorIndex = (this.referenceSegmentColorIndex + 1) % this.drawable.combinedIndicatorColorArray.length;
        updateSegmentColors();
    }

    private void resetSegmentColors() {
        this.referenceSegmentColorIndex = 0;
        updateSegmentColors();
    }

    private void updateSegmentColors() {
        int iFloorMod = MathUtils.floorMod(this.referenceSegmentColorIndex + 2, this.drawable.combinedIndicatorColorArray.length);
        int iFloorMod2 = MathUtils.floorMod(this.referenceSegmentColorIndex + 1, this.drawable.combinedIndicatorColorArray.length);
        this.segmentColors[0] = this.drawable.combinedIndicatorColorArray[iFloorMod];
        this.segmentColors[1] = this.drawable.combinedIndicatorColorArray[iFloorMod2];
        this.segmentColors[2] = this.drawable.combinedIndicatorColorArray[this.referenceSegmentColorIndex];
    }

    private void updateSegmentPositions() {
        this.segmentPositions[0] = 0.0f;
        float[] fArr = this.segmentPositions;
        float[] fArr2 = this.segmentPositions;
        float fMin = Math.min(getLineConnectPoint1Fraction(), getLineConnectPoint2Fraction());
        fArr2[2] = fMin;
        fArr[1] = fMin;
        float[] fArr3 = this.segmentPositions;
        float[] fArr4 = this.segmentPositions;
        float fMax = Math.max(getLineConnectPoint1Fraction(), getLineConnectPoint2Fraction());
        fArr4[4] = fMax;
        fArr3[3] = fMax;
        this.segmentPositions[5] = 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getLineConnectPoint1Fraction() {
        return this.lineConnectPoint1Fraction;
    }

    void setLineConnectPoint1Fraction(float f) {
        this.lineConnectPoint1Fraction = f;
        updateSegmentPositions();
        this.drawable.invalidateSelf();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getLineConnectPoint2Fraction() {
        return this.lineConnectPoint2Fraction;
    }

    void setLineConnectPoint2Fraction(float f) {
        this.lineConnectPoint2Fraction = f;
        updateSegmentPositions();
        this.drawable.invalidateSelf();
    }
}
