package com.baidu.mapapi.map;

import android.content.Context;
import android.os.Bundle;
import com.baidu.platform.comapi.map.InterfaceC0738L;

/* JADX INFO: renamed from: com.baidu.mapapi.map.d */
/* JADX INFO: loaded from: classes.dex */
class C0681d implements InterfaceC0738L {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ BaiduMap f661a;

    C0681d(BaiduMap baiduMap) {
        this.f661a = baiduMap;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0738L
    /* JADX INFO: renamed from: a */
    public Bundle mo430a(int i, int i2, int i3, Context context) {
        Tile tileM377a;
        this.f661a.f244F.lock();
        try {
            if (this.f661a.f241C != null && (tileM377a = this.f661a.f241C.m377a(i, i2, i3)) != null) {
                return tileM377a.toBundle();
            }
            this.f661a.f244F.unlock();
            return null;
        } finally {
            this.f661a.f244F.unlock();
        }
    }
}
