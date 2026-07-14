package com.idn.kmed.cervexa.video

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Stub: VideoActivity tidak lagi digunakan.
 * Kamera sekarang menggunakan Jieli SDK via MainActivity.
 * Dipertahankan hanya untuk kompatibilitas referensi lama.
 */
class VideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Redirect ke Jieli SDK MainActivity
        startActivity(Intent(this, Class.forName("com.jieli.stream.p016dv.running2.p017ui.activity.MainActivity")))
        finish()
    }
}