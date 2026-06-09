package com.google.zxing.client.result.optional;

import com.google.zxing.Result;
import com.google.zxing.client.result.TextParsedResult;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes.dex */
final class NDEFTextResultParser extends AbstractNDEFResultParser {
    NDEFTextResultParser() {
    }

    static String[] decodeTextPayload(byte[] bArr) {
        byte b = bArr[0];
        boolean z = (b & ByteCompanionObject.MIN_VALUE) != 0;
        int i = b & 31;
        return new String[]{bytesToString(bArr, 1, i, "US-ASCII"), bytesToString(bArr, i + 1, (bArr.length - i) - 1, z ? "UTF-16" : "UTF8")};
    }

    public static TextParsedResult parse(Result result) {
        NDEFRecord record;
        byte[] rawBytes = result.getRawBytes();
        if (rawBytes == null || (record = NDEFRecord.readRecord(rawBytes, 0)) == null || !record.isMessageBegin() || !record.isMessageEnd() || !record.getType().equals(NDEFRecord.TEXT_WELL_KNOWN_TYPE)) {
            return null;
        }
        String[] strArrDecodeTextPayload = decodeTextPayload(record.getPayload());
        return new TextParsedResult(strArrDecodeTextPayload[0], strArrDecodeTextPayload[1]);
    }
}
