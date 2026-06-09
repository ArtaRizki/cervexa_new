package com.baidu.platform.comapi.pano;

import android.net.Uri;
import android.text.TextUtils;
import com.baidu.mapapi.http.AsyncHttpClient;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.platform.comjni.util.AppMD5;
import com.tencent.tauth.AuthActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: renamed from: com.baidu.platform.comapi.pano.a */
/* JADX INFO: loaded from: classes.dex */
public class C0768a {

    /* JADX INFO: renamed from: a */
    AsyncHttpClient f1042a = new AsyncHttpClient();

    /* JADX INFO: renamed from: com.baidu.platform.comapi.pano.a$a */
    public interface a<T> {
        /* JADX INFO: renamed from: a */
        void mo502a(HttpClient.HttpStateError httpStateError);

        /* JADX INFO: renamed from: a */
        void mo503a(T t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public C0770c m757a(String str) {
        if (str == null || str.equals("")) {
            return new C0770c(PanoStateError.PANO_NOT_FOUND);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("result");
            if (jSONObjectOptJSONObject == null) {
                return new C0770c(PanoStateError.PANO_NOT_FOUND);
            }
            if (jSONObjectOptJSONObject.optInt(IjkMediaPlayer.OnNativeInvokeListener.ARG_ERROR) != 0) {
                return new C0770c(PanoStateError.PANO_UID_ERROR);
            }
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("content");
            if (jSONArrayOptJSONArray == null) {
                return new C0770c(PanoStateError.PANO_NOT_FOUND);
            }
            C0770c c0770c = null;
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(i).optJSONObject("poiinfo");
                if (jSONObjectOptJSONObject2 != null) {
                    c0770c = new C0770c(PanoStateError.PANO_NO_ERROR);
                    c0770c.m763a(jSONObjectOptJSONObject2.optString("PID"));
                    c0770c.m762a(jSONObjectOptJSONObject2.optInt("hasstreet"));
                }
            }
            return c0770c;
        } catch (JSONException e) {
            e.printStackTrace();
            return new C0770c(PanoStateError.PANO_NOT_FOUND);
        }
    }

    /* JADX INFO: renamed from: a */
    private String m758a(Uri.Builder builder) {
        Uri.Builder builderBuildUpon = Uri.parse(builder.build().toString() + HttpClient.getPhoneInfo()).buildUpon();
        builderBuildUpon.appendQueryParameter("sign", AppMD5.getSignMD5String(builderBuildUpon.build().getEncodedQuery()));
        return builderBuildUpon.build().toString();
    }

    /* JADX INFO: renamed from: a */
    private void m759a(Uri.Builder builder, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        builder.appendQueryParameter(str, str2);
    }

    /* JADX INFO: renamed from: a */
    public void m760a(String str, a<C0770c> aVar) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http");
        builder.encodedAuthority("api.map.baidu.com");
        builder.path("/sdkproxy/lbs_androidsdk/pano/v1/");
        m759a(builder, "qt", "poi");
        m759a(builder, "uid", str);
        m759a(builder, AuthActivity.ACTION_KEY, "0");
        String authToken = HttpClient.getAuthToken();
        if (authToken == null) {
            aVar.mo503a(new C0770c(PanoStateError.PANO_NO_TOKEN));
            return;
        }
        m759a(builder, "token", authToken);
        this.f1042a.get(m758a(builder), new C0769b(this, aVar));
    }
}
