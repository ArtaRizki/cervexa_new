package com.baidu.platform.comjni.tools;

import android.os.Bundle;
import com.baidu.mapapi.model.inner.C0706a;
import com.baidu.mapapi.model.inner.Point;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.baidu.platform.comjni.tools.a */
/* JADX INFO: loaded from: classes.dex */
public class C0785a {
    /* JADX INFO: renamed from: a */
    public static double m897a(Point point, Point point2) {
        Bundle bundle = new Bundle();
        bundle.putDouble("x1", point.f712x);
        bundle.putDouble("y1", point.f713y);
        bundle.putDouble("x2", point2.f712x);
        bundle.putDouble("y2", point2.f713y);
        JNITools.GetDistanceByMC(bundle);
        return bundle.getDouble("distance");
    }

    /* JADX INFO: renamed from: a */
    public static C0706a m898a(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("strkey", str);
        JNITools.TransGeoStr2ComplexPt(bundle);
        C0706a c0706a = new C0706a();
        Bundle bundle2 = bundle.getBundle("map_bound");
        if (bundle2 != null) {
            Bundle bundle3 = bundle2.getBundle("ll");
            if (bundle3 != null) {
                c0706a.f715b = new Point((int) bundle3.getDouble("ptx"), (int) bundle3.getDouble("pty"));
            }
            Bundle bundle4 = bundle2.getBundle("ru");
            if (bundle4 != null) {
                c0706a.f716c = new Point((int) bundle4.getDouble("ptx"), (int) bundle4.getDouble("pty"));
            }
        }
        for (ParcelItem parcelItem : (ParcelItem[]) bundle.getParcelableArray("poly_line")) {
            if (c0706a.f717d == null) {
                c0706a.f717d = new ArrayList<>();
            }
            Bundle bundle5 = parcelItem.getBundle();
            if (bundle5 != null) {
                ParcelItem[] parcelItemArr = (ParcelItem[]) bundle5.getParcelableArray("point_array");
                ArrayList<Point> arrayList = new ArrayList<>();
                for (ParcelItem parcelItem2 : parcelItemArr) {
                    Bundle bundle6 = parcelItem2.getBundle();
                    if (bundle6 != null) {
                        arrayList.add(new Point((int) bundle6.getDouble("ptx"), (int) bundle6.getDouble("pty")));
                    }
                }
                arrayList.trimToSize();
                c0706a.f717d.add(arrayList);
            }
        }
        c0706a.f717d.trimToSize();
        c0706a.f714a = (int) bundle.getDouble("type");
        return c0706a;
    }

    /* JADX INFO: renamed from: a */
    public static String m899a() {
        return JNITools.GetToken();
    }

    /* JADX INFO: renamed from: a */
    public static void m900a(boolean z, int i) {
        JNITools.openLogEnable(z, i);
    }

    /* JADX INFO: renamed from: b */
    public static void m901b() {
        JNITools.initClass(new Bundle(), 0);
    }
}
