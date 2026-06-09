package com.baidu.trace;

import com.baidu.lbsapi.auth.tracesdk.LBSAuthManager;
import com.baidu.trace.p012c.C0854e;
import com.baidu.trace.p012c.C0855f;
import com.generalplus.GoPlusDrone.Fragment.BaseFragment;
import com.jieli.lib.p015dv.control.utils.TopicKey;
import com.jieli.stream.p016dv.running2.util.IConstant;
import java.util.Hashtable;

/* JADX INFO: renamed from: com.baidu.trace.d */
/* JADX INFO: loaded from: classes.dex */
public final class C0858d {

    /* JADX INFO: renamed from: a */
    protected static String f1767a;

    /* JADX INFO: renamed from: b */
    protected static LBSAuthManager f1768b;

    /* JADX INFO: renamed from: a */
    protected static void m1248a() {
        if (f1768b == null) {
            return;
        }
        Hashtable hashtable = new Hashtable();
        hashtable.put("from", "trace");
        hashtable.put("mb", C0855f.f1748a);
        hashtable.put("os", IConstant.ANDROID_DIR);
        hashtable.put("sv", "3.0.7");
        hashtable.put("imt", f1768b.getIMEI());
        hashtable.put("net", C0854e.f1737a);
        hashtable.put("cpu", C0855f.f1749b);
        hashtable.put("resid", "02");
        hashtable.put("appid", "-1");
        hashtable.put(TopicKey.VERSION, "1");
        hashtable.put("pcn", C0854e.f1739c);
        hashtable.put("cuid", f1768b.getCUID());
        hashtable.put(BaseFragment.DATA_NAME, C0854e.f1738b);
        f1768b.authenticate(true, "lbs_tracesdk", hashtable, new C0859e());
    }

    /* JADX INFO: renamed from: b */
    protected static void m1249b() {
        f1768b = null;
    }
}
