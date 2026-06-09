package com.google.zxing.client.result.optional;

import com.google.zxing.Result;
import com.google.zxing.client.result.URIParsedResult;
import kotlin.UByte;

/* JADX INFO: loaded from: classes.dex */
final class NDEFURIResultParser extends AbstractNDEFResultParser {
    private static final String[] URI_PREFIXES = {null, "http://www.", "https://www.", "http://", "https://", "tel:", "mailto:", "ftp://anonymous:anonymous@", "ftp://ftp.", "ftps://", "sftp://", "smb://", "nfs://", "ftp://", "dav://", "news:", "telnet://", "imap:", "rtsp://", "urn:", "pop:", "sip:", "sips:", "tftp:", "btspp://", "btl2cap://", "btgoep://", "tcpobex://", "irdaobex://", "file://", "urn:epc:id:", "urn:epc:tag:", "urn:epc:pat:", "urn:epc:raw:", "urn:epc:", "urn:nfc:"};

    NDEFURIResultParser() {
    }

    static String decodeURIPayload(byte[] bArr) {
        int i = bArr[0] & UByte.MAX_VALUE;
        String[] strArr = URI_PREFIXES;
        String str = i < strArr.length ? strArr[i] : null;
        String strBytesToString = bytesToString(bArr, 1, bArr.length - 1, "UTF8");
        if (str == null) {
            return strBytesToString;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(strBytesToString);
        return stringBuffer.toString();
    }

    public static URIParsedResult parse(Result result) {
        NDEFRecord record;
        byte[] rawBytes = result.getRawBytes();
        if (rawBytes != null && (record = NDEFRecord.readRecord(rawBytes, 0)) != null && record.isMessageBegin() && record.isMessageEnd() && record.getType().equals(NDEFRecord.URI_WELL_KNOWN_TYPE)) {
            return new URIParsedResult(decodeURIPayload(record.getPayload()), null);
        }
        return null;
    }
}
