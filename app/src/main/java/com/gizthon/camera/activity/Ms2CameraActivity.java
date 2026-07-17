package com.gizthon.camera.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jieli.stream.p016dv.running2.p017ui.widget.media.IjkVideoView;
import com.weioa.KmedHealthIndonesia.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.view.TextureView;
import android.view.ViewGroup;
import android.view.SurfaceView;
import android.view.PixelCopy;
import android.view.Surface;

import com.gizthon.camera.utils.ViewRecorder;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Activity khusus untuk menampilkan live stream kamera WiFi MS2 (Elikliv/Jieli).
 *
 * MS2 berbasis Jieli, stream via RTSP port 554.
 * TIDAK memerlukan koneksi SDK Jieli (ClientManager/CommunicationService).
 *
 * URL dicoba secara berurutan (auto-probe):
 *   1. rtsp://192.168.1.1:554/264_pcm_rt/XXX.hd   (H264, kualitas HD)
 *   2. rtsp://192.168.1.1:554/264_pcm_rt/XXX.sd   (H264, kualitas SD)
 *   3. rtsp://192.168.1.1:554/264_pcm_rt/XXX.fhd  (H264, kualitas FHD)
 *   4. rtsp://192.168.1.1:554/avi_pcm_rt/front.hd (JPEG/AVI front)
 *   5. rtsp://192.168.1.1:554/avi_pcm_rt/front.sd (JPEG/AVI front SD)
 *   6. rtsp://192.168.1.1:554/264_pcm_rt/rear.hd
 *   7. rtsp://192.168.1.1:554/
 *
 * Alur: PatientActivity -> Ms2CameraActivity (jika MS2 WiFi terdeteksi)
 */
public class Ms2CameraActivity extends Activity {

    private static final String TAG = "Ms2CameraActivity";

    /** IP default gateway kamera MS2 */
    private static final String MS2_IP = "192.168.1.1";

    /** Daftar URL RTSP yang akan dicoba secara berurutan */
    private static final String[] CANDIDATE_URLS = {
        "rtsp://" + MS2_IP + ":554/264_pcm_rt/XXX.hd",
        "rtsp://" + MS2_IP + ":554/264_pcm_rt/XXX.sd",
        "rtsp://" + MS2_IP + ":554/264_pcm_rt/XXX.fhd",
        "rtsp://" + MS2_IP + ":554/avi_pcm_rt/front.hd",
        "rtsp://" + MS2_IP + ":554/avi_pcm_rt/front.sd",
        "rtsp://" + MS2_IP + ":554/264_pcm_rt/rear.hd",
        "rtsp://" + MS2_IP + ":554/",
    };

    /** Timeout per URL sebelum mencoba URL berikutnya (ms) */
    private static final long URL_TIMEOUT_MS = 6000;

    // --- Views ---
    private IjkVideoView mVideoView;
    private View flVideoContainer;
    private ProgressBar  pbLoading;
    private TextView     tvStatus;
    
    // Top & Bottom Bar Controls
    private ImageButton  btnBack;
    private ImageButton  btnCapture;
    private ImageButton  btnGallery;
    private ImageButton  btnModeSwitch;
    
    // Recording UI
    private LinearLayout llRecordTimer;
    private ImageView    ivVideoDot;
    private TextView     tvRecordTime;

    // --- State ---
    private int     mCurrentUrlIndex = 0;
    private boolean mStreamStarted   = false;
    private final Handler mUiHandler  = new Handler(Looper.getMainLooper());
    private Runnable mTimeoutRunnable;
    private Runnable mClockRunnable;
    
    // Mode State
    private boolean mIsVideoMode = false;
    private boolean mIsRecording = false;
    private long    mRecordingStartTime = 0;
    private Runnable mRecordTimerRunnable;
    
    // Zoom Logic
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;

    // Session Logic
    private File mSessionDir;
    private File mSnapsDir;
    private File mVidsDir;
    private String mPatientNama = "-";
    private String mPatientNik = "-";
    private String mPatientRs = "-";
    private String mPatientNrm = null;
    private long mPatientDobUtc = -1;

    // Video Recorder
    private ViewRecorder mRecorder;

    // ─────────────────────────────────────────────────────────────────────────

    public static void start(Context context) {
        context.startActivity(new Intent(context, Ms2CameraActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_ms2_camera);

        mVideoView = findViewById(R.id.iv_ms2_stream);
        flVideoContainer = findViewById(R.id.fl_video_container);
        pbLoading  = findViewById(R.id.pb_ms2_loading);
        tvStatus   = findViewById(R.id.tv_ms2_status);
        
        btnBack    = findViewById(R.id.btn_ms2_back);
        btnCapture = findViewById(R.id.btn_ms2_capture);
        btnGallery = findViewById(R.id.btn_ms2_gallery);
        btnModeSwitch = findViewById(R.id.btn_ms2_mode_switch);
        
        llRecordTimer = findViewById(R.id.ll_record_timer);
        ivVideoDot    = findViewById(R.id.camera_video_dot);
        tvRecordTime  = findViewById(R.id.tvRecordTime);

        // Mode real-time: aktifkan fflags=nobuffer + infbuf=1 + rtsp/udp
        // Ini menghilangkan jeda/delay 1 detik saat menggerakkan kamera MS2
        mVideoView.setRealtime(true);

        mVideoView.setRealtime(true);

        // Overlay Data & Session Initialization
        mPatientNama = getIntent().getStringExtra("patient_nama");
        if (mPatientNama == null || mPatientNama.isEmpty()) mPatientNama = "-";
        
        mPatientNik = getIntent().getStringExtra("patient_nik");
        if (mPatientNik == null || mPatientNik.isEmpty()) mPatientNik = "-";
        
        mPatientRs = getIntent().getStringExtra("patient_rs");
        if (mPatientRs == null || mPatientRs.isEmpty()) mPatientRs = "-";
        
        mPatientNrm = getIntent().getStringExtra("patient_nrm");
        if (mPatientNrm != null && mPatientNrm.isEmpty()) mPatientNrm = null;
        
        mPatientDobUtc = getIntent().getLongExtra("patient_dob_utc", -1);

        String dateFolder = com.idn.kmed.cervexa.utils.StorageUtils.INSTANCE.todayDateFolderWIB();
        String patientFolder = mPatientNik + "_" + mPatientNama.replace(" ", "_");
        
        mSessionDir = com.idn.kmed.cervexa.utils.StorageUtils.INSTANCE.ensureSessionDir(this, dateFolder, patientFolder);
        mSnapsDir = com.idn.kmed.cervexa.utils.StorageUtils.INSTANCE.ensureChildDir(mSessionDir, "Snapshots");
        mVidsDir = com.idn.kmed.cervexa.utils.StorageUtils.INSTANCE.ensureChildDir(mSessionDir, "Video");

        // Write session.json for MediaRepository to read
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            json.put("nama", mPatientNama);
            json.put("nik", mPatientNik);
            json.put("nrm", mPatientNrm);
            json.put("rs", mPatientRs);
            json.put("dob_utc", mPatientDobUtc);
            com.idn.kmed.cervexa.utils.StorageUtils.INSTANCE.writeSessionMetadata(mSessionDir, json.toString());
        } catch (Exception e) {
            Log.e(TAG, "Failed to write session.json", e);
        }

        TextView tvOverlayInfo = findViewById(R.id.tvOverlayInfo);
        if (tvOverlayInfo != null) {
            String top = mPatientNrm == null ? mPatientRs : mPatientRs + " / " + mPatientNrm;
            String text = top;
            if (!mPatientNama.isEmpty() && !mPatientNama.equals("-")) {
                text += " - " + mPatientNama;
            }
            tvOverlayInfo.setText(text);
        }

        TextView tvOverlayClock = findViewById(R.id.tvOverlayClock);
        if (tvOverlayClock != null) {
            startClock(tvOverlayClock);
        }

        // Gunakan TextureView agar bisa getBitmap() untuk capture
        mVideoView.setRender(IjkVideoView.RENDER_TEXTURE_VIEW);
        mVideoView.setAspectRatio(3); // fit screen

        setupPlayerListeners();
        setupGestureZoom();

        btnBack.setOnClickListener(v -> finish());
        
        Button btnSelesai = findViewById(R.id.btn_selesai);
        if (btnSelesai != null) {
            btnSelesai.setOnClickListener(v -> showExitConfirmDialog());
        }
        
        btnGallery.setOnClickListener(v -> {
            // Open GalleryListActivity
            Intent intent = new Intent(this, GalleryListActivity.class);
            startActivity(intent);
        });
        
        btnModeSwitch.setOnClickListener(v -> toggleCameraMode());

        btnCapture.setOnClickListener(v -> {
            if (mIsVideoMode) {
                toggleRecording();
            } else {
                captureFrame();
            }
        });

        setStatus("Menghubungkan ke kamera MS2...\n(mencoba URL 1 dari " + CANDIDATE_URLS.length + ")");
    }
    
    private void setupGestureZoom() {
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                mScaleFactor *= detector.getScaleFactor();
                // Limit zoom from 1.0x to 5.0x
                mScaleFactor = Math.max(1.0f, Math.min(mScaleFactor, 5.0f)); 
                mVideoView.setScaleX(mScaleFactor);
                mVideoView.setScaleY(mScaleFactor);
                return true;
            }
        });
    }

    private void startClock(final TextView tvClock) {
        final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault());
        mClockRunnable = new Runnable() {
            @Override
            public void run() {
                tvClock.setText(sdf.format(new java.util.Date()));
                mUiHandler.postDelayed(this, 1000);
            }
        };
        mUiHandler.post(mClockRunnable);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mScaleGestureDetector != null) {
            mScaleGestureDetector.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCurrentUrlIndex = 0;
        tryNextUrl();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mIsRecording) {
            toggleRecording();
        }
        cancelTimeout();
        if (mUiHandler != null) {
            mUiHandler.removeCallbacksAndMessages(null);
        }
        if (mVideoView != null) {
            mVideoView.stopPlayback();
        }
        mStreamStarted = false;
    }

    private void showExitConfirmDialog() {
        new android.app.AlertDialog.Builder(this)
            .setTitle("Selesaikan Sesi?")
            .setMessage("Anda yakin ingin menyelesaikan sesi ini?")
            .setPositiveButton("Ya", (dialog, which) -> showSaveConfirmDialog())
            .setNegativeButton("Tidak", null)
            .show();
    }

    private void showSaveConfirmDialog() {
        new android.app.AlertDialog.Builder(this)
            .setTitle("Simpan Sesi")
            .setMessage("Sesi ini dapat disimpan sebagai dokumen laporan/rekam medis atau hanya menyimpan media yang sudah diambil.")
            .setPositiveButton("Simpan Rekam Medis (PDF)", (dialog, which) -> generateAndActionPdf(true, false))
            .setNeutralButton("Unduh PDF", (dialog, which) -> generateAndActionPdf(true, true))
            .setNegativeButton("Batal", (dialog, which) -> finish())
            .show();
    }

    private void generateAndActionPdf(boolean sessionOnly, boolean download) {
        pbLoading.setVisibility(View.VISIBLE);
        tvStatus.setVisibility(View.VISIBLE);
        setStatus("Membuat PDF...");

        new Thread(() -> {
            File[] snapFilesArr = mSnapsDir.exists() ? mSnapsDir.listFiles() : new File[0];
            File[] vidFilesArr = mVidsDir.exists() ? mVidsDir.listFiles() : new File[0];

            java.util.List<File> snaps = snapFilesArr != null ? java.util.Arrays.asList(snapFilesArr) : new java.util.ArrayList<>();
            java.util.List<File> vids = vidFilesArr != null ? java.util.Arrays.asList(vidFilesArr) : new java.util.ArrayList<>();

            long ts = System.currentTimeMillis();
            String fname = sessionOnly ? "cervexa_sesi_" + ts + ".pdf" : "cervexa_pasien_" + ts + ".pdf";
            File outFile = new File(getCacheDir(), fname);

            Long dobObj = mPatientDobUtc > 0 ? mPatientDobUtc : null;

            File pdf = null;
            try {
                pdf = com.idn.kmed.cervexa.utils.PdfReportHelper.INSTANCE.generateSessionPdf(
                    outFile,
                    mPatientNama,
                    mPatientNik,
                    mPatientRs,
                    mPatientNrm,
                    dobObj,
                    -1, // sessionId
                    null, // sessionCode
                    null, // startedAt
                    null, // completedAt
                    snaps,
                    vids,
                    null, // aiClassification
                    null, // aiConfidenceScore
                    false // aiIsFallback
                );
            } catch (Exception e) {
                Log.e(TAG, "Error generating PDF", e);
            }
            final File finalPdf = pdf;

            mUiHandler.post(() -> {
                pbLoading.setVisibility(View.GONE);
                tvStatus.setVisibility(View.GONE);

                if (finalPdf == null) {
                    Toast.makeText(Ms2CameraActivity.this, "Gagal membuat PDF", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (download) {
                    boolean ok = com.idn.kmed.cervexa.utils.PrintHelper.INSTANCE.downloadPdf(Ms2CameraActivity.this, finalPdf, fname);
                    Toast.makeText(Ms2CameraActivity.this, ok ? "PDF tersimpan di folder Downloads" : "Gagal menyimpan PDF", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    String label = sessionOnly ? "Sesi Pemeriksaan" : "Data Pasien";
                    com.idn.kmed.cervexa.utils.PrintHelper.INSTANCE.printPdf(Ms2CameraActivity.this, finalPdf, "Cervexa — " + label);
                    finish();
                }
            });
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimeout();
        if (mClockRunnable != null) {
            mUiHandler.removeCallbacks(mClockRunnable);
        }
        if (mVideoView != null) {
            mVideoView.release(true);
        }
    }

    // ── Stream Logic ──────────────────────────────────────────────────────────

    /** Coba URL berikutnya dalam daftar kandidat */
    private void tryNextUrl() {
        if (mCurrentUrlIndex >= CANDIDATE_URLS.length) {
            // Semua URL sudah dicoba, semuanya gagal
            showLoading(false);
            setStatus("❌ Gagal terhubung ke kamera MS2 setelah mencoba semua endpoint.\n\n" +
                      "Pastikan:\n" +
                      "• HP terhubung ke WiFi wifi_camera_MS2_XXXX\n" +
                      "• Kamera MS2 sudah menyala\n" +
                      "• Mobile data sudah dimatikan\n\n" +
                      "Coba tekan tombol Back dan buka ulang kamera.");
            return;
        }

        String url = CANDIDATE_URLS[mCurrentUrlIndex];
        Log.i(TAG, "Mencoba URL [" + (mCurrentUrlIndex + 1) + "/" + CANDIDATE_URLS.length + "]: " + url);
        setStatus("Menghubungkan ke kamera MS2...\n(mencoba URL " + (mCurrentUrlIndex + 1) +
                  " dari " + CANDIDATE_URLS.length + ")\n\n" + url);
        showLoading(true);

        mStreamStarted = false;
        mVideoView.stopPlayback();
        mVideoView.setVideoPath(url);
        mVideoView.start();

        // Timeout: jika tidak ada preparedListener dalam waktu URL_TIMEOUT_MS, coba URL berikutnya
        cancelTimeout();
        mTimeoutRunnable = () -> {
            if (!mStreamStarted) {
                Log.w(TAG, "Timeout URL [" + mCurrentUrlIndex + "]: " + url);
                mCurrentUrlIndex++;
                tryNextUrl();
            }
        };
        mUiHandler.postDelayed(mTimeoutRunnable, URL_TIMEOUT_MS);
    }

    private void cancelTimeout() {
        if (mTimeoutRunnable != null) {
            mUiHandler.removeCallbacks(mTimeoutRunnable);
            mTimeoutRunnable = null;
        }
    }

    private void setupPlayerListeners() {
        // Stream berhasil di-prepare → tampilkan video
        mVideoView.setOnPreparedListener(iMediaPlayer -> {
            Log.i(TAG, "✅ Stream berhasil! URL: " + CANDIDATE_URLS[mCurrentUrlIndex]);
            mStreamStarted = true;
            cancelTimeout();
            showLoading(false);
            tvStatus.setVisibility(View.GONE);
            iMediaPlayer.setLooping(false);
            iMediaPlayer.start();
            // Simpan URL yang berhasil agar bisa ditampilkan
            runOnUiThread(() ->
                Toast.makeText(this, "📹 Stream MS2 aktif", Toast.LENGTH_SHORT).show());
        });

        // Error pada URL ini → coba URL berikutnya
        mVideoView.setOnErrorListener((iMediaPlayer, what, extra) -> {
            Log.e(TAG, "Error pada URL [" + mCurrentUrlIndex + "]: what=" + what + " extra=" + extra);
            cancelTimeout();
            if (!mStreamStarted) {
                mCurrentUrlIndex++;
                mUiHandler.postDelayed(this::tryNextUrl, 500);
            }
            return true; // true = kita sudah handle error
        });

        // Stream selesai (tidak seharusnya untuk live stream, tapi handle jika terjadi)
        mVideoView.setOnCompletionListener(iMediaPlayer -> {
            if (mStreamStarted) {
                Log.w(TAG, "Stream selesai, mencoba reconnect...");
                mStreamStarted = false;
                mCurrentUrlIndex = 0;
                mUiHandler.postDelayed(this::tryNextUrl, 1000);
            }
        });
    }

    // ── Capture ───────────────────────────────────────────────────────────────

    private void toggleCameraMode() {
        mIsVideoMode = !mIsVideoMode;
        if (mIsVideoMode) {
            btnModeSwitch.setImageResource(R.drawable.drawable_hor_photo); 
            btnCapture.setBackgroundResource(R.drawable.btn_new_shutter_video); 
            Toast.makeText(this, "Mode Video", Toast.LENGTH_SHORT).show();
        } else {
            if (mIsRecording) {
                toggleRecording(); // Stop recording if switching modes
            }
            btnModeSwitch.setImageResource(R.drawable.drawable_hor_video); 
            btnCapture.setBackgroundResource(R.drawable.btn_new_shutter); 
            Toast.makeText(this, "Mode Foto", Toast.LENGTH_SHORT).show();
        }
    }

    private void toggleRecording() {
        if (!mStreamStarted) {
            Toast.makeText(this, "Stream belum aktif", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mIsRecording) {
            // Stop recording
            mIsRecording = false;
            llRecordTimer.setVisibility(View.INVISIBLE);
            ivVideoDot.clearAnimation();
            if (mRecordTimerRunnable != null) {
                mUiHandler.removeCallbacks(mRecordTimerRunnable);
            }
            if (mRecorder != null) {
                mRecorder.stop();
                String path = mRecorder.getOutputPath();
                MediaScannerConnection.scanFile(this, new String[]{path}, new String[]{"video/mp4"}, null);
                mRecorder = null;
            }
            Toast.makeText(this, "Perekaman Selesai, video tersimpan di Galeri", Toast.LENGTH_SHORT).show();
            btnCapture.setBackgroundResource(R.drawable.btn_new_shutter_video);
        } else {
            // Start recording
            mIsRecording = true;
            mRecordingStartTime = System.currentTimeMillis();
            llRecordTimer.setVisibility(View.VISIBLE);
            btnCapture.setBackgroundResource(R.drawable.btn_shutter_video_recording); 

            // Animate red dot
            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(500);
            anim.setStartOffset(20);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);
            ivVideoDot.startAnimation(anim);

            try {
                String outputPath = new File(mVidsDir, "MS2_Video_" + System.currentTimeMillis() + ".mp4").getAbsolutePath();
                mRecorder = new ViewRecorder(mVideoView, outputPath);
                mRecorder.start();
                Toast.makeText(this, "Mulai merekam video...", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e(TAG, "Gagal memulai rekaman", e);
                Toast.makeText(this, "Gagal merekam video", Toast.LENGTH_SHORT).show();
            }

            mRecordTimerRunnable = new Runnable() {
                @Override
                public void run() {
                    if (!mIsRecording) return;
                    long elapsed = System.currentTimeMillis() - mRecordingStartTime;
                    int seconds = (int) (elapsed / 1000);
                    int h = seconds / 3600;
                    int m = (seconds % 3600) / 60;
                    int s = seconds % 60;
                    tvRecordTime.setText(String.format("%02d:%02d:%02d", h, m, s));
                    mUiHandler.postDelayed(this, 1000);
                }
            };
            mUiHandler.post(mRecordTimerRunnable);
        }
    }

    private TextureView findTextureView(ViewGroup group) {
        for (int i = 0; i < group.getChildCount(); i++) {
            View child = group.getChildAt(i);
            if (child instanceof TextureView) {
                return (TextureView) child;
            } else if (child instanceof ViewGroup) {
                TextureView tv = findTextureView((ViewGroup) child);
                if (tv != null) return tv;
            }
        }
        return null;
    }

    private void captureFrame() {
        if (!mStreamStarted) {
            Toast.makeText(this, "Stream belum aktif", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Bitmap bmp = null;
            TextureView tv = findTextureView(mVideoView);
            
            if (tv != null) {
                bmp = tv.getBitmap();
            } else {
                bmp = mVideoView.getBitmap();
            }

            if (bmp == null) {
                Toast.makeText(this, "Gagal mengambil frame dari stream", Toast.LENGTH_SHORT).show();
                return;
            }

            final Bitmap finalBmp = bmp;
            ExecutorService saver = Executors.newSingleThreadExecutor();
            saver.submit(() -> {
                try {
                    String fileName = "MS2_" + System.currentTimeMillis() + ".jpg";
                    File imageFile = new File(mSnapsDir, fileName);

                    try (OutputStream out = new FileOutputStream(imageFile)) {
                        finalBmp.compress(Bitmap.CompressFormat.JPEG, 95, out);
                    }

                    MediaScannerConnection.scanFile(this, new String[]{imageFile.getAbsolutePath()}, new String[]{"image/jpeg"}, null);

                    runOnUiThread(() ->
                            Toast.makeText(this, "📸 Gambar tersimpan di Galeri: " + fileName,
                                    Toast.LENGTH_SHORT).show());
                } catch (Exception e) {
                    Log.e(TAG, "Save capture failed: " + e.getMessage(), e);
                    runOnUiThread(() ->
                            Toast.makeText(this, "Gagal menyimpan gambar", Toast.LENGTH_SHORT).show());
                }
            });

        } catch (Exception e) {
            Log.e(TAG, "captureFrame error: " + e.getMessage(), e);
            Toast.makeText(this, "Gagal mengambil frame: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // ── UI Helpers ────────────────────────────────────────────────────────────

    private void showLoading(boolean show) {
        mUiHandler.post(() -> pbLoading.setVisibility(show ? View.VISIBLE : View.GONE));
    }

    private void setStatus(String msg) {
        mUiHandler.post(() -> {
            tvStatus.setVisibility(View.VISIBLE);
            tvStatus.setText(msg);
        });
    }
}
