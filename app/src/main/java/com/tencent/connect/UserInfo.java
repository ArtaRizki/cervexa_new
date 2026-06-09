package com.tencent.connect;

import android.content.Context;
import android.os.Bundle;
import com.jieli.lib.p015dv.control.utils.TopicKey;
import com.tencent.connect.auth.C2031c;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.open.utils.C2084d;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IUiListener;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class UserInfo extends BaseApi {
    public static final String GRAPH_OPEN_ID = "oauth2.0/m_me";

    public UserInfo(Context context, QQToken qQToken) {
        super(qQToken);
    }

    public UserInfo(Context context, C2031c c2031c, QQToken qQToken) {
        super(c2031c, qQToken);
    }

    public void getUserInfo(IUiListener iUiListener) {
        HttpUtils.requestAsync(this.f3024b, C2084d.m2215a(), "user/get_simple_userinfo", m2004a(), "GET", new BaseApi.TempRequestListener(iUiListener));
    }

    public void getVipUserInfo(IUiListener iUiListener) {
        HttpUtils.requestAsync(this.f3024b, C2084d.m2215a(), "user/get_vip_info", m2004a(), "GET", new BaseApi.TempRequestListener(iUiListener));
    }

    public void getVipUserRichInfo(IUiListener iUiListener) {
        HttpUtils.requestAsync(this.f3024b, C2084d.m2215a(), "user/get_vip_rich_info", m2004a(), "GET", new BaseApi.TempRequestListener(iUiListener));
    }

    public void getTenPayAddr(IUiListener iUiListener) {
        Bundle bundleA = m2004a();
        bundleA.putString(TopicKey.VERSION, "1");
        HttpUtils.requestAsync(this.f3024b, C2084d.m2215a(), "cft_info/get_tenpay_addr", bundleA, "GET", new BaseApi.TempRequestListener(iUiListener));
    }

    public void getOpenId(IUiListener iUiListener) {
        HttpUtils.requestAsync(this.f3024b, C2084d.m2215a(), GRAPH_OPEN_ID, m2004a(), "GET", new BaseApi.TempRequestListener(iUiListener));
    }
}
