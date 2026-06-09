package com.tencent.p023mm.opensdk.diffdev.p025a;

import com.tencent.p023mm.opensdk.diffdev.OAuthErrCode;
import com.tencent.p023mm.opensdk.diffdev.OAuthListener;
import com.tencent.p023mm.opensdk.utils.Log;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: com.tencent.mm.opensdk.diffdev.a.b */
/* JADX INFO: loaded from: classes2.dex */
final class C2043b implements OAuthListener {

    /* JADX INFO: renamed from: f */
    final /* synthetic */ C2042a f3071f;

    C2043b(C2042a c2042a) {
        this.f3071f = c2042a;
    }

    @Override // com.tencent.p023mm.opensdk.diffdev.OAuthListener
    public final void onAuthFinish(OAuthErrCode oAuthErrCode, String str) {
        Log.m2050d("MicroMsg.SDK.ListenerWrapper", String.format("onAuthFinish, errCode = %s, authCode = %s", oAuthErrCode.toString(), str));
        C2042a.m2040c(this.f3071f);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f3071f.f3068c);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((OAuthListener) it.next()).onAuthFinish(oAuthErrCode, str);
        }
    }

    @Override // com.tencent.p023mm.opensdk.diffdev.OAuthListener
    public final void onAuthGotQrcode(String str, byte[] bArr) {
        Log.m2050d("MicroMsg.SDK.ListenerWrapper", "onAuthGotQrcode, qrcodeImgPath = " + str);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f3071f.f3068c);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((OAuthListener) it.next()).onAuthGotQrcode(str, bArr);
        }
    }

    @Override // com.tencent.p023mm.opensdk.diffdev.OAuthListener
    public final void onQrcodeScanned() {
        Log.m2050d("MicroMsg.SDK.ListenerWrapper", "onQrcodeScanned");
        if (this.f3071f.handler != null) {
            this.f3071f.handler.post(new RunnableC2044c(this));
        }
    }
}
