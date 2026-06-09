package com.google.zxing.client.result;

import com.google.zxing.Result;

/* JADX INFO: loaded from: classes.dex */
final class BookmarkDoCoMoResultParser extends AbstractDoCoMoResultParser {
    private BookmarkDoCoMoResultParser() {
    }

    public static URIParsedResult parse(Result result) {
        String text = result.getText();
        if (text == null || !text.startsWith("MEBKM:")) {
            return null;
        }
        String strMatchSingleDoCoMoPrefixedField = matchSingleDoCoMoPrefixedField("TITLE:", text, true);
        String[] strArrMatchDoCoMoPrefixedField = matchDoCoMoPrefixedField("URL:", text, true);
        if (strArrMatchDoCoMoPrefixedField == null) {
            return null;
        }
        String str = strArrMatchDoCoMoPrefixedField[0];
        if (URIResultParser.isBasicallyValidURI(str)) {
            return new URIParsedResult(str, strMatchSingleDoCoMoPrefixedField);
        }
        return null;
    }
}
