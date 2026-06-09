package com.jieli.stream.p016dv.running2.util;

import com.jieli.lib.p015dv.control.DeviceClient;
import com.jieli.stream.p016dv.running2.p017ui.MainApplication;

/* JADX INFO: loaded from: classes.dex */
public class ClientManager {
    private static DeviceClient instance;
    private String tag = getClass().getSimpleName();

    public static DeviceClient getClient() {
        if (instance == null) {
            synchronized (ClientManager.class) {
                if (instance == null) {
                    instance = new DeviceClient(MainApplication.getApplication(), 0);
                }
            }
        }
        return instance;
    }

    public static void release() {
        DeviceClient deviceClient = instance;
        if (deviceClient != null) {
            deviceClient.release();
            instance = null;
        }
    }
}
