package com.google.zxing.client.result.optional;

import com.google.zxing.Result;

/* JADX INFO: loaded from: classes.dex */
final class NDEFSmartPosterResultParser extends AbstractNDEFResultParser {
    NDEFSmartPosterResultParser() {
    }

    public static NDEFSmartPosterParsedResult parse(Result result) {
        NDEFRecord record;
        byte[] rawBytes = result.getRawBytes();
        if (rawBytes == null || (record = NDEFRecord.readRecord(rawBytes, 0)) == null || !record.isMessageBegin() || !record.isMessageEnd() || !record.getType().equals(NDEFRecord.SMART_POSTER_WELL_KNOWN_TYPE)) {
            return null;
        }
        byte[] payload = record.getPayload();
        byte b = -1;
        NDEFRecord record2 = null;
        String str = null;
        String strDecodeURIPayload = null;
        int totalRecordLength = 0;
        int i = 0;
        while (totalRecordLength < payload.length && (record2 = NDEFRecord.readRecord(payload, totalRecordLength)) != null) {
            if (i == 0 && !record2.isMessageBegin()) {
                return null;
            }
            String type = record2.getType();
            if (NDEFRecord.TEXT_WELL_KNOWN_TYPE.equals(type)) {
                str = NDEFTextResultParser.decodeTextPayload(record2.getPayload())[1];
            } else if (NDEFRecord.URI_WELL_KNOWN_TYPE.equals(type)) {
                strDecodeURIPayload = NDEFURIResultParser.decodeURIPayload(record2.getPayload());
            } else if ("act".equals(type)) {
                b = record2.getPayload()[0];
            }
            i++;
            totalRecordLength += record2.getTotalRecordLength();
        }
        if (i != 0 && (record2 == null || record2.isMessageEnd())) {
            return new NDEFSmartPosterParsedResult(b, strDecodeURIPayload, str);
        }
        return null;
    }
}
