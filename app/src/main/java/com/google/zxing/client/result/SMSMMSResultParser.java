package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
final class SMSMMSResultParser extends ResultParser {
    private SMSMMSResultParser() {
    }

    private static void addNumberVia(Vector vector, Vector vector2, String str) {
        int iIndexOf = str.indexOf(59);
        String strSubstring = null;
        if (iIndexOf < 0) {
            vector.addElement(str);
        } else {
            vector.addElement(str.substring(0, iIndexOf));
            String strSubstring2 = str.substring(iIndexOf + 1);
            if (strSubstring2.startsWith("via=")) {
                strSubstring = strSubstring2.substring(4);
            }
        }
        vector2.addElement(strSubstring);
    }

    public static SMSParsedResult parse(Result result) {
        String str;
        String text = result.getText();
        String str2 = null;
        if (text == null) {
            return null;
        }
        if (!text.startsWith("sms:") && !text.startsWith("SMS:") && !text.startsWith("mms:") && !text.startsWith("MMS:")) {
            return null;
        }
        Hashtable nameValuePairs = parseNameValuePairs(text);
        boolean z = false;
        if (nameValuePairs == null || nameValuePairs.isEmpty()) {
            str = null;
        } else {
            str2 = (String) nameValuePairs.get("subject");
            str = (String) nameValuePairs.get("body");
            z = true;
        }
        int iIndexOf = text.indexOf(63, 4);
        String strSubstring = (iIndexOf < 0 || !z) ? text.substring(4) : text.substring(4, iIndexOf);
        int i = -1;
        Vector vector = new Vector(1);
        Vector vector2 = new Vector(1);
        while (true) {
            int i2 = i + 1;
            int iIndexOf2 = strSubstring.indexOf(44, i2);
            if (iIndexOf2 <= i) {
                addNumberVia(vector, vector2, strSubstring.substring(i2));
                return new SMSParsedResult(toStringArray(vector), toStringArray(vector2), str2, str);
            }
            addNumberVia(vector, vector2, strSubstring.substring(i2, iIndexOf2));
            i = iIndexOf2;
        }
    }
}
