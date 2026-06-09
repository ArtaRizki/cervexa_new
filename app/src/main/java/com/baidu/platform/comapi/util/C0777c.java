package com.baidu.platform.comapi.util;

import android.content.Context;
import android.os.Environment;
import java.io.File;

/* JADX INFO: renamed from: com.baidu.platform.comapi.util.c */
/* JADX INFO: loaded from: classes.dex */
public final class C0777c {

    /* JADX INFO: renamed from: a */
    private final boolean f1069a;

    /* JADX INFO: renamed from: b */
    private final String f1070b;

    /* JADX INFO: renamed from: c */
    private final String f1071c;

    /* JADX INFO: renamed from: d */
    private final String f1072d;

    /* JADX INFO: renamed from: e */
    private final String f1073e;

    /* JADX INFO: renamed from: f */
    private final String f1074f;

    C0777c(Context context) {
        this.f1069a = false;
        this.f1070b = Environment.getExternalStorageDirectory().getAbsolutePath();
        this.f1071c = this.f1070b + File.separator + "BaiduMapSDKNew";
        this.f1072d = context.getCacheDir().getAbsolutePath();
        this.f1073e = "";
        this.f1074f = "";
    }

    C0777c(String str, boolean z, String str2, Context context) {
        this.f1069a = z;
        this.f1070b = str;
        this.f1071c = this.f1070b + File.separator + "BaiduMapSDKNew";
        this.f1072d = this.f1071c + File.separator + "cache";
        this.f1073e = context.getCacheDir().getAbsolutePath();
        this.f1074f = str2;
    }

    /* JADX INFO: renamed from: a */
    public String m784a() {
        return this.f1070b;
    }

    /* JADX INFO: renamed from: b */
    public String m785b() {
        return this.f1070b + File.separator + "BaiduMapSDKNew";
    }

    /* JADX INFO: renamed from: c */
    public String m786c() {
        return this.f1072d;
    }

    /* JADX INFO: renamed from: d */
    public String m787d() {
        return this.f1073e;
    }

    public boolean equals(Object obj) {
        if (obj == null || !C0777c.class.isInstance(obj)) {
            return false;
        }
        return this.f1070b.equals(((C0777c) obj).f1070b);
    }
}
