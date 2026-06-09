package com.baidu.lbsapi.auth.tracesdk;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import com.baidu.p002a.p003a.p004a.p005a.p007b.C0634a;
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
    public static final String VERSION = "1.0.21.20161104";

    /* JADX INFO: renamed from: a */
    private static Context f148a;

    /* JADX INFO: renamed from: d */
    private static C0668l f149d;

    /* JADX INFO: renamed from: e */
    private static int f150e;

    /* JADX INFO: renamed from: f */
    private static Hashtable f151f = new Hashtable();

    /* JADX INFO: renamed from: g */
    private static LBSAuthManager f152g;

    /* JADX INFO: renamed from: b */
    private C0659c f153b = null;

    /* JADX INFO: renamed from: c */
    private C0661e f154c = null;

    /* JADX INFO: renamed from: h */
    private final Handler f155h = new HandlerC0664h(this, Looper.getMainLooper());

    private LBSAuthManager(Context context) {
        f148a = context;
        C0668l c0668l = f149d;
        if (c0668l != null && !c0668l.isAlive()) {
            f149d = null;
        }
        C0657a.m190b("BaiduApiAuth SDK Version:1.0.21.20161104");
        m186d();
    }

    /* JADX INFO: renamed from: a */
    private int m170a(String str) {
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
    /* JADX WARN: Removed duplicated region for block: B:33:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0032 A[PHI: r0 r6
  0x0032: PHI (r0v6 java.lang.String) = (r0v0 java.lang.String), (r0v0 java.lang.String), (r0v12 java.lang.String) binds: [B:36:0x0071, B:44:0x0081, B:7:0x002c] A[DONT_GENERATE, DONT_INLINE]
  0x0032: PHI (r6v10 java.io.FileInputStream) = (r6v8 java.io.FileInputStream), (r6v9 java.io.FileInputStream), (r6v13 java.io.FileInputStream) binds: [B:36:0x0071, B:44:0x0081, B:7:0x002c] A[DONT_GENERATE, DONT_INLINE]] */
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
    private java.lang.String m171a(int r6) throws java.lang.Throwable {
        /*
            r5 = this;
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L64 java.io.FileNotFoundException -> L74
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L64 java.io.FileNotFoundException -> L74
            java.lang.String r3 = "/proc/"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L64 java.io.FileNotFoundException -> L74
            r2.append(r6)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L64 java.io.FileNotFoundException -> L74
            java.lang.String r6 = "/cmdline"
            r2.append(r6)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L64 java.io.FileNotFoundException -> L74
            java.lang.String r6 = r2.toString()     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L64 java.io.FileNotFoundException -> L74
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L64 java.io.FileNotFoundException -> L74
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L64 java.io.FileNotFoundException -> L74
            r6.<init>(r1)     // Catch: java.lang.Throwable -> L4f java.io.IOException -> L64 java.io.FileNotFoundException -> L74
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L46 java.io.IOException -> L4b java.io.FileNotFoundException -> L4d
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L46 java.io.IOException -> L4b java.io.FileNotFoundException -> L4d
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L42 java.io.FileNotFoundException -> L44
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L42 java.io.FileNotFoundException -> L44
            java.lang.String r0 = r2.readLine()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L3b
            r2.close()
            r1.close()
        L32:
            r6.close()
            goto L84
        L37:
            r0 = move-exception
            goto L54
        L39:
            goto L67
        L3b:
            goto L77
        L3d:
            r2 = move-exception
            r4 = r2
            r2 = r0
            r0 = r4
            goto L54
        L42:
            r2 = r0
            goto L67
        L44:
            r2 = r0
            goto L77
        L46:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
            goto L54
        L4b:
            r1 = r0
            goto L66
        L4d:
            r1 = r0
            goto L76
        L4f:
            r6 = move-exception
            r1 = r0
            r2 = r1
            r0 = r6
            r6 = r2
        L54:
            if (r2 == 0) goto L59
            r2.close()
        L59:
            if (r1 == 0) goto L5e
            r1.close()
        L5e:
            if (r6 == 0) goto L63
            r6.close()
        L63:
            throw r0
        L64:
            r6 = r0
            r1 = r6
        L66:
            r2 = r1
        L67:
            if (r2 == 0) goto L6c
            r2.close()
        L6c:
            if (r1 == 0) goto L71
            r1.close()
        L71:
            if (r6 == 0) goto L84
            goto L32
        L74:
            r6 = r0
            r1 = r6
        L76:
            r2 = r1
        L77:
            if (r2 == 0) goto L7c
            r2.close()
        L7c:
            if (r1 == 0) goto L81
            r1.close()
        L81:
            if (r6 == 0) goto L84
            goto L32
        L84:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.tracesdk.LBSAuthManager.m171a(int):java.lang.String");
    }

    /* JADX INFO: renamed from: a */
    private String m172a(Context context) throws Throwable {
        int iMyPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == iMyPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        String strM171a = null;
        try {
            strM171a = m171a(iMyPid);
        } catch (IOException unused) {
        }
        return strM171a != null ? strM171a : f148a.getPackageName();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003c A[Catch: NameNotFoundException -> 0x004f, TryCatch #1 {NameNotFoundException -> 0x004f, blocks: (B:10:0x0036, B:12:0x003c, B:14:0x0046), top: B:25:0x0036 }] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m173a(android.content.Context r6, java.lang.String r7) {
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
            java.util.Hashtable r6 = com.baidu.lbsapi.auth.tracesdk.LBSAuthManager.f151f     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L51
            java.lang.Object r6 = r6.get(r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L51
            com.baidu.lbsapi.auth.tracesdk.LBSAuthManagerListener r6 = (com.baidu.lbsapi.auth.tracesdk.LBSAuthManagerListener) r6     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L51
            if (r6 == 0) goto L63
            java.lang.String r2 = "AndroidManifest.xml的application中没有meta-data标签"
            java.lang.String r2 = com.baidu.lbsapi.auth.tracesdk.ErrorMessage.m168a(r3, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L51
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
            java.util.Hashtable r1 = com.baidu.lbsapi.auth.tracesdk.LBSAuthManager.f151f     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L4f
            java.lang.Object r1 = r1.get(r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L4f
            com.baidu.lbsapi.auth.tracesdk.LBSAuthManagerListener r1 = (com.baidu.lbsapi.auth.tracesdk.LBSAuthManagerListener) r1     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L4f
            if (r1 == 0) goto L4d
            java.lang.String r2 = com.baidu.lbsapi.auth.tracesdk.ErrorMessage.m168a(r3, r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L4f
            r1.onAuthResult(r3, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L4f
        L4d:
            r1 = r6
            goto L63
        L4f:
            r1 = r6
            goto L52
        L51:
        L52:
            java.util.Hashtable r6 = com.baidu.lbsapi.auth.tracesdk.LBSAuthManager.f151f
            java.lang.Object r6 = r6.get(r7)
            com.baidu.lbsapi.auth.tracesdk.LBSAuthManagerListener r6 = (com.baidu.lbsapi.auth.tracesdk.LBSAuthManagerListener) r6
            if (r6 == 0) goto L63
            java.lang.String r7 = com.baidu.lbsapi.auth.tracesdk.ErrorMessage.m168a(r3, r0)
            r6.onAuthResult(r3, r7)
        L63:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.tracesdk.LBSAuthManager.m173a(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0028 A[Catch: JSONException -> 0x0066, all -> 0x00b9, TryCatch #0 {JSONException -> 0x0066, blocks: (B:7:0x000e, B:9:0x001b, B:10:0x0020, B:12:0x0028, B:13:0x0031, B:15:0x0040, B:16:0x0045), top: B:33:0x000e, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0040 A[Catch: JSONException -> 0x0066, all -> 0x00b9, TryCatch #0 {JSONException -> 0x0066, blocks: (B:7:0x000e, B:9:0x001b, B:10:0x0020, B:12:0x0028, B:13:0x0031, B:15:0x0040, B:16:0x0045), top: B:33:0x000e, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0094 A[Catch: all -> 0x00b9, TryCatch #1 {, blocks: (B:4:0x0003, B:5:0x0007, B:7:0x000e, B:9:0x001b, B:10:0x0020, B:12:0x0028, B:13:0x0031, B:15:0x0040, B:16:0x0045, B:20:0x0085, B:22:0x0094, B:23:0x00a7, B:25:0x00ab, B:27:0x00af, B:19:0x0067), top: B:35:0x0003, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001b A[Catch: JSONException -> 0x0066, all -> 0x00b9, TryCatch #0 {JSONException -> 0x0066, blocks: (B:7:0x000e, B:9:0x001b, B:10:0x0020, B:12:0x0028, B:13:0x0031, B:15:0x0040, B:16:0x0045), top: B:33:0x000e, outer: #1 }] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void m178a(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            if (r6 != 0) goto L7
            java.lang.String r6 = r5.m187e()     // Catch: java.lang.Throwable -> Lb9
        L7:
            android.os.Handler r0 = r5.f155h     // Catch: java.lang.Throwable -> Lb9
            android.os.Message r0 = r0.obtainMessage()     // Catch: java.lang.Throwable -> Lb9
            r1 = -1
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
            r2.<init>(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
            java.lang.String r6 = "status"
            boolean r6 = r2.has(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
            if (r6 != 0) goto L20
            java.lang.String r6 = "status"
            r2.put(r6, r1)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
        L20:
            java.lang.String r6 = "current"
            boolean r6 = r2.has(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
            if (r6 != 0) goto L31
            java.lang.String r6 = "current"
            long r3 = java.lang.System.currentTimeMillis()     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
            r2.put(r6, r3)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
        L31:
            java.lang.String r6 = r2.toString()     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
            r5.m185c(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
            java.lang.String r6 = "current"
            boolean r6 = r2.has(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
            if (r6 == 0) goto L45
            java.lang.String r6 = "current"
            r2.remove(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
        L45:
            java.lang.String r6 = "status"
            int r1 = r2.getInt(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
            r0.what = r1     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
            java.lang.String r6 = r2.toString()     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
            r0.obj = r6     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
            android.os.Bundle r6 = new android.os.Bundle     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
            r6.<init>()     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
            java.lang.String r2 = "listenerKey"
            r6.putString(r2, r7)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
            r0.setData(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
            android.os.Handler r6 = r5.f155h     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
            r6.sendMessage(r0)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lb9
            goto L85
        L66:
            r6 = move-exception
            r6.printStackTrace()     // Catch: java.lang.Throwable -> Lb9
            r0.what = r1     // Catch: java.lang.Throwable -> Lb9
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch: java.lang.Throwable -> Lb9
            r6.<init>()     // Catch: java.lang.Throwable -> Lb9
            r0.obj = r6     // Catch: java.lang.Throwable -> Lb9
            android.os.Bundle r6 = new android.os.Bundle     // Catch: java.lang.Throwable -> Lb9
            r6.<init>()     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r1 = "listenerKey"
            r6.putString(r1, r7)     // Catch: java.lang.Throwable -> Lb9
            r0.setData(r6)     // Catch: java.lang.Throwable -> Lb9
            android.os.Handler r6 = r5.f155h     // Catch: java.lang.Throwable -> Lb9
            r6.sendMessage(r0)     // Catch: java.lang.Throwable -> Lb9
        L85:
            com.baidu.lbsapi.auth.tracesdk.l r6 = com.baidu.lbsapi.auth.tracesdk.LBSAuthManager.f149d     // Catch: java.lang.Throwable -> Lb9
            r6.m228c()     // Catch: java.lang.Throwable -> Lb9
            int r6 = com.baidu.lbsapi.auth.tracesdk.LBSAuthManager.f150e     // Catch: java.lang.Throwable -> Lb9
            int r6 = r6 + (-1)
            com.baidu.lbsapi.auth.tracesdk.LBSAuthManager.f150e = r6     // Catch: java.lang.Throwable -> Lb9
            boolean r6 = com.baidu.lbsapi.auth.tracesdk.C0657a.f156a     // Catch: java.lang.Throwable -> Lb9
            if (r6 == 0) goto La7
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r7 = "httpRequest called mAuthCounter-- = "
            r6.<init>(r7)     // Catch: java.lang.Throwable -> Lb9
            int r7 = com.baidu.lbsapi.auth.tracesdk.LBSAuthManager.f150e     // Catch: java.lang.Throwable -> Lb9
            r6.append(r7)     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> Lb9
            com.baidu.lbsapi.auth.tracesdk.C0657a.m189a(r6)     // Catch: java.lang.Throwable -> Lb9
        La7:
            int r6 = com.baidu.lbsapi.auth.tracesdk.LBSAuthManager.f150e     // Catch: java.lang.Throwable -> Lb9
            if (r6 != 0) goto Lb7
            com.baidu.lbsapi.auth.tracesdk.l r6 = com.baidu.lbsapi.auth.tracesdk.LBSAuthManager.f149d     // Catch: java.lang.Throwable -> Lb9
            if (r6 == 0) goto Lb7
            com.baidu.lbsapi.auth.tracesdk.l r6 = com.baidu.lbsapi.auth.tracesdk.LBSAuthManager.f149d     // Catch: java.lang.Throwable -> Lb9
            r6.m226a()     // Catch: java.lang.Throwable -> Lb9
            r6 = 0
            com.baidu.lbsapi.auth.tracesdk.LBSAuthManager.f149d = r6     // Catch: java.lang.Throwable -> Lb9
        Lb7:
            monitor-exit(r5)
            return
        Lb9:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.tracesdk.LBSAuthManager.m178a(java.lang.String, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m179a(boolean z, String str, Hashtable hashtable, String str2) {
        String strM29a;
        String strM199c;
        String strM192a;
        String strM173a = m173a(f148a, str2);
        if (strM173a == null || strM173a.equals("")) {
            return;
        }
        HashMap map = new HashMap();
        map.put("url", "https://api.map.baidu.com/sdkcs/verify");
        C0657a.m189a("url:https://api.map.baidu.com/sdkcs/verify");
        map.put("output", "json");
        map.put("ak", strM173a);
        C0657a.m189a("ak:" + strM173a);
        map.put("mcode", C0658b.m193a(f148a));
        map.put("from", "lbs_yunsdk");
        if (hashtable != null && hashtable.size() > 0) {
            for (Map.Entry entry : hashtable.entrySet()) {
                String str3 = (String) entry.getKey();
                String str4 = (String) entry.getValue();
                if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
                    map.put(str3, str4);
                }
            }
        }
        try {
            strM29a = C0634a.m29a(f148a);
        } catch (Exception unused) {
            strM29a = "";
        }
        C0657a.m189a("cuid:" + strM29a);
        if (TextUtils.isEmpty(strM29a)) {
            map.put("cuid", "");
        } else {
            map.put("cuid", strM29a);
        }
        map.put("pcn", f148a.getPackageName());
        map.put(IConstant.VERSION, VERSION);
        try {
            strM199c = C0658b.m199c(f148a);
        } catch (Exception unused2) {
            strM199c = "";
        }
        if (TextUtils.isEmpty(strM199c)) {
            map.put("macaddr", "");
        } else {
            map.put("macaddr", strM199c);
        }
        try {
            strM192a = C0658b.m192a();
        } catch (Exception unused3) {
            strM192a = "";
        }
        if (TextUtils.isEmpty(strM192a)) {
            map.put(IjkMediaMeta.IJKM_KEY_LANGUAGE, "");
        } else {
            map.put(IjkMediaMeta.IJKM_KEY_LANGUAGE, strM192a);
        }
        if (z) {
            map.put("force", z ? "1" : "0");
        }
        if (str == null) {
            map.put("from_service", "");
        } else {
            map.put("from_service", str);
        }
        C0659c c0659c = new C0659c(f148a);
        this.f153b = c0659c;
        c0659c.m208a(map, new C0666j(this, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m180a(boolean z, String str, Hashtable hashtable, String[] strArr, String str2) {
        String strM29a;
        String strM199c;
        String strM192a;
        String strM173a = m173a(f148a, str2);
        if (strM173a == null || strM173a.equals("")) {
            return;
        }
        HashMap map = new HashMap();
        map.put("url", "https://api.map.baidu.com/sdkcs/verify");
        map.put("output", "json");
        map.put("ak", strM173a);
        map.put("from", "lbs_yunsdk");
        if (hashtable != null && hashtable.size() > 0) {
            for (Map.Entry entry : hashtable.entrySet()) {
                String str3 = (String) entry.getKey();
                String str4 = (String) entry.getValue();
                if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
                    map.put(str3, str4);
                }
            }
        }
        try {
            strM29a = C0634a.m29a(f148a);
        } catch (Exception unused) {
            strM29a = "";
        }
        if (TextUtils.isEmpty(strM29a)) {
            map.put("cuid", "");
        } else {
            map.put("cuid", strM29a);
        }
        map.put("pcn", f148a.getPackageName());
        map.put(IConstant.VERSION, VERSION);
        try {
            strM199c = C0658b.m199c(f148a);
        } catch (Exception unused2) {
            strM199c = "";
        }
        if (TextUtils.isEmpty(strM199c)) {
            map.put("macaddr", "");
        } else {
            map.put("macaddr", strM199c);
        }
        try {
            strM192a = C0658b.m192a();
        } catch (Exception unused3) {
            strM192a = "";
        }
        if (TextUtils.isEmpty(strM192a)) {
            map.put(IjkMediaMeta.IJKM_KEY_LANGUAGE, "");
        } else {
            map.put(IjkMediaMeta.IJKM_KEY_LANGUAGE, strM192a);
        }
        if (z) {
            map.put("force", z ? "1" : "0");
        }
        if (str == null) {
            map.put("from_service", "");
        } else {
            map.put("from_service", str);
        }
        C0661e c0661e = new C0661e(f148a);
        this.f154c = c0661e;
        c0661e.m215a(map, strArr, new C0667k(this, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public boolean m183b(String str) {
        String string;
        String strM173a = m173a(f148a, str);
        try {
            JSONObject jSONObject = new JSONObject(m187e());
            if (!jSONObject.has("ak")) {
                return true;
            }
            string = jSONObject.getString("ak");
        } catch (JSONException e) {
            e.printStackTrace();
            string = "";
        }
        return (strM173a == null || string == null || strM173a.equals(string)) ? false : true;
    }

    /* JADX INFO: renamed from: c */
    private void m185c(String str) {
        f148a.getSharedPreferences("authStatus_" + m172a(f148a), 0).edit().putString("status", str).commit();
    }

    /* JADX INFO: renamed from: d */
    private void m186d() {
        synchronized (LBSAuthManager.class) {
            if (f149d == null) {
                C0668l c0668l = new C0668l("auth");
                f149d = c0668l;
                c0668l.start();
                while (f149d.f181a == null) {
                    try {
                        if (C0657a.f156a) {
                            C0657a.m189a("wait for create auth thread.");
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
    private String m187e() {
        return f148a.getSharedPreferences("authStatus_" + m172a(f148a), 0).getString("status", "{\"status\":601}");
    }

    public static LBSAuthManager getInstance(Context context) {
        if (f152g == null) {
            synchronized (LBSAuthManager.class) {
                if (f152g == null) {
                    f152g = new LBSAuthManager(context);
                }
            }
        } else if (context != null) {
            f148a = context;
        } else if (C0657a.f156a) {
            C0657a.m191c("input context is null");
            new RuntimeException("here").printStackTrace();
        }
        return f152g;
    }

    public int authenticate(boolean z, String str, Hashtable hashtable, LBSAuthManagerListener lBSAuthManagerListener) {
        synchronized (LBSAuthManager.class) {
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            String string = sb.toString();
            if (lBSAuthManagerListener != null) {
                f151f.put(string, lBSAuthManagerListener);
            }
            String strM173a = m173a(f148a, string);
            if (strM173a != null && !strM173a.equals("")) {
                f150e++;
                if (C0657a.f156a) {
                    C0657a.m189a(" mAuthCounter  ++ = " + f150e);
                }
                String strM187e = m187e();
                if (C0657a.f156a) {
                    C0657a.m189a("getAuthMessage from cache:" + strM187e);
                }
                int iM170a = m170a(strM187e);
                if (iM170a == 601) {
                    try {
                        m185c(new JSONObject().put("status", 602).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                m186d();
                if (C0657a.f156a) {
                    C0657a.m189a("mThreadLooper.mHandler = " + f149d.f181a);
                }
                if (f149d != null && f149d.f181a != null) {
                    f149d.f181a.post(new RunnableC0665i(this, iM170a, z, string, str, hashtable));
                    return iM170a;
                }
                return -1;
            }
            return 101;
        }
    }

    public String getCUID() {
        Context context = f148a;
        if (context == null) {
            return "";
        }
        try {
            return C0634a.m29a(context);
        } catch (Exception e) {
            if (!C0657a.f156a) {
                return "";
            }
            e.printStackTrace();
            return "";
        }
    }

    public String getDeviceID() {
        Context context = f148a;
        if (context == null) {
            return "";
        }
        try {
            return C0634a.m30b(context);
        } catch (Exception e) {
            if (!C0657a.f156a) {
                return "";
            }
            e.printStackTrace();
            return "";
        }
    }

    public String getIMEI() {
        Context context = f148a;
        if (context == null) {
            return "";
        }
        try {
            return C0634a.m31c(context);
        } catch (Exception e) {
            if (!C0657a.f156a) {
                return "";
            }
            e.printStackTrace();
            return "";
        }
    }

    public String getKey() {
        Context context = f148a;
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
        Context context = f148a;
        return context == null ? "" : C0658b.m193a(context);
    }

    public String getPublicKey(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("com.baidu.lbsapi.API_KEY");
    }
}
