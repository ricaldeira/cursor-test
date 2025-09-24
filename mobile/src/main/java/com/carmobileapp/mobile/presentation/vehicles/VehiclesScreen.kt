package com.carmobileapp.mobile.presentation.vehicles

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehiclesScreen(
    onNavigateBack: () -> Unit,
    viewModel: VehiclesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Vehicles") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Add vehicle */ }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Vehicle")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Add vehicle */ }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Vehicle")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (uiState.vehicles.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Default.DirectionsCar,
                                contentDescription = null,
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                "No vehicles added yet",
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(
                                "Add your first vehicle to get started",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            } else {
                items(uiState.vehicles) { vehicle ->
                    VehicleCard(
                        vehicle = vehicle,
                        isCurrentVehicle = vehicle.id == uiState.currentVehicle?.id,
                        onSetAsCurrent = { viewModel.setCurrentVehicle(vehicle) },
                        onEdit = { /* TODO: Edit vehicle */ },
                        onDelete = { viewModel.deleteVehicle(vehicle.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun VehicleCard(
    vehicle: com.carmobileapp.core.domain.model.Vehicle,
    isCurrentVehicle: Boolean,
    onSetAsCurrent: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        "${vehicle.make} ${vehicle.model}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "${vehicle.year} • ${vehicle.color}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        vehicle.licensePlate,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                
                if (isCurrentVehicle) {
                    Badge {
                        Text("Current")
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if (!isCurrentVehicle) {
                    TextButton(onClick = onSetAsCurrent) {
                        Text("Set as Current")
                    }
                }
                
                TextButton(onClick = onEdit) {
                    Text("Edit")
                }
                
                TextButton(onClick = onDelete) {
                    Text("Delete")
                }
            }
        }
    }
}