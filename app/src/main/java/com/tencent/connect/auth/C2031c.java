package com.tencent.connect.auth;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.tencent.bugly.Bugly;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.p022a.C2024a;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.utils.C2081a;
import com.tencent.open.utils.C2084d;
import com.tencent.tauth.IUiListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: renamed from: com.tencent.connect.auth.c */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2031c {

    /* JADX INFO: renamed from: a */
    private AuthAgent f3018a;

    /* JADX INFO: renamed from: b */
    private QQToken f3019b;

    private C2031c(String str, Context context) {
        C2061f.m2133c("openSDK_LOG.QQAuth", "new QQAuth() --start");
        this.f3019b = new QQToken(str);
        this.f3018a = new AuthAgent(this.f3019b);
        C2024a.m1934c(context, this.f3019b);
        C2061f.m2133c("openSDK_LOG.QQAuth", "new QQAuth() --end");
    }

    /* JADX INFO: renamed from: a */
    public static C2031c m1990a(String str, Context context) {
        C2084d.m2216a(context.getApplicationContext());
        C2061f.m2133c("openSDK_LOG.QQAuth", "QQAuth -- createInstance() --start");
        try {
            PackageManager packageManager = context.getPackageManager();
            packageManager.getActivityInfo(new ComponentName(context.getPackageName(), "com.tencent.tauth.AuthActivity"), 0);
            packageManager.getActivityInfo(new ComponentName(context.getPackageName(), "com.tencent.connect.common.AssistActivity"), 0);
            C2031c c2031c = new C2031c(str, context);
            C2061f.m2133c("openSDK_LOG.QQAuth", "QQAuth -- createInstance()  --end");
            return c2031c;
        } catch (PackageManager.NameNotFoundException e) {
            C2061f.m2131b("openSDK_LOG.QQAuth", "createInstance() error --end", e);
            Toast.makeText(context.getApplicationContext(), "请参照文档在Androidmanifest.xml加上AuthActivity和AssitActivity的定义 ", 1).show();
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    public int m1991a(Activity activity, String str, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.QQAuth", "login()");
        return m1992a(activity, str, iUiListener, "");
    }

    /* JADX INFO: renamed from: a */
    public int m1992a(Activity activity, String str, IUiListener iUiListener, String str2) {
        C2061f.m2133c("openSDK_LOG.QQAuth", "-->login activity: " + activity);
        return m1989a(activity, null, str, iUiListener, str2);
    }

    /* JADX INFO: renamed from: a */
    public int m1994a(Fragment fragment, String str, IUiListener iUiListener, String str2) {
        FragmentActivity activity = fragment.getActivity();
        C2061f.m2133c("openSDK_LOG.QQAuth", "-->login activity: " + activity);
        return m1989a(activity, fragment, str, iUiListener, str2);
    }

    /* JADX INFO: renamed from: a */
    private int m1989a(Activity activity, Fragment fragment, String str, IUiListener iUiListener, String str2) {
        String str3;
        String packageName = activity.getApplicationContext().getPackageName();
        Iterator<ApplicationInfo> it = activity.getPackageManager().getInstalledApplications(128).iterator();
        while (true) {
            if (!it.hasNext()) {
                str3 = null;
                break;
            }
            ApplicationInfo next = it.next();
            if (packageName.equals(next.packageName)) {
                str3 = next.sourceDir;
                break;
            }
        }
        if (str3 != null) {
            try {
                String strM2203a = C2081a.m2203a(new File(str3));
                if (!TextUtils.isEmpty(strM2203a)) {
                    C2061f.m2127a("openSDK_LOG.QQAuth", "-->login channelId: " + strM2203a);
                    return m1993a(activity, str, iUiListener, strM2203a, strM2203a, "");
                }
            } catch (IOException e) {
                C2061f.m2131b("openSDK_LOG.QQAuth", "-->login get channel id exception.", e);
                e.printStackTrace();
            }
        }
        C2061f.m2130b("openSDK_LOG.QQAuth", "-->login channelId is null ");
        BaseApi.isOEM = false;
        return this.f3018a.doLogin(activity, str, iUiListener, false, fragment);
    }

    @Deprecated
    /* JADX INFO: renamed from: a */
    public int m1993a(Activity activity, String str, IUiListener iUiListener, String str2, String str3, String str4) {
        C2061f.m2133c("openSDK_LOG.QQAuth", "loginWithOEM");
        BaseApi.isOEM = true;
        if (str2.equals("")) {
            str2 = IConstant.DEFAULT_PATH;
        }
        if (str3.equals("")) {
            str3 = IConstant.DEFAULT_PATH;
        }
        if (str4.equals("")) {
            str4 = IConstant.DEFAULT_PATH;
        }
        BaseApi.installChannel = str3;
        BaseApi.registerChannel = str2;
        BaseApi.businessId = str4;
        return this.f3018a.doLogin(activity, str, iUiListener);
    }

    /* JADX INFO: renamed from: b */
    public int m1999b(Activity activity, String str, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.QQAuth", "reAuth()");
        return this.f3018a.doLogin(activity, str, iUiListener, true, null);
    }

    /* JADX INFO: renamed from: a */
    public void m1995a() {
        this.f3018a.m1946a((IUiListener) null);
    }

    /* JADX INFO: renamed from: a */
    public void m1997a(IUiListener iUiListener) {
        this.f3018a.m1947b(iUiListener);
    }

    /* JADX INFO: renamed from: b */
    public QQToken m2000b() {
        return this.f3019b;
    }

    /* JADX INFO: renamed from: a */
    public void m1998a(String str, String str2) {
        C2061f.m2127a("openSDK_LOG.QQAuth", "setAccessToken(), validTimeInSecond = " + str2 + "");
        this.f3019b.setAccessToken(str, str2);
    }

    /* JADX INFO: renamed from: c */
    public boolean m2001c() {
        StringBuilder sb = new StringBuilder();
        sb.append("isSessionValid(), result = ");
        sb.append(this.f3019b.isSessionValid() ? "true" : Bugly.SDK_IS_DEV);
        sb.append("");
        C2061f.m2127a("openSDK_LOG.QQAuth", sb.toString());
        return this.f3019b.isSessionValid();
    }

    /* JADX INFO: renamed from: a */
    public void m1996a(Context context, String str) {
        C2061f.m2127a("openSDK_LOG.QQAuth", "setOpenId() --start");
        this.f3019b.setOpenId(str);
        C2024a.m1935d(context, this.f3019b);
        C2061f.m2127a("openSDK_LOG.QQAuth", "setOpenId() --end");
    }
}
