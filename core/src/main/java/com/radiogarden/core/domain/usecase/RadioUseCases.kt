package com.radiogarden.core.domain.usecase

import com.radiogarden.core.data.repository.RadioRepository
import com.radiogarden.core.domain.model.RadioStation
import com.radiogarden.core.domain.model.RadioLocation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RadioUseCases @Inject constructor(
    private val repository: RadioRepository
) {
    // Station operations
    fun getAllStations(): Flow<List<RadioStation>> = repository.getAllStations()
    
    fun getFavoriteStations(): Flow<List<RadioStation>> = repository.getFavoriteStations()
    
    fun getRecentStations(): Flow<List<RadioStation>> = repository.getRecentStations()
    
    fun getStationsByCountry(country: String): Flow<List<RadioStation>> = 
        repository.getStationsByCountry(country)
    
    fun getStationsByCity(city: String): Flow<List<RadioStation>> = 
        repository.getStationsByCity(city)
    
    fun searchStations(query: String): Flow<List<RadioStation>> = 
        repository.searchStations(query)
    
    suspend fun getStationById(id: String): RadioStation? = repository.getStationById(id)
    
    suspend fun getCurrentlyPlayingStation(): RadioStation? = repository.getCurrentlyPlayingStation()
    
    suspend fun toggleFavorite(stationId: String, isFavorite: Boolean) = 
        repository.updateFavoriteStatus(stationId, isFavorite)
    
    suspend fun setCurrentlyPlaying(stationId: String) {
        repository.clearAllPlayingStatus()
        repository.updatePlayingStatus(stationId, true)
        repository.updateLastPlayed(stationId, System.currentTimeMillis())
    }
    
    suspend fun stopPlaying() = repository.clearAllPlayingStatus()
    
    // Location operations
    fun getAllLocations(): Flow<List<RadioLocation>> = repository.getAllLocations()
    
    fun getLocationsByCountry(country: String): Flow<List<RadioLocation>> = 
        repository.getLocationsByCountry(country)
    
    fun searchLocations(query: String): Flow<List<RadioLocation>> = 
        repository.searchLocations(query)
    
    suspend fun getLocationById(id: String): RadioLocation? = repository.getLocationById(id)
    
    suspend fun getSelectedLocation(): RadioLocation? = repository.getSelectedLocation()
    
    suspend fun selectLocation(locationId: String) {
        repository.clearAllSelectedStatus()
        repository.updateSelectedStatus(locationId, true)
    }
}
