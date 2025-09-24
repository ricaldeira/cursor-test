package com.carmobileapp.mobile.presentation.home

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
class HomeViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    init {
        observeCurrentVehicle()
    }
    
    private fun observeCurrentVehicle() {
        viewModelScope.launch {
            vehicleRepository.observeCurrentVehicle().collect { vehicle ->
                _uiState.value = _uiState.value.copy(currentVehicle = vehicle)
            }
        }
    }
}

data class HomeUiState(
    val currentVehicle: Vehicle? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)