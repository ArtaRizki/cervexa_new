package com.baidu.mapapi.map.offline;

import com.baidu.platform.comapi.map.C0750i;
import com.baidu.platform.comapi.map.C0761t;
import com.baidu.platform.comapi.map.C0762u;
import com.baidu.platform.comapi.map.C0765x;
import com.baidu.platform.comapi.map.InterfaceC0766y;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class MKOfflineMap {
    public static final int TYPE_DOWNLOAD_UPDATE = 0;
    public static final int TYPE_NETWORK_ERROR = 2;
    public static final int TYPE_NEW_OFFLINE = 6;
    public static final int TYPE_VER_UPDATE = 4;

    /* JADX INFO: renamed from: a */
    private static final String f685a = MKOfflineMap.class.getSimpleName();

    /* JADX INFO: renamed from: b */
    private C0762u f686b;

    /* JADX INFO: renamed from: c */
    private MKOfflineMapListener f687c;

    public void destroy() {
        this.f686b.m746d(0);
        this.f686b.m741b((InterfaceC0766y) null);
        this.f686b.m740b();
        C0750i.m709b();
    }

    public ArrayList<MKOLUpdateElement> getAllUpdateInfo() {
        ArrayList<C0765x> arrayListM747e = this.f686b.m747e();
        if (arrayListM747e == null) {
            return null;
        }
        ArrayList<MKOLUpdateElement> arrayList = new ArrayList<>();
        Iterator<C0765x> it = arrayListM747e.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getUpdatElementFromLocalMapElement(it.next().m751a()));
        }
        return arrayList;
    }

    public ArrayList<MKOLSearchRecord> getHotCityList() {
        ArrayList<C0761t> arrayListM743c = this.f686b.m743c();
        if (arrayListM743c == null) {
            return null;
        }
        ArrayList<MKOLSearchRecord> arrayList = new ArrayList<>();
        Iterator<C0761t> it = arrayListM743c.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getSearchRecordFromLocalCityInfo(it.next()));
        }
        return arrayList;
    }

    public ArrayList<MKOLSearchRecord> getOfflineCityList() {
        ArrayList<C0761t> arrayListM745d = this.f686b.m745d();
        if (arrayListM745d == null) {
            return null;
        }
        ArrayList<MKOLSearchRecord> arrayList = new ArrayList<>();
        Iterator<C0761t> it = arrayListM745d.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getSearchRecordFromLocalCityInfo(it.next()));
        }
        return arrayList;
    }

    public MKOLUpdateElement getUpdateInfo(int i) {
        C0765x c0765xM750g = this.f686b.m750g(i);
        if (c0765xM750g == null) {
            return null;
        }
        return OfflineMapUtil.getUpdatElementFromLocalMapElement(c0765xM750g.m751a());
    }

    @Deprecated
    public int importOfflineData() {
        return importOfflineData(false);
    }

    @Deprecated
    public int importOfflineData(boolean z) {
        int size;
        ArrayList<C0765x> arrayListM747e = this.f686b.m747e();
        int size2 = 0;
        if (arrayListM747e != null) {
            size2 = arrayListM747e.size();
            size = size2;
        } else {
            size = 0;
        }
        this.f686b.m739a(z, true);
        ArrayList<C0765x> arrayListM747e2 = this.f686b.m747e();
        if (arrayListM747e2 != null) {
            size = arrayListM747e2.size();
        }
        return size - size2;
    }

    public boolean init(MKOfflineMapListener mKOfflineMapListener) throws Throwable {
        C0750i.m706a();
        C0762u c0762uM731a = C0762u.m731a();
        this.f686b = c0762uM731a;
        if (c0762uM731a == null) {
            return false;
        }
        c0762uM731a.m737a(new C0693a(this));
        this.f687c = mKOfflineMapListener;
        return true;
    }

    public boolean pause(int i) {
        return this.f686b.m744c(i);
    }

    public boolean remove(int i) {
        return this.f686b.m748e(i);
    }

    public ArrayList<MKOLSearchRecord> searchCity(String str) {
        ArrayList<C0761t> arrayListM736a = this.f686b.m736a(str);
        if (arrayListM736a == null) {
            return null;
        }
        ArrayList<MKOLSearchRecord> arrayList = new ArrayList<>();
        Iterator<C0761t> it = arrayListM736a.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getSearchRecordFromLocalCityInfo(it.next()));
        }
        return arrayList;
    }

    public boolean start(int i) {
        C0762u c0762u = this.f686b;
        if (c0762u == null) {
            return false;
        }
        if (c0762u.m747e() != null) {
            for (C0765x c0765x : this.f686b.m747e()) {
                if (c0765x.f1034a.f1022a == i) {
                    if (c0765x.f1034a.f1031j || c0765x.f1034a.f1033l == 2 || c0765x.f1034a.f1033l == 3 || c0765x.f1034a.f1033l == 6) {
                        return this.f686b.m742b(i);
                    }
                    return false;
                }
            }
        }
        return this.f686b.m738a(i);
    }

    public boolean update(int i) {
        C0762u c0762u = this.f686b;
        if (c0762u != null && c0762u.m747e() != null) {
            Iterator<C0765x> it = this.f686b.m747e().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                C0765x next = it.next();
                if (next.f1034a.f1022a == i) {
                    if (next.f1034a.f1031j) {
                        return this.f686b.m749f(i);
                    }
                }
            }
        }
        return false;
    }
}
