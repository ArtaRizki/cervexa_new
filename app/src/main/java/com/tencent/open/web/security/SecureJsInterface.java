package com.tencent.open.web.security;

import com.tencent.open.C2055a;
import com.tencent.open.p026a.C2061f;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class SecureJsInterface extends C2055a.b {
    public static boolean isPWDEdit = false;

    /* JADX INFO: renamed from: a */
    private String f3303a;

    @Override // com.tencent.open.C2055a.b
    public boolean customCallback() {
        return true;
    }

    public void curPosFromJS(String str) {
        int i;
        C2061f.m2130b("openSDK_LOG.SecureJsInterface", "-->curPosFromJS: " + str);
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            C2061f.m2131b("openSDK_LOG.SecureJsInterface", "-->curPosFromJS number format exception.", e);
            i = -1;
        }
        if (i < 0) {
            throw new RuntimeException("position is illegal.");
        }
        boolean z = C2092a.f3306c;
        if (C2092a.f3305b) {
            if (Boolean.valueOf(JniInterface.BackSpaceChar(C2092a.f3305b, i)).booleanValue()) {
                C2092a.f3305b = false;
                return;
            }
            return;
        }
        String str2 = C2092a.f3304a;
        this.f3303a = str2;
        JniInterface.insetTextToArray(i, str2, str2.length());
        C2061f.m2127a("openSDK_LOG.SecureJsInterface", "curPosFromJS mKey: " + this.f3303a);
    }

    public void isPasswordEdit(String str) {
        int i;
        C2061f.m2133c("openSDK_LOG.SecureJsInterface", "-->is pswd edit, flag: " + str);
        try {
            i = Integer.parseInt(str);
        } catch (Exception e) {
            C2061f.m2135e("openSDK_LOG.SecureJsInterface", "-->is pswd edit exception: " + e.getMessage());
            i = -1;
        }
        if (i != 0 && i != 1) {
            throw new RuntimeException("is pswd edit flag is illegal.");
        }
        if (i == 0) {
            isPWDEdit = false;
        } else if (i == 1) {
            isPWDEdit = true;
        }
    }

    public void clearAllEdit() {
        C2061f.m2133c("openSDK_LOG.SecureJsInterface", "-->clear all edit.");
        try {
            JniInterface.clearAllPWD();
        } catch (Exception e) {
            C2061f.m2135e("openSDK_LOG.SecureJsInterface", "-->clear all edit exception: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String getMD5FromNative() {
        C2061f.m2133c("openSDK_LOG.SecureJsInterface", "-->get md5 form native");
        try {
            String pWDKeyToMD5 = JniInterface.getPWDKeyToMD5(null);
            C2061f.m2127a("openSDK_LOG.SecureJsInterface", "-->getMD5FromNative, MD5= " + pWDKeyToMD5);
            return pWDKeyToMD5;
        } catch (Exception e) {
            C2061f.m2135e("openSDK_LOG.SecureJsInterface", "-->get md5 form native exception: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
