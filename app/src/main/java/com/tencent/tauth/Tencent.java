package com.tencent.tauth;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.tencent.connect.auth.C2031c;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzonePublish;
import com.tencent.connect.share.QzoneShare;
import com.tencent.open.SocialApi;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.utils.C2084d;
import com.tencent.open.utils.C2087g;
import com.tencent.open.utils.C2089i;
import com.tencent.open.utils.HttpUtils;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class Tencent {
    public static final int REQUEST_LOGIN = 10001;

    /* JADX INFO: renamed from: b */
    private static Tencent f3309b;

    /* JADX INFO: renamed from: a */
    private final C2031c f3310a;

    public void releaseResource() {
    }

    private Tencent(String str, Context context) {
        this.f3310a = C2031c.m1990a(str, context);
    }

    public static synchronized Tencent createInstance(String str, Context context) {
        C2084d.m2216a(context.getApplicationContext());
        C2061f.m2133c("openSDK_LOG.Tencent", "createInstance()  -- start, appId = " + str);
        if (f3309b == null) {
            f3309b = new Tencent(str, context);
        } else if (!str.equals(f3309b.getAppId())) {
            f3309b.logout(context);
            f3309b = new Tencent(str, context);
        }
        if (!m2287a(context, str)) {
            return null;
        }
        C2061f.m2133c("openSDK_LOG.Tencent", "createInstance()  -- end");
        return f3309b;
    }

    /* JADX INFO: renamed from: a */
    private static boolean m2287a(Context context, String str) {
        try {
            context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), "com.tencent.tauth.AuthActivity"), 0);
            try {
                context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), "com.tencent.connect.common.AssistActivity"), 0);
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
                C2061f.m2135e("openSDK_LOG.Tencent", "AndroidManifest.xml 没有检测到com.tencent.connect.common.AssistActivity\n" + ("没有在AndroidManifest.xml中检测到com.tencent.connect.common.AssistActivity,请加上com.tencent.connect.common.AssistActivity,详细信息请查看官网文档.\n配置示例如下: \n<activity\n     android:name=\"com.tencent.connect.common.AssistActivity\"\n     android:screenOrientation=\"behind\"\n     android:theme=\"@android:style/Theme.Translucent.NoTitleBar\"\n     android:configChanges=\"orientation|keyboardHidden\">\n</activity>"));
                return false;
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            C2061f.m2135e("openSDK_LOG.Tencent", "AndroidManifest.xml 没有检测到com.tencent.tauth.AuthActivity" + (("没有在AndroidManifest.xml中检测到com.tencent.tauth.AuthActivity,请加上com.tencent.tauth.AuthActivity,并配置<data android:scheme=\"tencent" + str + "\" />,详细信息请查看官网文档.") + "\n配置示例如下: \n<activity\n     android:name=\"com.tencent.tauth.AuthActivity\"\n     android:noHistory=\"true\"\n     android:launchMode=\"singleTask\">\n<intent-filter>\n    <action android:name=\"android.intent.action.VIEW\" />\n    <category android:name=\"android.intent.category.DEFAULT\" />\n    <category android:name=\"android.intent.category.BROWSABLE\" />\n    <data android:scheme=\"tencent" + str + "\" />\n</intent-filter>\n</activity>"));
            return false;
        }
    }

    public int login(Activity activity, String str, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.Tencent", "login() with activity, scope is " + str);
        return this.f3310a.m1991a(activity, str, iUiListener);
    }

    public int login(Fragment fragment, String str, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.Tencent", "login() with fragment, scope is " + str);
        return this.f3310a.m1994a(fragment, str, iUiListener, "");
    }

    public int loginServerSide(Activity activity, String str, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.Tencent", "loginServerSide() with activity, scope = " + str + ",server_side");
        return this.f3310a.m1991a(activity, str + ",server_side", iUiListener);
    }

    public int loginServerSide(Fragment fragment, String str, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.Tencent", "loginServerSide() with fragment, scope = " + str + ",server_side");
        return this.f3310a.m1994a(fragment, str + ",server_side", iUiListener, "");
    }

    public int loginWithOEM(Activity activity, String str, IUiListener iUiListener, String str2, String str3, String str4) {
        C2061f.m2133c("openSDK_LOG.Tencent", "loginWithOEM() with activity, scope = " + str);
        return this.f3310a.m1993a(activity, str, iUiListener, str2, str3, str4);
    }

    public void logout(Context context) {
        C2061f.m2133c("openSDK_LOG.Tencent", "logout()");
        this.f3310a.m2000b().setAccessToken(null, "0");
        this.f3310a.m2000b().setOpenId(null);
    }

    public int reAuth(Activity activity, String str, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.Tencent", "reAuth() with activity, scope = " + str);
        return this.f3310a.m1999b(activity, str, iUiListener);
    }

    public void reportDAU() {
        this.f3310a.m1995a();
    }

    public void checkLogin(IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.Tencent", "checkLogin()");
        this.f3310a.m1997a(iUiListener);
    }

    public int invite(Activity activity, Bundle bundle, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.Tencent", "invite()");
        new SocialApi(this.f3310a.m2000b()).invite(activity, bundle, iUiListener);
        return 0;
    }

    public int story(Activity activity, Bundle bundle, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.Tencent", "story()");
        new SocialApi(this.f3310a.m2000b()).story(activity, bundle, iUiListener);
        return 0;
    }

    public int gift(Activity activity, Bundle bundle, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.Tencent", "gift()");
        new SocialApi(this.f3310a.m2000b()).gift(activity, bundle, iUiListener);
        return 0;
    }

    public int ask(Activity activity, Bundle bundle, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.Tencent", "ask()");
        new SocialApi(this.f3310a.m2000b()).ask(activity, bundle, iUiListener);
        return 0;
    }

    public void requestAsync(String str, Bundle bundle, String str2, IRequestListener iRequestListener, Object obj) {
        C2061f.m2133c("openSDK_LOG.Tencent", "requestAsync()");
        HttpUtils.requestAsync(this.f3310a.m2000b(), C2084d.m2215a(), str, bundle, str2, iRequestListener);
    }

    public JSONObject request(String str, Bundle bundle, String str2) throws JSONException, IOException, HttpUtils.NetworkUnavailableException, HttpUtils.HttpStatusException {
        C2061f.m2133c("openSDK_LOG.Tencent", "request()");
        return HttpUtils.request(this.f3310a.m2000b(), C2084d.m2215a(), str, bundle, str2);
    }

    public void shareToQQ(Activity activity, Bundle bundle, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.Tencent", "shareToQQ()");
        new QQShare(activity, this.f3310a.m2000b()).shareToQQ(activity, bundle, iUiListener);
    }

    public void shareToQzone(Activity activity, Bundle bundle, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.Tencent", "shareToQzone()");
        new QzoneShare(activity, this.f3310a.m2000b()).shareToQzone(activity, bundle, iUiListener);
    }

    public void publishToQzone(Activity activity, Bundle bundle, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.Tencent", "publishToQzone()");
        new QzonePublish(activity, this.f3310a.m2000b()).publishToQzone(activity, bundle, iUiListener);
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        C2061f.m2133c("openSDK_LOG.Tencent", "onActivityResult() deprecated, will do nothing");
        return false;
    }

    public static boolean onActivityResultData(int i, int i2, Intent intent, IUiListener iUiListener) {
        StringBuilder sb = new StringBuilder();
        sb.append("onActivityResultData() reqcode = ");
        sb.append(i);
        sb.append(", resultcode = ");
        sb.append(i2);
        sb.append(", data = null ? ");
        sb.append(intent == null);
        sb.append(", listener = null ? ");
        sb.append(iUiListener == null);
        C2061f.m2133c("openSDK_LOG.Tencent", sb.toString());
        return UIListenerManager.getInstance().onActivityResult(i, i2, intent, iUiListener);
    }

    public boolean isSessionValid() {
        return this.f3310a.m2001c();
    }

    public String getAppId() {
        return this.f3310a.m2000b().getAppId();
    }

    public String getAccessToken() {
        return this.f3310a.m2000b().getAccessToken();
    }

    public long getExpiresIn() {
        return this.f3310a.m2000b().getExpireTimeInSecond();
    }

    public String getOpenId() {
        return this.f3310a.m2000b().getOpenId();
    }

    @Deprecated
    public void handleLoginData(Intent intent, IUiListener iUiListener) {
        StringBuilder sb = new StringBuilder();
        sb.append("handleLoginData() data = null ? ");
        sb.append(intent == null);
        sb.append(", listener = null ? ");
        sb.append(iUiListener == null);
        C2061f.m2133c("openSDK_LOG.Tencent", sb.toString());
        UIListenerManager.getInstance().handleDataToListener(intent, iUiListener);
    }

    public static void handleResultData(Intent intent, IUiListener iUiListener) {
        StringBuilder sb = new StringBuilder();
        sb.append("handleResultData() data = null ? ");
        sb.append(intent == null);
        sb.append(", listener = null ? ");
        sb.append(iUiListener == null);
        C2061f.m2133c("openSDK_LOG.Tencent", sb.toString());
        UIListenerManager.getInstance().handleDataToListener(intent, iUiListener);
    }

    public void setAccessToken(String str, String str2) {
        C2061f.m2127a("openSDK_LOG.Tencent", "setAccessToken(), expiresIn = " + str2 + "");
        this.f3310a.m1998a(str, str2);
    }

    public void setOpenId(String str) {
        C2061f.m2127a("openSDK_LOG.Tencent", "setOpenId() --start");
        this.f3310a.m1996a(C2084d.m2215a(), str);
        C2061f.m2127a("openSDK_LOG.Tencent", "setOpenId() --end");
    }

    public boolean isReady() {
        return isSessionValid() && getOpenId() != null;
    }

    public QQToken getQQToken() {
        return this.f3310a.m2000b();
    }

    public boolean isSupportSSOLogin(Activity activity) {
        if (C2089i.m2273e(activity) && C2087g.m2239a(activity, Constants.PACKAGE_QQ_PAD) != null) {
            return true;
        }
        if (C2087g.m2239a(activity, "com.tencent.mobileqq") == null) {
            return false;
        }
        return C2087g.m2244b(activity);
    }
}
