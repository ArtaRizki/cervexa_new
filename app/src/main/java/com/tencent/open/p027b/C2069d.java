package com.tencent.open.p027b;

import android.os.SystemClock;
import com.tencent.open.utils.C2089i;

/* JADX INFO: renamed from: com.tencent.open.b.d */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2069d {

    /* JADX INFO: renamed from: a */
    protected static C2069d f3193a;

    protected C2069d() {
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C2069d m2162a() {
        if (f3193a == null) {
            f3193a = new C2069d();
        }
        return f3193a;
    }

    /* JADX INFO: renamed from: a */
    public void m2164a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        C2072g.m2172a().m2174a(C2089i.m2254a(str, str4, str5, str3, str2, str6, "", str7, str8, "", "", ""), str2, false);
    }

    /* JADX INFO: renamed from: a */
    public void m2165a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        C2072g.m2172a().m2174a(C2089i.m2254a(str, str4, str5, str3, str2, str6, str7, "", "", str8, str9, str10), str2, false);
    }

    /* JADX INFO: renamed from: a */
    public void m2163a(int i, String str, String str2, String str3, String str4, Long l, int i2, int i3, String str5) {
        long jElapsedRealtime = SystemClock.elapsedRealtime() - l.longValue();
        if (l.longValue() == 0 || jElapsedRealtime < 0) {
            jElapsedRealtime = 0;
        }
        StringBuffer stringBuffer = new StringBuffer("http://c.isdspeed.qq.com/code.cgi");
        stringBuffer.append("?domain=mobile.opensdk.com&cgi=opensdk&type=");
        stringBuffer.append(i);
        stringBuffer.append("&code=");
        stringBuffer.append(i2);
        stringBuffer.append("&time=");
        stringBuffer.append(jElapsedRealtime);
        stringBuffer.append("&rate=");
        stringBuffer.append(i3);
        stringBuffer.append("&uin=");
        stringBuffer.append(str2);
        stringBuffer.append("&data=");
        C2072g.m2172a().m2177a(stringBuffer.toString(), "GET", C2089i.m2253a(String.valueOf(i), String.valueOf(i2), String.valueOf(jElapsedRealtime), String.valueOf(i3), str, str2, str3, str4, str5), true);
    }
}
