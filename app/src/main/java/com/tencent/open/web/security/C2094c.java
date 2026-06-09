package com.tencent.open.web.security;

import android.webkit.WebView;
import com.tencent.open.C2055a;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.p028c.C2076c;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.tencent.open.web.security.c */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2094c extends C2055a.a {

    /* JADX INFO: renamed from: d */
    private String f3307d;

    public C2094c(WebView webView, long j, String str, String str2) {
        super(webView, j, str);
        this.f3307d = str2;
    }

    @Override // com.tencent.open.C2055a.a
    /* JADX INFO: renamed from: a */
    public void mo2085a(Object obj) {
        C2061f.m2127a("openSDK_LOG.SecureJsListener", "-->onComplete, result: " + obj);
    }

    @Override // com.tencent.open.C2055a.a
    /* JADX INFO: renamed from: a */
    public void mo2084a() {
        C2061f.m2130b("openSDK_LOG.SecureJsListener", "-->onNoMatchMethod...");
    }

    @Override // com.tencent.open.C2055a.a
    /* JADX INFO: renamed from: a */
    public void mo2086a(String str) {
        C2061f.m2127a("openSDK_LOG.SecureJsListener", "-->onCustomCallback, js: " + str);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("result", !C2076c.f3245a ? -4 : 0);
            jSONObject.put("sn", this.f3132b);
            jSONObject.put("data", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        m2285b(jSONObject.toString());
    }

    /* JADX INFO: renamed from: b */
    private void m2285b(String str) {
        WebView webView = this.f3131a.get();
        if (webView != null) {
            StringBuffer stringBuffer = new StringBuffer("javascript:");
            stringBuffer.append("if(!!");
            stringBuffer.append(this.f3307d);
            stringBuffer.append("){");
            stringBuffer.append(this.f3307d);
            stringBuffer.append("(");
            stringBuffer.append(str);
            stringBuffer.append(")}");
            String string = stringBuffer.toString();
            C2061f.m2127a("openSDK_LOG.SecureJsListener", "-->callback, callback: " + string);
            webView.loadUrl(string);
        }
    }
}
