package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public abstract class ResultParser {
    private static void appendKeyValue(String str, int i, int i2, Hashtable hashtable) {
        int iIndexOf = str.indexOf(61, i);
        if (iIndexOf >= 0) {
            hashtable.put(str.substring(i, iIndexOf), urlDecode(str.substring(iIndexOf + 1, i2)));
        }
    }

    private static int findFirstEscape(char[] cArr) {
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            char c = cArr[i];
            if (c == '+' || c == '%') {
                return i;
            }
        }
        return -1;
    }

    protected static boolean isStringOfDigits(String str, int i) {
        if (str == null || i != str.length()) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt < '0' || cCharAt > '9') {
                return false;
            }
        }
        return true;
    }

    protected static boolean isSubstringOfDigits(String str, int i, int i2) {
        int i3;
        if (str == null || str.length() < (i3 = i2 + i)) {
            return false;
        }
        while (i < i3) {
            char cCharAt = str.charAt(i);
            if (cCharAt < '0' || cCharAt > '9') {
                return false;
            }
            i++;
        }
        return true;
    }

    static String[] matchPrefixedField(String str, String str2, char c, boolean z) {
        int length = str2.length();
        Vector vector = null;
        int i = 0;
        while (i < length) {
            int iIndexOf = str2.indexOf(str, i);
            if (iIndexOf < 0) {
                break;
            }
            int length2 = iIndexOf + str.length();
            Vector vector2 = vector;
            boolean z2 = false;
            int length3 = length2;
            while (!z2) {
                int iIndexOf2 = str2.indexOf(c, length3);
                if (iIndexOf2 < 0) {
                    length3 = str2.length();
                } else if (str2.charAt(iIndexOf2 - 1) == '\\') {
                    length3 = iIndexOf2 + 1;
                } else {
                    if (vector2 == null) {
                        vector2 = new Vector(3);
                    }
                    String strUnescapeBackslash = unescapeBackslash(str2.substring(length2, iIndexOf2));
                    if (z) {
                        strUnescapeBackslash = strUnescapeBackslash.trim();
                    }
                    vector2.addElement(strUnescapeBackslash);
                    length3 = iIndexOf2 + 1;
                }
                z2 = true;
            }
            i = length3;
            vector = vector2;
        }
        if (vector == null || vector.isEmpty()) {
            return null;
        }
        return toStringArray(vector);
    }

    static String matchSinglePrefixedField(String str, String str2, char c, boolean z) {
        String[] strArrMatchPrefixedField = matchPrefixedField(str, str2, c, z);
        if (strArrMatchPrefixedField == null) {
            return null;
        }
        return strArrMatchPrefixedField[0];
    }

    protected static void maybeAppend(String str, StringBuffer stringBuffer) {
        if (str != null) {
            stringBuffer.append('\n');
            stringBuffer.append(str);
        }
    }

    protected static void maybeAppend(String[] strArr, StringBuffer stringBuffer) {
        if (strArr != null) {
            for (String str : strArr) {
                stringBuffer.append('\n');
                stringBuffer.append(str);
            }
        }
    }

    protected static String[] maybeWrap(String str) {
        if (str == null) {
            return null;
        }
        return new String[]{str};
    }

    private static int parseHexDigit(char c) {
        char c2 = 'a';
        if (c < 'a') {
            c2 = 'A';
            if (c < 'A') {
                if (c < '0' || c > '9') {
                    return -1;
                }
                return c - '0';
            }
            if (c > 'F') {
                return -1;
            }
        } else if (c > 'f') {
            return -1;
        }
        return (c - c2) + 10;
    }

    static Hashtable parseNameValuePairs(String str) {
        int iIndexOf = str.indexOf(63);
        if (iIndexOf < 0) {
            return null;
        }
        Hashtable hashtable = new Hashtable(3);
        int i = iIndexOf + 1;
        while (true) {
            int iIndexOf2 = str.indexOf(38, i);
            if (iIndexOf2 < 0) {
                appendKeyValue(str, i, str.length(), hashtable);
                return hashtable;
            }
            appendKeyValue(str, i, iIndexOf2, hashtable);
            i = iIndexOf2 + 1;
        }
    }

    public static ParsedResult parseResult(Result result) {
        URIParsedResult uRIParsedResult = BookmarkDoCoMoResultParser.parse(result);
        if (uRIParsedResult != null) {
            return uRIParsedResult;
        }
        AddressBookParsedResult addressBookParsedResult = AddressBookDoCoMoResultParser.parse(result);
        if (addressBookParsedResult != null) {
            return addressBookParsedResult;
        }
        EmailAddressParsedResult emailAddressParsedResult = EmailDoCoMoResultParser.parse(result);
        if (emailAddressParsedResult != null) {
            return emailAddressParsedResult;
        }
        AddressBookParsedResult addressBookParsedResult2 = AddressBookAUResultParser.parse(result);
        if (addressBookParsedResult2 != null) {
            return addressBookParsedResult2;
        }
        AddressBookParsedResult addressBookParsedResult3 = VCardResultParser.parse(result);
        if (addressBookParsedResult3 != null) {
            return addressBookParsedResult3;
        }
        AddressBookParsedResult addressBookParsedResult4 = BizcardResultParser.parse(result);
        if (addressBookParsedResult4 != null) {
            return addressBookParsedResult4;
        }
        CalendarParsedResult calendarParsedResult = VEventResultParser.parse(result);
        if (calendarParsedResult != null) {
            return calendarParsedResult;
        }
        EmailAddressParsedResult emailAddressParsedResult2 = EmailAddressResultParser.parse(result);
        if (emailAddressParsedResult2 != null) {
            return emailAddressParsedResult2;
        }
        EmailAddressParsedResult emailAddressParsedResult3 = SMTPResultParser.parse(result);
        if (emailAddressParsedResult3 != null) {
            return emailAddressParsedResult3;
        }
        TelParsedResult telParsedResult = TelResultParser.parse(result);
        if (telParsedResult != null) {
            return telParsedResult;
        }
        SMSParsedResult sMSParsedResult = SMSMMSResultParser.parse(result);
        if (sMSParsedResult != null) {
            return sMSParsedResult;
        }
        SMSParsedResult sMSParsedResult2 = SMSTOMMSTOResultParser.parse(result);
        if (sMSParsedResult2 != null) {
            return sMSParsedResult2;
        }
        GeoParsedResult geoParsedResult = GeoResultParser.parse(result);
        if (geoParsedResult != null) {
            return geoParsedResult;
        }
        WifiParsedResult wifiParsedResult = WifiResultParser.parse(result);
        if (wifiParsedResult != null) {
            return wifiParsedResult;
        }
        URIParsedResult uRIParsedResult2 = URLTOResultParser.parse(result);
        if (uRIParsedResult2 != null) {
            return uRIParsedResult2;
        }
        URIParsedResult uRIParsedResult3 = URIResultParser.parse(result);
        if (uRIParsedResult3 != null) {
            return uRIParsedResult3;
        }
        ISBNParsedResult iSBNParsedResult = ISBNResultParser.parse(result);
        if (iSBNParsedResult != null) {
            return iSBNParsedResult;
        }
        ProductParsedResult productParsedResult = ProductResultParser.parse(result);
        if (productParsedResult != null) {
            return productParsedResult;
        }
        ExpandedProductParsedResult expandedProductParsedResult = ExpandedProductResultParser.parse(result);
        return expandedProductParsedResult != null ? expandedProductParsedResult : new TextParsedResult(result.getText(), null);
    }

    static String[] toStringArray(Vector vector) {
        int size = vector.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = (String) vector.elementAt(i);
        }
        return strArr;
    }

    protected static String unescapeBackslash(String str) {
        int iIndexOf;
        if (str == null || (iIndexOf = str.indexOf(92)) < 0) {
            return str;
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length - 1);
        stringBuffer.append(str.toCharArray(), 0, iIndexOf);
        boolean z = false;
        while (iIndexOf < length) {
            char cCharAt = str.charAt(iIndexOf);
            if (z || cCharAt != '\\') {
                stringBuffer.append(cCharAt);
                z = false;
            } else {
                z = true;
            }
            iIndexOf++;
        }
        return stringBuffer.toString();
    }

    private static String urlDecode(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int iFindFirstEscape = findFirstEscape(charArray);
        if (iFindFirstEscape < 0) {
            return str;
        }
        int length = charArray.length;
        int i = length - 2;
        StringBuffer stringBuffer = new StringBuffer(i);
        stringBuffer.append(charArray, 0, iFindFirstEscape);
        while (iFindFirstEscape < length) {
            char c = charArray[iFindFirstEscape];
            if (c != '%') {
                if (c == '+') {
                    c = ' ';
                }
            } else if (iFindFirstEscape >= i) {
                stringBuffer.append('%');
                iFindFirstEscape++;
            } else {
                int i2 = iFindFirstEscape + 1;
                int hexDigit = parseHexDigit(charArray[i2]);
                iFindFirstEscape = i2 + 1;
                int hexDigit2 = parseHexDigit(charArray[iFindFirstEscape]);
                if (hexDigit < 0 || hexDigit2 < 0) {
                    stringBuffer.append('%');
                    stringBuffer.append(charArray[iFindFirstEscape - 1]);
                    stringBuffer.append(charArray[iFindFirstEscape]);
                }
                c = (char) ((hexDigit << 4) + hexDigit2);
            }
            stringBuffer.append(c);
            iFindFirstEscape++;
        }
        return stringBuffer.toString();
    }
}
