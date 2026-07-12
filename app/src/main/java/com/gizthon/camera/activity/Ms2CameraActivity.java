package com.gizthon.camera.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.weioa.KmedHealthIndonesia.R;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Activity khusus untuk menampilkan live stream kamera WiFi MS2 (Elikliv).
 *
 * TIDAK bergantung pada SDK Jieli (ClientManager, CommunicationService, dsb.).
 * Stream langsung diterima dari HTTP MJPEG endpoint kamera MS2:
 *   http://192.168.1.1:8080/?action=stream
 *
 * Alur: PatientActivity → Ms2CameraActivity (jika MS2 WiFi terdeteksi)
 */
public class Ms2CameraActivity extends Activity {

    private static final String TAG = "Ms2CameraActivity";

    /** URL MJPEG stream dari kamera MS2 */
    private static final String MS2_STREAM_URL  = "http://192.168.1.1:8080/?action=stream";
    /** Timeout koneksi awal */
    private static final int    CONNECT_TIMEOUT = 8000;
    /** Timeout baca */
    private static final int    READ_TIMEOUT    = 10000;

    // ── Views ─────────────────────────────────────────────────────────────────
    private android.widget.ImageView ivStream;
    private ProgressBar pbLoading;
    private TextView    tvStatus;
    private ImageButton btnBack;
    private ImageButton btnCapture;

    // ── State ─────────────────────────────────────────────────────────────────
    private final AtomicBoolean mRunning   = new AtomicBoolean(false);
    private ExecutorService     mExecutor;
    private final Handler       mUiHandler = new Handler(Looper.getMainLooper());

    // ─────────────────────────────────────────────────────────────────────────

    public static void start(Context context) {
        context.startActivity(new Intent(context, Ms2CameraActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fullscreen landscape
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_ms2_camera);

        ivStream   = findViewById(R.id.iv_ms2_stream);
        pbLoading  = findViewById(R.id.pb_ms2_loading);
        tvStatus   = findViewById(R.id.tv_ms2_status);
        btnBack    = findViewById(R.id.btn_ms2_back);
        btnCapture = findViewById(R.id.btn_ms2_capture);

        btnBack.setOnClickListener(v -> finish());

        btnCapture.setOnClickListener(v -> {
            // Ambil frame saat ini dari ImageView
            if (ivStream.getDrawable() != null) {
                ivStream.setDrawingCacheEnabled(true);
                Bitmap bmp = Bitmap.createBitmap(ivStream.getDrawingCache());
                ivStream.setDrawingCacheEnabled(false);
                saveCapture(bmp);
            } else {
                Toast.makeText(this, "Stream belum aktif", Toast.LENGTH_SHORT).show();
            }
        });

        setStatus("Menghubungkan ke kamera MS2...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        startStream();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopStream();
    }

    // ── Stream ────────────────────────────────────────────────────────────────

    private void startStream() {
        if (mRunning.get()) return;
        mRunning.set(true);
        mExecutor = Executors.newSingleThreadExecutor();
        showLoading(true);

        mExecutor.submit(() -> {
            try {
                Log.i(TAG, "Connecting to: " + MS2_STREAM_URL);
                URL url = new URL(MS2_STREAM_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(CONNECT_TIMEOUT);
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setDoInput(true);
                conn.connect();

                int responseCode = conn.getResponseCode();
                Log.i(TAG, "HTTP response: " + responseCode);

                if (responseCode != 200) {
                    setStatus("Kamera tidak merespons (HTTP " + responseCode + ")\nPastikan terhubung ke WiFi MS2");
                    showLoading(false);
                    return;
                }

                // Sembunyikan loading, tampilkan stream
                mUiHandler.post(() -> {
                    pbLoading.setVisibility(View.GONE);
                    tvStatus.setVisibility(View.GONE);
                });

                // Baca MJPEG stream (boundary-delimited JPEG frames)
                InputStream is = new BufferedInputStream(conn.getInputStream(), 16 * 1024);
                parseMjpegStream(is);

                conn.disconnect();

            } catch (Exception e) {
                Log.e(TAG, "Stream error: " + e.getMessage(), e);
                if (mRunning.get()) {
                    setStatus("Gagal terhubung ke kamera MS2.\n\n" +
                              "Pastikan:\n" +
                              "• HP terhubung ke WiFi " + "wifi_camera_MS2_XXXX\n" +
                              "• Kamera MS2 sudah menyala\n" +
                              "• Mobile data sudah dimatikan\n\n" +
                              "Error: " + e.getMessage());
                    showLoading(false);
                }
            }
        });
    }

    /**
     * Parser MJPEG stream sederhana.
     * MJPEG stream berupa rangkaian JPEG yang dipisahkan oleh boundary header HTTP.
     * Kita deteksi tiap frame dari marker JPEG: 0xFF 0xD8 (SOI) ... 0xFF 0xD9 (EOI).
     */
    private void parseMjpegStream(InputStream is) throws Exception {
        ByteArrayOutputStream frameBuffer = new ByteArrayOutputStream(64 * 1024);
        byte[] buf = new byte[4096];
        boolean inJpeg = false;
        int prev = -1;

        while (mRunning.get()) {
            int b = is.read();
            if (b == -1) break;

            // Deteksi SOI (FF D8)
            if (!inJpeg && prev == 0xFF && b == 0xD8) {
                inJpeg = true;
                frameBuffer.reset();
                frameBuffer.write(0xFF);
                frameBuffer.write(0xD8);
                prev = b;
                continue;
            }

            if (inJpeg) {
                frameBuffer.write(b);

                // Deteksi EOI (FF D9)
                if (prev == 0xFF && b == 0xD9) {
                    inJpeg = false;
                    byte[] jpegData = frameBuffer.toByteArray();
                    frameBuffer.reset();

                    if (jpegData.length > 1000) { // minimal frame valid ~1KB
                        final Bitmap bmp = BitmapFactory.decodeByteArray(jpegData, 0, jpegData.length);
                        if (bmp != null) {
                            mUiHandler.post(() -> {
                                if (mRunning.get()) {
                                    ivStream.setImageBitmap(bmp);
                                }
                            });
                        }
                    }
                }
            }

            prev = b;
        }
    }

    private void stopStream() {
        mRunning.set(false);
        if (mExecutor != null) {
            mExecutor.shutdownNow();
            mExecutor = null;
        }
    }

    // ── Capture ───────────────────────────────────────────────────────────────

    private void saveCapture(Bitmap bmp) {
        ExecutorService saver = Executors.newSingleThreadExecutor();
        saver.submit(() -> {
            try {
                String fileName = "MS2_" + System.currentTimeMillis() + ".jpg";
                android.content.ContentValues values = new android.content.ContentValues();
                values.put(android.provider.MediaStore.Images.Media.DISPLAY_NAME, fileName);
                values.put(android.provider.MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                values.put(android.provider.MediaStore.Images.Media.RELATIVE_PATH,
                        android.os.Environment.DIRECTORY_PICTURES + "/Cervexa");

                android.net.Uri uri = getContentResolver()
                        .insert(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                if (uri != null) {
                    try (java.io.OutputStream out = getContentResolver().openOutputStream(uri)) {
                        bmp.compress(Bitmap.CompressFormat.JPEG, 95, out);
                    }
                    mUiHandler.post(() ->
                            Toast.makeText(this, "📸 Gambar tersimpan: " + fileName, Toast.LENGTH_SHORT).show());
                }
            } catch (Exception e) {
                Log.e(TAG, "Save capture failed: " + e.getMessage(), e);
                mUiHandler.post(() ->
                        Toast.makeText(this, "Gagal menyimpan gambar", Toast.LENGTH_SHORT).show());
            }
        });
    }

    // ── UI Helpers ────────────────────────────────────────────────────────────

    private void showLoading(boolean show) {
        mUiHandler.post(() -> {
            pbLoading.setVisibility(show ? View.VISIBLE : View.GONE);
        });
    }

    private void setStatus(String msg) {
        mUiHandler.post(() -> {
            tvStatus.setVisibility(View.VISIBLE);
            tvStatus.setText(msg);
        });
    }
}
