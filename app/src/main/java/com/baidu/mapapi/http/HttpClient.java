package com.baidu.mapapi.http;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.baidu.mapapi.JNIInitializer;
import com.baidu.mapapi.common.Logger;
import com.baidu.platform.comapi.util.C0779e;
import com.bumptech.glide.load.Key;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class HttpClient {

    /* JADX INFO: renamed from: a */
    HttpURLConnection f213a;

    /* JADX INFO: renamed from: b */
    private String f214b = null;

    /* JADX INFO: renamed from: c */
    private String f215c = null;

    /* JADX INFO: renamed from: d */
    private int f216d;

    /* JADX INFO: renamed from: e */
    private int f217e;

    /* JADX INFO: renamed from: f */
    private String f218f;

    /* JADX INFO: renamed from: g */
    private ProtoResultCallback f219g;

    public enum HttpStateError {
        NO_ERROR,
        NETWORK_ERROR,
        INNER_ERROR,
        REQUEST_ERROR,
        SERVER_ERROR
    }

    public static abstract class ProtoResultCallback {
        public abstract void onFailed(HttpStateError httpStateError);

        public abstract void onSuccess(String str);
    }

    public HttpClient(String str, ProtoResultCallback protoResultCallback) {
        this.f218f = str;
        this.f219g = protoResultCallback;
    }

    /* JADX INFO: renamed from: a */
    private HttpURLConnection m236a() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f214b).openConnection();
            httpURLConnection.setRequestMethod(this.f218f);
            httpURLConnection.setDoOutput(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(this.f216d);
            httpURLConnection.setReadTimeout(this.f217e);
            return httpURLConnection;
        } catch (Exception e) {
            if (Logger.debugEnable()) {
                e.printStackTrace();
                return null;
            }
            Logger.logW("HttpClient", e.getMessage());
            return null;
        }
    }

    public static String getAuthToken() {
        return C0779e.f1112z;
    }

    public static String getPhoneInfo() {
        return C0779e.m802c();
    }

    protected boolean checkNetwork() {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) JNIInitializer.getCachedContext().getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return false;
            }
            return activeNetworkInfo.isAvailable();
        } catch (Exception e) {
            if (Logger.debugEnable()) {
                e.printStackTrace();
            } else {
                Logger.logW("HttpClient", e.getMessage());
            }
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [int] */
    /* JADX WARN: Type inference failed for: r1v17, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.StringBuilder] */
    protected void request(String str) throws Throwable {
        BufferedReader bufferedReader;
        Throwable th;
        ?? r1;
        BufferedReader bufferedReader2;
        Exception e;
        ?? responseCode;
        this.f214b = str;
        if (!checkNetwork()) {
            this.f219g.onFailed(HttpStateError.NETWORK_ERROR);
            return;
        }
        HttpURLConnection httpURLConnectionM236a = m236a();
        this.f213a = httpURLConnectionM236a;
        if (httpURLConnectionM236a == null) {
            this.f219g.onFailed(HttpStateError.INNER_ERROR);
            return;
        }
        if (TextUtils.isEmpty(this.f214b)) {
            this.f219g.onFailed(HttpStateError.REQUEST_ERROR);
            return;
        }
        try {
            this.f213a.connect();
            try {
                try {
                    responseCode = this.f213a.getResponseCode();
                } catch (Exception e2) {
                    bufferedReader2 = null;
                    e = e2;
                    responseCode = 0;
                } catch (Throwable th2) {
                    bufferedReader = null;
                    th = th2;
                    r1 = 0;
                }
                try {
                    if (200 != responseCode) {
                        HttpStateError httpStateError = HttpStateError.NO_ERROR;
                        HttpStateError httpStateError2 = responseCode >= 500 ? HttpStateError.SERVER_ERROR : responseCode >= 400 ? HttpStateError.REQUEST_ERROR : HttpStateError.INNER_ERROR;
                        if (Logger.debugEnable()) {
                            Logger.logW("HttpClient", this.f213a.getErrorStream().toString());
                        } else {
                            Logger.logW("HttpClient", "Get response from server failed, http response code=" + responseCode + ", error=" + httpStateError2);
                        }
                        this.f219g.onFailed(httpStateError2);
                        if (this.f213a != null) {
                            this.f213a.disconnect();
                            return;
                        }
                        return;
                    }
                    responseCode = this.f213a.getInputStream();
                    bufferedReader2 = new BufferedReader(new InputStreamReader((InputStream) responseCode, Key.STRING_CHARSET_NAME));
                    try {
                        StringBuffer stringBuffer = new StringBuffer();
                        while (true) {
                            int i = bufferedReader2.read();
                            if (i == -1) {
                                break;
                            } else {
                                stringBuffer.append((char) i);
                            }
                        }
                        this.f215c = stringBuffer.toString();
                        if (responseCode != 0) {
                            bufferedReader2.close();
                            responseCode.close();
                        }
                        if (this.f213a != null) {
                            this.f213a.disconnect();
                        }
                        this.f219g.onSuccess(this.f215c);
                    } catch (Exception e3) {
                        e = e3;
                        if (Logger.debugEnable()) {
                            e.printStackTrace();
                        } else {
                            Logger.logW("HttpClient", e.getMessage());
                        }
                        this.f219g.onFailed(HttpStateError.INNER_ERROR);
                        if (responseCode != 0 && bufferedReader2 != null) {
                            bufferedReader2.close();
                            responseCode.close();
                        }
                        if (this.f213a != null) {
                            this.f213a.disconnect();
                        }
                    }
                } catch (Exception e4) {
                    bufferedReader2 = null;
                    e = e4;
                } catch (Throwable th3) {
                    bufferedReader = null;
                    th = th3;
                    r1 = responseCode;
                    if (r1 != 0 && bufferedReader != null) {
                        bufferedReader.close();
                        r1.close();
                    }
                    if (this.f213a != null) {
                        this.f213a.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Exception e5) {
            if (Logger.debugEnable()) {
                e5.printStackTrace();
            } else {
                Logger.logW("HttpClient", e5.getMessage());
            }
            this.f219g.onFailed(HttpStateError.INNER_ERROR);
        }
    }

    public void setMaxTimeOut(int i) {
        this.f216d = i;
    }

    public void setReadTimeOut(int i) {
        this.f217e = i;
    }
}
