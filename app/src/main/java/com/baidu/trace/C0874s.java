package com.baidu.trace;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: renamed from: com.baidu.trace.s */
/* JADX INFO: loaded from: classes.dex */
public final class C0874s {

    /* JADX INFO: renamed from: a */
    private SharedPreferences f1852a;

    /* JADX INFO: renamed from: b */
    private Map<String, String> f1853b = new HashMap();

    /* JADX INFO: renamed from: c */
    private Set<String> f1854c = new HashSet();

    public C0874s(Context context) {
        this.f1852a = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /* JADX INFO: renamed from: a */
    public final String m1274a(String str) {
        if (!this.f1853b.containsKey(str)) {
            this.f1853b.put(str, this.f1852a.getString(str, null));
        }
        return this.f1853b.get(str);
    }

    /* JADX INFO: renamed from: a */
    public final void m1275a() {
        SharedPreferences.Editor editorEdit = this.f1852a.edit();
        for (String str : this.f1854c) {
            if (this.f1853b.get(str) == null) {
                editorEdit.remove(str);
            } else {
                editorEdit.putString(str, this.f1853b.get(str));
            }
        }
        editorEdit.apply();
        this.f1854c.clear();
    }

    /* JADX INFO: renamed from: a */
    public final void m1276a(String str, String str2) {
        this.f1853b.put(str, str2);
        if (this.f1854c.contains(str)) {
            return;
        }
        this.f1854c.add(str);
    }

    /* JADX INFO: renamed from: b */
    protected final void m1277b() {
        Map<String, String> map = this.f1853b;
        if (map != null) {
            map.clear();
            this.f1853b = null;
        }
        Set<String> set = this.f1854c;
        if (set != null) {
            set.clear();
            this.f1854c = null;
        }
    }
}
