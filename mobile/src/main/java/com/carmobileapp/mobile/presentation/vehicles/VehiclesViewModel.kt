package com.carmobileapp.mobile.presentation.vehicles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carmobileapp.core.domain.model.Vehicle
import com.carmobileapp.core.domain.repository.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehiclesViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(VehiclesUiState())
    val uiState: StateFlow<VehiclesUiState> = _uiState.asStateFlow()
    
    init {
        loadVehicles()
        observeCurrentVehicle()
    }
    
    private fun loadVehicles() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val vehicles = vehicleRepository.getVehicles()
                _uiState.value = _uiState.value.copy(
                    vehicles = vehicles,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
    
    private fun observeCurrentVehicle() {
        viewModelScope.launch {
            vehicleRepository.observeCurrentVehicle().collect { vehicle ->
                _uiState.value = _uiState.value.copy(currentVehicle = vehicle)
            }
        }
    }
    
    fun setCurrentVehicle(vehicle: Vehicle) {
        viewModelScope.launch {
            try {
                vehicleRepository.setCurrentVehicle(vehicle)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }
    
    fun deleteVehicle(vehicleId: String) {
        viewModelScope.launch {
            try {
                vehicleRepository.deleteVehicle(vehicleId)
                loadVehicles() // Refresh the list
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }
}

data class VehiclesUiState(
    val vehicles: List<Vehicle> = emptyList(),
    val currentVehicle: Vehicle? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)