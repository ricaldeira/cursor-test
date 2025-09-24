package com.radiogarden.core.data.repository

import com.radiogarden.core.data.database.RadioStationDao
import com.radiogarden.core.data.database.RadioLocationDao
import com.radiogarden.core.domain.model.RadioStation
import com.radiogarden.core.domain.model.RadioLocation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RadioRepository @Inject constructor(
    private val radioStationDao: RadioStationDao,
    private val radioLocationDao: RadioLocationDao
) {
    // Radio Stations
    fun getAllStations(): Flow<List<RadioStation>> = radioStationDao.getAllStations()
    
    fun getFavoriteStations(): Flow<List<RadioStation>> = radioStationDao.getFavoriteStations()
    
    fun getRecentStations(): Flow<List<RadioStation>> = radioStationDao.getRecentStations()
    
    fun getStationsByCountry(country: String): Flow<List<RadioStation>> = 
        radioStationDao.getStationsByCountry(country)
    
    fun getStationsByCity(city: String): Flow<List<RadioStation>> = 
        radioStationDao.getStationsByCity(city)
    
    fun searchStations(query: String): Flow<List<RadioStation>> = 
        radioStationDao.searchStations(query)
    
    suspend fun getStationById(id: String): RadioStation? = radioStationDao.getStationById(id)
    
    suspend fun getCurrentlyPlayingStation(): RadioStation? = radioStationDao.getCurrentlyPlayingStation()
    
    suspend fun insertStation(station: RadioStation) = radioStationDao.insertStation(station)
    
    suspend fun insertStations(stations: List<RadioStation>) = radioStationDao.insertStations(stations)
    
    suspend fun updateStation(station: RadioStation) = radioStationDao.updateStation(station)
    
    suspend fun updateFavoriteStatus(id: String, isFavorite: Boolean) = 
        radioStationDao.updateFavoriteStatus(id, isFavorite)
    
    suspend fun updatePlayingStatus(id: String, isPlaying: Boolean) = 
        radioStationDao.updatePlayingStatus(id, isPlaying)
    
    suspend fun updateLastPlayed(id: String, timestamp: Long) = 
        radioStationDao.updateLastPlayed(id, timestamp)
    
    suspend fun clearAllPlayingStatus() = radioStationDao.clearAllPlayingStatus()
    
    // Radio Locations
    fun getAllLocations(): Flow<List<RadioLocation>> = radioLocationDao.getAllLocations()
    
    fun getLocationsByCountry(country: String): Flow<List<RadioLocation>> = 
        radioLocationDao.getLocationsByCountry(country)
    
    fun searchLocations(query: String): Flow<List<RadioLocation>> = 
        radioLocationDao.searchLocations(query)
    
    suspend fun getLocationById(id: String): RadioLocation? = radioLocationDao.getLocationById(id)
    
    suspend fun getSelectedLocation(): RadioLocation? = radioLocationDao.getSelectedLocation()
    
    suspend fun insertLocation(location: RadioLocation) = radioLocationDao.insertLocation(location)
    
    suspend fun insertLocations(locations: List<RadioLocation>) = radioLocationDao.insertLocations(locations)
    
    suspend fun updateLocation(location: RadioLocation) = radioLocationDao.updateLocation(location)
    
    suspend fun updateSelectedStatus(id: String, isSelected: Boolean) = 
        radioLocationDao.updateSelectedStatus(id, isSelected)
    
    suspend fun clearAllSelectedStatus() = radioLocationDao.clearAllSelectedStatus()
}
