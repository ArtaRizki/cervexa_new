package com.tencent.open;

import android.R;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.open.C2055a;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.p027b.C2072g;
import com.tencent.open.p028c.C2075b;
import com.tencent.open.utils.C2086f;
import com.tencent.open.utils.C2089i;
import com.tencent.tauth.AuthActivity;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class TDialog extends AbstractDialogC2065b {

    /* JADX INFO: renamed from: c */
    static final FrameLayout.LayoutParams f3109c = new FrameLayout.LayoutParams(-1, -1);

    /* JADX INFO: renamed from: d */
    static Toast f3110d = null;

    /* JADX INFO: renamed from: f */
    private static WeakReference<ProgressDialog> f3111f;

    /* JADX INFO: renamed from: e */
    private WeakReference<Context> f3112e;

    /* JADX INFO: renamed from: g */
    private String f3113g;

    /* JADX INFO: renamed from: h */
    private OnTimeListener f3114h;

    /* JADX INFO: renamed from: i */
    private IUiListener f3115i;

    /* JADX INFO: renamed from: j */
    private FrameLayout f3116j;

    /* JADX INFO: renamed from: k */
    private C2075b f3117k;

    /* JADX INFO: renamed from: l */
    private Handler f3118l;

    /* JADX INFO: renamed from: m */
    private boolean f3119m;

    /* JADX INFO: renamed from: n */
    private QQToken f3120n;

    /* JADX INFO: compiled from: ProGuard */
    private class THandler extends Handler {

        /* JADX INFO: renamed from: b */
        private OnTimeListener f3129b;

        public THandler(OnTimeListener onTimeListener, Looper looper) {
            super(looper);
            this.f3129b = onTimeListener;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            C2061f.m2130b("openSDK_LOG.TDialog", "--handleMessage--msg.WHAT = " + message.what);
            int i = message.what;
            if (i == 1) {
                this.f3129b.m2080a((String) message.obj);
                return;
            }
            if (i == 2) {
                this.f3129b.onCancel();
                return;
            }
            if (i != 3) {
                if (i != 5 || TDialog.this.f3112e == null || TDialog.this.f3112e.get() == null) {
                    return;
                }
                TDialog.m2077d((Context) TDialog.this.f3112e.get(), (String) message.obj);
                return;
            }
            if (TDialog.this.f3112e == null || TDialog.this.f3112e.get() == null) {
                return;
            }
            TDialog.m2075c((Context) TDialog.this.f3112e.get(), (String) message.obj);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    private static class OnTimeListener implements IUiListener {

        /* JADX INFO: renamed from: a */
        String f3123a;

        /* JADX INFO: renamed from: b */
        String f3124b;

        /* JADX INFO: renamed from: c */
        private WeakReference<Context> f3125c;

        /* JADX INFO: renamed from: d */
        private String f3126d;

        /* JADX INFO: renamed from: e */
        private IUiListener f3127e;

        public OnTimeListener(Context context, String str, String str2, String str3, IUiListener iUiListener) {
            this.f3125c = new WeakReference<>(context);
            this.f3126d = str;
            this.f3123a = str2;
            this.f3124b = str3;
            this.f3127e = iUiListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a */
        public void m2080a(String str) {
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
            C2072g.m2172a().m2176a(this.f3126d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, jSONObject.optInt("ret", -6), this.f3123a, false);
            IUiListener iUiListener = this.f3127e;
            if (iUiListener != null) {
                iUiListener.onComplete(jSONObject);
                this.f3127e = null;
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            String str;
            if (uiError.errorMessage != null) {
                str = uiError.errorMessage + this.f3123a;
            } else {
                str = this.f3123a;
            }
            C2072g c2072gM2172a = C2072g.m2172a();
            c2072gM2172a.m2176a(this.f3126d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, uiError.errorCode, str, false);
            IUiListener iUiListener = this.f3127e;
            if (iUiListener != null) {
                iUiListener.onError(uiError);
                this.f3127e = null;
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            IUiListener iUiListener = this.f3127e;
            if (iUiListener != null) {
                iUiListener.onCancel();
                this.f3127e = null;
            }
        }
    }

    public TDialog(Context context, String str, String str2, IUiListener iUiListener, QQToken qQToken) {
        super(context, R.style.Theme.Translucent.NoTitleBar);
        this.f3119m = false;
        this.f3120n = null;
        this.f3112e = new WeakReference<>(context);
        this.f3113g = str2;
        this.f3114h = new OnTimeListener(context, str, str2, qQToken.getAppId(), iUiListener);
        this.f3118l = new THandler(this.f3114h, context.getMainLooper());
        this.f3115i = iUiListener;
        this.f3120n = qQToken;
    }

    @Override // com.tencent.open.AbstractDialogC2065b, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        m2069a();
        m2072b();
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        OnTimeListener onTimeListener = this.f3114h;
        if (onTimeListener != null) {
            onTimeListener.onCancel();
        }
        super.onBackPressed();
    }

    /* JADX INFO: renamed from: a */
    private void m2069a() {
        new TextView(this.f3112e.get()).setText("test");
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        C2075b c2075b = new C2075b(this.f3112e.get());
        this.f3117k = c2075b;
        c2075b.setLayoutParams(layoutParams);
        this.f3116j = new FrameLayout(this.f3112e.get());
        layoutParams.gravity = 17;
        this.f3116j.setLayoutParams(layoutParams);
        this.f3116j.addView(this.f3117k);
        setContentView(this.f3116j);
    }

    @Override // com.tencent.open.AbstractDialogC2065b
    /* JADX INFO: renamed from: a */
    protected void mo2078a(String str) {
        C2061f.m2130b("openSDK_LOG.TDialog", "--onConsoleMessage--");
        try {
            this.f3183a.mo2083a(this.f3117k, str);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    private void m2072b() {
        this.f3117k.setVerticalScrollBarEnabled(false);
        this.f3117k.setHorizontalScrollBarEnabled(false);
        this.f3117k.setWebViewClient(new FbWebViewClient());
        this.f3117k.setWebChromeClient(this.f3184b);
        this.f3117k.clearFormData();
        WebSettings settings = this.f3117k.getSettings();
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
        WeakReference<Context> weakReference = this.f3112e;
        if (weakReference != null && weakReference.get() != null) {
            settings.setDatabaseEnabled(true);
            settings.setDatabasePath(this.f3112e.get().getApplicationContext().getDir("databases", 0).getPath());
        }
        settings.setDomStorageEnabled(true);
        this.f3183a.m2081a(new JsListener(), "sdk_js_if");
        this.f3117k.loadUrl(this.f3113g);
        this.f3117k.setLayoutParams(f3109c);
        this.f3117k.setVisibility(4);
        this.f3117k.getSettings().setSavePassword(false);
    }

    /* JADX INFO: compiled from: ProGuard */
    private class JsListener extends C2055a.b {
        private JsListener() {
        }

        public void onAddShare(String str) {
            C2061f.m2130b("openSDK_LOG.TDialog", "JsListener onAddShare");
            onComplete(str);
        }

        public void onInvite(String str) {
            onComplete(str);
        }

        public void onCancelAddShare(String str) {
            C2061f.m2135e("openSDK_LOG.TDialog", "JsListener onCancelAddShare" + str);
            onCancel("cancel");
        }

        public void onCancelLogin() {
            onCancel("");
        }

        public void onCancelInvite() {
            C2061f.m2135e("openSDK_LOG.TDialog", "JsListener onCancelInvite");
            onCancel("");
        }

        public void onComplete(String str) {
            TDialog.this.f3118l.obtainMessage(1, str).sendToTarget();
            C2061f.m2135e("openSDK_LOG.TDialog", "JsListener onComplete" + str);
            TDialog.this.dismiss();
        }

        public void onCancel(String str) {
            C2061f.m2135e("openSDK_LOG.TDialog", "JsListener onCancel --msg = " + str);
            TDialog.this.f3118l.obtainMessage(2, str).sendToTarget();
            TDialog.this.dismiss();
        }

        public void showMsg(String str) {
            TDialog.this.f3118l.obtainMessage(3, str).sendToTarget();
        }

        public void onLoad(String str) {
            TDialog.this.f3118l.obtainMessage(4, str).sendToTarget();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    private class FbWebViewClient extends WebViewClient {
        private FbWebViewClient() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            C2061f.m2127a("openSDK_LOG.TDialog", "Redirect URL: " + str);
            if (str.startsWith(C2086f.m2232a().m2233a((Context) TDialog.this.f3112e.get(), "auth://tauth.qq.com/"))) {
                TDialog.this.f3114h.onComplete(C2089i.m2267c(str));
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            }
            if (str.startsWith(Constants.CANCEL_URI)) {
                TDialog.this.f3114h.onCancel();
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            }
            if (str.startsWith(Constants.CLOSE_URI)) {
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            }
            if (!str.startsWith(Constants.DOWNLOAD_URI)) {
                return str.startsWith("auth://progress");
            }
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(Uri.decode(str.substring(11))));
                intent.addFlags(268435456);
                if (TDialog.this.f3112e != null && TDialog.this.f3112e.get() != null) {
                    ((Context) TDialog.this.f3112e.get()).startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            TDialog.this.f3114h.onError(new UiError(i, str, str2));
            if (TDialog.this.f3112e != null && TDialog.this.f3112e.get() != null) {
                Toast.makeText((Context) TDialog.this.f3112e.get(), "网络连接异常或系统错误", 0).show();
            }
            TDialog.this.dismiss();
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            C2061f.m2127a("openSDK_LOG.TDialog", "Webview loading URL: " + str);
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            TDialog.this.f3117k.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c */
    public static void m2075c(Context context, String str) {
        try {
            JSONObject jSONObjectM2271d = C2089i.m2271d(str);
            int i = jSONObjectM2271d.getInt("type");
            String string = jSONObjectM2271d.getString("msg");
            if (i == 0) {
                if (f3110d == null) {
                    f3110d = Toast.makeText(context, string, 0);
                } else {
                    f3110d.setView(f3110d.getView());
                    f3110d.setText(string);
                    f3110d.setDuration(0);
                }
                f3110d.show();
                return;
            }
            if (i == 1) {
                if (f3110d == null) {
                    f3110d = Toast.makeText(context, string, 1);
                } else {
                    f3110d.setView(f3110d.getView());
                    f3110d.setText(string);
                    f3110d.setDuration(1);
                }
                f3110d.show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: d */
    public static void m2077d(Context context, String str) {
        if (context == null || str == null) {
            return;
        }
        try {
            JSONObject jSONObjectM2271d = C2089i.m2271d(str);
            int i = jSONObjectM2271d.getInt(AuthActivity.ACTION_KEY);
            String string = jSONObjectM2271d.getString("msg");
            if (i == 1) {
                if (f3111f == null || f3111f.get() == null) {
                    ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage(string);
                    f3111f = new WeakReference<>(progressDialog);
                    progressDialog.show();
                } else {
                    f3111f.get().setMessage(string);
                    if (!f3111f.get().isShowing()) {
                        f3111f.get().show();
                    }
                }
            } else if (i == 0) {
                if (f3111f == null) {
                    return;
                }
                if (f3111f.get() != null && f3111f.get().isShowing()) {
                    f3111f.get().dismiss();
                    f3111f = null;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
