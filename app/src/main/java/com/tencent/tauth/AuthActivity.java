package com.tencent.tauth;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.connect.common.AssistActivity;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.utils.C2087g;
import com.tencent.open.utils.C2089i;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class AuthActivity extends Activity {
    public static final String ACTION_KEY = "action";
    public static final String ACTION_SHARE_PRIZE = "sharePrize";

    /* JADX INFO: renamed from: a */
    private static int f3308a;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() == null) {
            C2061f.m2134d("openSDK_LOG.AuthActivity", "-->onCreate, getIntent() return null");
            finish();
            return;
        }
        Uri data = null;
        try {
            data = getIntent().getData();
        } catch (Exception e) {
            C2061f.m2135e("openSDK_LOG.AuthActivity", "-->onCreate, getIntent().getData() has exception! " + e.getMessage());
        }
        C2061f.m2127a("openSDK_LOG.AuthActivity", "-->onCreate, uri: " + data);
        m2286a(data);
    }

    /* JADX INFO: renamed from: a */
    private void m2286a(Uri uri) {
        C2061f.m2133c("openSDK_LOG.AuthActivity", "-->handleActionUri--start");
        if (uri != null && uri.toString() != null) {
            String string = "";
            if (!uri.toString().equals("")) {
                String string2 = uri.toString();
                Bundle bundleM2252a = C2089i.m2252a(string2.substring(string2.indexOf("#") + 1));
                if (bundleM2252a == null) {
                    C2061f.m2134d("openSDK_LOG.AuthActivity", "-->handleActionUri, bundle is null");
                    finish();
                    return;
                }
                String string3 = bundleM2252a.getString(ACTION_KEY);
                C2061f.m2133c("openSDK_LOG.AuthActivity", "-->handleActionUri, action: " + string3);
                if (string3 == null) {
                    finish();
                    return;
                }
                if (string3.equals("shareToQQ") || string3.equals("shareToQzone") || string3.equals("sendToMyComputer") || string3.equals("shareToTroopBar")) {
                    if (string3.equals("shareToQzone") && C2087g.m2239a(this, "com.tencent.mobileqq") != null && C2087g.m2245c(this, "5.2.0") < 0) {
                        int i = f3308a + 1;
                        f3308a = i;
                        if (i == 2) {
                            f3308a = 0;
                            finish();
                            return;
                        }
                    }
                    C2061f.m2133c("openSDK_LOG.AuthActivity", "-->handleActionUri, most share action, start assistactivity");
                    Intent intent = new Intent(this, (Class<?>) AssistActivity.class);
                    intent.putExtras(bundleM2252a);
                    intent.setFlags(603979776);
                    startActivity(intent);
                    finish();
                    return;
                }
                if (string3.equals("addToQQFavorites")) {
                    Intent intent2 = getIntent();
                    intent2.putExtras(bundleM2252a);
                    intent2.putExtra(Constants.KEY_ACTION, "action_share");
                    IUiListener listnerWithAction = UIListenerManager.getInstance().getListnerWithAction(string3);
                    if (listnerWithAction != null) {
                        UIListenerManager.getInstance().handleDataToListener(intent2, listnerWithAction);
                    }
                    finish();
                    return;
                }
                if (string3.equals(ACTION_SHARE_PRIZE)) {
                    Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
                    try {
                        string = C2089i.m2271d(bundleM2252a.getString("response")).getString("activityid");
                    } catch (Exception e) {
                        C2061f.m2131b("openSDK_LOG.AuthActivity", "sharePrize parseJson has exception.", e);
                    }
                    if (!TextUtils.isEmpty(string)) {
                        launchIntentForPackage.putExtra(ACTION_SHARE_PRIZE, true);
                        Bundle bundle = new Bundle();
                        bundle.putString("activityid", string);
                        launchIntentForPackage.putExtras(bundle);
                    }
                    startActivity(launchIntentForPackage);
                    finish();
                    return;
                }
                finish();
                return;
            }
        }
        C2061f.m2134d("openSDK_LOG.AuthActivity", "-->handleActionUri, uri invalid");
        finish();
    }
}
