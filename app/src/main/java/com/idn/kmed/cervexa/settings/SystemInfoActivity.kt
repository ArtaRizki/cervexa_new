package com.idn.kmed.cervexa.settings

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import android.widget.TextView
import androidx.core.content.edit
import com.idn.kmed.cervexa.utils.StorageUtils
import com.weioa.KmedHealthIndonesia.R
import com.idn.kmed.cervexa.gallery.MediaPagerActivity
import com.idn.kmed.cervexa.gallery.MediaPagerActivityLand
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

class SystemInfoActivity : AppCompatActivity() {
    private var statisticsTimer: Timer? = null
    private lateinit var tvDecoder: TextView
    private lateinit var tvLatency: TextView
    private lateinit var tvResolution: TextView
    private lateinit var tvLastTest: TextView
    private lateinit var btnSeeResult: View
    private var ivVideoImageResolution = Pair(0, 0)
    private var ss: AtomicBoolean = AtomicBoolean(false)
    private var canSS: AtomicBoolean = AtomicBoolean(false)
    private val prefs by lazy {
        getSharedPreferences(getString(R.string.pref_application), MODE_PRIVATE)
    }
    private val main = Handler(Looper.getMainLooper())
    private fun isLandscape(): Boolean =
        resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_system)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        tvDecoder = findViewById<TextView>(R.id.tvDecoder)
        tvLatency = findViewById<TextView>(R.id.tvLatency)
        tvResolution = findViewById<TextView>(R.id.tvResolution)
        tvLastTest = findViewById<TextView>(R.id.tvLastTest)
        btnSeeResult = findViewById<TextView>(R.id.btnSeeResult)
        val btnRetest = findViewById<MaterialButton>(R.id.btnRetest)

        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        btnSeeResult.setOnClickListener {
            val base = this.getExternalFilesDir("System")
            val dir = File(base, "Testing/${getPrefLatestImnageName()}")
            openPreview(dir,false)
        }

        tvDecoder.text = getPrefLatestDecodeer()
        tvLatency.text = getPrefLatestLatency()
        tvResolution.text = getPrefLatestResolution()
        tvLastTest.text = getPrefLatestDate()

        btnRetest.setOnClickListener {
            // Disabled in Jieli SDK mode
        }
    }

    private fun openPreview(file: File, isVideo: Boolean) {
        val paths = arrayListOf(file.absolutePath)
        val types = arrayListOf(if (isVideo) "VIDEO" else "IMAGE")

        val target = if (isLandscape())
            MediaPagerActivityLand::class.java
        else
            MediaPagerActivity::class.java

        startActivity(
            Intent(this, target).apply {
                putStringArrayListExtra("paths", paths)
                putStringArrayListExtra("types", types)
                putExtra("index", 0)
                putExtra("forceLandscape", isLandscape())
            }
        )
    }

    private fun getPrefLatestDate() : String{
        val lastes_test: String? = prefs.getString("date_test", "-")
        return lastes_test.toString()
    }

    private fun getPrefLatestDecodeer() : String {
        val lastes_test: String? = prefs.getString("decoder_test", "-")
        return lastes_test.toString()
    }

    private fun getPrefLatestLatency() : String {
        val lastes_test: String? = prefs.getString("latency_test", "-")
        return lastes_test.toString()
    }

    private fun getPrefLatestResolution() : String {
        val lastes_test: String? = prefs.getString("resolution_test", "-")
        return lastes_test.toString()
    }

    private fun getPrefLatestImnageName() : String {
        val lastes_test: String? = prefs.getString("result_image_name_test", "-")
        return lastes_test.toString()
    }

    private fun saveLatestTest(){
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm")
        val current = LocalDateTime.now().format(formatter)
        prefs.edit { putString("date_test", current) }
        prefs.edit { putString("decoder_test", tvDecoder.text.toString())}
        prefs.edit { putString("latency_test", tvLatency.text.toString())}
        prefs.edit { putString("resolution_test", tvResolution.text.toString())}
    }

    companion object {
        private const val DEBUG = true
    }
}