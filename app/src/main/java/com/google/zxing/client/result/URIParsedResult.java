package com.google.zxing.client.result;

/* JADX INFO: loaded from: classes.dex */
public final class URIParsedResult extends ParsedResult {
    private final String title;
    private final String uri;

    public URIParsedResult(String str, String str2) {
        super(ParsedResultType.URI);
        this.uri = massageURI(str);
        this.title = str2;
    }

    private boolean containsUser() {
        int iIndexOf = this.uri.indexOf(58) + 1;
        int length = this.uri.length();
        while (iIndexOf < length && this.uri.charAt(iIndexOf) == '/') {
            iIndexOf++;
        }
        int iIndexOf2 = this.uri.indexOf(47, iIndexOf);
        if (iIndexOf2 >= 0) {
            length = iIndexOf2;
        }
        int iIndexOf3 = this.uri.indexOf(64, iIndexOf);
        return iIndexOf3 >= iIndexOf && iIndexOf3 < length;
    }

    private static boolean isColonFollowedByPortNumber(String str, int i) {
        int i2 = i + 1;
        int iIndexOf = str.indexOf(47, i2);
        if (iIndexOf < 0) {
            iIndexOf = str.length();
        }
        if (iIndexOf <= i2) {
            return false;
        }
        while (i2 < iIndexOf) {
            if (str.charAt(i2) < '0' || str.charAt(i2) > '9') {
                return false;
            }
            i2++;
        }
        return true;
    }

    private static String massageURI(String str) {
        String strTrim = str.trim();
        int iIndexOf = strTrim.indexOf(58);
        if (iIndexOf >= 0 && !isColonFollowedByPortNumber(strTrim, iIndexOf)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(strTrim.substring(0, iIndexOf).toLowerCase());
            stringBuffer.append(strTrim.substring(iIndexOf));
            return stringBuffer.toString();
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("http://");
        stringBuffer2.append(strTrim);
        return stringBuffer2.toString();
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        StringBuffer stringBuffer = new StringBuffer(30);
        maybeAppend(this.title, stringBuffer);
        maybeAppend(this.uri, stringBuffer);
        return stringBuffer.toString();
    }

    public String getTitle() {
        return this.title;
    }

    public String getURI() {
        return this.uri;
    }

    public boolean isPossiblyMaliciousURI() {
        return containsUser();
    }
}
