package com.baidu.p013vi;

import android.net.NetworkInfo;

/* JADX INFO: renamed from: com.baidu.vi.c */
/* JADX INFO: loaded from: classes.dex */
public class C0885c {

    /* JADX INFO: renamed from: a */
    public String f1895a;

    /* JADX INFO: renamed from: b */
    public int f1896b;

    /* JADX INFO: renamed from: c */
    public int f1897c;

    /* JADX INFO: renamed from: com.baidu.vi.c$1, reason: invalid class name */
    /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a */
        static final /* synthetic */ int[] f1898a;

        static {
            int[] iArr = new int[NetworkInfo.State.values().length];
            f1898a = iArr;
            try {
                iArr[NetworkInfo.State.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1898a[NetworkInfo.State.CONNECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1898a[NetworkInfo.State.DISCONNECTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1898a[NetworkInfo.State.DISCONNECTING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1898a[NetworkInfo.State.SUSPENDED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public C0885c(NetworkInfo networkInfo) {
        this.f1895a = networkInfo.getTypeName();
        this.f1896b = networkInfo.getType();
        int i = AnonymousClass1.f1898a[networkInfo.getState().ordinal()];
        if (i == 1) {
            this.f1897c = 2;
        } else if (i != 2) {
            this.f1897c = 0;
        } else {
            this.f1897c = 1;
        }
    }
}
