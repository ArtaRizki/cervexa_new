package com.google.zxing.client.result;

import com.google.zxing.Result;

/* JADX INFO: loaded from: classes.dex */
final class VEventResultParser extends ResultParser {
    private VEventResultParser() {
    }

    public static CalendarParsedResult parse(Result result) {
        double d;
        String text = result.getText();
        if (text == null || text.indexOf("BEGIN:VEVENT") < 0) {
            return null;
        }
        String strMatchSingleVCardPrefixedField = VCardResultParser.matchSingleVCardPrefixedField("SUMMARY", text, true);
        String strMatchSingleVCardPrefixedField2 = VCardResultParser.matchSingleVCardPrefixedField("DTSTART", text, true);
        String strMatchSingleVCardPrefixedField3 = VCardResultParser.matchSingleVCardPrefixedField("DTEND", text, true);
        String strMatchSingleVCardPrefixedField4 = VCardResultParser.matchSingleVCardPrefixedField("LOCATION", text, true);
        String strMatchSingleVCardPrefixedField5 = VCardResultParser.matchSingleVCardPrefixedField("DESCRIPTION", text, true);
        String strMatchSingleVCardPrefixedField6 = VCardResultParser.matchSingleVCardPrefixedField("GEO", text, true);
        double d2 = Double.NaN;
        if (strMatchSingleVCardPrefixedField6 == null) {
            d = Double.NaN;
        } else {
            int iIndexOf = strMatchSingleVCardPrefixedField6.indexOf(59);
            try {
                d2 = Double.parseDouble(strMatchSingleVCardPrefixedField6.substring(0, iIndexOf));
                d = Double.parseDouble(strMatchSingleVCardPrefixedField6.substring(iIndexOf + 1));
            } catch (NumberFormatException | IllegalArgumentException unused) {
                return null;
            }
        }
        return new CalendarParsedResult(strMatchSingleVCardPrefixedField, strMatchSingleVCardPrefixedField2, strMatchSingleVCardPrefixedField3, strMatchSingleVCardPrefixedField4, null, strMatchSingleVCardPrefixedField5, d2, d);
    }
}
