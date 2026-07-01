package com.gizthon.camera.model

import androidx.room.*

@Dao
interface PatientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPatient(patient: Patient): Long

    @Update
    fun updatePatient(patient: Patient)

    @Query("SELECT * FROM patients ORDER BY updated_at DESC")
    fun getAllPatients(): List<Patient>

    @Query("SELECT * FROM patients WHERE id = :patientId LIMIT 1")
    fun getPatientById(patientId: Int): Patient?

    @Query("SELECT * FROM patients WHERE nik = :nik LIMIT 1")
    fun getPatientByNik(nik: String): Patient?

    @Query("SELECT * FROM patients WHERE nama LIKE '%' || :query || '%' OR nik LIKE '%' || :query || '%' ORDER BY updated_at DESC")
    fun searchPatients(query: String): List<Patient>

    @Query("DELETE FROM patients WHERE id = :patientId")
    fun deletePatientById(patientId: Int)

    @Query("SELECT COUNT(*) FROM patients")
    fun getTotalPatients(): Int

    @Query("UPDATE patients SET last_photo_path = :path, photo_count = photo_count + 1, updated_at = :now WHERE id = :patientId")
    fun updateLastPhoto(patientId: Int, path: String, now: Long)
}
