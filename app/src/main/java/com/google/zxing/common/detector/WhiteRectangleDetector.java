package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

/* JADX INFO: loaded from: classes.dex */
public final class WhiteRectangleDetector {
    private static final int CORR = 1;
    private static final int INIT_SIZE = 30;
    private final int downInit;
    private final int height;
    private final BitMatrix image;
    private final int leftInit;
    private final int rightInit;
    private final int upInit;
    private final int width;

    public WhiteRectangleDetector(BitMatrix bitMatrix) throws NotFoundException {
        this.image = bitMatrix;
        this.height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        this.width = width;
        int i = (width - 30) >> 1;
        this.leftInit = i;
        int i2 = (width + 30) >> 1;
        this.rightInit = i2;
        int i3 = this.height;
        int i4 = (i3 - 30) >> 1;
        this.upInit = i4;
        int i5 = (i3 + 30) >> 1;
        this.downInit = i5;
        if (i4 < 0 || i < 0 || i5 >= i3 || i2 >= width) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    public WhiteRectangleDetector(BitMatrix bitMatrix, int i, int i2, int i3) throws NotFoundException {
        this.image = bitMatrix;
        this.height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        this.width = width;
        int i4 = i >> 1;
        int i5 = i2 - i4;
        this.leftInit = i5;
        int i6 = i2 + i4;
        this.rightInit = i6;
        int i7 = i3 - i4;
        this.upInit = i7;
        int i8 = i3 + i4;
        this.downInit = i8;
        if (i7 < 0 || i5 < 0 || i8 >= this.height || i6 >= width) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private ResultPoint[] centerEdges(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) {
        float x = resultPoint.getX();
        float y = resultPoint.getY();
        float x2 = resultPoint2.getX();
        float y2 = resultPoint2.getY();
        float x3 = resultPoint3.getX();
        float y3 = resultPoint3.getY();
        float x4 = resultPoint4.getX();
        float y4 = resultPoint4.getY();
        return x < ((float) (this.width / 2)) ? new ResultPoint[]{new ResultPoint(x4 - 1.0f, y4 + 1.0f), new ResultPoint(x2 + 1.0f, y2 + 1.0f), new ResultPoint(x3 - 1.0f, y3 - 1.0f), new ResultPoint(x + 1.0f, y - 1.0f)} : new ResultPoint[]{new ResultPoint(x4 + 1.0f, y4 + 1.0f), new ResultPoint(x2 + 1.0f, y2 - 1.0f), new ResultPoint(x3 - 1.0f, y3 + 1.0f), new ResultPoint(x - 1.0f, y - 1.0f)};
    }

    private boolean containsBlackPoint(int i, int i2, int i3, boolean z) {
        if (z) {
            while (i <= i2) {
                if (this.image.get(i, i3)) {
                    return true;
                }
                i++;
            }
            return false;
        }
        while (i <= i2) {
            if (this.image.get(i3, i)) {
                return true;
            }
            i++;
        }
        return false;
    }

    private static int distanceL2(float f, float f2, float f3, float f4) {
        float f5 = f - f3;
        float f6 = f2 - f4;
        return round((float) Math.sqrt((f5 * f5) + (f6 * f6)));
    }

    private ResultPoint getBlackPointOnSegment(float f, float f2, float f3, float f4) {
        int iDistanceL2 = distanceL2(f, f2, f3, f4);
        float f5 = iDistanceL2;
        float f6 = (f3 - f) / f5;
        float f7 = (f4 - f2) / f5;
        for (int i = 0; i < iDistanceL2; i++) {
            float f8 = i;
            int iRound = round((f8 * f6) + f);
            int iRound2 = round((f8 * f7) + f2);
            if (this.image.get(iRound, iRound2)) {
                return new ResultPoint(iRound, iRound2);
            }
        }
        return null;
    }

    private static int round(float f) {
        return (int) (f + 0.5f);
    }

    public ResultPoint[] detect() throws NotFoundException {
        int i = this.leftInit;
        int i2 = this.rightInit;
        int i3 = this.upInit;
        int i4 = this.downInit;
        boolean z = false;
        boolean z2 = true;
        boolean z3 = false;
        while (z2) {
            boolean zContainsBlackPoint = true;
            boolean z4 = false;
            while (zContainsBlackPoint && i2 < this.width) {
                zContainsBlackPoint = containsBlackPoint(i3, i4, i2, false);
                if (zContainsBlackPoint) {
                    i2++;
                    z4 = true;
                }
            }
            if (i2 < this.width) {
                boolean zContainsBlackPoint2 = true;
                while (zContainsBlackPoint2 && i4 < this.height) {
                    zContainsBlackPoint2 = containsBlackPoint(i, i2, i4, true);
                    if (zContainsBlackPoint2) {
                        i4++;
                        z4 = true;
                    }
                }
                if (i4 < this.height) {
                    boolean zContainsBlackPoint3 = true;
                    while (zContainsBlackPoint3 && i >= 0) {
                        zContainsBlackPoint3 = containsBlackPoint(i3, i4, i, false);
                        if (zContainsBlackPoint3) {
                            i--;
                            z4 = true;
                        }
                    }
                    if (i >= 0) {
                        z2 = z4;
                        boolean zContainsBlackPoint4 = true;
                        while (zContainsBlackPoint4 && i3 >= 0) {
                            zContainsBlackPoint4 = containsBlackPoint(i, i2, i3, true);
                            if (zContainsBlackPoint4) {
                                i3--;
                                z2 = true;
                            }
                        }
                        if (i3 >= 0) {
                            if (z2) {
                                z3 = true;
                            }
                        }
                    }
                }
            }
            z = true;
            break;
        }
        if (z || !z3) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i5 = i2 - i;
        ResultPoint blackPointOnSegment = null;
        ResultPoint blackPointOnSegment2 = null;
        for (int i6 = 1; i6 < i5; i6++) {
            blackPointOnSegment2 = getBlackPointOnSegment(i, i4 - i6, i + i6, i4);
            if (blackPointOnSegment2 != null) {
                break;
            }
        }
        if (blackPointOnSegment2 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint blackPointOnSegment3 = null;
        for (int i7 = 1; i7 < i5; i7++) {
            blackPointOnSegment3 = getBlackPointOnSegment(i, i3 + i7, i + i7, i3);
            if (blackPointOnSegment3 != null) {
                break;
            }
        }
        if (blackPointOnSegment3 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint blackPointOnSegment4 = null;
        for (int i8 = 1; i8 < i5; i8++) {
            blackPointOnSegment4 = getBlackPointOnSegment(i2, i3 + i8, i2 - i8, i3);
            if (blackPointOnSegment4 != null) {
                break;
            }
        }
        if (blackPointOnSegment4 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        for (int i9 = 1; i9 < i5; i9++) {
            blackPointOnSegment = getBlackPointOnSegment(i2, i4 - i9, i2 - i9, i4);
            if (blackPointOnSegment != null) {
                break;
            }
        }
        if (blackPointOnSegment != null) {
            return centerEdges(blackPointOnSegment, blackPointOnSegment2, blackPointOnSegment4, blackPointOnSegment3);
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
