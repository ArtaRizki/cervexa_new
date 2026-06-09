package com.google.zxing.client.result;

import com.google.zxing.Result;

/* JADX INFO: loaded from: classes.dex */
final class URLTOResultParser {
    private URLTOResultParser() {
    }

    public static URIParsedResult parse(Result result) {
        int iIndexOf;
        String text = result.getText();
        if (text == null || (!(text.startsWith("urlto:") || text.startsWith("URLTO:")) || (iIndexOf = text.indexOf(58, 6)) < 0)) {
            return null;
        }
        return new URIParsedResult(text.substring(iIndexOf + 1), iIndexOf > 6 ? text.substring(6, iIndexOf) : null);
    }
}
