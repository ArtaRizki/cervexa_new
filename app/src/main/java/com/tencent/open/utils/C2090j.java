package com.tencent.open.utils;

/* JADX INFO: renamed from: com.tencent.open.utils.j */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public final class C2090j implements Cloneable {

    /* JADX INFO: renamed from: a */
    private long f3301a;

    public C2090j(long j) {
        this.f3301a = j;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof C2090j) && this.f3301a == ((C2090j) obj).m2282b();
    }

    /* JADX INFO: renamed from: a */
    public byte[] m2281a() {
        long j = this.f3301a;
        return new byte[]{(byte) (255 & j), (byte) ((65280 & j) >> 8), (byte) ((16711680 & j) >> 16), (byte) ((j & 4278190080L) >> 24)};
    }

    /* JADX INFO: renamed from: b */
    public long m2282b() {
        return this.f3301a;
    }

    public int hashCode() {
        return (int) this.f3301a;
    }
}
