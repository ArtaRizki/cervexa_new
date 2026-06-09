package com.jieli.stream.p016dv.running2.p017ui.widget.pullrefreshview.support.utils;

/* JADX INFO: loaded from: classes.dex */
public class Utils {
    public static final boolean isClassExists(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
