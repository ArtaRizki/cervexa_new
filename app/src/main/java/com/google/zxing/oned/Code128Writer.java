package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public final class Code128Writer extends UPCEANWriter {
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;

    private static boolean isDigits(String str, int i, int i2) {
        int i3 = i2 + i;
        while (i < i3) {
            char cCharAt = str.charAt(i);
            if (cCharAt < '0' || cCharAt > '9') {
                return false;
            }
            i++;
        }
        return true;
    }

    @Override // com.google.zxing.oned.UPCEANWriter, com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Hashtable hashtable) throws WriterException {
        if (barcodeFormat == BarcodeFormat.CODE_128) {
            return super.encode(str, barcodeFormat, i, i2, hashtable);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Can only encode CODE_128, but got ");
        stringBuffer.append(barcodeFormat);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    @Override // com.google.zxing.oned.UPCEANWriter
    public byte[] encode(String str) {
        int iCharAt;
        int length = str.length();
        if (length < 1 || length > 80) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Contents length should be between 1 and 80 characters, but got ");
            stringBuffer.append(length);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        int iAppendPattern = 0;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt < ' ' || cCharAt > '~') {
                throw new IllegalArgumentException("Contents should only contain characters between ' ' and '~'");
            }
        }
        Vector vector = new Vector();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1;
        while (i2 < length) {
            int i6 = i4 == 99 ? 2 : 4;
            int i7 = (length - i2 < i6 || !isDigits(str, i2, i6)) ? 100 : 99;
            if (i7 != i4) {
                int i8 = i7;
                iCharAt = i4 == 0 ? i7 == 100 ? 104 : 105 : i7;
                i4 = i8;
            } else if (i4 == 100) {
                iCharAt = str.charAt(i2) - ' ';
                i2++;
            } else {
                int i9 = i2 + 2;
                iCharAt = Integer.parseInt(str.substring(i2, i9));
                i2 = i9;
            }
            vector.addElement(Code128Reader.CODE_PATTERNS[iCharAt]);
            i3 += iCharAt * i5;
            if (i2 != 0) {
                i5++;
            }
        }
        vector.addElement(Code128Reader.CODE_PATTERNS[i3 % 103]);
        vector.addElement(Code128Reader.CODE_PATTERNS[106]);
        Enumeration enumerationElements = vector.elements();
        int i10 = 0;
        while (enumerationElements.hasMoreElements()) {
            for (int i11 : (int[]) enumerationElements.nextElement()) {
                i10 += i11;
            }
        }
        byte[] bArr = new byte[i10];
        Enumeration enumerationElements2 = vector.elements();
        while (enumerationElements2.hasMoreElements()) {
            iAppendPattern += appendPattern(bArr, iAppendPattern, (int[]) enumerationElements2.nextElement(), 1);
        }
        return bArr;
    }
}
