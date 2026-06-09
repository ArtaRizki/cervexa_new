package com.tencent.open;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import com.tencent.open.p026a.C2061f;

/* JADX INFO: renamed from: com.tencent.open.b */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractDialogC2065b extends Dialog {

    /* JADX INFO: renamed from: a */
    protected C2055a f3183a;

    /* JADX INFO: renamed from: b */
    protected final WebChromeClient f3184b;

    /* JADX INFO: renamed from: a */
    protected abstract void mo2078a(String str);

    public AbstractDialogC2065b(Context context, int i) {
        super(context, i);
        this.f3184b = new WebChromeClient() { // from class: com.tencent.open.b.1
            @Override // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                if (consoleMessage == null) {
                    return false;
                }
                C2061f.m2133c("openSDK_LOG.JsDialog", "WebChromeClient onConsoleMessage" + consoleMessage.message() + " -- From  111 line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
                if (Build.VERSION.SDK_INT <= 7) {
                    return true;
                }
                AbstractDialogC2065b.this.mo2078a(consoleMessage == null ? "" : consoleMessage.message());
                return true;
            }

            @Override // android.webkit.WebChromeClient
            public void onConsoleMessage(String str, int i2, String str2) {
                C2061f.m2133c("openSDK_LOG.JsDialog", "WebChromeClient onConsoleMessage" + str + " -- From 222 line " + i2 + " of " + str2);
                if (Build.VERSION.SDK_INT == 7) {
                    AbstractDialogC2065b.this.mo2078a(str);
                }
            }
        };
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3183a = new C2055a();
    }
}
