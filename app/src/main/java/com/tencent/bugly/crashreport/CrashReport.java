package com.tencent.bugly.crashreport;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.C1950b;
import com.tencent.bugly.CrashModule;
import com.tencent.bugly.crashreport.biz.C1956b;
import com.tencent.bugly.crashreport.common.info.C1958a;
import com.tencent.bugly.crashreport.common.strategy.C1961a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver;
import com.tencent.bugly.crashreport.crash.C1972c;
import com.tencent.bugly.crashreport.crash.C1973d;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.crashreport.crash.p021h5.C1976b;
import com.tencent.bugly.crashreport.crash.p021h5.H5JavaScriptInterface;
import com.tencent.bugly.proguard.C1980a;
import com.tencent.bugly.proguard.C2014q;
import com.tencent.bugly.proguard.C2020w;
import com.tencent.bugly.proguard.C2021x;
import com.tencent.bugly.proguard.C2023z;
import java.net.InetAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes2.dex */
public class CrashReport {

    /* JADX INFO: renamed from: a */
    private static Context f2302a;

    /* JADX INFO: compiled from: BUGLY */
    public static class CrashHandleCallback extends BuglyStrategy.C1948a {
    }

    /* JADX INFO: compiled from: BUGLY */
    public interface WebViewInterface {
        void addJavascriptInterface(H5JavaScriptInterface h5JavaScriptInterface, String str);

        CharSequence getContentDescription();

        String getUrl();

        void loadUrl(String str);

        void setJavaScriptEnabled(boolean z);
    }

    public static void enableBugly(boolean z) {
        C1950b.f2297a = z;
    }

    public static void initCrashReport(Context context) {
        if (context == null) {
            return;
        }
        f2302a = context;
        C1950b.m1421a(CrashModule.getInstance());
        C1950b.m1418a(context);
    }

    public static void initCrashReport(Context context, UserStrategy userStrategy) {
        if (context == null) {
            return;
        }
        f2302a = context;
        C1950b.m1421a(CrashModule.getInstance());
        C1950b.m1419a(context, userStrategy);
    }

    public static void initCrashReport(Context context, String str, boolean z) {
        if (context != null) {
            f2302a = context;
            C1950b.m1421a(CrashModule.getInstance());
            C1950b.m1420a(context, str, z, null);
        }
    }

    public static void initCrashReport(Context context, String str, boolean z, UserStrategy userStrategy) {
        if (context == null) {
            return;
        }
        f2302a = context;
        C1950b.m1421a(CrashModule.getInstance());
        C1950b.m1420a(context, str, z, userStrategy);
    }

    public static String getBuglyVersion(Context context) {
        if (context == null) {
            C2021x.m1872d("Please call with context.", new Object[0]);
            return "unknown";
        }
        return C1958a.m1471a(context).m1486c();
    }

    public static void testJavaCrash() {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not test Java crash because bugly is disable.");
        } else {
            if (!CrashModule.getInstance().hasInitialized()) {
                Log.e(C2021x.f2906a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
                return;
            }
            C1958a c1958aM1472b = C1958a.m1472b();
            if (c1958aM1472b != null) {
                c1958aM1472b.m1482b(24096);
            }
            throw new RuntimeException("This Crash create for Test! You can go to Bugly see more detail!");
        }
    }

    public static void testNativeCrash() {
        testNativeCrash(false, false, false);
    }

    public static void testNativeCrash(boolean z, boolean z2, boolean z3) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not test native crash because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2021x.f2906a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C2021x.m1866a("start to create a native crash for test!", new Object[0]);
            C1972c.m1609a().m1619a(z, z2, z3);
        }
    }

    public static void testANRCrash() {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not test ANR crash because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2021x.f2906a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C2021x.m1866a("start to create a anr crash for test!", new Object[0]);
            C1972c.m1609a().m1630l();
        }
    }

    public static void postException(Thread thread, int i, String str, String str2, String str3, Map<String, String> map) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not post crash caught because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2021x.f2906a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C1973d.m1644a(thread, i, str, str2, str3, map);
        }
    }

    public static void postException(int i, String str, String str2, String str3, Map<String, String> map) {
        postException(Thread.currentThread(), i, str, str2, str3, map);
    }

    public static void postCatchedException(Throwable th) {
        postCatchedException(th, Thread.currentThread(), false);
    }

    public static void postCatchedException(Throwable th, Thread thread) {
        postCatchedException(th, thread, false);
    }

    public static void postCatchedException(Throwable th, Thread thread, boolean z) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not post crash caught because bugly is disable.");
            return;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2021x.f2906a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        if (th == null) {
            C2021x.m1872d("throwable is null, just return", new Object[0]);
            return;
        }
        if (thread == null) {
            thread = Thread.currentThread();
        }
        C1972c.m1609a().m1617a(thread, th, false, (String) null, (byte[]) null, z);
    }

    public static void closeNativeReport() {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not close native report because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2021x.f2906a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C1972c.m1609a().m1625g();
        }
    }

    public static void startCrashReport() {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not start crash report because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.w(C2021x.f2906a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C1972c.m1609a().m1621c();
        }
    }

    public static void closeCrashReport() {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not close crash report because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.w(C2021x.f2906a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C1972c.m1609a().m1622d();
        }
    }

    public static void closeBugly() {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not close bugly because bugly is disable.");
            return;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.w(C2021x.f2906a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        if (f2302a == null) {
            return;
        }
        BuglyBroadcastReceiver buglyBroadcastReceiver = BuglyBroadcastReceiver.getInstance();
        if (buglyBroadcastReceiver != null) {
            buglyBroadcastReceiver.unregister(f2302a);
        }
        closeCrashReport();
        C1956b.m1444a(f2302a);
        C2020w c2020wM1858a = C2020w.m1858a();
        if (c2020wM1858a != null) {
            c2020wM1858a.m1862b();
        }
    }

    public static void setUserSceneTag(Context context, int i) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not set tag caught because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.e(C2021x.f2906a, "setTag args context should not be null");
            return;
        }
        if (i <= 0) {
            C2021x.m1872d("setTag args tagId should > 0", new Object[0]);
        }
        C1958a.m1471a(context).m1477a(i);
        C2021x.m1869b("[param] set user scene tag: %d", Integer.valueOf(i));
    }

    public static int getUserSceneTagId(Context context) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not get user scene tag because bugly is disable.");
            return -1;
        }
        if (context == null) {
            Log.e(C2021x.f2906a, "getUserSceneTagId args context should not be null");
            return -1;
        }
        return C1958a.m1471a(context).m1515z();
    }

    public static String getUserData(Context context, String str) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not get user data because bugly is disable.");
            return "unknown";
        }
        if (context == null) {
            Log.e(C2021x.f2906a, "getUserDataValue args context should not be null");
            return "unknown";
        }
        if (C2023z.m1914a(str)) {
            return null;
        }
        return C1958a.m1471a(context).m1496g(str);
    }

    public static void putUserData(Context context, String str, String str2) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not put user data because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(C2021x.f2906a, "putUserData args context should not be null");
            return;
        }
        if (str == null) {
            C2021x.m1872d("putUserData args key should not be null or empty", new Object[0]);
            return;
        }
        if (str2 == null) {
            C2021x.m1872d("putUserData args value should not be null", new Object[0]);
            return;
        }
        if (str2.length() > 200) {
            C2021x.m1872d("user data value length over limit %d, it will be cutted!", 200);
            str2 = str2.substring(0, 200);
        }
        C1958a c1958aM1471a = C1958a.m1471a(context);
        if (c1958aM1471a.m1512w().contains(str)) {
            NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
            if (nativeCrashHandler != null) {
                nativeCrashHandler.putKeyValueToNative(str, str2);
            }
            C1958a.m1471a(context).m1484b(str, str2);
            C2021x.m1871c("replace KV %s %s", str, str2);
            return;
        }
        if (c1958aM1471a.m1511v() >= 50) {
            C2021x.m1872d("user data size is over limit %d, it will be cutted!", 50);
            return;
        }
        if (str.length() > 50) {
            C2021x.m1872d("user data key length over limit %d , will drop this new key %s", 50, str);
            str = str.substring(0, 50);
        }
        NativeCrashHandler nativeCrashHandler2 = NativeCrashHandler.getInstance();
        if (nativeCrashHandler2 != null) {
            nativeCrashHandler2.putKeyValueToNative(str, str2);
        }
        C1958a.m1471a(context).m1484b(str, str2);
        C2021x.m1869b("[param] set user data: %s - %s", str, str2);
    }

    public static String removeUserData(Context context, String str) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not remove user data because bugly is disable.");
            return "unknown";
        }
        if (context == null) {
            Log.e(C2021x.f2906a, "removeUserData args context should not be null");
            return "unknown";
        }
        if (C2023z.m1914a(str)) {
            return null;
        }
        C2021x.m1869b("[param] remove user data: %s", str);
        return C1958a.m1471a(context).m1494f(str);
    }

    public static Set<String> getAllUserDataKeys(Context context) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not get all keys of user data because bugly is disable.");
            return new HashSet();
        }
        if (context == null) {
            Log.e(C2021x.f2906a, "getAllUserDataKeys args context should not be null");
            return new HashSet();
        }
        return C1958a.m1471a(context).m1512w();
    }

    public static int getUserDatasSize(Context context) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not get size of user data because bugly is disable.");
            return -1;
        }
        if (context == null) {
            Log.e(C2021x.f2906a, "getUserDatasSize args context should not be null");
            return -1;
        }
        return C1958a.m1471a(context).m1511v();
    }

    public static String getAppID() {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not get App ID because bugly is disable.");
            return "unknown";
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2021x.f2906a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
        return C1958a.m1471a(f2302a).m1493f();
    }

    public static void setUserId(String str) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not set user ID because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2021x.f2906a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            setUserId(f2302a, str);
        }
    }

    public static void setUserId(Context context, String str) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not set user ID because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.e(C2021x.f2906a, "Context should not be null when bugly has not been initialed!");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            C2021x.m1872d("userId should not be null", new Object[0]);
            return;
        }
        if (str.length() > 100) {
            String strSubstring = str.substring(0, 100);
            C2021x.m1872d("userId %s length is over limit %d substring to %s", str, 100, strSubstring);
            str = strSubstring;
        }
        if (str.equals(C1958a.m1471a(context).m1495g())) {
            return;
        }
        C1958a.m1471a(context).m1483b(str);
        C2021x.m1869b("[user] set userId : %s", str);
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        if (nativeCrashHandler != null) {
            nativeCrashHandler.setNativeUserId(str);
        }
        if (CrashModule.getInstance().hasInitialized()) {
            C1956b.m1442a();
        }
    }

    public static String getUserId() {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not get user ID because bugly is disable.");
            return "unknown";
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2021x.f2906a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
        return C1958a.m1471a(f2302a).m1495g();
    }

    public static void setDeviceId(Context context, String str) {
        if (str != null) {
            C1958a.m1471a(context).m1487c(str);
        }
    }

    public static String getDeviceID(Context context) {
        return C1958a.m1471a(context).m1497h();
    }

    public static String getAppVer() {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not get app version because bugly is disable.");
            return "unknown";
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2021x.f2906a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
        return C1958a.m1471a(f2302a).f2407k;
    }

    public static String getAppChannel() {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not get App channel because bugly is disable.");
            return "unknown";
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2021x.f2906a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
        return C1958a.m1471a(f2302a).f2409m;
    }

    public static void setContext(Context context) {
        f2302a = context;
    }

    public static boolean isLastSessionCrash() {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "The info 'isLastSessionCrash' is not accurate because bugly is disable.");
            return false;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2021x.f2906a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return false;
        }
        return C1972c.m1609a().m1620b();
    }

    public static void setSdkExtraData(Context context, String str, String str2) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not put SDK extra data because bugly is disable.");
        } else {
            if (context == null || C2023z.m1914a(str) || C2023z.m1914a(str2)) {
                return;
            }
            C1958a.m1471a(context).m1479a(str, str2);
        }
    }

    public static Map<String, String> getSdkExtraData() {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not get SDK extra data because bugly is disable.");
            return new HashMap();
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(C2021x.f2906a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return null;
        }
        return C1958a.m1471a(f2302a).f2359C;
    }

    public static Map<String, String> getSdkExtraData(Context context) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not get SDK extra data because bugly is disable.");
            return new HashMap();
        }
        if (context == null) {
            C2021x.m1872d("Context should not be null.", new Object[0]);
            return null;
        }
        return C1958a.m1471a(context).f2359C;
    }

    private static void putSdkData(Context context, String str, String str2) {
        if (context == null || C2023z.m1914a(str) || C2023z.m1914a(str2)) {
            return;
        }
        String strReplace = str.replace("[a-zA-Z[0-9]]+", "");
        if (strReplace.length() > 100) {
            Log.w(C2021x.f2906a, String.format("putSdkData key length over limit %d, will be cutted.", 50));
            strReplace = strReplace.substring(0, 50);
        }
        if (str2.length() > 500) {
            Log.w(C2021x.f2906a, String.format("putSdkData value length over limit %d, will be cutted!", 200));
            str2 = str2.substring(0, 200);
        }
        C1958a.m1471a(context).m1488c(strReplace, str2);
        C2021x.m1869b(String.format("[param] putSdkData data: %s - %s", strReplace, str2), new Object[0]);
    }

    public static void setIsAppForeground(Context context, boolean z) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not set 'isAppForeground' because bugly is disable.");
            return;
        }
        if (context == null) {
            C2021x.m1872d("Context should not be null.", new Object[0]);
            return;
        }
        if (z) {
            C2021x.m1871c("App is in foreground.", new Object[0]);
        } else {
            C2021x.m1871c("App is in background.", new Object[0]);
        }
        C1958a.m1471a(context).m1480a(z);
    }

    public static void setIsDevelopmentDevice(Context context, boolean z) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not set 'isDevelopmentDevice' because bugly is disable.");
            return;
        }
        if (context == null) {
            C2021x.m1872d("Context should not be null.", new Object[0]);
            return;
        }
        if (z) {
            C2021x.m1871c("This is a development device.", new Object[0]);
        } else {
            C2021x.m1871c("This is not a development device.", new Object[0]);
        }
        C1958a.m1471a(context).f2357A = z;
    }

    public static void setSessionIntervalMills(long j) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not set 'SessionIntervalMills' because bugly is disable.");
        } else {
            C1956b.m1443a(j);
        }
    }

    public static void setAppVersion(Context context, String str) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not set App version because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(C2021x.f2906a, "setAppVersion args context should not be null");
            return;
        }
        if (str == null) {
            Log.w(C2021x.f2906a, "App version is null, will not set");
            return;
        }
        C1958a.m1471a(context).f2407k = str;
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        if (nativeCrashHandler != null) {
            nativeCrashHandler.setNativeAppVersion(str);
        }
    }

    public static void setAppChannel(Context context, String str) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not set App channel because Bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(C2021x.f2906a, "setAppChannel args context should not be null");
            return;
        }
        if (str == null) {
            Log.w(C2021x.f2906a, "App channel is null, will not set");
            return;
        }
        C1958a.m1471a(context).f2409m = str;
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        if (nativeCrashHandler != null) {
            nativeCrashHandler.setNativeAppChannel(str);
        }
    }

    public static void setAppPackage(Context context, String str) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not set App package because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(C2021x.f2906a, "setAppPackage args context should not be null");
            return;
        }
        if (str == null) {
            Log.w(C2021x.f2906a, "App package is null, will not set");
            return;
        }
        C1958a.m1471a(context).f2399c = str;
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        if (nativeCrashHandler != null) {
            nativeCrashHandler.setNativeAppPackage(str);
        }
    }

    public static void setCrashFilter(String str) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not set App package because bugly is disable.");
            return;
        }
        Log.i(C2021x.f2906a, "Set crash stack filter: " + str);
        C1972c.f2578n = str;
    }

    public static void setCrashRegularFilter(String str) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not set App package because bugly is disable.");
            return;
        }
        Log.i(C2021x.f2906a, "Set crash stack filter: " + str);
        C1972c.f2579o = str;
    }

    public static void setHandleNativeCrashInJava(boolean z) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not set App package because bugly is disable.");
            return;
        }
        Log.i(C2021x.f2906a, "Should handle native crash in Java profile after handled in native profile: " + z);
        NativeCrashHandler.setShouldHandleInJava(z);
    }

    public static void setBuglyDbName(String str) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not set DB name because bugly is disable.");
            return;
        }
        Log.i(C2021x.f2906a, "Set Bugly DB name: " + str);
        C2014q.f2856a = str;
    }

    public static void enableObtainId(Context context, boolean z) {
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not set DB name because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(C2021x.f2906a, "enableObtainId args context should not be null");
            return;
        }
        Log.i(C2021x.f2906a, "Enable identification obtaining? " + z);
        C1958a.m1471a(context).m1485b(z);
    }

    public static void setServerUrl(String str) {
        if (C2023z.m1914a(str) || !C2023z.m1929c(str)) {
            Log.i(C2021x.f2906a, "URL is invalid.");
            return;
        }
        C1961a.m1546a(str);
        StrategyBean.f2426a = str;
        StrategyBean.f2427b = str;
    }

    public static boolean setJavascriptMonitor(WebView webView, boolean z) {
        return setJavascriptMonitor(webView, z, false);
    }

    public static boolean setJavascriptMonitor(final WebView webView, boolean z, boolean z2) {
        if (webView == null) {
            Log.w(C2021x.f2906a, "WebView is null.");
            return false;
        }
        webView.getSettings().setSavePassword(false);
        webView.getSettings().setAllowFileAccess(false);
        return setJavascriptMonitor(new WebViewInterface() { // from class: com.tencent.bugly.crashreport.CrashReport.1
            @Override // com.tencent.bugly.crashreport.CrashReport.WebViewInterface
            public final String getUrl() {
                return webView.getUrl();
            }

            @Override // com.tencent.bugly.crashreport.CrashReport.WebViewInterface
            public final void setJavaScriptEnabled(boolean z3) {
                WebSettings settings = webView.getSettings();
                if (settings.getJavaScriptEnabled()) {
                    return;
                }
                settings.setJavaScriptEnabled(true);
            }

            @Override // com.tencent.bugly.crashreport.CrashReport.WebViewInterface
            public final void loadUrl(String str) {
                webView.loadUrl(str);
            }

            @Override // com.tencent.bugly.crashreport.CrashReport.WebViewInterface
            public final void addJavascriptInterface(H5JavaScriptInterface h5JavaScriptInterface, String str) {
                webView.addJavascriptInterface(h5JavaScriptInterface, str);
            }

            @Override // com.tencent.bugly.crashreport.CrashReport.WebViewInterface
            public final CharSequence getContentDescription() {
                return webView.getContentDescription();
            }
        }, z, z2);
    }

    public static boolean setJavascriptMonitor(WebViewInterface webViewInterface, boolean z) {
        return setJavascriptMonitor(webViewInterface, z, false);
    }

    public static boolean setJavascriptMonitor(WebViewInterface webViewInterface, boolean z, boolean z2) {
        if (webViewInterface == null) {
            Log.w(C2021x.f2906a, "WebViewInterface is null.");
            return false;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            C2021x.m1873e("CrashReport has not been initialed! please to call method 'initCrashReport' first!", new Object[0]);
            return false;
        }
        C2021x.m1866a("Set Javascript exception monitor of webview.", new Object[0]);
        if (!C1950b.f2297a) {
            Log.w(C2021x.f2906a, "Can not set JavaScript monitor because bugly is disable.");
            return false;
        }
        C2021x.m1871c("URL of webview is %s", webViewInterface.getUrl());
        if (!z2 && Build.VERSION.SDK_INT < 19) {
            C2021x.m1873e("This interface is only available for Android 4.4 or later.", new Object[0]);
            return false;
        }
        C2021x.m1866a("Enable the javascript needed by webview monitor.", new Object[0]);
        webViewInterface.setJavaScriptEnabled(true);
        H5JavaScriptInterface h5JavaScriptInterface = H5JavaScriptInterface.getInstance(webViewInterface);
        if (h5JavaScriptInterface != null) {
            C2021x.m1866a("Add a secure javascript interface to the webview.", new Object[0]);
            webViewInterface.addJavascriptInterface(h5JavaScriptInterface, "exceptionUploader");
        }
        if (z) {
            C2021x.m1866a("Inject bugly.js(v%s) to the webview.", C1976b.m1656b());
            String strM1655a = C1976b.m1655a();
            if (strM1655a == null) {
                C2021x.m1873e("Failed to inject Bugly.js.", C1976b.m1656b());
                return false;
            }
            webViewInterface.loadUrl("javascript:" + strM1655a);
        }
        return true;
    }

    public static void setHttpProxy(String str, int i) {
        C1980a.m1687a(str, i);
    }

    public static void setHttpProxy(InetAddress inetAddress, int i) {
        C1980a.m1688a(inetAddress, i);
    }

    public static Proxy getHttpProxy() {
        return C1980a.m1693b();
    }

    /* JADX INFO: compiled from: BUGLY */
    public static class UserStrategy extends BuglyStrategy {

        /* JADX INFO: renamed from: c */
        private CrashHandleCallback f2304c;

        public UserStrategy(Context context) {
        }

        @Override // com.tencent.bugly.BuglyStrategy
        public synchronized void setCallBackType(int i) {
            this.f2273a = i;
        }

        @Override // com.tencent.bugly.BuglyStrategy
        public synchronized int getCallBackType() {
            return this.f2273a;
        }

        @Override // com.tencent.bugly.BuglyStrategy
        public synchronized void setCloseErrorCallback(boolean z) {
            this.f2274b = z;
        }

        @Override // com.tencent.bugly.BuglyStrategy
        public synchronized boolean getCloseErrorCallback() {
            return this.f2274b;
        }

        @Override // com.tencent.bugly.BuglyStrategy
        public synchronized CrashHandleCallback getCrashHandleCallback() {
            return this.f2304c;
        }

        public synchronized void setCrashHandleCallback(CrashHandleCallback crashHandleCallback) {
            this.f2304c = crashHandleCallback;
        }
    }
}
