package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C0731E;
import com.baidu.platform.comapi.map.InterfaceC0753l;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: renamed from: com.baidu.mapapi.map.q */
/* JADX INFO: loaded from: classes.dex */
class C0695q implements InterfaceC0753l {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ TextureMapView f690a;

    C0695q(TextureMapView textureMapView) {
        this.f690a = textureMapView;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: a */
    public void mo409a() {
        String str;
        if (this.f690a.f576b == null || this.f690a.f576b.m585b() == null) {
            return;
        }
        float f = this.f690a.f576b.m585b().m620D().f845a;
        if (this.f690a.f587o != f) {
            int iIntValue = ((Integer) TextureMapView.f575n.get((int) f)).intValue();
            int i = ((int) (((double) iIntValue) / this.f690a.f576b.m585b().m620D().f857m)) / 2;
            this.f690a.f586m.setPadding(i, 0, i, 0);
            Object[] objArr = new Object[1];
            if (iIntValue >= 1000) {
                objArr[0] = Integer.valueOf(iIntValue / 1000);
                str = String.format(" %d公里 ", objArr);
            } else {
                objArr[0] = Integer.valueOf(iIntValue);
                str = String.format(" %d米 ", objArr);
            }
            this.f690a.f584k.setText(str);
            this.f690a.f585l.setText(str);
            this.f690a.f587o = f;
        }
        this.f690a.m362b();
        this.f690a.requestLayout();
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: a */
    public void mo410a(Bitmap bitmap) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: a */
    public void mo411a(MotionEvent motionEvent) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: a */
    public void mo412a(GeoPoint geoPoint) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: a */
    public void mo413a(C0731E c0731e) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: a */
    public void mo414a(String str) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: a */
    public void mo415a(GL10 gl10, C0731E c0731e) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: a */
    public void mo416a(boolean z) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: b */
    public void mo417b() {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: b */
    public void mo418b(GeoPoint geoPoint) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: b */
    public void mo419b(C0731E c0731e) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: b */
    public boolean mo420b(String str) {
        return false;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: c */
    public void mo421c() {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: c */
    public void mo422c(GeoPoint geoPoint) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: c */
    public void mo423c(C0731E c0731e) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: d */
    public void mo424d() {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: d */
    public void mo425d(GeoPoint geoPoint) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: e */
    public void mo426e() {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: e */
    public void mo427e(GeoPoint geoPoint) {
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0753l
    /* JADX INFO: renamed from: f */
    public void mo428f() {
    }
}
