package com.tencent.open.utils;

import androidx.core.view.MotionEventCompat;
import kotlin.UByte;

/* JADX INFO: renamed from: com.tencent.open.utils.k */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public final class C2091k implements Cloneable {

    /* JADX INFO: renamed from: a */
    private int f3302a;

    public C2091k(byte[] bArr) {
        this(bArr, 0);
    }

    public C2091k(byte[] bArr, int i) {
        int i2 = (bArr[i + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK;
        this.f3302a = i2;
        this.f3302a = i2 + (bArr[i] & UByte.MAX_VALUE);
    }

    public C2091k(int i) {
        this.f3302a = i;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof C2091k) && this.f3302a == ((C2091k) obj).m2284b();
    }

    /* JADX INFO: renamed from: a */
    public byte[] m2283a() {
        int i = this.f3302a;
        return new byte[]{(byte) (i & 255), (byte) ((i & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8)};
    }

    /* JADX INFO: renamed from: b */
    public int m2284b() {
        return this.f3302a;
    }

    public int hashCode() {
        return this.f3302a;
    }
}
