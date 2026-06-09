package com.baidu.mapapi.utils.poi;

import android.content.Context;
import android.util.Log;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapapi.utils.poi.BaiduMapPoiSearch;
import com.baidu.platform.comapi.pano.C0768a;
import com.baidu.platform.comapi.pano.C0770c;

/* JADX INFO: renamed from: com.baidu.mapapi.utils.poi.a */
/* JADX INFO: loaded from: classes.dex */
final class C0714a implements C0768a.a<C0770c> {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ Context f760a;

    C0714a(Context context) {
        this.f760a = context;
    }

    @Override // com.baidu.platform.comapi.pano.C0768a.a
    /* JADX INFO: renamed from: a */
    public void mo502a(HttpClient.HttpStateError httpStateError) {
        String str;
        int i = BaiduMapPoiSearch.C07131.f754b[httpStateError.ordinal()];
        if (i == 1) {
            str = "current network is not available";
        } else if (i != 2) {
            return;
        } else {
            str = "network inner error, please check network";
        }
        Log.d("baidumapsdk", str);
    }

    @Override // com.baidu.platform.comapi.pano.C0768a.a
    /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void mo503a(C0770c c0770c) {
        String str;
        if (c0770c == null) {
            Log.d("baidumapsdk", "pano info is null");
            return;
        }
        int i = BaiduMapPoiSearch.C07131.f753a[c0770c.m761a().ordinal()];
        if (i == 1) {
            str = "pano uid is error, please check param poi uid";
        } else if (i == 2) {
            str = "pano id not found for this poi point";
        } else if (i == 3) {
            str = "please check ak for permission";
        } else {
            if (i != 4) {
                return;
            }
            if (c0770c.m765c() == 1) {
                try {
                    BaiduMapPoiSearch.m501b(c0770c.m764b(), this.f760a);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            str = "this point do not support for pano show";
        }
        Log.d("baidumapsdk", str);
    }
}
