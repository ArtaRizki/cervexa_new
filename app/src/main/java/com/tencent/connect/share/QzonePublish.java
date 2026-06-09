package com.tencent.connect.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.p022a.C2024a;
import com.tencent.open.TDialog;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.p027b.C2069d;
import com.tencent.open.utils.C2084d;
import com.tencent.open.utils.C2087g;
import com.tencent.open.utils.C2089i;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class QzonePublish extends BaseApi {
    public static final String PUBLISH_TO_QZONE_APP_NAME = "appName";
    public static final String PUBLISH_TO_QZONE_IMAGE_URL = "imageUrl";
    public static final String PUBLISH_TO_QZONE_KEY_TYPE = "req_type";
    public static final String PUBLISH_TO_QZONE_SUMMARY = "summary";
    public static final int PUBLISH_TO_QZONE_TYPE_PUBLISHMOOD = 3;
    public static final int PUBLISH_TO_QZONE_TYPE_PUBLISHVIDEO = 4;
    public static final String PUBLISH_TO_QZONE_VIDEO_DURATION = "videoDuration";
    public static final String PUBLISH_TO_QZONE_VIDEO_PATH = "videoPath";
    public static final String PUBLISH_TO_QZONE_VIDEO_SIZE = "videoSize";

    public QzonePublish(Context context, QQToken qQToken) {
        super(qQToken);
    }

    public void publishToQzone(final Activity activity, final Bundle bundle, final IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.QzonePublish", "publishToQzone() -- start");
        if (bundle == null) {
            iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_NULL_ERROR, null));
            C2061f.m2135e("openSDK_LOG.QzonePublish", "-->publishToQzone, params is null");
            C2069d.m2162a().m2163a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f3024b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, Constants.MSG_PARAM_NULL_ERROR);
            return;
        }
        if (C2087g.m2245c(activity, "5.9.5") < 0) {
            iUiListener.onError(new UiError(-15, Constants.MSG_PARAM_VERSION_TOO_LOW, null));
            C2061f.m2135e("openSDK_LOG.QzonePublish", "-->publishToQzone, this is not support below qq 5.9.5");
            C2069d.m2162a().m2163a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f3024b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "publicToQzone, this is not support below qq 5.9.5");
            new TDialog(activity, "", m2005a(""), null, this.f3024b).show();
            return;
        }
        String string = bundle.getString("summary");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("imageUrl");
        String strM2263b = C2089i.m2263b(activity);
        if (strM2263b == null) {
            strM2263b = bundle.getString("appName");
        } else if (strM2263b.length() > 20) {
            strM2263b = strM2263b.substring(0, 20) + "...";
        }
        if (!TextUtils.isEmpty(strM2263b)) {
            bundle.putString("appName", strM2263b);
        }
        bundle.putString("summary", string);
        int i = bundle.getInt("req_type");
        if (i == 3) {
            if (stringArrayList != null && stringArrayList.size() > 0) {
                for (int i2 = 0; i2 < stringArrayList.size(); i2++) {
                    if (!C2089i.m2279h(stringArrayList.get(i2))) {
                        stringArrayList.remove(i2);
                    }
                }
                bundle.putStringArrayList("imageUrl", stringArrayList);
            }
            m2024b(activity, bundle, iUiListener);
            C2061f.m2133c("openSDK_LOG.QzonePublish", "publishToQzone() --end");
            return;
        }
        if (i == 4) {
            final String string2 = bundle.getString(PUBLISH_TO_QZONE_VIDEO_PATH);
            if (!C2089i.m2279h(string2)) {
                C2061f.m2135e("openSDK_LOG.QzonePublish", "publishToQzone() video url invalid");
                iUiListener.onError(new UiError(-5, Constants.MSG_PUBLISH_VIDEO_ERROR, null));
                return;
            }
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.tencent.connect.share.QzonePublish.1
                @Override // android.media.MediaPlayer.OnPreparedListener
                public void onPrepared(MediaPlayer mediaPlayer2) {
                    long length = new File(string2).length();
                    int duration = mediaPlayer2.getDuration();
                    bundle.putString(QzonePublish.PUBLISH_TO_QZONE_VIDEO_PATH, string2);
                    bundle.putInt(QzonePublish.PUBLISH_TO_QZONE_VIDEO_DURATION, duration);
                    bundle.putLong(QzonePublish.PUBLISH_TO_QZONE_VIDEO_SIZE, length);
                    QzonePublish.this.m2024b(activity, bundle, iUiListener);
                    C2061f.m2133c("openSDK_LOG.QzonePublish", "publishToQzone() --end");
                }
            });
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.tencent.connect.share.QzonePublish.2
                @Override // android.media.MediaPlayer.OnErrorListener
                public boolean onError(MediaPlayer mediaPlayer2, int i3, int i4) {
                    C2061f.m2135e("openSDK_LOG.QzonePublish", "publishToQzone() mediaplayer onError()");
                    iUiListener.onError(new UiError(-5, Constants.MSG_PUBLISH_VIDEO_ERROR, null));
                    return false;
                }
            });
            try {
                mediaPlayer.setDataSource(string2);
                mediaPlayer.prepareAsync();
                return;
            } catch (Exception unused) {
                C2061f.m2135e("openSDK_LOG.QzonePublish", "publishToQzone() exception(s) occurred when preparing mediaplayer");
                iUiListener.onError(new UiError(-5, Constants.MSG_PUBLISH_VIDEO_ERROR, null));
                return;
            }
        }
        iUiListener.onError(new UiError(-5, Constants.MSG_SHARE_TYPE_ERROR, null));
        C2061f.m2135e("openSDK_LOG.QzonePublish", "publishToQzone() error--end请选择支持的分享类型");
        C2069d.m2162a().m2163a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f3024b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "publishToQzone() 请选择支持的分享类型");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public void m2024b(Activity activity, Bundle bundle, IUiListener iUiListener) {
        String str;
        C2061f.m2133c("openSDK_LOG.QzonePublish", "doPublishToQzone() --start");
        StringBuffer stringBuffer = new StringBuffer("mqqapi://qzone/publish?src_type=app&version=1&file_type=news");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("imageUrl");
        String string = bundle.getString("summary");
        int i = bundle.getInt("req_type", 3);
        String string2 = bundle.getString("appName");
        String string3 = bundle.getString(PUBLISH_TO_QZONE_VIDEO_PATH);
        int i2 = bundle.getInt(PUBLISH_TO_QZONE_VIDEO_DURATION);
        long j = bundle.getLong(PUBLISH_TO_QZONE_VIDEO_SIZE);
        String appId = this.f3024b.getAppId();
        String openId = this.f3024b.getOpenId();
        C2061f.m2127a("openSDK_LOG.QzonePublish", "openId:" + openId);
        if (3 != i || stringArrayList == null) {
            str = "";
        } else {
            StringBuffer stringBuffer2 = new StringBuffer();
            int size = stringArrayList.size();
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
            str = Constants.VIA_SHARE_TYPE_PUBLISHMOOD;
        }
        if (4 == i) {
            stringBuffer.append("&videoPath=" + Base64.encodeToString(C2089i.m2280i(string3), 2));
            stringBuffer.append("&videoDuration=" + Base64.encodeToString(C2089i.m2280i(String.valueOf(i2)), 2));
            stringBuffer.append("&videoSize=" + Base64.encodeToString(C2089i.m2280i(String.valueOf(j)), 2));
            str = Constants.VIA_SHARE_TYPE_PUBLISHVIDEO;
        }
        String str2 = str;
        if (!TextUtils.isEmpty(string)) {
            stringBuffer.append("&description=" + Base64.encodeToString(C2089i.m2280i(string), 2));
        }
        if (!TextUtils.isEmpty(appId)) {
            stringBuffer.append("&share_id=" + appId);
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append("&app_name=" + Base64.encodeToString(C2089i.m2280i(string2), 2));
        }
        if (!C2089i.m2274e(openId)) {
            stringBuffer.append("&open_id=" + Base64.encodeToString(C2089i.m2280i(openId), 2));
        }
        stringBuffer.append("&req_type=" + Base64.encodeToString(C2089i.m2280i(String.valueOf(i)), 2));
        C2061f.m2127a("openSDK_LOG.QzonePublish", "doPublishToQzone, url: " + stringBuffer.toString());
        C2024a.m1931a(C2084d.m2215a(), this.f3024b, "requireApi", "shareToNativeQQ");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(stringBuffer.toString()));
        intent.putExtra("pkg_name", activity.getPackageName());
        if (m2010a(intent)) {
            m2006a(activity, Constants.REQUEST_QZONE_SHARE, intent, false);
            C2069d.m2162a().m2163a(0, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f3024b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent success");
            C2069d.m2162a().m2165a(this.f3024b.getOpenId(), this.f3024b.getAppId(), Constants.VIA_SHARE_TO_QZONE, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, "3", "1", str2, "0", "1", "0");
        } else {
            C2061f.m2135e("openSDK_LOG.QzonePublish", "doPublishToQzone() target activity not found");
            C2069d.m2162a().m2163a(1, "SHARE_CHECK_SDK", Constants.DEFAULT_UIN, this.f3024b.getAppId(), String.valueOf(4), Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "hasActivityForIntent fail");
            C2069d.m2162a().m2165a(this.f3024b.getOpenId(), this.f3024b.getAppId(), Constants.VIA_SHARE_TO_QZONE, Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE, "3", "1", str2, "0", "1", "0");
        }
        C2061f.m2133c("openSDK_LOG", "doPublishToQzone() --end");
    }
}
