package com.tencent.open.p028c;

import android.content.Context;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.web.security.C2092a;
import com.tencent.open.web.security.SecureJsInterface;

/* JADX INFO: renamed from: com.tencent.open.c.c */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2076c extends C2075b {

    /* JADX INFO: renamed from: a */
    public static boolean f3245a;

    /* JADX INFO: renamed from: b */
    private KeyEvent f3246b;

    /* JADX INFO: renamed from: c */
    private C2092a f3247c;

    public C2076c(Context context) {
        super(context);
    }

    @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int unicodeChar;
        C2061f.m2130b("openSDK_LOG.SecureWebView", "-->dispatchKeyEvent, is device support: " + f3245a);
        if (!f3245a) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 4) {
                return super.dispatchKeyEvent(keyEvent);
            }
            if (keyCode == 66) {
                return super.dispatchKeyEvent(keyEvent);
            }
            if (keyCode == 67) {
                C2092a.f3305b = true;
                return super.dispatchKeyEvent(keyEvent);
            }
            if (keyEvent.getUnicodeChar() == 0) {
                return super.dispatchKeyEvent(keyEvent);
            }
            if (SecureJsInterface.isPWDEdit && (((unicodeChar = keyEvent.getUnicodeChar()) >= 33 && unicodeChar <= 95) || (unicodeChar >= 97 && unicodeChar <= 125))) {
                KeyEvent keyEvent2 = new KeyEvent(0, 17);
                this.f3246b = keyEvent2;
                return super.dispatchKeyEvent(keyEvent2);
            }
            return super.dispatchKeyEvent(keyEvent);
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.webkit.WebView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        int unicodeChar;
        C2061f.m2130b("openSDK_LOG.SecureWebView", "-->onKeyDown, is device support: " + f3245a);
        if (!f3245a) {
            return super.onKeyDown(i, keyEvent);
        }
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 4) {
                return super.onKeyDown(i, keyEvent);
            }
            if (keyCode == 66) {
                return super.onKeyDown(i, keyEvent);
            }
            if (keyCode == 67) {
                C2092a.f3305b = true;
                return super.onKeyDown(i, keyEvent);
            }
            if (keyEvent.getUnicodeChar() == 0) {
                return super.onKeyDown(i, keyEvent);
            }
            if (SecureJsInterface.isPWDEdit && (((unicodeChar = keyEvent.getUnicodeChar()) >= 33 && unicodeChar <= 95) || (unicodeChar >= 97 && unicodeChar <= 125))) {
                KeyEvent keyEvent2 = new KeyEvent(0, 17);
                this.f3246b = keyEvent2;
                return super.onKeyDown(keyEvent2.getKeyCode(), this.f3246b);
            }
            return super.onKeyDown(i, keyEvent);
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.webkit.WebView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        C2061f.m2133c("openSDK_LOG.SecureWebView", "-->create input connection, is edit: " + SecureJsInterface.isPWDEdit);
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        C2061f.m2127a("openSDK_LOG.SecureWebView", "-->onCreateInputConnection, inputConn is " + inputConnectionOnCreateInputConnection);
        if (inputConnectionOnCreateInputConnection != null) {
            f3245a = true;
            C2092a c2092a = new C2092a(super.onCreateInputConnection(editorInfo), false);
            this.f3247c = c2092a;
            return c2092a;
        }
        f3245a = false;
        return inputConnectionOnCreateInputConnection;
    }
}
