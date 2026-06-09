package com.baidu.mapapi.map.offline;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.platform.comapi.map.C0761t;
import com.baidu.platform.comapi.map.C0764w;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class OfflineMapUtil {
    public static MKOLSearchRecord getSearchRecordFromLocalCityInfo(C0761t c0761t) {
        if (c0761t == null) {
            return null;
        }
        MKOLSearchRecord mKOLSearchRecord = new MKOLSearchRecord();
        mKOLSearchRecord.cityID = c0761t.f1011a;
        mKOLSearchRecord.cityName = c0761t.f1012b;
        mKOLSearchRecord.cityType = c0761t.f1014d;
        int i = 0;
        if (c0761t.m729a() != null) {
            ArrayList<MKOLSearchRecord> arrayList = new ArrayList<>();
            for (C0761t c0761t2 : c0761t.m729a()) {
                arrayList.add(getSearchRecordFromLocalCityInfo(c0761t2));
                i += c0761t2.f1013c;
                mKOLSearchRecord.childCities = arrayList;
            }
        }
        if (mKOLSearchRecord.cityType == 1) {
            mKOLSearchRecord.size = i;
        } else {
            mKOLSearchRecord.size = c0761t.f1013c;
        }
        return mKOLSearchRecord;
    }

    public static MKOLUpdateElement getUpdatElementFromLocalMapElement(C0764w c0764w) {
        if (c0764w == null) {
            return null;
        }
        MKOLUpdateElement mKOLUpdateElement = new MKOLUpdateElement();
        mKOLUpdateElement.cityID = c0764w.f1022a;
        mKOLUpdateElement.cityName = c0764w.f1023b;
        if (c0764w.f1028g != null) {
            mKOLUpdateElement.geoPt = CoordUtil.mc2ll(c0764w.f1028g);
        }
        mKOLUpdateElement.level = c0764w.f1026e;
        mKOLUpdateElement.ratio = c0764w.f1030i;
        mKOLUpdateElement.serversize = c0764w.f1029h;
        mKOLUpdateElement.size = c0764w.f1030i == 100 ? c0764w.f1029h : (c0764w.f1029h / 100) * c0764w.f1030i;
        mKOLUpdateElement.status = c0764w.f1033l;
        mKOLUpdateElement.update = c0764w.f1031j;
        return mKOLUpdateElement;
    }
}
