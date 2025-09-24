package com.carmobileapp.core.data.repository

import com.carmobileapp.core.domain.model.Vehicle
import com.carmobileapp.core.domain.repository.VehicleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehicleRepositoryImpl @Inject constructor() : VehicleRepository {
    
    private val _vehicles = MutableStateFlow<List<Vehicle>>(emptyList())
    private val _currentVehicle = MutableStateFlow<Vehicle?>(null)
    
    override suspend fun getVehicles(): List<Vehicle> {
        return _vehicles.value
    }
    
    override suspend fun getVehicleById(id: String): Vehicle? {
        return _vehicles.value.find { it.id == id }
    }
    
    override suspend fun addVehicle(vehicle: Vehicle) {
        val currentVehicles = _vehicles.value.toMutableList()
        currentVehicles.add(vehicle)
        _vehicles.value = currentVehicles
    }
    
    override suspend fun updateVehicle(vehicle: Vehicle) {
        val currentVehicles = _vehicles.value.toMutableList()
        val index = currentVehicles.indexOfFirst { it.id == vehicle.id }
        if (index != -1) {
            currentVehicles[index] = vehicle
            _vehicles.value = currentVehicles
        }
    }
    
    override suspend fun deleteVehicle(id: String) {
        val currentVehicles = _vehicles.value.toMutableList()
        currentVehicles.removeAll { it.id == id }
        _vehicles.value = currentVehicles
        
        // If the deleted vehicle was the current one, clear it
        if (_currentVehicle.value?.id == id) {
            _currentVehicle.value = null
        }
    }
    
    override suspend fun getCurrentVehicle(): Vehicle? {
        return _currentVehicle.value
    }
    
    override suspend fun setCurrentVehicle(vehicle: Vehicle) {
        _currentVehicle.value = vehicle
    }
    
    override fun observeCurrentVehicle(): Flow<Vehicle?> {
        return _currentVehicle.asStateFlow()
    }
    
    override suspend fun connectToVehicle(vehicleId: String): Boolean {
        val vehicle = getVehicleById(vehicleId)
        return if (vehicle != null) {
            val updatedVehicle = vehicle.copy(
                isConnected = true,
                lastConnected = System.currentTimeMillis()
            )
            updateVehicle(updatedVehicle)
            true
        } else {
            false
        }
    }
    
    override suspend fun disconnectFromVehicle() {
        val currentVehicle = _currentVehicle.value
        if (currentVehicle != null) {
            val updatedVehicle = currentVehicle.copy(isConnected = false)
            updateVehicle(updatedVehicle)
        }
    }
}