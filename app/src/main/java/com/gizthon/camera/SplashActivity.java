package com.gizthon.camera;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import androidx.databinding.DataBindingUtil;
import com.gizthon.camera.activity.CameraBaseActivity;
import com.gizthon.camera.activity.GalleryListActivity;
import com.gizthon.camera.activity.HelpActivity;
import com.gizthon.camera.activity.PatientActivity;
import com.gizthon.camera.activity.UVCUSBCameraActivity;
import com.gizthon.camera.databinding.SplashActivityBinding;
import com.jaeger.library.StatusBarUtil;
import permissions.dispatcher.PermissionRequest;

/* JADX INFO: loaded from: classes.dex */
public class SplashActivity extends CameraBaseActivity {
    private String TAG = getClass().getSimpleName();
    private SplashActivityBinding binding;

    @Override // com.gizthon.camera.activity.CameraBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int layoutId = getResources().getIdentifier("splash_activity", "layout", getPackageName());
        setContentView(layoutId);

        int tvCameraId = getResources().getIdentifier("tv_camera", "id", getPackageName());
        if (tvCameraId != 0) {
            android.view.View tvCamera = findViewById(tvCameraId);
            if (tvCamera != null) {
                tvCamera.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        onClickGallery();
                    }
                });
            }
        }

        int tvConnectId = getResources().getIdentifier("tv_connect", "id", getPackageName());
        if (tvConnectId != 0) {
            android.view.View tvConnect = findViewById(tvConnectId);
            if (tvConnect != null) {
                tvConnect.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        // Langsung ke form pasien tanpa menunggu kamera USB
                        PatientActivity.start(SplashActivity.this);
                    }
                });
            }
        }

        int tvHelpId = getResources().getIdentifier("tv_help", "id", getPackageName());
        if (tvHelpId != 0) {
            android.view.View tvHelp = findViewById(tvHelpId);
            if (tvHelp != null) {
                tvHelp.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        onClickHelp();
                    }
                });
            }
        }
        StatusBarUtil.setColorNoTranslucent(this, Color.parseColor("#202226"));
        this.cameraManager.getWifiCamera().initDevice(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.cameraManager.getWifiCamera().initDevice(this);
    }

    public void onClickUsb() {
        SplashActivityPermissionsDispatcher.connectDeviceWithPermissionCheck(this, false);
    }

    public void onClickWifi() {
        SplashActivityPermissionsDispatcher.connectDeviceWithPermissionCheck(this, false);
    }

    @Override // com.gizthon.camera.activity.CameraBaseActivity
    public void onClickGallery() {
        GalleryListActivity.start(this);
    }

    public void onClickHelp() {
        HelpActivity.start(this);
    }

    public void connectDevice(boolean z) {
        this.cameraManager.getWifiCamera().initDevice(this);
        if (!this.cameraManager.getWifiCamera().isWifi) {
            // Selalu masuk ke form pasien terlebih dahulu sebelum kamera
            PatientActivity.start(this);
        } else {
            this.cameraManager.getWifiCamera().startWifi1Activity(this);
        }
    }

    

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        SplashActivityPermissionsDispatcher.onRequestPermissionsResult(this, i, iArr);
    }

    public void showRationaleForCamera(PermissionRequest permissionRequest) {
        Log.i(this.TAG, "showRationaleForCamera");
        permissionRequest.proceed();
    }

    public void onCameraDenied() {
        Log.i(this.TAG, "onCameraDenied");
    }

    public void onCameraNeverAskAgain() {
        Log.i(this.TAG, "onCameraNeverAskAgain");
    }
}
