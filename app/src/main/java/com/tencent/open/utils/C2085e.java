package com.tencent.open.utils;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import com.bumptech.glide.load.Key;
import com.tencent.connect.common.Constants;
import com.tencent.open.p026a.C2061f;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.tencent.open.utils.e */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2085e {

    /* JADX INFO: renamed from: a */
    private static Map<String, C2085e> f3271a = Collections.synchronizedMap(new HashMap());

    /* JADX INFO: renamed from: b */
    private static String f3272b = null;

    /* JADX INFO: renamed from: c */
    private Context f3273c;

    /* JADX INFO: renamed from: d */
    private String f3274d;

    /* JADX INFO: renamed from: e */
    private JSONObject f3275e = null;

    /* JADX INFO: renamed from: f */
    private long f3276f = 0;

    /* JADX INFO: renamed from: g */
    private int f3277g = 0;

    /* JADX INFO: renamed from: h */
    private boolean f3278h = true;

    /* JADX INFO: renamed from: a */
    public static C2085e m2221a(Context context, String str) {
        C2085e c2085e;
        synchronized (f3271a) {
            C2061f.m2127a("openSDK_LOG.OpenConfig", "getInstance begin");
            if (str != null) {
                f3272b = str;
            }
            if (str == null) {
                str = f3272b != null ? f3272b : "0";
            }
            c2085e = f3271a.get(str);
            if (c2085e == null) {
                c2085e = new C2085e(context, str);
                f3271a.put(str, c2085e);
            }
            C2061f.m2127a("openSDK_LOG.OpenConfig", "getInstance end");
        }
        return c2085e;
    }

    private C2085e(Context context, String str) {
        this.f3273c = null;
        this.f3274d = null;
        this.f3273c = context.getApplicationContext();
        this.f3274d = str;
        m2222a();
        m2226b();
    }

    /* JADX INFO: renamed from: a */
    private void m2222a() {
        try {
            this.f3275e = new JSONObject(m2227c("com.tencent.open.config.json"));
        } catch (JSONException unused) {
            this.f3275e = new JSONObject();
        }
    }

    /* JADX INFO: renamed from: c */
    private String m2227c(String str) {
        InputStream inputStreamOpen;
        String str2;
        String string = "";
        try {
            try {
                if (this.f3274d != null) {
                    str2 = str + "." + this.f3274d;
                } else {
                    str2 = str;
                }
                inputStreamOpen = this.f3273c.openFileInput(str2);
            } catch (FileNotFoundException unused) {
                inputStreamOpen = this.f3273c.getAssets().open(str);
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamOpen, Charset.forName(Key.STRING_CHARSET_NAME)));
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                try {
                    try {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            stringBuffer.append(line);
                        } catch (Throwable th) {
                            try {
                                inputStreamOpen.close();
                                bufferedReader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            throw th;
                        }
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        inputStreamOpen.close();
                        bufferedReader.close();
                    }
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return string;
            }
            string = stringBuffer.toString();
            inputStreamOpen.close();
            bufferedReader.close();
            return string;
        } catch (IOException e4) {
            e4.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: renamed from: a */
    private void m2224a(String str, String str2) {
        try {
            if (this.f3274d != null) {
                str = str + "." + this.f3274d;
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.f3273c.openFileOutput(str, 0), Charset.forName(Key.STRING_CHARSET_NAME));
            outputStreamWriter.write(str2);
            outputStreamWriter.flush();
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [com.tencent.open.utils.e$1] */
    /* JADX INFO: renamed from: b */
    private void m2226b() {
        if (this.f3277g != 0) {
            m2229d("update thread is running, return");
            return;
        }
        this.f3277g = 1;
        final Bundle bundle = new Bundle();
        bundle.putString("appid", this.f3274d);
        bundle.putString("appid_for_getting_config", this.f3274d);
        bundle.putString("status_os", Build.VERSION.RELEASE);
        bundle.putString("status_machine", Build.MODEL);
        bundle.putString("status_version", Build.VERSION.SDK);
        bundle.putString("sdkv", Constants.SDK_VERSION);
        bundle.putString("sdkp", "a");
        new Thread() { // from class: com.tencent.open.utils.e.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    C2085e.this.m2225a(C2089i.m2271d(HttpUtils.openUrl2(C2085e.this.f3273c, "http://cgi.connect.qq.com/qqconnectopen/openapi/policy_conf", "GET", bundle).f3298a));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                C2085e.this.f3277g = 0;
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m2225a(JSONObject jSONObject) {
        m2229d("cgi back, do update");
        this.f3275e = jSONObject;
        m2224a("com.tencent.open.config.json", jSONObject.toString());
        this.f3276f = SystemClock.elapsedRealtime();
    }

    /* JADX INFO: renamed from: c */
    private void m2228c() {
        int iOptInt = this.f3275e.optInt("Common_frequency");
        if (iOptInt == 0) {
            iOptInt = 1;
        }
        if (SystemClock.elapsedRealtime() - this.f3276f >= iOptInt * 3600000) {
            m2226b();
        }
    }

    /* JADX INFO: renamed from: a */
    public int m2230a(String str) {
        m2229d("get " + str);
        m2228c();
        return this.f3275e.optInt(str);
    }

    /* JADX INFO: renamed from: b */
    public boolean m2231b(String str) {
        m2229d("get " + str);
        m2228c();
        Object objOpt = this.f3275e.opt(str);
        if (objOpt == null) {
            return false;
        }
        if (objOpt instanceof Integer) {
            return !objOpt.equals(0);
        }
        if (objOpt instanceof Boolean) {
            return ((Boolean) objOpt).booleanValue();
        }
        return false;
    }

    /* JADX INFO: renamed from: d */
    private void m2229d(String str) {
        if (this.f3278h) {
            C2061f.m2127a("openSDK_LOG.OpenConfig", str + "; appid: " + this.f3274d);
        }
    }
}
