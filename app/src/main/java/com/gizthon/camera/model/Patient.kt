package com.gizthon.camera.model

import androidx.room.*

@Entity(
    tableName = "patients",
    indices = [Index(value = ["nik"], unique = true)]
)
data class Patient(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "nama")           var nama: String = "",
    @ColumnInfo(name = "nik")            var nik: String = "",
    @ColumnInfo(name = "tanggal_lahir")  var tanggalLahir: String = "",
    @ColumnInfo(name = "nama_rs")        var namaRs: String = "",
    @ColumnInfo(name = "no_rm")          var noRm: String = "",
    @ColumnInfo(name = "last_photo_path") var lastPhotoPath: String = "",
    @ColumnInfo(name = "photo_count")    var photoCount: Int = 0,
    @ColumnInfo(name = "created_at")     var createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "updated_at")     var updatedAt: Long = System.currentTimeMillis()
)
