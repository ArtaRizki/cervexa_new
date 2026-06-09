package com.jieli.stream.p016dv.running2.baidu.utils;

import android.content.Context;
import android.content.res.Resources;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.jieli.stream.p016dv.running2.C1438R;

/* JADX INFO: loaded from: classes.dex */
public class BitmapUtil {
    public static BitmapDescriptor bmArrowPoint;
    public static BitmapDescriptor bmEnd;
    public static BitmapDescriptor bmGcoding;
    public static BitmapDescriptor bmGeo;
    public static BitmapDescriptor bmStart;

    public static void init() {
        bmArrowPoint = BitmapDescriptorFactory.fromResource(C1438R.mipmap.ic_start_point);
        bmStart = BitmapDescriptorFactory.fromResource(C1438R.mipmap.icon_start);
        bmEnd = BitmapDescriptorFactory.fromResource(C1438R.mipmap.icon_end);
        bmGeo = BitmapDescriptorFactory.fromResource(C1438R.mipmap.icon_geo);
        bmGcoding = BitmapDescriptorFactory.fromResource(C1438R.mipmap.icon_gcoding);
    }

    public static void clear() {
        bmArrowPoint.recycle();
        bmStart.recycle();
        bmEnd.recycle();
        bmGeo.recycle();
    }

    public static BitmapDescriptor getMark(Context context, int i) {
        int identifier;
        Resources resources = context.getResources();
        if (i <= 10) {
            identifier = resources.getIdentifier("icon_mark" + i, "mipmap", context.getPackageName());
        } else {
            identifier = resources.getIdentifier("icon_markx", "mipmap", context.getPackageName());
        }
        return BitmapDescriptorFactory.fromResource(identifier);
    }
}
