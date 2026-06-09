package com.baidu.platform.comjni.map.commonmemcache;

/* JADX INFO: renamed from: com.baidu.platform.comjni.map.commonmemcache.a */
/* JADX INFO: loaded from: classes.dex */
public class C0783a {

    /* JADX INFO: renamed from: a */
    private long f1121a = 0;

    /* JADX INFO: renamed from: b */
    private JNICommonMemCache f1122b;

    public C0783a() {
        this.f1122b = null;
        this.f1122b = new JNICommonMemCache();
    }

    /* JADX INFO: renamed from: a */
    public long m881a() {
        long jCreate = this.f1122b.Create();
        this.f1121a = jCreate;
        return jCreate;
    }

    /* JADX INFO: renamed from: b */
    public void m882b() {
        long j = this.f1121a;
        if (j != 0) {
            this.f1122b.Init(j);
        }
    }
}
