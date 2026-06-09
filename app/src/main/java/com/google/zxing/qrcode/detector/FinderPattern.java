package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;

/* JADX INFO: loaded from: classes.dex */
public final class FinderPattern extends ResultPoint {
    private int count;
    private final float estimatedModuleSize;

    FinderPattern(float f, float f2, float f3) {
        super(f, f2);
        this.estimatedModuleSize = f3;
        this.count = 1;
    }

    boolean aboutEquals(float f, float f2, float f3) {
        if (Math.abs(f2 - getY()) > f || Math.abs(f3 - getX()) > f) {
            return false;
        }
        float fAbs = Math.abs(f - this.estimatedModuleSize);
        return fAbs <= 1.0f || fAbs / this.estimatedModuleSize <= 1.0f;
    }

    int getCount() {
        return this.count;
    }

    public float getEstimatedModuleSize() {
        return this.estimatedModuleSize;
    }

    void incrementCount() {
        this.count++;
    }
}
