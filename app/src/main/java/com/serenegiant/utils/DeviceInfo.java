package com.serenegiant.utils;

import android.os.Build;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class DeviceInfo {
    public static JSONObject get() throws JSONException {
        String str;
        String str2;
        String line;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("BUILD", Build.ID);
        } catch (Exception e) {
            jSONObject.put("BUILD", e.getMessage());
        }
        try {
            jSONObject.put("DISPLAY", Build.DISPLAY);
        } catch (Exception e2) {
            jSONObject.put("DISPLAY", e2.getMessage());
        }
        try {
            jSONObject.put("PRODUCT", Build.PRODUCT);
        } catch (Exception e3) {
            jSONObject.put("PRODUCT", e3.getMessage());
        }
        try {
            jSONObject.put("DEVICE", Build.DEVICE);
        } catch (Exception e4) {
            jSONObject.put("DEVICE", e4.getMessage());
        }
        try {
            jSONObject.put("BOARD", Build.BOARD);
        } catch (Exception e5) {
            jSONObject.put("BOARD", e5.getMessage());
        }
        try {
            jSONObject.put("MANUFACTURER", Build.MANUFACTURER);
        } catch (Exception e6) {
            jSONObject.put("MANUFACTURER", e6.getMessage());
        }
        try {
            jSONObject.put("BRAND", Build.BRAND);
        } catch (Exception e7) {
            jSONObject.put("BRAND", e7.getMessage());
        }
        try {
            jSONObject.put("MODEL", Build.MODEL);
        } catch (Exception e8) {
            jSONObject.put("MODEL", e8.getMessage());
        }
        try {
            jSONObject.put("BOOTLOADER", Build.BOOTLOADER);
        } catch (Exception e9) {
            jSONObject.put("BOOTLOADER", e9.getMessage());
        }
        try {
            jSONObject.put("HARDWARE", Build.HARDWARE);
        } catch (Exception e10) {
            jSONObject.put("HARDWARE", e10.getMessage());
        }
        int i = 0;
        if (BuildCheck.isAndroid5()) {
            try {
                String[] strArr = Build.SUPPORTED_ABIS;
                if (strArr != null && strArr.length > 0) {
                    JSONObject jSONObject2 = new JSONObject();
                    int length = strArr.length;
                    for (int i2 = 0; i2 < length; i2++) {
                        jSONObject2.put(Integer.toString(i2), strArr[i2]);
                    }
                    jSONObject.put("SUPPORTED_ABIS", jSONObject2);
                }
            } catch (Exception e11) {
                jSONObject.put("SUPPORTED_ABIS", e11.getMessage());
            }
            try {
                String[] strArr2 = Build.SUPPORTED_32_BIT_ABIS;
                if (strArr2 != null && strArr2.length > 0) {
                    JSONObject jSONObject3 = new JSONObject();
                    int length2 = strArr2.length;
                    for (int i3 = 0; i3 < length2; i3++) {
                        jSONObject3.put(Integer.toString(i3), strArr2[i3]);
                    }
                    jSONObject.put("SUPPORTED_32_BIT_ABIS", jSONObject3);
                }
            } catch (Exception e12) {
                jSONObject.put("SUPPORTED_32_BIT_ABIS", e12.getMessage());
            }
            try {
                String[] strArr3 = Build.SUPPORTED_64_BIT_ABIS;
                if (strArr3 != null && strArr3.length > 0) {
                    JSONObject jSONObject4 = new JSONObject();
                    int length3 = strArr3.length;
                    for (int i4 = 0; i4 < length3; i4++) {
                        jSONObject4.put(Integer.toString(i4), strArr3[i4]);
                    }
                    jSONObject.put("SUPPORTED_64_BIT_ABIS", jSONObject4);
                }
            } catch (Exception e13) {
                jSONObject.put("SUPPORTED_64_BIT_ABIS", e13.getMessage());
            }
        } else {
            try {
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put("0", Build.CPU_ABI);
                jSONObject5.put("1", Build.CPU_ABI2);
                jSONObject.put("SUPPORTED_ABIS", jSONObject5);
            } catch (Exception e14) {
                jSONObject.put("SUPPORTED_ABIS", e14.getMessage());
            }
        }
        try {
            jSONObject.put("RELEASE", Build.VERSION.RELEASE);
        } catch (Exception e15) {
            jSONObject.put("RELEASE", e15.getMessage());
        }
        try {
            jSONObject.put("API_LEVEL", Build.VERSION.SDK_INT);
        } catch (Exception e16) {
            jSONObject.put("API_LEVEL", e16.getMessage());
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/version"), 512);
            Object line2 = bufferedReader.readLine();
            bufferedReader.close();
            str = "PROC_VERSION";
            try {
                jSONObject.put(str, line2);
            } catch (Exception e17) {
                e = e17;
                jSONObject.put(str, e.getMessage());
            }
        } catch (Exception e18) {
            e = e18;
            str = "PROC_VERSION";
        }
        JSONObject jSONObject6 = new JSONObject();
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader("/proc/cpuinfo"), 512);
            do {
                line = bufferedReader2.readLine();
                if (line == null) {
                    break;
                }
                if (!TextUtils.isEmpty(line)) {
                    jSONObject6.put(Integer.toString(i), line);
                    i++;
                }
            } while (line != null);
            bufferedReader2.close();
            str2 = "PROC_CPUINFO";
            try {
                jSONObject.put(str2, jSONObject6);
            } catch (Exception e19) {
                e = e19;
                jSONObject.put(str2, e.getMessage());
            }
        } catch (Exception e20) {
            e = e20;
            str2 = "PROC_CPUINFO";
        }
        return jSONObject;
    }
}
