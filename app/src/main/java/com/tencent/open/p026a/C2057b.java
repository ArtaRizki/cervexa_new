package com.tencent.open.p026a;

import com.tencent.open.p026a.C2059d;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: renamed from: com.tencent.open.a.b */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2057b {

    /* JADX INFO: renamed from: a */
    private static SimpleDateFormat f3145a = C2059d.d.m2125a("yy.MM.dd.HH");

    /* JADX INFO: renamed from: g */
    private File f3151g;

    /* JADX INFO: renamed from: b */
    private String f3146b = "Tracer.File";

    /* JADX INFO: renamed from: c */
    private int f3147c = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: d */
    private int f3148d = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: e */
    private int f3149e = 4096;

    /* JADX INFO: renamed from: f */
    private long f3150f = 10000;

    /* JADX INFO: renamed from: h */
    private int f3152h = 10;

    /* JADX INFO: renamed from: i */
    private String f3153i = ".log";

    /* JADX INFO: renamed from: j */
    private long f3154j = LongCompanionObject.MAX_VALUE;

    public C2057b(File file, int i, int i2, int i3, String str, long j, int i4, String str2, long j2) {
        m2103a(file);
        m2106b(i);
        m2101a(i2);
        m2110c(i3);
        m2104a(str);
        m2102a(j);
        m2112d(i4);
        m2108b(str2);
        m2107b(j2);
    }

    /* JADX INFO: renamed from: a */
    public File m2100a() {
        return m2097c(System.currentTimeMillis());
    }

    /* JADX INFO: renamed from: c */
    private File m2097c(long j) {
        File fileM2105b = m2105b();
        try {
            return new File(fileM2105b, m2098c(m2099d(j)));
        } catch (Throwable th) {
            th.printStackTrace();
            return fileM2105b;
        }
    }

    /* JADX INFO: renamed from: d */
    private String m2099d(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return new SimpleDateFormat("yy.MM.dd.HH").format(calendar.getTime());
    }

    /* JADX INFO: renamed from: c */
    private String m2098c(String str) {
        return "com.tencent.mobileqq_connectSdk." + str + ".log";
    }

    /* JADX INFO: renamed from: b */
    public File m2105b() {
        File fileM2113e = m2113e();
        fileM2113e.mkdirs();
        return fileM2113e;
    }

    /* JADX INFO: renamed from: c */
    public String m2109c() {
        return this.f3146b;
    }

    /* JADX INFO: renamed from: a */
    public void m2104a(String str) {
        this.f3146b = str;
    }

    /* JADX INFO: renamed from: a */
    public void m2101a(int i) {
        this.f3147c = i;
    }

    /* JADX INFO: renamed from: b */
    public void m2106b(int i) {
        this.f3148d = i;
    }

    /* JADX INFO: renamed from: d */
    public int m2111d() {
        return this.f3149e;
    }

    /* JADX INFO: renamed from: c */
    public void m2110c(int i) {
        this.f3149e = i;
    }

    /* JADX INFO: renamed from: a */
    public void m2102a(long j) {
        this.f3150f = j;
    }

    /* JADX INFO: renamed from: e */
    public File m2113e() {
        return this.f3151g;
    }

    /* JADX INFO: renamed from: a */
    public void m2103a(File file) {
        this.f3151g = file;
    }

    /* JADX INFO: renamed from: f */
    public int m2114f() {
        return this.f3152h;
    }

    /* JADX INFO: renamed from: d */
    public void m2112d(int i) {
        this.f3152h = i;
    }

    /* JADX INFO: renamed from: b */
    public void m2108b(String str) {
        this.f3153i = str;
    }

    /* JADX INFO: renamed from: b */
    public void m2107b(long j) {
        this.f3154j = j;
    }
}
