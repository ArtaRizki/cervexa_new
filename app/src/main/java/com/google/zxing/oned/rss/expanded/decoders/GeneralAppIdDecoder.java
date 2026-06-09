package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
import kotlin.text.Typography;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.telnet.TelnetCommand;

/* JADX INFO: loaded from: classes.dex */
final class GeneralAppIdDecoder {
    private final BitArray information;
    private final CurrentParsingState current = new CurrentParsingState();
    private final StringBuffer buffer = new StringBuffer();

    GeneralAppIdDecoder(BitArray bitArray) {
        this.information = bitArray;
    }

    private DecodedChar decodeAlphanumeric(int i) {
        int iExtractNumericValueFromBitArray = extractNumericValueFromBitArray(i, 5);
        if (iExtractNumericValueFromBitArray == 15) {
            return new DecodedChar(i + 5, Typography.dollar);
        }
        if (iExtractNumericValueFromBitArray >= 5 && iExtractNumericValueFromBitArray < 15) {
            return new DecodedChar(i + 5, (char) ((iExtractNumericValueFromBitArray + 48) - 5));
        }
        int iExtractNumericValueFromBitArray2 = extractNumericValueFromBitArray(i, 6);
        if (iExtractNumericValueFromBitArray2 >= 32 && iExtractNumericValueFromBitArray2 < 58) {
            return new DecodedChar(i + 6, (char) (iExtractNumericValueFromBitArray2 + 33));
        }
        switch (iExtractNumericValueFromBitArray2) {
            case 58:
                return new DecodedChar(i + 6, '*');
            case 59:
                return new DecodedChar(i + 6, ',');
            case 60:
                return new DecodedChar(i + 6, '-');
            case 61:
                return new DecodedChar(i + 6, '.');
            case 62:
                return new DecodedChar(i + 6, '/');
            default:
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Decoding invalid alphanumeric value: ");
                stringBuffer.append(iExtractNumericValueFromBitArray2);
                throw new RuntimeException(stringBuffer.toString());
        }
    }

    private DecodedChar decodeIsoIec646(int i) {
        int iExtractNumericValueFromBitArray = extractNumericValueFromBitArray(i, 5);
        if (iExtractNumericValueFromBitArray == 15) {
            return new DecodedChar(i + 5, Typography.dollar);
        }
        if (iExtractNumericValueFromBitArray >= 5 && iExtractNumericValueFromBitArray < 15) {
            return new DecodedChar(i + 5, (char) ((iExtractNumericValueFromBitArray + 48) - 5));
        }
        int iExtractNumericValueFromBitArray2 = extractNumericValueFromBitArray(i, 7);
        if (iExtractNumericValueFromBitArray2 >= 64 && iExtractNumericValueFromBitArray2 < 90) {
            return new DecodedChar(i + 7, (char) (iExtractNumericValueFromBitArray2 + 1));
        }
        if (iExtractNumericValueFromBitArray2 >= 90 && iExtractNumericValueFromBitArray2 < 116) {
            return new DecodedChar(i + 7, (char) (iExtractNumericValueFromBitArray2 + 7));
        }
        int iExtractNumericValueFromBitArray3 = extractNumericValueFromBitArray(i, 8);
        switch (iExtractNumericValueFromBitArray3) {
            case 232:
                return new DecodedChar(i + 8, '!');
            case 233:
                return new DecodedChar(i + 8, Typography.quote);
            case FTPReply.SECURITY_DATA_EXCHANGE_COMPLETE /* 234 */:
                return new DecodedChar(i + 8, '%');
            case 235:
                return new DecodedChar(i + 8, Typography.amp);
            case TelnetCommand.EOF /* 236 */:
                return new DecodedChar(i + 8, '\'');
            case TelnetCommand.SUSP /* 237 */:
                return new DecodedChar(i + 8, '(');
            case TelnetCommand.ABORT /* 238 */:
                return new DecodedChar(i + 8, ')');
            case TelnetCommand.EOR /* 239 */:
                return new DecodedChar(i + 8, '*');
            case 240:
                return new DecodedChar(i + 8, '+');
            case TelnetCommand.NOP /* 241 */:
                return new DecodedChar(i + 8, ',');
            case 242:
                return new DecodedChar(i + 8, '-');
            case TelnetCommand.BREAK /* 243 */:
                return new DecodedChar(i + 8, '.');
            case 244:
                return new DecodedChar(i + 8, '/');
            case TelnetCommand.f3464AO /* 245 */:
                return new DecodedChar(i + 8, ':');
            case TelnetCommand.AYT /* 246 */:
                return new DecodedChar(i + 8, ';');
            case TelnetCommand.f3467EC /* 247 */:
                return new DecodedChar(i + 8, Typography.less);
            case TelnetCommand.f3468EL /* 248 */:
                return new DecodedChar(i + 8, '=');
            case TelnetCommand.f3469GA /* 249 */:
                return new DecodedChar(i + 8, Typography.greater);
            case 250:
                return new DecodedChar(i + 8, '?');
            case 251:
                return new DecodedChar(i + 8, '_');
            case TelnetCommand.WONT /* 252 */:
                return new DecodedChar(i + 8, ' ');
            default:
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Decoding invalid ISO/IEC 646 value: ");
                stringBuffer.append(iExtractNumericValueFromBitArray3);
                throw new RuntimeException(stringBuffer.toString());
        }
    }

    private DecodedNumeric decodeNumeric(int i) {
        int i2 = i + 7;
        if (i2 > this.information.size) {
            int iExtractNumericValueFromBitArray = extractNumericValueFromBitArray(i, 4);
            return iExtractNumericValueFromBitArray == 0 ? new DecodedNumeric(this.information.size, 10, 10) : new DecodedNumeric(this.information.size, iExtractNumericValueFromBitArray - 1, 10);
        }
        int iExtractNumericValueFromBitArray2 = extractNumericValueFromBitArray(i, 7) - 8;
        return new DecodedNumeric(i2, iExtractNumericValueFromBitArray2 / 11, iExtractNumericValueFromBitArray2 % 11);
    }

    static int extractNumericValueFromBitArray(BitArray bitArray, int i, int i2) {
        if (i2 > 32) {
            throw new IllegalArgumentException("extractNumberValueFromBitArray can't handle more than 32 bits");
        }
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            if (bitArray.get(i + i4)) {
                i3 |= 1 << ((i2 - i4) - 1);
            }
        }
        return i3;
    }

    private boolean isAlphaOr646ToNumericLatch(int i) {
        int i2 = i + 3;
        if (i2 > this.information.size) {
            return false;
        }
        while (i < i2) {
            if (this.information.get(i)) {
                return false;
            }
            i++;
        }
        return true;
    }

    private boolean isAlphaTo646ToAlphaLatch(int i) {
        int i2;
        if (i + 1 > this.information.size) {
            return false;
        }
        for (int i3 = 0; i3 < 5 && (i2 = i3 + i) < this.information.size; i3++) {
            if (i3 == 2) {
                if (!this.information.get(i + 2)) {
                    return false;
                }
            } else if (this.information.get(i2)) {
                return false;
            }
        }
        return true;
    }

    private boolean isNumericToAlphaNumericLatch(int i) {
        int i2;
        if (i + 1 > this.information.size) {
            return false;
        }
        for (int i3 = 0; i3 < 4 && (i2 = i3 + i) < this.information.size; i3++) {
            if (this.information.get(i2)) {
                return false;
            }
        }
        return true;
    }

    private boolean isStillAlpha(int i) {
        int iExtractNumericValueFromBitArray;
        if (i + 5 > this.information.size) {
            return false;
        }
        int iExtractNumericValueFromBitArray2 = extractNumericValueFromBitArray(i, 5);
        if (iExtractNumericValueFromBitArray2 < 5 || iExtractNumericValueFromBitArray2 >= 16) {
            return i + 6 <= this.information.size && (iExtractNumericValueFromBitArray = extractNumericValueFromBitArray(i, 6)) >= 16 && iExtractNumericValueFromBitArray < 63;
        }
        return true;
    }

    private boolean isStillIsoIec646(int i) {
        int iExtractNumericValueFromBitArray;
        if (i + 5 > this.information.size) {
            return false;
        }
        int iExtractNumericValueFromBitArray2 = extractNumericValueFromBitArray(i, 5);
        if (iExtractNumericValueFromBitArray2 >= 5 && iExtractNumericValueFromBitArray2 < 16) {
            return true;
        }
        if (i + 7 > this.information.size) {
            return false;
        }
        int iExtractNumericValueFromBitArray3 = extractNumericValueFromBitArray(i, 7);
        if (iExtractNumericValueFromBitArray3 < 64 || iExtractNumericValueFromBitArray3 >= 116) {
            return i + 8 <= this.information.size && (iExtractNumericValueFromBitArray = extractNumericValueFromBitArray(i, 8)) >= 232 && iExtractNumericValueFromBitArray < 253;
        }
        return true;
    }

    private boolean isStillNumeric(int i) {
        if (i + 7 > this.information.size) {
            return i + 4 <= this.information.size;
        }
        int i2 = i;
        while (true) {
            int i3 = i + 3;
            if (i2 >= i3) {
                return this.information.get(i3);
            }
            if (this.information.get(i2)) {
                return true;
            }
            i2++;
        }
    }

    private BlockParsedResult parseAlphaBlock() {
        CurrentParsingState currentParsingState;
        int i;
        while (isStillAlpha(this.current.position)) {
            DecodedChar decodedCharDecodeAlphanumeric = decodeAlphanumeric(this.current.position);
            this.current.position = decodedCharDecodeAlphanumeric.getNewPosition();
            if (decodedCharDecodeAlphanumeric.isFNC1()) {
                return new BlockParsedResult(new DecodedInformation(this.current.position, this.buffer.toString()), true);
            }
            this.buffer.append(decodedCharDecodeAlphanumeric.getValue());
        }
        if (isAlphaOr646ToNumericLatch(this.current.position)) {
            this.current.position += 3;
            this.current.setNumeric();
        } else if (isAlphaTo646ToAlphaLatch(this.current.position)) {
            if (this.current.position + 5 < this.information.size) {
                currentParsingState = this.current;
                i = currentParsingState.position + 5;
            } else {
                currentParsingState = this.current;
                i = this.information.size;
            }
            currentParsingState.position = i;
            this.current.setIsoIec646();
        }
        return new BlockParsedResult(false);
    }

    private DecodedInformation parseBlocks() {
        BlockParsedResult alphaBlock;
        boolean zIsFinished;
        do {
            int i = this.current.position;
            alphaBlock = this.current.isAlpha() ? parseAlphaBlock() : this.current.isIsoIec646() ? parseIsoIec646Block() : parseNumericBlock();
            zIsFinished = alphaBlock.isFinished();
            if (!(i != this.current.position) && !zIsFinished) {
                break;
            }
        } while (!zIsFinished);
        return alphaBlock.getDecodedInformation();
    }

    private BlockParsedResult parseIsoIec646Block() {
        CurrentParsingState currentParsingState;
        int i;
        while (isStillIsoIec646(this.current.position)) {
            DecodedChar decodedCharDecodeIsoIec646 = decodeIsoIec646(this.current.position);
            this.current.position = decodedCharDecodeIsoIec646.getNewPosition();
            if (decodedCharDecodeIsoIec646.isFNC1()) {
                return new BlockParsedResult(new DecodedInformation(this.current.position, this.buffer.toString()), true);
            }
            this.buffer.append(decodedCharDecodeIsoIec646.getValue());
        }
        if (isAlphaOr646ToNumericLatch(this.current.position)) {
            this.current.position += 3;
            this.current.setNumeric();
        } else if (isAlphaTo646ToAlphaLatch(this.current.position)) {
            if (this.current.position + 5 < this.information.size) {
                currentParsingState = this.current;
                i = currentParsingState.position + 5;
            } else {
                currentParsingState = this.current;
                i = this.information.size;
            }
            currentParsingState.position = i;
            this.current.setAlpha();
        }
        return new BlockParsedResult(false);
    }

    private BlockParsedResult parseNumericBlock() {
        while (isStillNumeric(this.current.position)) {
            DecodedNumeric decodedNumericDecodeNumeric = decodeNumeric(this.current.position);
            this.current.position = decodedNumericDecodeNumeric.getNewPosition();
            if (decodedNumericDecodeNumeric.isFirstDigitFNC1()) {
                return new BlockParsedResult(decodedNumericDecodeNumeric.isSecondDigitFNC1() ? new DecodedInformation(this.current.position, this.buffer.toString()) : new DecodedInformation(this.current.position, this.buffer.toString(), decodedNumericDecodeNumeric.getSecondDigit()), true);
            }
            this.buffer.append(decodedNumericDecodeNumeric.getFirstDigit());
            if (decodedNumericDecodeNumeric.isSecondDigitFNC1()) {
                return new BlockParsedResult(new DecodedInformation(this.current.position, this.buffer.toString()), true);
            }
            this.buffer.append(decodedNumericDecodeNumeric.getSecondDigit());
        }
        if (isNumericToAlphaNumericLatch(this.current.position)) {
            this.current.setAlpha();
            this.current.position += 4;
        }
        return new BlockParsedResult(false);
    }

    String decodeAllCodes(StringBuffer stringBuffer, int i) throws NotFoundException {
        String str = null;
        while (true) {
            DecodedInformation decodedInformationDecodeGeneralPurposeField = decodeGeneralPurposeField(i, str);
            stringBuffer.append(FieldParser.parseFieldsInGeneralPurpose(decodedInformationDecodeGeneralPurposeField.getNewString()));
            String strValueOf = decodedInformationDecodeGeneralPurposeField.isRemaining() ? String.valueOf(decodedInformationDecodeGeneralPurposeField.getRemainingValue()) : null;
            if (i == decodedInformationDecodeGeneralPurposeField.getNewPosition()) {
                return stringBuffer.toString();
            }
            i = decodedInformationDecodeGeneralPurposeField.getNewPosition();
            str = strValueOf;
        }
    }

    DecodedInformation decodeGeneralPurposeField(int i, String str) {
        this.buffer.setLength(0);
        if (str != null) {
            this.buffer.append(str);
        }
        this.current.position = i;
        DecodedInformation blocks = parseBlocks();
        return (blocks == null || !blocks.isRemaining()) ? new DecodedInformation(this.current.position, this.buffer.toString()) : new DecodedInformation(this.current.position, this.buffer.toString(), blocks.getRemainingValue());
    }

    int extractNumericValueFromBitArray(int i, int i2) {
        return extractNumericValueFromBitArray(this.information, i, i2);
    }
}
