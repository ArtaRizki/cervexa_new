package com.gizthon.camera.core.wifi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.generalplus.GoPlusDrone.Activity.CameraNewActivity;
import com.gizthon.camera.core.ICamera;
import com.gizthon.camera.core.OnCameraConnectedListener;
import com.jieli.stream.p016dv.running2.p017ui.activity.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public class WifiCamera implements ICamera {
    private static final String TAG = "WifiCamera";

    /** SSID prefix kamera MS2 Elikliv */
    private static final String SSID_MS2 = "wifi_camera";
    /** SSID prefix kamera Jieli */
    private static final String SSID_JIELI = "Cam-";
    /** IP yang ditetapkan DHCP Jieli ke client */
    private static final String IP_JIELI_CLIENT = "192.168.25.101";

    private OnCameraConnectedListener listener;
    public boolean isWifi = false;
    private boolean isJieli = false;

    @Override // com.gizthon.camera.core.ICamera
    public void checkDevice() {
    }

    @Override // com.gizthon.camera.core.ICamera
    public void connectDevice() {
    }

    @Override // com.gizthon.camera.core.ICamera
    public void destroyDevice() {
    }

    @Override // com.gizthon.camera.core.ICamera
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @Override // com.gizthon.camera.core.ICamera
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    @Override // com.gizthon.camera.core.ICamera
    public void onStart() {
    }

    @Override // com.gizthon.camera.core.ICamera
    public void setListener(OnCameraConnectedListener onCameraConnectedListener) {
        this.listener = onCameraConnectedListener;
    }

    /**
     * Inisialisasi & deteksi jenis kamera WiFi yang sedang terkoneksi.
     *
     * FIX: Sebelumnya pengecekan MS2 mengharuskan IP client = 192.168.1.2,
     * padahal DHCP pada MS2 bisa memberikan IP yang berbeda (mis. 192.168.1.100).
     * Sekarang deteksi MS2 hanya berdasarkan SSID (mengandung "wifi_camera"),
     * tanpa syarat IP yang ketat.
     */
    public void initDevice(Activity activity) {
        WifiManager wifiManager = (WifiManager) activity.getApplicationContext().getSystemService("wifi");
        if (wifiManager == null) {
            this.isWifi = false;
            this.isJieli = false;
            return;
        }
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        String strIntToIp = intToIp(connectionInfo.getIpAddress());
        
        Log.i(TAG, "WiFi IP    : " + strIntToIp);

        // Gunakan fungsi isMs2WifiConnected yang sudah disempurnakan
        if (isMs2WifiConnected(activity)) {
            this.isWifi = true;
            this.isJieli = false;
            Log.i(TAG, "Terdeteksi: kamera MS2 (WiFi)");

        // ── Jieli: syarat IP tetap dipertahankan karena SSID-nya lebih umum ──
        } else {
            String rawSsid = connectionInfo.getSSID();
            String ssid = (rawSsid == null) ? "" : rawSsid.replace("\"", "").trim();
            if (TextUtils.equals(strIntToIp, IP_JIELI_CLIENT) && ssid.contains(SSID_JIELI)) {
                this.isWifi = true;
                this.isJieli = true;
                Log.i(TAG, "Terdeteksi: kamera Jieli (WiFi)");
            } else {
                this.isWifi = false;
                this.isJieli = false;
                Log.i(TAG, "Tidak ada kamera WiFi terdeteksi");
            }
        }
    }

    /**
     * Helper statik: cek apakah HP saat ini terkoneksi ke WiFi kamera MS2.
     * Bisa dipanggil dari activity mana pun tanpa perlu instance WifiCamera.
     */
    public static boolean isMs2WifiConnected(Context context) {
        try {
            WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            if (wm == null || !wm.isWifiEnabled()) return false;
            
            WifiInfo info = wm.getConnectionInfo();
            if (info == null) return false;
            
            String ssid = info.getSSID();
            if (ssid == null) ssid = "";
            
            ssid = ssid.replace("\"", "").trim().toLowerCase();
            Log.i(TAG, "isMs2WifiConnected() raw SSID=" + ssid);
            
            // Variasi nama bawaan Elikliv MS2 dan generic WiFi microscope
            boolean connected = ssid.contains("ms2") || 
                                ssid.contains("hd wifi") || 
                                ssid.contains("wifi_camera") ||
                                ssid.contains("max-see");
                                
            // Jika Android mengembalikan <unknown ssid> atau string kosong karena masalah Location permission
            if (!connected && (ssid.contains("unknown ssid") || ssid.isEmpty())) {
                int ip = info.getIpAddress();
                if (ip != 0) {
                    // Coba baca IP address. Kamera MS2 biasanya memberi IP client 192.168.1.x atau 192.168.10.x
                    String ipStr = (ip & 255) + "." + ((ip >> 8) & 255) + "." + ((ip >> 16) & 255) + "." + ((ip >> 24) & 255);
                    Log.i(TAG, "isMs2WifiConnected() IP=" + ipStr);
                    if (ipStr.startsWith("192.168.1.") || ipStr.startsWith("192.168.10.")) {
                        Log.i(TAG, "isMs2WifiConnected() Menggunakan fallback deteksi IP");
                        connected = true;
                    }
                }
            }
            
            return connected;
        } catch (Exception e) {
            Log.e(TAG, "isMs2WifiConnected error: " + e.getMessage());
            return false;
        }
    }

    public void startWifi1Activity(Context context) {
        if (this.isJieli) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putBoolean("IsCard", false);
            intent.putExtras(bundle);
            intent.setClass(context, CameraNewActivity.class);
            context.startActivity(intent);
            return;
        }
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("isMs2", isMs2WifiConnected(context));
        context.startActivity(intent);
    }

    private String intToIp(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }
}
