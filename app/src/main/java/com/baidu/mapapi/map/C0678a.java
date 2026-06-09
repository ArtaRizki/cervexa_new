package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.map.Overlay;

/* JADX INFO: renamed from: com.baidu.mapapi.map.a */
/* JADX INFO: loaded from: classes.dex */
class C0678a implements Overlay.InterfaceC0675a {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ BaiduMap f658a;

    C0678a(BaiduMap baiduMap) {
        this.f658a = baiduMap;
    }

    @Override // com.baidu.mapapi.map.Overlay.InterfaceC0675a
    /* JADX INFO: renamed from: a */
    public void mo341a(Overlay overlay) {
        if (overlay != null && this.f658a.f265k.contains(overlay)) {
            Bundle bundleMo340a = overlay.mo340a();
            if (this.f658a.f263i != null) {
                this.f658a.f263i.m667d(bundleMo340a);
            }
            this.f658a.f265k.remove(overlay);
        }
        if (overlay != null && this.f658a.f267m.contains(overlay)) {
            this.f658a.f267m.remove(overlay);
        }
        if (overlay == null || !this.f658a.f266l.contains(overlay)) {
            return;
        }
        Marker marker = (Marker) overlay;
        if (marker.f463n != null) {
            this.f658a.f266l.remove(marker);
            if (this.f658a.f266l.size() != 0 || this.f658a.f263i == null) {
                return;
            }
            this.f658a.f263i.m661b(false);
        }
    }

    @Override // com.baidu.mapapi.map.Overlay.InterfaceC0675a
    /* JADX INFO: renamed from: b */
    public void mo342b(Overlay overlay) {
        if (overlay != null && this.f658a.f265k.contains(overlay)) {
            if (overlay instanceof Marker) {
                Marker marker = (Marker) overlay;
                if (marker.f463n != null && marker.f463n.size() != 0) {
                    if (this.f658a.f266l.contains(marker)) {
                        this.f658a.f266l.remove(marker);
                    }
                    this.f658a.f266l.add(marker);
                    if (this.f658a.f263i != null) {
                        this.f658a.f263i.m661b(true);
                    }
                }
            }
            Bundle bundle = new Bundle();
            if (this.f658a.f263i != null) {
                this.f658a.f263i.m662c(overlay.mo237a(bundle));
            }
        }
        if (this.f658a.f267m.contains(overlay)) {
            this.f658a.f267m.remove(overlay);
        }
        if (overlay instanceof Marker) {
            this.f658a.f267m.add((Marker) overlay);
        }
    }
}
