package com.tencent.p023mm.opensdk.channel.p024a;

import com.tencent.p023mm.opensdk.utils.C2050b;

/* JADX INFO: renamed from: com.tencent.mm.opensdk.channel.a.b */
/* JADX INFO: loaded from: classes2.dex */
public final class C2041b {
    /* JADX INFO: renamed from: a */
    public static byte[] m2037a(String str, int i, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        if (str != null) {
            stringBuffer.append(str);
        }
        stringBuffer.append(i);
        stringBuffer.append(str2);
        stringBuffer.append("mMcShCsTr");
        return C2050b.m2057c(stringBuffer.toString().substring(1, 9).getBytes()).getBytes();
    }
}
