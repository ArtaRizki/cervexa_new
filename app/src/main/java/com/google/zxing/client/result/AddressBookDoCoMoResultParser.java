package com.google.zxing.client.result;

import com.google.zxing.Result;

/* JADX INFO: loaded from: classes.dex */
final class AddressBookDoCoMoResultParser extends AbstractDoCoMoResultParser {
    AddressBookDoCoMoResultParser() {
    }

    public static AddressBookParsedResult parse(Result result) {
        String[] strArrMatchDoCoMoPrefixedField;
        String text = result.getText();
        if (text == null || !text.startsWith("MECARD:") || (strArrMatchDoCoMoPrefixedField = matchDoCoMoPrefixedField("N:", text, true)) == null) {
            return null;
        }
        String name = parseName(strArrMatchDoCoMoPrefixedField[0]);
        String strMatchSingleDoCoMoPrefixedField = matchSingleDoCoMoPrefixedField("SOUND:", text, true);
        String[] strArrMatchDoCoMoPrefixedField2 = matchDoCoMoPrefixedField("TEL:", text, true);
        String[] strArrMatchDoCoMoPrefixedField3 = matchDoCoMoPrefixedField("EMAIL:", text, true);
        String strMatchSingleDoCoMoPrefixedField2 = matchSingleDoCoMoPrefixedField("NOTE:", text, false);
        String[] strArrMatchDoCoMoPrefixedField4 = matchDoCoMoPrefixedField("ADR:", text, true);
        String strMatchSingleDoCoMoPrefixedField3 = matchSingleDoCoMoPrefixedField("BDAY:", text, true);
        return new AddressBookParsedResult(maybeWrap(name), strMatchSingleDoCoMoPrefixedField, strArrMatchDoCoMoPrefixedField2, strArrMatchDoCoMoPrefixedField3, strMatchSingleDoCoMoPrefixedField2, strArrMatchDoCoMoPrefixedField4, matchSingleDoCoMoPrefixedField("ORG:", text, true), (strMatchSingleDoCoMoPrefixedField3 == null || isStringOfDigits(strMatchSingleDoCoMoPrefixedField3, 8)) ? strMatchSingleDoCoMoPrefixedField3 : null, null, matchSingleDoCoMoPrefixedField("URL:", text, true));
    }

    private static String parseName(String str) {
        int iIndexOf = str.indexOf(44);
        if (iIndexOf < 0) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str.substring(iIndexOf + 1));
        stringBuffer.append(' ');
        stringBuffer.append(str.substring(0, iIndexOf));
        return stringBuffer.toString();
    }
}
