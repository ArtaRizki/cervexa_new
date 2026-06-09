package com.tencent.bugly.proguard;

import android.content.Context;
import com.tencent.connect.common.Constants;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.commons.net.imap.IMAPSClient;

/* JADX INFO: renamed from: com.tencent.bugly.proguard.s */
/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public final class C2016s {

    /* JADX INFO: renamed from: b */
    private static C2016s f2867b;

    /* JADX INFO: renamed from: a */
    public Map<String, String> f2868a = null;

    /* JADX INFO: renamed from: c */
    private Context f2869c;

    private C2016s(Context context) {
        this.f2869c = context;
    }

    /* JADX INFO: renamed from: a */
    public static C2016s m1833a(Context context) {
        if (f2867b == null) {
            f2867b = new C2016s(context);
        }
        return f2867b;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:(2:131|24)|(6:26|(1:28)(1:29)|30|133|31|37)(10:38|(1:48)(1:47)|(3:129|50|(5:138|52|136|53|54)(9:62|63|64|123|65|66|127|67|68))(1:80)|121|81|(1:83)|84|119|85|107)|97|98|(1:100)|139|101|107) */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x017f, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0180, code lost:
    
        r4 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0185, code lost:
    
        if (com.tencent.bugly.proguard.C2021x.m1867a(r4) == false) goto L106;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0178 A[Catch: all -> 0x016c, TRY_LEAVE, TryCatch #8 {all -> 0x016c, blocks: (B:24:0x009b, B:26:0x00a3, B:30:0x00b4, B:29:0x00b2, B:50:0x00dd, B:52:0x00e5, B:65:0x0115, B:67:0x011f, B:81:0x0139, B:84:0x015a, B:98:0x0172, B:100:0x0178), top: B:131:0x009b }] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0187 A[PHI: r4 r6 r10 r13 r14
  0x0187: PHI (r4v4 java.lang.Throwable) = (r4v3 java.lang.Throwable), (r4v17 java.lang.Throwable) binds: [B:105:0x0185, B:89:0x0167] A[DONT_GENERATE, DONT_INLINE]
  0x0187: PHI (r6v7 int) = (r6v6 int), (r6v13 int) binds: [B:105:0x0185, B:89:0x0167] A[DONT_GENERATE, DONT_INLINE]
  0x0187: PHI (r10v8 java.lang.String) = (r10v7 java.lang.String), (r10v14 java.lang.String) binds: [B:105:0x0185, B:89:0x0167] A[DONT_GENERATE, DONT_INLINE]
  0x0187: PHI (r13v5 int) = (r13v4 int), (r13v9 int) binds: [B:105:0x0185, B:89:0x0167] A[DONT_GENERATE, DONT_INLINE]
  0x0187: PHI (r14v5 boolean) = (r14v4 boolean), (r14v11 boolean) binds: [B:105:0x0185, B:89:0x0167] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] m1838a(java.lang.String r21, byte[] r22, com.tencent.bugly.proguard.RunnableC2019v r23, java.util.Map<java.lang.String, java.lang.String> r24) {
        /*
            Method dump skipped, instruction units count: 436
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2016s.m1838a(java.lang.String, byte[], com.tencent.bugly.proguard.v, java.util.Map):byte[]");
    }

    /* JADX INFO: renamed from: a */
    private static Map<String, String> m1836a(HttpURLConnection httpURLConnection) {
        HashMap map = new HashMap();
        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
        if (headerFields == null || headerFields.size() == 0) {
            return null;
        }
        for (String str : headerFields.keySet()) {
            List<String> list = headerFields.get(str);
            if (list.size() > 0) {
                map.put(str, list.get(0));
            }
        }
        return map;
    }

    /* JADX INFO: renamed from: b */
    private static byte[] m1837b(HttpURLConnection httpURLConnection) {
        BufferedInputStream bufferedInputStream;
        if (httpURLConnection == null) {
            return null;
        }
        try {
            bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
        } catch (Throwable th) {
            th = th;
            bufferedInputStream = null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int i = bufferedInputStream.read(bArr);
                if (i <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
            byteArrayOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                bufferedInputStream.close();
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            return byteArray;
        } catch (Throwable th3) {
            th = th3;
            try {
                if (!C2021x.m1867a(th)) {
                    th.printStackTrace();
                }
                return null;
            } finally {
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private HttpURLConnection m1835a(String str, byte[] bArr, String str2, Map<String, String> map) {
        if (str == null) {
            C2021x.m1873e("destUrl is null.", new Object[0]);
            return null;
        }
        TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.tencent.bugly.proguard.s.1
            @Override // javax.net.ssl.X509TrustManager
            public final X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            @Override // javax.net.ssl.X509TrustManager
            public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str3) throws CertificateException {
                C2021x.m1871c("checkClientTrusted", new Object[0]);
            }

            @Override // javax.net.ssl.X509TrustManager
            public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str3) throws CertificateException {
                C2021x.m1871c("checkServerTrusted", new Object[0]);
            }
        }};
        try {
            SSLContext sSLContext = SSLContext.getInstance(IMAPSClient.DEFAULT_PROTOCOL);
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpURLConnection httpURLConnectionM1834a = m1834a(str2, str);
        if (httpURLConnectionM1834a == null) {
            C2021x.m1873e("Failed to get HttpURLConnection object.", new Object[0]);
            return null;
        }
        try {
            httpURLConnectionM1834a.setRequestProperty("wup_version", "3.0");
            if (map != null && map.size() > 0) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    httpURLConnectionM1834a.setRequestProperty(entry.getKey(), URLEncoder.encode(entry.getValue(), "utf-8"));
                }
            }
            httpURLConnectionM1834a.setRequestProperty("A37", URLEncoder.encode(str2, "utf-8"));
            httpURLConnectionM1834a.setRequestProperty("A38", URLEncoder.encode(str2, "utf-8"));
            OutputStream outputStream = httpURLConnectionM1834a.getOutputStream();
            if (bArr == null) {
                outputStream.write(0);
            } else {
                outputStream.write(bArr);
            }
            return httpURLConnectionM1834a;
        } catch (Throwable th) {
            if (!C2021x.m1867a(th)) {
                th.printStackTrace();
            }
            C2021x.m1873e("Failed to upload, please check your network.", new Object[0]);
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    private static HttpURLConnection m1834a(String str, String str2) {
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(str2);
            if (C1980a.m1693b() != null) {
                httpURLConnection = (HttpURLConnection) url.openConnection(C1980a.m1693b());
            } else if (str != null && str.toLowerCase(Locale.US).contains("wap")) {
                httpURLConnection = (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(System.getProperty("http.proxyHost"), Integer.parseInt(System.getProperty("http.proxyPort")))));
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            }
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod(Constants.HTTP_POST);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(false);
            return httpURLConnection;
        } catch (Throwable th) {
            if (C2021x.m1867a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
