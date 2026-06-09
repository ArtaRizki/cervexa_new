package com.baidu.trace;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.baidu.trace.p */
/* JADX INFO: loaded from: classes.dex */
public final class C0871p extends PhoneStateListener {

    /* JADX INFO: renamed from: a */
    public static int f1836a;

    /* JADX INFO: renamed from: b */
    private TelephonyManager f1837b;

    /* JADX INFO: renamed from: f */
    private a f1841f;

    /* JADX INFO: renamed from: h */
    private int f1843h;

    /* JADX INFO: renamed from: j */
    private int f1845j;

    /* JADX INFO: renamed from: k */
    private int f1846k;

    /* JADX INFO: renamed from: c */
    private List<Integer> f1838c = new ArrayList();

    /* JADX INFO: renamed from: d */
    private List<Integer> f1839d = new ArrayList();

    /* JADX INFO: renamed from: e */
    private int f1840e = 0;

    /* JADX INFO: renamed from: g */
    private int f1842g = 0;

    /* JADX INFO: renamed from: i */
    private String f1844i = "";

    /* JADX INFO: renamed from: com.baidu.trace.p$a */
    public class a extends PhoneStateListener {
        public a(C0871p c0871p) {
        }

        @Override // android.telephony.PhoneStateListener
        public final void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            C0871p.f1836a = signalStrength.isGsm() ? 99 != signalStrength.getGsmSignalStrength() ? (signalStrength.getGsmSignalStrength() << 1) - 113 : 0 : signalStrength.getCdmaDbm();
        }
    }

    public C0871p(Context context) {
        this.f1841f = null;
        this.f1837b = (TelephonyManager) context.getSystemService("phone");
        a aVar = new a(this);
        this.f1841f = aVar;
        TelephonyManager telephonyManager = this.f1837b;
        if (telephonyManager != null) {
            telephonyManager.listen(aVar, 256);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:90:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void m1269a(com.baidu.trace.p010a.C0794c r10) {
        /*
            Method dump skipped, instruction units count: 400
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.trace.C0871p.m1269a(com.baidu.trace.a.c):void");
    }

    /* JADX INFO: renamed from: a */
    protected final boolean m1270a() {
        return this.f1837b != null;
    }

    /* JADX INFO: renamed from: b */
    protected final void m1271b() {
        List<Integer> list = this.f1838c;
        if (list != null) {
            list.clear();
            this.f1838c = null;
        }
        List<Integer> list2 = this.f1839d;
        if (list2 != null) {
            list2.clear();
            this.f1839d = null;
        }
    }
}
