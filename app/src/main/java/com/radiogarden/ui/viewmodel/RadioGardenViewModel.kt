package com.radiogarden.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radiogarden.core.audio.AudioStreamingService
import com.radiogarden.core.domain.model.RadioStation
import com.radiogarden.core.domain.model.PlaybackInfo
import com.radiogarden.core.domain.usecase.RadioUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RadioGardenUiState(
    val stations: List<RadioStation> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val isSearchVisible: Boolean = false,
    val selectedTabIndex: Int = 0
)

@HiltViewModel
class RadioGardenViewModel @Inject constructor(
    private val radioUseCases: RadioUseCases,
    private val audioStreamingService: AudioStreamingService
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(RadioGardenUiState())
    val uiState: StateFlow<RadioGardenUiState> = _uiState.asStateFlow()
    
    val playbackInfo: StateFlow<PlaybackInfo> = audioStreamingService.playbackInfo
    
    private val _searchQuery = MutableStateFlow("")
    
    init {
        loadStations()
        observeSearchQuery()
    }
    
    private fun loadStations() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            try {
                val stations = when (_uiState.value.selectedTabIndex) {
                    0 -> radioUseCases.getAllStations().first()
                    1 -> radioUseCases.getFavoriteStations().first()
                    2 -> radioUseCases.getRecentStations().first()
                    else -> emptyList()
                }
                
                _uiState.value = _uiState.value.copy(
                    stations = stations,
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
    
    private fun observeSearchQuery() {
        viewModelScope.launch {
            _searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .collect { query ->
                    if (query.isNotEmpty()) {
                        try {
                            val stations = radioUseCases.searchStations(query).first()
                            _uiState.value = _uiState.value.copy(stations = stations)
                        } catch (e: Exception) {
                            _uiState.value = _uiState.value.copy(error = e.message)
                        }
                    } else {
                        loadStations()
                    }
                }
        }
    }
    
    fun selectTab(index: Int) {
        _uiState.value = _uiState.value.copy(selectedTabIndex = index)
        loadStations()
    }
    
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }
    
    fun toggleSearch() {
        _uiState.value = _uiState.value.copy(
            isSearchVisible = !_uiState.value.isSearchVisible,
            searchQuery = if (!_uiState.value.isSearchVisible) "" else _uiState.value.searchQuery
        )
        if (!_uiState.value.isSearchVisible) {
            _searchQuery.value = ""
        }
    }
    
    fun playStation(station: RadioStation) {
        viewModelScope.launch {
            try {
                audioStreamingService.playStation(station)
                radioUseCases.setCurrentlyPlaying(station.id)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }
    
    fun togglePlayback() {
        if (audioStreamingService.isPlaying()) {
            audioStreamingService.pause()
        } else {
            audioStreamingService.resume()
        }
    }
    
    fun stopPlayback() {
        viewModelScope.launch {
            audioStreamingService.stop()
            radioUseCases.stopPlaying()
        }
    }
    
    fun toggleFavorite(stationId: String) {
        viewModelScope.launch {
            try {
                val station = radioUseCases.getStationById(stationId)
                station?.let {
                    radioUseCases.toggleFavorite(stationId, !it.isFavorite)
                    loadStations() // Refresh the list
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }
    
    fun retry() {
        loadStations()
    }
    
    override fun onCleared() {
        super.onCleared()
        audioStreamingService.release()
    }
}
