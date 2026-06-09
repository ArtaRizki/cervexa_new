package com.jieli.lib.p015dv.control.utils;

import com.jieli.lib.p015dv.control.connect.listener.OnConnectStateListener;
import com.jieli.lib.p015dv.control.json.bean.NotifyInfo;
import com.jieli.lib.p015dv.control.receiver.listener.OnNotifyListener;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class ListenerHelper {

    /* JADX INFO: renamed from: a */
    private static String f2220a = "ListenerHelper";

    /* JADX INFO: renamed from: b */
    private static ListenerHelper f2221b;

    /* JADX INFO: renamed from: c */
    private HashSet<OnConnectStateListener> f2222c = new HashSet<>();

    /* JADX INFO: renamed from: d */
    private HashSet<OnNotifyListener> f2223d = new HashSet<>();

    public static ListenerHelper getInstance() {
        if (f2221b == null) {
            synchronized (ListenerHelper.class) {
                if (f2221b == null) {
                    f2221b = new ListenerHelper();
                }
            }
        }
        return f2221b;
    }

    public boolean addConnectStateListener(OnConnectStateListener onConnectStateListener) {
        if (this.f2222c.contains(onConnectStateListener)) {
            return false;
        }
        return this.f2222c.add(onConnectStateListener);
    }

    public boolean removeConnectStateListener(OnConnectStateListener onConnectStateListener) {
        HashSet<OnConnectStateListener> hashSet = this.f2222c;
        if (hashSet != null) {
            return hashSet.remove(onConnectStateListener);
        }
        return false;
    }

    public boolean addNotifyListener(OnNotifyListener onNotifyListener) {
        HashSet<OnNotifyListener> hashSet = this.f2223d;
        if (hashSet == null || onNotifyListener == null || hashSet.contains(onNotifyListener)) {
            return false;
        }
        return this.f2223d.add(onNotifyListener);
    }

    public boolean removeNotifyListener(OnNotifyListener onNotifyListener) {
        HashSet<OnNotifyListener> hashSet = this.f2223d;
        if (hashSet != null) {
            return hashSet.remove(onNotifyListener);
        }
        return false;
    }

    public void dispatchDeviceConnectState(final int i) {
        HashSet<OnConnectStateListener> hashSet = this.f2222c;
        if (hashSet == null) {
            Dlog.m1386w(f2220a, "OnConnectStateListener is null");
            return;
        }
        for (final OnConnectStateListener onConnectStateListener : hashSet) {
            ClientContext.post(new Runnable() { // from class: com.jieli.lib.dv.control.utils.ListenerHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    onConnectStateListener.onStateChanged(Integer.valueOf(i));
                }
            });
        }
    }

    public void dispatchNotify(final NotifyInfo notifyInfo) {
        for (final OnNotifyListener onNotifyListener : this.f2223d) {
            ClientContext.post(new Runnable() { // from class: com.jieli.lib.dv.control.utils.ListenerHelper.2
                @Override // java.lang.Runnable
                public void run() {
                    onNotifyListener.onNotify(notifyInfo);
                }
            });
        }
    }

    public void release() {
        f2221b = null;
        HashSet<OnConnectStateListener> hashSet = this.f2222c;
        if (hashSet != null) {
            hashSet.clear();
        }
        HashSet<OnNotifyListener> hashSet2 = this.f2223d;
        if (hashSet2 != null) {
            hashSet2.clear();
        }
    }
}
