package com.gizthon.camera.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jieli.stream.p016dv.running2.p017ui.widget.media.IjkVideoView;
import com.weioa.KmedHealthIndonesia.R;

import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
 *   6. rtsp://192.168.1.1:554/                     (fallback root)
 *
 * Alur: PatientActivity → Ms2CameraActivity (jika MS2 WiFi terdeteksi)
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

    // ── Views ─────────────────────────────────────────────────────────────────
    private IjkVideoView mVideoView;
    private ProgressBar  pbLoading;
    private TextView     tvStatus;
    private ImageButton  btnBack;
    private ImageButton  btnCapture;

    // ── State ─────────────────────────────────────────────────────────────────
    private int     mCurrentUrlIndex = 0;
    private boolean mStreamStarted   = false;
    private final Handler mUiHandler  = new Handler(Looper.getMainLooper());
    private Runnable mTimeoutRunnable;

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
        pbLoading  = findViewById(R.id.pb_ms2_loading);
        tvStatus   = findViewById(R.id.tv_ms2_status);
        btnBack    = findViewById(R.id.btn_ms2_back);
        btnCapture = findViewById(R.id.btn_ms2_capture);

        // Gunakan TextureView agar bisa getBitmap() untuk capture
        mVideoView.setRender(IjkVideoView.RENDER_TEXTURE_VIEW);
        mVideoView.setAspectRatio(3); // fit screen

        setupPlayerListeners();

        btnBack.setOnClickListener(v -> finish());

        btnCapture.setOnClickListener(v -> captureFrame());

        setStatus("Menghubungkan ke kamera MS2...\n(mencoba URL 1 dari " + CANDIDATE_URLS.length + ")");
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
        cancelTimeout();
        if (mVideoView != null) {
            mVideoView.stopPlayback();
        }
        mStreamStarted = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimeout();
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

    private void captureFrame() {
        if (!mStreamStarted) {
            Toast.makeText(this, "Stream belum aktif", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Ambil bitmap dari VideoView via drawing cache
            mVideoView.setDrawingCacheEnabled(true);
            mVideoView.buildDrawingCache();
            Bitmap cache = mVideoView.getDrawingCache();
            if (cache == null) {
                Toast.makeText(this, "Gagal mengambil frame dari stream", Toast.LENGTH_SHORT).show();
                return;
            }
            final Bitmap bmp = Bitmap.createBitmap(cache);
            mVideoView.setDrawingCacheEnabled(false);

            ExecutorService saver = Executors.newSingleThreadExecutor();
            saver.submit(() -> {
                try {
                    String fileName = "MS2_" + System.currentTimeMillis() + ".jpg";
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
                    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                    values.put(MediaStore.Images.Media.RELATIVE_PATH,
                            Environment.DIRECTORY_PICTURES + "/Cervexa");

                    Uri uri = getContentResolver()
                            .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                    if (uri != null) {
                        try (OutputStream out = getContentResolver().openOutputStream(uri)) {
                            bmp.compress(Bitmap.CompressFormat.JPEG, 95, out);
                        }
                        runOnUiThread(() ->
                                Toast.makeText(this, "📸 Gambar tersimpan: " + fileName,
                                        Toast.LENGTH_SHORT).show());
                    }
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
