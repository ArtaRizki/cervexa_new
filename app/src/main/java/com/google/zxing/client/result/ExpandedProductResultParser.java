package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.tencent.connect.common.Constants;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
final class ExpandedProductResultParser extends ResultParser {
    private ExpandedProductResultParser() {
    }

    private static String findAIvalue(int i, String str) {
        char cCharAt;
        StringBuffer stringBuffer = new StringBuffer();
        if (str.charAt(i) != '(') {
            return "ERROR";
        }
        String strSubstring = str.substring(i + 1);
        for (int i2 = 0; i2 < strSubstring.length() && (cCharAt = strSubstring.charAt(i2)) != ')'; i2++) {
            switch (cCharAt) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    stringBuffer.append(cCharAt);
                    break;
                default:
                    return "ERROR";
            }
        }
        return stringBuffer.toString();
    }

    private static String findValue(int i, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        String strSubstring = str.substring(i);
        for (int i2 = 0; i2 < strSubstring.length(); i2++) {
            char cCharAt = strSubstring.charAt(i2);
            if (cCharAt != '(') {
                stringBuffer.append(cCharAt);
            } else {
                if (!"ERROR".equals(findAIvalue(i2, strSubstring))) {
                    break;
                }
                stringBuffer.append('(');
            }
        }
        return stringBuffer.toString();
    }

    public static ExpandedProductParsedResult parse(Result result) {
        String text;
        int i;
        if (!BarcodeFormat.RSS_EXPANDED.equals(result.getBarcodeFormat()) || (text = result.getText()) == null) {
            return null;
        }
        Hashtable hashtable = new Hashtable();
        String str = "-";
        String str2 = "-";
        String str3 = str2;
        String str4 = str3;
        String str5 = str4;
        String str6 = str5;
        String str7 = str6;
        String str8 = str7;
        String str9 = str8;
        String strSubstring = str9;
        String strSubstring2 = strSubstring;
        String strSubstring3 = strSubstring2;
        String strSubstring4 = strSubstring3;
        int i2 = 0;
        while (i2 < text.length()) {
            String strFindAIvalue = findAIvalue(i2, text);
            String str10 = strSubstring3;
            if ("ERROR".equals(strFindAIvalue)) {
                return null;
            }
            int length = i2 + strFindAIvalue.length() + 2;
            String strFindValue = findValue(length, text);
            int length2 = length + strFindValue.length();
            String str11 = text;
            if ("00".equals(strFindAIvalue)) {
                i = length2;
                str2 = strFindValue;
            } else if ("01".equals(strFindAIvalue)) {
                i = length2;
                str = strFindValue;
            } else if (Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(strFindAIvalue)) {
                i = length2;
                str3 = strFindValue;
            } else if (Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE.equals(strFindAIvalue)) {
                i = length2;
                str4 = strFindValue;
            } else if (Constants.VIA_REPORT_TYPE_JOININ_GROUP.equals(strFindAIvalue)) {
                i = length2;
                str5 = strFindValue;
            } else if (Constants.VIA_REPORT_TYPE_WPA_STATE.equals(strFindAIvalue)) {
                i = length2;
                str6 = strFindValue;
            } else if (Constants.VIA_REPORT_TYPE_START_GROUP.equals(strFindAIvalue)) {
                i = length2;
                str7 = strFindValue;
            } else {
                i = length2;
                if ("3100".equals(strFindAIvalue) || "3101".equals(strFindAIvalue) || "3102".equals(strFindAIvalue) || "3103".equals(strFindAIvalue) || "3104".equals(strFindAIvalue) || "3105".equals(strFindAIvalue) || "3106".equals(strFindAIvalue) || "3107".equals(strFindAIvalue) || "3108".equals(strFindAIvalue) || "3109".equals(strFindAIvalue)) {
                    strSubstring = strFindAIvalue.substring(3);
                    str9 = ExpandedProductParsedResult.KILOGRAM;
                } else if ("3200".equals(strFindAIvalue) || "3201".equals(strFindAIvalue) || "3202".equals(strFindAIvalue) || "3203".equals(strFindAIvalue) || "3204".equals(strFindAIvalue) || "3205".equals(strFindAIvalue) || "3206".equals(strFindAIvalue) || "3207".equals(strFindAIvalue) || "3208".equals(strFindAIvalue) || "3209".equals(strFindAIvalue)) {
                    strSubstring = strFindAIvalue.substring(3);
                    str9 = ExpandedProductParsedResult.POUND;
                } else {
                    if ("3920".equals(strFindAIvalue) || "3921".equals(strFindAIvalue) || "3922".equals(strFindAIvalue) || "3923".equals(strFindAIvalue)) {
                        strSubstring2 = strFindValue;
                        strSubstring3 = strFindAIvalue.substring(3);
                    } else if (!"3930".equals(strFindAIvalue) && !"3931".equals(strFindAIvalue) && !"3932".equals(strFindAIvalue) && !"3933".equals(strFindAIvalue)) {
                        hashtable.put(strFindAIvalue, strFindValue);
                    } else {
                        if (strFindValue.length() < 4) {
                            return null;
                        }
                        strSubstring2 = strFindValue.substring(3);
                        strSubstring4 = strFindValue.substring(0, 3);
                        strSubstring3 = strFindAIvalue.substring(3);
                    }
                    text = str11;
                    i2 = i;
                }
                str8 = strFindValue;
                strSubstring3 = str10;
                text = str11;
                i2 = i;
            }
            strSubstring3 = str10;
            text = str11;
            i2 = i;
        }
        return new ExpandedProductParsedResult(str, str2, str3, str4, str5, str6, str7, str8, str9, strSubstring, strSubstring2, strSubstring3, strSubstring4, hashtable);
    }
}
