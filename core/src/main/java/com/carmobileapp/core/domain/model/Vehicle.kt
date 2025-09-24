package com.carmobileapp.core.domain.model

data class Vehicle(
    val id: String,
    val make: String,
    val model: String,
    val year: Int,
    val color: String,
    val licensePlate: String,
    val vin: String? = null,
    val fuelType: FuelType = FuelType.GASOLINE,
    val isConnected: Boolean = false,
    val lastConnected: Long? = null
)

enum class FuelType {
    GASOLINE, DIESEL, ELECTRIC, HYBRID, PLUGIN_HYBRID
}
