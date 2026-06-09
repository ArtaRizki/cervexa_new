package com.baidu.p002a.p003a.p004a.p005a.p006a;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: renamed from: com.baidu.a.a.a.a.a.d */
/* JADX INFO: loaded from: classes.dex */
public final class C0633d {
    /* JADX INFO: renamed from: a */
    public static byte[] m28a(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA-1").digest(bArr);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
