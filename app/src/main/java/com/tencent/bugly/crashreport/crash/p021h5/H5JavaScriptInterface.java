package com.tencent.bugly.crashreport.crash.p021h5;

import android.webkit.JavascriptInterface;
import com.generalplus.GoPlusDrone.Fragment.BaseFragment;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.inner.InnerApi;
import com.tencent.bugly.proguard.C2021x;
import com.tencent.bugly.proguard.C2023z;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public class H5JavaScriptInterface {

    /* JADX INFO: renamed from: a */
    private static HashSet<Integer> f2621a = new HashSet<>();

    /* JADX INFO: renamed from: b */
    private String f2622b = null;

    /* JADX INFO: renamed from: c */
    private Thread f2623c = null;

    /* JADX INFO: renamed from: d */
    private String f2624d = null;

    /* JADX INFO: renamed from: e */
    private Map<String, String> f2625e = null;

    private H5JavaScriptInterface() {
    }

    public static H5JavaScriptInterface getInstance(CrashReport.WebViewInterface webViewInterface) {
        String string = null;
        if (webViewInterface == null || f2621a.contains(Integer.valueOf(webViewInterface.hashCode()))) {
            return null;
        }
        H5JavaScriptInterface h5JavaScriptInterface = new H5JavaScriptInterface();
        f2621a.add(Integer.valueOf(webViewInterface.hashCode()));
        Thread threadCurrentThread = Thread.currentThread();
        h5JavaScriptInterface.f2623c = threadCurrentThread;
        if (threadCurrentThread != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            for (int i = 2; i < threadCurrentThread.getStackTrace().length; i++) {
                StackTraceElement stackTraceElement = threadCurrentThread.getStackTrace()[i];
                if (!stackTraceElement.toString().contains("crashreport")) {
                    sb.append(stackTraceElement.toString());
                    sb.append("\n");
                }
            }
            string = sb.toString();
        }
        h5JavaScriptInterface.f2624d = string;
        HashMap map = new HashMap();
        StringBuilder sb2 = new StringBuilder();
        sb2.append((Object) webViewInterface.getContentDescription());
        map.put("[WebView] ContentDescription", sb2.toString());
        h5JavaScriptInterface.f2625e = map;
        return h5JavaScriptInterface;
    }

    /* JADX INFO: renamed from: a */
    private static C1975a m1654a(String str) {
        String string;
        if (str != null && str.length() > 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                C1975a c1975a = new C1975a();
                c1975a.f2626a = jSONObject.getString("projectRoot");
                if (c1975a.f2626a == null) {
                    return null;
                }
                c1975a.f2627b = jSONObject.getString("context");
                if (c1975a.f2627b == null) {
                    return null;
                }
                c1975a.f2628c = jSONObject.getString("url");
                if (c1975a.f2628c == null) {
                    return null;
                }
                c1975a.f2629d = jSONObject.getString("userAgent");
                if (c1975a.f2629d == null) {
                    return null;
                }
                c1975a.f2630e = jSONObject.getString(IjkMediaMeta.IJKM_KEY_LANGUAGE);
                if (c1975a.f2630e == null) {
                    return null;
                }
                c1975a.f2631f = jSONObject.getString(BaseFragment.DATA_NAME);
                if (c1975a.f2631f == null || c1975a.f2631f.equals(IConstant.DEFAULT_PATH) || (string = jSONObject.getString("stacktrace")) == null) {
                    return null;
                }
                int iIndexOf = string.indexOf("\n");
                if (iIndexOf < 0) {
                    C2021x.m1872d("H5 crash stack's format is wrong!", new Object[0]);
                    return null;
                }
                c1975a.f2633h = string.substring(iIndexOf + 1);
                c1975a.f2632g = string.substring(0, iIndexOf);
                int iIndexOf2 = c1975a.f2632g.indexOf(":");
                if (iIndexOf2 > 0) {
                    c1975a.f2632g = c1975a.f2632g.substring(iIndexOf2 + 1);
                }
                c1975a.f2634i = jSONObject.getString("file");
                if (c1975a.f2631f == null) {
                    return null;
                }
                c1975a.f2635j = jSONObject.getLong("lineNumber");
                if (c1975a.f2635j < 0) {
                    return null;
                }
                c1975a.f2636k = jSONObject.getLong("columnNumber");
                if (c1975a.f2636k < 0) {
                    return null;
                }
                C2021x.m1866a("H5 crash information is following: ", new Object[0]);
                C2021x.m1866a("[projectRoot]: " + c1975a.f2626a, new Object[0]);
                C2021x.m1866a("[context]: " + c1975a.f2627b, new Object[0]);
                C2021x.m1866a("[url]: " + c1975a.f2628c, new Object[0]);
                C2021x.m1866a("[userAgent]: " + c1975a.f2629d, new Object[0]);
                C2021x.m1866a("[language]: " + c1975a.f2630e, new Object[0]);
                C2021x.m1866a("[name]: " + c1975a.f2631f, new Object[0]);
                C2021x.m1866a("[message]: " + c1975a.f2632g, new Object[0]);
                C2021x.m1866a("[stacktrace]: \n" + c1975a.f2633h, new Object[0]);
                C2021x.m1866a("[file]: " + c1975a.f2634i, new Object[0]);
                C2021x.m1866a("[lineNumber]: " + c1975a.f2635j, new Object[0]);
                C2021x.m1866a("[columnNumber]: " + c1975a.f2636k, new Object[0]);
                return c1975a;
            } catch (Throwable th) {
                if (!C2021x.m1867a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    @JavascriptInterface
    public void printLog(String str) {
        C2021x.m1872d("Log from js: %s", str);
    }

    @JavascriptInterface
    public void reportJSException(String str) {
        if (str == null) {
            C2021x.m1872d("Payload from JS is null.", new Object[0]);
            return;
        }
        String strM1904a = C2023z.m1904a(str.getBytes());
        String str2 = this.f2622b;
        if (str2 != null && str2.equals(strM1904a)) {
            C2021x.m1872d("Same payload from js. Please check whether you've injected bugly.js more than one times.", new Object[0]);
            return;
        }
        this.f2622b = strM1904a;
        C2021x.m1872d("Handling JS exception ...", new Object[0]);
        C1975a c1975aM1654a = m1654a(str);
        if (c1975aM1654a == null) {
            C2021x.m1872d("Failed to parse payload.", new Object[0]);
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        if (c1975aM1654a.f2626a != null) {
            linkedHashMap2.put("[JS] projectRoot", c1975aM1654a.f2626a);
        }
        if (c1975aM1654a.f2627b != null) {
            linkedHashMap2.put("[JS] context", c1975aM1654a.f2627b);
        }
        if (c1975aM1654a.f2628c != null) {
            linkedHashMap2.put("[JS] url", c1975aM1654a.f2628c);
        }
        if (c1975aM1654a.f2629d != null) {
            linkedHashMap2.put("[JS] userAgent", c1975aM1654a.f2629d);
        }
        if (c1975aM1654a.f2634i != null) {
            linkedHashMap2.put("[JS] file", c1975aM1654a.f2634i);
        }
        if (c1975aM1654a.f2635j != 0) {
            linkedHashMap2.put("[JS] lineNumber", Long.toString(c1975aM1654a.f2635j));
        }
        linkedHashMap.putAll(linkedHashMap2);
        linkedHashMap.putAll(this.f2625e);
        linkedHashMap.put("Java Stack", this.f2624d);
        Thread thread = this.f2623c;
        if (c1975aM1654a != null) {
            InnerApi.postH5CrashAsync(thread, c1975aM1654a.f2631f, c1975aM1654a.f2632g, c1975aM1654a.f2633h, linkedHashMap);
        }
    }
}
