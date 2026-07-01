package com.serenegiant.utils;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class JSONHelper {
    private static final boolean DEBUG = false;
    private static final String TAG = JSONHelper.class.getSimpleName();

    public static long getLong(JSONObject jSONObject, String str, long j) throws JSONException {
        if (!jSONObject.has(str)) {
            return j;
        }
        try {
            return jSONObject.getLong(str);
        } catch (JSONException unused) {
            try {
                return jSONObject.getBoolean(str) ? 1L : 0L;
            } catch (JSONException unused2) {
                return Long.parseLong(jSONObject.getString(str));
            }
        }
    }

    public static long optLong(JSONObject jSONObject, String str, long j) {
        if (!jSONObject.has(str)) {
            return j;
        }
        try {
            return jSONObject.getLong(str);
        } catch (Exception unused) {
            try {
                return jSONObject.getBoolean(str) ? 1L : 0L;
            } catch (Exception unused2) {
                try {
                    return Long.parseLong(jSONObject.getString(str));
                } catch (Exception e) {
                    Log.w(TAG, e);
                    return j;
                }
            }
        }
    }

    public static int getInt(JSONObject jSONObject, String str, int i) throws JSONException {
        if (!jSONObject.has(str)) {
            return i;
        }
        try {
            return jSONObject.getInt(str);
        } catch (JSONException unused) {
            try {
                return jSONObject.getBoolean(str) ? 1 : 0;
            } catch (JSONException unused2) {
                return Integer.parseInt(jSONObject.getString(str));
            }
        }
    }

    public static int optInt(JSONObject jSONObject, String str, int i) {
        if (!jSONObject.has(str)) {
            return i;
        }
        try {
            return jSONObject.getInt(str);
        } catch (Exception unused) {
            try {
                return jSONObject.getBoolean(str) ? 1 : 0;
            } catch (Exception unused2) {
                try {
                    return Integer.parseInt(jSONObject.getString(str));
                } catch (Exception e) {
                    Log.w(TAG, e);
                    return i;
                }
            }
        }
    }

    public static boolean getBoolean(JSONObject jSONObject, String str, boolean z) throws JSONException {
        if (!jSONObject.has(str)) {
            return z;
        }
        try {
            return jSONObject.getBoolean(str);
        } catch (JSONException unused) {
            try {
                return jSONObject.getInt(str) != 0;
            } catch (JSONException unused2) {
                return jSONObject.getDouble(str) != 0.0d;
            }
        }
    }

    public static boolean optBoolean(JSONObject jSONObject, String str, boolean z) {
        if (!jSONObject.has(str)) {
            return z;
        }
        try {
            return jSONObject.getBoolean(str);
        } catch (Exception unused) {
            try {
                return jSONObject.getInt(str) != 0;
            } catch (Exception unused2) {
                try {
                    return jSONObject.getDouble(str) != 0.0d;
                } catch (Exception e) {
                    Log.w(TAG, e);
                    return z;
                }
            }
        }
    }
}
