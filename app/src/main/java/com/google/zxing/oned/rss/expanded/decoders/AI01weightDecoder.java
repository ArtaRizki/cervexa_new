package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

/* JADX INFO: loaded from: classes.dex */
abstract class AI01weightDecoder extends AI01decoder {
    AI01weightDecoder(BitArray bitArray) {
        super(bitArray);
    }

    protected abstract void addWeightCode(StringBuffer stringBuffer, int i);

    protected abstract int checkWeight(int i);

    protected void encodeCompressedWeight(StringBuffer stringBuffer, int i, int i2) {
        int iExtractNumericValueFromBitArray = this.generalDecoder.extractNumericValueFromBitArray(i, i2);
        addWeightCode(stringBuffer, iExtractNumericValueFromBitArray);
        int iCheckWeight = checkWeight(iExtractNumericValueFromBitArray);
        int i3 = 100000;
        for (int i4 = 0; i4 < 5; i4++) {
            if (iCheckWeight / i3 == 0) {
                stringBuffer.append('0');
            }
            i3 /= 10;
        }
        stringBuffer.append(iCheckWeight);
    }
}
