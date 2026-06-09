package com.tencent.connect.auth;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class QQToken {
    public static final int AUTH_QQ = 2;
    public static final int AUTH_QZONE = 3;
    public static final int AUTH_WEB = 1;

    /* JADX INFO: renamed from: a */
    private String f2972a;

    /* JADX INFO: renamed from: b */
    private String f2973b;

    /* JADX INFO: renamed from: c */
    private String f2974c;

    /* JADX INFO: renamed from: d */
    private int f2975d = 1;

    /* JADX INFO: renamed from: e */
    private long f2976e = -1;

    public QQToken(String str) {
        this.f2972a = str;
    }

    public boolean isSessionValid() {
        return this.f2973b != null && System.currentTimeMillis() < this.f2976e;
    }

    public String getAppId() {
        return this.f2972a;
    }

    public void setAppId(String str) {
        this.f2972a = str;
    }

    public String getAccessToken() {
        return this.f2973b;
    }

    public void setAccessToken(String str, String str2) throws NumberFormatException {
        this.f2973b = str;
        this.f2976e = 0L;
        if (str2 != null) {
            this.f2976e = System.currentTimeMillis() + (Long.parseLong(str2) * 1000);
        }
    }

    public String getOpenId() {
        return this.f2974c;
    }

    public void setOpenId(String str) {
        this.f2974c = str;
    }

    public int getAuthSource() {
        return this.f2975d;
    }

    public void setAuthSource(int i) {
        this.f2975d = i;
    }

    public long getExpireTimeInSecond() {
        return this.f2976e;
    }
}
