package com.baidu.trace.p012c;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.baidu.trace.C0791a;
import com.baidu.trace.C0802aa;
import com.baidu.trace.C0862h;
import com.baidu.trace.LBSTraceService;
import com.baidu.trace.api.analysis.OnAnalysisListener;
import com.baidu.trace.api.bos.OnBosListener;
import com.baidu.trace.api.entity.OnEntityListener;
import com.baidu.trace.api.fence.OnFenceListener;
import com.baidu.trace.api.track.OnTrackListener;
import com.baidu.trace.model.BaseRequest;
import com.baidu.trace.model.ProtocolType;
import com.baidu.trace.model.StatusCodes;
import com.bumptech.glide.load.Key;
import com.tencent.connect.common.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;

/* JADX INFO: renamed from: com.baidu.trace.c.a */
/* JADX INFO: loaded from: classes.dex */
public final class C0850a {

    /* JADX INFO: renamed from: c */
    private static final Pattern f1717c = Pattern.compile("[0-9A-Fa-f]{4}");

    /* JADX INFO: renamed from: d */
    private static a f1718d = null;

    /* JADX INFO: renamed from: e */
    private static b f1719e = null;

    /* JADX INFO: renamed from: f */
    private static WeakReference<Context> f1720f = null;

    /* JADX INFO: renamed from: g */
    private static boolean f1721g = false;

    /* JADX INFO: renamed from: a */
    public static ExecutorService f1715a = null;

    /* JADX INFO: renamed from: b */
    public static ProtocolType f1716b = ProtocolType.HTTPS;

    /* JADX INFO: renamed from: h */
    private static int f1722h = 5;

    /* JADX INFO: renamed from: i */
    private static int f1723i = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: j */
    private static long f1724j = 60;

    /* JADX INFO: renamed from: com.baidu.trace.c.a$1, reason: invalid class name */
    public class AnonymousClass1 {
    }

    /* JADX INFO: renamed from: com.baidu.trace.c.a$a */
    static class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
        }
    }

    /* JADX INFO: renamed from: com.baidu.trace.c.a$b */
    static class b implements HostnameVerifier {
        private b() {
        }

        /* synthetic */ b(byte b) {
            this();
        }

        @Override // javax.net.ssl.HostnameVerifier
        public final boolean verify(String str, SSLSession sSLSession) {
            return "api.map.baidu.com".equals(str);
        }
    }

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: renamed from: com.baidu.trace.c.a$c */
    public static final class c {

        /* JADX INFO: renamed from: a */
        public static final c f1725a = new c("GET", 0);

        /* JADX INFO: renamed from: b */
        public static final c f1726b = new c(Constants.HTTP_POST, 1);

        private c(String str, int i) {
        }
    }

    /* JADX INFO: renamed from: com.baidu.trace.c.a$d */
    static class d implements Runnable {

        /* JADX INFO: renamed from: a */
        protected int f1727a;

        /* JADX INFO: renamed from: b */
        protected int f1728b;

        /* JADX INFO: renamed from: c */
        protected String f1729c;

        /* JADX INFO: renamed from: d */
        protected c f1730d;

        /* JADX INFO: renamed from: e */
        protected String f1731e;

        /* JADX INFO: renamed from: f */
        protected BaseRequest f1732f;

        /* JADX INFO: renamed from: g */
        protected Object f1733g;

        d() {
        }

        /* JADX INFO: renamed from: a */
        private String m1213a(ProtocolType protocolType, String str) throws Exception {
            URL urlM1214a = m1214a(str);
            if (urlM1214a == null) {
                return "";
            }
            HttpURLConnection httpURLConnection = ProtocolType.HTTPS == protocolType ? (HttpsURLConnection) urlM1214a.openConnection() : (HttpURLConnection) urlM1214a.openConnection();
            return m1217a(httpURLConnection) ? m1218b(httpURLConnection) : "";
        }

        /* JADX INFO: renamed from: a */
        private URL m1214a(String str) {
            URL url;
            try {
                if (c.f1725a == this.f1730d) {
                    url = new URL(str + "?" + this.f1731e);
                } else {
                    if (c.f1726b != this.f1730d) {
                        return null;
                    }
                    url = new URL(str);
                }
                return url;
            } catch (MalformedURLException unused) {
                return null;
            }
        }

        /* JADX INFO: renamed from: a */
        static /* synthetic */ void m1215a(int i, BaseRequest baseRequest, int i2, String str, int i3, Object obj) {
            if (i == 0) {
                C0791a.m990a(baseRequest, i3, false, i2, str, (OnFenceListener) obj);
                return;
            }
            if (i == 1) {
                C0802aa.m1036a(baseRequest, i3, false, i2, str, (OnEntityListener) obj);
                return;
            }
            if (i == 2) {
                C0791a.m991a(baseRequest, i3, false, i2, str, (OnTrackListener) obj);
            } else if (i == 3) {
                C0862h.m1266a(baseRequest, i3, false, i2, str, (OnBosListener) obj, C0850a.f1718d);
            } else {
                if (i != 4) {
                    return;
                }
                C0791a.m989a(baseRequest, i3, false, i2, str, (OnAnalysisListener) obj);
            }
        }

        /* JADX INFO: renamed from: a */
        static /* synthetic */ void m1216a(int i, BaseRequest baseRequest, String str, int i2, Object obj) {
            if (i == 0) {
                try {
                    C0791a.m990a(baseRequest, i2, true, 0, URLDecoder.decode(str, Key.STRING_CHARSET_NAME), (OnFenceListener) obj);
                } catch (Exception unused) {
                    C0791a.m990a(baseRequest, i2, false, StatusCodes.PARSE_RESPONSE_FAILED, StatusCodes.MSG_PARSE_RESPONSE_FAILED, (OnFenceListener) obj);
                }
            } else {
                if (i == 1) {
                    C0802aa.m1036a(baseRequest, i2, true, 0, C0850a.m1204a(str), (OnEntityListener) obj);
                    return;
                }
                if (i == 2) {
                    C0791a.m991a(baseRequest, i2, true, 0, C0850a.m1204a(str), (OnTrackListener) obj);
                } else if (i == 3) {
                    C0862h.m1266a(baseRequest, i2, true, 0, C0850a.m1204a(str), (OnBosListener) obj, null);
                } else {
                    if (i != 4) {
                        return;
                    }
                    C0791a.m989a(baseRequest, i2, true, 0, C0850a.m1204a(str), (OnAnalysisListener) obj);
                }
            }
        }

        /* JADX INFO: renamed from: a */
        private boolean m1217a(HttpURLConnection httpURLConnection) {
            try {
                httpURLConnection.setRequestMethod(this.f1730d.name());
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(StatusCodes.NOT_EXIST_FENCE);
                httpURLConnection.setUseCaches(false);
                return true;
            } catch (ProtocolException unused) {
                return false;
            }
        }

        /* JADX INFO: renamed from: b */
        private String m1218b(HttpURLConnection httpURLConnection) {
            InputStream inputStream = null;
            try {
                try {
                    if (httpURLConnection instanceof HttpsURLConnection) {
                        ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(C0850a.f1719e);
                        if (c.f1726b == this.f1730d) {
                            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setDoInput(true);
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), Key.STRING_CHARSET_NAME));
                            bufferedWriter.write(this.f1731e);
                            bufferedWriter.flush();
                            bufferedWriter.close();
                            httpURLConnection.connect();
                        } else if (c.f1725a == this.f1730d) {
                            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                            if (200 == httpURLConnection.getResponseCode()) {
                            }
                        }
                        inputStream = httpURLConnection.getInputStream();
                    } else if (httpURLConnection instanceof HttpURLConnection) {
                        if (c.f1725a == this.f1730d) {
                            if (200 == httpURLConnection.getResponseCode()) {
                                inputStream = httpURLConnection.getInputStream();
                            }
                        } else if (c.f1726b == this.f1730d) {
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setDoInput(true);
                            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                            BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), Key.STRING_CHARSET_NAME));
                            bufferedWriter2.write(this.f1731e);
                            bufferedWriter2.flush();
                            bufferedWriter2.close();
                            httpURLConnection.connect();
                            inputStream = httpURLConnection.getInputStream();
                        }
                    }
                    String strM1209b = C0850a.m1209b(inputStream);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException unused) {
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return strM1209b;
                } catch (SSLHandshakeException unused2) {
                    C0791a.m1005a("BaiduTraceSDK", "current network is unavailable or unstable");
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException unused3) {
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return "";
                } catch (Exception unused4) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException unused5) {
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return "";
                }
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused6) {
                    }
                }
                if (httpURLConnection == null) {
                    throw th;
                }
                httpURLConnection.disconnect();
                throw th;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x008c  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0090  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void run() {
            /*
                r10 = this;
                com.baidu.trace.model.BaseRequest r0 = r10.f1732f
                boolean r0 = r0.isCanceled()
                java.lang.String r1 = "request has been canceled!"
                if (r0 == 0) goto Le
                com.baidu.trace.C0791a.m995a(r1)
                return
            Le:
                java.lang.ref.WeakReference r0 = com.baidu.trace.p012c.C0850a.m1210b()
                if (r0 == 0) goto L3b
                java.lang.ref.WeakReference r0 = com.baidu.trace.p012c.C0850a.m1210b()
                java.lang.Object r0 = r0.get()
                if (r0 == 0) goto L3b
                java.lang.ref.WeakReference r0 = com.baidu.trace.p012c.C0850a.m1210b()
                java.lang.Object r0 = r0.get()
                android.content.Context r0 = (android.content.Context) r0
                boolean r0 = com.baidu.trace.p011b.C0832d.m1138a(r0)
                if (r0 != 0) goto L3b
                com.baidu.trace.c.a$a r0 = com.baidu.trace.p012c.C0850a.m1211c()
                com.baidu.trace.c.b r1 = new com.baidu.trace.c.b
                r1.<init>(r10)
                r0.post(r1)
                return
            L3b:
                java.lang.StringBuffer r0 = new java.lang.StringBuffer
                r0.<init>()
                r2 = 3
                com.baidu.trace.model.ProtocolType r3 = com.baidu.trace.model.ProtocolType.HTTPS     // Catch: java.lang.Exception -> L7f
                com.baidu.trace.model.ProtocolType r4 = com.baidu.trace.p012c.C0850a.f1716b     // Catch: java.lang.Exception -> L7f
                if (r3 == r4) goto L6c
                int r3 = r10.f1727a     // Catch: java.lang.Exception -> L7f
                if (r2 != r3) goto L4c
                goto L6c
            L4c:
                com.baidu.trace.model.ProtocolType r3 = com.baidu.trace.model.ProtocolType.HTTP     // Catch: java.lang.Exception -> L7f
                com.baidu.trace.model.ProtocolType r4 = com.baidu.trace.p012c.C0850a.f1716b     // Catch: java.lang.Exception -> L7f
                if (r3 != r4) goto L84
                com.baidu.trace.model.ProtocolType r3 = com.baidu.trace.model.ProtocolType.HTTP     // Catch: java.lang.Exception -> L7f
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L7f
                java.lang.String r5 = "http://api.map.baidu.com/sdkproxy/v2/lbs_tracesdk/trace/v3/"
                r4.<init>(r5)     // Catch: java.lang.Exception -> L7f
                java.lang.String r5 = r10.f1729c     // Catch: java.lang.Exception -> L7f
                r4.append(r5)     // Catch: java.lang.Exception -> L7f
                java.lang.String r4 = r4.toString()     // Catch: java.lang.Exception -> L7f
            L64:
                java.lang.String r3 = r10.m1213a(r3, r4)     // Catch: java.lang.Exception -> L7f
                r0.append(r3)     // Catch: java.lang.Exception -> L7f
                goto L84
            L6c:
                com.baidu.trace.model.ProtocolType r3 = com.baidu.trace.model.ProtocolType.HTTPS     // Catch: java.lang.Exception -> L7f
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L7f
                java.lang.String r5 = "https://api.map.baidu.com/sdkproxy/v2/lbs_tracesdk/trace/v3/"
                r4.<init>(r5)     // Catch: java.lang.Exception -> L7f
                java.lang.String r5 = r10.f1729c     // Catch: java.lang.Exception -> L7f
                r4.append(r5)     // Catch: java.lang.Exception -> L7f
                java.lang.String r4 = r4.toString()     // Catch: java.lang.Exception -> L7f
                goto L64
            L7f:
                java.lang.String r3 = "http request failed"
                com.baidu.trace.C0791a.m995a(r3)
            L84:
                com.baidu.trace.model.BaseRequest r3 = r10.f1732f
                boolean r3 = r3.isCanceled()
                if (r3 == 0) goto L90
                com.baidu.trace.C0791a.m995a(r1)
                return
            L90:
                boolean r1 = android.text.TextUtils.isEmpty(r0)
                if (r1 != 0) goto Lb9
                int r1 = r10.f1727a
                if (r2 != r1) goto Lb9
                int r1 = r10.f1728b
                if (r2 == r1) goto Lb9
                com.baidu.trace.model.BaseRequest r3 = r10.f1732f
                java.lang.String r0 = r0.toString()
                int r4 = r10.f1728b
                java.lang.Object r1 = r10.f1733g
                r8 = r1
                com.baidu.trace.api.bos.OnBosListener r8 = (com.baidu.trace.api.bos.OnBosListener) r8
                com.baidu.trace.c.a$a r9 = com.baidu.trace.p012c.C0850a.m1211c()
                r5 = 1
                r6 = 0
                java.lang.String r7 = com.baidu.trace.p012c.C0850a.m1204a(r0)
                com.baidu.trace.C0862h.m1266a(r3, r4, r5, r6, r7, r8, r9)
                return
            Lb9:
                com.baidu.trace.c.a$a r1 = com.baidu.trace.p012c.C0850a.m1211c()
                com.baidu.trace.c.c r2 = new com.baidu.trace.c.c
                r2.<init>(r10, r0)
                r1.post(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.trace.p012c.C0850a.d.run():void");
        }

        public final String toString() {
            return "TaskRunnable [tag=" + this.f1732f.getTag() + ", serviceTag=" + this.f1727a + ", operateType=" + this.f1728b + ", action=" + this.f1729c + ", requestType=" + this.f1730d + ", parameters=" + this.f1731e + "]";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0050  */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.String m1204a(java.lang.String r7) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            java.lang.String r1 = ""
            if (r0 == 0) goto L9
            return r1
        L9:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r1)
            int r1 = r7.length()
            r2 = 0
        L13:
            if (r2 >= r1) goto L5d
            char r3 = r7.charAt(r2)
            r4 = 92
            if (r3 != r4) goto L57
            int r4 = r1 + (-1)
            if (r2 >= r4) goto L57
            int r2 = r2 + 1
            char r4 = r7.charAt(r2)
            r5 = 117(0x75, float:1.64E-43)
            if (r4 != r5) goto L50
            int r5 = r1 + (-5)
            if (r2 > r5) goto L50
            int r5 = r2 + 1
            int r6 = r2 + 5
            java.lang.String r5 = r7.substring(r5, r6)
            java.util.regex.Pattern r6 = com.baidu.trace.p012c.C0850a.f1717c
            java.util.regex.Matcher r6 = r6.matcher(r5)
            boolean r6 = r6.find()
            if (r6 == 0) goto L50
            r3 = 16
            int r3 = java.lang.Integer.parseInt(r5, r3)
            char r3 = (char) r3
            r0.append(r3)
            int r2 = r2 + 4
            goto L5a
        L50:
            r0.append(r3)
            r0.append(r4)
            goto L5a
        L57:
            r0.append(r3)
        L5a:
            int r2 = r2 + 1
            goto L13
        L5d:
            java.lang.String r7 = r0.toString()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.trace.p012c.C0850a.m1204a(java.lang.String):java.lang.String");
    }

    /* JADX INFO: renamed from: a */
    public static synchronized void m1205a() {
        if (f1720f != null) {
            f1720f.clear();
            f1720f = null;
        }
        if (!f1721g || f1715a == null) {
            return;
        }
        try {
            if (!f1715a.isShutdown()) {
                f1715a.shutdownNow();
            }
        } catch (Exception unused) {
        }
        f1718d = null;
        f1719e = null;
        f1715a = null;
        f1721g = false;
    }

    /* JADX INFO: renamed from: a */
    public static synchronized void m1206a(Context context, Class<?> cls) {
        if (f1720f == null) {
            f1720f = new WeakReference<>(context);
        }
        if (!f1721g || f1715a == null) {
            f1718d = new a();
            f1719e = new b((byte) 0);
            f1715a = LBSTraceService.class.getSimpleName().equals(cls.getSimpleName()) ? Executors.newSingleThreadExecutor() : new ThreadPoolExecutor(f1722h, f1723i, f1724j, TimeUnit.SECONDS, new LinkedBlockingQueue());
            f1721g = true;
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1207a(BaseRequest baseRequest, int i, String str, c cVar, int i2, String str2, Object obj) {
        d dVar = new d();
        dVar.f1727a = i;
        dVar.f1728b = i2;
        dVar.f1729c = str;
        dVar.f1730d = cVar;
        dVar.f1731e = str2;
        dVar.f1732f = baseRequest;
        dVar.f1733g = obj;
        ExecutorService executorService = f1715a;
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        f1715a.execute(dVar);
    }

    /* JADX INFO: renamed from: a */
    public static synchronized boolean m1208a(int i, int i2, long j) {
        boolean z;
        if (i < 0 || i2 <= 0 || i2 < i || j < 0) {
            z = false;
        } else {
            if (f1715a instanceof ThreadPoolExecutor) {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) f1715a;
                threadPoolExecutor.setCorePoolSize(i);
                threadPoolExecutor.setMaximumPoolSize(i2);
                threadPoolExecutor.setKeepAliveTime(j, TimeUnit.SECONDS);
            } else {
                f1722h = i;
                f1723i = i2;
                f1724j = j;
            }
            z = true;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public static String m1209b(InputStream inputStream) {
        if (inputStream == null) {
            return "";
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            try {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    stringBuffer.append(line);
                } catch (Exception unused) {
                    bufferedReader.close();
                } catch (Throwable th) {
                    try {
                        bufferedReader.close();
                    } catch (IOException unused2) {
                    }
                    throw th;
                }
            } catch (IOException unused3) {
            }
        }
        bufferedReader.close();
        return stringBuffer.toString();
    }
}
