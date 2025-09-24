package com.carmobileapp.car.di

import com.carmobileapp.car.CarSessionFactory
import com.carmobileapp.car.presentation.CarHomeScreenFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CarModule {
    
    @Provides
    @Singleton
    fun provideCarSessionFactory(): CarSessionFactory {
        return CarSessionFactory()
    }
    
    @Provides
    @Singleton
    fun provideCarHomeScreenFactory(): CarHomeScreenFactory {
        return CarHomeScreenFactory()
    }
}