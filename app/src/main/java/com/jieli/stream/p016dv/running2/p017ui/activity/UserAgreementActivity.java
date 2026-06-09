package com.jieli.stream.p016dv.running2.p017ui.activity;

import android.os.Bundle;
import android.webkit.WebView;
import com.jieli.stream.p016dv.running2.p017ui.base.BaseActivity;

/* JADX INFO: loaded from: classes.dex */
public class UserAgreementActivity extends BaseActivity {
    private final String USER_PROTOCOL = "http://cam.jieli.net:28111/app/app.user.service.protocol.html";

    @Override // com.jieli.stream.p016dv.running2.p017ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WebView webView = new WebView(this);
        webView.loadUrl("http://cam.jieli.net:28111/app/app.user.service.protocol.html");
        setContentView(webView);
    }
}
