package com.tencent.p023mm.opensdk.diffdev.p025a;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.tencent.p023mm.opensdk.diffdev.IDiffDevOAuth;
import com.tencent.p023mm.opensdk.diffdev.OAuthListener;
import com.tencent.p023mm.opensdk.utils.Log;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.tencent.mm.opensdk.diffdev.a.a */
/* JADX INFO: loaded from: classes2.dex */
public final class C2042a implements IDiffDevOAuth {

    /* JADX INFO: renamed from: d */
    private AsyncTaskC2045d f3069d;
    private Handler handler = null;

    /* JADX INFO: renamed from: c */
    private List<OAuthListener> f3068c = new ArrayList();

    /* JADX INFO: renamed from: e */
    private OAuthListener f3070e = new C2043b(this);

    /* JADX INFO: renamed from: c */
    static /* synthetic */ AsyncTaskC2045d m2040c(C2042a c2042a) {
        c2042a.f3069d = null;
        return null;
    }

    @Override // com.tencent.p023mm.opensdk.diffdev.IDiffDevOAuth
    public final void addListener(OAuthListener oAuthListener) {
        if (this.f3068c.contains(oAuthListener)) {
            return;
        }
        this.f3068c.add(oAuthListener);
    }

    @Override // com.tencent.p023mm.opensdk.diffdev.IDiffDevOAuth
    public final boolean auth(String str, String str2, String str3, String str4, String str5, OAuthListener oAuthListener) {
        Log.m2052i("MicroMsg.SDK.DiffDevOAuth", "start auth, appId = " + str);
        if (str == null || str.length() <= 0 || str2 == null || str2.length() <= 0) {
            Log.m2050d("MicroMsg.SDK.DiffDevOAuth", String.format("auth fail, invalid argument, appId = %s, scope = %s", str, str2));
            return false;
        }
        if (this.handler == null) {
            this.handler = new Handler(Looper.getMainLooper());
        }
        addListener(oAuthListener);
        if (this.f3069d != null) {
            Log.m2050d("MicroMsg.SDK.DiffDevOAuth", "auth, already running, no need to start auth again");
            return true;
        }
        AsyncTaskC2045d asyncTaskC2045d = new AsyncTaskC2045d(str, str2, str3, str4, str5, this.f3070e);
        this.f3069d = asyncTaskC2045d;
        if (Build.VERSION.SDK_INT >= 11) {
            asyncTaskC2045d.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            asyncTaskC2045d.execute(new Void[0]);
        }
        return true;
    }

    @Override // com.tencent.p023mm.opensdk.diffdev.IDiffDevOAuth
    public final void detach() {
        Log.m2052i("MicroMsg.SDK.DiffDevOAuth", "detach");
        this.f3068c.clear();
        stopAuth();
    }

    @Override // com.tencent.p023mm.opensdk.diffdev.IDiffDevOAuth
    public final void removeAllListeners() {
        this.f3068c.clear();
    }

    @Override // com.tencent.p023mm.opensdk.diffdev.IDiffDevOAuth
    public final void removeListener(OAuthListener oAuthListener) {
        this.f3068c.remove(oAuthListener);
    }

    @Override // com.tencent.p023mm.opensdk.diffdev.IDiffDevOAuth
    public final boolean stopAuth() {
        boolean zM2041a;
        Log.m2052i("MicroMsg.SDK.DiffDevOAuth", "stopAuth");
        try {
            zM2041a = this.f3069d == null ? true : this.f3069d.m2041a();
        } catch (Exception e) {
            Log.m2054w("MicroMsg.SDK.DiffDevOAuth", "stopAuth fail, ex = " + e.getMessage());
            zM2041a = false;
        }
        this.f3069d = null;
        return zM2041a;
    }
}
