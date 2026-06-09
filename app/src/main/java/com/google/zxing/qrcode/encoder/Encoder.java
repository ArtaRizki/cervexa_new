package com.google.zxing.qrcode.encoder;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.ECI;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Vector;
import kotlin.UByte;
import org.apache.commons.net.telnet.TelnetCommand;

/* JADX INFO: loaded from: classes.dex */
public final class Encoder {
    private static final int[] ALPHANUMERIC_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};
    static final String DEFAULT_BYTE_MODE_ENCODING = "ISO-8859-1";

    private Encoder() {
    }

    static void append8BitBytes(String str, BitArray bitArray, String str2) throws WriterException {
        try {
            byte[] bytes = str.getBytes(str2);
            for (byte b : bytes) {
                bitArray.appendBits(b, 8);
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException(e.toString());
        }
    }

    static void appendAlphanumericBytes(String str, BitArray bitArray) throws WriterException {
        int length = str.length();
        int i = 0;
        while (i < length) {
            int alphanumericCode = getAlphanumericCode(str.charAt(i));
            if (alphanumericCode == -1) {
                throw new WriterException();
            }
            int i2 = i + 1;
            if (i2 < length) {
                int alphanumericCode2 = getAlphanumericCode(str.charAt(i2));
                if (alphanumericCode2 == -1) {
                    throw new WriterException();
                }
                bitArray.appendBits((alphanumericCode * 45) + alphanumericCode2, 11);
                i += 2;
            } else {
                bitArray.appendBits(alphanumericCode, 6);
                i = i2;
            }
        }
    }

    static void appendBytes(String str, Mode mode, BitArray bitArray, String str2) throws WriterException {
        if (mode.equals(Mode.NUMERIC)) {
            appendNumericBytes(str, bitArray);
            return;
        }
        if (mode.equals(Mode.ALPHANUMERIC)) {
            appendAlphanumericBytes(str, bitArray);
            return;
        }
        if (mode.equals(Mode.BYTE)) {
            append8BitBytes(str, bitArray, str2);
        } else {
            if (mode.equals(Mode.KANJI)) {
                appendKanjiBytes(str, bitArray);
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Invalid mode: ");
            stringBuffer.append(mode);
            throw new WriterException(stringBuffer.toString());
        }
    }

    private static void appendECI(ECI eci, BitArray bitArray) {
        bitArray.appendBits(Mode.ECI.getBits(), 4);
        bitArray.appendBits(eci.getValue(), 8);
    }

    static void appendKanjiBytes(String str, BitArray bitArray) throws WriterException {
        int i;
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            for (int i2 = 0; i2 < length; i2 += 2) {
                int i3 = ((bytes[i2] & UByte.MAX_VALUE) << 8) | (bytes[i2 + 1] & UByte.MAX_VALUE);
                int i4 = 33088;
                if (i3 >= 33088 && i3 <= 40956) {
                    i = i3 - i4;
                } else if (i3 < 57408 || i3 > 60351) {
                    i = -1;
                } else {
                    i4 = 49472;
                    i = i3 - i4;
                }
                if (i == -1) {
                    throw new WriterException("Invalid byte sequence");
                }
                bitArray.appendBits(((i >> 8) * 192) + (i & 255), 13);
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException(e.toString());
        }
    }

    static void appendLengthInfo(int i, int i2, Mode mode, BitArray bitArray) throws WriterException {
        int characterCountBits = mode.getCharacterCountBits(Version.getVersionForNumber(i2));
        int i3 = (1 << characterCountBits) - 1;
        if (i <= i3) {
            bitArray.appendBits(i, characterCountBits);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i);
        stringBuffer.append("is bigger than");
        stringBuffer.append(i3);
        throw new WriterException(stringBuffer.toString());
    }

    static void appendModeInfo(Mode mode, BitArray bitArray) {
        bitArray.appendBits(mode.getBits(), 4);
    }

    static void appendNumericBytes(String str, BitArray bitArray) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            int iCharAt = str.charAt(i) - '0';
            int i2 = i + 2;
            if (i2 < length) {
                bitArray.appendBits((iCharAt * 100) + ((str.charAt(i + 1) - '0') * 10) + (str.charAt(i2) - '0'), 10);
                i += 3;
            } else {
                i++;
                if (i < length) {
                    bitArray.appendBits((iCharAt * 10) + (str.charAt(i) - '0'), 7);
                    i = i2;
                } else {
                    bitArray.appendBits(iCharAt, 4);
                }
            }
        }
    }

    private static int calculateMaskPenalty(ByteMatrix byteMatrix) {
        return MaskUtil.applyMaskPenaltyRule1(byteMatrix) + 0 + MaskUtil.applyMaskPenaltyRule2(byteMatrix) + MaskUtil.applyMaskPenaltyRule3(byteMatrix) + MaskUtil.applyMaskPenaltyRule4(byteMatrix);
    }

    private static int chooseMaskPattern(BitArray bitArray, ErrorCorrectionLevel errorCorrectionLevel, int i, ByteMatrix byteMatrix) throws WriterException {
        int i2 = Integer.MAX_VALUE;
        int i3 = -1;
        for (int i4 = 0; i4 < 8; i4++) {
            MatrixUtil.buildMatrix(bitArray, errorCorrectionLevel, i, i4, byteMatrix);
            int iCalculateMaskPenalty = calculateMaskPenalty(byteMatrix);
            if (iCalculateMaskPenalty < i2) {
                i3 = i4;
                i2 = iCalculateMaskPenalty;
            }
        }
        return i3;
    }

    public static Mode chooseMode(String str) {
        return chooseMode(str, null);
    }

    public static Mode chooseMode(String str, String str2) {
        if ("Shift_JIS".equals(str2)) {
            return isOnlyDoubleByteKanji(str) ? Mode.KANJI : Mode.BYTE;
        }
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt >= '0' && cCharAt <= '9') {
                z2 = true;
            } else {
                if (getAlphanumericCode(cCharAt) == -1) {
                    return Mode.BYTE;
                }
                z = true;
            }
        }
        return z ? Mode.ALPHANUMERIC : z2 ? Mode.NUMERIC : Mode.BYTE;
    }

    public static void encode(String str, ErrorCorrectionLevel errorCorrectionLevel, QRCode qRCode) throws WriterException {
        encode(str, errorCorrectionLevel, null, qRCode);
    }

    public static void encode(String str, ErrorCorrectionLevel errorCorrectionLevel, Hashtable hashtable, QRCode qRCode) throws WriterException {
        CharacterSetECI characterSetECIByName;
        String str2 = hashtable == null ? null : (String) hashtable.get(EncodeHintType.CHARACTER_SET);
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        Mode modeChooseMode = chooseMode(str, str2);
        BitArray bitArray = new BitArray();
        appendBytes(str, modeChooseMode, bitArray, str2);
        initQRCode(bitArray.getSizeInBytes(), errorCorrectionLevel, modeChooseMode, qRCode);
        BitArray bitArray2 = new BitArray();
        if (modeChooseMode == Mode.BYTE && !"ISO-8859-1".equals(str2) && (characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(str2)) != null) {
            appendECI(characterSetECIByName, bitArray2);
        }
        appendModeInfo(modeChooseMode, bitArray2);
        appendLengthInfo(modeChooseMode.equals(Mode.BYTE) ? bitArray.getSizeInBytes() : str.length(), qRCode.getVersion(), modeChooseMode, bitArray2);
        bitArray2.appendBitArray(bitArray);
        terminateBits(qRCode.getNumDataBytes(), bitArray2);
        BitArray bitArray3 = new BitArray();
        interleaveWithECBytes(bitArray2, qRCode.getNumTotalBytes(), qRCode.getNumDataBytes(), qRCode.getNumRSBlocks(), bitArray3);
        ByteMatrix byteMatrix = new ByteMatrix(qRCode.getMatrixWidth(), qRCode.getMatrixWidth());
        qRCode.setMaskPattern(chooseMaskPattern(bitArray3, qRCode.getECLevel(), qRCode.getVersion(), byteMatrix));
        MatrixUtil.buildMatrix(bitArray3, qRCode.getECLevel(), qRCode.getVersion(), qRCode.getMaskPattern(), byteMatrix);
        qRCode.setMatrix(byteMatrix);
        if (qRCode.isValid()) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Invalid QR code: ");
        stringBuffer.append(qRCode.toString());
        throw new WriterException(stringBuffer.toString());
    }

    static byte[] generateECBytes(byte[] bArr, int i) {
        int length = bArr.length;
        int[] iArr = new int[length + i];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & UByte.MAX_VALUE;
        }
        new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256).encode(iArr, i);
        byte[] bArr2 = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr2[i3] = (byte) iArr[length + i3];
        }
        return bArr2;
    }

    static int getAlphanumericCode(int i) {
        int[] iArr = ALPHANUMERIC_TABLE;
        if (i < iArr.length) {
            return iArr[i];
        }
        return -1;
    }

    static void getNumDataBytesAndNumECBytesForBlockID(int i, int i2, int i3, int i4, int[] iArr, int[] iArr2) throws WriterException {
        if (i4 >= i3) {
            throw new WriterException("Block ID too large");
        }
        int i5 = i % i3;
        int i6 = i3 - i5;
        int i7 = i / i3;
        int i8 = i7 + 1;
        int i9 = i2 / i3;
        int i10 = i9 + 1;
        int i11 = i7 - i9;
        int i12 = i8 - i10;
        if (i11 != i12) {
            throw new WriterException("EC bytes mismatch");
        }
        if (i3 != i6 + i5) {
            throw new WriterException("RS blocks mismatch");
        }
        if (i != ((i9 + i11) * i6) + ((i10 + i12) * i5)) {
            throw new WriterException("Total bytes mismatch");
        }
        if (i4 < i6) {
            iArr[0] = i9;
            iArr2[0] = i11;
        } else {
            iArr[0] = i10;
            iArr2[0] = i12;
        }
    }

    private static void initQRCode(int i, ErrorCorrectionLevel errorCorrectionLevel, Mode mode, QRCode qRCode) throws WriterException {
        qRCode.setECLevel(errorCorrectionLevel);
        qRCode.setMode(mode);
        for (int i2 = 1; i2 <= 40; i2++) {
            Version versionForNumber = Version.getVersionForNumber(i2);
            int totalCodewords = versionForNumber.getTotalCodewords();
            Version.ECBlocks eCBlocksForLevel = versionForNumber.getECBlocksForLevel(errorCorrectionLevel);
            int totalECCodewords = eCBlocksForLevel.getTotalECCodewords();
            int numBlocks = eCBlocksForLevel.getNumBlocks();
            int i3 = totalCodewords - totalECCodewords;
            if (i3 >= i + 3) {
                qRCode.setVersion(i2);
                qRCode.setNumTotalBytes(totalCodewords);
                qRCode.setNumDataBytes(i3);
                qRCode.setNumRSBlocks(numBlocks);
                qRCode.setNumECBytes(totalECCodewords);
                qRCode.setMatrixWidth(versionForNumber.getDimensionForVersion());
                return;
            }
        }
        throw new WriterException("Cannot find proper rs block info (input data too big?)");
    }

    static void interleaveWithECBytes(BitArray bitArray, int i, int i2, int i3, BitArray bitArray2) throws WriterException {
        if (bitArray.getSizeInBytes() != i2) {
            throw new WriterException("Number of bits and data bytes does not match");
        }
        Vector vector = new Vector(i3);
        int i4 = 0;
        int iMax = 0;
        int iMax2 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            getNumDataBytesAndNumECBytesForBlockID(i, i2, i3, i5, iArr, iArr2);
            int i6 = iArr[0];
            byte[] bArr = new byte[i6];
            bitArray.toBytes(i4 * 8, bArr, 0, i6);
            byte[] bArrGenerateECBytes = generateECBytes(bArr, iArr2[0]);
            vector.addElement(new BlockPair(bArr, bArrGenerateECBytes));
            iMax = Math.max(iMax, i6);
            iMax2 = Math.max(iMax2, bArrGenerateECBytes.length);
            i4 += iArr[0];
        }
        if (i2 != i4) {
            throw new WriterException("Data bytes does not match offset");
        }
        for (int i7 = 0; i7 < iMax; i7++) {
            for (int i8 = 0; i8 < vector.size(); i8++) {
                byte[] dataBytes = ((BlockPair) vector.elementAt(i8)).getDataBytes();
                if (i7 < dataBytes.length) {
                    bitArray2.appendBits(dataBytes[i7], 8);
                }
            }
        }
        for (int i9 = 0; i9 < iMax2; i9++) {
            for (int i10 = 0; i10 < vector.size(); i10++) {
                byte[] errorCorrectionBytes = ((BlockPair) vector.elementAt(i10)).getErrorCorrectionBytes();
                if (i9 < errorCorrectionBytes.length) {
                    bitArray2.appendBits(errorCorrectionBytes[i9], 8);
                }
            }
        }
        if (i == bitArray2.getSizeInBytes()) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Interleaving error: ");
        stringBuffer.append(i);
        stringBuffer.append(" and ");
        stringBuffer.append(bitArray2.getSizeInBytes());
        stringBuffer.append(" differ.");
        throw new WriterException(stringBuffer.toString());
    }

    private static boolean isOnlyDoubleByteKanji(String str) {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i = 0; i < length; i += 2) {
                int i2 = bytes[i] & UByte.MAX_VALUE;
                if ((i2 < 129 || i2 > 159) && (i2 < 224 || i2 > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }

    static void terminateBits(int i, BitArray bitArray) throws WriterException {
        int i2 = i << 3;
        if (bitArray.getSize() > i2) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("data bits cannot fit in the QR Code");
            stringBuffer.append(bitArray.getSize());
            stringBuffer.append(" > ");
            stringBuffer.append(i2);
            throw new WriterException(stringBuffer.toString());
        }
        for (int i3 = 0; i3 < 4 && bitArray.getSize() < i2; i3++) {
            bitArray.appendBit(false);
        }
        int size = bitArray.getSize() & 7;
        if (size > 0) {
            while (size < 8) {
                bitArray.appendBit(false);
                size++;
            }
        }
        int sizeInBytes = i - bitArray.getSizeInBytes();
        for (int i4 = 0; i4 < sizeInBytes; i4++) {
            bitArray.appendBits((i4 & 1) == 0 ? TelnetCommand.EOF : 17, 8);
        }
        if (bitArray.getSize() != i2) {
            throw new WriterException("Bits size does not equal capacity");
        }
    }
}
