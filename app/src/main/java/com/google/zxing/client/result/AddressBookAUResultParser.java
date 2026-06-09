package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Vector;
import org.apache.commons.net.SocketClient;

/* JADX INFO: loaded from: classes.dex */
final class AddressBookAUResultParser extends ResultParser {
    AddressBookAUResultParser() {
    }

    private static String[] matchMultipleValuePrefix(String str, int i, String str2, boolean z) {
        Vector vector = null;
        for (int i2 = 1; i2 <= i; i2++) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append(i2);
            stringBuffer.append(':');
            String strMatchSinglePrefixedField = matchSinglePrefixedField(stringBuffer.toString(), str2, '\r', z);
            if (strMatchSinglePrefixedField == null) {
                break;
            }
            if (vector == null) {
                vector = new Vector(i);
            }
            vector.addElement(strMatchSinglePrefixedField);
        }
        if (vector == null) {
            return null;
        }
        return toStringArray(vector);
    }

    public static AddressBookParsedResult parse(Result result) {
        String text = result.getText();
        if (text == null || text.indexOf("MEMORY") < 0 || text.indexOf(SocketClient.NETASCII_EOL) < 0) {
            return null;
        }
        String strMatchSinglePrefixedField = matchSinglePrefixedField("NAME1:", text, '\r', true);
        String strMatchSinglePrefixedField2 = matchSinglePrefixedField("NAME2:", text, '\r', true);
        String[] strArrMatchMultipleValuePrefix = matchMultipleValuePrefix("TEL", 3, text, true);
        String[] strArrMatchMultipleValuePrefix2 = matchMultipleValuePrefix("MAIL", 3, text, true);
        String strMatchSinglePrefixedField3 = matchSinglePrefixedField("MEMORY:", text, '\r', false);
        String strMatchSinglePrefixedField4 = matchSinglePrefixedField("ADD:", text, '\r', true);
        return new AddressBookParsedResult(maybeWrap(strMatchSinglePrefixedField), strMatchSinglePrefixedField2, strArrMatchMultipleValuePrefix, strArrMatchMultipleValuePrefix2, strMatchSinglePrefixedField3, strMatchSinglePrefixedField4 != null ? new String[]{strMatchSinglePrefixedField4} : null, null, null, null, null);
    }
}
