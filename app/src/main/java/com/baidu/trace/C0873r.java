package com.baidu.trace;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.baidu.trace.r */
/* JADX INFO: loaded from: classes.dex */
public final class C0873r {
    /* JADX INFO: renamed from: a */
    public static void m1273a(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            byte b = 15;
            byte b2 = 30;
            byte b3 = 5;
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                int i2 = jSONObject.getInt("id");
                byte b4 = (byte) jSONObject.getInt("value");
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            if (i2 == 4) {
                                C0881z.f1884s = b4;
                            } else if (i2 == 12 && b4 >= C0819ar.f1577c) {
                                b3 = b4;
                            }
                        } else if (b4 >= 5 && b4 <= 128) {
                            C0881z.f1883r = b4;
                        }
                    } else if (b4 >= 2) {
                        b2 = b4;
                    }
                } else if (b4 >= 5) {
                    b = b4;
                }
            }
            C0881z.f1881p = b;
            if (b3 == 0 || b2 == 0 || b2 % b3 != 0) {
                return;
            }
            C0819ar.f1576b = b3 * 1000;
            C0881z.f1888w = b3;
            C0843bd.f1683i = b2 * 1000;
            C0881z.f1882q = b2;
            C0843bd.m1187d();
        } catch (JSONException unused) {
        }
    }
}
