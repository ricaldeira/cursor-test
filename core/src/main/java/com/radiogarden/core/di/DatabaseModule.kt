package com.radiogarden.core.di

import android.content.Context
import androidx.room.Room
import com.radiogarden.core.data.database.RadioDatabase
import com.radiogarden.core.data.database.RadioStationDao
import com.radiogarden.core.data.database.RadioLocationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideRadioDatabase(@ApplicationContext context: Context): RadioDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            RadioDatabase::class.java,
            "radio_database"
        ).build()
    }
    
    @Provides
    fun provideRadioStationDao(database: RadioDatabase): RadioStationDao {
        return database.radioStationDao()
    }
    
    @Provides
    fun provideRadioLocationDao(database: RadioDatabase): RadioLocationDao {
        return database.radioLocationDao()
    }
}
