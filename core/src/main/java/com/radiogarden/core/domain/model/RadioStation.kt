package com.radiogarden.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "radio_stations")
data class RadioStation(
    @PrimaryKey
    val id: String,
    val name: String,
    val country: String,
    val city: String,
    val streamUrl: String,
    val website: String? = null,
    val description: String? = null,
    val genre: String? = null,
    val language: String? = null,
    val latitude: Double,
    val longitude: Double,
    val isFavorite: Boolean = false,
    val isPlaying: Boolean = false,
    val lastPlayed: Long = 0L,
    val imageUrl: String? = null,
    val bitrate: Int? = null,
    val codec: String? = null
) {
    val displayName: String
        get() = "$name - $city, $country"
    
    val location: String
        get() = "$city, $country"
}
