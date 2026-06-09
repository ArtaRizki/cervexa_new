package com.tencent.open.web.security;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import com.tencent.open.p026a.C2061f;

/* JADX INFO: renamed from: com.tencent.open.web.security.a */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class C2092a extends InputConnectionWrapper {

    /* JADX INFO: renamed from: a */
    public static String f3304a = null;

    /* JADX INFO: renamed from: b */
    public static boolean f3305b = false;

    /* JADX INFO: renamed from: c */
    public static boolean f3306c = false;

    public C2092a(InputConnection inputConnection, boolean z) {
        super(inputConnection, z);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean setComposingText(CharSequence charSequence, int i) {
        f3306c = true;
        f3304a = charSequence.toString();
        C2061f.m2127a("openSDK_LOG.CaptureInputConnection", "-->setComposingText: " + charSequence.toString());
        return super.setComposingText(charSequence, i);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean commitText(CharSequence charSequence, int i) {
        f3306c = true;
        f3304a = charSequence.toString();
        C2061f.m2127a("openSDK_LOG.CaptureInputConnection", "-->commitText: " + charSequence.toString());
        return super.commitText(charSequence, i);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean sendKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            C2061f.m2133c("openSDK_LOG.CaptureInputConnection", "sendKeyEvent");
            f3304a = String.valueOf((char) keyEvent.getUnicodeChar());
            f3306c = true;
            C2061f.m2130b("openSDK_LOG.CaptureInputConnection", "s: " + f3304a);
        }
        C2061f.m2130b("openSDK_LOG.CaptureInputConnection", "-->sendKeyEvent: " + f3304a);
        return super.sendKeyEvent(keyEvent);
    }
}
