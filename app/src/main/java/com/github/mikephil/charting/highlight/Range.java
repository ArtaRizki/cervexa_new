package com.github.mikephil.charting.highlight;

/* JADX INFO: loaded from: classes.dex */
public final class Range {
    public float from;

    /* JADX INFO: renamed from: to */
    public float f1959to;

    public Range(float f, float f2) {
        this.from = f;
        this.f1959to = f2;
    }

    public boolean contains(float f) {
        return f > this.from && f <= this.f1959to;
    }

    public boolean isLarger(float f) {
        return f > this.f1959to;
    }

    public boolean isSmaller(float f) {
        return f < this.from;
    }
}
