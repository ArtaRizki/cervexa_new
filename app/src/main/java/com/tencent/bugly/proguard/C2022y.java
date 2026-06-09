package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import com.bumptech.glide.load.Key;
import com.tencent.bugly.crashreport.common.info.C1958a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.y */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C2022y {

    /* JADX INFO: renamed from: a */
    public static boolean f2909a = true;

    /* JADX INFO: renamed from: b */
    private static boolean f2910b = true;

    /* JADX INFO: renamed from: c */
    private static SimpleDateFormat f2911c = null;

    /* JADX INFO: renamed from: d */
    private static int f2912d = 30720;

    /* JADX INFO: renamed from: e */
    private static StringBuilder f2913e = null;

    /* JADX INFO: renamed from: f */
    private static StringBuilder f2914f = null;

    /* JADX INFO: renamed from: g */
    private static boolean f2915g = false;

    /* JADX INFO: renamed from: h */
    private static a f2916h = null;

    /* JADX INFO: renamed from: i */
    private static String f2917i = null;

    /* JADX INFO: renamed from: j */
    private static String f2918j = null;

    /* JADX INFO: renamed from: k */
    private static Context f2919k = null;

    /* JADX INFO: renamed from: l */
    private static String f2920l = null;

    /* JADX INFO: renamed from: m */
    private static boolean f2921m = false;

    /* JADX INFO: renamed from: n */
    private static boolean f2922n = false;

    /* JADX INFO: renamed from: o */
    private static ExecutorService f2923o;

    /* JADX INFO: renamed from: p */
    private static int f2924p;

    /* JADX INFO: renamed from: q */
    private static final Object f2925q = new Object();

    static {
        try {
            f2911c = new SimpleDateFormat("MM-dd HH:mm:ss");
        } catch (Throwable th) {
            C2021x.m1870b(th.getCause());
        }
    }

    /* JADX INFO: renamed from: a */
    public static synchronized void m1876a(Context context) {
        if (f2921m || context == null || !f2909a) {
            return;
        }
        try {
            f2923o = Executors.newSingleThreadExecutor();
            f2914f = new StringBuilder(0);
            f2913e = new StringBuilder(0);
            f2919k = context;
            C1958a c1958aM1471a = C1958a.m1471a(context);
            f2917i = c1958aM1471a.f2400d;
            c1958aM1471a.getClass();
            f2918j = "";
            f2920l = f2919k.getFilesDir().getPath() + "/buglylog_" + f2917i + "_" + f2918j + ".txt";
            f2924p = Process.myPid();
        } catch (Throwable unused) {
        }
        f2921m = true;
    }

    /* JADX INFO: renamed from: a */
    public static void m1875a(int i) {
        synchronized (f2925q) {
            f2912d = i;
            if (i < 0) {
                f2912d = 0;
            } else if (i > 30720) {
                f2912d = 30720;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1878a(String str, String str2, Throwable th) {
        if (th == null) {
            return;
        }
        String message = th.getMessage();
        if (message == null) {
            message = "";
        }
        m1877a(str, str2, message + '\n' + C2023z.m1920b(th));
    }

    /* JADX INFO: renamed from: a */
    public static synchronized void m1877a(final String str, final String str2, final String str3) {
        if (f2921m && f2909a) {
            try {
                f2923o.execute(new Runnable() { // from class: com.tencent.bugly.proguard.y.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        C2022y.m1882c(str, str2, str3);
                    }
                });
            } catch (Exception e) {
                C2021x.m1870b(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c */
    public static synchronized void m1882c(String str, String str2, String str3) {
        if (f2910b) {
            m1883d(str, str2, str3);
        } else {
            m1884e(str, str2, str3);
        }
    }

    /* JADX INFO: renamed from: d */
    private static synchronized void m1883d(String str, String str2, String str3) {
        String strM1874a = m1874a(str, str2, str3, Process.myTid());
        synchronized (f2925q) {
            try {
                f2914f.append(strM1874a);
                if (f2914f.length() >= f2912d) {
                    f2914f = f2914f.delete(0, f2914f.indexOf("\u0001\r\n") + 1);
                }
            } finally {
            }
        }
    }

    /* JADX INFO: renamed from: e */
    private static synchronized void m1884e(String str, String str2, String str3) {
        String strM1874a = m1874a(str, str2, str3, Process.myTid());
        synchronized (f2925q) {
            try {
                f2914f.append(strM1874a);
                if (f2914f.length() <= f2912d) {
                    return;
                }
                if (f2915g) {
                    return;
                }
                f2915g = true;
                if (f2916h == null) {
                    f2916h = new a(f2920l);
                } else if (f2916h.f2930b == null || f2916h.f2930b.length() + ((long) f2914f.length()) > f2916h.f2933e) {
                    f2916h.m1886a();
                }
                if (f2916h.m1890a(f2914f.toString())) {
                    f2914f.setLength(0);
                    f2915g = false;
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static String m1874a(String str, String str2, String str3, long j) {
        String string;
        f2913e.setLength(0);
        if (str3.length() > 30720) {
            str3 = str3.substring(str3.length() - 30720, str3.length() - 1);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = f2911c;
        if (simpleDateFormat != null) {
            string = simpleDateFormat.format(date);
        } else {
            string = date.toString();
        }
        StringBuilder sb = f2913e;
        sb.append(string);
        sb.append(" ");
        sb.append(f2924p);
        sb.append(" ");
        sb.append(j);
        sb.append(" ");
        sb.append(str);
        sb.append(" ");
        sb.append(str2);
        sb.append(": ");
        sb.append(str3);
        sb.append("\u0001\r\n");
        return f2913e.toString();
    }

    /* JADX INFO: renamed from: a */
    public static byte[] m1879a() {
        if (f2910b) {
            if (f2909a) {
                return C2023z.m1916a((File) null, f2914f.toString(), "BuglyLog.txt");
            }
            return null;
        }
        return m1881b();
    }

    /* JADX INFO: renamed from: b */
    private static byte[] m1881b() {
        if (!f2909a) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        synchronized (f2925q) {
            if (f2916h != null && f2916h.f2929a && f2916h.f2930b != null && f2916h.f2930b.length() > 0) {
                sb.append(C2023z.m1901a(f2916h.f2930b, 30720, true));
            }
            if (f2914f != null && f2914f.length() > 0) {
                sb.append(f2914f.toString());
            }
        }
        return C2023z.m1916a((File) null, sb.toString(), "BuglyLog.txt");
    }

    /* JADX INFO: renamed from: com.tencent.bugly.proguard.y$a */
    /* JADX INFO: compiled from: BUGLY */
    public static class a {

        /* JADX INFO: renamed from: a */
        private boolean f2929a;

        /* JADX INFO: renamed from: b */
        private File f2930b;

        /* JADX INFO: renamed from: c */
        private String f2931c;

        /* JADX INFO: renamed from: d */
        private long f2932d;

        /* JADX INFO: renamed from: e */
        private long f2933e = 30720;

        public a(String str) {
            if (str == null || str.equals("")) {
                return;
            }
            this.f2931c = str;
            this.f2929a = m1886a();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a */
        public boolean m1886a() {
            try {
                File file = new File(this.f2931c);
                this.f2930b = file;
                if (file.exists() && !this.f2930b.delete()) {
                    this.f2929a = false;
                    return false;
                }
                if (this.f2930b.createNewFile()) {
                    return true;
                }
                this.f2929a = false;
                return false;
            } catch (Throwable th) {
                C2021x.m1867a(th);
                this.f2929a = false;
                return false;
            }
        }

        /* JADX INFO: renamed from: a */
        public final boolean m1890a(String str) {
            FileOutputStream fileOutputStream;
            if (!this.f2929a) {
                return false;
            }
            FileOutputStream fileOutputStream2 = null;
            try {
                fileOutputStream = new FileOutputStream(this.f2930b, true);
            } catch (Throwable th) {
                th = th;
            }
            try {
                byte[] bytes = str.getBytes(Key.STRING_CHARSET_NAME);
                fileOutputStream.write(bytes);
                fileOutputStream.flush();
                fileOutputStream.close();
                this.f2932d += (long) bytes.length;
                this.f2929a = true;
                try {
                    fileOutputStream.close();
                } catch (IOException unused) {
                }
                return true;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                try {
                    C2021x.m1867a(th);
                    this.f2929a = false;
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException unused2) {
                        }
                    }
                    return false;
                } catch (Throwable th3) {
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException unused3) {
                        }
                    }
                    throw th3;
                }
            }
        }
    }
}
