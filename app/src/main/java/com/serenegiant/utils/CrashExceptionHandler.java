package com.serenegiant.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.preference.PreferenceManager;
import com.generalplus.GoPlusDrone.Fragment.BaseFragment;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class CrashExceptionHandler implements Thread.UncaughtExceptionHandler {
    static final String LOG_NAME = "crashrepo.txt";
    static final String MAIL_TO = "t_saki@serenegiant.com";
    private final Thread.UncaughtExceptionHandler mHandler;
    private final WeakReference<Context> mWeakContext;
    private final WeakReference<PackageInfo> mWeakPackageInfo;

    public static void registerCrashHandler(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(new CrashExceptionHandler(context));
    }

    private CrashExceptionHandler(Context context) {
        this.mWeakContext = new WeakReference<>(context);
        try {
            this.mWeakPackageInfo = new WeakReference<>(context.getPackageManager().getPackageInfo(context.getPackageName(), 0));
            this.mHandler = Thread.getDefaultUncaughtExceptionHandler();
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.PrintWriter] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v7 */
    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Context context = this.mWeakContext.get();
        if (context != null) {
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(context.openFileOutput(LOG_NAME, 0));
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("Build", getBuildInfo());
                jSONObject.put("PackageInfo", getPackageInfo());
                jSONObject.put("Exception", getExceptionInfo(th));
                jSONObject.put("SharedPreferences", getPreferencesInfo());
                printWriter.print(jSONObject.toString());
                printWriter.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (printWriter != null) {
                    printWriter.close();
                }
            }
        }
        try {
            if (this.mHandler != null) {
                this.mHandler.uncaughtException(thread, th);
            }
        } catch (Exception unused) {
        }
    }

    private JSONObject getBuildInfo() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("BRAND", Build.BRAND);
        jSONObject.put("MODEL", Build.MODEL);
        jSONObject.put("DEVICE", Build.DEVICE);
        jSONObject.put("MANUFACTURER", Build.MANUFACTURER);
        jSONObject.put("VERSION.SDK_INT", Build.VERSION.SDK_INT);
        jSONObject.put("VERSION.RELEASE", Build.VERSION.RELEASE);
        return jSONObject;
    }

    private JSONObject getPackageInfo() throws JSONException {
        PackageInfo packageInfo = this.mWeakPackageInfo.get();
        JSONObject jSONObject = new JSONObject();
        if (packageInfo != null) {
            jSONObject.put("packageName", packageInfo.packageName);
            jSONObject.put("versionCode", packageInfo.versionCode);
            jSONObject.put("versionName", packageInfo.versionName);
        }
        return jSONObject;
    }

    private JSONObject getExceptionInfo(Throwable th) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(BaseFragment.DATA_NAME, th.getClass().getName());
        jSONObject.put("cause", th.getCause());
        jSONObject.put("message", th.getMessage());
        JSONArray jSONArray = new JSONArray();
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            jSONArray.put("at " + LogUtils.getMetaInfo(stackTraceElement));
        }
        jSONObject.put("stacktrace", jSONArray);
        return jSONObject;
    }

    private JSONObject getPreferencesInfo() throws JSONException {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.mWeakContext.get());
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, ?> entry : defaultSharedPreferences.getAll().entrySet()) {
            jSONObject.put(entry.getKey(), entry.getValue());
        }
        return jSONObject;
    }
}
