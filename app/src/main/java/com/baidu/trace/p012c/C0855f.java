package com.baidu.trace.p012c;

import android.os.Build;
import com.jieli.stream.p016dv.running2.util.IConstant;

/* JADX INFO: renamed from: com.baidu.trace.c.f */
/* JADX INFO: loaded from: classes.dex */
public final class C0855f {

    /* JADX INFO: renamed from: a */
    public static final String f1748a = Build.BRAND + "-" + Build.MODEL;

    /* JADX INFO: renamed from: b */
    public static final String f1749b = Build.CPU_ABI;

    /* JADX INFO: renamed from: c */
    public static final String f1750c;

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: renamed from: com.baidu.trace.c.f$a */
    public static final class a {

        /* JADX INFO: renamed from: a */
        public static final a f1751a = new a("CONNECT", 0);

        /* JADX INFO: renamed from: b */
        public static final a f1752b = new a("ENTER", 1);

        private a(String str, int i) {
        }
    }

    /* JADX INFO: renamed from: com.baidu.trace.c.f$b */
    public enum b {
        NOT_START,
        LOGGING,
        STARTED,
        LOGOUTING
    }

    static {
        StringBuilder sb = new StringBuilder(IConstant.ANDROID_DIR);
        sb.append(Build.VERSION.RELEASE);
        f1750c = sb.toString();
    }
}
