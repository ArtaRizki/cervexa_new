package com.tencent.bugly.crashreport.inner;

import com.tencent.bugly.crashreport.crash.C1973d;
import com.tencent.bugly.proguard.C2021x;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public class InnerApi {
    public static void postU3dCrashAsync(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            C2021x.m1873e("post u3d fail args null", new Object[0]);
        }
        C2021x.m1866a("post u3d crash %s %s", str, str2);
        C1973d.m1644a(Thread.currentThread(), 4, str, str2, str3, null);
    }

    public static void postCocos2dxCrashAsync(int i, String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            C2021x.m1873e("post cocos2d-x fail args null", new Object[0]);
        } else if (i != 5 && i != 6) {
            C2021x.m1873e("post cocos2d-x fail category illeagle: %d", Integer.valueOf(i));
        } else {
            C2021x.m1866a("post cocos2d-x crash %s %s", str, str2);
            C1973d.m1644a(Thread.currentThread(), i, str, str2, str3, null);
        }
    }

    public static void postH5CrashAsync(Thread thread, String str, String str2, String str3, Map<String, String> map) {
        if (str == null || str2 == null || str3 == null) {
            C2021x.m1873e("post h5 fail args null", new Object[0]);
        } else {
            C2021x.m1866a("post h5 crash %s %s", str, str2);
            C1973d.m1644a(thread, 8, str, str2, str3, map);
        }
    }
}
