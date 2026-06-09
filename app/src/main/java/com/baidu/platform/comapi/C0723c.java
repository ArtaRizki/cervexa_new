package com.baidu.platform.comapi;

import android.app.Application;
import android.content.Context;
import com.baidu.mapapi.JNIInitializer;
import com.baidu.mapapi.common.EnvironmentUtilities;
import java.io.File;

/* JADX INFO: renamed from: com.baidu.platform.comapi.c */
/* JADX INFO: loaded from: classes.dex */
public class C0723c {

    /* JADX INFO: renamed from: a */
    private static boolean f794a;

    /* JADX INFO: renamed from: a */
    public static void m534a(String str, Context context) {
        if (f794a) {
            return;
        }
        if (context == null) {
            throw new IllegalArgumentException("context can not be null");
        }
        if (!(context instanceof Application)) {
            throw new RuntimeException("context must be an Application Context");
        }
        NativeLoader.getInstance();
        NativeLoader.setContext(context);
        C0720a.m521a().m524a(context);
        C0720a.m521a().m528c();
        JNIInitializer.setContext((Application) context);
        if (str != null && !str.equals("")) {
            try {
                File file = new File(str + "/test.0");
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                if (file.exists()) {
                    file.delete();
                }
                EnvironmentUtilities.setSDCardPath(str);
            } catch (Exception e) {
                e.printStackTrace();
                throw new IllegalArgumentException("provided sdcard path can not used.");
            }
        }
        f794a = true;
    }
}
