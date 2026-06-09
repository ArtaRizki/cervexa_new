package com.jieli.stream.p016dv.running2.wxapi;

import android.content.Intent;
import android.os.Bundle;
import com.jieli.stream.p016dv.running2.p017ui.base.BaseActivity;
import com.jieli.stream.p016dv.running2.util.Dbug;
import com.jieli.stream.p016dv.running2.util.ManifestUtil;
import com.tencent.p023mm.opensdk.modelbase.BaseReq;
import com.tencent.p023mm.opensdk.modelbase.BaseResp;
import com.tencent.p023mm.opensdk.openapi.IWXAPI;
import com.tencent.p023mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.p023mm.opensdk.openapi.WXAPIFactory;

/* JADX INFO: loaded from: classes.dex */
public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private static final int TIMELINE_SUPPORTED_VERSION = 553779201;
    private IWXAPI api;
    private String tag = getClass().getSimpleName();

    @Override // com.jieli.stream.p016dv.running2.p017ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        IWXAPI iwxapiCreateWXAPI = WXAPIFactory.createWXAPI(this, ManifestUtil.getWeixinKey(this), false);
        this.api = iwxapiCreateWXAPI;
        iwxapiCreateWXAPI.handleIntent(getIntent(), this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Dbug.m1388e(this.tag, "onNewIntent---");
        setIntent(intent);
        this.api.handleIntent(intent, this);
    }

    @Override // com.tencent.p023mm.opensdk.openapi.IWXAPIEventHandler
    public void onReq(BaseReq baseReq) {
        Dbug.m1388e(this.tag, "onReq-----");
    }

    @Override // com.tencent.p023mm.opensdk.openapi.IWXAPIEventHandler
    public void onResp(BaseResp baseResp) {
        Dbug.m1388e(this.tag, "onResp------");
        finish();
    }
}
