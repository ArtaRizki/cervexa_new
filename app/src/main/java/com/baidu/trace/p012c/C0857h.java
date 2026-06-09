package com.baidu.trace.p012c;

import android.util.Log;
import com.baidu.trace.p012c.C0850a;
import java.text.SimpleDateFormat;

/* JADX INFO: renamed from: com.baidu.trace.c.h */
/* JADX INFO: loaded from: classes.dex */
public final class C0857h {

    /* JADX INFO: renamed from: a */
    private static final SimpleDateFormat f1758a = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-SSS");

    /* JADX INFO: renamed from: b */
    private static C0850a.AnonymousClass1 f1759b;

    /* JADX INFO: renamed from: c */
    private static C0850a.AnonymousClass1 f1760c;

    /* JADX INFO: renamed from: com.baidu.trace.c.h$1, reason: invalid class name */
    final /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a */
        static final /* synthetic */ int[] f1761a;

        static {
            int[] iArr = new int[a.values().length];
            f1761a = iArr;
            try {
                iArr[a.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1761a[a.INFO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1761a[a.WARNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1761a[a.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: renamed from: com.baidu.trace.c.h$a */
    public enum a {
        DEBUG(0),
        INFO(1),
        WARNING(2),
        ERROR(3);

        a(int i) {
        }
    }

    static {
        new SimpleDateFormat("yyyy-MM-dd");
        f1759b = null;
        f1760c = null;
    }

    /* JADX INFO: renamed from: a */
    public static void m1244a() {
    }

    /* JADX INFO: renamed from: a */
    public static void m1245a(String str, String str2) {
        int i = AnonymousClass1.f1761a[a.INFO.ordinal()];
        if (i == 1) {
            Log.d(str, str2);
        } else if (i == 2) {
            Log.i(str, str2);
        } else if (i == 3) {
            Log.w(str, str2);
        } else if (i == 4) {
            Log.e(str, str2);
        }
        "Error".equals(str);
    }

    /* JADX INFO: renamed from: b */
    public static void m1246b() {
    }
}
