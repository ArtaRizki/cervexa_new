package com.baidu.trace.p012c;

import android.text.TextUtils;
import kotlin.UByte;

/* JADX INFO: renamed from: com.baidu.trace.c.d */
/* JADX INFO: loaded from: classes.dex */
public final class C0853d {
    static {
        "0123456789abcdef".getBytes();
    }

    /* JADX INFO: renamed from: a */
    public static short m1219a(byte[] bArr, int i) {
        if (bArr == null || 2 > bArr.length) {
            return (short) 0;
        }
        return (short) ((bArr[1] & UByte.MAX_VALUE) | (bArr[0] << 8));
    }

    /* JADX INFO: renamed from: a */
    public static byte[] m1220a(String str) {
        return str == null ? new byte[0] : str.getBytes();
    }

    /* JADX INFO: renamed from: b */
    public static byte[] m1221b(String str) {
        int length = 6;
        byte[] bArr = new byte[6];
        if (TextUtils.isEmpty(str)) {
            return bArr;
        }
        try {
            String[] strArrSplit = str.split(":");
            if (6 > strArrSplit.length) {
                length = strArrSplit.length;
            }
            for (int i = 0; i < length; i++) {
                bArr[i] = (byte) Integer.parseInt(strArrSplit[i], 16);
            }
        } catch (Exception unused) {
        }
        return bArr;
    }
}
