package com.google.zxing.client.result;

import com.google.zxing.Result;

/* JADX INFO: loaded from: classes.dex */
final class URIResultParser extends ResultParser {
    private URIResultParser() {
    }

    static boolean isBasicallyValidURI(String str) {
        int iIndexOf;
        if (str == null || str.indexOf(32) >= 0 || str.indexOf(10) >= 0 || (iIndexOf = str.indexOf(46)) >= str.length() - 2) {
            return false;
        }
        int iIndexOf2 = str.indexOf(58);
        if (iIndexOf < 0 && iIndexOf2 < 0) {
            return false;
        }
        if (iIndexOf2 < 0) {
            return true;
        }
        if (iIndexOf < 0 || iIndexOf > iIndexOf2) {
            for (int i = 0; i < iIndexOf2; i++) {
                char cCharAt = str.charAt(i);
                if ((cCharAt < 'a' || cCharAt > 'z') && (cCharAt < 'A' || cCharAt > 'Z')) {
                    return false;
                }
            }
            return true;
        }
        if (iIndexOf2 >= str.length() - 2) {
            return false;
        }
        for (int i2 = iIndexOf2 + 1; i2 < iIndexOf2 + 3; i2++) {
            char cCharAt2 = str.charAt(i2);
            if (cCharAt2 < '0' || cCharAt2 > '9') {
                return false;
            }
        }
        return true;
    }

    public static URIParsedResult parse(Result result) {
        String text = result.getText();
        if (text != null && text.startsWith("URL:")) {
            text = text.substring(4);
        }
        if (text != null) {
            text = text.trim();
        }
        if (isBasicallyValidURI(text)) {
            return new URIParsedResult(text, null);
        }
        return null;
    }
}
