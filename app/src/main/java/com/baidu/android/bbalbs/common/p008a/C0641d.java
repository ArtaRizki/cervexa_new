package com.baidu.android.bbalbs.common.p008a;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: renamed from: com.baidu.android.bbalbs.common.a.d */
/* JADX INFO: loaded from: classes.dex */
public final class C0641d {
    /* JADX INFO: renamed from: a */
    public static byte[] m74a(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA-1").digest(bArr);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
