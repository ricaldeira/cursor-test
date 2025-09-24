package com.radiogarden.core.di

import android.content.Context
import com.radiogarden.core.audio.AudioStreamingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AudioModule {
    
    @Provides
    @Singleton
    fun provideAudioStreamingService(@ApplicationContext context: Context): AudioStreamingService {
        return AudioStreamingService(context)
    }
}
