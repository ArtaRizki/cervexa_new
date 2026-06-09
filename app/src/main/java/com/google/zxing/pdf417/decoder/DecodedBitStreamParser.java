package com.google.zxing.pdf417.decoder;

import kotlin.text.Typography;

/* JADX INFO: loaded from: classes.dex */
final class DecodedBitStreamParser {

    /* JADX INFO: renamed from: AL */
    private static final int f2038AL = 28;
    private static final int ALPHA = 0;
    private static final int ALPHA_SHIFT = 4;

    /* JADX INFO: renamed from: AS */
    private static final int f2039AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;

    /* JADX INFO: renamed from: LL */
    private static final int f2040LL = 27;
    private static final int LOWER = 1;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final int MIXED = 2;

    /* JADX INFO: renamed from: ML */
    private static final int f2041ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;

    /* JADX INFO: renamed from: PL */
    private static final int f2042PL = 25;

    /* JADX INFO: renamed from: PS */
    private static final int f2043PS = 29;
    private static final int PUNCT = 3;
    private static final int PUNCT_SHIFT = 5;
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;
    private static final char[] PUNCT_CHARS = {';', Typography.less, Typography.greater, '@', '[', '\\', '}', '_', '`', '~', '!', '\r', '\t', ',', ':', '\n', '-', '.', Typography.dollar, '/', Typography.quote, '|', '*', '(', ')', '?', '{', '}', '\''};
    private static final char[] MIXED_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', Typography.amp, '\r', '\t', ',', ':', '#', '-', '.', Typography.dollar, '/', '+', '%', '*', '=', '^'};
    private static final String[] EXP900 = {"000000000000000000000000000000000000000000001", "000000000000000000000000000000000000000000900", "000000000000000000000000000000000000000810000", "000000000000000000000000000000000000729000000", "000000000000000000000000000000000656100000000", "000000000000000000000000000000590490000000000", "000000000000000000000000000531441000000000000", "000000000000000000000000478296900000000000000", "000000000000000000000430467210000000000000000", "000000000000000000387420489000000000000000000", "000000000000000348678440100000000000000000000", "000000000000313810596090000000000000000000000", "000000000282429536481000000000000000000000000", "000000254186582832900000000000000000000000000", "000228767924549610000000000000000000000000000", "205891132094649000000000000000000000000000000"};

    private DecodedBitStreamParser() {
    }

    private static StringBuffer add(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer(5);
        StringBuffer stringBuffer2 = new StringBuffer(5);
        StringBuffer stringBuffer3 = new StringBuffer(str.length());
        for (int i = 0; i < str.length(); i++) {
            stringBuffer3.append('0');
        }
        int length = str.length() - 3;
        int i2 = 0;
        while (length > -1) {
            stringBuffer.setLength(0);
            stringBuffer.append(str.charAt(length));
            int i3 = length + 1;
            stringBuffer.append(str.charAt(i3));
            int i4 = length + 2;
            stringBuffer.append(str.charAt(i4));
            stringBuffer2.setLength(0);
            stringBuffer2.append(str2.charAt(length));
            stringBuffer2.append(str2.charAt(i3));
            stringBuffer2.append(str2.charAt(i4));
            int i5 = Integer.parseInt(stringBuffer.toString()) + Integer.parseInt(stringBuffer2.toString()) + i2;
            int i6 = i5 % 1000;
            stringBuffer3.setCharAt(i4, (char) ((i6 % 10) + 48));
            stringBuffer3.setCharAt(i3, (char) (((i6 / 10) % 10) + 48));
            stringBuffer3.setCharAt(length, (char) ((i6 / 100) + 48));
            length -= 3;
            i2 = i5 / 1000;
        }
        return stringBuffer3;
    }

    private static int byteCompaction(int i, int[] iArr, int i2, StringBuffer stringBuffer) {
        boolean z;
        boolean z2;
        long j = 900;
        int i3 = 6;
        if (i != 901) {
            if (i != BYTE_COMPACTION_MODE_LATCH_6) {
                return i2;
            }
            int i4 = i2;
            boolean z3 = false;
            int i5 = 0;
            long j2 = 0;
            while (i4 < iArr[0] && !z3) {
                int i6 = i4 + 1;
                int i7 = iArr[i4];
                if (i7 < 900) {
                    i5++;
                    j2 = (j2 * 900) + ((long) i7);
                    z = z3;
                    i4 = i6;
                } else {
                    boolean z4 = z3;
                    if (i7 != 900 && i7 != 901 && i7 != 902 && i7 != BYTE_COMPACTION_MODE_LATCH_6 && i7 != BEGIN_MACRO_PDF417_CONTROL_BLOCK && i7 != BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
                        if (i7 != MACRO_PDF417_TERMINATOR) {
                            z = z4;
                            i4 = i6;
                        }
                    }
                    i4 = i6 - 1;
                    z = true;
                }
                if (i5 % 5 != 0 || i5 <= 0) {
                    z2 = z;
                } else {
                    char[] cArr = new char[6];
                    int i8 = 0;
                    while (i8 < 6) {
                        cArr[5 - i8] = (char) (j2 & 255);
                        j2 >>= 8;
                        i8++;
                        z = z;
                    }
                    z2 = z;
                    stringBuffer.append(cArr);
                }
                z3 = z2;
            }
            return i4;
        }
        char[] cArr2 = new char[6];
        int[] iArr2 = new int[6];
        int i9 = i2;
        int i10 = 0;
        long j3 = 0;
        boolean z5 = false;
        while (i9 < iArr[0] && !z5) {
            int i11 = i9 + 1;
            int i12 = iArr[i9];
            if (i12 < 900) {
                iArr2[i10] = i12;
                i10++;
                j3 = (j3 * j) + ((long) i12);
            } else {
                if (i12 == 900 || i12 == 901 || i12 == 902 || i12 == BYTE_COMPACTION_MODE_LATCH_6 || i12 == BEGIN_MACRO_PDF417_CONTROL_BLOCK || i12 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || i12 == MACRO_PDF417_TERMINATOR) {
                    i9 = i11 - 1;
                    z5 = true;
                }
                if (i10 % 5 != 0 && i10 > 0) {
                    int i13 = 0;
                    while (i13 < i3) {
                        cArr2[5 - i13] = (char) (j3 % 256);
                        j3 >>= 8;
                        i13++;
                        i3 = 6;
                    }
                    stringBuffer.append(cArr2);
                    i10 = 0;
                }
                j = 900;
                i3 = 6;
            }
            i9 = i11;
            if (i10 % 5 != 0) {
            }
            j = 900;
            i3 = 6;
        }
        for (int i14 = (i10 / 5) * 5; i14 < i10; i14++) {
            stringBuffer.append((char) iArr2[i14]);
        }
        return i9;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static com.google.zxing.common.DecoderResult decode(int[] r4) throws com.google.zxing.FormatException {
        /*
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r1 = 100
            r0.<init>(r1)
            r1 = 1
            r1 = r4[r1]
            r2 = 2
        Lb:
            r3 = 0
            r3 = r4[r3]
            if (r2 >= r3) goto L39
            r3 = 913(0x391, float:1.28E-42)
            if (r1 == r3) goto L23
            r3 = 924(0x39c, float:1.295E-42)
            if (r1 == r3) goto L23
            switch(r1) {
                case 900: goto L28;
                case 901: goto L23;
                case 902: goto L1e;
                default: goto L1b;
            }
        L1b:
            int r2 = r2 + (-1)
            goto L28
        L1e:
            int r1 = numericCompaction(r4, r2, r0)
            goto L2c
        L23:
            int r1 = byteCompaction(r1, r4, r2, r0)
            goto L2c
        L28:
            int r1 = textCompaction(r4, r2, r0)
        L2c:
            int r2 = r4.length
            if (r1 >= r2) goto L34
            int r2 = r1 + 1
            r1 = r4[r1]
            goto Lb
        L34:
            com.google.zxing.FormatException r4 = com.google.zxing.FormatException.getFormatInstance()
            throw r4
        L39:
            com.google.zxing.common.DecoderResult r4 = new com.google.zxing.common.DecoderResult
            java.lang.String r0 = r0.toString()
            r1 = 0
            r4.<init>(r1, r0, r1, r1)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decode(int[]):com.google.zxing.common.DecoderResult");
    }

    private static String decodeBase900toBase10(int[] iArr, int i) {
        int i2 = 0;
        String strSubstring = null;
        StringBuffer stringBufferAdd = null;
        for (int i3 = 0; i3 < i; i3++) {
            StringBuffer stringBufferMultiply = multiply(EXP900[(i - i3) - 1], iArr[i3]);
            stringBufferAdd = stringBufferAdd == null ? stringBufferMultiply : add(stringBufferAdd.toString(), stringBufferMultiply.toString());
        }
        while (true) {
            if (i2 >= stringBufferAdd.length()) {
                break;
            }
            if (stringBufferAdd.charAt(i2) == '1') {
                strSubstring = stringBufferAdd.toString().substring(i2 + 1);
                break;
            }
            i2++;
        }
        return strSubstring == null ? stringBufferAdd.toString() : strSubstring;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void decodeTextCompaction(int[] r16, int[] r17, int r18, java.lang.StringBuffer r19) {
        /*
            r0 = r19
            r2 = r18
            r3 = 0
            r4 = 0
            r5 = 0
        L7:
            if (r3 >= r2) goto Lb8
            r6 = r16[r3]
            r7 = 4
            r8 = 3
            r9 = 28
            r10 = 27
            r11 = 2
            r12 = 913(0x391, float:1.28E-42)
            r14 = 5
            r15 = 1
            r1 = 29
            r13 = 26
            if (r4 == 0) goto L91
            if (r4 == r15) goto L79
            if (r4 == r11) goto L59
            if (r4 == r8) goto L42
            if (r4 == r7) goto L32
            if (r4 == r14) goto L28
            goto Lae
        L28:
            if (r6 >= r1) goto L2f
            char[] r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.PUNCT_CHARS
            char r13 = r1[r6]
            goto L37
        L2f:
            if (r6 != r1) goto L3f
            goto L4c
        L32:
            if (r6 >= r13) goto L3a
            int r6 = r6 + 65
            char r13 = (char) r6
        L37:
            r4 = r5
            goto Laf
        L3a:
            if (r6 != r13) goto L3f
            r4 = r5
            goto L99
        L3f:
            r4 = r5
            goto Lae
        L42:
            if (r6 >= r1) goto L4a
            char[] r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.PUNCT_CHARS
            char r13 = r1[r6]
            goto Laf
        L4a:
            if (r6 != r1) goto L4f
        L4c:
            r4 = 0
            goto Lae
        L4f:
            if (r6 != r12) goto Lae
            r1 = r17[r3]
        L53:
            char r1 = (char) r1
            r0.append(r1)
            goto Lae
        L59:
            r7 = 25
            if (r6 >= r7) goto L63
            char[] r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.MIXED_CHARS
            char r13 = r1[r6]
            goto Laf
        L63:
            if (r6 != r7) goto L68
            r4 = 3
            goto Lae
        L68:
            if (r6 != r13) goto L6b
            goto L99
        L6b:
            if (r6 != r10) goto L6e
            goto L9e
        L6e:
            if (r6 != r9) goto L71
            goto L4c
        L71:
            if (r6 != r1) goto L74
            goto La6
        L74:
            if (r6 != r12) goto Lae
            r1 = r17[r3]
            goto L53
        L79:
            if (r6 >= r13) goto L7e
            int r6 = r6 + 97
            goto L95
        L7e:
            if (r6 != r13) goto L81
            goto L99
        L81:
            if (r6 != r10) goto L86
            r5 = r4
            r4 = 4
            goto Lae
        L86:
            if (r6 != r9) goto L89
            goto La2
        L89:
            if (r6 != r1) goto L8c
            goto La6
        L8c:
            if (r6 != r12) goto Lae
            r1 = r17[r3]
            goto L53
        L91:
            if (r6 >= r13) goto L97
            int r6 = r6 + 65
        L95:
            char r13 = (char) r6
            goto Laf
        L97:
            if (r6 != r13) goto L9c
        L99:
            r13 = 32
            goto Laf
        L9c:
            if (r6 != r10) goto La0
        L9e:
            r4 = 1
            goto Lae
        La0:
            if (r6 != r9) goto La4
        La2:
            r4 = 2
            goto Lae
        La4:
            if (r6 != r1) goto La9
        La6:
            r5 = r4
            r4 = 5
            goto Lae
        La9:
            if (r6 != r12) goto Lae
            r1 = r17[r3]
            goto L53
        Lae:
            r13 = 0
        Laf:
            if (r13 == 0) goto Lb4
            r0.append(r13)
        Lb4:
            int r3 = r3 + 1
            goto L7
        Lb8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decodeTextCompaction(int[], int[], int, java.lang.StringBuffer):void");
    }

    private static StringBuffer multiply(String str, int i) {
        StringBuffer stringBuffer = new StringBuffer(str.length());
        for (int i2 = 0; i2 < str.length(); i2++) {
            stringBuffer.append('0');
        }
        int i3 = i / 100;
        int i4 = (i / 10) % 10;
        int i5 = i % 10;
        for (int i6 = 0; i6 < i5; i6++) {
            stringBuffer = add(stringBuffer.toString(), str);
        }
        for (int i7 = 0; i7 < i4; i7++) {
            String string = stringBuffer.toString();
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(str);
            stringBuffer2.append('0');
            stringBuffer = add(string, stringBuffer2.toString().substring(1));
        }
        for (int i8 = 0; i8 < i3; i8++) {
            String string2 = stringBuffer.toString();
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(str);
            stringBuffer3.append("00");
            stringBuffer = add(string2, stringBuffer3.toString().substring(2));
        }
        return stringBuffer;
    }

    private static int numericCompaction(int[] iArr, int i, StringBuffer stringBuffer) {
        int[] iArr2 = new int[15];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i3 == iArr[0]) {
                z = true;
            }
            if (i4 < 900) {
                iArr2[i2] = i4;
                i2++;
            } else if (i4 == 900 || i4 == 901 || i4 == BYTE_COMPACTION_MODE_LATCH_6 || i4 == BEGIN_MACRO_PDF417_CONTROL_BLOCK || i4 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || i4 == MACRO_PDF417_TERMINATOR) {
                i3--;
                z = true;
            }
            if (i2 % 15 == 0 || i4 == 902 || z) {
                stringBuffer.append(decodeBase900toBase10(iArr2, i2));
                i2 = 0;
            }
            i = i3;
        }
        return i;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:13:0x0031. Please report as an issue. */
    private static int textCompaction(int[] iArr, int i, StringBuffer stringBuffer) {
        int[] iArr2 = new int[iArr[0] << 1];
        int[] iArr3 = new int[iArr[0] << 1];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i4 < 900) {
                iArr2[i2] = i4 / 30;
                iArr2[i2 + 1] = i4 % 30;
                i2 += 2;
            } else if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                if (i4 != BYTE_COMPACTION_MODE_LATCH_6) {
                    switch (i4) {
                    }
                }
                i = i3 - 1;
                z = true;
            } else {
                iArr2[i2] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                i = i3 + 1;
                iArr3[i2] = iArr[i3];
                i2++;
            }
            i = i3;
        }
        decodeTextCompaction(iArr2, iArr3, i2, stringBuffer);
        return i;
    }
}
