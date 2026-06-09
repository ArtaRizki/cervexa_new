package com.tencent.p023mm.opensdk.diffdev.p025a;

import android.os.AsyncTask;
import com.baidu.mapapi.UIMsg;
import com.hoho.android.usbserial.driver.UsbId;
import com.tencent.p023mm.opensdk.diffdev.OAuthErrCode;
import com.tencent.p023mm.opensdk.diffdev.OAuthListener;
import com.tencent.p023mm.opensdk.utils.Log;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.tencent.mm.opensdk.diffdev.a.f */
/* JADX INFO: loaded from: classes2.dex */
final class AsyncTaskC2047f extends AsyncTask<Void, Void, a> {

    /* JADX INFO: renamed from: l */
    private OAuthListener f3086l;

    /* JADX INFO: renamed from: o */
    private String f3087o;

    /* JADX INFO: renamed from: u */
    private int f3088u;
    private String url;

    /* JADX INFO: renamed from: com.tencent.mm.opensdk.diffdev.a.f$a */
    static class a {

        /* JADX INFO: renamed from: n */
        public OAuthErrCode f3089n;

        /* JADX INFO: renamed from: v */
        public String f3090v;

        /* JADX INFO: renamed from: w */
        public int f3091w;

        a() {
        }

        /* JADX INFO: renamed from: b */
        public static a m2044b(byte[] bArr) {
            OAuthErrCode oAuthErrCode;
            String str;
            OAuthErrCode oAuthErrCode2;
            a aVar = new a();
            if (bArr == null || bArr.length == 0) {
                Log.m2051e("MicroMsg.SDK.NoopingResult", "parse fail, buf is null");
                oAuthErrCode = OAuthErrCode.WechatAuth_Err_NetworkErr;
            } else {
                try {
                    try {
                        JSONObject jSONObject = new JSONObject(new String(bArr, "utf-8"));
                        int i = jSONObject.getInt("wx_errcode");
                        aVar.f3091w = i;
                        Log.m2050d("MicroMsg.SDK.NoopingResult", String.format("nooping uuidStatusCode = %d", Integer.valueOf(i)));
                        int i2 = aVar.f3091w;
                        if (i2 == 408) {
                            oAuthErrCode2 = OAuthErrCode.WechatAuth_Err_OK;
                        } else if (i2 != 500) {
                            switch (i2) {
                                case 402:
                                    oAuthErrCode2 = OAuthErrCode.WechatAuth_Err_Timeout;
                                    break;
                                case 403:
                                    oAuthErrCode2 = OAuthErrCode.WechatAuth_Err_Cancel;
                                    break;
                                case UIMsg.l_ErrorNo.NETWORK_ERROR_404 /* 404 */:
                                    oAuthErrCode2 = OAuthErrCode.WechatAuth_Err_OK;
                                    break;
                                case 405:
                                    aVar.f3089n = OAuthErrCode.WechatAuth_Err_OK;
                                    aVar.f3090v = jSONObject.getString("wx_code");
                                    return aVar;
                                default:
                                    oAuthErrCode2 = OAuthErrCode.WechatAuth_Err_NormalErr;
                                    break;
                            }
                        } else {
                            oAuthErrCode2 = OAuthErrCode.WechatAuth_Err_NormalErr;
                        }
                        aVar.f3089n = oAuthErrCode2;
                        return aVar;
                    } catch (Exception e) {
                        str = String.format("parse json fail, ex = %s", e.getMessage());
                        Log.m2051e("MicroMsg.SDK.NoopingResult", str);
                        oAuthErrCode = OAuthErrCode.WechatAuth_Err_NormalErr;
                        aVar.f3089n = oAuthErrCode;
                        return aVar;
                    }
                } catch (Exception e2) {
                    str = String.format("parse fail, build String fail, ex = %s", e2.getMessage());
                }
            }
            aVar.f3089n = oAuthErrCode;
            return aVar;
        }
    }

    public AsyncTaskC2047f(String str, OAuthListener oAuthListener) {
        this.f3087o = str;
        this.f3086l = oAuthListener;
        this.url = String.format("https://long.open.weixin.qq.com/connect/l/qrconnect?f=json&uuid=%s", str);
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ a doInBackground(Void[] voidArr) {
        a aVar;
        OAuthErrCode oAuthErrCode;
        String str;
        String str2 = this.f3087o;
        if (str2 == null || str2.length() == 0) {
            Log.m2051e("MicroMsg.SDK.NoopingTask", "run fail, uuid is null");
            aVar = new a();
            oAuthErrCode = OAuthErrCode.WechatAuth_Err_NormalErr;
        } else {
            while (!isCancelled()) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.url);
                if (this.f3088u == 0) {
                    str = "";
                } else {
                    str = "&last=" + this.f3088u;
                }
                sb.append(str);
                String string = sb.toString();
                long jCurrentTimeMillis = System.currentTimeMillis();
                byte[] bArrM2043a = C2046e.m2043a(string, UsbId.SILABS_CP2102);
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                a aVarM2044b = a.m2044b(bArrM2043a);
                Log.m2050d("MicroMsg.SDK.NoopingTask", String.format("nooping, url = %s, errCode = %s, uuidStatusCode = %d, time consumed = %d(ms)", string, aVarM2044b.f3089n.toString(), Integer.valueOf(aVarM2044b.f3091w), Long.valueOf(jCurrentTimeMillis2 - jCurrentTimeMillis)));
                if (aVarM2044b.f3089n != OAuthErrCode.WechatAuth_Err_OK) {
                    Log.m2051e("MicroMsg.SDK.NoopingTask", String.format("nooping fail, errCode = %s, uuidStatusCode = %d", aVarM2044b.f3089n.toString(), Integer.valueOf(aVarM2044b.f3091w)));
                    return aVarM2044b;
                }
                this.f3088u = aVarM2044b.f3091w;
                if (aVarM2044b.f3091w == EnumC2048g.UUID_SCANED.getCode()) {
                    this.f3086l.onQrcodeScanned();
                } else if (aVarM2044b.f3091w != EnumC2048g.UUID_KEEP_CONNECT.getCode() && aVarM2044b.f3091w == EnumC2048g.UUID_CONFIRM.getCode()) {
                    if (aVarM2044b.f3090v == null || aVarM2044b.f3090v.length() == 0) {
                        Log.m2051e("MicroMsg.SDK.NoopingTask", "nooping fail, confirm with an empty code!!!");
                        aVarM2044b.f3089n = OAuthErrCode.WechatAuth_Err_NormalErr;
                    }
                    return aVarM2044b;
                }
            }
            Log.m2052i("MicroMsg.SDK.NoopingTask", "IDiffDevOAuth.stopAuth / detach invoked");
            aVar = new a();
            oAuthErrCode = OAuthErrCode.WechatAuth_Err_Auth_Stopped;
        }
        aVar.f3089n = oAuthErrCode;
        return aVar;
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onPostExecute(a aVar) {
        a aVar2 = aVar;
        this.f3086l.onAuthFinish(aVar2.f3089n, aVar2.f3090v);
    }
}
