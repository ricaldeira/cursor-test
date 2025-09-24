package com.carmobileapp.core.domain.repository

import com.carmobileapp.core.domain.model.Vehicle
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    suspend fun getVehicles(): List<Vehicle>
    suspend fun getVehicleById(id: String): Vehicle?
    suspend fun addVehicle(vehicle: Vehicle)
    suspend fun updateVehicle(vehicle: Vehicle)
    suspend fun deleteVehicle(id: String)
    suspend fun getCurrentVehicle(): Vehicle?
    suspend fun setCurrentVehicle(vehicle: Vehicle)
    fun observeCurrentVehicle(): Flow<Vehicle?>
    suspend fun connectToVehicle(vehicleId: String): Boolean
    suspend fun disconnectFromVehicle()
}