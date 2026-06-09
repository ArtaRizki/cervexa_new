package com.baidu.trace;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* JADX INFO: renamed from: com.baidu.trace.x */
/* JADX INFO: loaded from: classes.dex */
final class C0879x extends SQLiteOpenHelper {
    C0879x(C0876u c0876u, Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
