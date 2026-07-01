package com.gizthon.camera.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Patient::class], version = 2, exportSchema = false)
abstract class CervexaDatabase : RoomDatabase() {
    abstract fun patientDao(): PatientDao

    companion object {
        @Volatile private var INSTANCE: CervexaDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): CervexaDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    CervexaDatabase::class.java,
                    "cervexa_db"
                )
                .fallbackToDestructiveMigration() // Dev mode: recreate DB saat schema berubah
                .build().also { INSTANCE = it }
            }
    }
}
