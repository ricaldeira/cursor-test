package com.radiogarden.core.data.database

import androidx.room.*
import com.radiogarden.core.domain.model.RadioStation
import kotlinx.coroutines.flow.Flow

@Dao
interface RadioStationDao {
    @Query("SELECT * FROM radio_stations ORDER BY name ASC")
    fun getAllStations(): Flow<List<RadioStation>>
    
    @Query("SELECT * FROM radio_stations WHERE isFavorite = 1 ORDER BY lastPlayed DESC")
    fun getFavoriteStations(): Flow<List<RadioStation>>
    
    @Query("SELECT * FROM radio_stations WHERE lastPlayed > 0 ORDER BY lastPlayed DESC LIMIT 20")
    fun getRecentStations(): Flow<List<RadioStation>>
    
    @Query("SELECT * FROM radio_stations WHERE country = :country ORDER BY name ASC")
    fun getStationsByCountry(country: String): Flow<List<RadioStation>>
    
    @Query("SELECT * FROM radio_stations WHERE city = :city ORDER BY name ASC")
    fun getStationsByCity(city: String): Flow<List<RadioStation>>
    
    @Query("SELECT * FROM radio_stations WHERE name LIKE '%' || :query || '%' OR city LIKE '%' || :query || '%' OR country LIKE '%' || :query || '%' ORDER BY name ASC")
    fun searchStations(query: String): Flow<List<RadioStation>>
    
    @Query("SELECT * FROM radio_stations WHERE id = :id")
    suspend fun getStationById(id: String): RadioStation?
    
    @Query("SELECT * FROM radio_stations WHERE isPlaying = 1 LIMIT 1")
    suspend fun getCurrentlyPlayingStation(): RadioStation?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStation(station: RadioStation)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStations(stations: List<RadioStation>)
    
    @Update
    suspend fun updateStation(station: RadioStation)
    
    @Query("UPDATE radio_stations SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: String, isFavorite: Boolean)
    
    @Query("UPDATE radio_stations SET isPlaying = :isPlaying WHERE id = :id")
    suspend fun updatePlayingStatus(id: String, isPlaying: Boolean)
    
    @Query("UPDATE radio_stations SET lastPlayed = :timestamp WHERE id = :id")
    suspend fun updateLastPlayed(id: String, timestamp: Long)
    
    @Query("UPDATE radio_stations SET isPlaying = 0")
    suspend fun clearAllPlayingStatus()
    
    @Delete
    suspend fun deleteStation(station: RadioStation)
    
    @Query("DELETE FROM radio_stations")
    suspend fun deleteAllStations()
}
