package com.tencent.p023mm.opensdk.diffdev.p025a;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;
import com.tencent.p023mm.opensdk.diffdev.OAuthErrCode;
import com.tencent.p023mm.opensdk.diffdev.OAuthListener;
import com.tencent.p023mm.opensdk.utils.Log;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.tencent.mm.opensdk.diffdev.a.d */
/* JADX INFO: loaded from: classes2.dex */
public final class AsyncTaskC2045d extends AsyncTask<Void, Void, a> {

    /* JADX INFO: renamed from: h */
    private static final String f3073h = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tencent/MicroMsg/oauth_qrcode.png";

    /* JADX INFO: renamed from: i */
    private static String f3074i;
    private String appId;

    /* JADX INFO: renamed from: j */
    private String f3075j;

    /* JADX INFO: renamed from: k */
    private String f3076k;

    /* JADX INFO: renamed from: l */
    private OAuthListener f3077l;

    /* JADX INFO: renamed from: m */
    private AsyncTaskC2047f f3078m;
    private String scope;
    private String signature;

    /* JADX INFO: renamed from: com.tencent.mm.opensdk.diffdev.a.d$a */
    static class a {

        /* JADX INFO: renamed from: n */
        public OAuthErrCode f3079n;

        /* JADX INFO: renamed from: o */
        public String f3080o;

        /* JADX INFO: renamed from: p */
        public String f3081p;

        /* JADX INFO: renamed from: q */
        public String f3082q;

        /* JADX INFO: renamed from: r */
        public int f3083r;

        /* JADX INFO: renamed from: s */
        public String f3084s;

        /* JADX INFO: renamed from: t */
        public byte[] f3085t;

        private a() {
        }

        /* JADX INFO: renamed from: a */
        public static a m2042a(byte[] bArr) {
            OAuthErrCode oAuthErrCode;
            String str;
            a aVar = new a();
            if (bArr != null && bArr.length != 0) {
                try {
                } catch (Exception e) {
                    str = String.format("parse fail, build String fail, ex = %s", e.getMessage());
                }
                try {
                    JSONObject jSONObject = new JSONObject(new String(bArr, "utf-8"));
                    int i = jSONObject.getInt("errcode");
                    if (i != 0) {
                        Log.m2051e("MicroMsg.SDK.GetQRCodeResult", String.format("resp errcode = %d", Integer.valueOf(i)));
                        aVar.f3079n = OAuthErrCode.WechatAuth_Err_NormalErr;
                        aVar.f3083r = i;
                        aVar.f3084s = jSONObject.optString("errmsg");
                        return aVar;
                    }
                    String string = jSONObject.getJSONObject("qrcode").getString("qrcodebase64");
                    if (string != null && string.length() != 0) {
                        byte[] bArrDecode = Base64.decode(string, 0);
                        if (bArrDecode != null && bArrDecode.length != 0) {
                            aVar.f3079n = OAuthErrCode.WechatAuth_Err_OK;
                            aVar.f3085t = bArrDecode;
                            aVar.f3080o = jSONObject.getString("uuid");
                            String string2 = jSONObject.getString("appname");
                            aVar.f3081p = string2;
                            Log.m2050d("MicroMsg.SDK.GetQRCodeResult", String.format("parse succ, save in memory, uuid = %s, appname = %s, imgBufLength = %d", aVar.f3080o, string2, Integer.valueOf(aVar.f3085t.length)));
                            return aVar;
                        }
                        Log.m2051e("MicroMsg.SDK.GetQRCodeResult", "parse fail, qrcodeBuf is null");
                        aVar.f3079n = OAuthErrCode.WechatAuth_Err_JsonDecodeErr;
                        return aVar;
                    }
                    Log.m2051e("MicroMsg.SDK.GetQRCodeResult", "parse fail, qrcodeBase64 is null");
                    aVar.f3079n = OAuthErrCode.WechatAuth_Err_JsonDecodeErr;
                    return aVar;
                } catch (Exception e2) {
                    str = String.format("parse json fail, ex = %s", e2.getMessage());
                    Log.m2051e("MicroMsg.SDK.GetQRCodeResult", str);
                    oAuthErrCode = OAuthErrCode.WechatAuth_Err_NormalErr;
                    aVar.f3079n = oAuthErrCode;
                    return aVar;
                }
            }
            Log.m2051e("MicroMsg.SDK.GetQRCodeResult", "parse fail, buf is null");
            oAuthErrCode = OAuthErrCode.WechatAuth_Err_NetworkErr;
            aVar.f3079n = oAuthErrCode;
            return aVar;
        }
    }

    static {
        f3074i = null;
        f3074i = "https://open.weixin.qq.com/connect/sdk/qrconnect?appid=%s&noncestr=%s&timestamp=%s&scope=%s&signature=%s";
    }

    public AsyncTaskC2045d(String str, String str2, String str3, String str4, String str5, OAuthListener oAuthListener) {
        this.appId = str;
        this.scope = str2;
        this.f3075j = str3;
        this.f3076k = str4;
        this.signature = str5;
        this.f3077l = oAuthListener;
    }

    /* JADX INFO: renamed from: a */
    public final boolean m2041a() {
        Log.m2052i("MicroMsg.SDK.GetQRCodeTask", "cancelTask");
        AsyncTaskC2047f asyncTaskC2047f = this.f3078m;
        return asyncTaskC2047f == null ? cancel(true) : asyncTaskC2047f.cancel(true);
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ a doInBackground(Void[] voidArr) {
        Log.m2052i("MicroMsg.SDK.GetQRCodeTask", "external storage available = false");
        String str = String.format(f3074i, this.appId, this.f3075j, this.f3076k, this.scope, this.signature);
        long jCurrentTimeMillis = System.currentTimeMillis();
        byte[] bArrM2043a = C2046e.m2043a(str, -1);
        Log.m2050d("MicroMsg.SDK.GetQRCodeTask", String.format("doInBackground, url = %s, time consumed = %d(ms)", str, Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)));
        return a.m2042a(bArrM2043a);
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onPostExecute(a aVar) {
        a aVar2 = aVar;
        if (aVar2.f3079n != OAuthErrCode.WechatAuth_Err_OK) {
            Log.m2051e("MicroMsg.SDK.GetQRCodeTask", String.format("onPostExecute, get qrcode fail, OAuthErrCode = %s", aVar2.f3079n));
            this.f3077l.onAuthFinish(aVar2.f3079n, null);
            return;
        }
        Log.m2050d("MicroMsg.SDK.GetQRCodeTask", "onPostExecute, get qrcode success");
        this.f3077l.onAuthGotQrcode(aVar2.f3082q, aVar2.f3085t);
        AsyncTaskC2047f asyncTaskC2047f = new AsyncTaskC2047f(aVar2.f3080o, this.f3077l);
        this.f3078m = asyncTaskC2047f;
        if (Build.VERSION.SDK_INT >= 11) {
            asyncTaskC2047f.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            asyncTaskC2047f.execute(new Void[0]);
        }
    }
}
