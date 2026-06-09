package com.baidu.platform.comapi.util;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SysUpdateObservable {

    /* JADX INFO: renamed from: a */
    private static volatile SysUpdateObservable f1060a;

    /* JADX INFO: renamed from: b */
    private List<SysUpdateObserver> f1061b;

    private SysUpdateObservable() {
        this.f1061b = null;
        this.f1061b = new ArrayList();
    }

    public static SysUpdateObservable getInstance() {
        if (f1060a == null) {
            synchronized (SysUpdateObservable.class) {
                if (f1060a == null) {
                    f1060a = new SysUpdateObservable();
                }
            }
        }
        return f1060a;
    }

    public void addObserver(SysUpdateObserver sysUpdateObserver) {
        this.f1061b.add(sysUpdateObserver);
    }

    public void init() {
        for (SysUpdateObserver sysUpdateObserver : this.f1061b) {
            if (sysUpdateObserver != null) {
                sysUpdateObserver.init();
            }
        }
    }

    public void updateNetworkInfo(Context context) {
        for (SysUpdateObserver sysUpdateObserver : this.f1061b) {
            if (sysUpdateObserver != null) {
                sysUpdateObserver.updateNetworkInfo(context);
            }
        }
    }

    public void updateNetworkProxy(Context context) {
        for (SysUpdateObserver sysUpdateObserver : this.f1061b) {
            if (sysUpdateObserver != null) {
                sysUpdateObserver.updateNetworkProxy(context);
            }
        }
    }

    public void updatePhoneInfo() {
        for (SysUpdateObserver sysUpdateObserver : this.f1061b) {
            if (sysUpdateObserver != null) {
                sysUpdateObserver.updatePhoneInfo();
            }
        }
    }
}
