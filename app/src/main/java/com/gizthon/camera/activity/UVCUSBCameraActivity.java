package com.gizthon.camera.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.gizthon.camera.adapter.ResolutionListAdapter;
import com.gizthon.camera.application.CameraApplication;
import com.gizthon.camera.core.OnCameraConnectedListener;
// import com.gizthon.camera.databinding.UsbPreviewActivityBinding;
import com.gizthon.camera.model.CervexaDatabase;
import com.gizthon.camera.model.PatientDao;
import com.jaeger.library.StatusBarUtil;
import com.jiangdg.usbcamera.UVCCameraHelper;
import com.jiangdg.usbcamera.utils.FileUtils;
import com.serenegiant.usb.IButtonCallback;
import com.serenegiant.usb.Size;
import com.serenegiant.usb.UVCCamera;
import com.serenegiant.usb.common.AbstractUVCCameraHandler;
import com.serenegiant.usb.encoder.RecordParams;
import com.weioa.KmedHealthIndonesia.R;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class UVCUSBCameraActivity extends CameraBaseActivity {
    // ── Patient data ──────────────────────────────────────────────────────────
    private int currentPatientId = -1;
    private String currentPatientName = "";
    private String currentPatientNik = "";
    private String currentPatientRs = "";
    private java.util.concurrent.ExecutorService dbExecutor;

    // ── Camera ────────────────────────────────────────────────────────────────
    private ResolutionListAdapter adapter;
    public class UsbPreviewActivityBinding {
        public android.widget.TextView tvRecordTime;
        public android.widget.ImageView ivResolution;
        public android.view.View llResolution;
        public android.widget.ImageView ivZoomIn;
        public android.widget.ImageView ivZoomOut;
        public android.widget.ImageView ivZoomFocus;
        public android.widget.ImageView ivFocusImg;
        public android.widget.ImageView ivRecord;
        public android.widget.ImageView ivPicture;
        public android.widget.ImageView takePhoto;
        public android.widget.ImageView back;
        public androidx.recyclerview.widget.RecyclerView rcResolution;
        public com.serenegiant.usb.widget.UVCCameraTextureView cameraView;
        public android.widget.ImageView ivBroken;
        public android.view.View llConent;
        public void setEventHandler(Object o) {}
    }
    private UsbPreviewActivityBinding binding;
    private UVCCameraHelper mCameraHelper;
    private int position;
    private long startTime;
    protected Handler handler = new Handler();
    protected Runnable updateTimer = new Runnable() { // from class: com.gizthon.camera.activity.UVCUSBCameraActivity.14
        @Override // java.lang.Runnable
        public void run() {
            Long lValueOf = Long.valueOf(System.currentTimeMillis() - UVCUSBCameraActivity.this.startTime);
            Long lValueOf2 = Long.valueOf(((lValueOf.longValue() / 1000) / 60) / 60);
            Long lValueOf3 = Long.valueOf(((lValueOf.longValue() / 1000) / 60) % 60);
            Long lValueOf4 = Long.valueOf((lValueOf.longValue() / 1000) % 60);
            UVCUSBCameraActivity.this.binding.tvRecordTime.setText(String.format("%02d", lValueOf2) + ":" + String.format("%02d", lValueOf3) + ":" + String.format("%02d", lValueOf4));
            UVCUSBCameraActivity.this.handler.postDelayed(this, 1000L);
        }
    };

    public static void start(Context context) {
        context.startActivity(new Intent(context, (Class<?>) UVCUSBCameraActivity.class));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // com.gizthon.camera.activity.CameraBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int layoutId = getResources().getIdentifier("usb_preview_activity", "layout", getPackageName());
        setContentView(layoutId);
        
        this.binding = new UsbPreviewActivityBinding();
        this.binding.tvRecordTime = findViewById(getResources().getIdentifier("tv_record_time", "id", getPackageName()));
        this.binding.ivResolution = findViewById(getResources().getIdentifier("iv_resolution", "id", getPackageName()));
        this.binding.llResolution = findViewById(getResources().getIdentifier("ll_resolution", "id", getPackageName()));
        this.binding.ivZoomIn = findViewById(getResources().getIdentifier("iv_zoom_in", "id", getPackageName()));
        this.binding.ivZoomOut = findViewById(getResources().getIdentifier("iv_zoom_out", "id", getPackageName()));
        this.binding.ivZoomFocus = findViewById(getResources().getIdentifier("iv_zoom_focus", "id", getPackageName()));
        this.binding.ivFocusImg = findViewById(getResources().getIdentifier("iv_focus_img", "id", getPackageName()));
        this.binding.ivRecord = findViewById(getResources().getIdentifier("iv_record", "id", getPackageName()));
        this.binding.ivPicture = findViewById(getResources().getIdentifier("iv_picture", "id", getPackageName()));
        this.binding.takePhoto = findViewById(getResources().getIdentifier("take_photo", "id", getPackageName()));
        this.binding.back = findViewById(getResources().getIdentifier("back", "id", getPackageName()));
        this.binding.rcResolution = findViewById(getResources().getIdentifier("rc_resolution", "id", getPackageName()));
        this.binding.cameraView = findViewById(getResources().getIdentifier("camera_view", "id", getPackageName()));
        this.binding.ivBroken = findViewById(getResources().getIdentifier("iv_broken", "id", getPackageName()));
        this.binding.llConent = findViewById(getResources().getIdentifier("ll_conent", "id", getPackageName()));
        this.binding.setEventHandler(this);
        connectService();
        StatusBarUtil.setColorNoTranslucent(this, Color.parseColor("#4A4A4A"));
        dbExecutor = Executors.newSingleThreadExecutor();
        readPatientExtras();
        initDate();
    }

    /** Baca data pasien dari Intent dan tampilkan di overlay kamera. */
    private void readPatientExtras() {
        Intent intent = getIntent();
        currentPatientId   = intent.getIntExtra(PatientActivity.EXTRA_PATIENT_ID, -1);
        currentPatientName = intent.getStringExtra(PatientActivity.EXTRA_PATIENT_NAMA) != null
                ? intent.getStringExtra(PatientActivity.EXTRA_PATIENT_NAMA) : "";
        currentPatientNik  = intent.getStringExtra(PatientActivity.EXTRA_PATIENT_NIK) != null
                ? intent.getStringExtra(PatientActivity.EXTRA_PATIENT_NIK) : "";
        currentPatientRs   = intent.getStringExtra(PatientActivity.EXTRA_PATIENT_RS) != null
                ? intent.getStringExtra(PatientActivity.EXTRA_PATIENT_RS) : "";

        if (!TextUtils.isEmpty(currentPatientName)) {
            String label = "\uD83D\uDC64 " + currentPatientName;
            if (!TextUtils.isEmpty(currentPatientNik)) {
                label += "  |  NIK: " + currentPatientNik;
            }
            if (!TextUtils.isEmpty(currentPatientRs)) {
                label += "  |  " + currentPatientRs;
            }
            android.widget.TextView tvPatientInfo = findViewById(com.weioa.KmedHealthIndonesia.R.id.tv_patient_info);
            if (tvPatientInfo != null) {
                tvPatientInfo.setText(label);
                tvPatientInfo.setVisibility(View.VISIBLE);
            }
        }
    }

    void initDate() {
        this.binding.ivResolution.setOnClickListener(new View.OnClickListener() { // from class: com.gizthon.camera.activity.UVCUSBCameraActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UVCUSBCameraActivity.this.binding.llResolution.setVisibility(0);
                UVCUSBCameraActivity.this.adapter.setData(UVCUSBCameraActivity.this.getResolutionList());
            }
        });
        this.binding.llResolution.setOnClickListener(new View.OnClickListener() { // from class: com.gizthon.camera.activity.UVCUSBCameraActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UVCUSBCameraActivity.this.binding.llResolution.setVisibility(8);
            }
        });
        this.binding.ivZoomIn.setOnClickListener(new View.OnClickListener() { // from class: com.gizthon.camera.activity.UVCUSBCameraActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UVCUSBCameraActivity uVCUSBCameraActivity = UVCUSBCameraActivity.this;
                uVCUSBCameraActivity.zoom(true, uVCUSBCameraActivity.binding.llConent);
            }
        });
        this.binding.ivZoomOut.setOnClickListener(new View.OnClickListener() { // from class: com.gizthon.camera.activity.UVCUSBCameraActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UVCUSBCameraActivity uVCUSBCameraActivity = UVCUSBCameraActivity.this;
                uVCUSBCameraActivity.zoom(false, uVCUSBCameraActivity.binding.llConent);
            }
        });
        this.binding.ivZoomFocus.setOnClickListener(new View.OnClickListener() { // from class: com.gizthon.camera.activity.UVCUSBCameraActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (UVCUSBCameraActivity.this.binding.ivFocusImg.getVisibility() == 8) {
                    UVCUSBCameraActivity.this.binding.ivFocusImg.setVisibility(0);
                } else {
                    UVCUSBCameraActivity.this.binding.ivFocusImg.setVisibility(8);
                }
            }
        });
        this.binding.ivRecord.setOnClickListener(new ViewOnClickListenerC10946());
        this.binding.ivPicture.setOnClickListener(new View.OnClickListener() { // from class: com.gizthon.camera.activity.UVCUSBCameraActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UVCUSBCameraActivity.this.onClickGallery();
            }
        });
        this.binding.takePhoto.setOnClickListener(new View.OnClickListener() { // from class: com.gizthon.camera.activity.UVCUSBCameraActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UVCUSBCameraActivity.this.onClickTakePhoto();
            }
        });
        this.binding.back.setOnClickListener(new View.OnClickListener() { // from class: com.gizthon.camera.activity.UVCUSBCameraActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UVCUSBCameraActivity.this.finish();
            }
        });
        int tvPrintId = getResources().getIdentifier("tv_print", "id", getPackageName());
        android.widget.TextView tvPrint = tvPrintId != 0 ? (android.widget.TextView) findViewById(tvPrintId) : null;
        if (tvPrint != null) {
            tvPrint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(UVCUSBCameraActivity.this, "Buka Galeri untuk Print", Toast.LENGTH_SHORT).show();
                }
            });
        }

        int tvShareId = getResources().getIdentifier("tv_share", "id", getPackageName());
        android.widget.TextView tvShare = tvShareId != 0 ? (android.widget.TextView) findViewById(tvShareId) : null;
        if (tvShare != null) {
            tvShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(UVCUSBCameraActivity.this, "Buka Galeri untuk Share", Toast.LENGTH_SHORT).show();
                }
            });
        }

        int tvAiToggleId = getResources().getIdentifier("tv_ai_toggle", "id", getPackageName());
        android.widget.TextView tvAiToggle = tvAiToggleId != 0 ? (android.widget.TextView) findViewById(tvAiToggleId) : null;
        if (tvAiToggle != null) {
            tvAiToggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        com.gizthon.camera.utils.ViaModelHelper helper = new com.gizthon.camera.utils.ViaModelHelper(UVCUSBCameraActivity.this);
                        if (helper.isInitialized()) {
                            // Tampilkan shape ke Toast langsung di layar pengguna!
                            Toast.makeText(UVCUSBCameraActivity.this, "AI Shape:\n" + helper.getShapeInfo(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(UVCUSBCameraActivity.this, "Gagal memuat AI:\n" + helper.getShapeInfo(), Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(UVCUSBCameraActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        this.binding.rcResolution.setLayoutManager(new LinearLayoutManager(this));
        ResolutionListAdapter resolutionListAdapter = new ResolutionListAdapter(this);
        this.adapter = resolutionListAdapter;
        resolutionListAdapter.setOnClickItem(new ResolutionListAdapter.OnClickItem() { // from class: com.gizthon.camera.activity.UVCUSBCameraActivity.10
            @Override // com.gizthon.camera.adapter.ResolutionListAdapter.OnClickItem
            public void onClick(int i) {
                String[] strArrSplit;
                UVCUSBCameraActivity.this.binding.llResolution.setVisibility(8);
                if (UVCUSBCameraActivity.this.mCameraHelper == null || !UVCUSBCameraActivity.this.mCameraHelper.isCameraOpened() || (strArrSplit = ((String) UVCUSBCameraActivity.this.getResolutionList().get(i)).split("x")) == null || strArrSplit.length < 2) {
                    return;
                }
                UVCUSBCameraActivity.this.mCameraHelper.updateResolution(Integer.valueOf(strArrSplit[0]).intValue(), Integer.valueOf(strArrSplit[1]).intValue());
            }
        });
        this.binding.rcResolution.setAdapter(this.adapter);
    }

    /* JADX INFO: renamed from: com.gizthon.camera.activity.UVCUSBCameraActivity$6 */
    class ViewOnClickListenerC10946 implements View.OnClickListener {
        ViewOnClickListenerC10946() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (UVCUSBCameraActivity.this.mCameraHelper == null || !UVCUSBCameraActivity.this.mCameraHelper.isCameraOpened()) {
                UVCUSBCameraActivity.this.showShortMsg("sorry,camera open failed");
                return;
            }
            if (!UVCUSBCameraActivity.this.mCameraHelper.isPushing()) {
                String str = UVCCameraHelper.ROOT_PATH + "/MergeCamera/Media/Video/" + System.currentTimeMillis();
                RecordParams recordParams = new RecordParams();
                recordParams.setRecordPath(str);
                recordParams.setVoiceClose(true);
                recordParams.setRecordDuration(0);
                recordParams.setSupportOverlay(true);
                UVCUSBCameraActivity.this.mCameraHelper.startPusher(recordParams, new AbstractUVCCameraHandler.OnEncodeResultListener() { // from class: com.gizthon.camera.activity.UVCUSBCameraActivity.6.1
                    @Override // com.serenegiant.usb.common.AbstractUVCCameraHandler.OnEncodeResultListener
                    public void onEncodeResult(byte[] bArr, int i, int i2, long j, int i3) {
                        if (i3 == 1) {
                            FileUtils.putFileStream(bArr, i, i2);
                        }
                    }

                    @Override // com.serenegiant.usb.common.AbstractUVCCameraHandler.OnEncodeResultListener
                    public void onRecordResult(final String str2) {
                        if (TextUtils.isEmpty(str2)) {
                            return;
                        }
                        new Handler(UVCUSBCameraActivity.this.getMainLooper()).post(new Runnable() { // from class: com.gizthon.camera.activity.UVCUSBCameraActivity.6.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                Toast.makeText(UVCUSBCameraActivity.this, "save videoPath:" + str2, 0).show();
                                UVCUSBCameraActivity.this.refresh(str2);
                            }
                        });
                    }
                });
                UVCUSBCameraActivity.this.showVideoUI();
                return;
            }
            FileUtils.releaseFile();
            UVCUSBCameraActivity.this.mCameraHelper.stopPusher();
            UVCUSBCameraActivity.this.hideVideoUI();
        }
    }

    @Override // com.gizthon.camera.activity.CameraBaseActivity
    public void onClickTakePhoto() {
        final String str = UVCCameraHelper.ROOT_PATH + CameraApplication.DIRECTORY_NAME + System.currentTimeMillis() + UVCCameraHelper.SUFFIX_JPEG;
        this.mCameraHelper.capturePicture(str, new AbstractUVCCameraHandler.OnCaptureListener() { // from class: com.gizthon.camera.activity.UVCUSBCameraActivity.11
            @Override // com.serenegiant.usb.common.AbstractUVCCameraHandler.OnCaptureListener
            public void onCaptureResult(String str2) {
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                // Asosiasikan foto dengan pasien di DB (background thread)
                if (currentPatientId != -1) {
                    final String savedPath = str2;
                    dbExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                CervexaDatabase db = CervexaDatabase.getInstance(UVCUSBCameraActivity.this);
                                db.patientDao().updateLastPhoto(currentPatientId, savedPath, System.currentTimeMillis());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                new Handler(UVCUSBCameraActivity.this.getMainLooper()).post(new Runnable() { // from class: com.gizthon.camera.activity.UVCUSBCameraActivity.11.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(UVCUSBCameraActivity.this, "\uD83D\uDCF8 Foto tersimpan", 0).show();
                        UVCUSBCameraActivity.this.refresh(str);
                    }
                });
            }
        });
    }

    private void connectService() {
        this.cameraManager.getUVCUSBCamera().initDevice(this);
        this.cameraManager.getUVCUSBCamera().setIButtonCallback(new IButtonCallback() { // from class: com.gizthon.camera.activity.UVCUSBCameraActivity.12
            @Override // com.serenegiant.usb.IButtonCallback
            public void onButton(int i, int i2) {
            }
        });
        this.binding.cameraView.setCallback(this.cameraManager.getUVCUSBCamera());
        this.cameraManager.getUVCUSBCamera().setCameraView(this.binding.cameraView);
        this.cameraManager.getUVCUSBCamera().setListener(new OnCameraConnectedListener() { // from class: com.gizthon.camera.activity.UVCUSBCameraActivity.13
            @Override // com.gizthon.camera.core.OnCameraConnectedListener
            public void onConnectedSuccess(int i) {
                if (i == 20003) {
                    new Handler(UVCUSBCameraActivity.this.getMainLooper()).postDelayed(new Runnable() { // from class: com.gizthon.camera.activity.UVCUSBCameraActivity.13.1
                        @Override // java.lang.Runnable
                        public void run() {
                            UVCUSBCameraActivity.this.getResolutionList();
                            UVCUSBCameraActivity.this.binding.ivBroken.setVisibility(8);
                            UVCUSBCameraActivity.this.mCameraHelper.updateResolution(UVCCamera.DEFAULT_PREVIEW_WIDTH, 480);
                            UVCUSBCameraActivity.this.adapter.selected(UVCUSBCameraActivity.this.position);
                        }
                    }, 2000L);
                } else if (20004 == i) {
                    new Handler(UVCUSBCameraActivity.this.getMainLooper()).postDelayed(new Runnable() { // from class: com.gizthon.camera.activity.UVCUSBCameraActivity.13.2
                        @Override // java.lang.Runnable
                        public void run() {
                        }
                    }, 2000L);
                }
            }

            @Override // com.gizthon.camera.core.OnCameraConnectedListener
            public void onConnectedFailed(int i) {
                new Handler(UVCUSBCameraActivity.this.getMainLooper()).postDelayed(new Runnable() { // from class: com.gizthon.camera.activity.UVCUSBCameraActivity.13.3
                    @Override // java.lang.Runnable
                    public void run() {
                        UVCUSBCameraActivity.this.binding.ivBroken.setVisibility(0);
                    }
                }, 2000L);
            }
        });
        this.cameraManager.getUVCUSBCamera().connectDevice();
        this.cameraManager.getUVCUSBCamera().onStart();
        this.mCameraHelper = this.cameraManager.getUVCUSBCamera().getCameraHelper();
    }

    @Override // com.gizthon.camera.activity.CameraBaseActivity
    public void onClickGallery() {
        GalleryListActivity.start(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        FileUtils.releaseFile();
        this.cameraManager.getUVCUSBCamera().destroyDevice();
        if (dbExecutor != null) {
            dbExecutor.shutdown();
        }
    }

    public void zoom(boolean z, View view) {
        float scaleX = view.getScaleX();
        float scaleY = view.getScaleY();
        if (z) {
            if (scaleX > 1.0f) {
                view.setScaleX(scaleX - 0.5f);
                view.setScaleY(scaleY - 0.5f);
                return;
            }
            return;
        }
        if (scaleX < 4.0f) {
            view.setScaleX(scaleX + 0.5f);
            view.setScaleY(scaleY + 0.5f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showShortMsg(String str) {
        Toast.makeText(this, str, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showVideoUI() {
        this.binding.ivRecord.setImageResource(R.mipmap.icon_record_stop);
        this.binding.tvRecordTime.setVisibility(0);
        this.startTime = System.currentTimeMillis();
        this.handler.removeCallbacks(this.updateTimer);
        this.handler.postDelayed(this.updateTimer, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideVideoUI() {
        this.binding.ivRecord.setImageResource(R.mipmap.icon_record);
        this.handler.removeCallbacks(this.updateTimer);
        this.binding.tvRecordTime.setVisibility(8);
        this.binding.tvRecordTime.setText("00:00:00");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> getResolutionList() {
        List<Size> supportedPreviewSizes = this.mCameraHelper.getSupportedPreviewSizes();
        if (supportedPreviewSizes == null || supportedPreviewSizes.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < supportedPreviewSizes.size(); i++) {
            Size size = supportedPreviewSizes.get(i);
            if (size != null) {
                if (size.width == 640) {
                    this.position = i;
                }
                arrayList.add(size.width + "x" + size.height);
            }
        }
        return arrayList;
    }

    void refresh(String str) {
        File file = new File(str);
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(file));
        sendBroadcast(intent);
    }
}
