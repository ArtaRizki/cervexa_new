package com.tencent.open.utils;

import android.content.Context;
import java.io.File;

/* JADX INFO: renamed from: com.tencent.open.utils.d */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public final class C2084d {

    /* JADX INFO: renamed from: a */
    private static Context f3270a;

    /* JADX INFO: renamed from: a */
    public static final Context m2215a() {
        Context context = f3270a;
        if (context == null) {
            return null;
        }
        return context;
    }

    /* JADX INFO: renamed from: a */
    public static final void m2216a(Context context) {
        f3270a = context;
    }

    /* JADX INFO: renamed from: b */
    public static final String m2217b() {
        return m2215a() == null ? "" : m2215a().getPackageName();
    }

    /* JADX INFO: renamed from: c */
    public static final File m2218c() {
        if (m2215a() == null) {
            return null;
        }
        return m2215a().getFilesDir();
    }
}
