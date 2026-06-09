package com.tencent.open.p027b;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.jieli.lib.p015dv.control.utils.TopicKey;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.tencent.connect.common.Constants;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.utils.C2084d;
import com.tencent.open.utils.C2085e;
import com.tencent.open.utils.C2088h;
import com.tencent.open.utils.C2089i;
import com.tencent.open.utils.HttpUtils;
import java.io.IOException;
import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import kotlin.text.Typography;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: renamed from: com.tencent.open.b.g */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2072g {

    /* JADX INFO: renamed from: a */
    protected static C2072g f3196a;

    /* JADX INFO: renamed from: e */
    protected HandlerThread f3200e;

    /* JADX INFO: renamed from: f */
    protected Handler f3201f;

    /* JADX INFO: renamed from: b */
    protected Random f3197b = new Random();

    /* JADX INFO: renamed from: d */
    protected List<Serializable> f3199d = Collections.synchronizedList(new ArrayList());

    /* JADX INFO: renamed from: c */
    protected List<Serializable> f3198c = Collections.synchronizedList(new ArrayList());

    /* JADX INFO: renamed from: g */
    protected Executor f3202g = C2088h.m2248b();

    /* JADX INFO: renamed from: h */
    protected Executor f3203h = C2088h.m2248b();

    /* JADX INFO: renamed from: a */
    public static synchronized C2072g m2172a() {
        if (f3196a == null) {
            f3196a = new C2072g();
        }
        return f3196a;
    }

    private C2072g() {
        this.f3200e = null;
        if (this.f3200e == null) {
            HandlerThread handlerThread = new HandlerThread("opensdk.report.handlerthread", 10);
            this.f3200e = handlerThread;
            handlerThread.start();
        }
        if (!this.f3200e.isAlive() || this.f3200e.getLooper() == null) {
            return;
        }
        this.f3201f = new Handler(this.f3200e.getLooper()) { // from class: com.tencent.open.b.g.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1000) {
                    C2072g.this.m2180b();
                } else if (i == 1001) {
                    C2072g.this.m2183e();
                }
                super.handleMessage(message);
            }
        };
    }

    /* JADX INFO: renamed from: a */
    public void m2174a(final Bundle bundle, String str, final boolean z) {
        if (bundle == null) {
            return;
        }
        C2061f.m2127a("openSDK_LOG.ReportManager", "-->reportVia, bundle: " + bundle.toString());
        if (m2179a("report_via", str) || z) {
            this.f3202g.execute(new Runnable() { // from class: com.tencent.open.b.g.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("uin", Constants.DEFAULT_UIN);
                        bundle2.putString("imei", C2068c.m2158b(C2084d.m2215a()));
                        bundle2.putString("imsi", C2068c.m2159c(C2084d.m2215a()));
                        bundle2.putString("android_id", C2068c.m2160d(C2084d.m2215a()));
                        bundle2.putString("mac", C2068c.m2155a());
                        bundle2.putString(Constants.PARAM_PLATFORM, "1");
                        bundle2.putString("os_ver", Build.VERSION.RELEASE);
                        bundle2.putString(IConstant.KEY_POSITION, C2089i.m2269d(C2084d.m2215a()));
                        bundle2.putString("network", C2066a.m2150a(C2084d.m2215a()));
                        bundle2.putString(IjkMediaMeta.IJKM_KEY_LANGUAGE, C2068c.m2157b());
                        bundle2.putString("resolution", C2068c.m2156a(C2084d.m2215a()));
                        bundle2.putString("apn", C2066a.m2151b(C2084d.m2215a()));
                        bundle2.putString("model_name", Build.MODEL);
                        bundle2.putString("timezone", TimeZone.getDefault().getID());
                        bundle2.putString("sdk_ver", Constants.SDK_VERSION);
                        bundle2.putString("qz_ver", C2089i.m2270d(C2084d.m2215a(), Constants.PACKAGE_QZONE));
                        bundle2.putString("qq_ver", C2089i.m2266c(C2084d.m2215a(), "com.tencent.mobileqq"));
                        bundle2.putString(TopicKey.QUA, C2089i.m2272e(C2084d.m2215a(), C2084d.m2217b()));
                        bundle2.putString("packagename", C2084d.m2217b());
                        bundle2.putString("app_ver", C2089i.m2270d(C2084d.m2215a(), C2084d.m2217b()));
                        if (bundle != null) {
                            bundle2.putAll(bundle);
                        }
                        C2072g.this.f3199d.add(new C2067b(bundle2));
                        int size = C2072g.this.f3199d.size();
                        int iM2230a = C2085e.m2221a(C2084d.m2215a(), (String) null).m2230a("Agent_ReportTimeInterval");
                        if (iM2230a == 0) {
                            iM2230a = 10000;
                        }
                        if (!C2072g.this.m2178a("report_via", size) && !z) {
                            if (C2072g.this.f3201f.hasMessages(1001)) {
                                return;
                            }
                            Message messageObtain = Message.obtain();
                            messageObtain.what = 1001;
                            C2072g.this.f3201f.sendMessageDelayed(messageObtain, iM2230a);
                            return;
                        }
                        C2072g.this.m2183e();
                        C2072g.this.f3201f.removeMessages(1001);
                    } catch (Exception e) {
                        C2061f.m2131b("openSDK_LOG.ReportManager", "--> reporVia, exception in sub thread.", e);
                    }
                }
            });
        }
    }

    /* JADX INFO: renamed from: a */
    public void m2175a(String str, long j, long j2, long j3, int i) {
        m2176a(str, j, j2, j3, i, "", false);
    }

    /* JADX INFO: renamed from: a */
    public void m2176a(final String str, final long j, final long j2, final long j3, final int i, final String str2, final boolean z) {
        C2061f.m2127a("openSDK_LOG.ReportManager", "-->reportCgi, command: " + str + " | startTime: " + j + " | reqSize:" + j2 + " | rspSize: " + j3 + " | responseCode: " + i + " | detail: " + str2);
        if (m2179a("report_cgi", "" + i) || z) {
            this.f3203h.execute(new Runnable() { // from class: com.tencent.open.b.g.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        long jElapsedRealtime = SystemClock.elapsedRealtime() - j;
                        Bundle bundle = new Bundle();
                        String strM2150a = C2066a.m2150a(C2084d.m2215a());
                        bundle.putString("apn", strM2150a);
                        bundle.putString("appid", "1000067");
                        bundle.putString("commandid", str);
                        bundle.putString("detail", str2);
                        StringBuilder sb = new StringBuilder();
                        sb.append("network=");
                        sb.append(strM2150a);
                        sb.append(Typography.amp);
                        sb.append("sdcard=");
                        int i2 = 1;
                        sb.append(Environment.getExternalStorageState().equals("mounted") ? 1 : 0);
                        sb.append(Typography.amp);
                        sb.append("wifi=");
                        sb.append(C2066a.m2154e(C2084d.m2215a()));
                        bundle.putString("deviceInfo", sb.toString());
                        int iM2173a = 100 / C2072g.this.m2173a(i);
                        if (iM2173a > 0) {
                            i2 = iM2173a > 100 ? 100 : iM2173a;
                        }
                        bundle.putString("frequency", i2 + "");
                        bundle.putString("reqSize", j2 + "");
                        bundle.putString("resultCode", i + "");
                        bundle.putString("rspSize", j3 + "");
                        bundle.putString("timeCost", jElapsedRealtime + "");
                        bundle.putString("uin", Constants.DEFAULT_UIN);
                        C2072g.this.f3198c.add(new C2067b(bundle));
                        int size = C2072g.this.f3198c.size();
                        int iM2230a = C2085e.m2221a(C2084d.m2215a(), (String) null).m2230a("Agent_ReportTimeInterval");
                        if (iM2230a == 0) {
                            iM2230a = 10000;
                        }
                        if (C2072g.this.m2178a("report_cgi", size) || z) {
                            C2072g.this.m2180b();
                            C2072g.this.f3201f.removeMessages(1000);
                        } else if (!C2072g.this.f3201f.hasMessages(1000)) {
                            Message messageObtain = Message.obtain();
                            messageObtain.what = 1000;
                            C2072g.this.f3201f.sendMessageDelayed(messageObtain, iM2230a);
                        }
                    } catch (Exception e) {
                        C2061f.m2131b("openSDK_LOG.ReportManager", "--> reportCGI, exception in sub thread.", e);
                    }
                }
            });
        }
    }

    /* JADX INFO: renamed from: b */
    protected void m2180b() {
        this.f3203h.execute(new Runnable() { // from class: com.tencent.open.b.g.4
            /* JADX WARN: Removed duplicated region for block: B:24:0x00a5 A[Catch: Exception -> 0x00b8, TryCatch #0 {Exception -> 0x00b8, blocks: (B:3:0x0008, B:6:0x0011, B:9:0x0023, B:24:0x00a5, B:25:0x00b0, B:17:0x0094, B:19:0x0099, B:21:0x009e, B:12:0x003b, B:14:0x008a), top: B:30:0x0008, inners: #1, #2, #3 }] */
            /* JADX WARN: Removed duplicated region for block: B:35:0x00a3 A[EDGE_INSN: B:35:0x00a3->B:23:0x00a3 BREAK  A[LOOP:0: B:11:0x0039->B:40:?], SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:40:? A[LOOP:0: B:11:0x0039->B:40:?, LOOP_END, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    r14 = this;
                    java.lang.String r0 = "report_cgi"
                    java.lang.String r1 = "http://wspeed.qq.com/w.cgi"
                    java.lang.String r2 = "-->doReportCgi, doupload exception"
                    java.lang.String r3 = "openSDK_LOG.ReportManager"
                    com.tencent.open.b.g r4 = com.tencent.open.p027b.C2072g.this     // Catch: java.lang.Exception -> Lb8
                    android.os.Bundle r4 = r4.m2181c()     // Catch: java.lang.Exception -> Lb8
                    if (r4 != 0) goto L11
                    return
                L11:
                    android.content.Context r5 = com.tencent.open.utils.C2084d.m2215a()     // Catch: java.lang.Exception -> Lb8
                    r6 = 0
                    com.tencent.open.utils.e r5 = com.tencent.open.utils.C2085e.m2221a(r5, r6)     // Catch: java.lang.Exception -> Lb8
                    java.lang.String r7 = "Common_HttpRetryCount"
                    int r5 = r5.m2230a(r7)     // Catch: java.lang.Exception -> Lb8
                    if (r5 != 0) goto L23
                    r5 = 3
                L23:
                    java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb8
                    r7.<init>()     // Catch: java.lang.Exception -> Lb8
                    java.lang.String r8 = "-->doReportCgi, retryCount: "
                    r7.append(r8)     // Catch: java.lang.Exception -> Lb8
                    r7.append(r5)     // Catch: java.lang.Exception -> Lb8
                    java.lang.String r7 = r7.toString()     // Catch: java.lang.Exception -> Lb8
                    com.tencent.open.p026a.C2061f.m2130b(r3, r7)     // Catch: java.lang.Exception -> Lb8
                    r7 = 0
                    r8 = 0
                L39:
                    r9 = 1
                    int r8 = r8 + r9
                    android.content.Context r10 = com.tencent.open.utils.C2084d.m2215a()     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    org.apache.http.client.HttpClient r10 = com.tencent.open.utils.HttpUtils.getHttpClient(r10, r6, r1)     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    org.apache.http.client.methods.HttpPost r11 = new org.apache.http.client.methods.HttpPost     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    r11.<init>(r1)     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    java.lang.String r12 = "Accept-Encoding"
                    java.lang.String r13 = "gzip"
                    r11.addHeader(r12, r13)     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    java.lang.String r12 = "Content-Type"
                    java.lang.String r13 = "application/x-www-form-urlencoded"
                    r11.setHeader(r12, r13)     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    java.lang.String r12 = com.tencent.open.utils.HttpUtils.encodeUrl(r4)     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    byte[] r12 = com.tencent.open.utils.C2089i.m2280i(r12)     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    org.apache.http.entity.ByteArrayEntity r13 = new org.apache.http.entity.ByteArrayEntity     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    r13.<init>(r12)     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    r11.setEntity(r13)     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    org.apache.http.HttpResponse r10 = r10.execute(r11)     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    org.apache.http.StatusLine r10 = r10.getStatusLine()     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    int r10 = r10.getStatusCode()     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    r11.<init>()     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    java.lang.String r12 = "-->doReportCgi, statusCode: "
                    r11.append(r12)     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    r11.append(r10)     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    java.lang.String r11 = r11.toString()     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    com.tencent.open.p026a.C2061f.m2130b(r3, r11)     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    r11 = 200(0xc8, float:2.8E-43)
                    if (r10 != r11) goto La3
                    com.tencent.open.b.f r10 = com.tencent.open.p027b.C2071f.m2168a()     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    r10.m2171b(r0)     // Catch: java.lang.Exception -> L93 java.net.SocketTimeoutException -> L98 org.apache.http.conn.ConnectTimeoutException -> L9d
                    r7 = 1
                    goto La3
                L93:
                    r1 = move-exception
                    com.tencent.open.p026a.C2061f.m2131b(r3, r2, r1)     // Catch: java.lang.Exception -> Lb8
                    goto La3
                L98:
                    r9 = move-exception
                    com.tencent.open.p026a.C2061f.m2131b(r3, r2, r9)     // Catch: java.lang.Exception -> Lb8
                    goto La1
                L9d:
                    r9 = move-exception
                    com.tencent.open.p026a.C2061f.m2131b(r3, r2, r9)     // Catch: java.lang.Exception -> Lb8
                La1:
                    if (r8 < r5) goto L39
                La3:
                    if (r7 != 0) goto Lb0
                    com.tencent.open.b.f r1 = com.tencent.open.p027b.C2071f.m2168a()     // Catch: java.lang.Exception -> Lb8
                    com.tencent.open.b.g r2 = com.tencent.open.p027b.C2072g.this     // Catch: java.lang.Exception -> Lb8
                    java.util.List<java.io.Serializable> r2 = r2.f3198c     // Catch: java.lang.Exception -> Lb8
                    r1.m2170a(r0, r2)     // Catch: java.lang.Exception -> Lb8
                Lb0:
                    com.tencent.open.b.g r0 = com.tencent.open.p027b.C2072g.this     // Catch: java.lang.Exception -> Lb8
                    java.util.List<java.io.Serializable> r0 = r0.f3198c     // Catch: java.lang.Exception -> Lb8
                    r0.clear()     // Catch: java.lang.Exception -> Lb8
                    goto Lbe
                Lb8:
                    r0 = move-exception
                    java.lang.String r1 = "-->doReportCgi, doupload exception out."
                    com.tencent.open.p026a.C2061f.m2131b(r3, r1, r0)
                Lbe:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.p027b.C2072g.AnonymousClass4.run():void");
            }
        });
    }

    /* JADX INFO: renamed from: a */
    protected boolean m2179a(String str, String str2) {
        int iM2173a;
        C2061f.m2130b("openSDK_LOG.ReportManager", "-->availableFrequency, report: " + str + " | ext: " + str2);
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int i = 100;
        if (str.equals("report_cgi")) {
            try {
                iM2173a = m2173a(Integer.parseInt(str2));
                if (this.f3197b.nextInt(100) < iM2173a) {
                    z = true;
                }
            } catch (Exception unused) {
                return false;
            }
        } else {
            if (str.equals("report_via")) {
                iM2173a = C2070e.m2167a(str2);
                if (this.f3197b.nextInt(100) < iM2173a) {
                    i = iM2173a;
                    z = true;
                }
            }
            C2061f.m2130b("openSDK_LOG.ReportManager", "-->availableFrequency, result: " + z + " | frequency: " + i);
            return z;
        }
        i = iM2173a;
        C2061f.m2130b("openSDK_LOG.ReportManager", "-->availableFrequency, result: " + z + " | frequency: " + i);
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001c A[PHI: r0
  0x001c: PHI (r0v9 int) = (r0v6 int), (r0v12 int) binds: [B:11:0x0034, B:5:0x0019] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean m2178a(java.lang.String r5, int r6) {
        /*
            r4 = this;
            java.lang.String r0 = "report_cgi"
            boolean r0 = r5.equals(r0)
            r1 = 5
            r2 = 0
            r3 = 0
            if (r0 == 0) goto L1e
            android.content.Context r0 = com.tencent.open.utils.C2084d.m2215a()
            com.tencent.open.utils.e r0 = com.tencent.open.utils.C2085e.m2221a(r0, r2)
            java.lang.String r2 = "Common_CGIReportMaxcount"
            int r0 = r0.m2230a(r2)
            if (r0 != 0) goto L1c
            goto L38
        L1c:
            r1 = r0
            goto L38
        L1e:
            java.lang.String r0 = "report_via"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L37
            android.content.Context r0 = com.tencent.open.utils.C2084d.m2215a()
            com.tencent.open.utils.e r0 = com.tencent.open.utils.C2085e.m2221a(r0, r2)
            java.lang.String r2 = "Agent_ReportBatchCount"
            int r0 = r0.m2230a(r2)
            if (r0 != 0) goto L1c
            goto L38
        L37:
            r1 = 0
        L38:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "-->availableCount, report: "
            r0.append(r2)
            r0.append(r5)
            java.lang.String r5 = " | dataSize: "
            r0.append(r5)
            r0.append(r6)
            java.lang.String r5 = " | maxcount: "
            r0.append(r5)
            r0.append(r1)
            java.lang.String r5 = r0.toString()
            java.lang.String r0 = "openSDK_LOG.ReportManager"
            com.tencent.open.p026a.C2061f.m2130b(r0, r5)
            if (r6 < r1) goto L62
            r5 = 1
            return r5
        L62:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.p027b.C2072g.m2178a(java.lang.String, int):boolean");
    }

    /* JADX INFO: renamed from: a */
    protected int m2173a(int i) {
        if (i == 0) {
            int iM2230a = C2085e.m2221a(C2084d.m2215a(), (String) null).m2230a("Common_CGIReportFrequencySuccess");
            if (iM2230a == 0) {
                return 10;
            }
            return iM2230a;
        }
        int iM2230a2 = C2085e.m2221a(C2084d.m2215a(), (String) null).m2230a("Common_CGIReportFrequencyFailed");
        if (iM2230a2 == 0) {
            return 100;
        }
        return iM2230a2;
    }

    /* JADX INFO: renamed from: c */
    protected Bundle m2181c() {
        if (this.f3198c.size() == 0) {
            return null;
        }
        C2067b c2067b = (C2067b) this.f3198c.get(0);
        if (c2067b == null) {
            C2061f.m2130b("openSDK_LOG.ReportManager", "-->prepareCgiData, the 0th cgireportitem is null.");
            return null;
        }
        String str = c2067b.f3187a.get("appid");
        List<Serializable> listM2169a = C2071f.m2168a().m2169a("report_cgi");
        if (listM2169a != null) {
            this.f3198c.addAll(listM2169a);
        }
        C2061f.m2130b("openSDK_LOG.ReportManager", "-->prepareCgiData, mCgiList size: " + this.f3198c.size());
        if (this.f3198c.size() == 0) {
            return null;
        }
        Bundle bundle = new Bundle();
        try {
            bundle.putString("appid", str);
            bundle.putString("releaseversion", Constants.SDK_VERSION_REPORT);
            bundle.putString("device", Build.DEVICE);
            bundle.putString(TopicKey.QUA, Constants.SDK_QUA);
            bundle.putString("key", "apn,frequency,commandid,resultcode,tmcost,reqsize,rspsize,detail,touin,deviceinfo");
            for (int i = 0; i < this.f3198c.size(); i++) {
                C2067b c2067b2 = (C2067b) this.f3198c.get(i);
                bundle.putString(i + "_1", c2067b2.f3187a.get("apn"));
                bundle.putString(i + "_2", c2067b2.f3187a.get("frequency"));
                bundle.putString(i + "_3", c2067b2.f3187a.get("commandid"));
                bundle.putString(i + "_4", c2067b2.f3187a.get("resultCode"));
                bundle.putString(i + "_5", c2067b2.f3187a.get("timeCost"));
                bundle.putString(i + "_6", c2067b2.f3187a.get("reqSize"));
                bundle.putString(i + "_7", c2067b2.f3187a.get("rspSize"));
                bundle.putString(i + "_8", c2067b2.f3187a.get("detail"));
                bundle.putString(i + "_9", c2067b2.f3187a.get("uin"));
                bundle.putString(i + "_10", C2068c.m2161e(C2084d.m2215a()) + "&" + c2067b2.f3187a.get("deviceInfo"));
            }
            C2061f.m2127a("openSDK_LOG.ReportManager", "-->prepareCgiData, end. params: " + bundle.toString());
            return bundle;
        } catch (Exception e) {
            C2061f.m2131b("openSDK_LOG.ReportManager", "-->prepareCgiData, exception.", e);
            return null;
        }
    }

    /* JADX INFO: renamed from: d */
    protected Bundle m2182d() {
        List<Serializable> listM2169a = C2071f.m2168a().m2169a("report_via");
        if (listM2169a != null) {
            this.f3199d.addAll(listM2169a);
        }
        C2061f.m2130b("openSDK_LOG.ReportManager", "-->prepareViaData, mViaList size: " + this.f3199d.size());
        if (this.f3199d.size() == 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Serializable serializable : this.f3199d) {
            JSONObject jSONObject = new JSONObject();
            C2067b c2067b = (C2067b) serializable;
            for (String str : c2067b.f3187a.keySet()) {
                try {
                    String str2 = c2067b.f3187a.get(str);
                    if (str2 == null) {
                        str2 = "";
                    }
                    jSONObject.put(str, str2);
                } catch (JSONException e) {
                    C2061f.m2131b("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", e);
                }
            }
            jSONArray.put(jSONObject);
        }
        C2061f.m2127a("openSDK_LOG.ReportManager", "-->prepareViaData, JSONArray array: " + jSONArray.toString());
        Bundle bundle = new Bundle();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("data", jSONArray);
            bundle.putString("data", jSONObject2.toString());
            return bundle;
        } catch (JSONException e2) {
            C2061f.m2131b("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", e2);
            return null;
        }
    }

    /* JADX INFO: renamed from: e */
    protected void m2183e() {
        this.f3202g.execute(new Runnable() { // from class: com.tencent.open.b.g.5
            @Override // java.lang.Runnable
            public void run() {
                int i;
                try {
                    Bundle bundleM2182d = C2072g.this.m2182d();
                    if (bundleM2182d == null) {
                        return;
                    }
                    C2061f.m2127a("openSDK_LOG.ReportManager", "-->doReportVia, params: " + bundleM2182d.toString());
                    int iM2166a = C2070e.m2166a();
                    int i2 = 0;
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    boolean z = false;
                    int errorCodeFromException = 0;
                    long j = 0;
                    long j2 = 0;
                    do {
                        int i3 = i2 + 1;
                        try {
                            try {
                                try {
                                    C2089i.a aVarOpenUrl2 = HttpUtils.openUrl2(C2084d.m2215a(), "http://appsupport.qq.com/cgi-bin/appstage/mstats_batch_report", Constants.HTTP_POST, bundleM2182d);
                                    try {
                                        i = C2089i.m2271d(aVarOpenUrl2.f3298a).getInt("ret");
                                    } catch (JSONException unused) {
                                        i = -4;
                                    }
                                    if (i == 0 || !TextUtils.isEmpty(aVarOpenUrl2.f3298a)) {
                                        i3 = iM2166a;
                                        z = true;
                                    }
                                    j = aVarOpenUrl2.f3299b;
                                    j2 = aVarOpenUrl2.f3300c;
                                    i2 = i3;
                                } catch (HttpUtils.HttpStatusException e) {
                                    try {
                                        errorCodeFromException = Integer.parseInt(e.getMessage().replace(HttpUtils.HttpStatusException.ERROR_INFO, ""));
                                    } catch (Exception unused2) {
                                    }
                                } catch (HttpUtils.NetworkUnavailableException unused3) {
                                    C2072g.this.f3199d.clear();
                                    C2061f.m2130b("openSDK_LOG.ReportManager", "doReportVia, NetworkUnavailableException.");
                                    return;
                                } catch (ConnectTimeoutException unused4) {
                                    jElapsedRealtime = SystemClock.elapsedRealtime();
                                    i2 = i3;
                                    errorCodeFromException = -7;
                                    j = 0;
                                    j2 = 0;
                                } catch (Exception unused5) {
                                    i2 = iM2166a;
                                    errorCodeFromException = -6;
                                    j = 0;
                                    j2 = 0;
                                }
                            } catch (SocketTimeoutException unused6) {
                                jElapsedRealtime = SystemClock.elapsedRealtime();
                                i2 = i3;
                                errorCodeFromException = -8;
                                j = 0;
                                j2 = 0;
                            } catch (IOException e2) {
                                errorCodeFromException = HttpUtils.getErrorCodeFromException(e2);
                                i2 = i3;
                                j = 0;
                                j2 = 0;
                            }
                        } catch (JSONException unused7) {
                            i2 = i3;
                            errorCodeFromException = -4;
                            j = 0;
                            j2 = 0;
                        }
                    } while (i2 < iM2166a);
                    C2072g.this.m2176a("mapp_apptrace_sdk", jElapsedRealtime, j, j2, errorCodeFromException, null, false);
                    if (z) {
                        C2071f.m2168a().m2171b("report_via");
                    } else {
                        C2071f.m2168a().m2170a("report_via", C2072g.this.f3199d);
                    }
                    C2072g.this.f3199d.clear();
                    C2061f.m2130b("openSDK_LOG.ReportManager", "-->doReportVia, uploadSuccess: " + z);
                } catch (Exception e3) {
                    C2061f.m2131b("openSDK_LOG.ReportManager", "-->doReportVia, exception in serial executor.", e3);
                }
            }
        });
    }

    /* JADX INFO: renamed from: a */
    public void m2177a(final String str, final String str2, final Bundle bundle, final boolean z) {
        C2088h.m2247a(new Runnable() { // from class: com.tencent.open.b.g.6
            /* JADX WARN: Removed duplicated region for block: B:50:0x00e0 A[EDGE_INSN: B:50:0x00e0->B:35:0x00e0 BREAK  A[LOOP:0: B:21:0x0094->B:55:?], SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:55:? A[LOOP:0: B:21:0x0094->B:55:?, LOOP_END, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instruction units count: 250
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.open.p027b.C2072g.AnonymousClass6.run():void");
            }
        });
    }
}
