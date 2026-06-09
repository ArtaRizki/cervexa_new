package com.google.zxing.client.result;

import com.google.zxing.Result;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes.dex */
final class EmailDoCoMoResultParser extends AbstractDoCoMoResultParser {
    private static final char[] ATEXT_SYMBOLS = {'@', '.', '!', '#', Typography.dollar, '%', Typography.amp, '\'', '*', '+', '-', '/', '=', '?', '^', '_', '`', '{', '|', '}', '~'};

    EmailDoCoMoResultParser() {
    }

    private static boolean isAtextSymbol(char c) {
        int i = 0;
        while (true) {
            char[] cArr = ATEXT_SYMBOLS;
            if (i >= cArr.length) {
                return false;
            }
            if (c == cArr[i]) {
                return true;
            }
            i++;
        }
    }

    static boolean isBasicallyValidEmailAddress(String str) {
        if (str == null) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if ((cCharAt < 'a' || cCharAt > 'z') && ((cCharAt < 'A' || cCharAt > 'Z') && ((cCharAt < '0' || cCharAt > '9') && !isAtextSymbol(cCharAt)))) {
                return false;
            }
            if (cCharAt == '@') {
                if (z) {
                    return false;
                }
                z = true;
            }
        }
        return z;
    }

    public static EmailAddressParsedResult parse(Result result) {
        String[] strArrMatchDoCoMoPrefixedField;
        String text = result.getText();
        if (text == null || !text.startsWith("MATMSG:") || (strArrMatchDoCoMoPrefixedField = matchDoCoMoPrefixedField("TO:", text, true)) == null) {
            return null;
        }
        String str = strArrMatchDoCoMoPrefixedField[0];
        if (!isBasicallyValidEmailAddress(str)) {
            return null;
        }
        String strMatchSingleDoCoMoPrefixedField = matchSingleDoCoMoPrefixedField("SUB:", text, false);
        String strMatchSingleDoCoMoPrefixedField2 = matchSingleDoCoMoPrefixedField("BODY:", text, false);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("mailto:");
        stringBuffer.append(str);
        return new EmailAddressParsedResult(str, strMatchSingleDoCoMoPrefixedField, strMatchSingleDoCoMoPrefixedField2, stringBuffer.toString());
    }
}
