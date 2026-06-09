package com.baidu.mapapi.utils;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.platform.comapi.location.CoordinateType;

/* JADX INFO: loaded from: classes.dex */
public class CoordinateConverter {

    /* JADX INFO: renamed from: a */
    private LatLng f723a;

    /* JADX INFO: renamed from: b */
    private CoordType f724b;

    /* JADX INFO: renamed from: com.baidu.mapapi.utils.CoordinateConverter$1 */
    /* synthetic */ class C07071 {

        /* JADX INFO: renamed from: a */
        static final /* synthetic */ int[] f725a;

        static {
            int[] iArr = new int[CoordType.values().length];
            f725a = iArr;
            try {
                iArr[CoordType.COMMON.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f725a[CoordType.GPS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public enum CoordType {
        GPS,
        COMMON
    }

    /* JADX INFO: renamed from: a */
    private static LatLng m456a(LatLng latLng) {
        return m457a(latLng, CoordinateType.WGS84);
    }

    /* JADX INFO: renamed from: a */
    private static LatLng m457a(LatLng latLng, String str) {
        if (latLng == null) {
            return null;
        }
        return CoordUtil.Coordinate_encryptEx((float) latLng.longitude, (float) latLng.latitude, str);
    }

    /* JADX INFO: renamed from: b */
    private static LatLng m458b(LatLng latLng) {
        return m457a(latLng, CoordinateType.GCJ02);
    }

    public LatLng convert() {
        if (this.f723a == null) {
            return null;
        }
        if (this.f724b == null) {
            this.f724b = CoordType.GPS;
        }
        int i = C07071.f725a[this.f724b.ordinal()];
        if (i == 1) {
            return m458b(this.f723a);
        }
        if (i != 2) {
            return null;
        }
        return m456a(this.f723a);
    }

    public CoordinateConverter coord(LatLng latLng) {
        this.f723a = latLng;
        return this;
    }

    public CoordinateConverter from(CoordType coordType) {
        this.f724b = coordType;
        return this;
    }
}
