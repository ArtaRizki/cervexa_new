package com.tencent.open.web.security;

import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import com.bumptech.glide.load.Key;
import com.tencent.open.C2055a;
import com.tencent.open.p026a.C2061f;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: renamed from: com.tencent.open.web.security.b */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2093b extends C2055a {
    @Override // com.tencent.open.C2055a
    /* JADX INFO: renamed from: a */
    public void mo2082a(String str, String str2, List<String> list, C2055a.a aVar) {
        C2061f.m2127a("openSDK_LOG.SecureJsBridge", "-->getResult, objectName: " + str + " | methodName: " + str2);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.set(i, URLDecoder.decode(list.get(i), Key.STRING_CHARSET_NAME));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        C2055a.b bVar = this.f3130a.get(str);
        if (bVar != null) {
            C2061f.m2130b("openSDK_LOG.SecureJsBridge", "-->handler != null");
            bVar.call(str2, list, aVar);
        } else {
            C2061f.m2130b("openSDK_LOG.SecureJsBridge", "-->handler == null");
            if (aVar != null) {
                aVar.mo2084a();
            }
        }
    }

    @Override // com.tencent.open.C2055a
    /* JADX INFO: renamed from: a */
    public boolean mo2083a(WebView webView, String str) {
        C2061f.m2127a("openSDK_LOG.SecureJsBridge", "-->canHandleUrl---url = " + str);
        if (str == null || !Uri.parse(str).getScheme().equals("jsbridge")) {
            return false;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList((str + "/#").split("/")));
        if (arrayList.size() < 7) {
            return false;
        }
        String str2 = (String) arrayList.get(2);
        String str3 = (String) arrayList.get(3);
        String str4 = (String) arrayList.get(4);
        String str5 = (String) arrayList.get(5);
        C2061f.m2127a("openSDK_LOG.SecureJsBridge", "-->canHandleUrl, objectName: " + str2 + " | methodName: " + str3 + " | snStr: " + str4);
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
            try {
                mo2082a(str2, str3, arrayList.subList(6, arrayList.size() - 1), new C2094c(webView, Long.parseLong(str4), str, str5));
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }
}
