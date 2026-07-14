package com.idn.kmed.cervexa.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.idn.kmed.cervexa.home.HomeActivity
import com.weioa.KmedHealthIndonesia.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    // TODO: Set ke false untuk mengaktifkan login kembali
    private val SKIP_AUTH = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            if (SKIP_AUTH) {
                // Langsung ke Home tanpa cek token/onboarding
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
                return@postDelayed
            }

            // Logika auth original (aktifkan kembali jika SKIP_AUTH = false)
            val prefApps = getSharedPreferences(getString(R.string.pref_application), MODE_PRIVATE)
            val onBoardingDone = prefApps.getBoolean("on_boarding", false)
            val next: Intent = if (onBoardingDone) {
                Intent(this, HomeActivity::class.java)
            } else {
                Intent(this, OnboardingActivity::class.java)
            }
            startActivity(next)
            finish()
        }, 1_500L)
    }
}