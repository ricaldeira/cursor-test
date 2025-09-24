package com.radiogarden.core.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.radiogarden.core.domain.model.RadioStation
import com.radiogarden.core.domain.model.RadioLocation

@Database(
    entities = [RadioStation::class, RadioLocation::class],
    version = 1,
    exportSchema = false
)
abstract class RadioDatabase : RoomDatabase() {
    abstract fun radioStationDao(): RadioStationDao
    abstract fun radioLocationDao(): RadioLocationDao
    
    companion object {
        @Volatile
        private var INSTANCE: RadioDatabase? = null
        
        fun getDatabase(context: Context): RadioDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RadioDatabase::class.java,
                    "radio_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
