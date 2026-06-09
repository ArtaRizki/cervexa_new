package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
final class EmailAddressResultParser extends ResultParser {
    EmailAddressResultParser() {
    }

    public static EmailAddressParsedResult parse(Result result) {
        String str;
        String text = result.getText();
        String str2 = null;
        if (text == null) {
            return null;
        }
        if (!text.startsWith("mailto:") && !text.startsWith("MAILTO:")) {
            if (!EmailDoCoMoResultParser.isBasicallyValidEmailAddress(text)) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("mailto:");
            stringBuffer.append(text);
            return new EmailAddressParsedResult(text, null, null, stringBuffer.toString());
        }
        String strSubstring = text.substring(7);
        int iIndexOf = strSubstring.indexOf(63);
        if (iIndexOf >= 0) {
            strSubstring = strSubstring.substring(0, iIndexOf);
        }
        Hashtable nameValuePairs = parseNameValuePairs(text);
        if (nameValuePairs != null) {
            if (strSubstring.length() == 0) {
                strSubstring = (String) nameValuePairs.get("to");
            }
            str2 = (String) nameValuePairs.get("subject");
            str = (String) nameValuePairs.get("body");
        } else {
            str = null;
        }
        return new EmailAddressParsedResult(strSubstring, str2, str, text);
    }
}
