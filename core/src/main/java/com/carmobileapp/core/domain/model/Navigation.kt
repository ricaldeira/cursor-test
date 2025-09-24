package com.carmobileapp.core.domain.model

data class Location(
    val latitude: Double,
    val longitude: Double,
    val address: String? = null,
    val name: String? = null
)

data class Route(
    val id: String,
    val startLocation: Location,
    val endLocation: Location,
    val waypoints: List<Location> = emptyList(),
    val distance: Double, // in kilometers
    val estimatedTime: Long, // in minutes
    val trafficConditions: TrafficCondition = TrafficCondition.NORMAL
)

data class NavigationSession(
    val id: String,
    val route: Route,
    val currentLocation: Location,
    val isActive: Boolean = false,
    val startTime: Long? = null,
    val estimatedArrival: Long? = null
)

enum class TrafficCondition {
    LIGHT, NORMAL, HEAVY, SEVERE
}