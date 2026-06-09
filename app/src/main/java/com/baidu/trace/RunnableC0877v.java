package com.baidu.trace;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.baidu.trace.p012c.C0853d;

/* JADX INFO: renamed from: com.baidu.trace.v */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0877v implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ SQLiteDatabase f1857a;

    /* JADX INFO: renamed from: b */
    private /* synthetic */ C0876u f1858b;

    RunnableC0877v(C0876u c0876u, SQLiteDatabase sQLiteDatabase) {
        this.f1858b = c0876u;
        this.f1857a = sQLiteDatabase;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        Cursor cursorRawQuery;
        SQLiteDatabase writableDatabase;
        try {
            writableDatabase = this.f1858b.f1855a.getWritableDatabase();
            try {
                cursorRawQuery = writableDatabase.rawQuery("select * from trace_locationinfo;", null);
                while (cursorRawQuery != null) {
                    try {
                        if (!cursorRawQuery.moveToNext()) {
                            break;
                        }
                        String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("location_data"));
                        if (!TextUtils.isEmpty(string) && string.length() >= 8) {
                            byte[] bArrM1220a = C0853d.m1220a(string);
                            long j = Long.parseLong(string.substring(string.length() - 8, string.length()), 16);
                            StringBuffer stringBuffer = new StringBuffer("insert into ");
                            stringBuffer.append("trace_location(");
                            stringBuffer.append("loc_time, ");
                            stringBuffer.append("entity_name, ");
                            stringBuffer.append("location_data) values(?,?,?);");
                            this.f1857a.execSQL(stringBuffer.toString(), new Object[]{Long.valueOf(j), Trace.f1161e, bArrM1220a});
                        }
                    } catch (Exception unused) {
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        if (writableDatabase != null) {
                            writableDatabase.close();
                        }
                        if (this.f1858b.f1855a != null) {
                            this.f1858b.f1855a.close();
                            C0876u.m1280a(this.f1858b, (SQLiteOpenHelper) null);
                        }
                        if (this.f1858b.f1856b == null) {
                            return;
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        if (writableDatabase != null) {
                            writableDatabase.close();
                        }
                        if (this.f1858b.f1855a != null) {
                            this.f1858b.f1855a.close();
                            C0876u.m1280a(this.f1858b, (SQLiteOpenHelper) null);
                        }
                        if (this.f1858b.f1856b != null) {
                            this.f1858b.f1856b.deleteDatabase("LBSTRACE.db");
                            C0876u.m1278a(this.f1858b, (Context) null);
                        }
                        throw th;
                    }
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
                if (this.f1858b.f1855a != null) {
                    this.f1858b.f1855a.close();
                    C0876u.m1280a(this.f1858b, (SQLiteOpenHelper) null);
                }
                if (this.f1858b.f1856b == null) {
                    return;
                }
            } catch (Exception unused2) {
                cursorRawQuery = null;
            } catch (Throwable th2) {
                th = th2;
                cursorRawQuery = null;
            }
        } catch (Exception unused3) {
            cursorRawQuery = null;
            writableDatabase = null;
        } catch (Throwable th3) {
            th = th3;
            cursorRawQuery = null;
            writableDatabase = null;
        }
        this.f1858b.f1856b.deleteDatabase("LBSTRACE.db");
        C0876u.m1278a(this.f1858b, (Context) null);
    }
}
