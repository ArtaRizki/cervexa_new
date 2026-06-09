package com.baidu.trace;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: renamed from: com.baidu.trace.av */
/* JADX INFO: loaded from: classes.dex */
public class C0823av {

    /* JADX INFO: renamed from: a */
    private static Context f1604a;

    /* JADX INFO: renamed from: d */
    private static C0823av f1607d;

    /* JADX INFO: renamed from: b */
    private static final Set<String> f1605b = new HashSet();

    /* JADX INFO: renamed from: c */
    private static final Set<String> f1606c = new HashSet();

    /* JADX INFO: renamed from: e */
    private static a f1608e = a.ARMEABI;

    /* JADX INFO: renamed from: com.baidu.trace.av$1, reason: invalid class name */
    final /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a */
        static final /* synthetic */ int[] f1609a;

        static {
            int[] iArr = new int[a.values().length];
            f1609a = iArr;
            try {
                iArr[a.ARM64.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1609a[a.ARMV7.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1609a[a.ARMEABI.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1609a[a.X86_64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1609a[a.X86.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: renamed from: com.baidu.trace.av$a */
    enum a {
        ARMEABI("armeabi"),
        ARMV7("armeabi-v7a"),
        ARM64("arm64-v8a"),
        X86("x86"),
        X86_64("x86_64");


        /* JADX INFO: renamed from: f */
        private String f1616f;

        a(String str) {
            this.f1616f = str;
        }

        /* JADX INFO: renamed from: b */
        public final String m1107b() {
            return this.f1616f;
        }
    }

    private C0823av() {
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C0823av m1095a() {
        a aVar;
        if (f1607d == null) {
            f1607d = new C0823av();
            String str = Build.VERSION.SDK_INT < 21 ? Build.CPU_ABI : Build.SUPPORTED_ABIS[0];
            if (str == null) {
                aVar = a.ARMEABI;
            } else {
                if (str.contains("arm") && str.contains("v7")) {
                    f1608e = a.ARMV7;
                }
                if (str.contains("arm") && str.contains("64")) {
                    f1608e = a.ARM64;
                }
                if (str.contains("x86")) {
                    f1608e = str.contains("64") ? a.X86_64 : a.X86;
                }
                aVar = f1608e;
            }
            f1608e = aVar;
        }
        return f1607d;
    }

    /* JADX INFO: renamed from: a */
    public static void m1096a(Context context) {
        if (f1604a == null) {
            f1604a = context;
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m1097a(InputStream inputStream, FileOutputStream fileOutputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    fileOutputStream.flush();
                    try {
                        inputStream.close();
                        fileOutputStream.close();
                        return;
                    } catch (IOException unused) {
                        return;
                    }
                }
                fileOutputStream.write(bArr, 0, i);
            } catch (Throwable th) {
                try {
                    inputStream.close();
                    fileOutputStream.close();
                    throw th;
                } catch (IOException unused2) {
                    return;
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m1098a(Throwable th) {
        Log.e(C0823av.class.getSimpleName(), "loadException", th);
        for (String str : f1606c) {
            Log.e(C0823av.class.getSimpleName(), str + " Failed to load.");
        }
    }

    /* JADX INFO: renamed from: a */
    private boolean m1099a(String str, a aVar) throws Throwable {
        StringBuilder sb = new StringBuilder();
        sb.append("lib/" + aVar.m1107b() + "/");
        sb.append(str);
        String string = sb.toString();
        ZipFile zipFile = null;
        try {
            try {
                ZipFile zipFile2 = new ZipFile(8 <= Build.VERSION.SDK_INT ? f1604a.getPackageCodePath() : "");
                try {
                    File file = new File(m1101b(), str);
                    ZipEntry entry = zipFile2.getEntry(string);
                    if (entry == null) {
                        try {
                            zipFile2.close();
                        } catch (IOException unused) {
                        }
                        return false;
                    }
                    m1097a(zipFile2.getInputStream(entry), new FileOutputStream(file));
                    try {
                        zipFile2.close();
                        return true;
                    } catch (IOException unused2) {
                        return false;
                    }
                } catch (Exception e) {
                    e = e;
                    zipFile = zipFile2;
                    Log.e(C0823av.class.getSimpleName(), "copyError", e);
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException unused3) {
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    zipFile = zipFile2;
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException unused4) {
                            return false;
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    /* JADX INFO: renamed from: a */
    private boolean m1100a(String str, String str2) {
        return !m1099a(str2, a.ARMV7) ? m1102b(str, str2) : m1104d(str2, str);
    }

    /* JADX INFO: renamed from: b */
    private static String m1101b() {
        File file = new File(f1604a.getFilesDir(), "libs");
        file.mkdirs();
        return file.getAbsolutePath();
    }

    /* JADX INFO: renamed from: b */
    private boolean m1102b(String str, String str2) {
        if (m1099a(str2, a.ARMEABI)) {
            return m1104d(str2, str);
        }
        return false;
    }

    /* JADX INFO: renamed from: c */
    private boolean m1103c(String str, String str2) {
        return !m1099a(str2, a.X86) ? m1100a(str, str2) : m1104d(str2, str);
    }

    /* JADX INFO: renamed from: d */
    private boolean m1104d(String str, String str2) {
        try {
            System.load(new File(m1101b(), str).getAbsolutePath());
            synchronized (f1605b) {
                f1605b.add(str2);
            }
            return true;
        } catch (Throwable th) {
            synchronized (f1606c) {
                f1606c.add(str2);
                m1098a(th);
                return false;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0055 A[Catch: all -> 0x0074, TryCatch #1 {, blocks: (B:24:0x0025, B:35:0x0043, B:36:0x0048, B:38:0x0050, B:39:0x0055, B:41:0x005b, B:42:0x0060, B:43:0x0065, B:45:0x006d), top: B:52:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0065 A[Catch: all -> 0x0074, TryCatch #1 {, blocks: (B:24:0x0025, B:35:0x0043, B:36:0x0048, B:38:0x0050, B:39:0x0055, B:41:0x005b, B:42:0x0060, B:43:0x0065, B:45:0x006d), top: B:52:0x0005 }] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized boolean m1105a(java.lang.String r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 1
            java.util.Set<java.lang.String> r1 = com.baidu.trace.C0823av.f1605b     // Catch: java.lang.Throwable -> L25
            monitor-enter(r1)     // Catch: java.lang.Throwable -> L25
            java.util.Set<java.lang.String> r2 = com.baidu.trace.C0823av.f1605b     // Catch: java.lang.Throwable -> L22
            boolean r2 = r2.contains(r6)     // Catch: java.lang.Throwable -> L22
            if (r2 == 0) goto L10
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L22
            monitor-exit(r5)
            return r0
        L10:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L25
            java.lang.System.loadLibrary(r6)     // Catch: java.lang.Throwable -> L25
            java.util.Set<java.lang.String> r1 = com.baidu.trace.C0823av.f1605b     // Catch: java.lang.Throwable -> L25
            monitor-enter(r1)     // Catch: java.lang.Throwable -> L25
            java.util.Set<java.lang.String> r2 = com.baidu.trace.C0823av.f1605b     // Catch: java.lang.Throwable -> L1f
            r2.add(r6)     // Catch: java.lang.Throwable -> L1f
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L1f
            monitor-exit(r5)
            return r0
        L1f:
            r2 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L25
            throw r2     // Catch: java.lang.Throwable -> L25
        L22:
            r2 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L25
            throw r2     // Catch: java.lang.Throwable -> L25
        L25:
            java.lang.String r1 = java.lang.System.mapLibraryName(r6)     // Catch: java.lang.Throwable -> L74
            r2 = 0
            int[] r3 = com.baidu.trace.C0823av.AnonymousClass1.f1609a     // Catch: java.lang.Throwable -> L74
            com.baidu.trace.av$a r4 = com.baidu.trace.C0823av.f1608e     // Catch: java.lang.Throwable -> L74
            int r4 = r4.ordinal()     // Catch: java.lang.Throwable -> L74
            r3 = r3[r4]     // Catch: java.lang.Throwable -> L74
            if (r3 == r0) goto L65
            r0 = 2
            if (r3 == r0) goto L60
            r0 = 3
            if (r3 == r0) goto L5b
            r0 = 4
            if (r3 == r0) goto L48
            r0 = 5
            if (r3 == r0) goto L43
            goto L72
        L43:
            boolean r2 = r5.m1103c(r6, r1)     // Catch: java.lang.Throwable -> L74
            goto L72
        L48:
            com.baidu.trace.av$a r0 = com.baidu.trace.C0823av.a.X86_64     // Catch: java.lang.Throwable -> L74
            boolean r0 = r5.m1099a(r1, r0)     // Catch: java.lang.Throwable -> L74
            if (r0 != 0) goto L55
            boolean r6 = r5.m1103c(r6, r1)     // Catch: java.lang.Throwable -> L74
            goto L59
        L55:
            boolean r6 = r5.m1104d(r1, r6)     // Catch: java.lang.Throwable -> L74
        L59:
            r2 = r6
            goto L72
        L5b:
            boolean r2 = r5.m1102b(r6, r1)     // Catch: java.lang.Throwable -> L74
            goto L72
        L60:
            boolean r2 = r5.m1100a(r6, r1)     // Catch: java.lang.Throwable -> L74
            goto L72
        L65:
            com.baidu.trace.av$a r0 = com.baidu.trace.C0823av.a.ARM64     // Catch: java.lang.Throwable -> L74
            boolean r0 = r5.m1099a(r1, r0)     // Catch: java.lang.Throwable -> L74
            if (r0 != 0) goto L55
            boolean r6 = r5.m1100a(r6, r1)     // Catch: java.lang.Throwable -> L74
            goto L59
        L72:
            monitor-exit(r5)
            return r2
        L74:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.trace.C0823av.m1105a(java.lang.String):boolean");
    }
}
