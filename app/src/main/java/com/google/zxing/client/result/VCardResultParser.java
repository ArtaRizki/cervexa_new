package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
final class VCardResultParser extends ResultParser {
    private VCardResultParser() {
    }

    private static String decodeQuotedPrintable(String str, String str2) {
        char cCharAt;
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < length) {
            char cCharAt2 = str.charAt(i);
            if (cCharAt2 != '\n' && cCharAt2 != '\r') {
                if (cCharAt2 != '=') {
                    maybeAppendFragment(byteArrayOutputStream, str2, stringBuffer);
                    stringBuffer.append(cCharAt2);
                } else if (i < length - 2 && (cCharAt = str.charAt(i + 1)) != '\r' && cCharAt != '\n') {
                    i += 2;
                    try {
                        byteArrayOutputStream.write((toHexValue(cCharAt) * 16) + toHexValue(str.charAt(i)));
                    } catch (IllegalArgumentException unused) {
                    }
                }
            }
            i++;
        }
        maybeAppendFragment(byteArrayOutputStream, str2, stringBuffer);
        return stringBuffer.toString();
    }

    private static String formatAddress(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length);
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == ';') {
                cCharAt = ' ';
            }
            stringBuffer.append(cCharAt);
        }
        return stringBuffer.toString().trim();
    }

    private static void formatNames(String[] strArr) {
        if (strArr != null) {
            for (int i = 0; i < strArr.length; i++) {
                String str = strArr[i];
                String[] strArr2 = new String[5];
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    int iIndexOf = str.indexOf(59, i2);
                    if (iIndexOf > 0) {
                        strArr2[i3] = str.substring(i2, iIndexOf);
                        i3++;
                        i2 = iIndexOf + 1;
                    }
                }
                strArr2[i3] = str.substring(i2);
                StringBuffer stringBuffer = new StringBuffer(100);
                maybeAppendComponent(strArr2, 3, stringBuffer);
                maybeAppendComponent(strArr2, 1, stringBuffer);
                maybeAppendComponent(strArr2, 2, stringBuffer);
                maybeAppendComponent(strArr2, 0, stringBuffer);
                maybeAppendComponent(strArr2, 4, stringBuffer);
                strArr[i] = stringBuffer.toString().trim();
            }
        }
    }

    private static boolean isLikeVCardDate(String str) {
        if (str == null || isStringOfDigits(str, 8)) {
            return true;
        }
        return str.length() == 10 && str.charAt(4) == '-' && str.charAt(7) == '-' && isSubstringOfDigits(str, 0, 4) && isSubstringOfDigits(str, 5, 2) && isSubstringOfDigits(str, 8, 2);
    }

    static String matchSingleVCardPrefixedField(String str, String str2, boolean z) {
        String[] strArrMatchVCardPrefixedField = matchVCardPrefixedField(str, str2, z);
        if (strArrMatchVCardPrefixedField == null) {
            return null;
        }
        return strArrMatchVCardPrefixedField[0];
    }

    private static String[] matchVCardPrefixedField(String str, String str2, boolean z) {
        boolean z2;
        String str3;
        int iIndexOf;
        int length = str2.length();
        int length2 = 0;
        Vector vector = null;
        while (length2 < length) {
            int iIndexOf2 = str2.indexOf(str, length2);
            if (iIndexOf2 < 0) {
                break;
            }
            if (iIndexOf2 <= 0 || str2.charAt(iIndexOf2 - 1) == '\n') {
                length2 = iIndexOf2 + str.length();
                char c = ';';
                if (str2.charAt(length2) == ':' || str2.charAt(length2) == ';') {
                    int i = length2;
                    while (str2.charAt(i) != ':') {
                        i++;
                    }
                    if (i > length2) {
                        int i2 = length2 + 1;
                        z2 = false;
                        str3 = null;
                        while (i2 <= i) {
                            if (str2.charAt(i2) == c || str2.charAt(i2) == ':') {
                                String strSubstring = str2.substring(length2 + 1, i2);
                                int iIndexOf3 = strSubstring.indexOf(61);
                                if (iIndexOf3 >= 0) {
                                    String strSubstring2 = strSubstring.substring(0, iIndexOf3);
                                    String strSubstring3 = strSubstring.substring(iIndexOf3 + 1);
                                    if ("ENCODING".equalsIgnoreCase(strSubstring2)) {
                                        if ("QUOTED-PRINTABLE".equalsIgnoreCase(strSubstring3)) {
                                            z2 = true;
                                        }
                                    } else if ("CHARSET".equalsIgnoreCase(strSubstring2)) {
                                        str3 = strSubstring3;
                                    }
                                }
                                length2 = i2;
                            }
                            i2++;
                            c = ';';
                        }
                    } else {
                        z2 = false;
                        str3 = null;
                    }
                    int i3 = i + 1;
                    int i4 = i3;
                    while (true) {
                        iIndexOf = str2.indexOf(10, i4);
                        if (iIndexOf < 0) {
                            break;
                        }
                        if (iIndexOf < str2.length() - 1) {
                            int i5 = iIndexOf + 1;
                            if (str2.charAt(i5) == ' ' || str2.charAt(i5) == '\t') {
                                i4 = iIndexOf + 2;
                            }
                        }
                        if (!z2 || (str2.charAt(iIndexOf - 1) != '=' && str2.charAt(iIndexOf - 2) != '=')) {
                            break;
                        }
                        i4 = iIndexOf + 1;
                    }
                    if (iIndexOf < 0) {
                        length2 = length;
                    } else {
                        if (iIndexOf > i3) {
                            if (vector == null) {
                                vector = new Vector(1);
                            }
                            if (str2.charAt(iIndexOf - 1) == '\r') {
                                iIndexOf--;
                            }
                            String strSubstring4 = str2.substring(i3, iIndexOf);
                            if (z) {
                                strSubstring4 = strSubstring4.trim();
                            }
                            vector.addElement(z2 ? decodeQuotedPrintable(strSubstring4, str3) : stripContinuationCRLF(strSubstring4));
                        }
                        length2 = iIndexOf + 1;
                    }
                }
            } else {
                length2 = iIndexOf2 + 1;
            }
        }
        if (vector == null || vector.isEmpty()) {
            return null;
        }
        return toStringArray(vector);
    }

    private static void maybeAppendComponent(String[] strArr, int i, StringBuffer stringBuffer) {
        if (strArr[i] != null) {
            stringBuffer.append(' ');
            stringBuffer.append(strArr[i]);
        }
    }

    private static void maybeAppendFragment(ByteArrayOutputStream byteArrayOutputStream, String str, StringBuffer stringBuffer) {
        String str2;
        if (byteArrayOutputStream.size() > 0) {
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (str == null) {
                str2 = new String(byteArray);
            } else {
                try {
                    str2 = new String(byteArray, str);
                } catch (UnsupportedEncodingException unused) {
                    str2 = new String(byteArray);
                }
            }
            byteArrayOutputStream.reset();
            stringBuffer.append(str2);
        }
    }

    public static AddressBookParsedResult parse(Result result) {
        String text = result.getText();
        if (text == null || !text.startsWith("BEGIN:VCARD")) {
            return null;
        }
        String[] strArrMatchVCardPrefixedField = matchVCardPrefixedField("FN", text, true);
        if (strArrMatchVCardPrefixedField == null) {
            strArrMatchVCardPrefixedField = matchVCardPrefixedField("N", text, true);
            formatNames(strArrMatchVCardPrefixedField);
        }
        String[] strArr = strArrMatchVCardPrefixedField;
        String[] strArrMatchVCardPrefixedField2 = matchVCardPrefixedField("TEL", text, true);
        String[] strArrMatchVCardPrefixedField3 = matchVCardPrefixedField("EMAIL", text, true);
        String strMatchSingleVCardPrefixedField = matchSingleVCardPrefixedField("NOTE", text, false);
        String[] strArrMatchVCardPrefixedField4 = matchVCardPrefixedField("ADR", text, true);
        if (strArrMatchVCardPrefixedField4 != null) {
            for (int i = 0; i < strArrMatchVCardPrefixedField4.length; i++) {
                strArrMatchVCardPrefixedField4[i] = formatAddress(strArrMatchVCardPrefixedField4[i]);
            }
        }
        String strMatchSingleVCardPrefixedField2 = matchSingleVCardPrefixedField("ORG", text, true);
        String strMatchSingleVCardPrefixedField3 = matchSingleVCardPrefixedField("BDAY", text, true);
        return new AddressBookParsedResult(strArr, null, strArrMatchVCardPrefixedField2, strArrMatchVCardPrefixedField3, strMatchSingleVCardPrefixedField, strArrMatchVCardPrefixedField4, strMatchSingleVCardPrefixedField2, !isLikeVCardDate(strMatchSingleVCardPrefixedField3) ? null : strMatchSingleVCardPrefixedField3, matchSingleVCardPrefixedField("TITLE", text, true), matchSingleVCardPrefixedField("URL", text, true));
    }

    private static String stripContinuationCRLF(String str) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length);
        boolean z = false;
        for (int i = 0; i < length; i++) {
            if (z) {
                z = false;
            } else {
                char cCharAt = str.charAt(i);
                if (cCharAt != '\n') {
                    if (cCharAt != '\r') {
                        stringBuffer.append(cCharAt);
                    }
                    z = false;
                } else {
                    z = true;
                }
            }
        }
        return stringBuffer.toString();
    }

    private static int toHexValue(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        char c2 = 'A';
        if (c < 'A' || c > 'F') {
            c2 = 'a';
            if (c < 'a' || c > 'f') {
                throw new IllegalArgumentException();
            }
        }
        return (c - c2) + 10;
    }
}
