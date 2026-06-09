package com.baidu.trace;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.baidu.trace.api.fence.CircleFence;
import com.baidu.trace.api.fence.FenceShape;
import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.LatLng;
import com.baidu.trace.p012c.C0853d;
import com.baidu.trace.p012c.C0854e;
import com.generalplus.GoPlusDrone.Fragment.BaseFragment;

/* JADX INFO: renamed from: com.baidu.trace.w */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0878w implements Runnable {

    /* JADX INFO: renamed from: a */
    private /* synthetic */ int f1859a;

    /* JADX INFO: renamed from: b */
    private /* synthetic */ int f1860b;

    /* JADX INFO: renamed from: c */
    private /* synthetic */ SQLiteDatabase f1861c;

    RunnableC0878w(C0876u c0876u, int i, int i2, SQLiteDatabase sQLiteDatabase) {
        this.f1859a = i;
        this.f1860b = i2;
        this.f1861c = sQLiteDatabase;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        String str;
        String str2;
        String str3;
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        int i;
        StringBuffer stringBuffer3;
        String[] strArr;
        StringBuffer stringBuffer4;
        int i2;
        StringBuffer stringBuffer5;
        String str4 = "select * from ";
        String str5 = "ALTER TABLE ";
        String str6 = "trace_location_TEMP;";
        StringBuffer stringBuffer6 = new StringBuffer("CREATE TABLE if not exists ");
        stringBuffer6.append("trace_location (");
        stringBuffer6.append("loc_time INTEGER, ");
        stringBuffer6.append("entity_name VARCHAR(128), ");
        stringBuffer6.append("location_data BLOB);");
        StringBuffer stringBuffer7 = new StringBuffer("CREATE TABLE if not exists ");
        stringBuffer7.append("trace_fence (");
        stringBuffer7.append("fence_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        stringBuffer7.append("fence_name VARCHAR(128), ");
        stringBuffer7.append("monitored_person VARCHAR(128), ");
        stringBuffer7.append("coord_type VARCHAR(16), ");
        stringBuffer7.append("denoise INTEGER, ");
        stringBuffer7.append("fence_shape VARCHAR(16), ");
        stringBuffer7.append("create_time VARCHAR(32), ");
        stringBuffer7.append("modify_time VARCHAR(32), ");
        stringBuffer7.append("monitored_status VARCHAR(16), ");
        stringBuffer7.append("fence_extern_info TEXT);");
        StringBuffer stringBuffer8 = new StringBuffer("CREATE TABLE if not exists ");
        stringBuffer8.append("trace_fence_alarm (");
        stringBuffer8.append("fence_id INTEGER, ");
        stringBuffer8.append("fence_name VARCHAR(128), ");
        stringBuffer8.append("monitored_person VARCHAR(128), ");
        stringBuffer8.append("monitored_action VARCHAR(16), ");
        stringBuffer8.append("create_time INTEGER, ");
        stringBuffer8.append("cur_point TEXT, ");
        stringBuffer8.append("pre_point TEXT);");
        Cursor cursorRawQuery = null;
        String[] strArr2 = null;
        cursorRawQuery = null;
        try {
            int i3 = this.f1859a;
            Cursor cursor = null;
            while (i3 < this.f1860b) {
                try {
                    if (this.f1859a != 2) {
                        str = str4;
                        str2 = str5;
                        str3 = str6;
                        stringBuffer = stringBuffer6;
                        strArr = strArr2;
                        stringBuffer2 = stringBuffer7;
                        i = i3;
                        stringBuffer3 = stringBuffer8;
                    } else {
                        this.f1861c.execSQL(stringBuffer8.toString());
                        StringBuffer stringBuffer9 = new StringBuffer(str5);
                        stringBuffer9.append("trace_location RENAME TO ");
                        stringBuffer9.append(str6);
                        this.f1861c.execSQL(stringBuffer9.toString());
                        this.f1861c.execSQL(stringBuffer6.toString());
                        StringBuffer stringBuffer10 = new StringBuffer(str4);
                        stringBuffer10.append(str6);
                        Cursor cursorRawQuery2 = this.f1861c.rawQuery(stringBuffer10.toString(), strArr2);
                        if (cursorRawQuery2 != null) {
                            while (cursorRawQuery2.moveToNext()) {
                                String string = cursorRawQuery2.getString(cursorRawQuery2.getColumnIndex("location_data"));
                                if (!TextUtils.isEmpty(string)) {
                                    if (string.length() >= 8) {
                                        byte[] bArrM1220a = C0853d.m1220a(string);
                                        long j = Long.parseLong(string.substring(string.length() - 8, string.length()), 16);
                                        StringBuffer stringBuffer11 = new StringBuffer("insert into ");
                                        stringBuffer11.append("trace_location(");
                                        stringBuffer11.append("loc_time, ");
                                        stringBuffer11.append("entity_name, ");
                                        stringBuffer11.append("location_data) values(?,?,?);");
                                        this.f1861c.execSQL(stringBuffer11.toString(), new Object[]{Long.valueOf(j), Trace.f1161e, bArrM1220a});
                                    }
                                }
                            }
                            cursorRawQuery2.close();
                        }
                        StringBuffer stringBuffer12 = new StringBuffer("drop table ");
                        stringBuffer12.append(str6);
                        this.f1861c.execSQL(stringBuffer12.toString());
                        StringBuffer stringBuffer13 = new StringBuffer(str5);
                        stringBuffer13.append("trace_fence RENAME TO ");
                        stringBuffer13.append("trace_fence_TEMP;");
                        this.f1861c.execSQL(stringBuffer13.toString());
                        this.f1861c.execSQL(stringBuffer7.toString());
                        StringBuffer stringBuffer14 = new StringBuffer(str4);
                        stringBuffer14.append("trace_fence_TEMP;");
                        cursorRawQuery = this.f1861c.rawQuery(stringBuffer14.toString(), null);
                        if (cursorRawQuery != null) {
                            while (cursorRawQuery.moveToNext()) {
                                try {
                                    try {
                                        long j2 = cursorRawQuery.getLong(0);
                                        String string2 = cursorRawQuery.getString(1);
                                        int iIndexOf = string2.indexOf("[");
                                        int iIndexOf2 = string2.indexOf("]");
                                        if (iIndexOf > 0 && iIndexOf2 > 0) {
                                            String[] strArrSplit = string2.substring(iIndexOf + 1, iIndexOf2).split(";");
                                            ContentValues contentValues = new ContentValues();
                                            String str7 = str4;
                                            contentValues.put("monitored_person", Trace.f1161e);
                                            contentValues.put("fence_shape", FenceShape.circle.name());
                                            contentValues.put("coord_type", CoordType.bd09ll.name());
                                            contentValues.put("create_time", C0854e.m1237c());
                                            contentValues.put("modify_time", C0854e.m1237c());
                                            CircleFence circleFenceBuildLocalFence = CircleFence.buildLocalFence(j2, null, null, null, 0.0d, 0, null);
                                            int length = strArrSplit.length;
                                            String str8 = str5;
                                            String str9 = str6;
                                            int i4 = 0;
                                            while (true) {
                                                stringBuffer4 = stringBuffer6;
                                                if (i4 >= length) {
                                                    break;
                                                }
                                                StringBuffer stringBuffer15 = stringBuffer7;
                                                String[] strArrSplit2 = strArrSplit[i4].split("=");
                                                String[] strArr3 = strArrSplit;
                                                if (strArrSplit2.length == 2) {
                                                    String strTrim = strArrSplit2[0].trim();
                                                    String strTrim2 = strArrSplit2[1].trim();
                                                    if (BaseFragment.DATA_NAME.equals(strTrim)) {
                                                        contentValues.put("fence_name", strTrim2);
                                                    } else if ("center".equals(strTrim)) {
                                                        String[] strArrSplit3 = strTrim2.split(",");
                                                        if (strArrSplit3.length == 2) {
                                                            stringBuffer5 = stringBuffer8;
                                                            i2 = i3;
                                                            circleFenceBuildLocalFence.setCenter(new LatLng(Double.parseDouble(strArrSplit3[0]), Double.parseDouble(strArrSplit3[1])));
                                                        }
                                                    } else {
                                                        i2 = i3;
                                                        stringBuffer5 = stringBuffer8;
                                                        if ("radius".equals(strTrim)) {
                                                            circleFenceBuildLocalFence.setRadius(Double.parseDouble(strTrim2));
                                                        } else if ("precision".equals(strTrim)) {
                                                            contentValues.put("denoise", strTrim2);
                                                        }
                                                    }
                                                    i2 = i3;
                                                    stringBuffer5 = stringBuffer8;
                                                } else {
                                                    i2 = i3;
                                                    stringBuffer5 = stringBuffer8;
                                                }
                                                i4++;
                                                stringBuffer8 = stringBuffer5;
                                                stringBuffer6 = stringBuffer4;
                                                stringBuffer7 = stringBuffer15;
                                                strArrSplit = strArr3;
                                                i3 = i2;
                                            }
                                            StringBuffer stringBuffer16 = stringBuffer7;
                                            int i5 = i3;
                                            StringBuffer stringBuffer17 = stringBuffer8;
                                            StringBuffer stringBuffer18 = new StringBuffer();
                                            C0791a.m981a(circleFenceBuildLocalFence, (StringBuffer) null, stringBuffer18);
                                            contentValues.put("fence_extern_info", stringBuffer18.toString());
                                            if (j2 > 0) {
                                                try {
                                                    ContentValues contentValues2 = new ContentValues();
                                                    contentValues2.put("seq", Long.valueOf(j2 - 1));
                                                    StringBuffer stringBuffer19 = new StringBuffer(BaseFragment.DATA_NAME);
                                                    stringBuffer19.append(" like ? ");
                                                    this.f1861c.update("sqlite_sequence", contentValues2, stringBuffer19.toString(), new String[]{"trace_fence"});
                                                } catch (Exception unused) {
                                                }
                                            }
                                            this.f1861c.insert("trace_fence", null, contentValues);
                                            stringBuffer8 = stringBuffer17;
                                            str5 = str8;
                                            str6 = str9;
                                            stringBuffer6 = stringBuffer4;
                                            stringBuffer7 = stringBuffer16;
                                            i3 = i5;
                                            str4 = str7;
                                        }
                                    } catch (Exception unused2) {
                                        if (cursorRawQuery != null) {
                                            cursorRawQuery.close();
                                            return;
                                        }
                                        return;
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    if (cursorRawQuery != null) {
                                        cursorRawQuery.close();
                                    }
                                    throw th;
                                }
                            }
                            str = str4;
                            str2 = str5;
                            str3 = str6;
                            stringBuffer = stringBuffer6;
                            stringBuffer2 = stringBuffer7;
                            i = i3;
                            stringBuffer3 = stringBuffer8;
                            strArr = null;
                            cursorRawQuery.close();
                            cursor = null;
                        } else {
                            str = str4;
                            str2 = str5;
                            str3 = str6;
                            stringBuffer = stringBuffer6;
                            stringBuffer2 = stringBuffer7;
                            i = i3;
                            stringBuffer3 = stringBuffer8;
                            strArr = null;
                            cursor = cursorRawQuery;
                        }
                        StringBuffer stringBuffer20 = new StringBuffer("drop table ");
                        stringBuffer20.append("trace_fence_TEMP;");
                        this.f1861c.execSQL(stringBuffer20.toString());
                    }
                    i3 = i + 1;
                    strArr2 = strArr;
                    stringBuffer8 = stringBuffer3;
                    str5 = str2;
                    str6 = str3;
                    stringBuffer6 = stringBuffer;
                    stringBuffer7 = stringBuffer2;
                    str4 = str;
                } catch (Exception unused3) {
                    cursorRawQuery = cursor;
                } catch (Throwable th2) {
                    th = th2;
                    cursorRawQuery = cursor;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception unused4) {
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
