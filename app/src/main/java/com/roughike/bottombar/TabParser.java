package com.roughike.bottombar;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import androidx.core.content.ContextCompat;
import com.roughike.bottombar.BottomBarTab;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
class TabParser {
    private static final int AVG_NUMBER_OF_TABS = 5;
    private static final int COLOR_NOT_SET = -1;
    private static final int RESOURCE_NOT_FOUND = 0;
    private static final String TAB_TAG = "tab";
    private final Context context;
    private final BottomBarTab.Config defaultTabConfig;
    private final XmlResourceParser parser;
    private List<BottomBarTab> tabs = null;

    public static class TabParserException extends RuntimeException {
    }

    TabParser(Context context, BottomBarTab.Config config, int i) {
        this.context = context;
        this.defaultTabConfig = config;
        this.parser = context.getResources().getXml(i);
    }

    public List<BottomBarTab> parseTabs() {
        int next;
        if (this.tabs == null) {
            this.tabs = new ArrayList(5);
            do {
                try {
                    next = this.parser.next();
                    if (next == 2 && TAB_TAG.equals(this.parser.getName())) {
                        this.tabs.add(parseNewTab(this.parser, this.tabs.size()));
                    }
                } catch (IOException | XmlPullParserException e) {
                    e.printStackTrace();
                    throw new TabParserException();
                }
            } while (next != 1);
        }
        return this.tabs;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.roughike.bottombar.BottomBarTab parseNewTab(android.content.res.XmlResourceParser r8, int r9) {
        /*
            Method dump skipped, instruction units count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.roughike.bottombar.TabParser.parseNewTab(android.content.res.XmlResourceParser, int):com.roughike.bottombar.BottomBarTab");
    }

    private BottomBarTab tabWithDefaults() {
        BottomBarTab bottomBarTab = new BottomBarTab(this.context);
        bottomBarTab.setConfig(this.defaultTabConfig);
        return bottomBarTab;
    }

    private String getTitleValue(XmlResourceParser xmlResourceParser, int i) {
        int attributeResourceValue = xmlResourceParser.getAttributeResourceValue(i, 0);
        return attributeResourceValue == 0 ? xmlResourceParser.getAttributeValue(i) : this.context.getString(attributeResourceValue);
    }

    private int getColorValue(XmlResourceParser xmlResourceParser, int i) {
        int attributeResourceValue = xmlResourceParser.getAttributeResourceValue(i, 0);
        if (attributeResourceValue == 0) {
            try {
                return Color.parseColor(xmlResourceParser.getAttributeValue(i));
            } catch (Exception unused) {
                return -1;
            }
        }
        return ContextCompat.getColor(this.context, attributeResourceValue);
    }
}
