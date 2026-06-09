package com.tencent.open.p027b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.utils.C2084d;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.tencent.open.b.f */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2071f extends SQLiteOpenHelper {

    /* JADX INFO: renamed from: a */
    protected static final String[] f3194a = {"key"};

    /* JADX INFO: renamed from: b */
    protected static C2071f f3195b;

    /* JADX INFO: renamed from: a */
    public static synchronized C2071f m2168a() {
        if (f3195b == null) {
            f3195b = new C2071f(C2084d.m2215a());
        }
        return f3195b;
    }

    public C2071f(Context context) {
        super(context, "sdk_report.db", (SQLiteDatabase.CursorFactory) null, 2);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS via_cgi_report( _id INTEGER PRIMARY KEY,key TEXT,type TEXT,blob BLOB);");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS via_cgi_report");
        onCreate(sQLiteDatabase);
    }

    /* JADX INFO: renamed from: a */
    public synchronized List<Serializable> m2169a(String str) {
        ObjectInputStream objectInputStream;
        Serializable serializable;
        List<Serializable> listSynchronizedList = Collections.synchronizedList(new ArrayList());
        if (TextUtils.isEmpty(str)) {
            return listSynchronizedList;
        }
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (readableDatabase == null) {
            return listSynchronizedList;
        }
        Cursor cursor = null;
        ObjectInputStream objectInputStream2 = null;
        cursor = null;
        try {
            try {
                Cursor cursorQuery = readableDatabase.query("via_cgi_report", null, "type = ?", new String[]{str}, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.getCount() > 0) {
                            cursorQuery.moveToFirst();
                            do {
                                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(cursorQuery.getBlob(cursorQuery.getColumnIndex("blob")));
                                try {
                                    objectInputStream = new ObjectInputStream(byteArrayInputStream);
                                } catch (Exception unused) {
                                    objectInputStream = null;
                                } catch (Throwable th) {
                                    th = th;
                                }
                                try {
                                    serializable = (Serializable) objectInputStream.readObject();
                                    try {
                                        objectInputStream.close();
                                    } catch (IOException unused2) {
                                    }
                                    try {
                                        byteArrayInputStream.close();
                                    } catch (IOException unused3) {
                                    }
                                } catch (Exception unused4) {
                                    if (objectInputStream != null) {
                                        try {
                                            objectInputStream.close();
                                        } catch (IOException unused5) {
                                        }
                                    }
                                    try {
                                        byteArrayInputStream.close();
                                    } catch (IOException unused6) {
                                    }
                                    serializable = null;
                                } catch (Throwable th2) {
                                    th = th2;
                                    objectInputStream2 = objectInputStream;
                                    if (objectInputStream2 != null) {
                                        try {
                                            objectInputStream2.close();
                                        } catch (IOException unused7) {
                                        }
                                    }
                                    try {
                                        byteArrayInputStream.close();
                                        throw th;
                                    } catch (IOException unused8) {
                                        throw th;
                                    }
                                }
                                if (serializable != null) {
                                    listSynchronizedList.add(serializable);
                                }
                            } while (cursorQuery.moveToNext());
                        }
                    } catch (Exception e) {
                        e = e;
                        cursor = cursorQuery;
                        C2061f.m2131b("openSDK_LOG.ReportDatabaseHelper", "getReportItemFromDB has exception.", e);
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (readableDatabase != null) {
                        }
                        return listSynchronizedList;
                    } catch (Throwable th3) {
                        th = th3;
                        cursor = cursorQuery;
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (readableDatabase != null) {
                            readableDatabase.close();
                        }
                        throw th;
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Exception e2) {
            e = e2;
        }
        if (readableDatabase != null) {
            readableDatabase.close();
        }
        return listSynchronizedList;
    }

    /* JADX INFO: renamed from: a */
    public synchronized void m2170a(String str, List<Serializable> list) {
        ObjectOutputStream objectOutputStream;
        int size = list.size();
        if (size == 0) {
            return;
        }
        if (size > 20) {
            size = 20;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        m2171b(str);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        if (writableDatabase == null) {
            return;
        }
        writableDatabase.beginTransaction();
        try {
            try {
                ContentValues contentValues = new ContentValues();
                for (int i = 0; i < size; i++) {
                    Serializable serializable = list.get(i);
                    if (serializable != null) {
                        contentValues.put("type", str);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
                        ObjectOutputStream objectOutputStream2 = null;
                        try {
                            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                        } catch (IOException unused) {
                            objectOutputStream = null;
                        } catch (Throwable th) {
                            th = th;
                        }
                        try {
                            objectOutputStream.writeObject(serializable);
                            try {
                                objectOutputStream.close();
                            } catch (IOException unused2) {
                            }
                        } catch (IOException unused3) {
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            objectOutputStream2 = objectOutputStream;
                            if (objectOutputStream2 != null) {
                                try {
                                    objectOutputStream2.close();
                                } catch (IOException unused4) {
                                }
                            }
                            try {
                                byteArrayOutputStream.close();
                                throw th;
                            } catch (IOException unused5) {
                                throw th;
                            }
                        }
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException unused6) {
                        }
                        contentValues.put("blob", byteArrayOutputStream.toByteArray());
                        writableDatabase.insert("via_cgi_report", null, contentValues);
                    }
                    contentValues.clear();
                }
                writableDatabase.setTransactionSuccessful();
            } catch (Exception unused7) {
                C2061f.m2135e("openSDK_LOG.ReportDatabaseHelper", "saveReportItemToDB has exception.");
                writableDatabase.endTransaction();
                if (writableDatabase != null) {
                }
            }
        } finally {
            writableDatabase.endTransaction();
            if (writableDatabase != null) {
                writableDatabase.close();
            }
        }
    }

    /* JADX INFO: renamed from: b */
    public synchronized void m2171b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            if (writableDatabase == null) {
                return;
            }
            try {
                writableDatabase.delete("via_cgi_report", "type = ?", new String[]{str});
            } catch (Exception e) {
                C2061f.m2131b("openSDK_LOG.ReportDatabaseHelper", "clearReportItem has exception.", e);
                if (writableDatabase != null) {
                }
                return;
            }
            return;
        } finally {
            if (writableDatabase != null) {
                writableDatabase.close();
            }
        }
    }
}
