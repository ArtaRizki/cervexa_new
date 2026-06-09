package com.baidu.platform.comapi.map;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import java.util.Iterator;

/* JADX INFO: renamed from: com.baidu.platform.comapi.map.k */
/* JADX INFO: loaded from: classes.dex */
class HandlerC0752k extends Handler {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ GestureDetectorOnDoubleTapListenerC0751j f999a;

    HandlerC0752k(GestureDetectorOnDoubleTapListenerC0751j gestureDetectorOnDoubleTapListenerC0751j) {
        this.f999a = gestureDetectorOnDoubleTapListenerC0751j;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (this.f999a.f990g != null && ((Long) message.obj).longValue() == this.f999a.f990g.f954h) {
            if (message.what == 4000) {
                for (InterfaceC0753l interfaceC0753l : this.f999a.f990g.f952f) {
                    Bitmap bitmapCreateBitmap = null;
                    if (message.arg2 == 1) {
                        int[] iArr = new int[this.f999a.f987d * this.f999a.f988e];
                        int[] iArr2 = new int[this.f999a.f987d * this.f999a.f988e];
                        if (this.f999a.f990g.f953g == null) {
                            return;
                        }
                        int[] iArrM842a = this.f999a.f990g.f953g.m842a(iArr, this.f999a.f987d, this.f999a.f988e);
                        for (int i = 0; i < this.f999a.f988e; i++) {
                            for (int i2 = 0; i2 < this.f999a.f987d; i2++) {
                                int i3 = iArrM842a[(this.f999a.f987d * i) + i2];
                                iArr2[(((this.f999a.f988e - i) - 1) * this.f999a.f987d) + i2] = (i3 & (-16711936)) | ((i3 << 16) & 16711680) | ((i3 >> 16) & 255);
                            }
                        }
                        bitmapCreateBitmap = Bitmap.createBitmap(iArr2, this.f999a.f987d, this.f999a.f988e, Bitmap.Config.ARGB_8888);
                    }
                    interfaceC0753l.mo410a(bitmapCreateBitmap);
                }
                return;
            }
            if (message.what != 39) {
                if (message.what == 41) {
                    if (this.f999a.f990g == null) {
                        return;
                    }
                    if (this.f999a.f990g.f957l || this.f999a.f990g.f958m) {
                        Iterator<InterfaceC0753l> it = this.f999a.f990g.f952f.iterator();
                        while (it.hasNext()) {
                            it.next().mo419b(this.f999a.f990g.m620D());
                        }
                        return;
                    }
                    return;
                }
                if (message.what == 999) {
                    Iterator<InterfaceC0753l> it2 = this.f999a.f990g.f952f.iterator();
                    while (it2.hasNext()) {
                        it2.next().mo426e();
                    }
                    return;
                } else {
                    if (message.what == 50) {
                        for (InterfaceC0753l interfaceC0753l2 : this.f999a.f990g.f952f) {
                            if (message.arg1 == 0) {
                                interfaceC0753l2.mo416a(false);
                            } else if (message.arg1 == 1) {
                                interfaceC0753l2.mo416a(true);
                            }
                        }
                        return;
                    }
                    return;
                }
            }
            if (this.f999a.f990g == null) {
                return;
            }
            if (message.arg1 == 100) {
                this.f999a.f990g.m617A();
            } else if (message.arg1 == 200) {
                this.f999a.f990g.m627K();
            } else if (message.arg1 == 1) {
                this.f999a.requestRender();
            } else if (message.arg1 == 0) {
                this.f999a.requestRender();
                if (!this.f999a.f990g.m664c() && this.f999a.getRenderMode() != 0) {
                    this.f999a.setRenderMode(0);
                }
            } else if (message.arg1 == 2) {
                Iterator<InterfaceC0753l> it3 = this.f999a.f990g.f952f.iterator();
                while (it3.hasNext()) {
                    it3.next().mo421c();
                }
            }
            if (!this.f999a.f990g.f955i && this.f999a.f988e > 0 && this.f999a.f987d > 0 && this.f999a.f990g.m655b(0, 0) != null) {
                this.f999a.f990g.f955i = true;
                Iterator<InterfaceC0753l> it4 = this.f999a.f990g.f952f.iterator();
                while (it4.hasNext()) {
                    it4.next().mo417b();
                }
            }
            Iterator<InterfaceC0753l> it5 = this.f999a.f990g.f952f.iterator();
            while (it5.hasNext()) {
                it5.next().mo409a();
            }
        }
    }
}
