package com.carmobileapp.mobile.presentation.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carmobileapp.core.domain.model.Location
import com.carmobileapp.core.domain.repository.NavigationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val navigationRepository: NavigationRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(NavigationUiState())
    val uiState: StateFlow<NavigationUiState> = _uiState.asStateFlow()
    
    fun updateSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
        if (query.isNotEmpty()) {
            searchLocations(query)
        } else {
            _uiState.value = _uiState.value.copy(searchResults = emptyList())
        }
    }
    
    private fun searchLocations(query: String) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)
                val results = navigationRepository.searchLocation(query)
                _uiState.value = _uiState.value.copy(
                    searchResults = results,
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
}

data class NavigationUiState(
    val searchQuery: String = "",
    val searchResults: List<Location> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)