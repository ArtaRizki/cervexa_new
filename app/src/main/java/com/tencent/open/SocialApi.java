package com.tencent.open;

import android.app.Activity;
import android.os.Bundle;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class SocialApi {

    /* JADX INFO: renamed from: a */
    private SocialApiIml f3101a;

    public SocialApi(QQToken qQToken) {
        this.f3101a = new SocialApiIml(qQToken);
    }

    public void invite(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f3101a.invite(activity, bundle, iUiListener);
    }

    public void story(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f3101a.story(activity, bundle, iUiListener);
    }

    public void gift(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f3101a.gift(activity, bundle, iUiListener);
    }

    public void ask(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.f3101a.ask(activity, bundle, iUiListener);
    }
}
