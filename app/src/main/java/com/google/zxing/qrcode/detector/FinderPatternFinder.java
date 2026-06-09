package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.Collections;
import com.google.zxing.common.Comparator;
import com.jieli.stream.p016dv.running2.util.VideoManager;
import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class FinderPatternFinder {
    private static final int CENTER_QUORUM = 2;
    private static final int INTEGER_MATH_SHIFT = 8;
    protected static final int MAX_MODULES = 57;
    protected static final int MIN_SKIP = 3;
    private final int[] crossCheckStateCount;
    private boolean hasSkipped;
    private final BitMatrix image;
    private final Vector possibleCenters;
    private final ResultPointCallback resultPointCallback;

    private static class CenterComparator implements Comparator {
        private final float average;

        private CenterComparator(float f) {
            this.average = f;
        }

        @Override // com.google.zxing.common.Comparator
        public int compare(Object obj, Object obj2) {
            FinderPattern finderPattern = (FinderPattern) obj2;
            FinderPattern finderPattern2 = (FinderPattern) obj;
            if (finderPattern.getCount() != finderPattern2.getCount()) {
                return finderPattern.getCount() - finderPattern2.getCount();
            }
            float fAbs = Math.abs(finderPattern.getEstimatedModuleSize() - this.average);
            float fAbs2 = Math.abs(finderPattern2.getEstimatedModuleSize() - this.average);
            if (fAbs < fAbs2) {
                return 1;
            }
            return fAbs == fAbs2 ? 0 : -1;
        }
    }

    private static class FurthestFromAverageComparator implements Comparator {
        private final float average;

        private FurthestFromAverageComparator(float f) {
            this.average = f;
        }

        @Override // com.google.zxing.common.Comparator
        public int compare(Object obj, Object obj2) {
            float fAbs = Math.abs(((FinderPattern) obj2).getEstimatedModuleSize() - this.average);
            float fAbs2 = Math.abs(((FinderPattern) obj).getEstimatedModuleSize() - this.average);
            if (fAbs < fAbs2) {
                return -1;
            }
            return fAbs == fAbs2 ? 0 : 1;
        }
    }

    public FinderPatternFinder(BitMatrix bitMatrix) {
        this(bitMatrix, null);
    }

    public FinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback) {
        this.image = bitMatrix;
        this.possibleCenters = new Vector();
        this.crossCheckStateCount = new int[5];
        this.resultPointCallback = resultPointCallback;
    }

    private static float centerFromEnd(int[] iArr, int i) {
        return ((i - iArr[4]) - iArr[3]) - (iArr[2] / 2.0f);
    }

    private float crossCheckHorizontal(int i, int i2, int i3, int i4) {
        BitMatrix bitMatrix = this.image;
        int width = bitMatrix.getWidth();
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int i5 = i;
        while (i5 >= 0 && bitMatrix.get(i5, i2)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i5--;
        }
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i5 >= 0 && !bitMatrix.get(i5, i2) && crossCheckStateCount[1] <= i3) {
            crossCheckStateCount[1] = crossCheckStateCount[1] + 1;
            i5--;
        }
        if (i5 < 0 || crossCheckStateCount[1] > i3) {
            return Float.NaN;
        }
        while (i5 >= 0 && bitMatrix.get(i5, i2) && crossCheckStateCount[0] <= i3) {
            crossCheckStateCount[0] = crossCheckStateCount[0] + 1;
            i5--;
        }
        if (crossCheckStateCount[0] > i3) {
            return Float.NaN;
        }
        int i6 = i + 1;
        while (i6 < width && bitMatrix.get(i6, i2)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i6++;
        }
        if (i6 == width) {
            return Float.NaN;
        }
        while (i6 < width && !bitMatrix.get(i6, i2) && crossCheckStateCount[3] < i3) {
            crossCheckStateCount[3] = crossCheckStateCount[3] + 1;
            i6++;
        }
        if (i6 == width || crossCheckStateCount[3] >= i3) {
            return Float.NaN;
        }
        while (i6 < width && bitMatrix.get(i6, i2) && crossCheckStateCount[4] < i3) {
            crossCheckStateCount[4] = crossCheckStateCount[4] + 1;
            i6++;
        }
        if (crossCheckStateCount[4] < i3 && Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + crossCheckStateCount[4]) - i4) * 5 < i4 && foundPatternCross(crossCheckStateCount)) {
            return centerFromEnd(crossCheckStateCount, i6);
        }
        return Float.NaN;
    }

    private float crossCheckVertical(int i, int i2, int i3, int i4) {
        BitMatrix bitMatrix = this.image;
        int height = bitMatrix.getHeight();
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int i5 = i;
        while (i5 >= 0 && bitMatrix.get(i2, i5)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i5--;
        }
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i5 >= 0 && !bitMatrix.get(i2, i5) && crossCheckStateCount[1] <= i3) {
            crossCheckStateCount[1] = crossCheckStateCount[1] + 1;
            i5--;
        }
        if (i5 < 0 || crossCheckStateCount[1] > i3) {
            return Float.NaN;
        }
        while (i5 >= 0 && bitMatrix.get(i2, i5) && crossCheckStateCount[0] <= i3) {
            crossCheckStateCount[0] = crossCheckStateCount[0] + 1;
            i5--;
        }
        if (crossCheckStateCount[0] > i3) {
            return Float.NaN;
        }
        int i6 = i + 1;
        while (i6 < height && bitMatrix.get(i2, i6)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i6++;
        }
        if (i6 == height) {
            return Float.NaN;
        }
        while (i6 < height && !bitMatrix.get(i2, i6) && crossCheckStateCount[3] < i3) {
            crossCheckStateCount[3] = crossCheckStateCount[3] + 1;
            i6++;
        }
        if (i6 == height || crossCheckStateCount[3] >= i3) {
            return Float.NaN;
        }
        while (i6 < height && bitMatrix.get(i2, i6) && crossCheckStateCount[4] < i3) {
            crossCheckStateCount[4] = crossCheckStateCount[4] + 1;
            i6++;
        }
        if (crossCheckStateCount[4] < i3 && Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + crossCheckStateCount[4]) - i4) * 5 < i4 * 2 && foundPatternCross(crossCheckStateCount)) {
            return centerFromEnd(crossCheckStateCount, i6);
        }
        return Float.NaN;
    }

    private int findRowSkip() {
        int size = this.possibleCenters.size();
        if (size <= 1) {
            return 0;
        }
        FinderPattern finderPattern = null;
        for (int i = 0; i < size; i++) {
            FinderPattern finderPattern2 = (FinderPattern) this.possibleCenters.elementAt(i);
            if (finderPattern2.getCount() >= 2) {
                if (finderPattern != null) {
                    this.hasSkipped = true;
                    return ((int) (Math.abs(finderPattern.getX() - finderPattern2.getX()) - Math.abs(finderPattern.getY() - finderPattern2.getY()))) / 2;
                }
                finderPattern = finderPattern2;
            }
        }
        return 0;
    }

    protected static boolean foundPatternCross(int[] iArr) {
        int i;
        int i2;
        int i3 = 0;
        for (int i4 = 0; i4 < 5; i4++) {
            int i5 = iArr[i4];
            if (i5 == 0) {
                return false;
            }
            i3 += i5;
        }
        return i3 >= 7 && Math.abs(i - (iArr[0] << 8)) < (i2 = (i = (i3 << 8) / 7) / 2) && Math.abs(i - (iArr[1] << 8)) < i2 && Math.abs((i * 3) - (iArr[2] << 8)) < i2 * 3 && Math.abs(i - (iArr[3] << 8)) < i2 && Math.abs(i - (iArr[4] << 8)) < i2;
    }

    private int[] getCrossCheckStateCount() {
        int[] iArr = this.crossCheckStateCount;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        return iArr;
    }

    private boolean haveMultiplyConfirmedCenters() {
        int size = this.possibleCenters.size();
        float fAbs = 0.0f;
        int i = 0;
        float estimatedModuleSize = 0.0f;
        for (int i2 = 0; i2 < size; i2++) {
            FinderPattern finderPattern = (FinderPattern) this.possibleCenters.elementAt(i2);
            if (finderPattern.getCount() >= 2) {
                i++;
                estimatedModuleSize += finderPattern.getEstimatedModuleSize();
            }
        }
        if (i < 3) {
            return false;
        }
        float f = estimatedModuleSize / size;
        for (int i3 = 0; i3 < size; i3++) {
            fAbs += Math.abs(((FinderPattern) this.possibleCenters.elementAt(i3)).getEstimatedModuleSize() - f);
        }
        return fAbs <= estimatedModuleSize * 0.05f;
    }

    private FinderPattern[] selectBestPatterns() throws NotFoundException {
        int size = this.possibleCenters.size();
        if (size < 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        float estimatedModuleSize = 0.0f;
        if (size > 3) {
            float f = 0.0f;
            float f2 = 0.0f;
            for (int i = 0; i < size; i++) {
                float estimatedModuleSize2 = ((FinderPattern) this.possibleCenters.elementAt(i)).getEstimatedModuleSize();
                f += estimatedModuleSize2;
                f2 += estimatedModuleSize2 * estimatedModuleSize2;
            }
            float f3 = f / size;
            float fSqrt = (float) Math.sqrt((f2 / r0) - (f3 * f3));
            Collections.insertionSort(this.possibleCenters, new FurthestFromAverageComparator(f3));
            float fMax = Math.max(0.2f * f3, fSqrt);
            int i2 = 0;
            while (i2 < this.possibleCenters.size() && this.possibleCenters.size() > 3) {
                if (Math.abs(((FinderPattern) this.possibleCenters.elementAt(i2)).getEstimatedModuleSize() - f3) > fMax) {
                    this.possibleCenters.removeElementAt(i2);
                    i2--;
                }
                i2++;
            }
        }
        if (this.possibleCenters.size() > 3) {
            for (int i3 = 0; i3 < this.possibleCenters.size(); i3++) {
                estimatedModuleSize += ((FinderPattern) this.possibleCenters.elementAt(i3)).getEstimatedModuleSize();
            }
            Collections.insertionSort(this.possibleCenters, new CenterComparator(estimatedModuleSize / this.possibleCenters.size()));
            this.possibleCenters.setSize(3);
        }
        return new FinderPattern[]{(FinderPattern) this.possibleCenters.elementAt(0), (FinderPattern) this.possibleCenters.elementAt(1), (FinderPattern) this.possibleCenters.elementAt(2)};
    }

    FinderPatternInfo find(Hashtable hashtable) throws NotFoundException {
        boolean z = hashtable != null && hashtable.containsKey(DecodeHintType.TRY_HARDER);
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i = (height * 3) / VideoManager.ERROR_CLOSE_RECORD;
        if (i < 3 || z) {
            i = 3;
        }
        int[] iArr = new int[5];
        int i2 = i - 1;
        boolean zHaveMultiplyConfirmedCenters = false;
        while (i2 < height && !zHaveMultiplyConfirmedCenters) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            int i3 = 0;
            int i4 = 0;
            while (i3 < width) {
                if (this.image.get(i3, i2)) {
                    if ((i4 & 1) == 1) {
                        i4++;
                    }
                    iArr[i4] = iArr[i4] + 1;
                } else if ((i4 & 1) != 0) {
                    iArr[i4] = iArr[i4] + 1;
                } else if (i4 != 4) {
                    i4++;
                    iArr[i4] = iArr[i4] + 1;
                } else if (foundPatternCross(iArr) && handlePossibleCenter(iArr, i2, i3)) {
                    if (this.hasSkipped) {
                        zHaveMultiplyConfirmedCenters = haveMultiplyConfirmedCenters();
                    } else {
                        int iFindRowSkip = findRowSkip();
                        if (iFindRowSkip > iArr[2]) {
                            i2 += (iFindRowSkip - iArr[2]) - 2;
                            i3 = width - 1;
                        }
                    }
                    iArr[0] = 0;
                    iArr[1] = 0;
                    iArr[2] = 0;
                    iArr[3] = 0;
                    iArr[4] = 0;
                    i = 2;
                    i4 = 0;
                } else {
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = iArr[4];
                    iArr[3] = 1;
                    iArr[4] = 0;
                    i4 = 3;
                }
                i3++;
            }
            if (foundPatternCross(iArr) && handlePossibleCenter(iArr, i2, width)) {
                i = iArr[0];
                if (this.hasSkipped) {
                    zHaveMultiplyConfirmedCenters = haveMultiplyConfirmedCenters();
                }
            }
            i2 += i;
        }
        FinderPattern[] finderPatternArrSelectBestPatterns = selectBestPatterns();
        ResultPoint.orderBestPatterns(finderPatternArrSelectBestPatterns);
        return new FinderPatternInfo(finderPatternArrSelectBestPatterns);
    }

    protected BitMatrix getImage() {
        return this.image;
    }

    protected Vector getPossibleCenters() {
        return this.possibleCenters;
    }

    protected boolean handlePossibleCenter(int[] iArr, int i, int i2) {
        boolean z = false;
        int i3 = iArr[0] + iArr[1] + iArr[2] + iArr[3] + iArr[4];
        int iCenterFromEnd = (int) centerFromEnd(iArr, i2);
        float fCrossCheckVertical = crossCheckVertical(i, iCenterFromEnd, iArr[2], i3);
        if (!Float.isNaN(fCrossCheckVertical)) {
            float fCrossCheckHorizontal = crossCheckHorizontal(iCenterFromEnd, (int) fCrossCheckVertical, iArr[2], i3);
            if (!Float.isNaN(fCrossCheckHorizontal)) {
                float f = i3 / 7.0f;
                int size = this.possibleCenters.size();
                int i4 = 0;
                while (true) {
                    if (i4 >= size) {
                        break;
                    }
                    FinderPattern finderPattern = (FinderPattern) this.possibleCenters.elementAt(i4);
                    if (finderPattern.aboutEquals(f, fCrossCheckVertical, fCrossCheckHorizontal)) {
                        finderPattern.incrementCount();
                        z = true;
                        break;
                    }
                    i4++;
                }
                if (!z) {
                    FinderPattern finderPattern2 = new FinderPattern(fCrossCheckHorizontal, fCrossCheckVertical, f);
                    this.possibleCenters.addElement(finderPattern2);
                    ResultPointCallback resultPointCallback = this.resultPointCallback;
                    if (resultPointCallback != null) {
                        resultPointCallback.foundPossibleResultPoint(finderPattern2);
                    }
                }
                return true;
            }
        }
        return false;
    }
}
