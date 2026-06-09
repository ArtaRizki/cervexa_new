package com.google.zxing.common.reedsolomon;

/* JADX INFO: loaded from: classes.dex */
public final class ReedSolomonDecoder {
    private final GenericGF field;

    public ReedSolomonDecoder(GenericGF genericGF) {
        this.field = genericGF;
    }

    private int[] findErrorLocations(GenericGFPoly genericGFPoly) throws ReedSolomonException {
        int degree = genericGFPoly.getDegree();
        int i = 0;
        if (degree == 1) {
            return new int[]{genericGFPoly.getCoefficient(1)};
        }
        int[] iArr = new int[degree];
        for (int i2 = 1; i2 < this.field.getSize() && i < degree; i2++) {
            if (genericGFPoly.evaluateAt(i2) == 0) {
                iArr[i] = this.field.inverse(i2);
                i++;
            }
        }
        if (i == degree) {
            return iArr;
        }
        throw new ReedSolomonException("Error locator degree does not match number of roots");
    }

    private int[] findErrorMagnitudes(GenericGFPoly genericGFPoly, int[] iArr, boolean z) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            int iInverse = this.field.inverse(iArr[i]);
            int iMultiply = 1;
            for (int i2 = 0; i2 < length; i2++) {
                if (i != i2) {
                    int iMultiply2 = this.field.multiply(iArr[i2], iInverse);
                    iMultiply = this.field.multiply(iMultiply, (iMultiply2 & 1) == 0 ? iMultiply2 | 1 : iMultiply2 & (-2));
                }
            }
            iArr2[i] = this.field.multiply(genericGFPoly.evaluateAt(iInverse), this.field.inverse(iMultiply));
            if (z) {
                iArr2[i] = this.field.multiply(iArr2[i], iInverse);
            }
        }
        return iArr2;
    }

    private GenericGFPoly[] runEuclideanAlgorithm(GenericGFPoly genericGFPoly, GenericGFPoly genericGFPoly2, int i) throws ReedSolomonException {
        if (genericGFPoly.getDegree() < genericGFPoly2.getDegree()) {
            genericGFPoly2 = genericGFPoly;
            genericGFPoly = genericGFPoly2;
        }
        GenericGFPoly one = this.field.getOne();
        GenericGFPoly zero = this.field.getZero();
        GenericGFPoly genericGFPoly3 = genericGFPoly2;
        GenericGFPoly genericGFPolyAddOrSubtract = genericGFPoly;
        GenericGFPoly genericGFPoly4 = genericGFPoly3;
        GenericGFPoly zero2 = this.field.getZero();
        GenericGFPoly one2 = this.field.getOne();
        while (genericGFPoly4.getDegree() >= i / 2) {
            if (genericGFPoly4.isZero()) {
                throw new ReedSolomonException("r_{i-1} was zero");
            }
            GenericGFPoly zero3 = this.field.getZero();
            int iInverse = this.field.inverse(genericGFPoly4.getCoefficient(genericGFPoly4.getDegree()));
            while (genericGFPolyAddOrSubtract.getDegree() >= genericGFPoly4.getDegree() && !genericGFPolyAddOrSubtract.isZero()) {
                int degree = genericGFPolyAddOrSubtract.getDegree() - genericGFPoly4.getDegree();
                int iMultiply = this.field.multiply(genericGFPolyAddOrSubtract.getCoefficient(genericGFPolyAddOrSubtract.getDegree()), iInverse);
                zero3 = zero3.addOrSubtract(this.field.buildMonomial(degree, iMultiply));
                genericGFPolyAddOrSubtract = genericGFPolyAddOrSubtract.addOrSubtract(genericGFPoly4.multiplyByMonomial(degree, iMultiply));
            }
            GenericGFPoly genericGFPolyAddOrSubtract2 = zero3.multiply(zero).addOrSubtract(one);
            GenericGFPoly genericGFPolyAddOrSubtract3 = zero3.multiply(one2).addOrSubtract(zero2);
            GenericGFPoly genericGFPoly5 = genericGFPolyAddOrSubtract;
            genericGFPolyAddOrSubtract = genericGFPoly4;
            genericGFPoly4 = genericGFPoly5;
            GenericGFPoly genericGFPoly6 = zero;
            zero = genericGFPolyAddOrSubtract2;
            one = genericGFPoly6;
            zero2 = one2;
            one2 = genericGFPolyAddOrSubtract3;
        }
        int coefficient = one2.getCoefficient(0);
        if (coefficient == 0) {
            throw new ReedSolomonException("sigmaTilde(0) was zero");
        }
        int iInverse2 = this.field.inverse(coefficient);
        return new GenericGFPoly[]{one2.multiply(iInverse2), genericGFPoly4.multiply(iInverse2)};
    }

    public void decode(int[] iArr, int i) throws ReedSolomonException {
        GenericGFPoly genericGFPoly = new GenericGFPoly(this.field, iArr);
        int[] iArr2 = new int[i];
        boolean zEquals = this.field.equals(GenericGF.DATA_MATRIX_FIELD_256);
        boolean z = true;
        for (int i2 = 0; i2 < i; i2++) {
            int iEvaluateAt = genericGFPoly.evaluateAt(this.field.exp(zEquals ? i2 + 1 : i2));
            iArr2[(i - 1) - i2] = iEvaluateAt;
            if (iEvaluateAt != 0) {
                z = false;
            }
        }
        if (z) {
            return;
        }
        GenericGFPoly[] genericGFPolyArrRunEuclideanAlgorithm = runEuclideanAlgorithm(this.field.buildMonomial(i, 1), new GenericGFPoly(this.field, iArr2), i);
        GenericGFPoly genericGFPoly2 = genericGFPolyArrRunEuclideanAlgorithm[0];
        GenericGFPoly genericGFPoly3 = genericGFPolyArrRunEuclideanAlgorithm[1];
        int[] iArrFindErrorLocations = findErrorLocations(genericGFPoly2);
        int[] iArrFindErrorMagnitudes = findErrorMagnitudes(genericGFPoly3, iArrFindErrorLocations, zEquals);
        for (int i3 = 0; i3 < iArrFindErrorLocations.length; i3++) {
            int length = (iArr.length - 1) - this.field.log(iArrFindErrorLocations[i3]);
            if (length < 0) {
                throw new ReedSolomonException("Bad error location");
            }
            iArr[length] = GenericGF.addOrSubtract(iArr[length], iArrFindErrorMagnitudes[i3]);
        }
    }
}
