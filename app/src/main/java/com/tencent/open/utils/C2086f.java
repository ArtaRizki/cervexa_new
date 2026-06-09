package com.tencent.open.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.tencent.open.p026a.C2061f;
import java.lang.ref.WeakReference;
import java.net.URL;

/* JADX INFO: renamed from: com.tencent.open.utils.f */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2086f {

    /* JADX INFO: renamed from: a */
    private static C2086f f3281a;

    /* JADX INFO: renamed from: b */
    private volatile WeakReference<SharedPreferences> f3282b = null;

    /* JADX INFO: renamed from: a */
    public static synchronized C2086f m2232a() {
        if (f3281a == null) {
            f3281a = new C2086f();
        }
        return f3281a;
    }

    /* JADX INFO: renamed from: a */
    public String m2233a(Context context, String str) {
        if (this.f3282b == null || this.f3282b.get() == null) {
            this.f3282b = new WeakReference<>(context.getSharedPreferences("ServerPrefs", 0));
        }
        try {
            String host = new URL(str).getHost();
            if (host == null) {
                C2061f.m2135e("openSDK_LOG.ServerSetting", "Get host error. url=" + str);
                return str;
            }
            String string = this.f3282b.get().getString(host, null);
            if (string != null && !host.equals(string)) {
                String strReplace = str.replace(host, string);
                C2061f.m2127a("openSDK_LOG.ServerSetting", "return environment url : " + strReplace);
                return strReplace;
            }
            C2061f.m2127a("openSDK_LOG.ServerSetting", "host=" + host + ", envHost=" + string);
            return str;
        } catch (Exception e) {
            C2061f.m2135e("openSDK_LOG.ServerSetting", "getEnvUrl url=" + str + "error.: " + e.getMessage());
            return str;
        }
    }
}
