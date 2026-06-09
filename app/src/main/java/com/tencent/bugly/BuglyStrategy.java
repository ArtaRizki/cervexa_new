package com.tencent.bugly;

import com.tencent.bugly.crashreport.common.info.C1958a;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public class BuglyStrategy {

    /* JADX INFO: renamed from: c */
    private String f2275c;

    /* JADX INFO: renamed from: d */
    private String f2276d;

    /* JADX INFO: renamed from: e */
    private String f2277e;

    /* JADX INFO: renamed from: f */
    private long f2278f;

    /* JADX INFO: renamed from: g */
    private String f2279g;

    /* JADX INFO: renamed from: h */
    private String f2280h;

    /* JADX INFO: renamed from: r */
    private C1948a f2290r;

    /* JADX INFO: renamed from: i */
    private boolean f2281i = true;

    /* JADX INFO: renamed from: j */
    private boolean f2282j = true;

    /* JADX INFO: renamed from: k */
    private boolean f2283k = false;

    /* JADX INFO: renamed from: l */
    private boolean f2284l = true;

    /* JADX INFO: renamed from: m */
    private Class<?> f2285m = null;

    /* JADX INFO: renamed from: n */
    private boolean f2286n = true;

    /* JADX INFO: renamed from: o */
    private boolean f2287o = true;

    /* JADX INFO: renamed from: p */
    private boolean f2288p = true;

    /* JADX INFO: renamed from: q */
    private boolean f2289q = false;

    /* JADX INFO: renamed from: a */
    protected int f2273a = 31;

    /* JADX INFO: renamed from: b */
    protected boolean f2274b = false;

    public synchronized BuglyStrategy setBuglyLogUpload(boolean z) {
        this.f2286n = z;
        return this;
    }

    public synchronized BuglyStrategy setRecordUserInfoOnceADay(boolean z) {
        this.f2289q = z;
        return this;
    }

    public synchronized BuglyStrategy setUploadProcess(boolean z) {
        this.f2288p = z;
        return this;
    }

    public synchronized boolean isUploadProcess() {
        return this.f2288p;
    }

    public synchronized boolean isBuglyLogUpload() {
        return this.f2286n;
    }

    public synchronized boolean recordUserInfoOnceADay() {
        return this.f2289q;
    }

    public boolean isReplaceOldChannel() {
        return this.f2287o;
    }

    public void setReplaceOldChannel(boolean z) {
        this.f2287o = z;
    }

    public synchronized String getAppVersion() {
        if (this.f2275c == null) {
            return C1958a.m1472b().f2407k;
        }
        return this.f2275c;
    }

    public synchronized BuglyStrategy setAppVersion(String str) {
        this.f2275c = str;
        return this;
    }

    public synchronized BuglyStrategy setUserInfoActivity(Class<?> cls) {
        this.f2285m = cls;
        return this;
    }

    public synchronized Class<?> getUserInfoActivity() {
        return this.f2285m;
    }

    public synchronized String getAppChannel() {
        if (this.f2276d == null) {
            return C1958a.m1472b().f2409m;
        }
        return this.f2276d;
    }

    public synchronized BuglyStrategy setAppChannel(String str) {
        this.f2276d = str;
        return this;
    }

    public synchronized String getAppPackageName() {
        if (this.f2277e == null) {
            return C1958a.m1472b().f2399c;
        }
        return this.f2277e;
    }

    public synchronized BuglyStrategy setAppPackageName(String str) {
        this.f2277e = str;
        return this;
    }

    public synchronized long getAppReportDelay() {
        return this.f2278f;
    }

    public synchronized BuglyStrategy setAppReportDelay(long j) {
        this.f2278f = j;
        return this;
    }

    public synchronized String getLibBuglySOFilePath() {
        return this.f2279g;
    }

    public synchronized BuglyStrategy setLibBuglySOFilePath(String str) {
        this.f2279g = str;
        return this;
    }

    public synchronized String getDeviceID() {
        return this.f2280h;
    }

    public synchronized BuglyStrategy setDeviceID(String str) {
        this.f2280h = str;
        return this;
    }

    public synchronized boolean isEnableNativeCrashMonitor() {
        return this.f2281i;
    }

    public synchronized BuglyStrategy setEnableNativeCrashMonitor(boolean z) {
        this.f2281i = z;
        return this;
    }

    public synchronized BuglyStrategy setEnableUserInfo(boolean z) {
        this.f2284l = z;
        return this;
    }

    public synchronized boolean isEnableUserInfo() {
        return this.f2284l;
    }

    public synchronized boolean isEnableCatchAnrTrace() {
        return this.f2283k;
    }

    public void setEnableCatchAnrTrace(boolean z) {
        this.f2283k = z;
    }

    public synchronized boolean isEnableANRCrashMonitor() {
        return this.f2282j;
    }

    public synchronized BuglyStrategy setEnableANRCrashMonitor(boolean z) {
        this.f2282j = z;
        return this;
    }

    public synchronized C1948a getCrashHandleCallback() {
        return this.f2290r;
    }

    public synchronized BuglyStrategy setCrashHandleCallback(C1948a c1948a) {
        this.f2290r = c1948a;
        return this;
    }

    public synchronized void setCallBackType(int i) {
        this.f2273a = i;
    }

    public synchronized int getCallBackType() {
        return this.f2273a;
    }

    public synchronized void setCloseErrorCallback(boolean z) {
        this.f2274b = z;
    }

    public synchronized boolean getCloseErrorCallback() {
        return this.f2274b;
    }

    /* JADX INFO: renamed from: com.tencent.bugly.BuglyStrategy$a */
    /* JADX INFO: compiled from: BUGLY */
    public static class C1948a {
        public static final int CRASHTYPE_ANR = 4;
        public static final int CRASHTYPE_BLOCK = 7;
        public static final int CRASHTYPE_COCOS2DX_JS = 5;
        public static final int CRASHTYPE_COCOS2DX_LUA = 6;
        public static final int CRASHTYPE_JAVA_CATCH = 1;
        public static final int CRASHTYPE_JAVA_CRASH = 0;
        public static final int CRASHTYPE_NATIVE = 2;
        public static final int CRASHTYPE_U3D = 3;
        public static final int MAX_USERDATA_KEY_LENGTH = 100;
        public static final int MAX_USERDATA_VALUE_LENGTH = 30000;

        public synchronized Map<String, String> onCrashHandleStart(int i, String str, String str2, String str3) {
            return null;
        }

        public synchronized byte[] onCrashHandleStart2GetExtraDatas(int i, String str, String str2, String str3) {
            return null;
        }
    }
}
