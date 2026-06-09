package com.baidu.trace;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.media.session.PlaybackStateCompat;
import com.baidu.trace.C0819ar;
import com.baidu.trace.api.fence.AlarmPoint;
import com.baidu.trace.api.fence.CircleFence;
import com.baidu.trace.api.fence.FenceAlarmInfo;
import com.baidu.trace.api.fence.FenceInfo;
import com.baidu.trace.api.fence.FenceShape;
import com.baidu.trace.api.fence.HistoryAlarmRequest;
import com.baidu.trace.api.fence.MonitoredAction;
import com.baidu.trace.api.fence.MonitoredStatus;
import com.baidu.trace.api.fence.MonitoredStatusInfo;
import com.baidu.trace.api.track.CacheTrackInfo;
import com.baidu.trace.model.CoordType;
import com.baidu.trace.p011b.C0829a;
import com.baidu.trace.p012c.C0854e;
import java.io.File;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.trace.am */
/* JADX INFO: loaded from: classes.dex */
public class C0814am extends Thread {

    /* JADX INFO: renamed from: a */
    private static C0876u f1270a = null;

    /* JADX INFO: renamed from: b */
    private static SQLiteDatabase f1271b = null;

    /* JADX INFO: renamed from: c */
    private static boolean f1272c = false;

    /* JADX INFO: renamed from: d */
    private static File f1273d;

    /* JADX INFO: renamed from: e */
    private static int f1274e;

    /* JADX INFO: renamed from: f */
    private int f1275f;

    /* JADX INFO: renamed from: g */
    private String f1276g;

    public C0814am(int i, String str) {
        this.f1275f = 0;
        this.f1276g = "";
        this.f1275f = i;
        this.f1276g = str;
    }

    /* JADX INFO: renamed from: a */
    protected static long m1056a(Context context, String str, String str2, CoordType coordType, int i, FenceShape fenceShape, String str3) {
        m1063a(context);
        long jInsert = -1;
        if (f1271b == null) {
            return -1L;
        }
        m1081d();
        f1271b.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("fence_name", str);
            contentValues.put("monitored_person", str2);
            contentValues.put("coord_type", coordType.name());
            contentValues.put("denoise", Integer.valueOf(i));
            contentValues.put("fence_shape", fenceShape.name());
            contentValues.put("create_time", C0854e.m1237c());
            contentValues.put("modify_time", C0854e.m1237c());
            contentValues.put("monitored_status", MonitoredStatus.unknown.name());
            contentValues.put("fence_extern_info", str3);
            jInsert = f1271b.insert("trace_fence", null, contentValues);
            f1271b.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            f1271b.endTransaction();
            throw th;
        }
        f1271b.endTransaction();
        return jInsert;
    }

    /* JADX INFO: renamed from: a */
    protected static CircleFence m1058a(Context context, long j) {
        m1063a(context);
        CircleFence circleFenceBuildLocalFence = null;
        if (f1271b == null) {
            return null;
        }
        String[] strArr = {String.valueOf(j)};
        StringBuffer stringBuffer = new StringBuffer("fence_id");
        stringBuffer.append(" = ? ");
        Cursor cursorQuery = f1271b.query("trace_fence", new String[]{"fence_id", "fence_name", "monitored_person", "coord_type", "denoise", "fence_shape", "fence_extern_info"}, stringBuffer.toString(), strArr, null, null, null);
        if (cursorQuery == null) {
            return null;
        }
        if (cursorQuery.moveToNext()) {
            long j2 = cursorQuery.getLong(0);
            String string = cursorQuery.getString(1);
            String string2 = cursorQuery.getString(2);
            CoordType coordTypeValueOf = CoordType.valueOf(cursorQuery.getString(3));
            int i = cursorQuery.getInt(4);
            String string3 = cursorQuery.getString(5);
            String string4 = cursorQuery.getString(6);
            if (!FenceShape.circle.name().equals(string3)) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return null;
            }
            circleFenceBuildLocalFence = CircleFence.buildLocalFence(j2, string, string2, null, 0.0d, i, coordTypeValueOf);
            try {
                C0791a.m1006a(new JSONObject(string4), circleFenceBuildLocalFence);
            } catch (JSONException unused) {
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return circleFenceBuildLocalFence;
    }

    /* JADX INFO: renamed from: a */
    protected static String m1059a(String str, int i, Deque<C0819ar.a> deque) {
        String[] strArr;
        String str2 = null;
        if (f1271b == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer("select * from ");
        stringBuffer.append("trace_location");
        if (!C0829a.m1126d()) {
            return null;
        }
        if (C0843bd.f1681g) {
            stringBuffer.append(" where entity_name");
            stringBuffer.append(" like ?  limit 0,?;");
            strArr = new String[]{str, String.valueOf(10)};
        } else if (C0843bd.f1682h) {
            stringBuffer.append(" where entity_name");
            stringBuffer.append(" like (select entity_name");
            stringBuffer.append(" from trace_location");
            stringBuffer.append(" limit 1) limit 0,?;");
            strArr = new String[]{String.valueOf(10)};
        } else {
            strArr = null;
        }
        Cursor cursorM1283b = C0876u.m1283b(f1271b, stringBuffer.toString(), strArr);
        if (cursorM1283b == null) {
            return null;
        }
        int count = cursorM1283b.getCount();
        C0824aw.f1619c = count;
        if (count == 0) {
            if (C0843bd.f1681g) {
                C0843bd.f1681g = false;
            } else if (C0843bd.f1682h) {
                C0843bd.f1682h = false;
            }
            cursorM1283b.close();
            return null;
        }
        while (cursorM1283b.moveToNext()) {
            long j = cursorM1283b.getLong(cursorM1283b.getColumnIndex("loc_time"));
            String string = cursorM1283b.getString(cursorM1283b.getColumnIndex("entity_name"));
            byte[] blob = cursorM1283b.getBlob(cursorM1283b.getColumnIndex("location_data"));
            if (deque != null) {
                deque.offer(new C0819ar.a(string, blob, j));
            }
            str2 = string;
        }
        cursorM1283b.close();
        return str2;
    }

    /* JADX INFO: renamed from: a */
    protected static void m1060a() {
        f1272c = false;
        if (f1270a == null && f1271b == null) {
            return;
        }
        try {
            if (f1270a != null) {
                f1270a.close();
                f1270a = null;
            }
            if (f1271b != null) {
                f1271b.close();
                f1271b = null;
            }
        } catch (Exception unused) {
        }
        f1273d = null;
    }

    /* JADX INFO: renamed from: a */
    protected static void m1061a(int i) {
        if (i < 50) {
            return;
        }
        f1274e = i;
    }

    /* JADX INFO: renamed from: a */
    protected static void m1062a(long j, MonitoredStatus monitoredStatus) {
        SQLiteDatabase sQLiteDatabase = f1271b;
        if (sQLiteDatabase == null) {
            return;
        }
        sQLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("monitored_status", monitoredStatus.name());
            StringBuffer stringBuffer = new StringBuffer("fence_id");
            stringBuffer.append(" = ? ");
            f1271b.update("trace_fence", contentValues, stringBuffer.toString(), new String[]{String.valueOf(j)});
            f1271b.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            f1271b.endTransaction();
            throw th;
        }
        f1271b.endTransaction();
    }

    /* JADX INFO: renamed from: a */
    protected static void m1063a(Context context) {
        if (context != null) {
            if (f1270a == null || f1271b == null) {
                synchronized (C0814am.class) {
                    if (f1270a == null) {
                        f1270a = new C0876u(context);
                    }
                    if (f1271b == null && !f1272c) {
                        f1272c = true;
                        new C0815an().start();
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    protected static void m1064a(Context context, HistoryAlarmRequest historyAlarmRequest, List<FenceAlarmInfo> list) {
        m1063a(context);
        if (f1271b == null) {
            return;
        }
        String[] strArr = {"fence_id", "fence_name", "monitored_person", "monitored_action", "cur_point", "pre_point"};
        ArrayList arrayList = new ArrayList();
        String monitoredPerson = historyAlarmRequest.getMonitoredPerson();
        StringBuffer stringBuffer = new StringBuffer();
        if (C0854e.m1231a(monitoredPerson)) {
            stringBuffer.append("monitored_person like ? ");
            arrayList.add(monitoredPerson);
        }
        List<Long> fenceIds = historyAlarmRequest.getFenceIds();
        if (historyAlarmRequest.getStartTime() > 0) {
            stringBuffer.append(" and create_time");
            stringBuffer.append(" >= ? ");
            arrayList.add(String.valueOf(historyAlarmRequest.getStartTime()));
        }
        if (historyAlarmRequest.getEndTime() > 0) {
            stringBuffer.append(" and create_time");
            stringBuffer.append(" <= ? ");
            arrayList.add(String.valueOf(historyAlarmRequest.getEndTime()));
        }
        if (fenceIds != null && !fenceIds.isEmpty()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            for (int i = 0; i < fenceIds.size(); i++) {
                stringBuffer2.append('?');
                stringBuffer2.append(',');
                arrayList.add(String.valueOf(fenceIds.get(i)));
            }
            stringBuffer2.deleteCharAt(stringBuffer2.length() - 1);
            stringBuffer.append(" and fence_id");
            stringBuffer.append(" in( ");
            stringBuffer.append(stringBuffer2.toString());
            stringBuffer.append(")");
        }
        String[] strArr2 = new String[arrayList.size()];
        arrayList.toArray(strArr2);
        Cursor cursorQuery = f1271b.query("trace_fence_alarm", strArr, stringBuffer.toString(), strArr2, null, null, null);
        if (cursorQuery == null) {
            return;
        }
        while (cursorQuery.moveToNext()) {
            long j = cursorQuery.getLong(0);
            String string = cursorQuery.getString(1);
            String string2 = cursorQuery.getString(2);
            if (historyAlarmRequest.getMonitoredPerson().equals(string2)) {
                MonitoredAction monitoredActionValueOf = MonitoredAction.valueOf(cursorQuery.getString(3));
                String string3 = cursorQuery.getString(4);
                String string4 = cursorQuery.getString(5);
                AlarmPoint alarmPoint = new AlarmPoint();
                AlarmPoint alarmPoint2 = new AlarmPoint();
                try {
                    C0791a.m1008a(new JSONObject(string3), CoordType.bd09ll, alarmPoint, String.class);
                    C0791a.m1008a(new JSONObject(string4), CoordType.bd09ll, alarmPoint2, String.class);
                    list.add(new FenceAlarmInfo(j, string, string2, monitoredActionValueOf, alarmPoint, alarmPoint2));
                } catch (JSONException unused) {
                }
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
    }

    /* JADX INFO: renamed from: a */
    protected static void m1065a(Context context, String str, List<Long> list, List<FenceInfo> list2) {
        String[] strArr;
        m1063a(context);
        if (f1271b == null) {
            return;
        }
        String[] strArr2 = {"fence_id", "fence_name", "monitored_person", "coord_type", "denoise", "fence_shape", "create_time", "modify_time", "fence_extern_info"};
        StringBuffer stringBuffer = new StringBuffer();
        if (list != null && !list.isEmpty()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            String[] strArr3 = new String[C0854e.m1231a(str) ? list.size() + 1 : list.size()];
            for (int i = 0; i < list.size(); i++) {
                stringBuffer2.append('?');
                stringBuffer2.append(',');
                strArr3[i] = String.valueOf(list.get(i));
            }
            stringBuffer2.deleteCharAt(stringBuffer2.length() - 1);
            stringBuffer.append("fence_id in( ");
            stringBuffer.append(stringBuffer2.toString());
            stringBuffer.append(")");
            if (C0854e.m1231a(str)) {
                stringBuffer.append(" and ");
                stringBuffer.append("monitored_person like ? ");
                strArr3[strArr3.length - 1] = str;
            }
            strArr = strArr3;
        } else if (C0854e.m1231a(str)) {
            stringBuffer.append("monitored_person like ? ");
            strArr = new String[]{str};
        } else {
            strArr = null;
        }
        Cursor cursorQuery = f1271b.query("trace_fence", strArr2, stringBuffer.toString(), strArr, null, null, null);
        if (cursorQuery == null) {
            return;
        }
        while (cursorQuery.moveToNext()) {
            try {
                long j = cursorQuery.getLong(0);
                String string = cursorQuery.getString(1);
                String string2 = cursorQuery.getString(2);
                CoordType coordTypeValueOf = CoordType.valueOf(cursorQuery.getString(3));
                int i2 = cursorQuery.getInt(4);
                String string3 = cursorQuery.getString(5);
                String string4 = cursorQuery.getString(6);
                String string5 = cursorQuery.getString(7);
                JSONObject jSONObject = new JSONObject(cursorQuery.getString(8));
                if (FenceShape.circle.name().equals(string3)) {
                    CircleFence circleFenceBuildLocalFence = CircleFence.buildLocalFence(j, string, string2, null, 0.0d, i2, coordTypeValueOf);
                    C0791a.m1006a(jSONObject, circleFenceBuildLocalFence);
                    list2.add(new FenceInfo(FenceShape.circle, circleFenceBuildLocalFence, null, null, null, string4, string5));
                }
            } catch (Exception unused) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
    }

    /* JADX INFO: renamed from: a */
    protected static void m1066a(Context context, String str, String[] strArr) {
        m1063a(context);
        SQLiteDatabase sQLiteDatabase = f1271b;
        if (sQLiteDatabase == null) {
            return;
        }
        try {
            Cursor cursorQuery = sQLiteDatabase.query("trace_fence", new String[]{"fence_id", "fence_name", "monitored_person", "coord_type", "denoise", "fence_shape", "monitored_status", "fence_extern_info"}, null, null, null, null, null);
            if (cursorQuery == null) {
                return;
            }
            while (cursorQuery.moveToNext()) {
                try {
                    long j = cursorQuery.getLong(0);
                    String string = cursorQuery.getString(1);
                    String string2 = cursorQuery.getString(2);
                    CoordType coordTypeValueOf = CoordType.valueOf(cursorQuery.getString(3));
                    int i = cursorQuery.getInt(4);
                    String string3 = cursorQuery.getString(5);
                    MonitoredStatus monitoredStatusValueOf = MonitoredStatus.valueOf(cursorQuery.getString(6));
                    String string4 = cursorQuery.getString(7);
                    if (FenceShape.circle.name().equals(string3)) {
                        JSONObject jSONObject = new JSONObject(string4);
                        CircleFence circleFenceBuildLocalFence = CircleFence.buildLocalFence(j, string, string2, null, 0.0d, i, coordTypeValueOf);
                        C0791a.m1006a(jSONObject, circleFenceBuildLocalFence);
                        C0819ar.f1575a.m1041a(Long.valueOf(j).longValue(), circleFenceBuildLocalFence);
                        C0819ar.f1575a.m1042a(Long.valueOf(j).longValue(), monitoredStatusValueOf);
                    }
                } catch (Exception unused) {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    throw th;
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Exception unused2) {
        }
    }

    /* JADX INFO: renamed from: a */
    protected static void m1067a(Context context, List<Long> list, String str, List<MonitoredStatusInfo> list2) {
        m1063a(context);
        if (f1271b == null) {
            return;
        }
        String[] strArr = {"fence_id", "monitored_status"};
        ArrayList arrayList = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer("monitored_person");
        stringBuffer.append(" like ? ");
        arrayList.add(str);
        if (list != null && !list.isEmpty()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            for (int i = 0; i < list.size(); i++) {
                stringBuffer2.append('?');
                stringBuffer2.append(',');
                arrayList.add(String.valueOf(list.get(i)));
            }
            stringBuffer2.deleteCharAt(stringBuffer2.length() - 1);
            stringBuffer.append(" and fence_id");
            stringBuffer.append(" in( ");
            stringBuffer.append(stringBuffer2.toString());
            stringBuffer.append(")");
        }
        String[] strArr2 = new String[arrayList.size()];
        arrayList.toArray(strArr2);
        Cursor cursorQuery = f1271b.query("trace_fence", strArr, stringBuffer.toString(), strArr2, null, null, null);
        if (cursorQuery == null) {
            return;
        }
        while (cursorQuery.moveToNext()) {
            MonitoredStatusInfo monitoredStatusInfo = new MonitoredStatusInfo();
            monitoredStatusInfo.setFenceId(cursorQuery.getLong(0));
            if (C0854e.m1231a(cursorQuery.getString(1))) {
                monitoredStatusInfo.setMonitoredStatus(MonitoredStatus.valueOf(cursorQuery.getString(1)));
            }
            list2.add(monitoredStatusInfo);
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
    }

    /* JADX INFO: renamed from: a */
    protected static void m1068a(CircleFence circleFence, MonitoredAction monitoredAction, String str, String str2) {
        if (f1271b == null) {
            return;
        }
        m1081d();
        f1271b.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("fence_id", Long.valueOf(circleFence.getFenceId()));
            contentValues.put("fence_name", circleFence.getFenceName());
            contentValues.put("monitored_person", circleFence.getMonitoredPerson());
            contentValues.put("monitored_action", monitoredAction.name());
            contentValues.put("create_time", Long.valueOf(C0854e.m1233b()));
            contentValues.put("cur_point", str);
            contentValues.put("pre_point", str2);
            f1271b.insert("trace_fence_alarm", null, contentValues);
            f1271b.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            f1271b.endTransaction();
            throw th;
        }
        f1271b.endTransaction();
    }

    /* JADX INFO: renamed from: a */
    private static void m1069a(String str) {
        if (f1271b == null) {
            return;
        }
        Cursor cursorM1283b = C0876u.m1283b(f1271b, "select distinct entity_name from trace_location", null);
        if (cursorM1283b == null) {
            return;
        }
        C0843bd.f1682h = cursorM1283b.getCount() > 0;
        while (true) {
            if (!cursorM1283b.moveToNext()) {
                break;
            } else if (str.equals(cursorM1283b.getString(0))) {
                C0843bd.f1681g = true;
                break;
            }
        }
        cursorM1283b.close();
    }

    /* JADX INFO: renamed from: a */
    protected static void m1070a(String str, int i) {
        SQLiteDatabase sQLiteDatabase = f1271b;
        if (sQLiteDatabase == null) {
            return;
        }
        sQLiteDatabase.beginTransaction();
        try {
            StringBuffer stringBuffer = new StringBuffer("delete from ");
            stringBuffer.append("trace_location where rowid in(select rowid from ");
            stringBuffer.append("trace_location where ");
            stringBuffer.append("entity_name like ? limit 0,?);");
            C0876u.m1281a(f1271b, stringBuffer.toString(), new String[]{str, String.valueOf(i)});
            f1271b.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            f1271b.endTransaction();
            throw th;
        }
        f1271b.endTransaction();
    }

    /* JADX INFO: renamed from: a */
    private static void m1071a(Queue<C0819ar.a> queue) {
        if (f1271b == null) {
            return;
        }
        m1081d();
        f1271b.beginTransaction();
        try {
            Iterator<C0819ar.a> it = queue.iterator();
            while (it.hasNext()) {
                C0819ar.a next = it.next();
                StringBuffer stringBuffer = new StringBuffer("insert into ");
                stringBuffer.append("trace_location(");
                stringBuffer.append("loc_time, ");
                stringBuffer.append("entity_name, ");
                stringBuffer.append("location_data) values(?,?,?);");
                f1271b.execSQL(stringBuffer.toString(), new Object[]{Long.valueOf(next.f1597c), next.f1595a, next.f1596b});
                it.remove();
            }
            f1271b.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            f1271b.endTransaction();
            throw th;
        }
        f1271b.endTransaction();
    }

    /* JADX INFO: renamed from: a */
    protected static void m1072a(boolean z) {
        LinkedList linkedList = new LinkedList();
        if (!C0843bd.f1681g && C0824aw.f1617a != null && !C0824aw.f1617a.isEmpty()) {
            linkedList.addAll(C0824aw.f1617a);
            C0824aw.f1617a.removeAll(linkedList);
        }
        if (C0843bd.f1678c != null) {
            linkedList.addAll(C0843bd.f1678c);
            C0843bd.f1678c.removeAll(linkedList);
        }
        if (!linkedList.isEmpty()) {
            m1071a(linkedList);
        }
        linkedList.clear();
        if (z) {
            m1060a();
        }
    }

    /* JADX INFO: renamed from: a */
    protected static boolean m1073a(Context context, long j, String str) {
        int iDelete;
        m1063a(context);
        SQLiteDatabase sQLiteDatabase = f1271b;
        if (sQLiteDatabase == null) {
            return false;
        }
        sQLiteDatabase.beginTransaction();
        try {
            try {
                String[] strArr = {String.valueOf(j), str};
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("fence_id = ? ");
                stringBuffer.append(" and ");
                stringBuffer.append("monitored_person like ? ");
                iDelete = f1271b.delete("trace_fence", stringBuffer.toString(), strArr);
                try {
                    f1271b.setTransactionSuccessful();
                } catch (Exception unused) {
                }
            } finally {
                f1271b.endTransaction();
            }
        } catch (Exception unused2) {
            iDelete = 0;
        }
        return iDelete > 0;
    }

    /* JADX INFO: renamed from: a */
    protected static boolean m1074a(Context context, long j, String str, String str2, CoordType coordType, int i, FenceShape fenceShape, String str3) {
        int iUpdate;
        m1063a(context);
        SQLiteDatabase sQLiteDatabase = f1271b;
        if (sQLiteDatabase == null) {
            return false;
        }
        sQLiteDatabase.beginTransaction();
        try {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("fence_name", str);
                contentValues.put("monitored_person", str2);
                contentValues.put("coord_type", coordType.name());
                contentValues.put("denoise", Integer.valueOf(i));
                contentValues.put("fence_shape", fenceShape.name());
                contentValues.put("fence_extern_info", str3);
                contentValues.put("modify_time", C0854e.m1237c());
                StringBuffer stringBuffer = new StringBuffer("fence_id");
                stringBuffer.append(" = ? ");
                iUpdate = f1271b.update("trace_fence", contentValues, stringBuffer.toString(), new String[]{String.valueOf(j)});
                try {
                    f1271b.setTransactionSuccessful();
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
                iUpdate = 0;
            }
            return iUpdate > 0;
        } finally {
            f1271b.endTransaction();
        }
    }

    /* JADX INFO: renamed from: a */
    protected static boolean m1075a(Context context, String str) {
        String[] strArr;
        m1063a(context);
        SQLiteDatabase sQLiteDatabase = f1271b;
        if (sQLiteDatabase == null) {
            return false;
        }
        sQLiteDatabase.beginTransaction();
        try {
            String string = null;
            if (C0854e.m1231a(str)) {
                StringBuffer stringBuffer = new StringBuffer("monitored_person");
                stringBuffer.append(" like ? ");
                string = stringBuffer.toString();
                strArr = new String[]{str};
            } else {
                strArr = null;
            }
            f1271b.delete("trace_fence", string, strArr);
            f1271b.setTransactionSuccessful();
            return true;
        } catch (Exception unused) {
            return false;
        } finally {
            f1271b.endTransaction();
        }
    }

    /* JADX INFO: renamed from: a */
    protected static boolean m1076a(Context context, String str, List<CacheTrackInfo> list) {
        CacheTrackInfo cacheTrackInfo;
        m1063a(context);
        if (f1271b == null) {
            return false;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select entity_name");
        stringBuffer.append(", count(*), min(loc_time");
        stringBuffer.append("), max(loc_time");
        stringBuffer.append(") from trace_location");
        String[] strArr = null;
        if (C0854e.m1231a(str)) {
            stringBuffer.append(" where entity_name");
            stringBuffer.append(" like ? ");
            strArr = new String[]{str};
        } else {
            stringBuffer.append(" group by entity_name");
        }
        Cursor cursorRawQuery = f1271b.rawQuery(stringBuffer.toString(), strArr);
        if (cursorRawQuery == null) {
            return true;
        }
        while (cursorRawQuery.moveToNext()) {
            if (C0854e.m1231a(cursorRawQuery.getString(0))) {
                cacheTrackInfo = new CacheTrackInfo(cursorRawQuery.getString(0), cursorRawQuery.getInt(1), cursorRawQuery.getLong(2), cursorRawQuery.getLong(3));
            } else if (C0854e.m1231a(str)) {
                cacheTrackInfo = new CacheTrackInfo(str, cursorRawQuery.getInt(1), cursorRawQuery.getLong(2), cursorRawQuery.getLong(3));
            }
            list.add(cacheTrackInfo);
        }
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x008e  */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected static boolean m1077a(android.content.Context r13, java.util.List<java.lang.String> r14, java.util.List<com.baidu.trace.api.track.CacheTrackInfo> r15) {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.trace.C0814am.m1077a(android.content.Context, java.util.List, java.util.List):boolean");
    }

    /* JADX INFO: renamed from: b */
    static /* synthetic */ boolean m1079b(boolean z) {
        f1272c = false;
        return false;
    }

    /* JADX INFO: renamed from: d */
    private static void m1081d() {
        SQLiteDatabase sQLiteDatabase;
        if (f1271b == null || f1274e < 50) {
            return;
        }
        if (f1273d == null) {
            f1273d = new File(f1271b.getPath());
        }
        long jM1082e = m1082e();
        if (jM1082e > 0 && jM1082e / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED > f1274e && (sQLiteDatabase = f1271b) != null) {
            sQLiteDatabase.beginTransaction();
            try {
                StringBuffer stringBuffer = new StringBuffer("delete from ");
                stringBuffer.append("trace_location where rowid in(select rowid from ");
                stringBuffer.append("trace_location limit 0,?);");
                C0876u.m1281a(f1271b, stringBuffer.toString(), new String[]{String.valueOf(1000)});
                f1271b.setTransactionSuccessful();
            } catch (Exception unused) {
            } catch (Throwable th) {
                f1271b.endTransaction();
                throw th;
            }
            f1271b.endTransaction();
        }
    }

    /* JADX INFO: renamed from: e */
    private static long m1082e() {
        if (f1271b == null) {
            return 0L;
        }
        try {
            if (f1273d == null) {
                f1273d = new File(f1271b.getPath());
            }
            return f1273d.length();
        } catch (Exception unused) {
            return 0L;
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        int i = this.f1275f;
        if (i != 0) {
            if (i != 1) {
                return;
            }
            m1066a((Context) null, (String) null, (String[]) null);
            return;
        }
        String str = this.f1276g;
        SQLiteDatabase sQLiteDatabase = f1271b;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.beginTransaction();
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("entity_name", str);
                StringBuffer stringBuffer = new StringBuffer("entity_name");
                stringBuffer.append(" like ? ");
                String[] strArr = {""};
                f1271b.update("trace_location", contentValues, stringBuffer.toString(), strArr);
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("monitored_person", str);
                StringBuffer stringBuffer2 = new StringBuffer("monitored_person");
                stringBuffer2.append(" like ? ");
                f1271b.update("trace_fence", contentValues2, stringBuffer2.toString(), strArr);
                f1271b.setTransactionSuccessful();
            } catch (Exception unused) {
            } catch (Throwable th) {
                f1271b.endTransaction();
                throw th;
            }
            f1271b.endTransaction();
        }
        m1069a(this.f1276g);
        SQLiteDatabase sQLiteDatabase2 = f1271b;
        if (sQLiteDatabase2 == null) {
            return;
        }
        sQLiteDatabase2.beginTransaction();
        try {
            StringBuffer stringBuffer3 = new StringBuffer("create_time");
            stringBuffer3.append(" < ? ");
            f1271b.delete("trace_fence_alarm", stringBuffer3.toString(), new String[]{String.valueOf(C0854e.m1233b() - 604800)});
            f1271b.setTransactionSuccessful();
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            f1271b.endTransaction();
            throw th2;
        }
        f1271b.endTransaction();
    }
}
