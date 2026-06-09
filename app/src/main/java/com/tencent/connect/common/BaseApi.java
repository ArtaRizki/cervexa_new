package com.tencent.connect.common;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.tencent.connect.auth.C2031c;
import com.tencent.connect.auth.QQToken;
import com.tencent.open.TDialog;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.utils.C2084d;
import com.tencent.open.utils.C2087g;
import com.tencent.open.utils.C2089i;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseApi {
    public static String businessId = null;
    public static String installChannel = null;
    public static boolean isOEM = false;
    public static String registerChannel;

    /* JADX INFO: renamed from: a */
    protected C2031c f3023a;

    /* JADX INFO: renamed from: b */
    protected QQToken f3024b;

    public void releaseResource() {
    }

    public BaseApi(C2031c c2031c, QQToken qQToken) {
        this.f3023a = c2031c;
        this.f3024b = qQToken;
    }

    public BaseApi(QQToken qQToken) {
        this(null, qQToken);
    }

    /* JADX INFO: renamed from: a */
    protected Bundle m2004a() {
        Bundle bundle = new Bundle();
        bundle.putString("format", "json");
        bundle.putString("status_os", Build.VERSION.RELEASE);
        bundle.putString("status_machine", Build.MODEL);
        bundle.putString("status_version", Build.VERSION.SDK);
        bundle.putString("sdkv", Constants.SDK_VERSION);
        bundle.putString("sdkp", "a");
        QQToken qQToken = this.f3024b;
        if (qQToken != null && qQToken.isSessionValid()) {
            bundle.putString(Constants.PARAM_ACCESS_TOKEN, this.f3024b.getAccessToken());
            bundle.putString("oauth_consumer_key", this.f3024b.getAppId());
            bundle.putString("openid", this.f3024b.getOpenId());
            bundle.putString("appid_for_getting_config", this.f3024b.getAppId());
        }
        SharedPreferences sharedPreferences = C2084d.m2215a().getSharedPreferences(Constants.PREFERENCE_PF, 0);
        if (isOEM) {
            bundle.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + installChannel + "-" + IConstant.ANDROID_DIR + "-" + registerChannel + "-" + businessId);
        } else {
            bundle.putString(Constants.PARAM_PLATFORM_ID, sharedPreferences.getString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF));
        }
        return bundle;
    }

    /* JADX INFO: renamed from: a */
    protected String m2005a(String str) {
        Bundle bundleM2004a = m2004a();
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            bundleM2004a.putString("need_version", str);
        }
        sb.append("http://openmobile.qq.com/oauth2.0/m_jump_by_version?");
        sb.append(HttpUtils.encodeUrl(bundleM2004a));
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: b */
    public Bundle m2012b() {
        Bundle bundle = new Bundle();
        bundle.putString("appid", this.f3024b.getAppId());
        if (this.f3024b.isSessionValid()) {
            bundle.putString(Constants.PARAM_KEY_STR, this.f3024b.getAccessToken());
            bundle.putString(Constants.PARAM_KEY_TYPE, "0x80");
        }
        String openId = this.f3024b.getOpenId();
        if (openId != null) {
            bundle.putString("hopenid", openId);
        }
        bundle.putString(Constants.PARAM_PLATFORM, "androidqz");
        SharedPreferences sharedPreferences = C2084d.m2215a().getSharedPreferences(Constants.PREFERENCE_PF, 0);
        if (isOEM) {
            bundle.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + installChannel + "-" + IConstant.ANDROID_DIR + "-" + registerChannel + "-" + businessId);
        } else {
            bundle.putString(Constants.PARAM_PLATFORM_ID, sharedPreferences.getString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF));
            bundle.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
        }
        bundle.putString("sdkv", Constants.SDK_VERSION);
        bundle.putString("sdkp", "a");
        return bundle;
    }

    /* JADX INFO: renamed from: a */
    private Intent m2003a(Activity activity, Intent intent) {
        Intent intent2 = new Intent(activity.getApplicationContext(), (Class<?>) AssistActivity.class);
        intent2.putExtra("is_login", true);
        intent2.putExtra(AssistActivity.EXTRA_INTENT, intent);
        return intent2;
    }

    /* JADX INFO: renamed from: a */
    protected void m2006a(Activity activity, int i, Intent intent, boolean z) {
        Intent intent2 = new Intent(activity.getApplicationContext(), (Class<?>) AssistActivity.class);
        if (z) {
            intent2.putExtra("is_qq_mobile_share", true);
        }
        intent2.putExtra(AssistActivity.EXTRA_INTENT, intent);
        activity.startActivityForResult(intent2, i);
    }

    /* JADX INFO: renamed from: a */
    protected void m2007a(Activity activity, Intent intent, int i) {
        intent.putExtra(Constants.KEY_REQUEST_CODE, i);
        activity.startActivityForResult(m2003a(activity, intent), i);
    }

    /* JADX INFO: renamed from: a */
    protected void m2009a(Fragment fragment, Intent intent, int i) {
        intent.putExtra(Constants.KEY_REQUEST_CODE, i);
        fragment.startActivityForResult(m2003a(fragment.getActivity(), intent), i);
    }

    /* JADX INFO: renamed from: a */
    protected boolean m2010a(Intent intent) {
        if (intent != null) {
            return C2087g.m2240a(C2084d.m2215a(), intent);
        }
        return false;
    }

    /* JADX INFO: renamed from: b */
    protected Intent mo2011b(String str) {
        Intent intent = new Intent();
        if (C2089i.m2273e(C2084d.m2215a())) {
            intent.setClassName(Constants.PACKAGE_QQ_PAD, str);
            if (C2087g.m2240a(C2084d.m2215a(), intent)) {
                return intent;
            }
        }
        intent.setClassName("com.tencent.mobileqq", str);
        if (C2087g.m2240a(C2084d.m2215a(), intent)) {
            return intent;
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    protected void m2008a(Activity activity, Bundle bundle, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.BaseApi", "--handleDownloadLastestQQ");
        new TDialog(activity, "", "http://qzs.qq.com/open/mobile/login/qzsjump.html?" + HttpUtils.encodeUrl(bundle), null, this.f3024b).show();
    }

    /* JADX INFO: renamed from: c */
    protected Intent m2013c(String str) {
        Intent intent = new Intent();
        Intent intentMo2011b = mo2011b(str);
        if (intentMo2011b == null || intentMo2011b.getComponent() == null) {
            return null;
        }
        intent.setClassName(intentMo2011b.getComponent().getPackageName(), "com.tencent.open.agent.AgentActivity");
        return intent;
    }

    /* JADX INFO: compiled from: ProGuard */
    public class TempRequestListener implements IRequestListener {

        /* JADX INFO: renamed from: b */
        private final IUiListener f3026b;

        /* JADX INFO: renamed from: c */
        private final Handler f3027c;

        public TempRequestListener(IUiListener iUiListener) {
            this.f3026b = iUiListener;
            this.f3027c = new Handler(C2084d.m2215a().getMainLooper()) { // from class: com.tencent.connect.common.BaseApi.TempRequestListener.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    if (message.what == 0) {
                        TempRequestListener.this.f3026b.onComplete(message.obj);
                    } else {
                        TempRequestListener.this.f3026b.onError(new UiError(message.what, (String) message.obj, null));
                    }
                }
            };
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onComplete(JSONObject jSONObject) {
            Message messageObtainMessage = this.f3027c.obtainMessage();
            messageObtainMessage.obj = jSONObject;
            messageObtainMessage.what = 0;
            this.f3027c.sendMessage(messageObtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onIOException(IOException iOException) {
            Message messageObtainMessage = this.f3027c.obtainMessage();
            messageObtainMessage.obj = iOException.getMessage();
            messageObtainMessage.what = -2;
            this.f3027c.sendMessage(messageObtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onMalformedURLException(MalformedURLException malformedURLException) {
            Message messageObtainMessage = this.f3027c.obtainMessage();
            messageObtainMessage.obj = malformedURLException.getMessage();
            messageObtainMessage.what = -3;
            this.f3027c.sendMessage(messageObtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onJSONException(JSONException jSONException) {
            Message messageObtainMessage = this.f3027c.obtainMessage();
            messageObtainMessage.obj = jSONException.getMessage();
            messageObtainMessage.what = -4;
            this.f3027c.sendMessage(messageObtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onConnectTimeoutException(ConnectTimeoutException connectTimeoutException) {
            Message messageObtainMessage = this.f3027c.obtainMessage();
            messageObtainMessage.obj = connectTimeoutException.getMessage();
            messageObtainMessage.what = -7;
            this.f3027c.sendMessage(messageObtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onSocketTimeoutException(SocketTimeoutException socketTimeoutException) {
            Message messageObtainMessage = this.f3027c.obtainMessage();
            messageObtainMessage.obj = socketTimeoutException.getMessage();
            messageObtainMessage.what = -8;
            this.f3027c.sendMessage(messageObtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException networkUnavailableException) {
            Message messageObtainMessage = this.f3027c.obtainMessage();
            messageObtainMessage.obj = networkUnavailableException.getMessage();
            messageObtainMessage.what = -10;
            this.f3027c.sendMessage(messageObtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onHttpStatusException(HttpUtils.HttpStatusException httpStatusException) {
            Message messageObtainMessage = this.f3027c.obtainMessage();
            messageObtainMessage.obj = httpStatusException.getMessage();
            messageObtainMessage.what = -9;
            this.f3027c.sendMessage(messageObtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onUnknowException(Exception exc) {
            Message messageObtainMessage = this.f3027c.obtainMessage();
            messageObtainMessage.obj = exc.getMessage();
            messageObtainMessage.what = -6;
            this.f3027c.sendMessage(messageObtainMessage);
        }
    }
}
