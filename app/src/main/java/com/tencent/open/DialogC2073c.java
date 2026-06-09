package com.tencent.open;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.open.C2055a;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.p027b.C2072g;
import com.tencent.open.p028c.C2074a;
import com.tencent.open.p028c.C2075b;
import com.tencent.open.utils.C2086f;
import com.tencent.open.utils.C2089i;
import com.tencent.tauth.AuthActivity;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.tencent.open.c */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class DialogC2073c extends AbstractDialogC2065b implements C2074a.a {

    /* JADX INFO: renamed from: c */
    static Toast f3223c;

    /* JADX INFO: renamed from: d */
    private String f3224d;

    /* JADX INFO: renamed from: e */
    private IUiListener f3225e;

    /* JADX INFO: renamed from: f */
    private c f3226f;

    /* JADX INFO: renamed from: g */
    private Handler f3227g;

    /* JADX INFO: renamed from: h */
    private C2074a f3228h;

    /* JADX INFO: renamed from: i */
    private C2075b f3229i;

    /* JADX INFO: renamed from: j */
    private WeakReference<Context> f3230j;

    /* JADX INFO: renamed from: k */
    private int f3231k;

    public DialogC2073c(Context context, String str, String str2, IUiListener iUiListener, QQToken qQToken) {
        super(context, R.style.Theme.Translucent.NoTitleBar);
        this.f3230j = new WeakReference<>(context);
        this.f3224d = str2;
        this.f3226f = new c(context, str, str2, qQToken.getAppId(), iUiListener);
        this.f3227g = new d(this.f3226f, context.getMainLooper());
        this.f3225e = iUiListener;
        this.f3231k = Math.round(context.getResources().getDisplayMetrics().density * 185.0f);
        C2061f.m2135e("openSDK_LOG.PKDialog", "density=" + context.getResources().getDisplayMetrics().density + "; webviewHeight=" + this.f3231k);
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // com.tencent.open.AbstractDialogC2065b, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        getWindow().setSoftInputMode(16);
        getWindow().setSoftInputMode(1);
        m2187b();
        m2190c();
    }

    /* JADX INFO: renamed from: b */
    private void m2187b() {
        C2074a c2074a = new C2074a(this.f3230j.get());
        this.f3228h = c2074a;
        c2074a.setBackgroundColor(1711276032);
        this.f3228h.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        C2075b c2075b = new C2075b(this.f3230j.get());
        this.f3229i = c2075b;
        c2075b.setBackgroundColor(0);
        this.f3229i.setBackgroundDrawable(null);
        if (Build.VERSION.SDK_INT >= 11) {
            try {
                View.class.getMethod("setLayerType", Integer.TYPE, Paint.class).invoke(this.f3229i, 1, new Paint());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, this.f3231k);
        layoutParams.addRule(13, -1);
        this.f3229i.setLayoutParams(layoutParams);
        this.f3228h.addView(this.f3229i);
        this.f3228h.m2197a(this);
        setContentView(this.f3228h);
    }

    /* JADX INFO: renamed from: c */
    private void m2190c() {
        this.f3229i.setVerticalScrollBarEnabled(false);
        this.f3229i.setHorizontalScrollBarEnabled(false);
        this.f3229i.setWebViewClient(new a());
        this.f3229i.setWebChromeClient(this.f3184b);
        this.f3229i.clearFormData();
        WebSettings settings = this.f3229i.getSettings();
        if (settings == null) {
            return;
        }
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setCacheMode(-1);
        settings.setNeedInitialFocus(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setJavaScriptEnabled(true);
        WeakReference<Context> weakReference = this.f3230j;
        if (weakReference != null && weakReference.get() != null) {
            settings.setDatabaseEnabled(true);
            settings.setDatabasePath(this.f3230j.get().getApplicationContext().getDir("databases", 0).getPath());
        }
        settings.setDomStorageEnabled(true);
        this.f3183a.m2081a(new b(), "sdk_js_if");
        this.f3229i.clearView();
        this.f3229i.loadUrl(this.f3224d);
        this.f3229i.getSettings().setSavePassword(false);
    }

    /* JADX INFO: renamed from: com.tencent.open.c$b */
    /* JADX INFO: compiled from: ProGuard */
    private class b extends C2055a.b {
        private b() {
        }
    }

    /* JADX INFO: renamed from: com.tencent.open.c$a */
    /* JADX INFO: compiled from: ProGuard */
    private class a extends WebViewClient {
        private a() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            C2061f.m2127a("openSDK_LOG.PKDialog", "Redirect URL: " + str);
            if (str.startsWith(C2086f.m2232a().m2233a((Context) DialogC2073c.this.f3230j.get(), "auth://tauth.qq.com/"))) {
                DialogC2073c.this.f3226f.onComplete(C2089i.m2267c(str));
                DialogC2073c.this.dismiss();
                return true;
            }
            if (str.startsWith(Constants.CANCEL_URI)) {
                DialogC2073c.this.f3226f.onCancel();
                DialogC2073c.this.dismiss();
                return true;
            }
            if (!str.startsWith(Constants.CLOSE_URI)) {
                return false;
            }
            DialogC2073c.this.dismiss();
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            DialogC2073c.this.f3226f.onError(new UiError(i, str, str2));
            if (DialogC2073c.this.f3230j != null && DialogC2073c.this.f3230j.get() != null) {
                Toast.makeText((Context) DialogC2073c.this.f3230j.get(), "网络连接异常或系统错误", 0).show();
            }
            DialogC2073c.this.dismiss();
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            C2061f.m2127a("openSDK_LOG.PKDialog", "Webview loading URL: " + str);
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            DialogC2073c.this.f3229i.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c */
    public static void m2191c(Context context, String str) {
        try {
            JSONObject jSONObjectM2271d = C2089i.m2271d(str);
            int i = jSONObjectM2271d.getInt("type");
            String string = jSONObjectM2271d.getString("msg");
            if (i == 0) {
                if (f3223c == null) {
                    f3223c = Toast.makeText(context, string, 0);
                } else {
                    f3223c.setView(f3223c.getView());
                    f3223c.setText(string);
                    f3223c.setDuration(0);
                }
                f3223c.show();
                return;
            }
            if (i == 1) {
                if (f3223c == null) {
                    f3223c = Toast.makeText(context, string, 1);
                } else {
                    f3223c.setView(f3223c.getView());
                    f3223c.setText(string);
                    f3223c.setDuration(1);
                }
                f3223c.show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: d */
    public static void m2192d(Context context, String str) {
        if (context == null || str == null) {
            return;
        }
        try {
            JSONObject jSONObjectM2271d = C2089i.m2271d(str);
            jSONObjectM2271d.getInt(AuthActivity.ACTION_KEY);
            jSONObjectM2271d.getString("msg");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: com.tencent.open.c$d */
    /* JADX INFO: compiled from: ProGuard */
    private class d extends Handler {

        /* JADX INFO: renamed from: b */
        private c f3240b;

        public d(c cVar, Looper looper) {
            super(looper);
            this.f3240b = cVar;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            C2061f.m2130b("openSDK_LOG.PKDialog", "msg = " + message.what);
            int i = message.what;
            if (i == 1) {
                this.f3240b.m2196a((String) message.obj);
                return;
            }
            if (i == 2) {
                this.f3240b.onCancel();
                return;
            }
            if (i != 3) {
                if (i != 5 || DialogC2073c.this.f3230j == null || DialogC2073c.this.f3230j.get() == null) {
                    return;
                }
                DialogC2073c.m2192d((Context) DialogC2073c.this.f3230j.get(), (String) message.obj);
                return;
            }
            if (DialogC2073c.this.f3230j == null || DialogC2073c.this.f3230j.get() == null) {
                return;
            }
            DialogC2073c.m2191c((Context) DialogC2073c.this.f3230j.get(), (String) message.obj);
        }
    }

    /* JADX INFO: renamed from: com.tencent.open.c$c */
    /* JADX INFO: compiled from: ProGuard */
    private static class c implements IUiListener {

        /* JADX INFO: renamed from: a */
        String f3234a;

        /* JADX INFO: renamed from: b */
        String f3235b;

        /* JADX INFO: renamed from: c */
        private WeakReference<Context> f3236c;

        /* JADX INFO: renamed from: d */
        private String f3237d;

        /* JADX INFO: renamed from: e */
        private IUiListener f3238e;

        public c(Context context, String str, String str2, String str3, IUiListener iUiListener) {
            this.f3236c = new WeakReference<>(context);
            this.f3237d = str;
            this.f3234a = str2;
            this.f3235b = str3;
            this.f3238e = iUiListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a */
        public void m2196a(String str) {
            try {
                onComplete(C2089i.m2271d(str));
            } catch (JSONException e) {
                e.printStackTrace();
                onError(new UiError(-4, Constants.MSG_JSON_ERROR, str));
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            C2072g.m2172a().m2176a(this.f3237d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, jSONObject.optInt("ret", -6), this.f3234a, false);
            IUiListener iUiListener = this.f3238e;
            if (iUiListener != null) {
                iUiListener.onComplete(jSONObject);
                this.f3238e = null;
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            String str;
            if (uiError.errorMessage != null) {
                str = uiError.errorMessage + this.f3234a;
            } else {
                str = this.f3234a;
            }
            C2072g c2072gM2172a = C2072g.m2172a();
            c2072gM2172a.m2176a(this.f3237d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, uiError.errorCode, str, false);
            IUiListener iUiListener = this.f3238e;
            if (iUiListener != null) {
                iUiListener.onError(uiError);
                this.f3238e = null;
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            IUiListener iUiListener = this.f3238e;
            if (iUiListener != null) {
                iUiListener.onCancel();
                this.f3238e = null;
            }
        }
    }

    @Override // com.tencent.open.p028c.C2074a.a
    /* JADX INFO: renamed from: a */
    public void mo2194a(int i) {
        WeakReference<Context> weakReference = this.f3230j;
        if (weakReference != null && weakReference.get() != null) {
            if (i < this.f3231k && 2 == this.f3230j.get().getResources().getConfiguration().orientation) {
                this.f3229i.getLayoutParams().height = i;
            } else {
                this.f3229i.getLayoutParams().height = this.f3231k;
            }
        }
        C2061f.m2135e("openSDK_LOG.PKDialog", "onKeyboardShown keyboard show");
    }

    @Override // com.tencent.open.p028c.C2074a.a
    /* JADX INFO: renamed from: a */
    public void mo2193a() {
        this.f3229i.getLayoutParams().height = this.f3231k;
        C2061f.m2135e("openSDK_LOG.PKDialog", "onKeyboardHidden keyboard hide");
    }

    @Override // com.tencent.open.AbstractDialogC2065b
    /* JADX INFO: renamed from: a */
    protected void mo2078a(String str) {
        C2061f.m2130b("openSDK_LOG.PKDialog", "--onConsoleMessage--");
        try {
            this.f3183a.mo2083a(this.f3229i, str);
        } catch (Exception unused) {
        }
    }
}
