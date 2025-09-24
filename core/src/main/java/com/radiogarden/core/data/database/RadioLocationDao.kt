package com.radiogarden.core.data.database

import androidx.room.*
import com.radiogarden.core.domain.model.RadioLocation
import kotlinx.coroutines.flow.Flow

@Dao
interface RadioLocationDao {
    @Query("SELECT * FROM radio_locations ORDER BY name ASC")
    fun getAllLocations(): Flow<List<RadioLocation>>
    
    @Query("SELECT * FROM radio_locations WHERE country = :country ORDER BY name ASC")
    fun getLocationsByCountry(country: String): Flow<List<RadioLocation>>
    
    @Query("SELECT * FROM radio_locations WHERE name LIKE '%' || :query || '%' OR country LIKE '%' || :query || '%' ORDER BY name ASC")
    fun searchLocations(query: String): Flow<List<RadioLocation>>
    
    @Query("SELECT * FROM radio_locations WHERE id = :id")
    suspend fun getLocationById(id: String): RadioLocation?
    
    @Query("SELECT * FROM radio_locations WHERE isSelected = 1 LIMIT 1")
    suspend fun getSelectedLocation(): RadioLocation?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: RadioLocation)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(locations: List<RadioLocation>)
    
    @Update
    suspend fun updateLocation(location: RadioLocation)
    
    @Query("UPDATE radio_locations SET isSelected = :isSelected WHERE id = :id")
    suspend fun updateSelectedStatus(id: String, isSelected: Boolean)
    
    @Query("UPDATE radio_locations SET isSelected = 0")
    suspend fun clearAllSelectedStatus()
    
    @Delete
    suspend fun deleteLocation(location: RadioLocation)
    
    @Query("DELETE FROM radio_locations")
    suspend fun deleteAllLocations()
}
