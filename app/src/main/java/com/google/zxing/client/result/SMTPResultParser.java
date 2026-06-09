package com.google.zxing.client.result;

import com.google.zxing.Result;

/* JADX INFO: loaded from: classes.dex */
final class SMTPResultParser {
    private SMTPResultParser() {
    }

    public static EmailAddressParsedResult parse(Result result) {
        String str;
        String text = result.getText();
        String str2 = null;
        if (text == null) {
            return null;
        }
        if (!text.startsWith("smtp:") && !text.startsWith("SMTP:")) {
            return null;
        }
        String strSubstring = text.substring(5);
        int iIndexOf = strSubstring.indexOf(58);
        if (iIndexOf >= 0) {
            String strSubstring2 = strSubstring.substring(iIndexOf + 1);
            strSubstring = strSubstring.substring(0, iIndexOf);
            int iIndexOf2 = strSubstring2.indexOf(58);
            if (iIndexOf2 >= 0) {
                String strSubstring3 = strSubstring2.substring(iIndexOf2 + 1);
                String strSubstring4 = strSubstring2.substring(0, iIndexOf2);
                str = strSubstring3;
                str2 = strSubstring4;
            } else {
                str = null;
                str2 = strSubstring2;
            }
        } else {
            str = null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("mailto:");
        stringBuffer.append(strSubstring);
        return new EmailAddressParsedResult(strSubstring, str2, str, stringBuffer.toString());
    }
}
