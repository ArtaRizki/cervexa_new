package com.baidu.lbsapi.auth;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import com.baidu.android.bbalbs.common.util.CommonParam;
import com.jieli.stream.p016dv.running2.util.IConstant;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes.dex */
public class LBSAuthManager {
    public static final int CODE_AUTHENTICATE_SUCC = 0;
    public static final int CODE_AUTHENTICATING = 602;
    public static final int CODE_INNER_ERROR = -1;
    public static final int CODE_KEY_NOT_EXIST = 101;
    public static final int CODE_NETWORK_FAILED = -11;
    public static final int CODE_NETWORK_INVALID = -10;
    public static final int CODE_UNAUTHENTICATE = 601;
    public static final String VERSION = "1.0.20";

    /* JADX INFO: renamed from: a */
    private static Context f112a;

    /* JADX INFO: renamed from: d */
    private static C0656l f113d;

    /* JADX INFO: renamed from: e */
    private static int f114e;

    /* JADX INFO: renamed from: f */
    private static Hashtable<String, LBSAuthManagerListener> f115f = new Hashtable<>();

    /* JADX INFO: renamed from: g */
    private static LBSAuthManager f116g;

    /* JADX INFO: renamed from: b */
    private C0647c f117b = null;

    /* JADX INFO: renamed from: c */
    private C0649e f118c = null;

    /* JADX INFO: renamed from: h */
    private final Handler f119h = new HandlerC0652h(this, Looper.getMainLooper());

    private LBSAuthManager(Context context) {
        f112a = context;
        C0656l c0656l = f113d;
        if (c0656l != null && !c0656l.isAlive()) {
            f113d = null;
        }
        C0645a.m131b("BaiduApiAuth SDK Version:1.0.20");
        m127d();
    }

    /* JADX INFO: renamed from: a */
    private int m111a(String str) {
        JSONObject jSONObject;
        int i = -1;
        try {
            jSONObject = new JSONObject(str);
            if (!jSONObject.has("status")) {
                jSONObject.put("status", -1);
            }
            i = jSONObject.getInt("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jSONObject.has("current") && i == 0) {
            long j = jSONObject.getLong("current");
            long jCurrentTimeMillis = System.currentTimeMillis();
            if ((jCurrentTimeMillis - j) / 3600000.0d >= 24.0d) {
                i = 601;
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if (!simpleDateFormat.format(Long.valueOf(jCurrentTimeMillis)).equals(simpleDateFormat.format(Long.valueOf(j)))) {
                    i = 601;
                }
            }
            return i;
        }
        if (jSONObject.has("current") && i == 602) {
            if ((System.currentTimeMillis() - jSONObject.getLong("current")) / 1000 > 180.0d) {
                return 601;
            }
        }
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0035 A[PHI: r0 r6
  0x0035: PHI (r0v6 java.lang.String) = (r0v0 java.lang.String), (r0v0 java.lang.String), (r0v12 java.lang.String) binds: [B:36:0x0074, B:44:0x0084, B:7:0x002f] A[DONT_GENERATE, DONT_INLINE]
  0x0035: PHI (r6v10 java.io.FileInputStream) = (r6v8 java.io.FileInputStream), (r6v9 java.io.FileInputStream), (r6v13 java.io.FileInputStream) binds: [B:36:0x0074, B:44:0x0084, B:7:0x002f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.io.BufferedReader] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m112a(int r6) throws java.lang.Throwable {
        /*
            r5 = this;
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            r2.<init>()     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            java.lang.String r3 = "/proc/"
            r2.append(r3)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            r2.append(r6)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            java.lang.String r6 = "/cmdline"
            r2.append(r6)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            java.lang.String r6 = r2.toString()     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            r6.<init>(r1)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L67 java.io.FileNotFoundException -> L77
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4e java.io.FileNotFoundException -> L50
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4e java.io.FileNotFoundException -> L50
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45 java.io.FileNotFoundException -> L47
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45 java.io.FileNotFoundException -> L47
            java.lang.String r0 = r2.readLine()     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3c java.io.FileNotFoundException -> L3e
            r2.close()
            r1.close()
        L35:
            r6.close()
            goto L87
        L3a:
            r0 = move-exception
            goto L57
        L3c:
            goto L6a
        L3e:
            goto L7a
        L40:
            r2 = move-exception
            r4 = r2
            r2 = r0
            r0 = r4
            goto L57
        L45:
            r2 = r0
            goto L6a
        L47:
            r2 = r0
            goto L7a
        L49:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
            goto L57
        L4e:
            r1 = r0
            goto L69
        L50:
            r1 = r0
            goto L79
        L52:
            r6 = move-exception
            r1 = r0
            r2 = r1
            r0 = r6
            r6 = r2
        L57:
            if (r2 == 0) goto L5c
            r2.close()
        L5c:
            if (r1 == 0) goto L61
            r1.close()
        L61:
            if (r6 == 0) goto L66
            r6.close()
        L66:
            throw r0
        L67:
            r6 = r0
            r1 = r6
        L69:
            r2 = r1
        L6a:
            if (r2 == 0) goto L6f
            r2.close()
        L6f:
            if (r1 == 0) goto L74
            r1.close()
        L74:
            if (r6 == 0) goto L87
            goto L35
        L77:
            r6 = r0
            r1 = r6
        L79:
            r2 = r1
        L7a:
            if (r2 == 0) goto L7f
            r2.close()
        L7f:
            if (r1 == 0) goto L84
            r1.close()
        L84:
            if (r6 == 0) goto L87
            goto L35
        L87:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.LBSAuthManager.m112a(int):java.lang.String");
    }

    /* JADX INFO: renamed from: a */
    private String m113a(Context context) throws Throwable {
        int iMyPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == iMyPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        String strM112a = null;
        try {
            strM112a = m112a(iMyPid);
        } catch (IOException unused) {
        }
        return strM112a != null ? strM112a : f112a.getPackageName();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003c A[Catch: NameNotFoundException -> 0x004f, TryCatch #1 {NameNotFoundException -> 0x004f, blocks: (B:10:0x0036, B:12:0x003c, B:14:0x0046), top: B:25:0x0036 }] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m114a(android.content.Context r6, java.lang.String r7) {
        /*
            r5 = this;
            java.lang.String r0 = "无法在AndroidManifest.xml中获取com.baidu.android.lbs.API_KEY的值"
            java.lang.String r1 = ""
            java.lang.String r2 = r6.getPackageName()
            r3 = 101(0x65, float:1.42E-43)
            android.content.pm.PackageManager r6 = r6.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L51
            r4 = 128(0x80, float:1.8E-43)
            android.content.pm.ApplicationInfo r6 = r6.getApplicationInfo(r2, r4)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L51
            android.os.Bundle r2 = r6.metaData     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L51
            if (r2 != 0) goto L2c
            java.util.Hashtable<java.lang.String, com.baidu.lbsapi.auth.LBSAuthManagerListener> r6 = com.baidu.lbsapi.auth.LBSAuthManager.f115f     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L51
            java.lang.Object r6 = r6.get(r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L51
            com.baidu.lbsapi.auth.LBSAuthManagerListener r6 = (com.baidu.lbsapi.auth.LBSAuthManagerListener) r6     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L51
            if (r6 == 0) goto L63
            java.lang.String r2 = "AndroidManifest.xml的application中没有meta-data标签"
            java.lang.String r2 = com.baidu.lbsapi.auth.ErrorMessage.m109a(r3, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L51
            r6.onAuthResult(r3, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L51
            goto L63
        L2c:
            android.os.Bundle r6 = r6.metaData     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L51
            java.lang.String r2 = "com.baidu.lbsapi.API_KEY"
            java.lang.String r6 = r6.getString(r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L51
            if (r6 == 0) goto L3c
            boolean r1 = r6.equals(r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L4f
            if (r1 == 0) goto L4d
        L3c:
            java.util.Hashtable<java.lang.String, com.baidu.lbsapi.auth.LBSAuthManagerListener> r1 = com.baidu.lbsapi.auth.LBSAuthManager.f115f     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L4f
            java.lang.Object r1 = r1.get(r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L4f
            com.baidu.lbsapi.auth.LBSAuthManagerListener r1 = (com.baidu.lbsapi.auth.LBSAuthManagerListener) r1     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L4f
            if (r1 == 0) goto L4d
            java.lang.String r2 = com.baidu.lbsapi.auth.ErrorMessage.m109a(r3, r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L4f
            r1.onAuthResult(r3, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L4f
        L4d:
            r1 = r6
            goto L63
        L4f:
            r1 = r6
            goto L52
        L51:
        L52:
            java.util.Hashtable<java.lang.String, com.baidu.lbsapi.auth.LBSAuthManagerListener> r6 = com.baidu.lbsapi.auth.LBSAuthManager.f115f
            java.lang.Object r6 = r6.get(r7)
            com.baidu.lbsapi.auth.LBSAuthManagerListener r6 = (com.baidu.lbsapi.auth.LBSAuthManagerListener) r6
            if (r6 == 0) goto L63
            java.lang.String r7 = com.baidu.lbsapi.auth.ErrorMessage.m109a(r3, r0)
            r6.onAuthResult(r3, r7)
        L63:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.LBSAuthManager.m114a(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0028 A[Catch: JSONException -> 0x0066, all -> 0x00bc, TryCatch #0 {JSONException -> 0x0066, blocks: (B:7:0x000e, B:9:0x001b, B:10:0x0020, B:12:0x0028, B:13:0x0031, B:15:0x0040, B:16:0x0045), top: B:33:0x000e, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0040 A[Catch: JSONException -> 0x0066, all -> 0x00bc, TryCatch #0 {JSONException -> 0x0066, blocks: (B:7:0x000e, B:9:0x001b, B:10:0x0020, B:12:0x0028, B:13:0x0031, B:15:0x0040, B:16:0x0045), top: B:33:0x000e, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0094 A[Catch: all -> 0x00bc, TryCatch #1 {, blocks: (B:4:0x0003, B:5:0x0007, B:7:0x000e, B:9:0x001b, B:10:0x0020, B:12:0x0028, B:13:0x0031, B:15:0x0040, B:16:0x0045, B:20:0x0085, B:22:0x0094, B:23:0x00aa, B:25:0x00ae, B:27:0x00b7, B:19:0x0067), top: B:35:0x0003, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ae A[Catch: all -> 0x00bc, TryCatch #1 {, blocks: (B:4:0x0003, B:5:0x0007, B:7:0x000e, B:9:0x001b, B:10:0x0020, B:12:0x0028, B:13:0x0031, B:15:0x0040, B:16:0x0045, B:20:0x0085, B:22:0x0094, B:23:0x00aa, B:25:0x00ae, B:27:0x00b7, B:19:0x0067), top: B:35:0x0003, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001b A[Catch: JSONException -> 0x0066, all -> 0x00bc, TryCatch #0 {JSONException -> 0x0066, blocks: (B:7:0x000e, B:9:0x001b, B:10:0x0020, B:12:0x0028, B:13:0x0031, B:15:0x0040, B:16:0x0045), top: B:33:0x000e, outer: #1 }] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void m119a(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            if (r6 != 0) goto L7
            java.lang.String r6 = r5.m128e()     // Catch: java.lang.Throwable -> Lbc
        L7:
            android.os.Handler r0 = r5.f119h     // Catch: java.lang.Throwable -> Lbc
            android.os.Message r0 = r0.obtainMessage()     // Catch: java.lang.Throwable -> Lbc
            r1 = -1
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
            r2.<init>(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
            java.lang.String r6 = "status"
            boolean r6 = r2.has(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
            if (r6 != 0) goto L20
            java.lang.String r6 = "status"
            r2.put(r6, r1)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
        L20:
            java.lang.String r6 = "current"
            boolean r6 = r2.has(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
            if (r6 != 0) goto L31
            java.lang.String r6 = "current"
            long r3 = java.lang.System.currentTimeMillis()     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
            r2.put(r6, r3)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
        L31:
            java.lang.String r6 = r2.toString()     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
            r5.m126c(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
            java.lang.String r6 = "current"
            boolean r6 = r2.has(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
            if (r6 == 0) goto L45
            java.lang.String r6 = "current"
            r2.remove(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
        L45:
            java.lang.String r6 = "status"
            int r1 = r2.getInt(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
            r0.what = r1     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
            java.lang.String r6 = r2.toString()     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
            r0.obj = r6     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
            android.os.Bundle r6 = new android.os.Bundle     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
            r6.<init>()     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
            java.lang.String r2 = "listenerKey"
            r6.putString(r2, r7)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
            r0.setData(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
            android.os.Handler r6 = r5.f119h     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
            r6.sendMessage(r0)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lbc
            goto L85
        L66:
            r6 = move-exception
            r6.printStackTrace()     // Catch: java.lang.Throwable -> Lbc
            r0.what = r1     // Catch: java.lang.Throwable -> Lbc
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch: java.lang.Throwable -> Lbc
            r6.<init>()     // Catch: java.lang.Throwable -> Lbc
            r0.obj = r6     // Catch: java.lang.Throwable -> Lbc
            android.os.Bundle r6 = new android.os.Bundle     // Catch: java.lang.Throwable -> Lbc
            r6.<init>()     // Catch: java.lang.Throwable -> Lbc
            java.lang.String r1 = "listenerKey"
            r6.putString(r1, r7)     // Catch: java.lang.Throwable -> Lbc
            r0.setData(r6)     // Catch: java.lang.Throwable -> Lbc
            android.os.Handler r6 = r5.f119h     // Catch: java.lang.Throwable -> Lbc
            r6.sendMessage(r0)     // Catch: java.lang.Throwable -> Lbc
        L85:
            com.baidu.lbsapi.auth.l r6 = com.baidu.lbsapi.auth.LBSAuthManager.f113d     // Catch: java.lang.Throwable -> Lbc
            r6.m167c()     // Catch: java.lang.Throwable -> Lbc
            int r6 = com.baidu.lbsapi.auth.LBSAuthManager.f114e     // Catch: java.lang.Throwable -> Lbc
            int r6 = r6 + (-1)
            com.baidu.lbsapi.auth.LBSAuthManager.f114e = r6     // Catch: java.lang.Throwable -> Lbc
            boolean r6 = com.baidu.lbsapi.auth.C0645a.f120a     // Catch: java.lang.Throwable -> Lbc
            if (r6 == 0) goto Laa
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbc
            r6.<init>()     // Catch: java.lang.Throwable -> Lbc
            java.lang.String r7 = "httpRequest called mAuthCounter-- = "
            r6.append(r7)     // Catch: java.lang.Throwable -> Lbc
            int r7 = com.baidu.lbsapi.auth.LBSAuthManager.f114e     // Catch: java.lang.Throwable -> Lbc
            r6.append(r7)     // Catch: java.lang.Throwable -> Lbc
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> Lbc
            com.baidu.lbsapi.auth.C0645a.m130a(r6)     // Catch: java.lang.Throwable -> Lbc
        Laa:
            int r6 = com.baidu.lbsapi.auth.LBSAuthManager.f114e     // Catch: java.lang.Throwable -> Lbc
            if (r6 != 0) goto Lba
            com.baidu.lbsapi.auth.l r6 = com.baidu.lbsapi.auth.LBSAuthManager.f113d     // Catch: java.lang.Throwable -> Lbc
            r6.m165a()     // Catch: java.lang.Throwable -> Lbc
            com.baidu.lbsapi.auth.l r6 = com.baidu.lbsapi.auth.LBSAuthManager.f113d     // Catch: java.lang.Throwable -> Lbc
            if (r6 == 0) goto Lba
            r6 = 0
            com.baidu.lbsapi.auth.LBSAuthManager.f113d = r6     // Catch: java.lang.Throwable -> Lbc
        Lba:
            monitor-exit(r5)
            return
        Lbc:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.LBSAuthManager.m119a(java.lang.String, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m120a(boolean z, String str, Hashtable<String, String> hashtable, String str2) {
        String strM75a;
        String strM140c;
        String strM133a;
        String strM114a = m114a(f112a, str2);
        if (strM114a == null || strM114a.equals("")) {
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("url", "https://api.map.baidu.com/sdkcs/verify");
        C0645a.m130a("url:https://api.map.baidu.com/sdkcs/verify");
        map.put("output", "json");
        map.put("ak", strM114a);
        C0645a.m130a("ak:" + strM114a);
        map.put("mcode", C0646b.m134a(f112a));
        map.put("from", "lbs_yunsdk");
        if (hashtable != null && hashtable.size() > 0) {
            for (Map.Entry<String, String> entry : hashtable.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                    map.put(key, value);
                }
            }
        }
        try {
            strM75a = CommonParam.m75a(f112a);
        } catch (Exception unused) {
            strM75a = "";
        }
        C0645a.m130a("cuid:" + strM75a);
        if (TextUtils.isEmpty(strM75a)) {
            map.put("cuid", "");
        } else {
            map.put("cuid", strM75a);
        }
        map.put("pcn", f112a.getPackageName());
        map.put(IConstant.VERSION, VERSION);
        try {
            strM140c = C0646b.m140c(f112a);
        } catch (Exception unused2) {
            strM140c = "";
        }
        if (TextUtils.isEmpty(strM140c)) {
            map.put("macaddr", "");
        } else {
            map.put("macaddr", strM140c);
        }
        try {
            strM133a = C0646b.m133a();
        } catch (Exception unused3) {
            strM133a = "";
        }
        if (TextUtils.isEmpty(strM133a)) {
            map.put(IjkMediaMeta.IJKM_KEY_LANGUAGE, "");
        } else {
            map.put(IjkMediaMeta.IJKM_KEY_LANGUAGE, strM133a);
        }
        if (z) {
            map.put("force", z ? "1" : "0");
        }
        if (str == null) {
            map.put("from_service", "");
        } else {
            map.put("from_service", str);
        }
        C0647c c0647c = new C0647c(f112a);
        this.f117b = c0647c;
        c0647c.m149a(map, new C0654j(this, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m121a(boolean z, String str, Hashtable<String, String> hashtable, String[] strArr, String str2) {
        String strM75a;
        String strM140c;
        String strM133a;
        String strM114a = m114a(f112a, str2);
        if (strM114a == null || strM114a.equals("")) {
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("url", "https://api.map.baidu.com/sdkcs/verify");
        map.put("output", "json");
        map.put("ak", strM114a);
        map.put("from", "lbs_yunsdk");
        if (hashtable != null && hashtable.size() > 0) {
            for (Map.Entry<String, String> entry : hashtable.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                    map.put(key, value);
                }
            }
        }
        try {
            strM75a = CommonParam.m75a(f112a);
        } catch (Exception unused) {
            strM75a = "";
        }
        if (TextUtils.isEmpty(strM75a)) {
            map.put("cuid", "");
        } else {
            map.put("cuid", strM75a);
        }
        map.put("pcn", f112a.getPackageName());
        map.put(IConstant.VERSION, VERSION);
        try {
            strM140c = C0646b.m140c(f112a);
        } catch (Exception unused2) {
            strM140c = "";
        }
        if (TextUtils.isEmpty(strM140c)) {
            map.put("macaddr", "");
        } else {
            map.put("macaddr", strM140c);
        }
        try {
            strM133a = C0646b.m133a();
        } catch (Exception unused3) {
            strM133a = "";
        }
        if (TextUtils.isEmpty(strM133a)) {
            map.put(IjkMediaMeta.IJKM_KEY_LANGUAGE, "");
        } else {
            map.put(IjkMediaMeta.IJKM_KEY_LANGUAGE, strM133a);
        }
        if (z) {
            map.put("force", z ? "1" : "0");
        }
        if (str == null) {
            map.put("from_service", "");
        } else {
            map.put("from_service", str);
        }
        C0649e c0649e = new C0649e(f112a);
        this.f118c = c0649e;
        c0649e.m156a(map, strArr, new C0655k(this, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public boolean m124b(String str) {
        String string;
        String strM114a = m114a(f112a, str);
        try {
            JSONObject jSONObject = new JSONObject(m128e());
            if (!jSONObject.has("ak")) {
                return true;
            }
            string = jSONObject.getString("ak");
        } catch (JSONException e) {
            e.printStackTrace();
            string = "";
        }
        return (strM114a == null || string == null || strM114a.equals(string)) ? false : true;
    }

    /* JADX INFO: renamed from: c */
    private void m126c(String str) {
        f112a.getSharedPreferences("authStatus_" + m113a(f112a), 0).edit().putString("status", str).commit();
    }

    /* JADX INFO: renamed from: d */
    private void m127d() {
        synchronized (LBSAuthManager.class) {
            if (f113d == null) {
                C0656l c0656l = new C0656l("auth");
                f113d = c0656l;
                c0656l.start();
                while (f113d.f145a == null) {
                    try {
                        if (C0645a.f120a) {
                            C0645a.m130a("wait for create auth thread.");
                        }
                        Thread.sleep(3L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: e */
    private String m128e() {
        return f112a.getSharedPreferences("authStatus_" + m113a(f112a), 0).getString("status", "{\"status\":601}");
    }

    public static LBSAuthManager getInstance(Context context) {
        if (f116g == null) {
            synchronized (LBSAuthManager.class) {
                if (f116g == null) {
                    f116g = new LBSAuthManager(context);
                }
            }
        } else if (context != null) {
            f112a = context;
        } else if (C0645a.f120a) {
            C0645a.m132c("input context is null");
            new RuntimeException("here").printStackTrace();
        }
        return f116g;
    }

    public int authenticate(boolean z, String str, Hashtable<String, String> hashtable, LBSAuthManagerListener lBSAuthManagerListener) {
        synchronized (LBSAuthManager.class) {
            String str2 = System.currentTimeMillis() + "";
            if (lBSAuthManagerListener != null) {
                f115f.put(str2, lBSAuthManagerListener);
            }
            String strM114a = m114a(f112a, str2);
            if (strM114a != null && !strM114a.equals("")) {
                f114e++;
                if (C0645a.f120a) {
                    C0645a.m130a(" mAuthCounter  ++ = " + f114e);
                }
                String strM128e = m128e();
                if (C0645a.f120a) {
                    C0645a.m130a("getAuthMessage from cache:" + strM128e);
                }
                int iM111a = m111a(strM128e);
                if (iM111a == 601) {
                    try {
                        m126c(new JSONObject().put("status", 602).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                m127d();
                if (C0645a.f120a) {
                    C0645a.m130a("mThreadLooper.mHandler = " + f113d.f145a);
                }
                if (f113d != null && f113d.f145a != null) {
                    f113d.f145a.post(new RunnableC0653i(this, iM111a, z, str2, str, hashtable));
                    return iM111a;
                }
                return -1;
            }
            return 101;
        }
    }

    public String getCUID() {
        Context context = f112a;
        if (context == null) {
            return "";
        }
        try {
            return CommonParam.m75a(context);
        } catch (Exception e) {
            if (!C0645a.f120a) {
                return "";
            }
            e.printStackTrace();
            return "";
        }
    }

    public String getKey() {
        Context context = f112a;
        if (context == null) {
            return "";
        }
        try {
            return getPublicKey(context);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getMCode() {
        Context context = f112a;
        return context == null ? "" : C0646b.m134a(context);
    }

    public String getPublicKey(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("com.baidu.lbsapi.API_KEY");
    }
}
