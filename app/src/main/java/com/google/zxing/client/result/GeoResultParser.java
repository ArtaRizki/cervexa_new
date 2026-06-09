package com.google.zxing.client.result;

import com.google.zxing.Result;

/* JADX INFO: loaded from: classes.dex */
final class GeoResultParser extends ResultParser {
    private GeoResultParser() {
    }

    public static GeoParsedResult parse(Result result) {
        String strSubstring;
        String str;
        double d;
        double d2;
        String text = result.getText();
        if (text != null && (text.startsWith("geo:") || text.startsWith("GEO:"))) {
            int iIndexOf = text.indexOf(63, 4);
            if (iIndexOf < 0) {
                strSubstring = text.substring(4);
                str = null;
            } else {
                String strSubstring2 = text.substring(iIndexOf + 1);
                strSubstring = text.substring(4, iIndexOf);
                str = strSubstring2;
            }
            int iIndexOf2 = strSubstring.indexOf(44);
            if (iIndexOf2 < 0) {
                return null;
            }
            int i = iIndexOf2 + 1;
            int iIndexOf3 = strSubstring.indexOf(44, i);
            try {
                double d3 = Double.parseDouble(strSubstring.substring(0, iIndexOf2));
                if (d3 <= 90.0d && d3 >= -90.0d) {
                    if (iIndexOf3 < 0) {
                        d2 = Double.parseDouble(strSubstring.substring(i));
                        d = 0.0d;
                    } else {
                        double d4 = Double.parseDouble(strSubstring.substring(i, iIndexOf3));
                        d = Double.parseDouble(strSubstring.substring(iIndexOf3 + 1));
                        d2 = d4;
                    }
                    if (d2 <= 180.0d && d2 >= -180.0d && d >= 0.0d) {
                        return new GeoParsedResult(d3, d2, d, str);
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }
}
