package com.jieli.lib.p015dv.control.utils;

import android.content.Context;
import com.jieli.lib.p015dv.control.C1408R;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class VersionHelper {
    public static String getSdkVersionName(Context context) {
        byte[] bArr;
        InputStream inputStreamOpenRawResource = context.getResources().openRawResource(C1408R.raw.version);
        try {
            try {
                try {
                    bArr = new byte[inputStreamOpenRawResource.available()];
                } catch (IOException e) {
                    e.printStackTrace();
                    Dlog.m1384i("===", "InputStream closed");
                    inputStreamOpenRawResource.close();
                }
                if (inputStreamOpenRawResource.read(bArr) > 0) {
                    return new String(bArr);
                }
                Dlog.m1384i("===", "InputStream closed");
                inputStreamOpenRawResource.close();
                return "";
            } finally {
                try {
                    Dlog.m1384i("===", "InputStream closed");
                    inputStreamOpenRawResource.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        } catch (IOException e3) {
            e3.printStackTrace();
            return "";
        }
    }
}
