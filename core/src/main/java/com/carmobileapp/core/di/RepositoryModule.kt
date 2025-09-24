package com.carmobileapp.core.di

import com.carmobileapp.core.data.repository.NavigationRepositoryImpl
import com.carmobileapp.core.data.repository.UserRepositoryImpl
import com.carmobileapp.core.data.repository.VehicleRepositoryImpl
import com.carmobileapp.core.domain.repository.NavigationRepository
import com.carmobileapp.core.domain.repository.UserRepository
import com.carmobileapp.core.domain.repository.VehicleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
    
    @Binds
    @Singleton
    abstract fun bindVehicleRepository(
        vehicleRepositoryImpl: VehicleRepositoryImpl
    ): VehicleRepository
    
    @Binds
    @Singleton
    abstract fun bindNavigationRepository(
        navigationRepositoryImpl: NavigationRepositoryImpl
    ): NavigationRepository
}