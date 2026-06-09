package com.baidu.mapapi.map.offline;

import com.baidu.platform.comapi.map.InterfaceC0766y;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.baidu.mapapi.map.offline.a */
/* JADX INFO: loaded from: classes.dex */
class C0693a implements InterfaceC0766y {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ MKOfflineMap f688a;

    C0693a(MKOfflineMap mKOfflineMap) {
        this.f688a = mKOfflineMap;
    }

    @Override // com.baidu.platform.comapi.map.InterfaceC0766y
    /* JADX INFO: renamed from: a */
    public void mo447a(int i, int i2) {
        MKOfflineMapListener mKOfflineMapListener;
        if (i == 4) {
            ArrayList<MKOLUpdateElement> allUpdateInfo = this.f688a.getAllUpdateInfo();
            if (allUpdateInfo != null) {
                for (MKOLUpdateElement mKOLUpdateElement : allUpdateInfo) {
                    if (mKOLUpdateElement.update) {
                        this.f688a.f687c.onGetOfflineMapState(4, mKOLUpdateElement.cityID);
                    }
                }
                return;
            }
            return;
        }
        int i3 = 6;
        if (i == 6) {
            mKOfflineMapListener = this.f688a.f687c;
        } else if (i == 8) {
            this.f688a.f687c.onGetOfflineMapState(0, 65535 & (i2 >> 8));
            return;
        } else {
            if (i != 10) {
                if (i != 12) {
                    return;
                }
                this.f688a.f686b.m739a(true, false);
                return;
            }
            mKOfflineMapListener = this.f688a.f687c;
            i3 = 2;
        }
        mKOfflineMapListener.onGetOfflineMapState(i3, i2);
    }
}
