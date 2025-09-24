package com.carmobileapp.core.domain.repository

import com.carmobileapp.core.domain.model.Location
import com.carmobileapp.core.domain.model.NavigationSession
import com.carmobileapp.core.domain.model.Route
import kotlinx.coroutines.flow.Flow

interface NavigationRepository {
    suspend fun searchLocation(query: String): List<Location>
    suspend fun getCurrentLocation(): Location?
    suspend fun calculateRoute(start: Location, end: Location, waypoints: List<Location> = emptyList()): Route?
    suspend fun startNavigation(route: Route): NavigationSession
    suspend fun stopNavigation()
    suspend fun updateCurrentLocation(location: Location)
    fun observeNavigationSession(): Flow<NavigationSession?>
    fun observeCurrentLocation(): Flow<Location?>
}