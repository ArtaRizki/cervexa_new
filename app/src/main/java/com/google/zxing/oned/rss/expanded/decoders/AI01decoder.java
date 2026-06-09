package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

/* JADX INFO: loaded from: classes.dex */
abstract class AI01decoder extends AbstractExpandedDecoder {
    protected static final int gtinSize = 40;

    AI01decoder(BitArray bitArray) {
        super(bitArray);
    }

    private static void appendCheckDigit(StringBuffer stringBuffer, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < 13; i3++) {
            int iCharAt = stringBuffer.charAt(i3 + i) - '0';
            if ((i3 & 1) == 0) {
                iCharAt *= 3;
            }
            i2 += iCharAt;
        }
        int i4 = 10 - (i2 % 10);
        stringBuffer.append(i4 != 10 ? i4 : 0);
    }

    protected void encodeCompressedGtin(StringBuffer stringBuffer, int i) {
        stringBuffer.append("(01)");
        int length = stringBuffer.length();
        stringBuffer.append('9');
        encodeCompressedGtinWithoutAI(stringBuffer, i, length);
    }

    protected void encodeCompressedGtinWithoutAI(StringBuffer stringBuffer, int i, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            int iExtractNumericValueFromBitArray = this.generalDecoder.extractNumericValueFromBitArray((i3 * 10) + i, 10);
            if (iExtractNumericValueFromBitArray / 100 == 0) {
                stringBuffer.append('0');
            }
            if (iExtractNumericValueFromBitArray / 10 == 0) {
                stringBuffer.append('0');
            }
            stringBuffer.append(iExtractNumericValueFromBitArray);
        }
        appendCheckDigit(stringBuffer, i2);
    }
}
