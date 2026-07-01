package com.gizthon.camera.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gizthon.camera.model.CervexaDatabase
import com.gizthon.camera.model.Patient
import com.jaeger.library.StatusBarUtil
import com.weioa.KmedHealthIndonesia.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors

/**
 * Daftar histori pasien yang sudah terdaftar.
 * User bisa search, tap untuk lanjutkan pemeriksaan.
 */
class PatientHistoryActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(Intent(context, PatientHistoryActivity::class.java))
        }
    }

    private val executor = Executors.newSingleThreadExecutor()
    private val db by lazy { CervexaDatabase.getInstance(this) }
    private val patients = mutableListOf<Patient>()
    private lateinit var adapter: PatientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_history)
        StatusBarUtil.setColorNoTranslucent(this, Color.parseColor("#1565C0"))

        val rvPatients = findViewById<RecyclerView>(R.id.rvPatients)
        adapter = PatientAdapter(patients) { patient -> openCamera(patient) }
        rvPatients.layoutManager = LinearLayoutManager(this)
        rvPatients.adapter = adapter

        findViewById<ImageView>(R.id.ivBack).setOnClickListener { finish() }
        findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etSearch)
            ?.addTextChangedListener(object : android.text.TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, st: Int, c: Int, a: Int) {}
                override fun onTextChanged(s: CharSequence?, st: Int, b: Int, c: Int) { searchPatients(s?.toString() ?: "") }
                override fun afterTextChanged(s: android.text.Editable?) {}
            })

        loadPatients()
    }

    override fun onResume() {
        super.onResume()
        loadPatients()
    }

    override fun onDestroy() {
        super.onDestroy()
        executor.shutdown()
    }

    private fun loadPatients() {
        executor.execute {
            val list = db.patientDao().getAllPatients()
            runOnUiThread {
                patients.clear()
                patients.addAll(list)
                adapter.notifyDataSetChanged()
                updateEmptyState(list.isEmpty())
            }
        }
    }

    private fun searchPatients(query: String) {
        executor.execute {
            val list = if (query.isBlank()) db.patientDao().getAllPatients()
                       else db.patientDao().searchPatients(query)
            runOnUiThread {
                patients.clear()
                patients.addAll(list)
                adapter.notifyDataSetChanged()
                updateEmptyState(list.isEmpty())
            }
        }
    }

    private fun updateEmptyState(isEmpty: Boolean) {
        findViewById<TextView>(R.id.tvEmpty)?.visibility =
            if (isEmpty) android.view.View.VISIBLE else android.view.View.GONE
    }

    private fun openCamera(patient: Patient) {
        startActivity(Intent(this, UVCUSBCameraActivity::class.java).apply {
            putExtra(PatientActivity.EXTRA_PATIENT_ID,   patient.id)
            putExtra(PatientActivity.EXTRA_PATIENT_NAMA, patient.nama)
            putExtra(PatientActivity.EXTRA_PATIENT_NIK,  patient.nik)
            putExtra(PatientActivity.EXTRA_PATIENT_RS,   patient.namaRs)
            putExtra(PatientActivity.EXTRA_PATIENT_NRM,  patient.noRm)
            putExtra(PatientActivity.EXTRA_PATIENT_DOB,  patient.tanggalLahir)
        })
    }

    // ── Inner Adapter ─────────────────────────────────────────────────────────
    private inner class PatientAdapter(
        private val list: List<Patient>,
        private val onTap: (Patient) -> Unit
    ) : RecyclerView.Adapter<PatientAdapter.VH>() {

        inner class VH(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
            val tvNama:    TextView = itemView.findViewById(R.id.tvNama)
            val tvNik:     TextView = itemView.findViewById(R.id.tvNik)
            val tvRS:      TextView = itemView.findViewById(R.id.tvRS)
            val tvTanggal: TextView = itemView.findViewById(R.id.tvDob)
            val tvInisial: TextView = itemView.findViewById(R.id.tvAvatar)
        }

        override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): VH =
            VH(android.view.LayoutInflater.from(parent.context).inflate(R.layout.item_patient, parent, false))

        override fun getItemCount() = list.size

        override fun onBindViewHolder(holder: VH, position: Int) {
            val p = list[position]
            holder.tvNama.text    = p.nama
            holder.tvNik.text     = "NIK: ${p.nik}"
            holder.tvRS.text      = if (p.namaRs.isNotBlank()) "🏥 ${p.namaRs}" else ""
            val fmt = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            holder.tvTanggal.text = if (p.tanggalLahir.isNotBlank()) "🎂 ${p.tanggalLahir}" else ""
            holder.tvInisial.text = p.nama.take(1).uppercase()
            holder.itemView.setOnClickListener { onTap(p) }
        }
    }
}
