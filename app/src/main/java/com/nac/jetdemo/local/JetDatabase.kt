package com.nac.jetdemo.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Jet::class], version = 1, exportSchema = false)
abstract class JetDatabase : RoomDatabase() {

    abstract fun jetDao(): JetDao

    companion object {
        @Volatile
        private var instance: JetDatabase? = null

        fun getInstance(context: Context): JetDatabase =
            instance ?: synchronized(this) {
                Room.databaseBuilder(context, JetDatabase::class.java, "jet-db")
                    .build().also { instance = it }
            }
    }
}