package com.tencent.p023mm.opensdk.diffdev.p025a;

import com.tencent.p023mm.opensdk.utils.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

/* JADX INFO: renamed from: com.tencent.mm.opensdk.diffdev.a.e */
/* JADX INFO: loaded from: classes2.dex */
public final class C2046e {
    /* JADX INFO: renamed from: a */
    public static byte[] m2043a(String str, int i) {
        String string;
        StringBuilder sb;
        String message;
        if (str != null && str.length() != 0) {
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(str);
            if (i >= 0) {
                try {
                    HttpConnectionParams.setSoTimeout(defaultHttpClient.getParams(), i);
                } catch (Exception e) {
                    sb = new StringBuilder("httpGet, Exception ex = ");
                    message = e.getMessage();
                    sb.append(message);
                    string = sb.toString();
                    Log.m2051e("MicroMsg.SDK.NetUtil", string);
                    return null;
                } catch (IncompatibleClassChangeError e2) {
                    sb = new StringBuilder("httpGet, IncompatibleClassChangeError ex = ");
                    message = e2.getMessage();
                    sb.append(message);
                    string = sb.toString();
                    Log.m2051e("MicroMsg.SDK.NetUtil", string);
                    return null;
                } catch (Throwable th) {
                    sb = new StringBuilder("httpGet, Throwable ex = ");
                    message = th.getMessage();
                    sb.append(message);
                    string = sb.toString();
                    Log.m2051e("MicroMsg.SDK.NetUtil", string);
                    return null;
                }
            }
            HttpResponse httpResponseExecute = defaultHttpClient.execute(httpGet);
            if (httpResponseExecute.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toByteArray(httpResponseExecute.getEntity());
            }
            Log.m2051e("MicroMsg.SDK.NetUtil", "httpGet fail, status code = " + httpResponseExecute.getStatusLine().getStatusCode());
            return null;
        }
        string = "httpGet, url is null";
        Log.m2051e("MicroMsg.SDK.NetUtil", string);
        return null;
    }
}
