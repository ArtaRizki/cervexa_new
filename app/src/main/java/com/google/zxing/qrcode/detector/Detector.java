package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.PerspectiveTransform;
import com.google.zxing.qrcode.decoder.Version;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public class Detector {
    private final BitMatrix image;
    private ResultPointCallback resultPointCallback;

    public Detector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    private float calculateModuleSizeOneWay(ResultPoint resultPoint, ResultPoint resultPoint2) {
        float fSizeOfBlackWhiteBlackRunBothWays = sizeOfBlackWhiteBlackRunBothWays((int) resultPoint.getX(), (int) resultPoint.getY(), (int) resultPoint2.getX(), (int) resultPoint2.getY());
        float fSizeOfBlackWhiteBlackRunBothWays2 = sizeOfBlackWhiteBlackRunBothWays((int) resultPoint2.getX(), (int) resultPoint2.getY(), (int) resultPoint.getX(), (int) resultPoint.getY());
        return Float.isNaN(fSizeOfBlackWhiteBlackRunBothWays) ? fSizeOfBlackWhiteBlackRunBothWays2 / 7.0f : Float.isNaN(fSizeOfBlackWhiteBlackRunBothWays2) ? fSizeOfBlackWhiteBlackRunBothWays / 7.0f : (fSizeOfBlackWhiteBlackRunBothWays + fSizeOfBlackWhiteBlackRunBothWays2) / 14.0f;
    }

    protected static int computeDimension(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, float f) throws NotFoundException {
        int iRound = ((round(ResultPoint.distance(resultPoint, resultPoint2) / f) + round(ResultPoint.distance(resultPoint, resultPoint3) / f)) >> 1) + 7;
        int i = iRound & 3;
        if (i == 0) {
            return iRound + 1;
        }
        if (i == 2) {
            return iRound - 1;
        }
        if (i != 3) {
            return iRound;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public static PerspectiveTransform createTransform(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i) {
        float x;
        float y;
        float f;
        float f2 = i - 3.5f;
        if (resultPoint4 != null) {
            x = resultPoint4.getX();
            y = resultPoint4.getY();
            f = f2 - 3.0f;
        } else {
            x = (resultPoint2.getX() - resultPoint.getX()) + resultPoint3.getX();
            y = (resultPoint2.getY() - resultPoint.getY()) + resultPoint3.getY();
            f = f2;
        }
        return PerspectiveTransform.quadrilateralToQuadrilateral(3.5f, 3.5f, f2, 3.5f, f, f, 3.5f, f2, resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY(), x, y, resultPoint3.getX(), resultPoint3.getY());
    }

    private static int round(float f) {
        return (int) (f + 0.5f);
    }

    private static BitMatrix sampleGrid(BitMatrix bitMatrix, PerspectiveTransform perspectiveTransform, int i) throws NotFoundException {
        return GridSampler.getInstance().sampleGrid(bitMatrix, i, i, perspectiveTransform);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x006e A[PHI: r20
  0x006e: PHI (r20v2 int) = (r20v1 int), (r20v4 int) binds: [B:29:0x006c, B:26:0x005f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0073 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float sizeOfBlackWhiteBlackRun(int r20, int r21, int r22, int r23) {
        /*
            r19 = this;
            r0 = r19
            int r1 = r23 - r21
            int r1 = java.lang.Math.abs(r1)
            int r2 = r22 - r20
            int r2 = java.lang.Math.abs(r2)
            r3 = 0
            r4 = 1
            if (r1 <= r2) goto L14
            r1 = 1
            goto L15
        L14:
            r1 = 0
        L15:
            if (r1 == 0) goto L20
            r5 = r20
            r2 = r21
            r7 = r22
            r6 = r23
            goto L28
        L20:
            r2 = r20
            r5 = r21
            r6 = r22
            r7 = r23
        L28:
            int r8 = r6 - r2
            int r9 = java.lang.Math.abs(r8)
            int r10 = r7 - r5
            int r11 = java.lang.Math.abs(r10)
            int r12 = -r9
            int r12 = r12 >> r4
            r13 = -1
            if (r2 >= r6) goto L3b
            r14 = 1
            goto L3c
        L3b:
            r14 = -1
        L3c:
            if (r5 >= r7) goto L3f
            r13 = 1
        L3f:
            r15 = r2
            r16 = r5
        L42:
            if (r15 == r6) goto L9e
            if (r1 == 0) goto L49
            r17 = r16
            goto L4b
        L49:
            r17 = r15
        L4b:
            r18 = r1
            if (r1 == 0) goto L51
            r1 = r15
            goto L53
        L51:
            r1 = r16
        L53:
            if (r3 != r4) goto L62
            com.google.zxing.common.BitMatrix r4 = r0.image
            r20 = r6
            r6 = r17
            boolean r1 = r4.get(r6, r1)
            if (r1 == 0) goto L70
            goto L6e
        L62:
            r20 = r6
            r6 = r17
            com.google.zxing.common.BitMatrix r4 = r0.image
            boolean r1 = r4.get(r6, r1)
            if (r1 != 0) goto L70
        L6e:
            int r3 = r3 + 1
        L70:
            r1 = 3
            if (r3 != r1) goto L89
            int r15 = r15 - r2
            r1 = r16
            int r16 = r1 - r5
            if (r14 >= 0) goto L7c
            int r15 = r15 + 1
        L7c:
            int r15 = r15 * r15
            int r16 = r16 * r16
            int r15 = r15 + r16
            double r1 = (double) r15
        L83:
            double r1 = java.lang.Math.sqrt(r1)
            float r1 = (float) r1
            return r1
        L89:
            r1 = r16
            int r12 = r12 + r11
            if (r12 <= 0) goto L95
            if (r1 != r7) goto L91
            goto L9e
        L91:
            int r16 = r1 + r13
            int r12 = r12 - r9
            goto L97
        L95:
            r16 = r1
        L97:
            int r15 = r15 + r14
            r6 = r20
            r1 = r18
            r4 = 1
            goto L42
        L9e:
            int r8 = r8 * r8
            int r10 = r10 * r10
            int r8 = r8 + r10
            double r1 = (double) r8
            goto L83
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.Detector.sizeOfBlackWhiteBlackRun(int, int, int, int):float");
    }

    private float sizeOfBlackWhiteBlackRunBothWays(int i, int i2, int i3, int i4) {
        float width;
        float fSizeOfBlackWhiteBlackRun = sizeOfBlackWhiteBlackRun(i, i2, i3, i4);
        int width2 = i - (i3 - i);
        int height = 0;
        float height2 = 1.0f;
        if (width2 < 0) {
            width = i / (i - width2);
            width2 = 0;
        } else if (width2 > this.image.getWidth()) {
            width = (this.image.getWidth() - i) / (width2 - i);
            width2 = this.image.getWidth();
        } else {
            width = 1.0f;
        }
        float f = i2;
        int i5 = (int) (f - ((i4 - i2) * width));
        if (i5 < 0) {
            height2 = f / (i2 - i5);
        } else if (i5 > this.image.getHeight()) {
            height2 = (this.image.getHeight() - i2) / (i5 - i2);
            height = this.image.getHeight();
        } else {
            height = i5;
        }
        return fSizeOfBlackWhiteBlackRun + sizeOfBlackWhiteBlackRun(i, i2, (int) (i + ((width2 - i) * height2)), height);
    }

    protected float calculateModuleSize(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3) {
        return (calculateModuleSizeOneWay(resultPoint, resultPoint2) + calculateModuleSizeOneWay(resultPoint, resultPoint3)) / 2.0f;
    }

    public DetectorResult detect() throws NotFoundException, FormatException {
        return detect(null);
    }

    public DetectorResult detect(Hashtable hashtable) throws NotFoundException, FormatException {
        ResultPointCallback resultPointCallback = hashtable == null ? null : (ResultPointCallback) hashtable.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        this.resultPointCallback = resultPointCallback;
        return processFinderPatternInfo(new FinderPatternFinder(this.image, resultPointCallback).find(hashtable));
    }

    protected AlignmentPattern findAlignmentInRegion(float f, int i, int i2, float f2) throws NotFoundException {
        int i3 = (int) (f2 * f);
        int iMax = Math.max(0, i - i3);
        int iMin = Math.min(this.image.getWidth() - 1, i + i3) - iMax;
        float f3 = 3.0f * f;
        if (iMin < f3) {
            throw NotFoundException.getNotFoundInstance();
        }
        int iMax2 = Math.max(0, i2 - i3);
        int iMin2 = Math.min(this.image.getHeight() - 1, i2 + i3) - iMax2;
        if (iMin2 >= f3) {
            return new AlignmentPatternFinder(this.image, iMax, iMax2, iMin, iMin2, f, this.resultPointCallback).find();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected BitMatrix getImage() {
        return this.image;
    }

    protected ResultPointCallback getResultPointCallback() {
        return this.resultPointCallback;
    }

    protected DetectorResult processFinderPatternInfo(FinderPatternInfo finderPatternInfo) throws NotFoundException, FormatException {
        FinderPattern topLeft = finderPatternInfo.getTopLeft();
        FinderPattern topRight = finderPatternInfo.getTopRight();
        FinderPattern bottomLeft = finderPatternInfo.getBottomLeft();
        float fCalculateModuleSize = calculateModuleSize(topLeft, topRight, bottomLeft);
        if (fCalculateModuleSize < 1.0f) {
            throw NotFoundException.getNotFoundInstance();
        }
        int iComputeDimension = computeDimension(topLeft, topRight, bottomLeft, fCalculateModuleSize);
        Version provisionalVersionForDimension = Version.getProvisionalVersionForDimension(iComputeDimension);
        int dimensionForVersion = provisionalVersionForDimension.getDimensionForVersion() - 7;
        AlignmentPattern alignmentPatternFindAlignmentInRegion = null;
        if (provisionalVersionForDimension.getAlignmentPatternCenters().length > 0) {
            float x = (topRight.getX() - topLeft.getX()) + bottomLeft.getX();
            float y = (topRight.getY() - topLeft.getY()) + bottomLeft.getY();
            float f = 1.0f - (3.0f / dimensionForVersion);
            int x2 = (int) (topLeft.getX() + ((x - topLeft.getX()) * f));
            int y2 = (int) (topLeft.getY() + (f * (y - topLeft.getY())));
            for (int i = 4; i <= 16; i <<= 1) {
                try {
                    alignmentPatternFindAlignmentInRegion = findAlignmentInRegion(fCalculateModuleSize, x2, y2, i);
                    break;
                } catch (NotFoundException unused) {
                }
            }
        }
        return new DetectorResult(sampleGrid(this.image, createTransform(topLeft, topRight, bottomLeft, alignmentPatternFindAlignmentInRegion, iComputeDimension), iComputeDimension), alignmentPatternFindAlignmentInRegion == null ? new ResultPoint[]{bottomLeft, topLeft, topRight} : new ResultPoint[]{bottomLeft, topLeft, topRight, alignmentPatternFindAlignmentInRegion});
    }
}
