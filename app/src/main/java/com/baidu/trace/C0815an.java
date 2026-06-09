package com.baidu.trace;

import android.database.sqlite.SQLiteDatabase;

/* JADX INFO: renamed from: com.baidu.trace.an */
/* JADX INFO: loaded from: classes.dex */
final class C0815an extends Thread {
    C0815an() {
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        if (C0814am.f1271b != null) {
            return;
        }
        try {
            SQLiteDatabase unused = C0814am.f1271b = C0814am.f1270a.getWritableDatabase();
        } catch (Exception unused2) {
            SQLiteDatabase unused3 = C0814am.f1271b = null;
        } finally {
            C0814am.m1079b(false);
        }
    }
}
