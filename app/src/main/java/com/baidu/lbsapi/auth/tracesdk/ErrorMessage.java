package com.baidu.lbsapi.auth.tracesdk;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class ErrorMessage {
    ErrorMessage() {
    }

    /* JADX INFO: renamed from: a */
    static String m168a(int i, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", i);
            jSONObject.put("message", str);
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    /* JADX INFO: renamed from: a */
    static String m169a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", -1);
            jSONObject.put("message", str);
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }
}
