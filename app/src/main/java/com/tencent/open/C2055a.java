package com.tencent.open;

import android.net.Uri;
import android.webkit.WebView;
import com.bumptech.glide.load.Key;
import com.tencent.open.p026a.C2061f;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: renamed from: com.tencent.open.a */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2055a {

    /* JADX INFO: renamed from: a */
    protected HashMap<String, b> f3130a = new HashMap<>();

    /* JADX INFO: renamed from: com.tencent.open.a$a */
    /* JADX INFO: compiled from: ProGuard */
    public static class a {

        /* JADX INFO: renamed from: a */
        protected WeakReference<WebView> f3131a;

        /* JADX INFO: renamed from: b */
        protected long f3132b;

        /* JADX INFO: renamed from: c */
        protected String f3133c;

        public a(WebView webView, long j, String str) {
            this.f3131a = new WeakReference<>(webView);
            this.f3132b = j;
            this.f3133c = str;
        }

        /* JADX INFO: renamed from: a */
        public void mo2085a(Object obj) {
            String string;
            WebView webView = this.f3131a.get();
            if (webView == null) {
                return;
            }
            if (obj instanceof String) {
                string = "'" + ((Object) ((String) obj).replace("\\", "\\\\").replace("'", "\\'")) + "'";
            } else {
                string = ((obj instanceof Number) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof Boolean)) ? obj.toString() : "'undefined'";
            }
            webView.loadUrl("javascript:window.JsBridge&&JsBridge.callback(" + this.f3132b + ",{'r':0,'result':" + string + "});");
        }

        /* JADX INFO: renamed from: a */
        public void mo2084a() {
            WebView webView = this.f3131a.get();
            if (webView == null) {
                return;
            }
            webView.loadUrl("javascript:window.JsBridge&&JsBridge.callback(" + this.f3132b + ",{'r':1,'result':'no such method'})");
        }

        /* JADX INFO: renamed from: a */
        public void mo2086a(String str) {
            WebView webView = this.f3131a.get();
            if (webView != null) {
                webView.loadUrl("javascript:" + str);
            }
        }
    }

    /* JADX INFO: renamed from: com.tencent.open.a$b */
    /* JADX INFO: compiled from: ProGuard */
    public static class b {
        public boolean customCallback() {
            return false;
        }

        public void call(String str, List<String> list, a aVar) {
            Method method;
            Object objInvoke;
            Method[] declaredMethods = getClass().getDeclaredMethods();
            int length = declaredMethods.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    method = null;
                    break;
                }
                method = declaredMethods[i];
                if (method.getName().equals(str) && method.getParameterTypes().length == list.size()) {
                    break;
                } else {
                    i++;
                }
            }
            if (method == null) {
                if (aVar != null) {
                    aVar.mo2084a();
                    return;
                }
                return;
            }
            try {
                int size = list.size();
                if (size != 0) {
                    objInvoke = size != 1 ? size != 2 ? size != 3 ? size != 4 ? size != 5 ? method.invoke(this, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5)) : method.invoke(this, list.get(0), list.get(1), list.get(2), list.get(3), list.get(4)) : method.invoke(this, list.get(0), list.get(1), list.get(2), list.get(3)) : method.invoke(this, list.get(0), list.get(1), list.get(2)) : method.invoke(this, list.get(0), list.get(1)) : method.invoke(this, list.get(0));
                } else {
                    objInvoke = method.invoke(this, new Object[0]);
                }
                Class<?> returnType = method.getReturnType();
                C2061f.m2130b("openSDK_LOG.JsBridge", "-->call, result: " + objInvoke + " | ReturnType: " + returnType.getName());
                if (!"void".equals(returnType.getName()) && returnType != Void.class) {
                    if (aVar == null || !customCallback()) {
                        return;
                    }
                    aVar.mo2086a(objInvoke != null ? objInvoke.toString() : null);
                    return;
                }
                if (aVar != null) {
                    aVar.mo2085a((Object) null);
                }
            } catch (Exception e) {
                C2061f.m2131b("openSDK_LOG.JsBridge", "-->handler call mehtod ex. targetMethod: " + method, e);
                if (aVar != null) {
                    aVar.mo2084a();
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public void m2081a(b bVar, String str) {
        this.f3130a.put(str, bVar);
    }

    /* JADX INFO: renamed from: a */
    public void mo2082a(String str, String str2, List<String> list, a aVar) {
        C2061f.m2127a("openSDK_LOG.JsBridge", "getResult---objName = " + str + " methodName = " + str2);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.set(i, URLDecoder.decode(list.get(i), Key.STRING_CHARSET_NAME));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        b bVar = this.f3130a.get(str);
        if (bVar != null) {
            C2061f.m2130b("openSDK_LOG.JsBridge", "call----");
            bVar.call(str2, list, aVar);
        } else {
            C2061f.m2130b("openSDK_LOG.JsBridge", "not call----objName NOT FIND");
            if (aVar != null) {
                aVar.mo2084a();
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public boolean mo2083a(WebView webView, String str) {
        C2061f.m2127a("openSDK_LOG.JsBridge", "-->canHandleUrl---url = " + str);
        if (str == null || !Uri.parse(str).getScheme().equals("jsbridge")) {
            return false;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList((str + "/#").split("/")));
        if (arrayList.size() < 6) {
            return false;
        }
        String str2 = (String) arrayList.get(2);
        String str3 = (String) arrayList.get(3);
        List<String> listSubList = arrayList.subList(4, arrayList.size() - 1);
        a aVar = new a(webView, 4L, str);
        webView.getUrl();
        mo2082a(str2, str3, listSubList, aVar);
        return true;
    }
}
