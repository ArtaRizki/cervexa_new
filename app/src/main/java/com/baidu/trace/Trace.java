package com.baidu.trace;

import android.app.Notification;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class Trace {

    /* JADX INFO: renamed from: a */
    protected static int f1157a = 5;

    /* JADX INFO: renamed from: b */
    protected static int f1158b = 30;

    /* JADX INFO: renamed from: c */
    protected static int f1159c = 0;

    /* JADX INFO: renamed from: d */
    protected static int f1160d = 600000;

    /* JADX INFO: renamed from: e */
    protected static String f1161e = "";

    /* JADX INFO: renamed from: f */
    private long f1162f;

    /* JADX INFO: renamed from: g */
    private String f1163g;

    /* JADX INFO: renamed from: h */
    private boolean f1164h;

    /* JADX INFO: renamed from: i */
    private Notification f1165i;

    public Trace() {
        this.f1162f = 0L;
        this.f1163g = "";
        this.f1164h = false;
        this.f1165i = null;
    }

    public Trace(long j, String str) {
        this.f1162f = 0L;
        this.f1163g = "";
        this.f1164h = false;
        this.f1165i = null;
        this.f1162f = j;
        setEntityName(str);
    }

    public Trace(long j, String str, boolean z) {
        this.f1162f = 0L;
        this.f1163g = "";
        this.f1164h = false;
        this.f1165i = null;
        this.f1162f = j;
        this.f1164h = z;
        setEntityName(str);
    }

    public Trace(long j, String str, boolean z, Notification notification) {
        this.f1162f = 0L;
        this.f1163g = "";
        this.f1164h = false;
        this.f1165i = null;
        this.f1162f = j;
        this.f1163g = str;
        this.f1164h = z;
        this.f1165i = notification;
    }

    /* JADX INFO: renamed from: a */
    protected static boolean m948a(int i) {
        if (i < 50) {
            return false;
        }
        f1159c = i;
        return true;
    }

    /* JADX INFO: renamed from: a */
    protected static boolean m949a(int i, int i2) {
        if (i <= 0 || i > 300 || i2 < i || i2 < 2 || i2 > 300 || i2 % i != 0) {
            return false;
        }
        f1157a = i;
        f1158b = i2;
        return true;
    }

    /* JADX INFO: renamed from: b */
    protected static boolean m950b(int i) {
        if (i <= 0) {
            return false;
        }
        f1160d = i;
        return true;
    }

    public String getEntityName() {
        return this.f1163g;
    }

    public Notification getNotification() {
        return this.f1165i;
    }

    public long getServiceId() {
        return this.f1162f;
    }

    public boolean isNeedObjectStorage() {
        return this.f1164h;
    }

    public void setEntityName(String str) {
        this.f1163g = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f1161e = str;
    }

    public void setNeedObjectStorage(boolean z) {
        this.f1164h = z;
    }

    public void setNotification(Notification notification) {
        this.f1165i = notification;
    }

    public void setServiceId(long j) {
        this.f1162f = j;
    }
}
