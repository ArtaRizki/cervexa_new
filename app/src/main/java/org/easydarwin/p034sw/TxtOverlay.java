package org.easydarwin.p034sw;
import android.content.Context;
public class TxtOverlay {
    private static TxtOverlay instance;
    private long ctx;
    static { System.loadLibrary("TxtOverlay"); }
    public static void install(Context context) {}
    public void init(int i, int i2, String str) {}
    public void init(int i, int i2) {}
    public void overlay(byte[] bArr, String str) {}
    public void release() {}
    public static TxtOverlay getInstance() {
        if (instance == null) instance = new TxtOverlay();
        return instance;
    }
}
