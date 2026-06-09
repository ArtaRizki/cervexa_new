package com.google.zxing.client.result;

import com.google.zxing.Result;

/* JADX INFO: loaded from: classes.dex */
final class SMSTOMMSTOResultParser extends ResultParser {
    private SMSTOMMSTOResultParser() {
    }

    public static SMSParsedResult parse(Result result) {
        String strSubstring;
        String text = result.getText();
        if (text == null) {
            return null;
        }
        if (!text.startsWith("smsto:") && !text.startsWith("SMSTO:") && !text.startsWith("mmsto:") && !text.startsWith("MMSTO:")) {
            return null;
        }
        String strSubstring2 = text.substring(6);
        int iIndexOf = strSubstring2.indexOf(58);
        if (iIndexOf >= 0) {
            strSubstring = strSubstring2.substring(iIndexOf + 1);
            strSubstring2 = strSubstring2.substring(0, iIndexOf);
        } else {
            strSubstring = null;
        }
        return new SMSParsedResult(strSubstring2, (String) null, (String) null, strSubstring);
    }
}
