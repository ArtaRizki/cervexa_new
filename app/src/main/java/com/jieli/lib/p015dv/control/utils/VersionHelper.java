package com.jieli.lib.p015dv.control.utils;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class VersionHelper {
    public static String getSdkVersionName(Context context) {
        InputStream inputStreamOpenRawResource = null;
        try {
            inputStreamOpenRawResource = context.getResources().openRawResource(com.weioa.KmedHealthIndonesia.R.raw.version);
            byte[] bArr = new byte[inputStreamOpenRawResource.available()];
            if (inputStreamOpenRawResource.read(bArr) > 0) {
                return new String(bArr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStreamOpenRawResource != null) {
                try {
                    Dlog.m1384i("===", "InputStream closed");
                    inputStreamOpenRawResource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
}
