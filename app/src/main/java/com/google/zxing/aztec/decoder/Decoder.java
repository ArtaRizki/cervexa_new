package com.google.zxing.aztec.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.client.result.optional.NDEFRecord;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import com.jieli.lib.p015dv.control.utils.TopicKey;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.tencent.connect.common.Constants;
import org.apache.commons.net.SocketClient;

/* JADX INFO: loaded from: classes.dex */
public final class Decoder {
    private static final int BINARY = 5;
    private static final int DIGIT = 3;
    private static final int LOWER = 1;
    private static final int MIXED = 2;
    private static final int PUNCT = 4;
    private static final int UPPER = 0;
    private int codewordSize;
    private AztecDetectorResult ddata;
    private int invertedBitCount;
    private int numCodewords;
    private static final int[] NB_BITS_COMPACT = {0, 104, 240, 408, 608};
    private static final int[] NB_BITS = {0, 128, 288, 480, 704, 960, 1248, 1568, 1920, 2304, 2720, 3168, 3648, 4160, 4704, 5280, 5888, 6528, 7200, 7904, 8640, 9408, 10208, 11040, 11904, 12800, 13728, 14688, 15680, 16704, 17760, 18848, 19968};
    private static final int[] NB_DATABLOCK_COMPACT = {0, 17, 40, 51, 76};
    private static final int[] NB_DATABLOCK = {0, 21, 48, 60, 88, 120, 156, 196, 240, 230, IConstant.PROJECTION_HEIGHT, 316, 364, 416, 470, 528, 588, 652, IConstant.RES_HD_HEIGHT, 790, 864, 940, 1020, 920, 992, 1066, 1144, 1224, 1306, 1392, 1480, 1570, 1664};
    private static final String[] UPPER_TABLE = {"CTRL_PS", " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", NDEFRecord.TEXT_WELL_KNOWN_TYPE, NDEFRecord.URI_WELL_KNOWN_TYPE, "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] LOWER_TABLE = {"CTRL_PS", " ", "a", "b", "c", "d", "e", "f", "g", TopicKey.HEIGHT, "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", TopicKey.DURATION, "u", "v", TopicKey.WIDTH, "x", "y", "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] MIXED_TABLE = {"CTRL_PS", " ", "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", "|", "~", "\u007f", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] PUNCT_TABLE = {"", "\r", SocketClient.NETASCII_EOL, ". ", ", ", ": ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/", ":", ";", "<", "=", ">", "?", "[", "]", "{", "}", "CTRL_UL"};
    private static final String[] DIGIT_TABLE = {"CTRL_PS", " ", "0", "1", "2", "3", "4", "5", Constants.VIA_SHARE_TYPE_INFO, Constants.VIA_SHARE_TYPE_PUBLISHMOOD, Constants.VIA_SHARE_TYPE_PUBLISHVIDEO, "9", ",", ".", "CTRL_UL", "CTRL_US"};

    private boolean[] correctBits(boolean[] zArr) throws FormatException {
        GenericGF genericGF;
        int i;
        int i2;
        if (this.ddata.getNbLayers() <= 2) {
            this.codewordSize = 6;
            genericGF = GenericGF.AZTEC_DATA_6;
        } else if (this.ddata.getNbLayers() <= 8) {
            this.codewordSize = 8;
            genericGF = GenericGF.AZTEC_DATA_8;
        } else if (this.ddata.getNbLayers() <= 22) {
            this.codewordSize = 10;
            genericGF = GenericGF.AZTEC_DATA_10;
        } else {
            this.codewordSize = 12;
            genericGF = GenericGF.AZTEC_DATA_12;
        }
        int nbDatablocks = this.ddata.getNbDatablocks();
        if (this.ddata.isCompact()) {
            i = NB_BITS_COMPACT[this.ddata.getNbLayers()] - (this.numCodewords * this.codewordSize);
            i2 = NB_DATABLOCK_COMPACT[this.ddata.getNbLayers()];
        } else {
            i = NB_BITS[this.ddata.getNbLayers()] - (this.numCodewords * this.codewordSize);
            i2 = NB_DATABLOCK[this.ddata.getNbLayers()];
        }
        int i3 = i2 - nbDatablocks;
        int[] iArr = new int[this.numCodewords];
        int i4 = 0;
        while (true) {
            int i5 = 1;
            if (i4 >= this.numCodewords) {
                try {
                    break;
                } catch (ReedSolomonException unused) {
                    throw FormatException.getFormatInstance();
                }
            }
            int i6 = 1;
            while (true) {
                int i7 = this.codewordSize;
                if (i5 <= i7) {
                    if (zArr[(((i7 * i4) + i7) - i5) + i]) {
                        iArr[i4] = iArr[i4] + i6;
                    }
                    i6 <<= 1;
                    i5++;
                }
            }
            i4++;
        }
        new ReedSolomonDecoder(genericGF).decode(iArr, i3);
        this.invertedBitCount = 0;
        boolean[] zArr2 = new boolean[this.codewordSize * nbDatablocks];
        int i8 = 0;
        for (int i9 = 0; i9 < nbDatablocks; i9++) {
            int i10 = 1 << (this.codewordSize - 1);
            int i11 = 0;
            boolean z = false;
            for (int i12 = 0; i12 < this.codewordSize; i12++) {
                boolean z2 = (iArr[i9] & i10) == i10;
                if (i11 != this.codewordSize - 1) {
                    if (z == z2) {
                        i11++;
                    } else {
                        z = z2;
                        i11 = 1;
                    }
                    zArr2[((this.codewordSize * i9) + i12) - i8] = z2;
                } else {
                    if (z2 == z) {
                        throw FormatException.getFormatInstance();
                    }
                    i8++;
                    this.invertedBitCount++;
                    i11 = 0;
                    z = false;
                }
                i10 >>>= 1;
            }
        }
        return zArr2;
    }

    private boolean[] extractBits(BitMatrix bitMatrix) throws FormatException {
        boolean[] zArr;
        int i;
        int i2;
        if (this.ddata.isCompact()) {
            int nbLayers = this.ddata.getNbLayers();
            int[] iArr = NB_BITS_COMPACT;
            if (nbLayers > iArr.length) {
                throw FormatException.getFormatInstance();
            }
            zArr = new boolean[iArr[this.ddata.getNbLayers()]];
            i = NB_DATABLOCK_COMPACT[this.ddata.getNbLayers()];
        } else {
            int nbLayers2 = this.ddata.getNbLayers();
            int[] iArr2 = NB_BITS;
            if (nbLayers2 > iArr2.length) {
                throw FormatException.getFormatInstance();
            }
            zArr = new boolean[iArr2[this.ddata.getNbLayers()]];
            i = NB_DATABLOCK[this.ddata.getNbLayers()];
        }
        this.numCodewords = i;
        int nbLayers3 = this.ddata.getNbLayers();
        int i3 = bitMatrix.height;
        int i4 = 0;
        int i5 = 0;
        while (nbLayers3 != 0) {
            int i6 = 0;
            int i7 = 0;
            while (true) {
                i2 = i3 * 2;
                if (i6 >= i2 - 4) {
                    break;
                }
                int i8 = (i6 / 2) + i5;
                zArr[i4 + i6] = bitMatrix.get(i5 + i7, i8);
                zArr[((i2 + i4) - 4) + i6] = bitMatrix.get(i8, ((i5 + i3) - 1) - i7);
                i7 = (i7 + 1) % 2;
                i6++;
            }
            int i9 = 0;
            for (int i10 = i2 + 1; i10 > 5; i10--) {
                int i11 = i2 - i10;
                int i12 = ((i10 / 2) + i5) - 1;
                zArr[(((i3 * 4) + i4) - 8) + i11 + 1] = bitMatrix.get(((i5 + i3) - 1) - i9, i12);
                zArr[(((i3 * 6) + i4) - 12) + i11 + 1] = bitMatrix.get(i12, i5 + i9);
                i9 = (i9 + 1) % 2;
            }
            i5 += 2;
            i4 += (i3 * 8) - 16;
            nbLayers3--;
            i3 -= 4;
        }
        return zArr;
    }

    private static String getCharacter(int i, int i2) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "" : PUNCT_TABLE[i2] : DIGIT_TABLE[i2] : MIXED_TABLE[i2] : LOWER_TABLE[i2] : UPPER_TABLE[i2];
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String getEncodedData(boolean[] r14) throws com.google.zxing.FormatException {
        /*
            r13 = this;
            int r0 = r13.codewordSize
            com.google.zxing.aztec.AztecDetectorResult r1 = r13.ddata
            int r1 = r1.getNbDatablocks()
            int r0 = r0 * r1
            int r1 = r13.invertedBitCount
            int r0 = r0 - r1
            int r1 = r14.length
            if (r0 > r1) goto L78
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r2 = 20
            r1.<init>(r2)
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
        L1d:
            r8 = 0
        L1e:
            if (r3 != 0) goto L73
            r9 = 1
            if (r4 == 0) goto L25
            r8 = 1
            goto L26
        L25:
            r7 = r5
        L26:
            r10 = 5
            if (r5 == r10) goto L5c
            r11 = 3
            if (r5 != r11) goto L2e
            r11 = 4
            goto L2f
        L2e:
            r11 = 5
        L2f:
            int r12 = r0 - r6
            if (r12 >= r11) goto L34
            goto L62
        L34:
            int r12 = readCode(r14, r6, r11)
            int r6 = r6 + r11
            java.lang.String r11 = getCharacter(r5, r12)
            java.lang.String r12 = "CTRL_"
            boolean r12 = r11.startsWith(r12)
            if (r12 == 0) goto L58
            char r5 = r11.charAt(r10)
            int r5 = getTable(r5)
            r10 = 6
            char r10 = r11.charAt(r10)
            r11 = 83
            if (r10 != r11) goto L6e
            r4 = 1
            goto L6e
        L58:
            r1.append(r11)
            goto L6e
        L5c:
            int r10 = r0 - r6
            r11 = 8
            if (r10 >= r11) goto L64
        L62:
            r3 = 1
            goto L6e
        L64:
            int r9 = readCode(r14, r6, r11)
            int r6 = r6 + 8
            char r9 = (char) r9
            r1.append(r9)
        L6e:
            if (r8 == 0) goto L1e
            r5 = r7
            r4 = 0
            goto L1d
        L73:
            java.lang.String r14 = r1.toString()
            return r14
        L78:
            com.google.zxing.FormatException r14 = com.google.zxing.FormatException.getFormatInstance()
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.decoder.Decoder.getEncodedData(boolean[]):java.lang.String");
    }

    private static int getTable(char c) {
        if (c == 'B') {
            return 5;
        }
        if (c == 'D') {
            return 3;
        }
        if (c == 'P') {
            return 4;
        }
        if (c == 'U') {
            return 0;
        }
        if (c != 'L') {
            return c != 'M' ? 0 : 2;
        }
        return 1;
    }

    private static int readCode(boolean[] zArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            i3 <<= 1;
            if (zArr[i4]) {
                i3++;
            }
        }
        return i3;
    }

    private static BitMatrix removeDashedLines(BitMatrix bitMatrix) {
        int i = ((((bitMatrix.width - 1) / 2) / 16) * 2) + 1;
        BitMatrix bitMatrix2 = new BitMatrix(bitMatrix.width - i, bitMatrix.height - i);
        int i2 = 0;
        for (int i3 = 0; i3 < bitMatrix.width; i3++) {
            if (((bitMatrix.width / 2) - i3) % 16 != 0) {
                int i4 = 0;
                for (int i5 = 0; i5 < bitMatrix.height; i5++) {
                    if (((bitMatrix.width / 2) - i5) % 16 != 0) {
                        if (bitMatrix.get(i3, i5)) {
                            bitMatrix2.set(i2, i4);
                        }
                        i4++;
                    }
                }
                i2++;
            }
        }
        return bitMatrix2;
    }

    public DecoderResult decode(AztecDetectorResult aztecDetectorResult) throws FormatException {
        this.ddata = aztecDetectorResult;
        BitMatrix bits = aztecDetectorResult.getBits();
        if (!this.ddata.isCompact()) {
            bits = removeDashedLines(this.ddata.getBits());
        }
        return new DecoderResult(null, getEncodedData(correctBits(extractBits(bits))), null, null);
    }
}
