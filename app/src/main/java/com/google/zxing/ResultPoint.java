package com.google.zxing;

/* JADX INFO: loaded from: classes.dex */
public class ResultPoint {

    /* JADX INFO: renamed from: x */
    private final float f2031x;

    /* JADX INFO: renamed from: y */
    private final float f2032y;

    public ResultPoint(float f, float f2) {
        this.f2031x = f;
        this.f2032y = f2;
    }

    private static float crossProductZ(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3) {
        float f = resultPoint2.f2031x;
        float f2 = resultPoint2.f2032y;
        return ((resultPoint3.f2031x - f) * (resultPoint.f2032y - f2)) - ((resultPoint3.f2032y - f2) * (resultPoint.f2031x - f));
    }

    public static float distance(ResultPoint resultPoint, ResultPoint resultPoint2) {
        float x = resultPoint.getX() - resultPoint2.getX();
        float y = resultPoint.getY() - resultPoint2.getY();
        return (float) Math.sqrt((x * x) + (y * y));
    }

    public static void orderBestPatterns(ResultPoint[] resultPointArr) {
        ResultPoint resultPoint;
        ResultPoint resultPoint2;
        ResultPoint resultPoint3;
        float fDistance = distance(resultPointArr[0], resultPointArr[1]);
        float fDistance2 = distance(resultPointArr[1], resultPointArr[2]);
        float fDistance3 = distance(resultPointArr[0], resultPointArr[2]);
        if (fDistance2 >= fDistance && fDistance2 >= fDistance3) {
            resultPoint = resultPointArr[0];
            resultPoint2 = resultPointArr[1];
            resultPoint3 = resultPointArr[2];
        } else if (fDistance3 < fDistance2 || fDistance3 < fDistance) {
            resultPoint = resultPointArr[2];
            resultPoint2 = resultPointArr[0];
            resultPoint3 = resultPointArr[1];
        } else {
            resultPoint = resultPointArr[1];
            resultPoint2 = resultPointArr[0];
            resultPoint3 = resultPointArr[2];
        }
        if (crossProductZ(resultPoint2, resultPoint, resultPoint3) < 0.0f) {
            ResultPoint resultPoint4 = resultPoint3;
            resultPoint3 = resultPoint2;
            resultPoint2 = resultPoint4;
        }
        resultPointArr[0] = resultPoint2;
        resultPointArr[1] = resultPoint;
        resultPointArr[2] = resultPoint3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ResultPoint)) {
            return false;
        }
        ResultPoint resultPoint = (ResultPoint) obj;
        return this.f2031x == resultPoint.f2031x && this.f2032y == resultPoint.f2032y;
    }

    public final float getX() {
        return this.f2031x;
    }

    public final float getY() {
        return this.f2032y;
    }

    public int hashCode() {
        return (Float.floatToIntBits(this.f2031x) * 31) + Float.floatToIntBits(this.f2032y);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(25);
        stringBuffer.append('(');
        stringBuffer.append(this.f2031x);
        stringBuffer.append(',');
        stringBuffer.append(this.f2032y);
        stringBuffer.append(')');
        return stringBuffer.toString();
    }
}
