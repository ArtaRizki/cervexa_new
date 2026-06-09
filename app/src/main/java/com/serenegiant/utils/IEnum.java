package com.serenegiant.utils;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IEnum {

    public interface EnumInterface {
        /* JADX INFO: renamed from: id */
        int m1405id();

        String label();

        String name();

        int ordinal();
    }

    public interface EnumInterfaceEx extends EnumInterface {
        void put(String str, JSONObject jSONObject) throws JSONException;

        void put(JSONObject jSONObject) throws JSONException;
    }

    /* JADX INFO: renamed from: as */
    public static <E extends EnumInterface> E m1401as(Class<E> cls, int i) throws IllegalArgumentException {
        for (E e : cls.getEnumConstants()) {
            if (e.m1405id() == i) {
                return e;
            }
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: renamed from: as */
    public static <E extends EnumInterface> E m1403as(Class<E> cls, String str) throws IllegalArgumentException {
        if (!TextUtils.isEmpty(str)) {
            for (E e : cls.getEnumConstants()) {
                if (str.equalsIgnoreCase(e.label())) {
                    return e;
                }
            }
            str.toUpperCase();
            for (E e2 : cls.getEnumConstants()) {
                if (str.startsWith(e2.name().toUpperCase())) {
                    return e2;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: renamed from: as */
    public static <E extends EnumInterface> E m1402as(Class<E> cls, int i, String str) throws IllegalArgumentException {
        try {
            return (E) m1401as(cls, i);
        } catch (IllegalArgumentException unused) {
            return (E) m1403as(cls, str);
        }
    }

    /* JADX INFO: renamed from: as */
    public static <E extends EnumInterface> E m1404as(Class<E> cls, String str, int i) throws IllegalArgumentException {
        try {
            return (E) m1403as(cls, str);
        } catch (IllegalArgumentException unused) {
            return (E) m1401as(cls, i);
        }
    }

    public static <E extends EnumInterface> String identity(Class<E> cls, String str) {
        String strSubstring = null;
        if (!TextUtils.isEmpty(str)) {
            String upperCase = str.toUpperCase();
            for (E e : cls.getEnumConstants()) {
                if (upperCase.startsWith(e.name().toUpperCase())) {
                    strSubstring = str.substring(e.name().length() + 1);
                }
            }
        }
        return strSubstring;
    }

    public static JSONObject put(JSONObject jSONObject, String str, EnumInterface enumInterface) throws JSONException {
        jSONObject.put(str, enumInterface.label());
        return jSONObject;
    }
}
