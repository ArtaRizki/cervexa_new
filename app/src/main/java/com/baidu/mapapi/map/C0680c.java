package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.platform.comapi.map.InterfaceC0758q;

/* JADX INFO: renamed from: com.baidu.mapapi.map.c */
/* JADX INFO: loaded from: classes.dex */
class C0680c implements InterfaceC0758q {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ BaiduMap f660a;

    C0680c(BaiduMap baiduMap) {
        this.f660a = baiduMap;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0758q
    /* JADX INFO: renamed from: a */
    public Bundle mo429a(int i, int i2, int i3) {
        Tile tileM305a;
        this.f660a.f243E.lock();
        try {
            if (this.f660a.f242D != null && (tileM305a = this.f660a.f242D.m305a(i, i2, i3)) != null) {
                return tileM305a.toBundle();
            }
            this.f660a.f243E.unlock();
            return null;
        } finally {
            this.f660a.f243E.unlock();
        }
    }
}
