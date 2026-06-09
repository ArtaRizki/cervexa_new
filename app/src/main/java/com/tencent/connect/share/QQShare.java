package com.tencent.connect.share;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.TDialog;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.p027b.C2069d;
import com.tencent.open.utils.C2082b;
import com.tencent.open.utils.C2089i;
import com.tencent.open.utils.InterfaceC2083c;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.File;
import java.util.ArrayList;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class QQShare extends BaseApi {
    public static final int QQ_SHARE_SUMMARY_MAX_LENGTH = 60;
    public static final int QQ_SHARE_TITLE_MAX_LENGTH = 45;
    public static final String SHARE_TO_QQ_APP_NAME = "appName";
    public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
    public static final String SHARE_TO_QQ_EXT_INT = "cflag";
    public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
    public static final int SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN = 1;
    public static final int SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE = 2;
    public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
    public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
    public static final String SHARE_TO_QQ_KEY_TYPE = "req_type";
    public static final String SHARE_TO_QQ_SITE = "site";
    public static final String SHARE_TO_QQ_SUMMARY = "summary";
    public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
    public static final String SHARE_TO_QQ_TITLE = "title";
    public static final int SHARE_TO_QQ_TYPE_APP = 6;
    public static final int SHARE_TO_QQ_TYPE_AUDIO = 2;
    public static final int SHARE_TO_QQ_TYPE_DEFAULT = 1;
    public static final int SHARE_TO_QQ_TYPE_IMAGE = 5;
    public String mViaShareQQType;

    @Override // com.tencent.connect.common.BaseApi
    public void releaseResource() {
    }

    public QQShare(Context context, QQToken qQToken) {
        super(qQToken);
        this.mViaShareQQType = "";
    }

    public void shareToQQ(Activity activity, Bundle bundle, IUiListener iUiListener) {
        String str;
        C2061f.m2133c("openSDK_LOG.QQShare", "shareToQQ() -- start.");
        String string = bundle.getString("imageUrl");
        String string2 = bundle.getString("title");
        String string3 = bundle.getString("summary");
        String string4 = bundle.getString("targetUrl");
        String string5 = bundle.getString("imageLocalUrl");
        int i = bundle.getInt("req_type", 1);
        C2061f.m2133c("openSDK_LOG.QQShare", "shareToQQ -- type: " + i);
        if (i == 1) {
            this.mViaShareQQType = "1";
        } else if (i == 2) {
            this.mViaShareQQType = "3";
        } else if (i == 5) {
            this.mViaShareQQType = "2";
        } else if (i == 6) {
            this.mViaShareQQType = "4";
        }
        if (i == 6) {
            if (C2089i.m2277f(activity, "5.0.0")) {
                iUiListener.onError(new UiError(-15, Constants.MSG_PARAM_APPSHARE_TOO_LOW, null));
                C2061f.m2135e("openSDK_LOG.QQShare", "shareToQQ, app share is not support below qq5.0.");
                C2069d.m2162a().m2163a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f3024b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, app share is not support below qq5.0.");
                return;
            }
            string4 = String.format("http://fusion.qq.com/cgi-bin/qzapps/unified_jump?appid=%1$s&from=%2$s&isOpenAppID=1", this.f3024b.getAppId(), "mqq");
            bundle.putString("targetUrl", string4);
        }
        if (!C2089i.m2265b() && C2089i.m2277f(activity, "4.5.0")) {
            iUiListener.onError(new UiError(-6, Constants.MSG_SHARE_NOSD_ERROR, null));
            C2061f.m2135e("openSDK_LOG.QQShare", "shareToQQ sdcard is null--end");
            C2069d.m2162a().m2163a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f3024b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ sdcard is null");
            return;
        }
        if (i == 5) {
            if (C2089i.m2277f(activity, "4.3.0")) {
                iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_QQ_VERSION_ERROR, null));
                C2061f.m2135e("openSDK_LOG.QQShare", "shareToQQ, version below 4.3 is not support.");
                C2069d.m2162a().m2163a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f3024b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, version below 4.3 is not support.");
                return;
            } else if (!C2089i.m2279h(string5)) {
                iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, null));
                C2061f.m2135e("openSDK_LOG.QQShare", "shareToQQ -- error: 非法的图片地址!");
                C2069d.m2162a().m2163a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f3024b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR);
                return;
            }
        }
        if (i != 5) {
            if (TextUtils.isEmpty(string4) || (!string4.startsWith("http://") && !string4.startsWith("https://"))) {
                iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_ERROR, null));
                C2061f.m2135e("openSDK_LOG.QQShare", "shareToQQ, targetUrl is empty or illegal..");
                C2069d.m2162a().m2163a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f3024b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, targetUrl is empty or illegal..");
                return;
            } else if (TextUtils.isEmpty(string2)) {
                iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_TITLE_NULL_ERROR, null));
                C2061f.m2135e("openSDK_LOG.QQShare", "shareToQQ, title is empty.");
                C2069d.m2162a().m2163a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f3024b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, title is empty.");
                return;
            }
        }
        if (!TextUtils.isEmpty(string) && !string.startsWith("http://") && !string.startsWith("https://") && !new File(string).exists()) {
            iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, null));
            C2061f.m2135e("openSDK_LOG.QQShare", "shareToQQ, image url is emprty or illegal.");
            C2069d.m2162a().m2163a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f3024b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "shareToQQ, image url is emprty or illegal.");
            return;
        }
        if (TextUtils.isEmpty(string2) || string2.length() <= 45) {
            str = null;
        } else {
            str = null;
            bundle.putString("title", C2089i.m2256a(string2, 45, (String) null, (String) null));
        }
        if (!TextUtils.isEmpty(string3) && string3.length() > 60) {
            bundle.putString("summary", C2089i.m2256a(string3, 60, str, str));
        }
        if (C2089i.m2260a(activity)) {
            C2061f.m2133c("openSDK_LOG.QQShare", "shareToQQ, support share");
            m2019b(activity, bundle, iUiListener);
        } else {
            try {
                C2061f.m2134d("openSDK_LOG.QQShare", "shareToQQ, don't support share, will show download dialog");
                new TDialog(activity, "", m2005a(""), null, this.f3024b).show();
            } catch (RuntimeException e) {
                C2061f.m2131b("openSDK_LOG.QQShare", " shareToQQ, TDialog.show not in main thread", e);
                e.printStackTrace();
                iUiListener.onError(new UiError(-6, Constants.MSG_NOT_CALL_ON_MAIN_THREAD, null));
            }
        }
        C2061f.m2133c("openSDK_LOG.QQShare", "shareToQQ() -- end.");
    }

    /* JADX INFO: renamed from: b */
    private void m2019b(final Activity activity, final Bundle bundle, final IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.QQShare", "shareToMobileQQ() -- start.");
        String string = bundle.getString("imageUrl");
        final String string2 = bundle.getString("title");
        final String string3 = bundle.getString("summary");
        C2061f.m2127a("openSDK_LOG.QQShare", "shareToMobileQQ -- imageUrl: " + string);
        if (!TextUtils.isEmpty(string)) {
            if (C2089i.m2278g(string)) {
                if (TextUtils.isEmpty(string2) && TextUtils.isEmpty(string3)) {
                    if (iUiListener != null) {
                        iUiListener.onError(new UiError(-6, Constants.MSG_SHARE_NOSD_ERROR, null));
                        C2061f.m2135e("openSDK_LOG.QQShare", Constants.MSG_SHARE_NOSD_ERROR);
                    }
                    C2069d.m2162a().m2163a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f3024b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_SHARE_NOSD_ERROR);
                    return;
                }
                if (!C2089i.m2277f(activity, "4.3.0")) {
                    m2020c(activity, bundle, iUiListener);
                } else {
                    new C2082b(activity).m2213a(string, new InterfaceC2083c() { // from class: com.tencent.connect.share.QQShare.1
                        @Override // com.tencent.open.utils.InterfaceC2083c
                        /* JADX INFO: renamed from: a */
                        public void mo2022a(int i, ArrayList<String> arrayList) {
                        }

                        @Override // com.tencent.open.utils.InterfaceC2083c
                        /* JADX INFO: renamed from: a */
                        public void mo2021a(int i, String str) {
                            if (i == 0) {
                                bundle.putString("imageLocalUrl", str);
                            } else if (TextUtils.isEmpty(string2) && TextUtils.isEmpty(string3)) {
                                IUiListener iUiListener2 = iUiListener;
                                if (iUiListener2 != null) {
                                    iUiListener2.onError(new UiError(-6, Constants.MSG_SHARE_GETIMG_ERROR, null));
                                    C2061f.m2135e("openSDK_LOG.QQShare", "shareToMobileQQ -- error: 获取分享图片失败!");
                                }
                                C2069d.m2162a().m2163a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, QQShare.this.f3024b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_SHARE_GETIMG_ERROR);
                                return;
                            }
                            QQShare.this.m2020c(activity, bundle, iUiListener);
                        }
                    });
                }
            } else {
                bundle.putString("imageUrl", null);
                if (C2089i.m2277f(activity, "4.3.0")) {
                    C2061f.m2130b("openSDK_LOG.QQShare", "shareToMobileQQ -- QQ Version is < 4.3.0 ");
                    m2020c(activity, bundle, iUiListener);
                } else {
                    C2061f.m2130b("openSDK_LOG.QQShare", "shareToMobileQQ -- QQ Version is > 4.3.0 ");
                    C2039a.m2031a(activity, string, new InterfaceC2083c() { // from class: com.tencent.connect.share.QQShare.2
                        @Override // com.tencent.open.utils.InterfaceC2083c
                        /* JADX INFO: renamed from: a */
                        public void mo2022a(int i, ArrayList<String> arrayList) {
                        }

                        @Override // com.tencent.open.utils.InterfaceC2083c
                        /* JADX INFO: renamed from: a */
                        public void mo2021a(int i, String str) {
                            if (i == 0) {
                                bundle.putString("imageLocalUrl", str);
                            } else if (TextUtils.isEmpty(string2) && TextUtils.isEmpty(string3)) {
                                IUiListener iUiListener2 = iUiListener;
                                if (iUiListener2 != null) {
                                    iUiListener2.onError(new UiError(-6, Constants.MSG_SHARE_GETIMG_ERROR, null));
                                    C2061f.m2135e("openSDK_LOG.QQShare", "shareToMobileQQ -- error: 获取分享图片失败!");
                                }
                                C2069d.m2162a().m2163a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, QQShare.this.f3024b.getAppId(), String.valueOf(0), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_SHARE_GETIMG_ERROR);
                                return;
                            }
                            QQShare.this.m2020c(activity, bundle, iUiListener);
                        }
                    });
                }
            }
        } else {
            m2020c(activity, bundle, iUiListener);
        }
        C2061f.m2133c("openSDK_LOG.QQShare", "shareToMobileQQ() -- end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:51:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0315  */
    /* JADX INFO: renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m2020c(android.app.Activity r28, android.os.Bundle r29, com.tencent.tauth.IUiListener r30) {
        /*
            Method dump skipped, instruction units count: 866
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.share.QQShare.m2020c(android.app.Activity, android.os.Bundle, com.tencent.tauth.IUiListener):void");
    }
}
