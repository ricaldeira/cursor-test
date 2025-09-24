package com.radiogarden.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "radio_locations")
data class RadioLocation(
    @PrimaryKey
    val id: String,
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val stationCount: Int = 0,
    val isSelected: Boolean = false
) {
    val displayName: String
        get() = "$name, $country"
}
