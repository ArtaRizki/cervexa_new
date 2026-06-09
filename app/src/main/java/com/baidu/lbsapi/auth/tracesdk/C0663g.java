package com.baidu.lbsapi.auth.tracesdk;

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

/* JADX INFO: renamed from: com.baidu.lbsapi.auth.tracesdk.g */
/* JADX INFO: loaded from: classes.dex */
public class C0663g {

    /* JADX INFO: renamed from: a */
    private Context f166a;

    /* JADX INFO: renamed from: b */
    private String f167b = null;

    /* JADX INFO: renamed from: c */
    private HashMap f168c = null;

    /* JADX INFO: renamed from: d */
    private String f169d = null;

    public C0663g(Context context) {
        this.f166a = context;
    }

    /* JADX INFO: renamed from: a */
    private String m217a(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable()) {
                String extraInfo = activeNetworkInfo.getExtraInfo();
                String str = "cmwap";
                if (extraInfo == null || !(extraInfo.trim().toLowerCase().equals("cmwap") || extraInfo.trim().toLowerCase().equals("uniwap") || extraInfo.trim().toLowerCase().equals("3gwap") || extraInfo.trim().toLowerCase().equals("ctwap"))) {
                    str = "wifi";
                } else if (extraInfo.trim().toLowerCase().equals("ctwap")) {
                    return "ctwap";
                }
                return str;
            }
            return null;
        } catch (Exception e) {
            if (C0657a.f156a) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0161 A[Catch: all -> 0x0128, TryCatch #9 {all -> 0x0128, blocks: (B:7:0x002c, B:88:0x012d, B:90:0x0131, B:91:0x0134, B:101:0x015d, B:103:0x0161, B:104:0x0164, B:114:0x0188, B:116:0x018c, B:117:0x018f), top: B:147:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x018c A[Catch: all -> 0x0128, TryCatch #9 {all -> 0x0128, blocks: (B:7:0x002c, B:88:0x012d, B:90:0x0131, B:91:0x0134, B:101:0x015d, B:103:0x0161, B:104:0x0164, B:114:0x0188, B:116:0x018c, B:117:0x018f), top: B:147:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01b5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x00f1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x014e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01a9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x017c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b1 A[Catch: all -> 0x0100, TryCatch #0 {all -> 0x0100, blocks: (B:45:0x00ad, B:47:0x00b1, B:48:0x00c9), top: B:143:0x00ad }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00eb A[Catch: Exception -> 0x0111, IOException -> 0x0114, MalformedURLException -> 0x0117, all -> 0x011b, TRY_LEAVE, TryCatch #22 {all -> 0x011b, blocks: (B:8:0x0030, B:66:0x0105, B:68:0x010d, B:69:0x0110, B:51:0x00e3, B:53:0x00eb, B:31:0x0092, B:33:0x009a), top: B:156:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0103 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x010d A[Catch: Exception -> 0x0111, IOException -> 0x0114, MalformedURLException -> 0x0117, all -> 0x011b, TryCatch #22 {all -> 0x011b, blocks: (B:8:0x0030, B:66:0x0105, B:68:0x010d, B:69:0x0110, B:51:0x00e3, B:53:0x00eb, B:31:0x0092, B:33:0x009a), top: B:156:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0131 A[Catch: all -> 0x0128, TryCatch #9 {all -> 0x0128, blocks: (B:7:0x002c, B:88:0x012d, B:90:0x0131, B:91:0x0134, B:101:0x015d, B:103:0x0161, B:104:0x0164, B:114:0x0188, B:116:0x018c, B:117:0x018f), top: B:147:0x002c }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0157 A[PHI: r10 r14
  0x0157: PHI (r10v5 int) = (r10v0 int), (r10v1 int), (r10v2 int) binds: [B:97:0x0155, B:110:0x0183, B:123:0x01b0] A[DONT_GENERATE, DONT_INLINE]
  0x0157: PHI (r14v18 'e' java.io.IOException) = (r14v12 'e' java.io.IOException), (r14v17 'e' java.io.IOException), (r14v23 'e' java.io.IOException) binds: [B:97:0x0155, B:110:0x0183, B:123:0x01b0] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX INFO: renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m218a(javax.net.ssl.HttpsURLConnection r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 528
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.tracesdk.C0663g.m218a(javax.net.ssl.HttpsURLConnection):void");
    }

    /* JADX INFO: renamed from: b */
    private static String m219b(HashMap map) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Map.Entry entry : map.entrySet()) {
            if (z) {
                z = false;
            } else {
                sb.append("&");
            }
            sb.append(URLEncoder.encode((String) entry.getKey(), Key.STRING_CHARSET_NAME));
            sb.append("=");
            sb.append(URLEncoder.encode((String) entry.getValue(), Key.STRING_CHARSET_NAME));
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: b */
    private HttpsURLConnection m220b() {
        String str;
        try {
            URL url = new URL(this.f167b);
            C0657a.m189a("https URL: " + this.f167b);
            String strM217a = m217a(this.f166a);
            if (strM217a != null && !strM217a.equals("")) {
                C0657a.m189a("checkNetwork = " + strM217a);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) (strM217a.equals("cmwap") ? url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.172", 80))) : strM217a.equals("ctwap") ? url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.200", 80))) : url.openConnection());
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setRequestMethod(Constants.HTTP_POST);
                httpsURLConnection.setConnectTimeout(50000);
                httpsURLConnection.setReadTimeout(50000);
                return httpsURLConnection;
            }
            C0657a.m191c("Current network is not available.");
            this.f169d = ErrorMessage.m168a(-10, "Current network is not available.");
            return null;
        } catch (MalformedURLException e) {
            if (C0657a.f156a) {
                e.printStackTrace();
                C0657a.m189a(e.getMessage());
            }
            str = "Auth server could not be parsed as a URL.";
            this.f169d = ErrorMessage.m168a(-11, str);
            return null;
        } catch (Exception e2) {
            if (C0657a.f156a) {
                e2.printStackTrace();
                C0657a.m189a(e2.getMessage());
            }
            str = "Init httpsurlconnection failed.";
            this.f169d = ErrorMessage.m168a(-11, str);
            return null;
        }
    }

    /* JADX INFO: renamed from: c */
    private HashMap m221c(HashMap map) {
        HashMap map2 = new HashMap();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String string = ((String) it.next()).toString();
            map2.put(string, map.get(string));
        }
        return map2;
    }

    /* JADX INFO: renamed from: a */
    protected String m222a(HashMap map) throws Throwable {
        HashMap mapM221c = m221c(map);
        this.f168c = mapM221c;
        this.f167b = (String) mapM221c.get("url");
        HttpsURLConnection httpsURLConnectionM220b = m220b();
        if (httpsURLConnectionM220b == null) {
            C0657a.m191c("syncConnect failed,httpsURLConnection is null");
        } else {
            m218a(httpsURLConnectionM220b);
        }
        return this.f169d;
    }

    /* JADX INFO: renamed from: a */
    protected boolean m223a() {
        NetworkInfo activeNetworkInfo;
        C0657a.m189a("checkNetwork start");
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.f166a.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return false;
            }
            if (!activeNetworkInfo.isAvailable()) {
                return false;
            }
            C0657a.m189a("checkNetwork end");
            return true;
        } catch (Exception e) {
            if (C0657a.f156a) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
