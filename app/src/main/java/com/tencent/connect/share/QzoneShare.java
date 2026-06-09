package com.tencent.connect.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.connect.p022a.C2024a;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.p027b.C2069d;
import com.tencent.open.utils.C2084d;
import com.tencent.open.utils.C2089i;
import com.tencent.tauth.IUiListener;
import java.net.URLEncoder;
import java.util.ArrayList;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class QzoneShare extends BaseApi {
    public static final String SHARE_TO_QQ_APP_NAME = "appName";
    public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
    public static final String SHARE_TO_QQ_EXT_INT = "cflag";
    public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
    public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
    public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
    public static final String SHARE_TO_QQ_SITE = "site";
    public static final String SHARE_TO_QQ_SUMMARY = "summary";
    public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
    public static final String SHARE_TO_QQ_TITLE = "title";
    public static final String SHARE_TO_QZONE_KEY_TYPE = "req_type";
    public static final int SHARE_TO_QZONE_TYPE_APP = 6;
    public static final int SHARE_TO_QZONE_TYPE_IMAGE = 5;
    public static final int SHARE_TO_QZONE_TYPE_IMAGE_TEXT = 1;
    public static final int SHARE_TO_QZONE_TYPE_NO_TYPE = 0;

    /* JADX INFO: renamed from: c */
    private boolean f3052c;

    /* JADX INFO: renamed from: d */
    private boolean f3053d;

    /* JADX INFO: renamed from: e */
    private boolean f3054e;

    /* JADX INFO: renamed from: f */
    private boolean f3055f;
    public String mViaShareQzoneType;

    @Override // com.tencent.connect.common.BaseApi
    public void releaseResource() {
    }

    public QzoneShare(Context context, QQToken qQToken) {
        super(qQToken);
        this.mViaShareQzoneType = "";
        this.f3052c = true;
        this.f3053d = false;
        this.f3054e = false;
        this.f3055f = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0353  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0397  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x02e7 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void shareToQzone(final android.app.Activity r29, final android.os.Bundle r30, final com.tencent.tauth.IUiListener r31) {
        /*
            Method dump skipped, instruction units count: 1092
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.share.QzoneShare.shareToQzone(android.app.Activity, android.os.Bundle, com.tencent.tauth.IUiListener):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public void m2026b(Activity activity, Bundle bundle, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.QzoneShare", "doshareToQzone() --start");
        StringBuffer stringBuffer = new StringBuffer("mqqapi://share/to_qzone?src_type=app&version=1&file_type=news");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("imageUrl");
        String string = bundle.getString("title");
        String string2 = bundle.getString("summary");
        String string3 = bundle.getString("targetUrl");
        String string4 = bundle.getString("audio_url");
        int i = bundle.getInt("req_type", 1);
        String string5 = bundle.getString("appName");
        int i2 = bundle.getInt("cflag", 0);
        String string6 = bundle.getString("share_qq_ext_str");
        String appId = this.f3024b.getAppId();
        String openId = this.f3024b.getOpenId();
        C2061f.m2127a("openSDK_LOG.QzoneShare", "openId:" + openId);
        if (stringArrayList != null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            int size = stringArrayList.size() <= 9 ? stringArrayList.size() : 9;
            int i3 = 0;
            while (i3 < size) {
                ArrayList<String> arrayList = stringArrayList;
                stringBuffer2.append(URLEncoder.encode(stringArrayList.get(i3)));
                if (i3 != size - 1) {
                    stringBuffer2.append(";");
                }
                i3++;
                stringArrayList = arrayList;
            }
            stringBuffer.append("&image_url=" + Base64.encodeToString(C2089i.m2280i(stringBuffer2.toString()), 2));
        }
        if (!TextUtils.isEmpty(string)) {
            stringBuffer.append("&title=" + Base64.encodeToString(C2089i.m2280i(string), 2));
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append("&description=" + Base64.encodeToString(C2089i.m2280i(string2), 2));
        }
        if (!TextUtils.isEmpty(appId)) {
            stringBuffer.append("&share_id=" + appId);
        }
        if (!TextUtils.isEmpty(string3)) {
            stringBuffer.append("&url=" + Base64.encodeToString(C2089i.m2280i(string3), 2));
        }
        if (!TextUtils.isEmpty(string5)) {
            stringBuffer.append("&app_name=" + Base64.encodeToString(C2089i.m2280i(string5), 2));
        }
        if (!C2089i.m2274e(openId)) {
            stringBuffer.append("&open_id=" + Base64.encodeToString(C2089i.m2280i(openId), 2));
        }
        if (!C2089i.m2274e(string4)) {
            stringBuffer.append("&audioUrl=" + Base64.encodeToString(C2089i.m2280i(string4), 2));
        }
        stringBuffer.append("&req_type=" + Base64.encodeToString(C2089i.m2280i(String.valueOf(i)), 2));
        if (!C2089i.m2274e(string6)) {
            stringBuffer.append("&share_qq_ext_str=" + Base64.encodeToString(C2089i.m2280i(string6), 2));
        }
        stringBuffer.append("&cflag=" + Base64.encodeToString(C2089i.m2280i(String.valueOf(i2)), 2));
        C2061f.m2127a("openSDK_LOG.QzoneShare", "doshareToQzone, url: " + stringBuffer.toString());
        C2024a.m1931a(C2084d.m2215a(), this.f3024b, "requireApi", "shareToNativeQQ");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(stringBuffer.toString()));
        intent.putExtra("pkg_name", activity.getPackageName());
        if (C2089i.m2277f(activity, "4.6.0")) {
            if (m2010a(intent)) {
                UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_OLD_QZSHARE, iUiListener);
                m2007a(activity, intent, Constants.REQUEST_OLD_QZSHARE);
            }
            C2061f.m2133c("openSDK_LOG.QzoneShare", "doShareToQzone() -- QQ Version is < 4.6.0");
        } else {
            C2061f.m2133c("openSDK_LOG.QzoneShare", "doShareToQzone() -- QQ Version is > 4.6.0");
            if (UIListenerManager.getInstance().setListnerWithAction("shareToQzone", iUiListener) != null) {
                C2061f.m2133c("openSDK_LOG.QzoneShare", "doShareToQzone() -- do listener onCancel()");
            }
            if (m2010a(intent)) {
                m2006a(activity, Constants.REQUEST_QZONE_SHARE, intent, false);
            }
        }
        if (m2010a(intent)) {
            C2069d.m2162a().m2165a(this.f3024b.getOpenId(), this.f3024b.getAppId(), Constants.VIA_SHARE_TO_QZONE, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, "3", "0", this.mViaShareQzoneType, "0", "1", "0");
            C2069d.m2162a().m2163a(0, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f3024b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "");
        } else {
            C2069d.m2162a().m2165a(this.f3024b.getOpenId(), this.f3024b.getAppId(), Constants.VIA_SHARE_TO_QZONE, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, "3", "1", this.mViaShareQzoneType, "0", "1", "0");
            C2069d.m2162a().m2163a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f3024b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent fail");
        }
        C2061f.m2133c("openSDK_LOG", "doShareToQzone() --end");
    }
}
