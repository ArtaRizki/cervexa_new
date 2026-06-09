package com.baidu.platform.comjni.map.favorite;

import android.os.Bundle;

/* JADX INFO: renamed from: com.baidu.platform.comjni.map.favorite.a */
/* JADX INFO: loaded from: classes.dex */
public class C0784a {

    /* JADX INFO: renamed from: a */
    private long f1123a = 0;

    /* JADX INFO: renamed from: b */
    private JNIFavorite f1124b;

    /* JADX INFO: renamed from: com.baidu.platform.comjni.map.favorite.a$a */
    public static class a {

        /* JADX INFO: renamed from: a */
        public static boolean f1125a = false;

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: b */
        public static void m896b() {
            f1125a = true;
        }
    }

    public C0784a() {
        this.f1124b = null;
        this.f1124b = new JNIFavorite();
    }

    /* JADX INFO: renamed from: a */
    public int m883a(Bundle bundle) {
        try {
            return this.f1124b.GetAll(this.f1123a, bundle);
        } catch (Throwable unused) {
            return 0;
        }
    }

    /* JADX INFO: renamed from: a */
    public long m884a() {
        long jCreate = this.f1124b.Create();
        this.f1123a = jCreate;
        return jCreate;
    }

    /* JADX INFO: renamed from: a */
    public boolean m885a(int i) {
        return this.f1124b.SetType(this.f1123a, i);
    }

    /* JADX INFO: renamed from: a */
    public boolean m886a(String str) {
        return this.f1124b.Remove(this.f1123a, str);
    }

    /* JADX INFO: renamed from: a */
    public boolean m887a(String str, String str2) {
        a.m896b();
        return this.f1124b.Add(this.f1123a, str, str2);
    }

    /* JADX INFO: renamed from: a */
    public boolean m888a(String str, String str2, String str3, int i, int i2, int i3) {
        return this.f1124b.Load(this.f1123a, str, str2, str3, i, i2, i3);
    }

    /* JADX INFO: renamed from: b */
    public int m889b() {
        return this.f1124b.Release(this.f1123a);
    }

    /* JADX INFO: renamed from: b */
    public String m890b(String str) {
        try {
            return this.f1124b.GetValue(this.f1123a, str);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    public boolean m891b(String str, String str2) {
        a.m896b();
        return this.f1124b.Update(this.f1123a, str, str2);
    }

    /* JADX INFO: renamed from: c */
    public boolean m892c() {
        return this.f1124b.Clear(this.f1123a);
    }

    /* JADX INFO: renamed from: c */
    public boolean m893c(String str) {
        try {
            return this.f1124b.IsExist(this.f1123a, str);
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: d */
    public boolean m894d() {
        return this.f1124b.SaveCache(this.f1123a);
    }
}
