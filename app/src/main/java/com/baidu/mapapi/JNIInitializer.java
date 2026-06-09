package com.baidu.mapapi;

import android.app.Application;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class JNIInitializer {

    /* JADX INFO: renamed from: a */
    private static Context f184a;

    public static Context getCachedContext() {
        return f184a;
    }

    public static void setContext(Application application) {
        if (application == null) {
            throw new RuntimeException();
        }
        if (f184a == null) {
            f184a = application;
        }
    }
}
