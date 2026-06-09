package com.baidu.platform.comapi.map;

import android.os.Handler;
import com.baidu.platform.comjni.engine.C0780a;

/* JADX INFO: loaded from: classes.dex */
public class MessageCenter {
    public static void registMessage(int i, Handler handler) {
        C0780a.m820a(i, handler);
    }

    public static void unregistMessage(int i, Handler handler) {
        C0780a.m821b(i, handler);
    }
}
