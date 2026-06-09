package com.tencent.open.p026a;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: renamed from: com.tencent.open.a.a */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2056a extends AbstractC2064i implements Handler.Callback {

    /* JADX INFO: renamed from: a */
    private C2057b f3134a;

    /* JADX INFO: renamed from: b */
    private FileWriter f3135b;

    /* JADX INFO: renamed from: c */
    private File f3136c;

    /* JADX INFO: renamed from: d */
    private char[] f3137d;

    /* JADX INFO: renamed from: e */
    private volatile C2062g f3138e;

    /* JADX INFO: renamed from: f */
    private volatile C2062g f3139f;

    /* JADX INFO: renamed from: g */
    private volatile C2062g f3140g;

    /* JADX INFO: renamed from: h */
    private volatile C2062g f3141h;

    /* JADX INFO: renamed from: i */
    private volatile boolean f3142i;

    /* JADX INFO: renamed from: j */
    private HandlerThread f3143j;

    /* JADX INFO: renamed from: k */
    private Handler f3144k;

    public C2056a(C2057b c2057b) {
        this(C2058c.f3156b, true, C2063h.f3179a, c2057b);
    }

    public C2056a(int i, boolean z, C2063h c2063h, C2057b c2057b) {
        super(i, z, c2063h);
        this.f3142i = false;
        m2093a(c2057b);
        this.f3138e = new C2062g();
        this.f3139f = new C2062g();
        this.f3140g = this.f3138e;
        this.f3141h = this.f3139f;
        this.f3137d = new char[c2057b.m2111d()];
        m2088g();
        HandlerThread handlerThread = new HandlerThread(c2057b.m2109c(), c2057b.m2114f());
        this.f3143j = handlerThread;
        if (handlerThread != null) {
            handlerThread.start();
        }
        if (!this.f3143j.isAlive() || this.f3143j.getLooper() == null) {
            return;
        }
        this.f3144k = new Handler(this.f3143j.getLooper(), this);
    }

    /* JADX INFO: renamed from: a */
    public void m2091a() {
        if (this.f3144k.hasMessages(1024)) {
            this.f3144k.removeMessages(1024);
        }
        this.f3144k.sendEmptyMessage(1024);
    }

    /* JADX INFO: renamed from: b */
    public void m2095b() {
        m2089h();
        this.f3143j.quit();
    }

    @Override // com.tencent.open.p026a.AbstractC2064i
    /* JADX INFO: renamed from: a */
    protected void mo2092a(int i, Thread thread, long j, String str, String str2, Throwable th) {
        m2094a(m2149e().m2143a(i, thread, j, str, str2, th));
    }

    /* JADX INFO: renamed from: a */
    protected void m2094a(String str) {
        this.f3140g.m2139a(str);
        if (this.f3140g.m2138a() >= m2096c().m2111d()) {
            m2091a();
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 1024) {
            return true;
        }
        m2087f();
        return true;
    }

    /* JADX INFO: renamed from: f */
    private void m2087f() {
        if (Thread.currentThread() == this.f3143j && !this.f3142i) {
            this.f3142i = true;
            m2090i();
            try {
                this.f3141h.m2140a(m2088g(), this.f3137d);
            } catch (IOException unused) {
            } catch (Throwable th) {
                this.f3141h.m2141b();
                throw th;
            }
            this.f3141h.m2141b();
            this.f3142i = false;
        }
    }

    /* JADX INFO: renamed from: g */
    private Writer m2088g() {
        File fileM2100a = m2096c().m2100a();
        if ((fileM2100a != null && !fileM2100a.equals(this.f3136c)) || (this.f3135b == null && fileM2100a != null)) {
            this.f3136c = fileM2100a;
            m2089h();
            try {
                this.f3135b = new FileWriter(this.f3136c, true);
            } catch (IOException unused) {
                return null;
            }
        }
        return this.f3135b;
    }

    /* JADX INFO: renamed from: h */
    private void m2089h() {
        try {
            if (this.f3135b != null) {
                this.f3135b.flush();
                this.f3135b.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: i */
    private void m2090i() {
        synchronized (this) {
            if (this.f3140g == this.f3138e) {
                this.f3140g = this.f3139f;
                this.f3141h = this.f3138e;
            } else {
                this.f3140g = this.f3138e;
                this.f3141h = this.f3139f;
            }
        }
    }

    /* JADX INFO: renamed from: c */
    public C2057b m2096c() {
        return this.f3134a;
    }

    /* JADX INFO: renamed from: a */
    public void m2093a(C2057b c2057b) {
        this.f3134a = c2057b;
    }
}
