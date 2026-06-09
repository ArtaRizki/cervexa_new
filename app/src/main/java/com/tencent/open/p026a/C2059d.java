package com.tencent.open.p026a;

import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import java.text.SimpleDateFormat;

/* JADX INFO: renamed from: com.tencent.open.a.d */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2059d {

    /* JADX INFO: renamed from: com.tencent.open.a.d$a */
    /* JADX INFO: compiled from: ProGuard */
    public static final class a {
        /* JADX INFO: renamed from: a */
        public static final boolean m2115a(int i, int i2) {
            return i2 == (i & i2);
        }
    }

    /* JADX INFO: renamed from: com.tencent.open.a.d$d */
    /* JADX INFO: compiled from: ProGuard */
    public static final class d {
        /* JADX INFO: renamed from: a */
        public static SimpleDateFormat m2125a(String str) {
            return new SimpleDateFormat(str);
        }
    }

    /* JADX INFO: renamed from: com.tencent.open.a.d$b */
    /* JADX INFO: compiled from: ProGuard */
    public static final class b {
        /* JADX INFO: renamed from: a */
        public static boolean m2116a() {
            String externalStorageState = Environment.getExternalStorageState();
            return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
        }

        /* JADX INFO: renamed from: b */
        public static c m2117b() {
            if (m2116a()) {
                return c.m2118b(Environment.getExternalStorageDirectory());
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: com.tencent.open.a.d$c */
    /* JADX INFO: compiled from: ProGuard */
    public static class c {

        /* JADX INFO: renamed from: a */
        private File f3169a;

        /* JADX INFO: renamed from: b */
        private long f3170b;

        /* JADX INFO: renamed from: c */
        private long f3171c;

        /* JADX INFO: renamed from: a */
        public File m2119a() {
            return this.f3169a;
        }

        /* JADX INFO: renamed from: a */
        public void m2121a(File file) {
            this.f3169a = file;
        }

        /* JADX INFO: renamed from: b */
        public long m2122b() {
            return this.f3170b;
        }

        /* JADX INFO: renamed from: a */
        public void m2120a(long j) {
            this.f3170b = j;
        }

        /* JADX INFO: renamed from: c */
        public long m2124c() {
            return this.f3171c;
        }

        /* JADX INFO: renamed from: b */
        public void m2123b(long j) {
            this.f3171c = j;
        }

        /* JADX INFO: renamed from: b */
        public static c m2118b(File file) {
            c cVar = new c();
            cVar.m2121a(file);
            StatFs statFs = new StatFs(file.getAbsolutePath());
            long blockSize = statFs.getBlockSize();
            long blockCount = statFs.getBlockCount();
            long availableBlocks = statFs.getAvailableBlocks();
            cVar.m2120a(blockCount * blockSize);
            cVar.m2123b(availableBlocks * blockSize);
            return cVar;
        }

        public String toString() {
            return String.format("[%s : %d / %d]", m2119a().getAbsolutePath(), Long.valueOf(m2124c()), Long.valueOf(m2122b()));
        }
    }
}
