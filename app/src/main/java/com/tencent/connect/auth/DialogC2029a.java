package com.tencent.connect.auth;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.mapapi.UIMsg;
import com.tencent.connect.auth.C2030b;
import com.tencent.connect.common.Constants;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.p027b.C2072g;
import com.tencent.open.p028c.C2076c;
import com.tencent.open.utils.C2084d;
import com.tencent.open.utils.C2089i;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.web.security.C2093b;
import com.tencent.open.web.security.JniInterface;
import com.tencent.open.web.security.SecureJsInterface;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.tencent.connect.auth.a */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class DialogC2029a extends Dialog {

    /* JADX INFO: renamed from: a */
    private String f2977a;

    /* JADX INFO: renamed from: b */
    private b f2978b;

    /* JADX INFO: renamed from: c */
    private IUiListener f2979c;

    /* JADX INFO: renamed from: d */
    private Handler f2980d;

    /* JADX INFO: renamed from: e */
    private FrameLayout f2981e;

    /* JADX INFO: renamed from: f */
    private LinearLayout f2982f;

    /* JADX INFO: renamed from: g */
    private FrameLayout f2983g;

    /* JADX INFO: renamed from: h */
    private ProgressBar f2984h;

    /* JADX INFO: renamed from: i */
    private String f2985i;

    /* JADX INFO: renamed from: j */
    private C2076c f2986j;

    /* JADX INFO: renamed from: k */
    private Context f2987k;

    /* JADX INFO: renamed from: l */
    private C2093b f2988l;

    /* JADX INFO: renamed from: m */
    private boolean f2989m;

    /* JADX INFO: renamed from: n */
    private int f2990n;

    /* JADX INFO: renamed from: o */
    private String f2991o;

    /* JADX INFO: renamed from: p */
    private String f2992p;

    /* JADX INFO: renamed from: q */
    private long f2993q;

    /* JADX INFO: renamed from: r */
    private long f2994r;

    /* JADX INFO: renamed from: s */
    private HashMap<String, Runnable> f2995s;

    /* JADX INFO: renamed from: a */
    static /* synthetic */ String m1955a(DialogC2029a dialogC2029a, Object obj) {
        String str = dialogC2029a.f2977a + obj;
        dialogC2029a.f2977a = str;
        return str;
    }

    /* JADX INFO: renamed from: m */
    static /* synthetic */ int m1978m(DialogC2029a dialogC2029a) {
        int i = dialogC2029a.f2990n;
        dialogC2029a.f2990n = i + 1;
        return i;
    }

    static {
        try {
            Context contextM2215a = C2084d.m2215a();
            if (contextM2215a != null) {
                if (new File(contextM2215a.getFilesDir().toString() + "/" + AuthAgent.SECURE_LIB_NAME).exists()) {
                    System.load(contextM2215a.getFilesDir().toString() + "/" + AuthAgent.SECURE_LIB_NAME);
                    StringBuilder sb = new StringBuilder();
                    sb.append("-->load lib success:");
                    sb.append(AuthAgent.SECURE_LIB_NAME);
                    C2061f.m2133c("openSDK_LOG.AuthDialog", sb.toString());
                } else {
                    C2061f.m2133c("openSDK_LOG.AuthDialog", "-->fail, because so is not exists:" + AuthAgent.SECURE_LIB_NAME);
                }
            } else {
                C2061f.m2133c("openSDK_LOG.AuthDialog", "-->load lib fail, because context is null:" + AuthAgent.SECURE_LIB_NAME);
            }
        } catch (Exception e) {
            C2061f.m2131b("openSDK_LOG.AuthDialog", "-->load lib error:" + AuthAgent.SECURE_LIB_NAME, e);
        }
    }

    public DialogC2029a(Context context, String str, String str2, IUiListener iUiListener, QQToken qQToken) {
        super(context, R.style.Theme.Translucent.NoTitleBar);
        this.f2989m = false;
        this.f2993q = 0L;
        this.f2994r = 30000L;
        this.f2987k = context;
        this.f2977a = str2;
        this.f2978b = new b(str, str2, qQToken.getAppId(), iUiListener);
        this.f2980d = new c(this.f2978b, context.getMainLooper());
        this.f2979c = iUiListener;
        this.f2985i = str;
        this.f2988l = new C2093b();
        getWindow().setSoftInputMode(32);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        m1961b();
        m1968d();
        this.f2995s = new HashMap<>();
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        if (!this.f2989m) {
            this.f2978b.onCancel();
        }
        super.onBackPressed();
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
    }

    /* JADX INFO: renamed from: com.tencent.connect.auth.a$c */
    /* JADX INFO: compiled from: ProGuard */
    private class c extends Handler {

        /* JADX INFO: renamed from: b */
        private b f3007b;

        public c(b bVar, Looper looper) {
            super(looper);
            this.f3007b = bVar;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                this.f3007b.m1984a((String) message.obj);
            } else if (i == 2) {
                this.f3007b.onCancel();
            } else {
                if (i != 3) {
                    return;
                }
                DialogC2029a.m1962b(DialogC2029a.this.f2987k, (String) message.obj);
            }
        }
    }

    /* JADX INFO: renamed from: com.tencent.connect.auth.a$b */
    /* JADX INFO: compiled from: ProGuard */
    private class b implements IUiListener {

        /* JADX INFO: renamed from: a */
        String f3001a;

        /* JADX INFO: renamed from: b */
        String f3002b;

        /* JADX INFO: renamed from: d */
        private String f3004d;

        /* JADX INFO: renamed from: e */
        private IUiListener f3005e;

        public b(String str, String str2, String str3, IUiListener iUiListener) {
            this.f3004d = str;
            this.f3001a = str2;
            this.f3002b = str3;
            this.f3005e = iUiListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a */
        public void m1984a(String str) {
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
            C2072g.m2172a().m2176a(this.f3004d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, jSONObject.optInt("ret", -6), this.f3001a, false);
            IUiListener iUiListener = this.f3005e;
            if (iUiListener != null) {
                iUiListener.onComplete(jSONObject);
                this.f3005e = null;
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            String str;
            if (uiError.errorMessage != null) {
                str = uiError.errorMessage + this.f3001a;
            } else {
                str = this.f3001a;
            }
            C2072g.m2172a().m2176a(this.f3004d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, uiError.errorCode, str, false);
            DialogC2029a.this.m1957a(str);
            IUiListener iUiListener = this.f3005e;
            if (iUiListener != null) {
                iUiListener.onError(uiError);
                this.f3005e = null;
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            IUiListener iUiListener = this.f3005e;
            if (iUiListener != null) {
                iUiListener.onCancel();
                this.f3005e = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public String m1957a(String str) {
        StringBuilder sb = new StringBuilder(str);
        if (!TextUtils.isEmpty(this.f2992p) && this.f2992p.length() >= 4) {
            String str2 = this.f2992p;
            String strSubstring = str2.substring(str2.length() - 4);
            sb.append("_u_");
            sb.append(strSubstring);
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: com.tencent.connect.auth.a$a */
    /* JADX INFO: compiled from: ProGuard */
    private class a extends WebViewClient {
        private a() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            C2061f.m2127a("openSDK_LOG.AuthDialog", "-->Redirect URL: " + str);
            if (str.startsWith("auth://browser")) {
                JSONObject jSONObjectM2267c = C2089i.m2267c(str);
                DialogC2029a dialogC2029a = DialogC2029a.this;
                dialogC2029a.f2989m = dialogC2029a.m1970e();
                if (!DialogC2029a.this.f2989m) {
                    if (jSONObjectM2267c.optString("fail_cb", null) != null) {
                        DialogC2029a.this.m1982a(jSONObjectM2267c.optString("fail_cb"), "");
                    } else if (jSONObjectM2267c.optInt("fall_to_wv") == 1) {
                        DialogC2029a dialogC2029a2 = DialogC2029a.this;
                        DialogC2029a.m1955a(dialogC2029a2, (Object) (dialogC2029a2.f2977a.indexOf("?") > -1 ? "&" : "?"));
                        DialogC2029a.m1955a(DialogC2029a.this, (Object) "browser_error=1");
                        DialogC2029a.this.f2986j.loadUrl(DialogC2029a.this.f2977a);
                    } else {
                        String strOptString = jSONObjectM2267c.optString("redir", null);
                        if (strOptString != null) {
                            DialogC2029a.this.f2986j.loadUrl(strOptString);
                        }
                    }
                }
                return true;
            }
            if (str.startsWith("auth://tauth.qq.com/")) {
                DialogC2029a.this.f2978b.onComplete(C2089i.m2267c(str));
                DialogC2029a.this.dismiss();
                return true;
            }
            if (str.startsWith(Constants.CANCEL_URI)) {
                DialogC2029a.this.f2978b.onCancel();
                DialogC2029a.this.dismiss();
                return true;
            }
            if (str.startsWith(Constants.CLOSE_URI)) {
                DialogC2029a.this.dismiss();
                return true;
            }
            if (str.startsWith(Constants.DOWNLOAD_URI)) {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(Uri.decode(str.substring(11))));
                    intent.addFlags(268435456);
                    DialogC2029a.this.f2987k.startActivity(intent);
                } catch (Exception e) {
                    C2061f.m2131b("openSDK_LOG.AuthDialog", "-->start download activity exception, e: ", e);
                }
                return true;
            }
            if (str.startsWith("auth://progress")) {
                try {
                    List<String> pathSegments = Uri.parse(str).getPathSegments();
                    if (pathSegments.isEmpty()) {
                        return true;
                    }
                    int iIntValue = Integer.valueOf(pathSegments.get(0)).intValue();
                    if (iIntValue == 0) {
                        DialogC2029a.this.f2983g.setVisibility(8);
                        DialogC2029a.this.f2986j.setVisibility(0);
                    } else if (iIntValue == 1) {
                        DialogC2029a.this.f2983g.setVisibility(0);
                    }
                } catch (Exception unused) {
                }
                return true;
            }
            if (!str.startsWith("auth://onLoginSubmit")) {
                if (DialogC2029a.this.f2988l.mo2083a(DialogC2029a.this.f2986j, str)) {
                    return true;
                }
                C2061f.m2133c("openSDK_LOG.AuthDialog", "-->Redirect URL: return false");
                return false;
            }
            try {
                List<String> pathSegments2 = Uri.parse(str).getPathSegments();
                if (!pathSegments2.isEmpty()) {
                    DialogC2029a.this.f2992p = pathSegments2.get(0);
                }
            } catch (Exception unused2) {
            }
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            C2061f.m2133c("openSDK_LOG.AuthDialog", "-->onReceivedError, errorCode: " + i + " | description: " + str);
            if (!C2089i.m2268c(DialogC2029a.this.f2987k)) {
                DialogC2029a.this.f2978b.onError(new UiError(UIMsg.m_AppUI.MSG_CLICK_ITEM, "当前网络不可用，请稍后重试！", str2));
                DialogC2029a.this.dismiss();
                return;
            }
            if (!DialogC2029a.this.f2991o.startsWith("http://qzs.qq.com/open/mobile/login/qzsjump.html?")) {
                long jElapsedRealtime = SystemClock.elapsedRealtime() - DialogC2029a.this.f2993q;
                if (DialogC2029a.this.f2990n >= 1 || jElapsedRealtime >= DialogC2029a.this.f2994r) {
                    DialogC2029a.this.f2986j.loadUrl(DialogC2029a.this.m1954a());
                    return;
                } else {
                    DialogC2029a.m1978m(DialogC2029a.this);
                    DialogC2029a.this.f2980d.postDelayed(new Runnable() { // from class: com.tencent.connect.auth.a.a.1
                        @Override // java.lang.Runnable
                        public void run() {
                            DialogC2029a.this.f2986j.loadUrl(DialogC2029a.this.f2991o);
                        }
                    }, 500L);
                    return;
                }
            }
            DialogC2029a.this.f2978b.onError(new UiError(i, str, str2));
            DialogC2029a.this.dismiss();
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            C2061f.m2127a("openSDK_LOG.AuthDialog", "-->onPageStarted, url: " + str);
            super.onPageStarted(webView, str, bitmap);
            DialogC2029a.this.f2983g.setVisibility(0);
            DialogC2029a.this.f2993q = SystemClock.elapsedRealtime();
            if (!TextUtils.isEmpty(DialogC2029a.this.f2991o)) {
                DialogC2029a.this.f2980d.removeCallbacks((Runnable) DialogC2029a.this.f2995s.remove(DialogC2029a.this.f2991o));
            }
            DialogC2029a.this.f2991o = str;
            DialogC2029a dialogC2029a = DialogC2029a.this;
            d dVar = dialogC2029a.new d(dialogC2029a.f2991o);
            DialogC2029a.this.f2995s.put(str, dVar);
            DialogC2029a.this.f2980d.postDelayed(dVar, 120000L);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            C2061f.m2127a("openSDK_LOG.AuthDialog", "-->onPageFinished, url: " + str);
            DialogC2029a.this.f2983g.setVisibility(8);
            if (DialogC2029a.this.f2986j != null) {
                DialogC2029a.this.f2986j.setVisibility(0);
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            DialogC2029a.this.f2980d.removeCallbacks((Runnable) DialogC2029a.this.f2995s.remove(str));
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.cancel();
            DialogC2029a.this.f2978b.onError(new UiError(sslError.getPrimaryError(), "请求不合法，请检查手机安全设置，如系统时间、代理等。", "ssl error"));
            DialogC2029a.this.dismiss();
        }
    }

    /* JADX INFO: renamed from: com.tencent.connect.auth.a$d */
    /* JADX INFO: compiled from: ProGuard */
    class d implements Runnable {

        /* JADX INFO: renamed from: a */
        String f3008a;

        public d(String str) {
            this.f3008a = "";
            this.f3008a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            C2061f.m2127a("openSDK_LOG.AuthDialog", "-->timeoutUrl: " + this.f3008a + " | mRetryUrl: " + DialogC2029a.this.f2991o);
            if (this.f3008a.equals(DialogC2029a.this.f2991o)) {
                DialogC2029a.this.f2978b.onError(new UiError(9002, "请求页面超时，请稍后重试！", DialogC2029a.this.f2991o));
                DialogC2029a.this.dismiss();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public String m1954a() {
        String str = this.f2977a;
        String str2 = "http://qzs.qq.com/open/mobile/login/qzsjump.html?" + str.substring(str.indexOf("?") + 1);
        C2061f.m2133c("openSDK_LOG.AuthDialog", "-->generateDownloadUrl, url: http://qzs.qq.com/open/mobile/login/qzsjump.html?");
        return str2;
    }

    /* JADX INFO: renamed from: b */
    private void m1961b() {
        m1965c();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.f2986j = new C2076c(this.f2987k);
        if (Build.VERSION.SDK_INT >= 11) {
            this.f2986j.setLayerType(1, null);
        }
        this.f2986j.setLayoutParams(layoutParams);
        this.f2981e = new FrameLayout(this.f2987k);
        layoutParams.gravity = 17;
        this.f2981e.setLayoutParams(layoutParams);
        this.f2981e.addView(this.f2986j);
        this.f2981e.addView(this.f2983g);
        setContentView(this.f2981e);
    }

    /* JADX INFO: renamed from: c */
    private void m1965c() {
        TextView textView;
        this.f2984h = new ProgressBar(this.f2987k);
        this.f2984h.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.f2982f = new LinearLayout(this.f2987k);
        if (this.f2985i.equals("action_login")) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 16;
            layoutParams.leftMargin = 5;
            textView = new TextView(this.f2987k);
            if (Locale.getDefault().getLanguage().equals("zh")) {
                textView.setText("登录中...");
            } else {
                textView.setText("Logging in...");
            }
            textView.setTextColor(Color.rgb(255, 255, 255));
            textView.setTextSize(18.0f);
            textView.setLayoutParams(layoutParams);
        } else {
            textView = null;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 17;
        this.f2982f.setLayoutParams(layoutParams2);
        this.f2982f.addView(this.f2984h);
        if (textView != null) {
            this.f2982f.addView(textView);
        }
        this.f2983g = new FrameLayout(this.f2987k);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams3.leftMargin = 80;
        layoutParams3.rightMargin = 80;
        layoutParams3.topMargin = 40;
        layoutParams3.bottomMargin = 40;
        layoutParams3.gravity = 17;
        this.f2983g.setLayoutParams(layoutParams3);
        this.f2983g.setBackgroundResource(R.drawable.alert_dark_frame);
        this.f2983g.addView(this.f2982f);
    }

    /* JADX INFO: renamed from: d */
    private void m1968d() {
        this.f2986j.setVerticalScrollBarEnabled(false);
        this.f2986j.setHorizontalScrollBarEnabled(false);
        this.f2986j.setWebViewClient(new a());
        this.f2986j.setWebChromeClient(new WebChromeClient());
        this.f2986j.clearFormData();
        this.f2986j.clearSslPreferences();
        this.f2986j.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.tencent.connect.auth.a.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                return true;
            }
        });
        this.f2986j.setOnTouchListener(new View.OnTouchListener() { // from class: com.tencent.connect.auth.a.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if ((action != 0 && action != 1) || view.hasFocus()) {
                    return false;
                }
                view.requestFocus();
                return false;
            }
        });
        WebSettings settings = this.f2986j.getSettings();
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setCacheMode(-1);
        settings.setNeedInitialFocus(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(this.f2987k.getDir("databases", 0).getPath());
        settings.setDomStorageEnabled(true);
        C2061f.m2127a("openSDK_LOG.AuthDialog", "-->mUrl : " + this.f2977a);
        String str = this.f2977a;
        this.f2991o = str;
        this.f2986j.loadUrl(str);
        this.f2986j.setVisibility(4);
        this.f2986j.getSettings().setSavePassword(false);
        this.f2988l.m2081a(new SecureJsInterface(), "SecureJsInterface");
        SecureJsInterface.isPWDEdit = false;
        super.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.tencent.connect.auth.a.3
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                try {
                    JniInterface.clearAllPWD();
                } catch (Exception unused) {
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: e */
    public boolean m1970e() {
        C2030b c2030bM1985a = C2030b.m1985a();
        String strM1988c = c2030bM1985a.m1988c();
        C2030b.a aVar = new C2030b.a();
        aVar.f3015a = this.f2979c;
        aVar.f3016b = this;
        aVar.f3017c = strM1988c;
        String strM1987a = c2030bM1985a.m1987a(aVar);
        String str = this.f2977a;
        String strSubstring = str.substring(0, str.indexOf("?"));
        Bundle bundleM2262b = C2089i.m2262b(this.f2977a);
        bundleM2262b.putString("token_key", strM1988c);
        bundleM2262b.putString("serial", strM1987a);
        bundleM2262b.putString("browser", "1");
        String str2 = strSubstring + "?" + HttpUtils.encodeUrl(bundleM2262b);
        this.f2977a = str2;
        return C2089i.m2261a(this.f2987k, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public static void m1962b(Context context, String str) {
        try {
            JSONObject jSONObjectM2271d = C2089i.m2271d(str);
            int i = jSONObjectM2271d.getInt("type");
            Toast.makeText(context.getApplicationContext(), jSONObjectM2271d.getString("msg"), i).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: a */
    public void m1982a(String str, String str2) {
        this.f2986j.loadUrl("javascript:" + str + "(" + str2 + ");void(" + System.currentTimeMillis() + ");");
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        this.f2995s.clear();
        this.f2980d.removeCallbacksAndMessages(null);
        if (isShowing()) {
            super.dismiss();
        }
        C2076c c2076c = this.f2986j;
        if (c2076c != null) {
            c2076c.destroy();
            this.f2986j = null;
        }
    }
}
