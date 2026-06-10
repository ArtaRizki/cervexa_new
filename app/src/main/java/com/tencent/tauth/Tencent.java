package com.tencent.tauth;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
public class Tencent {
    public static Tencent createInstance(String appId, Context context) { return new Tencent(); }
    public void shareToQQ(Activity activity, android.os.Bundle params, IUiListener listener) {}
    public void shareToQzone(Activity activity, android.os.Bundle params, IUiListener listener) {}
    public static boolean onActivityResultData(int requestCode, int resultCode, Intent data, IUiListener listener) { return false; }
}
