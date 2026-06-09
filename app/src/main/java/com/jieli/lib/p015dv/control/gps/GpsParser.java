package com.jieli.lib.p015dv.control.gps;

import com.jieli.lib.p015dv.control.base.AbstractClient;
import com.jieli.lib.p015dv.control.model.GpsInfo;
import com.jieli.lib.p015dv.control.player.Stream;

/* JADX INFO: loaded from: classes.dex */
public class GpsParser extends AbstractClient {

    /* JADX INFO: renamed from: a */
    private long f2103a = 0;

    /* JADX INFO: renamed from: b */
    private OnGpsListener f2104b;

    private native boolean nativeClose();

    private native boolean nativeCreate();

    private native boolean nativeInit();

    private native boolean nativeParse(byte[] bArr);

    protected void onError(int i, String str) {
    }

    public GpsParser() {
        Stream.loadLibrariesOnce(Stream.sLocalLibLoader);
        nativeInit();
    }

    public boolean parse(byte[] bArr) {
        return nativeParse(bArr);
    }

    @Override // com.jieli.lib.p015dv.control.base.AbstractClient
    public boolean create() {
        return nativeCreate();
    }

    @Override // com.jieli.lib.p015dv.control.base.AbstractClient
    public boolean close() {
        this.f2104b = null;
        return nativeClose();
    }

    public void setOnGpsListener(OnGpsListener onGpsListener) {
        this.f2104b = onGpsListener;
    }

    protected void onParsed(double d, double d2, double d3) {
        if (this.f2104b != null) {
            GpsInfo gpsInfo = new GpsInfo();
            gpsInfo.setLongitude(d);
            gpsInfo.setLatitude(d2);
            gpsInfo.setSpeed(d3);
            this.f2104b.onGps(gpsInfo);
        }
    }
}
