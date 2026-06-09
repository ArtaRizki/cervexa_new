package com.baidu.trace;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.baidu.trace.p012c.C0850a;

/* JADX INFO: renamed from: com.baidu.trace.u */
/* JADX INFO: loaded from: classes.dex */
public final class C0876u extends SQLiteOpenHelper {

    /* JADX INFO: renamed from: a */
    private SQLiteOpenHelper f1855a;

    /* JADX INFO: renamed from: b */
    private Context f1856b;

    public C0876u(Context context) {
        super(context, "BAIDUTRACE.db", (SQLiteDatabase.CursorFactory) null, 3);
        this.f1855a = null;
        this.f1856b = null;
        String[] strArrDatabaseList = context.databaseList();
        if (strArrDatabaseList == null || strArrDatabaseList.length == 0) {
            return;
        }
        int length = strArrDatabaseList.length;
        boolean z = false;
        int i = 0;
        boolean z2 = false;
        while (true) {
            if (i >= length) {
                z = z2;
                break;
            }
            String str = strArrDatabaseList[i];
            if ("BAIDUTRACE.db".equals(str)) {
                context.deleteDatabase("LBSTRACE.db");
                break;
            } else {
                if ("LBSTRACE.db".equals(str)) {
                    z2 = true;
                }
                i++;
            }
        }
        if (z) {
            this.f1856b = context;
            this.f1855a = new C0879x(this, context, "LBSTRACE.db", null, 1);
        }
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ Context m1278a(C0876u c0876u, Context context) {
        c0876u.f1856b = null;
        return null;
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ SQLiteOpenHelper m1280a(C0876u c0876u, SQLiteOpenHelper sQLiteOpenHelper) {
        c0876u.f1855a = null;
        return null;
    }

    /* JADX INFO: renamed from: a */
    protected static void m1281a(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        try {
            sQLiteDatabase.execSQL(str, strArr);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    protected static Cursor m1283b(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        try {
            return sQLiteDatabase.rawQuery(str, strArr);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        StringBuffer stringBuffer = new StringBuffer("CREATE TABLE if not exists ");
        stringBuffer.append("trace_location (");
        stringBuffer.append("loc_time INTEGER, ");
        stringBuffer.append("entity_name VARCHAR(128), ");
        stringBuffer.append("location_data BLOB);");
        StringBuffer stringBuffer2 = new StringBuffer("CREATE TABLE if not exists ");
        stringBuffer2.append("trace_fence (");
        stringBuffer2.append("fence_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stringBuffer2.append("fence_name VARCHAR(128), ");
        stringBuffer2.append("monitored_person VARCHAR(128), ");
        stringBuffer2.append("coord_type VARCHAR(16), ");
        stringBuffer2.append("denoise INTEGER, ");
        stringBuffer2.append("fence_shape VARCHAR(16), ");
        stringBuffer2.append("create_time VARCHAR(32), ");
        stringBuffer2.append("modify_time VARCHAR(32), ");
        stringBuffer2.append("monitored_status VARCHAR(16), ");
        stringBuffer2.append("fence_extern_info TEXT);");
        StringBuffer stringBuffer3 = new StringBuffer("CREATE TABLE if not exists ");
        stringBuffer3.append("trace_fence_alarm (");
        stringBuffer3.append("fence_id INTEGER, ");
        stringBuffer3.append("fence_name VARCHAR(128), ");
        stringBuffer3.append("monitored_person VARCHAR(128), ");
        stringBuffer3.append("monitored_action VARCHAR(16), ");
        stringBuffer3.append("create_time INTEGER, ");
        stringBuffer3.append("cur_point TEXT, ");
        stringBuffer3.append("pre_point TEXT);");
        try {
            sQLiteDatabase.execSQL(stringBuffer.toString());
            sQLiteDatabase.execSQL(stringBuffer2.toString());
            sQLiteDatabase.execSQL(stringBuffer3.toString());
        } catch (Exception unused) {
        }
        if (this.f1855a == null) {
            return;
        }
        C0850a.f1715a.execute(new RunnableC0877v(this, sQLiteDatabase));
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        super.onDowngrade(sQLiteDatabase, i, i2);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        C0850a.f1715a.execute(new RunnableC0878w(this, i, i2, sQLiteDatabase));
    }
}
