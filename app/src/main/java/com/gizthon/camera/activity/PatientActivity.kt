package com.gizthon.camera.activity

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.gizthon.camera.model.CervexaDatabase
import com.gizthon.camera.model.Patient
import com.jaeger.library.StatusBarUtil
import com.weioa.KmedHealthIndonesia.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors

/**
 * Form registrasi pasien sebelum pemeriksaan kamera.
 * Alur: SplashActivity → PatientActivity → UVCUSBCameraActivity
 */
class PatientActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PATIENT_ID   = "patient_id"
        const val EXTRA_PATIENT_NAMA = "patient_nama"
        const val EXTRA_PATIENT_NIK  = "patient_nik"
        const val EXTRA_PATIENT_RS   = "patient_rs"
        const val EXTRA_PATIENT_NRM  = "patient_nrm"
        const val EXTRA_PATIENT_DOB  = "patient_dob"

        @JvmStatic
        fun start(context: Context) {
            context.startActivity(Intent(context, PatientActivity::class.java))
        }
    }

    // ── Views ─────────────────────────────────────────────────────────────────
    private lateinit var tilNama: TextInputLayout
    private lateinit var tilNik:  TextInputLayout
    private lateinit var tilDob:  TextInputLayout
    private lateinit var tilRS:   TextInputLayout
    private lateinit var tilNrm:  TextInputLayout

    private lateinit var etNama: TextInputEditText
    private lateinit var etNik:  TextInputEditText
    private lateinit var etDob:  TextInputEditText
    private lateinit var etRS:   TextInputEditText
    private lateinit var etNrm:  TextInputEditText

    private lateinit var btnDaftar:     MaterialButton
    private lateinit var loadingOverlay: View

    // ── State ─────────────────────────────────────────────────────────────────
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("id", "ID"))
    private var selectedDob: String? = null

    private val executor = Executors.newSingleThreadExecutor()
    private val db by lazy { CervexaDatabase.getInstance(this) }

    // ─────────────────────────────────────────────────────────────────────────
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)
        StatusBarUtil.setColorNoTranslucent(this, Color.parseColor("#1565C0"))
        initViews()
        setupListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        executor.shutdown()
    }

    // ─────────────────────────────────────────────────────────────────────────
    private fun initViews() {
        tilNama = findViewById(R.id.tilNama)
        tilNik  = findViewById(R.id.tilNik)
        tilDob  = findViewById(R.id.tilDob)
        tilRS   = findViewById(R.id.tilRS)
        tilNrm  = findViewById(R.id.tilNrm)

        etNama = findViewById(R.id.etNama)
        etNik  = findViewById(R.id.etNik)
        etDob  = findViewById(R.id.etDob)
        etRS   = findViewById(R.id.etRS)
        etNrm  = findViewById(R.id.etNrm)

        btnDaftar     = findViewById(R.id.btnDaftar)
        loadingOverlay = findViewById(R.id.loadingOverlay)

        // Date field: non-editable, buka picker saat diklik
        etDob.isFocusable  = false
        etDob.isClickable  = true
    }

    private fun setupListeners() {
        findViewById<View>(R.id.ivBack).setOnClickListener { finish() }

        etDob.setOnClickListener            { showDatePicker() }
        tilDob.setEndIconOnClickListener    { showDatePicker() }

        listOf(etNama to tilNama, etNik to tilNik, etRS to tilRS).forEach { (et, til) ->
            et.addTextChangedListener(object : android.text.TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, st: Int, c: Int, a: Int) {}
                override fun onTextChanged(s: CharSequence?, st: Int, b: Int, c: Int) { til.error = null }
                override fun afterTextChanged(s: android.text.Editable?) {}
            })
        }

        btnDaftar.setOnClickListener {
            hideKeyboard()
            handleRegistration()
        }
    }

    // ── Date Picker ───────────────────────────────────────────────────────────
    private fun showDatePicker() {
        val cal = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, year, month, day ->
                val selected = Calendar.getInstance().apply { set(year, month, day) }
                selectedDob = dateFormat.format(selected.time)
                etDob.setText(selectedDob)
                tilDob.error = null
            },
            cal.get(Calendar.YEAR) - 30,
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.maxDate = System.currentTimeMillis()
            datePicker.minDate = Calendar.getInstance().apply { add(Calendar.YEAR, -130) }.timeInMillis
            show()
            getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#1565C0"))
            getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#757575"))
        }
    }

    // ── Registration ──────────────────────────────────────────────────────────
    private fun handleRegistration() {
        if (!validate()) return

        val nama = etNama.text?.toString()?.trim() ?: ""
        val nik  = etNik.text?.toString()?.trim()  ?: ""
        val rs   = etRS.text?.toString()?.trim()   ?: ""
        val nrm  = etNrm.text?.toString()?.trim()  ?: ""
        val dob  = selectedDob ?: ""

        setLoading(true)

        executor.execute {
            val existing = db.patientDao().getPatientByNik(nik)
            runOnUiThread {
                setLoading(false)
                if (existing != null) {
                    executor.execute {
                        db.patientDao().updatePatient(existing.copy(
                            nama = nama, namaRs = rs, noRm = nrm,
                            tanggalLahir = dob, updatedAt = System.currentTimeMillis()
                        ))
                        runOnUiThread { openCamera(existing.id, nama, nik, rs, nrm, dob) }
                    }
                } else {
                    executor.execute {
                        val newId = db.patientDao().insertPatient(
                            Patient(nama = nama, nik = nik, tanggalLahir = dob, namaRs = rs, noRm = nrm)
                        )
                        runOnUiThread { openCamera(newId.toInt(), nama, nik, rs, nrm, dob) }
                    }
                }
            }
        }
    }

    private fun openCamera(patientId: Int, nama: String, nik: String, rs: String, nrm: String, dob: String) {
        startActivity(Intent(this, UVCUSBCameraActivity::class.java).apply {
            putExtra(EXTRA_PATIENT_ID,   patientId)
            putExtra(EXTRA_PATIENT_NAMA, nama)
            putExtra(EXTRA_PATIENT_NIK,  nik)
            putExtra(EXTRA_PATIENT_RS,   rs)
            putExtra(EXTRA_PATIENT_NRM,  nrm)
            putExtra(EXTRA_PATIENT_DOB,  dob)
        })
    }

    // ── Validation ────────────────────────────────────────────────────────────
    private fun validate(): Boolean {
        var ok = true
        if (etNama.text.isNullOrBlank()) { tilNama.error = "Nama wajib diisi"; ok = false }
        val nik = etNik.text?.toString()?.trim() ?: ""
        when {
            nik.isBlank()                       -> { tilNik.error = "NIK wajib diisi"; ok = false }
            nik.length != 16 || !nik.all { it.isDigit() } -> { tilNik.error = "NIK harus 16 digit angka"; ok = false }
        }
        if (selectedDob == null) { tilDob.error = "Tanggal lahir wajib diisi"; ok = false }
        if (etRS.text.isNullOrBlank()) { tilRS.error = "Nama Rumah Sakit wajib diisi"; ok = false }
        if (!ok) Toast.makeText(this, "Periksa kembali data yang diisi", Toast.LENGTH_SHORT).show()
        return ok
    }

    // ── Helpers ───────────────────────────────────────────────────────────────
    private fun setLoading(show: Boolean) {
        loadingOverlay.visibility = if (show) View.VISIBLE else View.GONE
        btnDaftar.isEnabled = !show
    }

    private fun hideKeyboard() {
        currentFocus?.let {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
                ?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}
