package com.google.zxing.aztec.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;

/* JADX INFO: loaded from: classes.dex */
public final class Detector {
    private boolean compact;
    private final BitMatrix image;
    private int nbCenterLayers;
    private int nbDataBlocks;
    private int nbLayers;
    private int shift;

    private static class Point {

        /* JADX INFO: renamed from: x */
        public final int f2033x;

        /* JADX INFO: renamed from: y */
        public final int f2034y;

        private Point(int i, int i2) {
            this.f2033x = i;
            this.f2034y = i2;
        }

        public ResultPoint toResultPoint() {
            return new ResultPoint(this.f2033x, this.f2034y);
        }
    }

    public Detector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    private static void correctParameterData(boolean[] zArr, boolean z) throws NotFoundException {
        int i;
        int i2;
        if (z) {
            i = 7;
            i2 = 2;
        } else {
            i = 10;
            i2 = 4;
        }
        int i3 = i - i2;
        int[] iArr = new int[i];
        int i4 = 0;
        while (true) {
            if (i4 >= i) {
                try {
                    break;
                } catch (ReedSolomonException unused) {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
            int i5 = 1;
            for (int i6 = 1; i6 <= 4; i6++) {
                if (zArr[((4 * i4) + 4) - i6]) {
                    iArr[i4] = iArr[i4] + i5;
                }
                i5 <<= 1;
            }
            i4++;
        }
        new ReedSolomonDecoder(GenericGF.AZTEC_PARAM).decode(iArr, i3);
        for (int i7 = 0; i7 < i2; i7++) {
            int i8 = 1;
            for (int i9 = 1; i9 <= 4; i9++) {
                zArr[((i7 * 4) + 4) - i9] = (iArr[i7] & i8) == i8;
                i8 <<= 1;
            }
        }
    }

    private static float distance(Point point, Point point2) {
        return (float) Math.sqrt(((point.f2033x - point2.f2033x) * (point.f2033x - point2.f2033x)) + ((point.f2034y - point2.f2034y) * (point.f2034y - point2.f2034y)));
    }

    private void extractParameters(Point[] pointArr) throws NotFoundException {
        boolean[] zArr;
        int i = 0;
        boolean[] zArrSampleLine = sampleLine(pointArr[0], pointArr[1], (this.nbCenterLayers * 2) + 1);
        boolean[] zArrSampleLine2 = sampleLine(pointArr[1], pointArr[2], (this.nbCenterLayers * 2) + 1);
        boolean[] zArrSampleLine3 = sampleLine(pointArr[2], pointArr[3], (this.nbCenterLayers * 2) + 1);
        boolean[] zArrSampleLine4 = sampleLine(pointArr[3], pointArr[0], (this.nbCenterLayers * 2) + 1);
        if (zArrSampleLine[0] && zArrSampleLine[this.nbCenterLayers * 2]) {
            this.shift = 0;
        } else if (zArrSampleLine2[0] && zArrSampleLine2[this.nbCenterLayers * 2]) {
            this.shift = 1;
        } else if (zArrSampleLine3[0] && zArrSampleLine3[this.nbCenterLayers * 2]) {
            this.shift = 2;
        } else {
            if (!zArrSampleLine4[0] || !zArrSampleLine4[this.nbCenterLayers * 2]) {
                throw NotFoundException.getNotFoundInstance();
            }
            this.shift = 3;
        }
        if (this.compact) {
            boolean[] zArr2 = new boolean[28];
            for (int i2 = 0; i2 < 7; i2++) {
                int i3 = i2 + 2;
                zArr2[i2] = zArrSampleLine[i3];
                zArr2[i2 + 7] = zArrSampleLine2[i3];
                zArr2[i2 + 14] = zArrSampleLine3[i3];
                zArr2[i2 + 21] = zArrSampleLine4[i3];
            }
            zArr = new boolean[28];
            while (i < 28) {
                zArr[i] = zArr2[((this.shift * 7) + i) % 28];
                i++;
            }
        } else {
            boolean[] zArr3 = new boolean[40];
            for (int i4 = 0; i4 < 11; i4++) {
                if (i4 < 5) {
                    int i5 = i4 + 2;
                    zArr3[i4] = zArrSampleLine[i5];
                    zArr3[i4 + 10] = zArrSampleLine2[i5];
                    zArr3[i4 + 20] = zArrSampleLine3[i5];
                    zArr3[i4 + 30] = zArrSampleLine4[i5];
                }
                if (i4 > 5) {
                    int i6 = i4 + 2;
                    zArr3[i4 - 1] = zArrSampleLine[i6];
                    zArr3[(i4 + 10) - 1] = zArrSampleLine2[i6];
                    zArr3[(i4 + 20) - 1] = zArrSampleLine3[i6];
                    zArr3[(i4 + 30) - 1] = zArrSampleLine4[i6];
                }
            }
            zArr = new boolean[40];
            while (i < 40) {
                zArr[i] = zArr3[((this.shift * 10) + i) % 40];
                i++;
            }
        }
        correctParameterData(zArr, this.compact);
        getParameters(zArr);
    }

    private Point[] getBullEyeCornerPoints(Point point) throws NotFoundException {
        this.nbCenterLayers = 1;
        Point point2 = point;
        Point point3 = point2;
        Point point4 = point3;
        Point point5 = point4;
        boolean z = true;
        while (this.nbCenterLayers < 9) {
            Point firstDifferent = getFirstDifferent(point2, z, 1, -1);
            Point firstDifferent2 = getFirstDifferent(point3, z, 1, 1);
            Point firstDifferent3 = getFirstDifferent(point4, z, -1, 1);
            Point firstDifferent4 = getFirstDifferent(point5, z, -1, -1);
            if (this.nbCenterLayers > 2) {
                double dDistance = (distance(firstDifferent4, firstDifferent) * this.nbCenterLayers) / (distance(point5, point2) * (this.nbCenterLayers + 2));
                if (dDistance < 0.75d || dDistance > 1.25d || !isWhiteOrBlackRectangle(firstDifferent, firstDifferent2, firstDifferent3, firstDifferent4)) {
                    break;
                }
            }
            z = !z;
            this.nbCenterLayers++;
            point5 = firstDifferent4;
            point2 = firstDifferent;
            point3 = firstDifferent2;
            point4 = firstDifferent3;
        }
        int i = this.nbCenterLayers;
        if (i != 5 && i != 7) {
            throw NotFoundException.getNotFoundInstance();
        }
        this.compact = this.nbCenterLayers == 5;
        float f = 1.5f / ((this.nbCenterLayers * 2) - 3);
        int i2 = point2.f2033x - point4.f2033x;
        int i3 = point2.f2034y - point4.f2034y;
        float f2 = i2 * f;
        int iRound = round(point4.f2033x - f2);
        float f3 = i3 * f;
        int iRound2 = round(point4.f2034y - f3);
        int iRound3 = round(point2.f2033x + f2);
        int iRound4 = round(point2.f2034y + f3);
        int i4 = point3.f2033x - point5.f2033x;
        int i5 = point3.f2034y - point5.f2034y;
        float f4 = i4 * f;
        int iRound5 = round(point5.f2033x - f4);
        float f5 = f * i5;
        int iRound6 = round(point5.f2034y - f5);
        int iRound7 = round(point3.f2033x + f4);
        int iRound8 = round(point3.f2034y + f5);
        if (!isValid(iRound3, iRound4) || !isValid(iRound7, iRound8) || !isValid(iRound, iRound2) || !isValid(iRound5, iRound6)) {
            throw NotFoundException.getNotFoundInstance();
        }
        return new Point[]{new Point(iRound3, iRound4), new Point(iRound7, iRound8), new Point(iRound, iRound2), new Point(iRound5, iRound6)};
    }

    private int getColor(Point point, Point point2) {
        float fDistance = distance(point, point2);
        float f = (point2.f2033x - point.f2033x) / fDistance;
        float f2 = (point2.f2034y - point.f2034y) / fDistance;
        float f3 = point.f2033x;
        float f4 = point.f2034y;
        boolean z = this.image.get(point.f2033x, point.f2034y);
        int i = 0;
        for (int i2 = 0; i2 < fDistance; i2++) {
            f3 += f;
            f4 += f2;
            if (this.image.get(round(f3), round(f4)) != z) {
                i++;
            }
        }
        double d = i / fDistance;
        if (d <= 0.1d || d >= 0.9d) {
            return d <= 0.1d ? z ? 1 : -1 : z ? -1 : 1;
        }
        return 0;
    }

    private Point getFirstDifferent(Point point, boolean z, int i, int i2) {
        int i3 = point.f2033x + i;
        int i4 = point.f2034y;
        while (true) {
            i4 += i2;
            if (!isValid(i3, i4) || this.image.get(i3, i4) != z) {
                break;
            }
            i3 += i;
        }
        int i5 = i3 - i;
        int i6 = i4 - i2;
        while (isValid(i5, i6) && this.image.get(i5, i6) == z) {
            i5 += i;
        }
        int i7 = i5 - i;
        while (isValid(i7, i6) && this.image.get(i7, i6) == z) {
            i6 += i2;
        }
        return new Point(i7, i6 - i2);
    }

    private Point getMatrixCenter() {
        ResultPoint resultPoint;
        ResultPoint resultPoint2;
        ResultPoint resultPoint3;
        ResultPoint resultPoint4;
        ResultPoint resultPoint5;
        ResultPoint resultPoint6;
        ResultPoint resultPoint7;
        ResultPoint resultPoint8;
        try {
            ResultPoint[] resultPointArrDetect = new WhiteRectangleDetector(this.image).detect();
            resultPoint3 = resultPointArrDetect[0];
            resultPoint4 = resultPointArrDetect[1];
            resultPoint2 = resultPointArrDetect[2];
            resultPoint = resultPointArrDetect[3];
        } catch (NotFoundException unused) {
            int i = this.image.width / 2;
            int i2 = this.image.height / 2;
            int i3 = i + 7;
            int i4 = i2 - 7;
            ResultPoint resultPoint9 = getFirstDifferent(new Point(i3, i4), false, 1, -1).toResultPoint();
            int i5 = i2 + 7;
            ResultPoint resultPoint10 = getFirstDifferent(new Point(i3, i5), false, 1, 1).toResultPoint();
            int i6 = i - 7;
            ResultPoint resultPoint11 = getFirstDifferent(new Point(i6, i5), false, -1, 1).toResultPoint();
            resultPoint = getFirstDifferent(new Point(i6, i4), false, -1, -1).toResultPoint();
            resultPoint2 = resultPoint11;
            resultPoint3 = resultPoint9;
            resultPoint4 = resultPoint10;
        }
        int iRound = round((((resultPoint3.getX() + resultPoint.getX()) + resultPoint4.getX()) + resultPoint2.getX()) / 4.0f);
        int iRound2 = round((((resultPoint3.getY() + resultPoint.getY()) + resultPoint4.getY()) + resultPoint2.getY()) / 4.0f);
        try {
            ResultPoint[] resultPointArrDetect2 = new WhiteRectangleDetector(this.image, 15, iRound, iRound2).detect();
            resultPoint5 = resultPointArrDetect2[0];
            resultPoint6 = resultPointArrDetect2[1];
            resultPoint7 = resultPointArrDetect2[2];
            resultPoint8 = resultPointArrDetect2[3];
        } catch (NotFoundException unused2) {
            int i7 = iRound + 7;
            int i8 = iRound2 - 7;
            resultPoint5 = getFirstDifferent(new Point(i7, i8), false, 1, -1).toResultPoint();
            int i9 = iRound2 + 7;
            resultPoint6 = getFirstDifferent(new Point(i7, i9), false, 1, 1).toResultPoint();
            int i10 = iRound - 7;
            resultPoint7 = getFirstDifferent(new Point(i10, i9), false, -1, 1).toResultPoint();
            resultPoint8 = getFirstDifferent(new Point(i10, i8), false, -1, -1).toResultPoint();
        }
        return new Point(round((((resultPoint5.getX() + resultPoint8.getX()) + resultPoint6.getX()) + resultPoint7.getX()) / 4.0f), round((((resultPoint5.getY() + resultPoint8.getY()) + resultPoint6.getY()) + resultPoint7.getY()) / 4.0f));
    }

    private ResultPoint[] getMatrixCornerPoints(Point[] pointArr) throws NotFoundException {
        float f = (((r0 * 2) + (this.nbLayers > 4 ? 1 : 0)) + ((this.nbLayers - 4) / 8)) / (this.nbCenterLayers * 2.0f);
        int i = pointArr[0].f2033x - pointArr[2].f2033x;
        int i2 = i + (i > 0 ? 1 : -1);
        int i3 = pointArr[0].f2034y - pointArr[2].f2034y;
        int i4 = i3 + (i3 > 0 ? 1 : -1);
        float f2 = i2 * f;
        int iRound = round(pointArr[2].f2033x - f2);
        float f3 = i4 * f;
        int iRound2 = round(pointArr[2].f2034y - f3);
        int iRound3 = round(pointArr[0].f2033x + f2);
        int iRound4 = round(pointArr[0].f2034y + f3);
        int i5 = pointArr[1].f2033x - pointArr[3].f2033x;
        int i6 = i5 + (i5 > 0 ? 1 : -1);
        int i7 = pointArr[1].f2034y - pointArr[3].f2034y;
        int i8 = i7 + (i7 > 0 ? 1 : -1);
        float f4 = i6 * f;
        int iRound5 = round(pointArr[3].f2033x - f4);
        float f5 = f * i8;
        int iRound6 = round(pointArr[3].f2034y - f5);
        int iRound7 = round(pointArr[1].f2033x + f4);
        int iRound8 = round(pointArr[1].f2034y + f5);
        if (isValid(iRound3, iRound4) && isValid(iRound7, iRound8) && isValid(iRound, iRound2) && isValid(iRound5, iRound6)) {
            return new ResultPoint[]{new ResultPoint(iRound3, iRound4), new ResultPoint(iRound7, iRound8), new ResultPoint(iRound, iRound2), new ResultPoint(iRound5, iRound6)};
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void getParameters(boolean[] zArr) {
        int i;
        int i2;
        if (this.compact) {
            i = 2;
            i2 = 6;
        } else {
            i = 5;
            i2 = 11;
        }
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = this.nbLayers << 1;
            this.nbLayers = i4;
            if (zArr[i3]) {
                this.nbLayers = i4 + 1;
            }
        }
        for (int i5 = i; i5 < i + i2; i5++) {
            int i6 = this.nbDataBlocks << 1;
            this.nbDataBlocks = i6;
            if (zArr[i5]) {
                this.nbDataBlocks = i6 + 1;
            }
        }
        this.nbLayers++;
        this.nbDataBlocks++;
    }

    private boolean isValid(int i, int i2) {
        return i >= 0 && i < this.image.width && i2 > 0 && i2 < this.image.height;
    }

    private boolean isWhiteOrBlackRectangle(Point point, Point point2, Point point3, Point point4) {
        int color;
        int color2;
        int color3;
        Point point5 = new Point(point.f2033x - 3, point.f2034y + 3);
        Point point6 = new Point(point2.f2033x - 3, point2.f2034y - 3);
        Point point7 = new Point(point3.f2033x + 3, point3.f2034y - 3);
        Point point8 = new Point(point4.f2033x + 3, point4.f2034y + 3);
        int color4 = getColor(point8, point5);
        return (color4 == 0 || (color = getColor(point5, point6)) != color4 || color == 0 || (color2 = getColor(point6, point7)) != color4 || color2 == 0 || (color3 = getColor(point7, point8)) != color4 || color3 == 0) ? false : true;
    }

    private static int round(float f) {
        return (int) (f + 0.5f);
    }

    private BitMatrix sampleGrid(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) throws NotFoundException {
        int i;
        if (this.compact) {
            i = (this.nbLayers * 4) + 11;
        } else {
            int i2 = this.nbLayers;
            i = i2 <= 4 ? (i2 * 4) + 15 : (i2 * 4) + ((((i2 - 4) / 8) + 1) * 2) + 15;
        }
        int i3 = i;
        float f = i3 - 0.5f;
        return GridSampler.getInstance().sampleGrid(bitMatrix, i3, i3, 0.5f, 0.5f, f, 0.5f, f, f, 0.5f, f, resultPoint.getX(), resultPoint.getY(), resultPoint4.getX(), resultPoint4.getY(), resultPoint3.getX(), resultPoint3.getY(), resultPoint2.getX(), resultPoint2.getY());
    }

    private boolean[] sampleLine(Point point, Point point2, int i) {
        boolean[] zArr = new boolean[i];
        float fDistance = distance(point, point2);
        float f = fDistance / (i - 1);
        float f2 = ((point2.f2033x - point.f2033x) * f) / fDistance;
        float f3 = (f * (point2.f2034y - point.f2034y)) / fDistance;
        float f4 = point.f2033x;
        float f5 = point.f2034y;
        for (int i2 = 0; i2 < i; i2++) {
            zArr[i2] = this.image.get(round(f4), round(f5));
            f4 += f2;
            f5 += f3;
        }
        return zArr;
    }

    public AztecDetectorResult detect() throws NotFoundException {
        Point[] bullEyeCornerPoints = getBullEyeCornerPoints(getMatrixCenter());
        extractParameters(bullEyeCornerPoints);
        ResultPoint[] matrixCornerPoints = getMatrixCornerPoints(bullEyeCornerPoints);
        BitMatrix bitMatrix = this.image;
        int i = this.shift;
        return new AztecDetectorResult(sampleGrid(bitMatrix, matrixCornerPoints[i % 4], matrixCornerPoints[(i + 3) % 4], matrixCornerPoints[(i + 2) % 4], matrixCornerPoints[(i + 1) % 4]), matrixCornerPoints, this.compact, this.nbDataBlocks, this.nbLayers);
    }
}
