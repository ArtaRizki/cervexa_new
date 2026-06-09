package com.baidu.lbsapi.auth;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.bumptech.glide.load.Key;
import com.tencent.connect.common.Constants;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.g */
/* JADX INFO: loaded from: classes.dex */
public class C0651g {

    /* JADX INFO: renamed from: a */
    private Context f130a;

    /* JADX INFO: renamed from: b */
    private String f131b = null;

    /* JADX INFO: renamed from: c */
    private HashMap<String, String> f132c = null;

    /* JADX INFO: renamed from: d */
    private String f133d = null;

    public C0651g(Context context) {
        this.f130a = context;
    }

    /* JADX INFO: renamed from: a */
    private String m158a(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable()) {
                String extraInfo = activeNetworkInfo.getExtraInfo();
                return (extraInfo == null || !(extraInfo.trim().toLowerCase().equals("cmwap") || extraInfo.trim().toLowerCase().equals("uniwap") || extraInfo.trim().toLowerCase().equals("3gwap") || extraInfo.trim().toLowerCase().equals("ctwap"))) ? "wifi" : extraInfo.trim().toLowerCase().equals("ctwap") ? "ctwap" : "cmwap";
            }
            return null;
        } catch (Exception e) {
            if (C0645a.f120a) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x016d A[Catch: all -> 0x0131, TryCatch #12 {all -> 0x0131, blocks: (B:7:0x002f, B:88:0x0136, B:90:0x013a, B:91:0x013d, B:101:0x0169, B:103:0x016d, B:104:0x0170, B:114:0x0197, B:116:0x019b, B:117:0x019e), top: B:148:0x002f }] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x019b A[Catch: all -> 0x0131, TryCatch #12 {all -> 0x0131, blocks: (B:7:0x002f, B:88:0x0136, B:90:0x013a, B:91:0x013d, B:101:0x0169, B:103:0x016d, B:104:0x0170, B:114:0x0197, B:116:0x019b, B:117:0x019e), top: B:148:0x002f }] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01c7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x018b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01bb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x015a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x00fa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b4 A[Catch: all -> 0x0109, TryCatch #5 {all -> 0x0109, blocks: (B:45:0x00b0, B:47:0x00b4, B:48:0x00cf), top: B:144:0x00b0 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00f4 A[Catch: Exception -> 0x011a, IOException -> 0x011d, MalformedURLException -> 0x0120, all -> 0x0124, TRY_LEAVE, TryCatch #2 {all -> 0x0124, blocks: (B:8:0x0033, B:66:0x010e, B:68:0x0116, B:69:0x0119, B:51:0x00ec, B:53:0x00f4, B:31:0x0095, B:33:0x009d), top: B:143:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x010c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0116 A[Catch: Exception -> 0x011a, IOException -> 0x011d, MalformedURLException -> 0x0120, all -> 0x0124, TryCatch #2 {all -> 0x0124, blocks: (B:8:0x0033, B:66:0x010e, B:68:0x0116, B:69:0x0119, B:51:0x00ec, B:53:0x00f4, B:31:0x0095, B:33:0x009d), top: B:143:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x013a A[Catch: all -> 0x0131, TryCatch #12 {all -> 0x0131, blocks: (B:7:0x002f, B:88:0x0136, B:90:0x013a, B:91:0x013d, B:101:0x0169, B:103:0x016d, B:104:0x0170, B:114:0x0197, B:116:0x019b, B:117:0x019e), top: B:148:0x002f }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0163 A[PHI: r10 r14
  0x0163: PHI (r10v5 int) = (r10v0 int), (r10v1 int), (r10v2 int) binds: [B:97:0x0161, B:110:0x0192, B:123:0x01c2] A[DONT_GENERATE, DONT_INLINE]
  0x0163: PHI (r14v18 'e' java.io.IOException) = (r14v12 'e' java.io.IOException), (r14v17 'e' java.io.IOException), (r14v23 'e' java.io.IOException) binds: [B:97:0x0161, B:110:0x0192, B:123:0x01c2] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m159a(javax.net.ssl.HttpsURLConnection r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 555
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.C0651g.m159a(javax.net.ssl.HttpsURLConnection):void");
    }

    /* JADX INFO: renamed from: b */
    private static String m160b(HashMap<String, String> map) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (z) {
                z = false;
            } else {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(entry.getKey(), Key.STRING_CHARSET_NAME));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue(), Key.STRING_CHARSET_NAME));
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: b */
    private HttpsURLConnection m161b() {
        String str;
        try {
            URL url = new URL(this.f131b);
            C0645a.m130a("https URL: " + this.f131b);
            String strM158a = m158a(this.f130a);
            if (strM158a != null && !strM158a.equals("")) {
                C0645a.m130a("checkNetwork = " + strM158a);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) (strM158a.equals("cmwap") ? url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.172", 80))) : strM158a.equals("ctwap") ? url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.200", 80))) : url.openConnection());
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setRequestMethod(Constants.HTTP_POST);
                httpsURLConnection.setConnectTimeout(50000);
                httpsURLConnection.setReadTimeout(50000);
                return httpsURLConnection;
            }
            C0645a.m132c("Current network is not available.");
            this.f133d = ErrorMessage.m109a(-10, "Current network is not available.");
            return null;
        } catch (MalformedURLException e) {
            if (C0645a.f120a) {
                e.printStackTrace();
                C0645a.m130a(e.getMessage());
            }
            str = "Auth server could not be parsed as a URL.";
            this.f133d = ErrorMessage.m109a(-11, str);
            return null;
        } catch (Exception e2) {
            if (C0645a.f120a) {
                e2.printStackTrace();
                C0645a.m130a(e2.getMessage());
            }
            str = "Init httpsurlconnection failed.";
            this.f133d = ErrorMessage.m109a(-11, str);
            return null;
        }
    }

    /* JADX INFO: renamed from: c */
    private HashMap<String, String> m162c(HashMap<String, String> map) {
        HashMap<String, String> map2 = new HashMap<>();
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String string = it.next().toString();
            map2.put(string, map.get(string));
        }
        return map2;
    }

    /* JADX INFO: renamed from: a */
    protected String m163a(HashMap<String, String> map) throws Throwable {
        HashMap<String, String> mapM162c = m162c(map);
        this.f132c = mapM162c;
        this.f131b = mapM162c.get("url");
        HttpsURLConnection httpsURLConnectionM161b = m161b();
        if (httpsURLConnectionM161b == null) {
            C0645a.m132c("syncConnect failed,httpsURLConnection is null");
        } else {
            m159a(httpsURLConnectionM161b);
        }
        return this.f133d;
    }

    /* JADX INFO: renamed from: a */
    protected boolean m164a() {
        NetworkInfo activeNetworkInfo;
        C0645a.m130a("checkNetwork start");
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.f130a.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return false;
            }
            if (!activeNetworkInfo.isAvailable()) {
                return false;
            }
            C0645a.m130a("checkNetwork end");
            return true;
        } catch (Exception e) {
            if (C0645a.f120a) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
