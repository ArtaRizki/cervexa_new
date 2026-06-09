package com.tencent.open;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import com.tencent.connect.auth.C2031c;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.p028c.C2075b;
import com.tencent.open.utils.C2084d;
import com.tencent.open.utils.C2085e;
import com.tencent.open.utils.C2086f;
import com.tencent.open.utils.C2087g;
import com.tencent.open.utils.C2089i;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class SocialApiIml extends BaseApi {

    /* JADX INFO: renamed from: c */
    private Activity f3102c;

    public SocialApiIml(QQToken qQToken) {
        super(qQToken);
    }

    public SocialApiIml(C2031c c2031c, QQToken qQToken) {
        super(c2031c, qQToken);
    }

    public void gift(Activity activity, Bundle bundle, IUiListener iUiListener) {
        m2064a(activity, SocialConstants.ACTION_GIFT, bundle, iUiListener);
    }

    public void ask(Activity activity, Bundle bundle, IUiListener iUiListener) {
        m2064a(activity, SocialConstants.ACTION_ASK, bundle, iUiListener);
    }

    /* JADX INFO: renamed from: a */
    private void m2064a(Activity activity, String str, Bundle bundle, IUiListener iUiListener) {
        this.f3102c = activity;
        Intent intentC = m2013c(SocialConstants.ACTIVITY_FRIEND_CHOOSER);
        if (intentC == null) {
            C2061f.m2133c("openSDK_LOG.SocialApiIml", "--askgift--friend chooser not found");
            intentC = m2013c(SocialConstants.ACTIVITY_ASK_GIFT);
        }
        Intent intent = intentC;
        bundle.putAll(m2012b());
        if (SocialConstants.ACTION_ASK.equals(str)) {
            bundle.putString("type", SocialConstants.TYPE_REQUEST);
        } else if (SocialConstants.ACTION_GIFT.equals(str)) {
            bundle.putString("type", SocialConstants.TYPE_FREEGIFT);
        }
        m2063a(activity, intent, str, bundle, C2086f.m2232a().m2233a(C2084d.m2215a(), "http://qzs.qq.com/open/mobile/request/sdk_request.html?"), iUiListener, false);
    }

    public void invite(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f3102c = activity;
        Intent intentC = m2013c(SocialConstants.ACTIVITY_FRIEND_CHOOSER);
        if (intentC == null) {
            C2061f.m2133c("openSDK_LOG.SocialApiIml", "--invite--friend chooser not found");
            intentC = m2013c(SocialConstants.ACTIVITY_INVITE);
        }
        bundle.putAll(m2012b());
        m2063a(activity, intentC, SocialConstants.ACTION_INVITE, bundle, C2086f.m2232a().m2233a(C2084d.m2215a(), "http://qzs.qq.com/open/mobile/invite/sdk_invite.html?"), iUiListener, false);
    }

    public void story(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f3102c = activity;
        Intent intentC = m2013c(SocialConstants.ACTIVITY_STORY);
        bundle.putAll(m2012b());
        m2063a(activity, intentC, SocialConstants.ACTION_STORY, bundle, C2086f.m2232a().m2233a(C2084d.m2215a(), "http://qzs.qq.com/open/mobile/sendstory/sdk_sendstory_v1.3.html?"), iUiListener, false);
    }

    /* JADX INFO: renamed from: a */
    private void m2063a(Activity activity, Intent intent, String str, Bundle bundle, String str2, IUiListener iUiListener, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("-->handleIntent action = ");
        sb.append(str);
        sb.append(", activityIntent = null ? ");
        boolean z2 = true;
        sb.append(intent == null);
        C2061f.m2133c("openSDK_LOG.SocialApiIml", sb.toString());
        if (intent != null) {
            m2062a(activity, intent, str, bundle, iUiListener);
            return;
        }
        C2085e c2085eM2221a = C2085e.m2221a(C2084d.m2215a(), this.f3024b.getAppId());
        if (!z && !c2085eM2221a.m2231b("C_LoginH5")) {
            z2 = false;
        }
        if (z2) {
            m2065a(activity, str, bundle, str2, iUiListener);
        } else {
            m2008a(activity, bundle, iUiListener);
        }
    }

    /* JADX INFO: renamed from: a */
    private void m2062a(Activity activity, Intent intent, String str, Bundle bundle, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.SocialApiIml", "-->handleIntentWithAgent action = " + str);
        intent.putExtra(Constants.KEY_ACTION, str);
        intent.putExtra(Constants.KEY_PARAMS, bundle);
        UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_SOCIAL_API, iUiListener);
        m2007a(activity, intent, Constants.REQUEST_SOCIAL_API);
    }

    /* JADX INFO: renamed from: a */
    private void m2065a(Activity activity, String str, Bundle bundle, String str2, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.SocialApiIml", "-->handleIntentWithH5 action = " + str);
        Intent intentMo2011b = mo2011b("com.tencent.open.agent.AgentActivity");
        IUiListener c2053a = new C2053a(activity, iUiListener, str, str2, bundle);
        Intent intentMo2011b2 = mo2011b("com.tencent.open.agent.EncryTokenActivity");
        if (intentMo2011b2 != null && intentMo2011b != null && intentMo2011b.getComponent() != null && intentMo2011b2.getComponent() != null && intentMo2011b.getComponent().getPackageName().equals(intentMo2011b2.getComponent().getPackageName())) {
            intentMo2011b2.putExtra("oauth_consumer_key", this.f3024b.getAppId());
            intentMo2011b2.putExtra("openid", this.f3024b.getOpenId());
            intentMo2011b2.putExtra(Constants.PARAM_ACCESS_TOKEN, this.f3024b.getAccessToken());
            intentMo2011b2.putExtra(Constants.KEY_ACTION, SocialConstants.ACTION_CHECK_TOKEN);
            if (m2010a(intentMo2011b2)) {
                C2061f.m2133c("openSDK_LOG.SocialApiIml", "-->handleIntentWithH5--found token activity");
                UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_SOCIAL_H5, c2053a);
                m2007a(activity, intentMo2011b2, Constants.REQUEST_SOCIAL_H5);
                return;
            }
            return;
        }
        C2061f.m2133c("openSDK_LOG.SocialApiIml", "-->handleIntentWithH5--token activity not found");
        String strM2275f = C2089i.m2275f("tencent&sdk&qazxc***14969%%" + this.f3024b.getAccessToken() + this.f3024b.getAppId() + this.f3024b.getOpenId() + "qzone3.4");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(SocialConstants.PARAM_ENCRY_EOKEN, strM2275f);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        c2053a.onComplete(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m2066a(Context context, String str, Bundle bundle, String str2, IUiListener iUiListener) {
        C2061f.m2127a("openSDK_LOG.SocialApiIml", "OpenUi, showDialog --start");
        CookieSyncManager.createInstance(context);
        bundle.putString("oauth_consumer_key", this.f3024b.getAppId());
        if (this.f3024b.isSessionValid()) {
            bundle.putString(Constants.PARAM_ACCESS_TOKEN, this.f3024b.getAccessToken());
        }
        String openId = this.f3024b.getOpenId();
        if (openId != null) {
            bundle.putString("openid", openId);
        }
        try {
            bundle.putString(Constants.PARAM_PLATFORM_ID, C2084d.m2215a().getSharedPreferences(Constants.PREFERENCE_PF, 0).getString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF));
        } catch (Exception e) {
            e.printStackTrace();
            bundle.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
        }
        String str3 = str2 + HttpUtils.encodeUrl(bundle);
        C2061f.m2130b("openSDK_LOG.SocialApiIml", "OpenUi, showDialog TDialog");
        if (SocialConstants.ACTION_CHALLENGE.equals(str) || SocialConstants.ACTION_BRAG.equals(str)) {
            C2061f.m2130b("openSDK_LOG.SocialApiIml", "OpenUi, showDialog PKDialog");
            new DialogC2073c(this.f3102c, str, str3, iUiListener, this.f3024b).show();
        } else {
            new TDialog(this.f3102c, str, str3, iUiListener, this.f3024b).show();
        }
    }

    /* JADX INFO: renamed from: com.tencent.open.SocialApiIml$a */
    /* JADX INFO: compiled from: ProGuard */
    private class C2053a implements IUiListener {

        /* JADX INFO: renamed from: b */
        private IUiListener f3104b;

        /* JADX INFO: renamed from: c */
        private String f3105c;

        /* JADX INFO: renamed from: d */
        private String f3106d;

        /* JADX INFO: renamed from: e */
        private Bundle f3107e;

        /* JADX INFO: renamed from: f */
        private Activity f3108f;

        C2053a(Activity activity, IUiListener iUiListener, String str, String str2, Bundle bundle) {
            this.f3104b = iUiListener;
            this.f3105c = str;
            this.f3106d = str2;
            this.f3107e = bundle;
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            String string;
            try {
                string = ((JSONObject) obj).getString(SocialConstants.PARAM_ENCRY_EOKEN);
            } catch (JSONException e) {
                e.printStackTrace();
                C2061f.m2131b("openSDK_LOG.SocialApiIml", "OpenApi, EncrytokenListener() onComplete error", e);
                string = null;
            }
            this.f3107e.putString("encrytoken", string);
            SocialApiIml socialApiIml = SocialApiIml.this;
            socialApiIml.m2066a((Context) socialApiIml.f3102c, this.f3105c, this.f3107e, this.f3106d, this.f3104b);
            if (TextUtils.isEmpty(string)) {
                C2061f.m2130b("openSDK_LOG.SocialApiIml", "The token get from qq or qzone is empty. Write temp token to localstorage.");
                SocialApiIml.this.writeEncryToken(this.f3108f);
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            C2061f.m2130b("openSDK_LOG.SocialApiIml", "OpenApi, EncryptTokenListener() onError" + uiError.errorMessage);
            this.f3104b.onError(uiError);
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            this.f3104b.onCancel();
        }
    }

    public void writeEncryToken(Context context) {
        String strM2275f;
        String accessToken = this.f3024b.getAccessToken();
        String appId = this.f3024b.getAppId();
        String openId = this.f3024b.getOpenId();
        if (accessToken == null || accessToken.length() <= 0 || appId == null || appId.length() <= 0 || openId == null || openId.length() <= 0) {
            strM2275f = null;
        } else {
            strM2275f = C2089i.m2275f("tencent&sdk&qazxc***14969%%" + accessToken + appId + openId + "qzone3.4");
        }
        C2075b c2075b = new C2075b(context);
        WebSettings settings = c2075b.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        String str = "<!DOCTYPE HTML><html lang=\"en-US\"><head><meta charset=\"UTF-8\"><title>localStorage Test</title><script type=\"text/javascript\">document.domain = 'qq.com';localStorage[\"" + this.f3024b.getOpenId() + "_" + this.f3024b.getAppId() + "\"]=\"" + strM2275f + "\";</script></head><body></body></html>";
        String strM2233a = C2086f.m2232a().m2233a(context, "http://qzs.qq.com");
        c2075b.loadDataWithBaseURL(strM2233a, str, "text/html", "utf-8", strM2233a);
    }

    @Override // com.tencent.connect.common.BaseApi
    /* JADX INFO: renamed from: b */
    protected Intent mo2011b(String str) {
        Intent intent = new Intent();
        intent.setClassName(Constants.PACKAGE_QZONE, str);
        Intent intent2 = new Intent();
        intent2.setClassName("com.tencent.mobileqq", str);
        Intent intent3 = new Intent();
        intent3.setClassName(Constants.PACKAGE_QQ_PAD, str);
        if (C2089i.m2273e(C2084d.m2215a()) && C2087g.m2240a(C2084d.m2215a(), intent3)) {
            return intent3;
        }
        if (C2087g.m2240a(C2084d.m2215a(), intent2) && C2087g.m2245c(C2084d.m2215a(), "4.7") >= 0) {
            return intent2;
        }
        if (C2087g.m2240a(C2084d.m2215a(), intent) && C2087g.m2235a(C2087g.m2239a(C2084d.m2215a(), Constants.PACKAGE_QZONE), "4.2") >= 0 && C2087g.m2241a(C2084d.m2215a(), intent.getComponent().getPackageName(), Constants.SIGNATRUE_QZONE)) {
            return intent;
        }
        return null;
    }
}
