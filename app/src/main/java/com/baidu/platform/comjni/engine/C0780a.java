package com.baidu.platform.comjni.engine;

import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.baidu.platform.comjni.engine.a */
/* JADX INFO: loaded from: classes.dex */
public class C0780a {

    /* JADX INFO: renamed from: a */
    private static final String f1113a = C0780a.class.getSimpleName();

    /* JADX INFO: renamed from: b */
    private static SparseArray<List<Handler>> f1114b = new SparseArray<>();

    /* JADX INFO: renamed from: a */
    public static void m819a(int i, int i2, int i3, long j) {
        synchronized (f1114b) {
            List<Handler> list = f1114b.get(i);
            if (list != null && !list.isEmpty()) {
                Iterator<Handler> it = list.iterator();
                while (it.hasNext()) {
                    Message.obtain(it.next(), i, i2, i3, Long.valueOf(j)).sendToTarget();
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m820a(int i, Handler handler) {
        synchronized (f1114b) {
            if (handler == null) {
                return;
            }
            List<Handler> list = f1114b.get(i);
            if (list == null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(handler);
                f1114b.put(i, arrayList);
            } else if (!list.contains(handler)) {
                list.add(handler);
            }
        }
    }

    /* JADX INFO: renamed from: b */
    public static void m821b(int i, Handler handler) {
        synchronized (f1114b) {
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
                List<Handler> list = f1114b.get(i);
                if (list != null) {
                    list.remove(handler);
                }
            }
        }
    }
}
