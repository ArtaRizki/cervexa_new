package com.tencent.connect.auth;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.connect.p022a.C2024a;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.p027b.C2069d;
import com.tencent.open.utils.C2084d;
import com.tencent.open.utils.C2086f;
import com.tencent.open.utils.C2087g;
import com.tencent.open.utils.C2088h;
import com.tencent.open.utils.C2089i;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import org.apache.commons.net.telnet.TelnetCommand;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class AuthAgent extends BaseApi {
    public static final String SECURE_LIB_ARM64_FILE_NAME = "libwbsafeedit_64";
    public static final String SECURE_LIB_ARM_FILE_NAME = "libwbsafeedit";
    public static String SECURE_LIB_FILE_NAME = "libwbsafeedit";
    public static String SECURE_LIB_NAME = null;
    public static final String SECURE_LIB_X86_64_FILE_NAME = "libwbsafeedit_x86_64";
    public static final String SECURE_LIB_X86_FILE_NAME = "libwbsafeedit_x86";

    /* JADX INFO: renamed from: c */
    private IUiListener f2942c;

    /* JADX INFO: renamed from: d */
    private String f2943d;

    /* JADX INFO: renamed from: e */
    private WeakReference<Activity> f2944e;

    static {
        SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
        String str = Build.CPU_ABI;
        if (str != null && !str.equals("")) {
            if (str.equalsIgnoreCase("arm64-v8a")) {
                SECURE_LIB_FILE_NAME = SECURE_LIB_ARM64_FILE_NAME;
                SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
                C2061f.m2133c("openSDK_LOG.AuthAgent", "is arm64-v8a architecture");
                return;
            }
            if (str.equalsIgnoreCase("x86")) {
                SECURE_LIB_FILE_NAME = SECURE_LIB_X86_FILE_NAME;
                SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
                C2061f.m2133c("openSDK_LOG.AuthAgent", "is x86 architecture");
                return;
            }
            if (str.equalsIgnoreCase("x86_64")) {
                SECURE_LIB_FILE_NAME = SECURE_LIB_X86_64_FILE_NAME;
                SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
                C2061f.m2133c("openSDK_LOG.AuthAgent", "is x86_64 architecture");
                return;
            }
            SECURE_LIB_FILE_NAME = SECURE_LIB_ARM_FILE_NAME;
            SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
            C2061f.m2133c("openSDK_LOG.AuthAgent", "is arm(default) architecture");
            return;
        }
        SECURE_LIB_FILE_NAME = SECURE_LIB_ARM_FILE_NAME;
        SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
        C2061f.m2133c("openSDK_LOG.AuthAgent", "is arm(default) architecture");
    }

    public AuthAgent(QQToken qQToken) {
        super(qQToken);
    }

    /* JADX INFO: renamed from: com.tencent.connect.auth.AuthAgent$c */
    /* JADX INFO: compiled from: ProGuard */
    private class C2028c implements IUiListener {

        /* JADX INFO: renamed from: b */
        private final IUiListener f2969b;

        /* JADX INFO: renamed from: c */
        private final boolean f2970c;

        /* JADX INFO: renamed from: d */
        private final Context f2971d;

        public C2028c(Context context, IUiListener iUiListener, boolean z, boolean z2) {
            this.f2971d = context;
            this.f2969b = iUiListener;
            this.f2970c = z;
            C2061f.m2130b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener()");
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            C2061f.m2130b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete");
            JSONObject jSONObject = (JSONObject) obj;
            try {
                String string = jSONObject.getString(Constants.PARAM_ACCESS_TOKEN);
                String string2 = jSONObject.getString(Constants.PARAM_EXPIRES_IN);
                String string3 = jSONObject.getString("openid");
                if (string != null && AuthAgent.this.f3024b != null && string3 != null) {
                    AuthAgent.this.f3024b.setAccessToken(string, string2);
                    AuthAgent.this.f3024b.setOpenId(string3);
                    C2024a.m1935d(this.f2971d, AuthAgent.this.f3024b);
                }
                String string4 = jSONObject.getString(Constants.PARAM_PLATFORM_ID);
                if (string4 != null) {
                    try {
                        this.f2971d.getSharedPreferences(Constants.PREFERENCE_PF, 0).edit().putString(Constants.PARAM_PLATFORM_ID, string4).commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                        C2061f.m2131b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete error", e);
                    }
                }
                if (this.f2970c) {
                    CookieSyncManager.getInstance().sync();
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                C2061f.m2131b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete error", e2);
            }
            this.f2969b.onComplete(jSONObject);
            AuthAgent.this.releaseResource();
            C2061f.m2129b();
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            C2061f.m2130b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onError");
            this.f2969b.onError(uiError);
            C2061f.m2129b();
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            C2061f.m2130b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onCancel");
            this.f2969b.onCancel();
            C2061f.m2129b();
        }
    }

    public int doLogin(Activity activity, String str, IUiListener iUiListener) {
        return doLogin(activity, str, iUiListener, false, null);
    }

    public int doLogin(Activity activity, String str, IUiListener iUiListener, boolean z, Fragment fragment) {
        this.f2943d = str;
        this.f2944e = new WeakReference<>(activity);
        this.f2942c = iUiListener;
        if (m1938a(activity, fragment, z)) {
            C2061f.m2133c("openSDK_LOG.AuthAgent", "OpenUi, showUi, return Constants.UI_ACTIVITY");
            C2069d.m2162a().m2164a(this.f3024b.getOpenId(), this.f3024b.getAppId(), "2", "1", "5", "0", "0", "0");
            return 1;
        }
        C2069d.m2162a().m2164a(this.f3024b.getOpenId(), this.f3024b.getAppId(), "2", "1", "5", "1", "0", "0");
        C2061f.m2134d("openSDK_LOG.AuthAgent", "doLogin startActivity fail show dialog.");
        C2027b c2027b = new C2027b(this.f2942c);
        this.f2942c = c2027b;
        return m1936a(z, c2027b);
    }

    @Override // com.tencent.connect.common.BaseApi
    public void releaseResource() {
        this.f2944e = null;
        this.f2942c = null;
    }

    /* JADX INFO: renamed from: a */
    private int m1936a(boolean z, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.AuthAgent", "OpenUi, showDialog -- start");
        CookieSyncManager.createInstance(C2084d.m2215a());
        Bundle bundleA = m2004a();
        if (z) {
            bundleA.putString("isadd", "1");
        }
        bundleA.putString(Constants.PARAM_SCOPE, this.f2943d);
        bundleA.putString(Constants.PARAM_CLIENT_ID, this.f3024b.getAppId());
        if (isOEM) {
            bundleA.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + installChannel + "-" + IConstant.ANDROID_DIR + "-" + registerChannel + "-" + businessId);
        } else {
            bundleA.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
        }
        String str = (System.currentTimeMillis() / 1000) + "";
        bundleA.putString("sign", C2087g.m2243b(C2084d.m2215a(), str));
        bundleA.putString("time", str);
        bundleA.putString("display", "mobile");
        bundleA.putString("response_type", "token");
        bundleA.putString("redirect_uri", "auth://tauth.qq.com/");
        bundleA.putString("cancel_display", "1");
        bundleA.putString("switch", "1");
        bundleA.putString("status_userip", C2089i.m2255a());
        final String str2 = C2086f.m2232a().m2233a(C2084d.m2215a(), "http://openmobile.qq.com/oauth2.0/m_authorize?") + HttpUtils.encodeUrl(bundleA);
        final C2028c c2028c = new C2028c(C2084d.m2215a(), iUiListener, true, false);
        C2061f.m2130b("openSDK_LOG.AuthAgent", "OpenUi, showDialog TDialog");
        C2088h.m2247a(new Runnable() { // from class: com.tencent.connect.auth.AuthAgent.1
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                C2087g.m2242a(AuthAgent.SECURE_LIB_FILE_NAME, AuthAgent.SECURE_LIB_NAME, 3);
                final Activity activity = (Activity) AuthAgent.this.f2944e.get();
                if (activity != null) {
                    activity.runOnUiThread(new Runnable() { // from class: com.tencent.connect.auth.AuthAgent.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            DialogC2029a dialogC2029a = new DialogC2029a(activity, "action_login", str2, c2028c, AuthAgent.this.f3024b);
                            Activity activity2 = activity;
                            if (activity2 == null || activity2.isFinishing()) {
                                return;
                            }
                            dialogC2029a.show();
                        }
                    });
                }
            }
        });
        C2061f.m2133c("openSDK_LOG.AuthAgent", "OpenUi, showDialog -- end");
        return 2;
    }

    /* JADX INFO: renamed from: a */
    private boolean m1938a(Activity activity, Fragment fragment, boolean z) {
        C2061f.m2133c("openSDK_LOG.AuthAgent", "startActionActivity() -- start");
        Intent intentB = mo2011b("com.tencent.open.agent.AgentActivity");
        if (intentB != null) {
            Bundle bundleA = m2004a();
            if (z) {
                bundleA.putString("isadd", "1");
            }
            bundleA.putString(Constants.PARAM_SCOPE, this.f2943d);
            bundleA.putString(Constants.PARAM_CLIENT_ID, this.f3024b.getAppId());
            if (isOEM) {
                bundleA.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + installChannel + "-" + IConstant.ANDROID_DIR + "-" + registerChannel + "-" + businessId);
            } else {
                bundleA.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
            }
            bundleA.putString("need_pay", "1");
            bundleA.putString(Constants.KEY_APP_NAME, C2087g.m2238a(C2084d.m2215a()));
            intentB.putExtra(Constants.KEY_ACTION, "action_login");
            intentB.putExtra(Constants.KEY_PARAMS, bundleA);
            if (m2010a(intentB)) {
                this.f2942c = new C2027b(this.f2942c);
                UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_LOGIN, this.f2942c);
                if (fragment != null) {
                    C2061f.m2130b("openSDK_LOG.AuthAgent", "startAssitActivity fragment");
                    m2009a(fragment, intentB, Constants.REQUEST_LOGIN);
                } else {
                    C2061f.m2130b("openSDK_LOG.AuthAgent", "startAssitActivity activity");
                    m2007a(activity, intentB, Constants.REQUEST_LOGIN);
                }
                C2061f.m2133c("openSDK_LOG.AuthAgent", "startActionActivity() -- end, found activity for loginIntent");
                C2069d.m2162a().m2163a(0, "LOGIN_CHECK_SDK", Constants.DEFAULT_UIN, this.f3024b.getAppId(), "", Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "");
                return true;
            }
        }
        C2069d.m2162a().m2163a(1, "LOGIN_CHECK_SDK", Constants.DEFAULT_UIN, this.f3024b.getAppId(), "", Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "startActionActivity fail");
        C2061f.m2133c("openSDK_LOG.AuthAgent", "startActionActivity() -- end, no target activity for loginIntent");
        return false;
    }

    /* JADX INFO: renamed from: a */
    protected void m1946a(IUiListener iUiListener) {
        String strM2275f;
        C2061f.m2133c("openSDK_LOG.AuthAgent", "reportDAU() -- start");
        String accessToken = this.f3024b.getAccessToken();
        String openId = this.f3024b.getOpenId();
        String appId = this.f3024b.getAppId();
        if (TextUtils.isEmpty(accessToken) || TextUtils.isEmpty(openId) || TextUtils.isEmpty(appId)) {
            strM2275f = "";
        } else {
            strM2275f = C2089i.m2275f("tencent&sdk&qazxc***14969%%" + accessToken + appId + openId + "qzone3.4");
        }
        if (TextUtils.isEmpty(strM2275f)) {
            C2061f.m2135e("openSDK_LOG.AuthAgent", "reportDAU -- encrytoken is null");
            return;
        }
        Bundle bundleA = m2004a();
        bundleA.putString("encrytoken", strM2275f);
        HttpUtils.requestAsync(this.f3024b, C2084d.m2215a(), "https://openmobile.qq.com/user/user_login_statis", bundleA, Constants.HTTP_POST, null);
        C2061f.m2133c("openSDK_LOG.AuthAgent", "reportDAU() -- end");
    }

    /* JADX INFO: renamed from: b */
    protected void m1947b(IUiListener iUiListener) {
        Bundle bundleA = m2004a();
        bundleA.putString("reqType", "checkLogin");
        HttpUtils.requestAsync(this.f3024b, C2084d.m2215a(), "https://openmobile.qq.com/v3/user/get_info", bundleA, "GET", new BaseApi.TempRequestListener(new C2026a(iUiListener)));
    }

    /* JADX INFO: renamed from: com.tencent.connect.auth.AuthAgent$a */
    /* JADX INFO: compiled from: ProGuard */
    private class C2026a implements IUiListener {

        /* JADX INFO: renamed from: a */
        IUiListener f2950a;

        public C2026a(IUiListener iUiListener) {
            this.f2950a = iUiListener;
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            if (obj == null) {
                C2061f.m2135e("openSDK_LOG.AuthAgent", "CheckLoginListener response data is null");
                return;
            }
            JSONObject jSONObject = (JSONObject) obj;
            try {
                int i = jSONObject.getInt("ret");
                String string = i == 0 ? "success" : jSONObject.getString("msg");
                if (this.f2950a != null) {
                    this.f2950a.onComplete(new JSONObject().put("ret", i).put("msg", string));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                C2061f.m2135e("openSDK_LOG.AuthAgent", "CheckLoginListener response data format error");
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            IUiListener iUiListener = this.f2950a;
            if (iUiListener != null) {
                iUiListener.onError(uiError);
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            IUiListener iUiListener = this.f2950a;
            if (iUiListener != null) {
                iUiListener.onCancel();
            }
        }
    }

    /* JADX INFO: renamed from: com.tencent.connect.auth.AuthAgent$b */
    /* JADX INFO: compiled from: ProGuard */
    private class C2027b implements IUiListener {

        /* JADX INFO: renamed from: a */
        IUiListener f2952a;

        /* JADX INFO: renamed from: c */
        private final String f2954c = "sendinstall";

        /* JADX INFO: renamed from: d */
        private final String f2955d = "installwording";

        /* JADX INFO: renamed from: e */
        private final String f2956e = "http://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi";

        public C2027b(IUiListener iUiListener) {
            this.f2952a = iUiListener;
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            JSONObject jSONObject;
            String string;
            if (obj == null || (jSONObject = (JSONObject) obj) == null) {
                return;
            }
            try {
                z = jSONObject.getInt("sendinstall") == 1;
                string = jSONObject.getString("installwording");
            } catch (JSONException unused) {
                C2061f.m2134d("openSDK_LOG.AuthAgent", "FeedConfirmListener onComplete There is no value for sendinstall.");
                string = "";
            }
            String strDecode = URLDecoder.decode(string);
            C2061f.m2127a("openSDK_LOG.AuthAgent", " WORDING = " + strDecode + "xx");
            if (z && !TextUtils.isEmpty(strDecode)) {
                m1950a(strDecode, this.f2952a, obj);
                return;
            }
            IUiListener iUiListener = this.f2952a;
            if (iUiListener != null) {
                iUiListener.onComplete(obj);
            }
        }

        /* JADX INFO: renamed from: com.tencent.connect.auth.AuthAgent$b$a */
        /* JADX INFO: compiled from: ProGuard */
        private abstract class a implements View.OnClickListener {

            /* JADX INFO: renamed from: d */
            Dialog f2966d;

            a(Dialog dialog) {
                this.f2966d = dialog;
            }
        }

        /* JADX INFO: renamed from: a */
        private void m1950a(String str, final IUiListener iUiListener, final Object obj) {
            PackageInfo packageInfo;
            Activity activity = (Activity) AuthAgent.this.f2944e.get();
            if (activity == null) {
                return;
            }
            Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(1);
            PackageManager packageManager = activity.getPackageManager();
            try {
                packageInfo = packageManager.getPackageInfo(activity.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                packageInfo = null;
            }
            Drawable drawableLoadIcon = packageInfo != null ? packageInfo.applicationInfo.loadIcon(packageManager) : null;
            View.OnClickListener onClickListener = new a(dialog) { // from class: com.tencent.connect.auth.AuthAgent.b.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    C2027b.this.m1951a();
                    if (this.f2966d != null && this.f2966d.isShowing()) {
                        this.f2966d.dismiss();
                    }
                    IUiListener iUiListener2 = iUiListener;
                    if (iUiListener2 != null) {
                        iUiListener2.onComplete(obj);
                    }
                }
            };
            View.OnClickListener onClickListener2 = new a(dialog) { // from class: com.tencent.connect.auth.AuthAgent.b.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (this.f2966d != null && this.f2966d.isShowing()) {
                        this.f2966d.dismiss();
                    }
                    IUiListener iUiListener2 = iUiListener;
                    if (iUiListener2 != null) {
                        iUiListener2.onComplete(obj);
                    }
                }
            };
            ColorDrawable colorDrawable = new ColorDrawable();
            colorDrawable.setAlpha(0);
            dialog.getWindow().setBackgroundDrawable(colorDrawable);
            dialog.setContentView(m1949a(activity, drawableLoadIcon, str, onClickListener, onClickListener2));
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.tencent.connect.auth.AuthAgent.b.3
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    IUiListener iUiListener2 = iUiListener;
                    if (iUiListener2 != null) {
                        iUiListener2.onComplete(obj);
                    }
                }
            });
            if (activity == null || activity.isFinishing()) {
                return;
            }
            dialog.show();
        }

        /* JADX INFO: renamed from: a */
        private Drawable m1948a(String str, Context context) {
            InputStream inputStreamOpen;
            Bitmap bitmapDecodeStream;
            Drawable drawableCreateFromStream = null;
            try {
                inputStreamOpen = context.getApplicationContext().getAssets().open(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (inputStreamOpen == null) {
                return null;
            }
            if (str.endsWith(".9.png")) {
                try {
                    bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpen);
                } catch (OutOfMemoryError e2) {
                    e2.printStackTrace();
                    bitmapDecodeStream = null;
                }
                if (bitmapDecodeStream == null) {
                    return null;
                }
                byte[] ninePatchChunk = bitmapDecodeStream.getNinePatchChunk();
                NinePatch.isNinePatchChunk(ninePatchChunk);
                return new NinePatchDrawable(bitmapDecodeStream, ninePatchChunk, new Rect(), null);
            }
            drawableCreateFromStream = Drawable.createFromStream(inputStreamOpen, str);
            inputStreamOpen.close();
            return drawableCreateFromStream;
        }

        /* JADX INFO: renamed from: a */
        private View m1949a(Context context, Drawable drawable, String str, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.density;
            RelativeLayout relativeLayout = new RelativeLayout(context);
            ImageView imageView = new ImageView(context);
            imageView.setImageDrawable(drawable);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setId(1);
            int i = (int) (60.0f * f);
            int i2 = (int) (f * 14.0f);
            int i3 = (int) (18.0f * f);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i);
            layoutParams.addRule(9);
            layoutParams.setMargins(0, i3, (int) (6.0f * f), i3);
            relativeLayout.addView(imageView, layoutParams);
            TextView textView = new TextView(context);
            textView.setText(str);
            textView.setTextSize(14.0f);
            textView.setGravity(3);
            textView.setIncludeFontPadding(false);
            textView.setPadding(0, 0, 0, 0);
            textView.setLines(2);
            textView.setId(5);
            textView.setMinWidth((int) (185.0f * f));
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(1, 1);
            layoutParams2.addRule(6, 1);
            float f2 = 5.0f * f;
            layoutParams2.setMargins(0, 0, (int) f2, 0);
            relativeLayout.addView(textView, layoutParams2);
            View view = new View(context);
            view.setBackgroundColor(Color.rgb(214, 214, 214));
            view.setId(3);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, 2);
            layoutParams3.addRule(3, 1);
            layoutParams3.addRule(5, 1);
            layoutParams3.addRule(7, 5);
            int i4 = (int) (12.0f * f);
            layoutParams3.setMargins(0, 0, 0, i4);
            relativeLayout.addView(view, layoutParams3);
            LinearLayout linearLayout = new LinearLayout(context);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams4.addRule(5, 1);
            layoutParams4.addRule(7, 5);
            layoutParams4.addRule(3, 3);
            Button button = new Button(context);
            button.setText("跳过");
            button.setBackgroundDrawable(m1948a("buttonNegt.png", context));
            button.setTextColor(Color.rgb(36, 97, 131));
            button.setTextSize(20.0f);
            button.setOnClickListener(onClickListener2);
            button.setId(4);
            int i5 = (int) (45.0f * f);
            LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(0, i5);
            layoutParams5.rightMargin = i2;
            int i6 = (int) (4.0f * f);
            layoutParams5.leftMargin = i6;
            layoutParams5.weight = 1.0f;
            linearLayout.addView(button, layoutParams5);
            Button button2 = new Button(context);
            button2.setText("确定");
            button2.setTextSize(20.0f);
            button2.setTextColor(Color.rgb(255, 255, 255));
            button2.setBackgroundDrawable(m1948a("buttonPost.png", context));
            button2.setOnClickListener(onClickListener);
            LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(0, i5);
            layoutParams6.weight = 1.0f;
            layoutParams6.rightMargin = i6;
            linearLayout.addView(button2, layoutParams6);
            relativeLayout.addView(linearLayout, layoutParams4);
            ViewGroup.LayoutParams layoutParams7 = new FrameLayout.LayoutParams((int) (279.0f * f), (int) (f * 163.0f));
            relativeLayout.setPadding(i2, 0, i4, i4);
            relativeLayout.setLayoutParams(layoutParams7);
            relativeLayout.setBackgroundColor(Color.rgb(TelnetCommand.f3467EC, 251, TelnetCommand.f3467EC));
            PaintDrawable paintDrawable = new PaintDrawable(Color.rgb(TelnetCommand.f3467EC, 251, TelnetCommand.f3467EC));
            paintDrawable.setCornerRadius(f2);
            relativeLayout.setBackgroundDrawable(paintDrawable);
            return relativeLayout;
        }

        /* JADX INFO: renamed from: a */
        protected void m1951a() {
            Bundle bundleB = AuthAgent.this.m2012b();
            Activity activity = (Activity) AuthAgent.this.f2944e.get();
            if (activity != null) {
                HttpUtils.requestAsync(AuthAgent.this.f3024b, activity, "http://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi", bundleB, Constants.HTTP_POST, null);
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            IUiListener iUiListener = this.f2952a;
            if (iUiListener != null) {
                iUiListener.onError(uiError);
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            IUiListener iUiListener = this.f2952a;
            if (iUiListener != null) {
                iUiListener.onCancel();
            }
        }
    }
}
