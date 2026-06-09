package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.StringUtils;
import com.jieli.stream.p016dv.running2.util.IConstant;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Vector;
import kotlin.text.Typography;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: loaded from: classes.dex */
final class DecodedBitStreamParser {
    private static final char[] ALPHANUMERIC_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', Typography.dollar, '%', '*', '+', '-', '.', '/', ':'};
    private static final int GB2312_SUBSET = 1;

    private DecodedBitStreamParser() {
    }

    static DecoderResult decode(byte[] bArr, Version version, ErrorCorrectionLevel errorCorrectionLevel, Hashtable hashtable) throws FormatException {
        Mode modeForBits;
        Mode mode;
        BitSource bitSource = new BitSource(bArr);
        StringBuffer stringBuffer = new StringBuffer(50);
        Vector vector = new Vector(1);
        CharacterSetECI characterSetECIByValue = null;
        boolean z = false;
        do {
            if (bitSource.available() < 4) {
                modeForBits = Mode.TERMINATOR;
            } else {
                try {
                    modeForBits = Mode.forBits(bitSource.readBits(4));
                } catch (IllegalArgumentException unused) {
                    throw FormatException.getFormatInstance();
                }
            }
            mode = modeForBits;
            if (!mode.equals(Mode.TERMINATOR)) {
                if (mode.equals(Mode.FNC1_FIRST_POSITION) || mode.equals(Mode.FNC1_SECOND_POSITION)) {
                    z = true;
                } else if (mode.equals(Mode.STRUCTURED_APPEND)) {
                    bitSource.readBits(16);
                } else if (mode.equals(Mode.ECI)) {
                    characterSetECIByValue = CharacterSetECI.getCharacterSetECIByValue(parseECIValue(bitSource));
                    if (characterSetECIByValue == null) {
                        throw FormatException.getFormatInstance();
                    }
                } else if (mode.equals(Mode.HANZI)) {
                    int bits = bitSource.readBits(4);
                    int bits2 = bitSource.readBits(mode.getCharacterCountBits(version));
                    if (bits == 1) {
                        decodeHanziSegment(bitSource, stringBuffer, bits2);
                    }
                } else {
                    int bits3 = bitSource.readBits(mode.getCharacterCountBits(version));
                    if (mode.equals(Mode.NUMERIC)) {
                        decodeNumericSegment(bitSource, stringBuffer, bits3);
                    } else if (mode.equals(Mode.ALPHANUMERIC)) {
                        decodeAlphanumericSegment(bitSource, stringBuffer, bits3, z);
                    } else if (mode.equals(Mode.BYTE)) {
                        decodeByteSegment(bitSource, stringBuffer, bits3, characterSetECIByValue, vector, hashtable);
                    } else {
                        if (!mode.equals(Mode.KANJI)) {
                            throw FormatException.getFormatInstance();
                        }
                        decodeKanjiSegment(bitSource, stringBuffer, bits3);
                    }
                }
            }
        } while (!mode.equals(Mode.TERMINATOR));
        String string = stringBuffer.toString();
        if (vector.isEmpty()) {
            vector = null;
        }
        return new DecoderResult(bArr, string, vector, errorCorrectionLevel != null ? errorCorrectionLevel.toString() : null);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void decodeAlphanumericSegment(com.google.zxing.common.BitSource r3, java.lang.StringBuffer r4, int r5, boolean r6) throws com.google.zxing.FormatException {
        /*
            int r0 = r4.length()
        L4:
            r1 = 1
            if (r5 <= r1) goto L22
            r1 = 11
            int r1 = r3.readBits(r1)
            int r2 = r1 / 45
            char r2 = toAlphaNumericChar(r2)
            r4.append(r2)
            int r1 = r1 % 45
            char r1 = toAlphaNumericChar(r1)
            r4.append(r1)
            int r5 = r5 + (-2)
            goto L4
        L22:
            if (r5 != r1) goto L30
            r5 = 6
            int r3 = r3.readBits(r5)
            char r3 = toAlphaNumericChar(r3)
            r4.append(r3)
        L30:
            if (r6 == 0) goto L5b
        L32:
            int r3 = r4.length()
            if (r0 >= r3) goto L5b
            char r3 = r4.charAt(r0)
            r5 = 37
            if (r3 != r5) goto L58
            int r3 = r4.length()
            int r3 = r3 - r1
            if (r0 >= r3) goto L53
            int r3 = r0 + 1
            char r6 = r4.charAt(r3)
            if (r6 != r5) goto L53
            r4.deleteCharAt(r3)
            goto L58
        L53:
            r3 = 29
            r4.setCharAt(r0, r3)
        L58:
            int r0 = r0 + 1
            goto L32
        L5b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.decoder.DecodedBitStreamParser.decodeAlphanumericSegment(com.google.zxing.common.BitSource, java.lang.StringBuffer, int, boolean):void");
    }

    private static void decodeByteSegment(BitSource bitSource, StringBuffer stringBuffer, int i, CharacterSetECI characterSetECI, Vector vector, Hashtable hashtable) throws FormatException {
        if ((i << 3) > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) bitSource.readBits(8);
        }
        try {
            stringBuffer.append(new String(bArr, characterSetECI == null ? StringUtils.guessEncoding(bArr, hashtable) : characterSetECI.getEncodingName()));
            vector.addElement(bArr);
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeHanziSegment(BitSource bitSource, StringBuffer stringBuffer, int i) throws FormatException {
        if (i * 13 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i * 2];
        int i2 = 0;
        while (i > 0) {
            int bits = bitSource.readBits(13);
            int i3 = (bits % 96) | ((bits / 96) << 8);
            int i4 = i3 + (i3 < 959 ? IConstant.FRAME_TYPE_I : 42657);
            bArr[i2] = (byte) ((i4 >> 8) & 255);
            bArr[i2 + 1] = (byte) (i4 & 255);
            i2 += 2;
            i--;
        }
        try {
            stringBuffer.append(new String(bArr, StringUtils.GB2312));
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeKanjiSegment(BitSource bitSource, StringBuffer stringBuffer, int i) throws FormatException {
        if (i * 13 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i * 2];
        int i2 = 0;
        while (i > 0) {
            int bits = bitSource.readBits(13);
            int i3 = (bits % 192) | ((bits / 192) << 8);
            int i4 = i3 + (i3 < 7936 ? 33088 : 49472);
            bArr[i2] = (byte) (i4 >> 8);
            bArr[i2 + 1] = (byte) i4;
            i2 += 2;
            i--;
        }
        try {
            stringBuffer.append(new String(bArr, StringUtils.SHIFT_JIS));
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeNumericSegment(BitSource bitSource, StringBuffer stringBuffer, int i) throws FormatException {
        int bits;
        while (i >= 3) {
            int bits2 = bitSource.readBits(10);
            if (bits2 >= 1000) {
                throw FormatException.getFormatInstance();
            }
            stringBuffer.append(toAlphaNumericChar(bits2 / 100));
            stringBuffer.append(toAlphaNumericChar((bits2 / 10) % 10));
            stringBuffer.append(toAlphaNumericChar(bits2 % 10));
            i -= 3;
        }
        if (i == 2) {
            int bits3 = bitSource.readBits(7);
            if (bits3 >= 100) {
                throw FormatException.getFormatInstance();
            }
            stringBuffer.append(toAlphaNumericChar(bits3 / 10));
            bits = bits3 % 10;
        } else {
            if (i != 1) {
                return;
            }
            bits = bitSource.readBits(4);
            if (bits >= 10) {
                throw FormatException.getFormatInstance();
            }
        }
        stringBuffer.append(toAlphaNumericChar(bits));
    }

    private static int parseECIValue(BitSource bitSource) {
        int bits = bitSource.readBits(8);
        if ((bits & 128) == 0) {
            return bits & WorkQueueKt.MASK;
        }
        if ((bits & 192) == 128) {
            return bitSource.readBits(8) | ((bits & 63) << 8);
        }
        if ((bits & 224) == 192) {
            return bitSource.readBits(16) | ((bits & 31) << 16);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Bad ECI bits starting with byte ");
        stringBuffer.append(bits);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    private static char toAlphaNumericChar(int i) throws FormatException {
        char[] cArr = ALPHANUMERIC_CHARS;
        if (i < cArr.length) {
            return cArr[i];
        }
        throw FormatException.getFormatInstance();
    }
}
