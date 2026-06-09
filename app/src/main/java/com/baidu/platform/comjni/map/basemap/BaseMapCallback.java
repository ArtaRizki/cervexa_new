package com.baidu.platform.comjni.map.basemap;

import android.os.Bundle;
import android.util.LongSparseArray;

/* JADX INFO: loaded from: classes.dex */
public class BaseMapCallback {

    /* JADX INFO: renamed from: a */
    private static LongSparseArray<InterfaceC0782b> f1115a = new LongSparseArray<>();

    public static int ReqLayerData(Bundle bundle, long j, int i, Bundle bundle2) {
        int size = f1115a.size();
        for (int i2 = 0; i2 < size; i2++) {
            InterfaceC0782b interfaceC0782bValueAt = f1115a.valueAt(i2);
            if (interfaceC0782bValueAt != null && interfaceC0782bValueAt.mo650a(j)) {
                return interfaceC0782bValueAt.mo632a(bundle, j, i, bundle2);
            }
        }
        return 0;
    }

    public static void addLayerDataInterface(long j, InterfaceC0782b interfaceC0782b) {
        f1115a.put(j, interfaceC0782b);
    }

    public static void removeLayerDataInterface(long j) {
        f1115a.remove(j);
    }
}
